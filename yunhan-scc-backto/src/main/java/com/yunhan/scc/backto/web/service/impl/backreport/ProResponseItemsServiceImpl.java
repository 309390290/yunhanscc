package com.yunhan.scc.backto.web.service.impl.backreport;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunhan.scc.backto.interfaceEntrance.service.backreport.BacktoInterFaceService;
import com.yunhan.scc.backto.web.dao.mapper.backreport.ProResponseItemsDao;
import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.order.ProPurOrderItemsBacktoDO;
import com.yunhan.scc.backto.web.model.backreport.ProResponseItemsBacktoCondition;
import com.yunhan.scc.backto.web.model.order.ProPurOrderItemsBacktoCondition;
import com.yunhan.scc.backto.web.service.backreport.ProResponseItemsService;
import com.yunhan.scc.backto.web.service.order.ProPurOrderItemsService;
import com.yunhan.scc.backto.web.service.system.SystemBacktoService;
import com.yunhan.scc.tools.service.BacktoUtil;
import com.yunhan.scc.tools.util.StringUtils;
import com.yunhan.scc.trading.web.entities.system.Operator;
import com.yunhan.scc.trading.web.spring.SessionUser;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：ProResponseItemsBacktoServiceImpl   
 * 类描述：   回告service
 * 创建人：lumin
 * 创建时间：2016-7-21 上午9:42:03   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */
@Service
public class ProResponseItemsServiceImpl implements ProResponseItemsService {
	
	@Autowired
	private ProResponseItemsDao proResponseItemsDao;
	@Autowired
	private ProPurOrderItemsService proPurOrderItemsService;
	@Autowired
	private SystemBacktoService systemBacktoService;
	//已回告
	private final static  String RESPONSESTATUS_FILISH = "5";
	//未回告
	private final static  String RESPONSESTATUS_UNFILISH = "0";
	@Autowired
	private BacktoInterFaceService backtoInterFaceService;
	private Log log = LogFactory.getLog(ProResponseItemsServiceImpl.class);
	
	@Override
	public List<ProResponseItemsBacktoDO> getProResponseItemsById(
			ProResponseItemsBacktoCondition condition) {
		return proResponseItemsDao.getProResponseItemsById(condition);
	}

	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveProResponseItemForCloseSend(String orderItemsId,String notEnoughReason,
			List<ProPurOrderItemsBacktoDO> backtoDOs) throws Exception{
		Operator operator = SessionUser.getSessionOperator();
		String[] id = orderItemsId.split(",");
		for (int i = 0; i < id.length; i++) {
			//新增回告数据或修改回告数据状态为0的
			ProResponseItemsBacktoCondition condition = new ProResponseItemsBacktoCondition();
			condition.setProPurOrderItemsId(Long.valueOf(id[i]));
			condition.setResponseStatus(RESPONSESTATUS_UNFILISH);
			List<ProResponseItemsBacktoDO> responseBacktoDOs = proResponseItemsDao.getProResponseItemsById(condition);
			//更新处理
			if(responseBacktoDOs!=null && responseBacktoDOs.size()>0 ){
				ProResponseItemsBacktoDO backtoDO =responseBacktoDOs.get(0);
				backtoDO.setUpdateTime(new Date());
				backtoDO.setUpdateUserCode(operator.getSoName());
				backtoDO.setResponseStatus(RESPONSESTATUS_FILISH);
				backtoDO.setIsValid("Y");
				backtoDO.setOtherAvailableReason(notEnoughReason);
				proResponseItemsDao.updateResponseItems(backtoDO);
			}else{//新增
				//获取订单明细数据
				for(ProPurOrderItemsBacktoDO item : backtoDOs){
					if(!item.getId().toString().equals(id[i])) continue;
					//新增回告记录
					ProResponseItemsBacktoDO responseItems = this.responseItemPackage(item,notEnoughReason,null);
					proResponseItemsDao.saveResponseItems(responseItems);
					break;
				}
			}
		}
		//更新订单细目数据
		proPurOrderItemsService.updateOrderItemsForCloseSend(orderItemsId);
		//计算订单状态
		systemBacktoService.updateOrderState(operator.getSoName(), backtoDOs.get(0).getPurchaserId(),orderItemsId , "0");
	}
	
