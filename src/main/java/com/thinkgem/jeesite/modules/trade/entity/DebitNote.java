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

/**
 * 交易明细查询Entity
 * @author chenjc
 * @version 2016-12-15
 */
public class DebitNote extends DataEntity<DebitNote> {
	
	private static final long serialVersionUID = 1L;
	private String memberId;		// 商户ID
	private String memberCode;		// 商户编号
	private String merchantCode;	// 通道商户编号
	private String money;			// 交易金额
	
	private String orderCode;		// 交易订单编号
	private String orderNumOuter;
	private String settleType;		// 结算类型 0:D+0 1:T+1
	private String txnMethod;		// 交易方式
	private String txnType;		// 交易类型 1:微信 2:支付宝
	private String memberTradeRate;		// 商户费率
	private String status;
	private String routeId;		// 支付通道ID
	
	private String respType;
	private String respCode;		// 应答码
	private String respMsg;		// 应答描述
	
	private Office office;			// 机构
	private Member member;			// 商户
	
	private String memberName;		// 商户名称
	private String officeName;		// 所属机构
	
	
	private String beginTime;
	private String endTime;
	
	private String mobilePhone;		// 手机号码
	

	private String beginMonth;
	private String endMonth;
	
	
	private String agentNameLevel1;// 所属一级机构
	
	private String payQrCodeId;
    
    private String payAccount;
    
    private String payUserName;
    
    private String payeeId;
    
    private String qrorderDealStatus;
    
    private String remarks;
	
	public DebitNote() {
		super();
	}

	public DebitNote(String id){
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

	@ExcelField(title="交易金额", align=2, sort=50)
	public String getMoney() {
		return money;
	}
	
	public void setMoney(String money) {
		this.money = money;
	}
	
	@ExcelField(title="商户费率", align=2, sort=55)
	public String getMemberTradeRate() {
		return memberTradeRate;
	}

	public void setMemberTradeRate(String memberTradeRate) {
		this.memberTradeRate = memberTradeRate;
	}

	//@ExcelField(title="结算方式", align=2, sort=70 , dictType="settle_type")
	@Length(min=0, max=1, message="结算方式长度必须介于 0 和 1 之间")
	public String getSettleType() {
		return settleType;
	}

	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}
	
	@ExcelField(title="交易订单号", align=2, sort=75)
	@Length(min=1, max=32, message="交易订单编号长度必须介于 1 和 32 之间")
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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
	
	//@ExcelField(title="应答码", align=2, sort=17)
	@Length(min=0, max=10, message="应答码长度必须介于 0 和 10 之间")
	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	
	@Length(min=0, max=256, message="应答描述长度必须介于 0 和 256 之间")
	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
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
	
	public String getBeginMonth() {
		return beginMonth;
	}

	public void setBeginMonth(String beginMonth) {
		this.beginMonth = beginMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	@ExcelField(title="交易方式", align=2, sort=79, dictType="txn_method")
	public String getTxnMethod() {
		return txnMethod;
	}

	public void setTxnMethod(String txnMethod) {
		this.txnMethod = txnMethod;
	}
	@ExcelField(title="商户订单号", align=2, sort=76)
	public String getOrderNumOuter() {
		return orderNumOuter;
	}

	public void setOrderNumOuter(String orderNumOuter) {
		this.orderNumOuter = orderNumOuter;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRespType() {
		return respType;
	}

	public void setRespType(String respType) {
		this.respType = respType;
	}

	public String getPayQrCodeId() {
		return payQrCodeId;
	}

	public void setPayQrCodeId(String payQrCodeId) {
		this.payQrCodeId = payQrCodeId;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getPayUserName() {
		return payUserName;
	}

	public void setPayUserName(String payUserName) {
		this.payUserName = payUserName;
	}

	public String getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}

	public String getQrorderDealStatus() {
		return qrorderDealStatus;
	}

	public void setQrorderDealStatus(String qrorderDealStatus) {
		this.qrorderDealStatus = qrorderDealStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}