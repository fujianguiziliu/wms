package com.xmg.pss.domain;

import generator.ObjectProp;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ObjectProp("供应商管理")
public class Supplier extends BasicDomain {
	private static final long serialVersionUID = 1L;
	@ObjectProp("供应商名称")
	private String name;
	@ObjectProp("联系电话")
	private String phone;
	@ObjectProp("地址")
	private String address;
	

	

}
