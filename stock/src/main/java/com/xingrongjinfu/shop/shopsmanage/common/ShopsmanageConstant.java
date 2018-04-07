package com.xingrongjinfu.shop.shopsmanage.common;

/**
 * 类描述 : 商家管理常量信息
 * 类名称 : ShopsConstant
 * 创建人 : xa
 * 创建时间 : 2018年1月9日
 * version: v1.0
 */
public class ShopsmanageConstant{


	 /**
     * 请求地址：跳转至商家管理   
     */
    public final static String SHOPSCATEGORY_URL = "shopsView";
	
    
    
    
    /**
     * 逻辑视图名：查询商家管理列表界面       要跳转的页面
     */
    public final static String SHOPSCATEGORY_PAGE = "shops/shops-list";
    
    
    /**
     * 逻辑视图名：商家管理添加界面
     */
    public final static String ADD_PAGE = "shops/shops-add";
    
    
    /**
     * 逻辑视图名：商家管理修改界面
     */
    public final static String MODIFY_PAGE = "shops/shops-modify";
    
    
    
    /**
     * 请求地址：用户列表查询
     */
    public final static String SHOPS_LIST_URL = "shopsList";
   
    
    
    /**
     * 删除商家管理     请求的url，要访问的controller方法
     */
    public final static String SHOPSCATEGORY_DEL_URL = "delshopsById";
    
    
    /**
     * 添加商家管理     请求的url，要访问的controller方法
     */
    public final static String SHOPSCATEGORY_ADD_URL = "addshops";
    
    
    /**
     * 请求地址：保存商家管理信息
     */
    public final static String SHOPSCATEGORY_SAVE_URL = "saveshops";
    
    
    
    /**
     * 修改商家管理     请求的url，要访问的controller方法
     */
    public final static String SHOPSCATEGORY_UPDATE_URL = "updateshops";
    
    
    
    /**
     * 修改商家管理状态     请求的url，要访问的controller方法
     */
    public final static String CHANGE_STATUS_URL = "changeShopsStatus";
    
    
    
    /**
     * 请求地址：导出商家管理excel
     */
    public final static String SHOPS_EXP_URL = "expShopsList";
    
    
    
    /**
     * 请求地址：导入商家管理excel
     */
    public final static String SHOPS_IMP_URL = "impShopsList";
    
    
    
    /**
     * 请求地址：导入商家管理excel
     */
    public final static String SHOPS_TOIMPPAGE_URL="toImpPage";
    
    
    
    /**
     * 下载导入模板
     */
    public final static String SHOPS_IMP_MODEL_URL="getImpExcelModel";
    
    
    
    /**
     * 逻辑视图名：导入界面地址
     */
    public final static String SHOPS_TOIMPPAGE_PAGE = "product/import-iframe";
    
    
}
