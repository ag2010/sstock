package com.xingrongjinfu.stockManage.supplier.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.product.productinfo.model.Product;
import com.xingrongjinfu.stockManage.supplier.model.Supplier;
import com.xingrongjinfu.system.role.model.Role;

//供货商数据处理层

@Repository("supplierDao")
public class SupplierDao extends DynamicObjectBaseDao implements ISupplierDao {

    //新增处理
	public int addSupplier(Supplier supplier) {
	
		 return (int) this.save("SupplierMapper.addSupplier", supplier);
	}

	//修改处理
	public int updateSupplier(Supplier supplier) {
		
		return (int) this.update("SupplierMapper.updateSupplier",supplier);
	}

	

	
	//查询所有供货商信息
	 @SuppressWarnings("unchecked")
	    public List<Supplier> pageInfoQuery(PageUtilEntity pageUtilEntity)
	    {

	        List<Supplier> supplierPageInfo = null;
	        try
	        {
	        	supplierPageInfo = (List<Supplier>) this.findForList("SupplierMapper.pageInfoQuery", pageUtilEntity);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return supplierPageInfo;

	    }


    //依据id查询处理
	public Supplier findSupplierById(String id) {
	
		return (Supplier) this.findForObject("SupplierMapper.findSupplierById", id);
	}


    //依据id删除处理
	public int deleteSupplier(String id) {
		
		 return this.update("SupplierMapper.deleteSupplier", id);
	}

	
	public int changeStatus(Supplier supplier) {
		
		return this.update("SupplierMapper.changeStatus", supplier);
	}

    //导出信息
	public List<Supplier> getExpSupplierList(PageUtilEntity pageUtilEntity) {
		List<Supplier> permsList = null;
        try
        {
            permsList = (List<Supplier>) this.findForList("SupplierMapper.getExpSupplierList", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return permsList;
	}

	//导入信息
	public int impSupplierList(List<Supplier> list) {
		  int a = 0;
	        try
	        {
	            for(Supplier p:list){
	                p.setId(UUID.randomUUID().toString());
	            }
	            a =  this.batchSave("SupplierMapper.impSupplierList", list);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return a;
	    }

	

	
}
