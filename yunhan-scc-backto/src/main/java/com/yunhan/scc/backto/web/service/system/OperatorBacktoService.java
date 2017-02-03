package com.yunhan.scc.backto.web.service.system;


import java.util.Map;

/**
 * 用户业务service 
 * @author luohoudong
 * @version created at 2016-9-18 上午11:53:39
 */
public interface OperatorBacktoService {
	/**
	 * 用户修改密码
	 * @author luohoudong
	 * @version created at 2016-9-18 上午11:53:58
	 * @param userId
	 * @param newPassword
	 */
	public void updatePassword(Long userId,String newPassword);
	
}
