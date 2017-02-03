package com.yunhan.scc.backto.web.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.yunhan.scc.backto.web.controller.BaseController;
import com.yunhan.scc.backto.web.entities.system.SendRuleConfigDo;
import com.yunhan.scc.backto.web.model.system.SendRuleConfigCondition;
import com.yunhan.scc.backto.web.service.system.DepartmentBacktoService;
import com.yunhan.scc.backto.web.service.system.SendRuleConfigService;
import com.yunhan.scc.tools.json.JSONArray;
import com.yunhan.scc.tools.json.JSONObject;
import com.yunhan.scc.tools.page.Json;
import com.yunhan.scc.tools.util.StringUtil;
import com.yunhan.scc.tools.util.regular.RegularJSUtil;
import com.yunhan.scc.trading.web.entities.system.Department;
import com.yunhan.scc.trading.web.entities.system.Operator;
import com.yunhan.scc.trading.web.entities.system.Role;
import com.yunhan.scc.trading.web.spring.SessionUser;

/**
 * 
 * 发货单号规则配置
 * @author xiongmingbao
 * @version created at 2016-8-29 下午2:15:29
 */
@Controller
@RequestMapping(value="/backto/system")
public class SendRuleConfigController extends BaseController{

private final static Log log = LogFactory.getLog(SendRuleConfigController.class);
	
@Autowired
private DepartmentBacktoService  departmentBacktoService;
@Autowired
private SendRuleConfigService sendRuleConfigService;
	
