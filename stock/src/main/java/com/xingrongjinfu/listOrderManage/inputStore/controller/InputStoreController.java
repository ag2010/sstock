package com.xingrongjinfu.listOrderManage.inputStore.controller;

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
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.listOrderManage.ListOrderManageConstant;
import com.xingrongjinfu.listOrderManage.inputStore.common.InputStoreConstant;
import com.xingrongjinfu.listOrderManage.inputStore.model.InputStore;
import com.xingrongjinfu.listOrderManage.inputStore.service.IInputStoreService;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.listOrderManage.procurement.common.ProcurementConstant;
import com.xingrongjinfu.listOrderManage.procurement.controller.ProcurementController;
import com.xingrongjinfu.listOrderManage.procurement.model.Procurement;
import com.xingrongjinfu.listOrderManage.procurement.model.ProcurementScrapOrder;
import com.xingrongjinfu.listOrderManage.procurement.service.IProcurementService;
import com.xingrongjinfu.product.productinfo.controller.ProductController;
import com.xingrongjinfu.product.productinfo.model.Product;
import com.xingrongjinfu.product.productinfo.service.IProductService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.ExportExcel;
import com.xingrongjinfu.utils.HttpClientUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 *@Author cj
 *@Date 2018年1月17日 下午6:58:27
 *@Version 1.0
 */ 
@Controller
@RequestMapping(ListOrderManageConstant.LIST_ROOT)
public class InputStoreController extends BaseController
{
  
    @Autowired
    private IInputStoreService inputStoreService;
    @Autowired
    private IProductService productService;

    
    
    /**
     * 跳转采购单列表界面
     */
    @RequestMapping(InputStoreConstant.INPUTSTORE_URL)
    public ModelAndView loadInputStorePage()
    {
        ModelAndView modelAndView = this.getModelAndView(InputStoreConstant.INPUTSTORE_PAGE);
        return modelAndView;
    }
    
    
    /**
     * 查询采购单列表
     */
    @RequestMapping(InputStoreConstant.INPUTSTORE_LIST_URL)
    public ModelAndView inputStoreList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

        List<InputStore> tableDataInfo = inputStoreService.queryInputStoreList(pageUtilEntity);
        Integer i = 1;
        for (InputStore inputStore:tableDataInfo){
        	inputStore.setNo(pageUtilEntity.getPage()+(i++));
        }
        return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
    
    
    /**
     * 跳转采购单处理界面
     */
    @RequestMapping(InputStoreConstant.INPUTSTORE_HANDLE_URL)
    public ModelAndView toInputStoreHandle(String id)
    {
        ModelAndView modelAndView = this.getModelAndView(InputStoreConstant.INPUTSTORE_HANDLE_PAGE);
        InputStore p= inputStoreService.getInputStoreInfoById(id);
        List<SysCode> proStore=productService.getProStoreOptions(); 
        modelAndView.addObject("inputStore",p);
        modelAndView.addObject("proStore",proStore);
        return modelAndView;
    }
    
