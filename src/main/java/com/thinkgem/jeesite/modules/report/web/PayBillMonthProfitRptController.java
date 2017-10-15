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
import com.thinkgem.jeesite.modules.report.entity.PayBillMonthDetailRpt;
import com.thinkgem.jeesite.modules.report.entity.PayBillMonthProfitRpt;
import com.thinkgem.jeesite.modules.report.entity.PayBillMonthRpt;
import com.thinkgem.jeesite.modules.report.service.PayBillMonthProfitRptService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 
 * @author WuFengSheng
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/report/payBillMonthProfitRpt")
public class PayBillMonthProfitRptController extends BaseController{
	
	@Autowired
	private PayBillMonthProfitRptService payBillMonthProfitRptService;
	
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
	@RequiresPermissions("report:payBillMonthProfitRpt:view")
	@RequestMapping(value = "list")
	public String list(PayBillMonthProfitRpt payBillMonthProfitRpt,HttpServletRequest request, HttpServletResponse response, Model model,String officeId,String type){
		User user = UserUtils.getUser();
		Office office = user.getOffice();
		
		if(StringUtils.isBlank(payBillMonthProfitRpt.getMonth())){
			payBillMonthProfitRpt.setMonth(DateUtils.getYear()+DateUtils.getLastMonth());
		}

		if("1".equals(user.getOffice().getId())){
			payBillMonthProfitRpt.setIsAdmin("1");
		}else{
			payBillMonthProfitRpt.setIsAdmin(null);
		}
		
		if(StringUtils.isNotBlank(officeId)){
			office = officeService.get(officeId);
			
		}
		
		payBillMonthProfitRpt.setOffice(office);
		
		if("1".equals(type)){
			try {
				String month = payBillMonthProfitRpt.getMonth();
				String fileName = month+"分润统计_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
				List<PayBillMonthProfitRpt> list = payBillMonthProfitRptService.findList(payBillMonthProfitRpt);
				new ExportExcel(month+"分润统计", PayBillMonthProfitRpt.class).setDataList(list).write(response, fileName).dispose();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			Page<PayBillMonthProfitRpt> page = payBillMonthProfitRptService.findPage(new Page<PayBillMonthProfitRpt>(request, response), payBillMonthProfitRpt);
			model.addAttribute("page",page);
		}
		
		model.addAttribute("payBillMonthProfitRpt",payBillMonthProfitRpt);
		return "modules/report/payBillMonthProfitRptList";
	}
	
	/**
	 * 
	 * @param payBillMonthProfitRpt
	 * @param request
	 * @param response
	 * @param model
	 * @param officeId
	 * @param type
	 * @return
	 */
	@RequiresPermissions("report:payBillMonthProfitRpt:view")
	@RequestMapping(value = "profitDetail")
	public String profitDetail(PayBillMonthDetailRpt payBillMonthDetailRpt,HttpServletRequest request, HttpServletResponse response, Model model,String officeId,String month,String type){
		
		if(StringUtils.isNotBlank(month) && StringUtils.isNotBlank(officeId)){
			Office office = officeService.get(officeId);
			payBillMonthDetailRpt.setOffice(office);
			payBillMonthDetailRpt.setMonth(month);
			
			if("1".equals(type)){
				try {
					String fileName = month+"分润明细_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
					List<PayBillMonthDetailRpt> list = payBillMonthProfitRptService.findProfitDetailList(payBillMonthDetailRpt);
					new ExportExcel(month+"分润明细", PayBillMonthDetailRpt.class).setDataList(list).write(response, fileName).dispose();
					return null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				Page<PayBillMonthDetailRpt> page = payBillMonthProfitRptService.findProfitDetailPage(new Page<PayBillMonthDetailRpt>(request, response),payBillMonthDetailRpt);
				model.addAttribute("page",page);
			}
		}
		
		model.addAttribute("payBillMonthDetailRpt",payBillMonthDetailRpt);
		
		return "modules/report/payBillMonthDetailList";
	}
}
