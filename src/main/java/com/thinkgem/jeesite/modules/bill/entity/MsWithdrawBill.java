/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bill.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 提现对账查询Entity
 * @author chenjc
 * @version 2016-12-23
 */
public class MsWithdrawBill extends DataEntity<MsWithdrawBill> {
	
	private static final long serialVersionUID = 1L;
	private String cooperator;		// 合作方标识
	private String memberId;		// 商户ID
	private String merchantCode;		// 通道商户号
	private String smzfMsgId;		// 平台流水号
	private String reqMsgId;		// 交易流水号
	private String accNo;		// 交易账号
	private String accName;		// 交易户名
	private String drawAmount;		// 提现金额
	private String drawFee;		// 提现手续费
	private String tradeFee;		// 交易手续费
	private String settleDate;		// 对账日期
	private String respType;		// 交易状态
	private String respCode;		// 响应码
	private String respMsg;		// 响应描述
	private Office office;			// 机构
	private Member member;			// 商户
	
	private String amount;			// 交易金额
	
	// 吴逢生 2017-01-14
	private String memberName;		// 商户名称
	private String officeId;
	private String officeName;		// 所属机构
	private String beginTime;
	private String endTime;
	private Date createTime;		// 创建时间
	
	private String mobilePhone;
	private String t0TradeRate;		// t0交易手续费扣率
	
	private String agentNameLevel1;// 所属一级机构
	private String settleType;		// 结算方式
	
	public MsWithdrawBill() {
		super();
	}

	public MsWithdrawBill(String id){
		super(id);
	}
	
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	@ExcelField(title="商户名称", align=2, sort=10)
	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@ExcelField(title="手机号", align=2, sort=15)
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@ExcelField(title="所属一级机构", align=2, sort=20)
	public String getAgentNameLevel1() {
		return agentNameLevel1;
	}

	public void setAgentNameLevel1(String agentNameLevel1) {
		this.agentNameLevel1 = agentNameLevel1;
	}
	
	@ExcelField(title="所属机构", align=2, sort=30)
	public String getOfficeName() {
		return office.getName();
	}
	
	@ExcelField(title="通道商户号", align=2, sort=35)
	@Length(min=0, max=32, message="通道商户号长度必须介于 0 和 32 之间")
	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	
	@Length(min=0, max=32, message="合作方标识长度必须介于 0 和 32 之间")
	public String getCooperator() {
		return cooperator;
	}

	public void setCooperator(String cooperator) {
		this.cooperator = cooperator;
	}
	
	@Length(min=0, max=11, message="商户ID长度必须介于 0 和 11 之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Length(min=0, max=32, message="平台流水号长度必须介于 0 和 32 之间")
	public String getSmzfMsgId() {
		return smzfMsgId;
	}

	public void setSmzfMsgId(String smzfMsgId) {
		this.smzfMsgId = smzfMsgId;
	}
	
	@ExcelField(title="交易流水号", align=2, sort=40)
	@Length(min=0, max=32, message="交易流水号长度必须介于 0 和 32 之间")
	public String getReqMsgId() {
		return reqMsgId;
	}

	public void setReqMsgId(String reqMsgId) {
		this.reqMsgId = reqMsgId;
	}
	
	@ExcelField(title="交易账号", align=2, sort=50)
	@Length(min=0, max=40, message="交易账号长度必须介于 0 和 40 之间")
	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	
	@ExcelField(title="交易户名", align=2, sort=60)
	@Length(min=0, max=128, message="交易户名长度必须介于 0 和 128 之间")
	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}
	
	@ExcelField(title="交易金额", align=2, sort=70)
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@ExcelField(title="商户费率", align=2, sort=75)
	public String getT0TradeRate() {
		return t0TradeRate;
	}

	public void setT0TradeRate(String t0TradeRate) {
		this.t0TradeRate = t0TradeRate;
	}
	
	@ExcelField(title="提现金额", align=2, sort=80)
	public String getDrawAmount() {
		return drawAmount;
	}

	public void setDrawAmount(String drawAmount) {
		this.drawAmount = drawAmount;
	}
	
	@ExcelField(title="提现手续费", align=2, sort=90)
	public String getDrawFee() {
		return drawFee;
	}

	public void setDrawFee(String drawFee) {
		this.drawFee = drawFee;
	}
	
	@ExcelField(title="交易手续费", align=2, sort=100)
	public String getTradeFee() {
		return tradeFee;
	}

	public void setTradeFee(String tradeFee) {
		this.tradeFee = tradeFee;
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
	
	//@ExcelField(title="创建时间", align=2, sort=130)
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return super.createDate;
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

	public String getSettleType() {
		return settleType;
	}

	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}
	
}