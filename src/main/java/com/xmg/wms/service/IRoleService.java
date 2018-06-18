package com.xmg.wms.service;

import java.util.List;

import com.xmg.wms.domain.Role;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.QueryObject;

public interface IRoleService {
	void save(Role p);

	void delete(Long id);

	void update(Role r);

	Role get(Long id);

	List<Role> list();

	//高级查询+分页
	PageResult pageQuery(QueryObject qo);

}