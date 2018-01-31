/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mem.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 商户绑卡流水表Entity
 * @author jjy
 * @version 2017-06-20
 */
public class MemberBindAcc extends DataEntity<MemberBindAcc> {
	
	private static final long serialVersionUID = 1L;
	
	private String memberId;		// 商户ID
	private String acc;				// 绑定卡号
	private String bankId;			// 归属银行ID
	private String bankName;		// 银行名称
	private String bankCode;		// 银行编码
	private String name;			// 持卡人姓名
	private String subName;			// 
	private String subId;			// 
	private String mobilePhone;		// 预留手机号码
	private String certNo;
	private String orderNum;
	private String jfState;
	private String wjfState;
	
	private String bindBeginTime;
	private String bindEndTime;
		
	private Member member;			//商户
	private Office office;			// 机构编号
	

	
	
	public String getMemberCode(){
		return this.member.getCode();
	}
	
	
	public String getMemberName(){
		return this.member.getName();
	}
	
	
	public String getMemberPhone(){
		return this.member.getMobilePhone();
	}
	
	
	public String getOfficeName(){
		return this.office.getName();
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

	public MemberBindAcc() {
		super();
	}

	public MemberBindAcc(String id){
		super(id);
	}

	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Length(min=10, max=25, message="银行卡号长度必须介于 10 和 25 之间")
	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}
	
	@NotNull(message="开户行不能为空")
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
	
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@Length(min=1, max=64, message="持卡人姓名长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	
	@Override
	public Date getCreateDate() {
		// TODO Auto-generated method stub
		return super.getCreateDate();
	}


	public String getBankCode() {
		return bankCode;
	}


	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}


	public String getSubName() {
		return subName;
	}


	public void setSubName(String subName) {
		this.subName = subName;
	}

	@NotNull(message="联行号不能为空")
	public String getSubId() {
		return subId;
	}


	public void setSubId(String subId) {
		this.subId = subId;
	}


	public String getJfState() {
		return jfState;
	}


	public void setJfState(String jfState) {
		this.jfState = jfState;
	}


	public String getWjfState() {
		return wjfState;
	}


	public void setWjfState(String wjfState) {
		this.wjfState = wjfState;
	}


	public String getBindBeginTime() {
		return bindBeginTime;
	}


	public void setBindBeginTime(String bindBeginTime) {
		this.bindBeginTime = bindBeginTime;
	}


	public String getBindEndTime() {
		return bindEndTime;
	}


	public void setBindEndTime(String bindEndTime) {
		this.bindEndTime = bindEndTime;
	}


	public String getCertNo() {
		return certNo;
	}


	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	
	
	
}