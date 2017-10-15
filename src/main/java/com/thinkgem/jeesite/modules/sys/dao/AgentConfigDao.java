/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.AgentConfig;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * OEM代理商配置信息DAO接口
 * @author 吴逢生
 * @version 2017-04-13
 */
@MyBatisDao
public interface AgentConfigDao extends CrudDao<AgentConfig> {
	
	AgentConfig findByUrl(AgentConfig agentConfig);
	
	
	public AgentConfig getAgentConfigByAgtCode(String agtCode);
}