package com.xingrongjinfu.stockManage.store.service;

import java.util.Date;
import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xingrongjinfu.stockManage.store.dao.IStoresDao;
import com.xingrongjinfu.stockManage.store.model.Stores;
/**
 * 类描述 : 库存管理业务逻辑实现
 * 类名称 : StoresService
 * 创建人 : xa
 * 创建时间 : 2018年1月12日
 * version: v1.0
 */
@Service("storesService")
public class StoresService implements IStoresService {

	@Autowired
	private IStoresDao storesDao;
	
	
	/**
	 * 根据仓库编号查询仓库管理信息 
	 * 
	 * @param id 仓库编号
     * @return 仓库管理集合信息
	 */
	public Stores findStoresById(String storesId) {
		return storesDao.findStoresById(storesId);
	}

	
	 /**
     * 分页查询仓库管理信息
     * 
     * @param page 分页对象
     * @return 仓库管理集合信息
     */
	public List<Stores> pageInfoQuery(PageUtilEntity pageUtilEntity) {
		return storesDao.pageInfoQuery(pageUtilEntity);
	}

	
	 /**
     * 新增仓库信息
     * 
     * @param shops 仓库管理信息
     * @return 结果
     */
	public int addStoresInfo(Stores stores) {
		stores.setAddTime(new Date());
		return storesDao.addStoresInfo(stores);
	}

	
	/**
     * 修改仓库信息
     * 
     * @param shops 仓库管理信息
     * @return 结果
     */
	public int updateStoresInfo(Stores stores) {
		stores.setUpdateTime(new Date());
		return storesDao.updateStoresInfo(stores);
	}

	
	/**
     * 删除仓库信息
     * 
     * @param shops 仓库管理信息
     * @return 结果
     */
	public int deleteStoresById(String storesId) {
		return storesDao.deleteStoresById(storesId);
	}

	
	/**
     * 修改仓库门店状态
     * 
     * @param shops 仓库管理信息
     * @return 结果
     */
	public int changeStoresStatus(Stores stores) {
		return storesDao.changeStoresStatus(stores);
	}



}
