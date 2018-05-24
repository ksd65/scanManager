/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.trade.dao.RuleMerchantDao;
import com.thinkgem.jeesite.modules.trade.entity.RuleMerchant;


@Service
@Transactional(readOnly = true)
public class RuleMerchantService extends CrudService<RuleMerchantDao, RuleMerchant> {

	
	public List<RuleMerchant> getRoute(RuleMerchant ruleMerchant) {
		return dao.getRoute(ruleMerchant);
	}
	
	public List<RuleMerchant> getRouteMerchant(RuleMerchant ruleMerchant) {
		return dao.getRouteMerchant(ruleMerchant);
	}
	
}