package com.xingrongjinfu.listOrderManage.order.dao;

import java.util.List;
import com.xingrongjinfu.listOrderManage.order.model.Orders;


//订单数据接口
public interface IOrderDao {

	//添加订单
	public int addOrder(Orders order);
	
	//依据订单号查询订单
	 public List<Orders> findOrderById(String id);
	
	//批量添加
	 public int saveOrders(List<Orders> list);
	  
	
}
