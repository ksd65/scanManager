package com.thinkgem.jeesite.modules.sys.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.dao.AgentConfigDao;
import com.thinkgem.jeesite.modules.sys.entity.AgentConfig;

public class AgentUtils {
	
	private static AgentConfigDao agentConfigDao = SpringContextHolder.getBean(AgentConfigDao.class);
	
	public static final String ORGAN_CONFIG_CACHE = "agentConfigCache";
	public static final String ORGAN_CONFIG_CACHE_ID_ = "agent_id_";
	
	public static AgentConfig getConfig(String remoteHost){
		CacheUtils.remove(ORGAN_CONFIG_CACHE, ORGAN_CONFIG_CACHE_ID_ + remoteHost);
		AgentConfig config = (AgentConfig)CacheUtils.get(ORGAN_CONFIG_CACHE, ORGAN_CONFIG_CACHE_ID_ + remoteHost);
		if (config ==  null){
			config=new AgentConfig();
			config.setUrl(remoteHost); 
			config=agentConfigDao.findByUrl(config);
			if (config == null){
				return null;
			} 
			CacheUtils.put(ORGAN_CONFIG_CACHE, ORGAN_CONFIG_CACHE_ID_ + remoteHost, config); 
		} 
		return config;
	}
	
	public static AgentConfig getConfig(HttpServletRequest request){
		
		String remoteHost = request.getHeader("Host");
		String[] remoteStr = remoteHost.split(":");
		if(remoteStr.length > 0){
			remoteHost = remoteStr[0];
		}
		System.out.println("remoteHost="+remoteHost);
		return AgentUtils.getConfig(remoteHost);
	}
}
