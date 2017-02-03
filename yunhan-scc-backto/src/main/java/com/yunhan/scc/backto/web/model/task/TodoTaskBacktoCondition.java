package com.yunhan.scc.backto.web.model.task;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：TodoTaskBacktoCondition   
 * 类描述：   
 * 创建人：lumin
 * 创建时间：2016-8-8 上午11:34:26   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */

public class TodoTaskBacktoCondition {
	/** 
	 * 供应商ID
	 */
	private String supplierId;
	/** 
	 * 登陆用户名
	 */
	private String userCode;
	/**
	 * 采购商ID
	 */
	private String purchaserId;
	/**
	 * 供应商ID
	 * @author pangzhenhua
	 * @version created at 2016年3月4日 上午11:24:40
	 * @return 
	 */
	public String getSupplierId() {
		return supplierId;
	}
	/**
	 * 供应商ID
	 * @author pangzhenhua
	 * @version created at 2016年3月4日 上午11:24:47
	 * @param supplierId 
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 登陆用户名
	 * @author pangzhenhua
	 * @version created at 2016年3月4日 上午11:24:57
	 * @return 
	 */
	public String getUserCode() {
		return userCode;
	}
	/**
	 * 登陆用户名
	 * @author pangzhenhua
	 * @version created at 2016年3月4日 上午11:24:53
	 * @param userCode 
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPurchaserId() {
		return purchaserId;
	}
	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}
	
}

