package com.thinkgem.jeesite.modules.report.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.report.dao.PayBillMonthRptDao;
import com.thinkgem.jeesite.modules.report.entity.PayBillMonthRpt;

@Service
@Transactional(readOnly = true)
public class PayBillMonthRptService extends CrudService<PayBillMonthRptDao,PayBillMonthRpt>{
	
	public PayBillMonthRpt get(String id) {
		return super.get(id);
	}
	
	public List<PayBillMonthRpt> findList(PayBillMonthRpt payBillMonthRpt) {
		return super.findList(payBillMonthRpt);
	}
	
	public Page<PayBillMonthRpt> findPage(Page<PayBillMonthRpt> page, PayBillMonthRpt payBillMonthRpt) {
		return super.findPage(page, payBillMonthRpt);
	}
	
	@Transactional(readOnly = false)
	public void save(PayBillMonthRpt payBillMonthRpt) {
		super.save(payBillMonthRpt);
	}
	
	@Transactional(readOnly = false)
	public void delete(PayBillMonthRpt payBillMonthRpt) {
		super.delete(payBillMonthRpt);
	}
}
