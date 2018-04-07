package com.xingrongjinfu.listOrderManage.inputStore.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;

import com.xingrongjinfu.listOrderManage.inputStore.model.InputStore;
import com.xingrongjinfu.listOrderManage.order.model.Orders; 

/**
 *
 *@Author cj
 *@Date 2018年1月17日 下午3:25:04
 *@Version 1.0
 */
public interface IInputStoreDao {
	public List<InputStore> queryInputStoreList(PageUtilEntity pageUtilEntity);
	
	public List<Orders> getOrderListByInputStoreId(PageUtilEntity pageUtilEntity);
    public  InputStore  getInputStoreInfoById(String id); 
    
    public String getInputStoreNo();
    
    public int saveInputStoreCheck(InputStore p);
    
    public int saveInputStore(InputStore p);
    public int saveInputStoreOrders(List<Orders> list);
    public int updateProStock(List<Orders> list);
    
    public List<InputStore> getExpInputStoreList(Map<String,String> map);
}

