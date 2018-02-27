/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.trade.dao.PayQrCodeTempDao;
import com.thinkgem.jeesite.modules.trade.entity.PayQrCodeTemp;


@Service
@Transactional(readOnly = true)
public class PayQrCodeTempService extends CrudService<PayQrCodeTempDao, PayQrCodeTemp> {

	public PayQrCodeTemp get(String id) {
		return super.get(id);
	}
	
	public List<PayQrCodeTemp> findList(PayQrCodeTemp payQrCodeTemp) {
		return super.findList(payQrCodeTemp);
	}
	
	public Page<PayQrCodeTemp> findPage(Page<PayQrCodeTemp> page, PayQrCodeTemp payQrCodeTemp) {
		return super.findPage(page, payQrCodeTemp);
	}
	
	@Transactional(readOnly = false)
	public void save(PayQrCodeTemp payQrCodeTemp) {
		super.save(payQrCodeTemp);
	}
	
	@Transactional(readOnly = false)
	public void delete(PayQrCodeTemp payQrCodeTemp) {
		super.delete(payQrCodeTemp);
	}
	
}