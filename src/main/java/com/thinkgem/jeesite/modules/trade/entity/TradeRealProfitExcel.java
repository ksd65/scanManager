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


public class TradeRealProfitExcel extends DataEntity<TradeRealProfitExcel> {
	
	private static final long serialVersionUID = 1L;
	private String memberId;		// 商户ID
	private String memberCode;		// 商户编号
	private String merchantCode;	// 通道商户编号
	private String merchantName;	// 通道商户编号
	private String routeCode;		// 支付通道ID
	private String routeName;		// 支付通道
	private String txnDate;
	private String txnMethod;		// 交易方式
	private String txnType;		// 交易类型 1:微信 2:支付宝
	private String tradeMoney;
	private String settleMoney;
	private String platTradeRate;
	private String agentTradeRate;
	private String memberTradeRate;
	private String realPlatTradeRate;
	private String platCost;
	private String agentCost;
	private String memberCost;
	private String drawPer;
	private String platProfit;
	private String agentProfit;
	private String agentProfitD1;
	private String agentOfficeId;
	private String platProfitRate;
	private String agentProfitRate;
	private String agentName;
	private String realPlatProfit;
	private String realPlatCost;
	private String realPlatProfitRate;
	
	private String agentTradeRateLevel2;
	private String agentProfitRateLevel1;
	private String agentProfitLevel1;
	private String channelProfitRate;
	private String channelProfit;
	
	private Office office;			// 机构
	private Member member;			// 商户
	private String memberName;		// 商户名称
	private String officeName;		// 所属机构
	
	private String beginTime;
	private String endTime;
	
	private String mobilePhone;		// 手机号码	
	private String agentNameLevel1;// 所属一级机构
	

	
	public TradeRealProfitExcel() {
		super();
	}

	public TradeRealProfitExcel(String id){
		super(id);
	}
	
