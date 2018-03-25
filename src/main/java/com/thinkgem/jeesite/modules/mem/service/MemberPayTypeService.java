/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mem.dao.MemberPayTypeDao;
import com.thinkgem.jeesite.modules.mem.entity.MemberPayType;

/**
 * 商户绑卡流水表Service
 * @author jjy
 * @version 2017-06-20
 */
@Service
@Transactional(readOnly = true)
public class MemberPayTypeService extends CrudService<MemberPayTypeDao, MemberPayType> {

	public MemberPayType get(String id) {
		return super.get(id);
	}
	
	public List<MemberPayType> findList(MemberPayType memberPayType) {
		return super.findList(memberPayType);
	}
	
	public Page<MemberPayType> findPage(Page<MemberPayType> page, MemberPayType memberPayType) {
		return super.findPage(page, memberPayType);
	}
	
	@Transactional(readOnly = false)
	public void save(MemberPayType memberPayType) {
		super.save(memberPayType);
	}
	
	@Transactional(readOnly = false)
	public void delete(MemberPayType memberPayType) {
		super.delete(memberPayType);
	}
	
}