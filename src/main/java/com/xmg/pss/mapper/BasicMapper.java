package com.xmg.pss.mapper;

import java.util.List;

import com.xmg.pss.query.QueryObject;

public interface BasicMapper<T> {
	void save(T t);

	void delete(Long id);

	void batchDelete(List<Long> ids);
	void update(T t);

	T get(Long id);

	List<T> list();

	//高级查询,将对应的结果集查询到
	List<T> pageQuery(QueryObject qo);

	//高级查询,查询到对应的总条数
	Long getTotalCount(QueryObject qo);
}
