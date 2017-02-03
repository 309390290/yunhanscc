package com.yunhan.scc.backto.web.service.impl.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhan.scc.backto.web.dao.mapper.order.ProPurOrderItemsDao;
import com.yunhan.scc.backto.web.entities.order.ProPurOrderItemsBacktoDO;
import com.yunhan.scc.backto.web.model.order.ProPurOrderItemsBacktoCondition;
import com.yunhan.scc.backto.web.service.order.ProPurOrderItemsService;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：ProPurOrderItemsServiceImpl   
 * 类描述：   
 * 创建人：lumin
 * 创建时间：2016-7-21 上午10:59:35   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */
@Service
public class ProPurOrderItemsServiceImpl implements ProPurOrderItemsService {
	
	@Autowired
	private ProPurOrderItemsDao proPurOrderItemsDao;

	@Override
	public List<ProPurOrderItemsBacktoDO> getProPurOrderItemsBacktoDOs(String purchaseOrderCode, String purchaserId) {
		ProPurOrderItemsBacktoCondition condition = new ProPurOrderItemsBacktoCondition();
		condition.setPurchaseOrderCode(purchaseOrderCode);
		condition.setPurchaserId(purchaserId);
		return proPurOrderItemsDao.getProPurOrderItemsBacktoDOs(condition);
	}

	@Override
	public void updateOrderItemsForCloseSend(String ids) {
		Map<String, Object> params = new HashMap<String, Object>();
		String[] id = ids.split(",");
		List<Integer> orderItemIds = new ArrayList<Integer>();
		for (int i = 0; i < id.length; i++) {
			orderItemIds.add(Integer.valueOf(id[i]));
		}
		params.put("closeReason", "供应商关闭");
		params.put("orderItemIds", orderItemIds);
		proPurOrderItemsDao.updateOrderItemsForCloseSend(params);
	}

	@Override
	public ProPurOrderItemsBacktoDO findItemById(
			ProPurOrderItemsBacktoCondition backtoCondition) {
		
		return proPurOrderItemsDao.findItemById(backtoCondition);
	}
	@Override
	public List<ProPurOrderItemsBacktoDO> findItemByIds(ProPurOrderItemsBacktoCondition condition){
		return proPurOrderItemsDao.findItemByIds(condition);
		
	}

	@Override
	public void updateOrderItemsForCloseSendOrOpenSend(String ids,
			String otherAvailableReason) {
		Map<String, Object> params = new HashMap<String, Object>();
		String[] id = ids.split(",");
		List<Integer> orderItemIds = new ArrayList<Integer>();
		for (int i = 0; i < id.length; i++) {
			if(org.apache.commons.lang.StringUtils.isNotBlank(id[i])){
				orderItemIds.add(Integer.valueOf(id[i]));

			}
		}
		params.put("orderItemIds", orderItemIds);
		if(orderItemIds.size()>0){
			//其余满足情况: 0-预计可发、1-暂时缺货、2、已停产，3、改版，4、版权到期，5、商品无效，6、销售受限、7-其他（后面5个都属于永久缺货类型） 9-无货不发
			if(otherAvailableReason==null||otherAvailableReason.equals("0")||otherAvailableReason.equals("1")){
				params.put("closeReason", "供应商开启");
				proPurOrderItemsDao.updateOrderItemsForOpenSend(params);
			}else if(otherAvailableReason.equals("2")||otherAvailableReason.equals("3")||otherAvailableReason.equals("4")||otherAvailableReason.equals("5")||otherAvailableReason.equals("6")||otherAvailableReason.equals("7") || otherAvailableReason.equals("9")){
				params.put("closeReason", "供应商关闭");
				proPurOrderItemsDao.updateOrderItemsForCloseSend(params);
			}
		}
		
		
		
	}
}

