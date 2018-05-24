package com.thinkgem.jeesite.modules.trade.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.sys.entity.Office;

public class PayTypeRule extends DataEntity<PayTypeRule>{

	private static final long serialVersionUID = -1898425812553186711L;

	private String payMethod;			
	private String payType;		
	private String memberId;		
	private String ruleType;	
	private String minMoney;
	private String maxMoney;
	private String beginTime;
	private String endTime;
	private String routeCode;
	private String aisleType;			
	private String merchantCode;		
	private String ruleRate;
	
	private Office office;			// 机构
	private Member member;			// 商户
	
	private String memberName;		// 商户名称
	private String officeName;		// 所属机构
	
	private String memberCode;
	
	
	
	
	
	
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getMinMoney() {
		return minMoney;
	}
	public void setMinMoney(String minMoney) {
		this.minMoney = minMoney;
	}
	public String getMaxMoney() {
		return maxMoney;
	}
	public void setMaxMoney(String maxMoney) {
		this.maxMoney = maxMoney;
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
	public String getRuleRate() {
		return ruleRate;
	}
	public void setRuleRate(String ruleRate) {
		this.ruleRate = ruleRate;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	
	
	
	
}
