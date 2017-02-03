package com.yunhan.scc.backto.web.model.order;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.yunhan.scc.tools.component.module.query.QueryCondition;

/**
 * ProPurOrderItems查询条件
 * @author wangtao
 * @version 2016-7-6 14:10:29
 */
public class ProPurOrderItemsBacktoCondition extends QueryCondition {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 43546457657687698L;
	/**
	 * 制单日期(停用，以总目为准)
	 */
	private Date addDate;
	/**
	 * 创建日期
	 */
	private Timestamp addTime;
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
	 * 商品名称，多个之间用逗号分隔
	 */
	private List<String> bookTitles;
	/**
	 * 关闭次数（大于1次，数据就不能再操作发货了）
	 */
	private Integer closeCounter;
	/**
	 * 品种关闭原因
	 */
	private String closeReason;
	/**
	 * 关闭状态：0-初始状态，5-开启，10-关闭	
	 */
	private Integer closeStatus;
	/**
	 * 是否回告和发货的控制标示：0-未回告，1-已回告，2-已发货（用于订单还未回告就已经有收货单的情况）
	 */
	private Integer controlFlag;
	/**
	 * 折扣
	 */
	private BigDecimal discountrate;
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 回告ids
	 */
	private List<String> ids;
	/**
	 * 印次
	 */
	private String impression;
	/**
	 * ISBN
	 */
	private String isbn;
	/**
	 * ISBN，多个之间用逗号分隔
	 */
	private List<String> isbns;
	/**
	 * 订单处理状态：Y-已处理，N-未处理
	 */
	private String isDeal;
	/**
	 * 是否已导出:Y-是，N-否
	 */
	private String isExport;
	/**
	 * 采购重点:Y-重点，N-非重点
	 */
	private String isImportent;
	/**
	 * 出版标识 常数
             0：单品 1：套装书 2：组套书
	 */
	private Integer isSuitBook;
	/**
	 * 品种有效性:Y-有效，N-采购商关闭，X-供应商关闭
	 */
	private String isValid;
	/**
	 * 供应商是否待办已查看:Y-是，N-否
	 */
	private String isView;
	/**
	 * 新品订单:Y-是，N-否
	 */
	private String isNew;
	/**
	 * 类别
	 */
	private String marketingCategories;
	/**
	 * 未发数量(停用)
	 */
	private Integer notSendoutQty;
	/**
	 * 订数
	 */
	private Integer orderQty;
	/**
	 * 征订期号
	 */
	private Integer orderTermNo;
	/**
	 * 装帧
	 */
	private String paperback;
	/**
	 * 定价
	 */
	private BigDecimal price;
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
	 * 品种实洋
	 */
	private BigDecimal realitySalePrice;
	/**
	 * 收货日期(最近一次收货日期)
	 */
	private Date receiveDate;
	/**
	 * 收货数量(累加)
	 */
	private Integer receiveQty;
	/**
	 * 订单品种备注
	 */
	private String remark;
	/**
	 * 行项唯一号
	 */
	private String rowUniqueNo;
	/**
	 * 销售单价
	 */
	private Integer salesPrices;
	/**
	 * 品种码洋
	 */
	private BigDecimal salePrice;
	/**
	 * 最新发货时间（每次发货后更新）
	 */
	private Date sendoutDate;
	/**
	 * 预留字段：对应发货单号(可能多个)
	 */
	private String sendoutGoodsCode;
	/**
	 * 发货数(每次发货后累加)
	 */
	private Integer sendoutQty;
	/**
	 * 发送日期(停用，以总目为准)
	 */
	private Date sendDate;
	/**
	 * 发货方案:1、统一发货，2、分批发货，3、凑包件发货
	 */
	private String sendScheme;
	/**
	 * 从书名
	 */
	private String seriesTitle;
	/**
	 * 订单来源类型:YHSYS-云汉报订产品、EDI、FTP、DB等，根据业务发展决定
	 */
	private String sourceType;
	/**
	 * 品种状态：0-未发送，5-不发送，10-已发送未回告，15-已回告未发货，20-部分发货，25-全部发货，30-部分收货，35-全部收货，40-已关闭(采购商)，45-已结束(供应商)，50-已逻辑删除
	 */
	private Integer status;
	/**
	 * 征订代码
	 */
	private String subscriptionCode;
	/**
	 * 供应商商商品ID
	 */
	private String supplierCommoditiesId;
	/**
	 * 供应商ID
	 */
	private String supplierId;
	/**
	 * 修改日期
	 */
	private Timestamp updateTime;
	/**
	 * 修改人
	 */
	private String updateUserCode;
	/**
	 * 紧急标识:Y-急单，N-非急单
	 */
	private String urgentFlag;
	/**
	 * deng lu ming 
	 */
	private String userCode;
	/**
	 * 本版版次
	 */
	private String versionNo;
	/**
	 * 仓位编码
	 */
	private String warehouse;
	/**
	 * 云汉ID
	 */
	private String yunhanId;
	/**
	 * 云汉订单号
	 */
	private String yunhanOrderCode;
	/**
	 * 采购订单号
	 */
	private String purchaserOrderCode;
	/**
	 * 采购订单号，多个之间用逗号分隔
	 */
	private List<String> purchaserOrderCodes;
	
