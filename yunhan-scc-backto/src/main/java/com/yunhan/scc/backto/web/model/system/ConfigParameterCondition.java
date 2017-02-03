package com.yunhan.scc.backto.web.model.system;

import java.util.Arrays;
import java.util.List;

import com.yunhan.scc.tools.component.module.query.QueryCondition;


/**
 * 参数值配置表 查询条件
 * @author xiongmingbao
 * @version 2016-3-16 11:31:38
 */
public class ConfigParameterCondition extends QueryCondition {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 有效性:Y-有效，N-无效
	 */
	private String isValid;
	/**
	 * 参数一的编码
	 */
	private String parameterCode1;
	/**
	 * 参数二的编码
	 */
	private String parameterCode2;
	/**
	 * 参数一的名称
	 */
	private String parameterName1;
	/**
	 * 参数二的名称
	 */
	private String parameterName2;
	/**
	 * 参数一的值
	 */
	private String parameterValue1;
	/**
	 * 参数二的值
	 */
	private String parameterValue2;
	/**
	 * 用户编码
	 */
	private String userCode;
	
	
	/**
	 * 配置类型
	 */
	private String configType;
	/**
	 * 用户集合
	 */
	private List<String> users;
	/**
	 * 主体集合
	 */
	private List<String> userPurs;
	/**
	 * 对应主体集合
	 */
	private List<String> userPursTo;
	
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
	 * 有效性:Y-有效，N-无效
	 * @param isValid
	 */
	
	public void setIsValid(String isValid){
		this.isValid=isValid;
	}
	/**
	 * 有效性:Y-有效，N-无效
	 * @return
	 */
	public String getIsValid(){
		return this.isValid;
	}
		
	/**
	 * 参数一的编码
	 * @param parameterCode1
	 */
	
	public void setParameterCode1(String parameterCode1){
		this.parameterCode1=parameterCode1;
	}
	/**
	 * 参数一的编码
	 * @return
	 */
	public String getParameterCode1(){
		return this.parameterCode1;
	}
		
	/**
	 * 参数二的编码
	 * @param parameterCode2
	 */
	
	public void setParameterCode2(String parameterCode2){
		this.parameterCode2=parameterCode2;
	}
	/**
	 * 参数二的编码
	 * @return
	 */
	public String getParameterCode2(){
		return this.parameterCode2;
	}
		
	/**
	 * 参数一的名称
	 * @param parameterName1
	 */
	
	public void setParameterName1(String parameterName1){
		this.parameterName1=parameterName1;
	}
	/**
	 * 参数一的名称
	 * @return
	 */
	public String getParameterName1(){
		return this.parameterName1;
	}
		
	/**
	 * 参数二的名称
	 * @param parameterName2
	 */
	
	public void setParameterName2(String parameterName2){
		this.parameterName2=parameterName2;
	}
	/**
	 * 参数二的名称
	 * @return
	 */
	public String getParameterName2(){
		return this.parameterName2;
	}
		
	/**
	 * 参数一的值
	 * @param parameterValue1
	 */
	
	public void setParameterValue1(String parameterValue1){
		this.parameterValue1=parameterValue1;
	}
	/**
	 * 参数一的值
	 * @return
	 */
	public String getParameterValue1(){
		return this.parameterValue1;
	}
		
	/**
	 * 参数二的值
	 * @param parameterValue2
	 */
	
	public void setParameterValue2(String parameterValue2){
		this.parameterValue2=parameterValue2;
	}
	/**
	 * 参数二的值
	 * @return
	 */
	public String getParameterValue2(){
		return this.parameterValue2;
	}
		
	/**
	 * 用户编码
	 * @param userCode
	 */
	
	public void setUserCode(String userCode){
		this.userCode=userCode;
	}
	/**
	 * 用户编码
	 * @return
	 */
	public String getUserCode(){
		return this.userCode;
	}
	public String getConfigType() {
		return configType;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
	}
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(String users) {
		if(null!=users&&!"".equals(users)){
			this.users = Arrays.asList(users.split(","));
		}
	}
	public List<String> getUserPurs() {
		return userPurs;
	}
	public void setUserPurs(String userPurs) {
		if(null!=userPurs&&!"".equals(userPurs)){
			this.userPurs = Arrays.asList(userPurs.split(","));
		}
	}
	public List<String> getUserPursTo() {
		return userPursTo;
	}
	public void setUserPursTo(String userPursTo) {
		if(null!=userPursTo&&!"".equals(userPursTo)){
			this.userPursTo = Arrays.asList(userPursTo.split(","));
		}
	}
		
}
