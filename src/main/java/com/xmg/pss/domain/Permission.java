package com.xmg.pss.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Permission extends BasicDomain {

	private static final long serialVersionUID = 1L;

	//给权限一个描述信息:员工列表
	private String name;
	//权限表达式:com.xmg.pss.web.action.DepartmentAction:execute
	private String expression;

	@Override
	public String toString() {
		return "Permission [name=" + name + ", expression=" + expression + "]";
	}

}