	/**
	 * 
	 * @Description: 根据订单细目数据生产待写入回告数据
	 * @param @param item
	 * @param @return   
	 * @return ProResponseItemsBacktoDO  
	 * @throws
	 * @author lumin
	 * @date 2016-7-21
	 */
	public ProResponseItemsBacktoDO responseItemPackage(ProPurOrderItemsBacktoDO item, String notEnoughReason,ProResponseItemsBacktoDO backtoDO){
		Operator operator = SessionUser.getSessionOperator();
		//新增回告记录
		ProResponseItemsBacktoDO responseItems = new ProResponseItemsBacktoDO();
		responseItems.setProPurOrderItemsId(item.getId());
		responseItems.setYunhanOrderCode(item.getYunhanOrderCode());
		responseItems.setPurchaseOrderCode(item.getPurchaseOrderCode());
		responseItems.setRowUniqueNo(item.getRowUniqueNo());
		responseItems.setYunhanId(item.getYunhanId());
		responseItems.setPurchaserCommoditiesId(item.getPurchaserCommoditiesId());
		responseItems.setIsbn(item.getIsbn());
		responseItems.setBookTitle(item.getBookTitle());
		responseItems.setPrice(item.getPrice());
		responseItems.setDiscountrate(item.getDiscountrate());
		responseItems.setOrderQty(item.getOrderQty());
		responseItems.setResponseUserCode(operator.getSoName());
		responseItems.setResponseDate(new Date());
		
		responseItems.setIsValid("Y");
		responseItems.setAddUserCode(operator.getSoName());
		responseItems.setAddTime(new Date());
		responseItems.setSupplierId(item.getSupplierId());
		responseItems.setPurchaserId(item.getPurchaserId());
		responseItems.setSupplierCommoditiesId(item.getSupplierCommoditiesId());
		
		if(notEnoughReason!=null){//关闭发货
			responseItems.setResponseStatus(RESPONSESTATUS_FILISH);
			responseItems.setResponseDate(new Date());
			responseItems.setAvailablePrice(item.getPrice());
			responseItems.setAvailableDiscountrate(item.getDiscountrate());
			responseItems.setThisSendQty(0);
			responseItems.setOtherAvailableReason(notEnoughReason);
		}else{//保存发货
			responseItems.setResponseStatus(RESPONSESTATUS_UNFILISH);
			responseItems.setAvailablePrice(backtoDO.getAvailablePrice());
			responseItems.setAvailableDiscountrate(backtoDO.getAvailableDiscountrate());
			responseItems.setThisSendQty(backtoDO.getThisSendQty());
			responseItems.setOtherAvailableReason(backtoDO.getOtherAvailableReason());
			responseItems.setPreSendDate(backtoDO.getPreSendDate());
			responseItems.setResponseRemark(backtoDO.getResponseRemark());
		}
		return responseItems;
	}


	@Override
	@Transactional(rollbackFor=Exception.class)
	public String batchSaveResponse(List<ProResponseItemsBacktoDO> sendBacktoDOs,String userCode,String source) throws Exception {
		//用于发货的回告细目id
		String ids = "";
		//无货
		String unSendIds = "";
		//永久缺货
		String closeSendIds = "";
		//是否全部无货回告标识
		boolean isUnSend = true;
		//保存发货的回告
		if(sendBacktoDOs!=null && sendBacktoDOs.size()>0){
			for(ProResponseItemsBacktoDO senDo : sendBacktoDOs){
				if(senDo.getThisSendQty()>0){
					isUnSend = false;
					break;
				}
			}
			//保存回告
			for(ProResponseItemsBacktoDO senDo : sendBacktoDOs){
				//用于发货
				ids += saveResponse(senDo,userCode,isUnSend,source)+",";
				//永久无货处理
				if(StringUtils.isNotBlank(senDo.getOtherAvailableReason())
						&& BacktoUtil.forEverStockoutReason(senDo.getOtherAvailableReason()) ){
					closeSendIds +=senDo.getProPurOrderItemsId()+",";
				}
				unSendIds +=senDo.getProPurOrderItemsId()+",";
			}
			
			//供应商回告发货数全为0
			if(isUnSend){
				//更新订单细目永久无货
				if(!closeSendIds.equals("")){
					closeSendIds = closeSendIds.substring(0, closeSendIds.length()-1);
					proPurOrderItemsService.updateOrderItemsForCloseSend(closeSendIds);
				}
				//计算订单细目状态
				unSendIds = unSendIds.substring(0, unSendIds.length()-1);
				systemBacktoService.updateOrderState(userCode,sendBacktoDOs.get(0).getPurchaserId(),unSendIds , "0");
				
				
				List<Long> responItemsIds=StringUtils.changeStrToLongList(ids);
				backtoInterFaceService.batchSaveSendDatas(responItemsIds, 1);//把需要发送的回告信息写入代发表
				ids = null;
			}
		}
		
		if(ids!=null && ids!="") 
			ids = ids.substring(0, ids.length()-1);
		return ids;
	}
	
