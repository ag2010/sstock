package com.xingrongjinfu.listOrderManage.outStore.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.listOrderManage.outStore.model.OutStore;


//出库单数据处理
@Repository("outStoreDao")
public class OutStoreDao extends DynamicObjectBaseDao implements IOutStoreDao{

	    //分页条件查询
	@SuppressWarnings("unchecked")
	public List<OutStore> pageInfoQuery(PageUtilEntity pageUtilEntity) {
		  List<OutStore> supplierPageInfo = null;
	        try
	        {
	        	supplierPageInfo = (List<OutStore>) this.findForList("OutStoreMapper.pageInfoQuery", pageUtilEntity);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return supplierPageInfo;
	}

	//依据id查询
	public OutStore findOutStoreById(String id) {
		
		return (OutStore)this.findForObject("OutStoreMapper.findOutStoreById", id);
	}

	//导出出库单信息
	public List<OutStore> getExpOutStoreList(Map<String, String> map) {
		 List<OutStore> permsList = null;
	        try
	        {
	            permsList = (List<OutStore>) this.findForList("OutStoreMapper.getExpPurchaseList", map);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return permsList;
	}
	
	
	//添加出库单信息
	public int addOutStore(OutStore outStore) {
		
		return (int)this.save("OutStoreMapper.addOutStore", outStore);
	}

	//修改状态
	public int changeStoreStatus(OutStore outStore) {
		
		return (int)this.update("OutStoreMapper.chagneOutStoreStatus", outStore) ;
	}

	//单号生成
/*	public String getOutStoreNo() {
		String maxNo=null;
    	String id_head="WO-"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"-";
    	try
        {
    		maxNo = (String) this.findForObject("OutStoreMapper.getOutStoreMaxNo", id_head);
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
	}*/

	
	public String getMaxId() {
		
		return  (String) findForObject("OutStoreMapper.getOutStoreMaxNo",null);
	}

	//出库库存更改
	public int updateProStock(List<Orders> list) {
	
		return this.batchSave("OutStoreMapper.updateProStock", list);
	}

	//批量添加出库单订单信息
	public int saveOutStoreOrders(List<Orders> list) {
		
		 return this.batchSave("OutStoreMapper.saveOutStoreOrders", list);
	}
	
	
	
		
		
    

	
}
