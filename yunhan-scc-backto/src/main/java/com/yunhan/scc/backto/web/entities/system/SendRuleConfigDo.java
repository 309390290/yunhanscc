package com.yunhan.scc.backto.web.entities.system;

import com.yunhan.scc.tools.component.module.query.QueryResult;
import java.lang.Integer;
import java.lang.String;


/**
 * 发货单规则配置 实体
 * @author xiongmingbao
 * @version 2016-8-24 14:58:59
 */
public class SendRuleConfigDo extends QueryResult

{
	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	*
	*/
	private Long id;
	/**
	*发货单长度
	*/
	private Integer sendoutGoodsLength;
	/**
	*发货单长度规则（A：长度至少多少位、B：长度固定多少位）
	*/
	private String lengthRule;
	/**
	*正则表达式
	*/
	private String regularExpression;
	/**
	*发货单格式(A:任意字符，B:数字，C:字母，D:中文，E:特殊字符)
	*/
	private String sendFormat;
	/**
	*供应商id
	*/
	private String supplierId;
	/**
	*供应商名称
	*/
	private String supplierName;
	/**
	* 采购商ID
	* 2016-10-19 wuyounan
	*/
	private String purchaserId;
	/**
	* 采购商名称
	* 2016-10-19 wuyounan
	*/
	private String purchaserName;
	/**
	* 是否启用标示
	* 2016-10-19 wuyounan
	*/
	private String isValid;

	/**
	 * 
	 * @param id
	 */
	public void setId(Long id){
		this.id=id;
	}
	
	/**
	 * 
	 * @return
	 */
	public Long getId(){
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
	 * 发货单长度规则（A：长度至少多少位、B：长度固定多少位）
	 * @param lengthRule
	 */
	public void setLengthRule(String lengthRule){
		this.lengthRule=lengthRule;
	}
	
	/**
	 * 发货单长度规则（A：长度至少多少位、B：长度固定多少位）
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
	 * 发货单格式(A:任意字符，B:数字，C:字母，D:中文，E:特殊字符)
	 * @param sendFormat
	 */
	public void setSendFormat(String sendFormat){
		this.sendFormat=sendFormat;
	}
	
	/**
	 * 发货单格式(A:任意字符，B:数字，C:字母，D:中文，E:特殊字符)
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
	/**
	 * 采购商ID
	 * 2016-10-19 wuyounan
	 * @return
	 */
	public String getPurchaserId() {
		return purchaserId;
	}
	/**
	 * 采购商ID
	 * 2016-10-19 wuyounan
	 * @param purchaserId
	 */
	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}
	/**
	 * 是否启用标示
	 * 2016-10-19 wuyounan
	 * @return
	 */
	public String getIsValid() {
		return isValid;
	}
	/**
	 * 是否启用标示
	 * 2016-10-19 wuyounan
	 * @param isValid
	 */
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPurchaserName() {
		return purchaserName;
	}

	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}

}