package com.yunhan.scc.backto.web.dao.mapper.backreport;

import java.util.List;

import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;
import com.yunhan.scc.backto.web.model.backreport.ProResponseItemsBacktoCondition;
/**
 *ProResponseItems数据操作层
 * @author luohoudong
 * @version 2016-7-18 15:43:58
 */
public interface ProResponseItemsDao{
	
	/**
	 * 
	 * @Description: 保存
	 * @param @param proResponseItemsBacktoDO   
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-7-21
	 */
	public void saveResponseItems(ProResponseItemsBacktoDO proResponseItemsBacktoDO);
	
	/**
	 * 
	 * @Description: 更新
	 * @param @param proResponseItemsBacktoDO   
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-7-21
	 */
	public void updateResponseItems(ProResponseItemsBacktoDO proResponseItemsBacktoDO);

	/**
	 * 
	 * @Description: 获取回告数据
	 * @param @param condition
	 * @param @return   
	 * @return List<ProResponseItemsBacktoDO>  
	 * @throws
	 * @author lumin
	 * @date 2016-7-21
	 */
	public List<ProResponseItemsBacktoDO> getProResponseItemsById(ProResponseItemsBacktoCondition condition);
	/**
	 * 根据回告细目ids修改回告细目状态为5-已回告
	 * @author luohoudong
	 * @version created at 2016-7-21 下午3:49:17
	 * @param responItemsIds
	 */
	public void updateResponStatus(List<Long> responItemsIds);
	/**
	 * 查询要回告的订单细目ids
	 * @author luohoudong
	 * @version created at 2016-8-19 下午3:12:50
	 * @param responItemsIds
	 * @return
	 */
	public String findReportOrderItemsIds(List<Long> responItemsIds);
	
	/**
	 * 根据回告细目ids查询回告细目信息
	 * @author luohoudong
	 * @version created at 2016-7-21 下午4:10:05
	 * @param responItemsIds
	 */
	public List<ProResponseItemsBacktoDO> findResponItemsByIds(List<Long> responItemsIds);
	/**
	 * 修改ID比自己大的回告信息 yangtao 2016-8-4
	 * @param proResponse
	 */
	public void updateResponseItemsIsValid(ProResponseItemsBacktoDO proResponse);
	
	/**
	 * 根据发货细目id获取回告单id
	 * @param proSendoutItemsId 发货细目id
	 * @return
	 */
	public ProResponseItemsBacktoDO findResponseItemsBySendoutId(Long proSendoutItemsId);
	
	
	/**
	 * 更新回告信息(暂时只更新发货单细目id，如需更新其他信息，请自行添加)
	 * @author luohoudong
	 * @version created at 2016-8-11 下午3:18:19
	 * @param proResponseItemsBacktoDO
	 */
	public void updateResponseItemsDO(ProResponseItemsBacktoDO proResponseItemsBacktoDO);
	/**
	 *  当多次回告对应一次发货，发货发生修改关系。只保留最后一次回告。前面回告删除。 yxp  2016年8月29日09:48:22
	 * @param proResponse
	 */
	public void updateResponseItemsIsValidForUpdateSend(ProResponseItemsBacktoDO proResponse);

}