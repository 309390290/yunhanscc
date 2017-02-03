/**  

* @Title: DcLocationBacktoServiceImpl.java 

* @Package com.yunhan.scc.backto.web.service.order.impl 

* @Description: TODO(用一句话描述该文件做什么) 

* @author swb

* @date 2016-2-23 上午10:54:25 

* @version V0.1  

*/ 
package com.yunhan.scc.backto.web.service.impl.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhan.scc.backto.web.dao.mapper.system.DcLocationBacktoDao;
import com.yunhan.scc.backto.web.entities.system.DcLocationBacktoDo;
import com.yunhan.scc.backto.web.service.system.DcLocationBacktoService;


/**
 *
 * 类名称：DcLocationBacktoServiceImpl   
 * 类描述：   
 * 创建人：swb
 * 创建时间：2016-2-23 上午10:54:25   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 *    
 */
@Service
public class DcLocationBacktoServiceImpl implements DcLocationBacktoService{
	
	@Autowired
	private DcLocationBacktoDao  dcLocationBacktoDao;

	@Override
	public List<DcLocationBacktoDo> getDcLocationByPurchaserId(String purchaserId)throws Exception {
		List<DcLocationBacktoDo> list = dcLocationBacktoDao.getLastLevelDcLocationsByPurchaserId(purchaserId);
		return list;
	}

	@Override
	public List<DcLocationBacktoDo> getDcLocationsAndItmesCount(String purchaserId,String userCode)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("purchaserId", purchaserId);
		map.put("userCode", userCode);
		return dcLocationBacktoDao.getDcLocationsAndItmesCount(map);
	}
	
	
	/**
	 * 获取采购商仓位和每个仓位待处理订单数
	 * @author luohoudong
	 * @version created at 2016-9-26 上午11:55:11
	 * @param purchaserId
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public List<DcLocationBacktoDo> getDcLocationsAndOrderCount(String purchaserId,String userCode) throws Exception{
		return dcLocationBacktoDao.getDcLocationsAndOrderCount(purchaserId,userCode);
	}
}
