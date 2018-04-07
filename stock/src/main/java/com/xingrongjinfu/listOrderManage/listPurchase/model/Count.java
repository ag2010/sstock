package com.xingrongjinfu.listOrderManage.listPurchase.model;

//用于订单详情中的统计
public class Count {

	private Integer totalProduct;//商品种类总数
	private Integer totalNum;//商品件数总数
	
	public Integer getTotalProduct() {
		return totalProduct;
	}
	public void setTotalProduct(Integer totalProduct) {
		this.totalProduct = totalProduct;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	
	public Count(){}
	public Count(Integer totalProduct, Integer totalNum) {
		
		this.totalProduct = totalProduct;
		this.totalNum = totalNum;
	}
	
	
	
	
	
}
