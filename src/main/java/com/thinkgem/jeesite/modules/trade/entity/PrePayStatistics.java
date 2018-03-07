package com.thinkgem.jeesite.modules.trade.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.sys.entity.Office;

public class PrePayStatistics extends DataEntity<PrePayStatistics>{

	private static final long serialVersionUID = -1898425812553186711L;

	private String memberId;
	private String tradeRate;
	private String preMoney;		// 预收款
	private String hisUsePreMoney;  //历史使用预收款
	private String todayUsePreMoney; //今天使用预收款
	private String usePreMoney;   //总使用预收款
	private String undealPreMoney;  //未处理预收款
	private String leftPreMoney;   //剩余预收款
	
	private String hisTradeMoney;   //历史交易金额
	private String todayTradeMoney; //今天交易金额
	
	private String tradeMoney;   //总交易金额
	private String undealMoney;  //未处理交易金额
	
	private String leftTradeMoney;   //剩余可交易金额
	
	private Office office;			// 机构
	private Member member;			// 商户
	
	private String memberName;		// 商户名称
	private String officeName;		// 所属机构
	
	
	private String txnDate;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	
	
	public String getPreMoney() {
		return preMoney;
	}
	public void setPreMoney(String preMoney) {
		this.preMoney = preMoney;
	}
	public String getHisTradeMoney() {
		return hisTradeMoney;
	}
	public void setHisTradeMoney(String hisTradeMoney) {
		this.hisTradeMoney = hisTradeMoney;
	}
	public String getTodayTradeMoney() {
		return todayTradeMoney;
	}
	public void setTodayTradeMoney(String todayTradeMoney) {
		this.todayTradeMoney = todayTradeMoney;
	}
	public String getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(String tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public String getUndealMoney() {
		return undealMoney;
	}
	public void setUndealMoney(String undealMoney) {
		this.undealMoney = undealMoney;
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
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getTradeRate() {
		return tradeRate;
	}
	public void setTradeRate(String tradeRate) {
		this.tradeRate = tradeRate;
	}
	public String getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}
	public String getHisUsePreMoney() {
		return hisUsePreMoney;
	}
	public void setHisUsePreMoney(String hisUsePreMoney) {
		this.hisUsePreMoney = hisUsePreMoney;
	}
	public String getTodayUsePreMoney() {
		return todayUsePreMoney;
	}
	public void setTodayUsePreMoney(String todayUsePreMoney) {
		this.todayUsePreMoney = todayUsePreMoney;
	}
	public String getUsePreMoney() {
		return usePreMoney;
	}
	public void setUsePreMoney(String usePreMoney) {
		this.usePreMoney = usePreMoney;
	}
	public String getLeftPreMoney() {
		return leftPreMoney;
	}
	public void setLeftPreMoney(String leftPreMoney) {
		this.leftPreMoney = leftPreMoney;
	}
	public String getLeftTradeMoney() {
		return leftTradeMoney;
	}
	public void setLeftTradeMoney(String leftTradeMoney) {
		this.leftTradeMoney = leftTradeMoney;
	}
	public String getUndealPreMoney() {
		return undealPreMoney;
	}
	public void setUndealPreMoney(String undealPreMoney) {
		this.undealPreMoney = undealPreMoney;
	}
	
	
	
}
