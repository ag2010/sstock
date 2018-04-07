package com.xingrongjinfu.listOrderManage.operLog.dao;

import com.xingrongjinfu.listOrderManage.operLog.model.OrderOperLog;

//操作日志数据接口
public interface IOrderOperLogDao {

	//添加的接口
	public int addOperLog(OrderOperLog oper);
}
