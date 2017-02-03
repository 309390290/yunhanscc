package com.yunhan.scc.backto.web.service.system;

import java.util.List;

import com.yunhan.scc.backto.web.entities.system.ConfigParameter;
import com.yunhan.scc.backto.web.model.system.ConfigParameterCondition;

/**
 * 
 * 参数值配置service
 * @author xiongmingbao
 * @version created at 2016-10-24 上午10:52:35
 */
public interface ConfigParameterService {
	
	/**
	 * 
	 * 新增参数值配置
	 * @author xiongmingbao
	 * @version created at 2016-3-16 下午4:13:25
	 * @param configParameter
	 */
	public void saveConfigParameter(ConfigParameter configParameter);
	
	/**
	 * 
	 * 修改参数值配置信息
	 * @author xiongmingbao
	 * @version created at 2016-3-16 下午5:24:06
	 * @param configParameter
	 */
	public void updateConfigParameter(ConfigParameter configParameter);
	
	/**
	 * 
	 * 根据ids批量删除参数值配置
	 * @author xiongmingbao
	 * @version created at 2016-3-16 下午5:52:29
	 * @param configParameter
	 */
	public void deleteConfigParameter(ConfigParameter configParameter);
	
	/**
	 * 
	 * 修改参数值配置有效性
	 * @author xiongmingbao
	 * @version created at 2016-3-16 下午6:14:43
	 * @param configParameter
	 */
	public void updateConfigParameterStatus(ConfigParameter configParameter);
	
	/**
	 * 
	 * 获取参数值配置信息
	 * @author xiongmingbao
	 * @version created at 2016-10-24 上午11:00:45
	 * @param condition
	 * @return
	 */
	public List<ConfigParameter> findConfigParameters(ConfigParameterCondition condition);
}
