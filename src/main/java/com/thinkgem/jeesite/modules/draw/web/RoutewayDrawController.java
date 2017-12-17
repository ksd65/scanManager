/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.draw.web;

import java.math.BigDecimal;
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
import com.thinkgem.jeesite.modules.draw.entity.RoutewayDraw;
import com.thinkgem.jeesite.modules.draw.service.RoutewayDrawService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 通道提现查询Controller
 * @author chenjc
 * @version 2016-12-21
 */
@Controller
@RequestMapping(value = "${adminPath}/draw/routewayDraw")
public class RoutewayDrawController extends BaseController {

	@Autowired
	private RoutewayDrawService routewayDrawService;
	
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute
	public RoutewayDraw get(@RequestParam(required=false) String id) {
		RoutewayDraw entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = routewayDrawService.get(id);
		}
		if (entity == null){
			entity = new RoutewayDraw();
		}
		return entity;
	}
	
	@RequiresPermissions("draw:routewayDraw:view")
	@RequestMapping(value = {"list", ""})
	public String list(RoutewayDraw routewayDraw, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();
		
		// 选择代理商
		if(StringUtils.isNotBlank(officeId)){
			Office office2 = officeService.get(officeId);
			if(office2 != null){
				office = office2;
			}
		}
		
		routewayDraw.setOffice(office);	
		
		//默认显示当天数据
		if(StringUtils.isEmpty(routewayDraw.getBeginTime())){
			//routewayDraw.setBeginTime(DateUtils.getDate("yyyyMMdd"));
		}
		
		if(StringUtils.isEmpty(routewayDraw.getEndTime())){
			//routewayDraw.setEndTime(DateUtils.getDate("yyyyMMdd"));
		}
		
		String applyBeginTime = routewayDraw.getApplyBeginTime();
		if(StringUtils.isNotEmpty(applyBeginTime)){
			routewayDraw.setApplyBeginTime(applyBeginTime+" 00:00:00");
		}else{
			routewayDraw.setApplyBeginTime(DateUtils.getDate("yyyy-MM-dd")+" 00:00:00");
		}
		String applyEndTime = routewayDraw.getApplyEndTime();
		if(StringUtils.isNotEmpty(applyEndTime)){
			routewayDraw.setApplyEndTime(applyEndTime+" 23:59:59");
		}else{
			routewayDraw.setApplyEndTime(DateUtils.getDate("yyyy-MM-dd")+" 23:59:59");
		}
		
		Page<RoutewayDraw> page = routewayDrawService.findPage(new Page<RoutewayDraw>(request, response), routewayDraw); 
		model.addAttribute("page", page);
		if(StringUtils.isNotEmpty(applyBeginTime)){
			routewayDraw.setApplyBeginTime(applyBeginTime);
		}else{
			routewayDraw.setApplyBeginTime(DateUtils.getDate("yyyy-MM-dd"));
		}
		if(StringUtils.isNotEmpty(applyEndTime)){
			routewayDraw.setApplyEndTime(applyEndTime);
		}else{
			routewayDraw.setApplyEndTime(DateUtils.getDate("yyyy-MM-dd"));
		}
		
		model.addAttribute("routewayDraw", routewayDraw);
		return "modules/draw/routewayDrawList";
	}

	@RequiresPermissions("draw:routewayDraw:view")
	@RequestMapping(value = "form")
	public String form(RoutewayDraw routewayDraw, Model model) {
		model.addAttribute("routewayDraw", routewayDraw);
		return "modules/draw/routewayDrawForm";
	}

	@RequiresPermissions("draw:routewayDraw:edit")
	@RequestMapping(value = "save")
	public String save(RoutewayDraw routewayDraw, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, routewayDraw)){
			return form(routewayDraw, model);
		}
		routewayDrawService.save(routewayDraw);
		addMessage(redirectAttributes, "保存通道提现明细成功");
		return "redirect:"+Global.getAdminPath()+"/draw/routewayDraw/?repage";
	}
	
	@RequiresPermissions("draw:routewayDraw:edit")
	@RequestMapping(value = "delete")
	public String delete(RoutewayDraw routewayDraw, RedirectAttributes redirectAttributes) {
		routewayDrawService.delete(routewayDraw);
		addMessage(redirectAttributes, "删除通道提现明细成功");
		return "redirect:"+Global.getAdminPath()+"/draw/routewayDraw/?repage";
	}
	
	/**
	 * 导出提现数据
	 * @param 
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("draw:routewayDraw:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(RoutewayDraw routewayDraw, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,String officeId) {
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
			
			routewayDraw.setOffice(office);	
			
            String fileName = "提现数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            //Page<RoutewayDraw> page = routewayDrawService.findPage(new Page<RoutewayDraw>(request, response, -1), routewayDraw);
            //Page<TradeDetail> page = tradeDetailService.findTradeDetail(new Page<TradeDetail>(request, response, -1), tradeDetail);
    		List<RoutewayDraw> list = routewayDrawService.findList(routewayDraw);
            new ExportExcel("提现数据", RoutewayDraw.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出提现数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/draw/routewayDraw/list?repage";
    }

	/**
	 * 统计查询数据
	 * @return
	 */
	@RequestMapping(value = "getSumData")
	@ResponseBody
	public Map<String, Object> getSumData(RoutewayDraw routewayDraw, HttpServletRequest request, HttpServletResponse response, Model model,String officeId){
		Map<String,Object> data = new HashMap<String, Object>();
		User user = UserUtils.getUser();
		Office office = user.getOffice();

		if(StringUtils.isNotBlank(officeId)){
			office = officeService.get(officeId);
		}
		routewayDraw.setOffice(office);

		if(StringUtils.isEmpty(routewayDraw.getBeginTime())){
			routewayDraw.setBeginTime(DateUtils.getDate("yyyyMMdd"));
		}

		if(StringUtils.isEmpty(routewayDraw.getEndTime())){
			routewayDraw.setEndTime(DateUtils.getDate("yyyyMMdd"));
		}

		BigDecimal sumMoney = new BigDecimal("0.00");
		int sumCount = 0;
		List<RoutewayDraw> list = routewayDrawService.findList(routewayDraw);
		if(list.size() > 0){
			for(RoutewayDraw t:list){
				sumCount++;
				if(!StringUtils.isEmpty(t.getDrawamount())){
					sumMoney = sumMoney.add(new BigDecimal(t.getDrawamount()));
				}
			}
		}

		data.put("sumCount", sumCount);
		data.put("sumMoney", sumMoney.doubleValue());
		return data;
	}
	
	@RequiresPermissions("draw:drawAudit:view")
	@RequestMapping(value = {"auditList"})
	public String auditList(RoutewayDraw routewayDraw, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();
		
		// 选择代理商
		if(StringUtils.isNotBlank(officeId)){
			Office office2 = officeService.get(officeId);
			if(office2 != null){
				office = office2;
			}
		}
		
		routewayDraw.setOffice(office);	
		
		//默认显示当天数据
		if(StringUtils.isEmpty(routewayDraw.getBeginTime())){
			//routewayDraw.setBeginTime(DateUtils.getDate("yyyyMMdd"));
		}
		
		if(StringUtils.isEmpty(routewayDraw.getEndTime())){
			//routewayDraw.setEndTime(DateUtils.getDate("yyyyMMdd"));
		}
		
		String applyBeginTime = routewayDraw.getApplyBeginTime();
		if(StringUtils.isNotEmpty(applyBeginTime)){
			routewayDraw.setApplyBeginTime(applyBeginTime+" 00:00:00");
		}else{
			routewayDraw.setApplyBeginTime(DateUtils.getDate("yyyy-MM-dd")+" 00:00:00");
		}
		String applyEndTime = routewayDraw.getApplyEndTime();
		if(StringUtils.isNotEmpty(applyEndTime)){
			routewayDraw.setApplyEndTime(applyEndTime+" 23:59:59");
		}else{
			routewayDraw.setApplyEndTime(DateUtils.getDate("yyyy-MM-dd")+" 23:59:59");
		}
		
		Page<RoutewayDraw> page = routewayDrawService.findPage(new Page<RoutewayDraw>(request, response), routewayDraw); 
		model.addAttribute("page", page);
		if(StringUtils.isNotEmpty(applyBeginTime)){
			routewayDraw.setApplyBeginTime(applyBeginTime);
		}else{
			routewayDraw.setApplyBeginTime(DateUtils.getDate("yyyy-MM-dd"));
		}
		if(StringUtils.isNotEmpty(applyEndTime)){
			routewayDraw.setApplyEndTime(applyEndTime);
		}else{
			routewayDraw.setApplyEndTime(DateUtils.getDate("yyyy-MM-dd"));
		}
		model.addAttribute("routewayDraw", routewayDraw);
		return "modules/draw/routewayDrawAuditList";
	}
	
	@RequestMapping(value = "audit")
	@ResponseBody
	public Map<String, Object> audit(HttpServletRequest request, HttpServletResponse response, Model model,String officeId){
		Map<String,Object> data = new HashMap<String, Object>();
		User user = UserUtils.getUser();
		Office office = user.getOffice();
		String id = request.getParameter("id");
		String auditResult = request.getParameter("auditResult");
		String auditRemark = request.getParameter("auditRemark");
		RoutewayDraw routewayDraw = new RoutewayDraw();
		routewayDraw.setId(id);
		routewayDraw.setAuditStatus(auditResult);
		routewayDraw.setRemarks(auditRemark);
		routewayDraw.setAuditBy(user.getId());
		int count = routewayDrawService.audit(routewayDraw);
		if(count > 0){
			if("2".equals(auditResult)){
				routewayDraw = new RoutewayDraw();
				routewayDraw.setId(id);
				RoutewayDraw draw = routewayDrawService.get(routewayDraw);
				JSONObject result = new JSONObject();
				JSONObject reqData=new JSONObject();
				reqData.put("drawId", id);
				reqData.put("memberId", draw.getMemberId());
				result=JSONObject.fromObject(HttpUtil.sendPostRequest(Global.getConfig("pospService")+"/api/bankPay/draw", CommonUtil.createSecurityRequstData(reqData)));
				if("0000".equals(result.getString("returnCode"))){
					data.put("result", "0");
				}else{
					data.put("result", "-1");
					data.put("msg", "提现失败："+result.getString("returnMsg"));
				}
			}
		}else{
			data.put("result", "-1");
			data.put("msg", "审核提交失败");
		}
		return data;
	}

}