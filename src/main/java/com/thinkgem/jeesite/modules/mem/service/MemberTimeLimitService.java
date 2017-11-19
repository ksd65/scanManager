package com.thinkgem.jeesite.modules.mem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mem.dao.MemberTimeLimitDao;
import com.thinkgem.jeesite.modules.mem.entity.MemberTimeLimit;

@Service
@Transactional(readOnly = true)
public class MemberTimeLimitService extends CrudService<MemberTimeLimitDao, MemberTimeLimit>{

}
