/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bill.web;

import java.math.BigDecimal;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.bill.entity.MsPayBill;
import com.thinkgem.jeesite.modules.bill.service.MsPayBillService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.trade.entity.TradeDetail;

/**
 * 交易对账查询Controller
 * @author chenjc
 * @version 2016-12-23
 */
@Controller
@RequestMapping(value = "${adminPath}/bill/msPayBill")
public class MsPayBillController extends BaseController {

	@Autowired
	private MsPayBillService msPayBillService;
	
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute
	public MsPayBill get(@RequestParam(required=false) String id) {
		MsPayBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = msPayBillService.get(id);
		}
		if (entity == null){
			entity = new MsPayBill();
		}
		return entity;
	}
	
	/**
	 * 统计查询数据
	 * @return
	 */
	@RequestMapping(value = "getSumData")
	@ResponseBody
	public Map<String, Object> getSumData(String officeId,String memberName,String mobilePhone,String beginTime,String endTime,String smzfMsgId,String settleType){
		Map<String, Object> data = new HashMap<String, Object>();
		
		MsPayBill msPayBill = new MsPayBill();
		msPayBill.setMemberName(memberName);
		msPayBill.setMobilePhone(mobilePhone);
		msPayBill.setBeginTime(beginTime);
		msPayBill.setEndTime(endTime);
		msPayBill.setSmzfMsgId(smzfMsgId);
		msPayBill.setSettleType(settleType);
		
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();
		// 选择代理商
		if(StringUtils.isNotBlank(officeId)){
			Office office2 = officeService.get(officeId);
			if(office2 != null){
				office = office2;
			}
		}
		msPayBill.setOffice(office);		
		
		int sumCount = 0;
		BigDecimal b_sumMoney = new BigDecimal("0.00");
		List<MsPayBill> list = msPayBillService.findList(msPayBill);
		if(list.size() > 0){
			for(MsPayBill t:list){
				sumCount++;
				b_sumMoney = b_sumMoney.add(new BigDecimal(t.getAmount()));
			}
		}
		
		data.put("sumCount", sumCount);
		data.put("sumMoney", b_sumMoney.doubleValue());
		return data;
	}
	
	@RequiresPermissions("bill:msPayBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(MsPayBill msPayBill, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		if(StringUtils.isNotBlank(officeId)){
			Office office = officeService.get(officeId);
			msPayBill.setOffice(office);
		}
		
		//默认显示前一天数据
		if(StringUtils.isEmpty(msPayBill.getBeginTime())){
			msPayBill.setBeginTime(DateUtils.formatDate(DateUtils.addDays(new Date(), -1), "yyyyMMdd"));
		}
		
		if(StringUtils.isEmpty(msPayBill.getEndTime())){
			msPayBill.setEndTime(DateUtils.formatDate(DateUtils.addDays(new Date(), -1), "yyyyMMdd"));
		}

		Page<MsPayBill> page = msPayBillService.findPage(new Page<MsPayBill>(request, response), msPayBill); 
		model.addAttribute("msPayBill",msPayBill);
		model.addAttribute("page", page);
		return "modules/bill/msPayBillList";
	}

	@RequiresPermissions("bill:msPayBill:view")
	@RequestMapping(value = "form")
	public String form(MsPayBill msPayBill, Model model) {
		model.addAttribute("msPayBill", msPayBill);
		return "modules/bill/msPayBillForm";
	}

	@RequiresPermissions("bill:msPayBill:edit")
	@RequestMapping(value = "save")
	public String save(MsPayBill msPayBill, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, msPayBill)){
			return form(msPayBill, model);
		}
		msPayBillService.save(msPayBill);
		addMessage(redirectAttributes, "保存交易对账成功");
		return "redirect:"+Global.getAdminPath()+"/bill/msPayBill/?repage";
	}
	
	@RequiresPermissions("bill:msPayBill:edit")
	@RequestMapping(value = "delete")
	public String delete(MsPayBill msPayBill, RedirectAttributes redirectAttributes) {
		msPayBillService.delete(msPayBill);
		addMessage(redirectAttributes, "删除交易对账成功");
		return "redirect:"+Global.getAdminPath()+"/bill/msPayBill/?repage";
	}
	
	/**
	 * 导出交易对账数据
	 * @param 
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("bill:msPayBill:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(MsPayBill msPayBill, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,String officeId) {
		try {
			if(StringUtils.isNotBlank(officeId)){
				Office office = officeService.get(officeId);
				msPayBill.setOffice(office);
			}
            String fileName = "交易对账数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
//           Page<MsPayBill> page = msPayBillService.findPage(new Page<MsPayBill>(request, response, -1), msPayBill); 
    		List<MsPayBill> list = msPayBillService.findList(msPayBill);
            new ExportExcel("交易对账数据", MsPayBill.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出交易对账数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/bill/msPayBill/list?repage";
    }

}