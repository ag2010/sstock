package com.xingrongjinfu.stockManage.supplier.common;

//供货商常量信息
public class SupplierConstant {
    
	 /**
     * 请求地址：跳转至供货商管理   
     */
    public final static String SUPPLIERS_URL = "supplierView";
	
    
    /**
     * 逻辑视图名：查询供货商管理列表界面       要跳转的页面   "system/dictionary-list";
     */
    public final static String SUPPLIERS_PAGE = "stock/supplier-list";
	
	//请求地址：查询供货商列表
	public static final String  SUPPLIERS_LIST_URL = "supplierList";
	
	//新增和修改供货商信息
    public final static String SUPPLIERS_SAVE_URL = "saveSupplier";
	
	
	//请求新增供货商信息
    public final static String TO_ADD_URL = "toSupplierAdd";
    
   //新增供货商界面
    public final static String ADD_PAGE = "stock/supplier-add";
    
    //请求修改供货商信息
    public final static String TO_MODIFY_URL = "toSupplierModify";
       
    //供货商修改界面
    public final static String MODIFY_PAGE = "stock/supplier-modify";
    
    //删除供货商请求地址
    public final static String DEL_URL = "deleteSupplierById";
    
    //状态请求地址
    public final static String CHANGE_STATUS_URL="changeSupplierStatus";
    
    //导出供货商信息请求
    public final static String SUPPLIER_EXPORT="exportSupplierList";
    
    //导入供货商信息
    public final static String SUPPLIER_IMP_URL="importSupplierList";
    
    //导入信息模板下载请求地址
    public final static String SUPPLIER_IMP_MODEL_URL="importSupplierModel";
    
    //跳转导入界面
    public final static String SUPPLIER_TOIMPPAGE_URL="stock/supplier-import";
    
    //展示界面导入请求
    public final static String SUPPLIER_IMPORT_REQUEST="importRequest";
}
