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
 * 商户账户信息Entity
 * @author chenjc
 * @version 2016-11-19
 */
public class MemberAccount extends DataEntity<MemberAccount> {
	
	private static final long serialVersionUID = 1L;
	private String memberId;		//绑定商户ID
	private String balance;			// 账户余额
	private String freezeMoney;		// 冻结金额

	public MemberAccount() {
		super();
	}

	public MemberAccount(String id){
		super(id);
	}

	@Length(min=1, max=11, message="绑定商户ID长度必须介于 1 和 11之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Length(min=1, max=15, message="账户余额必须介于 1 和 15之间")
	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	@Length(min=0, max=15, message="省份长度必须介于 0 和15之间")
	public String getFreezeMoney() {
		return freezeMoney;
	}

	public void setFreezeMoney(String freezeMoney) {
		this.freezeMoney = freezeMoney;
	}
}