package com.xingrongjinfu.listOrderManage.listPurchase.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.ActionControllerLog;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xingrongjinfu.listOrderManage.ListOrderManageConstant;
import com.xingrongjinfu.listOrderManage.listPurchase.common.ListPurchaseConstant;
import com.xingrongjinfu.listOrderManage.listPurchase.model.Count;
import com.xingrongjinfu.listOrderManage.listPurchase.model.ListPurchase;
import com.xingrongjinfu.listOrderManage.listPurchase.service.IListPurchaseService;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.listOrderManage.order.service.IOrderService;
import com.xingrongjinfu.product.productinfo.service.IProductService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.ExportExcel;
import com.xingrongjinfu.utils.HttpClientUtil;
import com.xingrongjinfu.utils.IdUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(ListOrderManageConstant.LIST_ROOT)
public class ListPurchaseController extends BaseController {

	@Autowired
	private IListPurchaseService listPurchaseService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
    private IProductService productService;

	//请求展示的页面
	@RequestMapping(ListPurchaseConstant.LISTPURCHASE_URL)
	public ModelAndView loadShops() {

		ModelAndView modelAndView = this.getModelAndView(ListPurchaseConstant.SUPPLIERS_PAGE);
		System.out.println(modelAndView.getViewName());
		return modelAndView;
	}
	
	//请求展示的数据
	@RequestMapping(ListPurchaseConstant.SUPPLIERS_LIST_URL)
	public ModelAndView supplierList() {
		System.out.println("心");
		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

		List<ListPurchase> tableDataInfo = listPurchaseService.pageInfoQuery(pageUtilEntity);
		Integer i =1;
		for(ListPurchase purchase:tableDataInfo){
			purchase.setNo(pageUtilEntity.getPage()+(i++)+"");
			purchase.setStoreName("总仓");
		}
		return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
		
	}
	
	
	// 跳转至审核界面
		@RequestMapping(ListPurchaseConstant.TO_REVIEW_URL)
		public ModelAndView toSupplierModify(@RequestParam(required = true) String Id) {
			ModelAndView modelAndView = this.getModelAndView(ListPurchaseConstant.REVIEW_PAGE);
			if (Id != null) {
				List<Orders> orderlist=this.orderService.findOrder(Id);
				modelAndView.addObject("purchase", this.listPurchaseService.findListPurchaseById(Id));
				 Integer i =1;
				 Integer products=orderlist.size();
				 Integer num=0;
				 Count count =new Count();
				 count.setTotalProduct(products);
				
			 		for(Orders orders:orderlist){
			 			orders.setNo((Integer)(i++));
			 			if(orders.getNumber()>0){
			 			num+=orders.getNumber();
			 			}else {num=0;}
			 		}
			 	 count.setTotalNum(num);
			 	modelAndView.addObject("count", count);
				modelAndView.addObject("orderlist",orderlist);
			}
			return modelAndView;
		}
       
		//导出订货单信息
		  @RequestMapping(ListPurchaseConstant.EXPORT_URL)
		    public @ResponseBody Message expProductList(HttpServletResponse response,Map<String,String> RelationMap)
		    { 
		         List<ListPurchase> tableDataInfo = listPurchaseService.getExpPurchaseList(RelationMap);
		         Integer i =1;
		 		for(ListPurchase purchase:tableDataInfo){
		 			purchase.setNo((i++)+"");
		 			String status=purchase.getStatus();
		 			if(status.equals("0")){
		 				purchase.setStatus("已拒绝");
		 			}else if(status.equals("1")){
		 				purchase.setStatus("待审核");
		 			}else if(status.equals("2")){
		 				purchase.setStatus("配货中");
		 			}else if(status.equals("3")){
		 				purchase.setStatus("配送中");
		 			}else if(status.equals("4")){
		 				purchase.setStatus("已完成");
		 			}else{
		 				purchase.setStatus("已作废");
		 			}
		 			
		 		}
		         ExportExcel<ListPurchase> ee=new ExportExcel<ListPurchase>(); 
		         String[] headers=new String[]{"序号","订货单号","订货时间","订货人员","订货商家","库存","状态","配货单号","备注"};
		         response.setContentType("application/force-download");// 设置强制下载不打开
		         response.addHeader("Content-Disposition","attachment;fileName=purchase.xls");// 设置文件名
		         try {
		        	OutputStream output = response.getOutputStream();
		            ee.exportExcel("订货单信息", headers, tableDataInfo, output); 
					output.flush();
					output.close();
				} catch (IOException e) { 
					e.printStackTrace();
					return new Message(0);
				} 
		        return new Message(1);
		    } 
		  
