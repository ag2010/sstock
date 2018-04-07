package com.xingrongjinfu.stockManage.supplier.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.stockManage.supplier.model.Supplier;
import com.xingrongjinfu.system.user.model.User;

//供货商业务层
public interface ISupplierService {

	//新增供货商信息
    public int addSupplier(Supplier supplier);

    //修改供货商信息
    public int updateSupplier(Supplier supplier);
    
    //依据id查找供货商
    public Supplier findSupplierById(String id);
	
	//依据id删除供货商
    public int deleteSupplier(String id);
    
    //依据条件分页查找供货商信息
    public List<Supplier> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    //修改状态
    public int changeStatus(Supplier supplier);
 
    //导出供货商信息
    //public List<Supplier> getExpSupplierList(Map<String,String> map);
    public List<Supplier> getExpSupplierList(PageUtilEntity pageUtilEntity);
  //导入供货商信息
    public int insertImpSupplierList(List<Supplier> list);
}
