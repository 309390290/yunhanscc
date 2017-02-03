package com.yunhan.scc.backto.web.service.impl.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhan.scc.backto.web.dao.mapper.order.ProOrderExportDao;
import com.yunhan.scc.backto.web.dao.mapper.system.SystemBacktoDao;
import com.yunhan.scc.backto.web.entities.system.TmpOrderItemsDo;
import com.yunhan.scc.backto.web.model.system.SetOrderStatusCondition;
import com.yunhan.scc.backto.web.service.system.SystemBacktoService;
import com.yunhan.scc.oauth2.web.entities.security.Department;
import com.yunhan.scc.tools.util.key.KeyUtils;
import com.yunhan.scc.trading.web.entities.system.Operator;
import com.yunhan.scc.trading.web.spring.SessionUser;


/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：SystemBacktoServiceImpl   
 * 类描述：   
 * 创建人：lumin
 * 创建时间：2016-7-13 下午2:08:56   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */
@Service
public class SystemBacktoServiceImpl implements SystemBacktoService{
	private Logger log =  Logger.getLogger(SystemBacktoServiceImpl.class);
	@Autowired
	private SystemBacktoDao systemBacktoDao;
	@Autowired
	private ProOrderExportDao proOrderExportDao;
	
	/**
	 * 获取用户采购商列表
	 */
	@Override
	public List<Department> selectMyPur(String userCode) {
		
		return systemBacktoDao.selectMyPur(userCode);
	}
	
	/**
	 * 修改订单状态-存储过程方式
	 * @param proPurOrderItemsIds 订单细目id 多个用英文逗号分隔
	 * @param purchaserId 采购商编码
	 * @param userCode 发货人员（当前登录人员）
	 * @param iOpFlag 0-回告发货,1-删除回告发货 
	 * @return
	 */
	public void updateOrderState(String userCode, String purchaserId, String proPurOrderItemsIds,String iOpFlag) throws Exception {
		try {
			//批次号
			String batchNo = KeyUtils.getUuid();
			List<TmpOrderItemsDo> itemsDos = new ArrayList<TmpOrderItemsDo>();
			if(null == proPurOrderItemsIds || "".equals(proPurOrderItemsIds)){
				return;
			}
			boolean isMulti = false;			//是否多个订单号
			if(proPurOrderItemsIds.indexOf(",") > 0){
				isMulti = true;
			}
			if(isMulti){
				String[] codes = proPurOrderItemsIds.split(",");
				List<String> codeList = Arrays.asList(codes);
				if(CollectionUtils.isNotEmpty(codeList)){
					for(String proPurOrderItemsId : codeList){
//						setOrderStatus(proPurOrderItemsId,purchaserId,userCode,iOpFlag);
						TmpOrderItemsDo do1 = new TmpOrderItemsDo();
						do1.setProPurOrderItemsId(Long.valueOf(proPurOrderItemsId));
						do1.setBatchNo(batchNo);
						itemsDos.add(do1);
					}
				}
			}else{
//				setOrderStatus(proPurOrderItemsIds,purchaserId,userCode,iOpFlag);
				TmpOrderItemsDo do1 = new TmpOrderItemsDo();
				do1.setProPurOrderItemsId(Long.valueOf(proPurOrderItemsIds));
				do1.setBatchNo(batchNo);
				itemsDos.add(do1);
			}
			//保存批次号和待处理订单细目id
			systemBacktoDao.saveTmpOrderIds(itemsDos);
			//启动处理
			setOrderStatusByBatch(batchNo, purchaserId, userCode, iOpFlag);
		} catch (Exception e) {
			log.error("回告发货调用存储过程修改状态订单异常！",e);
			throw e;
		}
	}
	
	
	/**
	 * 修改订单状态-存储过程方式
	 * @param proPurOrderItemsId 订单细目id
	 * @param purchaserId 采购商编码
	 * @param userCode 发货人员（当前登录人员）
	 * @param iOpFlag 0-回告发货,1-删除回告发货 
	 * @return
	 */
	public String setOrderStatus(String proPurOrderItemsId,String purchaserId,String userCode,String iOpFlag) throws Exception {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("proPurOrderItemsId", proPurOrderItemsId);
		para.put("purchaserId", purchaserId);
		para.put("userCode", userCode);
		para.put("iOpFlag", iOpFlag);
		try {
			systemBacktoDao.setOrderStatus(para);
			int zt = Integer.valueOf(para.get("oReturnInt").toString());
			if(zt==1){
				String logMsg="采购商id:"+purchaserId+"云汉订单细目id:"+proPurOrderItemsId+"用户编码:"+userCode;
				throw new Exception(logMsg+"调用存储过程设置订单异常: 原因："+para.get("oReturnStr"));
			}
			if(para.get("oReturnStr")==null) return null;
			return para.get("oReturnStr").toString();
		} catch (Exception e) {
			String logMsg="采购商id:"+purchaserId+"云汉订单细目id:"+proPurOrderItemsId+"用户编码:"+userCode;
			log.error(logMsg+"调用存储过程设置订单异常", e);
			throw new Exception(logMsg+"调用存储过程设置订单异常");
		}
	}
	
	
	
