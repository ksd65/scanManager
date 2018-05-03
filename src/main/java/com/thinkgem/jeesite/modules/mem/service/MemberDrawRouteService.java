/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mem.dao.MemberDrawRouteDao;
import com.thinkgem.jeesite.modules.mem.entity.MemberDrawRoute;

/**
 * 商户绑卡流水表Service
 * @author jjy
 * @version 2017-06-20
 */
@Service
@Transactional(readOnly = true)
public class MemberDrawRouteService extends CrudService<MemberDrawRouteDao, MemberDrawRoute> {

	public MemberDrawRoute get(String id) {
		return super.get(id);
	}
	
	public List<MemberDrawRoute> findList(MemberDrawRoute memberDrawRoute) {
		return super.findList(memberDrawRoute);
	}
	
	public Page<MemberDrawRoute> findPage(Page<MemberDrawRoute> page, MemberDrawRoute memberDrawRoute) {
		return super.findPage(page, memberDrawRoute);
	}
	
	@Transactional(readOnly = false)
	public void save(MemberDrawRoute memberDrawRoute) {
		super.save(memberDrawRoute);
	}
	
	@Transactional(readOnly = false)
	public void delete(MemberDrawRoute memberDrawRoute) {
		super.delete(memberDrawRoute);
	}
	
}