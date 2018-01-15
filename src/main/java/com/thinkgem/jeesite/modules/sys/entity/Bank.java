/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户Entity
 * @author ThinkGem
 * @version 2013-12-05
 */
public class Bank extends DataEntity<Bank> {

	private static final long serialVersionUID = 1L;
	private String name;	// 名称
	private Integer sort;	// 排序
	private Integer kbinId;	
	private String ftBankId;	
	private String code;
	
	public Bank() {
		super();
	}
	
	public Bank(String id){
		super(id);
	}
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getKbinId() {
		return kbinId;
	}

	public void setKbinId(Integer kbinId) {
		this.kbinId = kbinId;
	}

	public String getFtBankId() {
		return ftBankId;
	}

	public void setFtBankId(String ftBankId) {
		this.ftBankId = ftBankId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return id;
	}
}