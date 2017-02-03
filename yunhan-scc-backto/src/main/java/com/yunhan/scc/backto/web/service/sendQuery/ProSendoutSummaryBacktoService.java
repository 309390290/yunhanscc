package com.yunhan.scc.backto.web.service.sendQuery;

import java.util.List;

import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutSummaryBacktoDO;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutItemsBacktoCondition;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutSummaryBacktoCondition;
import com.yunhan.scc.tools.exception.YhsfServiceException;

/**
 * 发货单Service
 * 
 * @author zxc
 * @version created at 2016年2月19日 上午11:39:09
 */
public interface ProSendoutSummaryBacktoService {
	/**
	 * 根据ids 删除发货单总目
	 * 根据发货单号 删除发货单细目
	 * 
	 * @param sendOutids 需要删除的发货单总目ids 多个用，分割
	 * @author zxc date 2016-02-21
	 * @throws YhsfServiceException
	 * @修改 wangtao 2016年5月11日10:02:32
	 */
	void delSendoutSummary(String sendOutids) throws Exception;

	/**
	 * 根据ids 查询发货单
	 * 
	 * @param ProSendoutSummaryBacktoCondition
	 * @author zxc date 2016-02-21
	 */
	List<ProSendoutSummaryBacktoDO> queryProSendoutSummary(
			ProSendoutSummaryBacktoCondition pssc);
	
	/**
	 * 保存发货单总目，细目
	 * @param psic
	 *//*
	void saveSendoutSummaryAndItems(List<ProSendoutSummaryBacktoCondition> list);*/

//	public void sendOut(List<ProSendoutSummaryBacktoCondition> summarys);
	
	
	
	


	
	/**
	 * 通过id获得发货单（部分收货，全部收货）。
	 * @param ProSendoutSummaryCondition
	 * @author yxp
	 * date 2016-4-11 18:46:46
	 */
	public  List<ProSendoutSummaryBacktoDO> findSendOutSummaryById(
			ProSendoutSummaryBacktoCondition pssc);
	
	
	/**
	 * 发货单整单删除
	 * @param purchaserId 采购商编码
	 * @param supplierId 供应商编码
	 * @param sendoutGoodsCode 需要删除的发货单号
	 * @throws Exception
	 */
	public void delSendoutSummary(String purchaserId,String supplierId,String sendoutGoodsCode) throws Exception;
	/**
	 * 发货单 总目更新
	 * yangtao 2016-8-4
	 * @param pssc
	 */
	void updateSendoutSummary(ProSendoutSummaryBacktoDO pssc);
	
}