		    //货物送达订货单状态修改为完成
		     @RequestMapping(ListPurchaseConstant.ACTION_FINSH)
			    public @ResponseBody Message deliveryRefuse(String Id ) {
				 System.out.println("<<<<<<<<<<<<<<<<<ddid=="+Id);
				 ListPurchase purchase =listPurchaseService.findListPurchaseById(Id);
				 purchase.setStatus("4");
				 purchase.setUpdateTime(new Date());
				 int nub=listPurchaseService.changeStatus(purchase);
				 if(nub>0){
						Map paramsMap = new HashMap();
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("storageNo", Id);
						jsonObject.put("status", "ok");
						jsonObject.put("code", "0000");
						jsonObject.put("msg", "订单完成，确认收货");
						paramsMap.put("params", jsonObject);
						try {
						String  result=	HttpClientUtil.httpPostRequest(productService.getXgfUrl() + "/third/confirmReceipt", paramsMap);
						//System.out.println("????????????????????????????????????"+result);
						} catch (Exception e) {
							e.printStackTrace();
						}
				 }
			 
			        return new Message(nub);
			    }
				
				
				
				 
		 	// 新增订货单跳转
		 	@RequestMapping(ListPurchaseConstant.TO_ADD_URL)
		 	public ModelAndView toPurchaseAdd() {
		 		ModelAndView modelAndView = this.getModelAndView(ListPurchaseConstant.ADD_PAGE);
		 		 //String outStoreNo=listPurchaseService.getMaxId();
		 		 String maxId = listPurchaseService.getMaxId();
		 		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		            String outStoreNo = "BO-" + simpleDateFormat.format(new Date()).toString() + "-00001";
		            if (maxId != null && !"".equals(maxId)) {
		            	outStoreNo = "BO-" + simpleDateFormat.format(new Date()).toString() + "-" + IdUtil.cdPlus(maxId);
		            }
		         List<SysCode> proUnit=productService.getProUnitOptions();
		         List<SysCode> ProName=productService.getProNameOptions();
		         List<SysCode> proStore=productService.getProStoreOptions(); 
		         List<SysCode> shops=listPurchaseService.getShopOptions();
		         JSONArray jaProUnit=JSONArray.fromObject(proUnit);
		         JSONArray jaProName=JSONArray.fromObject(ProName);
		         modelAndView.addObject("proUnit",jaProUnit);
		         modelAndView.addObject("proName",jaProName);
		         modelAndView.addObject("outStoreNo",outStoreNo);
		         modelAndView.addObject("proStore",proStore);
		         modelAndView.addObject("shops",shops);
		         return modelAndView;
		 	}
		     
			// 新增订货单信息
	
			@ActionControllerLog(title = "货流管理", action = "货流管理-保存订货单", isSaveRequestData = true)
			@RequestMapping(ListPurchaseConstant.PURCHASE_SAVE_URL)
		    public @ResponseBody Message addPurchaseInfo(String id,String actorName,String shopId,String storeId,String remark,String orders) 
		    {    
				System.out.println("<<<<<<<<<<<<中国人");
		    	ListPurchase p=new ListPurchase();
		    	p.setId(id);
		    	p.setPurchaser(actorName);
		    	p.setStoreId(storeId);
		    	p.setShopId(shopId);
		    	p.setStatus("1");
		    	p.setRemark(remark);
		    	p.setPurchaseTime(new Date());
		    	p.setAddTime(new Date());
		    	    	
		    	
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
		    		pso.setObjType(1);
		    		pso.setObjId(id);
		    		pso.setAddTime(new Date());
		    		list.add(pso);
		    	}
		    	int a=0;
		    	try{
		    		a=listPurchaseService.addPurchaseInfo(p, list);
		    	}catch (Exception e)
		        {
		            e.printStackTrace(); 
		            return new Message(0);
		        } 
		        return new Message(a);
		    }
		   
		     
}
