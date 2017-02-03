package com.yunhan.scc.backto.web.entities.order;

import java.util.Arrays;
import java.util.List;

import com.yunhan.scc.tools.component.module.query.QueryResult;

/**
 * @author yangtao
 * 订单方式 合单导出 使用工具Do.里面的set方法不是默认的，如果要使用请注意。
 */
public class ProOrderExcelToolDo  extends QueryResult {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 订单细目
	 */
	private List<ProPurOrderItemsBacktoDO> orderItemsBacktoDos;
	/**
	 * 订单总目
	 */
	private List<ProPurOrderSummaryBacktoDO> orderSummaryBacktoDos;
	/**
	 * 订单编号
	 */
	private String purchaseOrderCodesString;
	private List<String> purchaseOrderCodes;
	
	private String orderType;
	private String excelName;
	private String wareHouse;
	private String name;
	private String purchaserName;

	/**
	 * 订单总目
	 */
	private List<ProPurOrderSummaryBacktoDO> listOne;
	/**
	 * 订单细目
	 */
	private List<ProPurOrderItemsBacktoDO> listTwo;
	
	/**
	 * 排序字段
	 */
	private String sortOrderField;
	/**
	 * 排序方式
	 */
	private String sortOrderRule;
	
	/**
	 * 订单细目ID
	 */
	private String orderItemsIds;
	
	/**
	 * 订单细目ID集合
	 */
	private List<String> itemsIds;
	
	
	/**
	 * add by yangtao 20160914 增加采购商和供应商
	 */
	private String purchaserId;
	private String supplierId;
	
	
	public List<String> getItemsIds() {
		return itemsIds;
	}
	public void setItemsIds(String itemsIds) {
		if(null != itemsIds && !"".equals(itemsIds)){
			this.itemsIds = Arrays.asList(itemsIds.split(","));
		}
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
	public String getOrderItemsIds() {
		return orderItemsIds;
	}
	public void setOrderItemsIds(String orderItemsIds) {
		setItemsIds(orderItemsIds);
		this.orderItemsIds = orderItemsIds;
	}
	
	public String getWareHouse() {
		return wareHouse;
	}
	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getExcelName() {
		return excelName;
	}
	public void setExeclName(String execlName) {
		this.excelName = execlName;
	}
	
	public List<ProPurOrderItemsBacktoDO> getOrderItemsBacktoDos() {
		return orderItemsBacktoDos;
	}
	public void setOrderItemsBacktoDos(
			List<ProPurOrderItemsBacktoDO> orderItemsBacktoDos) {
		this.orderItemsBacktoDos = orderItemsBacktoDos;
	}
	public List<ProPurOrderSummaryBacktoDO> getOrderSummaryBacktoDos() {
		return orderSummaryBacktoDos;
	}
	public void setOrderSummaryBacktoDos(
			List<ProPurOrderSummaryBacktoDO> orderSummaryBacktoDos) {
		this.orderSummaryBacktoDos = orderSummaryBacktoDos;
	}
	public String getPurchaserId() {
		return purchaserId;
	}
	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
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
	
	public String getPurchaseOrderCodesString() {
		return purchaseOrderCodesString;
	}
	
	public void setPurchaseOrderCodesString(String purchaseOrderCodesString) {
		this.purchaseOrderCodesString = purchaseOrderCodesString;
		
		if(purchaseOrderCodesString == null || purchaseOrderCodesString.equals("")){
			return;
		}else{
			setPurchaseOrderCodes(Arrays.asList(purchaseOrderCodesString.split(",")));
		}
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getPurchaserName() {
		return purchaserName;
	}
	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}
	
}
