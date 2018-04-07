package com.xingrongjinfu.app.order.controller;

import com.xingrongjinfu.app.AppConstant;
import com.xingrongjinfu.listOrderManage.listPurchase.model.ListPurchase;
import com.xingrongjinfu.listOrderManage.listPurchase.service.IListPurchaseService;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.listOrderManage.order.service.IOrderService;
import com.xingrongjinfu.shop.shopsmanage.model.Shops;
import com.xingrongjinfu.shop.shopsmanage.service.IShopsService;
import com.xingrongjinfu.utils.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wanghui
 * Date: 2018-01-15
 * Time: 17:24
 */
@Controller
@RequestMapping(AppConstant.APP_ROOT)
public class PurchaseController extends BaseController {

    @Autowired
    private IShopsService shopsService;

    @Autowired
    private IListPurchaseService listPurchaseService;

    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = AppConstant.ORDER)
    @ResponseBody
    @Transactional  //事务管理
    public Result<Object> purchase(@RequestParam("params") String params) throws Exception {
    	//System.out.println("正");
        if (StringUtil.isEmpty(params)) {
            return ResultUtil.error("参数为空");
        }
        JSONObject jsonObject = JSONObject.fromObject(params);
        if (StringUtil.nullOrBlank(String.valueOf(jsonObject.getString("shopId")))
                || StringUtil.nullOrBlank(String.valueOf(jsonObject.getString("shopName")))
                || StringUtil.nullOrBlank(String.valueOf(jsonObject.getString("shopAddress")))
                || StringUtil.nullOrBlank(String.valueOf(jsonObject.getString("shopPhone")))
                || StringUtil.nullOrBlank(String.valueOf(jsonObject.getString("shopContacts")))
                || StringUtil.nullOrBlank(String.valueOf(jsonObject.getString("purchaser")))
                || StringUtil.nullOrBlank(String.valueOf(jsonObject.getJSONArray("products")))
                || StringUtil.nullOrBlank(String.valueOf(jsonObject.getString("phone")))
                || StringUtil.nullOrBlank(String.valueOf(jsonObject.getString("address")))
        		) {
            return ResultUtil.error("9999", "参数为空！");
        }
        //商家信息
        String shopId = String.valueOf(jsonObject.getString("shopId"));
        String shopName = String.valueOf(jsonObject.getString("shopName"));
        String shopAddress = String.valueOf(jsonObject.getString("shopAddress"));
        String shopPhone = String.valueOf(jsonObject.getString("shopPhone"));
        String shopContacts = String.valueOf(jsonObject.getString("shopContacts"));
        String phone = String.valueOf(jsonObject.getString("phone"));
        String address = String.valueOf(jsonObject.getString("address"));
        String purchaser = String.valueOf(jsonObject.getString("purchaser"));
        //System.out.println("shopId========"+shopId);
        System.out.println("shopName========"+shopName);
        System.out.println("address========"+address);
        System.out.println("phone========"+phone);
        //System.out.println("shopContacts========"+shopContacts);
        System.out.println("purchaser========="+purchaser);
        
        Shops shops = new Shops();
        shops.setId(shopId);
        shops.setName(shopName);
        //shops.setAddress(shopAddress);
       // shops.setPhone(shopPhone);
        shops.setAddress(address);
        shops.setPhone(phone);
        shops.setContacts(shopContacts);
        shops.setStatus(1+"");
        if (!shopsService.existId(shopId)) {
            shops.setAddTime(new Date());
            shopsService.addShopsInfo(shops);
        } else {
            shops.setUpdateTime(new Date());
            shopsService.updateShopsInfo(shops);
        }


        //订货工单信息
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String maxId = listPurchaseService.getMaxId();
        String purchaserId = "BO-" + simpleDateFormat.format(new Date()).toString() + "-00001";
        if (maxId != null && !"".equals(maxId)) {
            purchaserId = "BO-" + simpleDateFormat.format(new Date()).toString() + "-" + IdUtil.cdPlus(maxId);
        }
        //String purchaser = String.valueOf(jsonObject.getString("purchaser"));
        //System.out.println("purchaser====="+purchaser);
        ListPurchase purchase = new ListPurchase();
        purchase.setId(purchaserId);
        purchase.setPurchaseTime(new Date());
        purchase.setPurchaser(purchaser);
        purchase.setShopId(shopId);
        purchase.setStoreId("c4f51326bbc24d2f80199c1af2161c56");
        
        purchase.setStatus("1");
        purchase.setAddTime(new Date());
        listPurchaseService.addPurchase(purchase);

        //订货单对应的订单详情信息
        JSONArray products = jsonObject.getJSONArray("products");
        for (int i = 0; i < products.size(); i++) {
            JSONObject object = products.getJSONObject(i);
            String barCode = object.getString("barCode");
            String number = object.getString("number");
            ////System.out.println("barCode======"+barCode);
            //System.out.println("number======"+number);
            Orders orders = new Orders();
            orders.setId(UuidUtil.get32UUID());
            orders.setAddTime(new Date());
            orders.setBarCode(barCode);
            orders.setNumber(Integer.parseInt(number));
            orders.setObjType(1);
            orders.setObjId(purchaserId);
            orderService.addOrder(orders);
        }
        JSONObject object = new JSONObject();
        object.put("purchaserId",purchaserId);
        return ResultUtil.success(object);
    }

}
