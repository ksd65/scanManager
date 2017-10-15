/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mem.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 商户绑卡流水表Entity
 * @author jjy
 * @version 2017-06-20
 */
public class MemberBindAccDtl extends DataEntity<MemberBindAccDtl> {
	
	private static final long serialVersionUID = 1L;
	private String memberId;		// 商户ID
	private String acc;				// 绑定卡号
	private String bankId;			// 归属银行ID
	private String bankName;		// 银行名称
	private String name;			// 持卡人姓名
	private String orderNum;		// 机构接入订单号
	private String mobilePhone;		// 预留手机号码
	private String bindType;		// 绑卡类型：0无积分，1有积分
	private String respCode;		// resp_code
	private String respMsg;			// 应答描述
	
	private Member member;			//商户
	private Office office;			// 机构编号
	private String agentNameLevel1;	//一级所属机构
	
	private String beginBindTime;	//开始绑定时间查询
	private String endBindTime;		//截至绑定时间查询
	
	private String fee;				//绑卡手续费

	@ExcelField(title="绑卡手续费", align=2, sort=20)
	public String getFee() {
		return "0.5";
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getBeginBindTime() {
		return beginBindTime;
	}

	public void setBeginBindTime(String beginBindTime) {
		this.beginBindTime = beginBindTime;
	}

	public String getEndBindTime() {
		return endBindTime;
	}

	public void setEndBindTime(String endBindTime) {
		this.endBindTime = endBindTime;
	}

	@ExcelField(title="一级所属机构", align=2, sort=20)
	public String getAgentNameLevel1() {
		return agentNameLevel1;
	}

	public void setAgentNameLevel1(String agentNameLevel1) {
		this.agentNameLevel1 = agentNameLevel1;
	}

	@ExcelField(title="商户编号", align=2, sort=5)
	public String getMemberCode(){
		return this.member.getCode();
	}
	
	@ExcelField(title="商户名称", align=2, sort=10)
	public String getMemberName(){
		return this.member.getName();
	}
	
	@ExcelField(title="手机号码", align=2, sort=15)
	public String getMemberPhone(){
		return this.member.getMobilePhone();
	}
	
	@ExcelField(title="所属机构", align=2, sort=20)
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

	public MemberBindAccDtl() {
		super();
	}

	public MemberBindAccDtl(String id){
		super(id);
	}

	@Length(min=1, max=11, message="商户ID长度必须介于 1 和 11 之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@ExcelField(title="银行卡号", align=2, sort=35)
	@Length(min=1, max=32, message="绑定卡号长度必须介于 1 和 32 之间")
	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}
	
	@Length(min=1, max=8, message="归属银行ID长度必须介于 1 和 8 之间")
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
	@ExcelField(title="发卡行", align=2, sort=30)
	@Length(min=0, max=255, message="银行名称长度必须介于 0 和 255 之间")
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@ExcelField(title="持卡人姓名", align=2, sort=25)
	@Length(min=0, max=255, message="持卡人姓名长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=32, message="机构接入订单号长度必须介于 0 和 32 之间")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	@ExcelField(title="预留手机号", align=2, sort=40)
	@Length(min=0, max=25, message="预留手机号码长度必须介于 0 和 25 之间")
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@ExcelField(title="绑卡类型(0无积分，1有积分)", align=2, sort=45)
	@Length(min=0, max=1, message="绑卡类型：0无积分，1有积分长度必须介于 0 和 1 之间")
	public String getBindType() {
		return bindType;
	}

	public void setBindType(String bindType) {
		this.bindType = bindType;
	}
	
	@Length(min=0, max=32, message="resp_code长度必须介于 0 和 32 之间")
	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	
	@Length(min=0, max=255, message="应答描述长度必须介于 0 和 255 之间")
	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	@ExcelField(title="绑卡时间", align=2, sort=50)
	@Override
	public Date getCreateDate() {
		// TODO Auto-generated method stub
		return super.getCreateDate();
	}
	
	
}