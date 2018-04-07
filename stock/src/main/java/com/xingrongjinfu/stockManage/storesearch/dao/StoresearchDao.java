package com.xingrongjinfu.stockManage.storesearch.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.stockManage.store.model.Stores;
import com.xingrongjinfu.stockManage.storesearch.model.StoreInfo;
import com.xingrongjinfu.stockManage.storewarn.model.StoreWarn;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.system.user.model.User;

/**
 * 类描述 : 库存查询数据访问接口实现
 * 类名称 : StoresearchDao
 * 创建人 : xa
 * 创建时间 : 2018年1月16日
 * version: v1.0
 */
@Repository("storesearchDao")
public class StoresearchDao extends DynamicObjectBaseDao implements IStoresearchDao{

	
	/**
     * 查询商品仓库的select框信息 
     * 
     * @return options
     */
	@SuppressWarnings("all")
	public List<SysCode> getStoreNameOptions() {
		List<SysCode> optionsList = null;
		try {
			optionsList = (List<SysCode>) this.findForList("GoodsStoresMapper.getStoreNameOptions");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return optionsList;
	}
	
	
	
	
	 /**
     * 查询所有库存商品信息
     * 
     * @return 库存商品集合
     */
	@SuppressWarnings("all")
	public List<Stores> queryStoresList(PageUtilEntity pageUtilEntity){
		  List<Stores> storesList = null;
	        try
	        {
	        	storesList = (List<Stores>) this.findForList("GoodsStoresMapper.storepageInfoQuery", pageUtilEntity);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return storesList;
	}

	
	

	/**
     * 根据条形码查询商品信息
     * 
     * @return 库存商品集合
     */
	@SuppressWarnings("all")
	public List<StoreInfo> getStoreInfoListByBarcode(PageUtilEntity pageUtilEntity){
		List<StoreInfo> storeInfoList = null;
		try {
			storeInfoList = (List<StoreInfo>) this.findForList("GoodsStoresMapper.StoreInfopageInfoQuery",pageUtilEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return 	storeInfoList;	
	}
	
	
	
	
	 /**
     * 导出库存信息
     * 
     * @param shops 库存信息
     * @return 结果
     */
	@SuppressWarnings("all")
	public List<Stores> getExpStoresList(Map<String, String> map){
		List<Stores> ExpstoresList = null;
		 try
	        {
			 ExpstoresList = (List<Stores>) this.findForList("GoodsStoresMapper.getExpStoresList", map);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
		
		
		return ExpstoresList;
	}





	
	
	
}
