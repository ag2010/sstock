package com.xingrongjinfu.listOrderManage.procurement.controller;
  
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.product.productcategory.common.ProductCategoryConstant;
import com.xingrongjinfu.product.productcategory.model.ProductCategory;
import com.xingrongjinfu.product.productinfo.common.ProductInfoConstant;
import com.xingrongjinfu.product.productinfo.model.Product;
import com.xingrongjinfu.product.productinfo.service.IProductService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.ExportExcel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.xingrongjinfu.listOrderManage.ListOrderManageConstant;
import com.xingrongjinfu.listOrderManage.procurement.common.ProcurementConstant;
import com.xingrongjinfu.listOrderManage.procurement.model.Procurement;
import com.xingrongjinfu.listOrderManage.procurement.model.ProcurementScrapOrder;
import com.xingrongjinfu.listOrderManage.procurement.service.IProcurementService; 

/**
 *
 *@Author cj
 *@Date 2018 
 *@Version 1.0
 */
@Controller
@RequestMapping(ListOrderManageConstant.LIST_ROOT)
public class ProcurementController extends BaseController
{

    private static final Logger logger = LoggerFactory.getLogger(ProcurementController.class);

    @Autowired
    private IProcurementService procurementService;
    @Autowired
    private IProductService productService;

    
    
    /**
     * 跳转采购单列表界面
     */
    @RequestMapping(ProcurementConstant.PROCUREMENT_URL)
    public ModelAndView loadProcurementPage()
    {
        ModelAndView modelAndView = this.getModelAndView(ProcurementConstant.PROCUREMENT_PAGE);
        return modelAndView;
    }
    
    
    /**
     * 查询采购单列表
     */
    @RequestMapping(ProcurementConstant.PROCUREMENT_LIST_URL)
    public ModelAndView procurementList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

        List<Procurement> tableDataInfo = procurementService.queryProcurementList(pageUtilEntity);
        Integer i = 1;
        for (Procurement procurement:tableDataInfo){
        	procurement.setNo(pageUtilEntity.getPage()+(i++));
        }
        return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
    
    
    /**
     * 跳转采购单处理界面
     */
    @RequestMapping(ProcurementConstant.PROCUREMENT_HANDLE_URL)
    public ModelAndView toProcurementHandle(String id)
    {
        ModelAndView modelAndView = this.getModelAndView(ProcurementConstant.PROCUREMENT_HANDLE_PAGE);
        Procurement p= procurementService.getProcurementInfoById(id);
        List<SysCode> proSupplier=productService.getProSupplierOptions();
    	List<SysCode> proStore=productService.getProStoreOptions();
        modelAndView.addObject("procurement",p);
        modelAndView.addObject("proSupplier",proSupplier);
        modelAndView.addObject("proStore",proStore);
        return modelAndView;
    }
    
