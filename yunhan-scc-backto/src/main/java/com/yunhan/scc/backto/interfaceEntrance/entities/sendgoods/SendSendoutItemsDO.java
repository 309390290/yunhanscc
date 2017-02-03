package com.yunhan.scc.backto.interfaceEntrance.entities.sendgoods;

import java.lang.Integer;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.yunhan.scc.tools.component.module.query.QueryResult;
import com.yunhan.scc.tools.execl.ExeclBeanInterface;
import com.yunhan.scc.tools.execl.ExeclDataStatus;
import com.yunhan.scc.tools.util.HttpUtil;
import com.yunhan.scc.tools.util.StringUtil;


/**
 * 发货单细目 实体
 * @author zxc
 * @version 2016-2-22 11:04:57
 */
public class SendSendoutItemsDO{
	private static final long serialVersionUID = 1L;
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
	*附属丛书名
	*/
	private String affiliateSeries;
	/**
	*著作者
	*/
	private String authorEditor;
	/**
	*商品名称
	*/
	private String bookTitle;
	/**
	*主键
	*/
	private Long id;
	/**
	*删除标记：Y-已删除，N-未删除
	*/
	private String isDelete;
	/**
	*印次
	*/
	private String impression;
	/**
	*ISBN
	*/
	private String isbn;
	/**
	*是否已导出:Y-是，N-否
	*/
	private String isExport;
	/**
	*回告差异导出标示:Y-是，N-否
	*/
	private String isDiffExport;
	/**
	*永久缺货导出标示:Y-是，N-否
	*/
	private String isForeverOosExport;
	/**
	*发货单主发标识:Y-是，N-否
	*/
	private String isInitiative;
	/**
	*供应商添加商品标识:Y-是，N-否
	*/
	private String isSupplierAddProduct;
	/**
	*ISRC号
	*/
	private String isrc;
	/**
	*暂时缺货标示：Y-是，N-否
	*/
	private String notEnoughFlag;
	/**
	*每包扎数
	*/
	private Integer pakageNum;
	/**
	*装帧
	*/
	private String paperback;
	/**
	*包号
	*/
	private String packageId;
	/**
	 * 定价
	 */
	private BigDecimal price;
	/**
	*出版者
	*/
	private String publisher;
	/**
	*订单细目ID
	*/
	private Long proPurOrderItemsId;
	/**
	*采购商商品ID
	*/
	private String purchaserCommoditiesId;
	/**
	*采购订单号
	*/
	private String purchaseOrderCode;
	/**
	*回告状态：0-未回告，5-已回告
	*/
	private String responseStatus;
	/**
	*回告类型:0-完全不满足、1-部分不满足、2-完全满足
	*/
	private String responseType;
	/**
	*收货日期(最近一次收货日期)
	*/
	private Date receiveDate;
	/**
	*收货数量(累加)
	*/
	private Integer receiveQty;
	/**
	*行项唯一号
	*/
	private String rowUniqueNo;
	/**
	*销售单价
	*/
	private Integer salesPrices;
	/**
	*订单来源类型:YHSYS-云汉报订产品、EDI、FTP、DB等，根据业务发展决定
	*/
	private String sourceType;
	/**
	*发货折扣
	*/
	private BigDecimal sendoutDiscountrate;
	/**
	*供应商发货单号
	*/
	private String sendoutGoodsCode;
	/**
	*征订代码
	*/
	private String subscriptionCode;
	/**
	*供应商ID
	*/
	private String supplierId;
	/**
	 * 采购商id
	 */
	private String purchaserId;
	/**
	*发货价
	*/
	private BigDecimal sendoutPrice;
	/**
	*发货数
	*/
	private Integer sendoutQty;
	/**
	*发货数
	*/
	private Integer sendoutQtySend;
	/**
	*从书名
	*/
	private String seriesTitle;
	/**
	*每扎册数
	*/
	private Integer volumes;
	
