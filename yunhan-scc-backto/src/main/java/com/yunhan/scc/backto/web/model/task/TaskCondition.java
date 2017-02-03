package com.yunhan.scc.backto.web.model.task;

import java.util.Arrays;
import java.util.List;

import com.yunhan.scc.tools.component.module.query.QueryCondition;

/**
 * 
 * 待办任务统计查询实体
 * @author xiongmingbao
 * @version created at 2016-10-20 下午5:15:56
 */
public class TaskCondition  extends QueryCondition{
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	/**
	 * 仓位列表集合
	 */
	private List<String> wareHouses;

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

	public List<String> getWareHouses() {
		return wareHouses;
	}

	public void setWareHouses(String wareHouses) {
		if(null != wareHouses && !"".equals(wareHouses)){
			this.wareHouses = Arrays.asList(wareHouses.split(","));
		}
	}

	
	
	
	
}

