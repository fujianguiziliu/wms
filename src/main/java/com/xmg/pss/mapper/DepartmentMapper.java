package com.xmg.pss.mapper;

import java.util.List;

import com.xmg.pss.domain.Department;
import com.xmg.pss.query.QueryObject;

public interface DepartmentMapper {
	void save(Department dept);

	void delete(Long id);

	void update(Department dept);

	Department get(Long id);

	List<Department> list();

	Long getTotalCount(QueryObject qo);

	List<Department> pageQuery(QueryObject qo);
}
