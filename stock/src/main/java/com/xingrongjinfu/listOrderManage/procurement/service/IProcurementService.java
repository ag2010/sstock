package com.xingrongjinfu.listOrderManage.procurement.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.listOrderManage.procurement.model.Procurement;
import com.xingrongjinfu.listOrderManage.procurement.model.ProcurementScrapOrder;
import com.xingrongjinfu.product.productinfo.model.Product;
import com.xingrongjinfu.system.syscode.model.SysCode;
 

/**
 * 权限管理 业务�?
 * 
 * @author y
 */
public interface IProcurementService
{
    /**
    
     * 
     * @return 订购单列表
     */
    public List<Procurement> queryProcurementList(PageUtilEntity pageUtilEntity);

    public List<ProcurementScrapOrder> getOrderListByProcurementId(PageUtilEntity pageUtilEntity);
    public  Procurement  getProcurementInfoById(String id); 
    
    public String getProcurementNo();
    
    public int saveProcurementCheck(Procurement p);
    
    public int addProcurementInfo(Procurement p,List<ProcurementScrapOrder> list);
    
    public List<Procurement> getExpProcurementList(Map<String,String> map);
    
    public List<Procurement> getPassedProcurementList(String type);
    
}
