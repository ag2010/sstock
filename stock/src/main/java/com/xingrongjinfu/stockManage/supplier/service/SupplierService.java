package com.xingrongjinfu.stockManage.supplier.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xingrongjinfu.stockManage.supplier.dao.ISupplierDao;
import com.xingrongjinfu.stockManage.supplier.model.Supplier;

//供货商业务处理
@Service("supplierService")
public class SupplierService implements ISupplierService{

	@Autowired
    private ISupplierDao supplierDao;
	
	//添加
	public int addSupplier(Supplier supplier) {
		
		return supplierDao.addSupplier(supplier);
	}

    //修改
	public int updateSupplier(Supplier supplier) {
		
		return supplierDao.updateSupplier(supplier);
	}

	//依据id查找
	public Supplier findSupplierById(String id) {
		
		return supplierDao.findSupplierById(id);
	}

    //依据id删除
	public int deleteSupplier(String id) {
		
		return supplierDao.deleteSupplier(id);
	}

    //带分页查询列表
	public List<Supplier> pageInfoQuery(PageUtilEntity pageUtilEntity) {

		return supplierDao.pageInfoQuery(pageUtilEntity);
	}


	//修改状态
	public int changeStatus(Supplier supplier) {
		
		return supplierDao.changeStatus(supplier);
	}

	//导出供货商信息
	public List<Supplier> getExpSupplierList(PageUtilEntity pageUtilEntity) {
	
		return supplierDao.getExpSupplierList(pageUtilEntity);
	}

	//导入供货商信息
	public int insertImpSupplierList(List<Supplier> list) {
	
		return supplierDao.impSupplierList(list);
	}

	

}
