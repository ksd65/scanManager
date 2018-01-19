/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.trade.entity.TradeDetail;

/**
 * 交易明细查询DAO接口
 * @author chenjc
 * @version 2016-12-15
 */
@MyBatisDao
public interface TradeDetailDao extends CrudDao<TradeDetail> {
	
	public List<TradeDetail> findQuickList(TradeDetail tradeDetail);
	
	public List<TradeDetail> findMonthTradeMoney(TradeDetail tradeDetail);

    public List<TradeDetail> findHisList(TradeDetail tradeDetail);
    
    public Double countSumMoney(TradeDetail tradeDetail);
    
    public Integer countRecord(TradeDetail tradeDetail);
    
    public Double countSumMoneyHis(TradeDetail tradeDetail);
    
    public Integer countRecordHis(TradeDetail tradeDetail);
}