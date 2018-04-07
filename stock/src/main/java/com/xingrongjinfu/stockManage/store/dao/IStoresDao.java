package com.xingrongjinfu.stockManage.store.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import com.xingrongjinfu.stockManage.store.model.Stores;
/**
 * 类描述 : 仓库管理数据访问接口
 * 类名称 : IStoresDao
 * 创建人 : xa
 * 创建时间 : 2018年1月9日
 * version: v1.0
 */
public interface IStoresDao {
    
	    /**
		 * 根据仓库编号查询仓库管理信息 
		 * 
		 * @param storesId 仓库编号
	     * @return 仓库管理集合信息
		 */
		public Stores findStoresById(String storesId); 

		
		
	    /**
	     * 分页查询仓库管理信息
	     * 
	     * @param page 分页对象
	     * @return 仓库管理集合信息
	     */
	    public List<Stores> pageInfoQuery(PageUtilEntity pageUtilEntity);
	    
	    
	    
	    
	    
	    /**
	     * 新增仓库信息
	     * 
	     * @param shops 仓库管理信息
	     * @return 结果
	     */
	    public int addStoresInfo(Stores stores);

	    /**
	     * 修改仓库信息
	     * 
	     * @param shops 仓库管理信息
	     * @return 结果
	     */
	    public int updateStoresInfo(Stores stores);

	    /**
	     * 删除仓库信息
	     * 
	     * @param shops 仓库管理信息
	     * @return 结果
	     */
	    public int deleteStoresById(String storesId);
		
	    
	    /**
	     * 修改仓库门店状态
	     * 
	     * @param shops 仓库管理信息
	     * @return 结果
	     */
	    public int changeStoresStatus(Stores stores);
	
	
}