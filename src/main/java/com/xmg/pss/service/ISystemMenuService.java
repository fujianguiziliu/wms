package com.xmg.pss.service;
import java.util.List;
import com.xmg.pss.domain.SystemMenu;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.SystemMenuQueryObject;
import com.xmg.pss.vo.SystemMenuVO;

public interface ISystemMenuService {
	void delete(Long id);
	void save(SystemMenu entity);
    SystemMenu get(Long id);
    List<SystemMenu> list();
	void update(SystemMenu entity);
	PageResult pageQuery(SystemMenuQueryObject qo);
	List<SystemMenuVO> queryParentListById(Long parentId);
}
