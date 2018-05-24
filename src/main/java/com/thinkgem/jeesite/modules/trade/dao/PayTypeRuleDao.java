/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.trade.entity.PayTypeRule;


@MyBatisDao
public interface PayTypeRuleDao extends CrudDao<PayTypeRule> {
	
	List<PayTypeRule> getDisTinctPayTypeRule(PayTypeRule payTypeRule);
	
	List<PayTypeRule> getRuleList(PayTypeRule payTypeRule);
	
	int deleteByType(PayTypeRule payTypeRule);
}