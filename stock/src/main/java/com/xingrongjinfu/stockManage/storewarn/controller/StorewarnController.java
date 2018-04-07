package com.xingrongjinfu.stockManage.storewarn.controller;

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
import com.xingrongjinfu.stockManage.StockManageConstant;
import com.xingrongjinfu.stockManage.storewarn.common.StoreWarnConstant;
import com.xingrongjinfu.stockManage.storewarn.model.StoreWarn;
import com.xingrongjinfu.stockManage.storewarn.service.IStorewarnService;
import com.xingrongjinfu.utils.ExportExcel;
/**
 * 类描述 : 库存预警业务处理
 * 类名称 : StorewarnController
 * 创建人 : xa
 * 创建时间 : 2018年1月17日
 * version: v1.0
 */
@Controller
@RequestMapping(StockManageConstant.STOCKMANAGE_ROOT)
public class StorewarnController extends BaseController{

	@Autowired
	private IStorewarnService storeWarnService;
	
	
	/**
     * 跳转库存预警列表界面
     */
    @RequestMapping(StoreWarnConstant.STOREWARN_URL)
    public ModelAndView loadStoresearch()
    {
        ModelAndView modelAndView = this.getModelAndView(StoreWarnConstant.STOREWARN_PAGE);
        return modelAndView;
    }
	
    
    
    /**
     * 查询库存预警信息列表
     */
    @RequestMapping(StoreWarnConstant.STOREWARN_LIST_URL)
    public ModelAndView storewarnList()
    {
    	
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<StoreWarn> tableDataInfo = storeWarnService.queryStoresWarnList(pageUtilEntity);
        Integer i= 1;
        if(null!=tableDataInfo&&tableDataInfo.size()>0){
        	for(StoreWarn storeWarn:tableDataInfo){
        		if(null!=storeWarn){
        			storeWarn.setNo(pageUtilEntity.getPage()+(i++)+"");
        		}
            	
            }
        }
        
        return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
    
	
    
    /**
     * 导出库存预警信息
     */
    @SuppressWarnings("all")
	@RequestMapping(StoreWarnConstant.STOREWARN_EXP_URL)
    public @ResponseBody Message getExpStoresList(HttpServletResponse response,String jsonObject)
    { 
    	 Map RelationMap= JSONObject.parseObject(jsonObject);
    	 int result = 0;
         List<StoreWarn> tableDataInfo = storeWarnService.getExpStoresWarnList(RelationMap);
         Integer i = 1;
        /* if(tableDataInfo.size()==0)
         {
            return new Message(false,"没有数据");
         }*/
         if(null!=tableDataInfo&&tableDataInfo.size()>0){
        	 for(StoreWarn storeWarn:tableDataInfo){
        		 if(null!=storeWarn){
        			 storeWarn.setNo((i++)+""); 
        		 }
             }
         }
           
         ExportExcel<StoreWarn> exportExcel=new ExportExcel<StoreWarn>(); 
         String[] headers=new String[]{"序号","商品名称","商品条码","供货商","单位","分类","库存上限","库存下限","库存"};
         response.setContentType("application/force-download");// 设置强制下载不打开
         response.addHeader("Content-Disposition","attachment;fileName=exportStoreWarn.xls");// 设置文件名
         try{
        	OutputStream output = response.getOutputStream();
        	if(null!=exportExcel){
        		exportExcel.exportExcel("库存预警信息", headers, tableDataInfo, output); 
        	}
			output.flush();
			output.close();
		} catch (IOException e) { 
			e.printStackTrace();
			return new Message(result);
		} 
        return new Message(result);
    } 
    
	
	
}
