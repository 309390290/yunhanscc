package com.yunhan.scc.backto.web.model.sendgoods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutItemsBacktoDO;
import com.yunhan.scc.tools.component.module.query.QueryCondition;

/**
 * ProSendoutSummary查询条件
 * @author luohoudong
 * @version 2016-7-15 16:04:14
 */
public class ProSendoutSummaryBacktoCondition extends QueryCondition {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -83610540268273760L;
	/**
	 * 创建日期
	 */
	private Date addTime;
	/**
	 * 创建人
	 */
	private String addUserCode;
	/**
	 * 到站
	 */
	private String destination;
	/**
	 * 发货随单号
	 */
	private String followNo;
	/**
	 * 主键
	 */
	private Long id;
	private List<String> ids;
	/**
	 * 删除标记：Y-已删除，N-未删除
	 */
	private String isDelete;
	/**
	 * 是否已导出:Y-是，N-否
	 */
	private String isExport;
	/**
	 * 发货单主发标识:Y-是，N-否
	 */
	private String isInitiative;
	/**
	 * 供应商添加商品标识:Y-是，N-否
	 */
	private String isSupplierAddProduct;
	/**
	 * 包件数
	 */
	private Integer pakagesQty;
	/**
	 * 采购商ID
	 */
	private String purchaserId;
	/**
	 * 采购订单号(暂时不用)
	 */
	private String purchaseOrderCode;
	/**
	 * 采购订单号
	 */
	private List<String> purchaseOrderCodes;
	/**
	 * 收货地址
	 */
	private String receiveAddress;
	/**
	 * 收货单位
	 */
	private String receiveCompany;
	/**
	 * 收货电话
	 */
	private String receivePhoneno;
	/**
	 * 收货备注
	 */
	private String receiveRemark;
	/**
	 * 收货人
	 */
	private String receiveUserName;
	/**
	 * 收货仓位
	 */
	private String receiveWarehouse;
	/**
	 * 发货日期：YYYYMMDD
	 */
	private Date sendoutDate;
	/**
	 * 供应商发货单号
	 */
	private String sendoutGoodsCode;
	/**
	 * 供应商发货单号
	 */
	private List<String> sendoutGoodsCodes;
	/**
	 * 发货备注
	 */
	private String sendoutRemark;
	/**
	 * 发出站
	 */
	private String sendoutStation;
	/**
	 * 发货单状态：0-未发货，5-已发货（未收货），10-部分收货，15-全部收货
	 */
	private Integer sendoutStatus;
	/**
	*发货单状态：发货单状态：0-未发货，5-已发货（未收货），10-部分收货，15-全部收货
	*/
	private List<Integer> sendoutStatuss;
	/**
	 * 数据来源类型:YHSYS-云汉报订产品、EDI、FTP、DB等，根据业务发展决定
	 */
	private String sourceType;
	/**
	 * 供货商联系电话
	 */
	private String supplierContactPhoneno;
	/**
	 * 供货商联系人
	 */
	private String supplierContactUser;
	/**
	 * 供应商ID
	 */
	private String supplierId;
	/**
	 * 发货总册数
	 */
	private Integer totalBookQty;
	/**
	 * 总码洋
	 */
	private BigDecimal totalPrice;
	/**
	 * 总实洋
	 */
	private BigDecimal totalRealityPrice;
	/**
	 * 发货品种总数
	 */
	private Integer totalVarietyQty;
	/**
	 * 承运商
	 */
	private String transportCompany;
	/**
	 * 运输方式：1：汽车,2：火车.....
	 */
	private String transportMode;
	/**
	 * 运单号
	 */
	private String transportNo;
	/**
	 * 承运商联系方式
	 */
	private String transportPhoneno;
	/**
	 * 修改日期
	 */
	private Date updateTime;
	/**
	 * 修改人
	 */
	private String updateUserCode;
	/**
	 * 平台订单号(暂时不用)
	 */
	private String yunhanOrderCode;
	/**
	 * 平台发货单号(暂时不启用)
	 */
	private String yunhanSendoutCode;
	
	/**
	 *回告细目ids
	 */
	private List<String>  responseItemsIds;
	/**
	 * 订单细目ids
	 */
	private List<String> orderItemsIds;

	/**
	 * 发货日期 From
	 */
	private String sendoutDateFrom;
	/**
	 * 发货日期 To
	 */
	private String sendoutDateTo;
	/**
	 * 制单日期 From
	 */
	private String addDateFrom;
	/**
	 * 制单日期 To
	 */
	private String addDateTo;
	
