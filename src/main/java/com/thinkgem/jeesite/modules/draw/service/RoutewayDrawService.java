/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.draw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.draw.dao.RoutewayDrawDao;
import com.thinkgem.jeesite.modules.draw.entity.RoutewayDraw;

/**
 * 通道提现查询Service
 * @author chenjc
 * @version 2016-12-21
 */
@Service
@Transactional(readOnly = true)
public class RoutewayDrawService extends CrudService<RoutewayDrawDao, RoutewayDraw> {

	public RoutewayDraw get(String id) {
		return super.get(id);
	}
	
	public List<RoutewayDraw> findList(RoutewayDraw routewayDraw) {
		return super.findList(routewayDraw);
	}
	
	public Page<RoutewayDraw> findPage(Page<RoutewayDraw> page, RoutewayDraw routewayDraw) {
		routewayDraw.getSqlMap().put("dsf", dataScopeFilter(routewayDraw.getCurrentUser(), "o", ""));
		return super.findPage(page, routewayDraw);
	}
	
	@Transactional(readOnly = false)
	public void save(RoutewayDraw routewayDraw) {
		super.save(routewayDraw);
	}
	
	@Transactional(readOnly = false)
	public void delete(RoutewayDraw routewayDraw) {
		super.delete(routewayDraw);
	}
	
	@Transactional(readOnly = false)
	public int audit(RoutewayDraw routewayDraw) {
		return dao.audit(routewayDraw);
	}
	
	public Double countSumMoney(RoutewayDraw routewayDraw) {
		return dao.countSumMoney(routewayDraw);
	}
	
	public Integer countRecord(RoutewayDraw routewayDraw) {
		return dao.countRecord(routewayDraw);
	}
	
}