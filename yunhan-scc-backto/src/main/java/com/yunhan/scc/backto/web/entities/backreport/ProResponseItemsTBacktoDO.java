package com.yunhan.scc.backto.web.entities.backreport;

import java.util.HashMap;
import java.util.Map;

import com.yunhan.scc.tools.component.module.query.QueryResult;
import com.yunhan.scc.tools.execl.ExeclBeanInterface;
import com.yunhan.scc.tools.execl.ExeclDataStatus;
import com.yunhan.scc.tools.util.DateUtils;


/**
 *ProResponseItemsT实体
 * @author luohoudong
 * @version 2016-7-14 9:52:28
 */
public class ProResponseItemsTBacktoDO extends QueryResult implements ExeclBeanInterface,Comparable<ProResponseItemsTBacktoDO>

{
	/**
	*创建日期
	*/
	private String addTime;
	/**
	*创建人
	*/
	private String addUserCode;
	/**
	*可供折扣
	*/
	private String availableDiscountrate;
	/**
	*可供价
	*/
	private String availablePrice;
	/**
	*满足数量
	*/
	private String availableQty;
	/**
	*商品名称
	*/
	private String bookTitle;
	/**
	*加工处理状态: Y-已加工，N-未加工
	*/
	private String dealStatus;
	/**
	*差异类型:0:无差异,1:数量有差异,2:折扣有差异,3:定价有差异,4:数量、折扣有差异,5:数量、定价有差异,6:折扣、定价有差异,7:数量、折扣、定价有差异
	*/
	private String diffType;
	/**
	*采购折扣
	*/
	private String discountrate;
	/**
	*主键
	*/
	private Long id;
	/**
	*ISBN
	*/
	private String isbn;
	/**
	*回告差异导出标示:Y-是，N-否
	*/
	private String isDiffExport;
	/**
	*永久缺货导出标示:Y-是，N-否
	*/
	private String isForeverOosExport;
	/**
	*是否有效：Y-是，N-否
	*/
	private String isValid;
	/**
	*不满足原因:1、暂时缺货，2、已停产，3、改版，4、版权到期，5、商品无效，6、销售受限（后面5个都属于永久缺货类型）
	*/
	private String notEnoughReason;
	/**
	*订数
	*/
	private String orderQty;
	/**
	*预计发货日期:1）本次发货量小于满足数量，则必填；2）日期格式：YYYYMMDD
	*/
	private String preSendDate;
	/**
	*定价
	*/
	private String price;
	/**
	*订单细目表ID
	*/
	private String proPurOrderItemsId;
	/**
	*采购商商品ID
	*/
	private String purchaserCommoditiesId;
	/**
	*采购商ID
	*/
	private String purchaserId;
	/**
	*采购订单号
	*/
	private String purchaseOrderCode;
	/**
	*回告日期:YYYYMMDD
	*/
	private String responseDate;
	/**
	*回告品种备注
	*/
	private String responseRemark;
	/**
	*回告状态:0-未回告，5-已回告
	*/
	private String responseStatus;
	/**
	*回告类型:0-完全不满足、1-部分不满足、2-完全满足
	*/
	private String responseType;
	/**
	*回告人
	*/
	private String responseUserCode;
	/**
	*行项唯一号
	*/
	private String rowUniqueNo;
	/**
	*供应商发货单号
	*/
	private String sendoutGoodsCode;
	/**
	*订单来源类型:YHSYS-云汉报订产品、EDI、FTP、DB等，根据业务发展决定
	*/
	private String sourceType;
	/**
	*供应商商品ID
	*/
	private String supplierCommoditiesId;
	/**
	*供应商ID
	*/
	private String supplierId;
	/**
	*本次发货数量
	*/
	private String thisSendQty;
	/**
	*修改日期
	*/
	private String updateTime;
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
	 * 丛书名
	 */
	private String affiliateSeries;
	/**
	 * 订单种类:0：一般零售 5：文教订单 10：电商订单 15：大中专订单 20：团购订单 25：馆配订单 30：活动订书
	 */
	private String orderType;
	/**
	 * 紧急程度:1：急单、2：普通单
	 */
	private String urgentFlag;
	/**
	 * 是否已导出:Y-是，N-否
	 */
	private String isExport;
	/**
	 * 新品订单:Y-是，N-否
	 */
	private String isNew;
	/**
	 * 采购重点:Y-重点，N-非重点
	 */
	private String isImportent;
	/**
	 * 出版标识 常数   0：单品 1：套装书 2：组套书
	 */
	private String isSuitBook;
	/**
	 * 已发数
	 */
	private String sendoutQty;
	/**
	 * 已收数
	 */
	private String receiveQty;
	/**
	 * 订单备注
	 */
	private String remark;
	/**
	 * 发货方式(收货类型):1：普通,2：直供
	 */
	private String sendGoodsType;
	/**
	 * 回告价
	 */
	private String reportPrice;
	/**
	 * 回告折扣
	 */
	private String reportDiscountrate;
	/**
	 * 上次回告其余满足情况
	 */
	private String reportAvailableReason;
	/**
	*采购商名称
	*/
	private String purchaserName;
	/**
	 * 到站
	 */
	private String destination;
	/**
	 * 仓位
	 */
	private String warehouse;
	/**
	 * 收货地址
	 */
	private String receiveAddress;
	/**
	 * 收货联系电话
	 */
	private String receivePhoneno;
	/**
	 * 收货联系人
	 */
	private String receiveContactUser;
	/**
	 * 回告id
	 */
	private Long respId;
	/**
	 * 历史回告备注
	 */
	private String historyRemark;
	
	
	public static Map<String,String> titleMap = new HashMap<String, String>(); 
	
