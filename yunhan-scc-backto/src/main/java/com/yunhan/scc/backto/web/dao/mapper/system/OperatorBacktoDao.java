package com.yunhan.scc.backto.web.dao.mapper.system;

import org.apache.ibatis.annotations.Param;


/**
 * 用户业务dao
 * @author luohoudong
 * @version created at 2016-9-18 下午12:01:33
 */
public interface OperatorBacktoDao{
	/**
	 * 用户修改密码
	 * @author luohoudong
	 * @version created at 2016-9-18 下午1:23:32
	 * @param userId
	 * @param newPassword
	 */
	public void updatePassword(@Param("userId")Long userId,@Param("newPassword")String newPassword);
}
