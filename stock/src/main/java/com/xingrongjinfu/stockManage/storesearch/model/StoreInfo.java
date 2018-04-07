package com.xingrongjinfu.stockManage.storesearch.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 类描述 : 仓库详细信息类
 * 类名称 : StroreInfo
 * 创建人 : xa
 * 创建时间 : 2018年1月25日
 * version: v1.0
 */
@SuppressWarnings("all")
public class StoreInfo implements Serializable{

	
	//vo    
	private String  inputId;    //入库单编号
	private String  outId;     //出库单编号
	private String  orderId;   //订单编号
	private String  objId;   //类型对应的编号
	private String  barCode;    //条码
	private String  productName;    //产品名称
	private String  categoryName;    //商品类别
	private Date  inputTime;    //入库时间
	private Date  outTime;    //出库时间
	private Integer  number;   //出库量   入库量
	
	
	public String getInputId() {
		return inputId;
	}
	public void setInputId(String inputId) {
		this.inputId = inputId;
	}
	public String getOutId() {
		return outId;
	}
	public void setOutId(String outId) {
		this.outId = outId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
	
	
}
