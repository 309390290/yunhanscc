package com.yunhan.scc.backto.web.service.impl.sendQuery;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunhan.scc.backto.interfaceEntrance.service.sendgoods.DeliveredInterfaceService;
import com.yunhan.scc.backto.web.dao.mapper.backreport.ProResponseItemsDao;
import com.yunhan.scc.backto.web.dao.mapper.sendgoods.ProSendoutItemsDao;
import com.yunhan.scc.backto.web.dao.mapper.sendgoods.ProSendoutSummaryDao;
import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutSummaryBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.SENDOUTNUMBERS;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutItemsBacktoCondition;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutSummaryBacktoCondition;
import com.yunhan.scc.backto.web.service.backreport.ProResponseItemsService;
import com.yunhan.scc.backto.web.service.order.ProPurOrderItemsService;
import com.yunhan.scc.backto.web.service.sendQuery.ProSendoutItemsBacktoService;
import com.yunhan.scc.backto.web.service.system.SystemBacktoService;
import com.yunhan.scc.trading.web.entities.system.Operator;
import com.yunhan.scc.trading.web.spring.SessionUser;




/**
 * 发货单ServiceImpl
 * 
 * @author zxc
 * @version created at 2016年2月19日 上午11:39:09
 */
@Service("proSendoutItemsBacktoService")
public class ProSendoutItemsBacktoServiceImpl implements ProSendoutItemsBacktoService {
	public static final Logger log = Logger.getLogger(ProSendoutSummaryBacktoServiceImpl.class);
	/**
	 * 发货单总目Dao
	 */
	@Autowired
	private ProSendoutSummaryDao proSendoutSummaryDao;
	/**
	 * 发货单细目Dao
	 */
	@Autowired
	private ProSendoutItemsDao proSendoutItemsDao;
	@Autowired
	private ProResponseItemsDao proResponseItemsDao;
	@Autowired
	private DeliveredInterfaceService deliveredInterfaceService;
	@Autowired
	private ProResponseItemsService proResponseItemsService;
	@Autowired
	private ProPurOrderItemsService proPurOrderItemsService;
	@Autowired 
	private SystemBacktoService systemBacktoService;
	//存储过程跟新状态Service

	@Override
	public List<ProSendoutItemsBacktoDO> queryProSendoutItems(
			ProSendoutItemsBacktoCondition psic) throws Exception {
		return proSendoutItemsDao.queryProSendoutItemsBySendcode(psic);
	}
	/**
	 * 发货单细目删除
	 * @param id
	 * @throws Exception
	 */
	@Transactional
	public void delSendoutItemsById(Long id) throws Exception {
		log.info("单发货单细目删除开始============id为："+id);
		ProSendoutItemsBacktoCondition psic=new ProSendoutItemsBacktoCondition(id);
		List<ProSendoutItemsBacktoDO> backtoDOs = proSendoutItemsDao.queryProSendoutItemsBySendcode(psic);
		if(backtoDOs !=null && backtoDOs.size()>0){
			ProSendoutItemsBacktoDO backto = backtoDOs.get(0);
			//删除回告
			delResponse(backto.getId());
			//发货单细目删除
			delSendOut(backto);
			//调用存储过程计算订单状态
			if(backto.getProPurOrderItemsId()!=null){
				Operator operator = SessionUser.getSessionOperator();
				log.info("删除发货单后，调用修改订单状态存储过程:供应商id"+backto.getSupplierId()+"采购商ID："+backto.getPurchaserId()+"订单号:"+backto.getProPurOrderItemsId());
				systemBacktoService.updateOrderState(operator.getSoName(), backto.getPurchaserId(),backto.getProPurOrderItemsId().toString() , "1");

			}
			//发送发货单
			deliveredInterfaceService.sendOutDelivered(backto.getSendoutGoodsCode(), backto.getSupplierId(), backto.getPurchaserId(), true);
			
		}
		log.info("单发货单细目删除结束============ id为："+id);
	}
	/**
	 * 根据发货信息删除回告信息
	 * @param backtoID 发货单id
	 * wangtao
	 */
	public void delResponse(Long backtoId) {
		//获取发货单对应的回告信息 此方法获取的回告信息只有发货单id 和回告id
		ProResponseItemsBacktoDO proResponse = proResponseItemsDao.findResponseItemsBySendoutId(backtoId);
		if(proResponse!=null){//判断本次发货是否有回告信息 有则删除
			proResponse.setId(proResponse.getId()-1);
			proResponseItemsDao.updateResponseItemsIsValid(proResponse);
		}
	}
	/**
	 * 发货细目删除
	 * 包含重新计算总目
	 * @param backto
	 */
	public void delSendOut(ProSendoutItemsBacktoDO backto) {
		//删除细目
		proSendoutItemsDao.updateSendoutItemsDeleteByid(new ProSendoutItemsBacktoCondition(backto.getId()));
		//统计发货单细目未删除的条数
		Integer ts = proSendoutItemsDao.findSendOutItemsCount(backto);
		ProSendoutSummaryBacktoCondition pssc = new ProSendoutSummaryBacktoCondition(
				backto.getPurchaserId(), backto.getSupplierId(),
				backto.getSendoutGoodsCode());
		if(ts <= 0){//当发货单细目都已经删除了则删除总目
			//删除总目
			proSendoutSummaryDao.updateProSendoutSummaryDelete(pssc);
		}else{
			//重新计算 总实洋 总码洋 发货总册数 发货品种总数  
			proSendoutSummaryDao.reCalculationSendOutSummary(pssc);
		}
	}

