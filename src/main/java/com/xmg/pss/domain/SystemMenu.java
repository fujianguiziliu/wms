package com.xmg.pss.domain;

import generator.ObjectProp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@ObjectProp("系统菜单")
public class SystemMenu extends BasicDomain{

	@ObjectProp("菜单名称")
	private String name;
	@ObjectProp("菜单编码")
	private String sn;
	@ObjectProp("URL")
	private String url;
	@ObjectProp("上级菜单")
	private SystemMenu parent;
	private List<SystemMenu> children;
	
	public Map<String, Object> toJson(){
		Map<String, Object> map =new HashMap<>();
		map.put("id", getId());
		map.put("pId", getParent().getId());
		map.put("name", getName());
		map.put("action", getUrl());
		return map;
	};
}
