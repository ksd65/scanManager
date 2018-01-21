/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.sys.entity.Office;


public class TradeAnalysis extends DataEntity<TradeAnalysis> {
	
	private static final long serialVersionUID = 1L;
	private String memberId;		// 商户ID
	private String memberCode;		// 商户编号
	private String merchantCode;	// 通道商户编号
	private String merchantName;	// 通道商户编号
	private String routeId;		// 支付通道ID
	private String routeName;		// 支付通道
	private String count0;	
	private String count1;
	private String count2;
	private String count3;
	private String count4;
	private String count5;
	private String count6;
	private String count7;
	private String count8;
	private String count9;
	private String count10;
	private String count11;
	private String successCount;
	private String failCount;
	private String routeFailCount;
	private String platSuccessPer;
	private String rountSuccessPer;

	private String txnMethod;		// 交易方式
	private String txnType;		// 交易类型 1:微信 2:支付宝
	
	private Office office;			// 机构
	private Member member;			// 商户
	private String memberName;		// 商户名称
	private String officeName;		// 所属机构
	
	private String beginTime;
	private String endTime;
	
	private String mobilePhone;		// 手机号码	
	private String agentNameLevel1;// 所属一级机构
	

	
	public TradeAnalysis() {
		super();
	}

	public TradeAnalysis(String id){
		super(id);
	}
	
	@Length(min=1, max=11, message="商户ID长度必须介于 1 和 11 之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@ExcelField(title="商户编号", align=2, sort=10)
	@Length(min=0, max=32, message="商户编号长度必须介于 0 和 32 之间")
	public String getMemberCode() {
		return memberCode;
	}
	
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	
	@ExcelField(title="商户名称", align=2, sort=20)
	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@ExcelField(title="手机号", align=2, sort=30)
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	
	//@ExcelField(title="所属一级机构", align=2, sort=40)
	public String getAgentNameLevel1() {
		return agentNameLevel1;
	}

	public void setAgentNameLevel1(String agentNameLevel1) {
		this.agentNameLevel1 = agentNameLevel1;
	}
	
	
	@ExcelField(title="所属机构", align=2, sort=40)
	public String getOfficeName() {
		if(office == null){
			return officeName;
		}
		return office.getName();
	}
	
	
	
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

		
	@ExcelField(title="交易类型", align=2, sort=80, dictType="txn_type")
	@Length(min=1, max=1, message="交易类型长度必须介于 1 和 1 之间")
	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	// @ExcelField(title="通道商户编号", align=2, sort=2)
	@Length(min=0, max=32, message="通道商户编号长度必须介于 0 和 32 之间")
	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	
	
	@Length(min=0, max=5, message="支付通道ID长度必须介于 0 和 5 之间")
	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	
	
	@NotNull(message="机构不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@NotNull(message="商户不能为空")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	// 
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

	@ExcelField(title="交易方式", align=2, sort=79, dictType="txn_method")
	public String getTxnMethod() {
		return txnMethod;
	}

	public void setTxnMethod(String txnMethod) {
		this.txnMethod = txnMethod;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getCount0() {
		return count0;
	}

	public void setCount0(String count0) {
		this.count0 = count0;
	}

	public String getCount1() {
		return count1;
	}

	public void setCount1(String count1) {
		this.count1 = count1;
	}

	public String getCount2() {
		return count2;
	}

	public void setCount2(String count2) {
		this.count2 = count2;
	}

	public String getCount3() {
		return count3;
	}

	public void setCount3(String count3) {
		this.count3 = count3;
	}

	public String getCount4() {
		return count4;
	}

	public void setCount4(String count4) {
		this.count4 = count4;
	}

	public String getCount5() {
		return count5;
	}

	public void setCount5(String count5) {
		this.count5 = count5;
	}

	public String getCount6() {
		return count6;
	}

	public void setCount6(String count6) {
		this.count6 = count6;
	}

	public String getCount7() {
		return count7;
	}

	public void setCount7(String count7) {
		this.count7 = count7;
	}

	public String getCount8() {
		return count8;
	}

	public void setCount8(String count8) {
		this.count8 = count8;
	}

	public String getCount9() {
		return count9;
	}

	public void setCount9(String count9) {
		this.count9 = count9;
	}

	public String getCount10() {
		return count10;
	}

	public void setCount10(String count10) {
		this.count10 = count10;
	}

	public String getCount11() {
		return count11;
	}

	public void setCount11(String count11) {
		this.count11 = count11;
	}

	public String getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(String successCount) {
		this.successCount = successCount;
	}

	public String getFailCount() {
		return failCount;
	}

	public void setFailCount(String failCount) {
		this.failCount = failCount;
	}

	public String getRouteFailCount() {
		return routeFailCount;
	}

	public void setRouteFailCount(String routeFailCount) {
		this.routeFailCount = routeFailCount;
	}

	public String getPlatSuccessPer() {
		return platSuccessPer;
	}

	public void setPlatSuccessPer(String platSuccessPer) {
		this.platSuccessPer = platSuccessPer;
	}

	public String getRountSuccessPer() {
		return rountSuccessPer;
	}

	public void setRountSuccessPer(String rountSuccessPer) {
		this.rountSuccessPer = rountSuccessPer;
	}
	
	
	
	
}