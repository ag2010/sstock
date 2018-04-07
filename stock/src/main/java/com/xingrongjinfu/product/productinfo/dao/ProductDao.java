package com.xingrongjinfu.product.productinfo.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.product.productinfo.model.Product;
import com.xingrongjinfu.system.syscode.model.SysCode;

/**
 * 权限管理 数据层处理
 *
 * @author y
 */
@Repository("productDao")
public class ProductDao extends DynamicObjectBaseDao implements IProductDao
{
    /**
     * 查询所有商品分类
     *
     * @return 商品分类集合
     */
    @SuppressWarnings("unchecked")
    public List<Product> queryProductList(PageUtilEntity pageUtilEntity)
    {
        List<Product> permsList = null;
        try
        {
            permsList = (List<Product>) this.findForList("ProductMapper.productpageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return permsList;
    }

    public  Product queryProductById(String productId)
    {
        Product product = null;
        try
        {
            product = (Product) this.findForObject("ProductMapper.getProductById", productId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return product;
    }
    
    public  Product queryProductByBarCode(String barCode)
    {
        Product product = null;
        try
        {
            product = (Product) this.findForObject("ProductMapper.getProductByBarCode", barCode);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return product;
    }

    /**
     * 查询商品操作页面的select框信息
     *
     * @return options
     */
    @SuppressWarnings("unchecked")
    public List<SysCode> getProAreaOptions(){
        List<SysCode> optionsList = null;
        try
        {
            optionsList= (List<SysCode>) this.findForList("ProductMapper.getProAreaOptions");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return optionsList;
    };
    @SuppressWarnings("unchecked")
    public List<SysCode> getProUnitOptions(){
        List<SysCode> optionsList = null;
        try
        {
            optionsList= (List<SysCode>) this.findForList("ProductMapper.getProUnitOptions");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return optionsList;
    };
    @SuppressWarnings("unchecked")
    public List<SysCode> getProTypeOptions(){
        List<SysCode> optionsList = null;
        try
        {
            optionsList= (List<SysCode>) this.findForList("ProductMapper.getProTypeOptions");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return optionsList;
    };
    @SuppressWarnings("unchecked")
    public List<SysCode> getProSupplierOptions(){
        List<SysCode> optionsList = null;
        try
        {
            optionsList= (List<SysCode>) this.findForList("ProductMapper.getProSupplierOptions");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return optionsList;
    }
    
    public List<SysCode> getProNameOptions(){
    	  List<SysCode> optionsList = null;
          try
          {
              optionsList= (List<SysCode>) this.findForList("ProductMapper.getProNameOptions");
          }
          catch (Exception e)
          {
              e.printStackTrace();
          }
          return optionsList;
      }
    
    public List<SysCode> getProStoreOptions(){
    	List<SysCode> optionsList = null;
        try
        {
            optionsList= (List<SysCode>) this.findForList("ProductMapper.getProStoreOptions");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return optionsList;
    } 

    @Override
    public String queryStock(String barCode) {
        return (String) findForObject("ProductMapper.queryStock",barCode);
    }


    public int editProduct(Product product){
        int a=0;
        try
        {
            a=  this.update("ProductMapper.editProduct",product);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return a;
    }
    public int addProduct(Product product){
        int a=0;
        try
        {
            a=  this.save("ProductMapper.addProduct",product);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return a;
    }

    public int delProduct(Product product){
        int a=0;
        try
        {
            a=  this.update("ProductMapper.deleteProduct",product);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return a;
    }

    public List<Product> getExpProductList(Map<String,String> map){
        List<Product> permsList = null;
        try
        {
            permsList = (List<Product>) this.findForList("ProductMapper.getExpProductList", map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return permsList;
    }

    public int impProductList(List<Product> list){
        int a = 0;
        try
        {
            for(Product p:list){
                p.setProductId(UUID.randomUUID().toString());
            }
            a =  this.batchSave("ProductMapper.impProductList", list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return a;
    }
    
    public List<Map<String,String>> getDictionaryMap(String type){
    	List<Map<String,String>> list = null;
        try
        {
        	list= (List<Map<String,String>>) this.findForList("ProductMapper.getDictionaryMap",type);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    
    public List<Product> getAllProduct(){
    	List<Product> list = null;
        try
        {
        	list= (List<Product>) this.findForList("ProductMapper.getAllProduct");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
}
