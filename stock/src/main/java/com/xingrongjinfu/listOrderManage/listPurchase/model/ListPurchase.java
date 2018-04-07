package com.xingrongjinfu.listOrderManage.listPurchase.model;


import java.io.Serializable;
import java.util.Date;

import com.xingrongjinfu.shop.shopsmanage.model.Shops;
import com.xingrongjinfu.stockManage.store.model.Stores;


//订货单对象
public class ListPurchase implements Serializable{

private String no;//页面序号
private String id; //订货单编号
private Date purchaseTime; //订货时间
private String purchaser; //订货人
private String name;//商家名称
private String storeName;//仓库名称
//private Integer status;  // 状态：0已拒绝 1待审核 2配货中 3配送中 4已完成 9已作废
private String status;
private String deliveryId; //配货单号
private String remark; //备注
private Date addTime; //新增时间
private Date updateTime; //修改时间
private String shopId; //订货商铺编号
private String storeId; //仓库编号
private static final long serialVersionUID = 1L;
private String phone;
private String address;
private Shops shop;//商家
private Stores store;//仓库

public String getPhone(){
	return phone;
}
public void setPhone(String phone){
	this.phone=phone;
}
public String getAddress(){
	return address;
}
public String getStoreName() {
	return storeName;
}

public void setAddress(String address){
	this.address=address;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
}
public void setStoreName(String storeName) {
	this.storeName = storeName;
}

public Stores getStore() {
	return store;
}
public void setStore(Stores store) {
	this.store = store;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
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
public Date getPurchaseTime() {
	return purchaseTime;
}
public void setPurchaseTime(Date purchaseTime) {
	this.purchaseTime = purchaseTime;
}
public String getPurchaser() {
	return purchaser;
}
public void setPurchaser(String purchaser) {
	this.purchaser = purchaser;
}
public String getShopId() {
	return shopId;
}
public void setShopId(String shopId) {
	this.shopId = shopId;
}
public String getStoreId() {
	return storeId;
}
public void setStoreId(String storeId) {
	this.storeId = storeId;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getDeliveryId() {
	return deliveryId;
}
public void setDeliveryId(String deliveryId) {
	this.deliveryId = deliveryId;
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

public Shops getShop() {
	return shop;
}
public void setShop(Shops shop) {
	this.shop = shop;
}
public ListPurchase(){}

public ListPurchase(String id, Date purchaseTime, String purchaser, String shopId, String storeId, String status,
		String deliveryld, String remark, Date addTime, Date updateTime) {
	super();
	this.id = id;
	this.purchaseTime = purchaseTime;
	this.purchaser = purchaser;
	this.shopId = shopId;
	this.storeId = storeId;
	this.status = status;
	this.deliveryId = deliveryld;
	this.remark = remark;
	this.addTime = addTime;
	this.updateTime = updateTime;
};





}
