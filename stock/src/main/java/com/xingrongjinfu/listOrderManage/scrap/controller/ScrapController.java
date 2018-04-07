package com.xingrongjinfu.listOrderManage.scrap.controller;

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
import com.xingrongjinfu.listOrderManage.procurement.common.ProcurementConstant;
import com.xingrongjinfu.listOrderManage.procurement.model.Procurement;
import com.xingrongjinfu.listOrderManage.procurement.model.ProcurementScrapOrder;
import com.xingrongjinfu.listOrderManage.scrap.common.ScrapConstant;
import com.xingrongjinfu.listOrderManage.scrap.model.Scrap;
import com.xingrongjinfu.listOrderManage.scrap.service.IScrapService;
import com.xingrongjinfu.product.productinfo.service.IProductService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.ExportExcel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject; 

/**
 *
 *@Author cj
 *@Date 2018年1月17日 下午3:23:36
 *@Version 1.0
 */

@Controller
@RequestMapping(ListOrderManageConstant.LIST_ROOT)
public class ScrapController extends BaseController{
 

    @Autowired
    private IScrapService scrapService; 
    @Autowired
    private IProductService productService;

	    
    /**
     * 跳转报废单列表界面
     */
    @RequestMapping(ScrapConstant.SCRAP_URL)
    public ModelAndView loadScrapPage()
    {
        ModelAndView modelAndView = this.getModelAndView(ScrapConstant.SCRAP_PAGE);
        return modelAndView;
    }
	    
    /**
     * 查询采购单列表
     */
    @RequestMapping(ScrapConstant.SCRAP_LIST_URL)
    public ModelAndView scrapList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

        List<Scrap> scrapList = scrapService.queryScrapList(pageUtilEntity);
        Integer i = 1;
        for (Scrap scrap:scrapList){
        	scrap.setNo(pageUtilEntity.getPage()+(i++));
        }
        return buildDatasTable(pageUtilEntity.getTotalResult(), scrapList);
    }
    
    /**
     * 跳转采购单处理界面
     */
    @RequestMapping(ScrapConstant.SCRAP_HANDLE_URL)
    public ModelAndView toScrapHandle(String id)
    {
        ModelAndView modelAndView = this.getModelAndView(ScrapConstant.SCRAP_HANDLE_PAGE);
        Scrap s= scrapService.getScrapInfoById(id);
        modelAndView.addObject("scrap",s);
        return modelAndView;
    }

    /**
     * 获取采购单信息
     */
    @RequestMapping(ScrapConstant.SCRAPORDER_INFO_URL)
    public ModelAndView getScrapInfo()
    {    
    	PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
    	List<ProcurementScrapOrder> list =  scrapService.getOrderListByScrapId(pageUtilEntity); 
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
    @RequestMapping(ScrapConstant.SCRAP_CHECK_URL)
    public @ResponseBody Message checkScrap(String status, String	remark ,String  id  )
    {    
    	Scrap p=new Scrap();
    	p.setStatus(status);
    	p.setId(id);
    	p.setRemark(remark);
    	p.setUpdateTime(new Date());
    	int a =  scrapService.saveScrapCheck(p); 
 
        return new Message(a);
    }
    
    
    /**
     * 跳转采购单添加界面
     */
    @RequestMapping(ScrapConstant.SCRAP_ADD_URL)
    public ModelAndView toScrapAdd()
    {
        ModelAndView modelAndView = this.getModelAndView(ScrapConstant.SCRAP_ADD_PAGE);
        String scrapNo=scrapService.getScrapNo();
        List<SysCode> proUnit=productService.getProUnitOptions();
        List<SysCode> proType=productService.getProTypeOptions();
        JSONArray jaProUnit=JSONArray.fromObject(proUnit);
        JSONArray jaProType=JSONArray.fromObject(proType);
        modelAndView.addObject("proUnit",jaProUnit);
        modelAndView.addObject("proType",jaProType);
        modelAndView.addObject("scrapNo",scrapNo);
        return modelAndView;
    }
    
    
    /**
     * 添加采购单信息
     * @throws ParseException 
     */
    @RequestMapping(ScrapConstant.SCRAP_ADD_INFO)
    public @ResponseBody Message addScrapInfo(String id,String procurementId,String status,String orders) 
    {    
    	Scrap p=new Scrap();
    	p.setId(id);
    	p.setProcurementId(procurementId); 
    	p.setScrapTime(new Date());
    	 
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
    		pso.setObjType(2);
    		pso.setObjId(id);
    		pso.setAddTime(new Date());
    		list.add(pso);
    	} 
    	int a=0;
    	try{
    		a=scrapService.addScrapInfo(p,list);
    	}catch (Exception e)
        {
            e.printStackTrace(); 
            return new Message(0);
        } 
        return new Message(a);
    }
   
    /**
     * 导出商品信息
     */
    @RequestMapping(ScrapConstant.SCRAP_EXP_URL)
    public @ResponseBody Message expScrapList(HttpServletResponse response,String id,String status,String beginTime,String endTime)
    { 
    	 Map<String,String> map =new HashMap<String, String>();
    	 map.put("id", id);
    	 map.put("status", status);
    	 map.put("beginTime", beginTime);
    	 map.put("endTime", endTime);
         List<Scrap> scrapList = scrapService.getExpScrapList(map);
         Integer i = 1;
         for (Scrap scrap:scrapList){ 
        	 scrap.setNo((i++));
        	 if(scrap.getStatus().equals("1")){
        		 scrap.setStatus("审核中");
        	 }else if(scrap.getStatus().equals("2")){
        		 scrap.setStatus("已完成");
        	 }
         }
         ExportExcel<Scrap> ee=new ExportExcel<Scrap>(); 
         String[] headers=new String[]{"序号","报废单号","派单时间","状态","采购单号","备注"};
         response.setContentType("application/force-download");// 设置强制下载不打开
         response.addHeader("Content-Disposition","attachment;fileName=scrap.xls");// 设置文件名
         try {
        	OutputStream output = response.getOutputStream();
            ee.exportExcel("商品信息", headers, scrapList, output); 
			output.flush();
			output.close();
		} catch (IOException e) { 
			e.printStackTrace();
			return new Message(0);
		} 
        return new Message(1);
    } 
}

