package com.xmg.wms.domain;

import generator.ObjectProp;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ObjectProp("小狗")

public class Dog extends BasicDomain {
	
	@ObjectProp("编码")
	private String name;
	
	@ObjectProp("名称")
	private String sn;


}
