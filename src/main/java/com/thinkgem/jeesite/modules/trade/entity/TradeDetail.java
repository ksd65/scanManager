/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.entity;

import org.hibernate.validator.constraints.Length;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.mem.entity.Member;

/**
 * 交易明细查询Entity
 * @author chenjc
 * @version 2016-12-15
 */
public class TradeDetail extends DataEntity<TradeDetail> {
	
	private static final long serialVersionUID = 1L;
	private String memberId;		// 商户ID
	private String memberCode;		// 商户编号
	private String merchantCode;	// 通道商户编号
	private String money;			// 交易金额
	private String d0Money;			// D0结算金额
	private String d0MemberFee;		// D0商户交易手续费
	private String d0MemberDraw;	// D0商户提现手续费
	private String d0RoutewayFee;	// D0通道交易手续费
	private String d0RoutewayDraw;	// D0通道提现手续费
	
	private String t1Money;			// T1结算金额
	private String t1MemberFee;		// T1商户交易手续费
	private String t1MemberDraw;	// T1商户提现手续费
	private String t1RoutewayFee;	// T1通道交易手续费
	private String t1RoutewayDraw;	// T1通道 提现手续费
	
	private String orderCode;		// 交易订单编号
	private String txnMethod;		// 交易方式
	private String txnType;		// 交易类型 1:微信 2:支付宝
	private String ptSerialNo;		// 平台流水号
	private String reqDate;		// 请求日期时间
	private String respDate;		// 应答日期时间
	private String respType;		// 应答类型 S:成功 E:失败 R:不确定
	private String respCode;		// 应答码
	private String respMsg;		// 应答描述
	private String cardType;		// 借贷记类型 1:借记 2:贷记 3:其他
	private String routeId;		// 支付通道ID
	private String payTime;		// 支付时间
	private String balanceDate;		// 对账日期
	private String channelNo;		// 支付渠道流水号
	private String settleType;		// 结算类型 0:D+0 1:T+1
	private String settleCancelFlag;		// 清算撤销标识
	private Office office;			// 机构
	private Member member;			// 商户
	
	// 吴逢生 2017-01-11
	private String memberName;		// 商户名称
	private String officeName;		// 所属机构
	private String txDate;			// 交易日期
	private String txTime;			// 交易时间
	
	private String beginTime;
	private String endTime;
	
	
	private String memberTradeRate;		// 商户费率
	private String memberDrawFee;		// 商户提现费
	private String memberSettleMoney;	// 结算金额
	
	private String mobilePhone;		// 手机号码
	
	// 吴逢生 2017-04-03
	private String beginMonth;
	private String endMonth;
	private String txMonth;
	private String wxMoney;
	private String qqMoney;
	private String zfbMoney;
	private String allMoney;
	private String jdMoney;
	private String bdMoney;
	private String interfaceType;
	
	private String agentNameLevel1;// 所属一级机构
	
	private String bounsQuickMoney;
	private String quickMoney;
	
	private String balanceStartTime;//对账开始时间
	private String balanceEndTime;	//对账结束时间
	
	private String orderNumOuter;
	
	public TradeDetail() {
		super();
	}

	public TradeDetail(String id){
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

	@ExcelField(title="结算方式", align=2, sort=70 , dictType="settle_type")
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

	@ExcelField(title="交易日期", align=2, sort=90)
	public String getTxDate() {
		return txDate;
	}

	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}

	@ExcelField(title="交易时间", align=2, sort=100)
	public String getTxTime() {
		return txTime;
	}

	public void setTxTime(String txTime) {
		this.txTime = txTime;
	}
	
	@ExcelField(title="交易状态", align=2, sort=110, dictType="resp_type")
	@Length(min=0, max=1, message="应答类型长度必须介于 0 和 1 之间")
	public String getRespType() {
		return respType;
	}

	public void setRespType(String respType) {
		this.respType = respType;
	}
	
