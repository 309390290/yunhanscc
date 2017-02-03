package com.yunhan.scc.backto.interfaceEntrance.service.job;


/**
 * 发送数据加工任务
 * @author luohoudong
 * @version created at 2016-10-17 上午11:48:19
 */
public interface SendDataJobService {
	/**
	 * 发送数据加工任务
	 * @author luohoudong
	 * @version created at 2016-10-17 上午11:52:18
	 * @param sendType (1:回告)
	 * @throws Exception
	 */
	public void processingSendData(Integer sendType) throws Exception;
}
