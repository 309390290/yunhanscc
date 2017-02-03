package com.yunhan.scc.backto.interfaceEntrance.service.impl.backreport;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yunhan.scc.backto.interfaceEntrance.dao.mapper.backreport.SendDataToInterfaceTDao;
import com.yunhan.scc.backto.interfaceEntrance.entities.backreport.SendDataToInterfaceTDO;
import com.yunhan.scc.backto.interfaceEntrance.service.backreport.BacktoInterFaceService;
import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;
import com.yunhan.scc.tools.constant.InterfaceEntranceConstant;
import com.yunhan.scc.tools.properties.ParameterTool;
import com.yunhan.scc.tools.util.HttpUtil;
import com.yunhan.scc.tools.util.Messaging;

/**
 * 回告对外接口
 * @author wangtao
 * @version created at 2016-3-8 上午10:23:06
 */
@Service
public class BacktoInterFaceServiceImpl implements BacktoInterFaceService {
	/**
	 * 对外调用接口地址
	 */
	private static String pathUrl = ParameterTool.getParameter("httpPath");
	
	private static final Log log = LogFactory.getLog(BacktoInterFaceServiceImpl.class);
	
	@Autowired
	private SendDataToInterfaceTDao sendDataToInterfaceTDao;

//	@Autowired
//	private ProResponseItemsBacktoService backtoService;
//	@Autowired
//	private ProPurOrderSummaryBacktoService proPurOrderSummaryBacktoService;
	
	public void sendOutBackto(List<ProResponseItemsBacktoDO> backtoDos) throws Exception {
		Messaging message=new Messaging();
//		pathUrl="http://10.100.143.94:8081/anze-scc-data-entrance";
		String url = pathUrl+InterfaceEntranceConstant.SEND_DATAS_URL;
		log.info("调用回告发送接口url:"+url+" 回告条数："+backtoDos.size());
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(InterfaceEntranceConstant.ORDER_REPORT, backtoDos); //订单回告数据 数据标识
		param.put(InterfaceEntranceConstant.RECEIVE_CONSUMER, "PURCHARSE"); //接收方 为采购商
		String result =HttpUtil.sendSms(url,param);
		try {
			message = JSON.parseObject(result, Messaging.class);
//			System.out.println(message);
		} catch (Exception e) {
			log.error("回告发送失败，解析返回值异常", e);
			throw new Exception("回告发送失败，解析返回值异常",e);
		}
		if(null != message){
			if("S".equals(message.getMessageType())){
				log.info("回告发送成功");
 			}
 			if("E".equals(message.getMessageType())){
 				throw new Exception("回告发送失败，接口返回信息：" + message.getMessage());
 			}
 		}
		
	}
	
	
	/**
	 * 批量保存待发送信息
	 * @author luohoudong
	 * @version created at 2016-10-17 上午11:32:02
	 * @param sourceDataIds  业务数据主键ids
	 * @param sendType	发送的业务类型：1-回告数据
	 * @throws Exception 	
	 */
	public void batchSaveSendDatas(List<Long> sourceDataIds,Integer sendType)throws Exception{
		if(sendType ==null){
			throw new Exception("批量保存待发送数据，发送业务类型不能为空!");
		}
		SendDataToInterfaceTDO sendDataToInterfaceTDO=new SendDataToInterfaceTDO();
		sendDataToInterfaceTDO.setSendType(sendType);
		for (Long sourceDataId : sourceDataIds) {
			sendDataToInterfaceTDO.setSourceDataId(sourceDataId);
			sendDataToInterfaceTDao.save(sendDataToInterfaceTDO);
		}
		
	}
}
