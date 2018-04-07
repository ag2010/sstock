package com.xingrongjinfu.listOrderManage.delivery.model;

import java.io.Serializable;
import java.util.Date;

import com.xingrongjinfu.shop.shopsmanage.model.Shops;
import com.xingrongjinfu.stockManage.store.model.Stores;

//配货单对象
public class Delivery implements Serializable{
	
	private String no;
	private String id; //编号
	private Date addTime; //新增时间
	private String name;//商家名称
	//private Integer status; //状态
	private String status;
	private String storeName;//仓库名称
	private String purchaseId; //订货单号
	private String outStoreId; //出库单号
	private String remark; //备注
	private Date deliveryTime; //派单时间
	private Date updateTime; //修改时间
	private String shopId; //商家编号
	private static final long serialVersionUID = 1L;
	
	private String storeId; //仓库编号
	private Shops shop;//商家
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Shops getShop() {
		return shop;
	}
	public void setShop(Shops shop) {
		this.shop = shop;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getOutStoreId() {
		return outStoreId;
	}
	public void setOutStoreId(String outStoreId) {
		this.outStoreId = outStoreId;
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
	
	public Delivery(){}
	
	public Delivery(String id, Date deliveryTime, String shopId, String status, String storeId, String purchaseId,
			String outStoreId, String remark, Date addTime, Date updateTime) {
		super();
		this.id = id;
		this.deliveryTime = deliveryTime;
		this.shopId = shopId;
		this.status = status;
		this.storeId = storeId;
		this.purchaseId = purchaseId;
		this.outStoreId = outStoreId;
		this.remark = remark;
		this.addTime = addTime;
		this.updateTime = updateTime;
	}
	
	
	
}
