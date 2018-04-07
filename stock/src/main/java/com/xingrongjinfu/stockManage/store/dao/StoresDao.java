package com.xingrongjinfu.stockManage.store.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;
import com.xingrongjinfu.stockManage.store.model.Stores;
/**
 * 类描述 : 仓库管理数据访问接口实现
 * 类名称 : StoresDao
 * 创建人 : xa
 * 创建时间 : 2018年1月12日
 * version: v1.0
 */
@Repository("storesDao")
public class StoresDao extends DynamicObjectBaseDao implements IStoresDao{

	/**
	 * 根据仓库编号查询仓库管理信息 
	 * 
	 * @param id 仓库编号
     * @return 仓库管理集合信息
	 */
	public Stores findStoresById(String storesId) {
		return (Stores) this.findForObject("GoodsStoresMapper.findStoresById", storesId);
	}

	
	 /**
     * 分页查询仓库管理信息
     * 
     * @param page 分页对象
     * @return 仓库管理集合信息
     */
	@SuppressWarnings("all")
	public List<Stores> pageInfoQuery(PageUtilEntity pageUtilEntity) {
		List<Stores> storesPageInfo = null;
		try {
			storesPageInfo = (List<Stores>) this.findForList("GoodsStoresMapper.pageInfoQuery", pageUtilEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return storesPageInfo;
	}

	/**
     * 新增仓库信息
     * 
     * @param shops 仓库管理信息
     * @return 结果
     */
	public int addStoresInfo(Stores stores) {
		return (int) this.save("GoodsStoresMapper.addStoresInfo", stores);
	}

	
	 /**
     * 修改仓库信息
     * 
     * @param shops 仓库管理信息
     * @return 结果
     */
	public int updateStoresInfo(Stores stores) {
		return this.update("GoodsStoresMapper.updateStoresInfo", stores);
	}

	
	 /**
     * 删除仓库信息
     * 
     * @param shops 仓库管理信息
     * @return 结果
     */
	public int deleteStoresById(String storesId) {
		return this.update("GoodsStoresMapper.deleteStoresById", storesId);
	}

	
	/**
     * 修改仓库门店状态
     * 
     * @param shops 仓库管理信息
     * @return 结果
     */
	public int changeStoresStatus(Stores stores) {
		return this.update("GoodsStoresMapper.changeStoresStatus", stores);
	}


	
	
}
