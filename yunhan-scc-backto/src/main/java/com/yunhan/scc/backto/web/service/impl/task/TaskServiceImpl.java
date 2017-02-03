package com.yunhan.scc.backto.web.service.impl.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhan.scc.backto.web.dao.mapper.system.SystemBacktoDao;
import com.yunhan.scc.backto.web.dao.mapper.task.TaskDao;
import com.yunhan.scc.backto.web.entities.task.Task;
import com.yunhan.scc.backto.web.entities.task.TodoTaskBacktoVo;
import com.yunhan.scc.backto.web.model.task.TodoTaskBacktoCondition;
import com.yunhan.scc.backto.web.service.task.TaskService;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：TaskServiceImpl   
 * 类描述：   待办任务接口实现
 * 创建人：lumin
 * 创建时间：2016-7-28 下午2:37:28   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private SystemBacktoDao systemBacktoDao;
	@Autowired
	private TaskDao taskDao;
	
	@Override
	public Integer getCloseOrderItemsCount(String purchaserId, String userCode)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userCode", userCode);
		params.put("purchaserId", purchaserId);
		return systemBacktoDao.getCloseOrderItemsCount(params);
	}

	@Override
	public void updateOrderItemViewed(String purchaserId, String userCode)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userCode", userCode);
		params.put("purchaserId", purchaserId);
		systemBacktoDao.updateOrderItemViewed(params);
		
	}

	@Override
	public List<Task> getUnDealWareHouse(String purchaserId, String userCode)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userCode", userCode);
		params.put("purchaserId", purchaserId);
		return taskDao.getUnDealWareHouse(params);
	}

	@Override
	public List<Task> getUnDealWareHouseForOrderType(String purchaserId,
			String userCode, String wareHouse) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userCode", userCode);
		params.put("purchaserId", purchaserId);
		params.put("wareHouse", wareHouse);
		return taskDao.getUnDealWareHouseForOrderType(params);
	}

	@Override
	public List<TodoTaskBacktoVo> selectTodoTask(
			TodoTaskBacktoCondition taskBacktoCondition) {
		return taskDao.selectTodoTask(taskBacktoCondition);
	}

	@Override
	public List<TodoTaskBacktoVo> selectCloseBacktoVos(
			TodoTaskBacktoCondition taskBacktoCondition) {
		return taskDao.getCloseOrderItemsCount(taskBacktoCondition);
	}

	@Override
	public List<Task> getUnDealWareHouseOrder(String purchaserId,String userCode) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userCode", userCode);
		params.put("purchaserId", purchaserId);
		return taskDao.getUnDealWareHouseOrder(params);
	}


}

