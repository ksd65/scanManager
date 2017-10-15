/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mem.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商户结算信息Entity
 * @author chenjc
 * @version 2016-11-19
 */
public class MemberBank extends DataEntity<MemberBank> {
	
	private static final long serialVersionUID = 1L;
	private String memberId;		//绑定商户ID
	private String settleType;		//结算方式
	private String bankId;			// 银行代码
	private String province;		// 省份
	private String city;			// 地市
	private String subId;			// 联行号
	private String bankOpen;		// 开户行名称	
	private String accountName;		//账户名称
	private String accountNumber;	//银行账号
	
	
	public MemberBank() {
		super();
	}

	public MemberBank(String id){
		super(id);
	}

	@Length(min=1, max=11, message="绑定商户ID长度必须介于 1 和 11 之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Length(min=1, max=1, message="结算长度必须介于 1 和 1之间")
	public String getSettleType() {
		return settleType;
	}

	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}
	
	@Length(min=1, max=15, message="银行代码长度必须介于 1 和 2 之间")
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
	@Length(min=0, max=15, message="省份长度必须介于 0 和15之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=15, message="地市长度必须介于 0 和 32 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=30, message="联行号长度必须介于 0 和 30之间")
	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}
	
	@Length(min=0, max=60, message="开户行名称长度必须介于 0 和 60 之间")
	public String getBankOpen() {
		return bankOpen;
	}

	public void setBankOpen(String bankOpen) {
		this.bankOpen = bankOpen;
	}
	
	@Length(min=0, max=30, message="账户名称长度必须介于 0 和 30 之间")
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	@Length(min=0, max=30, message="银行账号长度必须介于 0 和 30 之间")
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}