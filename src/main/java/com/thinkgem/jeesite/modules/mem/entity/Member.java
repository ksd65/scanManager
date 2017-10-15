/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mem.entity;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 商户Entity
 * @author linzw
 * @version 2016-11-15
 */
public class Member extends DataEntity<Member> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 商户编号:2位（10）+8位序列（12300000起）
	private String type;		// 商户类型
	private String loginCode;		// 登陆账号
	private String loginPass;		// 登陆密码
	private Member parent;		// 上级ID
	private String email;		// 电子邮箱
	private String mobilePhone;		// 手机号码
	private String name;		// 商户名称
	private String shortName;	// 商户简称
	private String contact;		// 联系人
	private String status;		// 状态
	private String sexType;		// 姓别
	private Date   birthday;		// 出生日期
	private String homePhone;		// 固定电话
	private String certNbr;		// 身份证号
	private String certPic1;		// 身份证正面图片
	private String certPic2;		// 身份证反面图片
	private String memcertPic;		// 手持身份证照片
	private String cardNbr;		// 认证银行卡卡号
	private String cardPic1;		// 银行卡正面图片
	private String cardPic2;		// 银行卡反面图片
	private String addr;		// 地址
	private String category;		// 经营类目
	private String province;		// 省份
	private String city;		// 城市
	private String county;		// 县乡
	private String verifyFlag;		// 认证标志
	private String wxRouteId;		// 微信通道ID
	private String wxMemberCode;	// 微信商户编号
	private String wxMerchantCode;	// 通道微信商户编号
	private String zfbRouteId;		// 支付宝通道ID
	private String zfbMemberCode;	// 支付宝商户编号
	private String zfbMerchantCode;	// 通道支付宝商户编号
	private String t0DrawFee;	// t0提现手续费
	private String t0TradeRate;	// t0交易手续费扣率
	private String t1DrawFee;	// t1提现手续费
	private String t1TradeRate;	// t1交易手续费扣率
	private String payCode;		// E码付编号
	private Office office;			// 机构编号

	private String txMonth;
	private String num;
	private String beginMonth;
	private String endMonth;
	
	private MemberBank memberBank;
	
	private String qqRouteId;		// qq钱包通道ID
	private String qqMemberCode;	// qq钱包商户编号
	private String qqMerchantCode;	// 通道qq钱包商户编号
	private String wxStatus;		// 微信状态
	private String zfbStatus;		// 支付宝状态
	private String qqStatus;		// qq钱包状态
	private String agentNameLevel1;
	
	private String bdRouteId;		// 百度钱包通道ID
	private String bdMemberCode;	// 百度钱包商户编号
	private String bdMerchantCode;	// 通道百度钱包商户编号
	private String bdStatus;		// 百度钱包状态
	
	private String jdRouteId;		// 京东钱包通道ID
	private String jdMemberCode;	// 京东钱包商户编号
	private String jdMerchantCode;	// 京东百度钱包商户编号
	private String jdStatus;		// 京东钱包状态
	
	private String mlJfStatus;		// 快捷支付状态(有积分)
	private String mlJfFee;			// 快捷支付提现费(有积分)
	private String mlJfRate;		// 快捷支付交易费率(有积分)
	private String mlWjfStatus;		// 快捷支付状态(无积分)
	private String mlWjfFee;		// 快捷支付提现费(无积分)
	private String mlWjfRate;		// 快捷支付交易费率(无积分)
	
	//省市县
	private String provinceName;	//省
	private String cityName;		//市
	private String countyName;		//区县
	
	//绑定银行账户信息
	private String bindBankUsername;//持卡人
	private String bindBankName;	//发卡行
	private String bindBankNumber;	//卡号
	private String bindBankPhone;	//预留手机号
	private Date bindBankTime;		//绑定时间
	
	//绑定银行账户信息查询
	private String beginBindBankTime;	//查询绑卡开始时间
	private String endBindBankTime;		//查询绑卡结束时间

	//2017-08-08
	private String level;		//商户等级
	private String drawStatus;	// 提现状态
	private String singleLimit;// 单笔交易限额
	private String dayLimit;	// 当日交易限额
	private String busPic;		// 营业执照照片
	private String headPic;	// 门头照片
	private String deskPic;	// 收银台照片
	private String insidePic;	// 店内照片
	private String staffPic;	// 员工照片

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDrawStatus() {
		return drawStatus;
	}

	public void setDrawStatus(String drawStatus) {
		this.drawStatus = drawStatus;
	}

	public String getSingleLimit() {
		return singleLimit;
	}

	public void setSingleLimit(String singleLimit) {
		this.singleLimit = singleLimit;
	}

	public String getDayLimit() {
		return dayLimit;
	}

	public void setDayLimit(String dayLimit) {
		this.dayLimit = dayLimit;
	}

	public String getBusPic() {
		return busPic;
	}

	public void setBusPic(String busPic) {
		this.busPic = busPic;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getDeskPic() {
		return deskPic;
	}

	public void setDeskPic(String deskPic) {
		this.deskPic = deskPic;
	}

	public String getInsidePic() {
		return insidePic;
	}

	public void setInsidePic(String insidePic) {
		this.insidePic = insidePic;
	}

	public String getStaffPic() {
		return staffPic;
	}

	public void setStaffPic(String staffPic) {
		this.staffPic = staffPic;
	}

	public String getBeginBindBankTime() {
		return beginBindBankTime;
	}

	public void setBeginBindBankTime(String beginBindBankTime) {
		this.beginBindBankTime = beginBindBankTime;
	}

	public String getEndBindBankTime() {
		return endBindBankTime;
	}

	public void setEndBindBankTime(String endBindBankTime) {
		this.endBindBankTime = endBindBankTime;
	}

	public String getBindBankUsername() {
		return bindBankUsername;
	}

	public void setBindBankUsername(String bindBankUsername) {
		this.bindBankUsername = bindBankUsername;
	}

	public String getBindBankName() {
		return bindBankName;
	}

	public void setBindBankName(String bindBankName) {
		this.bindBankName = bindBankName;
	}

	public String getBindBankNumber() {
		return bindBankNumber;
	}

	public void setBindBankNumber(String bindBankNumber) {
		this.bindBankNumber = bindBankNumber;
	}

	public String getBindBankPhone() {
		return bindBankPhone;
	}

	public void setBindBankPhone(String bindBankPhone) {
		this.bindBankPhone = bindBankPhone;
	}

	public Date getBindBankTime() {
		return bindBankTime;
	}

	public void setBindBankTime(Date bindBankTime) {
		this.bindBankTime = bindBankTime;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Member() {
		super();
	}

	public Member(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商户编号:2位（10）+8位序列（12300000起）长度必须介于 1 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=2, message="商户类型长度必须介于 1 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=32, message="登陆账号长度必须介于 0 和 32 之间")
	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	
	@Length(min=0, max=32, message="登陆密码长度必须介于 0 和 32 之间")
	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}
	
	@JsonBackReference
	public Member getParent() {
		return parent;
	}

	public void setParent(Member parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=128, message="电子邮箱长度必须介于 0 和 128 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=32, message="手机号码长度必须介于 0 和 32 之间")
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@Length(min=0, max=40, message="商户名称长度必须介于 0 和 40 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="商户简称长度必须介于 0 和 64 之间")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	@Length(min=0, max=64, message="联系人长度必须介于 0 和 64 之间")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=2, message="姓别长度必须介于 0 和 2 之间")
	public String getSexType() {
		return sexType;
	}

	public void setSexType(String sexType) {
		this.sexType = sexType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=40, message="固定电话长度必须介于 0 和 40 之间")
	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	
	@Length(min=0, max=40, message="身份证号长度必须介于 0 和 40 之间")
	public String getCertNbr() {
		return certNbr;
	}

	public void setCertNbr(String certNbr) {
		this.certNbr = certNbr;
	}
	
	@Length(min=0, max=60, message="身份证正面图片长度必须介于 0 和 60 之间")
	public String getCertPic1() {
		return certPic1;
	}

	public void setCertPic1(String certPic1) {
		this.certPic1 = certPic1;
	}
	
	@Length(min=0, max=60, message="身份证反面图片长度必须介于 0 和 60 之间")
	public String getCertPic2() {
		return certPic2;
	}

	public void setCertPic2(String certPic2) {
		this.certPic2 = certPic2;
	}
	
	@Length(min=0, max=60, message="手持身份证照片长度必须介于 0 和 60 之间")
	public String getMemcertPic() {
		return memcertPic;
	}

	public void setMemcertPic(String memcertPic) {
		this.memcertPic = memcertPic;
	}
	
	@Length(min=0, max=30, message="认证银行卡卡号长度必须介于 0 和 30 之间")
	public String getCardNbr() {
		return cardNbr;
	}

	public void setCardNbr(String cardNbr) {
		this.cardNbr = cardNbr;
	}
	
	@Length(min=0, max=60, message="银行卡正面图片长度必须介于 0 和 60 之间")
	public String getCardPic1() {
		return cardPic1;
	}

	public void setCardPic1(String cardPic1) {
		this.cardPic1 = cardPic1;
	}
	
	@Length(min=0, max=60, message="银行卡反面图片长度必须介于 0 和 60 之间")
	public String getCardPic2() {
		return cardPic2;
	}

	public void setCardPic2(String cardPic2) {
		this.cardPic2 = cardPic2;
	}
	
	@Length(min=0, max=256, message="地址长度必须介于 0 和 256 之间")
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Length(min=0, max=128, message="经营类目长度必须介于 0 和 128 之间")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Length(min=0, max=11, message="省份长度必须介于 0 和 11 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=11, message="城市长度必须介于 0 和 11 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=11, message="县乡长度必须介于 0 和 11 之间")
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	
	@Length(min=0, max=1, message="认证标志长度必须介于 0 和 1 之间")
	public String getVerifyFlag() {
		return verifyFlag;
	}

	public void setVerifyFlag(String verifyFlag) {
		this.verifyFlag = verifyFlag;
	}
	
	@Length(min=1, max=5, message="微信通道ID长度必须介于 1 和 5 之间")
	public String getWxRouteId() {
		return wxRouteId;
	}

	public void setWxRouteId(String wxRouteId) {
		this.wxRouteId = wxRouteId;
	}
	
	@Length(min=0, max=32, message="微信商户编号长度必须介于 0 和 32 之间")
	public String getWxMemberCode() {
		return wxMemberCode;
	}

	public void setWxMemberCode(String wxMemberCode) {
		this.wxMemberCode = wxMemberCode;
	}
	
	@Length(min=0, max=32, message="通道微信商户编号长度必须介于 0 和 32 之间")
	public String getWxMerchantCode() {
		return wxMerchantCode;
	}

	public void setWxMerchantCode(String wxMerchantCode) {
		this.wxMerchantCode = wxMerchantCode;
	}
	
	@Length(min=1, max=5, message="支付宝通道ID长度必须介于 1 和 5 之间")
	public String getZfbRouteId() {
		return zfbRouteId;
	}

	public void setZfbRouteId(String zfbRouteId) {
		this.zfbRouteId = zfbRouteId;
	}
	
	@Length(min=0, max=32, message="微信商户编号长度必须介于 0 和 32 之间")
	public String getZfbMemberCode() {
		return zfbMemberCode;
	}

	public void setZfbMemberCode(String zfbMemberCode) {
		this.zfbMemberCode = zfbMemberCode;
	}
	
	@Length(min=0, max=32, message="通道支付宝商户编号长度必须介于 0 和 32 之间")
	public String getZfbMerchantCode() {
		return zfbMerchantCode;
	}

	public void setZfbMerchantCode(String zfbMerchantCode) {
		this.zfbMerchantCode = zfbMerchantCode;
	}
	
	@Length(min=0, max=12, message="t0提现手续费长度必须介于 0 和 12 之间")
	public String getT0DrawFee() {
		return t0DrawFee;
	}

	public void setT0DrawFee(String t0DrawFee) {
		this.t0DrawFee = t0DrawFee;
	}
	
	@Length(min=0, max=12, message="t0交易手续费扣率长度必须介于 0 和 12 之间")
	public String getT0TradeRate() {
		return t0TradeRate;
	}

	public void setT0TradeRate(String t0TradeRate) {
		this.t0TradeRate = t0TradeRate;
	}
	
	@Length(min=0, max=12, message="t1提现手续费长度必须介于 0 和 12 之间")
	public String getT1DrawFee() {
		return t1DrawFee;
	}

	public void setT1DrawFee(String t1DrawFee) {
		this.t1DrawFee = t1DrawFee;
	}
	
	@Length(min=0, max=12, message="t1交易手续费扣率长度必须介于 0 和 12 之间")
	public String getT1TradeRate() {
		return t1TradeRate;
	}

	public void setT1TradeRate(String t1TradeRate) {
		this.t1TradeRate = t1TradeRate;
	}
	
	@Length(min=0, max=64, message="E码付编号长度必须介于 0 和 64 之间")
	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	
	public MemberBank getMemberBank() {
		return memberBank;
	}

	public void setMemberBank(MemberBank memberBank) {
		this.memberBank = memberBank;
	}
	
	@NotNull(message="机构编号不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getTxMonth() {
		return txMonth;
	}

	public void setTxMonth(String txMonth) {
		this.txMonth = txMonth;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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
	
	@Length(min=1, max=5, message="QQ钱包通道ID长度必须介于 1 和 5 之间")
	public String getQqRouteId() {
		return qqRouteId;
	}

	public void setQqRouteId(String qqRouteId) {
		this.qqRouteId = qqRouteId;
	}
	
	@Length(min=0, max=32, message="QQ钱包商户编号长度必须介于 0 和 32 之间")
	public String getQqMemberCode() {
		return qqMemberCode;
	}

	public void setQqMemberCode(String qqMemberCode) {
		this.qqMemberCode = qqMemberCode;
	}
	
	@Length(min=0, max=32, message="通道QQ钱包商户编号长度必须介于 0 和 32 之间")
	public String getQqMerchantCode() {
		return qqMerchantCode;
	}

	public void setQqMerchantCode(String qqMerchantCode) {
		this.qqMerchantCode = qqMerchantCode;
	}

	public String getWxStatus() {
		return wxStatus;
	}

	public void setWxStatus(String wxStatus) {
		this.wxStatus = wxStatus;
	}

	public String getZfbStatus() {
		return zfbStatus;
	}

	public void setZfbStatus(String zfbStatus) {
		this.zfbStatus = zfbStatus;
	}

	public String getQqStatus() {
		return qqStatus;
	}

	public void setQqStatus(String qqStatus) {
		this.qqStatus = qqStatus;
	}

	public String getAgentNameLevel1() {
		return agentNameLevel1;
	}

	public void setAgentNameLevel1(String agentNameLevel1) {
		this.agentNameLevel1 = agentNameLevel1;
	}
	
	@Length(min=1, max=5, message="百度钱包通道ID长度必须介于 1 和 5 之间")
	public String getBdRouteId() {
		return bdRouteId;
	}

	public void setBdRouteId(String bdRouteId) {
		this.bdRouteId = bdRouteId;
	}
	
	@Length(min=0, max=32, message="百度钱包商户编号长度必须介于 0 和 32 之间")
	public String getBdMemberCode() {
		return bdMemberCode;
	}

	public void setBdMemberCode(String bdMemberCode) {
		this.bdMemberCode = bdMemberCode;
	}
	
	@Length(min=0, max=32, message="通道百度钱包商户编号长度必须介于 0 和 32 之间")
	public String getBdMerchantCode() {
		return bdMerchantCode;
	}

	public void setBdMerchantCode(String bdMerchantCode) {
		this.bdMerchantCode = bdMerchantCode;
	}
	
	public String getBdStatus() {
		return bdStatus;
	}

	public void setBdStatus(String bdStatus) {
		this.bdStatus = bdStatus;
	}
	
	@Length(min=1, max=5, message="京东钱包通道ID长度必须介于 1 和 5 之间")
	public String getJdRouteId() {
		return jdRouteId;
	}

	public void setJdRouteId(String jdRouteId) {
		this.jdRouteId = jdRouteId;
	}
	
	@Length(min=0, max=32, message="京东钱包商户编号长度必须介于 0 和 32 之间")
	public String getJdMemberCode() {
		return jdMemberCode;
	}

	public void setJdMemberCode(String jdMemberCode) {
		this.jdMemberCode = jdMemberCode;
	}
	
	@Length(min=0, max=32, message="通道京东钱包商户编号长度必须介于 0 和 32 之间")
	public String getJdMerchantCode() {
		return jdMerchantCode;
	}

	public void setJdMerchantCode(String jdMerchantCode) {
		this.jdMerchantCode = jdMerchantCode;
	}
	
	public String getJdStatus() {
		return jdStatus;
	}

	public void setJdStatus(String jdStatus) {
		this.jdStatus = jdStatus;
	}
	
	public String getMlJfStatus() {
		return mlJfStatus;
	}

	public void setMlJfStatus(String mlJfStatus) {
		this.mlJfStatus = mlJfStatus;
	}
	
	public String getMlJfFee() {
		return mlJfFee;
	}

	public void setMlJfFee(String mlJfFee) {
		this.mlJfFee = mlJfFee;
	}
	
	public String getMlJfRate() {
		return mlJfRate;
	}

	public void setMlJfRate(String mlJfRate) {
		this.mlJfRate = mlJfRate;
	}
	
	public String getMlWjfStatus() {
		return mlWjfStatus;
	}

	public void setMlWjfStatus(String mlWjfStatus) {
		this.mlWjfStatus = mlWjfStatus;
	}
	
	public String getMlWjfFee() {
		return mlWjfFee;
	}

	public void setMlWjfFee(String mlWjfFee) {
		this.mlWjfFee = mlWjfFee;
	}
	
	public String getMlWjfRate() {
		return mlWjfRate;
	}

	public void setMlWjfRate(String mlWjfRate) {
		this.mlWjfRate = mlWjfRate;
	}
	
}