	/**
	 * 发货单细目list
	 */
	private List<ProSendoutItemsBacktoCondition> sendOutItems;
	/**
	 * 仓位列表
	 */
	private List<String> receiveWarehouses;
	
	public ProSendoutSummaryBacktoCondition(String _purchaserId,String _supplierId,String _sendoutGoodsCode) {
		this.purchaserId=_purchaserId;
		this.supplierId=_supplierId;
		this.sendoutGoodsCode=_sendoutGoodsCode;
	}
	
	public ProSendoutSummaryBacktoCondition() {
	}
	
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
		
	public List<ProSendoutItemsBacktoCondition> getSendOutItems() {
		return sendOutItems;
	}
	public void setSendOutItems(List<ProSendoutItemsBacktoCondition> sendOutItems) {
		this.sendOutItems = sendOutItems;
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
	 * 删除标记：Y-已删除，N-未删除
	 * @param isDelete
	 */
	
	public void setIsDelete(String isDelete){
		this.isDelete=isDelete;
	}
	/**
	 * 删除标记：Y-已删除，N-未删除
	 * @return
	 */
	public String getIsDelete(){
		return this.isDelete;
	}
		
	/**
	 * 是否已导出:Y-是，N-否
	 * @param isExport
	 */
	
	public void setIsExport(String isExport){
		this.isExport=isExport;
	}
	/**
	 * 是否已导出:Y-是，N-否
	 * @return
	 */
	public String getIsExport(){
		return this.isExport;
	}
		
	/**
	 * 发货单主发标识:Y-是，N-否
	 * @param isInitiative
	 */
	
	public void setIsInitiative(String isInitiative){
		this.isInitiative=isInitiative;
	}
	/**
	 * 发货单主发标识:Y-是，N-否
	 * @return
	 */
	public String getIsInitiative(){
		return this.isInitiative;
	}
		
	/**
	 * 供应商添加商品标识:Y-是，N-否
	 * @param isSupplierAddProduct
	 */
	
	public void setIsSupplierAddProduct(String isSupplierAddProduct){
		this.isSupplierAddProduct=isSupplierAddProduct;
	}
	/**
	 * 供应商添加商品标识:Y-是，N-否
	 * @return
	 */
	public String getIsSupplierAddProduct(){
		return this.isSupplierAddProduct;
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
	 * 采购订单号(暂时不用)
	 * @param purchaseOrderCode
	 */
	
	public void setPurchaseOrderCode(String purchaseOrderCode){
		this.purchaseOrderCode=purchaseOrderCode;
	}
	/**
	 * 采购订单号(暂时不用)
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
		this.sendoutGoodsCode=sendoutGoodsCode;
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
	 * 发货单状态：0-未发货，5-已发货（未收货），10-部分收货，15-全部收货
	 * @param sendoutStatus
	 */
	
	public void setSendoutStatus(Integer sendoutStatus){
		this.sendoutStatus=sendoutStatus;
	}
	/**
	 * 发货单状态：0-未发货，5-已发货（未收货），10-部分收货，15-全部收货
	 * @return
	 */
	public Integer getSendoutStatus(){
		return this.sendoutStatus;
	}
		
	/**
	 * 数据来源类型:YHSYS-云汉报订产品、EDI、FTP、DB等，根据业务发展决定
	 * @param sourceType
	 */
	
	public void setSourceType(String sourceType){
		this.sourceType=sourceType;
	}
	/**
	 * 数据来源类型:YHSYS-云汉报订产品、EDI、FTP、DB等，根据业务发展决定
	 * @return
	 */
	public String getSourceType(){
		return this.sourceType;
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
	 * 运输方式：1：汽车,2：火车.....
	 * @param transportMode
	 */
	
	public void setTransportMode(String transportMode){
		this.transportMode=transportMode;
	}
	/**
	 * 运输方式：1：汽车,2：火车.....
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
	 * 平台订单号(暂时不用)
	 * @param yunhanOrderCode
	 */
	
	public void setYunhanOrderCode(String yunhanOrderCode){
		this.yunhanOrderCode=yunhanOrderCode;
	}
	/**
	 * 平台订单号(暂时不用)
	 * @return
	 */
	public String getYunhanOrderCode(){
		return this.yunhanOrderCode;
	}
		
	/**
	 * 平台发货单号(暂时不启用)
	 * @param yunhanSendoutCode
	 */
	
	public void setYunhanSendoutCode(String yunhanSendoutCode){
		this.yunhanSendoutCode=yunhanSendoutCode;
	}
	/**
	 * 平台发货单号(暂时不启用)
	 * @return
	 */
	public String getYunhanSendoutCode(){
		return this.yunhanSendoutCode;
	}
	/**
	 * @return the responseItemsIds
	 */
	public List<String> getResponseItemsIds() {
		return responseItemsIds;
	}
	/**
	 * @param responseItemsIds the responseItemsIds to set
	 */
	public void setResponseItemsIds(String responseItemsIds) {
		String[] str=null;
		if(!"".equals(responseItemsIds)&& responseItemsIds!=null){
			str=responseItemsIds.split(",");
		}
		if(str!=null){
			this.responseItemsIds = Arrays.asList(str);
		}
	}
	/**
	 * @return the orderItemsIds
	 */
	public List<String> getOrderItemsIds() {
		return orderItemsIds;
	}
	/**
	 * @param orderItemsIds the orderItemsIds to set
	 */
	public void setOrderItemsIds(String orderItemsIds) {
		String[] str=null;
		if(!"".equals(orderItemsIds)&& orderItemsIds!=null){
			str=orderItemsIds.split(",");
		}
		if(str!=null){
			this.orderItemsIds = Arrays.asList(str);
		}
	}
	
	public List<String> getPurchaseOrderCodes() {
		return purchaseOrderCodes;
	}
	public void setPurchaseOrderCodes(String purchaseOrderCodes) {
		if(null != purchaseOrderCodes && !purchaseOrderCodes.equals("")){
			this.purchaseOrderCodes = Arrays.asList(purchaseOrderCodes.split(","));
		}else{
			this.purchaseOrderCodes = null;
		}
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(String ids) {
		if(null != ids && !ids.equals("")){
			String[] split = ids.split(",");
			List<String> list = new ArrayList<>();
			for(String s :split){
				if(s.equals("")){
					continue;
				}
				list.add(s);
			}
			this.ids = list;
		}else{
			this.ids = null;
		}
	}
	
	public List<String> getSendoutGoodsCodes() {
		return sendoutGoodsCodes;
	}
	public void setSendoutGoodsCodes(String sendoutGoodsCodes) {
		if(null != sendoutGoodsCodes && !sendoutGoodsCodes.equals("")){
//			this.sendoutGoodsCodes = Arrays.asList(sendoutGoodsCodes.split(","));
			String [] sendOutCodes = sendoutGoodsCodes.split(",");
			this.sendoutGoodsCodes = new ArrayList<String>();
			for(String sendOutCode :sendOutCodes){
				this.sendoutGoodsCodes.add(sendOutCode.trim());
			}
		}else{
			this.sendoutGoodsCodes = null;
		}
	}
	public List<Integer> getSendoutStatuss() {
		return sendoutStatuss;
	}
	public void setSendoutStatuss(String sendoutStatuss) {
		if(null != sendoutStatuss && !sendoutStatuss.equals("")){
			String[] split = sendoutStatuss.split(",");
			List<Integer> list = new ArrayList<>();
			for(String s :split){
				list.add(Integer.valueOf(s));
			}
			this.sendoutStatuss = list;
		}else{
			this.sendoutStatuss = null;
		}
	}
	public String getSendoutDateFrom() {
		return sendoutDateFrom;
	}
	public void setSendoutDateFrom(String sendoutDateFrom) {
		this.sendoutDateFrom = sendoutDateFrom;
	}
	public String getSendoutDateTo() {
		return sendoutDateTo;
	}
	public void setSendoutDateTo(String sendoutDateTo) {
		this.sendoutDateTo = sendoutDateTo;
	}
	public String getAddDateFrom() {
		return addDateFrom;
	}
	public void setAddDateFrom(String addDateFrom) {
		this.addDateFrom = addDateFrom;
	}
	public String getAddDateTo() {
		return addDateTo;
	}
	public void setAddDateTo(String addDateTo) {
		this.addDateTo = addDateTo;
	}
	/**
	 * 用于页面传参
	 */
	private String sendOutItemsJson;
	
	public String getSendOutItemsJson() {
		return sendOutItemsJson;
	}
	public void setSendOutItemsJson(String sendOutItemsJson) {
		this.sendOutItems = JSONArray.parseArray(sendOutItemsJson,ProSendoutItemsBacktoCondition.class);
		this.sendOutItemsJson = sendOutItemsJson;
	}

	public List<String> getReceiveWarehouses() {
		return receiveWarehouses;
	}

	public void setReceiveWarehouses(String receiveWarehouses) {
		if(null != receiveWarehouses && !"".equals(receiveWarehouses)){
			this.receiveWarehouses = Arrays.asList(receiveWarehouses.split(","));
		}
	}
		
}
