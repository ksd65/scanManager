package com.thinkgem.jeesite.modules.trade.entity;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.sys.entity.Office;

public class PayQrCode extends DataEntity<PayQrCode>{

	private static final long serialVersionUID = -1898425812553186711L;

	private String money;			// 交易金额
	private String qrCodeRemark;		// 备注
	private String payeeId;		// 
	private String qrCode;	// 二维码内容
	private String payType;
	private String filePath;
	private String status;
	
	private Office office;			// 机构
	private Member member;			// 商户
	
	private String memberName;		// 商户名称
	private String officeName;		// 所属机构
	
	private String beginTime;
	private String endTime;
	
	private String payAccount;			// 收款账号
	private String userName;		// 
	
	@NotNull(message="交易金额不能为空")
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
	@NotNull(message="收款备注不能为空")
	public String getQrCodeRemark() {
		return qrCodeRemark;
	}
	public void setQrCodeRemark(String qrCodeRemark) {
		this.qrCodeRemark = qrCodeRemark;
	}
	
	@NotNull(message="收款人不能为空")
	public String getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}
	
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@NotNull(message="二维码不能为空")
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
