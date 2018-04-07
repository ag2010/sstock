package com.xingrongjinfu.listOrderManage.inputStore.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingrongjinfu.listOrderManage.inputStore.dao.IInputStoreDao;
import com.xingrongjinfu.listOrderManage.inputStore.model.InputStore;
import com.xingrongjinfu.listOrderManage.order.model.Orders; 

/**
 *
 *@Author cj
 *@Date 2018年1月17日 下午3:25:55
 *@Version 1.0
 */
@Service("inputStoreService")
public class InputStoreService implements IInputStoreService{
	@Autowired
    private IInputStoreDao inputStoreDao;
	
	public List<InputStore> queryInputStoreList(PageUtilEntity pageUtilEntity){
		return inputStoreDao.queryInputStoreList(pageUtilEntity);
	}
	
	public List<Orders> getOrderListByInputStoreId(PageUtilEntity pageUtilEntity){
	    	return inputStoreDao.getOrderListByInputStoreId(pageUtilEntity);
    }
    public  InputStore  getInputStoreInfoById(String id){
    	return inputStoreDao.getInputStoreInfoById(id);
    }
    
    public int saveInputStoreCheck(InputStore p){
    	return inputStoreDao.saveInputStoreCheck(p);
    }
    
    public String getInputStoreNo(){
    	return inputStoreDao.getInputStoreNo();
    }
     
    @Transactional(rollbackFor = Exception.class)
    public int addInputStoreInfo(InputStore p,List<Orders> list){
    	int a=inputStoreDao.saveInputStore(p);
    		a=inputStoreDao.saveInputStoreOrders(list);  
    		a=inputStoreDao.updateProStock(list); 
    	return a; 
    }
    
    public List<InputStore> getExpInputStoreList(Map<String,String> map){
    	return inputStoreDao.getExpInputStoreList(map);
    }
}

