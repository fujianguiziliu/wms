package com.xmg.pss.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee extends BasicDomain {

	private static final long serialVersionUID = -2229490946756316031L;
	private String name;
	private String password;
	private String email;
	private Integer age;
	private Boolean admin;
	//多对以的关系
	private Department dept;

	private List<Role> roles = new ArrayList<>();

	@Override
	public String toString() {
		return "Employee [name=" + name + ", password=" + password + ", email="
				+ email + ", age=" + age + ", admin=" + admin + "]";
	}
	
	public String getRoleNames(){
		if (admin) {
			return ("[超级管理员]");
		}
		if (getRoles().size() ==0) {
			return ("[暂无角色]");
		}
		
		StringBuilder sb= new StringBuilder();
		sb.append("[");
		for(Role role : getRoles()){
			sb.append(role.getName()).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
		
		
	}

}
