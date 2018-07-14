package com.xmg.pss.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIDeclaration;
import com.xmg.pss.domain.Depot;
import com.xmg.pss.domain.Product;
import com.xmg.pss.domain.ProductStock;


import com.xmg.pss.domain.StockIncomeBill;
import com.xmg.pss.domain.StockIncomeBillItem;


import com.xmg.pss.mapper.ProductStockMapper;
import com.xmg.pss.mapper.StockIncomeBillMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.StockIncomeBillQueryObject;
import com.xmg.pss.service.IStockIncomeBillService;
import com.xmg.pss.util.UserContext;

import lombok.Setter;

public  class StockIncomeBillServiceImpl implements IStockIncomeBillService {
	@Setter
	private StockIncomeBillMapper stockIncomeBillMapper;
	
	@Setter
	private ProductStockMapper productStockMapper; 
	
	public void  delete(Long id) {
		stockIncomeBillMapper.deleteItemsByBillId(id);
		  stockIncomeBillMapper.delete(id);
	}

	public void save(StockIncomeBill entity) {
		//设置录入人和录入时间
		entity.setInputUser(UserContext.getCurrentUser());
		entity.setInputTime(new Date());
		//设置状态为未审核
		entity.setStatus(StockIncomeBill.NORMAL);
		//设置入库总金额个入库总数量
		BigDecimal totalNumber=BigDecimal.ZERO;
		BigDecimal totalAmount=BigDecimal.ZERO;
			//遍历入库明细，设置金额小计
		List<StockIncomeBillItem> items=entity.getItems();
		for (StockIncomeBillItem item : items) {
			BigDecimal number=item.getNumber();
			BigDecimal costPrice=item.getCostPrice();
			BigDecimal amount=number.multiply(costPrice).setScale(2);
			item.setAmount(amount);
			totalNumber=totalNumber.add(number);
			totalAmount=totalAmount.add(amount);
		}
		entity.setTotalAmount(totalAmount);
		entity.setTotalNumber(totalNumber);
		//保存入库单
		stockIncomeBillMapper.save(entity);
		
		//保存入库单明细（先关联入库单）
		 for (StockIncomeBillItem item : items) {
		item.setBill(entity);
			stockIncomeBillMapper.saveItem(item);
		}
	}

	public StockIncomeBill get(Long id) {
		return stockIncomeBillMapper.get(id);
	}

	public List<StockIncomeBill> list() {
		return stockIncomeBillMapper.list();
	}

	public void update(StockIncomeBill entity) {
		//清除出入库单明细数据
		stockIncomeBillMapper.deleteItemsByBillId(entity.getId());
		//重新计算出入库总数量和入库总金额
		BigDecimal totalNumber=BigDecimal.ZERO;
		BigDecimal totalAmount=BigDecimal.ZERO;
		List<StockIncomeBillItem> items=entity.getItems();
		for (StockIncomeBillItem item : items) {
			//对金额重新计算
			BigDecimal number=item.getNumber();
			BigDecimal salePrice=item.getCostPrice();
			BigDecimal amount=number.multiply(salePrice).setScale(2,BigDecimal.ROUND_HALF_UP);
			totalAmount = totalAmount.add(amount);
			totalNumber =totalNumber.add(number);
			item.setAmount(amount);
			//设置明细数据和销售入库里的关联关系
			item.setBill(entity);
			//重新保存入库明细数据
			stockIncomeBillMapper.saveItem(item);
		}
		//修改订单的数据
		entity.setTotalAmount(totalAmount);
		entity.setTotalNumber(totalNumber);
		  stockIncomeBillMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(StockIncomeBillQueryObject qo) {
		Long count = stockIncomeBillMapper.getTotalCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<StockIncomeBill> result = stockIncomeBillMapper.pageQuery(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}

	@Override
	public void audit(StockIncomeBill entity) {
		entity=stockIncomeBillMapper.get(entity.getId());
		if (entity.getStatus()==StockIncomeBill.NORMAL) {
			//设置审核人和审核时间
			entity.setAuditor(UserContext.getCurrentUser());
			entity.setAuditTime(new Date());
			//设置审核状态
			entity.setStatus(StockIncomeBill.AUDIT);
			//设置入库总金额和入库总数量
			BigDecimal totalNumber=BigDecimal.ZERO;
			BigDecimal totalAmount=BigDecimal.ZERO;
			//对于指定仓库的对应产品的相关的库存需要进行调整
				//遍历明细
			Depot depot =entity.getDepot();
			List<StockIncomeBillItem> items=entity.getItems();
			for (StockIncomeBillItem item : items) {
				//产品信息
				Product product=item.getProduct();
				//根据仓库id和产品id去查询对应的库存信息
				ProductStock ps =productStockMapper.queryByDepotAndProduct(depot.getId(),product.getId());
				if (ps==null) {//没有库存车信息
					ps=new ProductStock();
					ps.setDepot(depot);
					ps.setProduct(product);
					ps.setAmount(item.getAmount());
					ps.setPrice(item.getCostPrice());
					ps.setStoreNumber(item.getNumber());
					productStockMapper.save(ps);
				}else {//有库存信息
					//计算库存总金额和总数量
					ps.setAmount(ps.getAmount().add(item.getAmount()));
					ps.setStoreNumber(ps.getStoreNumber().add(item.getNumber()));
					//计算采购价格（移动加权平均）
					ps.setPrice(ps.getAmount().divide(ps.getStoreNumber(),2,BigDecimal.ROUND_HALF_UP));
				productStockMapper.update(ps);
				}
			}
			stockIncomeBillMapper.audit(entity);
			}else {
				throw new RuntimeException("已审核的单据不能进行修改");
			}
		}
		
}

