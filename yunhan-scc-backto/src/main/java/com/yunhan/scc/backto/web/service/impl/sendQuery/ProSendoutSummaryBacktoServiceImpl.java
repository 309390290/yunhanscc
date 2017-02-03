package com.yunhan.scc.backto.web.service.impl.sendQuery;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhan.scc.backto.interfaceEntrance.service.sendgoods.DeliveredInterfaceService;
import com.yunhan.scc.backto.web.dao.mapper.backreport.ProResponseItemsDao;
import com.yunhan.scc.backto.web.dao.mapper.order.ProPurOrderDao;
import com.yunhan.scc.backto.web.dao.mapper.order.ProPurOrderItemsDao;
import com.yunhan.scc.backto.web.dao.mapper.sendgoods.ProSendoutItemsDao;
import com.yunhan.scc.backto.web.dao.mapper.sendgoods.ProSendoutSummaryDao;
import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutSummaryBacktoDO;
import com.yunhan.scc.backto.web.model.backreport.ProResponseItemsBacktoCondition;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutItemsBacktoCondition;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutSummaryBacktoCondition;
import com.yunhan.scc.backto.web.service.backreport.ProResponseItemsService;
import com.yunhan.scc.backto.web.service.order.ProPurOrderItemsService;
import com.yunhan.scc.backto.web.service.sendQuery.ProSendoutItemsBacktoService;
import com.yunhan.scc.backto.web.service.sendQuery.ProSendoutSummaryBacktoService;
import com.yunhan.scc.backto.web.service.system.SystemBacktoService;
import com.yunhan.scc.trading.web.entities.system.Operator;
import com.yunhan.scc.trading.web.spring.SessionUser;


/**
 * 发货单ServiceImpl
 * 
 * @author zxc
 * @version created at 2016年2月19日 上午11:39:09
 */
@Service("proSendoutSummaryBacktoService")
public class ProSendoutSummaryBacktoServiceImpl implements ProSendoutSummaryBacktoService {
	public static final Logger log = Logger.getLogger(ProSendoutSummaryBacktoServiceImpl.class);
	/**
	 * 发货单总目Dao
	 */
	@Autowired
	private ProSendoutSummaryDao proSendoutSummaryBacktoDao;
	@Autowired
	private ProSendoutItemsDao proSendoutItemsDao;
	@Autowired
	private ProPurOrderDao proPurOrderDao;
	@Autowired
	private ProPurOrderItemsDao proPurOrderItemsDao;
	@Autowired
	private ProResponseItemsDao proResponseItemsDao;
	@Autowired
	private SystemBacktoService systemBacktoService;
	@Autowired
	private DeliveredInterfaceService deliveredInterfaceService;
	@Autowired
	private ProSendoutItemsBacktoService backtoItemsService;
	@Autowired
	private ProPurOrderItemsService proPurOrderItemsService;
	@Override
	public void delSendoutSummary(String sendOutids) throws Exception {
		log.info("删除发货单开始=================");
		ProSendoutSummaryBacktoCondition pssc = new ProSendoutSummaryBacktoCondition();
		pssc.setIds(sendOutids);
		List<ProSendoutSummaryBacktoDO> sendSummaryList = queryProSendoutSummary(pssc);
		for(ProSendoutSummaryBacktoDO sendSummary:sendSummaryList){
			delSendoutSummary(sendSummary.getPurchaserId(), sendSummary.getSupplierId(), sendSummary.getSendoutGoodsCode());
		}
		log.info("删除发货单结束=================");
		
	}

	@Override
	public List<ProSendoutSummaryBacktoDO> queryProSendoutSummary(
			  ProSendoutSummaryBacktoCondition pssc) {
		// TODO Auto-generated method stub
		return proSendoutSummaryBacktoDao.queryProSendoutSummary(pssc);
	}
	@Override
	public List<ProSendoutSummaryBacktoDO> findSendOutSummaryById(
			  ProSendoutSummaryBacktoCondition pssc) {
		List<ProSendoutSummaryBacktoDO>  list=proSendoutSummaryBacktoDao.findSendOutSummaryById(pssc);
		return list;
	}
	@Override
	public void delSendoutSummary(String purchaserId, String supplierId,
			String sendoutGoodsCode) throws Exception {
		log.info("发货单整单开始=================");
		Operator operator = SessionUser.getSessionOperator();
		ProSendoutSummaryBacktoCondition pssc = new ProSendoutSummaryBacktoCondition();
		pssc.setPurchaserId(purchaserId);
		pssc.setSupplierId(supplierId);
		pssc.setSendoutGoodsCode(sendoutGoodsCode);
		
		ProSendoutItemsBacktoCondition psic = new ProSendoutItemsBacktoCondition();
		psic.setPurchaserId(purchaserId);
		psic.setSupplierId(supplierId);
		psic.setSendoutGoodsCode(sendoutGoodsCode);
		//获得本次删除的所有发货单细目ID
		List<ProSendoutItemsBacktoDO> sendoutItems=proSendoutItemsDao.queryProSendoutItemsBySendcode(psic);
		List<String> orderItemsIds= new ArrayList<String>();
		for (ProSendoutItemsBacktoDO proSendoutItemsBacktoDO : sendoutItems) {
			Long orderItemsId= proSendoutItemsBacktoDO.getProPurOrderItemsId();
			if(orderItemsId !=null){
				orderItemsIds.add(orderItemsId+"");
				proPurOrderItemsService.updateOrderItemsForCloseSendOrOpenSend(proSendoutItemsBacktoDO.getProPurOrderItemsId().toString(), proSendoutItemsBacktoDO.getOtherAvailableReason());
			}
			//删除发货单对应的回告信息
			backtoItemsService.delResponse(proSendoutItemsBacktoDO.getId());
		}
		proSendoutItemsDao.updateSendoutItemsDeleteAll(psic);
		proSendoutSummaryBacktoDao.updateProSendoutSummaryDelete(pssc);
		deliveredInterfaceService.sendOutDelivered(sendoutGoodsCode, supplierId, purchaserId, true);
		//调用公用存储过程来修改订单状态
		log.info("发货单整单删除后，调用修改订单状态存储过程:供应商id"+supplierId+"采购商ID："+purchaserId+"订单细目id:"+orderItemsIds+"调用类型:1");
		//计算订单状态
		for(String orderid:orderItemsIds){
			String soname = "ADMIN";
			if(operator!=null){
				soname = operator.getSoName();
			}
			systemBacktoService.updateOrderState(soname, purchaserId,orderid , "1");
		}
	}

	/**
	 * 发货单 总目更新
	 * yangtao 2016-8-4
	 * @param pssc
	 */
	@Override
	public void updateSendoutSummary(ProSendoutSummaryBacktoDO pssc) {
		proSendoutSummaryBacktoDao.update(pssc);
		
	}


	
}
	
