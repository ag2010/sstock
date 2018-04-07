package com.xingrongjinfu.listOrderManage.outStore.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xingrongjinfu.listOrderManage.ListOrderManageConstant;
import com.xingrongjinfu.listOrderManage.delivery.model.Delivery;
import com.xingrongjinfu.listOrderManage.delivery.service.IDeliveryService;
import com.xingrongjinfu.listOrderManage.listPurchase.common.ListPurchaseConstant;
import com.xingrongjinfu.listOrderManage.listPurchase.model.Count;
import com.xingrongjinfu.listOrderManage.listPurchase.model.ListPurchase;
import com.xingrongjinfu.listOrderManage.listPurchase.service.IListPurchaseService;
import com.xingrongjinfu.listOrderManage.operLog.model.OrderOperLog;
import com.xingrongjinfu.listOrderManage.operLog.service.IOrderOperLogService;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.listOrderManage.order.service.IOrderService;
import com.xingrongjinfu.listOrderManage.outStore.common.OutStoreConstant;
import com.xingrongjinfu.listOrderManage.outStore.model.OutStore;
import com.xingrongjinfu.listOrderManage.outStore.service.IOutStoreService;
import com.xingrongjinfu.product.productinfo.controller.ProductController;
import com.xingrongjinfu.product.productinfo.model.Product;
import com.xingrongjinfu.product.productinfo.service.IProductService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.utils.ExportExcel;
import com.xingrongjinfu.utils.HttpClientUtil;
import com.xingrongjinfu.utils.IdUtil;
import com.xingrongjinfu.utils.UuidUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



//出库单控制层
@Controller
@RequestMapping(ListOrderManageConstant.LIST_ROOT)
public class OutStoreController extends BaseController {

	@Autowired
	private IOutStoreService outStoreService;
	
	@Autowired
	private IOrderService orderService;
		
	@Autowired
	private IDeliveryService deliveryService;
	
	@Autowired
	private IListPurchaseService listPurchaseService;
	
	@Autowired
	private IOrderOperLogService operLogService;
	
	@Autowired
    private IProductService productService;
	//请求展示的页面
	@RequestMapping(OutStoreConstant.OUTSTORE_URL)
	public ModelAndView loadShops() {

		ModelAndView modelAndView = this.getModelAndView(OutStoreConstant.OUTSTORE_PAGE);
		System.out.println(modelAndView.getViewName());
		return modelAndView;
	}
	
