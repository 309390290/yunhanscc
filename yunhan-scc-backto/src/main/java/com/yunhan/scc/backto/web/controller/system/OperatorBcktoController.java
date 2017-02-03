package com.yunhan.scc.backto.web.controller.system;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.yunhan.scc.backto.web.controller.BaseController;
import com.yunhan.scc.backto.web.service.system.OperatorBacktoService;
import com.yunhan.scc.tools.page.Json;
import com.yunhan.scc.tools.util.Encrypt;
import com.yunhan.scc.tools.util.StringUtils;

@Controller
@RequestMapping("/system")
public class OperatorBcktoController extends BaseController {
	
	private final Logger log = LoggerFactory
			.getLogger(OperatorBcktoController.class);
	@Autowired
	private OperatorBacktoService operatorBacktoService;
	
	@RequestMapping("/updatePassword")
	public void updatePassword(HttpServletRequest request, HttpServletResponse response) {
		Json json = new Json();
		try {
			
			String msg = request.getParameter("msg");
			String userId=request.getParameter("userId");
			String newPassword = request.getParameter("newPassword");
			//可供外部系统调用修改密码
			if(msg!=null && !msg.equals("")){
				JSONObject data = JSONObject.parseObject(msg);
				userId = data.getString("userId").toString();
		        newPassword = data.getString("newPassword").toString(); //获取新密码
			}
			if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(newPassword)) {
				json.setSuccess(false);
				json.setMsg("用户Id或密码为空！");
			} else {
				operatorBacktoService.updatePassword(Long.parseLong(userId),Encrypt.e(newPassword));
				json.setMsg("密码修改成功！");
				json.setSuccess(true);
			}
		} catch (Exception e) {
			log.error("修改用户密码失败：", e);
			json.setSuccess(false);
			json.setMsg("修改失败！请重试!");
		}
		writeJson(json, response);
	}

}
