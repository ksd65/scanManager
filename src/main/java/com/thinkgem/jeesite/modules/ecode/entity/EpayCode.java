/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ecode.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * E码付管理Entity
 * @author chenjc
 * @version 2016-11-22
 */
public class EpayCode extends DataEntity<EpayCode> {
	
	private static final long serialVersionUID = 1L;
	private String payCode;			// E码付编号
	private String status;			// 状态
	private Office office;			// 机构编号
	private Office transferOffice;	// 划拨机构编号
	private String memberId;		// 绑定商户ID
	private String batchNo;			// 划拨批次号
	private String createBatchNo;	// 生成批次号
	private String path;			// 图片路径
	private String flow;			// 流程信息，参考格式：内容1 时间1|内容2 时间2
	private int amount;				// 生成数量
	
	private String t0DrawFee;
	private String t0TradeRate;
	private String t1DrawFee;
	private String t1TradeRate;
	
	private String mlJfFee;
	private String mlJfRate;
	private String mlWjfFee;
	private String mlWjfRate;
	
	
	public EpayCode() {
		super();
	}

	public EpayCode(String id){
		super(id);
	}

	@Length(min=1, max=15, message="E码付编号长度必须介于 1 和 15 之间")
	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	
	@Length(min=1, max=2, message="状态长度必须介于 1 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@NotNull(message="机构编号不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	public Office getTransferOffice() {
		return transferOffice;
	}

	public void setTransferOffice(Office transferOffice) {
		this.transferOffice = transferOffice;
	}
	
	@Length(min=0, max=11, message="会员ID长度必须介于 0 和 11 之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Length(min=0, max=15, message="批次号长度必须介于 0 和 15 之间")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@Length(min=0, max=15, message="批次号长度必须介于 0 和 15 之间")
	public String getCreateBatchNo() {
		return createBatchNo;
	}

	public void setCreateBatchNo(String createBatchNo) {
		this.createBatchNo = createBatchNo;
	}
	
	@Length(min=0, max=128, message="图片路径长度必须介于 0 和 128 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Length(min=0, max=1024, message="流程信息，参考格式：内容1 时间1|内容2 时间2长度必须介于 0 和 1024 之间")
	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}
	
	@Length(min=0, max=10, message="生成数量必须介于 0 和 10之间")
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getT0DrawFee() {
		return t0DrawFee;
	}

	public void setT0DrawFee(String t0DrawFee) {
		this.t0DrawFee = t0DrawFee;
	}

	public String getT0TradeRate() {
		return t0TradeRate;
	}

	public void setT0TradeRate(String t0TradeRate) {
		this.t0TradeRate = t0TradeRate;
	}

	public String getT1DrawFee() {
		return t1DrawFee;
	}

	public void setT1DrawFee(String t1DrawFee) {
		this.t1DrawFee = t1DrawFee;
	}

	public String getT1TradeRate() {
		return t1TradeRate;
	}

	public void setT1TradeRate(String t1TradeRate) {
		this.t1TradeRate = t1TradeRate;
	}
		
	public String getMlJfFee() {
		return mlJfFee;
	}

	public void setMlJfFee(String mlJfFee) {
		this.mlJfFee = mlJfFee;
	}

	public String getMlJfRate() {
		return mlJfRate;
	}

	public void setMlJfRate(String mlJfRate) {
		this.mlJfRate = mlJfRate;
	}

	public String getMlWjfFee() {
		return mlWjfFee;
	}

	public void setMlWjfFee(String mlWjfFee) {
		this.mlWjfFee = mlWjfFee;
	}

	public String getMlWjfRate() {
		return mlWjfRate;
	}

	public void setMlWjfRate(String mlWjfRate) {
		this.mlWjfRate = mlWjfRate;
	}
}