	//请求展示的数据
	@RequestMapping(OutStoreConstant.OUTSTORE_LIST_URL)
	public ModelAndView supplierList() {
		
		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

		List<OutStore> tableDataInfo = outStoreService.pageInfoQuery(pageUtilEntity);
		Integer i =1;
		for(OutStore outs:tableDataInfo){
			outs.setNo((i++)+"");
		}

		return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}
	
	
	// 跳转至审核界面
		@RequestMapping(OutStoreConstant.TO_REVIEW_URL)
		public ModelAndView toSupplierModify(@RequestParam(required = true) String Id) {
			ModelAndView modelAndView = this.getModelAndView(OutStoreConstant.REVIEW_PAGE);
			if (Id != null) {
				List<Orders> orderlist=this.orderService.findOrder(Id);
				modelAndView.addObject("outStore", this.outStoreService.findOutStoreById(Id));
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
		
		//出库单信息导出
		@RequestMapping(OutStoreConstant.OUTSTORE_EXPORT_URL)
	    public @ResponseBody Message expProductList(HttpServletResponse response,Map<String,String> RelationMap)
	    { 
	         List<OutStore> tableDataInfo = outStoreService.getExpOutStoreList(RelationMap);
	         Integer i =1;
	 		for(OutStore outs:tableDataInfo){
	 			outs.setNo((i++)+"");
	 			String stat=outs.getStatus();
	 			String types=outs.getType();
	 			if(stat.equals("1")){
	 				outs.setStatus("审核中");
	 			}else{
	 				outs.setStatus("完成");
	 			}
	 			if(types.equals("1")){
	 				outs.setType("正常出库单");
	 			}else{
	 				outs.setType("报废单");
	 			}
	 		}
	         ExportExcel<OutStore> ee=new ExportExcel<OutStore>(); 
	         String[] headers=new String[]{"序号","出库单号","派单时间","状态","类型","配货单号","仓库","备注"};
	         response.setContentType("application/force-download");
	         response.addHeader("Content-Disposition","attachment;fileName=outStore.xls");
	         try {
	        	OutputStream output = response.getOutputStream();
	            ee.exportExcel("出库单信息", headers, tableDataInfo, output); 
				output.flush();
				output.close();
			} catch (IOException e) { 
				e.printStackTrace();
				return new Message(0);
			} 
	        return new Message(1);
	    } 
		
		
		
		//出库单的审核
		 @RequestMapping(OutStoreConstant.OUTSTORE_CHECK)
		    public @ResponseBody Message outStoreReview(String ooid,String ddid ) {
			 //System.out.println("<<<<<<<<<<<<<<<<<ooid==="+ooid+"                      "+ddid);
			 OutStore outs=outStoreService.findOutStoreById(ooid);
			 List<Orders> orderlist=this.orderService.findOrder(ooid);
			 System.out.println("状态============="+outs.getStatus());
			 if(outs.getStatus().equals("1")){
				 for(Orders o:orderlist){
					 if(o.getStock()>o.getNumber()){
				 
			//1修改本出库单的状态为完成 需要参数：本单的id 1和2从页面可以传 3需要通过对应的配货单id查找
			 OutStore outStore=new OutStore();
			 outStore.setId(ooid);
			 outStore.setStatus("2");
			 int num=outStoreService.changeStoreStatus(outStore);
			 int nua=0;
			 int nub=0;
			//2修改配货单的状态为配送中 本单对应配货单的id
			 if(ddid !=null && ddid !=""){
			 Delivery delivery = new Delivery();
			 delivery.setId(ddid);
			 delivery.setStatus("2");
			  nua=deliveryService.changeDeliveryStatus(delivery);
			 
			//3修改订货单的状态为配送中 本单对应订货单的id
			 ListPurchase purchase=new ListPurchase();
			 Delivery d=deliveryService.findDeliveryById(ddid);
			 purchase.setId(d.getPurchaseId());
			 purchase.setStatus("3");
			  nub=listPurchaseService.changeStatus(purchase);
			  }
		       
			 //4添加审核日志
			 User currentUser = getCurrentUser();
			 OrderOperLog operlog=new OrderOperLog();
			 operlog.setId(UuidUtil.get32UUID());
			 operlog.setUserId(currentUser.getUserId());
			 operlog.setObjId(ooid);
			 operlog.setType(1);
			 operlog.setAddTime(new Date());
			 int oper=operLogService.addOperLog(operlog);
			 
			 //5库存更改
			 List<Orders> orderslist=this.orderService.findOrder(ooid);
			 int  nuc=outStoreService.updateProStock(orderslist);
			 if(nuc>0){
	    			for(Orders order:orderslist){
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
	                    try {
							HttpClientUtil.httpPostRequest(productService.getXgfUrl()+"/third/addCommodity",hashMap);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    			}
	    		} 
			 
		       boolean flag=false;
		       if(num>0 && oper>0 && nuc>0){
		    	   flag=true;
		       }else{
		    	   flag=false;
		       }
		        return new Message(flag);
		        }else{
		        	boolean fla=false;
		        	return new Message(fla,"库存不足");
		        }
		 }
		    }else{
		    	boolean flag=false;
		    	return new Message(flag,"无效的操作");
		    }
			return new Message(true,"操作成功");
		 }
		 
		 
		 
		// 新增出货单跳转
		 	@RequestMapping(OutStoreConstant.TO_ADD_URL)
		 	public ModelAndView toOutStoreAdd() {
		 		ModelAndView modelAndView = this.getModelAndView(OutStoreConstant.ADD_PAGE);
		 		 //String outStoreNo=listPurchaseService.getMaxId();
		 		 String maxId = outStoreService.getMaxId();
		 		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		            String outStoreNo = "WO-" + simpleDateFormat.format(new Date()).toString() + "-00001";
		            if (maxId != null && !"".equals(maxId)) {
		            	outStoreNo = "WO-" + simpleDateFormat.format(new Date()).toString() + "-" + IdUtil.cdPlus(maxId);
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
		 	
		 			// 新增报废单信息			
					@ActionControllerLog(title = "货流管理", action = "货流管理-保存报废单", isSaveRequestData = true)
					@RequestMapping(OutStoreConstant.OUTSTORE_SAVE_URL)
				    public @ResponseBody Message addOutStore(String id,String type,String storeId,String remark,String orders) 
				    {    
						System.out.println("<<<<<<<<<<<<中国人");
						OutStore p=new OutStore();
				    	p.setId(id);
				        p.setType("2");
				    	p.setStoreId(storeId);
				    	p.setStatus("1");
				    	p.setRemark(remark);
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
				    		pso.setObjType(3);
				    		pso.setObjId(id);
				    		pso.setAddTime(new Date());
				    		list.add(pso);
				    	}
				    	int a=0;
				    	try{
				    		a=outStoreService.addOutStoreInfo(p, list); 
				    	}catch (Exception e)
				        {
				            e.printStackTrace(); 
				            return new Message(0);
				        } 
				        return new Message(a);
				    }
				   
}
