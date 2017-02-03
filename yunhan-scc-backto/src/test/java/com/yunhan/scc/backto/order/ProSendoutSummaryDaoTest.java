package com.yunhan.scc.backto.order;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunhan.scc.backto.base.BaseTest;
import com.yunhan.scc.backto.web.dao.mapper.sendgoods.ProSendoutSummaryDao;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutSummaryBacktoCondition;


public class ProSendoutSummaryDaoTest  extends BaseTest{
	@Autowired
	private ProSendoutSummaryDao proSendoutSummaryDao;
	
	/**
	 * 检查发货单是否可以追加   单元测试
	 * @author luohoudong
	 * @version created at 2016-7-20 上午10:16:36
	 */
	 @Test
	 public void checkSendoutAdditionalTest(){
		 ProSendoutSummaryBacktoCondition sendoutSummaryBacktoCondition=new ProSendoutSummaryBacktoCondition();
		 sendoutSummaryBacktoCondition.setSendoutGoodsCode("fhd20160427-008");
		 sendoutSummaryBacktoCondition.setOrderItemsIds("75664");
		 sendoutSummaryBacktoCondition.setPurchaserId("0004100001");
		 sendoutSummaryBacktoCondition.setSupplierId("0002200063");
		 sendoutSummaryBacktoCondition.setReceiveWarehouse("S001");
		 //Integer num= proSendoutSummaryDao.checkSendoutAdditional(sendoutSummaryBacktoCondition);
		 //System.out.println(num);
	 }
}
