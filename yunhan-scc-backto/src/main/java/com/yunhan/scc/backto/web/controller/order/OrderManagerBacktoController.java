package com.yunhan.scc.backto.web.controller.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yunhan.scc.backto.web.controller.BaseController;
import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.order.ProPurOrderItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.order.ProPurOrderSummaryBacktoDO;
import com.yunhan.scc.backto.web.model.order.ProPurOrderItemsBacktoCondition;
import com.yunhan.scc.backto.web.service.backreport.ProResponseItemsService;
import com.yunhan.scc.backto.web.service.order.ProPurOrderItemsService;
import com.yunhan.scc.backto.web.service.order.ProPurOrderSummaryService;
import com.yunhan.scc.backto.web.service.system.SystemBacktoService;
import com.yunhan.scc.oauth2.web.entities.security.Department;
import com.yunhan.scc.tools.page.Json;
import com.yunhan.scc.tools.util.StringUtils;
import com.yunhan.scc.trading.web.entities.system.Operator;
import com.yunhan.scc.trading.web.spring.SessionUser;
/**
 * 订单处理控制器
 * @author wangtao
 *	2016年7月14日11:17:11
 */
@Controller
@RequestMapping("/backto/order")
public class OrderManagerBacktoController extends BaseController{
	private Log log = LogFactory.getLog(OrderManagerBacktoController.class);
	
	@Autowired
	private final SystemBacktoService  systemBacktoService = null ;
	@Autowired
	private ProPurOrderSummaryService orderSummaryService;
	@Autowired
	private ProResponseItemsService proResponseItemsService;
	@Autowired 
	private ProPurOrderItemsService proPurOrderItemsService;
	
	
	/**
	 * 
	 * @Description: 订单回告-订单方式
	 * @param @param request
	 * @param @param response
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lumin
	 * @date 2016-7-13
	 */
	@RequestMapping("/page/toOrderProcessing")
	public ModelAndView toOrderSummary(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/backto/order/orderProcessingNew");
		String purchaserId = request.getParameter("purchaserId");  //采购商id
		String orderType = request.getParameter("orderType");
		String wareHouse = request.getParameter("wareHouse");
		String isDeal = request.getParameter("isDeal");//订单处理情况
		Operator operator = SessionUser.getSessionOperator();
		List<Department> departments = systemBacktoService.selectMyPur(operator.getSoName());
		request.setAttribute("department", departments);
		//采购商id
		if(StringUtils.isNotBlank(purchaserId)){
			request.setAttribute("purchaserId", purchaserId);
		}
		//直供订单
//		if(StringUtils.isNotBlank(wareHouse) && wareHouse.equals("ZG")){
			request.setAttribute("wareHouse", wareHouse);
//		}
		//订单种类
		if(StringUtils.isNotBlank(orderType)){
			request.setAttribute("orderType", orderType);
		}
		if(StringUtils.isNotBlank(isDeal)){
			modelAndView.addObject("isDeal",isDeal);
		}
		return modelAndView;
	}
	
	/**
	 * 
	 * @Description: 获取一个月前待处理品种数
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception   
	 * @return String  
	 * @throws
	 * @author lumin
	 * @date 2016-7-15
	 */
	@RequestMapping("/json/getUntreatedMonthAgo")
	public @ResponseBody void getUntreatedMonthAgo(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Json json = new Json();
		try {
			String purchaserId = request.getParameter("purchaserId");
			String sendDateEnd = request.getParameter("sendDateEnd");
			Operator operator = SessionUser.getSessionOperator();
			Integer countInteger = orderSummaryService.getUntreatedMonthAgo(purchaserId, sendDateEnd, operator.getSoName());
			json.setObj(countInteger);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setObj(0);
			json.setSuccess(false);
			json.setMsg("待处理品种统计后台异常");
		}
		writeJson(json, response);
	}
	
