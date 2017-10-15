/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.verifycode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.verifycode.entity.VerifyCode;
import com.thinkgem.jeesite.modules.verifycode.dao.VerifyCodeDao;

/**
 * 验证码Service
 * @author jjy
 * @version 2017-06-20
 */
@Service
@Transactional(readOnly = true)
public class VerifyCodeService extends CrudService<VerifyCodeDao, VerifyCode> {

	public VerifyCode get(String id) {
		return super.get(id);
	}
	
	public List<VerifyCode> findList(VerifyCode verifyCode) {
		return super.findList(verifyCode);
	}
	
	public Page<VerifyCode> findPage(Page<VerifyCode> page, VerifyCode verifyCode) {
		return super.findPage(page, verifyCode);
	}
	
	@Transactional(readOnly = false)
	public void save(VerifyCode verifyCode) {
		super.save(verifyCode);
	}
	
	@Transactional(readOnly = false)
	public void delete(VerifyCode verifyCode) {
		super.delete(verifyCode);
	}
	
}