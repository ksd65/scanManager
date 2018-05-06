/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.web;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.trade.entity.RoutewayDrawProfit;
import com.thinkgem.jeesite.modules.trade.entity.TradeDetail;
import com.thinkgem.jeesite.modules.trade.entity.TradeProfit;
import com.thinkgem.jeesite.modules.trade.service.TradeProfitService;

/**
 * 交易明细查询Controller
 * @author chenjc
 * @version 2016-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/trade/profit")
public class TradeProfitController extends BaseController {

	@Autowired
	private TradeProfitService tradeProfitService;
	
	@Autowired
	private OfficeService officeService;
	
	
	
	
	@RequiresPermissions("trade:profit:plat")
	@RequestMapping(value = {"plat"})
	public String plat(TradeProfit tradeProfit, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();

		// 选择代理商
		if (StringUtils.isNotBlank(officeId)) {
			Office office2 = officeService.get(officeId);
			if (office2 != null) {
				office = office2;
			}
		}

		tradeProfit.setOffice(office);
		
		String beginTime = tradeProfit.getBeginTime();
		if(StringUtils.isEmpty(beginTime)){
			beginTime = DateUtils.getBeforeDate(8,"yyyyMMdd");
		}
		
		String endTime = tradeProfit.getEndTime();
		if(StringUtils.isEmpty(endTime)){
			endTime = DateUtils.getBeforeDate(1,"yyyyMMdd");
		}
		tradeProfit.setBeginTime(beginTime);
		tradeProfit.setEndTime(endTime);
		
		Page<TradeProfit> page = tradeProfitService.platProfitPage(new Page<TradeProfit>(request, response), tradeProfit);
		model.addAttribute("page", page);
		
		model.addAttribute("tradeProfit", tradeProfit);
		return "modules/trade/profit/platProfit";
	}
	
	@RequiresPermissions("trade:profit:plat")
	@RequestMapping(value = "getPlatSumData")
	@ResponseBody
	public Map<String, Object> getPlatSumData(TradeProfit tradeProfit,String officeId){
		Map<String, Object> data = new HashMap<String, Object>();
		
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();
		// 选择代理商
		if(StringUtils.isNotBlank(officeId)){
			Office office2 = officeService.get(officeId);
			if(office2 != null){
				office = office2;
			}
		}
		tradeProfit.setOffice(office);		
		
		Map<String,Object> map = tradeProfitService.platProfitSum(tradeProfit);
		Double tradeMoney = null,settleMoney = null,platProfit = null , agentProfit = null , platCost = null , memberCost = null;
		if(map!=null){
			tradeMoney = ((BigDecimal)map.get("tradeMoney")).doubleValue();
			settleMoney = ((BigDecimal)map.get("settleMoney")).doubleValue();
			platProfit = ((BigDecimal)map.get("platProfit")).doubleValue();
			agentProfit = ((BigDecimal)map.get("agentProfit")).doubleValue();
			platCost = ((BigDecimal)map.get("platCost")).doubleValue();
			memberCost = ((BigDecimal)map.get("memberCost")).doubleValue();
		}
		tradeMoney = tradeMoney ==null ? 0:tradeMoney;
		settleMoney = settleMoney ==null ? 0:settleMoney;
		platProfit = platProfit ==null ? 0:platProfit;
		agentProfit = agentProfit ==null ? 0:agentProfit;
		platCost = platCost ==null ? 0:platCost;
		memberCost = memberCost ==null ? 0:memberCost;
		DecimalFormat df = new DecimalFormat("0.00");
	
		data.put("tradeMoney", df.format(tradeMoney.doubleValue()));
		data.put("settleMoney", df.format(settleMoney.doubleValue()));
		data.put("platProfit", df.format(platProfit.doubleValue()));
		data.put("agentProfit", df.format(agentProfit.doubleValue()));
		data.put("platCost", df.format(platCost.doubleValue()));
		data.put("memberCost", df.format(memberCost.doubleValue()));
		return data;
	}
	
	@RequiresPermissions("trade:profit:agent")
	@RequestMapping(value = {"agentMember"})
	public String agentMember(TradeProfit tradeProfit, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();

		tradeProfit.setOffice(office);
		
		boolean dataFlag = false;
		List<Role> roleList = UserUtils.getUser().getRoleList();
		if(roleList!=null&&roleList.size()>0){
			for(Role role:roleList){
				if("1".equals(role.getDataScope())){//查看所有数据
					dataFlag = true;
					break;
				}
			}
		}
		if(!dataFlag){
			tradeProfit.setAgentOfficeId(office.getId());
		}
		String beginTime = tradeProfit.getBeginTime();
		if(StringUtils.isEmpty(beginTime)){
			beginTime = DateUtils.getBeforeDate(8,"yyyyMMdd");
		}
		
		String endTime = tradeProfit.getEndTime();
		if(StringUtils.isEmpty(endTime)){
			endTime = DateUtils.getBeforeDate(1,"yyyyMMdd");
		}
		tradeProfit.setBeginTime(beginTime);
		tradeProfit.setEndTime(endTime);
		
		Page<TradeProfit> page = tradeProfitService.agentMemberProfitPage(new Page<TradeProfit>(request, response), tradeProfit);
		model.addAttribute("page", page);
		
		model.addAttribute("tradeProfit", tradeProfit);
		return "modules/trade/profit/agentMemberProfit";
	}
	
	@RequiresPermissions("trade:profit:agent")
	@RequestMapping(value = "getAgentSumData")
	@ResponseBody
	public Map<String, Object> getAgentSumData(TradeProfit tradeProfit,String officeId){
		Map<String, Object> data = new HashMap<String, Object>();
		
		Office office = UserUtils.getUser().getOffice();
		boolean dataFlag = false;
		List<Role> roleList = UserUtils.getUser().getRoleList();
		if(roleList!=null&&roleList.size()>0){
			for(Role role:roleList){
				if("1".equals(role.getDataScope())){//查看所有数据
					dataFlag = true;
					break;
				}
			}
		}
		if(!dataFlag){
			tradeProfit.setAgentOfficeId(office.getId());
		}		
		
		Map<String,Object> map = tradeProfitService.agentProfitSum(tradeProfit);
		Double tradeMoney = null,agentProfit = null , agentCost = null ;
		if(map!=null){
			tradeMoney = ((BigDecimal)map.get("tradeMoney")).doubleValue();
			agentCost = ((BigDecimal)map.get("agentCost")).doubleValue();
			agentProfit = ((BigDecimal)map.get("agentProfit")).doubleValue();
		}
		tradeMoney = tradeMoney ==null ? 0:tradeMoney;
		agentProfit = agentProfit ==null ? 0:agentProfit;
		agentCost = agentCost ==null ? 0:agentCost;
		DecimalFormat df = new DecimalFormat("0.00");
	
		data.put("tradeMoney", df.format(tradeMoney.doubleValue()));
		data.put("agentProfit", df.format(agentProfit.doubleValue()));
		data.put("agentCost", df.format(agentCost.doubleValue()));
		return data;
	}
	
	@RequiresPermissions("trade:profit:agent")
	@RequestMapping(value = {"agent"})
	public String agent(TradeProfit tradeProfit, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();

		tradeProfit.setOffice(office);
		
		boolean dataFlag = false;
		List<Role> roleList = UserUtils.getUser().getRoleList();
		if(roleList!=null&&roleList.size()>0){
			for(Role role:roleList){
				if("1".equals(role.getDataScope())){//查看所有数据
					dataFlag = true;
					break;
				}
			}
		}
		if(!dataFlag){
			tradeProfit.setAgentOfficeId(office.getId());
		}
		String beginTime = tradeProfit.getBeginTime();
		if(StringUtils.isEmpty(beginTime)){
			beginTime = DateUtils.getBeforeDate(8,"yyyyMMdd");
		}
		
		String endTime = tradeProfit.getEndTime();
		if(StringUtils.isEmpty(endTime)){
			endTime = DateUtils.getBeforeDate(1,"yyyyMMdd");
		}
		tradeProfit.setBeginTime(beginTime);
		tradeProfit.setEndTime(endTime);
		
		Page<TradeProfit> page = tradeProfitService.agentProfitPage(new Page<TradeProfit>(request, response), tradeProfit);
		model.addAttribute("page", page);
		
		model.addAttribute("tradeProfit", tradeProfit);
		return "modules/trade/profit/agentProfit";
	}
	
	@RequiresPermissions("trade:profit:plat")
	@RequestMapping(value = {"platDraw"})
	public String platDraw(RoutewayDrawProfit routewayDrawProfit, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();

		// 选择代理商
		if (StringUtils.isNotBlank(officeId)) {
			Office office2 = officeService.get(officeId);
			if (office2 != null) {
				office = office2;
			}
		}

		routewayDrawProfit.setOffice(office);
		
		String beginTime = routewayDrawProfit.getBeginTime();
		if(StringUtils.isEmpty(beginTime)){
			beginTime = DateUtils.getBeforeDate(8,"yyyyMMdd");
		}
		
		String endTime = routewayDrawProfit.getEndTime();
		if(StringUtils.isEmpty(endTime)){
			endTime = DateUtils.getBeforeDate(1,"yyyyMMdd");
		}
		routewayDrawProfit.setBeginTime(beginTime);
		routewayDrawProfit.setEndTime(endTime);
		
		Page<RoutewayDrawProfit> page = tradeProfitService.platDrawProfitPage(new Page<RoutewayDrawProfit>(request, response), routewayDrawProfit);
		model.addAttribute("page", page);
		
		model.addAttribute("routewayDrawProfit", routewayDrawProfit);
		return "modules/trade/profit/platDrawProfit";
	}
	
	@RequiresPermissions("trade:profit:plat")
	@RequestMapping(value = "getPlatDrawSumData")
	@ResponseBody
	public Map<String, Object> getPlatDrawSumData(RoutewayDrawProfit routewayDrawProfit,String officeId){
		Map<String, Object> data = new HashMap<String, Object>();
		
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();
		// 选择代理商
		if(StringUtils.isNotBlank(officeId)){
			Office office2 = officeService.get(officeId);
			if(office2 != null){
				office = office2;
			}
		}
		routewayDrawProfit.setOffice(office);		
		
		Map<String,Object> map = tradeProfitService.platDrawProfitSum(routewayDrawProfit);
		Double money = null,profit = null;
		if(map!=null){
			money = ((BigDecimal)map.get("money")).doubleValue();
			profit = ((BigDecimal)map.get("profit")).doubleValue();
		}
		money = money ==null ? 0:money;
		profit = profit ==null ? 0:profit;
		DecimalFormat df = new DecimalFormat("0.00");
	
		data.put("money", df.format(money.doubleValue()));
		data.put("profit", df.format(profit.doubleValue()));
		return data;
	}
}