	@Length(min=1, max=11, message="商户ID长度必须介于 1 和 11 之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@ExcelField(title="商户编号", align=2, sort=20)
	@Length(min=0, max=32, message="商户编号长度必须介于 0 和 32 之间")
	public String getMemberCode() {
		return memberCode;
	}
	
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	
	@ExcelField(title="商户名称", align=2, sort=30)
	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

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
	
	
	public String getOfficeName() {
		if(office == null){
			return officeName;
		}
		return office.getName();
	}
	
	
	
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

		
	@ExcelField(title="交易类型", align=2, sort=50, dictType="txn_type")
	@Length(min=1, max=1, message="交易类型长度必须介于 1 和 1 之间")
	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

   // @ExcelField(title="通道商户编码", align=2, sort=90)
	@Length(min=0, max=32, message="通道商户编号长度必须介于 0 和 32 之间")
	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
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

	@ExcelField(title="交易方式", align=2, sort=40, dictType="txn_method")
	public String getTxnMethod() {
		return txnMethod;
	}

	public void setTxnMethod(String txnMethod) {
		this.txnMethod = txnMethod;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	@ExcelField(title="交易通道", align=2, sort=60, dictType="route_code")
	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	@ExcelField(title="交易日期", align=2, sort=10)
	public String getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

    @ExcelField(title="交易金额", align=2, sort=80)
    public String getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(String tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	@ExcelField(title="商户应收", align=2, sort=210)
	public String getSettleMoney() {
		return settleMoney;
	}

	public void setSettleMoney(String settleMoney) {
		this.settleMoney = settleMoney;
	}

	@ExcelField(title="通道扣率", align=2, sort=150)
	public String getPlatTradeRate() {
		return platTradeRate;
	}

	public void setPlatTradeRate(String platTradeRate) {
		this.platTradeRate = platTradeRate;
	}

	@ExcelField(title="一级代理商成本扣率", align=2, sort=95)
	public String getAgentTradeRate() {
		return agentTradeRate;
	}

	public void setAgentTradeRate(String agentTradeRate) {
		this.agentTradeRate = agentTradeRate;
	}

	@ExcelField(title="商户成本扣率", align=2, sort=190)
	public String getMemberTradeRate() {
		return memberTradeRate;
	}

	public void setMemberTradeRate(String memberTradeRate) {
		this.memberTradeRate = memberTradeRate;
	}

	@ExcelField(title="通道佣金", align=2, sort=160)
	public String getPlatCost() {
		return platCost;
	}

	public void setPlatCost(String platCost) {
		this.platCost = platCost;
	}

	public String getAgentCost() {
		return agentCost;
	}

	public void setAgentCost(String agentCost) {
		this.agentCost = agentCost;
	}

	@ExcelField(title="商户付佣", align=2, sort=200)
	public String getMemberCost() {
		return memberCost;
	}

	public void setMemberCost(String memberCost) {
		this.memberCost = memberCost;
	}

	public String getDrawPer() {
		return drawPer;
	}

	public void setDrawPer(String drawPer) {
		this.drawPer = drawPer;
	}

    
    public String getPlatProfit() {
		return platProfit;
	}

	public void setPlatProfit(String platProfit) {
		this.platProfit = platProfit;
	}

//	@ExcelField(title="代理商佣金", align=2, sort=140)
	public String getAgentProfit() {
		return agentProfit;
	}

	public void setAgentProfit(String agentProfit) {
		this.agentProfit = agentProfit;
	}

	public String getAgentProfitD1() {
		return agentProfitD1;
	}

	public void setAgentProfitD1(String agentProfitD1) {
		this.agentProfitD1 = agentProfitD1;
	}

	public String getAgentOfficeId() {
		return agentOfficeId;
	}

	public void setAgentOfficeId(String agentOfficeId) {
		this.agentOfficeId = agentOfficeId;
	}

	
	public String getPlatProfitRate() {
		return platProfitRate;
	}

	public void setPlatProfitRate(String platProfitRate) {
		this.platProfitRate = platProfitRate;
	}

//	@ExcelField(title="代理商佣金扣率", align=2, sort=130)
	public String getAgentProfitRate() {
		return agentProfitRate;
	}

	public void setAgentProfitRate(String agentProfitRate) {
		this.agentProfitRate = agentProfitRate;
	}
	@ExcelField(title="代理商名称", align=2, sort=70)
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	@ExcelField(title="平台成本扣率", align=2, sort=90)
	public String getRealPlatTradeRate() {
		return realPlatTradeRate;
	}

	public void setRealPlatTradeRate(String realPlatTradeRate) {
		this.realPlatTradeRate = realPlatTradeRate;
	}

	@ExcelField(title="平台佣金", align=2, sort=120)
	public String getRealPlatProfit() {
		return realPlatProfit;
	}

	public void setRealPlatProfit(String realPlatProfit) {
		this.realPlatProfit = realPlatProfit;
	}

	//@ExcelField(title="上游佣金", align=2, sort=160)
	public String getRealPlatCost() {
		return realPlatCost;
	}

	public void setRealPlatCost(String realPlatCost) {
		this.realPlatCost = realPlatCost;
	}
	
	@ExcelField(title="平台佣金扣率", align=2, sort=110)
	public String getRealPlatProfitRate() {
		return realPlatProfitRate;
	}

	public void setRealPlatProfitRate(String realPlatProfitRate) {
		this.realPlatProfitRate = realPlatProfitRate;
	}

	public String getAgentTradeRateLevel2() {
		return agentTradeRateLevel2;
	}

	public void setAgentTradeRateLevel2(String agentTradeRateLevel2) {
		this.agentTradeRateLevel2 = agentTradeRateLevel2;
	}
	
	@ExcelField(title="一级代理商佣金扣率", align=2, sort=130)
	public String getAgentProfitRateLevel1() {
		return agentProfitRateLevel1;
	}

	public void setAgentProfitRateLevel1(String agentProfitRateLevel1) {
		this.agentProfitRateLevel1 = agentProfitRateLevel1;
	}

	@ExcelField(title="一级代理商佣金", align=2, sort=140)
	public String getAgentProfitLevel1() {
		return agentProfitLevel1;
	}

	public void setAgentProfitLevel1(String agentProfitLevel1) {
		this.agentProfitLevel1 = agentProfitLevel1;
	}
	@ExcelField(title="渠道方扣率", align=2, sort=170)
	public String getChannelProfitRate() {
		return channelProfitRate;
	}

	public void setChannelProfitRate(String channelProfitRate) {
		this.channelProfitRate = channelProfitRate;
	}

	@ExcelField(title="渠道方佣金", align=2, sort=180)
	public String getChannelProfit() {
		return channelProfit;
	}

	public void setChannelProfit(String channelProfit) {
		this.channelProfit = channelProfit;
	}

	
	
	
}