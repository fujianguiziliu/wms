package com.xmg.pss.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductQueryObject extends QueryObject {

	private String keyword;
	private Long brandId=-1L;
}
