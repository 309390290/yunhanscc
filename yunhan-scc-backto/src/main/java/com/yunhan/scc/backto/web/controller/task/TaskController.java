package com.yunhan.scc.backto.web.controller.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.util.NewBeanInstanceStrategy;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yunhan.scc.backto.web.controller.BaseController;
import com.yunhan.scc.backto.web.entities.system.ConfigParameter;
import com.yunhan.scc.backto.web.entities.system.ConstantBacktoDo;
import com.yunhan.scc.backto.web.entities.task.Task;
import com.yunhan.scc.backto.web.entities.task.TodoTaskBacktoVo;
import com.yunhan.scc.backto.web.model.system.ConfigParameterCondition;
import com.yunhan.scc.backto.web.model.task.TodoTaskBacktoCondition;
import com.yunhan.scc.backto.web.service.system.ConfigParameterService;
import com.yunhan.scc.backto.web.service.system.ConstantBacktoService;
import com.yunhan.scc.backto.web.service.system.SystemBacktoService;
import com.yunhan.scc.backto.web.service.task.TaskService;
import com.yunhan.scc.oauth2.web.entities.security.Department;
import com.yunhan.scc.tools.constant.ConstantTypeCode;
import com.yunhan.scc.tools.page.Json;
import com.yunhan.scc.tools.page.TaskInfModel;
import com.yunhan.scc.trading.web.entities.system.Operator;
import com.yunhan.scc.trading.web.spring.SessionUser;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：TaskController   
 * 类描述：   待办工作控制器
 * 创建人：lumin
 * 创建时间：2016-7-28 上午11:02:39   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */
@Controller
@RequestMapping("/backto/task")
public class TaskController extends BaseController{

