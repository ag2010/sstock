package com.xingrongjinfu.shop.shopsmanage.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.framework.base.util.PageUtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xingrongjinfu.shop.shopsmanage.dao.IShopsDao;
import com.xingrongjinfu.shop.shopsmanage.model.Shops;
/**
 * 类描述 : 商家管理业务逻辑实现
 * 类名称 : ShopsService
 * 创建人 : xa
 * 创建时间 : 2018年1月9日
 * version: v1.0
 */
@Service("shopsService")
public class ShopsService implements IShopsService {

	
	@Autowired
	private  IShopsDao shopsDao; 
	

	/**
	 * 根据商家编号查询商家管理信息 
	 * 
	 * @param id 商家编号
     * @return 商家管理集合信息
	 */
	public Shops findShopsById(String id) {
		return shopsDao.findShopsById(id);
	}
	
	
	
	
	/**
     * 分页查询商家管理信息
     * 
     * @param page 分页对象
     * @return 商家管理集合信息
     */
	public List<Shops> pageInfoQuery(PageUtilEntity pageUtilEntity) {
		return shopsDao.pageInfoQuery(pageUtilEntity);
	}

	
	
	
	/**
     * 新增商家信息
     * 
     * @param shops 商家管理信息
     * @return 结果
     */
	public int addShopsInfo(Shops shops) {
		shops.setAddTime(new Date());
		return shopsDao.addShopsInfo(shops);
	}

	
	
	/**
     * 修改商家信息
     * 
     * @param shops 商家管理信息
     * @return 结果
     */
	public int updateShopsInfo(Shops shops) {
		shops.setUpdateTime(new Date());
		return shopsDao.updateShopsInfo(shops);
	}

	
	
	/**
     * 删除商家信息
     * 
     * @param shops 商家管理信息
     * @return 结果
     */
	public int deleteShopsInfo(String shopId) {
		return shopsDao.deleteShopsInfo(shopId);
	}


	
	/**
     * 修改商家门店状态
     * 
     * @param shops 商家管理信息
     * @return 结果
     */
	public int changeShopsStatus(Shops shops) {
		return shopsDao.updateShopsInfo(shops);
	}



	/**
     * 导出商家管理信息
     * 
     * @param shops 商家管理信息
     * @return 结果
     */
	public List<Shops> getExpShopsList(Map<String, String> map) {
		return shopsDao.getExpShopsList(map);
	}



	/**
     * 导入商家管理信息
     * 
     * @param shops 商家管理信息
     * @return 结果
     */
	public int insertBatchShops(List<Shops> list) {
		return shopsDao.impShopsList(list);
	}

	@Override
	public boolean existId(String id) {
		return shopsDao.existId(id);
	}


}