	/**
	*供应商商品ID
	*/
	private String supplierCommoditiesId;
	/**
	*修改日期
	*/
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	/**
	*修改人
	*/
	private String updateUserCode;
	/**
	*云汉ID
	*/
	private String yunhanId;
	/**
	*平台订单号
	*/
	private String yunhanOrderCode;
	/**
	*平台发货单号
	*/
	private String yunhanSendoutCode;
	/**
	*紧急程度:1：急单、2：普通单
	*/
	private String urgentFlag;
	/**
	 * 下单日期 added by luohoudong
	 */
	private String orderAddDateStr;
	/**
	 * 发到站
	 */
	private String destination;
	/**
	 * 仓位 
	 */
	private String warehouse;
	
	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getAffiliateSeries() {
		return affiliateSeries;
	}

	public void setAffiliateSeries(String affiliateSeries) {
		this.affiliateSeries = affiliateSeries;
	}

	public String getAuthorEditor() {
		return authorEditor;
	}

	public void setAuthorEditor(String authorEditor) {
		this.authorEditor = authorEditor;
	}

	public String getImpression() {
		return impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}

	public String getPaperback() {
		return paperback;
	}

	public void setPaperback(String paperback) {
		this.paperback = paperback;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getSalesPrices() {
		return salesPrices;
	}

	public void setSalesPrices(Integer salesPrices) {
		this.salesPrices = salesPrices;
	}

	public String getSeriesTitle() {
		return seriesTitle;
	}

	public void setSeriesTitle(String seriesTitle) {
		this.seriesTitle = seriesTitle;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	/**
	*本版版次
	*/
	private String versionNo;
	/**
	 * 订单种类
	 * 0：零售订单 
	 * 5：文教订单
	 * 10：电商订单 
	 * 15：大中专订单
	 * 20：团购订单 
	 * 25：馆配订单 
	 * 30：活动订书
	 */
	private String orderType;
	/**
	 * 订单折扣
	 */
	private BigDecimal discountrate;
	/**
	 * 订单订数
	 */
	private Integer orderQty;
	/**
	 * 发货单状态：0-未发货，5-已发货（未收货），10-部分收货，15-全部收货
	 */
	private Integer status;
	
	/**
	*备注 added by luohoudong
	*/
	private String remark;
	
	/**
	 * 未发货数 added by luohoudong 
	 */
	private Long notSendoutQty;
	
	/**
	 * 出版标识 常数  0：单品 1：套装书 2：组套书 added by lvxupu
	 */
	private Integer isSuitBook;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	 * 平台发货单号
	 * @param yunhanSendoutCode
	 */
	public void setYunhanSendoutCode(String yunhanSendoutCode){
		this.yunhanSendoutCode=yunhanSendoutCode;
	}
	
	/**
	 * 平台发货单号
	 * @return
	 */
	public String getYunhanSendoutCode(){
		return this.yunhanSendoutCode;
	}

	public String getUrgentFlag() {
		return urgentFlag;
	}

	public void setUrgentFlag(String urgentFlag) {
		this.urgentFlag = urgentFlag;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public BigDecimal getDiscountrate() {
		return discountrate;
	}

	public void setDiscountrate(BigDecimal discountrate) {
		this.discountrate = discountrate;
	}

	public Integer getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}

	private String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	private int serNumber;//Execl中序号，可以不要？
	

	public int getSerNumber() {
		return serNumber;
	}

	public void setSerNumber(int serNumber) {
		this.serNumber = serNumber;
	}

	
	private String notEnoughReasonString;

	/**
	 * @return the notEnoughReasonString
	 */
	public String getNotEnoughReasonString() {
		return notEnoughReasonString;
	}

	/**
	 * @param notEnoughReasonString the notEnoughReasonString to set
	 */
	public void setNotEnoughReasonString(String notEnoughReasonString) {
		this.notEnoughReasonString = notEnoughReasonString;
	}
	
	/**
	 * 用于匹配Execl 中的可供价和可供折扣
	 */
	private BigDecimal availablePrice;
	private BigDecimal availableDiscountrate;
	/**
	 * 品种码洋
	 */
	private BigDecimal salePrice;
	/**
	 * 品种实洋
	 */
	private BigDecimal realitySalePrice;
	
	/**
	 * 预计发货时间 
	 */
	private String preSendDate;
	
	/**
	 * 回告品种备注
	 */
	private String responseRemark;
	
	/**
	 * 满足数量
	 */
	private Integer availableQty;
	/**
	 * 本次发货数量
	 */
	private Integer thisSendQty;
	
	
	public Integer getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(Integer availableQty) {
		this.availableQty = availableQty;
	}

	public Integer getThisSendQty() {
		return thisSendQty;
	}

	public void setThisSendQty(Integer thisSendQty) {
		this.thisSendQty = thisSendQty;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getRealitySalePrice() {
		return realitySalePrice;
	}

	public void setRealitySalePrice(BigDecimal realitySalePrice) {
		this.realitySalePrice = realitySalePrice;
	}

	public String getPreSendDate() {
		return preSendDate;
	}

	public void setPreSendDate(String preSendDate) {
		this.preSendDate = preSendDate;
	}

	public String getResponseRemark() {
		return responseRemark;
	}

	public void setResponseRemark(String responseRemark) {
		this.responseRemark = responseRemark;
	}

	public BigDecimal getAvailablePrice() {
		return availablePrice;
	}

	public void setAvailablePrice(BigDecimal availablePrice) {
		this.availablePrice = availablePrice;
	}

	public BigDecimal getAvailableDiscountrate() {
		return availableDiscountrate;
	}

	public void setAvailableDiscountrate(BigDecimal availableDiscountrate) {
		this.availableDiscountrate = availableDiscountrate;
	}



	
	
	/**
	 * @return the notSendoutQty
	 */
	public Long getNotSendoutQty() {
		return notSendoutQty;
	}

	/**
	 * @param notSendoutQty the notSendoutQty to set
	 */
	public void setNotSendoutQty(Long notSendoutQty) {
		this.notSendoutQty = notSendoutQty;
	}
	
	

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static void main(String[] args) {
		SendSendoutItemsDO d = new SendSendoutItemsDO();
		d.setAddTime(new Date());
		Map<Object, Object> adata = new HashMap<Object, Object>();
		adata.put("dd", d);
		System.out.println(HttpUtil.getStringFromJson(adata));
	}

	public String getIsrc() {
		return isrc;
	}

	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getIsExport() {
		return isExport;
	}

	public void setIsExport(String isExport) {
		this.isExport = isExport;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public Integer getPakageNum() {
		return pakageNum;
	}

	public void setPakageNum(Integer pakageNum) {
		this.pakageNum = pakageNum;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Integer getReceiveQty() {
		return receiveQty;
	}

	public void setReceiveQty(Integer receiveQty) {
		this.receiveQty = receiveQty;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSubscriptionCode() {
		return subscriptionCode;
	}

	public void setSubscriptionCode(String subscriptionCode) {
		this.subscriptionCode = subscriptionCode;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getVolumes() {
		return volumes;
	}

	public void setVolumes(Integer volumes) {
		this.volumes = volumes;
	}

	
	
	/**
	 * @return the orderAddDateStr
	 */
	public String getOrderAddDateStr() {
		return orderAddDateStr;
	}

	/**
	 * @param orderAddDateStr the orderAddDateStr to set
	 */
	public void setOrderAddDateStr(String orderAddDateStr) {
		this.orderAddDateStr = orderAddDateStr;
	}

	
	
	
	

	public String getPurchaserId() {
		return purchaserId;
	}

	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}

	public Integer getSendoutQtySend() {
		return sendoutQtySend;
	}

	public void setSendoutQtySend(Integer sendoutQtySend) {
		this.sendoutQtySend = sendoutQtySend;
	}

	public String getIsSupplierAddProduct() {
		return StringUtil.parseStringToNull(this.isSupplierAddProduct, "N");
	}
	public void setIsSupplierAddProduct(String isSupplierAddProduct) {
		this.isSupplierAddProduct = StringUtil.parseStringToNull(isSupplierAddProduct, "N");
		
	}


	public Integer getIsSuitBook() {
		return isSuitBook;
	}

	public void setIsSuitBook(Integer isSuitBook) {
		this.isSuitBook = isSuitBook;
	}
}