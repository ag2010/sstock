package com.xingrongjinfu.product.productcategory.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.xingrongjinfu.system.user.common.UserConstant;
import org.aspectj.lang.annotation.ActionControllerLog;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.product.ProductConstant;
import com.xingrongjinfu.product.productcategory.common.ProductCategoryConstant;
import com.xingrongjinfu.product.productcategory.model.ProductCategory;
import com.xingrongjinfu.product.productcategory.service.IProductCategoryService;
import com.xingrongjinfu.system.permission.common.PermissionConstant;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.ObjectUtil; 

/**
 *
 *@Author cj
 *@Date 2018 
 *@Version 1.0
 */
@Controller
@RequestMapping(ProductConstant.PRODUCT_ROOT)
public class ProductCategoryController extends BaseController
{

    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);

    @Autowired
    private IProductCategoryService productCategoryService;

    /**
     * 跳转菜单列表界面
     */
    @RequestMapping(ProductCategoryConstant.PRODUCTCATEGORY_URL)
    public ModelAndView loadProductCategory()
    {
        ModelAndView modelAndView = this.getModelAndView(ProductCategoryConstant.PRODUCTCATEGORY_PAGE);
        modelAndView.addObject("parents", getParentList());
        return modelAndView;
    }

    /**
     * 列表条件查询的分类的树展示
     */
    @RequestMapping(ProductCategoryConstant.PRODUCTCATEGORY_TREE_URL)
    public ModelAndView productCategoryTree() {
        ModelAndView modelAndView = this.getModelAndView(ProductCategoryConstant.PRODUCTCATEGORY_TREE_VIEW);
        List<Map<String, Object>> resMapTrees = new ArrayList<Map<String, Object>>();
        List<ProductCategory> productCategoryList = productCategoryService.queryProductCategory();
        for (ProductCategory category : productCategoryList) {
            Map<String, Object> permissionMap = new HashMap<String, Object>();
            permissionMap.put("id", category.getCategoryId());
            permissionMap.put("pId", category.getParentId());
            permissionMap.put("name", category.getCategoryName());
            permissionMap.put("open", true);
            /*permissionMap.put("checked", permission.getChecked());
            permissionMap.put("doCheck", "0".equals(permission.getAvailable()) ? true : false);*/
            resMapTrees.add(permissionMap);
        }
        modelAndView.addObject("zTreeNodes", JSON.toJSONString(resMapTrees));
        return modelAndView;
    }


    /**
     * 查询菜单列表数据
     */
    @RequestMapping(ProductCategoryConstant.PRODUCTCATEGORY_LIST_URL)
    public @ResponseBody List<Map<String, Object>> ProductCategoryList()
    {
        List<Map<String, Object>> resMapTrees = new ArrayList<Map<String, Object>>();

        // 根据用户取出菜单
        List<ProductCategory> productCategoryList = productCategoryService.queryProductCategory();

        for (ProductCategory productCategory : productCategoryList)
        {
            Map<String, Object> productCategoryMap = new HashMap<String, Object>();
            productCategoryMap.put("id", productCategory.getCategoryId());
            productCategoryMap.put("pId", productCategory.getParentId());
            productCategoryMap.put("pName", productCategory.getParentName()); 
            productCategoryMap.put("name", productCategory.getCategoryName());
            productCategoryMap.put("status", productCategory.getStatus());
            productCategoryMap.put("sort", productCategory.getSort());
            productCategoryMap.put("remark", productCategory.getRemark()); 
            productCategoryMap.put("open", false); 
            resMapTrees.add(productCategoryMap);
        }

        return resMapTrees;
    }

  
    /**
     * 根据ID删除用户
     */ 
    @RequestMapping(ProductCategoryConstant.PRODUCTCATEGORY_DEL_URL)
    public @ResponseBody Message delProductCategory(ProductCategory productCategory)
    {
        int result = 0;
        String permsId = productCategory.getCategoryId(); 
        if (ObjectUtil.isNotNull(permsId))
        {
            result = productCategoryService.deleteProductCategory(productCategory);
        }
         
        return new Message(result);
    }
    

    /**
     * 保存商品分类信息
     */ 
    @RequestMapping(ProductCategoryConstant.PRODUCTCATEGORY_SAVE_URL)
    public @ResponseBody Message saveProductCategory(ProductCategory productCategory)
    {
    	 int result=0;
    	String id=productCategory.getCategoryId();
    	
    	if(ObjectUtil.isNotNull(id)&&!"".equals(id)){
    		productCategory.setUpdateTime(new Date());
    		result  = productCategoryService.updateProductCategory(productCategory);
    	}else{
    		productCategory.setAddTime(new Date());
    		productCategory.setCategoryId(UUID.randomUUID().toString());
    		result  = productCategoryService.insertProductCategory(productCategory);
    	} 
        return new Message(result);
    }
    
    
    /**
     * 获取商品分类树信息
     */ 
    @RequestMapping("getCategoryTree")
    public @ResponseBody List<Map<String, Object>> getCategoryTree()
    {
    	List<Map<String, Object>> resMapTrees = new ArrayList<Map<String, Object>>();

        // 根据用户取出菜单
        List<ProductCategory> productCategoryList = productCategoryService.queryProductCategory();

        for (ProductCategory productCategory : productCategoryList)
        {
            Map<String, Object> productCategoryMap = new HashMap<String, Object>();
            productCategoryMap.put("id", productCategory.getCategoryId());
            productCategoryMap.put("pId", productCategory.getParentId());
            productCategoryMap.put("isParent", productCategory.getParentId().equals("0") ? true : false);
            productCategoryMap.put("name", productCategory.getCategoryName());
            productCategoryMap.put("status", productCategory.getStatus());
            productCategoryMap.put("sort", productCategory.getSort());
            productCategoryMap.put("remark", productCategory.getRemark()); 
            productCategoryMap.put("open", false); 
            resMapTrees.add(productCategoryMap);
        }
        
        return resMapTrees;
    }
    
    
 
    /**
     * 获取上级菜单对象key
     */
    public List<SysCode> getParentList()
    {
        List<ProductCategory> productCategoryList = productCategoryService.findProductCategoryByPid("0");
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (ProductCategory productCategory : productCategoryList)
        {
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(productCategory.getCategoryId().toString());
            sysCode.setCodevalue(productCategory.getCategoryName());
            sysCodeList.add(sysCode);
        }
        return sysCodeList;
    }


    
}

