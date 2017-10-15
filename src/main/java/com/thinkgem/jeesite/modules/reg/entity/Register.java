/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.reg.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 商户审核Entity
 * @author linzw
 * @version 2016-11-21
 */
public class Register extends DataEntity<Register> {
	
	private static final long serialVersionUID = 1L;
	private String code;			// 商户编号
	private String type;			// 商户类型
	private String loginCode;		// 登陆账号
	private String loginPass;		// 登陆密码
	private Register parentId;		// 上级ID
	private String email;			// 电子邮箱
	private String mobilePhone;		// 手机号码
	private String name;			// 商户名称
	private String shortName;		// 商户简称
	private String contact;			// 联系人
	private String status;			// 联系人
	private String certNbr;			// 身份证号
	private String certPic1;		// 身份证正面图片
	private String certPic2;		// 身份证反面图片
	private String memcertPic;		// 手持身份证照片
	private String cardNbr;			// 认证银行卡卡号
	private String cardPic1;		// 银行卡正面图片
	private String cardPic2;		// 银行卡反面图片
	private String addr;			// 地址
	private String category;		// 经营类目
	private String settleType;		//结算方式
	private String province;		// 省份
	private String city;			// 城市
	private String county;			// 县乡
	private String bankId;			// 银行代码
	private String subId;			// 联行行号
	private String bankOpen;		// 开户行名称
	private String accountName;		// 账户名称
	private String accountNumber;		// 账号
	private String payCode;			// E码付编号
	private String wxMemberCode;		// 微信商户编号
	private String zfbMemberCode;		// 微信商户编号
	private Office office;			// 机构编号
	
	public Register() {
		super();
	}

	public Register(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商户编号长度必须介于 1 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=2, message="商户类型长度必须介于 1 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=32, message="登陆账号长度必须介于 0 和 32 之间")
	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	
	@Length(min=0, max=32, message="登陆密码长度必须介于 0 和 32 之间")
	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}
	
	@JsonBackReference
	public Register getParentId() {
		return parentId;
	}

	public void setParentId(Register parentId) {
		this.parentId = parentId;
	}
	
	@Length(min=0, max=128, message="电子邮箱长度必须介于 0 和 128 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=32, message="手机号码长度必须介于 0 和 32 之间")
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@Length(min=0, max=128, message="商户名称长度必须介于 0 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="商户简称长度必须介于 0 和 64 之间")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	@Length(min=0, max=64, message="联系人长度必须介于 0 和 64 之间")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Length(min=0, max=2, message="审核状态长度必须介于 0 和2之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=40, message="身份证号长度必须介于 0 和 40 之间")
	public String getCertNbr() {
		return certNbr;
	}

	public void setCertNbr(String certNbr) {
		this.certNbr = certNbr;
	}
	
	@Length(min=0, max=60, message="身份证正面图片长度必须介于 0 和 60 之间")
	public String getCertPic1() {
		return certPic1;
	}

	public void setCertPic1(String certPic1) {
		this.certPic1 = certPic1;
	}
	
	@Length(min=0, max=60, message="身份证反面图片长度必须介于 0 和 60 之间")
	public String getCertPic2() {
		return certPic2;
	}

	public void setCertPic2(String certPic2) {
		this.certPic2 = certPic2;
	}
	
	@Length(min=0, max=60, message="手持身份证照片长度必须介于 0 和 60 之间")
	public String getMemcertPic() {
		return memcertPic;
	}

	public void setMemcertPic(String memcertPic) {
		this.memcertPic = memcertPic;
	}
	
	@Length(min=0, max=30, message="认证银行卡卡号长度必须介于 0 和 30 之间")
	public String getCardNbr() {
		return cardNbr;
	}

	public void setCardNbr(String cardNbr) {
		this.cardNbr = cardNbr;
	}
	
	@Length(min=0, max=60, message="银行卡正面图片长度必须介于 0 和 60 之间")
	public String getCardPic1() {
		return cardPic1;
	}

	public void setCardPic1(String cardPic1) {
		this.cardPic1 = cardPic1;
	}
	
	@Length(min=0, max=60, message="银行卡反面图片长度必须介于 0 和 60 之间")
	public String getCardPic2() {
		return cardPic2;
	}

	public void setCardPic2(String cardPic2) {
		this.cardPic2 = cardPic2;
	}
	
	@Length(min=0, max=256, message="地址长度必须介于 0 和 256 之间")
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Length(min=0, max=128, message="经营类目长度必须介于 0 和 128 之间")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Length(min=1, max=1, message="结算长度必须介于 1 和 1之间")
	public String getSettleType() {
		return settleType;
	}

	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}
	
	@Length(min=0, max=11, message="省份长度必须介于 0 和 11 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=11, message="城市长度必须介于 0 和 11 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=11, message="县乡长度必须介于 0 和 11 之间")
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	
	@Length(min=0, max=15, message="银行代码长度必须介于 0 和 15 之间")
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
	@Length(min=0, max=30, message="联行行号长度必须介于 0 和 30 之间")
	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}
	
	@Length(min=0, max=30, message="开户行名称长度必须介于 0 和 30 之间")
	public String getBankOpen() {
		return bankOpen;
	}

	public void setBankOpen(String bankOpen) {
		this.bankOpen = bankOpen;
	}
	
	@Length(min=0, max=60, message="账户名称长度必须介于 0 和 60 之间")
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	@Length(min=0, max=30, message="账号长度必须介于 0 和 30 之间")
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Length(min=0, max=64, message="E码付编号长度必须介于 0 和 64 之间")
	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	
	@Length(min=0, max=32, message="微信商户编号长度必须介于 0 和 32 之间")
	public String getWxMemberCode() {
		return wxMemberCode;
	}

	public void setWxMemberCode(String wxMemberCode) {
		this.wxMemberCode = wxMemberCode;
	}
	
	@Length(min=0, max=32, message="微信商户编号长度必须介于 0 和 32 之间")
	public String getZfbMemberCode() {
		return zfbMemberCode;
	}

	public void setZfbMemberCode(String zfbMemberCode) {
		this.zfbMemberCode = zfbMemberCode;
	}
	
	@NotNull(message="机构编号不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
}