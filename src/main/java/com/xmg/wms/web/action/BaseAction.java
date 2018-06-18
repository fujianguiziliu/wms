package com.xmg.wms.web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

//通用的action类
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	public static final String LIST = "list";
	public void putMsg(String msg) throws Exception{
		ServletActionContext.getResponse().setContentType("test/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(msg);
	}
}
