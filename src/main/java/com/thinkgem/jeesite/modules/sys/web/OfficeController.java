/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
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
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.ZxingHandler;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.agent.entity.AgentRate;
import com.thinkgem.jeesite.modules.agent.service.AgentRateService;
import com.thinkgem.jeesite.modules.sys.entity.AgentConfig;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.AgentConfigService;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 机构Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/office")
public class OfficeController extends BaseController {

	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private AgentRateService agentRateService;
	
	@Autowired
	private AgentConfigService agentConfigService;
	
	@ModelAttribute("office")
	public Office get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return officeService.get(id);
		}else{
			return new Office();
		}
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {""})
	public String index(Office office, Model model) {
//        model.addAttribute("list", officeService.findAll());
		return "modules/sys/officeIndex";
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {"list"})
	public String list(HttpServletRequest request, Office office, Model model) {
		User user = UserUtils.getUser();
		office.setId(user.getOffice().getId());
		office.setParentIds(user.getOffice().getParentIds()+user.getOffice().getId()+",");
		if(request.getParameter("type")!= null){
	       	 if(request.getParameter("type").equals("1")){
	       		 if((office.getCode() != null  && !office.getCode().equals(""))
	       				 || (office.getName() != null  && !office.getName().equals("")))
	             model.addAttribute("type", "1");
	       	 }
		}else{
			 office.setCode("");
			 office.setName("");
			 model.addAttribute("office", office);
		}
		List<Office> list = officeService.findList(office);
		for(Office o : list){
			o.setAgtType(DictUtils.getDictLabel(o.getAgtType(), "sys_agt_type", "未知"));
		}
        model.addAttribute("list", list);
		return "modules/sys/officeList";
	}
	
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "form")
	public String form(Office office, Model model) {
		User user = UserUtils.getUser();
		if (office.getParent()==null || office.getParent().getId()==null){
			office.setParent(user.getOffice());
		}
		office.setParent(officeService.get(office.getParent().getId()));
		if (office.getArea()==null){
			office.setArea(user.getOffice().getArea());
		}
		
		// 自动获取排序号
		/*
		if (StringUtils.isBlank(office.getId())&&office.getParent()!=null){
			int size = 0;
			List<Office> list = officeService.findAll();
			for (int i=0; i<list.size(); i++){
				Office e = list.get(i);
				if (e.getParent()!=null && e.getParent().getId()!=null
						&& e.getParent().getId().equals(office.getParent().getId())){
					size++;
				}
			}
			office.setCode(office.getParent().getCode() + StringUtils.leftPad(String.valueOf(size > 0 ? size+1 : 1), 3, "0"));
		}
		*/
		model.addAttribute("office", office);
		return "modules/sys/officeForm";
	}
	
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "save")
	public String save(Office office, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/office/";
		}
		if (!beanValidator(model, office)){
			return form(office, model);
		}
		User user = UserUtils.getUser();
		if(office.getAgtType() == null){
			office.setAgtType("0");
		}
		String merImg = "";
		String merRegUrl = "";
		if(office.getId() == null || office.getId().equals("")){
			String officeCode = officeService.getOfficeCode(office);
			if(officeCode == null || officeCode.equals("")){
				officeCode = officeService.getOfficeCodeById(office);
			}
			String tmpCode = "";
			if(office.getType().equals("2")){
				tmpCode = officeCode.substring(0, 4);
				officeCode = String.valueOf(Long.parseLong(tmpCode)+1)+"0001";
			}else if(office.getType().equals("3")){
				officeCode = String.valueOf(Long.parseLong(officeCode)+1); 
			}else{
				int parentType = officeService.getTypeByParentId(office);
				if(parentType == 2){
					officeCode = officeService.getAgtOfficeCode();
					if(officeCode == null || officeCode.equals("")){
						officeCode = "20010001";
					}else{
						tmpCode = officeCode.substring(0, 4);
						officeCode = String.valueOf(Long.parseLong(tmpCode)+1)+"0001";
					}
					office.setAgtGrade("1");
				}else{
					officeCode = String.valueOf(Long.parseLong(officeCode)+1);
					int agtGrade = officeService.getAgtGradeByParentId(office);
					if(agtGrade >=4){
						addMessage(redirectAttributes, "当前为四级代理商，不能添加下级代理商！");
						return "redirect:" + adminPath + "/sys/office/form";
					}else{
						office.setAgtGrade(String.valueOf(agtGrade+1));
					}
				}
				
				try{
					String agtType = office.getAgtType();
					if(agtType.equals("1")){
						String code = officeCode;
						String agentCodePath = Global.getConfig("agentCodePath");
						SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");//设置日期格式
						String curDate = date.format(new Date());
						// 保存路径
						String savePath = agentCodePath +File.separator+ curDate +File.separator;
						File dir = new File(savePath);  
				        if (!dir.exists()) {  
				        	dir.mkdirs();
				        }
				        String agnetImg = "";
						String agentRegUrl = "";				
						agentRegUrl = Global.getConfig("agentRegUrl");
						agnetImg = "agent.jpg";
						merRegUrl = Global.getConfig("merRegUrl");
						merImg = "template.jpg";
						// 代理商生成二维码
						String imgPath = savePath + code +".png";
						String contents = agentRegUrl + code;
						int width = 270, height = 270;
						ZxingHandler.encode2(contents, width, height, imgPath);	
						//图片合成
						String fileName = code + ".png";
				        File file1 = new File(savePath, fileName);
				        File file2 = null;
				        file2 = new File(agentCodePath, agnetImg);
						mergeImage(file1,file2,savePath,fileName,code);
						// 保存二维码地址
						String qrcodePath = File.separator+curDate+File.separator+fileName;
						office.setQrcodePath(qrcodePath);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			office.setCode(officeCode);
			officeService.save(office);
			AgentRate agentRate = new AgentRate();
			agentRate.setOffice(new Office(office.getId()));
			agentRate.setT0DrawFee("0");
			agentRate.setT0TradeRate("0");
			agentRate.setT1DrawFee("0");
			agentRate.setT1TradeRate("0");
			agentRate.setBonusQuickFee("0");
			agentRate.setBonusQuickRate("0");
			agentRate.setQuickFee("0");
			agentRate.setQuickRate("0");
			
			agentRate.setMT0DrawFee("1");
			agentRate.setMT0TradeRate("0.004");
			agentRate.setMT1DrawFee("1");
			agentRate.setMT1TradeRate("0.0038");
			agentRate.setMBonusQuickFee("2");
			agentRate.setMBonusQuickRate("0.005");
			agentRate.setMQuickFee("2");
			agentRate.setMQuickRate("0.0043");
			agentRate.setMUrl(merRegUrl);
			agentRate.setMImg(merImg);
			agentRateService.save(agentRate);
		}else{
			officeService.save(office);
		}
	
		/*
		if(office.getChildDeptList()!=null){
			Office childOffice = null;
			for(String id : office.getChildDeptList()){
				childOffice = new Office();
				childOffice.setName(DictUtils.getDictLabel(id, "sys_office_common", "未知"));
				childOffice.setParent(office);
				childOffice.setArea(office.getArea());
				childOffice.setType("2");
				childOffice.setGrade(String.valueOf(Integer.valueOf(office.getGrade())+1));
				childOffice.setUseable(Global.YES);
				officeService.save(childOffice);
			}
		}
		*/
		addMessage(redirectAttributes, "保存机构'" + office.getName() + "'成功");
		/*
		String id = "0".equals(office.getParentId()) ? "" : office.getParentId();
		return "redirect:" + adminPath + "/sys/office/list?id="+id+"&parentIds="+office.getParentIds();
		*/
		return "redirect:" + adminPath + "/sys/office/";
		
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
	
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "delete")
	public String delete(Office office, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/office/list";
		}
//		if (Office.isRoot(id)){
//			addMessage(redirectAttributes, "删除机构失败, 不允许删除顶级机构或编号空");
//		}else{
			officeService.delete(office);
			addMessage(redirectAttributes, "删除机构成功");
//		}
		return "redirect:" + adminPath + "/sys/office/list?id="+office.getParentId()+"&parentIds="+office.getParentIds();
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
		List<Office> list = officeService.findList(isAll);
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
	
	/**
	 * 验证联系电话是否有效
	 * @param phone
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "checkPhone")
	public String checkPhone(String oldPhone,String phone) {
		if (oldPhone !=null && phone.equals(oldPhone)) {
			return "true";
		} else if (phone !=null && systemService.getOfficeByPhone(phone) == null) {
			return "true";
		}
		return "false";
	}
}
