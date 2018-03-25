/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mem.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mem.entity.MemberPayType;

/**
 * 商户绑卡流水表DAO接口
 * @author jjy
 * @version 2017-06-20
 */
@MyBatisDao
public interface MemberPayTypeDao extends CrudDao<MemberPayType> {
	
}