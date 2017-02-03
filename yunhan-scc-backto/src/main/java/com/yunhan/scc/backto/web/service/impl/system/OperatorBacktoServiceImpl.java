package com.yunhan.scc.backto.web.service.impl.system;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhan.scc.backto.web.dao.mapper.system.OperatorBacktoDao;
import com.yunhan.scc.backto.web.service.system.OperatorBacktoService;

/**  
 * @Title: OperatorServiceImpl.java 
 * @Package com.yunhan.scc.trading.web.service.impl.system 
 * 用户操作业务类实现
 * @author wt
 * @date 2015-6-26 下午2:11:05 
 * @modify TangGuoFeng 2015-7-7 “@Service"中加一个标准别名，不然session中无法获取这个bean
 * @version V0.1  
 */
@Service("operatorService")
public class OperatorBacktoServiceImpl implements OperatorBacktoService {
	/**
	 * 用户DAO
	 */
	@Autowired
	private OperatorBacktoDao operatorBacktoDao;
	
	
	/**
	 * 用户修改密码
	 * @author luohoudong
	 * @version created at 2016-9-18 上午11:53:58
	 * @param userId
	 * @param newPassword
	 */
	@Override
	public void updatePassword(Long userId,String newPassword) {
		operatorBacktoDao.updatePassword(userId,newPassword);
	}

	
	
	
}
