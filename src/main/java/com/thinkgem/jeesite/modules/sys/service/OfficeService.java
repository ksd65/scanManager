/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.modules.oa.dao.LeaveDao;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 机构Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDao, Office> {
	
	@Autowired
	private OfficeDao officeDao;
	
	public List<Office> findAll(){
		return UserUtils.getOfficeList();
	}

	public List<Office> findList(Boolean isAll){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList();
		}else{
			return UserUtils.getOfficeList();
		}
	}
	
	@Transactional(readOnly = true)
	public List<Office> findList(Office office){
		if(office != null){
			office.setParentIds(office.getParentIds()+"%");
			return dao.findByParentIdsLike(office);
		}
		return  new ArrayList<Office>();
	}
	
	@Transactional(readOnly = false)
	public void save(Office office) {
		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Office office) {
		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	
	@Transactional(readOnly = true)
	public String getOfficeCode(Office office){
		return officeDao.getOfficeCode(office);
	}
	
	@Transactional(readOnly = true)
	public String getOfficeCodeById(Office office){
		return officeDao.getOfficeCodeById(office);
	}
	
	@Transactional(readOnly = true)
	public String getAgtOfficeCode(){
		return officeDao.getAgtOfficeCode();
	}
	
	@Transactional(readOnly = true)
	public int getTypeByParentId(Office office){
		return officeDao.getTypeByParentId(office);
	}
	
	@Transactional(readOnly = true)
	public Integer getAgtGradeByParentId(Office office){
		return officeDao.getAgtGradeByParentId(office);
	}
	
	@Transactional(readOnly = true)
	public String getAgentCodeByParentId(Office office){
		return officeDao.getAgentCodeByParentId(office);
	}
	
	@Transactional(readOnly = true)
	public List<Office> findLowerLevelList(Office office){
		if(office != null){
			return officeDao.findLowerLevelList(office);
		}
		return  new ArrayList<Office>();
	}
	
	/**
	 * 
	 * @param office
	 * @return
	 */
	public Office getOfficeByCode(Office office){
		return dao.getOfficeByCode(office);
	}
	
	@Transactional(readOnly = true)
	public int getAgtTypeByCode(String agtCode){
		return officeDao.getAgtTypeByCode(agtCode);
	}
	
	@Transactional(readOnly = false)
	public void deleteUserByOffice(Office office){
		 officeDao.deleteUserByOffice(office);
	}
	
	@Transactional(readOnly = true)
	public String getOnelevelByCode(String agtCode){
		return officeDao.getOnelevelByCode(agtCode);
	}
	
	@Transactional(readOnly = true)
	public List<Office> getModifyOfficeList(){
			return officeDao.getModifyOfficeList();
	}
	
	@Transactional(readOnly = false)
	public void updateQrcodePath(Office office){
		 officeDao.updateQrcodePath(office);
	}
}
