package com.xingrongjinfu.listOrderManage.procurement.model;

import java.util.Date;

/**
 *
 *@Author cj
 *@Date 2018年1月9日 上午9:26:28
 *@Version 1.0
 */
public class ProcurementScrapOrder { 
	
	 private String  id;  //'订单编号',
	 private String  categoryId;  //'分类编号',
	 private int  number;  //'数量 根据类型有 采购量 报废量',
	 private String  unit;  //'单位',
	 private Float  price;  //'采购单价 分为单位',
	 private Float  totalPrice;  //'小计价格 分为单位',
	 private String  objId;  //'类型对应的编号',
	 private int  objType;  //'类型 1采购单 2报废单',
	 private String  remark;  //'备注',
	 private Date  addTime;  //'新增时间',
	 private Date  updateTime;  //'修改时间',
	 
	 private int  no; 
	 private String categoryName;
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	 
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public int getObjType() {
		return objType;
	}
	public void setObjType(int objType) {
		this.objType = objType;
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
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	 
 

}

