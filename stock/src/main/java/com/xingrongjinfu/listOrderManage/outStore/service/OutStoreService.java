package com.xingrongjinfu.listOrderManage.outStore.service;

import java.util.List;
import java.util.Map;
import org.framework.base.util.PageUtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.listOrderManage.inputStore.dao.InputStoreDao;
import com.xingrongjinfu.listOrderManage.order.model.Orders;
import com.xingrongjinfu.listOrderManage.outStore.dao.IOutStoreDao;
import com.xingrongjinfu.listOrderManage.outStore.model.OutStore;

//出库单业务处理
@Service("outStoreService")
public class OutStoreService implements IOutStoreService{

	@Autowired
	private IOutStoreDao outStoreDao;
	 
	//条件分页查询
	public List<OutStore> pageInfoQuery(PageUtilEntity pageUtilEntity) {
	
		return outStoreDao.pageInfoQuery(pageUtilEntity);
	}
	
	//依据出库单号查询
	public OutStore findOutStoreById(String id) {
		
		return outStoreDao.findOutStoreById(id);
	}

	//出库单信息导出
	public List<OutStore> getExpOutStoreList(Map<String, String> map) {
		
		return outStoreDao.getExpOutStoreList(map);
	}


	//添加出库单信息的方法
	public int addOutStore(OutStore outStore) {
		
		return outStoreDao.addOutStore(outStore);
	}

	//改变状态的方法
	public int changeStoreStatus(OutStore outStore) {
	
		return outStoreDao.changeStoreStatus(outStore);
	}

	//生成单号的方法
	/*public String getOutStoreNo() {
		
		return outStoreDao.getOutStoreNo();
	}*/

	//生成单号的方法
	public String getMaxId() {
		
		return outStoreDao.getMaxId();
	}

	//出库库存更改
	public int updateProStock(List<Orders> list) {
		
		return outStoreDao.updateProStock(list);
	}

	//新增出库单信息
	public int addOutStoreInfo(OutStore p, List<Orders> list) {
	     int num=outStoreDao.addOutStore(p); 
	          num=outStoreDao.saveOutStoreOrders(list);
		return num;
	}

	

	   
}
