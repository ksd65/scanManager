package com.thinkgem.jeesite.modules.trade.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.sys.entity.Office;

public class Payee extends DataEntity<Payee>{

	private static final long serialVersionUID = -1898425812553186711L;

	private String payAccount;			// 收款账号
	private String userName;		// 
	private String payType;
	
	private Office office;			// 机构
	private Member member;			// 商户
	
	private String memberName;		// 商户名称
	private String officeName;		// 所属机构
	
	private String beginTime;
	private String endTime;
	
	private String oldPayAccount;
	
	
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
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
	public String getOldPayAccount() {
		return oldPayAccount;
	}
	public void setOldPayAccount(String oldPayAccount) {
		this.oldPayAccount = oldPayAccount;
	}
	
	
	
}
