/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.entity.AgentRate;
import com.thinkgem.jeesite.modules.agent.dao.AgentRateDao;
import com.thinkgem.jeesite.modules.ecode.dao.EpayCodeDao;
import com.thinkgem.jeesite.modules.ecode.entity.EpayCode;
import com.thinkgem.jeesite.modules.sys.entity.AgentConfig;

/**
 * 代理商费率成本配置Service
 * @author 吴逢生
 * @version 2017-03-11
 */
@Service
@Transactional(readOnly = true)
public class AgentRateService extends CrudService<AgentRateDao, AgentRate> {
	
	@Autowired
	private AgentRateDao agentRateDao;
	
	public AgentRate get(String id) {
		return super.get(id);
	}
	
	public List<AgentRate> findList(AgentRate agentRate) {
		return super.findList(agentRate);
	}
	
	public Page<AgentRate> findPage(Page<AgentRate> page, AgentRate agentRate) {
		return super.findPage(page, agentRate);
	}
	
	@Transactional(readOnly = false)
	public void save(AgentRate agentRate) {
		super.save(agentRate);
	}
	
	@Transactional(readOnly = false)
	public void delete(AgentRate agentRate) {
		super.delete(agentRate);
	}
	
	@Transactional(readOnly = true)
	public String getOfficeCfg(AgentRate agentRate){
		return agentRateDao.getOfficeCfg(agentRate);
	}
	
	@Transactional(readOnly = true)
	public AgentRate getAgentRateByOfficeId(AgentRate agentRate){
		return agentRateDao.getAgentRateByOfficeId(agentRate);
	}
	
	@Transactional(readOnly = false)
	public int updateMemberRate(AgentRate agentRate) {
		return agentRateDao.updateMemberRate(agentRate);
	}
	
}