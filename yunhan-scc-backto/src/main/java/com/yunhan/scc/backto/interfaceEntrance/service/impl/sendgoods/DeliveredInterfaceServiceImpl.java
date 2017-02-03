package com.yunhan.scc.backto.interfaceEntrance.service.impl.sendgoods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.yunhan.scc.backto.interfaceEntrance.dao.mapper.sendgoods.SendSendGoodsDao;
import com.yunhan.scc.backto.interfaceEntrance.entities.sendgoods.SendSendoutSummaryDO;
import com.yunhan.scc.backto.interfaceEntrance.service.sendgoods.DeliveredInterfaceService;
import com.yunhan.scc.backto.web.dao.mapper.order.ProPurOrderItemsDao;
import com.yunhan.scc.backto.web.dao.mapper.sendgoods.ProSendoutItemsDao;
import com.yunhan.scc.backto.web.dao.mapper.sendgoods.ProSendoutSummaryDao;
import com.yunhan.scc.backto.web.entities.order.ProPurOrderItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutSummaryBacktoDO;
import com.yunhan.scc.backto.web.model.order.ProPurOrderItemsBacktoCondition;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutItemsBacktoCondition;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutSummaryBacktoCondition;
import com.yunhan.scc.backto.web.service.sendQuery.ProSendoutSummaryBacktoService;
import com.yunhan.scc.backto.web.service.system.SystemBacktoService;
import com.yunhan.scc.tools.constant.InterfaceEntranceConstant;
import com.yunhan.scc.tools.properties.ParameterTool;
import com.yunhan.scc.tools.util.BeanUtils;
import com.yunhan.scc.tools.util.HttpUtil;
import com.yunhan.scc.tools.util.Messaging;

/**
 * 发货对外接口
 * 
 * @author wangtao
 * @version created at 2016-3-9 下午2:45:22
 */
@Service
public class DeliveredInterfaceServiceImpl implements DeliveredInterfaceService {
	private static final Log log = LogFactory
			.getLog(DeliveredInterfaceServiceImpl.class);
	@Autowired
	private SendSendGoodsDao sendGoodsDao;
	@Autowired
	private ProPurOrderItemsDao orderItemsDao;
	@Autowired
	private ProSendoutSummaryDao summaryDao;
	@Autowired
	private ProSendoutItemsDao sendoutItemsDao;  
	@Autowired
	private SystemBacktoService systemBacktoService;
	@Autowired
	private ProSendoutSummaryBacktoService backtoService;
	/**
	 * 对外调用接口地址
	 */
	private static String pathUrl = ParameterTool.getParameter("httpPath");
	
	/**
	 * 发送发货信息到采发系统中
	 * @param sendGoodCode 发货单号
	 * @param supplierId 供应商id（发送方）
	 * @param purchaserId 采购商id（接收方）
	 * @param isModify 是否为修改发货单 true 是  false　否
	 * @throws Exception
	 */
	public void sendOutDelivered(String sendGoodCode,String supplierId,String purchaserId, boolean isModify)throws Exception{
		ProSendoutSummaryBacktoCondition backtoCondition = new ProSendoutSummaryBacktoCondition();
		backtoCondition.setPurchaserId(purchaserId);
		backtoCondition.setSupplierId(supplierId);
		backtoCondition.setSendoutGoodsCode(sendGoodCode);
		List<SendSendoutSummaryDO> sends = sendGoodsDao.send_sendOutSummaryAndItems(backtoCondition);
		if(sends!=null&&sends.size()>=0){
			sendOutDeliveredByDo(sends, isModify);
		}else{
			throw new Exception("发货单不存在！");
		}
	}
	
	
	
	
	
