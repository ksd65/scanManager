/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
import com.thinkgem.jeesite.common.utils.CommonUtil;
import com.thinkgem.jeesite.common.utils.HttpUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.mem.service.MemberService;
import com.thinkgem.jeesite.modules.sys.entity.BankSub;
import com.thinkgem.jeesite.modules.trade.entity.PayTypeRule;
import com.thinkgem.jeesite.modules.trade.entity.RuleMerchant;
import com.thinkgem.jeesite.modules.trade.service.PayTypeRuleService;
import com.thinkgem.jeesite.modules.trade.service.RuleMerchantService;


@Controller
@RequestMapping(value = "${adminPath}/paytype/rule")
public class PayTypeRuleController extends BaseController {

	@Autowired
	private PayTypeRuleService payTypeRuleService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private RuleMerchantService ruleMerchantService;
	
	
	@ModelAttribute
	public PayTypeRule get(@RequestParam(required=false) String id) {
		PayTypeRule entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = payTypeRuleService.get(id);
		}
		if (entity == null){
			entity = new PayTypeRule();
		}
		return entity;
	}
	
	@RequiresPermissions("paytype:rule:view")
	@RequestMapping(value = {"list", ""})
	public String list(PayTypeRule payTypeRule, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		Page<PayTypeRule> page = payTypeRuleService.getDisTinctPayTypeRulePage(new Page<PayTypeRule>(request, response), payTypeRule); 
		
		model.addAttribute("page", page);
		model.addAttribute("payTypeRule",payTypeRule);
		
		return "modules/trade/payTypeRule/list";
	}

	@RequiresPermissions("paytype:rule:view")
	@RequestMapping(value = "form")
	public String form(PayTypeRule payTypeRule, Model model) {
		Member member = new Member();
		List<Member> memberList = memberService.findList(member);
		model.addAttribute("memberList", memberList);
		RuleMerchant ruleMerchant = new RuleMerchant();
		List<RuleMerchant> routeList = ruleMerchantService.getRoute(ruleMerchant);
		model.addAttribute("routeList", routeList);
		return "modules/trade/payTypeRule/rule";
	}

	@ResponseBody
	@RequestMapping(value ="getRouteMerchantList")
	public Map<String,Object> getRouteMerchantList(Model model, HttpServletRequest request) {
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			RuleMerchant ruleMerchant = new RuleMerchant();
			String routeCode = request.getParameter("routeCode");
			if (routeCode == null || "".equals(routeCode)) {
				routeCode = "";
			}else{
				ruleMerchant.setRouteCode(routeCode);
			}
			
			List<RuleMerchant> merList = ruleMerchantService.getRouteMerchant(ruleMerchant);
			result.put("merList", merList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			result.put("returnCode", "4004");
			result.put("returnMsg", "请求失败");
			return result;
		}
		return result;
	}
	
	
	@RequiresPermissions("paytype:rule:view")
	@RequestMapping(value = "saveMem")
	@ResponseBody
	public Map<String,Object> saveMem(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		Map<String,Object> result = new HashMap<String,Object>();
		String payMethod = request.getParameter("payMethod");
		String payType = request.getParameter("payType");
		String memberId = request.getParameter("memberId");
		String merInfo = request.getParameter("merInfo");
		if(!StringUtils.isBlank(merInfo)){
			PayTypeRule payTypeRule1 = new PayTypeRule();
			payTypeRule1.setPayMethod(payMethod);
			payTypeRule1.setPayType(payType);
			payTypeRule1.setMemberId(memberId);
			payTypeRuleService.deleteByType(payTypeRule1);
			String [] arr = merInfo.split(";");
			for(String mer : arr){
				PayTypeRule payTypeRule = new PayTypeRule();
				payTypeRule.setPayMethod(payMethod);
				payTypeRule.setPayType(payType);
				payTypeRule.setMemberId(memberId);
				payTypeRule.setRuleType("2");
				String [] arr1 = mer.split("#");
				payTypeRule.setRouteCode(arr1[0]);
				payTypeRule.setAisleType(arr1[1].trim());
				payTypeRule.setMerchantCode(arr1[2]);
				payTypeRule.setRuleRate(arr1[3]);
				payTypeRule.setMinMoney("0");
				payTypeRule.setMaxMoney("999999");
				payTypeRule.setBeginTime("000000");
				payTypeRule.setEndTime("235959");
				payTypeRuleService.save(payTypeRule);
			}
			result.put("returnCode", "0000");
			result.put("returnMsg", "配置成功");
		}else{
			result.put("returnCode", "0001");
			result.put("returnMsg", "配置失败");
		}
		
		return result;
	}
	
	@RequiresPermissions("paytype:rule:view")
	@RequestMapping(value = "edit")
	public String edit(PayTypeRule payTypeRule, Model model) {
		Member member = new Member();
		List<Member> memberList = memberService.findList(member);
		model.addAttribute("memberList", memberList);
		RuleMerchant ruleMerchant = new RuleMerchant();
		List<RuleMerchant> routeList = ruleMerchantService.getRoute(ruleMerchant);
		model.addAttribute("routeList", routeList);
		List<PayTypeRule> ruleList = payTypeRuleService.getRuleList(payTypeRule);
		model.addAttribute("ruleList", ruleList);
		return "modules/trade/payTypeRule/edit";
	}
	
	@RequiresPermissions("paytype:rule:view")
	@RequestMapping(value = "delete")
	public String delete(PayTypeRule payTypeRule, RedirectAttributes redirectAttributes) {
		payTypeRuleService.deleteByType(payTypeRule);
		addMessage(redirectAttributes, "删除通道规则成功");
		return "redirect:"+Global.getAdminPath()+"/paytype/rule/?repage";
	}
	
	@RequiresPermissions("paytype:rule:view")
	@RequestMapping(value = "detail")
	public String detail(PayTypeRule payTypeRule, Model model) {
		Member member = memberService.get(payTypeRule.getMemberId());
		if(member == null){
			payTypeRule.setMemberName("默认商户");
		}else{
			payTypeRule.setMemberName(member.getName());
		}
		List<PayTypeRule> ruleList = payTypeRuleService.getRuleList(payTypeRule);
		model.addAttribute("ruleList", ruleList);
		model.addAttribute("payTypeRule",payTypeRule);
		return "modules/trade/payTypeRule/detail";
	}
	
	

}