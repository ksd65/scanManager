/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mem.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.mem.entity.MemberBindAccDtl;
import com.thinkgem.jeesite.modules.mem.service.MemberBindAccDtlService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.trade.entity.TradeDetail;

/**
 * 商户绑卡流水表Controller
 * @author jjy
 * @version 2017-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/mem/memberBindAccDtl")
public class MemberBindAccDtlController extends BaseController {

	@Autowired
	private MemberBindAccDtlService memberBindAccDtlService;

	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute
	public MemberBindAccDtl get(@RequestParam(required=false) String id) {
		MemberBindAccDtl entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memberBindAccDtlService.get(id);
		}
		if (entity == null){
			entity = new MemberBindAccDtl();
		}
		return entity;
	}
	
	@RequiresPermissions("mem:memberBindAccDtl:view")
	@RequestMapping(value = {"list", ""})
	public String list(MemberBindAccDtl memberBindAccDtl, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		if(StringUtils.isNotBlank(officeId)){
			Office office = officeService.get(officeId);
			memberBindAccDtl.setOffice(office);
		}else{
			User user = UserUtils.getUser();
			memberBindAccDtl.setOffice(user.getOffice());
		}
		Page<MemberBindAccDtl> page = memberBindAccDtlService.findPage(new Page<MemberBindAccDtl>(request, response), memberBindAccDtl); 
		model.addAttribute("page", page);
		model.addAttribute("memberBindAccDtl",memberBindAccDtl);
		return "modules/mem/memberBindAccDtlList";
	}

	@RequiresPermissions("mem:memberBindAccDtl:view")
	@RequestMapping(value = "form")
	public String form(MemberBindAccDtl memberBindAccDtl, Model model) {
		model.addAttribute("memberBindAccDtl", memberBindAccDtl);
		return "modules/mem/memberBindAccDtlForm";
	}

	@RequiresPermissions("mem:memberBindAccDtl:edit")
	@RequestMapping(value = "save")
	public String save(MemberBindAccDtl memberBindAccDtl, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, memberBindAccDtl)){
			return form(memberBindAccDtl, model);
		}
		memberBindAccDtlService.save(memberBindAccDtl);
		addMessage(redirectAttributes, "保存商户绑卡流水成功");
		return "redirect:"+Global.getAdminPath()+"/mem/memberBindAccDtl/?repage";
	}
	
	@RequiresPermissions("mem:memberBindAccDtl:edit")
	@RequestMapping(value = "delete")
	public String delete(MemberBindAccDtl memberBindAccDtl, RedirectAttributes redirectAttributes) {
		memberBindAccDtlService.delete(memberBindAccDtl);
		addMessage(redirectAttributes, "删除商户绑卡流水成功");
		return "redirect:"+Global.getAdminPath()+"/mem/memberBindAccDtl/?repage";
	}
	
	
	@RequiresPermissions("mem:memberBindAccDtl:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(MemberBindAccDtl memberBindAccDtl,
							 HttpServletRequest request,
							 HttpServletResponse response,
							 RedirectAttributes redirectAttributes,
							 String officeId) {
		try {
			// 当前用户代理商
			Office office = UserUtils.getUser().getOffice();
			
			// 选择代理商
			if(StringUtils.isNotBlank(officeId)){
				Office office2 = officeService.get(officeId);
				if(office2 != null){
					office = office2;
				}
			}
			
			memberBindAccDtl.setOffice(office);
			
            String fileName = "商户绑卡信息流水"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            
            List<MemberBindAccDtl> list = memberBindAccDtlService.findList(memberBindAccDtl);
    		new ExportExcel("商户绑卡信息流水", MemberBindAccDtl.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出商户绑卡信息流水数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/mem/memberBindAccDtl/list?repage";
    }

}