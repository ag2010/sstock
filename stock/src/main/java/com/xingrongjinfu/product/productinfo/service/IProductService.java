package com.xingrongjinfu.product.productinfo.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.product.productinfo.model.Product;
import com.xingrongjinfu.system.syscode.model.SysCode;
 

/**
 * 权限管理 业务�?
 * 
 * @author y
 */
public interface IProductService
{
    /**
    
     * 
     * @return 商品列表
     */
    public List<Product> queryProductList(PageUtilEntity pageUtilEntity);

    
    /**
     * 查询商品详细信息
     * 
     * @return 商品 
     */
    public  Product queryProductById(String productId);
    
    
    /**
     * 查询商品详细信息
     * 
     * @return 商品 
     */
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

    public String queryStock(String barCode);
    
    
    
    public int editProduct(Product product);  
    public int addProduct(Product product); 
    public int deleteProduct(Product product); 
    
    
    public List<Product> getExpProductList(Map<String,String> map);
    public int insertBatchProduct(List<Product> list);
    
    //通过类型获取字典树列表
    public List<Map<String,String>> getDictionaryMap(String type);
    
    public List<Product> getAllProduct();
 
    public String getXgfUrl();
}
