package com.thinkgem.jeesite.modules.report.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.report.dao.PayBillMonthProfitRptDao;
import com.thinkgem.jeesite.modules.report.entity.PayBillMonthDetailRpt;
import com.thinkgem.jeesite.modules.report.entity.PayBillMonthProfitRpt;

@Service
@Transactional(readOnly = true)
public class PayBillMonthProfitRptService extends CrudService<PayBillMonthProfitRptDao,PayBillMonthProfitRpt>{
	
	public PayBillMonthProfitRpt get(String id) {
		return super.get(id);
	}
	
	public List<PayBillMonthProfitRpt> findList(PayBillMonthProfitRpt payBillMonthProfitRpt) {
		return super.findList(payBillMonthProfitRpt);
	}
	
	public Page<PayBillMonthProfitRpt> findPage(Page<PayBillMonthProfitRpt> page, PayBillMonthProfitRpt payBillMonthProfitRpt) {
		return super.findPage(page, payBillMonthProfitRpt);
	}
	
	@Transactional(readOnly = false)
	public void save(PayBillMonthProfitRpt payBillMonthProfitRpt) {
		super.save(payBillMonthProfitRpt);
	}
	
	@Transactional(readOnly = false)
	public void delete(PayBillMonthProfitRpt payBillMonthProfitRpt) {
		super.delete(payBillMonthProfitRpt);
	}
	
	/**
	 * 
	 * @param payBillMonthDetailRpt
	 * @return
	 */
	public List<PayBillMonthDetailRpt> findProfitDetailList(PayBillMonthDetailRpt payBillMonthDetailRpt){
		return dao.findProfitDetailList(payBillMonthDetailRpt);
	}
	
	/**
	 * 
	 * @param page
	 * @param payBillMonthProfitRpt
	 * @return
	 */
	public Page<PayBillMonthDetailRpt> findProfitDetailPage(Page<PayBillMonthDetailRpt> page,PayBillMonthDetailRpt payBillMonthDetailRpt){
		payBillMonthDetailRpt.setPage(page);
		page.setList(dao.findProfitDetailList(payBillMonthDetailRpt));
		return page;
	}
}
