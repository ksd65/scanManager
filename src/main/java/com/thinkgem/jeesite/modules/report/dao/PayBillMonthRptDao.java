package com.thinkgem.jeesite.modules.report.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bill.entity.MsPayBill;
import com.thinkgem.jeesite.modules.report.entity.PayBillMonthRpt;

/**
 * 
 * @author WuFengSheng
 *
 */
@MyBatisDao
public interface PayBillMonthRptDao extends CrudDao<PayBillMonthRpt> {
	
}
