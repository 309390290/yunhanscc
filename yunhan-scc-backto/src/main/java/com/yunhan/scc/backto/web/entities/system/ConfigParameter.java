package com.yunhan.scc.backto.web.entities.system;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yunhan.scc.tools.component.module.query.QueryResult;
import com.yunhan.scc.tools.execl.ExeclBeanInterface;
import com.yunhan.scc.tools.execl.ExeclDataStatus;


/**
 * 参数值配置 实体
 * @author xiongmingbao
 * @version 2016-3-16 11:31:38
 */
public class ConfigParameter extends QueryResult implements ExeclBeanInterface {
	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	*主键
	*/
	private Long id;
	
	private List<String> ids;
	/**
	*有效性:Y-有效，N-无效
	*/
	private String isValid;
	/**
	*参数一的编码
	*/
	private String parameterCode1;
	/**
	*参数二的编码
	*/
	private String parameterCode2;
	/**
	*参数一的名称
	*/
	private String parameterName1;
	/**
	*参数二的名称
	*/
	private String parameterName2;
	/**
	*参数一的值
	*/
	private String parameterValue1;
	/**
	*参数二的值
	*/
	private String parameterValue2;
	/**
	*用户编码
	*/
	private String userCode;
	
	/**
	*用户名
	*/
	private String	userName;
	/**
	*主体
	*/
	private String userPurName;
	/**
	*配置类型
	*/
	private String	configType;
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
		  if (null != ids && !ids.equals("")) {
			  this.ids = Arrays.asList(ids.split(","));
		  }
	}

		public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPurName() {
		return userPurName;
	}

	public void setUserPurName(String userPurName) {
		this.userPurName = userPurName;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

		/**
		 * 
		 * 错误信息
		 */
		private String errorMessage;
		
		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		@Override
		public String getPerties(String key) {
			return titleMap.get(key);
		}

		@Override
		public Map<String, String> getTileMap() {
			return titleMap;
		}
		public static Map<String,String> titleMap = new HashMap<>();
		
		static {
			titleMap.put("用户编码", "userCode");
			titleMap.put("对应主体编码", "parameterValue1");
			titleMap.put("订单种类", "parameterName2");
		}

		@Override
		public ExeclDataStatus hasError() {
			if(errorMessage == null){
				return ExeclDataStatus.SUCCES;
			}else{
				return ExeclDataStatus.ERROR;
			}
		}
}