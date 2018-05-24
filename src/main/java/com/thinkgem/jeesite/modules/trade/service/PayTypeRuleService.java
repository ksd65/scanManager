/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.trade.dao.PayTypeRuleDao;
import com.thinkgem.jeesite.modules.trade.entity.PayTypeRule;


@Service
@Transactional(readOnly = true)
public class PayTypeRuleService extends CrudService<PayTypeRuleDao, PayTypeRule> {

	public PayTypeRule get(String id) {
		return super.get(id);
	}
	
	public List<PayTypeRule> findList(PayTypeRule PayTypeRule) {
		return super.findList(PayTypeRule);
	}
	
	public Page<PayTypeRule> findPage(Page<PayTypeRule> page, PayTypeRule PayTypeRule) {
		return super.findPage(page, PayTypeRule);
	}
	
	@Transactional(readOnly = false)
	public void save(PayTypeRule PayTypeRule) {
		super.save(PayTypeRule);
	}
	
	@Transactional(readOnly = false)
	public void delete(PayTypeRule PayTypeRule) {
		super.delete(PayTypeRule);
	}
	
	@Transactional(readOnly = false)
	public void update(PayTypeRule PayTypeRule) {
		dao.update(PayTypeRule);
	}
	
	public List<PayTypeRule> getDisTinctPayTypeRule(PayTypeRule PayTypeRule) {
		return dao.getDisTinctPayTypeRule(PayTypeRule);
	}
	
	public Page<PayTypeRule> getDisTinctPayTypeRulePage(Page<PayTypeRule> page, PayTypeRule payTypeRule) {
		payTypeRule.setPage(page);
		page.setList(getDisTinctPayTypeRule(payTypeRule));
		return page;
	}
	
	public List<PayTypeRule> getRuleList(PayTypeRule PayTypeRule) {
		return dao.getRuleList(PayTypeRule);
	}
	
	@Transactional(readOnly = false)
	public int deleteByType(PayTypeRule payTypeRule) {
		System.out.println(payTypeRule.getPayMethod()+"======"+payTypeRule.getPayType()+"======="+payTypeRule.getMemberId());
		return dao.deleteByType(payTypeRule);
	}
	
}