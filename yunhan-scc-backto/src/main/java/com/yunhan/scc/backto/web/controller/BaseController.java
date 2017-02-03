package com.yunhan.scc.backto.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.yunhan.scc.tools.json.JSONArray;
import com.yunhan.scc.tools.json.JSONObject;
import com.yunhan.scc.tools.page.Json;

public class BaseController {

	public void writeJsonSimple(Object object, HttpServletResponse response) {
		try {
			Json jsonObject = new Json();
			jsonObject.setSuccess(true);
			jsonObject.setObj(object);
			JSONObject json = new JSONObject(jsonObject);
//			String json = JSON.toJSONStringWithDateFormat(jsonObject, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("text/html;charset=utf-8");
			// response.setContentType("application/json");
			response.getWriter().write(json.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeJson(Object object, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setContentType("application/json");
			String msg = "";
			//edit by yangtao 2016-4-11 增加list泛型 List --> List<?>
			if(object instanceof List<?>){
				JSONArray json = new JSONArray((List<?>)object);
				msg = json.toString();
			}else{
				JSONObject json = new JSONObject(object);
				msg = json.toString();
			}
//			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			response.getWriter().write(msg);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeJson(String jsonContent, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setContentType("application/json");
			response.getWriter().write(jsonContent);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
