/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.AgentConfig;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.dao.AgentConfigDao;

/**
 * OEM代理商配置信息Service
 * @author 吴逢生
 * @version 2017-04-13
 */
@Service
@Transactional(readOnly = true)
public class AgentConfigService extends CrudService<AgentConfigDao, AgentConfig> {

	public AgentConfig get(String id) {
		return super.get(id);
	}
	
	public List<AgentConfig> findList(AgentConfig agentConfig) {
		return super.findList(agentConfig);
	}
	
	public Page<AgentConfig> findPage(Page<AgentConfig> page, AgentConfig agentConfig) {
		return super.findPage(page, agentConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(AgentConfig agentConfig) {
		super.save(agentConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(AgentConfig agentConfig) {
		super.delete(agentConfig);
	}
	
	@Transactional(readOnly = true)
	public AgentConfig getAgentConfigByAgtCode(String agtCode){
		return dao.getAgentConfigByAgtCode(agtCode);
	}
	
}