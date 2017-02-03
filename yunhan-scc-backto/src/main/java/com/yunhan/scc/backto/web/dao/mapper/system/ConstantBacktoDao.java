package com.yunhan.scc.backto.web.dao.mapper.system;

import java.util.List;
import java.util.Map;

import com.yunhan.scc.backto.web.entities.system.ConstantBacktoDo;

/**
 * 常数dao
 * @author luohoudong
 * @version created at 2016-8-1 上午11:19:04
 */
public interface ConstantBacktoDao {

	/**
	 * 根据常数类型获取常数
	 * @author luohoudong
	 * @version created at 2016-8-1 上午11:18:37
	 * @param map
	 * @return
	 */
	public List<ConstantBacktoDo> getConstantsByType(Map<String, Object> map);
	/**
	 * 
	 * @Description: 获取常数值
	 * @param @param map
	 * @param @return   
	 * @return ConstantBacktoDo  
	 * @throws
	 * @author lumin
	 * @date 2016-8-30
	 */
	public ConstantBacktoDo getConstantByTypeAndCode(Map<String, Object> map);

}
