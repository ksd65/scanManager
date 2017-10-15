package com.thinkgem.jeesite.modules.report.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.report.entity.PayBillMonthMyProfitRpt;
import com.thinkgem.jeesite.modules.trade.entity.TradeDetail;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.report.entity.PayBillCurrentMonthProfitRpt;
import com.thinkgem.jeesite.modules.report.entity.PayBillMonthRpt;
import com.thinkgem.jeesite.modules.report.service.PayBillCurrentMonthProfitRptService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author WuFengSheng
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/report/payBillCurrentMonthProfitRpt")
public class BillCurrentMonthProfitRptController extends BaseController{
	
	@Autowired
	private PayBillCurrentMonthProfitRptService payBillCurrentMonthProfitRptService;
	
	@Autowired
	private OfficeService officeService;
	
	/**
	 * 
	 * @param payBillCurrentMonthProfitRpt
	 * @param request
	 * @param response
	 * @param model
	 * @param officeId
	 * @param month
	 * @param type
	 * @return
	 */
	@RequiresPermissions("report:payBillCurrentMonthProfitRpt:view")
	@RequestMapping(value = "list")
	public String list(PayBillCurrentMonthProfitRpt payBillCurrentMonthProfitRpt,
					   HttpServletRequest request,
					   HttpServletResponse response,
					   Model model,
					   String officeId,
					   String month,
					   String type){
		
		User user = UserUtils.getUser();
		Office office = user.getOffice();
		
		if(StringUtils.isNotBlank(officeId)){
			office = officeService.get(officeId);
		}
		payBillCurrentMonthProfitRpt.setOffice(office);
		
		if(StringUtils.isEmpty(payBillCurrentMonthProfitRpt.getBeginTime())){
			payBillCurrentMonthProfitRpt.setBeginTime(DateUtils.getDate("yyyyMMdd"));
		}
		
		if(StringUtils.isEmpty(payBillCurrentMonthProfitRpt.getEndTime())){
			payBillCurrentMonthProfitRpt.setEndTime(DateUtils.getDate("yyyyMMdd"));
		}

		if("1".equals(type)){
			try {
				String fileName = "当月分润统计_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
				List<PayBillCurrentMonthProfitRpt> list = payBillCurrentMonthProfitRptService.findList(payBillCurrentMonthProfitRpt);
				new ExportExcel("当月分润统计", PayBillCurrentMonthProfitRpt.class).setDataList(list).write(response, fileName).dispose();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {

			Page<PayBillCurrentMonthProfitRpt> pageRequest = new Page<PayBillCurrentMonthProfitRpt>(request, response);
			pageRequest.setOrderBy("t.settle_date desc");
			Page<PayBillCurrentMonthProfitRpt> page =
					payBillCurrentMonthProfitRptService.findPage(pageRequest, payBillCurrentMonthProfitRpt);
			model.addAttribute("page",page);

			model.addAttribute("payBillCurrentMonthProfitRpt",payBillCurrentMonthProfitRpt);
		}

		return "modules/report/payBillCurrentMonthProfitRptList";
	}


	/**
	 * 统计查询数据
	 * @return
	 */
	@RequestMapping(value = "getSumData")
	@ResponseBody
	public Map<String, Object> getSumData(PayBillCurrentMonthProfitRpt payBillCurrentMonthProfitRpt,HttpServletRequest request, HttpServletResponse response, Model model,String officeId){
		Map<String,Object> data = new HashMap<String, Object>();
		User user = UserUtils.getUser();
		Office office = user.getOffice();

		if(StringUtils.isNotBlank(officeId)){
			office = officeService.get(officeId);
		}
		payBillCurrentMonthProfitRpt.setOffice(office);

		if(StringUtils.isEmpty(payBillCurrentMonthProfitRpt.getBeginTime())){
			payBillCurrentMonthProfitRpt.setBeginTime(DateUtils.getDate("yyyyMMdd"));
		}

		if(StringUtils.isEmpty(payBillCurrentMonthProfitRpt.getEndTime())){
			payBillCurrentMonthProfitRpt.setEndTime(DateUtils.getDate("yyyyMMdd"));
		}

		BigDecimal sumMoney = new BigDecimal("0.00");
		BigDecimal sumProfit = new BigDecimal("0.00");
		List<PayBillCurrentMonthProfitRpt> list = payBillCurrentMonthProfitRptService.findList(payBillCurrentMonthProfitRpt);
		if(list.size() > 0){
			for(PayBillCurrentMonthProfitRpt t:list){
				if(!StringUtils.isEmpty(t.getProfit())){
					sumProfit = sumProfit.add(new BigDecimal(t.getProfit()));
					sumMoney = sumMoney.add(new BigDecimal(t.getAmount()));
				}
			}
		}

		data.put("sumProfit", sumProfit.doubleValue());
		data.put("sumMoney", sumMoney.doubleValue());
		return data;
	}
}
