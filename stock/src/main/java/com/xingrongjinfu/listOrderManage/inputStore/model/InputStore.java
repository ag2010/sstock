package com.xingrongjinfu.listOrderManage.inputStore.model;

import java.util.Date;

/**
 *
 *@Author cj
 *@Date 2018年1月17日 下午7:01:57
 *@Version 1.0
 */
public class InputStore {
	 private int  no;
	 private String  id;    //'报废单编号',
	 private Date  inputStoreTime;    //'报废时间',
	 private String  status;    //'1 审核中 2已完成', 
	 private String  procurementId;    //'采购单编号', 
	 private String  remark;    //'备注',
	 
	 private String  storeId;    //'仓库编号',
	 private String  type;    // '类型 1普通入库单 0门店退货入库单',
	
	 private Date  addTime;    //'新增时间',
	 private Date  updateTime;    //'修改时间',
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
	public Date getInputStoreTime() {
		return inputStoreTime;
	}
	public void setInputStoreTime(Date inputStoreTime) {
		this.inputStoreTime = inputStoreTime;
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
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
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
	 

}

