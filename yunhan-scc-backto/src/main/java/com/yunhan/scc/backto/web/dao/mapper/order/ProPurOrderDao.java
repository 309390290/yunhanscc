package com.yunhan.scc.backto.web.dao.mapper.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yunhan.scc.backto.web.entities.order.ProPurOrderItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.order.ProPurOrderSummaryBacktoDO;
import com.yunhan.scc.backto.web.model.order.ProPurOrderItemsBacktoCondition;
import com.yunhan.scc.backto.web.model.order.ProPurOrderSummaryBacktoCondition;

/**
 * 订单数据操作类
 * @author wangtao
 * 2016年7月6日15:51:46
 */
public interface ProPurOrderDao {
	/**
	 * 查询待处理品种
	 * @param condition
	 * @return 待处理品种
	 */
	public List<ProPurOrderItemsBacktoDO> queryPendingOrderItemsByPage(ProPurOrderItemsBacktoCondition condition);
	/**
	 * 
	 * @Description: 获取一个月前待处理品种数
	 * @param @param condition
	 * @param @return   
	 * @return Integer  
	 * @throws
	 * @author lumin
	 * @date 2016-7-15
	 */
	public Integer getUntreatedMonthAgo(ProPurOrderSummaryBacktoCondition condition);
	/**
	 * 
	 * @Description: 获取订单总目数据
	 * @param @param condition
	 * @param @return   
	 * @return ProPurOrderSummaryDO  
	 * @throws
	 * @author lumin
	 * @date 2016-7-15
	 */
	public ProPurOrderSummaryBacktoDO findProPurOrderSummary(ProPurOrderSummaryBacktoCondition condition);
	
	public ProPurOrderItemsBacktoDO findItemById(
			ProPurOrderItemsBacktoCondition backtoCondition);
	
	/**
	 * 
	 * @Description: 根据id更新订单总目的查阅状态为已查阅
	 * @param @param id   
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-9-2
	 */
	public void updateOrderSummaryIsView(@Param("ids") List<Integer> ids);
	
	/**
	 * 
	 * @Description: 获取订单总目id
	 * @param @param ids
	 * @param @return   
	 * @return List<Integer>  
	 * @throws
	 * @author lumin
	 * @date 2016-9-2
	 */
	public List<Integer> getOrderSummaryIds(@Param("ids") List<Long> ids);
}
