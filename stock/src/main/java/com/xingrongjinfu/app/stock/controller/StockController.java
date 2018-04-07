package com.xingrongjinfu.app.stock.controller;

import com.xingrongjinfu.app.AppConstant;
import com.xingrongjinfu.product.productinfo.service.IProductService;
import com.xingrongjinfu.utils.DesUtils;
import com.xingrongjinfu.utils.Result;
import com.xingrongjinfu.utils.ResultUtil;
import com.xingrongjinfu.utils.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: wanghui
 * Date: 2018-01-15
 * Time: 17:25
 */
@Controller
@RequestMapping(AppConstant.APP_ROOT)
public class StockController {

    @Autowired
    private IProductService productService;

    @RequestMapping(AppConstant.STOCK)
    @ResponseBody
    public Result<Object> purchase(@RequestParam("params") String params) throws Exception {
        if (StringUtil.isEmpty(params)) {
            return ResultUtil.error("参数为空");
        }
        HashMap hashMap = DesUtils.decryptToMap(params);
        if (StringUtil.nullOrBlank(String.valueOf(hashMap.get("barCode")))
                ) {
            return ResultUtil.error("9999", "参数为空！");
        }
        String barCode = String.valueOf(hashMap.get("barCode"));
        String stock = productService.queryStock(barCode);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", stock);
        return ResultUtil.success(jsonObject);
    }
}
