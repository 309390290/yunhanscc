package com.yunhan.scc.backto.web.service.backreport;

import java.util.List;

import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.order.ProPurOrderItemsBacktoDO;
import com.yunhan.scc.backto.web.model.backreport.ProResponseItemsBacktoCondition;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：ProResponseItemsService   
 * 类描述：   
 * 创建人：lumin
 * 创建时间：2016-7-21 上午9:37:33   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */

public interface ProResponseItemsService {
	
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
	public List<ProResponseItemsBacktoDO> getProResponseItemsById(ProResponseItemsBacktoCondition condition) throws Exception;
	
	/**
	 * 
	 * @Description: 保存关闭发货
	 * @param @param orderItemsId  订单细目ids
	 * @param @param notEnoughReason  关闭原因
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-7-21
	 */
	public void saveProResponseItemForCloseSend(String orderItemsId,
			String notEnoughReason,List<ProPurOrderItemsBacktoDO> backtoDOs) throws Exception;
	
	/**
	 * 
	 * @Description: 批量保存回告数据
	 * @param @param unSendBacktoDOs 不能发货回告的
	 * @param  SendBacktoDOs 可以发货回告
	 * @param  source  发货来源(PAGE:页面发货   TEMP:模板发货  )
	 * @param @return
	 * @param @throws Exception   
	 * @return String  
	 * @throws
	 * @author lumin
	 * @date 2016-7-22
	 */
	public String batchSaveResponse(List<ProResponseItemsBacktoDO> SendBacktoDOs,String userCode,String source) throws Exception;
	
	/**
	 * 
	 * @Description: 根据订单细目数据生产待写入回告数据
	 * @param @param item
	 * @param @return   
	 * @return ProResponseItemsBacktoDO  
	 * @throws
	 * @author lumin
	 * @date 2016-7-21
	 */
	public ProResponseItemsBacktoDO responseItemPackage(ProPurOrderItemsBacktoDO item, String notEnoughReason,ProResponseItemsBacktoDO backtoDO);
	
	/**
	 * 更新回告信息 yangtao 2016-8-1
	 * @param proResponse
	 * @throws Exception
	 */
	public void updateResponse(ProResponseItemsBacktoDO proResponse)throws Exception ;
	
	/**
	 * 修改ID比自己大的回告信息 yangtao 2016-8-4
	 * @param proResponse
	 */
	public void updateResponseItemsIsValid(ProResponseItemsBacktoDO proResponse);
	
	/**
	 * 
	 * @Description: 回告暂存
	 * @param @param SendBacktoDOs
	 * @param @param userCode
	 * @param @return
	 * @param @throws Exception   
	 * @return String  
	 * @throws
	 * @author lumin
	 * @date 2016-8-5
	 */
	public String saveTempResponse(List<ProResponseItemsBacktoDO> sendBacktoDOs,String userCode) throws Exception;
	/**
	 *  当多次回告对应一次发货，发货发生修改关系。只保留最后一次回告。前面回告删除。 yxp  2016年8月29日09:48:22
	 * @param proResponse
	 */
	public void updateResponseItemsIsValidForUpdateSend(ProResponseItemsBacktoDO proResponse);
	
	
}

