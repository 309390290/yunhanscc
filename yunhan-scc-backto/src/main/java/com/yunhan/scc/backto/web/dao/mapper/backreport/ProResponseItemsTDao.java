package com.yunhan.scc.backto.web.dao.mapper.backreport;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsTBacktoDO;
import com.yunhan.scc.backto.web.model.backreport.ProResponseItemsTBacktoCondition;

/**
 *ProResponseItemsT数据操作层
 * @author luohoudong
 * @version 2016-7-14 9:52:28
 */
public interface ProResponseItemsTDao{
	
	/**
	 * 保存临时回告发货信息
	 * @author luohoudong
	 * @version created at 2016-7-14 下午3:49:27
	 * @param responseItemsTDO
	 */
	public void save(ProResponseItemsTBacktoDO responseItemsTDO);
	/**
	 * 根据用户名删除临时回告信息
	 * @author luohoudong
	 * @version created at 2016-7-14 下午4:03:31
	 * @param userName
	 * @param flag ( report:只删回告信息)
	 */
	public void delProResponseItemsTsByUserName(@Param("addUserCode")String userName,@Param("flag")String flag);
	
	/**
	 * 根据创建人编码查询出可以模板发货的发货单号 
	 * @author luohoudong
	 * @version created at 2016-7-27 下午4:37:22
	 * @param userCode
	 * @return
	 */
	public List<String> findProResponseItemsTSendoutGoods(@Param("addUserCode")String addUserCode);
	/**
	 * 根据用户名和发货单号删除临时回告信息
	 * @author luohoudong
	 * @version created at 2016-8-2 下午3:15:24
	 * @param userName
	 * @param sendCode
	 */
	public void delProResponseItemsTsByUserNameAndSendCode(@Param("addUserCode")String userName,@Param("sendCode")String sendCode);
	/**
	 * 查看只回告数据条数
	 * @author luohoudong
	 * @version created at 2016-9-9 下午3:58:34
	 * @param condition
	 * @return
	 */
	public Integer findOnlyReportItemsTs_count(ProResponseItemsTBacktoCondition condition);

}