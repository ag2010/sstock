package com.xingrongjinfu.listOrderManage.scrap.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingrongjinfu.listOrderManage.inputStore.model.InputStore;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.listOrderManage.procurement.model.Procurement;
import com.xingrongjinfu.listOrderManage.procurement.model.ProcurementScrapOrder;
import com.xingrongjinfu.listOrderManage.scrap.dao.IScrapDao;
import com.xingrongjinfu.listOrderManage.scrap.model.Scrap;

/**
 *
 *@Author cj
 *@Date 2018年1月17日 下午3:25:55
 *@Version 1.0
 */
@Service("scrapService")
public class ScrapService implements IScrapService{
	@Autowired
    private IScrapDao scrapDao;
	
	public List<Scrap> queryScrapList(PageUtilEntity pageUtilEntity){
		return scrapDao.queryScrapList(pageUtilEntity);
	}
	
	public List<ProcurementScrapOrder> getOrderListByScrapId(PageUtilEntity pageUtilEntity){
	    	return scrapDao.getOrderListByScrapId(pageUtilEntity);
    }
    public  Scrap  getScrapInfoById(String id){
    	return scrapDao.getScrapInfoById(id);
    }
    
    public int saveScrapCheck(Scrap p){
    	return scrapDao.saveScrapCheck(p);
    }
    
    public String getScrapNo(){
    	return scrapDao.getScrapNo();
    }
     
    @Transactional(rollbackFor = Exception.class)
    public int addScrapInfo(Scrap p,List<ProcurementScrapOrder> list){
    	int a=scrapDao.saveScrap(p);
    		a=scrapDao.saveScrapOrders(list); 
    	return a; 
    }
    
    public List<Scrap> getExpScrapList(Map<String,String> map){
    	return scrapDao.getExpScrapList(map);
    }
}

