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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.mem.entity.MemberBindAcc;
import com.thinkgem.jeesite.modules.mem.service.MemberBindAccService;
import com.thinkgem.jeesite.modules.mem.service.MemberService;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.Bank;
import com.thinkgem.jeesite.modules.sys.entity.BankSub;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.AreaService;
import com.thinkgem.jeesite.modules.sys.service.BankService;
import com.thinkgem.jeesite.modules.sys.service.BankSubService;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 商户绑卡流水表Controller
 * @author jjy
 * @version 2017-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/mem/memberBindAcc")
public class MemberBindAccController extends BaseController {

	@Autowired
	private MemberBindAccService memberBindAccService;

	@Autowired
	private OfficeService officeService;
	
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private BankSubService bankSubService;
	
	@Autowired
	private AreaService areaService;
	
	@ModelAttribute
	public MemberBindAcc get(@RequestParam(required=false) String id) {
		MemberBindAcc entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memberBindAccService.get(id);
		}
		if (entity == null){
			entity = new MemberBindAcc();
		}
		return entity;
	}
	
	@RequiresPermissions("mem:memberBindAcc:view")
	@RequestMapping(value = {"list", ""})
	public String list(MemberBindAcc memberBindAcc, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		Office office = UserUtils.getUser().getOffice();
		Office office2 = officeService.get(office.getId());
		if("3".equals(office2.getAgtType())){
			Member mem = new Member();
			mem.setOffice(office);
			List<Member> list = memberService.findListByOfficeId(mem);
			if(list!=null && list.size()>0){
				Member member = new Member();
				String memberId = list.get(0).getId();
				member.setId(memberId);
				if("087e0384a40544b382f7a9352920a534".equals(office.getId())){//测试机构写死商户
					memberId = "15";
					member.setId(memberId);
				}
				memberBindAcc.setMember(member);
				String bindBeginTime = memberBindAcc.getBindBeginTime();
				if(StringUtils.isNotEmpty(bindBeginTime)){
					memberBindAcc.setBindBeginTime(bindBeginTime+" 00:00:00");
				}
				String bindEndTime = memberBindAcc.getBindEndTime();
				if(StringUtils.isNotEmpty(bindEndTime)){
					memberBindAcc.setBindEndTime(bindEndTime+" 23:59:59");
				}
				Page<MemberBindAcc> page = memberBindAccService.findPage(new Page<MemberBindAcc>(request, response), memberBindAcc); 
				model.addAttribute("page", page);
				if(StringUtils.isNotEmpty(bindBeginTime)){
					memberBindAcc.setBindBeginTime(bindBeginTime);
				}
				if(StringUtils.isNotEmpty(bindEndTime)){
					memberBindAcc.setBindEndTime(bindEndTime);
				}
				model.addAttribute("memberBindAcc",memberBindAcc);
			}
		}else{
			model.addAttribute("message", "您没有绑卡权限");
			
		}
		
		return "modules/mem/memberBindAccList";
	}

	@RequiresPermissions("mem:memberBindAcc:view")
	@RequestMapping(value = "form")
	public String form(MemberBindAcc memberBindAcc, Model model) {
		List<Bank> bankList = bankService.findList(null);
		model.addAttribute("bankList", bankList);
		model.addAttribute("memberBindAcc", memberBindAcc);
		return "modules/mem/memberBindAccForm";
	}

	@RequiresPermissions("mem:memberBindAcc:view")
	@RequestMapping(value = "save")
	public String save(MemberBindAcc memberBindAcc, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, memberBindAcc)){
			return form(memberBindAcc, model);
		}
		Office office = UserUtils.getUser().getOffice();
		Office office2 = officeService.get(office.getId());
		if("3".equals(office2.getAgtType())){
			Member mem = new Member();
			mem.setOffice(office);
			List<Member> list = memberService.findListByOfficeId(mem);
			if(list!=null && list.size()>0){
				String memberId = list.get(0).getId();
				if("087e0384a40544b382f7a9352920a534".equals(office.getId())){//测试机构写死商户
					memberId = "15";
				}
				memberBindAcc.setMemberId(memberId);
				Bank bank = bankService.get(new Bank(memberBindAcc.getBankId()));
				if(bank!=null){
					memberBindAcc.setBankName(bank.getName());
					memberBindAcc.setBankCode(bank.getCode());
				}
				BankSub bankSub =  bankSubService.get(new BankSub(memberBindAcc.getSubName().split("_")[0]));
				if(bankSub!=null){
					memberBindAcc.setSubName(bankSub.getSubName());
				}
				Area area = areaService.get(new Area(memberBindAcc.getAreaId()));
				if(area != null){
					memberBindAcc.setCity(area.getName());
					Area area1 = areaService.get(new Area(area.getParentId()));
					if(area1 != null){
						memberBindAcc.setProvince(area1.getName());
					}
				}
				
			}
		}
		memberBindAccService.save(memberBindAcc);
		addMessage(redirectAttributes, "保存提现银行卡成功");
		return "redirect:"+Global.getAdminPath()+"/mem/memberBindAcc/?repage";
	}
	
	@RequiresPermissions("mem:memberBindAcc:view")
	@RequestMapping(value = "delete")
	public String delete(MemberBindAcc MemberBindAcc, RedirectAttributes redirectAttributes) {
		memberBindAccService.delete(MemberBindAcc);
		addMessage(redirectAttributes, "删除提现银行卡成功");
		return "redirect:"+Global.getAdminPath()+"/mem/memberBindAcc/?repage";
	}
	
	
	

}