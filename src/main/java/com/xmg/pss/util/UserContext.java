package com.xmg.pss.util;

import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.xmg.pss.domain.Employee;

public class UserContext {

	public static void setCurrentUser(Employee employee ){
		
		ActionContext.getContext().getSession().put("EMPLOYEE_IN_SESSION", employee);
	}
	public static Employee getCurrentUser(){
		
		return (Employee) ActionContext.getContext().getSession().get("EMPLOYEE_IN_SESSION");
	}
	
	
public static void setCurrentPermissionSet(Set<String> permissionSet){
		
		ActionContext.getContext().getSession().put("PERMISSION_IN_SESSION", permissionSet);
	}
	public static Set<String> getCurrentPermissionSet(){
		
		return (Set<String>) ActionContext.getContext().getSession().get("PERMISSION_IN_SESSION");
	}
	
	
}
