package com.xmg.pss.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Department {

	private Long id;
	private String name;
	private String sn;

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", sn=" + sn + "]";
	}

}
