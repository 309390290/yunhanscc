package com.yunhan.scc.backto.web.dao.mapper.masterdata;

import java.util.List;

import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;
import com.yunhan.scc.backto.web.model.backreport.ProResponseItemsBacktoCondition;
/**
 *ProResponseItems数据操作层
 * @author luohoudong
 * @version 2016-7-18 15:43:58
 */
public interface MasterdataDao{
	
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
	 * 根据回告细目ids查询回告细目信息
	 * @author luohoudong
	 * @version created at 2016-7-21 下午4:10:05
	 * @param responItemsIds
	 */
	public List<ProResponseItemsBacktoDO> findResponItemsByIds(List<Long> responItemsIds);

}