	private List<String> purchaseOrderCodes;
	/**
	 * 订单种类
	 */
	private Integer orderType;
	/**
	 * 订单种类，多个之间用逗号分隔
	 */
	private List<Integer> orderTypes;
	/**
	 * 发货日期开始
	 */
	private String sendOutDateStart;
	/**
	 * 发货日期结束
	 */
	private String sendOutDateEnd;
	/**
	*发货方式(收货类型):1：普通,2：直供
	*/
	private String sendGoodsType;
	/**
	 * 排序字段
	 */
	private String sortOrderField;
	/**
	 * 排序方式
	 */
	private String sortOrderRule;
	
	
	/**
	 * 发送日期开始
	 */
	private String sendDateStart;
	/**
	 * 发送日期结束
	 */
	private String sendDateEnd;
	
	private String otherAvailableReason;
	
	/**
	 * 多个品种有效性
	 */
	private List<String> isValids;
	/**
	 * 仓位列表
	 */
	private List<String> warehouses;
	
	
	/**
	 * 制单日期(停用，以总目为准)
	 * @param addDate
	 */
	
	public void setAddDate(Date addDate){
		this.addDate=addDate;
	}
	/**
	 * 制单日期(停用，以总目为准)
	 * @return
	 */
	public Date getAddDate(){
		return this.addDate;
	}
		
	/**
	 * 创建日期
	 * @param addTime
	 */
	
