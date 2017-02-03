package com.yunhan.scc.backto.interfaceEntrance.dao.mapper.backreport;

import java.util.List;

import com.yunhan.scc.backto.interfaceEntrance.entities.backreport.SendDataToInterfaceTDO;
import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;

/**
 * 业务数据推送给接口的临时表 DAO
 * @author luohoudong
 * @version 2016-10-17 10:55:27
 */
public interface SendDataToInterfaceTDao {
	/**
	 * 新增待发送回告信息
	 * @author luohoudong
	 * @version created at 2016-10-17 上午11:09:33
	 * @param sendDataToInterfaceTDO
	 */
	public void save(SendDataToInterfaceTDO sendDataToInterfaceTDO);
	/**
	 * 
	 * @author luohoudong
	 * @version created at 2016-10-17 上午11:15:40
	 * @param id
	 */
	public void delete(Long sourceDataId);
	/**
	 *查询需要发送的回告数据
	 * @author luohoudong
	 * @version created at 2016-10-17 下午1:26:33
	 * @param sendType
	 * @return
	 */
	public List<ProResponseItemsBacktoDO> findSendResponItems();
	

	
}
