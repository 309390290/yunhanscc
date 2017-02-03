package com.yunhan.scc.backto.interfaceEntrance.service.sendgoods;

import java.util.List;

import com.yunhan.scc.backto.interfaceEntrance.entities.sendgoods.SendSendoutSummaryDO;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutSummaryBacktoCondition;
import com.yunhan.scc.tools.util.Messaging;

/**
 * 发货对外接口
 * @author wangtao
 * @version created at 2016-3-9 下午2:45:11
 */
public interface DeliveredInterfaceService {

	
	/**
	 * 发送发货信息到采发
	 * @author wangtao
	 * @version created at 2016-3-8 上午10:11:00
	 * @param delivereds 发货信息-发货单号 和供应商编码未必填项
	 * @param supplierId 供应商id
	 * @param purchaserId 采购商id
	 * @param isModify 是否为修改发货单
	 */
	public void sendOutDeliveredByDo(
			List<SendSendoutSummaryDO> delivereds, boolean isModify)
			throws Exception;
	
	
	/**
	 * 发送发货信息到采发系统中
	 * @param sendGoodCode 发货单号
	 * @param supplierId 供应商id（发送方）
	 * @param purchaserId 采购商id（接收方）
	 * @param isModify 是否为修改发货单 true 是  false　否
	 * @throws Exception
	 */
	public void sendOutDelivered(String sendGoodCode,String supplierId,String purchaserId, boolean isModify)throws Exception;

	/**
	 * 接收发货信息
	 * @author wangtao
	 * @version created at 2016-3-8 上午10:54:39
	 * @param delivereds 发货数据
	 * @throws Exception
	 */
	public Messaging acceptDelivered(List<ProSendoutSummaryBacktoCondition> delivereds) throws Exception;


}
