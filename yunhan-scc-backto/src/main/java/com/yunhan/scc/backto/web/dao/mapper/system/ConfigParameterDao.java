package com.yunhan.scc.backto.web.dao.mapper.system;

import java.util.List;

import com.yunhan.scc.backto.web.entities.system.ConfigParameter;
import com.yunhan.scc.backto.web.model.system.ConfigParameterCondition;



/**
 * 
 * 参数值配置
 * @author xiongmingbao
 * @version created at 2016-10-24 上午10:53:44
 */
public interface ConfigParameterDao {

	/**
	 * 
	 * 根据ID获取参数值配置信息
	 * @author xiongmingbao
	 * @version created at 2016-3-16 下午1:29:12
	 * @param id
	 * @return
	 */
	public ConfigParameter getConfigParameterById(Long id);
	
	/**
	 * 
	 * 获取参数值配置信息
	 * @author xiongmingbao
	 * @version created at 2016-10-24 上午10:59:09
	 * @param condition
	 * @return
	 */
	public List<ConfigParameter> findConfigParameters(ConfigParameterCondition condition);
	
	/**
	 * 
	 * 新增参数值配置
	 * @author xiongmingbao
	 * @version created at 2016-3-16 下午3:57:11
	 * @param configParameter
	 */
	public void saveConfigParameter(ConfigParameter configParameter);
	
	/**
	 * 
	 * 修改参数值配置信息
	 * @author xiongmingbao
	 * @version created at 2016-3-16 下午5:21:23
	 * @param configParameter
	 */
	public void updateConfigParameter(ConfigParameter configParameter);
	
	/**
	 * 
	 * 根据ids批量删除参数值配置
	 * @author xiongmingbao
	 * @version created at 2016-3-16 下午5:50:49
	 * @param configParameter
	 */
	public void deleteConfigParameter(ConfigParameter configParameter);
	
	/**
	 * 
	 * 修改参数值配置有效性
	 * @author xiongmingbao
	 * @version created at 2016-3-16 下午6:13:58
	 * @param configParameter
	 */
	public void updateConfigParameterStatus(ConfigParameter configParameter);
 }
