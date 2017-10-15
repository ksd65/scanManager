/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mem.entity.MemberBindAccDtl;
import com.thinkgem.jeesite.modules.mem.dao.MemberBindAccDtlDao;

/**
 * 商户绑卡流水表Service
 * @author jjy
 * @version 2017-06-20
 */
@Service
@Transactional(readOnly = true)
public class MemberBindAccDtlService extends CrudService<MemberBindAccDtlDao, MemberBindAccDtl> {

	public MemberBindAccDtl get(String id) {
		return super.get(id);
	}
	
	public List<MemberBindAccDtl> findList(MemberBindAccDtl memberBindAccDtl) {
		return super.findList(memberBindAccDtl);
	}
	
	public Page<MemberBindAccDtl> findPage(Page<MemberBindAccDtl> page, MemberBindAccDtl memberBindAccDtl) {
		return super.findPage(page, memberBindAccDtl);
	}
	
	@Transactional(readOnly = false)
	public void save(MemberBindAccDtl memberBindAccDtl) {
		super.save(memberBindAccDtl);
	}
	
	@Transactional(readOnly = false)
	public void delete(MemberBindAccDtl memberBindAccDtl) {
		super.delete(memberBindAccDtl);
	}
	
}