package com.xingrongjinfu.shop.shopsmanage.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;
import com.xingrongjinfu.shop.shopsmanage.model.Shops;
import com.xingrongjinfu.utils.UuidUtil;
/**
 * 类描述 : 商家管理数据访问接口实现
 * 类名称 : ShopsDao
 * 创建人 : xa
 * 创建时间 : 2018年1月9日
 * version: v1.0
 */
@Repository("shopsDao")
public class ShopsDao extends DynamicObjectBaseDao implements IShopsDao{

	
	
	/**
	 * 根据商家编号查询商家管理信息 
	 * 
	 * @param id 商家编号
     * @return 商家管理集合信息
	 */
	public Shops findShopsById(String id){
		return (Shops) this.findForObject("GoodsShopsMapper.findShopsById", id);
	}
	
	
	
	  /**
     * 分页查询商家管理信息
     * 
     * @param page 分页对象
     * @return 商家管理集合信息
     */
	@SuppressWarnings("all")
	public List<Shops> pageInfoQuery(PageUtilEntity pageUtilEntity){
		 List<Shops> shopsPageInfo = null;
	        try
	        {
	            shopsPageInfo = (List<Shops>) this.findForList("GoodsShopsMapper.pageInfoQuery", pageUtilEntity);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return shopsPageInfo;
	}

	
	
	
	 /**
     * 新增商家信息
     * 
     * @param shop 商家管理信息
     * @return 结果
     */
	public int addShopsInfo(Shops shops){
		return (int) this.save("GoodsShopsMapper.addShopsInfo", shops);
	}

	
	
	/**
     * 修改商家信息
     * 
     * @param shop 商家管理信息
     * @return 结果
     */
	public int updateShopsInfo(Shops shops){
	    return  this.update("GoodsShopsMapper.updateShopsInfo", shops);
	}

	
	
	/**
     * 删除商家信息
     * 
     * @param shop 商家管理信息
     * @return 结果
     */
	public int deleteShopsInfo(String shopId){
		return this.update("GoodsShopsMapper.deleteShopsInfo", shopId);
	}



	/**
     * 修改商家门店状态
     * 
     * @param shops 商家管理信息
     * @return 结果
     */
	public int changeShopsStatus(Shops shops){
		return this.update("GoodsShopsMapper.changeShopsStatus", shops);
	}



	 /**
     * 导出商家管理信息
     * 
     * @param shops 商家管理信息
     * @return 结果
     */
	@SuppressWarnings("all")
	public List<Shops> getExpShopsList(Map<String, String> map) {
		List<Shops> shopsList = null;
		try {
			shopsList = (List<Shops>) this.findForList("GoodsShopsMapper.getExpShopsList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shopsList;
	}



	/**
     * 导入商家管理信息
     * 
     * @param shops 商家管理信息
     * @return 结果
     */
	public int impShopsList(List<Shops> list) {
		int result = 0;
		try {
			for(Shops shops:list){
				shops.setId(UuidUtil.get32UUID());
				shops.setAddTime(new Date());
	    	}
			result =  this.batchSave("GoodsShopsMapper.impShopsList", list);
		} catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean existId(@Param("id") String id) {
		String name = (String) this.findForObject("GoodsShopsMapper.existId",id);
		if (name != null && !"".equals(name)){
			return true;
		}else {
			return false;
		}
	}


}
