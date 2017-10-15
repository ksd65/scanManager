package com.thinkgem.jeesite.modules.report.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 我的分润
 * @author WuFengSheng
 *
 */
public class PayBillMonthMyProfitRpt extends DataEntity<PayBillMonthMyProfitRpt>{
	
	private static final long serialVersionUID = 1L;
	
	public PayBillMonthMyProfitRpt(){
		super();
	}
	
	public PayBillMonthMyProfitRpt(String id){
		super(id);
	}
	
	private Office office;
	private String year;
	private String month;
	
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
		
	private String totalCount;
	private String totalAmount;
	private String totalProfit;
	private String isAdmin;
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@ExcelField(title="月份", align=2, sort=10)
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@ExcelField(title="D0交易笔数", align=2, sort=20)
	public String getD0Count() {
		return d0Count;
	}

	public void setD0Count(String d0Count) {
		this.d0Count = d0Count;
	}

	@ExcelField(title="D0交易总额", align=2, sort=30)
	public String getD0Amount() {
		return d0Amount;
	}

	public void setD0Amount(String d0Amount) {
		this.d0Amount = d0Amount;
	}
	
	@ExcelField(title="T1交易笔数", align=2, sort=40)
	public String getT1Count() {
		return t1Count;
	}

	public void setT1Count(String t1Count) {
		this.t1Count = t1Count;
	}

	@ExcelField(title="T1交易总额", align=2, sort=50)
	public String getT1Amount() {
		return t1Amount;
	}

	public void setT1Amount(String t1Amount) {
		this.t1Amount = t1Amount;
	}
	
	@ExcelField(title="快捷(有积分)交易笔数", align=2, sort=60)
	public String getMlJfCount() {
		return mlJfCount;
	}

	public void setMlJfCount(String mlJfCount) {
		this.mlJfCount = mlJfCount;
	}

	@ExcelField(title="快捷(有积分)交易总额", align=2, sort=70)
	public String getMlJfAmount() {
		return mlJfAmount;
	}

	public void setMlJfAmount(String mlJfAmount) {
		this.mlJfAmount = mlJfAmount;
	}
	
	@ExcelField(title="快捷(无积分)交易笔数", align=2, sort=80)
	public String getMlWjfCount() {
		return mlWjfCount;
	}

	public void setMlWjfCount(String mlWjfCount) {
		this.mlWjfCount = mlWjfCount;
	}

	@ExcelField(title="快捷(无积分)交易总额", align=2, sort=90)
	public String getMlWjfAmount() {
		return mlWjfAmount;
	}

	public void setMlWjfAmount(String mlWjfAmount) {
		this.mlWjfAmount = mlWjfAmount;
	}
	
	@ExcelField(title="交易总笔数", align=2, sort=100)
	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	@ExcelField(title="交易总金额", align=2, sort=110)
	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	@ExcelField(title="D0预期分润", align=2, sort=120)
	public String getD0Profit() {
		return d0Profit;
	}

	public void setD0Profit(String d0Profit) {
		this.d0Profit = d0Profit;
	}

	@ExcelField(title="T1预期分润", align=2, sort=130)
	public String getT1Profit() {
		return t1Profit;
	}

	public void setT1Profit(String t1Profit) {
		this.t1Profit = t1Profit;
	}
	
	@ExcelField(title="快捷(有积分)预期分润", align=2, sort=140)
	public String getMlJfProfit() {
		return mlJfProfit;
	}

	public void setMlJfProfit(String mlJfProfit) {
		this.mlJfProfit = mlJfProfit;
	}

	@ExcelField(title="快捷(无积分)预期分润", align=2, sort=150)
	public String getMlWjfProfit() {
		return mlWjfProfit;
	}

	public void setMlWjfProfit(String mlWjfProfit) {
		this.mlWjfProfit = mlWjfProfit;
	}
	
	@ExcelField(title="预期分润合计", align=2, sort=160)
	public String getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(String totalProfit) {
		this.totalProfit = totalProfit;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

}
