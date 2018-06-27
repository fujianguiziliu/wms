package com.xmg.pss.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class SystemMenuQueryObject extends QueryObject {

	private Long parentId=-1L;
	
	private String parentSn;
}
