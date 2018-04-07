package com.xingrongjinfu.listOrderManage.operLog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xingrongjinfu.listOrderManage.operLog.dao.IOrderOperLogDao;
import com.xingrongjinfu.listOrderManage.operLog.model.OrderOperLog;

//审核日志业务处理
@Service("oderOperLogService")
public class OrderOperLogService implements IOrderOperLogService{

	@Autowired
	private IOrderOperLogDao orderOperLogDao;
	//添加的业务
	public int addOperLog(OrderOperLog oper) {
		
		return orderOperLogDao.addOperLog(oper);
	}

}
