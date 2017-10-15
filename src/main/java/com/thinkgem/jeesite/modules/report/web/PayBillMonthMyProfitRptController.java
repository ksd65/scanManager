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
import com.thinkgem.jeesite.modules.report.entity.PayBillMonthMyProfitRpt;
import com.thinkgem.jeesite.modules.report.entity.PayBillMonthProfitRpt;
import com.thinkgem.jeesite.modules.report.service.PayBillMonthMyProfitRptService;
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
@RequestMapping(value = "${adminPath}/report/payBillMonthMyProfitRpt")
public class PayBillMonthMyProfitRptController extends BaseController{
	
	@Autowired
	private PayBillMonthMyProfitRptService payBillMonthMyProfitRptService;
	
	@Autowired
	private OfficeService officeService;
	
	
	/**
	 * 我的分润
	 * 吴逢生 2017-05-25
	 * @param payBillMonthMyProfitRpt
	 * @param request
	 * @param response
	 * @param model
	 * @param officeId
	 * @param type
	 * @return
	 */
	@RequiresPermissions("report:payBillMonthMyProfitRpt:view")
	@RequestMapping(value = "list")
	public String myProfit(PayBillMonthMyProfitRpt payBillMonthMyProfitRpt,HttpServletRequest request, HttpServletResponse response, Model model,String officeId,String type){
		User user = UserUtils.getUser();
		Office office = user.getOffice();
		
		payBillMonthMyProfitRpt.setOffice(office);
		if(StringUtils.isBlank(payBillMonthMyProfitRpt.getYear())){
			payBillMonthMyProfitRpt.setYear(DateUtils.getYear());
		}
		
		if("admin".equals(user.getLoginName())){
			payBillMonthMyProfitRpt.setIsAdmin("1");
		}else{
			payBillMonthMyProfitRpt.setIsAdmin(null);
		}
		
		if("1".equals(type)){
			try {
				String month = payBillMonthMyProfitRpt.getYear();
				String fileName = month+"我的分润统计_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
				List<PayBillMonthMyProfitRpt> list = payBillMonthMyProfitRptService.findList(payBillMonthMyProfitRpt);
				new ExportExcel(month+"我的分润统计", PayBillMonthMyProfitRpt.class).setDataList(list).write(response, fileName).dispose();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			Page<PayBillMonthMyProfitRpt> p = new Page<PayBillMonthMyProfitRpt>(request, response);
			p.setOrderBy("a.month desc");
			Page<PayBillMonthMyProfitRpt> page = payBillMonthMyProfitRptService.findPage(p, payBillMonthMyProfitRpt);
			model.addAttribute("page",page);
		}
		return "modules/report/payBillMonthMyProfitRptList";
	}
}
