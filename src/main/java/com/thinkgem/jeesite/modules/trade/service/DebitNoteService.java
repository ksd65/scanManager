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
import com.thinkgem.jeesite.modules.trade.dao.DebitNoteDao;
import com.thinkgem.jeesite.modules.trade.entity.DebitNote;

/**
 * 交易明细查询Service
 * @author chenjc
 * @version 2016-12-15
 */
@Service
@Transactional(readOnly = true)
public class DebitNoteService extends CrudService<DebitNoteDao, DebitNote> {
	
	@Autowired
	private DebitNoteDao debitNoteDao;
	
	public DebitNote get(String id) {
		return super.get(id);
	}
	
	public List<DebitNote> findList(DebitNote tradeDetail) {
		return super.findList(tradeDetail);
	}
	
	public Page<DebitNote> findPage(Page<DebitNote> page, DebitNote tradeDetail) {
		tradeDetail.getSqlMap().put("dsf", dataScopeFilter(tradeDetail.getCurrentUser(), "o", ""));
		return super.findPage(page, tradeDetail);
	}
	
	
	
	@Transactional(readOnly = false)
	public void save(DebitNote tradeDetail) {
		super.save(tradeDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(DebitNote tradeDetail) {
		super.delete(tradeDetail);
	}
	
	
}