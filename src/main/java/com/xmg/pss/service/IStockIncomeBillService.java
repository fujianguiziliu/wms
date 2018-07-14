package com.xmg.pss.service;
import java.util.List;
import com.xmg.pss.domain.StockIncomeBill;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.StockIncomeBillQueryObject;

public interface IStockIncomeBillService {
	void delete(Long id);
	void save(StockIncomeBill entity);
    StockIncomeBill get(Long id);
    List<StockIncomeBill> list();
	void update(StockIncomeBill entity);
	PageResult pageQuery(StockIncomeBillQueryObject qo);
	void audit(StockIncomeBill entity);
}
