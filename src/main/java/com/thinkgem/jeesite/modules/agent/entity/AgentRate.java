/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 代理商费率成本配置Entity
 * @author 吴逢生
 * @version 2017-03-11
 */
public class AgentRate extends DataEntity<AgentRate> {
	
	private static final long serialVersionUID = 1L;
	private Office office;			// 机构编号
	private String agtName;			//代理商名称
	private String t0DrawFee;		// T0提现费
	private String t0TradeRate;		// T0交易费率
	private String t1DrawFee;		// T1提现费
	private String t1TradeRate;		// T1交易费率
	
	// 快捷 6:快捷有积分 7:快捷无积分
	private String bonusQuickRate;	// 有积分快捷费率
	private String bonusQuickFee; 	// 有积分快捷提现费
	private String quickRate;		// 无积分快捷费率
	private String quickFee; 		// 无积分快捷提现费
	
	private String mT0DrawFee;		// 默认商户T0提现费
	private String mT0TradeRate;	// 默认商户T0交易费率
	private String mT1DrawFee;		// 默认商户T1提现费
	private String mT1TradeRate;	// 默认商户T1交易费率
	
	// 快捷 6:快捷有积分 7:快捷无积分
	private String mBonusQuickRate;	// 默认商户有积分快捷费率
	private String mBonusQuickFee; 	// 默认商户有积分快捷提现费
	private String mQuickRate;		// 默认商户无积分快捷费率
	private String mQuickFee; 		// 默认商户无积分快捷提现费
	
	private String mUrl;			// 商户二维码地址
	private String mImg; 		// 商户二维码图片
	
	public AgentRate() {
		super();
	}

	public AgentRate(String id){
		super(id);
	}

	@NotNull(message="机构编号不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	public String getAgtName() {
		return agtName;
	}

	public void setAgtName(String agtName) {
		this.agtName = agtName;
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
	
	public String getT1DrawFee() {
		return t1DrawFee;
	}

	public void setT1DrawFee(String t1DrawFee) {
		this.t1DrawFee = t1DrawFee;
	}
	
	public String getT1TradeRate() {
		return t1TradeRate;
	}

	public void setT1TradeRate(String t1TradeRate) {
		this.t1TradeRate = t1TradeRate;
	}

	public String getBonusQuickRate() {
		return bonusQuickRate;
	}

	public void setBonusQuickRate(String bonusQuickRate) {
		this.bonusQuickRate = bonusQuickRate;
	}

	public String getBonusQuickFee() {
		return bonusQuickFee;
	}

	public void setBonusQuickFee(String bonusQuickFee) {
		this.bonusQuickFee = bonusQuickFee;
	}

	public String getQuickRate() {
		return quickRate;
	}

	public void setQuickRate(String quickRate) {
		this.quickRate = quickRate;
	}

	public String getQuickFee() {
		return quickFee;
	}

	public void setQuickFee(String quickFee) {
		this.quickFee = quickFee;
	}
	
	
	
	
	public String getMT0DrawFee() {
		return mT0DrawFee;
	}

	public void setMT0DrawFee(String mT0DrawFee) {
		this.mT0DrawFee = mT0DrawFee;
	}
	
	public String getMT0TradeRate() {
		return mT0TradeRate;
	}

	public void setMT0TradeRate(String mT0TradeRate) {
		this.mT0TradeRate = mT0TradeRate;
	}
	
	public String getMT1DrawFee() {
		return mT1DrawFee;
	}

	public void setMT1DrawFee(String mT1DrawFee) {
		this.mT1DrawFee = mT1DrawFee;
	}
	
	public String getMT1TradeRate() {
		return mT1TradeRate;
	}

	public void setMT1TradeRate(String mT1TradeRate) {
		this.mT1TradeRate = mT1TradeRate;
	}

	public String getMBonusQuickRate() {
		return mBonusQuickRate;
	}

	public void setMBonusQuickRate(String mBonusQuickRate) {
		this.mBonusQuickRate = mBonusQuickRate;
	}

	public String getMBonusQuickFee() {
		return mBonusQuickFee;
	}

	public void setMBonusQuickFee(String mBonusQuickFee) {
		this.mBonusQuickFee = mBonusQuickFee;
	}

	public String getMQuickRate() {
		return mQuickRate;
	}

	public void setMQuickRate(String mQuickRate) {
		this.mQuickRate = mQuickRate;
	}

	public String getMQuickFee() {
		return mQuickFee;
	}

	public void setMQuickFee(String mQuickFee) {
		this.mQuickFee = mQuickFee;
	}
	
	public String getMUrl() {
		return mUrl;
	}

	public void setMUrl(String mUrl) {
		this.mUrl = mUrl;
	}

	public String getMImg() {
		return mImg;
	}

	public void setMImg(String mImg) {
		this.mImg = mImg;
	}
	
	
}