/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bill.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 交易对账查询Entity
 * @author chenjc
 * @version 2016-12-23
 */
public class MsPayBill extends DataEntity<MsPayBill> {
	
	private static final long serialVersionUID = 1L;
	private String cooperator;		// 合作方标识
	private String memberId;		// 商户ID
	private String merchantCode;	// 通道商户号
	private String smzfMsgId;		// 平台流水号
	private String reqMsgId;		// 请求交易流水号
	private String amount;		// 交易金额
	private String settleDate;		// 对账日期
	private String respType;		// 交易状态
	private String respCode;		// 响应码
	private String respMsg;		// 响应描述
	private String transactionType;		// 交易类型
	private String oriReqMsgId;		// 原支付流水号
	private String fee;		// 手续费
	private String payWay;		// 支付通道
	private String payType;		// 借贷记类型
	private String drawBatchNo;		// 提现批次流水号
	private Office office;			// 机构
	private Member member;			// 商户
	
	// 吴逢生 2017-01-14
	private String memberName;		// 商户名称
	private String officeName;		// 所属机构
	private String beginTime;
	private String endTime;
	private String mobilePhone;		// 手机号
	private String settleType;		// 结算方式
	
	private String agentNameLevel1;// 所属一级机构
	private String t0DrawFee;	// t0提现手续费
	private String t0TradeRate;	// t0交易手续费扣率
	private String t1DrawFee;	// t1提现手续费
	private String t1TradeRate;	// t1交易手续费扣率
	
	private String memberTradeRate;		// 商户费率
	
	
	public MsPayBill() {
		super();
	}

	public MsPayBill(String id){
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
	
	@ExcelField(title="所属一级机构", align=2, sort=30)
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
	
	@ExcelField(title="通道商户号", align=2, sort=45)
	@Length(min=0, max=32, message="通道商户号长度必须介于 0 和 32 之间")
	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	
	@ExcelField(title="平台流水号", align=2, sort=50)
	@Length(min=0, max=32, message="平台流水号长度必须介于 0 和 32 之间")
	public String getSmzfMsgId() {
		return smzfMsgId;
	}

	public void setSmzfMsgId(String smzfMsgId) {
		this.smzfMsgId = smzfMsgId;
	}
	
	@ExcelField(title="请求交易流水号", align=2, sort=60)
	@Length(min=0, max=32, message="请求交易流水号长度必须介于 0 和 32 之间")
	public String getReqMsgId() {
		return reqMsgId;
	}

	public void setReqMsgId(String reqMsgId) {
		this.reqMsgId = reqMsgId;
	}
	
	@ExcelField(title="商户费率", align=2, sort=70)
	public String getMemberTradeRate() {
		return memberTradeRate;
	}

	public void setMemberTradeRate(String memberTradeRate) {
		this.memberTradeRate = memberTradeRate;
	}
	
	
	public String getT1TradeRate() {
			return member.getT1TradeRate();
	}

	public void setT1TradeRate(String t1TradeRate) {
		this.t1TradeRate = t1TradeRate;
	}

	@ExcelField(title="提现手续费", align=2, sort=80)
	public String getT1DrawFee() {
		if("1".equals(settleType)){
			return member.getT1DrawFee();
		}else{
			return member.getT0DrawFee();
		}
	}

	public void setT1DrawFee(String t1DrawFee) {
		this.t1DrawFee = t1DrawFee;
	}
	
	@ExcelField(title="交易金额", align=2, sort=90)
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@ExcelField(title="结算方式", align=2, sort=100)
	public String getSettleType() {
		return settleType;
	}

	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}
	
	@ExcelField(title="对账日期", align=2, sort=110)
	@Length(min=0, max=8, message="对账日期长度必须介于 0 和 8 之间")
	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	
	@ExcelField(title="交易状态", align=2, sort=120, dictType="resp_type")
	@Length(min=0, max=1, message="交易状态长度必须介于 0 和 1 之间")
	public String getRespType() {
		return respType;
	}

	public void setRespType(String respType) {
		this.respType = respType;
	}

	@ExcelField(title="交易类型", align=2, sort=130)
	@Length(min=0, max=4, message="交易类型长度必须介于 0 和 4 之间")
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	@ExcelField(title="支付通道", align=2, sort=140)
	@Length(min=0, max=32, message="支付通道长度必须介于 0 和 32 之间")
	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	
	@ExcelField(title="借贷记类型", align=2, sort=150)
	@Length(min=0, max=1, message="借贷记类型长度必须介于 0 和 1 之间")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@ExcelField(title="提现批次流水号", align=2, sort=160)
	public String getDrawBatchNo() {
		return drawBatchNo;
	}

	public void setDrawBatchNo(String drawBatchNo) {
		this.drawBatchNo = drawBatchNo;
	}

	public String getT0DrawFee() {
		return t0DrawFee;
	}

	public void setT0DrawFee(String t0DrawFee) {
		this.t0DrawFee = t0DrawFee;
	}

	public String getT0TradeRate() {
		return t0TradeRate;
	}

	public void setT0TradeRate(String t0TradeRate) {
		this.t0TradeRate = t0TradeRate;
	}
	
	@Length(min=0, max=32, message="合作方标识长度必须介于 0 和 32 之间")
	public String getCooperator() {
		return cooperator;
	}

	public void setCooperator(String cooperator) {
		this.cooperator = cooperator;
	}
	
	@Length(min=1, max=11, message="商户ID长度必须介于 1 和 11 之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Length(min=0, max=6, message="响应码长度必须介于 0 和 6 之间")
	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	
	@Length(min=0, max=256, message="响应描述长度必须介于 0 和 256 之间")
	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	
	@Length(min=0, max=32, message="原支付流水号长度必须介于 0 和 32 之间")
	public String getOriReqMsgId() {
		return oriReqMsgId;
	}

	public void setOriReqMsgId(String oriReqMsgId) {
		this.oriReqMsgId = oriReqMsgId;
	}
	
	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
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
}