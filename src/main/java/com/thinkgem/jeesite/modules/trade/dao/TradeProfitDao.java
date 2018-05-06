/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.trade.entity.RoutewayDrawProfit;
import com.thinkgem.jeesite.modules.trade.entity.TradeProfit;


@MyBatisDao
public interface TradeProfitDao extends CrudDao<TradeProfit> {
	//平台利润
	public List<TradeProfit> platProfit(TradeProfit tradeProfit);
	//平台利润统计
	public Map<String,Object> platProfitSum(TradeProfit tradeProfit);
	//代理商利润，按商户展示
	public List<TradeProfit> agentMemberProfit(TradeProfit tradeProfit);
	//代理商利润统计
	public Map<String,Object> agentProfitSum(TradeProfit tradeProfit);
	//代理商利润
	public List<TradeProfit> agentProfit(TradeProfit tradeProfit);
	
	//平台代付利润
	public List<RoutewayDrawProfit> platDrawProfit(RoutewayDrawProfit routewayDrawProfit);
	//平台代付利润统计
	public Map<String,Object> platDrawProfitSum(RoutewayDrawProfit routewayDrawProfit);
}