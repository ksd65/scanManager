/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.draw.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.draw.entity.RoutewayDraw;

/**
 * 通道提现查询DAO接口
 * @author chenjc
 * @version 2016-12-21
 */
@MyBatisDao
public interface RoutewayDrawDao extends CrudDao<RoutewayDraw> {
	
}