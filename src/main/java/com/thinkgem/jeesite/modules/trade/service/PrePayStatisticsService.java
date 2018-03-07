/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.trade.dao.PrePayStatisticsDao;
import com.thinkgem.jeesite.modules.trade.entity.PrePayStatistics;


@Service
@Transactional(readOnly = true)
public class PrePayStatisticsService extends CrudService<PrePayStatisticsDao, PrePayStatistics> {

	public PrePayStatistics get(String id) {
		return super.get(id);
	}
	
	public List<PrePayStatistics> findList(PrePayStatistics prePayStatistics) {
		return super.findList(prePayStatistics);
	}
	
	public Page<PrePayStatistics> findPage(Page<PrePayStatistics> page, PrePayStatistics prePayStatistics) {
		return super.findPage(page, prePayStatistics);
	}
	
	@Transactional(readOnly = false)
	public void save(PrePayStatistics prePayStatistics) {
		super.save(prePayStatistics);
	}
	
	@Transactional(readOnly = false)
	public void delete(PrePayStatistics prePayStatistics) {
		super.delete(prePayStatistics);
	}
	
	
}