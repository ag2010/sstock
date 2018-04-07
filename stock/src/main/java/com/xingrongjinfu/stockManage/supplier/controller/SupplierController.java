package com.xingrongjinfu.stockManage.supplier.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.ActionControllerLog;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.product.productinfo.common.ProductInfoConstant;
import com.xingrongjinfu.product.productinfo.controller.ProductController;
import com.xingrongjinfu.stockManage.StockManageConstant;
import com.xingrongjinfu.stockManage.supplier.common.SupplierConstant;
import com.xingrongjinfu.stockManage.supplier.model.Supplier;
import com.xingrongjinfu.stockManage.supplier.service.ISupplierService;
import com.xingrongjinfu.utils.ExportExcel;
import com.xingrongjinfu.utils.IdUtil;
import com.xingrongjinfu.utils.ImportExcel;

// 供货商表现层
@Controller
@RequestMapping(StockManageConstant.STOCKMANAGE_ROOT)
public class SupplierController extends BaseController {

	@Autowired
	private ISupplierService supplierService;

	// 跳转供货商管理列表界面
	@RequestMapping(SupplierConstant.SUPPLIERS_URL)
	public ModelAndView loadShops() {

		ModelAndView modelAndView = this.getModelAndView(SupplierConstant.SUPPLIERS_PAGE);
		System.out.println(modelAndView.getViewName());
		return modelAndView;
	}

	// 条件查询页面
	@RequestMapping(SupplierConstant.SUPPLIERS_LIST_URL)
	public ModelAndView supplierList() {
		
		
		System.out.println("心");
		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

		//List<TableDataInfo> tableDataInfo = supplierService.pageInfoQuery(pageUtilEntity);
		List<Supplier> tableDataInfo = supplierService.pageInfoQuery(pageUtilEntity);
		Integer i =1;
		for(Supplier supplier:tableDataInfo){
			supplier.setNo(pageUtilEntity.getPage()+(i++)+"");
		}
		return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	
	}

	// 跳转至供货商新增界面
	@RequestMapping(SupplierConstant.TO_ADD_URL)
	public ModelAndView toUserAdd(String userId) {
		ModelAndView modelAndView = this.getModelAndView(SupplierConstant.ADD_PAGE);
		return modelAndView;
	}

	// 保存供货商信息
	@ActionControllerLog(title = "库存管理", action = "库存管理-保存供货商", isSaveRequestData = true)
	@RequestMapping(SupplierConstant.SUPPLIERS_SAVE_URL)
	public @ResponseBody Message saveSupplier(Supplier supplier) {
		int result = 0;
		String id = supplier.getId();
		System.out.println(id);
		if (id != null) {
			supplier.setUpdateTime(new Date());
			result = supplierService.updateSupplier(supplier);
		} else {
			supplier.setId(IdUtil.get32UUID());
			supplier.setAddTime(new Date());
			result = supplierService.addSupplier(supplier);
		}
		return new Message(result);
	}

	// 跳转至供货商修改界面
	@RequestMapping(SupplierConstant.TO_MODIFY_URL)
	public ModelAndView toSupplierModify(@RequestParam(required = true) String Id) {
		ModelAndView modelAndView = this.getModelAndView(SupplierConstant.MODIFY_PAGE);
		if (Id != null) {
			modelAndView.addObject("supplier", this.supplierService.findSupplierById(Id));
		}
		return modelAndView;
	}

	// 删除供货商信息
	@ActionControllerLog(title = "库存管理", action = "库存管理-删除供货商", isSaveRequestData = true)
	@RequestMapping(SupplierConstant.DEL_URL)
	public @ResponseBody Message deleteUserById(Supplier supplier) {
		int result = 0;
		String Id = supplier.getId();
		if (Id != null) {
			result = supplierService.deleteSupplier(Id);
		}

		return new Message(result);
	}

	// 启动/停用 操作
	@ActionControllerLog(title = "库存管理", action = "库存管理-启用/停用-用户", isSaveRequestData = true)
	@RequestMapping(SupplierConstant.CHANGE_STATUS_URL)
	public @ResponseBody Message changeSupplierStatus(Supplier supplier) {
		int result = 0;
		String id = supplier.getId();
		if (id != null) {
			supplier.setUpdateTime(new Date());
			result = supplierService.changeStatus(supplier);
		}
		return new Message(result);
	}


