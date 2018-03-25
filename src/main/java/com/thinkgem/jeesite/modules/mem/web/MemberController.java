/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mem.web;

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
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.HttpUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.mem.entity.MemberPayType;
import com.thinkgem.jeesite.modules.mem.service.MemberPayTypeService;
import com.thinkgem.jeesite.modules.mem.service.MemberService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 商户Controller
 * @author linzw
 * @version 2016-11-15
 */
@Controller
@RequestMapping(value = "${adminPath}/mem/member")
public class MemberController extends BaseController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberPayTypeService memberPayTypeService;
	
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute
	public Member get(@RequestParam(required=false) String id) {
		Member entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memberService.get(id);
		}
		if (entity == null){
			entity = new Member();
		}
		return entity;
	}
	
	@RequiresPermissions("mem:member:view")
	@RequestMapping(value = {"list", ""})
	public String list(Member member, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		if(StringUtils.isNotBlank(officeId)){
			Office office = officeService.get(officeId);
			member.setOffice(office);
		}else{
			User user = UserUtils.getUser();
			member.setOffice(user.getOffice());
		}
		Page<Member> page = memberService.findPage(new Page<Member>(request, response), member); 
		model.addAttribute("page", page);
		model.addAttribute("member",member);
		return "modules/mem/memberList";
	}

	@RequiresPermissions("mem:member:view")
	@RequestMapping(value = "form")
	public String form(Member member, Model model) {
		model.addAttribute("member", member);
		return "modules/mem/memberForm";
	}
	
	@RequiresPermissions("mem:member:view")
	@RequestMapping(value = "detail")
	public String detail(Member member, Model model) {
		model.addAttribute("member", member);
		return "modules/mem/memberDetail";
	}

	@RequiresPermissions("mem:member:edit")
	@RequestMapping(value = "save")
	public String save(Member member, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, member)){
			return form(member, model);
		}
		memberService.save(member);
		addMessage(redirectAttributes, "保存商户信息成功");
		return "redirect:"+Global.getAdminPath()+"/mem/member/?repage";
	}

	@RequiresPermissions("mem:member:edit")
	@RequestMapping(value = "delete")
	public String delete(Member member, RedirectAttributes redirectAttributes) {
		memberService.delete(member);
		addMessage(redirectAttributes, "删除商户信息成功");
		return "redirect:"+Global.getAdminPath()+"/mem/member/?repage";
	}

	@RequiresPermissions("mem:member:audit")
	@RequestMapping(value = "disable")
	public String disable(Member member, RedirectAttributes redirectAttributes) {
		memberService.disable(member);
		addMessage(redirectAttributes, "禁用成功");
		return "redirect:"+Global.getAdminPath()+"/mem/member/?repage";
	}
	
	@RequiresPermissions("mem:member:audit")
	@RequestMapping(value = "enable")
	public String enable(Member member, RedirectAttributes redirectAttributes) {
		memberService.enable(member);
		addMessage(redirectAttributes, "启用成功");
		return "redirect:"+Global.getAdminPath()+"/mem/member/?repage";
	}
	
	@RequiresPermissions("mem:member:audit")
	@RequestMapping(value = "audit")
	public String audit(Member member, RedirectAttributes redirectAttributes) {
		memberService.enable(member);
		addMessage(redirectAttributes, "审核成功");
		return "redirect:"+Global.getAdminPath()+"/mem/member/?repage";
	}
	
	/**
	 * 商户绑卡信息
	 * @param member
	 * @return
	 */
	@RequiresPermissions("mem:member:view")
	@RequestMapping(value = "banks")
	public String banks(Member member, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		if(StringUtils.isNotBlank(officeId)){
			Office office = officeService.get(officeId);
			member.setOffice(office);
		}else{
			User user = UserUtils.getUser();
			member.setOffice(user.getOffice());
		}
		Page<Member> page = memberService.findBankPage(new Page<Member>(request, response), member); 
		model.addAttribute("page", page);
		model.addAttribute("member",member);
		return "modules/mem/memberBankList";
	}

	/**
	 * 
	 * @param member
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("mem:member:view")
	@RequestMapping(value = "monthMemberNum")
	public String monthMemberNum(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
		String endMonth = DateUtils.getYear()+DateUtils.getMonth();
		String beginMonth = DateUtils.getSubtract12Month(endMonth);
		member.setBeginMonth(beginMonth);
		member.setEndMonth(endMonth);
		
		List<Member> list = memberService.findMonthMemberNum(member);
		long memberNum = 0;
		for(Member m:list){
			memberNum += Integer.parseInt(m.getNum());
			m.setNum(String.valueOf(memberNum));
		}
		model.addAttribute("list", list);
		/**
		 * 生成月份数据
		 */
		String listMonth = "[";
		for(Member m :list){
			listMonth = listMonth + "'"+m.getTxMonth()+"',";
		}
		listMonth = listMonth.substring(0,listMonth.lastIndexOf(","))+"]";
		model.addAttribute("listMonth", listMonth);
		
		/**
		 * 生成图表数据
		 */
		String listData = "[";
		for(Member m :list){
			listData = listData + "'"+ String.format("%#.2f", Float.parseFloat(m.getNum())/10000) +"',";
		}
		listData = listData.substring(0,listData.lastIndexOf(","))+"]";
		model.addAttribute("listData", listData);
		return "modules/mem/monthMemberList";
	}

	/**
	 * 更新商户信息（状态，提现状态，单笔限额，单日限额）
	 * @param m
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("mem:member:edit")
	@RequestMapping(value = "update")
	public String update(Member m, Model model, RedirectAttributes redirectAttributes) {
		Map<String, Object> data = new HashMap<String, Object>();
		Member member = memberService.get(m.getId());
		member.setStatus(m.getStatus());
		member.setDrawStatus(m.getDrawStatus());
		member.setSingleLimit(m.getSingleLimit());
		member.setDayLimit(m.getDayLimit());
		memberService.save(member);

		addMessage(redirectAttributes, "保存商户信息成功");
		return "redirect:"+Global.getAdminPath()+"/mem/member/?repage";
	}

	/**
	 * 商户认证信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("mem:member:view")
	@RequestMapping(value = "cert")
	public String cert(String id,Model model){
		Member member = memberService.get(id);
		model.addAttribute("member",member);
		return "modules/mem/memberCert";
	}

	@RequiresPermissions("mem:member:edit")
	@RequestMapping(value = "doCert")
	public String doCert(Member member, RedirectAttributes redirectAttributes){
		memberService.updateCert(member);
		addMessage(redirectAttributes, "更新成功");
		return "redirect:"+Global.getAdminPath()+"/mem/member/?repage";
	}

	@RequiresPermissions("mem:member:edit")
	@RequestMapping(value = "toRegist")
	public String toRegist(Member member, Model model) {
		model.addAttribute("member", member);
		return "modules/mem/memberRegist";
	}
	
	@RequiresPermissions("mem:member:edit")
	@RequestMapping(value = "saveMem")
	@ResponseBody
	public Map<String,Object> saveMem(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		Map<String,Object> result = new HashMap<String,Object>();
		JSONObject reqData=new JSONObject();
		reqData.put("name", request.getParameter("name"));
		reqData.put("shortName", request.getParameter("shortName"));
		reqData.put("contact", request.getParameter("contact"));
		reqData.put("mobilePhone", request.getParameter("mobilePhone"));
		reqData.put("certNbr", request.getParameter("certNbr"));
		reqData.put("busLicenceNbr", request.getParameter("busLicenceNbr"));
		reqData.put("email", request.getParameter("email"));
		reqData.put("officeId", request.getParameter("officeId"));
		reqData.put("remarks", request.getParameter("remarks"));
		reqData.put("tradeInfo", request.getParameter("tradeInfo"));
		JSONObject res=JSONObject.fromObject(HttpUtil.sendPostRequest(Global.getConfig("pospService")+"/api/memberInfo/toRegister", CommonUtil.createSecurityRequstData(reqData)));
		if("0000".equals(res.getString("returnCode"))){
			result.put("returnCode", "0000");
			result.put("returnMsg", "商户进件成功");
		}else{
			result.put("returnCode", "0001");
			result.put("returnMsg", res.getString("returnMsg"));
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "keyinfo")
	public Map<String,Object> keyinfo(Member member, Model model) {
		Map<String,Object> result = new HashMap<String,Object>();
		Office office =  officeService.get(member.getOffice().getId());
		if(office!=null){
			result.put("returnCode", "0000");
			result.put("privateKey", office.getPrivateKeyRsa());
			result.put("publicKey", office.getPublicKeyRsa());
		}
		return result;
	}
	
	@RequiresPermissions("mem:member:edit")
	@RequestMapping(value = "toEdit")
	public String toEdit(String id,Model model){
		Member member = memberService.get(id);
		model.addAttribute("member",member);
		
		MemberPayType memberPayType = new MemberPayType();
		memberPayType.setMemberId(id);
		List<MemberPayType> list = memberPayTypeService.findList(memberPayType);
		if(list!=null && list.size()>0){
			for(MemberPayType mp:list){
				mp.setTxnType(transPayType(mp.getPayType()));
			}
		}
		model.addAttribute("paytype",list);
		return "modules/mem/memberInfo";
	}
	
	private String transPayType(String txnType){
		Map<String,String> obj = new HashMap<String, String>();
		obj.put("WX","1");
		obj.put("ZFB","2");
		obj.put("QQ","3");
		obj.put("JD","5");
		obj.put("YL","8");
		obj.put("KJ","9");
		return obj.get(txnType);
	}
	
	@RequiresPermissions("mem:member:edit")
	@RequestMapping(value = "updateMem")
	@ResponseBody
	public Map<String,Object> updateMem(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		Map<String,Object> result = new HashMap<String,Object>();
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", request.getParameter("memberId"));
		reqData.put("name", request.getParameter("name"));
		reqData.put("shortName", request.getParameter("shortName"));
		reqData.put("contact", request.getParameter("contact"));
		reqData.put("mobilePhone", request.getParameter("mobilePhone"));
		reqData.put("certNbr", request.getParameter("certNbr"));
		reqData.put("busLicenceNbr", request.getParameter("busLicenceNbr"));
		reqData.put("email", request.getParameter("email"));
		reqData.put("remarks", request.getParameter("remarks"));
		reqData.put("tradeInfo", request.getParameter("tradeInfo"));
		JSONObject res=JSONObject.fromObject(HttpUtil.sendPostRequest(Global.getConfig("pospService")+"/api/memberInfo/updateMember", CommonUtil.createSecurityRequstData(reqData)));
		if("0000".equals(res.getString("returnCode"))){
			result.put("returnCode", "0000");
			result.put("returnMsg", "商户修改成功");
		}else{
			result.put("returnCode", "0001");
			result.put("returnMsg", res.getString("returnMsg"));
		}
		return result;
	}
	
	@RequiresPermissions("mem:member:view")
	@RequestMapping(value = "toDetail")
	public String toDetail(Member member, Model model) {
		Member member1 = memberService.get(member.getId());
		model.addAttribute("member",member1);
		
		MemberPayType memberPayType = new MemberPayType();
		memberPayType.setMemberId(member.getId());
		List<MemberPayType> list = memberPayTypeService.findList(memberPayType);
		if(list!=null && list.size()>0){
			for(MemberPayType mp:list){
				mp.setTxnType(transPayType(mp.getPayType()));
			}
		}
		model.addAttribute("paytype",list);
		return "modules/mem/memberDetail1";
	}
	
}