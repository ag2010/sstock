package com.xingrongjinfu.stockManage.storesearch.service;

import java.util.List;
import java.util.Map;
import org.framework.base.util.PageUtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xingrongjinfu.stockManage.store.model.Stores;
import com.xingrongjinfu.stockManage.storesearch.dao.IStoresearchDao;
import com.xingrongjinfu.stockManage.storesearch.model.StoreInfo;
import com.xingrongjinfu.stockManage.storewarn.model.StoreWarn;
import com.xingrongjinfu.system.syscode.model.SysCode;

/**
 * 类描述 : 库存查询业务逻辑实现
 * 类名称 : StoresearchService
 * 创建人 : xa
 * 创建时间 : 2018年1月16日
 * version: v1.0
 */
@Service("storesearchService")
public class StoresearchService implements IStoresearchService{

	@Autowired
	private IStoresearchDao storeSearchDao;
	
	
	/**
     * 查询商品仓库的select框信息 
     * 
     * @return options
     */
	public List<SysCode> getStoreNameOptions() {
		return storeSearchDao.getStoreNameOptions();
	}
	
	
	
	 /**
     * 查询所有库存商品信息
     * 
     * @return 库存商品集合
     */
	public List<Stores> queryStoresList(PageUtilEntity pageUtilEntity) {
		return storeSearchDao.queryStoresList(pageUtilEntity);
	}

	
	
	 /**
     * 根据条形码查询商品信息查询商品信息
     * 
     * @return 库存商品集合
     */
	public List<StoreInfo> getStoreInfoListByBarcode(PageUtilEntity pageUtilEntity){
		return storeSearchDao.getStoreInfoListByBarcode(pageUtilEntity);
	}

	
	
	
	
	/**
     * 导出库存信息
     * 
     * @param shops 库存信息
     * @return 结果
     */
	public List<Stores> getExpStoresList(Map<String, String> map) {
		return storeSearchDao.getExpStoresList(map);
	}



	
	
	
}
