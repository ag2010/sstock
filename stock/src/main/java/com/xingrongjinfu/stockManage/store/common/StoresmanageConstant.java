package com.xingrongjinfu.stockManage.store.common;

/**
 * 类描述 : 仓库管理常量信息
 * 类名称 : StoresmanageConstant
 * 创建人 : xa
 * 创建时间 : 2018年1月12日
 * version: v1.0
 */
public class StoresmanageConstant{

	
	 /**
     * 请求地址：跳转至仓库管理   
     */
    public final static String STORESCATEGORY_URL = "storesView";
	
    
    /**
     * 逻辑视图名：查询商家管理列表界面       要跳转的页面
     */
    public final static String STORESCATEGORY_PAGE = "stock/stores-list";
    
    
    /**
     * 请求地址：仓库管理列表查询
     */
    public final static String STORES_LIST_URL = "storesList";
    
    
    

    
    
    /**
     * 删除商家管理     请求的url，要访问的controller方法
     */
    public final static String STORESCATEGORY_DEL_URL = "delstoresById";
    
    
    /**
     * 添加商家管理     请求的url，要访问的controller方法
     */
    public final static String STORESCATEGORY_ADD_URL = "addstores";
    
    
    /**
     * 请求地址：保存商家管理信息
     */
    public final static String STORESCATEGORY_SAVE_URL = "savestores";
    
    
    
    /**
     * 修改商家管理     请求的url，要访问的controller方法
     */
    public final static String STORESCATEGORY_UPDATE_URL = "updatestores";
    
    
    
    /**
     * 修改商家管理状态     请求的url，要访问的controller方法
     */
    public final static String CHANGE_STATUS_URL = "changeStoresStatus";
    

    
    /**
     * 逻辑视图名：商家管理添加界面
     */
    public final static String ADD_PAGE = "stock/stores-add";
    
    
    /**
     * 逻辑视图名：商家管理修改界面
     */
    public final static String MODIFY_PAGE = "stock/stores-modify";
    
}