	//导出供货商信息
	@RequestMapping(SupplierConstant.SUPPLIER_EXPORT)
	   public @ResponseBody Message expSupplierList(HttpServletResponse response,PageUtilEntity pageUtilEntity)
	    { 
		System.out.println();
	         List<Supplier> tableDataInfo = supplierService.getExpSupplierList(pageUtilEntity);
	         Integer i =1;
		 		for(Supplier supp:tableDataInfo){
		 			supp.setNo((i++)+"");
		 			String statu=supp.getStatus();
		 			if(statu.equals("1")){
		 				System.out.println("静");
		 				supp.setStatus("启用");
		 			}else if(statu.equals("0")){
		 				supp.setStatus("弃用");
		 			}
		 		}
	         ExportExcel<Supplier> ee=new ExportExcel<Supplier>(); 
	         String[] headers=new String[]{"编号","供货商名称","地址","电话","手机","邮箱","状态"};
	         response.setContentType("application/force-download");// 设置强制下载不打开
	         response.addHeader("Content-Disposition","attachment;fileName=supplier.xls");// 设置文件名
	         try {
	        	OutputStream output = response.getOutputStream();
	            ee.exportExcel("供货商信息", headers, tableDataInfo, output); 
				output.flush();
				output.close();
			} catch (IOException e) { 
				e.printStackTrace();
				return new Message(0);
			} 
	        return new Message(1);
	    } 
	//跳入导入界面
	 @RequestMapping(SupplierConstant.SUPPLIER_IMPORT_REQUEST)
	    public ModelAndView toImpPage()
	    {
	        ModelAndView modelAndView = this.getModelAndView(SupplierConstant.SUPPLIER_TOIMPPAGE_URL);
	        return modelAndView;
	    }
	
	
	
	
	//导入供货商信息
	 @RequestMapping(SupplierConstant.SUPPLIER_IMP_URL)
	    public @ResponseBody Message impProduct (MultipartFile file) throws IllegalStateException, IOException
	    { 
	    	 int n=0;
	    	//"序号","内部编码","商品名称","条码","分类","库存","单位","售价","进价"
	    	//productId,code,productName,barCode,categoryName,stock,unit,salePrice,purchasePrice 
	    	String[] fields=new String[]{"id","name","address","tel","phone","email","status","addTime"};
	    	try {
	    		ImportExcel<Supplier> ie=new ImportExcel<Supplier>();
	    		List<Supplier> list=ie.readExcel(file.getInputStream(), fields, new Supplier().getClass().getName());
	    		for(Supplier p:list){	    			
	    			p.setAddTime(new Date());
	    		}
	    		if(list.size()==0){
	    			return new Message(false,"没有数据！"); 
	    			}else{
				n=supplierService.insertImpSupplierList(list);
				System.out.println(list.size());}
			} catch (IOException e) { 
				e.printStackTrace();
				return new Message(false,"导入失败！"); 
			}  
	       return new Message(true,"导入成功，添加数据"+n+"条！"); 
	   }
	 
	 
	   /**
	     * 下载导入模板
	     */
	    @RequestMapping(SupplierConstant.SUPPLIER_IMP_MODEL_URL)
	    public @ResponseBody Message getImpExcelModel(HttpServletResponse response)
	    {   
	         response.setContentType("application/force-download");// 设置强制下载不打开
	         response.addHeader("Content-Disposition","attachment;fileName=export.xls");// 设置文件名
	         try {
	        	InputStream is= ProductController.class.getClassLoader().getResource("../jsp/excelModel/model_supplier.xls").openStream();
	            OutputStream output = response.getOutputStream();
	        	int ch;
	            while ((ch = is.read()) != -1) {   
	            	output.write(ch);   
	            } 
				output.flush();
				output.close();
			} catch (IOException e) { 
				e.printStackTrace();
				return new Message(0);
			} 
	        return new Message(1);
	    } 
}