	/** 
	 * 错误 信息
	 */
	private String errorMessage;
	
	/**
	 * 错误数据分类 1:已处理，2:无法匹配，不对应数据库
	 */
	private int errorNumber;
	/**
	 * 供应商添加商品标识:Y-是，N-否
	 */
	private String isSupplierAddProduct;
	/**
	 * 供应商与采购商发货单规则 正则表达式
	 * 2016-10-27 --- wyn
	 */
	private String regularExpression;
	
	
	
	public String getRegularExpression() {
		return regularExpression;
	}

	public void setRegularExpression(String regularExpression) {
		this.regularExpression = regularExpression;
	}

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
		this.isbn=isbn.replaceAll("\\.00", "");
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
		this.orderQty=orderQty.replaceAll("\\.00", "");
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
		this.preSendDate =DateUtils.convertToNormalDateFromat(preSendDate).replaceAll("/", "-");
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
		
		this.proPurOrderItemsId=proPurOrderItemsId.replaceAll("\\.00", "");
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
		this.purchaseOrderCode=purchaseOrderCode.replaceAll("\\.00", "");
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
		this.thisSendQty=thisSendQty.replaceAll("\\.00", "");
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
	

	
	
	
	
	/**
	 * @return the affiliateSeries
	 */
	public String getAffiliateSeries() {
		return affiliateSeries;
	}

	/**
	 * @param affiliateSeries the affiliateSeries to set
	 */
	public void setAffiliateSeries(String affiliateSeries) {
		this.affiliateSeries = affiliateSeries;
	}

	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * @return the urgentFlag
	 */
	public String getUrgentFlag() {
		return urgentFlag;
	}

	/**
	 * @param urgentFlag the urgentFlag to set
	 */
	public void setUrgentFlag(String urgentFlag) {
		this.urgentFlag = urgentFlag;
	}

	/**
	 * @return the isExport
	 */
	public String getIsExport() {
		return isExport;
	}

	/**
	 * @param isExport the isExport to set
	 */
	public void setIsExport(String isExport) {
		this.isExport = isExport;
	}

	/**
	 * @return the isNew
	 */
	public String getIsNew() {
		return isNew;
	}

	/**
	 * @param isNew the isNew to set
	 */
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	/**
	 * @return the isImportent
	 */
	public String getIsImportent() {
		return isImportent;
	}

	/**
	 * @param isImportent the isImportent to set
	 */
	public void setIsImportent(String isImportent) {
		this.isImportent = isImportent;
	}

	/**
	 * @return the isSuitBook
	 */
	public String getIsSuitBook() {
		return isSuitBook;
	}

	/**
	 * @param isSuitBook the isSuitBook to set
	 */
	public void setIsSuitBook(String isSuitBook) {
		this.isSuitBook = isSuitBook;
	}

	/**
	 * @return the sendoutQty
	 */
	public String getSendoutQty() {
		return sendoutQty;
	}

	/**
	 * @param sendoutQty the sendoutQty to set
	 */
	public void setSendoutQty(String sendoutQty) {
		this.sendoutQty = sendoutQty;
	}

	/**
	 * @return the receiveQty
	 */
	public String getReceiveQty() {
		return receiveQty;
	}

