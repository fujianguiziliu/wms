package com.xmg.pss.domain;

import generator.ObjectProp;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ObjectProp("小猫")

public class Cat extends BasicDomain {
	
	@ObjectProp("编码")
	private String sn;
	
	@ObjectProp("名称")
	private String name;


}
