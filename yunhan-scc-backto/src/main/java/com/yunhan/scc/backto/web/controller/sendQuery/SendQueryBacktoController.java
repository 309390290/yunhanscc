package com.yunhan.scc.backto.web.controller.sendQuery;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunhan.scc.backto.interfaceEntrance.service.sendgoods.DeliveredInterfaceService;
import com.yunhan.scc.backto.web.controller.BaseController;
import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutSummaryBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.SENDOUTNUMBERS;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutItemsBacktoCondition;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutSummaryBacktoCondition;
import com.yunhan.scc.backto.web.service.backreport.ProResponseItemsService;
import com.yunhan.scc.backto.web.service.order.ProPurOrderItemsService;
import com.yunhan.scc.backto.web.service.order.ProPurOrderSummaryService;
import com.yunhan.scc.backto.web.service.sendQuery.ProSendoutItemsBacktoService;
import com.yunhan.scc.backto.web.service.sendQuery.ProSendoutSummaryBacktoService;
import com.yunhan.scc.backto.web.service.sendgoods.SendgoodsService;
import com.yunhan.scc.backto.web.service.system.SystemBacktoService;
import com.yunhan.scc.oauth2.web.entities.security.Department;
import com.yunhan.scc.tools.exception.YhsfServiceException;
import com.yunhan.scc.tools.page.Json;
import com.yunhan.scc.trading.web.entities.system.Operator;
import com.yunhan.scc.trading.web.spring.SessionUser;

/**
 * 发货单查询及修改Controller
 * @author yxp
 * @version created at 2016年7月28日13:52:01
 */
@Controller
@RequestMapping("/backto/sendQuery")
public class SendQueryBacktoController extends BaseController {
 
	/**
	 * 发货单总目service
	 */
	@Autowired
	private ProPurOrderSummaryService proPurOrderSummaryService;
	
	@Autowired
	private ProResponseItemsService proResponseItemsService;
	
	@Autowired
	private ProPurOrderItemsService proPurOrderItemsService;
	@Autowired
	private SendgoodsService sendgoodsService;
	@Autowired
	private ProSendoutSummaryBacktoService  proSendoutSummaryBacktoService;
	@Autowired
	private ProSendoutItemsBacktoService  proSendoutItemsBacktoService;
	
	@Autowired
	private DeliveredInterfaceService deliveredInterfaceService;
	//存储过程跟新状态Service
	@Autowired 
	private SystemBacktoService systemBacktoService;
	
	//@Autowired
	//private Constant constantBacktoService;
	
	private final Logger LOG = LoggerFactory
			.getLogger(SendQueryBacktoController.class);

	/**
	 * 跳转到发货单查询及修改页面
	 * 
	 * @author yxp
	 * @version created at 2016年7月28日14:02:18
	 * @return
	 */
	@RequestMapping("/page/qureyModify")
	public String toQureyModify(HttpServletRequest request) {
		Operator operator = SessionUser.getSessionOperator();
		List<Department> departments = systemBacktoService.selectMyPur(operator.getSoName());
		request.setAttribute("department", departments);
		return "backto/sendQuery/qureyModifyNew";
	}

