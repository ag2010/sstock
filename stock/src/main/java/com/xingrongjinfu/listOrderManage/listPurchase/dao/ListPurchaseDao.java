package com.xingrongjinfu.listOrderManage.listPurchase.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;
import com.xingrongjinfu.listOrderManage.listPurchase.model.ListPurchase;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.system.syscode.model.SysCode;


//订货单数据处理层
@Repository("listPurchaseDao")
public class ListPurchaseDao extends DynamicObjectBaseDao implements IListPurchaseDao {

	//条件分页查询
	@SuppressWarnings("unchecked")
	public List<ListPurchase> pageInfoQuery(PageUtilEntity pageUtilEntity) {
		  List<ListPurchase> supplierPageInfo = null;
	        try
	        {
	        	supplierPageInfo = (List<ListPurchase>) this.findForList("ListPurchaseMapper.pageInfoQuery", pageUtilEntity);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return supplierPageInfo;
	}

	//依据工单号查询
	public ListPurchase findListPurchaseById(String id) {
		

		return (ListPurchase) this.findForObject("ListPurchaseMapper.findListPurchaseById", id);
	}

	//订货单信息导出
	public List<ListPurchase> getExpPurchaseList(Map<String, String> map) {
	
		 List<ListPurchase> permsList = null;
	        try
	        {
	            permsList = (List<ListPurchase>) this.findForList("ListPurchaseMapper.getExpPurchaseList", map);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return permsList;
	}

	
	public String getMaxId() {
		return (String) findForObject("ListPurchaseMapper.getMaxId",null);
	}

	
	public int addPurchase(ListPurchase listPurchase) {
		return save("ListPurchaseMapper.addPurchase",listPurchase);
	}

	
	//状态修改
		public int changeStatus(ListPurchase purchase) {
			
			return (int)this.update("ListPurchaseMapper.changePurchaseStatus", purchase);
		}
		
		
		@SuppressWarnings("unchecked")
	    public List<SysCode> getShopOptions(){
	        List<SysCode> optionsList = null;
	        try
	        {
	            optionsList= (List<SysCode>) this.findForList("ListPurchaseMapper.getProSupplierOptions");
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return optionsList;
	    }

		
		public int saveInputStoreOrders(List<Orders> list) {
		
			 return this.batchSave("ListPurchaseMapper.savePurchaseOrders", list);
		}
}
