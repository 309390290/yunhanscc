package com.yunhan.scc.backto.web.entities.order;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.yunhan.scc.tools.component.module.query.QueryResult;


/**
 *ProPurOrderSummary实体
 * @author wangtao
 * @version 2016-7-7 13:14:06
 */
public class ProPurOrderSummaryBacktoDO extends QueryResult

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	*制单日期:YYYYMMDD
	*/
	private Date addDate;
	/**
	*创建日期
	*/
	private Timestamp addTime;
	/**
	*制单人名称:中文人名+"_"+采购商内部人员编号（即采购联系人）
	*/
	private String addUser;
	/**
	*创建人
	*/
	private String addUserCode;
	/**
	*制单人电话
	*/
	private String addUserPhoneno;
	/**
	*满足数
	*/
	private Integer availableQty;
	/**
	*满足率
	*/
	private Long availableRate;
	/**
	*业务来源类型：COM-一般图书，EDU-文教
	*/
	private String businessType;
	/**
	*订单关闭原因
	*/
	private String closeReason;
	/**
	*到站
	*/
	private String destination;
	/**
	*订单有效期:精确到时刻
	*/
	private String expiryDate;
	/**
	*主键
	*/
	private Long id;
	/**
	*订单处理状态：Y-已处理，N-未处理
	*/
	private String isDeal;
	/**
	*是否已导出:Y-是，N-否
	*/
	private String isExport;
	/**
	*新品订单:Y-是，N-否
	*/
	private String isNew;
	/**
	*订单完整性（在订单创建和修改的时候计算）:0-有效订单，1-未找到供应商，2-订单其他信息不完整
	*/
	private String isOrderComplete;
	/**
	*补单标识:Y-是，N-否
	*/
	private String isSupplement;
	/**
	*供应商是否已查阅:Y-是，N-否
	*/
	private String isView;
	/**
	*差异类型（停用）: 0:无差异,1:数量有差异,2:折扣有差异,3:定价有差异,4:数量、折扣有差异,5:数量、定价有差异,6:折扣、定价有差异,7:数量、折扣、定价有差异
	*/
	private Integer orderDifference;
	/**
	*订单备注
	*/
	private String orderRemark;
	/**
	*订单状态：0-未发送，5-不发送，10-已发送未回告，13-部分回告，15-全部回告，20-部分发货(属于未收货)，25-全部发货(属于未收货)，30-部分收货，35-全部收货，40-已关闭(采购商)，45-已结束(供应商)，47-需审批,50-已逻辑删除
	*/
	private Integer orderStatus;
	/**
	*订单种类:0：一般零售
5：文教订单
10：电商订单
15：大中专订单
20：团购订单
25：馆配订单
30：活动订书
	*/
	private Integer orderType;
	
	private String orderTypeString;
	
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
	*收货联系人
	*/
	private String receiveContactUser;
	/**
	*收货单位
	*/
	private String receiveDept;
	/**
	*收货电话
	*/
	private String receivePhoneno;
	/**
	*到货数
	*/
	private Integer receiveQty;
	/**
	*到货率
	*/
	private Long receiveRate;
	/**
	*要求到货日期：YYYYMMDD
	*/
	private Date reqArriveDate;
	/**
	*预留字段：对应发货单号(多个)
	*/
	private String sendoutGoodsCode;
	/**
	*发货数
	*/
	private Integer sendoutQty;
	/**
	*发货率
	*/
	private Long sendoutRate;
	/**
	*发送日期:YYYYMMDD
	*/
	private Date sendDate;
	/**
	*发货方式(收货类型):1：普通,2：直供
	*/
	private String sendGoodsType;
	/**
	*发送人名称：中文人名+"_"+采购商内部人员编号（即采购联系人）
	*/
	private String sendUser;
	/**
	*订单来源类型:YHSYS-云汉报订产品、EDI、FTP、DB等，根据业务发展决定
	*/
	private String sourceType;
	/**
	*供应商ID
	*/
	private String supplierId;
	/**
	*总册数
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
	*品种数
	*/
	private Integer totalVarietyQty;
	/**
	*修改日期
	*/
	private Timestamp updateTime;
	/**
	*修改人
	*/
	private String updateUserCode;
	/**
	*紧急程度:1：急单、2：普通单
	*/
	private String urgentFlag;
	/**
	*仓位
	*/
	private String warehouse;
	/**
	*云汉订单号
	*/
	private String yunhanOrderCode;
	/**
	 * 未处理品种数
	 */
	private Integer untreated;
	/**
	 * 已处理品种数
	 */
	private Integer alreadyProcessed;
	/**
	 * 采购商名称
	 */
	private String purchaserName;
	/**
	 * 收货地址及联系方式
	 */
	private String addPhone;
	
	/**
	 * Excel 导出使用，用于表示总目下的所有细目。
	 */
	private List<ProPurOrderItemsBacktoDO> orderItems;
	/**
	 * Excel 导出使用，用于表示文件的名称。
	 */
	private String execlName;
	
	/**
	 * Excel 导出使用。
	 */
	private String orderItemsIds;
	
	/**
	 * 排序字段
	 */
	private String sortOrderField;
	/**
	 * 排序方式
	 */
	private String sortOrderRule;
	
	
	public List<ProPurOrderItemsBacktoDO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<ProPurOrderItemsBacktoDO> orderItems) {
		this.orderItems = orderItems;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getUntreated() {
		return untreated;
	}

	public void setUntreated(Integer untreated) {
		this.untreated = untreated;
	}

	public Integer getAlreadyProcessed() {
		return alreadyProcessed;
	}

	public void setAlreadyProcessed(Integer alreadyProcessed) {
		this.alreadyProcessed = alreadyProcessed;
	}

	/**
	 * 制单日期:YYYYMMDD
	 * @param addDate
	 */
	public void setAddDate(Date addDate){
		this.addDate=addDate;
	}
	
	/**
	 * 制单日期:YYYYMMDD
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
	 * 制单人名称:中文人名+"_"+采购商内部人员编号（即采购联系人）
	 * @param addUser
	 */
	public void setAddUser(String addUser){
		this.addUser=addUser;
	}
	
	/**
	 * 制单人名称:中文人名+"_"+采购商内部人员编号（即采购联系人）
	 * @return
	 */
	public String getAddUser(){
		return this.addUser;
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
	 * 制单人电话
	 * @param addUserPhoneno
	 */
	public void setAddUserPhoneno(String addUserPhoneno){
		this.addUserPhoneno=addUserPhoneno;
	}
	
	/**
	 * 制单人电话
	 * @return
	 */
	public String getAddUserPhoneno(){
		return this.addUserPhoneno;
	}
	/**
	 * 满足数
	 * @param availableQty
	 */
	public void setAvailableQty(Integer availableQty){
		this.availableQty=availableQty;
	}
	
	/**
	 * 满足数
	 * @return
	 */
	public Integer getAvailableQty(){
		return this.availableQty;
	}
	/**
	 * 满足率
	 * @param availableRate
	 */
	public void setAvailableRate(Long availableRate){
		this.availableRate=availableRate;
	}
	
	/**
	 * 满足率
	 * @return
	 */
	public Long getAvailableRate(){
		return this.availableRate;
	}
	/**
	 * 业务来源类型：COM-一般图书，EDU-文教
	 * @param businessType
	 */
	public void setBusinessType(String businessType){
		this.businessType=businessType;
	}
	
	/**
	 * 业务来源类型：COM-一般图书，EDU-文教
	 * @return
	 */
	public String getBusinessType(){
		return this.businessType;
	}
	/**
	 * 订单关闭原因
	 * @param closeReason
	 */
	public void setCloseReason(String closeReason){
		this.closeReason=closeReason;
	}
	
	/**
	 * 订单关闭原因
	 * @return
	 */
	public String getCloseReason(){
		return this.closeReason;
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
	 * 订单有效期:精确到时刻
	 * @param expiryDate
	 */
	public void setExpiryDate(String expiryDate){
		this.expiryDate=expiryDate;
	}
	
	/**
	 * 订单有效期:精确到时刻
	 * @return
	 */
	public String getExpiryDate(){
		return this.expiryDate;
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
	 * 新品订单:Y-是，N-否
	 * @param isNew
	 */
	public void setIsNew(String isNew){
		this.isNew=isNew;
	}
	
	/**
	 * 新品订单:Y-是，N-否
	 * @return
	 */
	public String getIsNew(){
		return this.isNew;
	}
	/**
	 * 订单完整性（在订单创建和修改的时候计算）:0-有效订单，1-未找到供应商，2-订单其他信息不完整
	 * @param isOrderComplete
	 */
	public void setIsOrderComplete(String isOrderComplete){
		this.isOrderComplete=isOrderComplete;
	}
	
	/**
	 * 订单完整性（在订单创建和修改的时候计算）:0-有效订单，1-未找到供应商，2-订单其他信息不完整
	 * @return
	 */
	public String getIsOrderComplete(){
		return this.isOrderComplete;
	}
	/**
	 * 补单标识:Y-是，N-否
	 * @param isSupplement
	 */
	public void setIsSupplement(String isSupplement){
		this.isSupplement=isSupplement;
	}
	
	/**
	 * 补单标识:Y-是，N-否
	 * @return
	 */
	public String getIsSupplement(){
		return this.isSupplement;
	}
	/**
	 * 供应商是否已查阅:Y-是，N-否
	 * @param isView
	 */
	public void setIsView(String isView){
		this.isView=isView;
	}
	
	/**
	 * 供应商是否已查阅:Y-是，N-否
	 * @return
	 */
	public String getIsView(){
		return this.isView;
	}
	/**
	 * 差异类型（停用）: 0:无差异,1:数量有差异,2:折扣有差异,3:定价有差异,4:数量、折扣有差异,5:数量、定价有差异,6:折扣、定价有差异,7:数量、折扣、定价有差异
	 * @param orderDifference
	 */
	public void setOrderDifference(Integer orderDifference){
		this.orderDifference=orderDifference;
	}
	
	/**
	 * 差异类型（停用）: 0:无差异,1:数量有差异,2:折扣有差异,3:定价有差异,4:数量、折扣有差异,5:数量、定价有差异,6:折扣、定价有差异,7:数量、折扣、定价有差异
	 * @return
	 */
	public Integer getOrderDifference(){
		return this.orderDifference;
	}
	/**
	 * 订单备注
	 * @param orderRemark
	 */
	public void setOrderRemark(String orderRemark){
		this.orderRemark=orderRemark;
	}
	
	/**
	 * 订单备注
	 * @return
	 */
	public String getOrderRemark(){
		return this.orderRemark;
	}
	/**
	 * 订单状态：0-未发送，5-不发送，10-已发送未回告，13-部分回告，15-全部回告，20-部分发货(属于未收货)，25-全部发货(属于未收货)，30-部分收货，35-全部收货，40-已关闭(采购商)，45-已结束(供应商)，47-需审批,50-已逻辑删除
	 * @param orderStatus
	 */
	public void setOrderStatus(Integer orderStatus){
		this.orderStatus=orderStatus;
	}
	
	/**
	 * 订单状态：0-未发送，5-不发送，10-已发送未回告，13-部分回告，15-全部回告，20-部分发货(属于未收货)，25-全部发货(属于未收货)，30-部分收货，35-全部收货，40-已关闭(采购商)，45-已结束(供应商)，47-需审批,50-已逻辑删除
	 * @return
	 */
	public Integer getOrderStatus(){
		return this.orderStatus;
	}
	/**
	 * 订单种类:0：一般零售
5：文教订单
10：电商订单
15：大中专订单
20：团购订单
25：馆配订单
30：活动订书

	 * @param orderType
	 */
	public void setOrderType(Integer orderType){
		this.orderType=orderType;
	}
	
	/**
	 * 订单种类:0：一般零售
5：文教订单
10：电商订单
15：大中专订单
20：团购订单
25：馆配订单
30：活动订书

	 * @return
	 */
	public Integer getOrderType(){
		return this.orderType;
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
	 * 收货联系人
	 * @param receiveContactUser
	 */
	public void setReceiveContactUser(String receiveContactUser){
		this.receiveContactUser=receiveContactUser;
	}
	
	/**
	 * 收货联系人
	 * @return
	 */
	public String getReceiveContactUser(){
		return this.receiveContactUser;
	}
	/**
	 * 收货单位
	 * @param receiveDept
	 */
	public void setReceiveDept(String receiveDept){
		this.receiveDept=receiveDept;
	}
	
	/**
	 * 收货单位
	 * @return
	 */
	public String getReceiveDept(){
		return this.receiveDept;
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
	 * 到货数
	 * @param receiveQty
	 */
	public void setReceiveQty(Integer receiveQty){
		this.receiveQty=receiveQty;
	}
	
	/**
	 * 到货数
	 * @return
	 */
	public Integer getReceiveQty(){
		return this.receiveQty;
	}
	/**
	 * 到货率
	 * @param receiveRate
	 */
	public void setReceiveRate(Long receiveRate){
		this.receiveRate=receiveRate;
	}
	
	/**
	 * 到货率
	 * @return
	 */
	public Long getReceiveRate(){
		return this.receiveRate;
	}
	/**
	 * 要求到货日期：YYYYMMDD
	 * @param reqArriveDate
	 */
	public void setReqArriveDate(Date reqArriveDate){
		this.reqArriveDate=reqArriveDate;
	}
	
	/**
	 * 要求到货日期：YYYYMMDD
	 * @return
	 */
	public Date getReqArriveDate(){
		return this.reqArriveDate;
	}
	/**
	 * 预留字段：对应发货单号(多个)
	 * @param sendoutGoodsCode
	 */
	public void setSendoutGoodsCode(String sendoutGoodsCode){
		this.sendoutGoodsCode=sendoutGoodsCode;
	}
	
	/**
	 * 预留字段：对应发货单号(多个)
	 * @return
	 */
	public String getSendoutGoodsCode(){
		return this.sendoutGoodsCode;
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
	 * 发货率
	 * @param sendoutRate
	 */
	public void setSendoutRate(Long sendoutRate){
		this.sendoutRate=sendoutRate;
	}
	
	/**
	 * 发货率
	 * @return
	 */
	public Long getSendoutRate(){
		return this.sendoutRate;
	}
	/**
	 * 发送日期:YYYYMMDD
	 * @param sendDate
	 */
	public void setSendDate(Date sendDate){
		this.sendDate=sendDate;
	}
	
	/**
	 * 发送日期:YYYYMMDD
	 * @return
	 */
	public Date getSendDate(){
		return this.sendDate;
	}
	/**
	 * 发货方式(收货类型):1：普通,2：直供
	 * @param sendGoodsType
	 */
	public void setSendGoodsType(String sendGoodsType){
		this.sendGoodsType=sendGoodsType;
	}
	
	/**
	 * 发货方式(收货类型):1：普通,2：直供
	 * @return
	 */
	public String getSendGoodsType(){
		return this.sendGoodsType;
	}
	/**
	 * 发送人名称：中文人名+"_"+采购商内部人员编号（即采购联系人）
	 * @param sendUser
	 */
	public void setSendUser(String sendUser){
		this.sendUser=sendUser;
	}
	
	/**
	 * 发送人名称：中文人名+"_"+采购商内部人员编号（即采购联系人）
	 * @return
	 */
	public String getSendUser(){
		return this.sendUser;
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
	 * 总册数
	 * @param totalBookQty
	 */
	public void setTotalBookQty(Integer totalBookQty){
		this.totalBookQty=totalBookQty;
	}
	
	/**
	 * 总册数
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
	 * 品种数
	 * @param totalVarietyQty
	 */
	public void setTotalVarietyQty(Integer totalVarietyQty){
		this.totalVarietyQty=totalVarietyQty;
	}
	
	/**
	 * 品种数
	 * @return
	 */
	public Integer getTotalVarietyQty(){
		return this.totalVarietyQty;
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
	 * 紧急程度:1：急单、2：普通单
	 * @param urgentFlag
	 */
	public void setUrgentFlag(String urgentFlag){
		this.urgentFlag=urgentFlag;
	}
	
	/**
	 * 紧急程度:1：急单、2：普通单
	 * @return
	 */
	public String getUrgentFlag(){
		return this.urgentFlag;
	}
	/**
	 * 仓位
	 * @param warehouse
	 */
	public void setWarehouse(String warehouse){
		this.warehouse=warehouse;
	}
	
	/**
	 * 仓位
	 * @return
	 */
	public String getWarehouse(){
		return this.warehouse;
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

	public String getPurchaserName() {
		return purchaserName;
	}

	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}

	public String getAddPhone() {
		return addPhone;
	}

	public void setAddPhone(String addPhone) {
		this.addPhone = addPhone;
	}

	public String getExeclName() {
		return execlName;
	}

	public void setExeclName(String execlName) {
		this.execlName = execlName;
	}

	public String getOrderTypeString() {
		return orderTypeString;
	}

	public void setOrderTypeString(String orderTypeString) {
		this.orderTypeString = orderTypeString;
	}

	/**
	 * @return the orderItemsIds
	 */
	public String getOrderItemsIds() {
		return orderItemsIds;
	}

	/**
	 * @param orderItemsIds the orderItemsIds to set
	 */
	public void setOrderItemsIds(String orderItemsIds) {
		this.orderItemsIds = orderItemsIds;
	}

	/**
	 * @return the sortOrderField
	 */
	public String getSortOrderField() {
		return sortOrderField;
	}

	/**
	 * @param sortOrderField the sortOrderField to set
	 */
	public void setSortOrderField(String sortOrderField) {
		this.sortOrderField = sortOrderField;
	}

	/**
	 * @return the sortOrderRule
	 */
	public String getSortOrderRule() {
		return sortOrderRule;
	}

	/**
	 * @param sortOrderRule the sortOrderRule to set
	 */
	public void setSortOrderRule(String sortOrderRule) {
		this.sortOrderRule = sortOrderRule;
	}
	
	
	


}