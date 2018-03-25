/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mem.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.mem.entity.MemberBank;
import com.thinkgem.jeesite.modules.mem.entity.MemberAccount;

/**
 * 商户DAO接口
 * @author linzw
 * @version 2016-11-15
 */
@MyBatisDao
public interface MemberDao extends CrudDao<Member> {
	public int insert(Member member);
	public int insertMemberBank(MemberBank memberBank);
	public int insertMemberAccount(MemberAccount memberAccount);
	public String getMemberIdByCode(Member member);
	
	public List<Member> findMonthMemberNum(Member member);
	public List<Member> findBankInfoList(Member member);
	public List<Member> findListByOfficeId(Member member);
	public void disable(Member member);
	public void enable(Member member);
}