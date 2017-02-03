package com.yunhan.scc.backto.web.service.sendQuery;

import java.util.List;

import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.SENDOUTNUMBERS;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutItemsBacktoCondition;
import com.yunhan.scc.tools.exception.YhsfServiceException;

/**
 * 发货单Service
 * 
 * @author zxc
 * @version created at 2016年2月19日 上午11:39:09
 */
public interface ProSendoutItemsBacktoService {

	/**
	 * 根据发货单号查询发货单细目
	 * 
	 * @param psic
	 * @return
	 */
	List<ProSendoutItemsBacktoDO> queryProSendoutItems(ProSendoutItemsBacktoCondition psic) throws Exception;

	/**
	 * 根据发货单细目ID删除发货单细目 并验证是否删除发货单总目
	 * 
	 * @param id
	 * @throws Exception
	 */
	void delSendoutItemsById(Long id) throws Exception;
	

	/**
     * 对传过来的了list校验 查看是否有发货资格
     * @param sendItemsList
     * @return
     * @author yxp  2016-6-1 10:14:36
     * 
     */
	public SENDOUTNUMBERS getRigthUpdateOrDeleteProSendSendItems(List<ProSendoutItemsBacktoDO> sendItemsList);
	/**
	 * 更新发货信息 2016-8-10
	 * @param sendoutItemsBacktoDO
	 */
	public void updateSendoutItem(ProSendoutItemsBacktoDO sendoutItemsBacktoDO,ProResponseItemsBacktoDO proResponse,String proPurOrderItemsId) throws Exception;

	/**
	 * 根据发货信息删除回告信息
	 * @param backto
	 * wangtao
	 */
	public void delResponse(Long backtoId);
	
}
