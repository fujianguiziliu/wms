package com.xmg.pss.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.pss.domain.OrderBill;
import com.xmg.pss.domain.OrderBillItem;
import com.xmg.pss.mapper.OrderBillMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.OrderBillQueryObject;
import com.xmg.pss.service.IOrderBillService;
import com.xmg.pss.util.UserContext;

import lombok.Setter;
public class OrderBillServiceImpl implements IOrderBillService {
	@Setter
	private OrderBillMapper orderBillMapper;
	
	public void  delete(Long id) {
		//从数据库中查询当前的对象
		OrderBill bill = orderBillMapper.get(id);
		//判断状态
		if (bill.getStatus()==OrderBill.NORMAL) {
			//先删除订单明细
			orderBillMapper.deleteItemsByBillId(id);
			//再删除订单操作
			orderBillMapper.delete(id);
			
		}else {
			throw new RuntimeException("亲，该数据已审核，不允许删除哦！");
		}
		  
	}

	public void save(OrderBill entity) {
		
		//设置好录入人和录入时间
		entity.setInputUser(UserContext.getCurrentUser());
		entity.setInputTime(new Date());
		//设置审核状态为未审核状态
		entity.setStatus(OrderBill.NORMAL);
		//计算采购金额和采购总数量
		BigDecimal totalAmount=BigDecimal.ZERO;
		BigDecimal totalNumber=BigDecimal.ZERO;
		//循环遍历订单明细
		List<OrderBillItem> items=entity.getItems();
		for (OrderBillItem item : items) {
			BigDecimal number=item.getNumber();
			BigDecimal costPrice=item.getCostPrice();
			BigDecimal amount = number.multiply(costPrice).setScale(BigDecimal.ROUND_HALF_UP);
			item.setAmount(amount);
			totalAmount=totalAmount.add(amount);
			totalNumber=totalNumber.add(number); 
		}
		entity.setTotalAmount(totalAmount);
		entity.setTotalNumber(totalNumber);
		
		//保存订单明细中的数据 
		  orderBillMapper.save(entity);
		  for (OrderBillItem item : items) {
			//和采购订单关联起来
			  item.setBill(entity);
			//保存订单明细
			  orderBillMapper.saveItem(item);
		}
	}

	public OrderBill get(Long id) {
		return orderBillMapper.get(id);
	}

	public List<OrderBill> list() {
		return orderBillMapper.list();
	}

	public void update(OrderBill entity) {
		//修改的时候需要做哪些操作
		//如果是已审核的单据不给更改
		
		//1，计算采购总金额和采购总数量
		//计算采购金额和采购总数量
				BigDecimal totalAmount=BigDecimal.ZERO;
				BigDecimal totalNumber=BigDecimal.ZERO;
				//循环遍历订单明细
				List<OrderBillItem> items=entity.getItems();
				//删除订单明细的数据
				orderBillMapper.deleteItemsByBillId(entity.getId());
				
				for (OrderBillItem item : items) {
					BigDecimal number=item.getNumber();
					BigDecimal costPrice=item.getCostPrice();
					BigDecimal amount = number.multiply(costPrice).setScale(BigDecimal.ROUND_HALF_UP);
					item.setAmount(amount);
					//设置关联订单
					item.setBill(entity);
					//保存订单明细
					orderBillMapper.saveItem(item);
					totalAmount=totalAmount.add(amount);
					totalNumber=totalNumber.add(number); 
				}
				entity.setTotalAmount(totalAmount);
				entity.setTotalNumber(totalNumber);
				
		//2.订单明细中的数据（先删除数据库中的原来的订单明细。再添加新的订单明细）
		  orderBillMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(OrderBillQueryObject qo) {
		Long count = orderBillMapper.getTotalCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<OrderBill> result = orderBillMapper.pageQuery(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}

	@Override
	public void audit(OrderBill entity) {
		entity=orderBillMapper.get(entity.getId());
		if (entity.getStatus()==OrderBill.NORMAL) {
			//设置审核人和审核状态时间
			entity.setAuditor(UserContext.getCurrentUser());
			entity.setAuditTime(new Date());
			//设置审核状态
			entity.setStatus(OrderBill.AUDIT);
			orderBillMapper.audit(entity);
		}else {
			throw new RuntimeException("已审核，不能进行重复操作");
		}
		
	}
}