	 /**	*//**
	 * 跳转到发货单修改页面
	 * 
	 * @author zxc
	 * @version created at 2016-2-19
	 * @return
	 */
	@RequestMapping("/page/modify")
	public String toModify(HttpServletRequest request) {
		String ids = request.getParameter("id");
		if (null != ids && ids.length() > 0) {
//			List<ConstantBacktoDo> constants = constantBacktoService.getConstantsByType("ListAddGoodsPurchaserId");
//			request.setAttribute("constantName",
//					constants.get(0).getValue());
			ProSendoutSummaryBacktoCondition pssc = new ProSendoutSummaryBacktoCondition();
			pssc.setIds(ids);
			List<ProSendoutSummaryBacktoDO> sendSummaryList = sendgoodsService.queryProSendoutSummary(pssc);
			if (null != sendSummaryList && sendSummaryList.size() == 1) {
				String  sendoutGoodsCode = sendSummaryList.get(0).getSendoutGoodsCode();
				if( null!=sendoutGoodsCode && sendoutGoodsCode.length()>0){
				   request.setAttribute("sendSummary", sendSummaryList.get(0));
				   return "backto/sendQuery/modifyNew";
				}else{
				   LOG.error("[TO ——> 发货单修改页面]: 出现发货单号为null！");
				}
			} else {
				LOG.error("[TO ——> 发货单修改页面]: 出现根据ID查询发货单查到多条或者没查到！");
			}
		} else {
			LOG.error("[TO ——> 发货单修改页面]: 获取参数异常！");
		}
		return null;
	}
	
	/**
	 * 跳转到发货单详情页面，包含主发
	 * 
	 * @author zxc
	 * @version created at 2016-2-23
	 * @return
	 */
	@RequestMapping("/page/detail")
	public String toDetail(HttpServletRequest request) {
		String ids = request.getParameter("id");
		//主发标识
		String isInitiative = request.getParameter("isInitiative");
		if (null != ids && ids.length() > 0) {
			ProSendoutSummaryBacktoCondition pssc = new ProSendoutSummaryBacktoCondition();
			pssc.setIds(ids);
			List<ProSendoutSummaryBacktoDO> sendSummaryList = proSendoutSummaryBacktoService
					.queryProSendoutSummary(pssc);
			if (null != sendSummaryList && sendSummaryList.size() == 1) {
				String  sendoutGoodsCode = sendSummaryList.get(0).getSendoutGoodsCode();
				if( null!=sendoutGoodsCode && sendoutGoodsCode.length()>0){
					request.setAttribute("sendSummary", sendSummaryList.get(0));
					if(Objects.equals(isInitiative,"Y")){
						return "backto/sendQuery/mainDetail";
					 }
					return "backto/sendQuery/detailNew";
				}else{
					LOG.error("[TO ——> 发货单详情页面]: 出现发货单号为null！");
				}
			} else {
				LOG.error("[TO ——> 发货单修改页面]: 出现根据ID查询发货单查到多条或者没查到！");
			}
		} else {
			LOG.error("[TO ——> 发货单修改页面]: 获取参数异常！");
		}
		return null;
	}
	
	
	/**
	 * 查询及修改页面--获得发货单是否可以修改或者删除。这里为东莞发货总目ID 获得
	 * @return
	 */
	@RequestMapping("/getProSendSummaryById")
	public void getProSendSummaryById(HttpServletRequest request,
			HttpServletResponse response) {
		String ids = request.getParameter("ids");
		Json result = new Json();
		ProSendoutSummaryBacktoCondition pssc = new ProSendoutSummaryBacktoCondition();
		pssc.setIds(ids);
		try {
			List<ProSendoutSummaryBacktoDO> sendSummaryList = proSendoutSummaryBacktoService
					.queryProSendoutSummary(pssc);
			if (null != sendSummaryList && sendSummaryList.size() == 1) {
				String  sendoutGoodsCode = sendSummaryList.get(0).getSendoutGoodsCode();
			if( null!=sendoutGoodsCode && sendoutGoodsCode.length()>0){
				ProSendoutSummaryBacktoDO proSendSummary=sendSummaryList.get(0);
				result.setObj(proSendSummary);
				result.setSuccess(true);
				}
			}
			
		} catch (Exception e) {
			LOG.error("通过id获取发货单总目出错",e);
			result.setSuccess(false);
			result.setMsg("通过id获取发货单总目出错");
		}
		writeJson(result, response);
	}
	/**
	 * 查询及修改页面--获得发货单是否可以修改或者删除。这里为东莞发货总目ID 获得
	 * @return
	 */
	@RequestMapping("/getRigthUpdateOrDeleteProSendSendItems")
	public void getRigthUpdateOrDeleteProSendSendItems(HttpServletRequest request,
			HttpServletResponse response) {
		String ids = request.getParameter("ids");
		Json result = new Json();
		if (null != ids && ids.length() > 0) {
			ProSendoutSummaryBacktoCondition pssc = new ProSendoutSummaryBacktoCondition();
			pssc.setIds(ids);
			try {
				List<ProSendoutSummaryBacktoDO> sendSummaryList = proSendoutSummaryBacktoService
						.queryProSendoutSummary(pssc);
				
				if (null != sendSummaryList && sendSummaryList.size()>0){
					for(ProSendoutSummaryBacktoDO pssd :sendSummaryList){
						ProSendoutItemsBacktoCondition psic=new ProSendoutItemsBacktoCondition();
						psic.setPurchaserId(pssd.getPurchaserId());
						psic.setSupplierId(pssd.getSupplierId());
						psic.setSendoutGoodsCode(pssd.getSendoutGoodsCode());
						List<ProSendoutItemsBacktoDO> sendItemsList=proSendoutItemsBacktoService.queryProSendoutItems(psic);
						//如果flag=true。本次发货是最后一次发货。暨可以删除或者修改。sendItemsList里面必须包含订单细目id，id。
						SENDOUTNUMBERS sendout =proSendoutItemsBacktoService.getRigthUpdateOrDeleteProSendSendItems(sendItemsList);
						boolean flag = sendout.isLAST();
						result.setObj(flag);
					}
				}
					result.setSuccess(true);
			} catch (Exception e) {
				LOG.error("获得发货单是否可以修改或者删除出错",e);
				result.setSuccess(false);
				result.setMsg("系统繁忙请稍后再试，或直接联系管理员！");
			}
		}
		writeJson(result, response);
	}
	
