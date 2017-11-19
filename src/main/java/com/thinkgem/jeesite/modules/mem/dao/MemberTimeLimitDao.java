package com.thinkgem.jeesite.modules.mem.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mem.entity.MemberTimeLimit;

@MyBatisDao
public interface MemberTimeLimitDao extends CrudDao<MemberTimeLimit>{

}
