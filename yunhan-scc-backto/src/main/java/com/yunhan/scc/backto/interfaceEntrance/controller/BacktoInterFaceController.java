package com.yunhan.scc.backto.interfaceEntrance.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.yunhan.scc.backto.interfaceEntrance.service.job.SendDataJobService;
import com.yunhan.scc.backto.interfaceEntrance.service.sendgoods.DeliveredInterfaceService;
import com.yunhan.scc.backto.web.controller.BaseController;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutSummaryBacktoCondition;
import com.yunhan.scc.tools.util.HttpUtil;
import com.yunhan.scc.tools.util.Messaging;

/**
 * 回告发货对外接口
 * @author wangtao
 * @version created at 2016-3-8 下午4:40:07
 */
@Controller
@RequestMapping("/server/backto")
public class BacktoInterFaceController extends BaseController {
	static{
		 SerializeConfig mapping = new SerializeConfig();
		 mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
	}
	private static Log log  = LogFactory.getLog(BacktoInterFaceController.class);
	
	@Autowired
	private DeliveredInterfaceService deliveredInterfaceService;
	@Autowired
	private SendDataJobService sendDataJobService;
	
	
	/**
	 * 发货接收
	 * @author wangtao
	 * @version created at 2016-3-16 上午10:06:47
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/httpSaveDelivered")
	public void acceptDelivered(HttpServletResponse response,HttpServletRequest request)throws Exception {
		Messaging message = new Messaging();
		log.info("=================>接收采发的发货数据 Controller  开始");
		try {
			//设置request编码
			request.setCharacterEncoding("UTF-8");
			byte[] reData = HttpUtil.readInputStream(request.getInputStream());
			String msg = new String(reData, "UTF-8");
			log.info("接收到发货信息:"+ msg);
			List<ProSendoutSummaryBacktoCondition> datas = JSONArray.parseArray(msg,ProSendoutSummaryBacktoCondition.class);
			if(datas!=null){
				deliveredInterfaceService.acceptDelivered(datas);
			}
			message.setMessageType("S");
		} catch (Exception e) {
			message.setMessageType("E");
			message.setMessage("保存发货失败，错误信息："+e.getMessage());
			log.error("接收采发系统发货数据出现异常：",e);
		}
		writeJson(message, response);
		log.info("=================>接收采发的发货数据 Controller 结束");
	}
	
	/**
	 * 加工发送数据信息任务
	 * @author luohoudong
	 * @version created at 2016-10-17 下午1:48:30
	 * @param request sendType:{1:回告}
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/processSendDatas")
	public void processSendDatas(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Messaging message=new Messaging();
		log.info("=================> 加工发送数据信息任务 开始");
		try {
			String msg= request.getParameter("sendType");
			/*byte[] reData = HttpUtil.readInputStream(request.getInputStream());
			String msg = new String(reData, "UTF-8");
			Map<String, Object> globalPool=JSON.parseObject(msg,new TypeReference<Map<String, Object>>(){});*/
			sendDataJobService.processingSendData(Integer.parseInt(msg));
			message.setMessageType("S");
		} catch (Exception e) {
			message.setMessageType("E");
			log.error("加工发送数据信息任务出现异常：",e);
		}
		writeJson(message, response);
		log.info("=================> 加工发送数据信息任务  结束");
	}

}