    /**
     * 获取采购单信息
     */
    @RequestMapping(ProcurementConstant.PROCUREMENTORDER_INFO_URL)
    public ModelAndView getProcurementInfo()
    {    
    	PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
    	List<ProcurementScrapOrder> list =  procurementService.getOrderListByProcurementId(pageUtilEntity); 
    	Integer i = 1;
        for (ProcurementScrapOrder p:list){
        	p.setNo(pageUtilEntity.getPage()+(i++));
        	if(p.getPrice()!=null){p.setPrice(p.getPrice()/100);}
    		if(p.getTotalPrice()!=null){p.setTotalPrice(p.getTotalPrice()/100);}
        }
        return buildDatasTable(pageUtilEntity.getTotalResult(), list);
    }
    
    
    /**
     * 通过采购单审核
     */
    @RequestMapping(ProcurementConstant.PROCUREMENT_CHECK_URL)
    public @ResponseBody Message checkProcurement(String status, String	remark ,String  id  )
    {    
    	Procurement p=new Procurement();
    	p.setStatus(status);
    	p.setId(id);
    	p.setRemark(remark);
    	p.setUpdateTime(new Date());
    	int a =  procurementService.saveProcurementCheck(p); 
 
        return new Message(a);
    }
    
    
    /**
     * 跳转采购单添加界面
     */
    @RequestMapping(ProcurementConstant.PROCUREMENT_ADD_URL)
    public ModelAndView toProcurementAdd()
    {
        ModelAndView modelAndView = this.getModelAndView(ProcurementConstant.PROCUREMENT_ADD_PAGE);
        String procurementNo=procurementService.getProcurementNo();
        List<SysCode> proUnit=productService.getProUnitOptions();
        List<SysCode> proType=productService.getProTypeOptions();
        List<SysCode> proSupplier=productService.getProSupplierOptions();
    	List<SysCode> proStore=productService.getProStoreOptions(); 
        
        JSONArray jaProUnit=JSONArray.fromObject(proUnit);
        JSONArray jaProType=JSONArray.fromObject(proType);
        modelAndView.addObject("proUnit",jaProUnit);
        modelAndView.addObject("proType",jaProType);
        modelAndView.addObject("procurementNo",procurementNo);
        modelAndView.addObject("proSupplier",proSupplier);
        modelAndView.addObject("proStore",proStore);
        return modelAndView;
    }
    
    
    /**
     * 添加采购单信息
     * @throws ParseException 
     */
    @RequestMapping(ProcurementConstant.PROCUREMENT_ADD_INFO)
    public @ResponseBody Message addProcurementInfo(String id,String supplierId,String storeId,String status,String orders) 
    {    
    	int a=0;
    	try{
    		Procurement p=new Procurement();
        	p.setId(id);
        	p.setSupplierId(supplierId);
        	p.setStoreId(storeId);
        	p.setProcurementTime(new Date());
        	 
        	p.setStatus(status);
        	  
    		JSONArray ja=JSONArray.fromObject(orders);
        	List<ProcurementScrapOrder> list=new ArrayList<ProcurementScrapOrder>();
        	for(Object o:ja){
        		JSONObject jo=JSONObject.fromObject(o);
        		int index=jo.getInt("index");
        		ProcurementScrapOrder pso=new ProcurementScrapOrder();
        		pso.setId(UUID.randomUUID().toString());
        		pso.setCategoryId(jo.getString("categoryId"+index));
        		pso.setNumber(jo.getInt("number"+index));
        		pso.setUnit(jo.getString("unit"+index));
        		pso.setPrice(Float.valueOf(jo.getString("price"+index))*100);
        		pso.setTotalPrice(pso.getNumber()*pso.getPrice());
        		pso.setRemark(jo.getString("remark"+index)); 
        		pso.setObjType(1);
        		pso.setObjId(id);
        		pso.setAddTime(new Date());
        		list.add(pso);
        	}
        	
    		a=procurementService.addProcurementInfo(p,list);
    	}catch (Exception e)
        {
            e.printStackTrace(); 
            return new Message(a);
        }  
        return new Message(a);
    }
   
    /**
     * 导出采购单信息
     */
    @RequestMapping(ProcurementConstant.PROCUREMENT_EXP_URL)
    public @ResponseBody Message expProcurementList(HttpServletResponse response,String id,String status,String beginTime,String endTime)
    { 
    	 Map<String,String> map =new HashMap<String, String>();
    	 map.put("id", id);
    	 map.put("status", status);
    	 map.put("beginTime", beginTime);
    	 map.put("endTime", endTime);
         List<Procurement> procurementList = procurementService.getExpProcurementList(map);
         Integer i = 1;
         for (Procurement procurement:procurementList){ 
        	 procurement.setNo((i++));
        	 procurement.setStatus("1".equals(procurement.getStatus())?"审核中":"已完成");
         }
         ExportExcel<Procurement> ee=new ExportExcel<Procurement>(); 
         String[] headers=new String[]{"序号","采购单号","派单时间","状态","供货商","仓库","备注"};
         response.setContentType("application/force-download");// 设置强制下载不打开
         response.addHeader("Content-Disposition","attachment;fileName=procurement.xls");// 设置文件名
         try {
        	OutputStream output = response.getOutputStream();
            ee.exportExcel("商品信息", headers, procurementList, output); 
			output.flush();
			output.close();
		} catch (IOException e) { 
			e.printStackTrace();
			return new Message(0);
		} 
        return new Message(1);
    } 
    
    
    
    /**
     * 获取已通过签没有录入入库单或报废单的采购单号
     */
    @RequestMapping(ProcurementConstant.PROCUREMENT_PASSED_URL) 
    public @ResponseBody List<Map<String, Object>> getPassedProcurementList(String type)
    {
    	List<Map<String, Object>> resMapTrees = new ArrayList<Map<String, Object>>();

        // 根据用户取出菜单
        List<Procurement> procurementList = procurementService.getPassedProcurementList(type);

        for (Procurement procurement : procurementList)
        {
            Map<String, Object> procurementMap = new HashMap<String, Object>();
            procurementMap.put("id", procurement.getId());
            procurementMap.put("name", procurement.getId());  
            resMapTrees.add(procurementMap);
        }
        
        return resMapTrees;
    }

     
}

