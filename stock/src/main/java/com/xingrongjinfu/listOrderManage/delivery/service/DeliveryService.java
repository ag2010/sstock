package com.xingrongjinfu.listOrderManage.delivery.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.listOrderManage.delivery.dao.IDeliveryDao;
import com.xingrongjinfu.listOrderManage.delivery.model.Delivery;

//配货单处理接口
@Service("deliveryService")
public class DeliveryService implements IDeliveryService {
    
	@Autowired
	private IDeliveryDao deliveryDao;

	//条件分页查询
	public List<Delivery> pageInfoQuery(PageUtilEntity pageUtilEntity) {
	
		return deliveryDao.pageInfoQuery(pageUtilEntity);
	}

	//依据单号查询
	public Delivery findDeliveryById(String id) {
	
		return deliveryDao.findDeliveryById(id);
	}

	//导出订货单信息
	public List<Delivery> getExpDeliveryList(Map<String, String> map) {
		
		return deliveryDao.getExpDeliveryList(map);
	}

	//配货单号生成方法
/*	public String getDeliveryNo() {
		
		return deliveryDao.getDeliveryNo();
	}*/

	//添加配货单信息
	public int addDelivery(Delivery delivery) {
		
		return deliveryDao.addDelivery(delivery);
	}

	//修改状态的方法
	public int changeDeliveryStatus(Delivery delivery) {
	
		return deliveryDao.changeDeliveryStatus(delivery);
	}

	//配货单号生成方法
	public String getMaxId() {

		return deliveryDao.getMaxId();
	}

}
