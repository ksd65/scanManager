/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bill.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bill.entity.MsWithdrawBill;

/**
 * 提现对账查询DAO接口
 * @author chenjc
 * @version 2016-12-23
 */
@MyBatisDao
public interface MsWithdrawBillDao extends CrudDao<MsWithdrawBill> {
	
}