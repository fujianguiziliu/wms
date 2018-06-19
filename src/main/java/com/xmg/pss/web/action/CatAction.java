package com.xmg.pss.web.action;

import com.xmg.pss.domain.Cat;
import com.xmg.pss.query.CatQueryObject;
import com.xmg.pss.service.ICatService;
import com.xmg.pss.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class CatAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private ICatService catService;

	@Getter
	private CatQueryObject qo=new CatQueryObject();

	@Getter
	private Cat cat = new Cat();
	
	@RequiredPermission("小猫列表")
	public String execute(){
		try {
			putContext("result", catService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("小猫编辑")
	public String input() {
		try {
			if (cat.getId() != null) {
                cat = catService.get(cat.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("小猫保存/更新")
	public String saveOrUpdate() {
		try {
			if (cat.getId() == null) {
                catService.save(cat);
				addActionMessage("增加成功");
            } else {
                catService.update(cat);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("小猫删除")
	public String delete()  throws  Exception {
		try {
			if (cat.getId() != null) {
                catService.delete(cat.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

}
