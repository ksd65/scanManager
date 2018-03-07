/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.thinkgem.jeesite.modules.trade.entity.PrePayStatistics;
import com.thinkgem.jeesite.modules.trade.service.PrePayStatisticsService;

/**
 * 交易明细查询Controller
 * @author chenjc
 * @version 2016-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/trade/prepay")
public class PrePayStatisticsController extends BaseController {

	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private PrePayStatisticsService prePayStatisticsService;
	
	
	@RequiresPermissions("trade:prepay:view")
	@RequestMapping(value = {"list", ""})
	public String list(PrePayStatistics prePayStatistics, HttpServletRequest request, HttpServletResponse response, Model model,String officeId) {
		// 当前用户代理商
		Office office = UserUtils.getUser().getOffice();

		// 选择代理商
		if (StringUtils.isNotBlank(officeId)) {
			Office office2 = officeService.get(officeId);
			if (office2 != null) {
				office = office2;
			}
		}

		prePayStatistics.setOffice(office);
		prePayStatistics.setTxnDate(DateUtils.getDate("yyyyMMdd"));
		
		Page<PrePayStatistics> page = prePayStatisticsService.findPage(new Page<PrePayStatistics>(request, response), prePayStatistics);
		model.addAttribute("page", page);
		
		model.addAttribute("prePayStatistics", prePayStatistics);
		return "modules/trade/prePayStatisticsList";
	}
	
	
	
}