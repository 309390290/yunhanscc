package com.yunhan.scc.backto.web.service.task;

import java.util.List;

import com.yunhan.scc.backto.web.entities.task.Task;
import com.yunhan.scc.backto.web.entities.task.TodoTaskBacktoVo;
import com.yunhan.scc.backto.web.model.task.TodoTaskBacktoCondition;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：TaskService   
 * 类描述：   待办任务service
 * 创建人：lumin
 * 创建时间：2016-7-28 下午2:34:22   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */

public interface TaskService {

	/**
	 * 
	 * @Description: 获取用户某一采购商关闭品种数量
	 * @param @param purchaserId 采购商id
	 * @param @param userCode 用户code
	 * @param @return
	 * @param @throws Exception   
	 * @return Integer  
	 * @throws
	 * @author lumin
	 * @date 2016-7-28
	 */
	public Integer getCloseOrderItemsCount(String purchaserId,String userCode) throws Exception;
	
	/**
	 * 
	 * @Description: 更新从待办进入查看已关闭品种为已查看，待办清零
	 * @param @param purchaserId 采购商id
	 * @param @param userCode 用户code
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-7-28
	 */
	public void updateOrderItemViewed(String purchaserId,String userCode) throws Exception;
	
	/**
	 * 
	 * @Description: 统计采购商未处理品种数
	 * @param @param purchaserId
	 * @param @param userCode
	 * @param @return
	 * @param @throws Exception   
	 * @return List<Task>  
	 * @throws
	 * @author lumin
	 * @date 2016-7-28
	 */
	public List<Task> getUnDealWareHouse(String purchaserId,String userCode ) throws Exception;
	
	/**
	 * 
	 * @Description: 获取采购商某一仓位具体订单类型未处理品种
	 * @param @param purchaserId 采购商id
	 * @param @param userCode 用户编号
	 * @param @param wareHouse 仓位编码
	 * @param @return
	 * @param @throws Exception   
	 * @return List<Task>  
	 * @throws
	 * @author lumin
	 * @date 2016-7-29
	 */
	public List<Task> getUnDealWareHouseForOrderType(String purchaserId,String userCode,String wareHouse ) throws Exception;
	
	/**
	 * 
	 * @Description: 获取订单回告待办
	 * @param @param taskBacktoCondition
	 * @param @return   
	 * @return List<TodoTaskBacktoVo>  
	 * @throws
	 * @author lumin
	 * @date 2016-8-8
	 */
	public List<TodoTaskBacktoVo> selectTodoTask(TodoTaskBacktoCondition taskBacktoCondition);
	
	/**
	 * 
	 * @Description: 采购商关闭发货品种数统计
	 * @param @param taskBacktoCondition
	 * @param @return   
	 * @return List<TodoTaskBacktoVo>  
	 * @throws
	 * @author lumin
	 * @date 2016-8-8
	 */
	public List<TodoTaskBacktoVo> selectCloseBacktoVos(TodoTaskBacktoCondition taskBacktoCondition);
	
	/**
	 * 
	 * 统计采购商未处理订单数
	 * @author xiongmingbao
	 * @version created at 2016-10-21 上午9:23:43
	 * @param purchaserId
	 * @param userCode
	 * @return List<Task>  
	 * @throws Exception
	 */
	public List<Task> getUnDealWareHouseOrder(String purchaserId,String userCode ) throws Exception;
}

