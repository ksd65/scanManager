/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.trade.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.trade.entity.DebitNote;

/**
 * 交易明细查询DAO接口
 * @author chenjc
 * @version 2016-12-15
 */
@MyBatisDao
public interface DebitNoteDao extends CrudDao<DebitNote> {
	
	
}