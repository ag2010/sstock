package com.xingrongjinfu.stockManage.store.controller;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xingrongjinfu.stockManage.store.common.StoresmanageConstant;
import com.xingrongjinfu.stockManage.store.model.Stores;
import com.xingrongjinfu.stockManage.StockManageConstant;
import com.xingrongjinfu.stockManage.store.service.IStoresService;
import com.xingrongjinfu.utils.UuidUtil;
/**
 * 类描述 : 仓库管理业务处理
 * 类名称 : StoresController
 * 创建人 : xa
 * 创建时间 : 2018年1月12日
 * version: v1.0
 */
@Controller
@RequestMapping(StockManageConstant.STOCKMANAGE_ROOT)
public class StoresController extends BaseController{

	@Autowired
	private IStoresService storesService;
	
	/**
     * 跳转仓库管理列表界面
     */
    @RequestMapping(StoresmanageConstant.STORESCATEGORY_URL)
    public ModelAndView loadStores()
    {
        ModelAndView modelAndView = this.getModelAndView(StoresmanageConstant.STORESCATEGORY_PAGE);
        return modelAndView;
    }
	
	
    /**
     * 跳转仓库管理新增界面
     */
    @RequestMapping(StoresmanageConstant.STORESCATEGORY_ADD_URL)
    public ModelAndView toStoreAdd(String storesId)
    {
        return this.getModelAndView(StoresmanageConstant.ADD_PAGE);
    }
	
    
    /**
     * 跳转仓库管理修改界面
     */
    @RequestMapping(StoresmanageConstant.STORESCATEGORY_UPDATE_URL)
    public ModelAndView storesAdd(@RequestParam(required = true) String storesId) {
         ModelAndView modelAndView = this.getModelAndView(StoresmanageConstant.MODIFY_PAGE);
         if (storesId != null)
	        {
	            modelAndView.addObject("stores", this.storesService.findStoresById(storesId));
	        }
         return modelAndView;
    }
	
    
    /**
     * 查询仓库管理信息列表
     */
    @RequestMapping(StoresmanageConstant.STORES_LIST_URL)
    public ModelAndView storesList()
    {
    	
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<Stores> tableDataInfo = storesService.pageInfoQuery(pageUtilEntity);
        Integer i= 1;
        for(Stores stores:tableDataInfo){
        	stores.setNo(pageUtilEntity.getPage()+(i++)+"");
        }
        return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
    
    
    
    /**
	 * 添加仓库管理信息
	 */
    @RequestMapping(StoresmanageConstant.STORESCATEGORY_SAVE_URL)
	public @ResponseBody Message saveStores(Stores stores){
		int result = 0;   
        if ("".equals(stores.getId()) || stores.getId() == null) {
            String id = UuidUtil.get32UUID();
            stores.setId(id);
            result = this.storesService.addStoresInfo(stores);
        } else {
            result = this.storesService.updateStoresInfo(stores);
        }
        return new Message(result);
	}
    
	
    
    /**
	 * 修改仓库状态
	 */
	@RequestMapping(StoresmanageConstant.CHANGE_STATUS_URL)
     public @ResponseBody Message changeStoresStatus(Stores stores)
    {
        int result = 0;
        String id = stores.getId();
        if (id != null)
        {
            result = storesService.changeStoresStatus(stores);
        }
        return new Message(result);
    } 
    
    
	
	/**
	 * 删除仓库管理信息
	 */
	@RequestMapping(StoresmanageConstant.STORESCATEGORY_DEL_URL)
	public @ResponseBody Message deleteStores(Stores stores)
    {
        int result = 0;
        String id = stores.getId();
        if (id != null)
        {
            result = storesService.deleteStoresById(id);
        }
        return new Message(result);
    }
	
	
    
}
