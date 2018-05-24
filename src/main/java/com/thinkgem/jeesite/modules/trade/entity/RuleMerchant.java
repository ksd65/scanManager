package com.thinkgem.jeesite.modules.trade.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class RuleMerchant extends DataEntity<RuleMerchant>{

	private static final long serialVersionUID = -1898425812553186711L;

	private String routeCode;
	private String aisleType;			
	private String merchantCode;
	private String routeName;
	private String merchantName;
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
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
		
	
	
	
	
	
	
}
