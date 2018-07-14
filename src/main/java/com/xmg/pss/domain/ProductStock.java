package com.xmg.pss.domain;

import java.math.BigDecimal;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIDeclaration;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ObjectProp("产品库存")
public class ProductStock extends BasicDomain{

	@ObjectProp("库存价格")
	private BigDecimal price;
	
	@ObjectProp("库存数量")
	private BigDecimal storeNumber;
	
	@ObjectProp("库存总金额")
	private BigDecimal amount;
	
	@ObjectProp("货品")
	private Product product;
	
	@ObjectProp("仓库")
	private Depot depot; 
	
}
