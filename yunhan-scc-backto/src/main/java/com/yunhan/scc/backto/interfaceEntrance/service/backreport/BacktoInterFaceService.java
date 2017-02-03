package com.yunhan.scc.backto.interfaceEntrance.service.backreport;

import java.util.List;

import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;

/**
 * 回告对外接口
 * @author wangtao
 * @version created at 2016-3-8 上午9:41:49
 */
public interface BacktoInterFaceService {

	/**
	 * 发送回告信息到采发
	 * @author wangtao
	 * @version created at 2016-3-8 上午10:11:00
	 * @param backtoDos 回告信息
	 */
	public void sendOutBackto(List<ProResponseItemsBacktoDO> backtoDos)throws Exception;
	/**
	 * 批量保存待发送信息
	 * @author luohoudong
	 * @version created at 2016-10-17 上午11:32:02
	 * @param sourceDataIds  业务数据主键ids
	 * @param sendType	发送的业务类型：1-回告数据
	 * @throws Exception 	
	 */
	public void batchSaveSendDatas(List<Long> sourceDataIds,Integer sendType)throws Exception;
	
}