	/**
	 * @param receiveQty the receiveQty to set
	 */
	public void setReceiveQty(String receiveQty) {
		this.receiveQty = receiveQty;
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

	/**
	 * @return the sendGoodsType
	 */
	public String getSendGoodsType() {
		return sendGoodsType;
	}

	/**
	 * @param sendGoodsType the sendGoodsType to set
	 */
	public void setSendGoodsType(String sendGoodsType) {
		this.sendGoodsType = sendGoodsType;
	}

	




	/**
	 * @return the reportPrice
	 */
	public String getReportPrice() {
		return reportPrice;
	}

	/**
	 * @param reportPrice the reportPrice to set
	 */
	public void setReportPrice(String reportPrice) {
		this.reportPrice = reportPrice;
	}

	/**
	 * @return the reportDiscountrate
	 */
	public String getReportDiscountrate() {
		return reportDiscountrate;
	}

	/**
	 * @param reportDiscountrate the reportDiscountrate to set
	 */
	public void setReportDiscountrate(String reportDiscountrate) {
		this.reportDiscountrate = reportDiscountrate;
	}


	



	/**
	 * @return the purchaserName
	 */
	public String getPurchaserName() {
		return purchaserName;
	}

	/**
	 * @param purchaserName the purchaserName to set
	 */
	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @return the warehouse
	 */
	public String getWarehouse() {
		return warehouse;
	}

	/**
	 * @param warehouse the warehouse to set
	 */
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}


	



	/**
	 * @return the receiveAddress
	 */
	public String getReceiveAddress() {
		return receiveAddress;
	}

	/**
	 * @param receiveAddress the receiveAddress to set
	 */
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	/**
	 * @return the receivePhoneno
	 */
	public String getReceivePhoneno() {
		return receivePhoneno;
	}

	/**
	 * @param receivePhoneno the receivePhoneno to set
	 */
	public void setReceivePhoneno(String receivePhoneno) {
		this.receivePhoneno = receivePhoneno;
	}

	/**
	 * @return the receiveContactUser
	 */
	public String getReceiveContactUser() {
		return receiveContactUser;
	}

	/**
	 * @param receiveContactUser the receiveContactUser to set
	 */
	public void setReceiveContactUser(String receiveContactUser) {
		this.receiveContactUser = receiveContactUser;
	}

	




	/**
	 * @return the respId
	 */
	public Long getRespId() {
		return respId;
	}

	/**
	 * @param respId the respId to set
	 */
	public void setRespId(Long respId) {
		this.respId = respId;
	}
	


	/**
	 * @return the isSupplierAddProduct
	 */
	public String getIsSupplierAddProduct() {
		return isSupplierAddProduct;
	}

	/**
	 * @param isSupplierAddProduct the isSupplierAddProduct to set
	 */
	public void setIsSupplierAddProduct(String isSupplierAddProduct) {
		this.isSupplierAddProduct = isSupplierAddProduct;
	}








	/**
	 * @return the reportAvailableReason
	 */
	public String getReportAvailableReason() {
		return reportAvailableReason;
	}

	/**
	 * @param reportAvailableReason the reportAvailableReason to set
	 */
	public void setReportAvailableReason(String reportAvailableReason) {
		this.reportAvailableReason = reportAvailableReason;
	}








	/**
	 * @return the historyRemark
	 */
	public String getHistoryRemark() {
		return historyRemark;
	}

	/**
	 * @param historyRemark the historyRemark to set
	 */
	public void setHistoryRemark(String historyRemark) {
		this.historyRemark = historyRemark;
	}








	static{
		
		//有订单发货模板字段对应关系
		titleMap.put("细目ID","proPurOrderItemsId");
		titleMap.put("订单号码", "purchaseOrderCode");
		titleMap.put("ISBN", "isbn");
		titleMap.put("商品名称", "bookTitle");
		titleMap.put("定价", "price");
		titleMap.put("折扣", "discountrate");
		titleMap.put("定价", "availablePrice");
		titleMap.put("折扣", "availableDiscountrate");
		titleMap.put("订数", "orderQty");
		titleMap.put("本次发货数", "thisSendQty");
		titleMap.put("其余满足情况", "notEnoughReason");
		titleMap.put("其余预计发货时间","preSendDate");
		titleMap.put("备注", "responseRemark");
		titleMap.put("发货单号","sendoutGoodsCode");
		//无订单发货模板字段对应关系
		titleMap.put("发货折扣（%）", "availableDiscountrate");
		titleMap.put("发货数", "thisSendQty");
		
		titleMap.put("序号","1");
		titleMap.put("错误描述", "errorMessage");
		
	}



	@Override
	public int compareTo(ProResponseItemsTBacktoDO o) {
		return 0;
	}

	@Override
	public String getPerties(String key) {
		return titleMap.get(key);
	}

	@Override
	public Map<String, String> getTileMap() {
		return titleMap;
	}
	
	
	

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getErrorNumber() {
		return errorNumber;
	}

	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}

	@Override
	public ExeclDataStatus hasError() {
		if(errorMessage == null){
			return ExeclDataStatus.SUCCES;
		}
		switch (errorMessage) {
		case "":
			return ExeclDataStatus.SUCCES;
		case "DISCARD":
			return ExeclDataStatus.DISCARD;
		default :
			return ExeclDataStatus.ERROR;
		}
	}
	
	
	
}