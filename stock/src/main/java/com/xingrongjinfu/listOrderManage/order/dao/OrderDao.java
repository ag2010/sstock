package com.xingrongjinfu.listOrderManage.order.dao;

import java.util.List;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;
import com.xingrongjinfu.listOrderManage.order.model.Orders;


//订单数据实现层
@Repository("orderDao")
public class OrderDao extends DynamicObjectBaseDao implements IOrderDao {

	//添加订单
	public int addOrder(Orders order) {
	
		return this.save("OrderMapper.addOrder", order);
	}
	
	//依据订单号查询订单
	@SuppressWarnings("unchecked")
	public List<Orders> findOrderById(String id) {

		List<Orders> optionsList = null;
        try
        {
        	optionsList= (List<Orders>) this.findForList("OrderMapper.findOrderById",id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return optionsList; 
	}
	
	//批量添加
	public int saveOrders(List<Orders> list) {
		 int a = 0;
         try
         { 
             a =  this.batchSave("OrderMapper.saveOrders", list);
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
         return a;
	}

	


}
