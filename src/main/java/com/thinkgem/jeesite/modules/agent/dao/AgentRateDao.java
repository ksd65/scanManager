/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.agent.entity.AgentRate;
import com.thinkgem.jeesite.modules.ecode.entity.EpayCode;
import com.thinkgem.jeesite.modules.sys.entity.AgentConfig;

/**
 * 代理商费率成本配置DAO接口
 * @author 吴逢生
 * @version 2017-03-11
 */
@MyBatisDao
public interface AgentRateDao extends CrudDao<AgentRate> {
	public String getOfficeCfg(AgentRate agentRate);
	public AgentRate getAgentRateByOfficeId(AgentRate agentRate);
	public int updateMemberRate(AgentRate agentRate);
}