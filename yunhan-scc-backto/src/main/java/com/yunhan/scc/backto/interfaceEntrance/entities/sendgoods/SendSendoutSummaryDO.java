package com.yunhan.scc.backto.interfaceEntrance.entities.sendgoods;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.yunhan.scc.tools.util.StringUtil;


/**
 * 待发送_发货单总目 实体
 * @author wangtao
 * @version 2016年7月21日15:01:01
 */
public class SendSendoutSummaryDO{
	/**
	*创建日期
	*/
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	/**
	*创建人
	*/
	private String addUserCode;
	/**
	*到站
	*/
	private String destination;
	/**
	*发货随单号
	*/
	private String followNo;
	/**
	*主键
	*/
	private Long id;
	/**
	*发货单主发标识:Y-是，N-否
	*/
	private String isInitiative;
	/**
	*供应商添加商品标识:Y-是，N-否
	*/
	private String isSupplierAddProduct;
	/**
	*包件数
	*/
	private Integer pakagesQty;
	/**
	*采购商ID
	*/
	private String purchaserId;
	/**
	*采购订单号
	*/
	private String purchaseOrderCode;
	/**
	*收货地址
	*/
	private String receiveAddress;
	/**
	*收货单位
	*/
	private String receiveCompany;
	/**
	*收货电话
	*/
	private String receivePhoneno;
	/**
	*收货备注
	*/
	private String receiveRemark;
	/**
	*收货人
	*/
	private String receiveUserName;
	/**
	*收货仓位
	*/
	private String receiveWarehouse;
	/**
	*发货日期：YYYYMMDD
	*/
	private Date sendoutDate;

	/**
	*供应商发货单号
	*/
	private String sendoutGoodsCode;
	/**
	*发货备注
	*/
	private String sendoutRemark;
	/**
	*发出站
	*/
	private String sendoutStation;
	/**
	*发货单状态：发货单状态：0-未发货，5-已发货（未收货），10-部分收货，15-全部收货
	*/
	private Integer sendoutStatus;
	/**
	*供货商联系电话
	*/
	private String supplierContactPhoneno;
	/**
	*供货商联系人
	*/
	private String supplierContactUser;
	/**
	*供应商ID
	*/
	private String supplierId;
	/**
	*发货总册数
	*/
	private Integer totalBookQty;
	/**
	*总码洋
	*/
	private BigDecimal totalPrice;
	/**
	*总实洋
	*/
	private BigDecimal totalRealityPrice;
	/**
	*发货品种总数
	*/
	private Integer totalVarietyQty;
	/**
	*承运商
	*/
	private String transportCompany;
	/**
	*运输方式：1：汽车,2：火车
	*/
	private String transportMode;
	/**
	*运单号
	*/
	private String transportNo;
	/**
	*承运商联系方式
	*/
	private String transportPhoneno;
	/**
	*修改日期
	*/
	private Date updateTime;
	/**
	*修改人
	*/
	private String updateUserCode;
	/**
	*平台订单号
	*/
	private String yunhanOrderCode;
	/**
	*云汉发货单号
	*/
	private String yunhanSendoutCode;
	/**
	 * 删除标识  Y-已删除  N-未删除
	 */
	private String isDelete;
	/**
	 * 发货单细目list
	 */
	private List<SendSendoutItemsDO> sendOutItems;
	
	
	/**
	 * 发货日期String类型  added by  luohoudong
	 */
	private String sendoutDateStr;
	
	

	/**
	 * 创建日期
	 * @param addTime
	 */
	public void setAddTime(Date addTime){
		this.addTime=addTime;
	}
	
	/**
	 * 创建日期
	 * @return
	 */
	public Date getAddTime(){
		return this.addTime;
	}
	/**
	 * 创建人
	 * @param addUserCode
	 */
	public void setAddUserCode(String addUserCode){
		this.addUserCode=addUserCode;
	}
	
	/**
	 * 创建人
	 * @return
	 */
	public String getAddUserCode(){
		return this.addUserCode;
	}
	/**
	 * 到站
	 * @param destination
	 */
	public void setDestination(String destination){
		this.destination=destination;
	}
	
