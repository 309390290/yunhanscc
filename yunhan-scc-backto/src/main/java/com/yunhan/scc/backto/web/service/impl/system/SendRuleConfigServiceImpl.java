package com.yunhan.scc.backto.web.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhan.scc.backto.web.dao.mapper.system.SendRuleConfigDao;
import com.yunhan.scc.backto.web.entities.system.SendRuleConfigDo;
import com.yunhan.scc.backto.web.model.system.SendRuleConfigCondition;
import com.yunhan.scc.backto.web.service.system.SendRuleConfigService;
/**
 * 
 * 发货单号规则配置
 * @author xiongmingbao
 * @version created at 2016-8-29 下午2:16:16
 */
@Service
public class SendRuleConfigServiceImpl implements SendRuleConfigService{

	@Autowired
	private SendRuleConfigDao sendRuleConfigDao;

	/**
	 * 
	 * 根据条件查询供应商与采购商发货单规则
	 * @author xiongmingbao
	 * @version created at 2016-8-24 下午4:59:20
	 * @param sendRuleConfigCondition
	 * @version created at 2016-10-24 下午4:59:20
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<SendRuleConfigDo> getSendRuleConfigBySupplierId(SendRuleConfigCondition sendRuleConfigCondition)throws Exception {
		return sendRuleConfigDao.getSendRuleConfigBySupplierId(sendRuleConfigCondition);
	}

	/**
	 * 
	 * 保存发货单规则
	 * @author xiongmingbao
	 * @version created at 2016-8-24 下午4:59:42
	 * @param sendRuleConfigDo
	 * @throws Exception
	 */
	@Override
	public void saveSendRuleConfig(SendRuleConfigDo sendRuleConfigDo)	throws Exception {
		if(sendRuleConfigDo.getId() ==null){
			sendRuleConfigDao.saveSendRuleConfig(sendRuleConfigDo);
		}else {
			sendRuleConfigDao.updateSendRuleConfig(sendRuleConfigDo);
		}
	}

}
