/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.trade.entity.TradeAnalysis;


@MyBatisDao
public interface TradeAnalysisDao extends CrudDao<TradeAnalysis> {
	
    public List<TradeAnalysis> routeMerchantAnalysis(TradeAnalysis tradeAnalysis);
    
    public List<TradeAnalysis> routeAnalysis(TradeAnalysis tradeAnalysis);
	
    public List<TradeAnalysis> memberAnalysis(TradeAnalysis tradeAnalysis);
}