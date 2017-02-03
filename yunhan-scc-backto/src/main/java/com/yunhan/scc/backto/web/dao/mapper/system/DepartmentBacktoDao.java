package com.yunhan.scc.backto.web.dao.mapper.system;

import java.util.List;
import java.util.Map;

import com.yunhan.scc.trading.web.entities.system.Department;

/**
 * 系统数据服务
 * @author wuyounan
 * @version created at 2016年10月21日 
 */
public interface DepartmentBacktoDao {
	
	/**
	 * 根据商家id获取商家信息
	 * @author wuyounan
	 * @version created at 2016-10-21
	 * @param purchaserId
	 * @return
	 */
	public Department getDepartmentBySapverdorId(String purchaserId);
	
	/**
	 * 供应商查询所管采购商
	 * @author wuyounan
	 * @version created at 2016年10月21日
	 * @param userCode
	 * @return 
	 */
	public List<Department> selectMyPur(String userCode);
	
	/**
	 * 获取所有供应商
	 * @author wuyounan
	 * @param map
	 * @return
	 */
	public List<Department> selectSupplierList(Map<String, Object> map);
	
	/**
	 * 根据供应商ID获取 有商务关系的采购商
	 * @author wuyounan
	 * @version created at 2016年10月21日
	 * @param supplierId
	 * @return 
	 */
	public List<Department> selectDepartmentBySupplierId(String supplierId);

	public List<Department> findDepartmentBySapvendorIdorNameByUser(Map<String, Object> condi);
}
