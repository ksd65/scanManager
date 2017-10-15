/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bill.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bill.entity.MsPayBill;
import com.thinkgem.jeesite.modules.bill.dao.MsPayBillDao;

/**
 * 交易对账查询Service
 * @author chenjc
 * @version 2016-12-23
 */
@Service
@Transactional(readOnly = true)
public class MsPayBillService extends CrudService<MsPayBillDao, MsPayBill> {

	public MsPayBill get(String id) {
		return super.get(id);
	}
	
	public List<MsPayBill> findList(MsPayBill msPayBill) {
		return super.findList(msPayBill);
	}
	
	public Page<MsPayBill> findPage(Page<MsPayBill> page, MsPayBill msPayBill) {
		return super.findPage(page, msPayBill);
	}
	
	@Transactional(readOnly = false)
	public void save(MsPayBill msPayBill) {
		super.save(msPayBill);
	}
	
	@Transactional(readOnly = false)
	public void delete(MsPayBill msPayBill) {
		super.delete(msPayBill);
	}
	
}