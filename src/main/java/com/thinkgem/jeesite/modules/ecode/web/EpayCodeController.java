/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ecode.web;

import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.awt.Color;  
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;  
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;  

import javax.imageio.ImageIO;

import java.text.SimpleDateFormat;





import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.ZxingHandler;
import com.thinkgem.jeesite.modules.ecode.entity.EpayCode;
import com.thinkgem.jeesite.modules.ecode.entity.TransferDetail;
import com.thinkgem.jeesite.modules.ecode.service.EpayCodeService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.AgentConfig;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.AgentConfigService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * E码付管理Controller
 * @author chenjc
 * @version 2016-11-22
 */
@Controller
@RequestMapping(value = "${adminPath}/ecode/epayCode")
public class EpayCodeController extends BaseController {

	@Autowired
	private EpayCodeService epayCodeService;
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private AgentConfigService agentConfigService;
	
	
	@ModelAttribute
	public EpayCode get(@RequestParam(required=false) String id) {
		EpayCode entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = epayCodeService.get(id);
		}
		if (entity == null){
			entity = new EpayCode();
		}
		return entity;
	}
	
	@RequiresPermissions("ecode:epayCode:view")
	@RequestMapping(value = {"list", ""})
	public String list(EpayCode epayCode,RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, Model model,String type) throws IOException{
		/*
		 * 吴逢生 2017-06-05
		 * 数据范围过虑
		 */
		User user = UserUtils.getUser();
		epayCode.setOffice(user.getOffice());
		
		
		if("1".equals(type)){
			String agentCode = user.getOffice().getCode();
			String merRegUrl = "";
			String onelevelCode = officeService.getOnelevelByCode(agentCode);
			int agtType = officeService.getAgtTypeByCode(onelevelCode);
			String epaycodeImg = "";
			if(agtType == 2){
				AgentConfig agentConfig = new AgentConfig();
				agentConfig = agentConfigService.getAgentConfigByAgtCode(onelevelCode);
				merRegUrl = Global.getConfig(agentConfig.getEpaycodeUrlName());
				epaycodeImg = agentConfig.getEpaycodeImg();
			}else{
				merRegUrl = Global.getConfig("merRegUrl");
				epaycodeImg = "template.jpg";
			}
			/*
			if(agentCode.substring(0, 4).equals("2027")){//大喜钱包
				merRegUrl = Global.getConfig("dxMerRegUrl");
			}else{
				merRegUrl = Global.getConfig("merRegUrl");
			}
			*/
			String epayCodePath = Global.getConfig("epayCodePath");
			ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
			
			try{
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
				String zipName = df.format(new Date())+".zip";
		        response.setContentType("APPLICATION/OCTET-STREAM");  
		        response.setHeader("Content-Disposition","attachment; filename="+zipName);
		        
		        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");//设置日期格式
				String curDate = date.format(new Date());
				String savePath = epayCodePath +File.separator+ curDate +File.separator;
				File dir = new File(savePath);  
		        if (!dir.exists()) {  
		        	dir.mkdirs();
		        }
		        
				List<EpayCode> list = epayCodeService.findList(epayCode);
				
				for (int i=0; i<list.size(); i++){
					EpayCode e = list.get(i);
					String code = e.getPayCode();
					// 生成二维码
					String imgPath = savePath+code+".png";
					String contents = merRegUrl+code;
					int width = 270, height = 270;
					ZxingHandler.encode2(contents, width, height, imgPath);	
					//图片合成
					String fileName = code + ".png";
			        File file1 = new File(savePath, fileName);
			        File file2 = null;
			        file2 = new File(epayCodePath, epaycodeImg); 
			        /*
			        if(agentCode.substring(0, 4).equals("2027")){//大喜钱包
			        	 file2 = new File(epayCodePath, "dxTemplate.jpg"); 
			        }else{
			        	 file2 = new File(epayCodePath, "template.jpg"); 
			        }
			        */
					mergeImage(file1,file2,savePath,fileName,code);
					e.setPath(File.separator+curDate+File.separator+code+".png");
					epayCodeService.updatePath(e);
					//打压缩包并下载
					zipFile(imgPath, out);
	                response.flushBuffer();
				}
				return null;
			}catch(Exception e){
				addMessage(redirectAttributes, "生成二维码图片失败！失败信息："+e.getMessage());
			}finally{
	            out.close();
	        }
		}else{
			Page<EpayCode> page = epayCodeService.findPage(new Page<EpayCode>(request, response), epayCode); 
			model.addAttribute("page", page);
			return "modules/ecode/epayCodeList";
		}
		return null;
	}

	@RequiresPermissions("ecode:epayCode:view")
	@RequestMapping(value = "form")
	public String form(EpayCode epayCode, HttpServletRequest request, HttpServletResponse response, Model model) {
		//model.addAttribute("epayCode", epayCode);
		TransferDetail transferDetail = new TransferDetail();
		Page<TransferDetail> page = epayCodeService.findTransferDetailList(new Page<TransferDetail>(request, response), transferDetail);
		User user = UserUtils.getUser();
		epayCode.setOffice(user.getOffice());
		String amount = epayCodeService.getTransferAmount(epayCode);
		model.addAttribute("page", page);
		model.addAttribute("amount", amount);
		return "modules/ecode/epayCodeTransfer";
	}
	
	
	/**
	 * 获取机构JSON数据。
	 * @param extId 排除的ID
	 * @param type	类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Office office = new Office();
		List<Office> list = officeService.findLowerLevelList(office);
		for (int i=0; i<list.size(); i++){
			Office e = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1))
					&& (type == null || (type != null && (type.equals("1") ? type.equals(e.getType()) : true)))
					&& (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))					
					&& Global.YES.equals(e.getUseable())){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				map.put("type", e.getType());
				if (type != null && "3".equals(type)){
					map.put("isParent", true);
				}
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	@RequiresPermissions("ecode:epayCode:edit")
	@RequestMapping(value = "transfer")
	public String transfer(EpayCode epayCode, Model model, RedirectAttributes redirectAttributes) {
		if(epayCode.getTransferOffice().getId() == null || epayCode.getTransferOffice().getId().equals("")){
			addMessage(redirectAttributes, "请选择要划拨的机构");
			return "redirect:"+Global.getAdminPath()+"/ecode/epayCode/form";
		}
		User user = UserUtils.getUser();
		epayCodeService.transferEpayCode(epayCode, user);
		addMessage(redirectAttributes, "划拨成功");
		return "redirect:"+Global.getAdminPath()+"/ecode/epayCode/form";
	}
	
	@RequiresPermissions("ecode:epayCode:edit")
	@RequestMapping(value = "create")
	public String create(EpayCode epayCode, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		epayCode.setOffice(user.getOffice());
		String amount = epayCodeService.getCreateAmount(epayCode);
		model.addAttribute("amount", amount);
		return "modules/ecode/epayCodeDownload";
	}
	
	@RequiresPermissions("ecode:epayCode:edit")
	@RequestMapping(value = "autosendCfg")
	public String autosendCfg(EpayCode epayCode, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		epayCode.setOffice(user.getOffice());
		//获取可进行自动分发码数量
		String amount = epayCodeService.getCreateAmount(epayCode);
		model.addAttribute("amount", amount);
		return "modules/ecode/epayCodeAutosend";
	}
	
	@RequiresPermissions("ecode:epayCode:edit")
    @RequestMapping(value = "download")
    public String download(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException{
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String zipName = df.format(new Date())+".zip";
        response.setContentType("APPLICATION/OCTET-STREAM");  
        response.setHeader("Content-Disposition","attachment; filename="+zipName);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        String amount = request.getParameter("amount");
        
		User user = UserUtils.getUser();
		String agentCode = user.getOffice().getCode();
		String merRegUrl = "";
		String onelevelCode = officeService.getOnelevelByCode(agentCode);
		int agtType = officeService.getAgtTypeByCode(onelevelCode);
		String epaycodeImg = "";
		if(agtType == 2){
			AgentConfig agentConfig = new AgentConfig();
			agentConfig = agentConfigService.getAgentConfigByAgtCode(onelevelCode);
			merRegUrl = Global.getConfig(agentConfig.getEpaycodeUrlName());
			epaycodeImg = agentConfig.getEpaycodeImg();
		}else{
			merRegUrl = Global.getConfig("merRegUrl");
			epaycodeImg = "template.jpg";
		}
		/*
		if(agentCode.substring(0, 4).equals("2027")){//大喜钱包
			merRegUrl = Global.getConfig("dxMerRegUrl");
		}else{
			merRegUrl = Global.getConfig("merRegUrl");
		}
		*/
		String epayCodePath = Global.getConfig("epayCodePath");
		try {
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");//设置日期格式
			String curDate = date.format(new Date());
			String savePath = epayCodePath +File.separator+ curDate +File.separator;
			File dir = new File(savePath);  
	        if (!dir.exists()) {  
	        	dir.mkdirs();
	        }
			EpayCode epayCode = new EpayCode();
			epayCode.setAmount(Integer.parseInt(amount));
			epayCode.setOffice(user.getOffice());
			List<EpayCode> list = epayCodeService.findCreateEpayCode(epayCode);
			
			// 更新手续费与费率
			for(int j=0;j < list.size();j++){
				EpayCode entity = list.get(j);
				
				// 配置数据
		        String t0DrawFee 	= request.getParameter("t0DrawFee");
		        String t0TradeRate 	= request.getParameter("t0TradeRate");
		        String t1DrawFee 	= request.getParameter("t1DrawFee");
		        String t1TradeRate 	= request.getParameter("t1TradeRate");		        
		        String mlJfFee 	= request.getParameter("mlJfFee");
		        String mlJfRate 	= request.getParameter("mlJfRate");
		        String mlWjfFee 	= request.getParameter("mlWjfFee");
		        String mlWjfRate 	= request.getParameter("mlWjfRate");
		        
		        entity.setT0DrawFee(t0DrawFee);
		        entity.setT0TradeRate(t0TradeRate);
		        entity.setT1DrawFee(t1DrawFee);
		        entity.setT1TradeRate(t1TradeRate);
		        entity.setMlJfFee(mlJfFee);
		        entity.setMlJfRate(mlJfRate);
		        entity.setMlWjfFee(mlWjfFee);
		        entity.setMlWjfRate(mlWjfRate);
		        
		        epayCodeService.updateFeeAndRate(entity, user);
			}
			
			for (int i=0; i<list.size(); i++){
				EpayCode e = list.get(i);
				String code = e.getPayCode();
				// 生成二维码
				String imgPath = savePath+code+".png";
				String contents = merRegUrl+code;
				int width = 270, height = 270;
				ZxingHandler.encode2(contents, width, height, imgPath);	
				//图片合成
				String fileName = code + ".png";
		        File file1 = new File(savePath, fileName);
		        File file2 = null;
		        file2 = new File(epayCodePath, epaycodeImg); 
		        /*
		        if(agentCode.substring(0, 4).equals("2027")){//大喜钱包
		        	 file2 = new File(epayCodePath, "dxTemplate.jpg"); 
		        }else{
		        	 file2 = new File(epayCodePath, "template.jpg"); 
		        }
		        */
				mergeImage(file1,file2,savePath,fileName,code);
				e.setPath(File.separator+curDate+File.separator+code+".png");
				epayCodeService.updatePath(e);
				//打压缩包并下载
				zipFile(imgPath, out);
	            response.flushBuffer();
				
			}
			epayCode.setStatus("3");
			epayCodeService.createferEpayCode(epayCode, user);
			return null;
			
		} catch (Exception e) {
			addMessage(redirectAttributes, "生成二维码图片失败！失败信息："+e.getMessage());
		}finally{
            out.close();
        }
		return "redirect:" + adminPath + "/ecode/epayCode/create";
    }
	
	
	@RequiresPermissions("ecode:epayCode:edit")
    @RequestMapping(value = "autosend")
    public String autosend(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException{
		
		//SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		//String zipName = df.format(new Date())+".zip";
       // response.setContentType("APPLICATION/OCTET-STREAM");  
        //response.setHeader("Content-Disposition","attachment; filename="+zipName);
        //ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        String amount = request.getParameter("amount");
        
		User user = UserUtils.getUser();
		String agentCode = user.getOffice().getCode();
		String merRegUrl = "";
		String onelevelCode = officeService.getOnelevelByCode(agentCode);
		int agtType = officeService.getAgtTypeByCode(onelevelCode);
		String epaycodeImg = "";
		if(agtType == 2){
			AgentConfig agentConfig = new AgentConfig();
			agentConfig = agentConfigService.getAgentConfigByAgtCode(onelevelCode);
			merRegUrl = Global.getConfig(agentConfig.getEpaycodeUrlName());
			epaycodeImg = agentConfig.getEpaycodeImg();
		}else{
			merRegUrl = Global.getConfig("merRegUrl");
			epaycodeImg = "template.jpg";
		}
		/*
		if(agentCode.substring(0, 4).equals("2027")){//大喜钱包
			merRegUrl = Global.getConfig("dxMerRegUrl");
		}else{
			merRegUrl = Global.getConfig("merRegUrl");
		}
		*/
		String epayCodePath = Global.getConfig("epayCodePath");
		try {
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");//设置日期格式
			String curDate = date.format(new Date());
			String savePath = epayCodePath +File.separator+ curDate +File.separator;
			File dir = new File(savePath);  
	        if (!dir.exists()) {  
	        	dir.mkdirs();
	        }
			EpayCode epayCode = new EpayCode();
			epayCode.setAmount(Integer.parseInt(amount));
			epayCode.setOffice(user.getOffice());
			List<EpayCode> list = epayCodeService.findCreateEpayCode(epayCode);
			
			// 更新手续费与费率
			for(int j=0;j < list.size();j++){
				EpayCode entity = list.get(j);
				
				// 配置数据
		        String t0DrawFee 	= request.getParameter("t0DrawFee");
		        String t0TradeRate 	= request.getParameter("t0TradeRate");
		        String t1DrawFee 	= request.getParameter("t1DrawFee");
		        String t1TradeRate 	= request.getParameter("t1TradeRate");		        
		        String mlJfFee 	= request.getParameter("mlJfFee");
		        String mlJfRate 	= request.getParameter("mlJfRate");
		        String mlWjfFee 	= request.getParameter("mlWjfFee");
		        String mlWjfRate 	= request.getParameter("mlWjfRate");
		        
		        entity.setT0DrawFee(t0DrawFee);
		        entity.setT0TradeRate(t0TradeRate);
		        entity.setT1DrawFee(t1DrawFee);
		        entity.setT1TradeRate(t1TradeRate);
		        entity.setMlJfFee(mlJfFee);
		        entity.setMlJfRate(mlJfRate);
		        entity.setMlWjfFee(mlWjfFee);
		        entity.setMlWjfRate(mlWjfRate);
		        
		        epayCodeService.updateFeeAndRate(entity, user);
			}
			
			for (int i=0; i<list.size(); i++){
				EpayCode e = list.get(i);
				String code = e.getPayCode();
				// 生成二维码
				String imgPath = savePath+code+".png";
				String contents = merRegUrl+code;
				int width = 270, height = 270;
				ZxingHandler.encode2(contents, width, height, imgPath);	
				//图片合成
				String fileName = code + ".png";
		        File file1 = new File(savePath, fileName);
		        File file2 = null;
		        file2 = new File(epayCodePath, epaycodeImg); 
		        /*
		        if(agentCode.substring(0, 4).equals("2027")){//大喜钱包
		        	 file2 = new File(epayCodePath, "dxTemplate.jpg"); 
		        }else{
		        	 file2 = new File(epayCodePath, "template.jpg"); 
		        }
		        */
				mergeImage(file1,file2,savePath,fileName,code);
				e.setPath(File.separator+curDate+File.separator+code+".png");
				epayCodeService.updatePath(e);
			}
			
			epayCode.setStatus("8");
			epayCodeService.createferEpayCode(epayCode, user);
			addMessage(redirectAttributes, "配置成功");
			return "redirect:" + adminPath + "/ecode/epayCode/autosendCfg";
			
		} catch (Exception e) {
			
			addMessage(redirectAttributes, "自动分发二维码配置失败！失败信息："+e.getMessage());
			
		}finally{
            //out.close();
        }
		return "redirect:" + adminPath + "/ecode/epayCode/autosendCfg";
    }
	
	public static void mergeImage(File file1, File file2, String savePath, String fileName, String code) throws IOException {        
        try{
        	BufferedImage image1 = ImageIO.read(file1);  
            BufferedImage image2 = ImageIO.read(file2);  
            Graphics g = image2.getGraphics();
            g.drawImage(image1, 185, 490, null);
            Font font = new Font("华文彩云", Font.PLAIN, 40);
            g.setFont(font);
            g.setColor(Color.black);
            g.drawString(code, 210, 820);
            ImageIO.write(image2, "JPG", new File(savePath, fileName));
        }catch(IOException e){
        	e.printStackTrace();
        }
		
    }
	
	public static void zipFile(String fileName, ZipOutputStream out) throws IOException{
        File file = new File(fileName);
        if( file.exists() ){
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(file);
            out.putNextEntry(new ZipEntry(file.getName()));
            int len = 0 ;
            //读入需要下载的文件的内容，打包到zip文件    
            while ((len = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
            }
            out.flush();
            out.closeEntry();
            fis.close();
        }
    }

/*
	@RequiresPermissions("ecode:epayCode:edit")
	@RequestMapping(value = "save")
	public String save(EpayCode epayCode, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, epayCode)){
			return form(epayCode, model);
		}
		epayCodeService.save(epayCode);
		addMessage(redirectAttributes, "保存商户二维码成功");
		return "redirect:"+Global.getAdminPath()+"/ecode/epayCode/?repage";
	}
	
	@RequiresPermissions("ecode:epayCode:edit")
	@RequestMapping(value = "delete")
	public String delete(EpayCode epayCode, RedirectAttributes redirectAttributes) {
		epayCodeService.delete(epayCode);
		addMessage(redirectAttributes, "删除商户二维码成功");
		return "redirect:"+Global.getAdminPath()+"/ecode/epayCode/?repage";
	}
*/
}