	/**
	 * 到站
	 * @return
	 */
	public String getDestination(){
		return this.destination;
	}
	/**
	 * 发货随单号
	 * @param followNo
	 */
	public void setFollowNo(String followNo){
		this.followNo=followNo;
	}
	
	/**
	 * 发货随单号
	 * @return
	 */
	public String getFollowNo(){
		return this.followNo;
	}
	/**
	 * 主键
	 * @param id
	 */
	public void setId(Long id){
		this.id=id;
	}
	
	/**
	 * 主键
	 * @return
	 */
	public Long getId(){
		return this.id;
	}
	/**
	 * 发货单主发标识:Y-是，N-否
	 * @param isInitiative
	 */
	
	public void setIsInitiative(String isInitiative){
		this.isInitiative=StringUtil.parseStringToNull(isInitiative, "N");
	}
	/**
	 * 发货单主发标识:Y-是，N-否
	 * @return
	 */
	public String getIsInitiative(){
		return StringUtil.parseStringToNull(this.isInitiative, "N");
	}
	/**
	 * 包件数
	 * @param pakagesQty
	 */
	public void setPakagesQty(Integer pakagesQty){
		this.pakagesQty=pakagesQty;
	}
	
	/**
	 * 包件数
	 * @return
	 */
	public Integer getPakagesQty(){
		return this.pakagesQty;
	}
	/**
	 * 采购商ID
	 * @param purchaserId
	 */
	public void setPurchaserId(String purchaserId){
		this.purchaserId=purchaserId;
	}
	
	/**
	 * 采购商ID
	 * @return
	 */
	public String getPurchaserId(){
		return this.purchaserId;
	}
	/**
	 * 采购订单号
	 * @param purchaseOrderCode
	 */
	public void setPurchaseOrderCode(String purchaseOrderCode){
		this.purchaseOrderCode=purchaseOrderCode;
	}
	
	/**
	 * 采购订单号
	 * @return
	 */
	public String getPurchaseOrderCode(){
		return this.purchaseOrderCode;
	}
	/**
	 * 收货地址
	 * @param receiveAddress
	 */
	public void setReceiveAddress(String receiveAddress){
		this.receiveAddress=receiveAddress;
	}
	
	/**
	 * 收货地址
	 * @return
	 */
	public String getReceiveAddress(){
		return this.receiveAddress;
	}
	/**
	 * 收货单位
	 * @param receiveCompany
	 */
	public void setReceiveCompany(String receiveCompany){
		this.receiveCompany=receiveCompany;
	}
	
	/**
	 * 收货单位
	 * @return
	 */
	public String getReceiveCompany(){
		return this.receiveCompany;
	}
	/**
	 * 收货电话
	 * @param receivePhoneno
	 */
	public void setReceivePhoneno(String receivePhoneno){
		this.receivePhoneno=receivePhoneno;
	}
	
	/**
	 * 收货电话
	 * @return
	 */
	public String getReceivePhoneno(){
		return this.receivePhoneno;
	}
	/**
	 * 收货备注
	 * @param receiveRemark
	 */
	public void setReceiveRemark(String receiveRemark){
		this.receiveRemark=receiveRemark;
	}
	
	/**
	 * 收货备注
	 * @return
	 */
	public String getReceiveRemark(){
		return this.receiveRemark;
	}
	/**
	 * 收货人
	 * @param receiveUserName
	 */
	public void setReceiveUserName(String receiveUserName){
		this.receiveUserName=receiveUserName;
	}
	
	/**
	 * 收货人
	 * @return
	 */
	public String getReceiveUserName(){
		return this.receiveUserName;
	}
	/**
	 * 收货仓位
	 * @param receiveWarehouse
	 */
	public void setReceiveWarehouse(String receiveWarehouse){
		this.receiveWarehouse=receiveWarehouse;
	}
	
