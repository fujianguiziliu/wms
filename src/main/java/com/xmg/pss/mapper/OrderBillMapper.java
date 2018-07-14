package com.xmg.pss.mapper;

import com.xmg.pss.domain.OrderBill;
import com.xmg.pss.domain.OrderBillItem;
import com.xmg.pss.query.OrderBillQueryObject;
import java.util.List;

public interface OrderBillMapper {
	void save(OrderBill entity);
	void update(OrderBill entity);
	void delete(Long id);
    OrderBill get(Long id);
	List<OrderBill> list();
    Long getTotalCount(OrderBillQueryObject qo);
    List<OrderBill> pageQuery(OrderBillQueryObject qo);
    List<OrderBillItem> queryItemsByBillId(Long billId);
	void saveItem(OrderBillItem item);
	void deleteItemsByBillId(Long id);
	void audit(OrderBill entity);
}