	public void sendOutDeliveredByDo(
			List<SendSendoutSummaryDO> delivereds, boolean isModify)
			throws Exception {
		Messaging message = new Messaging();
//		pathUrl="http://10.100.143.53:1688/anze-scc-data-entrance";
		String url = pathUrl + InterfaceEntranceConstant.SEND_DATAS_URL;
		log.info("调用发货  发送接口url:" + url + " 发货条数：" + delivereds.size());
		Map<String, Object> param = new HashMap<String, Object>();
		if (isModify) { // 判断是否为修改
			param.put(InterfaceEntranceConstant.MODIFY_DELIVERY_NOTE,
					delivereds); // 修改订单发货数据 数据标识
		} else {
			param.put(InterfaceEntranceConstant.ADD_DELIVERY_NOTE, delivereds); // 新增订单发货数据
																				// 数据标识
		}
		param.put(InterfaceEntranceConstant.RECEIVE_CONSUMER, "PURCHARSE"); // 接收方
																			// 为采购商
		String result = HttpUtil.sendSms(url, param);
		try {
			message = JSON.parseObject(result, Messaging.class);
		} catch (Exception e) {
			log.error("发货发送失败，解析返回值异常", e);
			throw new Exception("发货发送失败，解析返回值异常", e);
		}
		if (null != message) {
			if ("S".equals(message.getMessageType())) {
				log.info("发货发送成功");
			}
			if ("E".equals(message.getMessageType())) {
				throw new Exception("发货发送失败，接口返回信息：" + message.getMessage());
			}
		}
	}
	
	
	
	/**
	 * 根据供应商商品ID、供应商ID、采购商ID 查找订单细目ID。
	 * @param pssbd
	 * @return 返回的订单细目实体中，只有订单细目Id，如需其他信息修改XML文件中的查询语句
	 * @author YangTAO
	 * 2016-4-28
	 */
	public ProPurOrderItemsBacktoDO getOrderItemId(ProSendoutItemsBacktoCondition pssbd){
		
		ProPurOrderItemsBacktoCondition ppoibc = new ProPurOrderItemsBacktoCondition();
		
		String supplierCommoditiesId = pssbd.getSupplierCommoditiesId();
		if(pssbd.getPurchaseOrderCode()==null){
			log.info("若订单号码不存在则不需检查");
			return null;
		}
		List<ProPurOrderItemsBacktoDO> list = null;
		ppoibc.setSupplierId(pssbd.getSupplierId()); //设置供应商
		ppoibc.setPurchaserId(pssbd.getPurchaserId());//设置采购商
		ppoibc.setPurchaseOrderCode(pssbd.getPurchaseOrderCode()); //设置订单号码
		log.info("供应商:"+pssbd.getSupplierId()+" 采购商:"+pssbd.getPurchaserId()+" 订单号码："+
				pssbd.getPurchaseOrderCode()+"  供应商商品ID:"+supplierCommoditiesId);
		if(supplierCommoditiesId != null){
			//设置第一个查询条件 供应商商品ID
			ppoibc.setSupplierCommoditiesId(supplierCommoditiesId);
			list = orderItemsDao.findOrderIdbySendout_scmid(ppoibc);
			if(list != null && list.size() ==1){
				return list.get(0);
			}
			//第二个查询条件不用设置 
			list = orderItemsDao.findOrderIdbySendout_ppoid(ppoibc);
			if(list != null && list.size() ==1){
				return list.get(0);
			}
		}
		
		//设置第三个查询条件 ISBN + 定价 + 书名
		ppoibc.setIsbn(pssbd.getIsbn());
		ppoibc.setPrice(pssbd.getPrice());
		ppoibc.setBookTitle(pssbd.getBookTitle());
		list = orderItemsDao.findOrderIdbySendout_isbn(ppoibc);
		if(list != null && list.size() ==1){
			return list.get(0);
		}
		
		//第四个查询条件 ISBN + 定价
		list = orderItemsDao.findOrderIdbySendout_isbn_price(ppoibc);
		if(list != null && list.size() ==1){
			return list.get(0);
		}
		//第五个条件  若为上海世纪集团公司的发货 则使用isbn+定价+书名+上海世纪下所有供应商匹配
		list = orderItemsDao.findOrderIdbySendout_jtgs_isbn_book_pice(ppoibc);
		if(list != null && list.size() ==1){
			return list.get(0);
		} else{
			return null;
		}
	}

	
	/**
	 * 接收发货信息
	 * 
	 * @author wangtao
	 * @version created at 2016-3-8 上午10:54:39
	 * @param delivereds
	 *            发货数据
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public Messaging acceptDelivered(
			List<ProSendoutSummaryBacktoCondition> delivereds) throws Exception {
		Map<String, String> yunhanCode = new HashMap<String, String>(); // 排重后的订单号
		for (ProSendoutSummaryBacktoCondition delivered : delivereds) {
			try {
				ProSendoutSummaryBacktoDO backtoDO = new ProSendoutSummaryBacktoDO(); 
				BeanUtils.copyProperties(delivered, backtoDO);
				
				// 删除已存在相同的发货单
				delSendGoods(delivered.getPurchaserId(), delivered.getSupplierId(), delivered.getSendoutGoodsCode());
				
				List<ProSendoutItemsBacktoCondition> proSendoutItemsBacktoConditionList = delivered.getSendOutItems();
				for (ProSendoutItemsBacktoCondition proSendoutItemsBacktoCondition : proSendoutItemsBacktoConditionList) {
					ProSendoutItemsBacktoDO itemsBacktoDO =  new ProSendoutItemsBacktoDO();
					BeanUtils.copyProperties(proSendoutItemsBacktoCondition, itemsBacktoDO);
					/**
					 * 根据发货单找订单细目
					 */
					ProPurOrderItemsBacktoDO back = getOrderItemId(proSendoutItemsBacktoCondition);
					if (back != null) { // 若找到订单细目则将订单细目id放入发货单中
						itemsBacktoDO.setProPurOrderItemsId(back.getId());
						// 添加订单号到需要调用计算订单状态的订单集合中 若重复则无需再次添加
						if (!yunhanCode.containsKey(back.getId()+"")) {
							yunhanCode.put(back.getId()+"",back.getPurchaserId());
						}
					}
					
