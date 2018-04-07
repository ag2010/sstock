package com.xingrongjinfu.listOrderManage.outStore.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.listOrderManage.listPurchase.model.ListPurchase;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.listOrderManage.outStore.model.OutStore;


//出库单数据层
public interface IOutStoreDao {

	//条件分页查询
	public List<OutStore> pageInfoQuery(PageUtilEntity pageUtilEntity);
	
	
	//依据出库单号查询
	public OutStore findOutStoreById(String id);
	
	//出库单信息导出
	public List<OutStore> getExpOutStoreList(Map<String,String> map);
	
	//添加出库单的信息
	public int addOutStore(OutStore outStore);
	
	
	//修改状态的方法
	public int changeStoreStatus(OutStore outStore);
	
	//生成出库单号的方法
	public String getMaxId();
	
	//出库库存更改
	public int updateProStock(List<Orders> list);
	
	//批量添加出库单订单信息
	public int saveOutStoreOrders(List<Orders> list);
}
