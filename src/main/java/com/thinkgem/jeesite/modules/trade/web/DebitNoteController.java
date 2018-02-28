/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.CommonUtil;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.HttpUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.trade.entity.DebitNote;
import com.thinkgem.jeesite.modules.trade.entity.PayQrCode;
import com.thinkgem.jeesite.modules.trade.entity.PayQrCodeTemp;
import com.thinkgem.jeesite.modules.trade.service.DebitNoteService;
import com.thinkgem.jeesite.modules.trade.service.PayQrCodeService;
import com.thinkgem.jeesite.modules.trade.service.PayQrCodeTempService;

/**
 * 交易明细查询Controller
 * @author chenjc
 * @version 2016-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/trade/debitNote")
public class DebitNoteController extends BaseController {

	@Autowired
	private DebitNoteService debitNoteService;
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private PayQrCodeTempService payQrCodeTempService;
	
	@Autowired
	private PayQrCodeService payQrCodeService;
	
	//@RequiresPermissions("trade:tradeDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(DebitNote debitNote, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();

		// 选择代理商
		if (StringUtils.isNotBlank(officeId)) {
			Office office2 = officeService.get(officeId);
			if (office2 != null) {
				office = office2;
			}
		}

		debitNote.setOffice(office);
		
		
		String beginTime = debitNote.getBeginTime();
		if(StringUtils.isEmpty(beginTime)){
			beginTime = DateUtils.getDate("yyyy-MM-dd");
		}
		
		String endTime = debitNote.getEndTime();
		if(StringUtils.isEmpty(endTime)){
			endTime = DateUtils.getDate("yyyy-MM-dd");
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			Date date1 = format.parse(beginTime+" 00:00:00");
			Date date2 = format.parse(endTime+" 00:00:00");
			double d=DateUtils.getDistanceOfTwoDate(date1, date2);
			if(d+1>7){
				model.addAttribute("message", "查询日期跨度不能超过7天");
				return "modules/trade/debitNoteList";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		debitNote.setBeginTime(beginTime+" 00:00:00");
		debitNote.setEndTime(endTime+" 23:59:59");
		
		
		Page<DebitNote> page = debitNoteService.findPage(new Page<DebitNote>(request, response), debitNote);
		model.addAttribute("page", page);
		
		debitNote.setBeginTime(beginTime);
		debitNote.setEndTime(endTime);
		
		model.addAttribute("debitNote", debitNote);
		return "modules/trade/debitNoteList";
	}
	
	@RequiresPermissions("trade:debitNote:qrOrder")
	@RequestMapping(value = {"qrOrderList"})
	public String qrOrderList(DebitNote debitNote, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();

		// 选择代理商
		if (StringUtils.isNotBlank(officeId)) {
			Office office2 = officeService.get(officeId);
			if (office2 != null) {
				office = office2;
			}
		}

		debitNote.setOffice(office);
		
		
		String beginTime = debitNote.getBeginTime();
		if(StringUtils.isEmpty(beginTime)){
			beginTime = DateUtils.getDate("yyyy-MM-dd");
		}
		
		String endTime = debitNote.getEndTime();
		if(StringUtils.isEmpty(endTime)){
			endTime = DateUtils.getDate("yyyy-MM-dd");
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			Date date1 = format.parse(beginTime+" 00:00:00");
			Date date2 = format.parse(endTime+" 00:00:00");
			double d=DateUtils.getDistanceOfTwoDate(date1, date2);
			if(d+1>7){
				model.addAttribute("message", "查询日期跨度不能超过7天");
				return "modules/trade/debitNoteList";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		debitNote.setBeginTime(beginTime+" 00:00:00");
		debitNote.setEndTime(endTime+" 23:59:59");
		
		debitNote.setRouteId("1024");//个人扫码
		debitNote.setQrorderDealStatus("0");//待处理
		debitNote.setRespType("0");//未支付
		
		Page<DebitNote> page = debitNoteService.findPage(new Page<DebitNote>(request, response), debitNote);
		model.addAttribute("page", page);
		
		debitNote.setBeginTime(beginTime);
		debitNote.setEndTime(endTime);
		
		model.addAttribute("debitNote", debitNote);
		
		return "modules/trade/debitNoteQrList";
	}
	
	
	@RequiresPermissions("trade:debitNote:qrOrder")
	@RequestMapping(value = "deal")
	public String deal(DebitNote debitNote, RedirectAttributes redirectAttributes) {
		String status = debitNote.getStatus();
		
		DebitNote note = debitNoteService.get(debitNote.getId());
		if(note == null){
			addMessage(redirectAttributes, "订单不存在");
			return "redirect:"+Global.getAdminPath()+"/trade/debitNote/qrOrderList?repage";
		}
		
		if(!"0".equals(note.getStatus())&&!"0".equals(note.getQrorderDealStatus())){
			addMessage(redirectAttributes, "订单状态不正确");
			return "redirect:"+Global.getAdminPath()+"/trade/debitNote/qrOrderList?repage";
		}
		
		if("0".equals(status)||"2".equals(status)){//未支付,支付失败
			PayQrCodeTemp payQrCodeTemp = new PayQrCodeTemp();
			payQrCodeTemp.setQrCodeId(note.getPayQrCodeId());
			payQrCodeTempService.delete(payQrCodeTemp);
			PayQrCode payQrCode = new PayQrCode();
			payQrCode.setStatus("0");
			payQrCode.setId(note.getPayQrCodeId());
			payQrCodeService.update(payQrCode);
			debitNote.setQrorderDealStatus("1");
			debitNoteService.updateDealStatus(debitNote);
		}else if("1".equals(status)){//支付成功
			
			JSONObject result = new JSONObject();
			JSONObject reqData=new JSONObject();
			reqData.put("orderCode", note.getOrderCode());
			result=JSONObject.fromObject(HttpUtil.sendPostRequest(Global.getConfig("pospService")+"/api/cashierDesk/grsmSuccess", CommonUtil.createSecurityRequstData(reqData)));
			if(!"0000".equals(result.getString("returnCode"))){
				addMessage(redirectAttributes, "操作失败："+result.getString("returnMsg"));
				return "redirect:"+Global.getAdminPath()+"/trade/debitNote/qrOrderList?repage";
			}
			
		}
		
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.getAdminPath()+"/trade/debitNote/qrOrderList?repage";
	}
	
}