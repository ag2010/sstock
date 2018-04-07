package com.xingrongjinfu.listOrderManage.outStore.model;

import java.io.Serializable;
import java.util.Date;

import com.xingrongjinfu.stockManage.store.model.Stores;

//出库单对象
public class OutStore implements Serializable {

	private String no;//页面序号
	private String id; //编号
	private Date addTime; //新增时间
	//private Integer status; //状态 1审核中 2 完成
	//private Integer type; //类型 1正常出库单 2 报废单
	private String status;
	private String type;
	private String deliveryId; //配货单号
	private String storeName; //仓库名称
	private String remark; //备注
	private Date updateTime; //修改时间
	private Date outStoreTime; //出库时间
	private String storeId; //仓库编号
	private static final long serialVersionUID = 1L;
	
	private Stores store;//仓库
	
	public Stores getStore() {
		return store;
	}
	public void setStore(Stores store) {
		this.store = store;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getOutStoreTime() {
		return outStoreTime;
	}
	public void setOutStoreTime(Date outStoreTime) {
		this.outStoreTime = outStoreTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
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
	
	public OutStore(){}
	public OutStore(String id, Date outStoreTime, String status, String type, String deliveryId, String storeId,
			String remark, Date addTime, Date updateTime) {
		super();
		this.id = id;
		this.outStoreTime = outStoreTime;
		this.status = status;
		this.type = type;
		this.deliveryId = deliveryId;
		this.storeId = storeId;
		this.remark = remark;
		this.addTime = addTime;
		this.updateTime = updateTime;
	}
	
	
	
	
}
