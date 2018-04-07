package com.xingrongjinfu.listOrderManage.delivery.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import com.xingrongjinfu.listOrderManage.delivery.model.Delivery;

//配货单数据接口
public interface IDeliveryDao {

	// 条件分也不查询
	public List<Delivery> pageInfoQuery(PageUtilEntity pageUtilEntity);

	// 依据工单号查询
	public Delivery findDeliveryById(String id);
	
	//导出订货单信息

    public List<Delivery> getExpDeliveryList(Map<String,String> map);
	
	//单号的生成方法
    // public String getDeliveryNo();
    public  String getMaxId();
     
    //添加配货单信息
     public int addDelivery(Delivery delivery);
     
    //修改状态的方法
     public int changeDeliveryStatus(Delivery delivery);

}




