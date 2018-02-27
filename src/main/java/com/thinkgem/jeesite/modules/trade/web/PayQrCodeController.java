/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.trade.entity.PayQrCode;
import com.thinkgem.jeesite.modules.trade.service.PayQrCodeService;


@Controller
@RequestMapping(value = "${adminPath}/trade/payQrCode")
public class PayQrCodeController extends BaseController {

	@Autowired
	private PayQrCodeService payQrCodeService;
	
	
	@ModelAttribute
	public PayQrCode get(@RequestParam(required=false) String id) {
		PayQrCode entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = payQrCodeService.get(id);
		}
		if (entity == null){
			entity = new PayQrCode();
		}
		return entity;
	}
	
	@RequiresPermissions("trade:payQrCode:view")
	@RequestMapping(value = {"list", ""})
	public String list(PayQrCode payQrCode, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		String beginTime = payQrCode.getBeginTime();
		if(StringUtils.isNotEmpty(beginTime)){
			payQrCode.setBeginTime(beginTime+" 00:00:00");
		}
		String endTime = payQrCode.getEndTime();
		if(StringUtils.isNotEmpty(endTime)){
			payQrCode.setEndTime(endTime+" 23:59:59");
		}
		Page<PayQrCode> page = payQrCodeService.findPage(new Page<PayQrCode>(request, response), payQrCode); 
		
		
		if(StringUtils.isNotEmpty(beginTime)){
			payQrCode.setBeginTime(beginTime);
		}
		if(StringUtils.isNotEmpty(endTime)){
			payQrCode.setEndTime(endTime);
		}
		model.addAttribute("page", page);
		model.addAttribute("payQrCode",payQrCode);
		
		return "modules/trade/payQrCode/qrCodeList";
	}

	@RequiresPermissions("trade:payQrCode:view")
	@RequestMapping(value = "form")
	public String form(PayQrCode payQrCode, Model model) {
		model.addAttribute("payQrCode", payQrCode);
		return "modules/trade/payQrCode/qrCodeForm";
	}

	@RequiresPermissions("trade:payQrCode:view")
	@RequestMapping(value = "save")
	public String save(PayQrCode payQrCode, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, payQrCode)){
			return form(payQrCode, model);
		}
		if(StringUtils.isBlank(payQrCode.getFilePath())){
			addMessage(model,"新增二维码失败，二维码图片不能为空");
			return form(payQrCode, model);
		}
		String qrCode = getQrCodeText(payQrCode.getFilePath());
		if(StringUtils.isBlank(qrCode)){
			addMessage(model,"新增二维码失败，二维码图片解析失败");
			return form(payQrCode, model);
		}
		payQrCode.setQrCode(qrCode);
		payQrCode.setStatus("0");
		payQrCodeService.save(payQrCode);
		addMessage(redirectAttributes, "新增二维码成功");
		return "redirect:"+Global.getAdminPath()+"/trade/payQrCode/?repage";
	}
	
	@RequiresPermissions("trade:payQrCode:view")
	@RequestMapping(value = "delete")
	public String delete(PayQrCode payQrCode, RedirectAttributes redirectAttributes) {
		payQrCodeService.delete(payQrCode);
		addMessage(redirectAttributes, "删除二维码成功");
		return "redirect:"+Global.getAdminPath()+"/trade/payQrCode/?repage";
	}
	
	private String getQrCodeText(String filePath){
		try {   
            MultiFormatReader formatReader = new MultiFormatReader();  
            String basePath = Global.getConfig("filePath");
            filePath = basePath + filePath; 
            File file = new File(filePath);   
              
            BufferedImage image = ImageIO.read(file);  
              
            LuminanceSource source = new BufferedImageLuminanceSource(image);   
              
            Binarizer  binarizer = new HybridBinarizer(source);   
              
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);   
              
            Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();      
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");      
              
            Result result = formatReader.decode(binaryBitmap,hints);   
 
            System.out.println("result = "+ result.toString());   
            System.out.println("resultFormat = "+ result.getBarcodeFormat());   
            System.out.println("resultText = "+ result.getText());   
            return result.getText();
 
        } catch (Exception e) {   
            e.printStackTrace();   
        } 
		return "";
	}
	

}