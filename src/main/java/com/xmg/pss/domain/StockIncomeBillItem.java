package com.xmg.pss.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class StockIncomeBillItem extends BasicDomain{
	
	//入库价格
	private BigDecimal costPrice;
	
	//入库数量
	private BigDecimal number;
	
	//入库金额小计
	private BigDecimal amount;
	
	//备注
	private String remark;
	
	//产品
	private Product product;
	
	//入库订单
	private StockIncomeBill bill;


	
}

