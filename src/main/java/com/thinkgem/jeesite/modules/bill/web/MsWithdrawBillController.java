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

import com.thinkgem.jeesite.modules.draw.entity.RoutewayDraw;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.bill.entity.MsPayBill;
import com.thinkgem.jeesite.modules.bill.entity.MsWithdrawBill;
import com.thinkgem.jeesite.modules.bill.service.MsWithdrawBillService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 提现对账查询Controller
 * @author chenjc
 * @version 2016-12-23
 */
@Controller
@RequestMapping(value = "${adminPath}/bill/msWithdrawBill")
public class MsWithdrawBillController extends BaseController {

	@Autowired
	private MsWithdrawBillService msWithdrawBillService;
	
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute
	public MsWithdrawBill get(@RequestParam(required=false) String id) {
		MsWithdrawBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = msWithdrawBillService.get(id);
		}
		if (entity == null){
			entity = new MsWithdrawBill();
		}
		return entity;
	}
	
	@RequiresPermissions("bill:msWithdrawBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(MsWithdrawBill msWithdrawBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		
		if(StringUtils.isNotBlank(msWithdrawBill.getOfficeId())){
			Office office = officeService.get(msWithdrawBill.getOfficeId());
			msWithdrawBill.setOffice(office);
		}else{
			msWithdrawBill.setOffice(user.getOffice());
		}
		
		//默认显示前一天数据
		if(StringUtils.isEmpty(msWithdrawBill.getBeginTime())){
			msWithdrawBill.setBeginTime(DateUtils.formatDate(DateUtils.addDays(new Date(), -1), "yyyyMMdd"));
		}
		
		if(StringUtils.isEmpty(msWithdrawBill.getEndTime())){
			msWithdrawBill.setEndTime(DateUtils.formatDate(DateUtils.addDays(new Date(), -1), "yyyyMMdd"));
		}

		
		Page<MsWithdrawBill> page = msWithdrawBillService.findPage(new Page<MsWithdrawBill>(request, response), msWithdrawBill); 
		model.addAttribute("msWithdrawBill", msWithdrawBill);
		model.addAttribute("page", page);
		return "modules/bill/msWithdrawBillList";
	}

	@RequiresPermissions("bill:msWithdrawBill:view")
	@RequestMapping(value = "form")
	public String form(MsWithdrawBill msWithdrawBill, Model model) {
		model.addAttribute("msWithdrawBill", msWithdrawBill);
		return "modules/bill/msWithdrawBillForm";
	}

	@RequiresPermissions("bill:msWithdrawBill:edit")
	@RequestMapping(value = "save")
	public String save(MsWithdrawBill msWithdrawBill, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, msWithdrawBill)){
			return form(msWithdrawBill, model);
		}
		msWithdrawBillService.save(msWithdrawBill);
		addMessage(redirectAttributes, "保存提现对账成功");
		return "redirect:"+Global.getAdminPath()+"/bill/msWithdrawBill/?repage";
	}
	
	@RequiresPermissions("bill:msWithdrawBill:edit")
	@RequestMapping(value = "delete")
	public String delete(MsWithdrawBill msWithdrawBill, RedirectAttributes redirectAttributes) {
		msWithdrawBillService.delete(msWithdrawBill);
		addMessage(redirectAttributes, "删除提现对账成功");
		return "redirect:"+Global.getAdminPath()+"/bill/msWithdrawBill/?repage";
	}
	
	/**
	 * 导出提现对账数据
	 * @param 
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("bill:msWithdrawBill:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(MsWithdrawBill msWithdrawBill, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			User user = UserUtils.getUser();
			
			if(StringUtils.isNotBlank(msWithdrawBill.getOfficeId())){
				Office office = officeService.get(msWithdrawBill.getOfficeId());
				msWithdrawBill.setOffice(office);
			}else{
				msWithdrawBill.setOffice(user.getOffice());
			}
			
            String fileName = "提现对账数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
//            Page<MsWithdrawBill> page = msWithdrawBillService.findPage(new Page<MsWithdrawBill>(request, response, -1), msWithdrawBill); 
    		List<MsWithdrawBill> list = msWithdrawBillService.findList(msWithdrawBill);
            new ExportExcel("提现对账数据", MsWithdrawBill.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出提现对账数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/bill/msWithdrawBill/list?repage";
    }

	/**
	 * 统计查询数据
	 * @return
	 */
	@RequestMapping(value = "getSumData")
	@ResponseBody
	public Map<String, Object> getSumData(MsWithdrawBill msWithdrawBill, HttpServletRequest request, HttpServletResponse response, Model model){
		Map<String,Object> data = new HashMap<String, Object>();
		User user = UserUtils.getUser();

		if(StringUtils.isNotBlank(msWithdrawBill.getOfficeId())){
			Office office = officeService.get(msWithdrawBill.getOfficeId());
			msWithdrawBill.setOffice(office);
		}else{
			msWithdrawBill.setOffice(user.getOffice());
		}

		if(StringUtils.isEmpty(msWithdrawBill.getBeginTime())){
			msWithdrawBill.setBeginTime(DateUtils.getDate("yyyyMMdd"));
		}

		if(StringUtils.isEmpty(msWithdrawBill.getEndTime())){
			msWithdrawBill.setEndTime(DateUtils.getDate("yyyyMMdd"));
		}

		BigDecimal sumMoney = new BigDecimal("0.00");
		int sumCount = 0;
		List<MsWithdrawBill> list = msWithdrawBillService.findList(msWithdrawBill);
		if(list.size() > 0){
			for(MsWithdrawBill t:list){
				sumCount++;
				if(!StringUtils.isEmpty(t.getDrawAmount())){
					sumMoney = sumMoney.add(new BigDecimal(t.getDrawAmount()));
				}
			}
		}

		data.put("sumCount", sumCount);
		data.put("sumMoney", sumMoney.doubleValue());
		return data;
	}

}