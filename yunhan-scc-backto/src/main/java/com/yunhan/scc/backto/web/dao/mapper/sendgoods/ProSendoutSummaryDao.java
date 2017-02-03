package com.yunhan.scc.backto.web.dao.mapper.sendgoods;

import java.util.List;

import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutSummaryBacktoDO;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutSummaryBacktoCondition;

/**
 *ProSendoutSummary数据操作层
 * @author luohoudong
 * @version 2016-7-15 16:04:14
 */
public interface ProSendoutSummaryDao{

	/**
	  * 根据回告细目ids生成发货单头信息
	  * @author luohoudong
	  * @version created at 2016-7-15 下午4:17:20
	  * @param backIds
	  * @return
	  */
	public ProSendoutSummaryBacktoDO getSendoutHeaders(ProSendoutSummaryBacktoCondition proSendoutSummaryBacktoCondition);
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
	public List<Integer> checkSendoutAdditional(ProSendoutSummaryBacktoCondition sendoutSummaryBacktoCondition);
	
	/**
	 * 
	 * 保存发货单总目信息(用于回告发货--码洋、实洋  根据发货单细目计算)
	 * @author luohoudong
	 * @version created at 2016-7-21 下午4:30:48
	 * @param proSendoutSummaryBacktoDO
	 */
	public void saveProSendoutSummaryDO(ProSendoutSummaryBacktoDO proSendoutSummaryBacktoDO);
	
	/**
	 * 追加发货时,更新发货单总目信息(用于回告发货--码洋、实洋  根据发货单细目计算)
	 * @author luohoudong
	 * @version created at 2016-7-27 上午9:27:23
	 * @param proSendoutSummaryBacktoDO
	 */
	public void  updateProSendoutSummaryDO(ProSendoutSummaryBacktoDO proSendoutSummaryBacktoDO);
	
	/**
	 * 查询发货单总目信息
	 * @author luohoudong
	 * @version created at 2016-7-26 下午5:12:23
	 * @param proSendoutSummaryBacktoCondition
	 * @return
	 */
	public ProSendoutSummaryBacktoDO findProSendoutSummaryBySendoutGoodsCode(ProSendoutSummaryBacktoCondition sendoutSummaryBacktoCondition);
	/**
	 * 根据ID（传入实体）查询总目信息
	 * @author YT 2016-7-28
	 * @param pssc
	 * @return
	 */
	public List<ProSendoutSummaryBacktoDO> queryProSendoutSummary(ProSendoutSummaryBacktoCondition pssc);
	/**
	 * 通过id获得发货单（部分收货，全部收货）。
	 * @param ProSendoutSummaryCondition
	 * @author yxp
	 * date 2016-4-11 18:46:46
	 */
	List<ProSendoutSummaryBacktoDO> findSendOutSummaryById(
			ProSendoutSummaryBacktoCondition pssc);
	
	/**
	 * 更新发货单总目
	 * @author yxp
	 * @version created at 2016年8月2日14:12:34
	 * @param proSendoutSummaryBacktoDO
	 */
	public void  updateProSendoutSummaryDelete(ProSendoutSummaryBacktoCondition sendoutSummaryBacktoCondition);
	/**
	 * 更新发货单总目信息
	 * yangtao 2016-8-4
	 * @param pssc
	 */
	public void update(ProSendoutSummaryBacktoDO pssc);
	
	/**
	 * 发货总目重新计算  总实洋 总码洋 发货总册数 发货品种总数  
	 * @param sendoutGoodsCode 发货单号
	 * @param supplierId 供应商id
	 * @param  purchaserId 采购商id
	 */
	public void reCalculationSendOutSummary(ProSendoutSummaryBacktoCondition pssc);
	
	/**
	 * 查看供应商发货单号是否已经被其他采购商使用
	 * @author luohoudong
	 * @version created at 2016-8-12 下午3:09:22
	 * @param sendoutSummaryBacktoCondition
	 * @return
	 */
	public Integer findSupplierIdSendoutGoodsIsExist(ProSendoutSummaryBacktoCondition sendoutSummaryBacktoCondition);
	
}