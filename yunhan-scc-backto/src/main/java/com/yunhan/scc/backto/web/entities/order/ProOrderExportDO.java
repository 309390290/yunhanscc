package com.yunhan.scc.backto.web.entities.order;

import java.util.List;

import com.yunhan.scc.tools.component.module.query.QueryResult;

/**
 * 订单导出实体类
 * @author zwj
 * @version created at 2016-3-15 下午5:30:00
 */
public class ProOrderExportDO  extends QueryResult {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 订单总目
	 */
	private List<ProPurOrderSummaryBacktoDO> listOne;
	/**
	 * 订单细目
	 */
	private List<ProPurOrderItemsBacktoDO> listTwo;
	/**
	 * 订单编号
	 */
	private String orderCodes;
	
	/**
	 * 勾选ID
	 */
	private String orderItemsIds;
	/**
	 * 收货地址
	 */
	private String receiptAddress;
	/**
	 * 收货人
	 */
	private String consignee;
	/**
	 * 联系电话
	 */
	private String telephone;
	/**
	 * 文件名称
	 */
	private String excelName;
	/**
	 * 排序字段
	 */
	private String sortOrderField;
	/**
	 * 排序方式
	 */
	private String sortOrderRule;
	
	private String purchaserName;
	
	public List<ProPurOrderSummaryBacktoDO> getListOne() {
		return listOne;
	}
	public void setListOne(List<ProPurOrderSummaryBacktoDO> listOne) {
		this.listOne = listOne;
	}
	public List<ProPurOrderItemsBacktoDO> getListTwo() {
		return listTwo;
	}
	public void setListTwo(List<ProPurOrderItemsBacktoDO> listTwo) {
		this.listTwo = listTwo;
	}
	public String getOrderCodes() {
		return orderCodes;
	}
	public void setOrderCodes(String orderCodes) {
		this.orderCodes = orderCodes;
	}
	public String getReceiptAddress() {
		return receiptAddress;
	}
	public void setReceiptAddress(String receiptAddress) {
		this.receiptAddress = receiptAddress;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getOrderItemsIds() {
		return orderItemsIds;
	}
	public void setOrderItemsIds(String orderItemsIds) {
		this.orderItemsIds = orderItemsIds;
	}
	public String getExcelName() {
		return excelName;
	}
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	public String getSortOrderField() {
		return sortOrderField;
	}
	public void setSortOrderField(String sortOrderField) {
		this.sortOrderField = sortOrderField;
	}
	public String getSortOrderRule() {
		return sortOrderRule;
	}
	public void setSortOrderRule(String sortOrderRule) {
		this.sortOrderRule = sortOrderRule;
	}
	public String getPurchaserName() {
		return purchaserName;
	}
	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}

}