	public void setAddTime(Timestamp addTime){
		this.addTime=addTime;
	}
	/**
	 * 创建日期
	 * @return
	 */
	public Timestamp getAddTime(){
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
	 * 关闭次数（大于1次，数据就不能再操作发货了）
	 * @param closeCounter
	 */
	
	public void setCloseCounter(Integer closeCounter){
		this.closeCounter=closeCounter;
	}
	/**
	 * 关闭次数（大于1次，数据就不能再操作发货了）
	 * @return
	 */
	public Integer getCloseCounter(){
		return this.closeCounter;
	}
		
	/**
	 * 品种关闭原因
	 * @param closeReason
	 */
	
	public void setCloseReason(String closeReason){
		this.closeReason=closeReason;
	}
	/**
	 * 品种关闭原因
	 * @return
	 */
	public String getCloseReason(){
		return this.closeReason;
	}
		
	/**
	 * 关闭状态：0-初始状态，5-开启，10-关闭	
	 * @param closeStatus
	 */
	
	public void setCloseStatus(Integer closeStatus){
		this.closeStatus=closeStatus;
	}
	/**
	 * 关闭状态：0-初始状态，5-开启，10-关闭	
	 * @return
	 */
	public Integer getCloseStatus(){
		return this.closeStatus;
	}
		
	/**
	 * 是否回告和发货的控制标示：0-未回告，1-已回告，2-已发货（用于订单还未回告就已经有收货单的情况）
	 * @param controlFlag
	 */
	
	public void setControlFlag(Integer controlFlag){
		this.controlFlag=controlFlag;
	}
	/**
	 * 是否回告和发货的控制标示：0-未回告，1-已回告，2-已发货（用于订单还未回告就已经有收货单的情况）
	 * @return
	 */
	public Integer getControlFlag(){
		return this.controlFlag;
	}
		
	/**
	 * 折扣
	 * @param discountrate
	 */
	
	public void setDiscountrate(BigDecimal discountrate){
		this.discountrate=discountrate;
	}
	/**
	 * 折扣
	 * @return
	 */
	public BigDecimal getDiscountrate(){
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
	 * 订单处理状态：Y-已处理，N-未处理
	 * @param isDeal
	 */
	
	public void setIsDeal(String isDeal){
		this.isDeal=isDeal;
	}
	/**
	 * 订单处理状态：Y-已处理，N-未处理
	 * @return
	 */
	public String getIsDeal(){
		return this.isDeal;
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
	 * 采购重点:Y-重点，N-非重点
	 * @param isImportent
	 */
	
	public void setIsImportent(String isImportent){
		this.isImportent=isImportent;
	}
	/**
	 * 采购重点:Y-重点，N-非重点
	 * @return
	 */
	public String getIsImportent(){
		return this.isImportent;
	}
		
	/**
	 * 出版标识 常数
             0：单品 1：套装书 2：组套书
	 * @param isSuitBook
	 */
	
	public void setIsSuitBook(Integer isSuitBook){
		this.isSuitBook=isSuitBook;
	}
	/**
	 * 出版标识 常数
             0：单品 1：套装书 2：组套书
	 * @return
	 */
	public Integer getIsSuitBook(){
		return this.isSuitBook;
	}
		
	/**
	 * 品种有效性:Y-有效，N-采购商关闭，X-供应商关闭
	 * @param isValid
	 */
	
	public void setIsValid(String isValid){
		this.isValid=isValid;
	}
	/**
	 * 品种有效性:Y-有效，N-采购商关闭，X-供应商关闭
	 * @return
	 */
	public String getIsValid(){
		return this.isValid;
	}
		
	/**
	 * 供应商是否待办已查看:Y-是，N-否
	 * @param isView
	 */
	
	public void setIsView(String isView){
		this.isView=isView;
	}
	/**
	 * 供应商是否待办已查看:Y-是，N-否
	 * @return
	 */
	public String getIsView(){
		return this.isView;
	}
		
	/**
	 * 类别
	 * @param marketingCategories
	 */
	
	public void setMarketingCategories(String marketingCategories){
		this.marketingCategories=marketingCategories;
	}
	/**
	 * 类别
	 * @return
	 */
	public String getMarketingCategories(){
		return this.marketingCategories;
	}
		
	/**
	 * 未发数量(停用)
	 * @param notSendoutQty
	 */
	
	public void setNotSendoutQty(Integer notSendoutQty){
		this.notSendoutQty=notSendoutQty;
	}
	/**
	 * 未发数量(停用)
	 * @return
	 */
	public Integer getNotSendoutQty(){
		return this.notSendoutQty;
	}
		
	/**
	 * 订数
	 * @param orderQty
	 */
	
	public void setOrderQty(Integer orderQty){
		this.orderQty=orderQty;
	}
	/**
	 * 订数
	 * @return
	 */
	public Integer getOrderQty(){
		return this.orderQty;
	}
		
	/**
	 * 征订期号
	 * @param orderTermNo
	 */
	
	public void setOrderTermNo(Integer orderTermNo){
		this.orderTermNo=orderTermNo;
	}
	/**
	 * 征订期号
	 * @return
	 */
	public Integer getOrderTermNo(){
		return this.orderTermNo;
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
	 * 品种实洋
	 * @param realitySalePrice
	 */
	
	public void setRealitySalePrice(BigDecimal realitySalePrice){
		this.realitySalePrice=realitySalePrice;
	}
	/**
	 * 品种实洋
	 * @return
	 */
	public BigDecimal getRealitySalePrice(){
		return this.realitySalePrice;
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
	 * 订单品种备注
	 * @param remark
	 */
	
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**
	 * 订单品种备注
	 * @return
	 */
	public String getRemark(){
		return this.remark;
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
	 * 品种码洋
	 * @param salePrice
	 */
	
	public void setSalePrice(BigDecimal salePrice){
		this.salePrice=salePrice;
	}
	/**
	 * 品种码洋
	 * @return
	 */
	public BigDecimal getSalePrice(){
		return this.salePrice;
	}
		
	/**
	 * 最新发货时间（每次发货后更新）
	 * @param sendoutDate
	 */
	
	public void setSendoutDate(Date sendoutDate){
		this.sendoutDate=sendoutDate;
	}
	/**
	 * 最新发货时间（每次发货后更新）
	 * @return
	 */
	public Date getSendoutDate(){
		return this.sendoutDate;
	}
		
	/**
	 * 预留字段：对应发货单号(可能多个)
	 * @param sendoutGoodsCode
	 */
	
	public void setSendoutGoodsCode(String sendoutGoodsCode){
		this.sendoutGoodsCode=sendoutGoodsCode;
	}
	/**
	 * 预留字段：对应发货单号(可能多个)
	 * @return
	 */
	public String getSendoutGoodsCode(){
		return this.sendoutGoodsCode;
	}
		
	/**
	 * 发货数(每次发货后累加)
	 * @param sendoutQty
	 */
	
	public void setSendoutQty(Integer sendoutQty){
		this.sendoutQty=sendoutQty;
	}
	/**
	 * 发货数(每次发货后累加)
	 * @return
	 */
	public Integer getSendoutQty(){
		return this.sendoutQty;
	}
		
	/**
	 * 发送日期(停用，以总目为准)
	 * @param sendDate
	 */
	
	public void setSendDate(Date sendDate){
		this.sendDate=sendDate;
	}
	/**
	 * 发送日期(停用，以总目为准)
	 * @return
	 */
	public Date getSendDate(){
		return this.sendDate;
	}
		
	/**
	 * 发货方案:1、统一发货，2、分批发货，3、凑包件发货
	 * @param sendScheme
	 */
	
	public void setSendScheme(String sendScheme){
		this.sendScheme=sendScheme;
	}
	/**
	 * 发货方案:1、统一发货，2、分批发货，3、凑包件发货
	 * @return
	 */
	public String getSendScheme(){
		return this.sendScheme;
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
	 * 品种状态：0-未发送，5-不发送，10-已发送未回告，15-已回告未发货，20-部分发货，25-全部发货，30-部分收货，35-全部收货，40-已关闭(采购商)，45-已结束(供应商)，50-已逻辑删除
	 * @param status
	 */
	
	public void setStatus(Integer status){
		this.status=status;
	}
	/**
	 * 品种状态：0-未发送，5-不发送，10-已发送未回告，15-已回告未发货，20-部分发货，25-全部发货，30-部分收货，35-全部收货，40-已关闭(采购商)，45-已结束(供应商)，50-已逻辑删除
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
	 * 供应商商商品ID
	 * @param supplierCommoditiesId
	 */
	
	public void setSupplierCommoditiesId(String supplierCommoditiesId){
		this.supplierCommoditiesId=supplierCommoditiesId;
	}
	/**
	 * 供应商商商品ID
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
	
	public void setUpdateTime(Timestamp updateTime){
		this.updateTime=updateTime;
	}
	/**
	 * 修改日期
	 * @return
	 */
	public Timestamp getUpdateTime(){
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
	 * 紧急标识:Y-急单，N-非急单
	 * @param urgentFlag
	 */
	
	public void setUrgentFlag(String urgentFlag){
		this.urgentFlag=urgentFlag;
	}
	/**
	 * 紧急标识:Y-急单，N-非急单
	 * @return
	 */
	public String getUrgentFlag(){
		return this.urgentFlag;
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
	 * 仓位编码
	 * @param warehouse
	 */
	
	public void setWarehouse(String warehouse){
		this.warehouse=warehouse;
	}
	/**
	 * 仓位编码
	 * @return
	 */
	public String getWarehouse(){
		return this.warehouse;
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
	 * 云汉订单号
	 * @param yunhanOrderCode
	 */
	
	public void setYunhanOrderCode(String yunhanOrderCode){
		this.yunhanOrderCode=yunhanOrderCode;
	}
	/**
	 * 云汉订单号
	 * @return
	 */
	public String getYunhanOrderCode(){
		return this.yunhanOrderCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	public List<String> getPurchaserOrderCodes() {
		return purchaserOrderCodes;
	}
	
	public void setPurchaserOrderCodes(String purchaserOrderCodes) {
		if(null != purchaserOrderCodes && !"".equals(purchaserOrderCodes)){
			this.purchaserOrderCodes = Arrays.asList(purchaserOrderCodes.split(","));
		}
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public String getPurchaserOrderCode() {
		return purchaserOrderCode;
	}
	public void setPurchaserOrderCode(String purchaserOrderCode) {
		this.purchaserOrderCode = purchaserOrderCode;
	}
	public List<String> getIsbns() {
		return isbns;
	}
	public void setIsbns(String isbns) {
		if(null != isbns && !"".equals(isbns)){
			this.isbns = Arrays.asList(isbns.split(","));
		}
	}
	public List<String> getBookTitles() {
		return bookTitles;
	}
	public void setBookTitles(String bookTitles) {
		if(null != bookTitles && !"".equals(bookTitles)){
			this.bookTitles = Arrays.asList(bookTitles.split(","));
		}
	}
	public String getSendOutDateStart() {
		return sendOutDateStart;
	}
	public void setSendOutDateStart(String sendOutDateStart) {
		this.sendOutDateStart = sendOutDateStart;
	}
	public String getSendOutDateEnd() {
		return sendOutDateEnd;
	}
	public void setSendOutDateEnd(String sendOutDateEnd) {
		this.sendOutDateEnd = sendOutDateEnd;
	}
	public String getSendGoodsType() {
		return sendGoodsType;
	}
	public void setSendGoodsType(String sendGoodsType) {
		this.sendGoodsType = sendGoodsType;
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
	/**
	 * @return the ids
	 */
	public List<String> getIds() {
		return ids;
	}
	/**
	 * @param ids the ids to set
	 */
	public void setIds(String ids) {
		String[] str=null;
		if(!"".equals(ids)&& ids!=null){
			str=ids.split(",");
		}
		if(str!=null){
			this.ids = Arrays.asList(str);
		}
	}
	public String getSendDateStart() {
		return sendDateStart;
	}
	public void setSendDateStart(String sendDateStart) {
		this.sendDateStart = sendDateStart;
	}
	public String getSendDateEnd() {
		return sendDateEnd;
	}
	public void setSendDateEnd(String sendDateEnd) {
		this.sendDateEnd = sendDateEnd;
	}
	public List<Integer> getOrderTypes() {
		return orderTypes;
	}
	
	public void setOrderTypes(String orderTypes) {
		if(null != orderTypes && !"".equals(orderTypes)){
			List<Integer> orderTypeList= new ArrayList<Integer>();
			String[] orderTypeStrings = orderTypes.split(",");
			for (int i = 0; i < orderTypeStrings.length; i++) {
				orderTypeList.add(Integer.valueOf(orderTypeStrings[i]));
			}
			this.orderTypes = orderTypeList;
		}
	}
	public String getOtherAvailableReason() {
		return otherAvailableReason;
	}
	public void setOtherAvailableReason(String otherAvailableReason) {
		this.otherAvailableReason = otherAvailableReason;
	}
	/**
	 * @return the isValids
	 */
	public List<String> getIsValids() {
		return isValids;
	}
	/**
	 * @param isValids the isValids to set
	 */
	public void setIsValids(String isValids) {
		if(null != isValids && !"".equals(isValids)){
			this.isValids = Arrays.asList(isValids.split(","));
		}
	}
	public List<String> getPurchaseOrderCodes() {
		return purchaseOrderCodes;
	}
	public void setPurchaseOrderCodes(List<String> purchaseOrderCodes) {
		this.purchaseOrderCodes = purchaseOrderCodes;
	}
	public List<String> getWarehouses() {
		return warehouses;
	}
	public void setWarehouses(String warehouses) {
		if(null != warehouses && !"".equals(warehouses)){
			this.warehouses = Arrays.asList(warehouses.split(","));
		}
	}
	
}
