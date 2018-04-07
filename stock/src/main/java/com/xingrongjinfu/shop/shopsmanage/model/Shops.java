package com.xingrongjinfu.shop.shopsmanage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * 类描述 : 商家管理类
 * 类名称 : Shops
 * 创建人 : xa
 * 创建时间 : 2018年1月9日
 * version: v1.0
 */
@SuppressWarnings("serial")
public class Shops implements Serializable{
  
	//vo   页面序号展示
    private String no;
	
    private String name;   //商家名称

    private String address;   //商家地址

    private String phone;   //移动电话

    private String tel;    //座机号码

    private String email;    //邮箱地址

    private String status;   //状态 1为启用 0为弃用

    private Date addTime;    //新增时间

    private Date updateTime;    //修改时间

    private String contacts;   //联系人

    private String id;  //商家编号
    
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static void main(String[] args) {
		double  num = 18.88888;
		BigDecimal big = new BigDecimal(num);
		//保留2位小数  
	    double result = big.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
	    System.out.println(result);  
	}
	
}