    /**
     * 获取采购单信息
     */
    @RequestMapping(InputStoreConstant.INPUTSTOREORDER_INFO_URL)
    public ModelAndView getInputStoreInfo()
    {    
    	PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
    	List<Orders> list =  inputStoreService.getOrderListByInputStoreId(pageUtilEntity); 
    	Integer i = 1;
        for (Orders order:list){
        	order.setNo(pageUtilEntity.getPage()+(i++));  
    		if(order.getPurchasePrice()!=null){order.setPurchasePrice(order.getPurchasePrice()/100);}
        }
        return buildDatasTable(pageUtilEntity.getTotalResult(), list);
    }
    
    
    /**
     * 通过采购单审核
     */
    @RequestMapping(InputStoreConstant.INPUTSTORE_CHECK_URL)
    public @ResponseBody Message checkInputStore(String status, String	remark ,String  id  )
    {    
    	InputStore p=new InputStore();
    	p.setStatus(status);
    	p.setId(id);
    	p.setRemark(remark);
    	p.setUpdateTime(new Date());
    	int a =  inputStoreService.saveInputStoreCheck(p); 
 
        return new Message(a);
    }
    
    
    /**
     * 跳转采购单添加界面
     */
    @RequestMapping(InputStoreConstant.INPUTSTORE_ADD_URL)
    public ModelAndView toInputStoreAdd()
    {
        ModelAndView modelAndView = this.getModelAndView(InputStoreConstant.INPUTSTORE_ADD_PAGE);
        String inputStoreNo=inputStoreService.getInputStoreNo();
        List<SysCode> proUnit=productService.getProUnitOptions();
        List<SysCode> ProName=productService.getProNameOptions();
        List<SysCode> proStore=productService.getProStoreOptions(); 
        JSONArray jaProUnit=JSONArray.fromObject(proUnit);
        JSONArray jaProName=JSONArray.fromObject(ProName);
        modelAndView.addObject("proUnit",jaProUnit);
        modelAndView.addObject("proName",jaProName);
        modelAndView.addObject("inputStoreNo",inputStoreNo);
        modelAndView.addObject("proStore",proStore);
        return modelAndView;
    }
    
    
    /**
     * 添加采购单信息
     * @throws ParseException 
     */
    @RequestMapping(InputStoreConstant.INPUTSTORE_ADD_INFO)
    public @ResponseBody Message addInputStoreInfo(String id,String procurementId,String type,String storeId,String status,String orders) 
    {    
    	InputStore p=new InputStore();
    	p.setId(id);
    	p.setProcurementId(procurementId);
    	p.setStoreId(storeId);
    	p.setType(type);
    	p.setInputStoreTime(new Date());//派单时间即为添加时间
    	
    	p.setStatus(status); 
    	
    	
    	JSONArray ja=JSONArray.fromObject(orders);
    	List<Orders> list=new ArrayList<Orders>();
    	for(Object o:ja){
    		JSONObject jo=JSONObject.fromObject(o);
    		int index=jo.getInt("index");
    		Orders pso=new Orders();
    		pso.setId(UUID.randomUUID().toString()); 
    		pso.setBarCode(jo.getString("barCode"+index));
    		pso.setNumber(jo.getInt("number"+index)); 
    		pso.setRemark(jo.getString("remark"+index)); 
    		pso.setObjType(4);
    		pso.setObjId(id);
    		pso.setAddTime(new Date());
    		list.add(pso);
    	}
    	int a=0;
    	try{
    		a=inputStoreService.addInputStoreInfo(p,list); 
    		if(a>0){
    			for(Orders order:list){
    				ProductController pc=new ProductController();
    				Product product=productService.queryProductByBarCode(order.getBarCode()); 
    				JSONObject jsonObject = new JSONObject(); 
    				jsonObject.put("commodityName",product.getProductName());
    	            jsonObject.put("commodityNo",product.getBarCode());
    	            jsonObject.put("country",pc.getOrigin(product.getOrigin(),productService.getDictionaryMap("area")));
    	            jsonObject.put("grade",product.getLevel());
    	            jsonObject.put("origin",product.getOrigin());
    	            jsonObject.put("unit",product.getUnit());
    	            jsonObject.put("imgMain",product.getImg());
    	            jsonObject.put("specification",product.getSpec());
    	            jsonObject.put("inPrice",(product.getPurchasePrice()).intValue());
    	            jsonObject.put("storage",product.getStock());
    	            jsonObject.put("storageCondition",product.getStorageCondition());
    	            jsonObject.put("stock",product.getStock());
                    HashMap hashMap = new HashMap();
                    hashMap.put("params",jsonObject); 
                    HttpClientUtil.httpPostRequest(productService.getXgfUrl()+"/third/addCommodity",hashMap);
    			}
    		} 
    	}catch (Exception e)
        {
            e.printStackTrace(); 
            return new Message(0);
        } 
        return new Message(a);
    }
   
    /**
     * 导出采购单信息
     */
    @RequestMapping(InputStoreConstant.INPUTSTORE_EXP_URL)
    public @ResponseBody Message expInputStoreList(HttpServletResponse response,String id,String status,String beginTime,String endTime)
    { 
    	 Map<String,String> map =new HashMap<String, String>();
    	 map.put("id", id);
    	 map.put("status", status);
    	 map.put("beginTime", beginTime);
    	 map.put("endTime", endTime);
         List<InputStore> inputStoreList = inputStoreService.getExpInputStoreList(map);
         Integer i = 1;
         for (InputStore inputStore:inputStoreList){ 
        	 inputStore.setNo((i++));
        	 if(inputStore.getStatus().equals("1")){
        		 inputStore.setStatus("审核中");
        	 }else if(inputStore.getStatus().equals("2")){
        		 inputStore.setStatus("已完成");
        	 }
         }
         ExportExcel<InputStore> ee=new ExportExcel<InputStore>(); 
         String[] headers=new String[]{"序号","报废单号","派单时间","状态","采购单号","备注"};
         response.setContentType("application/force-download");// 设置强制下载不打开
         response.addHeader("Content-Disposition","attachment;fileName=inputStore.xls");// 设置文件名
         try {
        	OutputStream output = response.getOutputStream();
            ee.exportExcel("商品信息", headers, inputStoreList, output); 
			output.flush();
			output.close();
		} catch (IOException e) { 
			e.printStackTrace();
			return new Message(0);
		} 
        return new Message(1);
    } 
     
}

