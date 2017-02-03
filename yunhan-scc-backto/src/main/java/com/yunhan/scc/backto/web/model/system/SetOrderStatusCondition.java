package com.yunhan.scc.backto.web.model.system;

/**
 * 存储过程设置订单状态查询条件
 */
public class SetOrderStatusCondition {
	/** 
	 * 订单细目id
	 */
	private String proPurOrderItemsId;
	/** 
	 * 采购商编码
	 */
	private String purchaserId;
	/** 
	 * 登陆人编码
	 */
	private String userCode;
	/**
	 * 发货回告标识
	 * 0-回告发货,1-删除回告发货 
	 */
	private String iOpFlag;
	
	
	public String getiOpFlag() {
		return iOpFlag;
	}
	public void setiOpFlag(String iOpFlag) {
		this.iOpFlag = iOpFlag;
	}

	public String getProPurOrderItemsId() {
		return proPurOrderItemsId;
	}
	public void setProPurOrderItemsId(String proPurOrderItemsId) {
		this.proPurOrderItemsId = proPurOrderItemsId;
	}
	/**
	 * 采购商编码
	 * @author pangzhenhua
	 * @version created at 2016年3月7日 下午4:14:55
	 * @return 
	 */
	public String getPurchaserId() {
		return purchaserId;
	}
	/**
	 * 采购商编码
	 * @author pangzhenhua
	 * @version created at 2016年3月7日 下午4:14:59
	 * @param purchaserId 
	 */
	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}
	/**
	 * 登陆人编码
	 * @author pangzhenhua
	 * @version created at 2016年3月7日 下午4:15:05
	 * @return 
	 */
	public String getUserCode() {
		return userCode;
	}
	/**
	 * 登陆人编码
	 * @author pangzhenhua
	 * @version created at 2016年3月7日 下午4:15:08
	 * @param userCode 
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
