/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bill.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bill.entity.MsWithdrawBill;
import com.thinkgem.jeesite.modules.bill.dao.MsWithdrawBillDao;

/**
 * 提现对账查询Service
 * @author chenjc
 * @version 2016-12-23
 */
@Service
@Transactional(readOnly = true)
public class MsWithdrawBillService extends CrudService<MsWithdrawBillDao, MsWithdrawBill> {

	public MsWithdrawBill get(String id) {
		return super.get(id);
	}
	
	public List<MsWithdrawBill> findList(MsWithdrawBill msWithdrawBill) {
		return super.findList(msWithdrawBill);
	}
	
	public Page<MsWithdrawBill> findPage(Page<MsWithdrawBill> page, MsWithdrawBill msWithdrawBill) {
		return super.findPage(page, msWithdrawBill);
	}
	
	@Transactional(readOnly = false)
	public void save(MsWithdrawBill msWithdrawBill) {
		super.save(msWithdrawBill);
	}
	
	@Transactional(readOnly = false)
	public void delete(MsWithdrawBill msWithdrawBill) {
		super.delete(msWithdrawBill);
	}
	
}