	/**
	 * 收货仓位
	 * @return
	 */
	public String getReceiveWarehouse(){
		return this.receiveWarehouse;
	}
	/**
	 * 发货日期：YYYYMMDD
	 * @param sendoutDate
	 */
	public void setSendoutDate(Date sendoutDate){
		  this.sendoutDate=sendoutDate;
	}
	
	/**
	 * 发货日期：YYYYMMDD
	 * @return
	 */
	public Date getSendoutDate(){
		return this.sendoutDate;
	}
	/**
	 * 供应商发货单号
	 * @param sendoutGoodsCode
	 */
	public void setSendoutGoodsCode(String sendoutGoodsCode){
		if(sendoutGoodsCode!=null){
			this.sendoutGoodsCode=sendoutGoodsCode.trim();
		}else{
			this.sendoutGoodsCode=null;
		}
	}
	
	/**
	 * 供应商发货单号
	 * @return
	 */
	public String getSendoutGoodsCode(){
		return this.sendoutGoodsCode;
	}
	/**
	 * 发货备注
	 * @param sendoutRemark
	 */
	public void setSendoutRemark(String sendoutRemark){
		this.sendoutRemark=sendoutRemark;
	}
	
	/**
	 * 发货备注
	 * @return
	 */
	public String getSendoutRemark(){
		return this.sendoutRemark;
	}
	/**
	 * 发出站
	 * @param sendoutStation
	 */
	public void setSendoutStation(String sendoutStation){
		this.sendoutStation=sendoutStation;
	}
	
	/**
	 * 发出站
	 * @return
	 */
	public String getSendoutStation(){
		return this.sendoutStation;
	}
	/**
	 * 发货单状态：发货单状态：0-未发货，5-已发货（未收货），10-部分收货，15-全部收货
	 * @param sendoutStatus
	 */
	public void setSendoutStatus(Integer sendoutStatus){
		this.sendoutStatus=sendoutStatus;
	}
	
	/**
	 * 发货单状态：发货单状态：0-未发货，5-已发货（未收货），10-部分收货，15-全部收货
	 * @return
	 */
	public Integer getSendoutStatus(){
		return this.sendoutStatus;
	}
	/**
	 * 供货商联系电话
	 * @param supplierContactPhoneno
	 */
	public void setSupplierContactPhoneno(String supplierContactPhoneno){
		this.supplierContactPhoneno=supplierContactPhoneno;
	}
	
	/**
	 * 供货商联系电话
	 * @return
	 */
	public String getSupplierContactPhoneno(){
		return this.supplierContactPhoneno;
	}
	/**
	 * 供货商联系人
	 * @param supplierContactUser
	 */
	public void setSupplierContactUser(String supplierContactUser){
		this.supplierContactUser=supplierContactUser;
	}
	
	/**
	 * 供货商联系人
	 * @return
	 */
	public String getSupplierContactUser(){
		return this.supplierContactUser;
	}
	/**
	 * 供应商ID
	 * @param supplierId
	 */
	public void setSupplierId(String supplierId){
		this.supplierId=supplierId;
	}
	
	/**
	 * 供应商ID
	 * @return
	 */
	public String getSupplierId(){
		return this.supplierId;
	}
	/**
	 * 发货总册数
	 * @param totalBookQty
	 */
	public void setTotalBookQty(Integer totalBookQty){
		this.totalBookQty=totalBookQty;
	}
	
	/**
	 * 发货总册数
	 * @return
	 */
	public Integer getTotalBookQty(){
		return this.totalBookQty;
	}
	/**
	 * 总码洋
	 * @param totalPrice
	 */
	public void setTotalPrice(BigDecimal totalPrice){
		this.totalPrice=totalPrice;
	}
	
	/**
	 * 总码洋
	 * @return
	 */
	public BigDecimal getTotalPrice(){
		return this.totalPrice;
	}
	/**
	 * 总实洋
	 * @param totalRealityPrice
	 */
	public void setTotalRealityPrice(BigDecimal totalRealityPrice){
		this.totalRealityPrice=totalRealityPrice;
	}
	