	/**
	 * 查询及修改页面--删除发货单
	 * @return
	 */
	@RequestMapping("/delSendoutSummary")
	public void delSendoutSummary(HttpServletRequest request,
			HttpServletResponse response) {
		String ids = request.getParameter("ids");
		Json result = new Json();
		if (null != ids && ids.length() > 0) {
			try {
					proSendoutSummaryBacktoService.delSendoutSummary(ids);
					result.setSuccess(true);
					result.setMsg("删除成功！");

			} catch (Exception e) {
				LOG.error("删除发货单出错",e);
				result.setSuccess(false);
				result.setMsg("系统繁忙请稍后再试，或直接联系管理员！");
			}
		}
		writeJson(result, response);
	}
	
	
	/**
	 * 修改发货单 更新回告、发货信息、以及订单差异。
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updateSendoutItems")
	public void detailBySendoutCode(HttpServletRequest request,HttpServletResponse response) {
		Json result = new Json();
		
//		Operator operator = SessionUser.getSessionOperator();
//		if(operator == null){
//			result.setStatusAndMessage(false, "登录超时，请重新登录！");
//			writeJson(result, response);
//			return;
//		}
//		String sapvendorId =  operator.getSapvendorId();//当前登陆者ID
//		
		
		String responseId =  request.getParameter("responseId");
		String sendoutId = request.getParameter("id");
		String sendoutQty = request.getParameter("sendoutQty");
		String otherAvailableReason = request.getParameter("otherAvailableReason");
		String preSendDate = request.getParameter("preSendDate");
		String remark = request.getParameter("remark");
		String sendoutPrice = request.getParameter("sendoutPrice");
		String sendoutDiscountrate = request.getParameter("sendoutDiscountrate");
		String sendoutGoodsCode = request.getParameter("sendoutGoodsCode");
		String supplierId = request.getParameter("supplierId");
		String purchaserId = request.getParameter("purchaserId");
		String proPurOrderItemsId = request.getParameter("proPurOrderItemsId");//订单细目ID
		//发货实体
		ProSendoutItemsBacktoDO proSendoutItemsBacktoDO = new ProSendoutItemsBacktoDO();
		proSendoutItemsBacktoDO.setRemark(remark);
		proSendoutItemsBacktoDO.setId(Long.parseLong(sendoutId));
		proSendoutItemsBacktoDO.setSendoutQty(Integer.parseInt(sendoutQty));
		proSendoutItemsBacktoDO.setSupplierId(supplierId);
		proSendoutItemsBacktoDO.setPurchaserId(purchaserId);
		proSendoutItemsBacktoDO.setSendoutGoodsCode(sendoutGoodsCode);
		proSendoutItemsBacktoDO.setOtherAvailableReason(otherAvailableReason);
		if(sendoutPrice!=null){
			proSendoutItemsBacktoDO.setSendoutPrice(new BigDecimal(sendoutPrice));
		}
		proSendoutItemsBacktoDO.setSendoutDiscountrate(new BigDecimal(sendoutDiscountrate));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//回告实体
			ProResponseItemsBacktoDO proResponse = new ProResponseItemsBacktoDO();
			try {
				if(responseId!=null&&!responseId.equals("null")){
					proResponse.setId(Long.parseLong(responseId));
				}
				
			} catch (NumberFormatException e2) {
				result.setStatusAndMessage(false, "更新回告出错");
				writeJson(result, response);
				return;
			}
			proResponse.setUpdateTime(new Date());
			proResponse.setResponseRemark(remark);
			if(sendoutPrice!=null){
				proResponse.setAvailablePrice(new BigDecimal(sendoutPrice));
			}
			proResponse.setAvailableDiscountrate(new BigDecimal(sendoutDiscountrate));
			proResponse.setThisSendQty(Integer.parseInt(sendoutQty));
			try {
				if(!"".equals(preSendDate)){
					proResponse.setPreSendDate(sdf.parse(preSendDate)); //其余预计发货日期
				}
			} catch (ParseException e1) {
			}
			proResponse.setOtherAvailableReason(otherAvailableReason);
			
			try {
				proSendoutItemsBacktoService.updateSendoutItem(proSendoutItemsBacktoDO, proResponse, proPurOrderItemsId);
				result.setSuccess(true);
				result.setStatusAndMessage(true, "保存成功");
			} catch (Exception e) {
				LOG.error("保存发货单出错",e);
				result.setSuccess(false);
				result.setMsg("操作失败，请稍后再试！或直接联系管理人员！");
			}
		writeJson(result, response);
	}
	
	/**
	 * 是否是最后一次发货回告？
	 * @param request
	 * @param response
	 */
	@RequestMapping("/isLastResone")
	public void isLastResone(HttpServletRequest request,HttpServletResponse response) {
		Json result = new Json();
		String sendoutId = request.getParameter("id");//发货单ID
		String proPurOrderItemsId = request.getParameter("proPurOrderItemsId");
		try {
			Map<String,Boolean> map = new HashMap<String, Boolean>();
			if(proPurOrderItemsId==null||proPurOrderItemsId.equals("null")){
				map.put("last",true);
			}else{
				ProSendoutItemsBacktoDO p = new ProSendoutItemsBacktoDO();
				p.setId(Long.parseLong(sendoutId));
				p.setProPurOrderItemsId(Long.parseLong(proPurOrderItemsId));
				List<ProSendoutItemsBacktoDO> sendItemsList = new ArrayList<>();
				sendItemsList.add(p);
				SENDOUTNUMBERS sendoutnumbers = proSendoutItemsBacktoService.getRigthUpdateOrDeleteProSendSendItems(sendItemsList);
				map.put("frist", sendoutnumbers.isFRIST());
				map.put("last",sendoutnumbers.isLAST());
			}
			result.setObj(map);
		} catch (NumberFormatException e) {
			result.setStatusAndMessage(false, "查询出现异常");
		}
		result.setStatusAndMessage(true, "查询成功");
		writeJson(result, response);
	}
	/**
	 * 修改页面--删除发货单
	 * @param id  发货单id
	 * @return
	 */
	@RequestMapping("/delSendoutItems")
	public void delSendoutItems(Long id,HttpServletRequest request,
			HttpServletResponse response) {
		Json result = new Json();
		if (null != id) {
			try {
			   //删除发货单
				proSendoutItemsBacktoService.delSendoutItemsById(id);
				result.setStatusAndMessage(true, "删除成功！");
			} catch (Exception e) {
				LOG.error("删除发货单出错",e);
				result.setSuccess(false);
				result.setMsg(e.getMessage());
			}
		}
		writeJson(result, response);
    }
	/**
	 * 修改页面--确认修改发货单
	 * @return
	 */
	@RequestMapping("/saveSendSummaryItems")
	public void saveSendSummaryItems(ProSendoutSummaryBacktoDO pssc,HttpServletRequest request,HttpServletResponse response) {
		   Json result = new Json();
			try {
				List<ProSendoutItemsBacktoDO> infos = pssc.getSendOutItems();
				for(ProSendoutItemsBacktoDO p : infos){
					ProResponseItemsBacktoDO proResponse=new ProResponseItemsBacktoDO();
					proResponse.setSupplierId(proResponse.getSupplierId());
					proResponse.setPurchaserId(proResponse.getPurchaserId());
					if(p.getResponseId()!=null){
						proResponse.setId(Long.valueOf(p.getResponseId()));
					}
					proResponse.setProSendoutItemsId(p.getId());
					proResponse.setUpdateTime(new Date());
					proResponse.setResponseRemark(p.getRemark());
					proResponse.setAvailablePrice(p.getSendoutPrice());
					proResponse.setAvailableDiscountrate(p.getSendoutDiscountrate());
					proResponse.setThisSendQty(p.getSendoutQty());
					proResponse.setOtherAvailableReason(p.getOtherAvailableReason());
					String proPurOrderItemsId="";
					if(p.getProPurOrderItemsId()!=null){
						 proPurOrderItemsId=p.getProPurOrderItemsId().toString();
					}
					
					proSendoutItemsBacktoService.updateSendoutItem(p,proResponse,proPurOrderItemsId);
				}
				
				proSendoutSummaryBacktoService.updateSendoutSummary(pssc);
				
//				for(ProSendoutItemsBacktoCondition proSendoutItemsBacktoCondition : infos){
//					proSendoutItemsBacktoCondition.getSendoutDiscountrate();
//				}
				
//				for (int i = 0; i < infos.size(); i++) {
//					infos.get(i).setSupplierId(pssc.getSupplierId());
//					infos.get(i).setPurchaserId(pssc.getPurchaserId());
//					if(infos.get(i).getId()==null){
//						infos.get(i).setId(0L);
//					}
//				}
//				pssc.setSendOutItems(infos);
//				para.add(pssc);
//				
//				List<ProSendoutSummaryBacktoDo> backto = proSendoutSummaryBacktoService.queryProSendoutSummary(pssc);
//				List<ProSendoutSummaryBacktoDo> backtoDos = proSendoutSummaryBacktoService.findSendOutSummaryBySendoutGoodsCode(pssc);
//				if(backtoDos==null&&backtoDos.size()<=0){
//					backtoService.saveProSendoutSummaryAndProSendoutItemsCodeIsTimeGo(para);
//					result.setStatusAndMessage(true, "修改发货单成功！");
//				}
//				//edit by yangtao 修改if判断标准
//				if(backtoDos==null||backtoDos.size()<=0){
//					backtoService.saveProSendoutSummaryAndProSendoutItemsCodeIsTimeGo(para,1);
//					result.setStatusAndMessage(true, "修改发货单成功！");
//				}
//				else{
//					if(backto!=null && backto.size()>0&&backto.get(0).getSendoutGoodsCode().equals(backtoDos.get(0).getSendoutGoodsCode())){
//						backtoService.saveProSendoutSummaryAndProSendoutItemsCodeIsTimeGo(para,1);
//						result.setStatusAndMessage(true, "修改发货单成功！");
//					}else{
//						result.setStatusAndMessage(false, "此发货单号已存在，不可重复！");
//					}
//				}
				result.setSuccess(true);
				result.setMsg("修改成功！");
			} catch (Exception e) {
				LOG.error("修改发货单出错！",e);
				result.setSuccess(false);
				result.setMsg("修改发货单出错，请稍后再试！");
			}
		writeJson(result, response);
     }
	
	
	/*//**
	 * 获取发货单总目
	 * @author luohoudong
	 * @version created at 2016-3-14 下午2:16:49
	 * @param pssc
	 * @param request
	 * @param response
	 *//*
	@RequestMapping("/getSendSummary")
	public void getSendSummary(HttpServletRequest request,HttpServletResponse response) {
		   Json result = new Json();
			try {
			   String sendOutGoodsCode = request.getParameter("sendOutGoodsCode"); //发货单号
			   if(sendOutGoodsCode==null || "".equals(sendOutGoodsCode)){
				   result.setSuccess(false);
				   result.setMsg("传入的发货单号为空!");
			   }else{
				   ProSendoutSummaryBacktoDo backtoDo=proSendoutSummaryBacktoService.getProSendoutSummary_T(sendOutGoodsCode);
				   result.setSuccess(true);
				   result.setObj(backtoDo);
			   }
			} catch (Exception e) {
				LOG.error("获取发货单总目出错！",e);
				result.setSuccess(false);
				result.setMsg("获取发货单总目出错");
			}
		writeJson(result, response);
     }
	
	*//**
	 * 根据临时发货单细目id删除临时发货单细目
	 * @author luohoudong
	 * @version created at 2016-3-15 下午2:15:49
	 * @param request
	 * @param response
	 *//*
	@RequestMapping("/deleteSendoutItems_T")
	public void deleteSendoutItems_T(HttpServletRequest request,
			HttpServletResponse response) {
		Json result = new Json();
		String itemsId = request.getParameter("itemsId"); //临时发货单细目id
		if(itemsId==null || "".equals(itemsId)){
			throw new ServiceException("要删除的临时发货单细目id不能为空!");
		}
		try {
			proSendoutItemsBacktoService.deleteProSendoutItems_T(Long.valueOf(itemsId));
			result.setSuccess(true);
		} catch (YhsfServiceException e) {
			LOG.error("删除临时发货单细目出错！",e);
			result.setSuccess(false);
			result.setMsg("删除临时发货单细目出错！");
		}
		writeJson(result, response);
     }
	*/
	/**
	 * 通过id获得发货单（部分收货，全部收货）
	 * @author yxp
	 * @version created at 2016-4-11 18:54:23
	 * @param request
	 * @param response
	 */
	@RequestMapping("/findSendOutSummaryById")
	public void findSendOutSummaryById(HttpServletRequest request,
			HttpServletResponse response) {
		Json result = new Json();
		try {
		String id = request.getParameter("ids"); //
		String[] ids=id.split(",");
		int count=0;
		for (int i = 0; i < ids.length; i++) {
			ProSendoutSummaryBacktoCondition pssc=new ProSendoutSummaryBacktoCondition();
			pssc.setId(Long.valueOf(ids[i]));
			List<ProSendoutSummaryBacktoDO> list=proSendoutSummaryBacktoService.findSendOutSummaryById(pssc);
			if(list.size()>0){
				count++;
			}
		}
		if(count>0){
			//如果count>0，表面存在
			result.setMsg("0");
		}else{
			result.setMsg("1");
		}
			result.setSuccess(true);
		} catch (YhsfServiceException e) {
			LOG.error("通过id获得发货单出错！",e);
			result.setSuccess(false);
			result.setMsg("3");
		}
		writeJson(result, response);
     }
	
	
	
	
	
}