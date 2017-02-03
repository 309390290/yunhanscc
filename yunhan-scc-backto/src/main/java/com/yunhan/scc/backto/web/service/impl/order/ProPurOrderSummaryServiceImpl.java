package com.yunhan.scc.backto.web.service.impl.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhan.scc.backto.web.dao.mapper.order.ProPurOrderDao;
import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.order.ProPurOrderSummaryBacktoDO;
import com.yunhan.scc.backto.web.model.order.ProPurOrderSummaryBacktoCondition;
import com.yunhan.scc.backto.web.service.order.ProPurOrderSummaryService;
import com.yunhan.scc.tools.util.StringUtils;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：ProPurOrderSummaryServiceImpl   
 * 类描述： 订单总目接口service实现类  
 * 创建人：lumin
 * 创建时间：2016-7-15 上午11:24:59   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */
@Service
public class ProPurOrderSummaryServiceImpl implements ProPurOrderSummaryService {
	
	@Autowired
	private ProPurOrderDao proPurOrderDao;

	/**
	 * 获取一个月前待处理品种数
	 */
	@Override
	public Integer getUntreatedMonthAgo(String purchaserId, String sendDateEnd,
			String userCode) throws Exception {
		ProPurOrderSummaryBacktoCondition condition = new ProPurOrderSummaryBacktoCondition();
		condition.setPurchaserId(purchaserId);
		condition.setSendDateEnd(sendDateEnd);
		condition.setUserCode(userCode);
		return proPurOrderDao.getUntreatedMonthAgo(condition);
	}
    
	/**
	 * 获取订单总目数据
	 */
	@Override
	public ProPurOrderSummaryBacktoDO getProPurOrderSummaryDO(Long id,
			String purchaserId) {
		ProPurOrderSummaryBacktoCondition condition = new ProPurOrderSummaryBacktoCondition();
		condition.setPurchaserId(purchaserId);
		condition.setId(id);
		return proPurOrderDao.findProPurOrderSummary(condition);
	}

	@Override
	public void updateOrderSummaryIsView(Integer id,String itemIds,List<ProResponseItemsBacktoDO> backtoDos) throws Exception {
		List<Integer> ids = new ArrayList<Integer>();
		//获取订单细目id
		List<Long> orderItemIds = new ArrayList<Long>();
		//订单详情
		if(id!=null){
			ids.add(id);
		}
		//导出品种
		if(StringUtils.isNotBlank(itemIds)){
			String[] idsArray = itemIds.split(",");
			for(int i=0; i<idsArray.length; i++){
				orderItemIds.add(Long.valueOf(idsArray[i]));
			}
		}
		//回告
		if(backtoDos != null && backtoDos.size()>0){
      			for(ProResponseItemsBacktoDO backtoDo : backtoDos){
				if(backtoDo.getProPurOrderItemsId()!=null) orderItemIds.add(backtoDo.getProPurOrderItemsId());
			}
		}
		//查询订单总目id
		if(orderItemIds.size()>0){
			ids = proPurOrderDao.getOrderSummaryIds(orderItemIds);
		}
		
		if(ids.size()>0)
			proPurOrderDao.updateOrderSummaryIsView(ids);
		
	}
	/**
	 * 根据总目id集合修改查阅标识  多个总目id用,号分隔
	 * @param orderIds 总目id集合
	 * @throws Exception
	 */
	public void updateOrderSummaryIsView(String orderIds) throws Exception {
		List<Integer> ids = new ArrayList<Integer>();
		//订单详情
		if(orderIds!=null){
			String [] idrs = orderIds.split(",");
			for(String id :idrs){
				ids.add(Integer.valueOf(id));
			}
		}
		if(ids.size()>0)
			proPurOrderDao.updateOrderSummaryIsView(ids);
		
	}

}

