package com.xingrongjinfu.product.productinfo.common;

/**
 * 权限管理 常量信息
 * 
 * @author y
 */
public class ProductInfoConstant
{

    /**
     * 请求地址：跳转至商品资料页面
     */
    public final static String PRODUCT_URL = "productView";
 
    /**
     * 请求地址：跳转至商品添加页面
     */
    public final static String PRODUCT_HANDLE_URL = "toProductHandle";
    
    /**
     * 请求地址：查询商品分类树
     */
    public final static String PRODUCT_LIST_URL = "productList";
    
    /**
     * 请求地址：删除商品分类
     */
    public final static String PRODUCT_DEL_URL = "delProduct";
 
    /**
     * 请求地址：新增商品分类/修改商品分类
     */
    public final static String PRODUCT_SAVE_URL = "saveProduct";
    
    /**
     * 请求地址：获取商品操作页面select框信息
     */
    public final static String PRODUCT_SELOPTIONS_URL = "querySelOptions";
    
    /**
     * 请求地址：导出商品excel
     */
    public final static String PRODUCT_EXP_URL = "expProductList";
    
    /**
     * 请求地址：导入商品excel
     */
    public final static String PRODUCT_IMP_URL = "impProductList";
    
    
    public final static String PRODUCT_IMP_MODEL_URL="getImpExcelModel";
    
    public final static String PRODUCT_TOIMPPAGE_URL="toImpPage";
 
    public final static String PRODUCTTREE_URL="getProductTree";
    
   
    
    /**
     * 逻辑视图名：查询菜单列表界面地址
     */
    public final static String PRODUCT_PAGE = "product/product-list";
    /**
     * 逻辑视图名：查询菜单列表界面地址
     */
    public final static String PRODUCT_HANDLE_PAGE = "product/product-handle";
    
    /**
     * 逻辑视图名：导入界面地址
     */
    public final static String PRODUCT_TOIMPPAGE_PAGE = "product/import-iframe";
    
    

}
