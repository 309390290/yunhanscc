package com.yunhan.scc.backto.web.model.backreport;

import com.yunhan.scc.tools.component.module.query.QueryCondition;
import java.lang.Integer;
import java.lang.String;

/**
 * ProResponseItemsT查询条件
 * @author luohoudong
 * @version 2016-7-14 9:52:28
 */
public class ProResponseItemsTBacktoCondition extends QueryCondition {
	
	/**
	 * 创建日期
	 */
	private String addTime;
	/**
	 * 创建人
	 */
	private String addUserCode;
	/**
	 * 可供折扣
	 */
	private String availableDiscountrate;
	/**
	 * 可供价
	 */
	private String availablePrice;
	/**
	 * 满足数量
	 */
	private String availableQty;
	/**
	 * 商品名称
	 */
	private String bookTitle;
	/**
	 * 加工处理状态: Y-已加工，N-未加工
	 */
	private String dealStatus;
	/**
	 * 差异类型:0:无差异,1:数量有差异,2:折扣有差异,3:定价有差异,4:数量、折扣有差异,5:数量、定价有差异,6:折扣、定价有差异,7:数量、折扣、定价有差异
	 */
	private String diffType;
	/**
	 * 采购折扣
	 */
	private String discountrate;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * ISBN
	 */
	private String isbn;
	/**
	 * 回告差异导出标示:Y-是，N-否
	 */
	private String isDiffExport;
	/**
	 * 永久缺货导出标示:Y-是，N-否
	 */
	private String isForeverOosExport;
	/**
	 * 是否有效：Y-是，N-否
	 */
	private String isValid;
	/**
	 * 不满足原因:1、暂时缺货，2、已停产，3、改版，4、版权到期，5、商品无效，6、销售受限（后面5个都属于永久缺货类型）
	 */
	private String notEnoughReason;
	/**
	 * 订数
	 */
	private String orderQty;
	/**
	 * 预计发货日期:1）本次发货量小于满足数量，则必填；2）日期格式：YYYYMMDD
	 */
	private String preSendDate;
	/**
	 * 定价
	 */
	private String price;
	/**
	 * 订单细目表ID
	 */
	private String proPurOrderItemsId;
	/**
	 * 采购商商品ID
	 */
	private String purchaserCommoditiesId;
	/**
	 * 采购商ID
	 */
	private String purchaserId;
	/**
	 * 采购订单号
	 */
	private String purchaseOrderCode;
	/**
	 * 回告日期:YYYYMMDD
	 */
	private String responseDate;
	/**
	 * 回告品种备注
	 */
	private String responseRemark;
	/**
	 * 回告状态:0-未回告，5-已回告
	 */
	private String responseStatus;
	/**
	 * 回告类型:0-完全不满足、1-部分不满足、2-完全满足
	 */
	private String responseType;
	/**
	 * 回告人
	 */
	private String responseUserCode;
	/**
	 * 行项唯一号
	 */
	private String rowUniqueNo;
	/**
	 * 供应商发货单号
	 */
	private String sendoutGoodsCode;
	/**
	 * 订单来源类型:YHSYS-云汉报订产品、EDI、FTP、DB等，根据业务发展决定
	 */
	private String sourceType;
	/**
	 * 供应商商品ID
	 */
	private String supplierCommoditiesId;
	/**
	 * 供应商ID
	 */
	private String supplierId;
	/**
	 * 本次发货数量
	 */
	private String thisSendQty;
	/**
	 * 修改日期
	 */
	private String updateTime;
	/**
	 * 修改人
	 */
	private String updateUserCode;
	/**
	 * 云汉ID
	 */
	private String yunhanId;
	/**
	 * 平台订单号
	 */
	private String yunhanOrderCode;
	/**
	 * 创建日期
	 * @param addTime
	 */
	
