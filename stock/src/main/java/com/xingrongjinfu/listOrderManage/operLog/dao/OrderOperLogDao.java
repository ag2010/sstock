package com.xingrongjinfu.listOrderManage.operLog.dao;

import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.listOrderManage.operLog.model.OrderOperLog;

//审核日志数据处理层
@Repository("orderOperLogDao")
public class OrderOperLogDao extends DynamicObjectBaseDao implements IOrderOperLogDao{

	//添加的方法
	public int addOperLog(OrderOperLog oper) {
		
		return (int)this.save("OrderOperLogMapper.addOperLog", oper);
	}

}
