package com.xmg.wms.mapper;

import com.xmg.wms.domain.Dog;
import com.xmg.wms.query.DogQueryObject;
import java.util.List;

public interface DogMapper {
	void save(Dog entity);
	void update(Dog entity);
	void delete(Long id);
    Dog get(Long id);
	List<Dog> list();
    Long getTotalCount(DogQueryObject qo);
    List<Dog> pageQuery(DogQueryObject qo);
}