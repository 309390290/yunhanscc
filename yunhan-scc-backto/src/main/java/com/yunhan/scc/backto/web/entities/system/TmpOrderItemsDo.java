package com.yunhan.scc.backto.web.entities.system;
/**
 * 全局临时表-数据对象（用于存储过程计算订单状态）
 * @author wangtao
 *2016年8月24日09:43:53
 */
public class TmpOrderItemsDo {
	//每个事务处理的批次号
	private String batchNo;
	//存储每个事务的订单细目id
	private Long proPurOrderItemsId;
	/**
	 * @return the batchNo
	 */
	public String getBatchNo() {
		return batchNo;
	}
	/**
	 * @param batchNo the batchNo to set
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	/**
	 * @return the proPurOrderItemsId
	 */
	public Long getProPurOrderItemsId() {
		return proPurOrderItemsId;
	}
	/**
	 * @param proPurOrderItemsId the proPurOrderItemsId to set
	 */
	public void setProPurOrderItemsId(Long proPurOrderItemsId) {
		this.proPurOrderItemsId = proPurOrderItemsId;
	}
	
}
