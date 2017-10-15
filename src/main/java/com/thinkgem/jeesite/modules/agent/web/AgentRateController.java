/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.agent.entity.AgentRate;
import com.thinkgem.jeesite.modules.agent.service.AgentRateService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 代理商费率成本配置Controller
 * @author 吴逢生
 * @version 2017-03-11
 */
@Controller
@RequestMapping(value = "${adminPath}/agent/agentRate")
public class AgentRateController extends BaseController {

	@Autowired
	private AgentRateService agentRateService;
	
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute
	public AgentRate get(@RequestParam(required=false) String id) {
		AgentRate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = agentRateService.get(id);
		}
		if (entity == null){
			entity = new AgentRate();
		}
		return entity;
	}
	
	@RequiresPermissions("agent:agentRate:view")
	@RequestMapping(value = {"list", ""})
	public String list(AgentRate agentRate, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		agentRate.setOffice(user.getOffice());
		
		Page<AgentRate> page = agentRateService.findPage(new Page<AgentRate>(request, response), agentRate); 
		model.addAttribute("page", page);
		return "modules/agent/agentRateList";
	}

	@RequiresPermissions("agent:agentRate:view")
	@RequestMapping(value = "form")
	public String form(AgentRate agentRate, Model model) {
		model.addAttribute("agentRate", agentRate);
		return "modules/agent/agentRateForm";
	}
	
	@RequiresPermissions("agent:agentRate:view")
	@RequestMapping(value = "memberRateform")
	public String memberRateform(AgentRate agentRate, Model model) {
		User user = UserUtils.getUser();
		agentRate.setOffice(user.getOffice());
		agentRate = agentRateService.getAgentRateByOfficeId(agentRate);
		model.addAttribute("agentRate", agentRate);
		return "modules/agent/memberRateForm";
	}
	
	@RequiresPermissions("agent:agentRate:edit")
	@RequestMapping(value = "save")
	public String save(AgentRate agentRate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, agentRate)){
			return form(agentRate, model);
		}
		
		if(agentRate.getId() == null || agentRate.getId().equals("")){
			String isCfg = agentRateService.getOfficeCfg(agentRate);
			if(Integer.parseInt(isCfg) > 0){
				addMessage(redirectAttributes, "该代理商成本已配置!");
				return "redirect:" + adminPath + "/agent/agentRate/form";
			}
		}
		else{
			//List<AgentRate> list = agentRateService.findList(agentRate);
			//if(list != null && list.size() > 0){
				agentRate.setId(agentRate.getId());
			//}
		}
		
		agentRateService.save(agentRate);
		addMessage(redirectAttributes, "保存代理商费率成本配置成功");
		return "redirect:"+Global.getAdminPath()+"/agent/agentRate/?repage";
	}
	
	@RequiresPermissions("agent:agentRate:edit")
	@RequestMapping(value = "delete")
	public String delete(AgentRate agentRate, RedirectAttributes redirectAttributes) {
		agentRateService.delete(agentRate);
		addMessage(redirectAttributes, "删除代理商费率成本配置成功");
		return "redirect:"+Global.getAdminPath()+"/agent/agentRate/?repage";
	}
	
	/**
	 * 获取一级代理商数据。
	 * @param extId 排除的ID
	 * @param type	类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Office> list = officeService.findList(isAll);
		User user = UserUtils.getUser();
		int curGrade = 0;
		for (int i=0; i<list.size(); i++){
			Office e = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1))
					&& (e.getType().equals("4"))
					//&& (e.getAgtGrade().equals("1"))				
					&& Global.YES.equals(e.getUseable())){
				if(e.getCode().equals(user.getOffice().getCode())){
					curGrade = Integer.parseInt(e.getAgtGrade());
				}
				int agtGrade = Integer.parseInt(e.getAgtGrade());
				if(agtGrade - curGrade != 1){
					continue;
				}
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				/*
				map.put("type", e.getType());
				if (type != null && "3".equals(type)){
					map.put("isParent", true);
				}
				*/
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	@RequiresPermissions("agent:agentRate:edit")
	@RequestMapping(value = "saveMemberRate")
	public String saveMemberRate(AgentRate agentRate, Model model, RedirectAttributes redirectAttributes) {
		agentRateService.updateMemberRate(agentRate);
		model.addAttribute("agentRate", agentRate);
		addMessage(redirectAttributes, "配置成功");
		return "redirect:" + adminPath + "/agent/agentRate/memberRateform";
	}
}