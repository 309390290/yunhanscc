package com.yunhan.scc.backto.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunhan.scc.backto.web.controller.BaseController;
import com.yunhan.scc.backto.web.model.system.SetOrderStatusCondition;
import com.yunhan.scc.backto.web.service.system.DepartmentBacktoService;
import com.yunhan.scc.backto.web.service.system.SystemBacktoService;
import com.yunhan.scc.oauth2.web.entities.security.Department;
import com.yunhan.scc.tools.page.Json;
import com.yunhan.scc.trading.web.entities.system.Operator;
import com.yunhan.scc.trading.web.spring.SessionUser;

/**
 * 
 * @author shiwenbo
 * @version created at 2016-3-3 上午11:00:36
 */
@Controller
@RequestMapping(value="/backto/system")
public class SystemBacktoController extends BaseController{

private final static Log log = LogFactory.getLog(SystemBacktoController.class);
	
	@Autowired
	private SystemBacktoService systemBacktoService;
	@Autowired
	private DepartmentBacktoService departmentBacktoService;
	
	
	/**
	 * 获取采购商业务关系
	 * @author shiwenbo
	 * @version created at 2016-3-3 上午11:06:04
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/getPurchaserBussinessRelation")
	public void getPurchaserBussinessRelation(HttpServletRequest request, HttpServletResponse response){
		Operator operator = SessionUser.getSessionOperator();
		Json json = new Json();
		List<Department> departments = new ArrayList<Department>();
		try {
			if(null == operator){
				throw new Exception("登陆失败，请重新登陆！");
			}
			departments = systemBacktoService.selectMyPur(operator.getSoName());
			json.setObj(departments);
			json.setSuccess(true);
		} catch (Exception e) {
			log.error("获取采购商业务关系",e);
			e.printStackTrace();
		}
		writeJson(json, response);
	}
	
	/**
	 * 待办进入的已关闭数据查询加载完后调用该方法设置数据为已查看
	 * @author pangzhenhua
	 * @version created at 2016年3月7日 下午3:02:36 
	 */
	@RequestMapping(value="/updateCloseOrderItemToViewed")
	public void updateCloseOrderItemToViewed(HttpServletRequest request, HttpServletResponse response){
		Operator operator = SessionUser.getSessionOperator();
		//systemBacktoService.updateCloseOrderItemToViewed(operator.getSoName());
		Json json = new Json();
		json.setSuccess(true);
		writeJson(json, response);
	}

	@RequestMapping(value="/test")
	public void test(){
		SetOrderStatusCondition setOrderStatusCondition = new SetOrderStatusCondition();
		setOrderStatusCondition.setPurchaserId("0002300043");
		setOrderStatusCondition.setUserCode("2200063hg");
		//setOrderStatusCondition.setYunhanOrderCode("1201602250001413");
		//systemBacktoService.setOrderStatus(setOrderStatusCondition);
	}
}