	/**
	 * 订单详情处理
	 * 2016年7月14日16:38:53
	 */
	@RequestMapping("/page/toOrderDetailsProcessing")
	public ModelAndView toOrderDetailsProcessing(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView modelAndView = new ModelAndView("/backto/order/orderDetailsProcessingNew");
		//获取订单数据和采购商id
		String purchaserId = request.getParameter("purchaserId");
		String id = request.getParameter("id");
		String isDeal = request.getParameter("isDeal");
		String type = request.getParameter("type");
		modelAndView.addObject("requestType", type);
		//更新订单总目供应商查阅标识
		orderSummaryService.updateOrderSummaryIsView(Integer.valueOf(id), null, null);
		//查询订单总目
		ProPurOrderSummaryBacktoDO proPurOrderSummaryDO = orderSummaryService.getProPurOrderSummaryDO(Long.valueOf(id), purchaserId);
		modelAndView.addObject("summary", proPurOrderSummaryDO);
		if(StringUtils.isNotBlank(isDeal)){
			modelAndView.addObject("isDeal",isDeal);
		}
		return modelAndView;
	}
	/**
	 * 订单处理-品种方式
	 * @author wangtao
	 * 2016年7月14日16:46:47
	 */
	@RequestMapping("/page/toOrderItems")
	public ModelAndView toOrderItems(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/backto/order/orderItemsProcessingNew");
		Operator operator = SessionUser.getSessionOperator();
		String purchaserId = request.getParameter("purchaserId");
		String orderCodes = request.getParameter("orderCodes");
		String isDeal=request.getParameter("isDeal");//商品有效性
		String wareHouse=request.getParameter("wareHouse");//仓位
		String sendGoodsType=request.getParameter("sendGoodsType");
		String isValid=request.getParameter("isValid");
		String orderType=request.getParameter("orderType");
		String controlFlag=request.getParameter("controlFlag");
		String otherReason=request.getParameter("otherReason");
		List<Department> departments = systemBacktoService.selectMyPur(operator.getSoName());
		request.setAttribute("department", departments);
		request.setAttribute("purchaserId", purchaserId);
		request.setAttribute("orderCodes", orderCodes);
		request.setAttribute("isDeal", isDeal);
		request.setAttribute("wareHouse", wareHouse);
		request.setAttribute("sendGoodsType", sendGoodsType);
		request.setAttribute("isValid", isValid);
		request.setAttribute("orderType", orderType);
		request.setAttribute("controlFlag", controlFlag);
		request.setAttribute("otherReason", otherReason);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Description: 订单方式-订单详情-关闭发货
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-7-20
	 */
	@RequestMapping("/json/saveCloseSendOutGoods")
	public @ResponseBody void saveCloseSendOutGoods(HttpServletRequest request, HttpServletResponse response)throws Exception{
		Json json = new Json();
		try {
			String ids = request.getParameter("ids");
			String notGoodsReason = request.getParameter("notGoodsReason");
			String purchaseOrderCode = request.getParameter("purchaseOrderCode");
			String purchaserId = request.getParameter("purchaserId");
			if(StringUtils.isEmpty(ids)){
				json.setSuccess(false);
				json.setMsg("获取数据失败");
			}else{
				//获取订单数据
				List<ProPurOrderItemsBacktoDO> itemsBacktoDOs = 
						proPurOrderItemsService.getProPurOrderItemsBacktoDOs(purchaseOrderCode, purchaserId);
				proResponseItemsService.saveProResponseItemForCloseSend(ids, notGoodsReason,itemsBacktoDOs);
				json.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			json.setSuccess(false);
			json.setMsg("关闭发货后台服务器异常!");
		}
		writeJson(json, response);
	}
	
	
	/**
	 * 
	 * @Description: 订单处理-品种方式-关闭发货
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-7-20
	 */
	@RequestMapping("/json/saveCloseSendOutGoodPz")
	public @ResponseBody void saveCloseSendOutGoodPz(HttpServletRequest request, HttpServletResponse response)throws Exception{
		Json json = new Json();
		try {
			String ids = request.getParameter("ids");
			String notGoodsReason = request.getParameter("notGoodsReason");
			if(StringUtils.isEmpty(ids)){
				json.setSuccess(false);
				json.setMsg("获取数据失败");
			}else{
				//获取订单数据
				//List<ProPurOrderItemsBacktoDO> itemsBacktoDOs = 
				//		proPurOrderItemsService.getProPurOrderItemsBacktoDOs(purchaseOrderCode, purchaserId);
				ProPurOrderItemsBacktoCondition condition=new ProPurOrderItemsBacktoCondition();
				condition.setIds(ids);
				List<ProPurOrderItemsBacktoDO> itemsBacktoDOs = 
						proPurOrderItemsService.findItemByIds(condition);
				proResponseItemsService.saveProResponseItemForCloseSend(ids, notGoodsReason,itemsBacktoDOs);
				json.setSuccess(true);
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("关闭发货后台服务器异常!");
			log.error("品种方式-关闭发货异常",e);
		}
		writeJson(json, response);
	}
	
	
	/**
	 * 
	 * @Description: 回告保存
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-7-22
	 */
	@RequestMapping("/json/saveResponse")
	public @ResponseBody void saveResponse(HttpServletRequest request, HttpServletResponse response)throws Exception{
		Json json = new Json();
		String ids="";
		try {
			Operator operator = SessionUser.getSessionOperator();
			if(null == operator){
				json.setSuccess(false);
				json.setMsg("用户登陆失败，请重新登陆！");
			}else{
				String saveSend = request.getParameter("saveSend");
				String type = request.getParameter("type");//type标识是回告暂存还是回告发货，0为回告发货
				List<ProResponseItemsBacktoDO> SendBacktoDOs = JSONObject.parseArray(saveSend,ProResponseItemsBacktoDO.class);
				//回告发货
				if(type!=null && "0".equals(type)){
					//更新订单的查阅标识为已查阅
					orderSummaryService.updateOrderSummaryIsView(null, null, SendBacktoDOs);
					
					 ids = proResponseItemsService.batchSaveResponse(SendBacktoDOs,operator.getSoName(),"PAGE");
				
				}else{//只回告暂存
					proResponseItemsService.saveTempResponse(SendBacktoDOs, operator.getSoName());
				}
				json.setObj(ids);
				json.setSuccess(true);
				
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("保存数据后台服务器异常");
			log.error("保存数据后台服务器异常",e);
		}
		writeJson(json, response);
	}

}
