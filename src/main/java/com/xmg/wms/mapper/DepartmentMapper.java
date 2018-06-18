package com.xmg.wms.mapper;

import java.util.List;

import com.xmg.wms.domain.Department;
import com.xmg.wms.query.QueryObject;

public interface DepartmentMapper {
	void save(Department dept);

	void delete(Long id);

	void update(Department dept);

	Department get(Long id);

	List<Department> list();

	Long getTotalCount(QueryObject qo);

	List<Department> pageQuery(QueryObject qo);
}
