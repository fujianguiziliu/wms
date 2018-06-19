package com.xmg.pss.mapper;

import com.xmg.pss.domain.Cat;
import com.xmg.pss.query.CatQueryObject;
import java.util.List;

public interface CatMapper {
	void save(Cat entity);
	void update(Cat entity);
	void delete(Long id);
    Cat get(Long id);
	List<Cat> list();
    Long getTotalCount(CatQueryObject qo);
    List<Cat> pageQuery(CatQueryObject qo);
}