	@ExcelField(title="借贷记类型", align=2, sort=120, dictType="card_type")
	@Length(min=0, max=1, message="借贷记类型长度必须介于 0 和 1 之间")
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	@ExcelField(title="支付时间", align=2, sort=130)
	@Length(min=0, max=16, message="支付时间长度必须介于 0 和 16 之间")
	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	
	@ExcelField(title="对账日期", align=2, sort=140)
	@Length(min=0, max=10, message="对账日期长度必须介于 0 和 10 之间")
	public String getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}
	
	//@ExcelField(title="接口类型", align=2, sort=150)
	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}
	
	@ExcelField(title="商户提现费", align=2, sort=60)
	public String getMemberDrawFee() {
		return memberDrawFee;
	}

	public void setMemberDrawFee(String memberDrawFee) {
		this.memberDrawFee = memberDrawFee;
	}

	public String getMemberSettleMoney() {
		return memberSettleMoney;
	}

	public void setMemberSettleMoney(String memberSettleMoney) {
		this.memberSettleMoney = memberSettleMoney;
	}
	
	
	
	// @ExcelField(title="通道商户编号", align=2, sort=2)
	@Length(min=0, max=32, message="通道商户编号长度必须介于 0 和 32 之间")
	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	
	//@ExcelField(title="D0结算金额", align=2, sort=4)
	public String getD0Money() {
		return d0Money;
	}
	
	public void setD0Money(String d0Money) {
		this.d0Money = d0Money;
	}
	
	//@ExcelField(title="D0商户交易手续费", align=2, sort=5)
	public String getD0MemberFee() {
		return d0MemberFee;
	}
	
	public void setD0MemberFee(String d0MemberFee) {
		this.d0MemberFee = d0MemberFee;
	}
	
	//@ExcelField(title="D0商户提现手续费", align=2, sort=6)
	public String getD0MemberDraw() {
		return d0MemberDraw;
	}
	
	public void setD0MemberDraw(String d0MemberDraw) {
		this.d0MemberDraw = d0MemberDraw;
	}
	
	//@ExcelField(title="D0通道交易手续费", align=2, sort=7)
	public String getD0RoutewayFee() {
		return d0RoutewayFee;
	}
	
	public void setD0RoutewayFee(String d0RoutewayFee) {
		this.d0RoutewayFee = d0RoutewayFee;
	}
	
	//@ExcelField(title="D0通道提现手续费", align=2, sort=8)
	public String getD0RoutewayDraw() {
		return d0RoutewayDraw;
	}
	
	public void setD0RoutewayDraw(String d0RoutewayDraw) {
		this.d0RoutewayDraw = d0RoutewayDraw;
	}
	
	//@ExcelField(title="T1结算金额", align=2, sort=9)
	public String getT1Money() {
		return t1Money;
	}
	
	public void setT1Money(String t1Money) {
		this.t1Money = t1Money;
	}
	
	//@ExcelField(title="T1商户交易手续费", align=2, sort=10)
	public String getT1MemberFee() {
		return t1MemberFee;
	}
	
	public void setT1MemberFee(String t1MemberFee) {
		this.t1MemberFee = t1MemberFee;
	}
	
	//@ExcelField(title="T1商户提现手续费", align=2, sort=11)
	public String getT1MemberDraw() {
		return t1MemberDraw;
	}
	
	public void setT1MemberDraw(String t1MemberDraw) {
		this.t1MemberDraw = t1MemberDraw;
	}
	
	//@ExcelField(title="T1通道交易手续费", align=2, sort=12)
	public String getT1RoutewayFee() {
		return t1RoutewayFee;
	}
	
	public void setT1RoutewayFee(String t1RoutewayFee) {
		this.t1RoutewayFee = t1RoutewayFee;
	}
	
	//@ExcelField(title="T1通道提现手续费", align=2, sort=13)
	public String getT1RoutewayDraw() {
		return t1RoutewayDraw;
	}
	
	public void setT1RoutewayDraw(String t1RoutewayDraw) {
		this.t1RoutewayDraw = t1RoutewayDraw;
	}
	
	
	@Length(min=1, max=32, message="平台流水号长度必须介于 1 和 32 之间")
	public String getPtSerialNo() {
		return ptSerialNo;
	}

	public void setPtSerialNo(String ptSerialNo) {
		this.ptSerialNo = ptSerialNo;
	}
	
	
	//@ExcelField(title="请求日期时间", align=2, sort=16)
	@Length(min=0, max=16, message="请求日期时间长度必须介于 0 和 16 之间")
	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	
	@Length(min=0, max=16, message="应答日期时间长度必须介于 0 和 16 之间")
	public String getRespDate() {
		return respDate;
	}

	public void setRespDate(String respDate) {
		this.respDate = respDate;
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
	@ExcelField(title="通道流水号", align=2, sort=77)
	@Length(min=0, max=64, message="支付渠道流水号长度必须介于 0 和 64 之间")
	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	
	@Length(min=0, max=1, message="清算撤销标识长度必须介于 0 和 1 之间")
	public String getSettleCancelFlag() {
		return settleCancelFlag;
	}

	public void setSettleCancelFlag(String settleCancelFlag) {
		this.settleCancelFlag = settleCancelFlag;
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

	public String getTxMonth() {
		return txMonth;
	}

	public void setTxMonth(String txMonth) {
		this.txMonth = txMonth;
	}

	public String getWxMoney() {
		return wxMoney;
	}

	public void setWxMoney(String wxMoney) {
		this.wxMoney = wxMoney;
	}

	public String getQqMoney() {
		return qqMoney;
	}

	public void setQqMoney(String qqMoney) {
		this.qqMoney = qqMoney;
	}

	public String getZfbMoney() {
		return zfbMoney;
	}

	public void setZfbMoney(String zfbMoney) {
		this.zfbMoney = zfbMoney;
	}

	public String getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(String allMoney) {
		this.allMoney = allMoney;
	}

	public String getBounsQuickMoney() {
		return bounsQuickMoney;
	}

	public void setBounsQuickMoney(String bounsQuickMoney) {
		this.bounsQuickMoney = bounsQuickMoney;
	}

	public String getQuickMoney() {
		return quickMoney;
	}

	public void setQuickMoney(String quickMoney) {
		this.quickMoney = quickMoney;
	}

	public String getJdMoney() {
		return jdMoney;
	}

	public void setJdMoney(String jdMoney) {
		this.jdMoney = jdMoney;
	}
	
	public String getBdMoney() {
		return bdMoney;
	}

	public void setBdMoney(String bdMoney) {
		this.bdMoney = bdMoney;
	}
	
	public String getBalanceStartTime() {
		return balanceStartTime;
	}

	public void setBalanceStartTime(String balanceStartTime) {
		this.balanceStartTime = balanceStartTime;
	}

	public String getBalanceEndTime() {
		return balanceEndTime;
	}

	public void setBalanceEndTime(String balanceEndTime) {
		this.balanceEndTime = balanceEndTime;
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
	
	
}