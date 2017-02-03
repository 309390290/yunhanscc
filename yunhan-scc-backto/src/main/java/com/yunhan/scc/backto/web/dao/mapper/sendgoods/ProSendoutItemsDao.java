package com.yunhan.scc.backto.web.dao.mapper.sendgoods;

import java.util.List;

import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutItemsBacktoDO;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutItemsBacktoCondition;

/**
 * 回告数据操作层
 * @author wangtao
 * @version 2016-7-6 15:29:49
 */
public interface ProSendoutItemsDao{

	/**
	 * @return
	 */
	public List<ProSendoutItemsBacktoDO> queryByPage() throws Exception;
	
	/**
	 * 
	 * 根据订单号+订单细目id+发货单号+发货价+发货折扣查找发货单细目 
	 * @author luohoudong
	 * @version created at 2016-7-20 下午2:44:08
	 * @param psic
	 * @return
	 */
	public List<ProSendoutItemsBacktoDO> findSendOutItemsByOrderCodeAndItemsId(ProSendoutItemsBacktoDO psic);
	
	/**
	 * 
	 * 根据订单号+供应商商品ID+发货单号+发货价+发货折扣查找发货单细目
	 * @author luohoudong
	 * @version created at 2016-7-20 下午2:46:08
	 * @param psic
	 * @return
	 */
	public List<ProSendoutItemsBacktoDO> findSendOutItemsByOrderCodeAndSupplier(ProSendoutItemsBacktoDO psic);
	
	/**
	 * 根据订单号+ISBN+书名+发货单号+发货价+发货折扣查找发货单细目
	 * @author luohoudong
	 * @version created at 2016-7-20 下午2:46:23
	 * @param psic
	 * @return
	 */
	public List<ProSendoutItemsBacktoDO> findSendOutItemsByOrderCodeAndIsbnBook(ProSendoutItemsBacktoDO psic);
	/**
	 * 根据id删除发货单细目 状态为0的 
	 * @author luohoudong
	 * @version created at 2016-7-20 下午4:18:39
	 * @param psic
	 */
	public void delSendoutItemsByIdByStatus(ProSendoutItemsBacktoDO psic);
	/**
	 * 
	 * 新增发货时根据规则检查出若是追加发货则进行数量累加 
	 * @author luohoudong
	 * @version created at 2016-7-20 下午4:20:44
	 * @param psic
	 */
	public void updateSendoutItemsBySave(ProSendoutItemsBacktoDO psic);
	/**
     * 根据发货单号查询发货单细目
     * @param psic
     * @return
     */
	public List<ProSendoutItemsBacktoDO> queryProSendoutItemsBySendcode(ProSendoutItemsBacktoCondition psic);
	/**
	 * 
	 * 更新发货单细目
	 * @author luohoudong
	 * @version created at 2016-7-20 下午4:26:39
	 * @param psic
	 */
	public void updateSendoutItems(ProSendoutItemsBacktoDO psic);
	/**
	 * 保存发货单细目
	 * @author luohoudong
	 * @version created at 2016-7-20 下午4:29:29
	 * @param psic
	 */
	public void saveSendoutItems(ProSendoutItemsBacktoDO psic);
	/**
	 * 
	 * 根据订单细目ID获得最大的IDmax
	 * @author luohoudong
	 * @version created at 2016-7-20 下午2:46:08
	 * @param psic
	 * @return
	 */
	public List<ProSendoutItemsBacktoDO> findMaxIdByProPurOrderItemsId(ProSendoutItemsBacktoDO psic);
	/**
	 * 
	 * 打上删除标记_整单删除
	 * @author luohoudong
	 * @version created at 2016-7-20 下午4:26:39
	 * @param psic
	 */
	public void updateSendoutItemsDeleteAll(ProSendoutItemsBacktoCondition psic);

	/**
	 * 打上删除标记_单发货单删除
	 * @param psic
	 */
	public void updateSendoutItemsDeleteByid(ProSendoutItemsBacktoCondition psic);
	/**
	 * 统计发货单下未删除的细目有多少条
	 * @param psic
	 * @return
	 */
	public Integer findSendOutItemsCount(ProSendoutItemsBacktoDO psic);
	

}