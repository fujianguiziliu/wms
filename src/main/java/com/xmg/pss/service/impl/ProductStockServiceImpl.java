package com.xmg.pss.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.pss.domain.ProductStock;
import com.xmg.pss.mapper.ProductStockMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.ProductStockQueryObject;
import com.xmg.pss.service.IProductStockService;
import lombok.Setter;
public class ProductStockServiceImpl implements IProductStockService {
	@Setter
	private ProductStockMapper productStockMapper;
	
	public void  delete(Long id) {
		  productStockMapper.delete(id);
	}

	public void save(ProductStock entity) {
		  productStockMapper.save(entity);
	}

	public ProductStock get(Long id) {
		return productStockMapper.get(id);
	}

	public List<ProductStock> list() {
		return productStockMapper.list();
	}

	public void update(ProductStock entity) {
		  productStockMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(ProductStockQueryObject qo) {
		Long count = productStockMapper.getTotalCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<ProductStock> result = productStockMapper.pageQuery(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}
}
