/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mem.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商户结算信息Entity
 * @author chenjc
 * @version 2016-11-19
 */
public class MemberDrawRoute extends DataEntity<MemberDrawRoute> {
	
	private static final long serialVersionUID = 1L;
	private String memberId;		
	private String payMethod;		
	private String payType;	
	private String routeCode;
	private String aisleType;
	private String merchantCode;
	private String d0Percent;		
	private String d1Percent;			
	private String t1Percent;			
	private String drawFee;	
	
	public MemberDrawRoute() {
		super();
	}

	public MemberDrawRoute(String id){
		super(id);
	}

	@Length(min=1, max=11, message="绑定商户ID长度必须介于 1 和 11 之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public String getAisleType() {
		return aisleType;
	}

	public void setAisleType(String aisleType) {
		this.aisleType = aisleType;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getD0Percent() {
		return d0Percent;
	}

	public void setD0Percent(String d0Percent) {
		this.d0Percent = d0Percent;
	}

	public String getD1Percent() {
		return d1Percent;
	}

	public void setD1Percent(String d1Percent) {
		this.d1Percent = d1Percent;
	}

	public String getT1Percent() {
		return t1Percent;
	}

	public void setT1Percent(String t1Percent) {
		this.t1Percent = t1Percent;
	}

	public String getDrawFee() {
		return drawFee;
	}

	public void setDrawFee(String drawFee) {
		this.drawFee = drawFee;
	}

	
	
	
	
}