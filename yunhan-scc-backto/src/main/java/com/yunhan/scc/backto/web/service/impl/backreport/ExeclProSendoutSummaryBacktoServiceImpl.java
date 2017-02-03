package com.yunhan.scc.backto.web.service.impl.backreport;

import org.springframework.stereotype.Service;

import com.yunhan.scc.tools.execl.ExeclBeanInterface;
import com.yunhan.scc.tools.execl.ExeclHandleData;
/**
 * Execl 订单发货总目信息处理    对应空白模板。空白模板不需要处理 
 * @author luohoudong
 * @version created at 2016-8-4 上午11:12:14
 */
@Service
public class ExeclProSendoutSummaryBacktoServiceImpl implements ExeclHandleData{

	@Override
	public ExeclBeanInterface handleData(ExeclBeanInterface beanInterface,
			ExeclBeanInterface oldbean) throws Exception {
		return beanInterface;
	}

}
