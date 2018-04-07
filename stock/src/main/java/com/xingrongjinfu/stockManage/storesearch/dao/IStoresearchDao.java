package com.xingrongjinfu.stockManage.storesearch.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;

import com.xingrongjinfu.stockManage.store.model.Stores;
import com.xingrongjinfu.stockManage.storesearch.model.StoreInfo;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.system.user.model.User;
/**
 * 类描述 : 库存查询数据访问接口
 * 类名称 : IStoresearchDao
 * 创建人 : xa
 * 创建时间 : 2018年1月16日
 * version: v1.0
 */
public interface IStoresearchDao {
	
	
	 /**
     * 查询商品仓库的select框信息 
     * 
     * @return options
     */
    public List<SysCode> getStoreNameOptions();
	

	 /**
     * 查询所有库存商品信息
     * 
     * @return 库存商品集合
     */
    public List<Stores> queryStoresList(PageUtilEntity pageUtilEntity);
	
    
    
    /**
     * 根据条形码查询商品信息
     * 
     * @return 库存商品集合
     */
    public List<StoreInfo> getStoreInfoListByBarcode(PageUtilEntity pageUtilEntity);
    
    
    
    /**
     * 导出库存信息
     * 
     * @param shops 库存信息
     * @return 结果       
     */
    public List<Stores> getExpStoresList(Map<String,String> map);
    
    
    
}
