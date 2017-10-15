/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ecode.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 划拨明细Entity
 * @author chenjc
 * @version 2016-11-22
 */
public class TransferDetail extends DataEntity<TransferDetail> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 本机构编号
	private Office transferOffice;// 划拨机构编号
	private String batchNo;			// 划拨批次号
	private String amount;			// 划拨数量
	
	public TransferDetail() {
		super();
	}

	public TransferDetail(String id){
		super(id);
	}

	@NotNull(message="机构编号不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@NotNull(message="机构编号不能为空")
	public Office getTransferOffice() {
		return transferOffice;
	}

	public void setTransferOffice(Office transferOffice) {
		this.transferOffice = transferOffice;
	}
	
	@Length(min=0, max=15, message="批次号长度必须介于 0 和 15 之间")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@Length(min=0, max=10, message="划拨数量长度必须介于 0 和 15 之间")
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}