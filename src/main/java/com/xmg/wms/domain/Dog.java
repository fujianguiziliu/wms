package com.xmg.wms.domain;

import generator.ObjectProp;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ObjectProp("小狗")

public class Dog extends BasicDomain {
	
	@ObjectProp("编码")
	private String sn;
	
	@ObjectProp("名称")
	private String name;


}
