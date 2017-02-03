package com.yunhan.scc.backto.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunhan.scc.oauth2.springoauth.service.dto.UserDto;
import com.yunhan.scc.oauth2.web.service.security.UserService;
import com.yunhan.scc.tools.page.Json;
import com.yunhan.scc.trading.web.entities.system.Operator;
import com.yunhan.scc.trading.web.spring.SessionUser;

/**
 * 项目名称：yunhan-scc-gd-web 
 * 类名称：MasterInfoAdditionController 
 * 类描述： 商品信息补充Controller
 * 创建人：zzp 
 * 创建时间：2015-12-28 上午10:26:52 
 * 修改人： 
 * 修改时间： 
 * 修改备注：
 *   
 * @version V0.1
 */
@Controller
public class MainBacktoController extends BaseController {
	private Log log = LogFactory.getLog(MainBacktoController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/mainBacktoController/page/mainPageBackto" })
	public String mainPageBackto(HttpServletRequest req) {
		return "/mainPageBackto";
	}

	@RequestMapping(value = "/backto/common/userinfo")
	public String commonUserInfo(Model model, HttpServletRequest request) {
		UserDto user = userService.loadCurrentUserJsonDto();
		model.addAttribute("user", user);
		return "/common/userinfo";
	}

	@RequestMapping("/backto/common/json/getUserInfo")
	public void getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		Json json = new Json();
		try {
			Operator operator = SessionUser.getSessionOperator();
			json.setSuccess(true);
			json.setObj(operator);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("获取用户信息失败");
			e.printStackTrace();
			log.error("列表查询失败", e);
		}
		writeJson(json, response);
	}
}