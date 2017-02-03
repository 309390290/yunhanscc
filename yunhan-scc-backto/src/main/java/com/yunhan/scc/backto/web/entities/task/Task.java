package com.yunhan.scc.backto.web.entities.task;

import com.yunhan.scc.tools.component.module.query.QueryResult;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：Task   
 * 类描述：   待办任务统计实体
 * 创建人：lumin
 * 创建时间：2016-7-28 下午4:23:21   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */

public class Task extends QueryResult{
	
	/**
	 * 采购商id
	 */
	private String purchaserId;
	
	/**
	 * 用户code
	 */
	private String userCode;
	
	/**
	 * 仓位编码
	 */
	private String wareHouse;
	
	/**
	 * 仓位名称
	 */
	private String wareHouseStr;
	
	/**
	 * 订单种类
	 */
	private Integer orderType;
	/**
	 * 订单种类描述
	 */
	private String orderTypeStr;
	
	/**
	 * 待处理数
	 */
	private Integer unDealCount;
	
	/**
	 * 未回告数 
	 */
	private Integer unResponselCount;
	
	/**
	 * 预计可发货数
	 */
	private Integer expectedDeliveryCount;
	
	/**
	 * 暂时缺货数
	 */
	private Integer tempNoGoodsCount;
	/**
	 * 新书待入库
	 */
	private Integer newWaitWarehousingCount;
	/**
	 * 待处理订单数
	 */
	private Integer unDealOrderCount;
	/**
	 * 部分处理订单数
	 */
	private Integer partDealOrderCount;

	public String getPurchaserId() {
		return purchaserId;
	}

	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getUnDealCount() {
		return unDealCount;
	}

	public void setUnDealCount(Integer unDealCount) {
		this.unDealCount = unDealCount;
	}

	public Integer getUnResponselCount() {
		return unResponselCount;
	}

	public void setUnResponselCount(Integer unResponselCount) {
		this.unResponselCount = unResponselCount;
	}

	public Integer getExpectedDeliveryCount() {
		return expectedDeliveryCount;
	}

	public void setExpectedDeliveryCount(Integer expectedDeliveryCount) {
		this.expectedDeliveryCount = expectedDeliveryCount;
	}

	public Integer getTempNoGoodsCount() {
		return tempNoGoodsCount;
	}

	public void setTempNoGoodsCount(Integer tempNoGoodsCount) {
		this.tempNoGoodsCount = tempNoGoodsCount;
	}

	public String getWareHouseStr() {
		return wareHouseStr;
	}

	public void setWareHouseStr(String wareHouseStr) {
		this.wareHouseStr = wareHouseStr;
	}

	public String getOrderTypeStr() {
		return orderTypeStr;
	}

	public void setOrderTypeStr(String orderTypeStr) {
		this.orderTypeStr = orderTypeStr;
	}

	/**
	 * @return the newWaitWarehousingCount
	 */
	public Integer getNewWaitWarehousingCount() {
		return newWaitWarehousingCount;
	}

	/**
	 * @param newWaitWarehousingCount the newWaitWarehousingCount to set
	 */
	public void setNewWaitWarehousingCount(Integer newWaitWarehousingCount) {
		this.newWaitWarehousingCount = newWaitWarehousingCount;
	}

	public Integer getUnDealOrderCount() {
		return unDealOrderCount;
	}

	public void setUnDealOrderCount(Integer unDealOrderCount) {
		this.unDealOrderCount = unDealOrderCount;
	}

	public Integer getPartDealOrderCount() {
		return partDealOrderCount;
	}

	public void setPartDealOrderCount(Integer partDealOrderCount) {
		this.partDealOrderCount = partDealOrderCount;
	}

	
	
	
	
}

