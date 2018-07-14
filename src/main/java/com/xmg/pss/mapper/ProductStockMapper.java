package com.xmg.pss.mapper;

import com.xmg.pss.domain.ProductStock;
import com.xmg.pss.query.ProductStockQueryObject;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ProductStockMapper {
	void save(ProductStock entity);
	void update(ProductStock entity);
	void delete(Long id);
    ProductStock get(Long id);
	List<ProductStock> list();
    Long getTotalCount(ProductStockQueryObject qo);
    List<ProductStock> pageQuery(ProductStockQueryObject qo);
	ProductStock queryByDepotAndProduct(@Param("depotId")Long depotId, @Param("productId")Long productId);
}