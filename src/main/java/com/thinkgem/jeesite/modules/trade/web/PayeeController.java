/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.web;

import java.util.HashMap;
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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.mem.service.MemberService;
import com.thinkgem.jeesite.modules.trade.entity.MemberPayee;
import com.thinkgem.jeesite.modules.trade.entity.Payee;
import com.thinkgem.jeesite.modules.trade.service.MemberPayeeService;
import com.thinkgem.jeesite.modules.trade.service.PayeeService;


@Controller
@RequestMapping(value = "${adminPath}/trade/payee")
public class PayeeController extends BaseController {

	@Autowired
	private PayeeService payeeService;

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberPayeeService memberPayeeService;
	
	@ModelAttribute
	public Payee get(@RequestParam(required=false) String id) {
		Payee entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = payeeService.get(id);
		}
		if (entity == null){
			entity = new Payee();
		}
		return entity;
	}
	
	@RequiresPermissions("trade:payee:view")
	@RequestMapping(value = {"list", ""})
	public String list(Payee payee, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		String beginTime = payee.getBeginTime();
		if(StringUtils.isNotEmpty(beginTime)){
			payee.setBeginTime(beginTime+" 00:00:00");
		}
		String endTime = payee.getEndTime();
		if(StringUtils.isNotEmpty(endTime)){
			payee.setEndTime(endTime+" 23:59:59");
		}
		Page<Payee> page = payeeService.findPage(new Page<Payee>(request, response), payee); 
		if(StringUtils.isNotEmpty(beginTime)){
			payee.setBeginTime(beginTime);
		}
		if(StringUtils.isNotEmpty(endTime)){
			payee.setEndTime(endTime);
		}
		model.addAttribute("page", page);
		model.addAttribute("payee",payee);
		
		Member member = new Member();
		List<Member> list = memberService.findList(member);
		model.addAttribute("memberList",list);
		
		return "modules/trade/payee/payeeList";
	}

	@RequiresPermissions("trade:payee:view")
	@RequestMapping(value = "form")
	public String form(Payee payee, Model model) {
		model.addAttribute("payee", payee);
		return "modules/trade/payee/payeeForm";
	}
	
	@ResponseBody
	@RequiresPermissions("trade:payee:view")
	@RequestMapping(value = "checkAccount")
	public String checkAccount(String oldPayAccount,String payAccount,String payType) {
		Payee ee = new Payee();
		ee.setPayAccount(payAccount);
		ee.setPayType(payType);
		if (oldPayAccount !=null && payAccount.equals(oldPayAccount)) {
			return "true";
		} else if (payAccount !=null && payeeService.getByAccount(ee) == null) {
			return "true";
		}
		return "false";
	}

	@RequiresPermissions("trade:payee:view")
	@RequestMapping(value = "save")
	public String save(Payee payee, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, payee)){
			return form(payee, model);
		}
		if(!"true".equals(checkAccount(payee.getOldPayAccount(), payee.getPayAccount(), payee.getPayType()))){
			addMessage(model,"新增收款人失败，收款账号已存在");
			return form(payee, model);
		}
		payeeService.save(payee);
		addMessage(redirectAttributes, "新增收款人成功");
		return "redirect:"+Global.getAdminPath()+"/trade/payee/?repage";
	}
	
	@RequiresPermissions("trade:payee:view")
	@RequestMapping(value = "delete")
	public String delete(Payee payee, RedirectAttributes redirectAttributes) {
		payeeService.delete(payee);
		addMessage(redirectAttributes, "删除收款人成功");
		return "redirect:"+Global.getAdminPath()+"/trade/payee/?repage";
	}
	
	
	@ResponseBody
	@RequestMapping(value ="getPayeeList")
	public Map<String,Object> getPayeeList(Model model, HttpServletRequest request) {
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			Payee payee = new Payee();
			String payType = request.getParameter("payType");
			if (payType == null || "".equals(payType)) {
				payType = "";
			}else{
				payee.setPayType(payType);
			}
			
			List<Payee> payeeList = payeeService.findList(payee);
			result.put("payeeList", payeeList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			result.put("returnCode", "4004");
			result.put("returnMsg", "请求失败");
			return result;
		}
		return result;
	}
	
	@RequiresPermissions("trade:payee:view")
	@RequestMapping(value = "grant")
	public String grant(MemberPayee memberPayee, Model model, RedirectAttributes redirectAttributes) {
		
		if(StringUtils.isBlank(memberPayee.getPayeeId())){
			addMessage(redirectAttributes, "收款人为空");
			return "redirect:"+Global.getAdminPath()+"/trade/payee/?repage";
		}
		
		memberPayeeService.deleteByPayeeId(memberPayee);
		String memberIds = memberPayee.getMemberId();
		if(!StringUtils.isBlank(memberIds)){
			String[] arr = memberIds.split(",");
			if(arr!=null && arr.length>0){
				for(String memberId:arr){
					MemberPayee payee = new MemberPayee();
					payee.setMemberId(memberId);
					payee.setPayeeId(memberPayee.getPayeeId());
					memberPayeeService.save(payee);
				}
			}
		}
		
		
		addMessage(redirectAttributes, "收款人授权专用商户成功");
		return "redirect:"+Global.getAdminPath()+"/trade/payee/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value ="getMemberPayeeList")
	public Map<String,Object> getMemberPayeeList(Model model, HttpServletRequest request) {
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			MemberPayee memberPayee = new MemberPayee();
			memberPayee.setPayeeId(request.getParameter("payeeId"));
			
			List<MemberPayee> payeeList = memberPayeeService.findList(memberPayee);
			result.put("memberList", payeeList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			result.put("returnCode", "4004");
			result.put("returnMsg", "请求失败");
			return result;
		}
		return result;
	}

}