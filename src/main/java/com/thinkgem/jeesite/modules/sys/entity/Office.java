/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 机构Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
public class Office extends TreeEntity<Office> {

	private static final long serialVersionUID = 1L;
//	private Office parent;	// 父级编号
//	private String parentIds; // 所有父级编号
	private Area area;		// 归属区域
	private String code; 	// 机构编码
//	private String name; 	// 机构名称
//	private Integer sort;		// 排序
	private String type; 	// 机构类型（1：公司；2：部门；3：小组）
	private String agtType; 	// 代理商类型（0：非代理 1：普通；2：OEM；3：机构合作）
	private String agtGrade; 	// 代理商类型（1：一级；2：二级；3：三级；4：四级）
	private String grade; 	// 机构等级（1：一级；2：二级；3：三级；4：四级）
	private String address; // 联系地址
	private String zipCode; // 邮政编码
	private String master; 	// 负责人
	private String phone; 	// 电话
	private String fax; 	// 传真
	private String email; 	// 邮箱
	private String useable;//是否可用
	private User primaryPerson;//主负责人
	private User deputyPerson;//副负责人
	private List<String> childDeptList;//快速添加子部门
	
	private String t0DrawFee;		// T0单笔提现手续费
	private String t0TradeRate;		// T0交易手续费扣率
	private String t1DrawFee;		// T1单笔提现手续费
	private String t1TradeRate;		// T1交易手续费扣率
	
	// 快捷
	private String bonusQuickRate;	// 有积分
	private String bonusQuickFee; 	// 有积分
	private String quickRate;		// 无积分
	private String quickFee; 		// 无积分
	
	private String qrcodePath;		// 二维码图片地址
	
	private String privateKeyRsa;	
	private String publicKeyRsa;	
	
	public Office(){
		super();
//		this.sort = 30;
		this.type = "2";
	}

	public Office(String id){
		super(id);
	}
	
	public List<String> getChildDeptList() {
		return childDeptList;
	}

	public void setChildDeptList(List<String> childDeptList) {
		this.childDeptList = childDeptList;
	}

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public User getPrimaryPerson() {
		return primaryPerson;
	}

	public void setPrimaryPerson(User primaryPerson) {
		this.primaryPerson = primaryPerson;
	}

	public User getDeputyPerson() {
		return deputyPerson;
	}

	public void setDeputyPerson(User deputyPerson) {
		this.deputyPerson = deputyPerson;
	}

//	@JsonBackReference
//	@NotNull
	public Office getParent() {
		return parent;
	}

	public void setParent(Office parent) {
		this.parent = parent;
	}
//
//	@Length(min=1, max=2000)
//	public String getParentIds() {
//		return parentIds;
//	}
//
//	public void setParentIds(String parentIds) {
//		this.parentIds = parentIds;
//	}

	@NotNull
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
//
//	@Length(min=1, max=100)
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Integer getSort() {
//		return sort;
//	}
//
//	public void setSort(Integer sort) {
//		this.sort = sort;
//	}
	
	@Length(min=1, max=1)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=1)
	public String getAgtType() {
		return agtType;
	}

	public void setAgtType(String agtType) {
		this.agtType = agtType;
	}
	
	@Length(min=1, max=1)
	public String getAgtGrade() {
		return agtGrade;
	}

	public void setAgtGrade(String agtGrade) {
		this.agtGrade = agtGrade;
	}
	
	@Length(min=1, max=1)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Length(min=0, max=255)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Length(min=0, max=100)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Length(min=0, max=100)
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	@Length(min=0, max=200)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Length(min=0, max=200)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Length(min=0, max=200)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Length(min=0, max=100)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

//	public String getParentId() {
//		return parent != null && parent.getId() != null ? parent.getId() : "0";
//	}
	
	@Override
	public String toString() {
		return name;
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

	public String getQrcodePath() {
		return qrcodePath;
	}

	public void setQrcodePath(String qrcodePath) {
		this.qrcodePath = qrcodePath;
	}

	public String getPrivateKeyRsa() {
		return privateKeyRsa;
	}

	public void setPrivateKeyRsa(String privateKeyRsa) {
		this.privateKeyRsa = privateKeyRsa;
	}

	public String getPublicKeyRsa() {
		return publicKeyRsa;
	}

	public void setPublicKeyRsa(String publicKeyRsa) {
		this.publicKeyRsa = publicKeyRsa;
	}
	
	
}