package com.xingrongjinfu.stockManage.storewarn.dao;

import java.util.List;
import java.util.Map;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;
import com.xingrongjinfu.stockManage.storewarn.model.StoreWarn;

/**
 * 类描述 : 库存预警数据访问接口实现
 * 类名称 : StorewarnDao
 * 创建人 : xa
 * 创建时间 : 2018年1月17日
 * version: v1.0
 */
@Repository("storewarnDao")
public class StorewarnDao extends DynamicObjectBaseDao implements IStorewarnDao{

	
	/**
     * 查询所有库存预警信息
     * 
     * @return 库存预警信息集合
     */
	@SuppressWarnings("all")
	public List<StoreWarn> queryStoresWarnList(PageUtilEntity pageUtilEntity){
		List<StoreWarn> storesWarnList = null;
		try {
			storesWarnList = (List<StoreWarn>) this.findForList("GoodsStoresMapper.storeWarnpageInfoQuery", pageUtilEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return storesWarnList;
	}

	
	/**
     * 导出库存预警信息
     * 
     * @param Stores 库存预警信息
     * @return 结果       
     */
	@SuppressWarnings("all")
	public List<StoreWarn> getExpStoresWarnList(Map<String, String> map){
		List<StoreWarn> ExpstoreWarnList = null;
		try {
			ExpstoreWarnList = (List<StoreWarn>) this.findForList("GoodsStoresMapper.getExpStoreWarnList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExpstoreWarnList;
	}

	
	
	
	
	
}
