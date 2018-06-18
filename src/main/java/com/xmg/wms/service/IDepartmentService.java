package com.xmg.wms.service;

import java.util.List;

import com.xmg.wms.domain.Department;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.QueryObject;

public interface IDepartmentService {
	void save(Department dept);

	void delete(Long id);

	void update(Department dept);

	Department get(Long id);

	List<Department> list();
	
	PageResult pageQuery(QueryObject qo);
}
