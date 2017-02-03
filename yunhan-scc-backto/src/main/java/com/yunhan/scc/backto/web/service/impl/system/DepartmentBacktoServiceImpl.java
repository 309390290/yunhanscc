package com.yunhan.scc.backto.web.service.impl.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhan.scc.backto.web.dao.mapper.system.DepartmentBacktoDao;
import com.yunhan.scc.backto.web.service.system.DepartmentBacktoService;
import com.yunhan.scc.trading.web.entities.system.Department;


/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：SystemBacktoServiceImpl   
 * 类描述：   
 * 创建人：wuyounan
 * 创建时间：2016-10-21  
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */
@Service
public class DepartmentBacktoServiceImpl implements DepartmentBacktoService{
	private Logger log =  Logger.getLogger(DepartmentBacktoServiceImpl.class);
	@Autowired
	private DepartmentBacktoDao departmentBacktoDao;
	
	/**
	 * 获取用户采购商列表
	 */
	@Override
	public List<Department> selectMyPur(String userCode) throws Exception{
		
		return departmentBacktoDao.selectMyPur(userCode);
	}
	
	/**
	 * 根据商家id获取商家信息
	 */
	@Override
	public Department getDepartmentByPurchaserId(String purchaserId) throws Exception{
		return departmentBacktoDao.getDepartmentBySapverdorId(purchaserId);
	}

	/**
	 * 获取所有供应商
	 * （通过条件查询商家）
	 */
	@Override
	public List<Department> selectSupplierList(Map<String, Object> map) throws Exception{
		return departmentBacktoDao.selectSupplierList(map);
	}

	@Override
	public List<Department> selectSupplierList(String supplierId)
			throws Exception {
		return departmentBacktoDao.selectDepartmentBySupplierId(supplierId);
	}

	@Override
	public List<Department> findDepartmentBySapvendorIdorName(String supplierId,
			String supplierName, int i) {
		Map<String, Object> condi = new HashMap<String, Object>();
		condi.put("supplierID", supplierId);
		condi.put("supplierName", supplierName);
		condi.put("rownum", i);
		List<Department> departments = departmentBacktoDao.findDepartmentBySapvendorIdorNameByUser(condi);
		if (null != departments && !departments.isEmpty()) return departments;
		return null;
	}

}

