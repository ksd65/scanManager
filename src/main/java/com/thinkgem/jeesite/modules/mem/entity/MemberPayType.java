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
public class MemberPayType extends DataEntity<MemberPayType> {
	
	private static final long serialVersionUID = 1L;
	private String memberId;		
	private String payMethod;		
	private String payType;	
	private String txnType;
	private String routeCode;
	private String aisleType;
	private String merchantCode;
	private String t0TradeRate;		
	private String t1TradeRate;			
	private String t0DrawFee;			
	private String t1DrawFee;	
	private String t0DrawRate;			
	private String t1DrawRate;
	
	public MemberPayType() {
		super();
	}

	public MemberPayType(String id){
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

	public String getT0TradeRate() {
		return t0TradeRate;
	}

	public void setT0TradeRate(String t0TradeRate) {
		this.t0TradeRate = t0TradeRate;
	}

	public String getT1TradeRate() {
		return t1TradeRate;
	}

	public void setT1TradeRate(String t1TradeRate) {
		this.t1TradeRate = t1TradeRate;
	}

	public String getT0DrawFee() {
		return t0DrawFee;
	}

	public void setT0DrawFee(String t0DrawFee) {
		this.t0DrawFee = t0DrawFee;
	}

	public String getT1DrawFee() {
		return t1DrawFee;
	}

	public void setT1DrawFee(String t1DrawFee) {
		this.t1DrawFee = t1DrawFee;
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

	public String getT0DrawRate() {
		return t0DrawRate;
	}

	public void setT0DrawRate(String t0DrawRate) {
		this.t0DrawRate = t0DrawRate;
	}

	public String getT1DrawRate() {
		return t1DrawRate;
	}

	public void setT1DrawRate(String t1DrawRate) {
		this.t1DrawRate = t1DrawRate;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	
	
	
}