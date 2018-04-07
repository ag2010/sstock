package com.xingrongjinfu.listOrderManage.scrap.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;

import com.xingrongjinfu.listOrderManage.procurement.model.ProcurementScrapOrder;
import com.xingrongjinfu.listOrderManage.scrap.model.Scrap;

/**
 *
 *@Author cj
 *@Date 2018年1月17日 下午3:25:04
 *@Version 1.0
 */
public interface IScrapDao {
	public List<Scrap> queryScrapList(PageUtilEntity pageUtilEntity);
	
	public List<ProcurementScrapOrder> getOrderListByScrapId(PageUtilEntity pageUtilEntity);
    public  Scrap  getScrapInfoById(String id); 
    
    public String getScrapNo();
    
    public int saveScrapCheck(Scrap p);
    
    public int saveScrap(Scrap p);
    public int saveScrapOrders(List<ProcurementScrapOrder> list);
    
    public List<Scrap> getExpScrapList(Map<String,String> map);
}

