package com.xingrongjinfu.product.productinfo.model;

import java.util.Date;

import com.xingrongjinfu.stockManage.store.model.Stores;

/**
 * @Author cj
 * @Date 2018年1月9日 上午9:26:28
 * @Version 1.0
 */
public class Product {
    //vo
	private String no;
    //"序号","内部编码","商品名称","条码","分类","库存","单位","售价","进价"  导出顺序 
    private String code;  //'内部编码',
    private String productName;  //'产品名称',
    private String barCode;  //'条码',
    private String categoryName; //'分类名称',
    private Integer stock;  // '库存量',
    private String unit;  //'单位',
    private Float salePrice;  //'销售价格 分为单位',
    private Float purchasePrice;  //'进货价格 分为单位',
    private String origin;  //'产地',

    private String productId;  //'产品编号',  UUID
    private String categoryId;  //'类别编号', 
    private String spec;  //'规格',
    private Integer storageCondition;  //'储存条件 1为常温 0为冷藏',
    private String img;  //'商品图片',
    private Integer level;  //'等级 0为不良品 1为一等品 2为二等品',
    private Integer stockTop;  //'库存上限',
    private Integer stockDown;  //'库存下限',
    private String expiryDate;  //'保质期',  表单赋值只能是String
    private String characteristic;  //'特征', 
    private String supplierName;  //'供应商名称',
    private String remark;  //'备注',
    private Integer status;  //'状态 1启用 0弃用',
    private String storesName;  //仓库id
   
    private Date addTime;  //'添加时间',
    private Date updateTime;  //'修改时间',
    private String storesId;  //仓库id
    private String supplierId;  //'供应商编号',
    private Stores stores;   //仓库类


    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

   

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    
    
    public Float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Float salePrice) {
		this.salePrice = salePrice;
	}

	public Float getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getStorageCondition() {
        return storageCondition;
    }

    public void setStorageCondition(Integer storageCondition) {
        this.storageCondition = storageCondition;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStockTop() {
        return stockTop;
    }

    public void setStockTop(Integer stockTop) {
        this.stockTop = stockTop;
    }

    public Integer getStockDown() {
        return stockDown;
    }

    public void setStockDown(Integer stockDown) {
        this.stockDown = stockDown;
    }

    
 

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    
    public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

	public String getStoresId() {
		return storesId;
	}

	public void setStoresId(String storesId) {
		this.storesId = storesId;
	}

	public Stores getStores() {
		return stores;
	}

	public void setStores(Stores stores) {
		this.stores = stores;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getStoresName() {
		return storesName;
	}

	public void setStoresName(String storesName) {
		this.storesName = storesName;
	}

    public static void main(String[] args) {
		System.out.println(new Product().getBarCode());
	}
    
}

