package com.yunhan.scc.backto.web.dao.mapper.order;

import java.util.List;

import com.yunhan.scc.backto.web.entities.order.ProOrderExportDO;
import com.yunhan.scc.backto.web.model.order.ProOrderExportCondition;

/**
 * 订单导出Dao
 * @author zwj
 * @version created at 2016-7-21 下午5:36:49
 */
public interface ProOrderExportDao {

	/**
	 *  已处理品种勾选导出
	 * @param condition
	 * @return 
	 */
	public List<ProOrderExportDO> findProcessedVarietiesExport(ProOrderExportCondition condition);
	
}
