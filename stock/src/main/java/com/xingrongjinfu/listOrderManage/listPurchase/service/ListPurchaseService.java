package com.xingrongjinfu.listOrderManage.listPurchase.service;

import java.util.List;
import java.util.Map;
import org.framework.base.util.PageUtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xingrongjinfu.listOrderManage.listPurchase.dao.IListPurchaseDao;
import com.xingrongjinfu.listOrderManage.listPurchase.model.ListPurchase;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.system.syscode.model.SysCode;


@Service("listPurchaseService")
public class ListPurchaseService implements IListPurchaseService{

	@Autowired
	private IListPurchaseDao listPurchaseDao;

	public List<ListPurchase> pageInfoQuery(PageUtilEntity pageUtilEntity) {
	
		return listPurchaseDao.pageInfoQuery(pageUtilEntity);
	}
	

	
	public ListPurchase findListPurchaseById(String id) {
		
		return listPurchaseDao.findListPurchaseById(id);
	}



	//导出
	public List<ListPurchase> getExpPurchaseList(Map<String, String> map) {
		
		return listPurchaseDao.getExpPurchaseList(map);
	}

	//获取单号
	public String getMaxId() {
		return listPurchaseDao.getMaxId();
	}

	//添加
	public int addPurchase(ListPurchase listPurchase) {
		return listPurchaseDao.addPurchase(listPurchase);
	}



	//修改状态
	public int changeStatus(ListPurchase purchase) {
		
		return listPurchaseDao.changeStatus(purchase);
	}



	//商家
	public List<SysCode> getShopOptions() {
		
		return listPurchaseDao.getShopOptions();
	}





	@Transactional(rollbackFor = Exception.class)
	public int addPurchaseInfo(ListPurchase p, List<Orders> list) {
		int a=listPurchaseDao.addPurchase(p);
		     a=listPurchaseDao.saveInputStoreOrders(list);
		return a;
	}


}