	/**
	 * 总实洋
	 * @return
	 */
	public BigDecimal getTotalRealityPrice(){
		return this.totalRealityPrice;
	}
	/**
	 * 发货品种总数
	 * @param totalVarietyQty
	 */
	public void setTotalVarietyQty(Integer totalVarietyQty){
		this.totalVarietyQty=totalVarietyQty;
	}
	
	/**
	 * 发货品种总数
	 * @return
	 */
	public Integer getTotalVarietyQty(){
		return this.totalVarietyQty;
	}
	/**
	 * 承运商
	 * @param transportCompany
	 */
	public void setTransportCompany(String transportCompany){
		this.transportCompany=transportCompany;
	}
	
	/**
	 * 承运商
	 * @return
	 */
	public String getTransportCompany(){
		return this.transportCompany;
	}
	/**
	 * 运输方式：1：汽车,2：火车
	 * @param transportMode
	 */
	public void setTransportMode(String transportMode){
		this.transportMode=transportMode;
	}
	
	/**
	 * 运输方式：1：汽车,2：火车
	 * @return
	 */
	public String getTransportMode(){
		return this.transportMode;
	}
	/**
	 * 运单号
	 * @param transportNo
	 */
	public void setTransportNo(String transportNo){
		this.transportNo=transportNo;
	}
	
	/**
	 * 运单号
	 * @return
	 */
	public String getTransportNo(){
		return this.transportNo;
	}
	/**
	 * 承运商联系方式
	 * @param transportPhoneno
	 */
	public void setTransportPhoneno(String transportPhoneno){
		this.transportPhoneno=transportPhoneno;
	}
	
	/**
	 * 承运商联系方式
	 * @return
	 */
	public String getTransportPhoneno(){
		return this.transportPhoneno;
	}
	/**
	 * 修改日期
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
	
	/**
	 * 修改日期
	 * @return
	 */
	public Date getUpdateTime(){
		return this.updateTime;
	}
	/**
	 * 修改人
	 * @param updateUserCode
	 */
	public void setUpdateUserCode(String updateUserCode){
		this.updateUserCode=updateUserCode;
	}
	
	/**
	 * 修改人
	 * @return
	 */
	public String getUpdateUserCode(){
		return this.updateUserCode;
	}
	/**
	 * 平台订单号
	 * @param yunhanOrderCode
	 */
	public void setYunhanOrderCode(String yunhanOrderCode){
		this.yunhanOrderCode=yunhanOrderCode;
	}
	
	/**
	 * 平台订单号
	 * @return
	 */
	public String getYunhanOrderCode(){
		return this.yunhanOrderCode;
	}
	/**
	 * 云汉发货单号
	 * @param yunhanSendoutCode
	 */
	public void setYunhanSendoutCode(String yunhanSendoutCode){
		this.yunhanSendoutCode=yunhanSendoutCode;
	}
	
	/**
	 * 云汉发货单号
	 * @return
	 */
	public String getYunhanSendoutCode(){
		return this.yunhanSendoutCode;
	}

	/**
	 * @return the sendOutItems
	 */
	public List<SendSendoutItemsDO> getSendOutItems() {
		return sendOutItems;
	}

	/**
	 * @param sendOutItems the sendOutItems to set
	 */
	public void setSendOutItems(List<SendSendoutItemsDO> sendOutItems) {
		this.sendOutItems = sendOutItems;
	}

	/**
	 * @return the sendoutDateStr
	 */
	public String getSendoutDateStr() {
		return sendoutDateStr;
	}

	/**
	 * @param sendoutDateStr the sendoutDateStr to set
	 */
	public void setSendoutDateStr(String sendoutDateStr) {
		this.sendoutDateStr = sendoutDateStr;
	}
	
	

	public String getIsSupplierAddProduct() {
		return StringUtil.parseStringToNull(this.isSupplierAddProduct, "N");
	}
	public void setIsSupplierAddProduct(String isSupplierAddProduct) {
		this.isSupplierAddProduct = StringUtil.parseStringToNull(isSupplierAddProduct, "N");
		
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
}