package com.xmg.pss.service;

import java.util.List;

import com.xmg.pss.domain.Employee;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;

public interface IEmployeeService {
	void save(Employee e);

	void delete(Long id);

	void batchDelete(List<Long> ids);
	
	void update(Employee e);

	Employee get(Long id);

	List<Employee> list();

	//高级查询+分页
	PageResult pageQuery(QueryObject qo);

	Employee checkLogin(String username,
			String password);
}
