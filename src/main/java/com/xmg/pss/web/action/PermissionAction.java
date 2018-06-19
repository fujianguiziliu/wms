package com.xmg.pss.web.action;

import lombok.Getter;
import lombok.Setter;

import com.opensymphony.xwork2.ActionContext;
import com.xmg.pss.domain.Permission;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;
import com.xmg.pss.service.IPermissionService;

public class PermissionAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Getter
	private Permission permission = new Permission();
	@Setter
	private IPermissionService permissionService;
	/*@Setter
	private String repassword;*/
	@Getter
	private QueryObject qo = new QueryObject();

	@Override
	public String execute() throws Exception {
		PageResult result = permissionService.pageQuery(qo);
		//将数据添加到值栈中
		ActionContext.getContext().put("result", result);
		return LIST;
	}

	public String delete() throws Exception {
		if (permission.getId() != null) {
			permissionService.delete(permission.getId());
			putMsg("删除成功");
		}
		return NONE;
	}

	public String reload() throws Exception{
		try {
			permissionService.reload();
			putMsg("加载成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			putMsg("加载失败，请联系管理员");
		}
		return NONE;
	}

}
