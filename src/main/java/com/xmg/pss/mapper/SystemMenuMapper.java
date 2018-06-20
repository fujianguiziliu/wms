package com.xmg.pss.mapper;

import com.xmg.pss.domain.SystemMenu;
import com.xmg.pss.query.SystemMenuQueryObject;
import java.util.List;

public interface SystemMenuMapper {
	void save(SystemMenu entity);
	void update(SystemMenu entity);
	void delete(Long id);
    SystemMenu get(Long id);
	List<SystemMenu> list();
    Long getTotalCount(SystemMenuQueryObject qo);
    List<SystemMenu> pageQuery(SystemMenuQueryObject qo);
}