	public void setAddTime(String addTime){
		this.addTime=addTime;
	}
	/**
	 * 创建日期
	 * @return
	 */
	public String getAddTime(){
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
	 * 可供折扣
	 * @param availableDiscountrate
	 */
	
	public void setAvailableDiscountrate(String availableDiscountrate){
		this.availableDiscountrate=availableDiscountrate;
	}
	/**
	 * 可供折扣
	 * @return
	 */
	public String getAvailableDiscountrate(){
		return this.availableDiscountrate;
	}
		
	/**
	 * 可供价
	 * @param availablePrice
	 */
	
	public void setAvailablePrice(String availablePrice){
		this.availablePrice=availablePrice;
	}
	/**
	 * 可供价
	 * @return
	 */
	public String getAvailablePrice(){
		return this.availablePrice;
	}
		
	/**
	 * 满足数量
	 * @param availableQty
	 */
	
	public void setAvailableQty(String availableQty){
		this.availableQty=availableQty;
	}
	/**
	 * 满足数量
	 * @return
	 */
	public String getAvailableQty(){
		return this.availableQty;
	}
		
	/**
	 * 商品名称
	 * @param bookTitle
	 */
	
	public void setBookTitle(String bookTitle){
		this.bookTitle=bookTitle;
	}
	/**
	 * 商品名称
	 * @return
	 */
	public String getBookTitle(){
		return this.bookTitle;
	}
		
	/**
	 * 加工处理状态: Y-已加工，N-未加工
	 * @param dealStatus
	 */
	
	public void setDealStatus(String dealStatus){
		this.dealStatus=dealStatus;
	}
	/**
	 * 加工处理状态: Y-已加工，N-未加工
	 * @return
	 */
	public String getDealStatus(){
		return this.dealStatus;
	}
		
	/**
	 * 差异类型:0:无差异,1:数量有差异,2:折扣有差异,3:定价有差异,4:数量、折扣有差异,5:数量、定价有差异,6:折扣、定价有差异,7:数量、折扣、定价有差异
	 * @param diffType
	 */
	
	public void setDiffType(String diffType){
		this.diffType=diffType;
	}
	/**
	 * 差异类型:0:无差异,1:数量有差异,2:折扣有差异,3:定价有差异,4:数量、折扣有差异,5:数量、定价有差异,6:折扣、定价有差异,7:数量、折扣、定价有差异
	 * @return
	 */
	public String getDiffType(){
		return this.diffType;
	}
		
	/**
	 * 采购折扣
	 * @param discountrate
	 */
	
	public void setDiscountrate(String discountrate){
		this.discountrate=discountrate;
	}
	/**
	 * 采购折扣
	 * @return
	 */
	public String getDiscountrate(){
		return this.discountrate;
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
	 * ISBN
	 * @param isbn
	 */
	
	public void setIsbn(String isbn){
		this.isbn=isbn;
	}
	/**
	 * ISBN
	 * @return
	 */
	public String getIsbn(){
		return this.isbn;
	}
		
	/**
	 * 回告差异导出标示:Y-是，N-否
	 * @param isDiffExport
	 */
	
	public void setIsDiffExport(String isDiffExport){
		this.isDiffExport=isDiffExport;
	}
	/**
	 * 回告差异导出标示:Y-是，N-否
	 * @return
	 */
	public String getIsDiffExport(){
		return this.isDiffExport;
	}
		
	/**
	 * 永久缺货导出标示:Y-是，N-否
	 * @param isForeverOosExport
	 */
	
	public void setIsForeverOosExport(String isForeverOosExport){
		this.isForeverOosExport=isForeverOosExport;
	}
	/**
	 * 永久缺货导出标示:Y-是，N-否
	 * @return
	 */
	public String getIsForeverOosExport(){
		return this.isForeverOosExport;
	}
		
	/**
	 * 是否有效：Y-是，N-否
	 * @param isValid
	 */
	
	public void setIsValid(String isValid){
		this.isValid=isValid;
	}
	/**
	 * 是否有效：Y-是，N-否
	 * @return
	 */
	public String getIsValid(){
		return this.isValid;
	}
		
	/**
	 * 不满足原因:1、暂时缺货，2、已停产，3、改版，4、版权到期，5、商品无效，6、销售受限（后面5个都属于永久缺货类型）
	 * @param notEnoughReason
	 */
	
	public void setNotEnoughReason(String notEnoughReason){
		this.notEnoughReason=notEnoughReason;
	}
	/**
	 * 不满足原因:1、暂时缺货，2、已停产，3、改版，4、版权到期，5、商品无效，6、销售受限（后面5个都属于永久缺货类型）
	 * @return
	 */
	public String getNotEnoughReason(){
		return this.notEnoughReason;
	}
		
	/**
	 * 订数
	 * @param orderQty
	 */
	
	public void setOrderQty(String orderQty){
		this.orderQty=orderQty;
	}
	/**
	 * 订数
	 * @return
	 */
	public String getOrderQty(){
		return this.orderQty;
	}
		
	/**
	 * 预计发货日期:1）本次发货量小于满足数量，则必填；2）日期格式：YYYYMMDD
	 * @param preSendDate
	 */
	
	public void setPreSendDate(String preSendDate){
		this.preSendDate=preSendDate;
	}
	/**
	 * 预计发货日期:1）本次发货量小于满足数量，则必填；2）日期格式：YYYYMMDD
	 * @return
	 */
	public String getPreSendDate(){
		return this.preSendDate;
	}
		
	/**
	 * 定价
	 * @param price
	 */
	
	public void setPrice(String price){
		this.price=price;
	}
	/**
	 * 定价
	 * @return
	 */
	public String getPrice(){
		return this.price;
	}
		
	/**
	 * 订单细目表ID
	 * @param proPurOrderItemsId
	 */
	
	public void setProPurOrderItemsId(String proPurOrderItemsId){
		this.proPurOrderItemsId=proPurOrderItemsId;
	}
	/**
	 * 订单细目表ID
	 * @return
	 */
	public String getProPurOrderItemsId(){
		return this.proPurOrderItemsId;
	}
		
	/**
	 * 采购商商品ID
	 * @param purchaserCommoditiesId
	 */
	
	public void setPurchaserCommoditiesId(String purchaserCommoditiesId){
		this.purchaserCommoditiesId=purchaserCommoditiesId;
	}
	/**
	 * 采购商商品ID
	 * @return
	 */
	public String getPurchaserCommoditiesId(){
		return this.purchaserCommoditiesId;
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
	 * 回告日期:YYYYMMDD
	 * @param responseDate
	 */
	
	public void setResponseDate(String responseDate){
		this.responseDate=responseDate;
	}
	/**
	 * 回告日期:YYYYMMDD
	 * @return
	 */
	public String getResponseDate(){
		return this.responseDate;
	}
		
	/**
	 * 回告品种备注
	 * @param responseRemark
	 */
	
	public void setResponseRemark(String responseRemark){
		this.responseRemark=responseRemark;
	}
	/**
	 * 回告品种备注
	 * @return
	 */
	public String getResponseRemark(){
		return this.responseRemark;
	}
		
	/**
	 * 回告状态:0-未回告，5-已回告
	 * @param responseStatus
	 */
	
	public void setResponseStatus(String responseStatus){
		this.responseStatus=responseStatus;
	}
	/**
	 * 回告状态:0-未回告，5-已回告
	 * @return
	 */
	public String getResponseStatus(){
		return this.responseStatus;
	}
		
	/**
	 * 回告类型:0-完全不满足、1-部分不满足、2-完全满足
	 * @param responseType
	 */
	
	public void setResponseType(String responseType){
		this.responseType=responseType;
	}
	/**
	 * 回告类型:0-完全不满足、1-部分不满足、2-完全满足
	 * @return
	 */
	public String getResponseType(){
		return this.responseType;
	}
		
	/**
	 * 回告人
	 * @param responseUserCode
	 */
	
	public void setResponseUserCode(String responseUserCode){
		this.responseUserCode=responseUserCode;
	}
	/**
	 * 回告人
	 * @return
	 */
	public String getResponseUserCode(){
		return this.responseUserCode;
	}
		
	/**
	 * 行项唯一号
	 * @param rowUniqueNo
	 */
	
	public void setRowUniqueNo(String rowUniqueNo){
		this.rowUniqueNo=rowUniqueNo;
	}
	/**
	 * 行项唯一号
	 * @return
	 */
	public String getRowUniqueNo(){
		return this.rowUniqueNo;
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
	 * 订单来源类型:YHSYS-云汉报订产品、EDI、FTP、DB等，根据业务发展决定
	 * @param sourceType
	 */
	
	public void setSourceType(String sourceType){
		this.sourceType=sourceType;
	}
	/**
	 * 订单来源类型:YHSYS-云汉报订产品、EDI、FTP、DB等，根据业务发展决定
	 * @return
	 */
	public String getSourceType(){
		return this.sourceType;
	}
		
	/**
	 * 供应商商品ID
	 * @param supplierCommoditiesId
	 */
	
	public void setSupplierCommoditiesId(String supplierCommoditiesId){
		this.supplierCommoditiesId=supplierCommoditiesId;
	}
	/**
	 * 供应商商品ID
	 * @return
	 */
	public String getSupplierCommoditiesId(){
		return this.supplierCommoditiesId;
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
	 * 本次发货数量
	 * @param thisSendQty
	 */
	
	public void setThisSendQty(String thisSendQty){
		this.thisSendQty=thisSendQty;
	}
	/**
	 * 本次发货数量
	 * @return
	 */
	public String getThisSendQty(){
		return this.thisSendQty;
	}
		
	/**
	 * 修改日期
	 * @param updateTime
	 */
	
	public void setUpdateTime(String updateTime){
		this.updateTime=updateTime;
	}
	/**
	 * 修改日期
	 * @return
	 */
	public String getUpdateTime(){
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
	 * 云汉ID
	 * @param yunhanId
	 */
	
	public void setYunhanId(String yunhanId){
		this.yunhanId=yunhanId;
	}
	/**
	 * 云汉ID
	 * @return
	 */
	public String getYunhanId(){
		return this.yunhanId;
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
		
}
