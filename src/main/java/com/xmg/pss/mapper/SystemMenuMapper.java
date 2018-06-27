package com.xmg.pss.mapper;

import com.xmg.pss.domain.SystemMenu;
import com.xmg.pss.query.SystemMenuQueryObject;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SystemMenuMapper {
	void save(SystemMenu entity);
	void update(SystemMenu entity);
	void delete(Long id);
    SystemMenu get(Long id);
	List<SystemMenu> list();
    Long getTotalCount(SystemMenuQueryObject qo);
    List<SystemMenu> pageQuery(SystemMenuQueryObject qo);
    List<SystemMenu> queryMenusByParentSn(String parentSn);
	List<SystemMenu> queryMenusByParentSnAndEmpId(@Param("parentSn")String parentSn, 
			@Param("empId")Long empId);
}