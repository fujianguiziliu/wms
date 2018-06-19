package com.xmg.pss.web.interceptor;

import java.lang.reflect.Method;
import java.util.Set;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xmg.pss.domain.Employee;
import com.xmg.pss.util.RequiredPermission;

@SuppressWarnings("unchecked")
public class SecutityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		//如果用户是超级管理员,不要校验
		Employee e = (Employee) invocation.getInvocationContext().getSession()
				.get("EMPLOYEE_IN_SESSION");
		if (e.getAdmin()) {
			return invocation.invoke();
		}
		//获取到登陆用户所有权限:session中, expressionSet
		Set<String> expressionSet = (Set<String>) invocation
				.getInvocationContext().getSession()
				.get("EXPRESSIONSET_IN_SESSION");
		//获取用户访问的是哪一个action中的哪一个method
		//actionName+ : + methodName
		Object action = invocation.getProxy().getAction();
		String actionName = action.getClass().getName();
		String methodName = invocation.getProxy().getMethod();

		//如果访问的方法上面没有requiredPermission标签,那么就不需要做校验
		Method method = action.getClass().getMethod(methodName);
		if (!method.isAnnotationPresent(RequiredPermission.class)) {
			return invocation.invoke();
		}

		//用户访问的资源的权限表达式
		String expression = actionName + ":" + methodName;

		//判断该表达式是否在expressionSet集合中
		//在:放行
		//不在:跳转到对应页面中
		if (expressionSet.contains(expression)) {
			return invocation.invoke();
		}
		return "nopermission";
	}
}
