/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.verifycode.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 验证码Entity
 * @author jjy
 * @version 2017-06-20
 */
public class VerifyCode extends DataEntity<VerifyCode> {
	
	private static final long serialVersionUID = 1L;
	private String mobilePhone;		// mobile_phone
	private String verifyCode;		// E码付编号
	private String status;		// 状态   0--无效，1--有效
	private String memberId;		// 会员ID
	private String type;		// 类型  1--注册验证码
	private String msgCode;		// 短信接口返回的code
	
	public VerifyCode() {
		super();
	}

	public VerifyCode(String id){
		super(id);
	}

	@Length(min=1, max=11, message="mobile_phone长度必须介于 1 和 11 之间")
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@Length(min=1, max=15, message="E码付编号长度必须介于 1 和 15 之间")
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	@Length(min=1, max=2, message="状态   0--无效，1--有效长度必须介于 1 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=11, message="会员ID长度必须介于 0 和 11 之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Length(min=0, max=1, message="类型  1--注册验证码长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="短信接口返回的code长度必须介于 0 和 255 之间")
	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	
}