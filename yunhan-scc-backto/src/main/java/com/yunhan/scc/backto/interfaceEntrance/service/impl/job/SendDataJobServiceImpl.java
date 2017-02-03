package com.yunhan.scc.backto.interfaceEntrance.service.impl.job;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunhan.scc.backto.interfaceEntrance.dao.mapper.backreport.SendDataToInterfaceTDao;
import com.yunhan.scc.backto.interfaceEntrance.service.backreport.BacktoInterFaceService;
import com.yunhan.scc.backto.interfaceEntrance.service.job.SendDataJobService;
import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;

/**
 * 发送数据加工任务逻辑层
 * @author wangtao
 * @version created at 2016-1-5 下午1:22:30
 */
@Service
public class SendDataJobServiceImpl implements SendDataJobService {

	protected static Log log  = LogFactory.getLog(SendDataJobServiceImpl.class);
	
	@Autowired
	private SendDataToInterfaceTDao sendDataToInterfaceTDao;
	@Autowired
	private BacktoInterFaceService backtoInterFaceService;
	
	
	
	/**
	 * 商品流转数据加工
	 * @author wangtao
	 * @version created at 2016-1-5 下午1:35:29
	 * @param sendType (1:回告)
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public synchronized void processingSendData(Integer sendType) throws Exception{
		//调用发送回告
		if(sendType.equals(1)){//调用发送回告到采发接口，然后删除发送接口临时表中对应的回告id
			List<ProResponseItemsBacktoDO> backtoDos=sendDataToInterfaceTDao.findSendResponItems();
			backtoInterFaceService.sendOutBackto(backtoDos);
			for (ProResponseItemsBacktoDO proResponseItemsBacktoDO : backtoDos) {
				sendDataToInterfaceTDao.delete(proResponseItemsBacktoDO.getId());
			}
		}
		
		
		
		
	}
	
	
}
