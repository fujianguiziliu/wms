package com.xmg.pss.mapper;

import com.xmg.pss.domain.StockIncomeBill;
import com.xmg.pss.domain.StockIncomeBillItem;
import com.xmg.pss.query.StockIncomeBillQueryObject;
import java.util.List;

public interface StockIncomeBillMapper {
	void save(StockIncomeBill entity);
	void update(StockIncomeBill entity);
	void delete(Long id);
    StockIncomeBill get(Long id);
	List<StockIncomeBill> list();
    Long getTotalCount(StockIncomeBillQueryObject qo);
    List<StockIncomeBill> pageQuery(StockIncomeBillQueryObject qo);
    
    
    List<StockIncomeBillItem> queryItemsByBillId(Long billId);
	void saveItem(StockIncomeBillItem item);
	void audit(StockIncomeBill entity);
	void deleteItemsByBillId(Long billId);
	

}