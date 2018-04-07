package com.xingrongjinfu.product.productcategory.dao;

import java.util.List;

import com.xingrongjinfu.product.productcategory.model.ProductCategory;
import com.xingrongjinfu.system.permission.model.Permission; 

/**
 * 权限管理 数据层
 * 
 * @author y
 */
public interface IProductCategoryDao
{

    /**
     * 查询所有商品分类信息
     * 
     * @return 商品分类集合
     */
    public List<ProductCategory> queryProductCategory();

     
 
 
    /**
     * 根据父菜单ID查询
     * 
     * @param   商品分类pid
     * @return 商品分类对象
     */
    public List<ProductCategory> findProductCategoryByPid(String parentId);
    
    /**
     * 修改商品分类
     * 
     * @param menu 商品分类对象
     * @return  
     */
    public int deleteProductCategory(ProductCategory ProductCategory);
    
    
    /**
     * 新增商品分类
     * 
     * @param productCategory 商品分类对象
     * @return  
     */
    public int  insertProductCategory(ProductCategory productCategory);

    /**
     * 修改商品分类
     * 
     * @param productCategory 商品分类对象
     * @return  
     */
    public int  updateProductCategory(ProductCategory productCategory);

}
