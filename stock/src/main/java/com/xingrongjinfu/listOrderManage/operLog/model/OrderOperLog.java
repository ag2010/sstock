package com.xingrongjinfu.listOrderManage.operLog.model;

import java.util.Date;

//审核日志对象
public class OrderOperLog {

	private String id; //审核日志编号
	private String userId; //用户编号
	private String objId; //被操作的单编号
	private Integer type; //操作类型：1通过 2 拒绝
	private Date addTime; //新增时间
	private Date updateTime; //修改时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public OrderOperLog(){}
	public OrderOperLog(String id, String userId, String objId, Integer type, Date addTime, Date updateTime) {
		
		this.id = id;
		this.userId = userId;
		this.objId = objId;
		this.type = type;
		this.addTime = addTime;
		this.updateTime = updateTime;
	}
	
	
	
	
	
}
