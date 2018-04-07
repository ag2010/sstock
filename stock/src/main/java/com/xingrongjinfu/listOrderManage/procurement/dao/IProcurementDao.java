package com.xingrongjinfu.listOrderManage.procurement.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.listOrderManage.procurement.model.Procurement;
import com.xingrongjinfu.listOrderManage.procurement.model.ProcurementScrapOrder;
import com.xingrongjinfu.product.productinfo.model.Product;
import com.xingrongjinfu.system.syscode.model.SysCode; 

/**
 * 权限管理 数据层
 * 
 * @author y
 */
public interface IProcurementDao
{

    /**
     * 查询所有采购单列表
     * 
     * @return 采购单集合
     */
    public List<Procurement> queryProcurementList(PageUtilEntity pageUtilEntity);
 
    public List<ProcurementScrapOrder> getOrderListByProcurementId(PageUtilEntity pageUtilEntity);
    public  Procurement  getProcurementInfoById(String id); 
    
    public int saveProcurementCheck(Procurement p);
    public String getProcurementNo();
    
    public int saveProcurement(Procurement p);
    public int saveProcurementOrders(List<ProcurementScrapOrder> list);
    
    public List<Procurement> getExpProcurementList(Map<String,String> map);
    
    public List<Procurement> getPassedProcurementList(String type);
}
