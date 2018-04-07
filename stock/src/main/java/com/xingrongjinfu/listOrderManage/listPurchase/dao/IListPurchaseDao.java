package com.xingrongjinfu.listOrderManage.listPurchase.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import com.xingrongjinfu.listOrderManage.listPurchase.model.ListPurchase;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.system.syscode.model.SysCode;


//订货单数据接口
public interface IListPurchaseDao {

    //条件分也不查询
    public List<ListPurchase> pageInfoQuery(PageUtilEntity pageUtilEntity);

    //依据工单号查询
    public ListPurchase findListPurchaseById(String id);

    //导出订货单信息
    public List<ListPurchase> getExpPurchaseList(Map<String, String> map);

    //获取最大ID
    String getMaxId();

    //添加工单
    int addPurchase(ListPurchase listPurchase);
    
  //修改状态
  	public int changeStatus(ListPurchase purchase);
  	
  	  	
  	//商家
  	 public List<SysCode> getShopOptions();
  	
  	 public int saveInputStoreOrders(List<Orders> list);
}
