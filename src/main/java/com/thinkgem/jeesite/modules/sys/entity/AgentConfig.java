/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * OEM代理商配置信息Entity
 * @author 吴逢生
 * @version 2017-04-13
 */
public class AgentConfig extends DataEntity<AgentConfig> {
	
	private static final long serialVersionUID = 1L;
	private String agentId;		// 代理商编号
	private String agentName;		// 代理商名称
	private String agentShortName;		// 代理商简称
	private String agentCopyright;		// 代理商版权信息
	private String url;		// URL域名
	private String epaycodeUrlName;	 // 地址配置名
	private String epaycodeImg;		// 背景图片名
	private String agentUrlName;	 // 代理商地址配置名
	private String agentImg;		// 代理商背景图片名
	
	public AgentConfig() {
		super();
	}

	public AgentConfig(String id){
		super(id);
	}

	@Length(min=0, max=64, message="代理商编号长度必须介于 0 和 64 之间")
	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	
	@Length(min=0, max=200, message="代理商名称长度必须介于 0 和 200 之间")
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	@Length(min=0, max=200, message="代理商简称长度必须介于 0 和 200 之间")
	public String getAgentShortName() {
		return agentShortName;
	}

	public void setAgentShortName(String agentShortName) {
		this.agentShortName = agentShortName;
	}
	
	@Length(min=0, max=200, message="代理商版权信息长度必须介于 0 和 200 之间")
	public String getAgentCopyright() {
		return agentCopyright;
	}

	public void setAgentCopyright(String agentCopyright) {
		this.agentCopyright = agentCopyright;
	}
	
	@Length(min=0, max=255, message="URL域名长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=32, message="地址配置名长度必须介于 0 和 32 之间")
	public String getEpaycodeUrlName() {
		return epaycodeUrlName;
	}

	public void setEpaycodeUrlName(String epaycodeUrlName) {
		this.epaycodeUrlName = epaycodeUrlName;
	}
	
	@Length(min=0, max=32, message="背景图片名长度必须介于 0 和 32之间")
	public String getEpaycodeImg() {
		return epaycodeImg;
	}

	public void setEpaycodeImg(String epaycodeImg) {
		this.epaycodeImg = epaycodeImg;
	}
	
	@Length(min=0, max=32, message="代理商地址配置名长度必须介于 0 和 32 之间")
	public String getAgentUrlName() {
		return agentUrlName;
	}

	public void setAgentUrlName(String agentUrlName) {
		this.agentUrlName = agentUrlName;
	}
	
	@Length(min=0, max=32, message="代理商背景图片名长度必须介于 0 和 32之间")
	public String getAgentImg() {
		return agentImg;
	}

	public void setAgentImg(String agentImg) {
		this.agentImg = agentImg;
	}
}