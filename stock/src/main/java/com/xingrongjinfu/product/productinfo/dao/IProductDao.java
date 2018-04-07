package com.xingrongjinfu.product.productinfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.product.productinfo.model.Product;
import com.xingrongjinfu.system.syscode.model.SysCode; 

/**
 * 权限管理 数据层
 * 
 * @author y
 */
public interface IProductDao
{

    /**
     * 查询所有商品信息
     * 
     * @return 商品集合
     */
    public List<Product> queryProductList(PageUtilEntity pageUtilEntity);
 
    /**
     * 查询所有商品详细信息
     * 
     * @return 商品 
     */
    public  Product queryProductById(String productId);
    
    public  Product queryProductByBarCode(String barCode);
    
    /**
     * 查询商品操作页面的select框信息
     * 
     * @return options
     */
    public List<SysCode> getProAreaOptions(); 
    public List<SysCode> getProUnitOptions();
    public List<SysCode> getProTypeOptions();
    public List<SysCode> getProSupplierOptions();
    public List<SysCode> getProNameOptions();
    public List<SysCode> getProStoreOptions();

    String queryStock(@Param("barCode") String barCode);
    
    
    
    public int editProduct(Product product);  
    public int addProduct(Product product); 
    public int delProduct(Product product); 
    
    
    public List<Product> getExpProductList(Map<String,String> map);
    public int impProductList(List<Product> list);
    
    public List<Map<String,String>> getDictionaryMap(String type);
    
    public List<Product> getAllProduct();
}
