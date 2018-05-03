/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.dao;

import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.trade.entity.TradeDailyTotal;


@MyBatisDao
public interface TradeDailyTotalDao extends CrudDao<TradeDailyTotal> {
	
	public Double sumTradeMoney(Map<String,Object> paramMap);
	
	public Double sumSettleMoney(Map<String,Object> paramMap);

	public Double tradeMoney(Map<String,Object> paramMap);

	public Double settleMoney(Map<String,Object> paramMap);

}