/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.trade.dao.TradeAnalysisDao;
import com.thinkgem.jeesite.modules.trade.entity.TradeAnalysis;
import com.thinkgem.jeesite.modules.trade.entity.TradeDetail;


@Service
@Transactional(readOnly = true)
public class TradeAnalysisService extends CrudService<TradeAnalysisDao, TradeAnalysis> {
	
	@Autowired
	private TradeAnalysisDao tradeAnalysisDao;
	
    public List<TradeAnalysis> routeMerchantAnalysis(TradeAnalysis tradeAnalysis){
    	return tradeAnalysisDao.routeMerchantAnalysis(tradeAnalysis);
    }
    
    public Page<TradeAnalysis> routeMerchantAnalysisPage(Page<TradeAnalysis> tradeAnalysisPage, TradeAnalysis tradeAnalysis) {
    	tradeAnalysis.setPage(tradeAnalysisPage);
    	tradeAnalysisPage.setList(tradeAnalysisDao.routeMerchantAnalysis(tradeAnalysis));
		return tradeAnalysisPage;
	}
	
    public List<TradeAnalysis> routeAnalysis(TradeAnalysis tradeAnalysis){
    	return tradeAnalysisDao.routeAnalysis(tradeAnalysis);
    }
	
    public List<TradeAnalysis> memberAnalysis(TradeAnalysis tradeAnalysis){
    	return tradeAnalysisDao.memberAnalysis(tradeAnalysis);
    }
    
    public Page<TradeAnalysis> memberAnalysisPage(Page<TradeAnalysis> tradeAnalysisPage, TradeAnalysis tradeAnalysis) {
    	tradeAnalysis.setPage(tradeAnalysisPage);
    	tradeAnalysisPage.setList(tradeAnalysisDao.memberAnalysis(tradeAnalysis));
		return tradeAnalysisPage;
	}
	
}