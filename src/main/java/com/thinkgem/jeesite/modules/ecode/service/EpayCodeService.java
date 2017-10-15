/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ecode.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ecode.entity.EpayCode;
import com.thinkgem.jeesite.modules.ecode.entity.TransferDetail;
import com.thinkgem.jeesite.modules.ecode.dao.EpayCodeDao;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * E码付管理Service
 * @author chenjc
 * @version 2016-11-22
 */
@Service
@Transactional(readOnly = true)
public class EpayCodeService extends CrudService<EpayCodeDao, EpayCode> {
	
	@Autowired
	private EpayCodeDao epayCodeeDao;
	
	public EpayCode get(String id) {
		return super.get(id);
	}
	
	public List<EpayCode> findList(EpayCode epayCode) {
		epayCode.getSqlMap().put("dsf", dataScopeFilter(epayCode.getCurrentUser(), "o", ""));
		return super.findList(epayCode);
	}
	
	public Page<EpayCode> findPage(Page<EpayCode> page, EpayCode epayCode) {
		epayCode.getSqlMap().put("dsf", dataScopeFilter(epayCode.getCurrentUser(), "o", ""));
		return super.findPage(page, epayCode);
	}
	
	@Transactional(readOnly = false)
	public void save(EpayCode epayCode) {
		super.save(epayCode);
	}
	
	@Transactional(readOnly = false)
	public void delete(EpayCode epayCode) {
		super.delete(epayCode);
	}
	
	public Page<TransferDetail> findTransferDetailList(Page<TransferDetail> page, TransferDetail transferDetail) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		transferDetail.getSqlMap().put("dsf", dataScopeFilter(transferDetail.getCurrentUser(), "o", ""));
		// 设置分页参数
		transferDetail.setPage(page);
		// 执行分页查询
		page.setList(epayCodeeDao.findTransferDetailList(transferDetail));
		return page;
	}
	

	
	@Transactional(readOnly = false)
	public void transferEpayCode(EpayCode epayCode, User user) {
		String batchNo = epayCodeeDao.getBatchNoSeq();
		epayCode.setOffice(user.getOffice());
		epayCode.setStatus("2");
		epayCode.setBatchNo(batchNo);
		epayCode.setUpdateBy(user);
		epayCode.setUpdateDate(new Date());
		epayCodeeDao.transferEpayCode(epayCode);
		
		TransferDetail transferDetail = new TransferDetail();
		transferDetail.setAmount(String.valueOf(epayCode.getAmount()));
		transferDetail.setBatchNo(batchNo);
		transferDetail.setOffice(epayCode.getOffice());
		transferDetail.setTransferOffice(epayCode.getTransferOffice());
		transferDetail.setCreateBy(user);
		transferDetail.setCreateDate(new Date());
		transferDetail.setUpdateBy(user);
		transferDetail.setUpdateDate(new Date());
		epayCodeeDao.insertTransferDetail(transferDetail);
	}

	@Transactional(readOnly = true)
	public String getTransferAmount(EpayCode epayCode){
		return epayCodeeDao.getTransferAmount(epayCode);
	}
	
	@Transactional(readOnly = true)
	public String getCreateAmount(EpayCode epayCode){
		return epayCodeeDao.getCreateAmount(epayCode);
	}
	
	@Transactional(readOnly = true)
	public List<EpayCode> findCreateEpayCode(EpayCode epayCode){
		return epayCodeeDao.findCreateEpayCode(epayCode);
	}
	
	@Transactional(readOnly = false)
	public int updatePath(EpayCode epayCode) {
		return epayCodeeDao.updatePath(epayCode);
	}
	
	@Transactional(readOnly = false)
	public void createferEpayCode(EpayCode epayCode, User user) {
		String createBatchNo = epayCodeeDao.getCreateBatchNoSeq();
		//epayCode.setStatus("3");
		epayCode.setCreateBatchNo(createBatchNo);
		epayCode.setUpdateBy(user);
		epayCode.setUpdateDate(new Date());
		epayCodeeDao.createEpayCode(epayCode);
	}
	
	@Transactional(readOnly = false)
	public int updateFeeAndRate(EpayCode epayCode,User user){
		epayCode.setUpdateBy(user);
		epayCode.setUpdateDate(new Date());
		return dao.updateFeeAndRate(epayCode);
	}
}