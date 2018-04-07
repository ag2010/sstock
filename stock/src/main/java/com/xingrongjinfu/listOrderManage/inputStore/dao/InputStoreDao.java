package com.xingrongjinfu.listOrderManage.inputStore.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.listOrderManage.inputStore.model.InputStore;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.listOrderManage.procurement.model.Procurement;
import com.xingrongjinfu.listOrderManage.procurement.model.ProcurementScrapOrder;
import com.xingrongjinfu.listOrderManage.scrap.model.Scrap;

/**
 *
 *@Author cj
 *@Date 2018年1月17日 下午3:24:41
 *@Version 1.0
 */
@Repository("inputStoreDao")
public class InputStoreDao extends DynamicObjectBaseDao implements IInputStoreDao{

	@Override
	public List<InputStore> queryInputStoreList(PageUtilEntity pageUtilEntity) {
		 List<InputStore> permsList = null;
	        try
	        {
	            permsList = (List<InputStore>) this.findForList("InputStoreMapper.inputStorepageInfoQuery", pageUtilEntity);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return permsList;
	}
	
	 public List<Orders> getScrapOrderById(String id){
	    	List<Orders> p=null;
	    	try
	        {
	            p = (List<Orders>) this.findForObject("InputStoreMapper.getInputStoreOrderByOrderId", id);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return p;
	    	
	    }
	    
	    
	    public List<Orders> getOrderListByInputStoreId(PageUtilEntity pageUtilEntity){
	    	List<Orders> p=null;
	    	try
	        {
	            p = (List<Orders>) this.findForList("InputStoreMapper.inputStoreOrderpageInfoQuery", pageUtilEntity);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return p;
	    }
	    public  InputStore  getInputStoreInfoById(String id){
	    	InputStore p=null;
	    	try
	        {
	            p = (InputStore) this.findForObject("InputStoreMapper.getInputStoreInfoById", id);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return p;
	    }
	    
	    public int saveInputStoreCheck(InputStore p){
	    	int a=0;
	    	try
	        {
	            a = this.save("InputStoreMapper.saveInputStoreCheck", p);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return a;
	    }
	    
	    public String getInputStoreNo(){
	    	String maxNo=null;
	    	String id_head="WI-"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"-";
	    	try
	        {
	    		Map<String, Object> map=new HashMap<String, Object>();
	    		map.put("id_head", id_head);
	    		map.put("date", new Date());
	    		maxNo = (String) this.findForObject("InputStoreMapper.getInputStoreMaxNo", map);
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
	    
	    public int saveInputStore(InputStore p){  
	         return this.save("InputStoreMapper.saveInputStore", p);
	    }
	    public int saveInputStoreOrders(List<Orders> list){ 
	         return this.batchSave("InputStoreMapper.saveInputStoreOrders", list);
	    }
	    public int updateProStock(List<Orders> list){ 
	         return this.batchSave("InputStoreMapper.updateProStock", list);
	    }
	     
	    
	    public List<InputStore> getExpInputStoreList(Map<String,String> map){
	    	List<InputStore> list=null;
	    	try
	        {
	    		list = (List<InputStore>) this.findForList("InputStoreMapper.getExpInputStoreList", map);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return list;
	    }

}

