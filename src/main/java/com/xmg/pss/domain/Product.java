package com.xmg.pss.domain;

import generator.ObjectProp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.alibaba.fastjson.JSON;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ObjectProp("货品管理")


public class Product extends BasicDomain{

	@ObjectProp("货品编码")
	private String sn;
	@ObjectProp("货品名称")
	private String name;
	@ObjectProp("成本价格")
	private BigDecimal costPrice;
	@ObjectProp("销售价格")
	private BigDecimal salePrice;
	@ObjectProp("货品图片")
	private String imagePath;
	@ObjectProp("备注")
	private String intro;
	@ObjectProp("货品品牌")
	private Brand brand;
	
	
	public String getSmallImagePath(){
		if (StringUtils.isNotEmpty(getImagePath())) {
			int index=imagePath.lastIndexOf(".");
			return imagePath.substring(0, index)+"_small" +imagePath.substring(index);
		}
		return "";
		
		
		
	}
	
	
	public String getProductJson(){
		Map<String,Object> map = new HashMap<>();
		map.put("id", getId());
		map.put("name", getName());
		map.put("costPrice", getCostPrice());
		map.put("brandName", getBrand()==null?"":getBrand().getName());
		
		return JSON.toJSONString(map);
		
	}
}
