package com.xmg.wms.web.action;

import com.xmg.wms.domain.Dog;
import com.xmg.wms.query.DogQueryObject;
import com.xmg.wms.service.IDogService;
import com.xmg.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class DogAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IDogService dogService;

	@Getter
	private DogQueryObject qo=new DogQueryObject();

	@Getter
	private Dog dog = new Dog();
	
	@RequiredPermission("小狗列表")
	public String execute(){
		try {
			putContext("result", dogService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("小狗编辑")
	public String input() {
		try {
			if (dog.getId() != null) {
                dog = dogService.get(dog.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("小狗保存/更新")
	public String saveOrUpdate() {
		try {
			if (dog.getId() == null) {
                dogService.save(dog);
				addActionMessage("增加成功");
            } else {
                dogService.update(dog);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("小狗删除")
	public String delete()  throws  Exception {
		try {
			if (dog.getId() != null) {
                dogService.delete(dog.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

}
