package com.yunhan.scc.backto.web.service.impl.backreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsTBacktoDO;
import com.yunhan.scc.backto.web.entities.order.ProPurOrderItemsBacktoDO;
import com.yunhan.scc.backto.web.model.order.ProPurOrderItemsBacktoCondition;
import com.yunhan.scc.backto.web.service.order.ProPurOrderItemsService;
import com.yunhan.scc.tools.execl.ExeclBeanInterface;
import com.yunhan.scc.tools.execl.ExeclHandleData;
import com.yunhan.scc.tools.util.StringUtil;

/**
 * 
 * 导入的回告临时信息数据验证
 * @author luohoudong
 * @version created at 2016-7-14 下午5:11:05
 */
@Service
public class ExeclProResponseItemsTFromBlankServiceImpl implements ExeclHandleData{
	@Autowired
	private ProPurOrderItemsService proPurOrderItemsService ;
	
	/**
	 * 
	 * @author YangTao 2016-3-10 下午2:35:19
	 * @param beanInterface
	 * @param oldbean
	 * @throws Exception
	 */
	@Override
	public ExeclBeanInterface handleData(ExeclBeanInterface beanInterface,ExeclBeanInterface oldbean) throws Exception {
		if(beanInterface instanceof  ProResponseItemsTBacktoDO){
			ProResponseItemsTBacktoDO pritdo = (ProResponseItemsTBacktoDO) beanInterface;
			//订单细目是否为空验证
			String proPurOrderItemId = pritdo.getProPurOrderItemsId();
			if(proPurOrderItemId != null &&  !proPurOrderItemId.equals("")){
				pritdo.setErrorMessage("导入的模板无法解析!");
				return pritdo;
			}
			
			if("".equals(pritdo.getIsbn()) || "".equals(pritdo.getBookTitle())){
				pritdo.setErrorMessage("DISCARD");
				return pritdo;
			}
			
			//如果代码执行到这里，我们需要将我们解析Excel产生的多余数据去除掉。
			pritdo.setIsbn(StringUtil.delEndTwoZero( pritdo.getIsbn()));
			pritdo.setOrderQty(StringUtil.delEndTwoZero(pritdo.getOrderQty()));
			pritdo.setThisSendQty(StringUtil.delEndTwoZero(pritdo.getThisSendQty()));
			//已发数，已收数
			
			return pritdo;
		}else{
			throw new ClassCastException();
		}
	}

}
