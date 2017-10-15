/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.reg.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ecode.entity.EpayCode;
import com.thinkgem.jeesite.modules.reg.entity.Register;

/**
 * 商户审核DAO接口
 * @author linzw
 * @version 2016-11-21
 */
@MyBatisDao
public interface RegisterDao extends CrudDao<Register> {
	public int registerAudit(Register register);
}