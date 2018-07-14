package com.xmg.pss.service;
import java.util.List;
import com.xmg.pss.domain.ProductStock;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.ProductStockQueryObject;

public interface IProductStockService {
	void delete(Long id);
	void save(ProductStock entity);
    ProductStock get(Long id);
    List<ProductStock> list();
	void update(ProductStock entity);
	PageResult pageQuery(ProductStockQueryObject qo);
}
