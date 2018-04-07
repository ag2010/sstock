package com.xingrongjinfu.listOrderManage.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.listOrderManage.order.dao.IOrderDao;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
//订单业务处理
@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderDao orderDao;
	
	public int addOrder(Orders order) {
	
		return orderDao.addOrder(order);
	}
	
	//依据Id查找
	public List<Orders> findOrder(String id) {
	
		return orderDao.findOrderById(id);
	}


	//批量添加
	public int saveOrders(List<Orders> list) {
		
		return orderDao.saveOrders(list);
	}

	
	

}
