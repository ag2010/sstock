package com.xingrongjinfu.listOrderManage.inputStore.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;

import com.xingrongjinfu.listOrderManage.inputStore.model.InputStore;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
   

/**
 *
 *@Author cj
 *@Date 2018年1月17日 下午3:26:12
 *@Version 1.0
 */
public interface IInputStoreService {
	public List<InputStore> queryInputStoreList(PageUtilEntity pageUtilEntity);

	public List<Orders> getOrderListByInputStoreId(PageUtilEntity pageUtilEntity);
    public  InputStore  getInputStoreInfoById(String id); 
    
    public String getInputStoreNo();
    
    public int saveInputStoreCheck(InputStore p);
     
    
    public int  addInputStoreInfo(InputStore p,List<Orders> list);
    
    public List<InputStore> getExpInputStoreList(Map<String,String> map);
}

