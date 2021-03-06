/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.draw.web;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.thinkgem.jeesite.modules.mem.entity.Member;
import com.thinkgem.jeesite.modules.mem.entity.MemberBindAcc;
import com.thinkgem.jeesite.modules.mem.entity.MemberDrawRoute;
import com.thinkgem.jeesite.modules.mem.entity.MemberPayType;
import com.thinkgem.jeesite.modules.mem.service.MemberBindAccService;
import com.thinkgem.jeesite.modules.mem.service.MemberDrawRouteService;
import com.thinkgem.jeesite.modules.mem.service.MemberPayTypeService;
import com.thinkgem.jeesite.modules.mem.service.MemberService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.trade.service.TradeDailyTotalService;

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
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberBindAccService memberBindAccService;
	
	@Autowired
	private TradeDailyTotalService tradeDailyTotalService;
	
	@Autowired
	private MemberDrawRouteService memberDrawRouteService;
	
	@Autowired
	private MemberPayTypeService memberPayTypeService;
	
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
		routewayDraw.setDrawType("1");//提现
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
			routewayDraw.setDrawType("2");//代付
			//默认显示当天数据
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
			
            String fileName = "代付数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            //Page<RoutewayDraw> page = routewayDrawService.findPage(new Page<RoutewayDraw>(request, response, -1), routewayDraw);
            //Page<TradeDetail> page = tradeDetailService.findTradeDetail(new Page<TradeDetail>(request, response, -1), tradeDetail);
    		List<RoutewayDraw> list = routewayDrawService.findList(routewayDraw);
            new ExportExcel("代付数据", RoutewayDraw.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出代付数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/draw/routewayDraw/agentPayList?repage";
    }

	/**
	 * 统计查询数据
	 * @return
	 */
	@RequestMapping(value = "getSumData")
	@ResponseBody
	public Map<String, Object> getSumData(RoutewayDraw routewayDraw, HttpServletRequest request, HttpServletResponse response, Model model,String officeId){
		Map<String,Object> data = new HashMap<String, Object>();
		
		Office office = UserUtils.getUser().getOffice();
		// 选择代理商
		if(StringUtils.isNotBlank(officeId)){
			Office office2 = officeService.get(officeId);
			if(office2 != null){
				office = office2;
			}
		}
		
		routewayDraw.setOffice(office);	
		routewayDraw.setDrawType("2");//代付
		routewayDraw.setRespType("S");//成功
		//默认显示当天数据
		if(StringUtils.isEmpty(routewayDraw.getBeginTime())){
			routewayDraw.setBeginTime(DateUtils.getDate("yyyyMMdd"));
		}
		
		if(StringUtils.isEmpty(routewayDraw.getEndTime())){
			routewayDraw.setEndTime(DateUtils.getDate("yyyyMMdd"));
		}
		
		int sumCount = 0;
		//	BigDecimal b_sumMoney = new BigDecimal("0.00");
		sumCount = routewayDrawService.countRecord(routewayDraw);
		Double money = routewayDrawService.countSumMoney(routewayDraw);
		money = money ==null ? 0:money;
		DecimalFormat df = new DecimalFormat("0.00");
		

		data.put("sumCount", sumCount);
		data.put("sumMoney", df.format(money.doubleValue()));
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
	//	routewayDraw.setDrawType("1");//提现
		routewayDraw.setNotRouteCode("1027");//排除易生
		routewayDraw.setListFlag("1");
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
		List<RoutewayDraw> list = page.getList();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				RoutewayDraw draw = list.get(i);
				if("1".equals(draw.getAuditStatus())){//待审核
					getMoney(draw);
				}
			}
		}
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
		String id = request.getParameter("id");
		String auditResult = request.getParameter("auditResult");
		String auditRemark = request.getParameter("auditRemark");
		RoutewayDraw drawT = routewayDrawService.get(id);
		if(drawT==null){
			data.put("result", "-1");
			data.put("msg", "提现记录不存在 ");
			return data;
		}
		if(!"1".equals(drawT.getAuditStatus())){
			data.put("result", "-1");
			data.put("msg", "提现记录已审核 ");
			return data;
		}
		String routeCode = drawT.getRouteCode();
		RoutewayDraw routewayDraw = new RoutewayDraw();
		routewayDraw.setId(id);
		routewayDraw.setAuditStatus(auditResult);
		routewayDraw.setRemarks(auditRemark);
		routewayDraw.setAuditBy(user.getId());
		if("2".equals(auditResult)&&"1014".equals(routeCode)){//环迅不调用接口
			routewayDraw.setRespType("S");
			routewayDraw.setReqDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			routewayDraw.setRespDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		}
		routewayDraw.setRouteCode(routeCode);
		routewayDraw.setMemberId(drawT.getMemberId());
		getMoney(routewayDraw);
		int count = routewayDrawService.audit(routewayDraw);
		if(count > 0){
			if("2".equals(auditResult)){
				if("1014".equals(routeCode)){
					data.put("result", "0");
				}else{
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
			}
		}else{
			data.put("result", "-1");
			data.put("msg", "审核提交失败");
		}
		return data;
	}
	
	@RequiresPermissions("draw:agentPayList:view")
	@RequestMapping(value = {"agentPayList"})
	public String agentPayList(RoutewayDraw routewayDraw, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
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
		routewayDraw.setDrawType("2");//代付
		//默认显示当天数据
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
		return "modules/draw/agentPayList";
	}
	
	
	@RequestMapping(value = "apply")
	public String apply(RoutewayDraw routewayDraw, Model model) {
		model.addAttribute("routewayDraw", routewayDraw);
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();
		Office office2 = officeService.get(office.getId());
		if("3".equals(office2.getAgtType())){
			Member mem = new Member();
			mem.setOffice(office);
			List<Member> list = memberService.findListByOfficeId(mem);
			if(list!=null && list.size()>0){
				Member member = new Member();
				String memberId = list.get(0).getId();
				member.setId(memberId);
				if("087e0384a40544b382f7a9352920a534".equals(office.getId())){//测试机构写死商户
					memberId = "1471";
					member.setId(memberId);
				}
				MemberBindAcc memberBindAcc = new MemberBindAcc();
				memberBindAcc.setMember(member);
				List<MemberBindAcc> accList = memberBindAccService.findList(memberBindAcc);
				model.addAttribute("accList", accList);
				
				/*JSONObject result = new JSONObject();
				JSONObject reqData=new JSONObject();
				reqData.put("routeCode", "1008");//先写死瑞付
				reqData.put("memberId", memberId);
				result=JSONObject.fromObject(HttpUtil.sendPostRequest(Global.getConfig("pospService")+"/api/memberInfo/draw", CommonUtil.createSecurityRequstData(reqData)));
				if("0000".equals(result.getString("returnCode"))){
					model.addAttribute("balanceAccount", result);
				}*/
			}
		}else{
			model.addAttribute("message", "您没有提现权限");
			
		}
		return "modules/draw/routewayDrawApply";
	}
	
	@ResponseBody
	@RequestMapping(value ="queryRouteBalance")
	public Map<String,Object> queryRouteBalance(Model model, HttpServletRequest request) {
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			
			Office office = UserUtils.getUser().getOffice();
			Office office2 = officeService.get(office.getId());
			if("3".equals(office2.getAgtType())){
				Member mem = new Member();
				mem.setOffice(office);
				List<Member> list = memberService.findListByOfficeId(mem);
				if(list!=null && list.size()>0){
					String memberId = list.get(0).getId();
					if("087e0384a40544b382f7a9352920a534".equals(office.getId())){//测试机构写死商户
						memberId = "1471";
					}
					
					String routeCode = request.getParameter("routeCode");
					if(StringUtils.isBlank(routeCode)){
						result.put("returnCode", "0004");
						result.put("returnMsg", "请先选择通道");
						return result;
					}
					
					JSONObject result1 = new JSONObject();
					JSONObject reqData=new JSONObject();
					reqData.put("routeCode", routeCode);
					reqData.put("memberId", memberId);
					result1=JSONObject.fromObject(HttpUtil.sendPostRequest(Global.getConfig("pospService")+"/api/memberInfo/draw", CommonUtil.createSecurityRequstData(reqData)));
					if("0000".equals(result1.getString("returnCode"))){
						result.put("returnCode", "0000");
						result.put("balanceAccount", result1);
					}else{
						result.put("returnCode", "0004");
						result.put("returnMsg", result1.getString("returnMsg"));
					}
				}
			}else{
				result.put("returnCode", "0004");
				result.put("returnMsg", "您没有提现权限");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			result.put("returnCode", "4004");
			result.put("returnMsg", "请求失败");
			return result;
		}
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value ="applySubmit")
	public Map<String,Object> applySubmit(Model model, HttpServletRequest request) {
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			String routeCode = request.getParameter("routeCode");
			if (routeCode == null || "".equals(routeCode)) {
				result.put("returnCode", "0001");
				result.put("returnMsg", "提现通道为空");
				return result;
			}
			
			String bindAccId = request.getParameter("bindAccId");
			if (bindAccId == null || "".equals(bindAccId)) {
				result.put("returnCode", "0001");
				result.put("returnMsg", "提现银行卡为空");
				return result;
			}
			String drawMoney = request.getParameter("drawMoney");
			if (drawMoney == null || "".equals(drawMoney)) {
				result.put("returnCode", "0001");
				result.put("returnMsg", "提现金额为空");
				return result;
			}
			String drawPwd = request.getParameter("drawPwd");
			if (drawPwd == null || "".equals(drawPwd)) {
				result.put("returnCode", "0001");
				result.put("returnMsg", "提现密码为空");
				return result;
			}
			User user = UserUtils.getUser();
			if (!SystemService.validatePassword(drawPwd, user.getDrawPwd())){
				result.put("returnCode", "0001");
				result.put("returnMsg", "提现密码错误");
				return result;
			}
			
			Office office = UserUtils.getUser().getOffice();
			Office office2 = officeService.get(office.getId());
			if("3".equals(office2.getAgtType())){
				Member mem = new Member();
				mem.setOffice(office);
				List<Member> list = memberService.findListByOfficeId(mem);
				if(list!=null && list.size()>0){
					String memberId = list.get(0).getId();
					if("087e0384a40544b382f7a9352920a534".equals(office.getId())){//测试机构写死商户
						memberId = "1471";
					}
					MemberBindAcc bindAcc = memberBindAccService.get(new MemberBindAcc(bindAccId));
					if(bindAcc==null){
						result.put("returnCode", "0001");
						result.put("returnMsg", "没有对应的提现银行卡");
						return result;
					}
					if(!memberId.equals(bindAcc.getMemberId())){
						result.put("returnCode", "0001");
						result.put("returnMsg", "提现银行卡非法");
						return result;
					}
					
					
					JSONObject res = new JSONObject();
					JSONObject reqData=new JSONObject();
					reqData.put("routeCode", routeCode);
					reqData.put("memberId", memberId);
					reqData.put("drawMoney", drawMoney);
					reqData.put("bankName", bindAcc.getBankName());
					reqData.put("subName", bindAcc.getSubName());
					reqData.put("subId", bindAcc.getSubId());
					reqData.put("bankAccount", bindAcc.getAcc());
					reqData.put("accountName", bindAcc.getName());
					reqData.put("bankCode", bindAcc.getBankCode());
					reqData.put("certNo", bindAcc.getCertNo());
					reqData.put("mobilePhone", bindAcc.getMobilePhone());
					reqData.put("province", bindAcc.getProvince());
					reqData.put("city", bindAcc.getCity());
					res=JSONObject.fromObject(HttpUtil.sendPostRequest(Global.getConfig("pospService")+"/api/memberInfo/drawCommit", CommonUtil.createSecurityRequstData(reqData)));
					if("0000".equals(res.getString("returnCode"))){
						result.put("returnCode", "0000");
						result.put("returnMsg", "提现申请提交成功");
					}else{
						result.put("returnCode", "0001");
						result.put("returnMsg", res.getString("returnMsg"));
					}
				}
			}else{
				result.put("returnCode", "0001");
				result.put("returnMsg", "您没有提现权限");
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			result.put("returnCode", "4004");
			result.put("returnMsg", "请求失败");
			return result;
		}
		return result;
	}
	
	private void getMoney(RoutewayDraw draw){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", draw.getMemberId());
		paramMap.put("routeCode", draw.getRouteCode());
		Double sumTradeMoney = tradeDailyTotalService.sumTradeMoney(paramMap);
		sumTradeMoney = sumTradeMoney == null ? 0 : sumTradeMoney;
		Double sumSettleMoney = tradeDailyTotalService.sumSettleMoney(paramMap);
		sumSettleMoney = sumSettleMoney == null ? 0 : sumSettleMoney;

		paramMap.put("txnDate", DateUtils.getDate("yyyyMMdd"));
		Double todayTradeMoney = tradeDailyTotalService.tradeMoney(paramMap);
		todayTradeMoney = todayTradeMoney == null ? 0 : todayTradeMoney;

		Double todaySettleMoney = 0d;
		if("1038".equals(draw.getRouteCode())){
			MemberPayType payTypeParam = new MemberPayType();
			payTypeParam.setMemberId(draw.getMemberId());
			payTypeParam.setPayMethod("005");
			payTypeParam.setPayType("KJ");
			List<MemberPayType> list = memberPayTypeService.findMemberPayType(payTypeParam);
			String memberRate = "1";
			if(list!=null&&list.size()>0){
				memberRate = list.get(0).getT0TradeRate();
			}
			paramMap.put("memberRate", memberRate);
			todaySettleMoney = tradeDailyTotalService.settleMemberProfitMoney(paramMap);
		}else{
			todaySettleMoney = tradeDailyTotalService.settleMoney(paramMap);
		}
		todaySettleMoney = todaySettleMoney == null ? 0 : todaySettleMoney;
		
		RoutewayDraw tmp2 = new RoutewayDraw();
		tmp2.setMemberId(draw.getMemberId());
		tmp2.setRouteCode(draw.getRouteCode());
		tmp2.setAuditStatus("2");
		tmp2.setRespType("S");
		tmp2.setRespDate(DateUtils.getDate("yyyyMMdd"));
		Double todayDrawProfit = routewayDrawService.countSumMemberDrawProfit(tmp2);
		todayDrawProfit = todayDrawProfit == null ? 0 : todayDrawProfit;
		
		DecimalFormat df = new DecimalFormat("0.00");
		Double tradeMoney =  new BigDecimal(sumTradeMoney).add(new BigDecimal(todayTradeMoney)).doubleValue();
		draw.setTradeMoney(df.format(tradeMoney));
		
		Double settleMoney =  new BigDecimal(sumSettleMoney).add(new BigDecimal(todaySettleMoney)).add(new BigDecimal(todayDrawProfit)).doubleValue();
		draw.setSettleMoney(df.format(settleMoney));
		
		RoutewayDraw tmp = new RoutewayDraw();
		tmp.setMemberId(draw.getMemberId());
		tmp.setRouteCode(draw.getRouteCode());
		tmp.setAuditStatus("2");
		tmp.setRespType("S");
		if("1038".equals(draw.getRouteCode())){
			tmp.setDrawType("1");
		}
		Double drawedMoney = routewayDrawService.countSumMoney(tmp);
		drawedMoney = drawedMoney == null ? 0 : drawedMoney;
		draw.setDrawedMoney(df.format(drawedMoney));
		
		tmp.setRespType("R");
		Double drawedMoneyR = routewayDrawService.countSumMoney(tmp);
		drawedMoneyR = drawedMoneyR == null ? 0 : drawedMoneyR;
		
		MemberDrawRoute tmp1 = new MemberDrawRoute();
		tmp1.setMemberId(draw.getMemberId());
		tmp1.setRouteCode(draw.getRouteCode());
		List<MemberDrawRoute> routeList = memberDrawRouteService.findList(tmp1);
		if(routeList == null || routeList.size()==0){
			tmp1.setMemberId("0");
			tmp1.setRouteCode(draw.getRouteCode());
			routeList = memberDrawRouteService.findList(tmp1);
		}
		if(routeList != null && routeList.size()>0){
			MemberDrawRoute drawRoute  = routeList.get(0);
			
			Double canDrawToday = (new BigDecimal(todaySettleMoney).add(new BigDecimal(todayDrawProfit))).multiply(new BigDecimal(drawRoute.getD0Percent())).doubleValue();//当天可提现的金额
			Double canDrawMoney = new BigDecimal(sumSettleMoney).subtract(new BigDecimal(drawedMoney)).subtract(new BigDecimal(drawedMoneyR)).add(new BigDecimal(canDrawToday)).doubleValue();
			draw.setCanDrawMoney(df.format(canDrawMoney));
		}
	}
}