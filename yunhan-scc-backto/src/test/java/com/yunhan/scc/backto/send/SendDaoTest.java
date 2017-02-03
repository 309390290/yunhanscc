package com.yunhan.scc.backto.send;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunhan.scc.backto.base.BaseTest;
import com.yunhan.scc.backto.interfaceEntrance.dao.mapper.backreport.SendDataToInterfaceTDao;
import com.yunhan.scc.backto.interfaceEntrance.entities.backreport.SendDataToInterfaceTDO;

public class SendDaoTest extends BaseTest{
	@Autowired
	private SendDataToInterfaceTDao sendDataToInterfaceTDao;
	/**
	 * 保存待发送回告信息
	 * @author luohoudong
	 * @version created at 2016-10-17 上午11:07:46
	 */
	 @Test
	 public void saveTest(){
		 SendDataToInterfaceTDO dataToInterfaceTDO=new SendDataToInterfaceTDO();
		 dataToInterfaceTDO.setSendType(1);
		 dataToInterfaceTDO.setSourceDataId(999L);
		 sendDataToInterfaceTDao.save(dataToInterfaceTDO);
	 }
	 
	 /**
	  * 
	  * 删除待发送回告信息
	  * @author luohoudong
	  * @version created at 2016-10-17 上午11:19:26
	  */
	 @Test
	 public void deleteTest(){
		 sendDataToInterfaceTDao.delete(999L);
	 }
	 
	 
}
