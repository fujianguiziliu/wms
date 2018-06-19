package com.xmg.pss.test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmg.pss.domain.Role;
import com.xmg.pss.service.IRoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RoleServiceImplTest {

	@Autowired
	private IRoleService roleService;

	@Test
	public void testSave() {
		Role r = new Role();
		r.setName("超级管理员");
		r.setSn("SUPER_MGR");

		roleService.save(r);
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testList() {
		fail("Not yet implemented");
	}

	@Test
	public void testPageQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetRoleMapper() {
		fail("Not yet implemented");
	}

}
