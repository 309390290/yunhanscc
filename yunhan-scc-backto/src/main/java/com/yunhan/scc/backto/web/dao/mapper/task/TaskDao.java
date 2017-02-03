package com.yunhan.scc.backto.web.dao.mapper.task;

import java.util.List;
import java.util.Map;

import com.yunhan.scc.backto.web.entities.task.Task;
import com.yunhan.scc.backto.web.entities.task.TodoTaskBacktoVo;
import com.yunhan.scc.backto.web.model.task.TodoTaskBacktoCondition;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：TaskDao   
 * 类描述：   
 * 创建人：lumin
 * 创建时间：2016-7-28 下午4:35:35   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */

public interface TaskDao {
	
	/**
	 * 
	 * @Description: 分仓位统计待处理品种数
	 * @param @param params
	 * @param @return
	 * @param @throws Exception   
	 * @return List<Task>  
	 * @throws
	 * @author lumin
	 * @date 2016-7-28
	 */
	public List<Task> getUnDealWareHouse(Map<String, Object> params) throws Exception;

	/**
	 * 
	 * @Description: 获取某一仓位不同类型订单未处理数
	 * @param @param params
	 * @param @return
	 * @param @throws Exception   
	 * @return List<Task>  
	 * @throws
	 * @author lumin
	 * @date 2016-7-28
	 */
	public List<Task> getUnDealWareHouseForOrderType(Map<String, Object> params) throws Exception;
	
	/**
	 * 
	 * @Description: 订单待办统计
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
	 * @Description: 采购商关闭品种数
	 * @param @param taskBacktoCondition
	 * @param @return   
	 * @return List<TodoTaskBacktoVo>  
	 * @throws
	 * @author lumin
	 * @date 2016-8-8
	 */
	public List<TodoTaskBacktoVo> getCloseOrderItemsCount(TodoTaskBacktoCondition taskBacktoCondition);
	
	/**
	 * 
	 *  分仓位统计待处理订单数
	 * @author xiongmingbao
	 * @version created at 2016-10-21 上午9:19:44
	 * @param params
	 * @return List<Task>  
	 * @throws Exception
	 */
	public List<Task> getUnDealWareHouseOrder(Map<String, Object> params) throws Exception;
}

