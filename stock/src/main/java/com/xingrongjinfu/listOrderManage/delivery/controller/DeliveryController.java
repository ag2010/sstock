package com.xingrongjinfu.listOrderManage.delivery.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
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
import com.xingrongjinfu.listOrderManage.delivery.common.DeliveryConstant;
import com.xingrongjinfu.listOrderManage.delivery.model.Delivery;
import com.xingrongjinfu.listOrderManage.delivery.service.IDeliveryService;
import com.xingrongjinfu.listOrderManage.listPurchase.model.Count;
import com.xingrongjinfu.listOrderManage.listPurchase.model.ListPurchase;
import com.xingrongjinfu.listOrderManage.listPurchase.service.IListPurchaseService;
import com.xingrongjinfu.listOrderManage.operLog.model.OrderOperLog;
import com.xingrongjinfu.listOrderManage.operLog.service.IOrderOperLogService;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.listOrderManage.order.service.IOrderService;
import com.xingrongjinfu.listOrderManage.outStore.model.OutStore;
import com.xingrongjinfu.listOrderManage.outStore.service.IOutStoreService;
import com.xingrongjinfu.product.productinfo.service.IProductService;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.utils.ExportExcel;
import com.xingrongjinfu.utils.HttpClientUtil;
import com.xingrongjinfu.utils.IdUtil;
import com.xingrongjinfu.utils.UuidUtil;

import net.sf.json.JSONObject;

//配货单控制层
@Controller
@RequestMapping(ListOrderManageConstant.LIST_ROOT)
public class DeliveryController extends BaseController {

	@Autowired
	private IDeliveryService deliveryService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IListPurchaseService listPurchaseService;

	@Autowired
	private IOutStoreService outStoreService;

	@Autowired
	private IOrderOperLogService orderOperLogService;

	@Autowired
	private IProductService productService;

	// 跳转请求
	@RequestMapping(DeliveryConstant.LISTDELIVERY_URL)
	public ModelAndView loadShops() {

		ModelAndView modelAndView = this.getModelAndView(DeliveryConstant.DELIVERY_PAGE);
		System.out.println(modelAndView.getViewName());
		return modelAndView;
	}

