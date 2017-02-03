package com.yunhan.scc.backto.web.entities.task;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：TodoTaskBacktoVo   
 * 类描述： 待办任务  
 * 创建人：lumin
 * 创建时间：2016-8-8 上午11:33:15   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */

public class TodoTaskBacktoVo {
	/** 
	 * 采购商ID
	 */
	private String purchaserId;
	/** 
	 * 采购商名称
	 */
	private String purchaserNm;
	/** 
	 * 待办数
	 */
	private String dbs;
	/**
	 * 采购订单号
	 */
	private String purchseOrderCodes;
	
	/**
	 * 采购商ID
	 * @author pangzhenhua
	 * @version created at 2016年3月4日 上午11:15:03
	 * @return 
	 */
	public String getPurchaserId() {
		return purchaserId;
	}
	/**
	 * 采购商ID
	 * @author pangzhenhua
	 * @version created at 2016年3月4日 上午11:15:13
	 * @param purchaserId 
	 */
	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}
	/**
	 * 采购商名称
	 * @author pangzhenhua
	 * @version created at 2016年3月4日 上午11:15:17
	 * @return 
	 */
	public String getPurchaserNm() {
		return purchaserNm;
	}
	/**
	 * 采购商名称
	 * @author pangzhenhua
	 * @version created at 2016年3月4日 上午11:15:23
	 * @param purchaserNm 
	 */
	public void setPurchaserNm(String purchaserNm) {
		this.purchaserNm = purchaserNm;
	}
	/**
	 * 待办数量
	 * @author pangzhenhua
	 * @version created at 2016年3月4日 上午11:15:27
	 * @return 
	 */
	public String getDbs() {
		return dbs;
	}
	/**
	 * 待办数量
	 * @author pangzhenhua
	 * @version created at 2016年3月4日 上午11:15:35
	 * @param dbs 
	 */
	public void setDbs(String dbs) {
		this.dbs = dbs;
	}
	/**
	 * @return the purchseOrderCodes
	 */
	public String getPurchseOrderCodes() {
		return purchseOrderCodes;
	}
	/**
	 * @param purchseOrderCodes the purchseOrderCodes to set
	 */
	public void setPurchseOrderCodes(String purchseOrderCodes) {
		this.purchseOrderCodes = purchseOrderCodes;
	}
	
	
	
}

