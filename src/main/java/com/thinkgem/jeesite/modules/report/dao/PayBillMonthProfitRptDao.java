package com.thinkgem.jeesite.modules.report.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.report.entity.PayBillMonthDetailRpt;
import com.thinkgem.jeesite.modules.report.entity.PayBillMonthProfitRpt;

/**
 * 
 * @author WuFengSheng
 *
 */
@MyBatisDao
public interface PayBillMonthProfitRptDao extends CrudDao<PayBillMonthProfitRpt> {
	
	public List<PayBillMonthDetailRpt> findProfitDetailList(PayBillMonthDetailRpt payBillMonthDetailRpt);
	
}
