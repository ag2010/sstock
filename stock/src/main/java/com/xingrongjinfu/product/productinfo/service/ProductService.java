package com.xingrongjinfu.product.productinfo.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.product.productinfo.dao.IProductDao;
import com.xingrongjinfu.product.productinfo.model.Product;
import com.xingrongjinfu.system.syscode.model.SysCode;

/**
 * 权限管理 业务处理
 *
 * @author y
 */
@Service("productService")
public class ProductService implements IProductService {

    @Autowired
    private IProductDao productDao;
    
    @Value("${xgfUrl}")
	public String xgfUrl;

    /**
     * 查询所有权限信息
     *
     * @return 权限集合
     */
    public List<Product> queryProductList(PageUtilEntity pageUtilEntity) {
        return productDao.queryProductList(pageUtilEntity);
    }

    /**
     * 查询商品详细信息
     *
     * @return 商品
     */
    public Product queryProductById(String productId) {

        return productDao.queryProductById(productId);
    }
    
    /**
     * 查询商品详细信息
     *
     * @return 商品
     */
    public Product queryProductByBarCode(String barCode) {

        return productDao.queryProductByBarCode(barCode);
    }


    /**
     * 查询商品操作页面的select框信息
     *
     * @return options
     */
    public List<SysCode> getProAreaOptions() {
        return productDao.getProAreaOptions();
    }

    public List<SysCode> getProUnitOptions() {
        return productDao.getProUnitOptions();
    }

    public List<SysCode> getProTypeOptions() {
        return productDao.getProTypeOptions();
    }

    public List<SysCode> getProSupplierOptions() {
        return productDao.getProSupplierOptions();
    }
    public List<SysCode> getProNameOptions(){
        return productDao.getProNameOptions();
    }
    public List<SysCode> getProStoreOptions(){
        return productDao.getProStoreOptions();
    }
    

    @Override
    public String queryStock(String barCode) {
        return productDao.queryStock(barCode);
    }

    public int editProduct(Product product) {
        return productDao.editProduct(product);
    }

    public int addProduct(Product product) {
        return productDao.addProduct(product);
    }

    public int deleteProduct(Product product) {
        return productDao.delProduct(product);
    }

    /**
     * 获取导出数据
     */
    public List<Product> getExpProductList(Map<String, String> map) {
        return productDao.getExpProductList(map);
    }

    public int insertBatchProduct(List<Product> list) {
        return productDao.impProductList(list);
    }
    
    public List<Map<String,String>> getDictionaryMap(String type){
    	 return productDao.getDictionaryMap(type);
    }
    
    public List<Product> getAllProduct(){
    	 return productDao.getAllProduct();
    }

	@Override
	public String getXgfUrl() {
		// TODO Auto-generated method stub
		return xgfUrl;
	}
}
