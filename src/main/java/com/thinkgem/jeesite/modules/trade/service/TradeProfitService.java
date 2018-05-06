/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.trade.dao.TradeProfitDao;
import com.thinkgem.jeesite.modules.trade.entity.RoutewayDrawProfit;
import com.thinkgem.jeesite.modules.trade.entity.TradeProfit;


@Service
@Transactional(readOnly = true)
public class TradeProfitService extends CrudService<TradeProfitDao, TradeProfit> {
	
	@Autowired
	private TradeProfitDao tradeProfitDao;
	
    
	
    public List<TradeProfit> platProfit(TradeProfit tradeProfit){
    	return tradeProfitDao.platProfit(tradeProfit);
    }
    
    public Page<TradeProfit> platProfitPage(Page<TradeProfit> platProfitPage, TradeProfit tradeProfit) {
    	tradeProfit.setPage(platProfitPage);
    	platProfitPage.setList(tradeProfitDao.platProfit(tradeProfit));
		return platProfitPage;
	}
    
    public Map<String,Object> platProfitSum(TradeProfit tradeProfit){
    	return tradeProfitDao.platProfitSum(tradeProfit);
    }
    
    public List<TradeProfit> agentMemberProfit(TradeProfit tradeProfit){
    	return tradeProfitDao.agentMemberProfit(tradeProfit);
    }
    
    public Page<TradeProfit> agentMemberProfitPage(Page<TradeProfit> agentMemberProfitPage, TradeProfit tradeProfit) {
    	tradeProfit.setPage(agentMemberProfitPage);
    	agentMemberProfitPage.setList(tradeProfitDao.agentMemberProfit(tradeProfit));
		return agentMemberProfitPage;
	}
    
    public Map<String,Object> agentProfitSum(TradeProfit tradeProfit){
    	return tradeProfitDao.agentProfitSum(tradeProfit);
    }
    
    public List<TradeProfit> agentProfit(TradeProfit tradeProfit){
    	return tradeProfitDao.agentProfit(tradeProfit);
    }
    
    public Page<TradeProfit> agentProfitPage(Page<TradeProfit> agentProfitPage, TradeProfit tradeProfit) {
    	tradeProfit.setPage(agentProfitPage);
    	agentProfitPage.setList(tradeProfitDao.agentProfit(tradeProfit));
		return agentProfitPage;
	}
    
    public List<RoutewayDrawProfit> platDrawProfit(RoutewayDrawProfit routewayDrawProfit){
    	return tradeProfitDao.platDrawProfit(routewayDrawProfit);
    }
    
    public Page<RoutewayDrawProfit> platDrawProfitPage(Page<RoutewayDrawProfit> platDrawProfitPage, RoutewayDrawProfit routewayDrawProfit) {
    	routewayDrawProfit.setPage(platDrawProfitPage);
    	platDrawProfitPage.setList(tradeProfitDao.platDrawProfit(routewayDrawProfit));
		return platDrawProfitPage;
	}
    
    public Map<String,Object> platDrawProfitSum(RoutewayDrawProfit routewayDrawProfit){
    	return tradeProfitDao.platDrawProfitSum(routewayDrawProfit);
    }
	
}