	/**
	 * 
	 * @Description: 保存回告
	 * @param @param backtoDO 
	 * @param @param isUnSend 无发货标识
	 * @param  source  来源(PAGE:页面回告   TEMP:模板回告  )
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lumin
	 * @date 2016-7-22
	 */
	private String saveResponse(ProResponseItemsBacktoDO backtoDO,String userCode,boolean isUnSend,String source) throws Exception{
		String responseId = "";
		//新增回告数据或修改回告数据状态为0的
		ProResponseItemsBacktoCondition condition = new ProResponseItemsBacktoCondition();
		condition.setProPurOrderItemsId(backtoDO.getProPurOrderItemsId());
		condition.setResponseStatus(RESPONSESTATUS_UNFILISH);
		List<ProResponseItemsBacktoDO> responseBacktoDOs = proResponseItemsDao.getProResponseItemsById(condition);
		//已存在未结束的回告
		if(responseBacktoDOs!=null && responseBacktoDOs.size()>0){
			ProResponseItemsBacktoDO backtoDOQuery =responseBacktoDOs.get(0);
			backtoDOQuery.setUpdateTime(new Date());
			backtoDOQuery.setUpdateUserCode(userCode);
			backtoDOQuery.setOtherAvailableReason(backtoDO.getOtherAvailableReason());
			backtoDOQuery.setAvailableDiscountrate(backtoDO.getAvailableDiscountrate());
			backtoDOQuery.setAvailablePrice(backtoDO.getAvailablePrice());
			backtoDOQuery.setThisSendQty(backtoDO.getThisSendQty());
			backtoDOQuery.setPreSendDate(backtoDO.getPreSendDate());
			backtoDOQuery.setResponseRemark(backtoDO.getResponseRemark());
			backtoDOQuery.setSourceType(source);//来源(PAGE:页面回告   TEMP:模板回告  )
			if(isUnSend){//无货
				backtoDOQuery.setResponseStatus(RESPONSESTATUS_FILISH);
			}
			proResponseItemsDao.updateResponseItems(backtoDOQuery);
			responseId = backtoDOQuery.getId().toString();
		}else{
			//获取订单细目数据
			ProPurOrderItemsBacktoCondition backtoCondition = new ProPurOrderItemsBacktoCondition();
			backtoCondition.setId(backtoDO.getProPurOrderItemsId());
			ProPurOrderItemsBacktoDO orderItemsBacktoDO =proPurOrderItemsService.findItemById(backtoCondition);
			if(orderItemsBacktoDO!=null){
				//新增回告记录
				ProResponseItemsBacktoDO responseItems = this.responseItemPackage(orderItemsBacktoDO,null,backtoDO);
				//无货
				responseItems.setSourceType(source);//来源(PAGE:页面回告   TEMP:模板回告  )
				if(isUnSend) responseItems.setResponseStatus(RESPONSESTATUS_FILISH);
				proResponseItemsDao.saveResponseItems(responseItems);
				responseId = responseItems.getId().toString();
			}
		}
		return responseId;
	}

	/**
	 * 更新回告信息
	 * yangtao 2016-8-1
	 */
	@Override
	public void updateResponse(ProResponseItemsBacktoDO proResponse)throws Exception {
		proResponseItemsDao.updateResponseItems(proResponse);
	}

	/**
	 * 修改ID比自己大的回告信息 yangtao 2016-8-4
	 * @param proResponse
	 */
	@Override
	public void updateResponseItemsIsValid(ProResponseItemsBacktoDO proResponse) {
		if(proResponse!=null && proResponse.getId()!=null&&proResponse.getProPurOrderItemsId()!=null){
			proResponseItemsDao.updateResponseItemsIsValid(proResponse);
		}
	}

	@Override
	public String saveTempResponse(List<ProResponseItemsBacktoDO> sendBacktoDOs,
			String userCode) throws Exception {
		//循环暂存回告数据
		if(sendBacktoDOs!=null && sendBacktoDOs.size()>0){
			for(ProResponseItemsBacktoDO backtoDO : sendBacktoDOs){
				saveResponse(backtoDO, userCode, false,"PAGE");
			}
		}
		return null;
	}


	@Override
	public void updateResponseItemsIsValidForUpdateSend(
			ProResponseItemsBacktoDO proResponse) {
		proResponseItemsDao.updateResponseItemsIsValidForUpdateSend(proResponse);
		
	}
	
	
}

