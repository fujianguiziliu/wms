package com.xmg.wms.service;
import java.util.List;
import com.xmg.wms.domain.Dog;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.DogQueryObject;

public interface IDogService {
	void delete(Long id);
	void save(Dog entity);
    Dog get(Long id);
    List<Dog> list();
	void update(Dog entity);
	PageResult pageQuery(DogQueryObject qo);
}
