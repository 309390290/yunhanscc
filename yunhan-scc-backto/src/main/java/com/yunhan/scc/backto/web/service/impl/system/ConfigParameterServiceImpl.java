package com.yunhan.scc.backto.web.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhan.scc.backto.web.dao.mapper.system.ConfigParameterDao;
import com.yunhan.scc.backto.web.entities.system.ConfigParameter;
import com.yunhan.scc.backto.web.model.system.ConfigParameterCondition;
import com.yunhan.scc.backto.web.service.system.ConfigParameterService;

/**
 * 
 * 参数值配置service
 * @author xiongmingbao
 * @version created at 2016-10-24 上午10:52:03
 */
@Service
public class ConfigParameterServiceImpl implements ConfigParameterService{
	@Autowired
	private ConfigParameterDao configParameterDao;
	/**
	 * 新增参数值配置
	 */
	@Override
	public void saveConfigParameter(ConfigParameter configParameter) {
		configParameterDao.saveConfigParameter(configParameter);
	}

	/**
	 * 
	 * 修改参数值配置信息
	 * @author xiongmingbao
	 * @version created at 2016-3-16 下午5:24:06
	 * @param configParameter
	 */
	@Override
	public void updateConfigParameter(ConfigParameter configParameter) {
		configParameterDao.updateConfigParameter(configParameter);
	}

	/**
	 * 
	 * 根据ids批量删除参数值配置
	 * @author xiongmingbao
	 * @version created at 2016-3-16 下午5:52:29
	 * @param configParameter
	 */
	@Override
	public void deleteConfigParameter(ConfigParameter configParameter) {
		configParameterDao.deleteConfigParameter(configParameter);
	}

	/**
	 * 
	 * 修改参数值配置有效性
	 * @author xiongmingbao
	 * @version created at 2016-3-16 下午6:14:43
	 * @param configParameter
	 */
	@Override
	public void updateConfigParameterStatus(ConfigParameter configParameter) {
		configParameterDao.updateConfigParameterStatus(configParameter);
	}

	/**
	 * 
	 * 获取参数值配置信息
	 * @author xiongmingbao
	 * @version created at 2016-10-24 上午11:00:45
	 * @param condition
	 * @return
	 */
	@Override
	public List<ConfigParameter> findConfigParameters(ConfigParameterCondition condition) {
		return configParameterDao.findConfigParameters(condition);
	}
	
	
}