	@Override
	public SENDOUTNUMBERS getRigthUpdateOrDeleteProSendSendItems(
			List<ProSendoutItemsBacktoDO> sendItemsList) {
		SENDOUTNUMBERS sendoutnumbers = new SENDOUTNUMBERS();
	for (ProSendoutItemsBacktoDO proSendoutItemsBacktoDO : sendItemsList) {
		if(proSendoutItemsBacktoDO.getProPurOrderItemsId()==null){
			sendoutnumbers.setLAST(true);
		}else{
			List<ProSendoutItemsBacktoDO> sendItemsListByProItemsId=proSendoutItemsDao.findMaxIdByProPurOrderItemsId(proSendoutItemsBacktoDO);
			if(!proSendoutItemsBacktoDO.getId().toString().equals(sendItemsListByProItemsId.get(0).getId().toString())){
				sendoutnumbers.setLAST(false);
			}else{
				sendoutnumbers.setLAST(true);
			}
			if(sendItemsListByProItemsId.get(0).getIsFrist() == 1){//add by yangtao 2016-8-3 用于表示是否是第一次回告发货
				sendoutnumbers.setFRIST(true);
			}else{
				sendoutnumbers.setFRIST(false);
			}
		}
	}
		return sendoutnumbers;
	}

	/**
	 * 
	 * 更新发货信息 2016-8-10
	 * @param sendoutItemsBacktoDO
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateSendoutItem(ProSendoutItemsBacktoDO sendoutItemsBacktoDO,ProResponseItemsBacktoDO proResponse,String proPurOrderItemsId) throws Exception{
		proSendoutItemsDao.updateSendoutItems(sendoutItemsBacktoDO);
		ProSendoutSummaryBacktoDO proSendoutSummaryBacktoDO=new ProSendoutSummaryBacktoDO();
		proSendoutSummaryBacktoDO.setSendoutGoodsCode(sendoutItemsBacktoDO.getSendoutGoodsCode());
		proSendoutSummaryBacktoDO.setSupplierId(sendoutItemsBacktoDO.getSupplierId());
		proSendoutSummaryBacktoDO.setPurchaserId(sendoutItemsBacktoDO.getPurchaserId());
		proSendoutSummaryDao.updateProSendoutSummaryDO(proSendoutSummaryBacktoDO);
		if(StringUtils.isNotBlank(proPurOrderItemsId)){ //订单id存在时修改回告信息
			//更新订单有效无效更新
			proPurOrderItemsService.updateOrderItemsForCloseSendOrOpenSend(proPurOrderItemsId, proResponse.getOtherAvailableReason());
			try {
				if(proResponse.getId()!=null){
					proResponseItemsService.updateResponse(proResponse);
					//逻辑设置比此ID大的回告信息为无效。
				
					proResponse.setProPurOrderItemsId(Long.parseLong(proPurOrderItemsId));
					proResponseItemsService.updateResponseItemsIsValid(proResponse);
					
					proResponse.setProSendoutItemsId(sendoutItemsBacktoDO.getId());
					proResponseItemsService.updateResponseItemsIsValidForUpdateSend(proResponse);
				}
			} catch (Exception e) {
				log.info("当多次回告对应一次发货，发货发生修改关系。只保留最后一次回告。前面回告删除  失败");
				throw e;
			}
		}
		
		try {
			systemBacktoService.updateOrderState(sendoutItemsBacktoDO.getSupplierId(),sendoutItemsBacktoDO.getPurchaserId(),proPurOrderItemsId,"0");
		} catch (Exception e) {
			log.info("计算回告发货可供价可供折扣差异失败！");
			throw e;
		}
		try {
			//向接口发送消息
			deliveredInterfaceService.sendOutDelivered(sendoutItemsBacktoDO.getSendoutGoodsCode(),sendoutItemsBacktoDO.getSupplierId(),sendoutItemsBacktoDO.getPurchaserId(),true);
		} catch (Exception e) {
			log.info("更新发货，向接口发送数据失败");
			throw e;
		}
		
		
		
	}
	
}
