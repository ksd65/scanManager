/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.dao.LeaveDao;
import com.thinkgem.jeesite.modules.oa.entity.Leave;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 机构DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	/**
	 * 获取机构编号
	 * @param 
	 * @return
	 */
	public String getOfficeCode(Office office);
	
	/**
	 * 根据id获取机构编号
	 * @param 
	 * @return
	 */
	public String getOfficeCodeById(Office office);
	
	/**
	 * 获取代理商机构编号
	 * @param 
	 * @return
	 */
	public String getAgtOfficeCode();
	
	/**
	 * 根据上级ID获取机构类型
	 * @param 
	 * @return
	 */
	public int getTypeByParentId(Office office);
	
	/**
	 * 根据上级ID获取 代理商级别
	 * @param 
	 * @return
	 */
	public Integer getAgtGradeByParentId(Office office);
	
	/**
	 * 根据上级ID获取代理商编号
	 * @param 
	 * @return
	 */
	public String getAgentCodeByParentId(Office office);
	
	/**
	 * 根据联系电话查询机构
	 * @param phone
	 * @return
	 */
	public Office getByPhone(String phone);
	
	/**
	 * 获取下级机构列表
	 * @param office
	 * @return
	 */
	public List<Office> findLowerLevelList(Office office);
	
	/**
	 * 
	 * @param office
	 * @return
	 */
	public Office getOfficeByCode(Office office);
	
	/**
	 * 
	 * @param office
	 * @return
	 */
	public int getAgtTypeByCode(String agtCode);
	
	/**
	 * 
	 * @param office
	 * @return
	 */
	public void deleteUserByOffice(Office office);
	
	/**
	 * 
	 * @param office
	 * @return
	 */
	public String getOnelevelByCode(String agtCode);
	
	/**
	 * 获取需要修改的机构列表
	 * @param office
	 * @return
	 */
	public List<Office> getModifyOfficeList();
	
	/**
	 * 
	 * @param office
	 * @return
	 */
	public void updateQrcodePath(Office office);
}
