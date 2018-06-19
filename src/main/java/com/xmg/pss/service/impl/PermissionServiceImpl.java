package com.xmg.pss.service.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Setter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.xmg.pss.domain.Permission;
import com.xmg.pss.mapper.PermissionMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;
import com.xmg.pss.service.IPermissionService;
import com.xmg.pss.util.RequiredPermission;
import com.xmg.pss.web.action.BaseAction;

public class PermissionServiceImpl implements IPermissionService,
		ApplicationContextAware {

	private ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
	}

	@Setter
	private PermissionMapper permissionMapper;

	@Override
	public void save(Permission e) {
		permissionMapper.save(e);
	}

	@Override
	public void delete(Long id) {
		permissionMapper.delete(id);
	}

	@Override
	public List<Permission> list() {
		return permissionMapper.list();
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCount = permissionMapper.getTotalCount(qo);
		if (totalCount == 0) {
			return new PageResult(Collections.EMPTY_LIST, 0, 1,
					qo.getPageSize());
		}
		List<Permission> listData = permissionMapper.pageQuery(qo);
		return new PageResult(listData, totalCount.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
	}

	//获取到Spring容器中管理的所有的Action对象
	//在指定的方法上贴对应的标签:RequiredPermission
	//拼接权限表达式:actionName+:+methodName
	//将对应的权限保存到数据库中
	@Override
	public void reload() {

		//当数据库中有对应的权限就不再保存,反之保存
		List<Permission> list = permissionMapper.list();
		Set<String> expressionSet = new HashSet<>();
		for (Permission permission : list) {
			String expression = permission.getExpression();
			expressionSet.add(expression);
		}

		Map<String, BaseAction> actionsMap = ctx
				.getBeansOfType(BaseAction.class);
		Collection<BaseAction> actions = actionsMap.values();
		//获取到对应action中的方法
		for (BaseAction action : actions) {
			Method[] methods = action.getClass().getDeclaredMethods();
			//获取到action的全限定名
			String actionName = action.getClass().getName();
			//获取每个方法上的注解
			for (Method method : methods) {
				if (method.isAnnotationPresent(RequiredPermission.class)) {
					RequiredPermission rp = method
							.getAnnotation(RequiredPermission.class);
					String name = rp.value();
					//获取到方法名称
					String methodName = method.getName();
					//拼接权限表达式
					String expression = actionName + ":" + methodName;

					if (!expressionSet.contains(expression)) {
						Permission p = new Permission();
						p.setName(name);
						p.setExpression(expression);
						permissionMapper.save(p);
					}
				}
			}
		}

	}

}
