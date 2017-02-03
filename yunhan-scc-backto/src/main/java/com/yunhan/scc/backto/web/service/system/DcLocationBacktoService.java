package com.yunhan.scc.backto.web.service.system;

import java.util.List;

import com.yunhan.scc.backto.web.entities.system.DcLocationBacktoDo;

/**
 * 
 * ClassName: DcLocationBacktoService 
 * @Description: 仓位service
 * @author lumin
 * @date 2016-7-13
 */
public interface DcLocationBacktoService {
	
	/**
	 * 
	 * @Description: 根据采购商id获取仓位
	 * @param @param purchaserId
	 * @param @return
	 * @param @throws Exception   
	 * @return List<DcLocationBacktoDo>  
	 * @throws
	 * @author lumin
	 * @date 2016-7-13
	 */
	public List<DcLocationBacktoDo> getDcLocationByPurchaserId(String purchaserId) throws Exception;

	/**
	 * 
	 * @Description: 获取采购商仓位和每个仓位待处理品种数
	 * @param @param purchaserId
	 * @param @return   
	 * @return List<Task>  
	 * @throws
	 * @author lumin
	 * @date 2016-8-25
	 */
	public List<DcLocationBacktoDo> getDcLocationsAndItmesCount(String purchaserId,String userCode) throws Exception;
	
	/**
	 * 获取采购商仓位和每个仓位待处理订单数
	 * @author luohoudong
	 * @version created at 2016-9-26 上午11:55:11
	 * @param purchaserId
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public List<DcLocationBacktoDo> getDcLocationsAndOrderCount(String purchaserId,String userCode) throws Exception;
}
