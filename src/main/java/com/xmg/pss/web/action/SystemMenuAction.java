package com.xmg.pss.web.action;

import java.util.ArrayList;
import java.util.List;

import com.xmg.pss.domain.SystemMenu;
import com.xmg.pss.query.SystemMenuQueryObject;
import com.xmg.pss.service.ISystemMenuService;
import com.xmg.pss.util.RequiredPermission;
import com.xmg.pss.vo.SystemMenuVO;

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
	
	@Setter
	private String parentName;
	@RequiredPermission("系统菜单列表")
	public String execute(){
		try {
			putContext("result", systemMenuService.pageQuery(qo));
			List<SystemMenuVO> parentList=systemMenuService.queryParentListById(qo.getParentId());
			putContext("parentList", parentList);
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("系统菜单编辑")
	public String input() throws  Exception{
		//根据qo.parentId查找父级菜单
		if (qo.getParentId() != null && qo.getParentId()>0) {
			SystemMenu parent = systemMenuService.get(qo.getParentId());
			putContext("parentName", parent.getName());
			//systemMenu.setParent(parent);
		}else {
			putContext("parentName", "根目录");
			//systemMenu.setParent(null);
		}
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
		if (qo.getParentId() != null && qo.getParentId()>0) {
			SystemMenu parent = systemMenuService.get(qo.getParentId());
			//putContext("parentName", parent.getName());
			systemMenu.setParent(parent);
		}else {
			//putContext("parentName", "根目录");
			systemMenu.setParent(null);
		}
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
