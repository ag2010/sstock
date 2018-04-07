package com.xingrongjinfu.stockManage.storewarn.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 类描述 : 库存预警展示类
 * 类名称 : StoreWarn
 * 创建人 : xa
 * 创建时间 : 2018年1月17日
 * version: v1.0
 */
@SuppressWarnings("serial")
public class StoreWarn implements Serializable{

	    //vo    
		private String  no;    //页面序号展示
		private String productName;    //产品名称
		private String barCode;    //条码
		private String name;   //供货商名称
		private String unit;     //单位
		private String categoryName;    //商品类别
		private Integer stockTop;   //库存上限
		private Integer stockDown;   //库存下限
		private String stock;    //库存
		private String expiryDate;   //保质期
		private String productId;   //产品编号
		private String categoryId;   //类别编号
		private Integer status;   //商品状态   1启用 0弃用
		private String code;     //内部编码
		private String storeName;    //仓库
		
		public String getNo() {
			return no;
		}
		public void setNo(String no) {
			this.no = no;
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
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
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
		public String getStock() {
			return stock;
		}
		public void setStock(String stock) {
			this.stock = stock;
		}
		public String getExpiryDate() {
			return expiryDate;
		}
		public void setExpiryDate(String expiryDate) {
			this.expiryDate = expiryDate;
		}
		public String getProductId() {
			return productId;
		}
		public void setProductId(String productId) {
			this.productId = productId;
		}
		public String getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getStoreName() {
			return storeName;
		}
		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}
		
		
		
		
	
	
}
