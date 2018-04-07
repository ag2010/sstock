package com.xingrongjinfu.listOrderManage.procurement.model;

import java.util.Date;

/**
 *
 *@Author cj
 *@Date 2018年1月9日 上午9:26:28
 *@Version 1.0
 */
public class Procurement { 
	
	private int no;
	private String id;   //'采购单编号',
	private Date procurementTime;   //'采购时间',
	private String status;   //'1审核中 2已完成',
	private String name;  //'供应商名称',
	private String storeName; //'仓库名称', 
	private String remark;   //'备注',
	
	private String supplierId;   //'供应商ID',
	private String storeId;   //'仓库编号',
	
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
	public Date getProcurementTime() {
		return procurementTime;
	}
	public void setProcurementTime(Date procurementTime) {
		this.procurementTime = procurementTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
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

