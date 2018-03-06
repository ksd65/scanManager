/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.trade.dao.PayeeDao;
import com.thinkgem.jeesite.modules.trade.entity.Payee;


@Service
@Transactional(readOnly = true)
public class PayeeService extends CrudService<PayeeDao, Payee> {

	public Payee get(String id) {
		return super.get(id);
	}
	
	public Payee getByAccount(Payee payee){
		return dao.getByAccount(payee);
	}
	
	public List<Payee> findList(Payee payee) {
		return super.findList(payee);
	}
	
	public Page<Payee> findPage(Page<Payee> page, Payee payee) {
		return super.findPage(page, payee);
	}
	
	@Transactional(readOnly = false)
	public void save(Payee payee) {
		super.save(payee);
	}
	
	@Transactional(readOnly = false)
	public void delete(Payee payee) {
		super.delete(payee);
	}
	
	@Transactional(readOnly = false)
	public int update(Payee payee) {
		return dao.update(payee);
	}
	
}