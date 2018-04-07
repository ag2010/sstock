package com.xingrongjinfu.stockManage.storewarn.service;

import java.util.List;
import java.util.Map;
import org.framework.base.util.PageUtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xingrongjinfu.stockManage.storewarn.dao.IStorewarnDao;
import com.xingrongjinfu.stockManage.storewarn.model.StoreWarn;

/**
 * 类描述 : 库存预警业务逻辑实现
 * 类名称 : StorewarnService
 * 创建人 : xa
 * 创建时间 : 2018年1月17日
 * version: v1.0
 */
@Service("storewarnService")
public class StorewarnService implements IStorewarnService{

	
	@Autowired
	private IStorewarnDao storeWarnDao; 
	
	/**
     * 查询所有库存预警信息
     * 
     * @return 库存预警信息集合
     */
	public List<StoreWarn> queryStoresWarnList(PageUtilEntity pageUtilEntity) {
		return storeWarnDao.queryStoresWarnList(pageUtilEntity);
	}

	
	/**
     * 导出库存预警信息
     * 
     * @param Stores 库存预警信息
     * @return 结果       
     */
	public List<StoreWarn> getExpStoresWarnList(Map<String, String> map) {
		return storeWarnDao.getExpStoresWarnList(map);
	}

	
	
	
	
	
}
