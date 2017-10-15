/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.AgentConfig;
import com.thinkgem.jeesite.modules.sys.service.AgentConfigService;

/**
 * OEM代理商配置信息Controller
 * @author 吴逢生
 * @version 2017-04-13
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/agentConfig")
public class AgentConfigController extends BaseController {

	@Autowired
	private AgentConfigService agentConfigService;
	
	@ModelAttribute
	public AgentConfig get(@RequestParam(required=false) String id) {
		AgentConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = agentConfigService.get(id);
		}
		if (entity == null){
			entity = new AgentConfig();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:agentConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(AgentConfig agentConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AgentConfig> page = agentConfigService.findPage(new Page<AgentConfig>(request, response), agentConfig); 
		model.addAttribute("page", page);
		return "modules/sys/agentConfigList";
	}

	@RequiresPermissions("sys:agentConfig:view")
	@RequestMapping(value = "form")
	public String form(AgentConfig agentConfig, Model model) {
		model.addAttribute("agentConfig", agentConfig);
		return "modules/sys/agentConfigForm";
	}

	@RequiresPermissions("sys:agentConfig:edit")
	@RequestMapping(value = "save")
	public String save(AgentConfig agentConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, agentConfig)){
			return form(agentConfig, model);
		}
		agentConfigService.save(agentConfig);
		addMessage(redirectAttributes, "保存OEM代理商配置信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/agentConfig/?repage";
	}
	
	@RequiresPermissions("sys:agentConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(AgentConfig agentConfig, RedirectAttributes redirectAttributes) {
		agentConfigService.delete(agentConfig);
		addMessage(redirectAttributes, "删除OEM代理商配置信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/agentConfig/?repage";
	}

}