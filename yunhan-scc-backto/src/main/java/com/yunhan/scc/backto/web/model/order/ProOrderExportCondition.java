package com.yunhan.scc.backto.web.model.order;


import java.util.Arrays;
import java.util.List;

import com.yunhan.scc.tools.component.module.query.QueryCondition;

/**
 * 订单导出查询实体类
 * @author zwj
 * @version created at 2016-3-15 下午5:30:00
 */
public class ProOrderExportCondition  extends QueryCondition {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 订单细目ID
	 */
	private String orderItemsIds;
	
	/**
	 * 订单细目ID集合
	 */
	private List<String> itemsIds;
	/**
	 * 采购商id
	 */
	private String purchaserId;
	/**
	 * 供应商ID
	 */
	private String supplierId;
	
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
	 * 仓位code
	 */
	private String cwCode;
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
	
	private String purchaseOrderCode;
	private List<String> purchaseOrderCodes;
	
	private String purchaseOrderCodesTmp;
	
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
		setItemsIds(orderItemsIds);
		this.orderItemsIds = orderItemsIds;
	}
	public String getPurchaserId() {
		return purchaserId;
	}
	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}
	public String getCwCode() {
		return cwCode;
	}
	public void setCwCode(String cwCode) {
		this.cwCode = cwCode;
	}
	public List<String> getItemsIds() {
		return itemsIds;
	}
	public void setItemsIds(String itemsIds) {
		if(null != itemsIds && !"".equals(itemsIds)){
			this.itemsIds = Arrays.asList(itemsIds.split(","));
		}
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
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public List<String> getPurchaseOrderCodes() {
		return purchaseOrderCodes;
	}
	public void setPurchaseOrderCodes(List<String> purchaseOrderCodes) {
		this.purchaseOrderCodes = purchaseOrderCodes;
	}
	public String getPurchaseOrderCode() {
		return purchaseOrderCode;
	}
	public void setPurchaseOrderCode(String purchaseOrderCode) {
		this.purchaseOrderCode = purchaseOrderCode;
	}
	public String getPurchaseOrderCodesTmp() {
		return purchaseOrderCodesTmp;
	}
	public void setPurchaseOrderCodesTmp(String purchaseOrderCodesTmp) {
		this.purchaseOrderCodesTmp = purchaseOrderCodesTmp;
		if(null != purchaseOrderCodesTmp && !"".equals(purchaseOrderCodesTmp)){
			setPurchaseOrderCodes(Arrays.asList(purchaseOrderCodesTmp.split(",")));
		}
	}
	

}
