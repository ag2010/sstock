package com.xingrongjinfu.listOrderManage.scrap.model;

import java.util.Date;

/**
 *
 *@Author cj
 *@Date 2018年1月17日 下午3:47:29
 *@Version 1.0
 */
public class Scrap {
	private int no; 
	private String id;   //'报废单编号',
	private Date scrapTime;   //'报废时间',
	private String status;   //'1审核中 2已完成',
	private String procurementId;   //'采购单编号',
	private String remark;   //'备注',
	
	private Date addTime;   //'新增时间',
	private Date updateTime;   //'修改时间',
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getScrapTime() {
		return scrapTime;
	}
	public void setScrapTime(Date scrapTime) {
		this.scrapTime = scrapTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProcurementId() {
		return procurementId;
	}
	public void setProcurementId(String procurementId) {
		this.procurementId = procurementId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
	
}

