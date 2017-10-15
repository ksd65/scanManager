/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.verifycode.web;

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
import com.thinkgem.jeesite.modules.verifycode.entity.VerifyCode;
import com.thinkgem.jeesite.modules.verifycode.service.VerifyCodeService;

/**
 * 验证码Controller
 * @author jjy
 * @version 2017-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/verifycode/verifyCode")
public class VerifyCodeController extends BaseController {

	@Autowired
	private VerifyCodeService verifyCodeService;
	
	@ModelAttribute
	public VerifyCode get(@RequestParam(required=false) String id) {
		VerifyCode entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = verifyCodeService.get(id);
		}
		if (entity == null){
			entity = new VerifyCode();
		}
		return entity;
	}
	
	@RequiresPermissions("verifycode:verifyCode:view")
	@RequestMapping(value = {"list", ""})
	public String list(VerifyCode verifyCode, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VerifyCode> page = verifyCodeService.findPage(new Page<VerifyCode>(request, response), verifyCode); 
		model.addAttribute("page", page);
		return "modules/verifycode/verifyCodeList";
	}

	@RequiresPermissions("verifycode:verifyCode:view")
	@RequestMapping(value = "form")
	public String form(VerifyCode verifyCode, Model model) {
		model.addAttribute("verifyCode", verifyCode);
		return "modules/verifycode/verifyCodeForm";
	}

	@RequiresPermissions("verifycode:verifyCode:edit")
	@RequestMapping(value = "save")
	public String save(VerifyCode verifyCode, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, verifyCode)){
			return form(verifyCode, model);
		}
		verifyCodeService.save(verifyCode);
		addMessage(redirectAttributes, "保存验证码成功");
		return "redirect:"+Global.getAdminPath()+"/verifycode/verifyCode/?repage";
	}
	
	@RequiresPermissions("verifycode:verifyCode:edit")
	@RequestMapping(value = "delete")
	public String delete(VerifyCode verifyCode, RedirectAttributes redirectAttributes) {
		verifyCodeService.delete(verifyCode);
		addMessage(redirectAttributes, "删除验证码成功");
		return "redirect:"+Global.getAdminPath()+"/verifycode/verifyCode/?repage";
	}

}