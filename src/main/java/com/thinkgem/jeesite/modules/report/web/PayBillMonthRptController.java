package com.thinkgem.jeesite.modules.report.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.report.entity.PayBillMonthRpt;
import com.thinkgem.jeesite.modules.report.service.PayBillMonthRptService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.trade.entity.TradeDetail;


/**
 * 
 * @author WuFengSheng
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/report/payBillMonthRpt")
public class PayBillMonthRptController extends BaseController{
	
	@Autowired
	private PayBillMonthRptService payBillMonthRptService;
	
	@Autowired
	private OfficeService officeService;
	
	/**
	 * 
	 * @param payBillMonthRpt
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("report:payBillMonthRpt:view")
	@RequestMapping(value = "list")
	public String list(PayBillMonthRpt payBillMonthRpt,HttpServletRequest request, HttpServletResponse response, Model model,String officeId,String type){
		User user = UserUtils.getUser();
		Office office = user.getOffice();
		
		if(StringUtils.isBlank(payBillMonthRpt.getMonth())){
			payBillMonthRpt.setMonth(DateUtils.getYear()+DateUtils.getLastMonth());
		}
		
		if("admin".equals(user.getLoginName())){
			payBillMonthRpt.setIsAdmin("1");
		}else{
			payBillMonthRpt.setIsAdmin(null);
		}
		
		if(StringUtils.isNotBlank(officeId)){
			office = officeService.get(officeId);
			
		}
		
		payBillMonthRpt.setOffice(office);
		
		if("1".equals(type)){
			try {
				String month = payBillMonthRpt.getMonth();
				String fileName = month+"交易统计_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
				List<PayBillMonthRpt> list = payBillMonthRptService.findList(payBillMonthRpt);
				new ExportExcel(month+"交易统计", PayBillMonthRpt.class).setDataList(list).write(response, fileName).dispose();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			Page<PayBillMonthRpt> page = payBillMonthRptService.findPage(new Page<PayBillMonthRpt>(request, response), payBillMonthRpt);
			model.addAttribute("page",page);
		}
		
		model.addAttribute("payBillMonthRpt",payBillMonthRpt);
		return "modules/report/payBillMonthRptList";
	}
	
	
	
}
