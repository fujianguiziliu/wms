package com.xmg.pss.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmg.pss.domain.Employee;

public interface EmployeeMapper extends BasicMapper<Employee> {
	//将所在部门的员工的部门编号更新为null
	void updateByDeptId(Long deptId);

	void updateRelation(@Param("eid") Long eid, @Param("rid") Long rid);

	void deleteRelation(Long id);
	
	void batchDeleteRelation(List<Long> ids);

	Employee checkLogin(@Param("username") String username,
			@Param("password") String password);
}
