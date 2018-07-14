package com.xmg.pss.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ObjectProp("采购订单")
public class OrderBill extends BasicDomain{
	
	public static final int NORMAL=0;
	
	public static final int AUDIT=1;
	
	@ObjectProp("订单编号")
	private String sn;
	@ObjectProp("业务时间")
	private Date vdate;
	@ObjectProp("审核状态")
	private int status;
	@ObjectProp("采购总金额")
	private BigDecimal totalAmount;
	@ObjectProp("采购总数量")
	private BigDecimal totalNumber;
	@ObjectProp("审核时间")
	private Date auditTime;
	@ObjectProp("录入时间")
	private Date inputTime;
	@ObjectProp("录入人")
	private Employee inputUser;
	@ObjectProp("审核时间")
	private Employee auditor;
	@ObjectProp("供应商")
	private Supplier supplier;
	
	private List<OrderBillItem> items=new ArrayList<>();
}
