package com.xingrongjinfu.shop.shopsmanage.dao;

import java.util.List;
import java.util.Map;
import org.framework.base.util.PageUtilEntity;
import com.xingrongjinfu.shop.shopsmanage.model.Shops;
/**
 * 类描述 : 商家管理数据访问接口
 * 类名称 : IShopsDao
 * 创建人 : xa
 * 创建时间 : 2018年1月9日
 * version: v1.0
 */
public interface IShopsDao {

    
    /**
	 * 根据商家编号查询商家管理信息 
	 * 
	 * @param id 商家编号
     * @return 商家管理集合信息
	 */
	public Shops findShopsById(String id); 

	
	
    /**
     * 分页查询商家管理信息
     * 
     * @param page 分页对象
     * @return 商家管理集合信息
     */
    public List<Shops> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    
    
    /**
     * 新增商家信息
     * 
     * @param shops 商家管理信息
     * @return 结果
     */
    public int addShopsInfo(Shops shops);

    /**
     * 修改商家信息
     * 
     * @param shops 商家管理信息
     * @return 结果
     */
    public int updateShopsInfo(Shops shops);

    /**
     * 删除商家信息
     * 
     * @param shops 商家管理信息
     * @return 结果
     */
    public int deleteShopsInfo(String shopId);
	
    
    /**
     * 修改商家门店状态
     * 
     * @param shops 商家管理信息
     * @return 结果
     */
    public int changeShopsStatus(Shops shops);


    /**
     * 导出商家管理信息
     * 
     * @param shops 商家管理信息
     * @return 结果
     */
    public List<Shops> getExpShopsList(Map<String,String> map);
    
    
    /**
     * 导入商家管理信息
     * 
     * @param shops 商家管理信息
     * @return 结果
     */
    public int impShopsList(List<Shops> list);

    boolean existId(String id);
    
    
}
