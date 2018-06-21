package com.xmg.pss.service.impl;

import java.util.Collections;
import java.util.List;

import lombok.Setter;

import com.xmg.pss.domain.Permission;
import com.xmg.pss.domain.Role;
import com.xmg.pss.domain.SystemMenu;
import com.xmg.pss.mapper.RoleMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;
import com.xmg.pss.service.IRoleService;

public class RoleServiceImpl implements IRoleService {

	@Setter
	private RoleMapper roleMapper;

	@Override
	public void save(Role r) {
		roleMapper.save(r);
		//把角色对应的权限保存到中间表中role_permission
		List<Permission> permissions = r.getPermissions();
		for (Permission permission : permissions) {
			roleMapper.updatePermissionRelation(r.getId(), permission.getId());
		}
		List<SystemMenu> menus = r.getMenus();
		for (SystemMenu menu : menus) {
			roleMapper.updateMenuRelation(r.getId(), menu.getId());
		}
	}

	@Override
	public void delete(Long id) {
		roleMapper.deletePermissionRelation(id);
		roleMapper.deleteMenuRelation(id);
		roleMapper.delete(id);
	}

	@Override
	public void update(Role r) {
		roleMapper.update(r);
		//需要将角色对应的最新的权限更新到中间表中
		//将当前的角色的所有的权限删除,然后再将本次操作中的权限保存进去
		roleMapper.deletePermissionRelation(r.getId());
		roleMapper.deleteMenuRelation(r.getId());
		//把角色对应的权限保存到中间表中role_permission
		List<Permission> permissions = r.getPermissions();
		for (Permission permission : permissions) {
			roleMapper.updatePermissionRelation(r.getId(), permission.getId());
		}
		List<SystemMenu> menus = r.getMenus();
		for (SystemMenu menu : menus) {
			roleMapper.updateMenuRelation(r.getId(), menu.getId());
		}
	}

	@Override
	public Role get(Long id) {
		return roleMapper.get(id);
	}

	@Override
	public List<Role> list() {
		return roleMapper.list();
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCount = roleMapper.getTotalCount(qo);
		if (totalCount == 0) {
			return new PageResult(Collections.EMPTY_LIST, 0, 1,
					qo.getPageSize());
		}
		List<Role> listData = roleMapper.pageQuery(qo);
		return new PageResult(listData, totalCount.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
	}
}
