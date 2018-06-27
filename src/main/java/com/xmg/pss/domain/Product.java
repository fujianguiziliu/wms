package com.xmg.pss.domain;

import java.math.BigDecimal;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ObjectProp("货品管理")
public class Product extends BasicDomain{

	@ObjectProp("货品名称")
	private String name;
	
	@ObjectProp("货品编码")
	private String sn;
	
	@ObjectProp("成本价格")
	private BigDecimal costPrice;
	
	@ObjectProp("销售价格")
	private BigDecimal salePrice;
	
	@ObjectProp("货品图片")
	private String imagePath; 
	
	@ObjectProp("备注")
	private String intro;
	
	@ObjectProp("货品品牌")
	private Brand brand;
	
	
	
}
