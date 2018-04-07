package com.xingrongjinfu.product.productinfo.controller;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSException;
import com.xingrongjinfu.product.ProductConstant;
import com.xingrongjinfu.product.productcategory.common.ProductCategoryConstant;
import com.xingrongjinfu.product.productcategory.model.ProductCategory;
import com.xingrongjinfu.product.productcategory.service.IProductCategoryService;
import com.xingrongjinfu.product.productinfo.common.ProductInfoConstant;
import com.xingrongjinfu.product.productinfo.model.Product;
import com.xingrongjinfu.product.productinfo.service.IProductService;
import com.xingrongjinfu.product.productinfo.service.ProductService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.AliyunOSSClientUtil;
import com.xingrongjinfu.utils.ExportExcel;
import com.xingrongjinfu.utils.HttpClientUtil;
import com.xingrongjinfu.utils.ImportExcel;
import net.sf.json.JSONObject;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 *
 *@Author cj
 *@Date 2018 
 *@Version 1.0
 */
@Controller
@RequestMapping(ProductConstant.PRODUCT_ROOT)
public class ProductController extends BaseController
{

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private IProductService productService;
    @Autowired
    private IProductCategoryService productCategoryService;
 
    /**
     * 跳转商品列表界面
     */
    @RequestMapping(ProductInfoConstant.PRODUCT_URL)
    public ModelAndView loadProductPage()
    {
        ModelAndView modelAndView = this.getModelAndView(ProductInfoConstant.PRODUCT_PAGE);
        return modelAndView;
    }
    
    
    /**
     * 查询用户列表
     */
    @RequestMapping(ProductInfoConstant.PRODUCT_LIST_URL)
    public ModelAndView productList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<Map<String, String>> areaList=getDictionaryMap("area");
        List<Product> tableDataInfo = productService.queryProductList(pageUtilEntity);
        Integer i = 1;
        for (Product product:tableDataInfo){ 
            product.setNo(pageUtilEntity.getPage()+(i++)+""); 
            String origin=product.getOrigin(); 
            product.setOrigin(getDetialOrigin(origin,areaList));
            if(product.getSalePrice()!=null){product.setSalePrice(product.getSalePrice()/100);}
    		if(product.getPurchasePrice()!=null){product.setPurchasePrice(product.getPurchasePrice()/100);}
        }

        return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
    
    /**
     * 跳转至商品添加界面
     */
    @RequestMapping(ProductInfoConstant.PRODUCT_HANDLE_URL)
    public ModelAndView toProductHandle(String productId,String handleType)
    {
    	Product p=new Product();
    	if(productId!=null&&!"".equals(productId)){
    		p=productService.queryProductById(productId);  
    		if(p.getSalePrice()!=null){p.setSalePrice(p.getSalePrice()/100);}
    		if(p.getPurchasePrice()!=null){p.setPurchasePrice(p.getPurchasePrice()/100);}
    		if(p.getImg()!=null&&!"".equals(p.getImg())){
    			p.setImg("http://xrjf.oss-cn-shanghai.aliyuncs.com/"+p.getImg());
    		}
    	} 
    	if(p.getImg()==null||"".equals(p.getImg())){p.setImg(this.getRequest().getContextPath()+"/img/noPicture.jpg");}
			
		 
    	
    	List<SysCode> proUnit=productService.getProUnitOptions(); 
    	List<SysCode> proSupplier=productService.getProSupplierOptions();
    	List<SysCode> proStore=productService.getProStoreOptions();
        ModelAndView modelAndView = this.getModelAndView(ProductInfoConstant.PRODUCT_HANDLE_PAGE);
        modelAndView.addObject("product",p); 
        modelAndView.addObject("proUnit",proUnit); 
        modelAndView.addObject("proSupplier",proSupplier);
        modelAndView.addObject("proStore",proStore);
        return modelAndView;
    }
     
    
   
