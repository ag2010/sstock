package com.xingrongjinfu.listOrderManage.listPurchase.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.listOrderManage.inputStore.model.InputStore;
import com.xingrongjinfu.listOrderManage.listPurchase.model.ListPurchase;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.system.syscode.model.SysCode;

//配货单业务处理
public interface IListPurchaseService {

    //条件分页查询
    public List<ListPurchase> pageInfoQuery(PageUtilEntity pageUtilEntity);

    //依据id查询
    public ListPurchase findListPurchaseById(String id);

    //导出信息
    public List<ListPurchase> getExpPurchaseList(Map<String, String> map);

    //生成单号
    String getMaxId();

    //添加信息
    int addPurchase(ListPurchase listPurchase);
     
    //修改状态
	public int changeStatus(ListPurchase purchase);
		
	//商家
	 public List<SysCode> getShopOptions();
	 
	 //添加订单信息
	 public int  addPurchaseInfo(ListPurchase p,List<Orders> list);
}
