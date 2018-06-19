package com.xmg.pss.web.action;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.opensymphony.xwork2.ActionContext;
import com.xmg.pss.domain.Department;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.DepartmentQueryObject;
import com.xmg.pss.service.IDepartmentService;
import com.xmg.pss.util.RequiredPermission;

public class DepartmentAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Getter
	private Department dept = new Department();
	@Setter
	private IDepartmentService deptService;
	
	@Getter
	private DepartmentQueryObject qo=new DepartmentQueryObject();

	@RequiredPermission("部门列表")
	public String execute() throws Exception {
	 PageResult result =deptService.pageQuery(qo);
		//将数据添加到值栈中
		ActionContext.getContext().put("result", result);
		return LIST;
	}

	@RequiredPermission("部门删除")
	public String delete() throws Exception {
		if (dept.getId() != null) {
			deptService.delete(dept.getId());
			putMsg("删除成功");
		}
		return NONE;
	}

	@RequiredPermission("部门编辑")
	public String input() throws Exception {
		if (dept.getId() != null) {
			dept = deptService.get(dept.getId());
		}
		return INPUT;
	}

	@RequiredPermission("部门保存或更新")
	public String saveOrUpdate() throws Exception {
		if (dept.getId() != null) {
			deptService.update(dept);
			addActionMessage("修改成功");
		} else {
			deptService.save(dept);
			addActionMessage("新增成功");
		}
		return SUCCESS;
	}
}
