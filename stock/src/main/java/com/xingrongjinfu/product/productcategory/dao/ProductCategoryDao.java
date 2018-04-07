package com.xingrongjinfu.product.productcategory.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.product.productcategory.model.ProductCategory;
import com.xingrongjinfu.system.permission.model.Permission; 

/**
 * 权限管理 数据层处理
 * 
 * @author y
 */
@Repository("productCategoryDao")
public class ProductCategoryDao extends DynamicObjectBaseDao implements IProductCategoryDao
{
    /**
     * 查询所有商品分类
     * 
     * @return 商品分类集合
     */
    @SuppressWarnings("unchecked")
    public List<ProductCategory> queryProductCategory()
    {
        List<ProductCategory> permsList = null;
        try
        {
            permsList = (List<ProductCategory>) this.findForList("ProductCategoryMapper.queryProductCategory", null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return permsList;
    }

  

 
    /**
     * 根据父菜单ID查询
     * 
     * @param productCategoryid  
     * @return 商品分类对象
     */
    @SuppressWarnings("unchecked")
    public List<ProductCategory> findProductCategoryByPid(@Param(value = "parentId") String parentId)
    {
        List<ProductCategory> parentList = null;
        try
        {
            parentList = (List<ProductCategory>) this.findForList("ProductCategoryMapper.findProductCategoryByPid", parentId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return parentList;
    }
    

    /**
     * 删除商品类别
     * 
     * @param   商品类别
     * @return
     */
    public int deleteProductCategory(ProductCategory productCategory)
    {
        return (int) this.delete("ProductCategoryMapper.deleteProductCategory", productCategory);
    }

    /**
     * 新增商品分类
     * 
     * @param 商品分类
     * @return
     */
    public int insertProductCategory(ProductCategory productCategory)
    {
        return (int) this.save("ProductCategoryMapper.insertProductCategory", productCategory);
    }

    /**
     * 修改商品分类
     * 
     * @param 商品分类
     * @return
     */
    public int updateProductCategory(ProductCategory productCategory)
    {
        return (int) this.update("ProductCategoryMapper.updateProductCategory", productCategory);
    }
}
