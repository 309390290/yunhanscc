package com.yunhan.scc.backto.web.dao.mapper.system;

import java.util.List;
import java.util.Map;

import com.yunhan.scc.backto.web.entities.system.ConstantBacktoDo;
import com.yunhan.scc.backto.web.entities.system.SendRuleConfigDo;
import com.yunhan.scc.backto.web.model.system.SendRuleConfigCondition;

/**
 * 发货单规则配置
 * @author xiongmingbao
 * @version created at 2016-8-24 下午3:47:33
 */
public interface SendRuleConfigDao {
	
	/**
	 * 
	 * 根据采购商id查询当前采购商发货单规则
	 * @author xiongmingbao
	 * @version created at 2016-8-24 下午4:45:44
	 * @param sendRuleConfigCondition
	 * @return
	 */
	public List<SendRuleConfigDo> getSendRuleConfigBySupplierId(SendRuleConfigCondition sendRuleConfigCondition);
	
	/**
	 * 
	 * 修改发货单规则
	 * @author xiongmingbao
	 * @version created at 2016-8-24 下午4:46:15
	 * @param sendRuleConfigDo
	 */
	public void updateSendRuleConfig(SendRuleConfigDo sendRuleConfigDo);
	/**
	 * 
	 * 新增发货单规则
	 * @author xiongmingbao
	 * @version created at 2016-8-24 下午4:46:29
	 * @param sendRuleConfigDo
	 */
	public void saveSendRuleConfig(SendRuleConfigDo sendRuleConfigDo);
}
