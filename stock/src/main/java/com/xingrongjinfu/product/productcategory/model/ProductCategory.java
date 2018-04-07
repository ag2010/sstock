package com.xingrongjinfu.product.productcategory.model;

import java.util.Date;

/**
 *
 *@Author cj
 *@Date 2018年1月9日 上午9:26:28
 *@Version 1.0
 */
public class ProductCategory { 
	
	 private String categoryId;  //'类别编号',
	 private String  categoryName;  //'类别名称',
	 private String parentId;  //'父级编号',
	 private Date addTime;  //'新增时间',
	 private Date updateTime;  //'修改时间',
	 private Integer status;  //'状态 1启用 0弃用',
	 private Integer sort;  //'排序',
	 private String remark;  //'备注',
	 
	 private String parentName;   
	 
	 
	 public String toString()
	    {
	        return "productCategory [categoryId=" + categoryId + ","
	        		+ " categoryName=" + categoryName + ","
	        		+ " parentId=" + parentId + ","
	        		+ " addTime="+ addTime + ","
	        		+ " updateTime=" + updateTime + ","
	        		+ " status=" + status + ","
	        		+ " sort=" + sort
	                + ", remark=" + remark +  "]";
	    }

	 
	 
	 
	public String getCategoryId() {
		return categoryId;
	}




	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}




	public String getParentId() {
		return parentId;
	}




	public void setParentId(String parentId) {
		this.parentId = parentId;
	}




	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}




	public String getParentName() {
		return parentName;
	}




	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	 
	 

}

