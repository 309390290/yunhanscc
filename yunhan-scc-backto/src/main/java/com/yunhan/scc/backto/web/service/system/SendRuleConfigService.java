package com.yunhan.scc.backto.web.service.system;

import java.util.List;

import com.yunhan.scc.backto.web.entities.system.SendRuleConfigDo;
import com.yunhan.scc.backto.web.model.system.SendRuleConfigCondition;

/**
 * 
 * 发货单规则配置
 * @author xiongmingbao
 * @version created at 2016-8-24 下午4:56:25
 */
public interface SendRuleConfigService {
	
	/**
	 * 
	 * 根据采购商id查询当前采购商发货单规则
	 * @author xiongmingbao
	 * @version created at 2016-8-24 下午4:59:20
	 * @param supplierId
	 * @return
	 * @throws Exception
	 */
	public List<SendRuleConfigDo> getSendRuleConfigBySupplierId(SendRuleConfigCondition sendRuleConfigCondition) throws Exception;

	/**
	 * 
	 * 保存发货单规则
	 * @author xiongmingbao
	 * @version created at 2016-8-24 下午4:59:42
	 * @param sendRuleConfigDo
	 * @throws Exception
	 */
	public void saveSendRuleConfig(SendRuleConfigDo sendRuleConfigDo) throws Exception;
}
