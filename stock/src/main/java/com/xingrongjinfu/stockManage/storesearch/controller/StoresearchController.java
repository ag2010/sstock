package com.xingrongjinfu.stockManage.storesearch.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.framework.base.util.PageUtilEntity;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.xingrongjinfu.listOrderManage.inputStore.common.InputStoreConstant;
import com.xingrongjinfu.listOrderManage.inputStore.model.InputStore;
import com.xingrongjinfu.stockManage.StockManageConstant;
import com.xingrongjinfu.stockManage.store.model.Stores;
import com.xingrongjinfu.stockManage.storesearch.common.StoreSearchConstant;
import com.xingrongjinfu.stockManage.storesearch.model.StoreInfo;
import com.xingrongjinfu.stockManage.storesearch.service.IStoresearchService;
import com.xingrongjinfu.stockManage.storewarn.model.StoreWarn;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.ExportExcel;
/**
 * 类描述 : 库存查询业务处理
 * 类名称 : StoresearchController
 * 创建人 : xa
 * 创建时间 : 2018年1月16日
 * version: v1.0
 */
@Controller
@RequestMapping(StockManageConstant.STOCKMANAGE_ROOT)
public class StoresearchController extends BaseController{

	/*@Autowired
	private IStoresService storesService;*/
	@Autowired
	private IStoresearchService storeSearchService;
	                            
	
	
	/**
     * 跳转库存查询列表界面
     */
    @RequestMapping(StoreSearchConstant.STORESEARCH_URL)
    public ModelAndView loadStoresearch(String storesId)
    {
    	/*Stores stores=new Stores();
    	if(storesId!=null&&!"".equals(storesId)){
    		stores=storesService.findStoresById(storesId); 
    	} */
    	List<SysCode> storeName = storeSearchService.getStoreNameOptions();
        ModelAndView modelAndView = this.getModelAndView(StoreSearchConstant.STORESEARCH_PAGE);
        /*modelAndView.addObject("stores",stores);*/
        modelAndView.addObject("storeName",storeName);
        return modelAndView;
    }
	
    
 
    
    /**
     * 查询库存信息列表
     */
    @RequestMapping(StoreSearchConstant.STORESEARCH_LIST_URL)
    public ModelAndView storesearchList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<Stores> tableDataInfo = storeSearchService.queryStoresList(pageUtilEntity);
        Integer i= 1;
        if(null!=tableDataInfo&&tableDataInfo.size()>0){
        	 for(Stores stores:tableDataInfo){
        		if(null!=stores){
        			stores.setNo(pageUtilEntity.getPage()+(i++)+"");
        		}
             }
        }
        return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
    
    
    
    
    /**
     * 根据条形码查询商品信息
     * 
     */
    @RequestMapping(StoreSearchConstant.STOREINFO_HANDLE_URL)
    public ModelAndView toStoreInfoHandle()
    {
    	ModelAndView modelAndView = this.getModelAndView(StoreSearchConstant.STOREINFO_LIST_PAGE);
        return modelAndView;
    }
    
    
    
    
    /**
     * 获取入库单信息
     */
    @RequestMapping(StoreSearchConstant.STOREINFO_INFO_URL)
    public ModelAndView getStoreInfo()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<StoreInfo> storeInfoList = storeSearchService.getStoreInfoListByBarcode(pageUtilEntity);
        return buildDatasTable(pageUtilEntity.getTotalResult(), storeInfoList);
    }
    
    
    
    
    /**
     * 导出库存信息
     */
    @SuppressWarnings("all")
	@RequestMapping(StoreSearchConstant.STORESEARCH_EXP_URL)
    public @ResponseBody Message getExpStoresList(HttpServletResponse response,String jsonObject)
    { 
    	 Map RelationMap = JSONObject.parseObject(jsonObject);
    	 int result = 0;
         List<Stores> tableDataInfo = storeSearchService.getExpStoresList(RelationMap);
         Integer i = 1;
        /* if(tableDataInfo.size()==0)
         {
            return new Message(false,"没有数据");
         }*/
         if(null!=tableDataInfo&&tableDataInfo.size()>0){
        	 for(Stores stores:tableDataInfo){
        		 if(null!=stores){
        			 stores.setNo((i++)+"");
        		 }
             } 
         }
         
         ExportExcel<Stores> exportExcel=new ExportExcel<Stores>(); 
         String[] headers=new String[]{"序号","内部编码","商品名称","条码","分类","仓库","库存","单位"};
         response.setContentType("application/force-download");// 设置强制下载不打开
         response.addHeader("Content-Disposition","attachment;fileName=exportStores.xls");// 设置文件名
         try{
        	OutputStream output = response.getOutputStream();
        	exportExcel.exportExcel("库存管理信息", headers, tableDataInfo, output); 
			output.flush();
			output.close();
		} catch (IOException e) { 
			e.printStackTrace();
			return new Message(result);
		} 
        return new Message(result);
    } 
    
	
}
