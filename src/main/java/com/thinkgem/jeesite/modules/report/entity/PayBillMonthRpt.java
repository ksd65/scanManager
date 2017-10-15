package com.thinkgem.jeesite.modules.report.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 
 * @author WuFengSheng
 *
 */
public class PayBillMonthRpt extends DataEntity<PayBillMonthRpt>{
	
	private static final long serialVersionUID = 1L;
	
	private String officeId;
	private String officeName;
	private Office office;
	private String month;
	private String d0Count;
	private String d0Amount;
	private String t1Count;
	private String t1Amount;
	private String totalCount;
	private String totalAmount;
	// 快捷交易
	private String mlJfCount;
	private String mlJfAmount;
	private String mlWjfCount;
	private String mlWjfAmount;
	
	private String isAdmin;
	
	public PayBillMonthRpt(){
		super();
	}
	
	public PayBillMonthRpt(String id){
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
	
	@ExcelField(title="年月", align=2, sort=10)
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@ExcelField(title="所属机构", align=2, sort=20)
	public String getOfficeName() {
		return office.getName();
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	@ExcelField(title="D0交易笔数", align=2, sort=30)
	public String getD0Count() {
		return d0Count;
	}

	public void setD0Count(String d0Count) {
		this.d0Count = d0Count;
	}

	@ExcelField(title="D0交易额", align=2, sort=40)
	public String getD0Amount() {
		return d0Amount;
	}

	public void setD0Amount(String d0Amount) {
		this.d0Amount = d0Amount;
	}

	@ExcelField(title="T+1交易笔数", align=2, sort=50)
	public String getT1Count() {
		return t1Count;
	}

	public void setT1Count(String t1Count) {
		this.t1Count = t1Count;
	}
	
	@ExcelField(title="T+1交易额", align=2, sort=60)
	public String getT1Amount() {
		return t1Amount;
	}

	public void setT1Amount(String t1Amount) {
		this.t1Amount = t1Amount;
	}

	@ExcelField(title="快捷(有积分)总笔数", align=2, sort=70)
	public String getMlJfCount() {
		return mlJfCount;
	}

	public void setMlJfCount(String mlJfCount) {
		this.mlJfCount = mlJfCount;
	}

	@ExcelField(title="快捷(有积分)交易额", align=2, sort=80)
	public String getMlJfAmount() {
		return mlJfAmount;
	}

	public void setMlJfAmount(String mlJfAmount) {
		this.mlJfAmount = mlJfAmount;
	}

	@ExcelField(title="快捷(无积分)总笔数", align=2, sort=90)
	public String getMlWjfCount() {
		return mlWjfCount;
	}

	public void setMlWjfCount(String mlWjfCount) {
		this.mlWjfCount = mlWjfCount;
	}

	@ExcelField(title="快捷(无积分)交易额", align=2, sort=100)
	public String getMlWjfAmount() {
		return mlWjfAmount;
	}

	public void setMlWjfAmount(String mlWjfAmount) {
		this.mlWjfAmount = mlWjfAmount;
	}
	
	@ExcelField(title="交易总笔数", align=2, sort=110)
	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	@ExcelField(title="交易总额", align=2, sort=120)
	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
}
