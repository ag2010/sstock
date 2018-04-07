package com.xingrongjinfu.product.productcategory.service;

import java.util.List;

import com.xingrongjinfu.product.productcategory.model.ProductCategory; 

/**
 * 权限管理 业务�?
 * 
 * @author y
 */
public interface IProductCategoryService
{
    /**
    
     * 
     * @return 权限集合
     */
    public List<ProductCategory> queryProductCategory();

    
 
    /**
     * 根据父菜单ID查询
     * 
     * @param menuId 菜单ID
     * @return 商品列表对象
     */
    public List<ProductCategory> findProductCategoryByPid(String parentId);
    
    /**
     * 删除商品分类节点
     * 
     * @param productCategory
     * @return  
     */
    public int  deleteProductCategory(ProductCategory productCategory);
    
    /**
     * 新增商品分类节点
     * 
     * @param productCategory
     * @return  
     */
    public int  insertProductCategory(ProductCategory productCategory);
    
    /**
     * 修改商品分类节点
     * 
     * @param productCategory
     * @return  
     */
    public int  updateProductCategory(ProductCategory productCategory);
 
}
