/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mem.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.mem.dao.MemberDao;

/**
 * 商户Service
 * @author linzw
 * @version 2016-11-15
 */
@Service
@Transactional(readOnly = true)
public class MemberService extends CrudService<MemberDao, Member> {

	public Member get(String id) {
		return super.get(id);
	}
	
	public List<Member> findList(Member member) {
		return super.findList(member);
	}
	
	public Page<Member> findPage(Page<Member> page, Member member) {
		member.getSqlMap().put("dsf", dataScopeFilter(member.getCurrentUser(), "o", ""));
		return super.findPage(page, member);
	}
	
	@Transactional(readOnly = false)
	public void save(Member member) {
		super.save(member);
	}
	
	@Transactional(readOnly = false)
	public void delete(Member member) {
		super.delete(member);
	}
	
	public List<Member> findMonthMemberNum(Member member){
		return dao.findMonthMemberNum(member);
	}

	public Page<Member> findBankPage(Page<Member> page, Member member) {
		member.setPage(page);
		page.setList(dao.findBankInfoList(member));
		return page;
	}
	
	public List<Member> findListByOfficeId(Member member) {
		return dao.findListByOfficeId(member);
	}

	//禁用
	@Transactional(readOnly = false)
	public void disable(Member member) {
		dao.disable(member);
	}
	
	@Transactional(readOnly = false)
	public void enable(Member member) {
		dao.enable(member);
	}

	@Transactional(readOnly = false)
	public void updateCert(Member member) {
		Member oldMember = get(member.getId());
		oldMember.setLevel(member.getLevel());
		oldMember.setCertPic1(member.getCertPic1());
		oldMember.setCertPic2(member.getCertPic2());
		oldMember.setBusPic(member.getBusPic());
		oldMember.setDeskPic(member.getDeskPic());
		oldMember.setInsidePic(member.getInsidePic());
		oldMember.setCardPic1(member.getCardPic1());
		oldMember.setCardPic2(member.getCardPic2());
		oldMember.setMemcertPic(member.getMemcertPic());
		oldMember.setMemberBank(member.getMemberBank());
		oldMember.setStaffPic(member.getStaffPic());
		save(oldMember);
	}
}