package com.thinkgem.jeesite.modules.mem.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class MemberTimeLimit extends DataEntity<MemberTimeLimit>{

	private static final long serialVersionUID = 7716465866903326483L;
	
	private String memberId;		//绑定商户ID
	
	private String beginTime;
	
	private String endTime;
	
	private String timeLimit;
	
	public MemberTimeLimit(){
		super();
	}
	
	public MemberTimeLimit(String id){
		super(id);
	}

	@Length(min=1, max=11, message="绑定商户ID长度必须介于 1 和 11 之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Length(min=1, max=8, message="起始时间长度必须介于 1 和 8 之间")
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	@Length(min=1, max=8, message="截止时间长度必须介于 1 和 8 之间")
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	
}