	/**
	 * 修改订单状态-存储过程方式
	 * @param proPurOrderItemsId 订单细目id
	 * @param purchaserId 采购商编码
	 * @param userCode 发货人员（当前登录人员）
	 * @param iOpFlag 0-回告发货,1-删除回告发货 
	 * @return
	 */
	public String setOrderStatusByBatch(String batchNo,String purchaserId,String userCode,String iOpFlag) throws Exception {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("i_batch_no", batchNo);
		para.put("purchaserId", purchaserId);
		para.put("userCode", userCode);
		para.put("iOpFlag", iOpFlag);
		try {
			systemBacktoDao.setOrderStatusByBatch(para);
			int zt = Integer.valueOf(para.get("oReturnInt").toString());
			if(zt==1){
				String logMsg="采购商id:"+purchaserId+"批次号:"+batchNo+"用户编码:"+userCode;
				throw new Exception(logMsg+"调用存储过程设置订单异常: 原因："+para.get("oReturnStr"));
			}
			if(para.get("oReturnStr")==null) return null;
			return para.get("oReturnStr").toString();
		} catch (Exception e) {
			String logMsg="采购商id:"+purchaserId+"批次号:"+batchNo+"用户编码:"+userCode;
			log.error(logMsg+"调用存储过程设置订单异常", e);
			throw new Exception(logMsg+"调用存储过程设置订单异常");
		}
	}
	

	/**
	 * 
	 * @Description: 修改导出状态
	 * @param ids 数据ids（多个用逗号隔开）
	 * @param type 数据类型  默认为空 若为空则为订单细目   orderSummary 订单总目
	 * @return void  
	 * @throws Exception 
	 * @throws
	 * @author zwj
	 * @date 2016-7-25
	 */
	public void updateExportState(String ids,String type) throws Exception {
		if(ids==null||ids.equals("")){
			throw new Exception("修改导出状态时没有发现对象");
		}
		Operator operator = SessionUser.getSessionOperator();
		if(operator==null){
			throw new Exception("登录失效！请重新登录!");
		}
		String userCode = operator.getSoName();
		try {
			String [] idrs = ids.split(",");
			if("orderSummary".equalsIgnoreCase(type)){
				saveOrUpdateNodeUpByOrderSum(userCode,idrs);
			}else{
				saveOrUpdateNodeUp(userCode,idrs);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * 下载标识记录（新增或修改）
	 * @param userCode 下载者
	 * @param dataIds 下载数据id
	 * @author wangtao
	 * 2016年7月26日10:53:55
	 */
	public void saveOrUpdateNodeUp(String userCode,String [] dataIds)throws Exception{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("dataType", "ORDER"); //数据类型
		param.put("nodeTp", "HGFH"); //下载数据节点
		param.put("userCode", userCode);//下载者
		param.put("dataIds",dataIds); //下载数据id
		systemBacktoDao.saveOrUpdateNodeUp(param);
	}
	
	
	/**
	 * 下载标识记录,整单下载标识计算（新增或修改）
	 * @param userCode 下载者
	 * @param dataIds 下载数据id
	 * @author wangtao
	 * 2016年7月26日10:53:55
	 */
	public void saveOrUpdateNodeUpByOrderSum(String userCode,String [] dataIds)throws Exception{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("dataType", "ORDER"); //数据类型
		param.put("nodeTp", "HGFH"); //下载数据节点
		param.put("userCode", userCode);//下载者
		param.put("dataIds",dataIds); //下载数据id
		systemBacktoDao.saveOrUpdateNodeUpByOrderSum(param);
	}

	@Override
	public Department getDepartmentByPurchaserId(String purchaserId) throws Exception{
		return systemBacktoDao.getDepartmentBySapverdorId(purchaserId);
	}

}

