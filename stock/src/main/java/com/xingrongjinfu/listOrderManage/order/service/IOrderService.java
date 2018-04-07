package com.xingrongjinfu.listOrderManage.order.service;

import java.util.List;

import com.xingrongjinfu.listOrderManage.order.model.Orders;

//订单业务处理接口
public interface IOrderService {

	//添加订单
	public int addOrder(Orders order);
	
	//依据订单号查询订单
	public List<Orders> findOrder(String id);
	
	//批量添加
	  public int saveOrders(List<Orders> list);
}
