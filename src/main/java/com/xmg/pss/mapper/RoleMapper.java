package com.xmg.pss.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmg.pss.domain.Role;
import com.xmg.pss.query.QueryObject;

public interface RoleMapper {
	void save(Role r);

	void delete(Long id);

	void update(Role r);

	Role get(Long id);

	List<Role> list();

	Long getTotalCount(QueryObject qo);

	List<Role> pageQuery(QueryObject qo);

	/**
	 * 维护角色和权限的关系
	 * @param id  角色主键
	 * @param id2  权限的主键
	 */
	void updateRelation(@Param("rid") Long id, @Param("pid") Long id2);

	/**
	 * 根据角色将中间表中的数据删除
	 * @param id
	 */
	void deleteRelation(Long id);

}
