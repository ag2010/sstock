package com.xingrongjinfu.shop.shopsmanage.controller;

import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.xingrongjinfu.shop.ShopsConstant;
import com.xingrongjinfu.shop.shopsmanage.common.ShopsmanageConstant;
import com.xingrongjinfu.shop.shopsmanage.model.Shops;
import com.xingrongjinfu.shop.shopsmanage.service.IShopsService;
import com.xingrongjinfu.utils.ExportExcel;
import com.xingrongjinfu.utils.ImportExcel;
import com.xingrongjinfu.utils.UuidUtil;
/**
 * 类描述 : 商家管理业务处理
 * 类名称 : ShopsController
 * 创建人 : xa
 * 创建时间 : 2018年1月9日
 * version: v1.0
 */
@Controller
@RequestMapping(ShopsConstant.SHOPS_ROOT)
public class ShopsController extends BaseController{

	@Autowired
	private IShopsService shopsService;
	
	
	/**
     * 跳转商家管理列表界面
     */
    @RequestMapping(ShopsmanageConstant.SHOPSCATEGORY_URL)
    public ModelAndView loadShops()
    {
        ModelAndView modelAndView = this.getModelAndView(ShopsmanageConstant.SHOPSCATEGORY_PAGE);
       // modelAndView.addObject("shops", shopsList());
        return modelAndView;
    }
	
    
    /**
     * 跳转商家管理新增界面
     */
    @RequestMapping(ShopsmanageConstant.SHOPSCATEGORY_ADD_URL)
    public ModelAndView toShopAdd(String id)
    {
        return this.getModelAndView(ShopsmanageConstant.ADD_PAGE);
    }
    
    
    /**
     * 跳转商家管理修改界面
     */
    @RequestMapping(ShopsmanageConstant.SHOPSCATEGORY_UPDATE_URL)
    public ModelAndView shopsAdd(@RequestParam(required = true) String id) {
         ModelAndView modelAndView = this.getModelAndView(ShopsmanageConstant.MODIFY_PAGE);
         if (id != null)
	        {
	            modelAndView.addObject("shops", this.shopsService.findShopsById(id));
	        }

         return modelAndView;
    }
    

	
	 /**
     * 查询商家管理信息列表
     */
    @RequestMapping(ShopsmanageConstant.SHOPS_LIST_URL)
    public ModelAndView shopsList()
    {
    	
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<Shops> tableDataInfo = shopsService.pageInfoQuery(pageUtilEntity);
        Integer i = 1;
        for (Shops shops:tableDataInfo){
        	shops.setNo(pageUtilEntity.getPage()+(i++)+"");
        }
        return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
	
	
	
	/**
	 * 添加商家管理信息
	 */
    @RequestMapping(ShopsmanageConstant.SHOPSCATEGORY_SAVE_URL)
	public @ResponseBody Message saveShops(Shops shops){
		int result = 0;   
        if ("".equals(shops.getId()) || shops.getId() == null) {
            String id = UuidUtil.get32UUID();
            shops.setId(id);
            result = this.shopsService.addShopsInfo(shops);
        } else {
            result = this.shopsService.updateShopsInfo(shops);
        }
        return new Message(result);
	}
	
	

    
    
	/**
	 * 修改商家状态
	 */
	@RequestMapping(ShopsmanageConstant.CHANGE_STATUS_URL)
     public @ResponseBody Message changeShopsStatus(Shops shops)
    {
        int result = 0;
        String id = shops.getId();
        if (id != null)
        {
            result = shopsService.changeShopsStatus(shops);
        }
        return new Message(result);
    }

	
	
	/**
	 * 删除商家管理信息
	 */
	@RequestMapping(ShopsmanageConstant.SHOPSCATEGORY_DEL_URL)
	public @ResponseBody Message deleteShops(Shops shops)
    {
        int result = 0;
        String shopId = shops.getId();
        if (shopId != null)
        {
            result = shopsService.deleteShopsInfo(shopId);
        }
        return new Message(result);
    }
	
	
	
	/**
     * 导出商家管理信息
     */
    @SuppressWarnings("all")
	@RequestMapping(ShopsmanageConstant.SHOPS_EXP_URL)
    public @ResponseBody Message expShopsList(HttpServletResponse response,String jsonObject)
    { 
    	 Map RelationMap= JSONObject.parseObject(jsonObject);
    	 int result = 0;
         List<Shops> tableDataInfo = shopsService.getExpShopsList(RelationMap);
         Integer i = 1;
         for(Shops shops:tableDataInfo){
        	 shops.setNo((i++)+"");
        	 if(shops.getStatus().equals("0")){
        		 shops.setStatus("弃用");
        	 }else if(shops.getStatus().equals("1")){
        		 shops.setStatus("启用");
        	 }
         }
         ExportExcel<Shops> exportExcel=new ExportExcel<Shops>(); 
         String[] headers=new String[]{"商家编号","商家名称","商家地址","手机","座机电话","邮箱地址","状态","新增时间","修改时间","联系人"};
         response.setContentType("application/force-download");// 设置强制下载不打开
         response.addHeader("Content-Disposition","attachment;fileName=exportShops.xls");// 设置文件名
         try {
        	OutputStream output = response.getOutputStream();
        	exportExcel.exportExcel("商家管理信息", headers, tableDataInfo, output); 
			output.flush();
			output.close();
		} catch (IOException e) { 
			e.printStackTrace();
			return new Message(result);
		} 
        return new Message(result);
    } 
    
    
    /**
     * 跳转导入界面
     */
    @RequestMapping(ShopsmanageConstant.SHOPS_TOIMPPAGE_URL)
    public ModelAndView toImpPage()
    {
        ModelAndView modelAndView = this.getModelAndView(ShopsmanageConstant.SHOPS_TOIMPPAGE_PAGE);
        return modelAndView;
    }
    
    
    /**
     * 下载导入模板
     */
    @RequestMapping(ShopsmanageConstant.SHOPS_IMP_MODEL_URL)
    public @ResponseBody Message getImpExcelModel(HttpServletResponse response)
    {   
         response.setContentType("application/force-download");// 设置强制下载不打开
         response.addHeader("Content-Disposition","attachment;fileName=export.xls");// 设置文件名
         try {
        	InputStream is= ShopsController.class.getClassLoader().getResource("../jsp/excelModel/model_shops.xls").openStream();
            OutputStream output = response.getOutputStream();
        	int ch;
            while ((ch = is.read()) != -1) {   
            	output.write(ch);   
            } 
			output.flush();
			output.close();
		} catch (IOException e) { 
			e.printStackTrace();
			return new Message(0);
		} 
        return new Message(1);
    } 
    
    
	
    /**
     * 导入商家管理信息
     * @return 
     * @throws IOException 
     * @throws IllegalStateException 
     */
    @RequestMapping(ShopsmanageConstant.SHOPS_IMP_URL)
    public @ResponseBody Message impShops(MultipartFile file) throws IllegalStateException, IOException
    { 
    	int result = 0;
    	List<Shops> list=null;
    	//"序号","商家名称","商家地址","手机","座机电话","邮箱地址","状态","联系人"
    	String[] fields=new String[]{"no","name","address","phone","tel","email","status","contacts"};
    	try {
    		ImportExcel<Shops> importExcel=new ImportExcel<Shops>();
			list=importExcel.readExcel(file.getInputStream(), fields, new Shops().getClass().getName());
			for(Shops shops:list){
				if(shops.getStatus().equals("弃用")){
	        		 shops.setStatus("0");
	        	 }else if(shops.getStatus().equals("启用")){
	        		 shops.setStatus("1");
	        	 }
			}
			if(list.size()==0){
				return new Message(0); 
			}
			result=shopsService.insertBatchShops(list);
		} catch (IOException e) { 
			e.printStackTrace();
			return new Message(false,"导入失败！"); 
		}  
       return new Message(true,"导入成功，添加数据"+result+"条！"); 
   }
    
	
	
}
