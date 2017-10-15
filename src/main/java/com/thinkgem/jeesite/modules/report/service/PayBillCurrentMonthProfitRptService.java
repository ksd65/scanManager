package com.thinkgem.jeesite.modules.report.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.report.dao.PayBillCurrentMonthProfitRptDao;
import com.thinkgem.jeesite.modules.report.entity.PayBillCurrentMonthProfitRpt;

@Service
@Transactional(readOnly = true)
public class PayBillCurrentMonthProfitRptService extends CrudService<PayBillCurrentMonthProfitRptDao,PayBillCurrentMonthProfitRpt>{
	
	public PayBillCurrentMonthProfitRpt get(String id) {
		return super.get(id);
	}
	
	public List<PayBillCurrentMonthProfitRpt> findList(PayBillCurrentMonthProfitRpt payBillCurrentMonthProfitRpt) {
		return super.findList(payBillCurrentMonthProfitRpt);
	}
	
	public Page<PayBillCurrentMonthProfitRpt> findPage(Page<PayBillCurrentMonthProfitRpt> page, PayBillCurrentMonthProfitRpt payBillCurrentMonthProfitRpt) {
		return super.findPage(page, payBillCurrentMonthProfitRpt);
	}
	
	@Transactional(readOnly = false)
	public void save(PayBillCurrentMonthProfitRpt payBillCurrentMonthProfitRpt) {
		super.save(payBillCurrentMonthProfitRpt);
	}
	
	@Transactional(readOnly = false)
	public void delete(PayBillCurrentMonthProfitRpt payBillCurrentMonthProfitRpt) {
		super.delete(payBillCurrentMonthProfitRpt);
	}
}
