/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.BankDao;
import com.thinkgem.jeesite.modules.sys.entity.Bank;


@Service
@Transactional(readOnly = true)
public class BankService extends CrudService<BankDao, Bank> {

	public Bank get(String id) {
		return super.get(id);
	}
	
	public List<Bank> findList(Bank Bank) {
		return super.findList(Bank);
	}
	
	public Page<Bank> findPage(Page<Bank> page, Bank Bank) {
		return super.findPage(page, Bank);
	}
	
}