    /**
     * 添加/修改商品信息
     */
    @RequestMapping(ProductInfoConstant.PRODUCT_SAVE_URL)
    public @ResponseBody Message saveProduct(Product product,MultipartFile picture) throws UnsupportedEncodingException {
    	int a=0;
        try {
            String key = AliyunOSSClientUtil.uploadImg(picture);
            if(key!=null){

                //String originalFilename = picture.getOriginalFilename();
                String filePath = AliyunOSSClientUtil.FOLDER + AliyunOSSClientUtil.filePath;
                product.setImg(filePath);

            }
        } catch (OSSException e) {
            e.printStackTrace();
            return new Message(a);
        }
        //将价格转换成分
        if(product.getSalePrice()!=null){product.setSalePrice(product.getSalePrice()*100);}
		if(product.getPurchasePrice()!=null){product.setPurchasePrice(product.getPurchasePrice()*100);}
    	
		if(product.getProductId()!=null&&!"".equals(product.getProductId())){
    		product.setUpdateTime(new Date()); 
    		a=productService.editProduct(product); 
    	}else{
    		product.setProductId(UUID.randomUUID().toString()); 
    		product.setAddTime(new Date());
    		
    		a=productService.addProduct(product); 
    	}

    	if (a == 1){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("commodityName",product.getProductName());
            jsonObject.put("commodityNo",product.getBarCode());
            jsonObject.put("country",getOrigin(product.getOrigin(),getDictionaryMap("area")));
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

        return new Message(a);
    }
    
    
    /**
     * 删除商品信息
     */
    @RequestMapping(ProductInfoConstant.PRODUCT_DEL_URL)
    public @ResponseBody Message delProduct(Product product)
    {
    	int a=0;
    	if(product.getProductId()!=null&&!"".equals(product.getProductId())){
    		a=productService.deleteProduct(product); 
    	} 
        return new Message(a);
    } 
    
    
    /**
     * 导出商品信息  
     */
    @RequestMapping(ProductInfoConstant.PRODUCT_EXP_URL)
    public @ResponseBody Message expProductList(HttpServletResponse response,String jsonObject)
    { 
    	 Map RelationMap=com.alibaba.fastjson.JSONObject.parseObject(jsonObject);
         List<Product> ProductList = productService.getExpProductList(RelationMap);
         List<Map<String, String>> areaList=getDictionaryMap("area");
         Integer i = 1;
         for (Product product:ProductList){ 
             product.setNo((i++)+"");
             String origin=product.getOrigin(); 
             product.setOrigin(getDetialOrigin(origin,areaList));
             if(product.getSalePrice()!=null){product.setSalePrice(product.getSalePrice()/100);}
     		 if(product.getPurchasePrice()!=null){product.setPurchasePrice(product.getPurchasePrice()/100);}
         }
         ExportExcel<Product> ee=new ExportExcel<Product>();   
         String[] headers=new String[]{"序号","内部编码","商品名称","条码","分类","库存","单位","售价","进价","产地"};
         response.setContentType("application/force-download");// 设置强制下载不打开
         response.addHeader("Content-Disposition","attachment;fileName=product.xls");// 设置文件名
         try {
        	OutputStream output = response.getOutputStream();
            ee.exportExcel("商品信息", headers, ProductList, output); 
			output.flush();
			output.close();
		} catch (IOException e) { 
			e.printStackTrace();
			return new Message(0);
		} 
        return new Message(1);
    } 
    
    /**
     * 跳转导入界面
     */
    @RequestMapping(ProductInfoConstant.PRODUCT_TOIMPPAGE_URL)
    public ModelAndView toImpPage()
    {
        ModelAndView modelAndView = this.getModelAndView(ProductInfoConstant.PRODUCT_TOIMPPAGE_PAGE);
        return modelAndView;
    }
    
    
    /**
     * 下载导入模板
     */
    @RequestMapping(ProductInfoConstant.PRODUCT_IMP_MODEL_URL)
    public @ResponseBody Message getImpExcelModel(HttpServletResponse response)
    {   
         response.setContentType("application/force-download");// 设置强制下载不打开
         response.addHeader("Content-Disposition","attachment;fileName=model_pro.xls");// 设置文件名
         try {
        	InputStream is= ProductController.class.getClassLoader().getResource("../jsp/excelModel/model_pro.xls").openStream();
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
    
    
    /**
     * 导入商品信息
     * @return 
     * @throws IOException 
     * @throws IllegalStateException 
     */
    @RequestMapping(value=ProductInfoConstant.PRODUCT_IMP_URL, produces = "application/json; charset=utf-8")
    public @ResponseBody String impProduct (MultipartFile file) throws IllegalStateException, IOException
    { 
    	 
    	 int n=0; 
    	// 序号	内部编码      商品名称             条码                    分类名               库存        单位	           售价 	             进价 	  	   产地	规格 	储存条件  	            等级	 库存上限	      库存下限	       保质期	          特征	                      供应商编号	  备注	  状态	  仓库
    	//  no   code   productName  barCode  categoryName  stock   unit  salePrice  purchasePrice  origin  spec  storageCondition  level   stockTop  stockDown  expiryDate characteristic  supplierId  remark  status  storesId
    	 
    	
    	String[] fields=new String[]{ "no","code","productName","barCode","categoryName","stock","unit","salePrice","purchasePrice",
    			"origin","spec","storageCondition","level","stockTop","stockDown","expiryDate","characteristic","supplierName","remark","status","storesName"};
    	try {
    		ImportExcel<Product> ie=new ImportExcel<Product>();
    		List<Product> list=ie.readExcel(file.getInputStream(), fields, new Product().getClass().getName());
    		for(Product p:list){
    			p.setStoresId("0");
    			p.setAddTime(new Date());
    			if(p.getSalePrice()!=null){p.setSalePrice(p.getSalePrice()*100);}
        		if(p.getPurchasePrice()!=null){p.setPurchasePrice(p.getPurchasePrice()*100);}
    		}
    		if(list.size()==0){ 
    			return "导入失败，没有数据！";
    			}
			n=productService.insertBatchProduct(list);
			System.out.println(list.size());
		} catch (IOException e) { 
			e.printStackTrace(); 
			return "导入异常，请检查数据格式是否正确！";
		}   
    	return "导入成功，添加数据"+n+"条！";
   }
    
    public List<Map<String, String>> getDictionaryMap(String type){
    	return  productService.getDictionaryMap(type);
    }

    //产地国内还是国外
    public String getOrigin(String origin,List<Map<String, String>> areaList){
        for(Map m:areaList){
            if(origin.equals(m.get("thirdLevel"))){
                if("商品产地".equals(m.get("secondLevel"))){
                    break;
                }else{
                    origin=m.get("secondLevel")+"";
                    if("商品产地".equals(m.get("firstLevel"))){
                        break;
                    }else{
                        origin=m.get("firstLevel")+"";
                    }
                }
            }
        }
        return origin;
    }

    //获取详细产地
    public String getDetialOrigin(String origin,List<Map<String, String>> areaList){
	    for(Map m:areaList){
	    	if(origin.equals(m.get("thirdLevel"))){
	    		if("商品产地".equals(m.get("secondLevel"))){
	    			break;
	    		}else{
	    			origin=m.get("secondLevel")+">"+origin;
	    			if("商品产地".equals(m.get("firstLevel"))){
	    				break;
	    			}else{
	    				origin=m.get("firstLevel")+">"+origin;
	    			}
	    		}
	    	} 
	    }
	    return origin;
    }
    
    @RequestMapping(ProductInfoConstant.PRODUCTTREE_URL)
    public @ResponseBody List<Map<String, Object>> productTree() { 
        List<Map<String, Object>> resMapTrees = new ArrayList<Map<String, Object>>(); 
        List<Product> productList = productService.getAllProduct();
        List<ProductCategory> productCategoryList = productCategoryService.queryProductCategory();
        for (ProductCategory category : productCategoryList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", category.getCategoryId());
            map.put("pId", category.getParentId());
            map.put("name", category.getCategoryName()); 
            map.put("isCategory", true); 
            resMapTrees.add(map);
        } 
        
        for (Product product : productList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", product.getProductId());
            map.put("pId", product.getCategoryId());
            map.put("name", product.getProductName());
            map.put("barCode", product.getBarCode()); 
            map.put("isCategory", false);
            //具体商品节点用不同图片显示    
            map.put("icon", "/stock/uiloader/lib/zTree/v3/css/zTreeStyle/img/diy/5.png"); 
            resMapTrees.add(map);
        }
        return resMapTrees;
    }
    
     
}

