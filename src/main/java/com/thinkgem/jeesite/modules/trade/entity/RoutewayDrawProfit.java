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


public class RoutewayDrawProfit extends DataEntity<RoutewayDrawProfit> {
	
	private static final long serialVersionUID = 1L;
	private String memberId;		// 商户ID
	private String memberCode;		// 商户编号
	private String routeCode;		// 支付通道ID
	private String routeName;		// 支付通道
	private String txnDate;
	private String money;
	private String profit;
	private String agentOfficeId;
	private String agentName;
	
	private Office office;			// 机构
	private Member member;			// 商户
	private String memberName;		// 商户名称
	private String officeName;		// 所属机构
	
	private String beginTime;
	private String endTime;
	
	private String mobilePhone;		// 手机号码	
	private String agentNameLevel1;// 所属一级机构
	

	
	public RoutewayDrawProfit() {
		super();
	}

	public RoutewayDrawProfit(String id){
		super(id);
	}
	
	@Length(min=1, max=11, message="商户ID长度必须介于 1 和 11 之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@ExcelField(title="商户编号", align=2, sort=20)
	@Length(min=0, max=32, message="商户编号长度必须介于 0 和 32 之间")
	public String getMemberCode() {
		return memberCode;
	}
	
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	
	@ExcelField(title="商户名称", align=2, sort=30)
	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

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
	
	public String getOfficeName() {
		if(office == null){
			return officeName;
		}
		return office.getName();
	}
	
	
	
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
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

	

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	@ExcelField(title="交易通道", align=2, sort=50, dictType="route_code")
	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}
	
	@ExcelField(title="代付日期", align=2, sort=10)
	public String getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	
	public String getAgentOfficeId() {
		return agentOfficeId;
	}

	public void setAgentOfficeId(String agentOfficeId) {
		this.agentOfficeId = agentOfficeId;
	}

	@ExcelField(title="代理商名称", align=2, sort=40)
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	@ExcelField(title="代付（提现）金额", align=2, sort=60)
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@ExcelField(title="代付（提现）收益", align=2, sort=70)
	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	
	
	
}