package com.xingrongjinfu.listOrderManage.delivery.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;
import com.xingrongjinfu.listOrderManage.delivery.model.Delivery;
import com.xingrongjinfu.utils.IdUtil;

//配货单数据处理
@Repository("deliveryDao")
public class DeliveryDao extends DynamicObjectBaseDao implements IDeliveryDao{

   //条件分页查询
	@SuppressWarnings("unchecked")
	public List<Delivery> pageInfoQuery(PageUtilEntity pageUtilEntity) {
		  List<Delivery> supplierPageInfo = null;
	        try
	        {
	        	supplierPageInfo = (List<Delivery>) this.findForList("DeliveryMapper.pageInfoQuery", pageUtilEntity);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return supplierPageInfo;
	}

	//依据id查询
	public Delivery findDeliveryById(String id) {
		return (Delivery) this.findForObject("DeliveryMapper.findDeliveryById", id);
	}

	//导出信息
	public List<Delivery> getExpDeliveryList(Map<String, String> map) {
		 List<Delivery> permsList = null;
	        try
	        {
	            permsList = (List<Delivery>) this.findForList("DeliveryMapper.getExpDeliveryList", map);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return permsList;
	}

	
	//生成单号
/*	public String getDeliveryNo() {
		String maxNo=null;
    	String id_head="DO-"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"-";
    	try
        {
    		maxNo = (String) this.findForObject("DeliveryMapper.getDeliveryMaxNo", id_head);
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
*/
	//添加
	public int addDelivery(Delivery delivery) {
		
		return (int)this.save("DeliveryMapper.addDelivery", delivery);
	}

	//状态修改
	public int changeDeliveryStatus(Delivery delivery) {
		
		return (int)this.update("DeliveryMapper.changeDeliveryStatus", delivery);
	}

	//生成单号
	public String getMaxId() {
		
		return (String) findForObject("DeliveryMapper.getDeliveryMaxNo",null);
	}

}
