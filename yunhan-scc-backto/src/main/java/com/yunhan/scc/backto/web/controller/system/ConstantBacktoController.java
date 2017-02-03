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
import com.yunhan.scc.backto.web.entities.system.ConstantBacktoDo;
import com.yunhan.scc.backto.web.service.system.ConstantBacktoService;
import com.yunhan.scc.tools.page.Json;
import com.yunhan.scc.trading.web.entities.system.Operator;
import com.yunhan.scc.trading.web.spring.SessionUser;

@Controller
@RequestMapping(value="/backto/constant")
public class ConstantBacktoController extends BaseController{
	private final static Log log = LogFactory.getLog(ConstantBacktoController.class);
	
	@Autowired
	private ConstantBacktoService constantBacktoService;
	
	/**
	 * 根据常数类型获取常数
	 * @author luohoudong
	 * @version created at 2016-8-1 上午11:22:29
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/json/getConstantByCode")
	public void getConstantByCode(HttpServletRequest request, HttpServletResponse response){
		Operator operator = SessionUser.getSessionOperator();
		Json json = new Json();
		List<ConstantBacktoDo> constants = new ArrayList<ConstantBacktoDo>();
		try {
			if(null == operator){
//				throw new Exception("登陆失败，请重新登陆！");
			}
			String typeCode = request.getParameter("typeCode");  //常数类型
			constants = constantBacktoService.getConstantsByType(typeCode);
			json.setObj(constants);
			json.setSuccess(true);
		} catch (Exception e) {
			log.error("常数类型获取常数",e);
		}
		writeJson(json, response);
	}
	

}
