package com.xingrongjinfu.stockManage.store.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.xingrongjinfu.product.productinfo.model.Product;
/**
 * 
 * 类描述 : 仓库管理类
 * 类名称 : Shops
 * 创建人 : xa
 * 创建时间 : 2018年1月9日
 * version: v1.0
 */
@SuppressWarnings("serial")
public class Stores implements Serializable{
	
	//vo  页面序号展示  
	private String  no;
	private String code;     //内部编码
	private String productName;    //产品名称
	private String barCode;    //条码
	private String categoryName;    //商品类别
	private String storeName;   //仓库名称
	private String stock;    //库存
	private String unit;     //单位
	private Integer stockTop;   //库存上限
	private Integer stockDown;   //库存下限
	private String  expiryDate;   //保质期
	private String name;   //供货商名称
	//"序号","内部编码","商品名称","条码","分类","仓库","库存","单位"
	//private static final long serialVersionUID = 1L;   //导入导出时注意注释这个UID，避免反射找不到get方法

	

    
    private String id;  //编号

    private String address;   //仓库地址

    private Integer status;   //状态 1为启用 0为弃用

    private Date addTime;   //新增时间

    private Date updateTime;   //修改时间

    List<Product> products;   //商品集合
 
    
    private String categoryId;    //商品分类id


	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getBarCode() {
		return barCode;
	}


	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public String getStock() {
		return stock;
	}


	public void setStock(String stock) {
		this.stock = stock;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
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


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public String getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


	public Integer getStockTop() {
		return stockTop;
	}


	public void setStockTop(Integer stockTop) {
		this.stockTop = stockTop;
	}


	public Integer getStockDown() {
		return stockDown;
	}


	public void setStockDown(Integer stockDown) {
		this.stockDown = stockDown;
	}


	

	public String getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	
}