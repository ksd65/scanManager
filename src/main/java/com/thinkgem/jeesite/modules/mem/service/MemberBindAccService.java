/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mem.dao.MemberBindAccDao;
import com.thinkgem.jeesite.modules.mem.entity.MemberBindAcc;

/**
 * 商户绑卡流水表Service
 * @author jjy
 * @version 2017-06-20
 */
@Service
@Transactional(readOnly = true)
public class MemberBindAccService extends CrudService<MemberBindAccDao, MemberBindAcc> {

	public MemberBindAcc get(String id) {
		return super.get(id);
	}
	
	public List<MemberBindAcc> findList(MemberBindAcc MemberBindAcc) {
		return super.findList(MemberBindAcc);
	}
	
	public Page<MemberBindAcc> findPage(Page<MemberBindAcc> page, MemberBindAcc MemberBindAcc) {
		return super.findPage(page, MemberBindAcc);
	}
	
	@Transactional(readOnly = false)
	public void save(MemberBindAcc MemberBindAcc) {
		super.save(MemberBindAcc);
	}
	
	@Transactional(readOnly = false)
	public void delete(MemberBindAcc MemberBindAcc) {
		super.delete(MemberBindAcc);
	}
	
}