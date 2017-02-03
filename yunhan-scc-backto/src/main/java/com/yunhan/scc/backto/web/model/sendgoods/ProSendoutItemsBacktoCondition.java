package com.yunhan.scc.backto.web.model.sendgoods;

import com.yunhan.scc.tools.component.module.query.QueryCondition;
import java.lang.Integer;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ProSendoutItems查询条件
 * @author wangtao
 * @version 2016-7-6 15:29:49
 */
public class ProSendoutItemsBacktoCondition extends QueryCondition {
	
	/**
	 * 创建日期
	 */
	private Date addTime;
	/**
	 * 创建人
	 */
	private String addUserCode;
	/**
	 * 附属丛书名
	 */
	private String affiliateSeries;
	/**
	 * 著作者
	 */
	private String authorEditor;
	/**
	 * 商品名称
	 */
	private String bookTitle;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 印次
	 */
	private String impression;
	/**
	 * ISBN
	 */
	private String isbn;
	/**
	 * ISRC号
	 */
	private String isrc;
	/**
	 * 删除标记：Y-已删除，N-未删除
	 */
	private String isDelete;
	/**
	 * 回告差异导出标示:Y-是，N-否
	 */
	private String isDiffExport;
	/**
	 * 是否已导出:Y-是，N-否
	 */
	private String isExport;
	/**
	 * 永久缺货导出标示:Y-是，N-否
	 */
	private String isForeverOosExport;
	/**
	 * 发货单主发标识:Y-是，N-否
	 */
	private String isInitiative;
	/**
	 * 供应商添加商品标识:Y-是，N-否
	 */
	private String isSupplierAddProduct;
	/**
	 * 暂时缺货标示：Y-是，N-否
	 */
	private String notEnoughFlag;
	/**
	 * 包号
	 */
	private String packageId;
	/**
	 * 每包扎数
	 */
	private Integer pakageNum;
	/**
	 * 装帧
	 */
	private String paperback;
	/**
	 * 定价
	 */
	private BigDecimal price;
	/**
	 * 订单细目ID
	 */
	private Long proPurOrderItemsId;
	/**
	 * 出版者
	 */
	private String publisher;
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
	 * 收货日期(最近一次收货日期)
	 */
	private Date receiveDate;
	/**
	 * 收货数量(累加)
	 */
	private Integer receiveQty;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 回告状态：0-未回告，5-已回告
	 */
	private String responseStatus;
	/**
	 * 回告类型:0-完全不满足、1-部分不满足、2-完全满足
	 */
	private String responseType;
	/**
	 * 行项唯一号
	 */
	private String rowUniqueNo;
	/**
	 * 销售单价
	 */
	private Integer salesPrices;
	/**
	 * 发货折扣
	 */
	private BigDecimal sendoutDiscountrate;
	/**
	 * 供应商发货单号
	 */
	private String sendoutGoodsCode;
	/**
	 * 发货价
	 */
	private BigDecimal sendoutPrice;
	/**
	 * 发货数
	 */
	private Integer sendoutQty;
	/**
	 * 从书名
	 */
	private String seriesTitle;
	/**
	 * 数据来源类型:YHSYS-云汉报订产品、EDI、FTP、DB等，根据业务发展决定
	 */
	private String sourceType;
	/**
	 * 发货单状态：0-未发货，5-已发货（未收货），10-部分收货，15-全部收货
	 */
	private Integer status;
	/**
	 * 征订代码
	 */
	private String subscriptionCode;
	/**
	 * 供应商商品ID
	 */
	private String supplierCommoditiesId;
	/**
	 * 供应商ID
	 */
	private String supplierId;
	/**
	 * userCode
	 */
	private String userCode;
	/**
	 * 修改日期
	 */
	private Date updateTime;
	/**
	 * 修改人
	 */
	private String updateUserCode;
	/**
	 * 本版版次
	 */
	private String versionNo;
	/**
	 * 每扎册数
	 */
	private Integer volumes;
	/**
	 * 云汉ID
	 */
	private String yunhanId;
	/**
	 * 平台订单号
	 */
	private String yunhanOrderCode;
	/**
	 * 平台发货单号(暂时不启用)
	 */
	private String yunhanSendoutCode;
	
	
	public ProSendoutItemsBacktoCondition() {
	}
	public ProSendoutItemsBacktoCondition(Long _id) {
		this.id = _id;
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
	 * 附属丛书名
	 * @param affiliateSeries
	 */
	
	public void setAffiliateSeries(String affiliateSeries){
		this.affiliateSeries=affiliateSeries;
	}
	/**
	 * 附属丛书名
	 * @return
	 */
	public String getAffiliateSeries(){
		return this.affiliateSeries;
	}
		
	/**
	 * 著作者
	 * @param authorEditor
	 */
	
	public void setAuthorEditor(String authorEditor){
		this.authorEditor=authorEditor;
	}
	/**
	 * 著作者
	 * @return
	 */
	public String getAuthorEditor(){
		return this.authorEditor;
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
	 * 印次
	 * @param impression
	 */
	
	public void setImpression(String impression){
		this.impression=impression;
	}
	/**
	 * 印次
	 * @return
	 */
	public String getImpression(){
		return this.impression;
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
	 * ISRC号
	 * @param isrc
	 */
	
	public void setIsrc(String isrc){
		this.isrc=isrc;
	}
	/**
	 * ISRC号
	 * @return
	 */
	public String getIsrc(){
		return this.isrc;
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
	 * 暂时缺货标示：Y-是，N-否
	 * @param notEnoughFlag
	 */
	
	public void setNotEnoughFlag(String notEnoughFlag){
		this.notEnoughFlag=notEnoughFlag;
	}
	/**
	 * 暂时缺货标示：Y-是，N-否
	 * @return
	 */
	public String getNotEnoughFlag(){
		return this.notEnoughFlag;
	}
		
	/**
	 * 包号
	 * @param packageId
	 */
	
	public void setPackageId(String packageId){
		this.packageId=packageId;
	}
	/**
	 * 包号
	 * @return
	 */
	public String getPackageId(){
		return this.packageId;
	}
		
	/**
	 * 每包扎数
	 * @param pakageNum
	 */
	
	public void setPakageNum(Integer pakageNum){
		this.pakageNum=pakageNum;
	}
	/**
	 * 每包扎数
	 * @return
	 */
	public Integer getPakageNum(){
		return this.pakageNum;
	}
		
	/**
	 * 装帧
	 * @param paperback
	 */
	
	public void setPaperback(String paperback){
		this.paperback=paperback;
	}
	/**
	 * 装帧
	 * @return
	 */
	public String getPaperback(){
		return this.paperback;
	}
		
	/**
	 * 定价
	 * @param price
	 */
	
	public void setPrice(BigDecimal price){
		this.price=price;
	}
	/**
	 * 定价
	 * @return
	 */
	public BigDecimal getPrice(){
		return this.price;
	}
		
	/**
	 * 订单细目ID
	 * @param proPurOrderItemsId
	 */
	
	public void setProPurOrderItemsId(Long proPurOrderItemsId){
		this.proPurOrderItemsId=proPurOrderItemsId;
	}
	/**
	 * 订单细目ID
	 * @return
	 */
	public Long getProPurOrderItemsId(){
		return this.proPurOrderItemsId;
	}
		
	/**
	 * 出版者
	 * @param publisher
	 */
	
	public void setPublisher(String publisher){
		this.publisher=publisher;
	}
	/**
	 * 出版者
	 * @return
	 */
	public String getPublisher(){
		return this.publisher;
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
	 * 收货日期(最近一次收货日期)
	 * @param receiveDate
	 */
	
	public void setReceiveDate(Date receiveDate){
		this.receiveDate=receiveDate;
	}
	/**
	 * 收货日期(最近一次收货日期)
	 * @return
	 */
	public Date getReceiveDate(){
		return this.receiveDate;
	}
		
	/**
	 * 收货数量(累加)
	 * @param receiveQty
	 */
	
	public void setReceiveQty(Integer receiveQty){
		this.receiveQty=receiveQty;
	}
	/**
	 * 收货数量(累加)
	 * @return
	 */
	public Integer getReceiveQty(){
		return this.receiveQty;
	}
		
	/**
	 * 备注
	 * @param remark
	 */
	
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**
	 * 备注
	 * @return
	 */
	public String getRemark(){
		return this.remark;
	}
		
	/**
	 * 回告状态：0-未回告，5-已回告
	 * @param responseStatus
	 */
	
	public void setResponseStatus(String responseStatus){
		this.responseStatus=responseStatus;
	}
	/**
	 * 回告状态：0-未回告，5-已回告
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
	 * 销售单价
	 * @param salesPrices
	 */
	
	public void setSalesPrices(Integer salesPrices){
		this.salesPrices=salesPrices;
	}
	/**
	 * 销售单价
	 * @return
	 */
	public Integer getSalesPrices(){
		return this.salesPrices;
	}
		
	/**
	 * 发货折扣
	 * @param sendoutDiscountrate
	 */
	
	public void setSendoutDiscountrate(BigDecimal sendoutDiscountrate){
		this.sendoutDiscountrate=sendoutDiscountrate;
	}
	/**
	 * 发货折扣
	 * @return
	 */
	public BigDecimal getSendoutDiscountrate(){
		return this.sendoutDiscountrate;
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
	 * 发货价
	 * @param sendoutPrice
	 */
	
	public void setSendoutPrice(BigDecimal sendoutPrice){
		this.sendoutPrice=sendoutPrice;
	}
	/**
	 * 发货价
	 * @return
	 */
	public BigDecimal getSendoutPrice(){
		return this.sendoutPrice;
	}
		
	/**
	 * 发货数
	 * @param sendoutQty
	 */
	
	public void setSendoutQty(Integer sendoutQty){
		this.sendoutQty=sendoutQty;
	}
	/**
	 * 发货数
	 * @return
	 */
	public Integer getSendoutQty(){
		return this.sendoutQty;
	}
		
	/**
	 * 从书名
	 * @param seriesTitle
	 */
	
	public void setSeriesTitle(String seriesTitle){
		this.seriesTitle=seriesTitle;
	}
	/**
	 * 从书名
	 * @return
	 */
	public String getSeriesTitle(){
		return this.seriesTitle;
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
	 * 发货单状态：0-未发货，5-已发货（未收货），10-部分收货，15-全部收货
	 * @param status
	 */
	
	public void setStatus(Integer status){
		this.status=status;
	}
	/**
	 * 发货单状态：0-未发货，5-已发货（未收货），10-部分收货，15-全部收货
	 * @return
	 */
	public Integer getStatus(){
		return this.status;
	}
		
	/**
	 * 征订代码
	 * @param subscriptionCode
	 */
	
	public void setSubscriptionCode(String subscriptionCode){
		this.subscriptionCode=subscriptionCode;
	}
	/**
	 * 征订代码
	 * @return
	 */
	public String getSubscriptionCode(){
		return this.subscriptionCode;
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
	 * 本版版次
	 * @param versionNo
	 */
	
	public void setVersionNo(String versionNo){
		this.versionNo=versionNo;
	}
	/**
	 * 本版版次
	 * @return
	 */
	public String getVersionNo(){
		return this.versionNo;
	}
		
	/**
	 * 每扎册数
	 * @param volumes
	 */
	
	public void setVolumes(Integer volumes){
		this.volumes=volumes;
	}
	/**
	 * 每扎册数
	 * @return
	 */
	public Integer getVolumes(){
		return this.volumes;
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
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
		
}
