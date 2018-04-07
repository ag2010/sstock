package com.xingrongjinfu.listOrderManage.procurement.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingrongjinfu.listOrderManage.inputStore.model.InputStore;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.listOrderManage.procurement.dao.IProcurementDao;
import com.xingrongjinfu.listOrderManage.procurement.model.Procurement;
import com.xingrongjinfu.listOrderManage.procurement.model.ProcurementScrapOrder; 
/**
 * 权限管理 业务处理
 * 
 * @author y
 */
@Service("procurementService")
public class ProcurementService implements IProcurementService
{

    @Autowired
    private IProcurementDao procurementDao;

     
    public List<Procurement> queryProcurementList(PageUtilEntity pageUtilEntity)
    {
        return procurementDao.queryProcurementList(pageUtilEntity);
    }
 
    
    public List<ProcurementScrapOrder> getOrderListByProcurementId(PageUtilEntity pageUtilEntity){
    	return procurementDao.getOrderListByProcurementId(pageUtilEntity);
    }
    public  Procurement  getProcurementInfoById(String id){
    	return procurementDao.getProcurementInfoById(id);
    }
    
    public int saveProcurementCheck(Procurement p){
    	return procurementDao.saveProcurementCheck(p);
    }
    
    public String getProcurementNo(){
    	return procurementDao.getProcurementNo();
    }
     
    @Transactional(rollbackFor = Exception.class)
    public int addProcurementInfo(Procurement p,List<ProcurementScrapOrder> list){
    	int a=procurementDao.saveProcurement(p);
    		a=procurementDao.saveProcurementOrders(list);
    	return a; 
    }
    
    public List<Procurement> getExpProcurementList(Map<String,String> map){
    	return procurementDao.getExpProcurementList(map);
    }
    
    public List<Procurement> getPassedProcurementList(String type){
    	return procurementDao.getPassedProcurementList(type);
    }
}
