package com.yunhan.scc.backto.web.service.system;

import java.util.List;
import java.util.Map;

import com.yunhan.scc.trading.web.entities.system.Department;


/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：DepartmentBacktoService   
 * 类描述：   
 * 创建人：wuyounan
 * 创建时间：2016-10-21
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */

public interface DepartmentBacktoService {
	
	/**
	 * 
	 * @Description: 获取供应商用户管理采购商列表
	 * @param @param userCode
	 * @param @return   
	 * @return List<Department>  
	 * @throws
	 * @author wuyounan
	 * @date 2016-10-21
	 */
	public List<Department> selectMyPur(String userCode) throws Exception;
	
	/**
	 * 
	 * @Description: 根据商家id获取商家信息
	 * @param @param purchaserId
	 * @param @return   
	 * @return Department  
	 * @throws Exception 
	 * @throws
	 * @author wuyounan
	 * @date 2016-10-21
	 */
	public Department getDepartmentByPurchaserId(String purchaserId) throws Exception;
	
	/**
	 * 获取所有供应商
	 * @author wuyounan
	 * @param map
	 * @return List<Department>
	 * @throws Exception 
	 * @date 2016-10-21
	 */
	public List<Department> selectSupplierList(Map<String, Object> map) throws Exception;
	
	/**
	 * 根据供应商 数据ID获取 有商务关系的采购商
	 * @author wuyounan
	 * @param map
	 * @return List<Department>
	 * @throws Exception 
	 * @date 2016-10-21
	 */
	public List<Department> selectSupplierList(String supplierId) throws Exception;

	public List<Department> findDepartmentBySapvendorIdorName(String term, String term2, int i);
}

