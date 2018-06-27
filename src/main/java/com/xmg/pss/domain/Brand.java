package com.xmg.pss.domain;

import generator.ObjectProp;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ObjectProp("品牌管理")
public class Brand extends BasicDomain {
	private static final long serialVersionUID = 1L;
	@ObjectProp("品牌名称")
	private String name;
	@ObjectProp("品牌编码")
	private String sn;

	

}
