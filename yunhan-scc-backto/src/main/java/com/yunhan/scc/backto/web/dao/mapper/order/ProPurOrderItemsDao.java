package com.yunhan.scc.backto.web.dao.mapper.order;

import java.util.List;
import java.util.Map;

import com.yunhan.scc.backto.web.entities.order.ProPurOrderItemsBacktoDO;
import com.yunhan.scc.backto.web.model.order.ProPurOrderItemsBacktoCondition;
import com.yunhan.scc.backto.web.model.order.ProPurOrderSummaryBacktoCondition;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：ProPurOrderItemsDao   
 * 类描述：   
 * 创建人：lumin
 * 创建时间：2016-7-18 上午11:25:03   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */

public interface ProPurOrderItemsDao {
	/**
	 * 根据订单细目id查询订单细目信息
	 * @author luohoudong
	 * @version created at 2016-7-21 上午11:15:53
	 * @param id
	 * @return
	 */
	public ProPurOrderItemsBacktoDO getProPurOrderItemsBacktoById(Long id);
	/**
	 * 
	 * @Description: 获取订单细目数据
	 * @param @param condition
	 * @param @return   
	 * @return List<ProPurOrderItemsBacktoDO>  
	 * @throws
	 * @author lumin
	 * @date 2016-7-21
	 */
	public List<ProPurOrderItemsBacktoDO> getProPurOrderItemsBacktoDOs(ProPurOrderItemsBacktoCondition condition);
	
	/**
	 * 根据采购订单号,订单类型、采购商id,供应商id查询管配的订单细目
	 * @author luohoudong
	 * @version created at 2016-8-9 下午3:45:28
	 * @param condition
	 * @return
	 */
	public List<ProPurOrderItemsBacktoDO> findProPurOrderItemsBacktoDOs(ProPurOrderSummaryBacktoCondition condition);
	/**
	 * 
	 * @Description: 关闭发货更新订单明细数据
	 * @param @param params   
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-7-21
	 */
	public void updateOrderItemsForCloseSend(Map<String, Object> params);
	/**
	 * 
	 * @Description: 开启发货更新订单明细数据
	 * @param @param params   
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-7-21
	 */
	public void updateOrderItemsForOpenSend(Map<String, Object> params);
	/**
	 * 更新订单细目信息
	 * @author luohoudong
	 * @version created at 2016-8-11 下午4:06:00
	 * @param proPurOrderItemsBacktoDO
	 */
	public void updateOrderItems(ProPurOrderItemsBacktoDO proPurOrderItemsBacktoDO);
	/**
	 * 根据ID查询 细目信息
	 * @author YangTao 2016-7-21
	 * @param backtoCondition
	 * @return
	 */
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
	 * 查询订单细目信息
	 * @author luohoudong
	 * @version created at 2016-8-10 下午2:35:30
	 * @param condition
	 * @return
	 */
	public List<ProPurOrderItemsBacktoDO> findProPurOrderItems(ProPurOrderItemsBacktoCondition condition);

	
	/**
	 * 下面四个方法 用于根据接口发送过来的数据查询订单细目ID 一级比一级查询条件宽松
	 * 四个方法都是用于接口使用的。
	 * @author YangTao
	 * @param supplierCommoditiesId 供应商商品ID
	 * @return
	 */
	public List<ProPurOrderItemsBacktoDO> findOrderIdbySendout_scmid(ProPurOrderItemsBacktoCondition ppoibc);
	public List<ProPurOrderItemsBacktoDO> findOrderIdbySendout_ppoid(ProPurOrderItemsBacktoCondition ppoibc);
	public List<ProPurOrderItemsBacktoDO> findOrderIdbySendout_isbn_price(ProPurOrderItemsBacktoCondition ppoibc);
	public List<ProPurOrderItemsBacktoDO> findOrderIdbySendout_isbn(ProPurOrderItemsBacktoCondition ppoibc);
	public List<ProPurOrderItemsBacktoDO> findOrderIdbySendout_jtgs_isbn_book_pice(ProPurOrderItemsBacktoCondition ppoibc);

}

