package com.xmg.pss.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class OrderBillItem extends BasicDomain{
	
	//采购价格
	private BigDecimal costPrice;
	
	//采购数量
	private BigDecimal number;
	
	//采购金额小计
	private BigDecimal amount;
	
	//备注
	private String remark;
	
	//产品
	private Product product;
	
	//采购订单
	private OrderBill bill;
	
}
