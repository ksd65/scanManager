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

import net.sf.json.JSONObject;

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
import com.thinkgem.jeesite.common.utils.CommonUtil;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.HttpUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.trade.entity.TradeDetail;
import com.thinkgem.jeesite.modules.trade.service.TradeDetailService;

/**
 * 交易明细查询Controller
 * @author chenjc
 * @version 2016-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/trade/tradeDetail")
public class TradeDetailController extends BaseController {

	@Autowired
	private TradeDetailService tradeDetailService;
	
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute
	public TradeDetail get(@RequestParam(required=false) String id) {
		TradeDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tradeDetailService.get(id);
		}
		if (entity == null){
			entity = new TradeDetail();
		}
		return entity;
	}
	
	/**
	 * 统计查询数据
	 * @return
	 */
	@RequestMapping(value = "getSumData")
	@ResponseBody
	public Map<String, Object> getSumData(String officeId,String memberName,String mobilePhone,String beginTime,String endTime,String respType,String settleType,String txnType,String respBeginTime,String respEndTime){
		Map<String, Object> data = new HashMap<String, Object>();
		
		TradeDetail tradeDetail = new TradeDetail();
		//tradeDetail.setMemberCode(memberCode);
		tradeDetail.setMemberName(memberName);
		tradeDetail.setMobilePhone(mobilePhone);
		if(StringUtils.isNoneBlank(beginTime)){
			tradeDetail.setBeginTime(beginTime);
		}
		if(StringUtils.isNoneBlank(endTime)){
			tradeDetail.setEndTime(endTime);
		}
		if(StringUtils.isNoneBlank(respBeginTime)){
			tradeDetail.setRespBeginTime(DateUtils.formatDate(DateUtils.parseDate(respBeginTime), "yyyyMMddHHmmss"));
		}
		if(StringUtils.isNoneBlank(respEndTime)){
			tradeDetail.setRespEndTime(DateUtils.formatDate(DateUtils.parseDate(respEndTime), "yyyyMMddHHmmss"));;
		}
		
		
		tradeDetail.setRespType(respType);
		tradeDetail.setSettleType(settleType);
		tradeDetail.setTxnType(txnType);
		
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();
		// 选择代理商
		if(StringUtils.isNotBlank(officeId)){
			Office office2 = officeService.get(officeId);
			if(office2 != null){
				office = office2;
			}
		}
		tradeDetail.setOffice(office);		
		
		int sumCount = 0;
	//	BigDecimal b_sumMoney = new BigDecimal("0.00");
		sumCount = tradeDetailService.countRecord(tradeDetail);
		Double money = tradeDetailService.countSumMoney(tradeDetail);
		money = money ==null ? 0:money;
		DecimalFormat df = new DecimalFormat("#.00");
	/*	List<TradeDetail> list = tradeDetailService.findList(tradeDetail);
		if(list.size() > 0){
			for(TradeDetail t:list){
				sumCount++;
				b_sumMoney = b_sumMoney.add(new BigDecimal(t.getMoney()));
			}
		}*/
		
		data.put("sumCount", sumCount);
		data.put("sumMoney", df.format(money.doubleValue()));
		return data;
	}
	
	/**
	 * 统计历史查询数据
	 * @return
	 */
	@RequestMapping(value = "getSumDataHis")
	@ResponseBody
	public Map<String, Object> getSumDataHis(String officeId,String memberName,String mobilePhone,String beginTime,String endTime,String respType,String settleType,String txnType){
		Map<String, Object> data = new HashMap<String, Object>();
		
		TradeDetail tradeDetail = new TradeDetail();
		//tradeDetail.setMemberCode(memberCode);
		tradeDetail.setMemberName(memberName);
		tradeDetail.setMobilePhone(mobilePhone);
		tradeDetail.setBeginTime(beginTime);
		tradeDetail.setEndTime(endTime);
		tradeDetail.setRespType(respType);
		tradeDetail.setSettleType(settleType);
		tradeDetail.setTxnType(txnType);
		
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();
		// 选择代理商
		if(StringUtils.isNotBlank(officeId)){
			Office office2 = officeService.get(officeId);
			if(office2 != null){
				office = office2;
			}
		}
		tradeDetail.setOffice(office);	
		DecimalFormat df = new DecimalFormat("#.00");
		
		int sumCount = 0;
		//	BigDecimal b_sumMoney = new BigDecimal("0.00");
		sumCount = tradeDetailService.countRecordHis(tradeDetail);
		Double money = tradeDetailService.countSumMoneyHis(tradeDetail);
		money = money ==null ? 0:money;
		
		data.put("sumCount", sumCount);
		data.put("sumMoney", df.format(money.doubleValue()));
		return data;
	}
	
	@RequiresPermissions("trade:tradeDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(TradeDetail tradeDetail, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();

		// 选择代理商
		if (StringUtils.isNotBlank(officeId)) {
			Office office2 = officeService.get(officeId);
			if (office2 != null) {
				office = office2;
			}
		}

		tradeDetail.setOffice(office);
		// 第一次访问交易日期清空
		String first = request.getParameter("first");

		if ("1".equals(first)) {
			if ("his".equals(request.getParameter("his"))) {
				tradeDetail.setBeginTime(DateUtils.getBeforeDate(31, "yyyyMMdd"));
				tradeDetail.setEndTime(DateUtils.getBeforeDate(31, "yyyyMMdd"));
			}else{
				tradeDetail.setRespBeginTime(DateUtils.getDate("yyyy-MM-dd")+" 00:00:00");
				tradeDetail.setRespEndTime(DateUtils.getDate("yyyy-MM-dd")+" 23:59:59");
			}
		}
		String respBeginTime = "";
		String respEndTime = "";
		if (!"his".equals(request.getParameter("his"))) {
			respBeginTime = tradeDetail.getRespBeginTime();
			respEndTime = tradeDetail.getRespEndTime();
			tradeDetail.setRespBeginTime(DateUtils.formatDate(DateUtils.parseDate(respBeginTime), "yyyyMMddHHmmss"));
			tradeDetail.setRespEndTime(DateUtils.formatDate(DateUtils.parseDate(respEndTime), "yyyyMMddHHmmss"));;
		}
		//默认显示当天数据
		/*
		if(StringUtils.isEmpty(tradeDetail.getBeginTime())){
			tradeDetail.setBeginTime(DateUtils.getDate("yyyyMMdd"));
		}
		
		if(StringUtils.isEmpty(tradeDetail.getEndTime())){
			tradeDetail.setEndTime(DateUtils.getDate("yyyyMMdd"));
		}
		*/

		//是否查询历史数据
		String retPage="";
		Page<TradeDetail> page = null;
		if ("his".equals(request.getParameter("his"))) {
			model.addAttribute("his", "his");
			page = tradeDetailService.findHisPage(new Page<TradeDetail>(request, response), tradeDetail);
			retPage="modules/trade/tradeDetailHisList";
		} else {
			page = tradeDetailService.findPage(new Page<TradeDetail>(request, response), tradeDetail);
			retPage="modules/trade/tradeDetailList";
		}


		model.addAttribute("page", page);
		/*
		if("1".equals(first)){
			tradeDetail.setBeginTime("");
			tradeDetail.setEndTime("");
		}
		*/
		if (!"his".equals(request.getParameter("his"))) {
			tradeDetail.setRespBeginTime(respBeginTime);
			tradeDetail.setRespEndTime(respEndTime);
		}
		model.addAttribute("tradeDetail", tradeDetail);
		return retPage;
	}
	
	@RequiresPermissions("trade:tradeDetail:view")
	@RequestMapping(value = "form")
	public String form(TradeDetail tradeDetail, Model model) {
		model.addAttribute("tradeDetail", tradeDetail);
		return "modules/trade/tradeDetailForm";
	}

	@RequiresPermissions("trade:tradeDetail:edit")
	@RequestMapping(value = "save")
	public String save(TradeDetail tradeDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tradeDetail)){
			return form(tradeDetail, model);
		}
		tradeDetailService.save(tradeDetail);
		addMessage(redirectAttributes, "保存交易明细成功");
		return "redirect:"+Global.getAdminPath()+"/trade/tradeDetail/?repage";
	}
	
	@RequiresPermissions("trade:tradeDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(TradeDetail tradeDetail, RedirectAttributes redirectAttributes) {
		tradeDetailService.delete(tradeDetail);
		addMessage(redirectAttributes, "删除交易明细成功");
		return "redirect:"+Global.getAdminPath()+"/trade/tradeDetail/?repage";
	}
	
	/**
	 * 导出交易数据
	 * @param 
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("trade:tradeDetail:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TradeDetail tradeDetail, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,String officeId) {
		try {
			// 当前用户代理商
			Office office = UserUtils.getUser().getOffice();
			
			// 选择代理商
			if(StringUtils.isNotBlank(officeId)){
				Office office2 = officeService.get(officeId);
				if(office2 != null){
					office = office2;
				}
			}
			
			
			
			if(StringUtils.isNoneBlank(tradeDetail.getRespBeginTime())){
				tradeDetail.setRespBeginTime(DateUtils.formatDate(DateUtils.parseDate(tradeDetail.getRespBeginTime()), "yyyyMMddHHmmss"));
			}
			if(StringUtils.isNoneBlank(tradeDetail.getRespEndTime())){
				tradeDetail.setRespEndTime(DateUtils.formatDate(DateUtils.parseDate(tradeDetail.getRespEndTime()), "yyyyMMddHHmmss"));;
			}
			tradeDetail.setOffice(office);
			
            String fileName = "交易数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            //Page<TradeDetail> page = tradeDetailService.findPage(new Page<TradeDetail>(request, response, -1), tradeDetail);
//            Page<TradeDetail> page = tradeDetailService.findTradeDetail(new Page<TradeDetail>(request, response, -1), tradeDetail);
            
            List<TradeDetail> list = tradeDetailService.findList(tradeDetail);
    		new ExportExcel("交易数据", TradeDetail.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出交易数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/trade/tradeDetail/list?repage";
    }
	
	/**
	 * 导出交易数据
	 * @param 
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("trade:tradeDetail:view")
    @RequestMapping(value = "exportHis", method=RequestMethod.POST)
    public String exportFileHis(TradeDetail tradeDetail, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,String officeId) {
		try {
			// 当前用户代理商
			Office office = UserUtils.getUser().getOffice();
			
			// 选择代理商
			if(StringUtils.isNotBlank(officeId)){
				Office office2 = officeService.get(officeId);
				if(office2 != null){
					office = office2;
				}
			}
			
			tradeDetail.setOffice(office);
			
            String fileName = "交易数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            //Page<TradeDetail> page = tradeDetailService.findPage(new Page<TradeDetail>(request, response, -1), tradeDetail);
//            Page<TradeDetail> page = tradeDetailService.findTradeDetail(new Page<TradeDetail>(request, response, -1), tradeDetail);
            
            List<TradeDetail> list = tradeDetailService.findHisList(tradeDetail);
    		new ExportExcel("交易数据", TradeDetail.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出交易数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/trade/tradeDetail/list?repage&his=his";
    }
	
	/**
	 * 
	 * @param tradeDetail
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("trade:tradeDetail:view")
	@RequestMapping(value = "monthTradeMoney")
	public String monthTradeMoney(TradeDetail tradeDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		String endMonth = DateUtils.getYear()+DateUtils.getMonth();
		String beginMonth = DateUtils.getSubtract12Month(endMonth);
		tradeDetail.setBeginMonth(beginMonth);
		tradeDetail.setEndMonth(endMonth);
		
		List<TradeDetail> list = tradeDetailService.findMonthTradeMoney(tradeDetail); 
		model.addAttribute("list", list);
		String listMonth = "[";
		for(TradeDetail t :list){
			listMonth = listMonth + "'"+t.getTxMonth()+"',";
		}
		listMonth = listMonth.substring(0,listMonth.lastIndexOf(","))+"]";
		model.addAttribute("listMonth", listMonth);
		
		String wxData  = "[";
		String qqData  = "[";
		String zfbData = "[";
		String jdData  = "[";
		String bdData  = "[";
		
		String bounsQuickData = "[";
		String quickData = "[";
		String allData = "[";
		
		// 微信数据
		for(TradeDetail t :list){
			wxData += "'"+String.format("%#.2f", Float.parseFloat(t.getWxMoney())/10000)+"',";
		}
		wxData = wxData.substring(0,wxData.lastIndexOf(","))+"]";
		model.addAttribute("wxData", wxData);
		
		// qq数据
		for(TradeDetail t :list){
			qqData += "'"+String.format("%#.2f", Float.parseFloat(t.getQqMoney())/10000)+"',";
		}
		qqData = qqData.substring(0,qqData.lastIndexOf(","))+"]";
		model.addAttribute("qqData", qqData);		
		
		// 支付宝数据
		for(TradeDetail t :list){
			zfbData += "'"+String.format("%#.2f", Float.parseFloat(t.getZfbMoney())/10000)+"',";
		}
		zfbData = zfbData.substring(0,zfbData.lastIndexOf(","))+"]";
		model.addAttribute("zfbData", zfbData);
		
		// 快捷（有积分）
		for(TradeDetail t :list){
			bounsQuickData += "'"+String.format("%#.2f", Float.parseFloat(t.getBounsQuickMoney())/10000)+"',";
		}
		bounsQuickData = bounsQuickData.substring(0,bounsQuickData.lastIndexOf(","))+"]";
		model.addAttribute("bounsQuickData", bounsQuickData);
				
		// 快捷（无积分）
		for(TradeDetail t :list){
			quickData += "'"+String.format("%#.2f", Float.parseFloat(t.getQuickMoney())/10000)+"',";
		}
		quickData = quickData.substring(0,quickData.lastIndexOf(","))+"]";
		model.addAttribute("quickData", quickData);
		// 京东钱包
		for(TradeDetail t :list){
			jdData += "'"+String.format("%#.2f", Float.parseFloat(t.getJdMoney())/10000)+"',";
		}
		jdData = jdData.substring(0,jdData.lastIndexOf(","))+"]";
		model.addAttribute("jdData", jdData);
		
		// 百度钱包
		for(TradeDetail t :list){
			bdData += "'"+String.format("%#.2f", Float.parseFloat(t.getBdMoney())/10000)+"',";
		}
		bdData = bdData.substring(0,jdData.lastIndexOf(","))+"]";
		model.addAttribute("bdData", bdData);
		
		// 合计数据
		for(TradeDetail t :list){
			allData += "'"+String.format("%#.2f", Float.parseFloat(t.getAllMoney())/10000)+"',";
		}
		allData = allData.substring(0,allData.lastIndexOf(","))+"]";
		model.addAttribute("allData", allData);
		
		return "modules/trade/monthTradeMoneyList";
	}
	
	/**
	 * 快捷交易查询
	 * @param tradeDetail
	 * @param request
	 * @param response
	 * @param model
	 * @param officeId
	 * @return
	 */
	@RequiresPermissions("trade:tradeQuickDetail:view")
	@RequestMapping(value = {"quick/list", "/quick"})
	public String quickList(TradeDetail tradeDetail, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();
		
		// 选择代理商
		if(StringUtils.isNotBlank(officeId)){
			Office office2 = officeService.get(officeId);
			if(office2 != null){
				office = office2;
			}
		}
		
		tradeDetail.setOffice(office);
		
		//默认显示当天数据
		if(StringUtils.isEmpty(tradeDetail.getBeginTime())){
			tradeDetail.setBeginTime(DateUtils.getDate("yyyyMMdd"));
		}
		
		if(StringUtils.isEmpty(tradeDetail.getEndTime())){
			tradeDetail.setEndTime(DateUtils.getDate("yyyyMMdd"));
		}
		
		Page<TradeDetail> page = tradeDetailService.findTradeQuickPage(new Page<TradeDetail>(request, response), tradeDetail); 
		model.addAttribute("page", page);
		model.addAttribute("tradeDetail",tradeDetail);
		return "modules/trade/tradeQuickList";
	}
	
	/**
	 * 导出快捷交易数据
	 * @param 
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("trade:tradeQuickDetail:view")
    @RequestMapping(value = "quick/export", method=RequestMethod.POST)
    public String exportQuickFile(TradeDetail tradeDetail, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,String officeId) {
		try {
			// 当前用户代理商
			Office office = UserUtils.getUser().getOffice();
			
			// 选择代理商
			if(StringUtils.isNotBlank(officeId)){
				Office office2 = officeService.get(officeId);
				if(office2 != null){
					office = office2;
				}
			}
			
			tradeDetail.setOffice(office);
			
            String fileName = "交易数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            
            List<TradeDetail> list = tradeDetailService.findQuickList(tradeDetail);
    		new ExportExcel("交易数据", TradeDetail.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出交易数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/trade/tradeDetail/quick/list?repage";
    }
	
	/**
	 * 统计查询快捷交易数据
	 * @return
	 */
	@RequestMapping(value = "quick/getSumData")
	@ResponseBody
	public Map<String, Object> getQuickSumData(String officeId,String memberName,String mobilePhone,String beginTime,String endTime,String respType,String settleType,String txnType){
		Map<String, Object> data = new HashMap<String, Object>();
		
		TradeDetail tradeDetail = new TradeDetail();
		//tradeDetail.setMemberCode(memberCode);
		tradeDetail.setMemberName(memberName);
		tradeDetail.setMobilePhone(mobilePhone);
		tradeDetail.setBeginTime(beginTime);
		tradeDetail.setEndTime(endTime);
		tradeDetail.setRespType(respType);
		tradeDetail.setSettleType(settleType);
		tradeDetail.setTxnType(txnType);
		
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();
		// 选择代理商
		if(StringUtils.isNotBlank(officeId)){
			Office office2 = officeService.get(officeId);
			if(office2 != null){
				office = office2;
			}
		}
		tradeDetail.setOffice(office);		
		
		int sumCount = 0;
		BigDecimal b_sumMoney = new BigDecimal("0.00");
		List<TradeDetail> list = tradeDetailService.findQuickList(tradeDetail);
		if(list.size() > 0){
			for(TradeDetail t:list){
				sumCount++;
				b_sumMoney = b_sumMoney.add(new BigDecimal(t.getMoney()));
			}
		}
		
		data.put("sumCount", sumCount);
		data.put("sumMoney", b_sumMoney.doubleValue());
		return data;
	}
	//手动回调通知
	@RequestMapping(value = "callback")
	@ResponseBody
	public Map<String, Object> callback(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> data = new HashMap<String, Object>();
		String orderCode = request.getParameter("orderCode");
		if(StringUtils.isBlank(orderCode)){
			data.put("result", "-1");
			data.put("msg", "订单号为空 ");
			return data;
		}
		
		JSONObject result = new JSONObject();
		JSONObject reqData=new JSONObject();
		reqData.put("orderCode", orderCode);
		result=JSONObject.fromObject(HttpUtil.sendPostRequest(Global.getConfig("pospService")+"/api/cashierDesk/callBack", CommonUtil.createSecurityRequstData(reqData)));
		if(!"0000".equals(result.getString("returnCode"))){
			data.put("result", "-1");
			data.put("msg", "操作失败："+result.getString("returnMsg"));
			return data;
		}
		data.put("result", "0");
		return data;
	}
	
	
}