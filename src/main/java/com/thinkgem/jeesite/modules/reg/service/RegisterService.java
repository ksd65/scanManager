/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.reg.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.reg.entity.Register;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.mem.entity.MemberBank;
import com.thinkgem.jeesite.modules.mem.entity.MemberAccount;
import com.thinkgem.jeesite.modules.ecode.entity.EpayCode;
import com.thinkgem.jeesite.modules.reg.dao.RegisterDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.mem.dao.MemberDao;
import com.thinkgem.jeesite.modules.ecode.dao.EpayCodeDao;

/**
 * 商户审核Service
 * @author linzw
 * @version 2016-11-21
 */
@Service
@Transactional(readOnly = true)
public class RegisterService extends CrudService<RegisterDao, Register> {
	
	@Autowired
	private RegisterDao registerDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private EpayCodeDao epayCodeDao;
	
	public Register get(String id) {
		return super.get(id);
	}
	
	public List<Register> findList(Register register) {
		return super.findList(register);
	}
	
	public Page<Register> findPage(Page<Register> page, Register register) {
		register.getSqlMap().put("dsf", dataScopeFilter(register.getCurrentUser(), "o", ""));
		return super.findPage(page, register);
	}
	
	@Transactional(readOnly = false)
	public void save(Register register) {
		super.save(register);
	}
	
	@Transactional(readOnly = false)
	public void delete(Register register) {
		super.delete(register);
	}
	
	@Transactional(readOnly = false)
	public void registerAudit(Register register, User user) {
		if(register.getStatus().equals("1")){//审核通过
			registerDao.registerAudit(register);
			Member member = new Member();
			member.setCode(register.getCode());
			member.setType("1");
			member.setLoginCode(register.getLoginCode());
			member.setLoginPass(register.getLoginPass());
			member.setEmail(register.getEmail());
			member.setMobilePhone(register.getMobilePhone());
			member.setName(register.getName());
			member.setContact(register.getContact());
			member.setStatus("0");
			member.setCertNbr(register.getCertNbr());
			member.setCertPic1(register.getCertPic1());
			member.setCertPic2(register.getCertPic2());
			member.setMemcertPic(register.getMemcertPic());
			member.setCardPic1(register.getCardPic1());
			member.setCardPic2(register.getCardPic2());
			member.setAddr(register.getAddr());
			member.setProvince(register.getProvince());
			member.setCity(register.getCity());
			member.setCounty(register.getCounty());
			member.setWxRouteId("1");
			member.setZfbRouteId("1");
			member.setPayCode(register.getPayCode());
			member.setCreateBy(user);
			member.setCreateDate(new Date());
			member.setUpdateBy(user);
			member.setUpdateDate(new Date());
			memberDao.insert(member);
			member.setId(memberDao.getMemberIdByCode(member));
			System.out.println("memberId = "+member.getId());
			
			MemberBank memberBank = new MemberBank();
			memberBank.setMemberId(member.getId());
			memberBank.setBankId(register.getBankId());
			memberBank.setProvince(register.getProvince());
			memberBank.setCity(register.getCity());
			memberBank.setSubId(register.getSubId());
			memberBank.setBankOpen(register.getBankOpen());
			memberBank.setAccountName(register.getAccountName());
			memberBank.setAccountNumber(register.getAccountNumber());
			memberBank.setCreateBy(user);
			memberBank.setCreateDate(new Date());
			memberBank.setUpdateBy(user);
			memberBank.setUpdateDate(new Date());
			memberDao.insertMemberBank(memberBank);
			
			MemberAccount memberAccount = new MemberAccount();
			memberAccount.setMemberId(member.getId());
			memberAccount.setBalance("0");
			memberAccount.setFreezeMoney("0");
			memberAccount.setCreateBy(user);
			memberAccount.setCreateDate(new Date());
			memberAccount.setUpdateBy(user);
			memberAccount.setUpdateDate(new Date());
			memberDao.insertMemberAccount(memberAccount);
			
			EpayCode epayCode = new EpayCode();
			epayCode.setStatus("5");
			epayCode.setMemberId(member.getId());
			epayCode.setPayCode(register.getPayCode());
			epayCodeDao.updateStatus(epayCode);

		}else{//审核拒绝
			registerDao.registerAudit(register);
			EpayCode epayCode = new EpayCode();
			epayCode.setStatus("6");
			epayCode.setPayCode(register.getPayCode());
			epayCodeDao.updateStatus(epayCode);
		}
		
	}
	
}