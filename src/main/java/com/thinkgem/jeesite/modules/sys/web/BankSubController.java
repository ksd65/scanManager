/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.BankSub;
import com.thinkgem.jeesite.modules.sys.service.BankSubService;

/**
 * 区域Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/bankSub")
public class BankSubController extends BaseController {

	@Autowired
	private BankSubService bankSubService;
	
	
	
	@ResponseBody
	@RequestMapping(value ="getBankSubList")
	public Map<String,Object> getBankSubList(Model model, HttpServletRequest request) {
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			BankSub bankSub = new BankSub();
			String cityId = request.getParameter("cityId");
			if (cityId == null || "".equals(cityId)) {
				cityId = "";
			}else{
				bankSub.setCityId(Integer.parseInt(cityId));
			}
			String bankId = request.getParameter("bankId");
			if (bankId == null || "".equals(bankId)) {
				bankId = "";
			}else{
				bankSub.setBankId(Integer.parseInt(bankId));
			}
			List<BankSub> subList = bankSubService.findList(bankSub);
			result.put("subList", subList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			result.put("returnCode", "4004");
			result.put("returnMsg", "请求失败");
			return result;
		}
		return result;
	}
	
	
}
