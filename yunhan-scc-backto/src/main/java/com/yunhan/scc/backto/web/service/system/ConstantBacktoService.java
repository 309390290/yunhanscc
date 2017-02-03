package com.yunhan.scc.backto.web.service.system;

import java.util.List;

import com.yunhan.scc.backto.web.entities.system.ConstantBacktoDo;

/**
 * 获取常数配置service
 * @author luohoudong
 * @version created at 2016-8-1 上午11:20:42
 */
public interface ConstantBacktoService {
	
	/**
	 * 根据常数类型获取常数
	 * @author luohoudong
	 * @version created at 2016-8-1 上午11:20:51
	 * @param typeCode
	 * @return
	 */
	public List<ConstantBacktoDo> getConstantsByType(String typeCode);

	/**
	 * 
	 * @Description: 获取常数信息
	 * @param @param typeCode 常数类型代码
	 * @param @param consName 常数名称
	 * @param @return   
	 * @return ConstantBacktoDo  
	 * @throws
	 * @author lumin
	 * @date 2016-8-30
	 */
	public ConstantBacktoDo getConstantByTypeAndCode(String typeCode,String consName);
}
