package com.xmg.pss.service.impl;

import java.util.Collections;
import java.util.List;

import lombok.Setter;

import com.xmg.pss.domain.Department;
import com.xmg.pss.mapper.DepartmentMapper;
import com.xmg.pss.mapper.EmployeeMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;
import com.xmg.pss.service.IDepartmentService;

public class DepartmentServiceImpl implements IDepartmentService {

	@Setter
	private DepartmentMapper deptMapper;

	@Setter
	private EmployeeMapper empMapper;

	@Override
	public void save(Department dept) {
		deptMapper.save(dept);
	}

	@Override
	public void delete(Long id) {
		//在删除对应部门之前,需要将其关联的数据先删掉或者是将其外键列设置null
		empMapper.updateByDeptId(id);
		
		deptMapper.delete(id);
	}

	@Override
	public void update(Department dept) {
		deptMapper.update(dept);
	}

	@Override
	public Department get(Long id) {
		return deptMapper.get(id);
	}

	@Override
	public List<Department> list() {
		return deptMapper.list();
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long count=deptMapper.getTotalCount(qo);
		if (count ==0) {
			return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
		}
		List<Department> listData=deptMapper.pageQuery(qo);
		return new PageResult(listData, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
	}

}
