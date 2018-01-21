/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.trade.entity.DebitNote;
import com.thinkgem.jeesite.modules.trade.entity.TradeAnalysis;
import com.thinkgem.jeesite.modules.trade.service.DebitNoteService;
import com.thinkgem.jeesite.modules.trade.service.TradeAnalysisService;

/**
 * 交易明细查询Controller
 * @author chenjc
 * @version 2016-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/trade/analysis")
public class TradeAnalysisController extends BaseController {

	@Autowired
	private TradeAnalysisService tradeAnalysisService;
	
	@Autowired
	private OfficeService officeService;
	
	
	
	//@RequiresPermissions("trade:tradeDetail:view")
	@RequestMapping(value = {"routeMerchant"})
	public String routeMerchant(TradeAnalysis tradeAnalysis, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		
		String beginTime = tradeAnalysis.getBeginTime();
		if(StringUtils.isEmpty(beginTime)){
			beginTime = DateUtils.getDate("yyyy-MM-dd")+" 00:00:00";
		}
		
		String endTime = tradeAnalysis.getEndTime();
		if(StringUtils.isEmpty(endTime)){
			endTime = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		}
		tradeAnalysis.setBeginTime(beginTime);
		tradeAnalysis.setEndTime(endTime);
		
		Page<TradeAnalysis> page = tradeAnalysisService.routeMerchantAnalysisPage(new Page<TradeAnalysis>(request, response), tradeAnalysis);
		model.addAttribute("page", page);
		
		//List<TradeAnalysis> list = tradeAnalysisService.routeMerchantAnalysis(tradeAnalysis);
		//model.addAttribute("list", list);
		
		model.addAttribute("tradeAnalysis", tradeAnalysis);
		return "modules/trade/routeMerchantAnalysis";
	}
	
	@RequestMapping(value = {"route", ""})
	public String route(TradeAnalysis tradeAnalysis, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		
		String beginTime = tradeAnalysis.getBeginTime();
		if(StringUtils.isEmpty(beginTime)){
			beginTime = DateUtils.getDate("yyyy-MM-dd")+" 00:00:00";
		}
		
		String endTime = tradeAnalysis.getEndTime();
		if(StringUtils.isEmpty(endTime)){
			endTime = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		}
		tradeAnalysis.setBeginTime(beginTime);
		tradeAnalysis.setEndTime(endTime);
		List<TradeAnalysis> list = tradeAnalysisService.routeAnalysis(tradeAnalysis);
		
		model.addAttribute("list", list);
		
		model.addAttribute("tradeAnalysis", tradeAnalysis);
		return "modules/trade/routeAnalysis";
	}
	
	@RequestMapping(value = {"member"})
	public String member(TradeAnalysis tradeAnalysis, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();

		// 选择代理商
		if (StringUtils.isNotBlank(officeId)) {
			Office office2 = officeService.get(officeId);
			if (office2 != null) {
				office = office2;
			}
		}

		tradeAnalysis.setOffice(office);
		
		String beginTime = tradeAnalysis.getBeginTime();
		if(StringUtils.isEmpty(beginTime)){
			beginTime = DateUtils.getDate("yyyy-MM-dd")+" 00:00:00";
		}
		
		String endTime = tradeAnalysis.getEndTime();
		if(StringUtils.isEmpty(endTime)){
			endTime = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		}
		tradeAnalysis.setBeginTime(beginTime);
		tradeAnalysis.setEndTime(endTime);
		
		Page<TradeAnalysis> page = tradeAnalysisService.memberAnalysisPage(new Page<TradeAnalysis>(request, response), tradeAnalysis);
		model.addAttribute("page", page);
		
	//	List<TradeAnalysis> list = tradeAnalysisService.memberAnalysis(tradeAnalysis);
		
	//	model.addAttribute("list", list);
		
		model.addAttribute("tradeAnalysis", tradeAnalysis);
		return "modules/trade/memberAnalysis";
	}
}