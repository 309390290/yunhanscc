package com.yunhan.scc.backto.web.service.backreport;

import java.util.List;
import java.util.Map;

import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsTBacktoDO;
import com.yunhan.scc.backto.web.model.backreport.ProResponseItemsTBacktoCondition;
import com.yunhan.scc.trading.web.entities.system.Operator;



/**
 * 临时回告发货信息service
 * @author luohoudong
 * @version created at 2016-7-14 下午3:45:16
 */
public interface ProResponseItemsTService {
	/**
	 * 保存临时回告信息
	 * @author luohoudong
	 * @version created at 2016-7-14 下午3:49:27
	 * @param responseItemsTDO
	 */
	public void save(ProResponseItemsTBacktoDO responseItemsTDO);
	
	/**
	 * 解析临时回告信息
	 * @author luohoudong
	 * @version created at 2016-7-14 下午3:55:29
	 * @param files
	 * @param operator
	 * @param purchaserIds
	 * @throws Exception
	 * @return Map<String,Object>
	 */
	public Map<String,Object> resolveProResponseItemsT(List<String> files,Operator operator,String purchaserId)throws Exception;
	
	/**
	 * 空白模板解析临时回告信息
	 * @author luohoudong
	 * @version created at 2016-7-14 下午3:55:29
	 * @param files
	 * @param operator
	 * @param purchaserIds
	 * @param warehouse
	 * @throws Exception
	 * @return Map<String,Object>
	 */
	public Map<String,Object> resolveProResponseItemsTFromBlank(List<String> files,Operator operator,String purchaserId,String warehouse)throws Exception;
	/**
	 * 根据创建人编码查询出可以模板发货的发货单号 
	 * @author luohoudong
	 * @version created at 2016-7-27 下午4:37:22
	 * @param userCode
	 * @return
	 */
	public List<String> findProResponseItemsTSendoutGoods(String addUserCode);
	
	/**
	 * 查看只回告数据条数
	 * @author luohoudong
	 * @version created at 2016-9-9 下午3:58:34
	 * @param condition
	 * @return
	 */
	public Integer findOnlyReportItemsTs_count(ProResponseItemsTBacktoCondition condition);
	

}

