package com.xmg.pss.service;

import java.util.List;

import com.xmg.pss.domain.Department;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;

public interface IDepartmentService {
	void save(Department dept);

	void delete(Long id);

	void update(Department dept);

	Department get(Long id);

	List<Department> list();
	
	PageResult pageQuery(QueryObject qo);
}
