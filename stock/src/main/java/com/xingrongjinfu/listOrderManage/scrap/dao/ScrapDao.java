package com.xingrongjinfu.listOrderManage.scrap.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.listOrderManage.procurement.model.Procurement;
import com.xingrongjinfu.listOrderManage.procurement.model.ProcurementScrapOrder;
import com.xingrongjinfu.listOrderManage.scrap.model.Scrap;

/**
 *
 *@Author cj
 *@Date 2018年1月17日 下午3:24:41
 *@Version 1.0
 */
@Repository("scrapDao")
public class ScrapDao extends DynamicObjectBaseDao implements IScrapDao{

	@Override
	public List<Scrap> queryScrapList(PageUtilEntity pageUtilEntity) {
		 List<Scrap> permsList = null;
	        try
	        {
	            permsList = (List<Scrap>) this.findForList("ScrapMapper.scrappageInfoQuery", pageUtilEntity);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return permsList;
	}
	
	 public List<ProcurementScrapOrder> getScrapOrderById(String id){
	    	List<ProcurementScrapOrder> p=null;
	    	try
	        {
	            p = (List<ProcurementScrapOrder>) this.findForObject("ScrapMapper.getScrapOrderByOrderId", id);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return p;
	    	
	    }
	    
	    
	    public List<ProcurementScrapOrder> getOrderListByScrapId(PageUtilEntity pageUtilEntity){
	    	List<ProcurementScrapOrder> p=null;
	    	try
	        {
	            p = (List<ProcurementScrapOrder>) this.findForList("ScrapMapper.scrapOrderpageInfoQuery", pageUtilEntity);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return p;
	    }
	    public  Scrap  getScrapInfoById(String id){
	    	Scrap p=null;
	    	try
	        {
	            p = (Scrap) this.findForObject("ScrapMapper.getScrapInfoById", id);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return p;
	    }
	    
	    public int saveScrapCheck(Scrap p){
	    	int a=0;
	    	try
	        {
	            a = this.save("ScrapMapper.saveScrapCheck", p);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return a;
	    }
	    
	    public String getScrapNo(){
	    	String maxNo=null;
	    	String id_head="SC-"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"-";
	    	try
	        {
	    		Map<String, Object> map=new HashMap<String, Object>();
	    		map.put("id_head", id_head);
	    		map.put("date", new Date());
	    		maxNo = (String) this.findForObject("ScrapMapper.getScrapMaxNo", map);
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
	    
	    public int saveScrap(Scrap p){ 
	         return this.save("ScrapMapper.saveScrap", p);
	    }
	    public int saveScrapOrders(List<ProcurementScrapOrder> list){ 
	         return this.batchSave("ProcurementMapper.saveProcurementOrders", list);
	    }
	    
	    public List<Scrap> getExpScrapList(Map<String,String> map){
	    	List<Scrap> list=null;
	    	try
	        {
	    		list = (List<Scrap>) this.findForList("ScrapMapper.getExpScrapList", map);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return list;
	    }

}

