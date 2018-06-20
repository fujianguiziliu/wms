package com.xmg.pss.domain;

import generator.ObjectProp;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ObjectProp("小狗")
public class Cat extends BasicDomain {
	private static final long serialVersionUID = 1L;
	@ObjectProp("编码")
	private String sn;
	@ObjectProp("名称")
	private String name;

	

}
