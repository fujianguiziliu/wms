package com.xmg.wms.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.wms.domain.Dog;
import com.xmg.wms.mapper.DogMapper;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.DogQueryObject;
import com.xmg.wms.service.IDogService;
import lombok.Setter;
public class DogServiceImpl implements IDogService {
	@Setter
	private DogMapper dogMapper;
	
	public void  delete(Long id) {
		  dogMapper.delete(id);
	}

	public void save(Dog entity) {
		  dogMapper.save(entity);
	}

	public Dog get(Long id) {
		return dogMapper.get(id);
	}

	public List<Dog> list() {
		return dogMapper.list();
	}

	public void update(Dog entity) {
		  dogMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(DogQueryObject qo) {
		Long count = dogMapper.getTotalCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<Dog> result = dogMapper.pageQuery(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}
}
