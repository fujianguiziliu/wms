package com.xmg.pss.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.pss.domain.SystemMenu;
import com.xmg.pss.mapper.SystemMenuMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.SystemMenuQueryObject;
import com.xmg.pss.service.ISystemMenuService;
import lombok.Setter;
public class SystemMenuServiceImpl implements ISystemMenuService {
	@Setter
	private SystemMenuMapper systemMenuMapper;
	
	public void  delete(Long id) {
		  systemMenuMapper.delete(id);
	}

	public void save(SystemMenu entity) {
		  systemMenuMapper.save(entity);
	}

	public SystemMenu get(Long id) {
		return systemMenuMapper.get(id);
	}

	public List<SystemMenu> list() {
		return systemMenuMapper.list();
	}

	public void update(SystemMenu entity) {
		  systemMenuMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(SystemMenuQueryObject qo) {
		Long count = systemMenuMapper.getTotalCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<SystemMenu> result = systemMenuMapper.pageQuery(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}
}
