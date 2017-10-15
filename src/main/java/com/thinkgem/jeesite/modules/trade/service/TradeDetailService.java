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
import com.thinkgem.jeesite.modules.trade.entity.TradeDetail;
import com.thinkgem.jeesite.modules.trade.dao.TradeDetailDao;

/**
 * 交易明细查询Service
 * @author chenjc
 * @version 2016-12-15
 */
@Service
@Transactional(readOnly = true)
public class TradeDetailService extends CrudService<TradeDetailDao, TradeDetail> {
	
	@Autowired
	private TradeDetailDao tradeDetailDao;
	
	public TradeDetail get(String id) {
		return super.get(id);
	}
	
	public List<TradeDetail> findList(TradeDetail tradeDetail) {
		return super.findList(tradeDetail);
	}
	
	public Page<TradeDetail> findPage(Page<TradeDetail> page, TradeDetail tradeDetail) {
		tradeDetail.getSqlMap().put("dsf", dataScopeFilter(tradeDetail.getCurrentUser(), "o", ""));
		return super.findPage(page, tradeDetail);
	}
	
	/**
	 * 快捷分页查询
	 * @param page
	 * @param tradeDetail
	 * @return
	 */
	public Page<TradeDetail> findTradeQuickPage(Page<TradeDetail> page,TradeDetail tradeDetail){
		tradeDetail.getSqlMap().put("dsf", dataScopeFilter(tradeDetail.getCurrentUser(), "o", ""));
		tradeDetail.setPage(page);
		page.setList(dao.findQuickList(tradeDetail));
		return page;
	}
	
	public List<TradeDetail> findQuickList(TradeDetail tradeDetail){
		return dao.findQuickList(tradeDetail);
	}
	
	public Page<TradeDetail> findTradeDetail(Page<TradeDetail> page, TradeDetail tradeDetail) {
		
		tradeDetail.getSqlMap().put("dsf", dataScopeFilter(tradeDetail.getCurrentUser(), "o", ""));
		// 设置分页参数
		tradeDetail.setPage(page);
		// 执行分页查询
		page.setList(tradeDetailDao.findList(tradeDetail));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(TradeDetail tradeDetail) {
		super.save(tradeDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(TradeDetail tradeDetail) {
		super.delete(tradeDetail);
	}
	
	public List<TradeDetail> findMonthTradeMoney(TradeDetail tradeDetail){
		return dao.findMonthTradeMoney(tradeDetail);
	}
	
	public List<TradeDetail> findHisList(TradeDetail tradeDetail){
		return dao.findHisList(tradeDetail);
	}
	
	/**
	 * 历史交易查询
	 * @param tradeDetailPage
	 * @param tradeDetail
	 * @return
	 */
	public Page<TradeDetail> findHisPage(Page<TradeDetail> tradeDetailPage, TradeDetail tradeDetail) {
		tradeDetail.setPage(tradeDetailPage);
		tradeDetailPage.setList(dao.findHisList(tradeDetail));
		return tradeDetailPage;
	}
}