	// 分页条件查询
	@RequestMapping(DeliveryConstant.DELIVERY_LIST_URL)
	public ModelAndView supplierList() {
		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

		List<Delivery> tableDataInfo = deliveryService.pageInfoQuery(pageUtilEntity);
		Integer i = 1;
		for (Delivery delivery : tableDataInfo) {
			delivery.setNo(pageUtilEntity.getPage() + (i++) + "");
		}
		return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);

	}

	// 审核跳转
	@RequestMapping(DeliveryConstant.TO_REVIEW_URL)
	public ModelAndView toSupplierModify(@RequestParam(required = true) String Id) {

		ModelAndView modelAndView = this.getModelAndView(DeliveryConstant.REVIEW_PAGE);
		if (Id != null) {
			List<Orders> orderlist = this.orderService.findOrder(Id);
			// System.out.println("<<<<<<<<<<<<<<<<<<<<<==="+orderlist.size());
			modelAndView.addObject("deliver", this.deliveryService.findDeliveryById(Id));
			Integer i = 1;
			Integer products = orderlist.size();
			Integer num = 0;
			Count count = new Count();
			count.setTotalProduct(products);
			for (Orders orders : orderlist) {
				orders.setNo((Integer) (i++));
				if (orders.getNumber() > 0) {
					num += orders.getNumber();
				} else {
					num = 0;
				}
			}
			count.setTotalNum(num);
			modelAndView.addObject("count", count);
			modelAndView.addObject("orderlist", orderlist);
			modelAndView.addObject("orderlist", orderlist);
		}
		return modelAndView;
	}

	// 信息导出 ;
	@RequestMapping(DeliveryConstant.EXPORT_URL)
	public @ResponseBody Message expDeliveryList(HttpServletResponse response, Map<String, String> RelationMap) {
		List<Delivery> tableDataInfo = deliveryService.getExpDeliveryList(RelationMap);
		Integer i = 1;
		for (Delivery delivery : tableDataInfo) {
			delivery.setNo((i++) + "");
			String status = delivery.getStatus();
			if (status.equals("1")) {
				delivery.setStatus("待出库");
			} else if (status.equals("2")) {
				delivery.setStatus("配送中");
			} else {
				delivery.setStatus("已完成");
			}
		}
		ExportExcel<Delivery> ee = new ExportExcel<Delivery>();
		String[] headers = new String[] { "序号", "配货单号", "派单时间", "订货商家", "状态", "仓库", "订货单号", "出库单号", "备注" };
		response.setContentType("application/force-download");
		response.addHeader("Content-Disposition", "attachment;fileName=purchase.xls");
		try {
			OutputStream output = response.getOutputStream();
			ee.exportExcel("配货单信息", headers, tableDataInfo, output);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
			return new Message(0);
		}
		return new Message(1);
	}

	// 订货单审核通过
	@RequestMapping(DeliveryConstant.DELIVERY_ADD)
	public @ResponseBody Message addDelivery(String Id) {
		// System.out.println("<<<<<<<<<<<<<<<<<"+Id);
		ListPurchase purchase = listPurchaseService.findListPurchaseById(Id);
		List<Orders> oos = this.orderService.findOrder(Id);
		String statusType = purchase.getStatus();
		String shop = purchase.getShopId();
		// System.out.println("<<<<<<<<<<<<<<<<<<<<<<shopId"+shop);
		if (statusType.equals("1")) {
			for (Orders o : oos) {
				if (o.getStock() > o.getNumber()) {
					// 1修改订货单状态，插入配货单和修改时间
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
					// 生成配货单号
					String maxId = deliveryService.getMaxId();
					String deliveryId = "DO-" + simpleDateFormat.format(new Date()).toString() + "-00001";
					if (maxId != null && !"".equals(maxId)) {
						deliveryId = "DO-" + simpleDateFormat.format(new Date()).toString() + "-"
								+ IdUtil.cdPlus(maxId);
					}

					// 生成出库单号
					String maxIdd = outStoreService.getMaxId();
					String outStoreId = "WO-" + simpleDateFormat.format(new Date()).toString() + "-00001";
					if (maxIdd != null && !"".equals(maxIdd)) {
						outStoreId = "WO-" + simpleDateFormat.format(new Date()).toString() + "-"
								+ IdUtil.cdPlus(maxIdd);
					}

					purchase.setId(Id);
					purchase.setStatus("2");
					purchase.setDeliveryId(deliveryId);
					purchase.setUpdateTime(new Date());
					int num = listPurchaseService.changeStatus(purchase);
					// System.out.println("<<<<<<<<<<<<<<<<<id"+Id);
					// System.out.println("<<<<<<<<<<<<<<<<<statusType"+statusType);
					// System.out.println("<<<<<<<<<<<<<<<<<deliveryNo"+deliveryId);
					/// System.out.println("<<<<<<<<<<<<<<<<<outNo"+outStoreId);
					// System.out.println("<<<<<<<<<<<<<<<<<<<<<<<< num="+num+"
					// "+purchase.getShopId());

					// 2添加配货单
					Delivery delivery = new Delivery();
					delivery.setId(deliveryId);// 生成单号
					delivery.setDeliveryTime(new Date());
					delivery.setShopId(shop);
					delivery.setStatus("1");
					delivery.setStoreId(purchase.getStoreId());
					delivery.setPurchaseId(Id);
					delivery.setRemark(purchase.getRemark());
					delivery.setOutStoreId(outStoreId);
					delivery.setAddTime(new Date());
					int result = deliveryService.addDelivery(delivery);

					// 3添加出库单
					OutStore outStore = new OutStore();
					outStore.setAddTime(new Date());
					outStore.setId(outStoreId);
					outStore.setStatus("1");
					outStore.setType("1");
					outStore.setDeliveryId(deliveryId);
					outStore.setStoreId(purchase.getStoreId());
					outStore.setRemark(purchase.getRemark());
					int nun = outStoreService.addOutStore(outStore);

					// 4添加orders表数据
					List<Orders> orderlist = orderService.findOrder(Id);// 依据订货单号查找对应的订购商品信息
					/*
					 * List<Orders> deliveyOrders=new ArrayList<Orders>();
					 * List<Orders> outOrders=new ArrayList<Orders>();
					 */
					for (Orders orders : orderlist) {
						Orders ordera = new Orders();
						Orders orderc = new Orders();
						// 用于配货单
						ordera.setId(UuidUtil.get32UUID());
						ordera.setBarCode(orders.getBarCode());
						ordera.setNumber(orders.getNumber());
						ordera.setRemark(orders.getRemark());
						ordera.setObjType(2);
						ordera.setObjId(deliveryId);
						ordera.setAddTime(new Date());
						orderService.addOrder(ordera);

						// 用于出库单
						orderc.setId(UuidUtil.get32UUID());
						orderc.setBarCode(orders.getBarCode());
						orderc.setNumber(orders.getNumber());
						orderc.setRemark(orders.getRemark());
						orderc.setObjType(3);
						orderc.setObjId(outStoreId);
						orderc.setAddTime(new Date());
						orderService.addOrder(orderc);

						// 5添加审核日志
						User currentUser = getCurrentUser();
						OrderOperLog operlog = new OrderOperLog();
						operlog.setId(UuidUtil.get32UUID());
						operlog.setUserId(currentUser.getUserId());
						operlog.setObjId(Id);
						operlog.setType(1);
						operlog.setAddTime(new Date());
						int oper = orderOperLogService.addOperLog(operlog);

					}

					boolean flag = false;
					if (num > 0 && result > 0 && nun > 0) {
						flag = true;
					} else {
						flag = false;
					}
					return new Message(flag);
				} else {
					boolean fla = false;
					return new Message(fla, "库存不足");
				}
			}
		} else {
			boolean flags = false;
			return new Message(flags, "无效的操作");
		}
		return null;
	}

	// 订货单审核拒绝
	@RequestMapping(DeliveryConstant.PURCHASE_REFUSE)
	public @ResponseBody Message purchaseRefuse(String Id) {
		System.out.println("<<<<<<<<<<<<<<<<<ddid==" + Id);
		ListPurchase outs = listPurchaseService.findListPurchaseById(Id);
		if (outs.getStatus().equals("1")) {
			// 1修改订货单的状态为拒绝
			outs.setStatus("0");
			int nub = listPurchaseService.changeStatus(outs);

			// 2添加审核日志
			User currentUser = getCurrentUser();
			OrderOperLog operlog = new OrderOperLog();
			operlog.setId(UuidUtil.get32UUID());
			operlog.setUserId(currentUser.getUserId());
			operlog.setObjId(Id);
			operlog.setType(0);
			operlog.setAddTime(new Date());
			int oper = orderOperLogService.addOperLog(operlog);

			boolean flag = false;
			if (nub > 0 && oper > 0) {
				flag = true;
			} else {
				flag = false;
			}
			return new Message(flag);
		} else {
			boolean s = false;
			return new Message(s, "无效的操作");
		}
	}

	// 配货单审核通过
	@RequestMapping(DeliveryConstant.DELIVERY_CHECK)
	public @ResponseBody Message deliveryReview(String Id) {
		System.out.println("<<<<<<<<<<<<<<<<<ddid==" + Id);
		Delivery outs = deliveryService.findDeliveryById(Id);
		if (outs.getStatus().equals("2")) {
			// 1修改本配货单的状态为已完成
			Delivery delivery = new Delivery();
			delivery.setId(Id);
			delivery.setStatus("3");
			delivery.setUpdateTime(new Date());
			int nua = deliveryService.changeDeliveryStatus(delivery);

			// 2添加审核日志
			User currentUser = getCurrentUser();
			OrderOperLog operlog = new OrderOperLog();
			operlog.setId(UuidUtil.get32UUID());
			operlog.setUserId(currentUser.getUserId());
			operlog.setObjId(Id);
			operlog.setType(1);
			operlog.setAddTime(new Date());
			int oper = orderOperLogService.addOperLog(operlog);
			// System.out.println("@@@@@@@@@@@@@@@@@@@@@@@"+currentUser.getUserId());
			boolean flag = false;
			if (nua > 0 && oper > 0) {
				flag = true;
			} else {
				flag = false;
			}
			Map paramsMap = new HashMap();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("purchaserId", outs.getPurchaseId());
			jsonObject.put("code", "0000");
			jsonObject.put("msg", "审核通过");
			paramsMap.put("params", jsonObject);
			try {
				HttpClientUtil.httpPostRequest(productService.getXgfUrl() + "/third/sendStorage", paramsMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Message(flag);
		} else {
			boolean s = false;
			return new Message(s, "无效的操作");
		}
	}

	// 配货单审核拒绝
	@RequestMapping(DeliveryConstant.DELIVERY_REFUSE)
	public @ResponseBody Message deliveryRefuse(String Id) {
		System.out.println("<<<<<<<<<<<<<<<<<ddid==" + Id);
		Delivery outs = deliveryService.findDeliveryById(Id);
		if (outs.getStatus().equals("2")) {

			// 1添加审核日志
			User currentUser = getCurrentUser();
			OrderOperLog operlog = new OrderOperLog();
			operlog.setId(UuidUtil.get32UUID());
			operlog.setUserId(currentUser.getUserId());
			operlog.setObjId(Id);
			operlog.setType(0);
			operlog.setAddTime(new Date());
			int oper = orderOperLogService.addOperLog(operlog);

			// 2修改订货单的状态为配送中 本单对应订货单的id
			ListPurchase purchase = new ListPurchase();
			Delivery d = deliveryService.findDeliveryById(Id);
			purchase.setId(d.getPurchaseId());
			purchase.setStatus("0");
			int nub = listPurchaseService.changeStatus(purchase);

			boolean flag = false;
			if (nub > 0 && oper > 0) {
				flag = true;
			} else {
				flag = false;
			}

			Map paramsMap = new HashMap();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("purchaserId", outs.getPurchaseId());
			jsonObject.put("code", "9999");
			jsonObject.put("msg", "审核拒绝，" + outs.getRemark());
			paramsMap.put("params", jsonObject);
			try {   //productService.getXgfUrl()获取公司的服务器地址
				HttpClientUtil.httpPostRequest(productService.getXgfUrl() + "/third/sendStorage", paramsMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Message(flag);
		} else {
			boolean s = false;
			return new Message(s, "无效的操作");
		}
	}

}
