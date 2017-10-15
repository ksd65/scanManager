package com.thinkgem.jeesite.modules.report.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 
 * @author WuFengSheng
 *
 */
public class PayBillMonthDetailRpt extends DataEntity<PayBillMonthDetailRpt>{
	
	private static final long serialVersionUID = 1L;
	
	private String officeId;
	private String officeName;
	private Office office;
	private String memberName;
	private String month;
	
	private String d0Amount;
	private String t1Amount;
	
	private String d0Profit;
	private String t1Profit;
	
	// 快捷
	private String mlJfAmount;
	private String mlWjfAmount;
	private String mlJfProfit;
	private String mlWjfProfit;
	
	private String settleDate;
	private String code;
	
	private String isAdmin;
	
	public PayBillMonthDetailRpt(){
		super();
	}
	
	public PayBillMonthDetailRpt(String id){
		super(id);
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
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@ExcelField(title="交易日期", align=2, sort=10)
	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	
	@ExcelField(title="所属机构", align=2, sort=20)
	public String getOfficeName() {
		return office.getName();
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	@ExcelField(title="商户名称", align=2, sort=30)
	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	@ExcelField(title="D0交易金额", align=2, sort=40)
	public String getD0Amount() {
		return d0Amount;
	}

	public void setD0Amount(String d0Amount) {
		this.d0Amount = d0Amount;
	}
	
	@ExcelField(title="D0分润", align=2, sort=50)
	public String getD0Profit() {
		return d0Profit;
	}

	public void setD0Profit(String d0Profit) {
		this.d0Profit = d0Profit;
	}
	
	@ExcelField(title="T1交易金额", align=2, sort=60)
	public String getT1Amount() {
		return t1Amount;
	}

	public void setT1Amount(String t1Amount) {
		this.t1Amount = t1Amount;
	}
	
	@ExcelField(title="T1分润", align=2, sort=70)
	public String getT1Profit() {
		return t1Profit;
	}

	public void setT1Profit(String t1Profit) {
		this.t1Profit = t1Profit;
	}
	
	@ExcelField(title="快捷(有积分)交易金额", align=2, sort=80)
	public String getMlJfAmount() {
		return mlJfAmount;
	}

	public void setMlJfAmount(String mlJfAmount) {
		this.mlJfAmount = mlJfAmount;
	}

	@ExcelField(title="快捷(有积分)分润", align=2, sort=90)
	public String getMlJfProfit() {
		return mlJfProfit;
	}

	public void setMlJfProfit(String mlJfProfit) {
		this.mlJfProfit = mlJfProfit;
	}

	@ExcelField(title="快捷(无积分)交易金额", align=2, sort=100)
	public String getMlWjfAmount() {
		return mlWjfAmount;
	}

	public void setMlWjfAmount(String mlWjfAmount) {
		this.mlWjfAmount = mlWjfAmount;
	}

	@ExcelField(title="快捷(无积分)分润", align=2, sort=110)
	public String getMlWjfProfit() {
		return mlWjfProfit;
	}

	public void setMlWjfProfit(String mlWjfProfit) {
		this.mlWjfProfit = mlWjfProfit;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
