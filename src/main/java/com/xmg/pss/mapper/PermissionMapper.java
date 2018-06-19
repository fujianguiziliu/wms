package com.xmg.pss.mapper;

import java.util.List;

import com.xmg.pss.domain.Permission;
import com.xmg.pss.query.QueryObject;

public interface PermissionMapper {
	void save(Permission dept);

	void delete(Long id);

	List<Permission> list();

	Long getTotalCount(QueryObject qo);

	List<Permission> pageQuery(QueryObject qo);

}
