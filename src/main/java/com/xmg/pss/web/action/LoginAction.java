package com.xmg.pss.web.action;

import lombok.Setter;

import com.xmg.pss.service.IEmployeeService;

public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Setter
	private IEmployeeService employeeService;
	@Setter
	private String username;
	@Setter
	private String password;

	public String execute() throws Exception {
		try {
			employeeService.checkLogin(username, password);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			//将错误信息添加到actionError中
			super.addActionError(e.getMessage());
			return LOGIN;
		}

	}

}
