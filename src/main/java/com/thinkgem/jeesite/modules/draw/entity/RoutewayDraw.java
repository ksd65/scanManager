/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.draw.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 通道提现查询Entity
 * @author chenjc
 * @version 2016-12-21
 */
public class RoutewayDraw extends DataEntity<RoutewayDraw> {
	
	private static final long serialVersionUID = 1L;
	private String memberId;		// 商户ID
	private String memberCode;		// 商户编号
	private String merchantCode;		// 通道商户编号
	private String money;		// 交易金额
	private String orderCode;		// 交易订单编号
	private String ptSerialNo;		// 平台流水号
	private String reqDate;		// 请求日期时间
	private String respDate;		// 应答日期时间
	private String respType;		// 应答类型 S:成功 E:失败 R:不确定
	private String respCode;		// 应答码
	private String respMsg;		// 应答描述
	private String drawamount;		// 实际提现金额
	private String drawfee;		// 提现手续费
	private String drawRate;		// 提现费率
	private String tradefee;		// 交易手续费
	private String settleDate;		// 对账日期
	private Office office;			// 机构
	private Member member;			// 商户
	
	
	// 吴逢生 2017-01-14
	private String memberName;		// 商户名称
	private String officeName;		// 所属机构
	private String txDate;			// 请求日期
	private String txTime;			// 请求时间
	
	private String beginTime;
	private String endTime;	
	
	private Date createTime;		// 创建时间
	private String mobilePhone;
	private String memberRate;		// 商户费率
	private String agentNameLevel1;// 所属一级机构
	
	private String auditStatus; //审核状态
	private Date auditTime;
	private String auditBy;
	
	private String applyBeginTime;
	private String applyEndTime;
	private String drawMoney;
	private String drawType;
	private String orderNumOuter;
	private String bankName;
	private String subName;
	private String subId;
	private String bankAccount;
	private String accountName;
	private String certNo;
	private String tel;
	private String bankCode;
	private String routeCode;
	public RoutewayDraw() {
		super();
	}

	public RoutewayDraw(String id){
		super(id);
	}
	
	@ExcelField(title="商户名称", align=2, sort=10)
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@ExcelField(title="手机号", align=2, sort=20)
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	//@ExcelField(title="所属一级机构", align=2, sort=30)
	public String getAgentNameLevel1() {
		return agentNameLevel1;
	}

	public void setAgentNameLevel1(String agentNameLevel1) {
		this.agentNameLevel1 = agentNameLevel1;
	}
	
	@ExcelField(title="所属机构", align=2, sort=40)
	public String getOfficeName() {
		return office.getName();
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	@ExcelField(title="商户编号", align=2, sort=50)
	@Length(min=0, max=32, message="商户编号长度必须介于 0 和 32 之间")
	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	
	@ExcelField(title="平台流水号", align=2, sort=60)
	@Length(min=1, max=32, message="平台流水号长度必须介于 1 和 32 之间")
	public String getPtSerialNo() {
		return ptSerialNo;
	}

	public void setPtSerialNo(String ptSerialNo) {
		this.ptSerialNo = ptSerialNo;
	}
	
	@ExcelField(title="提现日期", align=2, sort=70)
	public String getTxDate() {
		return txDate;
	}

	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}

	@ExcelField(title="提现时间", align=2, sort=80)
	public String getTxTime() {
		return txTime;
	}

	public void setTxTime(String txTime) {
		this.txTime = txTime;
	}
	
	@ExcelField(title="应答类型", align=2, sort=90, dictType="resp_type")
	@Length(min=0, max=1, message="应答类型 S:成功 E:失败 R:不确定长度必须介于 0 和 1 之间")
	public String getRespType() {
		return respType;
	}

	public void setRespType(String respType) {
		this.respType = respType;
	}
	
	@ExcelField(title="应答码", align=2, sort=100)
	@Length(min=0, max=10, message="应答码长度必须介于 0 和 10 之间")
	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	
	@ExcelField(title="交易金额", align=2, sort=110)
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	@ExcelField(title="实际提现金额", align=2, sort=120)
	public String getDrawamount() {
		return drawamount;
	}

	public void setDrawamount(String drawamount) {
		this.drawamount = drawamount;
	}
	
	@ExcelField(title="提现手续费", align=2, sort=130)
	public String getDrawfee() {
		return drawfee;
	}

	public void setDrawfee(String drawfee) {
		this.drawfee = drawfee;
	}
	
	@ExcelField(title="商户费率", align=2, sort=135)
	public String getMemberRate() {
		return memberRate;
	}

	public void setMemberRate(String memberRate) {
		this.memberRate = memberRate;
	}
	
	@ExcelField(title="交易手续费", align=2, sort=140)
	public String getTradefee() {
		return tradefee;
	}

	public void setTradefee(String tradefee) {
		this.tradefee = tradefee;
	}
	
	@ExcelField(title="对账日期", align=2, sort=150)
	@Length(min=0, max=10, message="对账日期长度必须介于 0 和 10 之间")
	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	
	@Length(min=1, max=11, message="商户ID长度必须介于 1 和 11 之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	//@ExcelField(title="通道商户编号", align=2, sort=2)
	@Length(min=0, max=32, message="通道商户编号长度必须介于 0 和 32 之间")
	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	
	@Length(min=0, max=32, message="交易订单编号长度必须介于 0 和 32 之间")
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	// @ExcelField(title="请求日期时间", align=2, sort=4)
	@Length(min=0, max=16, message="请求日期时间长度必须介于 0 和 16 之间")
	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	
	//@ExcelField(title="应答日期时间", align=2, sort=5)
	@Length(min=0, max=16, message="应答日期时间长度必须介于 0 和 16 之间")
	public String getRespDate() {
		return respDate;
	}

	public void setRespDate(String respDate) {
		this.respDate = respDate;
	}
	
	@Length(min=0, max=256, message="应答描述长度必须介于 0 和 256 之间")
	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
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

	//@ExcelField(title="创建时间", align=2, sort=130)
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return super.createDate;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getApplyBeginTime() {
		return applyBeginTime;
	}

	public void setApplyBeginTime(String applyBeginTime) {
		this.applyBeginTime = applyBeginTime;
	}

	public String getApplyEndTime() {
		return applyEndTime;
	}

	public void setApplyEndTime(String applyEndTime) {
		this.applyEndTime = applyEndTime;
	}

	public String getDrawMoney() {
		return drawMoney;
	}

	public void setDrawMoney(String drawMoney) {
		this.drawMoney = drawMoney;
	}

	public String getAuditBy() {
		return auditBy;
	}

	public void setAuditBy(String auditBy) {
		this.auditBy = auditBy;
	}

	public String getDrawType() {
		return drawType;
	}

	public void setDrawType(String drawType) {
		this.drawType = drawType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOrderNumOuter() {
		return orderNumOuter;
	}

	public void setOrderNumOuter(String orderNumOuter) {
		this.orderNumOuter = orderNumOuter;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public String getDrawRate() {
		return drawRate;
	}

	public void setDrawRate(String drawRate) {
		this.drawRate = drawRate;
	}
	
	
	
}