package com.xingrongjinfu.listOrderManage.procurement.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.listOrderManage.inputStore.model.InputStore;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.listOrderManage.procurement.model.Procurement;
import com.xingrongjinfu.listOrderManage.procurement.model.ProcurementScrapOrder;
import com.xingrongjinfu.product.productinfo.model.Product;
import com.xingrongjinfu.system.syscode.model.SysCode; 

/**
 * 权限管理 数据层处理
 * 
 * @author y
 */
@Repository("procurementDao")
public class ProcurementDao extends DynamicObjectBaseDao implements IProcurementDao
{
    /**
     * 查询所有商品分类
     * 
     * @return 商品分类集合
     */
    @SuppressWarnings("unchecked")
    public List<Procurement> queryProcurementList(PageUtilEntity pageUtilEntity)
    {
        List<Procurement> permsList = null;
        try
        {
            permsList = (List<Procurement>) this.findForList("ProcurementMapper.procurementpageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return permsList;
    }
    
    public List<ProcurementScrapOrder> getProcurementOrderById(String id){
    	List<ProcurementScrapOrder> p=null;
    	try
        {
            p = (List<ProcurementScrapOrder>) this.findForObject("ProcurementMapper.getProcurementOrderByOrderId", id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return p;
    	
    }
    
    
    public List<ProcurementScrapOrder> getOrderListByProcurementId(PageUtilEntity pageUtilEntity){
    	List<ProcurementScrapOrder> p=null;
    	try
        {
            p = (List<ProcurementScrapOrder>) this.findForList("ProcurementMapper.procurementOrderpageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return p;
    }
    public  Procurement  getProcurementInfoById(String id){
    	Procurement p=null;
    	try
        {
            p = (Procurement) this.findForObject("ProcurementMapper.getProcurementInfoById", id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return p;
    }
    
    public int saveProcurementCheck(Procurement p){
    	int a=0;
    	try
        {
            a = this.save("ProcurementMapper.saveProcurementCheck", p);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return a;
    }
    
    public String getProcurementNo(){
    	String maxNo=null;
    	String id_head="PO-"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"-";
    	try
        {
    		Map<String, Object> map=new HashMap<String, Object>();
    		map.put("id_head", id_head);
    		map.put("date", new Date());
    		maxNo = (String) this.findForObject("ProcurementMapper.getProcurementMaxNo", map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    	if(maxNo==null||maxNo.equals("")){
    		maxNo=id_head+"00001";
    	}else{
    		maxNo=id_head+ String.format("%05d", (Integer.parseInt(maxNo.split("-")[2])+1));
    	}
        return maxNo;
    }
     
    public int saveProcurement(Procurement p){ 
         return this.save("ProcurementMapper.saveProcurement", p);
    }
    public int saveProcurementOrders(List<ProcurementScrapOrder> list){ 
         return this.batchSave("ProcurementMapper.saveProcurementOrders", list);
    }
    
    public List<Procurement> getExpProcurementList(Map<String,String> map){
    	List<Procurement> list=null;
    	try
        {
    		list = (List<Procurement>) this.findForList("ProcurementMapper.getExpProcurementList", map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
 
	public List<Procurement> getPassedProcurementList(String type) {
		List<Procurement> list=null;
    	try
        {
    		Map<String,String> map=new HashMap<>();
    		map.put("type", type);
    		list = (List<Procurement>) this.findForList("ProcurementMapper.getPassedProcurementList",map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list; 
	}
}