					itemsBacktoDO.setStatus(5); // 设置发货单状态为已发货
					itemsBacktoDO.setSourceType("EDI");
					itemsBacktoDO.setAddUserCode("ADMIN");
					sendoutItemsDao.saveSendoutItems(itemsBacktoDO);
		
				}
				backtoDO.setSourceType("EDI"); // 设置数据来源为EDI
				backtoDO.setSendoutStatus(5); // 设置发货单状态为已发货
				backtoDO.setAddUserCode("ADMIN");
				summaryDao.saveProSendoutSummaryDO(backtoDO);
				/**
				 * 调用存储过程写回告数据
				 */
				instPesponseItemsCall(backtoDO);
			} catch (Exception e) {
				log.error("接口发货单号："+delivered.getSendoutGoodsCode(),e);
				throw e;
			}
		}
		
		for(String code :yunhanCode.keySet()){
//			调用公用存储过程来修改订单状态
//			systemBacktoService.setOrderStatus(yunhanCode.get(code), code, "admin", "0");
			systemBacktoService.updateOrderState("admin",yunhanCode.get(code),code,"0");
		}
		return null;
	}
	/**
	 * 接口专用  存储过程写回告数据
	 * @param backtoDO
	 */
	public void instPesponseItemsCall(ProSendoutSummaryBacktoDO backtoDO){
		Map<String, String> param = new HashMap<String, String>();
		param.put("sendoutGoodsCode", backtoDO.getSendoutGoodsCode());
		param.put("purchaserId", backtoDO.getPurchaserId());
		param.put("supplierId", backtoDO.getSupplierId());
		sendGoodsDao.instPesponseItemsCall(param);
	}
	/**
	 * 删除发货单 -整单删除  接口专用
	 * @param sendoutGoodsCode 发货单号
	 * @param purchaserId 采购商id
	 * @param supplierId 供应商id
	 */
	public void delSendGoods(String purchaserId,String supplierId,String sendoutGoodsCode){
		ProSendoutSummaryBacktoCondition pssc = new ProSendoutSummaryBacktoCondition();
		pssc.setPurchaserId(purchaserId);
		pssc.setSupplierId(supplierId);
		pssc.setSendoutGoodsCode(sendoutGoodsCode);
		ProSendoutItemsBacktoCondition psic = new ProSendoutItemsBacktoCondition();
		psic.setPurchaserId(purchaserId);
		psic.setSupplierId(supplierId);
		psic.setSendoutGoodsCode(sendoutGoodsCode);
		sendoutItemsDao.updateSendoutItemsDeleteAll(psic); //删除细目  逻辑删除
		summaryDao.updateProSendoutSummaryDelete(pssc); //删除总目  逻辑删除
		log.info("接口删除发货单：发货单号："+sendoutGoodsCode+" 采购商id：" +purchaserId+"供应商id："+supplierId);
	}
	
	
}
