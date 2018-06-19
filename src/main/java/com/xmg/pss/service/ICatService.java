package com.xmg.pss.service;
import java.util.List;
import com.xmg.pss.domain.Cat;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.CatQueryObject;

public interface ICatService {
	void delete(Long id);
	void save(Cat entity);
    Cat get(Long id);
    List<Cat> list();
	void update(Cat entity);
	PageResult pageQuery(CatQueryObject qo);
}
