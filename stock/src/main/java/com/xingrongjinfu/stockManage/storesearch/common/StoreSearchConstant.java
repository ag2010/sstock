package com.xingrongjinfu.stockManage.storesearch.common;

/**
 * 类描述 : 库存查询常量信息
 * 类名称 : StoreSearchConstant
 * 创建人 : xa
 * 创建时间 : 2018年1月16日
 * version: v1.0
 */
public class StoreSearchConstant {

	
	 /**
	 * 请求地址：跳转至库存查询   
	 */
    public final static String STORESEARCH_URL = "storesearchView";
	
    
    
    /**
     * 逻辑视图名：查询库存列表界面       要跳转的页面
     */
    public final static String STORESEARCH_PAGE = "stock/storesearch-list";
    
    
    
    /**
     * 请求地址：跳转至入库单
     */
    public final static String STOREINFO_HANDLE_URL = "toStoreInfoHandle";
    
    
    
    
    /**
     * 逻辑视图名：查询入库单操作界面地址(跳转jsp页面)
     */
    public final static String STOREINFO_LIST_PAGE = "stock/storeinfo-list";
    
    
    /**
     * 请求地址：获取入库单信息
     */
    public final static String STOREINFO_INFO_URL = "getStoreInfo";
    
    
    
    /**
     * 请求地址：库存管理列表查询
     */
    public final static String STORESEARCH_LIST_URL = "storesearchList";
    
    
    
    /**
     * 请求地址：导出库存管理excel
     */
    public final static String STORESEARCH_EXP_URL = "expStoresList";
    
	
}
