/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.trade.dao.TradeDailyTotalDao;
import com.thinkgem.jeesite.modules.trade.entity.TradeDailyTotal;


@Service
@Transactional(readOnly = true)
public class TradeDailyTotalService extends CrudService<TradeDailyTotalDao, TradeDailyTotal> {
	
	@Autowired
	private TradeDailyTotalDao tradeDailyTotalDao;
	
    public Double sumTradeMoney(Map<String,Object> paramMap){
    	return tradeDailyTotalDao.sumTradeMoney(paramMap);
    }
    
    public Double sumSettleMoney(Map<String,Object> paramMap){
    	return tradeDailyTotalDao.sumSettleMoney(paramMap);
    }
    
    public Double tradeMoney(Map<String,Object> paramMap){
    	return tradeDailyTotalDao.tradeMoney(paramMap);
    }
    
    public Double settleMoney(Map<String,Object> paramMap){
    	return tradeDailyTotalDao.settleMoney(paramMap);
    }
    
	
}