	private Log log = LogFactory.getLog(TaskController.class);
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private SystemBacktoService systemBacktoService;
	@Autowired
	private ConstantBacktoService constantBacktoService;
	@Autowired
	private ConfigParameterService configParameterService;
	
	
	/**
	 * 
	 * @Description: 跳转到订单待办页面
	 * @param @param request
	 * @param @param response
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author lumin
	 * @date 2016-7-28
	 */
	@RequestMapping("/page/tasklist")
	public ModelAndView toTaskList(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		ModelAndView modelAndView = new ModelAndView("/task/tasklist");
		String purchaserId = request.getParameter("purchaserId");
		//获取商家信息
		Department department = systemBacktoService.getDepartmentByPurchaserId(purchaserId);
		modelAndView.addObject("department", department);
		//获取采购商关闭订单商品数
		Operator operator = SessionUser.getSessionOperator();
		Integer count = taskService.getCloseOrderItemsCount(purchaserId, operator.getSoName());
		modelAndView.addObject("userCode", operator.getSoName());
		modelAndView.addObject("closeCount", count);
		TodoTaskBacktoCondition td = new TodoTaskBacktoCondition();
		td.setUserCode(operator.getSoName());
		td.setSupplierId(operator.getSapvendorId());
		td.setPurchaserId(purchaserId);
		List<TodoTaskBacktoVo> closeOrderItemsCount = taskService.selectCloseBacktoVos(td);
		if(closeOrderItemsCount.size()>0){
			modelAndView.addObject("closeOrderCodes", closeOrderItemsCount.get(0).getPurchseOrderCodes());
		}
		//获取各个仓位的待处理品种数
		List<Task> tasks = taskService.getUnDealWareHouse(purchaserId, operator.getSoName());
		modelAndView.addObject("tasks", tasks);
		Integer total = 0;
		if(tasks!=null && tasks.size()>0){
			for (Task task : tasks) {
				total +=task.getUnDealCount();
			}
		}
		modelAndView.addObject("total", total);
		//获取各个仓位的待处理订单数
		List<Task> orderTasks = taskService.getUnDealWareHouseOrder(purchaserId, operator.getSoName());
		modelAndView.addObject("orderTasks", orderTasks);
		Integer orderTotal = 0;
		if(orderTasks!=null && orderTasks.size()>0){
			for (Task task : orderTasks) {
				orderTotal +=task.getUnDealCount();
			}
		}
		modelAndView.addObject("orderTotal", orderTotal);
		//按什么方式统计-订单方式，品种方式 A-品种方式 B-订单方式
		String statisticalDimension = "A";
		ConfigParameterCondition condition = new ConfigParameterCondition();
		condition.setUserCode(operator.getSoName());
		condition.setParameterCode1(ConstantTypeCode.BACKTO_TASK_STATISTICS_DIMENSION);//参数编码
		condition.setIsValid("Y");
		List<ConfigParameter> configParameterList = configParameterService.findConfigParameters(condition);
		if (configParameterList.size()>0) {
			statisticalDimension = configParameterList.get(0).getParameterValue1();
		}
		modelAndView.addObject("statisticalDimension", statisticalDimension);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Description: 获取具体某一仓位的待处理品种数
	 * @param @param request
	 * @param @param response   
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-7-29
	 */
	@RequestMapping("/json/getUnDealWareHouse")
	public @ResponseBody void getUnDealWareHouseForOrderType(HttpServletRequest request,
			HttpServletResponse response){
		Json json = new Json();
		try {
			String purchaserId = request.getParameter("purchaserId");
			String userCode = request.getParameter("userCode");
			String wareHouse = request.getParameter("wareHouse");
			List<Task> tasks = taskService.getUnDealWareHouseForOrderType(purchaserId, userCode, wareHouse);
			json.setSuccess(true);
			json.setObj(tasks);
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "获取待处理品种数量后台异常:"+e.toString();
			log.info(msg);
			json.setSuccess(false);
			json.setMsg(msg);
		}
		writeJson(json, response);
	}
	
	
	/**
	 * 
	 * @Description: 采购商关闭订单商品数清零
	 * @param @param request
	 * @param @param response   
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-7-28
	 */
	@RequestMapping("/json/updateOrderItemViewed")
	public @ResponseBody void updateOrderItemViewed(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		Json json = new Json();
		try {
			//获取采购商id
			String purchaserId = request.getParameter("purchaserId");
			String userCode = request.getParameter("userCode");
			taskService.updateOrderItemViewed(purchaserId, userCode);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("更新从待办进入查看已关闭品种为已查看，待办清零出现异常:"+e.toString());
			json.setSuccess(false);
			json.setMsg("服务器后台异常");
		}
		writeJson(json, response);
	}
	
	/**
	 * 
	 * @Description: 获取首页订单回告待办
	 * @param @param request
	 * @param @param response   
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-8-8
	 */
	@RequestMapping("/backToStatisticsForJson")
	public void getOrderTask(HttpServletRequest request,HttpServletResponse response) {
		Json json = new Json();
		/*String basePath = request.getScheme() + "://" + request.getServerName();
		log.info("=======回告待办域名：====>>>>" + basePath);*/
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = request.getParameter("msg");
		if (StringUtils.isEmpty(msg)) {
			json.setSuccess(false);
			json.setMsg("参数获取异常");
		} else {
			try {
				//取回告的待回告待办统计
				/*TodoTaskBacktoCondition td = new TodoTaskBacktoCondition();
				td.setSupplierId("0002200059");
				td.setUserCode("2200059PQ");*/
				
				TodoTaskBacktoCondition td = JSONObject.parseObject(msg, TodoTaskBacktoCondition.class);
				//获取调用URL
				ConstantBacktoDo backtoDo = constantBacktoService.getConstantByTypeAndCode("DYURL", "RESPONSETASK_URL");
				if(backtoDo==null){
					throw new NullPointerException("未获取到回告地址url常数，请查看是否进行配置【DYURL】的常数类型【RESPONSETASK_URL】的常数");
				}
				List<TodoTaskBacktoVo> todoTaskBacktoVos = taskService.selectTodoTask(td);
				if (CollectionUtils.isNotEmpty(todoTaskBacktoVos)) {
					List<TaskInfModel> taskInfModelList = new ArrayList<TaskInfModel>();
					for (TodoTaskBacktoVo tBacktoVo : todoTaskBacktoVos) {
						TaskInfModel taskInfModel = new TaskInfModel();
						taskInfModel.setTaskName("待处理品种<span class='fontRed'>" + tBacktoVo.getDbs() + "</span> 个！");
						taskInfModel.setUrl(backtoDo.getConsValue()+"/backto/task/page/tasklist?purchaserId="+tBacktoVo.getPurchaserId());
						taskInfModel.setPurchaserId(tBacktoVo.getPurchaserId());
						taskInfModel.setPurchaserName(tBacktoVo.getPurchaserNm());
						taskInfModelList.add(taskInfModel);
						
					}
					map.put("todoTaskBacktoVos", taskInfModelList);
				}
				//取采购商已关闭的订单品种代办统计
				List<TodoTaskBacktoVo> closeOrderItemsCount = taskService.selectCloseBacktoVos(td);
				if(CollectionUtils.isNotEmpty(todoTaskBacktoVos)){
					List<TaskInfModel> taskInfModelList = new ArrayList<TaskInfModel>();
					for (TodoTaskBacktoVo closeOrder : closeOrderItemsCount) {
						if(StringUtils.isNotBlank(closeOrder.getDbs())){
							TaskInfModel taskInfModel = new TaskInfModel();
							taskInfModel.setTaskName("已关闭的采购订单品种<span class='fontRed'>" + closeOrder.getDbs() + "</span> 个！");
							taskInfModel.setUrl(backtoDo.getConsValue()+"/backto/order/page/toOrderItems?purchaserId="+closeOrder.getPurchaserId()+"&orderCodes="+closeOrder.getPurchseOrderCodes()+"&isDeal=Y&isValid=N");
							taskInfModel.setPurchaserId(closeOrder.getPurchaserId());
							taskInfModel.setPurchaserName(closeOrder.getPurchaserNm());
							taskInfModelList.add(taskInfModel);
						}
					}
					if(taskInfModelList.size()>0)
						map.put("closeProductNum", taskInfModelList);
				}
				json.setSuccess(true);
				json.setObj(map);
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("订单待办获取异常");
			}
		}
		writeJson(json, response);
	}
	
	/**
	 * 
	 * 保存用户选择的统计维度
	 * @author xiongmingbao
	 * @version created at 2016-10-24 上午10:42:33
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/json/updateStatisticalDimension")
	public @ResponseBody void updateStatisticalDimension(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		Json json = new Json();
		try {
			Operator operator = SessionUser.getSessionOperator();
			String userCode = operator.getSoName();
			String statisticalDimension = request.getParameter("statisticalDimension");
			ConfigParameterCondition condition = new ConfigParameterCondition();
			condition.setUserCode(userCode);
			condition.setParameterCode1(ConstantTypeCode.BACKTO_TASK_STATISTICS_DIMENSION);//参数编码
			condition.setIsValid("Y");
			List<ConfigParameter> configParameterList = configParameterService.findConfigParameters(condition);
			if (configParameterList.size() >0) {
				//更新
				ConfigParameter configParameter = new ConfigParameter();
				configParameter.setId(configParameterList.get(0).getId());
				configParameter.setParameterValue1(statisticalDimension);
				configParameterService.updateConfigParameter(configParameter);
			}else{
				//新增
				ConfigParameter configParameter = new ConfigParameter();
				configParameter.setUserCode(userCode);
				configParameter.setIsValid("Y");
				configParameter.setParameterCode1(ConstantTypeCode.BACKTO_TASK_STATISTICS_DIMENSION);
				configParameter.setParameterName1("回告待办中间页统计维度");
				configParameter.setParameterValue1(statisticalDimension);
				configParameterService.saveConfigParameter(configParameter);
			}
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("修改待办中间页统计方式出现异常:"+e.toString());
			json.setSuccess(false);
			json.setMsg("服务器后台异常");
		}
		writeJson(json, response);
	}
	
}

