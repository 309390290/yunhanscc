package com.yunhan.scc.backto.web.dao.mapper.system;

import java.util.List;
import java.util.Map;

import com.yunhan.scc.backto.web.entities.system.TmpOrderItemsDo;
import com.yunhan.scc.oauth2.web.entities.security.Department;

/**
 * 系统数据服务
 * @author pangzhenhua
 * @version created at 2016年2月24日 上午11:50:06
 */
public interface SystemBacktoDao {
	
	/**
	 * 根据采购商id获取采购商信息
	 * @author shiwenbo
	 * @version created at 2016-3-8 下午5:00:20
	 * @param purchaserId
	 * @return
	 */
	public Department getDepartmentBySapverdorId(String purchaserId);
	
	/**
	 * 供应商查询所管采购商
	 * @author pangzhenhua
	 * @version created at 2016年2月25日 下午4:27:15
	 * @param userCode
	 * @return 
	 */
	public List<Department> selectMyPur(String userCode);
	
	
	/**
	 * 
	 * @Description: 查询已关闭品种待办统计
	 * @param @param params 
	 * @param @return
	 * @param @throws Exception   
	 * @return Integer  
	 * @throws
	 * @author lumin
	 * @date 2016-7-28
	 */
	public Integer getCloseOrderItemsCount(Map<String, Object> params) throws Exception;
	
	/**
	 * 
	 * @Description:  更新从待办进入查看已关闭品种为已查看，待办清零
	 * @param @param params
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author lumin
	 * @date 2016-7-28
	 */
	public void updateOrderItemViewed(Map<String, Object> params) throws Exception;
	
	/**
	 * 调用存储过程设置订单状态
	 * @author wangtao
	 * @version created at 2016年7月20日16:25:09
	 */
	public void setOrderStatus(Map<String, Object> para);
	
	/**
	 * 下载标识记录（新增或修改）
	 * @param userCode 下载者
	 * @param dataIds 下载数据id
	 * @param dataType 数据类型
	 * @param nodeTp 下载数据节点
	 * @author wangtao
	 * 2016年7月26日10:53:55
	 */
	public void saveOrUpdateNodeUp(Map<String, Object> param);
	
	/**
	 * 下载标识记录（新增或修改）
	 * @param userCode 下载者
	 * @param dataIds 下载数据id
	 * @param dataType 数据类型
	 * @param nodeTp 下载数据节点
	 * @author wangtao
	 * 2016年9月28日09:58:33
	 */
	public void saveOrUpdateNodeUpByOrderSum(Map<String, Object> param);
	
	/**
	 * 保存需要计算订单状态的细目id
	 * @param tmpOrderItemsDos
	 */
	public void saveTmpOrderIds(List<TmpOrderItemsDo> tmpOrderItemsDos);
	
	/**
	 * 调用存储过程设置订单状态（批量）
	 * @author wangtao
	 * @version created at 2016年7月20日16:25:09
	 */
	public void setOrderStatusByBatch(Map<String, Object> para);
}
