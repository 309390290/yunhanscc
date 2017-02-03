package com.yunhan.scc.backto.web.dao.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.yunhan.scc.backto.web.entities.system.DcLocationBacktoDo;

/**
 * 
 * ClassName: DcLocationBacktoDao 
 * @Description: 仓位dao
 * @author lumin
 * @date 2016-7-13
 */
public interface DcLocationBacktoDao {
	
	/**
	 * 
	 * @Description: 根据采购商id获取仓位
	 * @param @param params
	 * @param @return   
	 * @return List<DcLocationBacktoDo>  
	 * @throws
	 * @author lumin
	 * @date 2016-7-13
	 */
	public List<DcLocationBacktoDo> getLastLevelDcLocationsByPurchaserId(@Param("purchaserId") String purchaserId);
	/**
	 * 
	 * 根据采购商id和仓位编码获取仓位信息
	 * @author luohoudong
	 * @version created at 2016-8-16 上午10:32:31
	 * @param purchaserId
	 * @param code
	 * @return
	 */
	public DcLocationBacktoDo getDCByCodeAndSapId(@Param("purchaserId") String purchaserId,@Param("code") String code);
	/**
	 * 
	 * @Description: 获取采购商仓位和每个仓位待处理品种数
	 * @param @param purchaserId
	 * @param @return   
	 * @return List<Task>  
	 * @throws
	 * @author lumin
	 * @date 2016-8-25
	 */
	public List<DcLocationBacktoDo> getDcLocationsAndItmesCount(Map<String, Object> params);
	
	/**
	 * 获取采购商仓位和每个仓位待处理订单数
	 * @author luohoudong
	 * @version created at 2016-9-26 上午11:57:47
	 * @param purchaserId
	 * @param userCode
	 * @return
	 */
	public List<DcLocationBacktoDo> getDcLocationsAndOrderCount(@Param("purchaserId") String purchaserId,@Param("userCode") String userCode);
	
}
