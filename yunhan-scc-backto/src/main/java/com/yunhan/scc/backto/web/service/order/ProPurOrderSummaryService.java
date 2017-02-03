package com.yunhan.scc.backto.web.service.order;

import java.util.List;

import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.order.ProPurOrderSummaryBacktoDO;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：ProPurOrderSummaryService   
 * 类描述：   订单总目service接口
 * 创建人：lumin
 * 创建时间：2016-7-15 上午11:24:06   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */

public interface ProPurOrderSummaryService {
	/**
	 * 
	 * @Description: 获取采购商一个月前待处理品种数
	 * @param @param purchaserId 采购商id
	 * @param @param sendDateEnd 订单发送日期
	 * @param @param userCode 用户名
	 * @param @return
	 * @param @throws Exception   
	 * @return Integer  
	 * @throws
	 * @author lumin
	 * @date 2016-7-15
	 */
	public Integer getUntreatedMonthAgo(String purchaserId,String sendDateEnd,String userCode) throws Exception;

	/**
	 * 
	 * @Description: 根据订单主键id和采购商id获取订单总目信息
	 * @param @param id
	 * @param @param purchaserId
	 * @param @return   
	 * @return ProPurOrderSummaryDO  
	 * @throws
	 * @author lumin
	 * @date 2016-7-15
	 */
	public ProPurOrderSummaryBacktoDO getProPurOrderSummaryDO(Long id,String purchaserId) throws Exception;
	
	/**
	 * 
	 * @Description: 更新订单查阅标识
	 * @param @param id 单个订单，供应商通过“订单号链接、订单处理按钮”，进入【单订单详情页面】后，该订单的“订单查阅标识”变更为“已查阅”
	 * @param @param itemIds 订单细目品种id，供应商通过“导出按钮”，下载勾选的品种，则品种所属的订单的“订单查阅标识”变更为“已查阅” 
	 * @param @param responseItemsBacktoDO 品种回告，供应商对品种执行回告、发货操作后，品种状态≠未回告，则品种所属的订单的“订单查阅标识”变更为“已查阅”
	 * 三种情况在同时只能存在其中一种
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-9-2
	 */
	public void updateOrderSummaryIsView(Integer id,String itemIds,List<ProResponseItemsBacktoDO> backtoDos) throws Exception;
	
	/**
	 * 根据总目id集合修改查阅标识  多个总目id用,号分隔
	 * @param orderIds 总目id集合
	 * @throws Exception
	 */
	public void updateOrderSummaryIsView(String orderIds) throws Exception;
}

