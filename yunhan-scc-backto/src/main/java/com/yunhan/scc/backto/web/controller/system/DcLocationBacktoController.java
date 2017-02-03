package com.yunhan.scc.backto.web.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunhan.scc.backto.web.controller.BaseController;
import com.yunhan.scc.backto.web.controller.order.OrderManagerBacktoController;
import com.yunhan.scc.backto.web.entities.system.DcLocationBacktoDo;
import com.yunhan.scc.backto.web.service.order.ProPurOrderSummaryService;
import com.yunhan.scc.backto.web.service.system.DcLocationBacktoService;
import com.yunhan.scc.backto.web.service.system.SystemBacktoService;
import com.yunhan.scc.tools.json.JSONArray;
import com.yunhan.scc.tools.page.Json;
import com.yunhan.scc.trading.web.entities.system.Operator;
import com.yunhan.scc.trading.web.spring.SessionUser;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：DcLocationBacktoController   
 * 类描述：   
 * 创建人：zzp
 * 创建时间：2016-7-14 下午5:07:45   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */
@Controller
@RequestMapping("/backto/system")
public class DcLocationBacktoController extends BaseController{
	private Log log = LogFactory.getLog(OrderManagerBacktoController.class);
	@Autowired
	private final DcLocationBacktoService dcLocationBacktoService = null;
	@Autowired
	private SystemBacktoService systemBacktoService;
	@Autowired
	private ProPurOrderSummaryService orderSummaryService;
	/**
	 * 
	 * @Description: 获取采购商仓位
	 * @param @param purId
	 * @param @return
	 * @param @throws Exception   
	 * @return String  
	 * @throws
	 * @author lumin
	 * @date 2016-7-13
	 */
	@RequestMapping(value = "/json/getDC", method = RequestMethod.POST)
	public @ResponseBody String getDC(@RequestParam("purId") String purId) throws Exception {
		List<DcLocationBacktoDo> dcs = dcLocationBacktoService.getDcLocationByPurchaserId(purId);
		JSONArray json = new JSONArray(dcs);
		return json.toString();
	}
	
	/**
	 * 
	 * @Description: 修改导出状态（已导出）
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author zwj
	 * @date 2016-7-22
	 */
	@RequestMapping("/json/updateExportState")
	public  void updateExportState(HttpServletRequest request, HttpServletResponse response)throws Exception{
		Json json = new Json();
		try {
				//订单细目ids（多个用逗号隔开）
				String orderItemsIds = request.getParameter("orderItemsIds");
				//更新订单查阅标识为已查阅
				orderSummaryService.updateOrderSummaryIsView(null, orderItemsIds, null);
				systemBacktoService.updateExportState(orderItemsIds,null);
				json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修改订单细目导出标识异常",e);
			json.setSuccess(false);
			//json.setMsg(e.toString());
		}
		writeJson(json, response);
	}
	
	/**
	 * 
	 * @Description: 修改导出状态（已导出）
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author zwj
	 * @date 2016-7-22
	 */
	@RequestMapping("/json/updateOrderExportState")
	public  void updateOrderExportState(HttpServletRequest request, HttpServletResponse response)throws Exception{
		Json json = new Json();
		try {
				//订单细目ids（多个用逗号隔开）
				String orderIds = request.getParameter("orderIds");
				//更新订单查阅标识为已查阅
				orderSummaryService.updateOrderSummaryIsView(orderIds);
				systemBacktoService.updateExportState(orderIds,"orderSummary");
				json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修改订单总目导出标识异常",e);
			json.setSuccess(false);
		}
		writeJson(json, response);
	}
	
	/**
	 * 
	 * @Description: 获取仓位和待处理品种数
	 * @param @param purId
	 * @param @return
	 * @param @throws Exception   
	 * @return String  
	 * @throws
	 * @author lumin
	 * @date 2016-8-25
	 */
	@RequestMapping(value = "/json/getDCAndCount", method = RequestMethod.POST)
	public @ResponseBody String getDCAndCount(@RequestParam("purId") String purId) throws Exception {
		Operator operator = SessionUser.getSessionOperator();
		List<DcLocationBacktoDo> dcs = dcLocationBacktoService.getDcLocationsAndItmesCount(purId,operator.getSoName());
		JSONArray json = new JSONArray(dcs);
		return json.toString();
	}
	
	/**
	 * 获取仓位和待处理订单数
	 * @author luohoudong
	 * @version created at 2016-9-26 下午1:19:44
	 * @param purId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/json/getDCAndOrderCount", method = RequestMethod.POST)
	public @ResponseBody String getDCAndOrderCount(@RequestParam("purId") String purId) throws Exception {
		Operator operator = SessionUser.getSessionOperator();
		List<DcLocationBacktoDo> dcs = dcLocationBacktoService.getDcLocationsAndOrderCount(purId,operator.getSoName());
		JSONArray json = new JSONArray(dcs);
		return json.toString();
	}
	
	
}

