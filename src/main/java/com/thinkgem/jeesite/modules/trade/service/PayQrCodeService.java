/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

import com.thinkgem.jeesite.modules.trade.dao.PayQrCodeDao;
import com.thinkgem.jeesite.modules.trade.entity.PayQrCode;


@Service
@Transactional(readOnly = true)
public class PayQrCodeService extends CrudService<PayQrCodeDao, PayQrCode> {

	public PayQrCode get(String id) {
		return super.get(id);
	}
	
	public List<PayQrCode> findList(PayQrCode PayQrCode) {
		return super.findList(PayQrCode);
	}
	
	public Page<PayQrCode> findPage(Page<PayQrCode> page, PayQrCode PayQrCode) {
		return super.findPage(page, PayQrCode);
	}
	
	@Transactional(readOnly = false)
	public void save(PayQrCode PayQrCode) {
		super.save(PayQrCode);
	}
	
	@Transactional(readOnly = false)
	public void delete(PayQrCode PayQrCode) {
		super.delete(PayQrCode);
	}
	
	@Transactional(readOnly = false)
	public void update(PayQrCode PayQrCode) {
		dao.update(PayQrCode);
	}
	
}