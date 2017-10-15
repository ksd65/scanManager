package com.thinkgem.jeesite.modules.report.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.report.dao.PayBillMonthMyProfitRptDao;
import com.thinkgem.jeesite.modules.report.entity.PayBillMonthMyProfitRpt;

@Service
@Transactional(readOnly = true)
public class PayBillMonthMyProfitRptService extends CrudService<PayBillMonthMyProfitRptDao,PayBillMonthMyProfitRpt>{
	
	public PayBillMonthMyProfitRpt get(String id) {
		return super.get(id);
	}
	
	public List<PayBillMonthMyProfitRpt> findList(PayBillMonthMyProfitRpt payBillMonthMyProfitRpt) {
		return super.findList(payBillMonthMyProfitRpt);
	}
	
	public Page<PayBillMonthMyProfitRpt> findPage(Page<PayBillMonthMyProfitRpt> page, PayBillMonthMyProfitRpt payBillMonthMyProfitRpt) {
		return super.findPage(page, payBillMonthMyProfitRpt);
	}
	
	@Transactional(readOnly = false)
	public void save(PayBillMonthMyProfitRpt payBillMonthMyProfitRpt) {
		super.save(payBillMonthMyProfitRpt);
	}
	
	@Transactional(readOnly = false)
	public void delete(PayBillMonthMyProfitRpt payBillMonthMyProfitRpt) {
		super.delete(payBillMonthMyProfitRpt);
	}
}
