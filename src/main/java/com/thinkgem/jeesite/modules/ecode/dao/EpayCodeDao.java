/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ecode.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ecode.entity.EpayCode;
import com.thinkgem.jeesite.modules.ecode.entity.TransferDetail;
import com.thinkgem.jeesite.modules.sys.entity.Menu;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * E码付管理DAO接口
 * @author chenjc
 * @version 2016-11-22
 */
@MyBatisDao
public interface EpayCodeDao extends CrudDao<EpayCode> {
	
	public int transferEpayCode(EpayCode epayCode);
	
	public int insertTransferDetail(TransferDetail transferDetail);
	
	public List<TransferDetail> findTransferDetailList(TransferDetail transferDetail);

	public String getTransferAmount(EpayCode epayCode);
	
	public String getCreateAmount(EpayCode epayCode);
	
	public String getBatchNoSeq();
	
	public List<EpayCode> findCreateEpayCode(EpayCode epayCode);
	
	public String getCreateBatchNoSeq();
	
	public int createEpayCode(EpayCode epayCode);
	
	public int updateStatus(EpayCode epayCode);
	
	public int updatePath(EpayCode epayCode);
	
	public int updateFeeAndRate(EpayCode epayCode);
}