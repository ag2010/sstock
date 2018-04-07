package com.xingrongjinfu.stockManage.supplier.model;

import java.io.Serializable;
import java.util.Date;


//供货商管理
@SuppressWarnings("serial")
public class Supplier implements Serializable {
     
	private String  no;//页面序号
	
	private String name;  //供货商名称
	private String address;  //供货商地址
	private String tel;  //座机电话
	private String phone;  //移动电话
	private String email;  //供货商邮箱
	//private Integer status;  //启用状态：1为启用   0为弃用
	private String status;
	private Date addTime;  //新增时间
	private Date updateTime;  //修改时间
	private String  id;  //供货商编号

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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
/*	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}*/
	
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status=status;
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
	
	public Supplier(){}
	
	public Supplier(String id, String name, String address, String tel, String phone, String email, String  status,
			Date addTime, Date updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.phone = phone;
		this.email = email;
		this.status = status;
		this.addTime = addTime;
		this.updateTime = updateTime;
	}
	
	
	
}
