package com.xmg.pss.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Role extends BasicDomain {
	private static final long serialVersionUID = 1L;
	private String name;
	private String sn;

	private List<Permission> permissions = new ArrayList<>();

	@Override
	public String toString() {
		return "Role [name=" + name + ", sn=" + sn + "]";
	}

}