	/**
	 * 
	 * 发货单规则配置页面
	 * @author xiongmingbao
	 * @version created at 2016-8-24 下午5:11:48
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/page/toSendRuleConfig")
	public ModelAndView toSendRuleConfig(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView modelAndView = new ModelAndView("/backto/system/toSendRuleConfigNew");
		Operator operator = SessionUser.getSessionOperator();
		List<Department> suppliers = null;
		List<Department> purchasers = null;
		boolean isAdminRole = false;//是否是管理员角色标示
		for(Role role : operator.getRoles()){
			if("admin_key".equals(role.getRoleKey())){
				isAdminRole = true;
			}
		}
		if(isAdminRole){
			Map<String,Object> supplierMap = new HashMap<String,Object>();
			supplierMap.put("strusInd", 1);//是否启用
			supplierMap.put("merchantType", 1);//供应商
			suppliers = departmentBacktoService.selectSupplierList(supplierMap);
			Map<String,Object> purchaserMap = new HashMap<String,Object>();
			purchaserMap.put("strusInd", 1);//是否启用
			purchaserMap.put("merchantType", 0);//采购商
			purchasers = departmentBacktoService.selectSupplierList(purchaserMap);
		}else{
			suppliers = new ArrayList<Department>();
			suppliers.add(departmentBacktoService.getDepartmentByPurchaserId(operator.getSapvendorId()+""));
			purchasers = departmentBacktoService.selectMyPur(operator.getSoName());
		}
		request.setAttribute("suppliers", suppliers);
		request.setAttribute("purchasers", purchasers);
		request.setAttribute("isAdminRole", isAdminRole);
		return modelAndView;
	}
	
	/**
	 * 
	 * 保存发货单规则
	 * @author xiongmingbao
	 * @version created at 2016-8-26 下午1:37:03
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/json/saveSendRuleConfig", method = RequestMethod.POST)
	public void saveSendRuleConfig(HttpServletRequest request, HttpServletResponse response){
		Json json = new Json();
		try {
			String sendRuleId = request.getParameter("sendRuleId");
			String supplierId = request.getParameter("supplierId");  //update at 2016-10-19 wuyounan
			String purchaserId = request.getParameter("purchaserId");
			String lengthRule = request.getParameter("lengthRule");
			String length = request.getParameter("length");
			String sendFormat = request.getParameter("sendFormat");
			String isValid = request.getParameter("isValid");
			String regularExpression = RegularJSUtil.getRegular(sendFormat, lengthRule, Integer.parseInt(length));//得到正则表达式
			SendRuleConfigDo sendRuleConfigDo = new SendRuleConfigDo();
			if (!StringUtil.isNull(sendRuleId)) {
				sendRuleConfigDo.setId(Long.parseLong(sendRuleId));
			}
			sendRuleConfigDo.setSendoutGoodsLength(Integer.parseInt(length));
			sendRuleConfigDo.setLengthRule(lengthRule);
			sendRuleConfigDo.setSendFormat(sendFormat);
			sendRuleConfigDo.setSupplierId(supplierId);  //update at 2016-10-19 wuyounan
			sendRuleConfigDo.setRegularExpression(regularExpression);
			sendRuleConfigDo.setIsValid(isValid);
			sendRuleConfigDo.setPurchaserId(purchaserId);
			if(StringUtil.isNull(sendRuleId)){
				SendRuleConfigCondition sendRule = new SendRuleConfigCondition();
				sendRule.setSupplierId(supplierId);
				sendRule.setPurchaserId(purchaserId);
				List<SendRuleConfigDo> list = sendRuleConfigService.getSendRuleConfigBySupplierId(sendRule);
				if(list.size()>0){
					json.setSuccess(false);
					json.setMsg("已经存在供应商与采购商的发货单规则!");
				}else{
					sendRuleConfigService.saveSendRuleConfig(sendRuleConfigDo);
					json.setSuccess(true);
				}
			}else{
				sendRuleConfigService.saveSendRuleConfig(sendRuleConfigDo);
				json.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			json.setSuccess(false);
			json.setMsg("保存发货单规则后台服务器异常!");
		}
		writeJson(json, response);
	}
	/**
	 * 
	 * 根据供应商ID获取有商务关系的采购商
	 * @author wuyounan
	 * @version created at 2016-10-26 下午1:37:03
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/json/getPurchaserList", method = RequestMethod.POST)
	public void getPurchaserBySupperlierId(HttpServletRequest request, HttpServletResponse response){
		String supplierId = request.getParameter("supplierId"); 
		Json json = new Json();
		List<Department> list = null;
		try {
			Department department =departmentBacktoService.getDepartmentByPurchaserId(supplierId);
			 list = departmentBacktoService.selectSupplierList(department.getId()+"");
			 json.setSuccess(true);
			 json.setObj(list);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			 json.setSuccess(false);
			 json.setMsg("获取采购商数据后台系统错误");
		}
		writeJson(json, response);
	}
	
	/**
	 * 
	 * 获取用户权限获取启用 供应商/采购商
	 * @author wuyounan
	 * @version created at 2016-8-26 下午1:37:03
	 * @param request  
	 * @param response
	 */
	@RequestMapping(value = "/json/getSupperlierList", method = RequestMethod.POST)
	public void getSupperlierList(HttpServletRequest request, HttpServletResponse response){
		JSONArray jsons = new JSONArray();
		Operator operator = SessionUser.getSessionOperator();
		String type =request.getParameter("type");
		boolean isAdminRole = false;//是否是管理员角色标示
		for(Role role : operator.getRoles()){
			if("admin_key".equals(role.getRoleKey())){
				isAdminRole = true;
			}
		}
		List<Department> list = null;
		try {
			if(isAdminRole){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("strusInd", 1);//是否启用
				map.put("merchantType", type);//1:供应商,0:采购商
				list =departmentBacktoService.selectSupplierList(map);
				System.out.println(list.size());
			}else{
				if("1".equals(type)){
					list = new ArrayList<Department>();
					list.add(departmentBacktoService.getDepartmentByPurchaserId(operator.getSapvendorId()+""));
				}else{
					list = departmentBacktoService.selectMyPur(operator.getSoName());
				}
			}
			for (Department department : list) {
				JSONObject json = new JSONObject();
				json.put("supplierId", department.getSapvendorId());
				json.put("supplierName", department.getName());
				jsons.put(json);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		writeJsonSimple(jsons, response);
	}
	
	@RequestMapping("/json/showSupplierIdOrName")
	public void showSupplierIdOrName(HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<Map<String, String>> departmentList = new ArrayList<Map<String, String>>();
		String term = request.getParameter("q");
		List<Department> departments = departmentBacktoService.findDepartmentBySapvendorIdorName(term, term, 10);
		if (null != departments) {
			for (int i = 0; i < departments.size(); i++) {
				Department department = departments.get(i);
				String value = department.getSapvendorId();
				String name = department.getName();
				String label = department.getSapvendorId() + " || " + department.getName();
				Map<String, String> map = new HashMap<String, String>();
				map.put("supplierName", name);
				map.put("supplierId", value);
				map.put("label", label);
				departmentList.add(map);
			}
			writeJson(departmentList, response);
		}

	}
	
	
}
