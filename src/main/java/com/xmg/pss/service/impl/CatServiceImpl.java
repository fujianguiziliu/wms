package com.xmg.pss.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.pss.domain.Cat;
import com.xmg.pss.mapper.CatMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.CatQueryObject;
import com.xmg.pss.service.ICatService;
import lombok.Setter;
public class CatServiceImpl implements ICatService {
	@Setter
	private CatMapper catMapper;
	
	public void  delete(Long id) {
		  catMapper.delete(id);
	}

	public void save(Cat entity) {
		  catMapper.save(entity);
	}

	public Cat get(Long id) {
		return catMapper.get(id);
	}

	public List<Cat> list() {
		return catMapper.list();
	}

	public void update(Cat entity) {
		  catMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(CatQueryObject qo) {
		Long count = catMapper.getTotalCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<Cat> result = catMapper.pageQuery(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}
}
