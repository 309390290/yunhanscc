package com.yunhan.scc.backto.web.model.backreport;


import com.yunhan.scc.tools.component.module.query.QueryCondition;
import java.lang.Integer;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * ProResponseItems查询条件
 * @author luohoudong
 * @version 2016-7-18 15:43:58
 */
public class ProResponseItemsBacktoCondition extends QueryCondition {
	
	/**
	 * 创建日期
	 */
	private Date addTime;
	/**
	 * 创建人
	 */
	private String addUserCode;
	/**
	 * 可供折扣
	 */
	private BigDecimal availableDiscountrate;
	/**
	 * 可供价
	 */
	private BigDecimal availablePrice;
	/**
	 * 满足数量 
	 * 弃用20160803
	 */
//	private Integer availableQty;
	/**
	 * 商品名称
	 */
	private String bookTitle;
	/**
	 * 差异类型:0:无差异,1:数量有差异,2:折扣有差异,3:定价有差异,4:数量、折扣有差异,5:数量、定价有差异,6:折扣、定价有差异,7:数量、折扣、定价有差异
	 */
	private String diffType;
	/**
	 * 采购折扣
	 */
	private BigDecimal discountrate;
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
	 * 关闭原因: 2、已停产，3、改版，4、版权到期，5、商品无效，6、销售受限（后面5个都属于永久缺货类型），7-其他
	 *  弃用20160803
	 */
//	private String notEnoughReason;
	/**
	 * 订数
	 */
	private Integer orderQty;
	/**
	 * 其余满足情况: 0-预计可发、1-暂时缺货、2、已停产，3、改版，4、版权到期，5、商品无效，6、销售受限（后面5个都属于永久缺货类型）
	 */
	private String otherAvailableReason;
	/**
	 * 预计发货日期:1）本次发货量小于满足数量，则必填；2）日期格式：YYYYMMDD
	 */
	private Date preSendDate;
	/**
	 * 定价
	 */
	private BigDecimal price;
	/**
	 * 订单细目表ID
	 */
	private Long proPurOrderItemsId;
	/**
	 * 发货单细目id
	 */
	private Long proSendoutItemsId;
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
	private Date responseDate;
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
	 * 订单来源类型:YHSYS-云汉报订产品、EDI、FTP、DB、SCM等，根据业务发展决定
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
	private Integer thisSendQty;
	/**
	 * 修改日期
	 */
	private Date updateTime;
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
	 * 回告ids
	 */
	private List<String> ids;
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
	 * 可供折扣
	 * @param availableDiscountrate
	 */
	
	public void setAvailableDiscountrate(BigDecimal availableDiscountrate){
		this.availableDiscountrate=availableDiscountrate;
	}
	/**
	 * 可供折扣
	 * @return
	 */
	public BigDecimal getAvailableDiscountrate(){
		return this.availableDiscountrate;
	}
		
	/**
	 * 可供价
	 * @param availablePrice
	 */
	
	public void setAvailablePrice(BigDecimal availablePrice){
		this.availablePrice=availablePrice;
	}
	/**
	 * 可供价
	 * @return
	 */
	public BigDecimal getAvailablePrice(){
		return this.availablePrice;
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
	
	public void setDiscountrate(BigDecimal discountrate){
		this.discountrate=discountrate;
	}
	/**
	 * 采购折扣
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
	 * 其余满足情况: 0-预计可发、1-暂时缺货、2、已停产，3、改版，4、版权到期，5、商品无效，6、销售受限（后面5个都属于永久缺货类型）
	 * @param otherAvailableReason
	 */
	
	public void setOtherAvailableReason(String otherAvailableReason){
		this.otherAvailableReason=otherAvailableReason;
	}
	/**
	 * 其余满足情况: 0-预计可发、1-暂时缺货、2、已停产，3、改版，4、版权到期，5、商品无效，6、销售受限（后面5个都属于永久缺货类型）
	 * @return
	 */
	public String getOtherAvailableReason(){
		return this.otherAvailableReason;
	}
		
	/**
	 * 预计发货日期:1）本次发货量小于满足数量，则必填；2）日期格式：YYYYMMDD
	 * @param preSendDate
	 */
	
	public void setPreSendDate(Date preSendDate){
		this.preSendDate=preSendDate;
	}
	/**
	 * 预计发货日期:1）本次发货量小于满足数量，则必填；2）日期格式：YYYYMMDD
	 * @return
	 */
	public Date getPreSendDate(){
		return this.preSendDate;
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
	 * 订单细目表ID
	 * @param proPurOrderItemsId
	 */
	
	public void setProPurOrderItemsId(Long proPurOrderItemsId){
		this.proPurOrderItemsId=proPurOrderItemsId;
	}
	/**
	 * 订单细目表ID
	 * @return
	 */
	public Long getProPurOrderItemsId(){
		return this.proPurOrderItemsId;
	}
		
	/**
	 * 发货单细目id
	 * @param proSendoutItemsId
	 */
	
	public void setProSendoutItemsId(Long proSendoutItemsId){
		this.proSendoutItemsId=proSendoutItemsId;
	}
	/**
	 * 发货单细目id
	 * @return
	 */
	public Long getProSendoutItemsId(){
		return this.proSendoutItemsId;
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
	
	public void setResponseDate(Date responseDate){
		this.responseDate=responseDate;
	}
	/**
	 * 回告日期:YYYYMMDD
	 * @return
	 */
	public Date getResponseDate(){
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
	 * 订单来源类型:YHSYS-云汉报订产品、EDI、FTP、DB、SCM等，根据业务发展决定
	 * @param sourceType
	 */
	
	public void setSourceType(String sourceType){
		this.sourceType=sourceType;
	}
	/**
	 * 订单来源类型:YHSYS-云汉报订产品、EDI、FTP、DB、SCM等，根据业务发展决定
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
	
	public void setThisSendQty(Integer thisSendQty){
		this.thisSendQty=thisSendQty;
	}
	/**
	 * 本次发货数量
	 * @return
	 */
	public Integer getThisSendQty(){
		return this.thisSendQty;
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
	
	
		
}
