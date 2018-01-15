/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.BankSubDao;
import com.thinkgem.jeesite.modules.sys.entity.BankSub;


@Service
@Transactional(readOnly = true)
public class BankSubService extends CrudService<BankSubDao, BankSub> {

	public BankSub get(String id) {
		return super.get(id);
	}
	
	public List<BankSub> findList(BankSub bank) {
		return super.findList(bank);
	}
	
	public Page<BankSub> findPage(Page<BankSub> page, BankSub bank) {
		return super.findPage(page, bank);
	}
	
}