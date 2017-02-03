package com.yunhan.scc.backto.web.service.system;

import java.util.List;

import com.yunhan.scc.oauth2.web.entities.security.Department;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：SystemBacktoService   
 * 类描述：   
 * 创建人：lumin
 * 创建时间：2016-7-13 下午2:07:04   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */

public interface SystemBacktoService {
	
	/**
	 * 
	 * @Description: 获取采购商用户采购商列表
	 * @param @param userCode
	 * @param @return   
	 * @return List<Department>  
	 * @throws
	 * @author lumin
	 * @date 2016-7-13
	 */
	public List<Department> selectMyPur(String userCode);
	
	/**
	 * 修改订单状态-存储过程方式
	 * @param proPurOrderItemsId 订单细目id
	 * @param purchaserId 采购商编码
	 * @param userCode 发货人员（当前登录人员）
	 * @param iOpFlag 0-回告发货,1-删除回告发货 
	 * @return
	 */
	public String setOrderStatus(String proPurOrderItemsId,String purchaserId,String userCode,String iOpFlag) throws Exception;

	
	/**
	 * 修改订单状态-存储过程方式
	 * @param proPurOrderItemsIds 订单细目id 多个用英文逗号分隔
	 * @param purchaserId 采购商编码
	 * @param userCode 发货人员（当前登录人员）
	 * @param iOpFlag 0-回告发货,1-删除回告发货 
	 * @return
	 */
	public void updateOrderState(String userCode, String purchaserId, String proPurOrderItemsIds,String iOpFlag) throws Exception;
	
	/**
	 * 
	 * @Description: 修改导出状态
	 * @param ids 数据ids（多个用逗号隔开）
	 * @param type 数据类型  默认为空 若为空则为订单细目   orderSummary 订单总目
	 * @return void  
	 * @throws Exception 
	 * @throws
	 * @author zwj
	 * @date 2016-7-25
	 */
	public void updateExportState(String ids,String type)throws Exception;
	
	/**
	 * 
	 * @Description: 根据采购商id获取采购商信息
	 * @param @param purchaserId
	 * @param @return   
	 * @return Department  
	 * @throws Exception 
	 * @throws
	 * @author lumin
	 * @date 2016-7-28
	 */
	public Department getDepartmentByPurchaserId(String purchaserId) throws Exception;
	
	/**
	 * 下载标识记录,整单下载标识计算（新增或修改）
	 * @param userCode 下载者
	 * @param dataIds 下载数据id
	 * @author wangtao
	 * 2016年7月26日10:53:55
	 */
	public void saveOrUpdateNodeUpByOrderSum(String userCode,String [] dataIds)throws Exception;

}

