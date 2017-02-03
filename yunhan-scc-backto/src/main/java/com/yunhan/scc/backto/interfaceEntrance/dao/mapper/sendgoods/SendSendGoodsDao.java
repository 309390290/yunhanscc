package com.yunhan.scc.backto.interfaceEntrance.dao.mapper.sendgoods;

import java.util.List;
import java.util.Map;

import com.yunhan.scc.backto.interfaceEntrance.entities.sendgoods.SendSendoutSummaryDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutSummaryBacktoDO;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutSummaryBacktoCondition;
/**
 * 发货单总目Dao
 * @author wangtao
 * @version 2016年7月21日16:49:56
 */
public interface SendSendGoodsDao {

	
	/**
	 * 通过发货单号查询发货单总目和细目信息
	 * @param pssc 发货单号集合
	 * @return 发货单总目加细目
	 */
	public List<SendSendoutSummaryDO> send_sendOutSummaryAndItems(ProSendoutSummaryBacktoCondition pssc);
	
	/**
	 * 根据发货造回告数据（接口专用）
	 * @author wangtao
	 * @version created at 2016-3-28 下午4:40:23
	 * @param backtoCondition 发货单号、采购商ID、供应商ID
	 * @return 消息（成功或失败）
	 */
	public void instPesponseItemsCall(Map<String, String> param);
}
