package com.yunhan.scc.backto.web.service.sendgoods;

import java.util.List;

import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutSummaryBacktoDO;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutSummaryBacktoCondition;



/**
 * 回告发货service
 * @author luohoudong
 * @version created at 2016-7-15 下午3:54:59
 */
public interface SendgoodsService {
	 /**
	  * 根据回告细目ids生成发货单头信息
	  * @author luohoudong
	  * @version created at 2016-7-15 下午4:17:20
	  * @param backIds
	  * @return
	  */
	public ProSendoutSummaryBacktoDO getSendoutHeaders(String backIds);
	
	/**
	 * 保存发货单(发货操作)
	 * @author luohoudong
	 * @version created at 2016-7-19 下午2:50:21
	 * @param sendoutSummaryBacktoDO 发货单头信息
	 * @param sendoutItems	发货单细目信息
	 * @return 发货单总目id
	 */
	public String  saveSendOutgoods(ProSendoutSummaryBacktoDO sendoutSummaryBacktoDO,List<ProSendoutItemsBacktoDO> sendoutItems)throws Exception;
	
	
	/**
	 * 
	 * 检查发货单是否可以追加   
	 * 如果 flagNum > 1 则追加的发货单品种已收货 ;
	 * 如果 flagNum = 1  则追加的发货单仓位不一致;
	 * 如果 flagNum = 0  则可以追加 ;
	 * 如果 flagNum = -1 则订单号不重复
	 * @author luohoudong
	 * @version created at 2016-7-20 上午9:41:58
	 * @param sendoutSummaryBacktoCondition 
	 * {仓位:receiveWarehouse 发货单号:sendoutGoodsCode 采购商id：purchaserId 供应商id:supplierId 
	 * 回告细目ids：responseItemsIds 不能为空}
	 * @return flagNum
	 * 
	 */
	public Integer checkSendoutAdditional(ProSendoutSummaryBacktoCondition sendoutSummaryBacktoCondition,List<ProResponseItemsBacktoDO> responItemsDOs);
	/**
	 * 检查本次发货数是否符合需求
	 * @author luohoudong
	 * @version created at 2016-7-20 下午2:28:32
	 * @param orderQty 订数
	 * @param sendQty   已发数
	 * @param thisSendQty	本次发货数
	 * @throws Exception
	 */
	public void checkThisSendQty(Integer orderQty,Integer sendQty,Integer thisSendQty)throws Exception;
	/**
	 *  匹配发货单规则（规则按优先级匹配，如匹配不上，依次延用下一条规则）：
	 * (1) 订单号+订单细目id+发货单号+发货价+发货折扣； 
	 * (2)订单号+供应商商品ID+发货单号+发货价+发货折扣； 
	 * (3)订单号+ISBN+书名+发货单号+发货价+发货折扣；
	 * 没有订单号的就用没订单号的匹配
	 * @author luohoudong
	 * @version created at 2016-7-20 下午2:50:30
	 * @param sendOuntItems
	 * @return
	 */
	public ProSendoutItemsBacktoDO findSendOutItmesWhether(ProSendoutItemsBacktoDO sendOuntItems);
	/**
	 * 回告发货
	 * @author luohoudong
	 * @version created at 2016-7-20 下午4:51:50
	 * @param sendoutSummaryBacktoDO 发货单头信息
	 * @param responItemsDOs  回告发货细目信息
	 * @param source  发货来源(PAGE:页面发货   TEMP:模板发货 B_TEMP:空白模板发货 )
	 *  @return 发货单总目id
	 */
	public String reportAndSendout(ProSendoutSummaryBacktoDO sendoutSummaryBacktoDO,List<ProResponseItemsBacktoDO> responItemsDOs,String source)throws Exception;
	
	/**
	 * 根据ID（传入实体）查询总目信息
	 * @author YT 2016-7-28
	 * @param pssc
	 * @return
	 */
	public List<ProSendoutSummaryBacktoDO> queryProSendoutSummary(ProSendoutSummaryBacktoCondition pssc);
	
	/**
	 * 
	 * @Description: 模板发货批量保存回告数据
	 * @param @param unSendBacktoDOs 不能发货回告的
	 * @param @param SendBacktoDOs 可以发货回告
	 * @param @return
	 * @param @throws Exception   
	 * @return String  
	 * @throws
	 * @author luohoudong
	 */
	public void batchSaveResponseFromTemp(List<ProResponseItemsBacktoDO> SendBacktoDOs,String userCode) throws Exception;
	
	/**
	 * 检查发货单是否可以发货
	 * A:存在品种所属订单的订单种类=馆配订单；
	 * B:存在品种所属订单的订单种类=大中专、团购、活动订书
	 * C:本次发货的品种，所属订单的仓位不一致
	 * D:勾选了直供订单品种，且还有其它订单品种
	 * E:其他采购商已经使用了此发货单号
	 * @author luohoudong
	 * @version created at 2016-8-9 上午10:40:42
	 * @param responItemsDOs
	 * @param summaryBacktoCondition
	 * @return 成功："S"   错误代码：A、B、C、D、E
	 */
	public String checkSendoutIsMaySend(List<ProResponseItemsBacktoDO> responItemsDOs,ProSendoutSummaryBacktoCondition summaryBacktoCondition);
	
}

