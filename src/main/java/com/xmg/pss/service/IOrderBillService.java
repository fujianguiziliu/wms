package com.xmg.pss.service;
import java.util.List;
import com.xmg.pss.domain.OrderBill;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.OrderBillQueryObject;

public interface IOrderBillService {
	void delete(Long id);
	void save(OrderBill entity);
    OrderBill get(Long id);
    List<OrderBill> list();
	void update(OrderBill entity);
	void audit(OrderBill entity);
	PageResult pageQuery(OrderBillQueryObject qo);
	
}
