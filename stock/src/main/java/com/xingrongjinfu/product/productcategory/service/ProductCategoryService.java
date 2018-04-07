package com.xingrongjinfu.product.productcategory.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.product.productcategory.dao.IProductCategoryDao;
import com.xingrongjinfu.product.productcategory.model.ProductCategory;
import com.xingrongjinfu.system.permission.model.Permission; 

/**
 * 权限管理 业务处理
 * 
 * @author y
 */
@Service("productCategoryService")
public class ProductCategoryService implements IProductCategoryService
{

    @Autowired
    private IProductCategoryDao productCategoryDao;

    /**
     * 查询所有权限信息
     * 
     * @return 权限集合
     */
    public List<ProductCategory> queryProductCategory()
    {
        return productCategoryDao.queryProductCategory();
    }

    
  
 
    /**
     * 根据父菜单ID查询
     * 
     * @param menuId 菜单ID
     * @return 菜单对象
     */
    public List<ProductCategory> findProductCategoryByPid(String parentId)
    {
        return productCategoryDao.findProductCategoryByPid(parentId);
    }

    
    /**
     * 删除商品分类节点
     * 
     * @param productCategory
     * @return
     */
    public int deleteProductCategory(ProductCategory productCategory)
    {
        return productCategoryDao.deleteProductCategory(productCategory);
    }

    /**
     * 新增商品分类节点
     * 
     * @param productCategory
     * @return
     */
    public int insertProductCategory(ProductCategory productCategory)
    {
        return productCategoryDao.insertProductCategory(productCategory);
    }
    
    /**
     * 修改商品分类节点
     * 
     * @param productCategory
     * @return
     */
    public int updateProductCategory(ProductCategory productCategory)
    {
        return productCategoryDao.updateProductCategory(productCategory);
    }

}
