package com.thinkgem.jeesite.modules.report.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 
 * @author WuFengSheng
 *
 */
public class PayBillCurrentMonthProfitRpt extends DataEntity<PayBillCurrentMonthProfitRpt>{
	
	private static final long serialVersionUID = 1L;
	
	private String officeId;
	private String officeName;
	private Office office;
	private String memberName;	// 商户名称
	private String txDate;		// 交易日期
	private String amount;		// 交易金额
	private String transType;	// 交易类型
	private String profit;		// 分润

	private String d0Count;
	private String d0Amount;
	private String d0Profit;

	private String t1Count;
	private String t1Amount;
	private String t1Profit;

	// 快捷
	private String mlJfCount;
	private String mlJfAmount;
	private String mlJfProfit;

	private String mlWjfCount;
	private String mlWjfAmount;
	private String mlWjfProfit;
	
	private String isAdmin;
	
	private String beginTime;
	private String endTime;

	public PayBillCurrentMonthProfitRpt(){
		super();
	}
	
	public PayBillCurrentMonthProfitRpt(String id){
		super(id);
	}


	public String getD0Count() {
		return d0Count;
	}

	public void setD0Count(String d0Count) {
		this.d0Count = d0Count;
	}

	public String getD0Amount() {
		return d0Amount;
	}

	public void setD0Amount(String d0Amount) {
		this.d0Amount = d0Amount;
	}

	public String getD0Profit() {
		return d0Profit;
	}

	public void setD0Profit(String d0Profit) {
		this.d0Profit = d0Profit;
	}

	public String getT1Count() {
		return t1Count;
	}

	public void setT1Count(String t1Count) {
		this.t1Count = t1Count;
	}

	public String getT1Amount() {
		return t1Amount;
	}

	public void setT1Amount(String t1Amount) {
		this.t1Amount = t1Amount;
	}

	public String getT1Profit() {
		return t1Profit;
	}

	public void setT1Profit(String t1Profit) {
		this.t1Profit = t1Profit;
	}

	public String getMlJfCount() {
		return mlJfCount;
	}

	public void setMlJfCount(String mlJfCount) {
		this.mlJfCount = mlJfCount;
	}

	public String getMlJfAmount() {
		return mlJfAmount;
	}

	public void setMlJfAmount(String mlJfAmount) {
		this.mlJfAmount = mlJfAmount;
	}

	public String getMlJfProfit() {
		return mlJfProfit;
	}

	public void setMlJfProfit(String mlJfProfit) {
		this.mlJfProfit = mlJfProfit;
	}

	public String getMlWjfCount() {
		return mlWjfCount;
	}

	public void setMlWjfCount(String mlWjfCount) {
		this.mlWjfCount = mlWjfCount;
	}

	public String getMlWjfAmount() {
		return mlWjfAmount;
	}

	public void setMlWjfAmount(String mlWjfAmount) {
		this.mlWjfAmount = mlWjfAmount;
	}

	public String getMlWjfProfit() {
		return mlWjfProfit;
	}

	public void setMlWjfProfit(String mlWjfProfit) {
		this.mlWjfProfit = mlWjfProfit;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@ExcelField(title="商户名称", align=2, sort=10)
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@ExcelField(title="所属机构", align=2, sort=20)
	public String getOfficeName() {
		return office.getName();
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	@ExcelField(title="交易日期", align=2, sort=30)
	public String getTxDate() {
		return txDate;
	}

	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}
	
	@ExcelField(title="交易金额", align=2, sort=40)
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@ExcelField(title="交易类型", align=2, sort=50)
	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	@ExcelField(title="分润", align=2, sort=60)
	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
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
