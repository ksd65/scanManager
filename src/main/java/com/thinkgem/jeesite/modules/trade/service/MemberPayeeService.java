/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.trade.dao.MemberPayeeDao;
import com.thinkgem.jeesite.modules.trade.entity.MemberPayee;


@Service
@Transactional(readOnly = true)
public class MemberPayeeService extends CrudService<MemberPayeeDao, MemberPayee> {

	public MemberPayee get(String id) {
		return super.get(id);
	}
	
	public List<MemberPayee> findList(MemberPayee memberPayee) {
		return super.findList(memberPayee);
	}
	
	public Page<MemberPayee> findPage(Page<MemberPayee> page, MemberPayee memberPayee) {
		return super.findPage(page, memberPayee);
	}
	
	@Transactional(readOnly = false)
	public void save(MemberPayee memberPayee) {
		super.save(memberPayee);
	}
	
	@Transactional(readOnly = false)
	public void delete(MemberPayee memberPayee) {
		super.delete(memberPayee);
	}
	
	@Transactional(readOnly = false)
	public int deleteByPayeeId(MemberPayee memberPayee){
		return dao.deleteByPayeeId(memberPayee);
	}
	
}