package com.yunhan.scc.backto.web.entities.system;

import com.yunhan.scc.tools.component.module.query.QueryResult;


/**
 * 常数表 实体
 * @author luohoudong
 * @version created at 2016-8-1 上午11:20:16
 */
public class ConstantBacktoDo extends QueryResult{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	*常数编码
	*/
	private String consCode;
	/**
	*常数名
	*/
	private String consName;
	/**
	*常数值
	*/
	private String consValue;
	/**
	*启用标识(1是启用,0是未启用)
	*/
	private Integer control;
	/**
	*主键
	*/
	private Integer id;
	/**
	*主体编码
	*/
	private String objEcd;
	/**
	*备注
	*/
	private String remark;
	/**
	*排序序号
	*/
	private Integer sortnum;
	/**
	*类型代码(类型表类型代码)
	*/
	private String typeCode;
	
	/**
	 * 为了使用下拉插件，把consValue转为value ,consName转为name
	 */
	private String value;
	private String name;
	/**
	 * 常数编码
	 * @param consCode
	 */
	public void setConsCode(String consCode){
		this.consCode=consCode;
	}
	
	/**
	 * 常数编码
	 * @return
	 */
	public String getConsCode(){
		return this.consCode;
	}
	/**
	 * 常数名
	 * @param consName
	 */
	public void setConsName(String consName){
		this.consName=consName;
	}
	
	/**
	 * 常数名
	 * @return
	 */
	public String getConsName(){
		return this.consName;
	}
	/**
	 * 常数值
	 * @param consValue
	 */
	public void setConsValue(String consValue){
		this.consValue=consValue;
	}
	
	/**
	 * 常数值
	 * @return
	 */
	public String getConsValue(){
		return this.consValue;
	}
	/**
	 * 启用标识(1是启用,0是未启用)
	 * @param control
	 */
	public void setControl(Integer control){
		this.control=control;
	}
	
	/**
	 * 启用标识(1是启用,0是未启用)
	 * @return
	 */
	public Integer getControl(){
		return this.control;
	}
	/**
	 * 主键
	 * @param id
	 */
	public void setId(Integer id){
		this.id=id;
	}
	
	/**
	 * 主键
	 * @return
	 */
	public Integer getId(){
		return this.id;
	}
	/**
	 * 主体编码
	 * @param objEcd
	 */
	public void setObjEcd(String objEcd){
		this.objEcd=objEcd;
	}
	
	/**
	 * 主体编码
	 * @return
	 */
	public String getObjEcd(){
		return this.objEcd;
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
	 * 排序序号
	 * @param sortnum
	 */
	public void setSortnum(Integer sortnum){
		this.sortnum=sortnum;
	}
	
	/**
	 * 排序序号
	 * @return
	 */
	public Integer getSortnum(){
		return this.sortnum;
	}
	/**
	 * 类型代码(类型表类型代码)
	 * @param typeCode
	 */
	public void setTypeCode(String typeCode){
		this.typeCode=typeCode;
	}
	
	/**
	 * 类型代码(类型表类型代码)
	 * @return
	 */
	public String getTypeCode(){
		return this.typeCode;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
