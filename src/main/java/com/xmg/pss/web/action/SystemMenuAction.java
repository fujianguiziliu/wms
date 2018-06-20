package com.xmg.pss.web.action;

import com.xmg.pss.domain.SystemMenu;
import com.xmg.pss.query.SystemMenuQueryObject;
import com.xmg.pss.service.ISystemMenuService;
import com.xmg.pss.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class SystemMenuAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private ISystemMenuService systemMenuService;

	@Getter
	private SystemMenuQueryObject qo=new SystemMenuQueryObject();

	@Getter
	private SystemMenu systemMenu = new SystemMenu();
	
	@RequiredPermission("系统菜单列表")
	public String execute(){
		try {
			putContext("result", systemMenuService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("系统菜单编辑")
	public String input() {
		try {
			if (systemMenu.getId() != null) {
                systemMenu = systemMenuService.get(systemMenu.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("系统菜单保存/更新")
	public String saveOrUpdate() {
		try {
			if (systemMenu.getId() == null) {
                systemMenuService.save(systemMenu);
				addActionMessage("增加成功");
            } else {
                systemMenuService.update(systemMenu);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("系统菜单删除")
	public String delete()  throws  Exception {
		try {
			if (systemMenu.getId() != null) {
                systemMenuService.delete(systemMenu.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

}
