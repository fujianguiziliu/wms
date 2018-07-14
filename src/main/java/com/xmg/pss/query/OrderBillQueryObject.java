package com.xmg.pss.query;

import java.util.Date;

import com.xmg.pss.util.DateUtil;

import lombok.Getter;
import lombok.Setter;

@Setter
public class OrderBillQueryObject extends QueryObject {
	
	private Date beginDate;//开始时间
	private Date endDate;//结束时间
	private Long supplierId=-1L;//供应商id
	private int status=-1;//审核状态
	public Date getBeginDate() {
		if (beginDate!=null) {
			return DateUtil.getBeginDate(beginDate);
		}
		return beginDate;
	}
	public Date getEndDate() {
		if (endDate!=null) {
			return DateUtil.getEndDate(endDate);
		}
		return endDate;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public int getStatus() {
		return status;
	}
	
	

}
