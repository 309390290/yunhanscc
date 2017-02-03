package com.yunhan.scc.backto.interfaceEntrance.entities.backreport;

import java.lang.Integer;
import java.util.Date;

import com.yunhan.scc.tools.component.module.query.QueryResult;


/**
 * 业务数据推送给接口的临时表 实体
 * @author luohoudong
 * @version 2016-10-17 10:55:27
 */
public class SendDataToInterfaceTDO extends QueryResult

{
	/**
	*数据创建日期
	*/
	private Date addDate;
	/**
	*主键
	*/
	private Long id;
	/**
	*发送的业务类型：1-回告数据
	*/
	private Integer sendType;
	/**
	*业务数据主键id
	*/
	private Long sourceDataId;
	/**
	 * 数据创建日期
	 * @param addDate
	 */
	public void setAddDate(Date addDate){
		this.addDate=addDate;
	}
	
	/**
	 * 数据创建日期
	 * @return
	 */
	public Date getAddDate(){
		return this.addDate;
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
	 * 发送的业务类型：1-回告数据
	 * @param sendType
	 */
	public void setSendType(Integer sendType){
		this.sendType=sendType;
	}
	
	/**
	 * 发送的业务类型：1-回告数据
	 * @return
	 */
	public Integer getSendType(){
		return this.sendType;
	}
	/**
	 * 业务数据主键id
	 * @param sourceDataId
	 */
	public void setSourceDataId(Long sourceDataId){
		this.sourceDataId=sourceDataId;
	}
	
	/**
	 * 业务数据主键id
	 * @return
	 */
	public Long getSourceDataId(){
		return this.sourceDataId;
	}

}