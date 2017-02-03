package com.yunhan.scc.backto.web.model.system;

import java.util.Arrays;
import java.util.List;

import com.yunhan.scc.tools.component.module.query.QueryCondition;

/**
 * 发货单规则配置 实体
 * @author xiongmingbao
 * @version 2016-8-24 14:58:59
 */
public class SendRuleConfigCondition extends QueryCondition 

{
	/**
	*
	*/
	private Integer id;
	/**
	*发货单长度
	*/
	private Integer sendoutGoodsLength;
	/**
	*发货单长度规则（1：长度至少多少位、2：长度固定多少位）
	*/
	private String lengthRule;
	/**
	*正则表达式
	*/
	private String regularExpression;
	/**
	*发货单格式
	*/
	private String sendFormat;
	/**
	*供应商id
	*/
	private String supplierId;
	/**
	*采购商id
	*/
	private String purchaserId;
	/**
	 * add by yangtao 2016-11-25 用于接收前台数据
	 */
	private List<String> supplierIds;
	
	private List<String> purchaserIds;
	/**
	*是否启用标志(Y:启用;N:停用)
	*/
	private String isValid;
	/**
	 * 排序字段
	 */
	private String sortOrderField;
	/**
	 * 排序方式
	 */
	private String sortOrderRule;
	/**
	 * 
	 * @param id
	 */
	public void setId(Integer id){
		this.id=id;
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer getId(){
		return this.id;
	}
	/**
	 * 发货单长度
	 * @param length
	 */
	public void setSendoutGoodsLength(Integer sendoutGoodsLength){
		this.sendoutGoodsLength=sendoutGoodsLength;
	}
	
	/**
	 * 发货单长度
	 * @return
	 */
	public Integer getSendoutGoodsLength(){
		return this.sendoutGoodsLength;
	}
	/**
	 * 发货单长度规则（1：长度至少多少位、2：长度固定多少位）
	 * @param lengthRule
	 */
	public void setLengthRule(String lengthRule){
		this.lengthRule=lengthRule;
	}
	
	/**
	 * 发货单长度规则（1：长度至少多少位、2：长度固定多少位）
	 * @return
	 */
	public String getLengthRule(){
		return this.lengthRule;
	}
	/**
	 * 正则表达式
	 * @param regularExpression
	 */
	public void setRegularExpression(String regularExpression){
		this.regularExpression=regularExpression;
	}
	
	/**
	 * 正则表达式
	 * @return
	 */
	public String getRegularExpression(){
		return this.regularExpression;
	}
	/**
	 * 发货单格式
	 * @param sendFormat
	 */
	public void setSendFormat(String sendFormat){
		this.sendFormat=sendFormat;
	}
	
	/**
	 * 发货单格式
	 * @return
	 */
	public String getSendFormat(){
		return this.sendFormat;
	}
	/**
	 * 供应商id
	 * @param supplierId
	 */
	public void setSupplierId(String supplierId){
		this.supplierId=supplierId;
	}
	
	/**
	 * 供应商id
	 * @return
	 */
	public String getSupplierId(){
		return this.supplierId;
	}

	public String getPurchaserId() {
		return purchaserId;
	}

	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
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

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public List<String> getSupplierIds() {
		return supplierIds;
	}

	public void setSupplierIds(String supplierIds) {
		if(null!=supplierIds&&!"".equals(supplierIds)){
			this.supplierIds = Arrays.asList(supplierIds.split(","));
		}
	}

	public List<String> getPurchaserIds() {
		return purchaserIds;
	}

	public void setPurchaserIds(String purchaserIds) {
		if(null!=purchaserIds&&!"".equals(purchaserIds)){
			this.purchaserIds = Arrays.asList(purchaserIds.split(","));
		}
	}
	
	

}