package com.yunhan.scc.backto.web.service.order;

import com.yunhan.scc.backto.web.entities.order.ProPurOrderItemsBacktoDO;
import com.yunhan.scc.backto.web.model.order.ProPurOrderItemsBacktoCondition;

import java.util.List;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：ProPurOrderItemsService   
 * 类描述：   订单细目service
 * 创建人：lumin
 * 创建时间：2016-7-21 上午10:56:07   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */

public interface ProPurOrderItemsService {
	
	/**
	 * 
	 * @Description: 根据订单号和采购商id获取订单明细数据
	 * @param @param purchaseOrderCode 采购订单号
	 * @param @param purchaserId 采购商id
	 * @param @return   
	 * @return List<ProPurOrderItemsBacktoDO>  
	 * @throws
	 * @author lumin
	 * @date 2016-7-21
	 */
	public List<ProPurOrderItemsBacktoDO> getProPurOrderItemsBacktoDOs(String purchaseOrderCode,String purchaserId);
	/**
	 * 
	 * @Description: 关闭发货更新订单明细数据,支持批量更新
	 * @param @param ids
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-7-21
	 */
	public void updateOrderItemsForCloseSend(String ids);
	
	public ProPurOrderItemsBacktoDO findItemById(
			ProPurOrderItemsBacktoCondition backtoCondition);
	/**
	 * 
	 * @Description: 获取订单细目数据
	 * @param @param condition
	 * @param @return   
	 * @return List<ProPurOrderItemsBacktoDO>  
	 * @throws
	 * @author yxp
	 * @date 2016-7-21
	 */
	public List<ProPurOrderItemsBacktoDO> findItemByIds(ProPurOrderItemsBacktoCondition condition);
	/**
	 * 
	 * @Description: 关闭或者开启发货更新订单明细数据,支持批量更新
	 * @param otherAvailableReason
	 * @param ids
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-7-21
	 */
	public void updateOrderItemsForCloseSendOrOpenSend(String ids,String otherAvailableReason);

}

