package com.xmg.pss.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmg.pss.domain.Department;
import com.xmg.pss.domain.Employee;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.EmployeeQueryObject;
import com.xmg.pss.service.IEmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceImplTest {

	@Autowired
	private IEmployeeService empService;

	@Test
	public void testSave() throws Exception {
		Employee e = new Employee();
		e.setName("Will");
		e.setAdmin(true);
		e.setAge(17);
		Department dept = new Department();
		dept.setId(20L);
		e.setDept(dept);
		e.setEmail("Will@qq.com");
		e.setPassword("1234");
		empService.save(e);
	}

	@Test
	public void testDelete() throws Exception {
		empService.delete(18L);
	}

	@Test
	public void testUpdate() throws Exception {
		Employee e = new Employee();
		e.setName("Neld");
		e.setAdmin(true);
		e.setAge(18);
		Department dept = new Department();
		dept.setId(20L);
		e.setDept(dept);
		e.setEmail("Neld@qq.com");
		e.setPassword("1234");
		e.setId(1L);
		empService.update(e);
	}

	@Test
	public void testGet() throws Exception {
		Employee e = empService.get(1L);
		System.out.println(e.getDept());
		System.out.println(e);
	}

	@Test
	public void testList() throws Exception {
		List<Employee> list = empService.list();
		for (Employee e : list) {
			System.out.println(e);

		}
	}

	@Test
	public void testPageQuery() throws Exception {
		EmployeeQueryObject qo = new EmployeeQueryObject();
		qo.setKeyword("qq");
		qo.setDeptId(222L);
		PageResult result = empService.pageQuery(qo);
		
		System.out.println(result);
	}
}
