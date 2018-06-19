package com.xmg.pss.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import javassist.expr.NewArray;

import lombok.Getter;
import lombok.Setter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.xmg.pss.domain.Department;
import com.xmg.pss.domain.Employee;
import com.xmg.pss.domain.Role;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.EmployeeQueryObject;
import com.xmg.pss.service.IDepartmentService;
import com.xmg.pss.service.IEmployeeService;
import com.xmg.pss.service.IRoleService;
import com.xmg.pss.util.RequiredPermission;

public class EmployeeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Getter
	private Employee e = new Employee();
	@Setter
	private IEmployeeService empService;
	@Setter
	private IDepartmentService deptService;
	@Setter
	private IRoleService roleService;
	
	@Setter@Getter
	private List<Long> ids=new ArrayList<>();
	@Setter
	private String repassword;
	@Getter 
	private EmployeeQueryObject qo = new EmployeeQueryObject();

	@RequiredPermission("员工列表")
	@InputConfig(methodName = "input")
	public String execute() throws Exception {
		try {
			PageResult result = empService.pageQuery(qo);
			//将数据添加到值栈中
			ActionContext.getContext().put("result", result);

			//将页面上需要的部门信息全部查询出来
			List<Department> depts = deptService.list();
			ActionContext.getContext().put("depts", depts);
			//int i=1/0;
			
		} catch (Exception e1) {
			e1.printStackTrace();
			addActionError("操作失败1，请联系管理员");
		}
		return LIST;
	}

	@RequiredPermission("员工删除")
	public String delete() throws Exception {
		try {
			if (e.getId() != null) {
				empService.delete(e.getId());
				putMsg("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败，请联系管理员");
		}
		return NONE;
	}
	
	@RequiredPermission("员工批量删除")
	public String batchDelete() throws Exception {
		try {
			if (ids.size()>0) {
				empService.batchDelete(ids);
				putMsg("删除成功");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			putMsg("批量删除失败，请联系管理员");
		}
		return NONE;
	}

	@RequiredPermission("员工编辑")
	public String input() throws Exception {
		//将页面上需要的部门信息全部查询出来
		List<Department> depts = deptService.list();
		ActionContext.getContext().put("depts", depts);
		List<Role> roles = roleService.list();
		ActionContext.getContext().put("roles", roles);
		if (e.getId() != null) {
			e = empService.get(e.getId());
		}
		return INPUT;
	}

	@RequiredPermission("员工保存或更新")
	//@InputConfig(methodName = "input")
	public String saveOrUpdate() throws Exception {
		try {
			//int i=1/0;
			if (e.getId() != null) {
				empService.update(e);
				addActionMessage("修改成功");
			} else {
				empService.save(e);
				addActionMessage("新增成功");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			addActionError("操作失败2，请联系管理员");
		}
		return SUCCESS;
	}
}
