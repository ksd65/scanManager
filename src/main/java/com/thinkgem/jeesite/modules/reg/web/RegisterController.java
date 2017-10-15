/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.reg.web;

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
import com.thinkgem.jeesite.modules.reg.entity.Register;
import com.thinkgem.jeesite.modules.reg.service.RegisterService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 商户审核Controller
 * @author linzw
 * @version 2016-11-21
 */
@Controller
@RequestMapping(value = "${adminPath}/reg/register")
public class RegisterController extends BaseController {

	@Autowired
	private RegisterService registerService;
	
	@ModelAttribute
	public Register get(@RequestParam(required=false) String id) {
		Register entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = registerService.get(id);
		}
		if (entity == null){
			entity = new Register();
		}
		return entity;
	}
	
	@RequiresPermissions("reg:register:view")
	@RequestMapping(value = {"list", ""})
	public String list(Register register, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*
		 * 吴逢生 2017-06-05
		 * 数据范围过虑
		 */
		User user = UserUtils.getUser();
		register.setOffice(user.getOffice());
		
		Page<Register> page = registerService.findPage(new Page<Register>(request, response), register); 
		model.addAttribute("page", page);
		return "modules/reg/registerList";
	}
	
	@RequiresPermissions("reg:register:view")
	@RequestMapping(value = "detail")
	public String detail(Register register, Model model) {
		model.addAttribute("register", registerService.get(register.getId()));
		return "modules/reg/registerDetail";
	}
	
	@RequiresPermissions("reg:register:view")
	@RequestMapping(value = "form")
	public String form(Register register, Model model) {
		model.addAttribute("register", registerService.get(register.getId()));
		return "modules/reg/registerForm";
	}
	
	@RequiresPermissions("reg:register:edit")
	@RequestMapping(value = "audit")
	public String audit(Register register, Model model, RedirectAttributes redirectAttributes) {
		/*
		if (!beanValidator(model, register)){
			return form(register, model);
		}
		*/
		User user = UserUtils.getUser();
		registerService.registerAudit(register,user);
		addMessage(redirectAttributes, "审核提交完成");
		return "redirect:"+Global.getAdminPath()+"/reg/register/?repage";
	}
	
	@RequiresPermissions("reg:register:edit")
	@RequestMapping(value = "save")
	public String save(Register register, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, register)){
			return form(register, model);
		}
		registerService.save(register);
		addMessage(redirectAttributes, "保存商户审核成功");
		return "redirect:"+Global.getAdminPath()+"/reg/register/?repage";
	}
	
	@RequiresPermissions("reg:register:edit")
	@RequestMapping(value = "delete")
	public String delete(Register register, RedirectAttributes redirectAttributes) {
		registerService.delete(register);
		addMessage(redirectAttributes, "删除商户审核成功");
		return "redirect:"+Global.getAdminPath()+"/reg/register/?repage";
	}

}