package com.yunhan.scc.backto.web.service.impl.sendgoods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.yunhan.scc.backto.interfaceEntrance.service.backreport.BacktoInterFaceService;
import com.yunhan.scc.backto.interfaceEntrance.service.sendgoods.DeliveredInterfaceService;
import com.yunhan.scc.backto.web.dao.mapper.backreport.ProResponseItemsDao;
import com.yunhan.scc.backto.web.dao.mapper.backreport.ProResponseItemsTDao;
import com.yunhan.scc.backto.web.dao.mapper.order.ProPurOrderItemsDao;
import com.yunhan.scc.backto.web.dao.mapper.sendgoods.ProSendoutItemsDao;
import com.yunhan.scc.backto.web.dao.mapper.sendgoods.ProSendoutSummaryDao;
import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.order.ProPurOrderItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutSummaryBacktoDO;
import com.yunhan.scc.backto.web.model.backreport.ProResponseItemsBacktoCondition;
import com.yunhan.scc.backto.web.model.order.ProPurOrderItemsBacktoCondition;
import com.yunhan.scc.backto.web.model.order.ProPurOrderSummaryBacktoCondition;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutItemsBacktoCondition;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutSummaryBacktoCondition;
import com.yunhan.scc.backto.web.service.backreport.ProResponseItemsService;
import com.yunhan.scc.backto.web.service.sendgoods.SendgoodsService;
import com.yunhan.scc.backto.web.service.system.SystemBacktoService;
import com.yunhan.scc.tools.constant.OrderType;
import com.yunhan.scc.tools.constant.SendoutStatus;
import com.yunhan.scc.tools.service.BacktoUtil;
import com.yunhan.scc.tools.util.BeanUtils;
import com.yunhan.scc.tools.util.StringUtils;
import com.yunhan.scc.trading.web.entities.system.Operator;
import com.yunhan.scc.trading.web.spring.SessionUser;

/**
 * 
 * 回告发货service实现
 * @author luohoudong
 * @version created at 2016-7-14 下午3:48:07
 */
@Service
public class SendgoodsServiceImpl implements SendgoodsService{
	
	private Log log = LogFactory.getLog(SendgoodsServiceImpl.class);
	
	@Autowired
	private  ProSendoutSummaryDao proSendoutSummaryDao;
	@Autowired
	private ProSendoutItemsDao proSendoutItemsDao;
	@Autowired
	private ProPurOrderItemsDao proPurOrderItemsDao;
	@Autowired
	private ProResponseItemsDao proResponseItemsDao;
	@Autowired
	private BacktoInterFaceService backtoInterFaceService;
	@Autowired
	private SystemBacktoService systemBacktoService;
	@Autowired
	private DeliveredInterfaceService deliveredInterfaceService;
	@Autowired
	private ProResponseItemsService responseItemsService;
	@Autowired
	private ProResponseItemsTDao proResponseItemsTDao;
	
	/**
	  * 根据回告细目ids生成发货单头信息
	  * @author luohoudong
	  * @version created at 2016-7-15 下午4:17:20
	  * @param backIds
	  * @return
	  */
	public ProSendoutSummaryBacktoDO getSendoutHeaders(String backIds){
		ProSendoutSummaryBacktoCondition backtoCondition=new ProSendoutSummaryBacktoCondition();
		backtoCondition.setResponseItemsIds(backIds);
		return proSendoutSummaryDao.getSendoutHeaders(backtoCondition);
	}
	
	
	
	/**
	 * 保存发货单(发货操作)
	 * @author luohoudong
	 * @version created at 2016-7-19 下午2:50:21
	 * @param sendoutSummaryBacktoDO 发货单头信息
	 * @param sendoutItems	发货单细目信息
	 * @param source  发货来源(PAGE:页面发货   TEMP:模板发货 )
	 * @return 发货单总目id
	 */
	public String  saveSendOutgoods(ProSendoutSummaryBacktoDO sendoutSummaryBacktoDO,List<ProSendoutItemsBacktoDO> sendoutItems)throws Exception{
		String sendOutSummaryId="";
		for (ProSendoutItemsBacktoDO proSendoutItemsBacktoDO : sendoutItems) {
			ProResponseItemsBacktoDO proResponseItemsBacktoDO=new ProResponseItemsBacktoDO();
			
			//如果非供应商添加商品,则检查本次发货数是否小于订数130%-已发数
			if("N".equals(proSendoutItemsBacktoDO.getIsSupplierAddProduct())){
				checkThisSendQty(proSendoutItemsBacktoDO.getOrderQty(),proSendoutItemsBacktoDO.getHistoryOrderQty(),proSendoutItemsBacktoDO.getSendoutQty());
			}
			ProSendoutItemsBacktoDO backtoDo = findSendOutItmesWhether(proSendoutItemsBacktoDO);
			if(backtoDo!=null){
				//若当前发货细目是已经暂存了的则先删除掉然后将量加到之前发货上
				if(proSendoutItemsBacktoDO.getId()!=null){
					proSendoutItemsDao.delSendoutItemsByIdByStatus(proSendoutItemsBacktoDO);
				}
				proSendoutItemsBacktoDO.setId(backtoDo.getId());
				proSendoutItemsDao.updateSendoutItemsBySave(proSendoutItemsBacktoDO);
				//如果发货单细目上回告id不为空，则需要把发货单id，回写到回告上
				if(proSendoutItemsBacktoDO.getResponseId()!=null && !"".equals(proSendoutItemsBacktoDO.getResponseId())){
					proResponseItemsBacktoDO.setId(Long.valueOf(proSendoutItemsBacktoDO.getResponseId()));
					proResponseItemsBacktoDO.setProSendoutItemsId(backtoDo.getId());
					proResponseItemsDao.updateResponseItemsDO(proResponseItemsBacktoDO);
				}
				
			}else{
				if(proSendoutItemsBacktoDO.getId()!=null&&proSendoutItemsBacktoDO.getId()!=0){
					/****根据发货单细目id查询一次库，若存在则直接更新状态，若不存在就新增发货单细目 （查询）start*******/
					ProSendoutItemsBacktoCondition pis = new ProSendoutItemsBacktoCondition();
					pis.setId(proSendoutItemsBacktoDO.getId());
					List<ProSendoutItemsBacktoDO> backtoDos =  proSendoutItemsDao.queryProSendoutItemsBySendcode(pis);
					/****根据发货单细目id查询一次库，若存在则直接更新状态，若不存在就新增发货单细目 （查询）end*******/
					if(backtoDos!=null&&backtoDos.size()>=1){
						//将发货状态改为发货
						proSendoutItemsBacktoDO.setStatus(5);
						//修改发货单细目
						proSendoutItemsDao.updateSendoutItems(proSendoutItemsBacktoDO);
					}else{
						//保存发货单细目
						proSendoutItemsDao.saveSendoutItems(proSendoutItemsBacktoDO);
					}
				}else{
					//保存发货单细目
					proSendoutItemsDao.saveSendoutItems(proSendoutItemsBacktoDO);
				}
				//如果发货单细目上回告id不为空，则需要把发货单id，回写到回告上
				if(proSendoutItemsBacktoDO.getResponseId()!=null && !"".equals(proSendoutItemsBacktoDO.getResponseId())){
					proResponseItemsBacktoDO.setId(Long.valueOf(proSendoutItemsBacktoDO.getResponseId()));
					proResponseItemsBacktoDO.setProSendoutItemsId(proSendoutItemsBacktoDO.getId());
					proResponseItemsDao.updateResponseItemsDO(proResponseItemsBacktoDO);
				}
			}
		}
		/*******************根据发货单号,采购商id,供应商id 查看发货总目信息是否存在,如果存在则更新总目信息的发货数、码洋、实洋、品种数   ****************/
		ProSendoutSummaryBacktoCondition summaryBacktoCondition=new ProSendoutSummaryBacktoCondition();
		summaryBacktoCondition.setPurchaserId(sendoutSummaryBacktoDO.getPurchaserId());
		summaryBacktoCondition.setSupplierId(sendoutSummaryBacktoDO.getSupplierId());
		summaryBacktoCondition.setSendoutGoodsCode(sendoutSummaryBacktoDO.getSendoutGoodsCode());
		ProSendoutSummaryBacktoDO summarySendDO=proSendoutSummaryDao.findProSendoutSummaryBySendoutGoodsCode(summaryBacktoCondition);
		if(summarySendDO != null){
			//如果原来不是供应商添加则,不需要更改
			if(summarySendDO.getIsSupplierAddProduct()!=null && "N".equals(summarySendDO.getIsSupplierAddProduct())){
				sendoutSummaryBacktoDO.setIsSupplierAddProduct("N");
			}
			proSendoutSummaryDao.updateProSendoutSummaryDO(sendoutSummaryBacktoDO);
			log.info("发货单号:"+sendoutSummaryBacktoDO.getSendoutGoodsCode()+">>>>追加发货成功!" );
			sendOutSummaryId=summarySendDO.getId().toString();
		}else{
			proSendoutSummaryDao.saveProSendoutSummaryDO(sendoutSummaryBacktoDO);
			log.info("发货单号:"+sendoutSummaryBacktoDO.getSendoutGoodsCode()+">>>>新增发货成功!" );
			sendOutSummaryId=sendoutSummaryBacktoDO.getId().toString();
		}
		return sendOutSummaryId;
		
	}
	
	/**
	 * 
	 * 检查发货单是否可以追加   
	 * 如果 flagNum > 1 则追加的发货单品种已收货 ;
	 * 如果 flagNum = 1  则追加的发货单仓位不一致;
	 * 如果 flagNum = 0  则可以追加 ;
	 * 如果 flagNum = -1 则订单号不重复
	 * @author luohoudong
	 * @version created at 2016-7-20 上午9:41:58
	 * @param sendoutSummaryBacktoCondition 
	 * {仓位:receiveWarehouse 发货单号:sendoutGoodsCode 采购商id：purchaserId 供应商id:supplierId 
	 * 回告细目ids：responseItemsIds }
	 * @return flagNum
	 * 
	 */
	public Integer checkSendoutAdditional(ProSendoutSummaryBacktoCondition sendoutSummaryBacktoCondition,List<ProResponseItemsBacktoDO> responItemsDOs){
		Integer	flagNum=-1;
		List<Integer> checkNums=proSendoutSummaryDao.checkSendoutAdditional(sendoutSummaryBacktoCondition);
		for (Integer integer : checkNums) {
			if(integer==2){
				flagNum=2;
				break;
			}else if(integer==1){
				flagNum=1;
				break;
			}else if(integer==0){
				flagNum=0;
			}
			
		}
		if(flagNum==-1){
			ProSendoutSummaryBacktoDO summarySendDO=proSendoutSummaryDao.findProSendoutSummaryBySendoutGoodsCode(sendoutSummaryBacktoCondition);
			if(summarySendDO==null){
				if(responItemsDOs!=null){
					for (ProResponseItemsBacktoDO proResponseItemsBacktoDO : responItemsDOs) {
						ProSendoutItemsBacktoDO proSendoutItemsBacktoDO=new ProSendoutItemsBacktoDO();
						//采购商id
						proSendoutItemsBacktoDO.setPurchaserId(sendoutSummaryBacktoCondition.getPurchaserId());
						//供应商id
						proSendoutItemsBacktoDO.setSupplierId(sendoutSummaryBacktoCondition.getSupplierId());
						//订单细目id
						proSendoutItemsBacktoDO.setProPurOrderItemsId(proResponseItemsBacktoDO.getProPurOrderItemsId());
						//isbn
						proSendoutItemsBacktoDO.setIsbn(proResponseItemsBacktoDO.getIsbn());
						//书名
						proSendoutItemsBacktoDO.setBookTitle(proResponseItemsBacktoDO.getBookTitle());
						//发货价
						proSendoutItemsBacktoDO.setSendoutPrice(proResponseItemsBacktoDO.getAvailablePrice());
						//发货折扣
						proSendoutItemsBacktoDO.setSendoutDiscountrate(proResponseItemsBacktoDO.getAvailableDiscountrate());
						//订单号
						proSendoutItemsBacktoDO.setPurchaseOrderCode(proResponseItemsBacktoDO.getPurchaseOrderCode());
						//供应商商品id
						proSendoutItemsBacktoDO.setSupplierCommoditiesId(proResponseItemsBacktoDO.getSupplierCommoditiesId());
						//发货单号
						proSendoutItemsBacktoDO.setSendoutGoodsCode(sendoutSummaryBacktoCondition.getSendoutGoodsCode());
						ProSendoutItemsBacktoDO backtoDo = findSendOutItmesWhether(proSendoutItemsBacktoDO);
						if(backtoDo!=null)
							flagNum= 0;
						else
							flagNum= -1;
						
					}
				}
			}else{
				flagNum= 0;
			}
		}
			
		return flagNum;
	}
	
	
	/**
	 * 检查本次发货数是否符合需求
	 * @author luohoudong
	 * @version created at 2016-7-20 下午2:28:32
	 * @param orderQty 订数
	 * @param sendQty   已发数
	 * @param thisSendQty	本次发货数
	 * @throws Exception
	 */
	public void checkThisSendQty(Integer orderQty,Integer sendQty,Integer thisSendQty)throws Exception{
		double orderQtyc = orderQty*1.3;
		if(orderQtyc-sendQty-thisSendQty<0){//-未发数  > 定数*0.3  则本次发货异常
			throw new Exception("本次发货无效，发货数大于了订数130%！");
		}
	}
	
	/**
	 * 
	 * 匹配发货单规则（规则按优先级匹配，如匹配不上，依次延用下一条规则）：
	 * (1) 订单号+订单细目id+发货单号+发货价+发货折扣； 
	 * (2)无订单号+供应商商品ID+发货单号+发货价+发货折扣； 
	 * (3)无订单号+ISBN+书名+发货单号+发货价+发货折扣；
	 * 没有订单号的就用没订单号的匹配
	 */
	public ProSendoutItemsBacktoDO findSendOutItmesWhether(ProSendoutItemsBacktoDO sendOuntItems){
		List<ProSendoutItemsBacktoDO> backtoDos = null;
		//(1) 订单号+订单细目id+发货单号+发货价+发货折扣； 
		if(sendOuntItems!=null && sendOuntItems.getProPurOrderItemsId()!=null){
			backtoDos = proSendoutItemsDao.findSendOutItemsByOrderCodeAndItemsId(sendOuntItems);
			if(backtoDos!=null&&backtoDos.size() == 1){
				return backtoDos.get(0);
			}
		}
		
		//无订单号+供应商商品ID+发货单号+发货价+发货折扣； /*edit by yangtao 增加对该查询的异常处理. 2016-5-16*/
		if(sendOuntItems!=null&&sendOuntItems.getPurchaseOrderCode()==null && sendOuntItems.getSupplierCommoditiesId()!=null){
			backtoDos = proSendoutItemsDao.findSendOutItemsByOrderCodeAndSupplier(sendOuntItems);
			if(backtoDos!=null&&backtoDos.size() == 1){
				return backtoDos.get(0);
			}
		}
		//无订单号时
		if(sendOuntItems!=null&&sendOuntItems.getPurchaseOrderCode()==null){
			//无订单号+ISBN+书名+发货单号+发货价+发货折扣； 
			backtoDos = proSendoutItemsDao.findSendOutItemsByOrderCodeAndIsbnBook(sendOuntItems);
			if(backtoDos!=null&&backtoDos.size() == 1){
				return backtoDos.get(0);
			}
		}
		return null;
	}
	
	
	/**
	 * 回告发货
	 * @author luohoudong
	 * @version created at 2016-7-20 下午4:51:50
	 * @param sendoutSummaryBacktoDO 发货单头信息
	 * @param responItemsDOs  回告发货细目信息
	 * @param source  发货来源(PAGE:页面发货   TEMP:模板发货   B_TEMP:空白模板发货)
	 * @return 发货单总目id
	 */
	@Transactional(rollbackFor=Exception.class)
	public String reportAndSendout(ProSendoutSummaryBacktoDO sendoutSummaryBacktoDO,List<ProResponseItemsBacktoDO> responItemsDOs,String source)throws Exception {
		Operator operator=SessionUser.getSessionOperator();//从session中获取登录用户信息
		sendoutSummaryBacktoDO.setAddUserCode(operator.getSoName());//创建人编码
		sendoutSummaryBacktoDO.setUpdateUserCode(operator.getSoName());//修改人编码
		sendoutSummaryBacktoDO.setSourceType(source);//发货来源
		sendoutSummaryBacktoDO.setSendoutStatus(SendoutStatus.SEND_NOT_RECEIVING);//
		List<ProSendoutItemsBacktoDO>   sendoutItems=new ArrayList<ProSendoutItemsBacktoDO>();// 发货单状态：5-已发货（未收货）
		List<ProResponseItemsBacktoDO>  needResponseItems=new ArrayList<ProResponseItemsBacktoDO>();//需要回告的回告信息
		String needResponseIds=sendoutSummaryBacktoDO.getResponseItemsIds();//需要回告的回告细目ids
		String proPurOrderItemsIds="";//记录订单细目ids,用于调用修改订单状态的存储过程
		Boolean sumIsSupplierAdd=true;//总目是否供应商添加  true：是  false：否
		
		for (ProResponseItemsBacktoDO responseItem : responItemsDOs) {
			Boolean isSupplierAdd=true;//是否供应商添加商品 true:是  false：否
			ProSendoutItemsBacktoDO sendoutItemsDO=new ProSendoutItemsBacktoDO();//发货单细目信息
			/**************** 如果不是供应商添加,则基本信息从订单上拷贝过来 **********/
			
			//如果能够匹配订单，没有回告信息，则需要新增回告信息
			//如果能够匹配订单,有回告信息
			//无法匹配订单，则作为供应商添加
			if(responseItem.getProPurOrderItemsId()!=null ){
				//查询订单细目信息
				ProPurOrderItemsBacktoDO orderItems=proPurOrderItemsDao.getProPurOrderItemsBacktoById(responseItem.getProPurOrderItemsId());
				if(orderItems!=null){//如果匹配上订单
					//proPurOrderItemsIds=StringUtils.idsAddChildId(proPurOrderItemsIds,orderItems.getId().toString(), ",");
					BeanUtils.copyProperties(orderItems, sendoutItemsDO,"id,status");//把订单上的基本信息拷贝到发货单细目信息上
					sendoutItemsDO.setHistoryOrderQty(orderItems.getSendoutQty());//已发数
					sendoutItemsDO.setIsSupplierAddProduct("N");//是否供应商添加
					needResponseItems.add(responseItem);//把有回告id的回告细目信息添加到需要回告的List中
					
					isSupplierAdd=false;//是否供应商添加商品 true:是  false：否
					String otherAvailableReason="";//回告的其余满足情况
					
					/*************	新增回告信息    开始***********/
					if(responseItem.getId()!=null){
						ProResponseItemsBacktoCondition condition=new ProResponseItemsBacktoCondition();
						condition.setId(responseItem.getId());
						condition.setProPurOrderItemsId(orderItems.getId());
						List<ProResponseItemsBacktoDO> responseItemsBacktoDOs= proResponseItemsDao.getProResponseItemsById(condition);
						if(responseItemsBacktoDOs.size()>0){//如果有回告信息
							needResponseIds=StringUtils.idsAddChildId(needResponseIds,responseItemsBacktoDOs.get(0).getId().toString(), ",");
							//如果传入的参数有其余满足原因,则用传入的其余满足原因
							if(responseItem.getOtherAvailableReason()!=null){
								otherAvailableReason=responseItem.getOtherAvailableReason();
							}else if(responseItemsBacktoDOs.get(0).getOtherAvailableReason()!=null){
								otherAvailableReason=responseItemsBacktoDOs.get(0).getOtherAvailableReason();
							}
							//把回告细目id暂存发货单细目上,用于保存发货单后,把发货单细目id会写回告上
							sendoutItemsDO.setResponseId(responseItemsBacktoDOs.get(0).getId().toString());
							responseItem.setId(responseItemsBacktoDOs.get(0).getId());
							responseItem.setSourceType(source);
							proResponseItemsDao.updateResponseItemsDO(responseItem);
						}else{//如果没有回告信息,则需要新增回告信息
							//新增回告记录
							ProResponseItemsBacktoDO responseItems =responseItemsService.responseItemPackage(orderItems,null,responseItem);
							responseItems.setSourceType(source);
							proResponseItemsDao.saveResponseItems(responseItems);
							needResponseIds=StringUtils.idsAddChildId(needResponseIds, responseItems.getId().toString(), ",");
							//把回告细目id暂存发货单细目上,用于保存发货单后,把发货单细目id会写回告上
							sendoutItemsDO.setResponseId(responseItems.getId().toString());
						}
					}else{
						//如果传入的参数有其余满足原因,则用传入的其余满足原因
						if(responseItem.getOtherAvailableReason()!=null){
							otherAvailableReason=responseItem.getOtherAvailableReason();
						}
						//新增回告记录
						ProResponseItemsBacktoDO responseItems =responseItemsService.responseItemPackage(orderItems,null,responseItem);
						responseItems.setSourceType(source);
						proResponseItemsDao.saveResponseItems(responseItems);
						needResponseIds=StringUtils.idsAddChildId(needResponseIds, responseItems.getId().toString(), ",");
						//把回告细目id暂存发货单细目上,用于保存发货单后,把发货单细目id会写回告上
						sendoutItemsDO.setResponseId(responseItems.getId().toString());
					}
					/*************	新增回告信息    结束***********/
					
					//如果回告其余满足原因为永久缺货,则订单细目的品种有效性更新为供应商关闭 X-供应商关闭
					if( BacktoUtil.forEverStockoutReason(otherAvailableReason) ){
						String closeReason="";
						switch (otherAvailableReason) {
							case "2":
								closeReason="已停产";
								break;
							case "3":
								closeReason="改版";
								break;
							case "4":
								closeReason="版权到期";
								break;
							case "5":
								closeReason="商品无效";
								break;
							case "6":
								closeReason="销售受限";
								break;
						}
						
						orderItems.setIsValid("X");
						orderItems.setCloseReason(closeReason);
						proPurOrderItemsDao.updateOrderItems(orderItems);
					}
					//更新订单品种有效性 结束
				}
				
				
			}
			/**************** 如果是供应商添加,则订单细目基本信息从前端传入**********/
			if(isSupplierAdd){
				sendoutItemsDO.setIsbn(responseItem.getIsbn());//ISBN
				sendoutItemsDO.setBookTitle(responseItem.getBookTitle());//书名
				sendoutItemsDO.setPurchaserId(sendoutSummaryBacktoDO.getPurchaserId());//采购商id
				sendoutItemsDO.setSupplierId(sendoutSummaryBacktoDO.getSupplierId());//供应商id
				sendoutItemsDO.setPrice(responseItem.getAvailablePrice());//由于数据库定价字段不能为空,则供应商添加商品的定价为发货价
				sendoutItemsDO.setPurchaseOrderCode(responseItem.getPurchaseOrderCode());//采购订单号
				sendoutItemsDO.setIsSupplierAddProduct("Y");//是否供应商添加
			}else{//如果有一个不是供应商添加
				sumIsSupplierAdd=false;
				sendoutItemsDO.setIsSupplierAddProduct("N");//是否供应商添加
			}
			//sendoutItemsDO.setHistoryOrderQty(responseItem.getHistoryOrderQty());//已发数
			
			sendoutItemsDO.setProPurOrderItemsId(responseItem.getProPurOrderItemsId());//订单细目id
			sendoutItemsDO.setSourceType(source);//发货来源
			sendoutItemsDO.setRemark(responseItem.getResponseRemark());//细目备注
			sendoutItemsDO.setSendoutGoodsCode(sendoutSummaryBacktoDO.getSendoutGoodsCode());//发货单号
			sendoutItemsDO.setSendoutDiscountrate(responseItem.getAvailableDiscountrate());//发货折扣
			sendoutItemsDO.setSendoutPrice(responseItem.getAvailablePrice());//发货价
			sendoutItemsDO.setStatus(SendoutStatus.SEND_NOT_RECEIVING);// 发货单状态：5-已发货（未收货）
			sendoutItemsDO.setSendoutQty(responseItem.getThisSendQty());//发货数
			sendoutItemsDO.setAddUserCode(operator.getSoName());//创建人编码
			sendoutItemsDO.setUpdateUserCode(operator.getSoName());//修改人编码
			sendoutItems.add(sendoutItemsDO);
		}
		if(sumIsSupplierAdd){
			sendoutSummaryBacktoDO.setIsSupplierAddProduct("Y");
		}
		
		if(needResponseIds!=null && !"".equals(needResponseIds)){
			proPurOrderItemsIds=proResponseItemsDao.findReportOrderItemsIds(StringUtils.changeStrToLongList(needResponseIds));
		}
		
		log.info("========>需要回告的回告细目ids为:"+needResponseIds);
		if(needResponseIds != null && !"".equals(needResponseIds)){
			List<Long> responItemsIds=StringUtils.changeStrToLongList(needResponseIds);
			proResponseItemsDao.updateResponStatus(responItemsIds);
			log.info("========>需要回告的回告细目ids为:"+needResponseIds +"修改更新回告状态成功!");
			backtoInterFaceService.batchSaveSendDatas(responItemsIds, 1);//把需要发送的回告信息写入代发表
		}
		
		
		
		log.info("开始保存发货单信息>>>");
		String sendoutSummaryId=saveSendOutgoods(sendoutSummaryBacktoDO, sendoutItems);
		
		
		log.info("开始调用修改订单状态的存储过程>>>>  创建人编码:{"+operator.getSoName()+"} " +
				"采购商id:{"+sendoutSummaryBacktoDO.getPurchaserId()+"} 订单细目ids:{"+proPurOrderItemsIds+"}iOpFlag:{0}");
		systemBacktoService.updateOrderState(operator.getSoName(), sendoutSummaryBacktoDO.getPurchaserId(), proPurOrderItemsIds,"0");
		
		
		log.info("调用发送发货单信息接口: 发货单号{"+sendoutSummaryBacktoDO.getSendoutGoodsCode()+"} " +
				"供应商id{"+sendoutSummaryBacktoDO.getSupplierId()+"} 采购商id{"+sendoutSummaryBacktoDO.getPurchaserId()+"} isModify{false}");
		deliveredInterfaceService.sendOutDelivered(sendoutSummaryBacktoDO.getSendoutGoodsCode(), sendoutSummaryBacktoDO.getSupplierId(), sendoutSummaryBacktoDO.getPurchaserId(), false);
		
		
		if("TEMP".equals(sendoutSummaryBacktoDO.getSourceType())){//如果是模板方式发货,则要删除临时表数据
			log.info("删除临时回告信息: 用户名{"+operator.getSoName()+"} 发货单号{"+sendoutSummaryBacktoDO.getHistorySendoutCode()+"}");
			proResponseItemsTDao.delProResponseItemsTsByUserNameAndSendCode(operator.getSoName(), sendoutSummaryBacktoDO.getHistorySendoutCode());
		}if("B_TEMP".equals(sendoutSummaryBacktoDO.getSourceType())){//如果是空白模板方式发货,则要删除临时表数据
			log.info("删除临时回告信息: 用户名{"+operator.getSoName()+"}");
			proResponseItemsTDao.delProResponseItemsTsByUserName(operator.getSoName(), null);
		}
		return sendoutSummaryId;
		
	}


	/**
	 * 根据ID（传入实体）查询总目信息
	 * @author YT 2016-7-28
	 * @param pssc
	 * @return
	 */
	@Override
	public List<ProSendoutSummaryBacktoDO> queryProSendoutSummary(ProSendoutSummaryBacktoCondition pssc) {
		return proSendoutSummaryDao.queryProSendoutSummary(pssc);
	}
	
	/**
	 * 
	 * @Description: 模板发货批量保存回告数据
	 * @param @param unSendBacktoDOs 不能发货回告的
	 * @param @param SendBacktoDOs 可以发货回告
	 * @param @return
	 * @param @throws Exception   
	 * @return String  
	 * @throws
	 * @author luohoudong
	 */
	@Transactional(rollbackFor=Exception.class)
	public void batchSaveResponseFromTemp(List<ProResponseItemsBacktoDO> SendBacktoDOs,String userCode) throws Exception{
		//调用批量保存回告方法
		String needResponseIds=responseItemsService.batchSaveResponse(SendBacktoDOs, userCode,"TEMP");
		//删除临时回告信息
		proResponseItemsTDao.delProResponseItemsTsByUserName(userCode,"report");
		if(needResponseIds!=null && !"".equals(needResponseIds)){
			log.info("========>需要回告的回告细目ids为:"+needResponseIds);
			List<Long> responItemsIds=StringUtils.changeStrToLongList(needResponseIds);
			proResponseItemsDao.updateResponStatus(responItemsIds);
			log.info("========>需要回告的回告细目ids为:"+needResponseIds +"修改更新回告状态成功!");
			
			backtoInterFaceService.batchSaveSendDatas(responItemsIds, 1);//把需要发送的回告信息写入代发表
		}
		if(needResponseIds!=null && !"".equals(needResponseIds)){
			log.info("模板回告发货（只回告） ===>调用修改订单状态存储过程的回告细目ids{"+needResponseIds+"}");
		}
		if(needResponseIds!=null && !"".equals(needResponseIds)){
			List<Long> responItemsIds=StringUtils.changeStrToLongList(needResponseIds);
			List<ProResponseItemsBacktoDO> backtoDos=proResponseItemsDao.findResponItemsByIds(responItemsIds);
			for (ProResponseItemsBacktoDO proResponseItemsBacktoDO : backtoDos) {
				log.info("开始调用修改订单状态的存储过程>>>>  创建人编码:{"+userCode+"} " +
						"采购商id:{"+proResponseItemsBacktoDO.getPurchaserId()+"} 订单细目ids:{"+proResponseItemsBacktoDO.getProPurOrderItemsId()+"}iOpFlag:{0}");
				systemBacktoService.updateOrderState(userCode,proResponseItemsBacktoDO.getPurchaserId(),proResponseItemsBacktoDO.getProPurOrderItemsId().toString(),"0");
			}
		}
		if(needResponseIds!=null && !"".equals(needResponseIds)){
			log.info("模板回告发货（只回告） ===>调用修改订单状态存储过程的回告细目ids{"+needResponseIds+"} 调用存储过程成功!");
		}
		
		
	}
	
	
	/**
	 * 检查发货单是否可以发货
	 * A:存在品种所属订单的订单种类=馆配订单；
	 * B:存在品种所属订单的订单种类=大中专、团购、活动订书
	 * C:本次发货的品种，所属订单的仓位不一致
	 * D:勾选了直供订单品种，且还有其它订单品种
	 * E:其他采购商已经使用了此发货单号
	 * @author luohoudong
	 * @version created at 2016-8-9 上午10:40:42
	 * @param responItemsDOs
	 * @param summaryBacktoCondition
	 * @return 成功："S"   错误代码：A、B、C、D、E
	 */
	public String checkSendoutIsMaySend(List<ProResponseItemsBacktoDO> responItemsDOs,ProSendoutSummaryBacktoCondition summaryBacktoCondition){
		Operator operator=SessionUser.getSessionOperator();//从session中获取登录用户信息
		String checkFlag="S";//成功，满足发货
		/************************校验需要发货品种  开始  ********************/
		/******************************e校验开始*****************//*
		//查看供应商的其他其他采购商是否已经使用了该发货单号
		Integer  sNum= proSendoutSummaryDao.findSupplierIdSendoutGoodsIsExist(summaryBacktoCondition);
		if(sNum>0){
			return "E";
		}
		*//******************************e校验结束*****************/
		/******************************d校验开始(直供订单)*****************/
		//检查发货方式是否不一致
		Boolean sendTypeFlag=checkOrderSendGoodsTypeIsDiff(responItemsDOs);
		if(sendTypeFlag){
			return "D";
		}
		/******************************d校验结束(直供订单)*****************/
		/******************************c校验开始(检查仓位是否一致)*****************/
		Boolean dcFlag=checkOrderWarehousesIsDiff(responItemsDOs);
		if(dcFlag){
			return "C";
		}
		/******************************c校验结束(检查仓位是否一致)*****************/
		
		/******************************a校验开始*****************/
		//根据采购订单号和采购商id查询订单细目信息
		Boolean pgFlag=checkOrderIsPartSendA(responItemsDOs, operator.getSapvendorId(),summaryBacktoCondition.getPurchaserId());
		//如果存在品种所属订单的订单种类=馆配订单,未处理品种中（本次发货数+已发数）<订数，则给出提示a
		if(pgFlag){
			return "A";
		}
		/******************************a校验结束*****************/
		/******************************b校验开始*****************/
		//存在品种所属订单的订单种类=大中专、团购、活动订书
		Boolean bFlag=checkOrderIsPartSendB(responItemsDOs, operator.getSapvendorId(),summaryBacktoCondition.getPurchaserId());
		//如果存在品种所属订单的订单种类=馆配订单,未处理品种中（本次发货数+已发数）<订数，则给出提示a
		if(bFlag){
			return "B";
		}
		/******************************b校验结束*****************/
		/************************校验需要发货品种  结束  ********************/
		return checkFlag;
	}
	
	
	
	/**
	 * 检查馆配订单是否部分发货
	 * @author luohoudong
	 * @version created at 2016-8-9 下午4:00:23
	 * @param responItemsDOs
	 * @param suplierId
	 * @return true:是  false：否
	 */
	public boolean checkOrderIsPartSendA(List<ProResponseItemsBacktoDO> responItemsDOs,String supplierId,String purchaseId){
		String orderTypes=OrderType.GP_ORDER.toString();
		Map<String,Integer> map=new HashMap<String, Integer>();
		String purchaserOrderCodes="";//采购订单号
		for (ProResponseItemsBacktoDO proResponseItemsBacktoDO : responItemsDOs) {
			purchaserOrderCodes=StringUtils.idsAddChildId(purchaserOrderCodes,proResponseItemsBacktoDO.getPurchaseOrderCode(),",");
			if(proResponseItemsBacktoDO.getProPurOrderItemsId()!=null){
				//把页面相应订单细目id和本次发货数 放入map的k(订单细目id) v(本次发货数)里面
				map.put(proResponseItemsBacktoDO.getProPurOrderItemsId().toString(),proResponseItemsBacktoDO.getThisSendQty());
			}
		}
		ProPurOrderSummaryBacktoCondition condition=new ProPurOrderSummaryBacktoCondition();
		condition.setPurchaserOrderCodes(purchaserOrderCodes);
		condition.setPurchaserId(purchaseId);
		condition.setSupplierId(supplierId);
		condition.setOrderTypes(orderTypes);
		List<ProPurOrderItemsBacktoDO> orderItemsList=proPurOrderItemsDao.findProPurOrderItemsBacktoDOs(condition);
		for (ProPurOrderItemsBacktoDO proPurOrderItemsBacktoDO : orderItemsList) {
			//获取对应细目的本次发货数
			Integer thisSendQty= map.get(proPurOrderItemsBacktoDO.getId().toString());//通过馆配的订单细目id能找到相对应的本次发货数
			thisSendQty=thisSendQty==null?0:thisSendQty;
			//只针对未处理的品种进行判断
			if(thisSendQty>0 && OrderType.GP_ORDER.equals(proPurOrderItemsBacktoDO.getOrderType()) 
					&& (proPurOrderItemsBacktoDO.getOrderQty()-proPurOrderItemsBacktoDO.getSendoutQty())>0 
					&& "Y".equals(proPurOrderItemsBacktoDO.getIsValid())){
				Integer sendoutQty=proPurOrderItemsBacktoDO.getSendoutQty();//已发数
				Integer orderQty=proPurOrderItemsBacktoDO.getOrderQty();//定数
				//如果 本次发货数+已发数>=订数
				if(thisSendQty+sendoutQty<orderQty){
					return true;
				}
			}
			
		}
		
		return false;
	}
	
	/**
	 * 检查(大中专、团购、活动订书)订单是否部分发货
	 * @author luohoudong
	 * @version created at 2016-8-9 下午4:00:23
	 * @param responItemsDOs
	 * @param suplierId
	 * @return true:是  false：否
	 */
	public boolean checkOrderIsPartSendB(List<ProResponseItemsBacktoDO> responItemsDOs,String supplierId,String purchaseId){
		String orderTypes=OrderType.DZZ_ORDER+","+OrderType.TG_ORDER+","+OrderType.HD_ORDER;
		Map<String,Integer> map=new HashMap<String, Integer>();
		String purchaserOrderCodes="";//采购订单号
		for (ProResponseItemsBacktoDO proResponseItemsBacktoDO : responItemsDOs) {
			purchaserOrderCodes=StringUtils.idsAddChildId(purchaserOrderCodes,proResponseItemsBacktoDO.getPurchaseOrderCode(),",");
			if(proResponseItemsBacktoDO.getProPurOrderItemsId()!=null){
				//把页面相应订单细目id和本次发货数 放入map的k(订单细目id) v(本次发货数)里面
				map.put(proResponseItemsBacktoDO.getProPurOrderItemsId().toString(),proResponseItemsBacktoDO.getThisSendQty());
			}
			
		}
		ProPurOrderSummaryBacktoCondition condition=new ProPurOrderSummaryBacktoCondition();
		condition.setPurchaserOrderCodes(purchaserOrderCodes);
		condition.setPurchaserId(purchaseId);
		condition.setSupplierId(supplierId);
		condition.setOrderTypes(orderTypes);
		List<ProPurOrderItemsBacktoDO> orderItemsList=proPurOrderItemsDao.findProPurOrderItemsBacktoDOs(condition);
		for (ProPurOrderItemsBacktoDO proPurOrderItemsBacktoDO : orderItemsList) {
			//获取对应细目的本次发货数
			Integer thisSendQty= map.get(proPurOrderItemsBacktoDO.getId().toString());//通过馆配的订单细目id能找到相对应的本次发货数
			thisSendQty=thisSendQty==null?0:thisSendQty;
			//只针对未处理的品种进行判断
			if(thisSendQty>0 && (OrderType.DZZ_ORDER==proPurOrderItemsBacktoDO.getOrderType() || 
					OrderType.TG_ORDER.equals(proPurOrderItemsBacktoDO.getOrderType()) || OrderType.HD_ORDER.equals(proPurOrderItemsBacktoDO.getOrderType()))
					&& (proPurOrderItemsBacktoDO.getOrderQty()-proPurOrderItemsBacktoDO.getSendoutQty())>0 
					&& "Y".equals(proPurOrderItemsBacktoDO.getIsValid())){
				Integer sendoutQty=proPurOrderItemsBacktoDO.getSendoutQty();//已发数
				Integer orderQty=proPurOrderItemsBacktoDO.getOrderQty();//定数
				//如果 本次发货数+已发数>=订数
				if(thisSendQty+sendoutQty<orderQty){
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	/**
	 * 检查发货品种仓位是否不一致
	 * @author luohoudong
	 * @version created at 2016-8-10 上午11:37:17
	 * @param responItemsDOs
	 * @return true:不一致   false：一致
	 */
	public boolean checkOrderWarehousesIsDiff(List<ProResponseItemsBacktoDO> responItemsDOs){
		Map<String,Integer> map=new HashMap<String, Integer>();
		String ids="";//订单细目ids
		
		for (ProResponseItemsBacktoDO proResponseItemsBacktoDO : responItemsDOs) {
			if(proResponseItemsBacktoDO.getProPurOrderItemsId()!=null){
				ids=StringUtils.idsAddChildId(ids,proResponseItemsBacktoDO.getProPurOrderItemsId().toString(),",");
				//把页面相应订单细目id和本次发货数 放入map的k(订单细目id) v(本次发货数)里面
				map.put(proResponseItemsBacktoDO.getProPurOrderItemsId().toString(),proResponseItemsBacktoDO.getThisSendQty());
			}
		}
		if(!"".equals(ids)){
			ProPurOrderItemsBacktoCondition condition=new ProPurOrderItemsBacktoCondition();
			condition.setIds(ids);
			List<ProPurOrderItemsBacktoDO> orderItemsList=proPurOrderItemsDao.findProPurOrderItems(condition);
			String warehouse="";//仓位
			for (ProPurOrderItemsBacktoDO proPurOrderItemsBacktoDO : orderItemsList) {
				Integer thisSendQty= map.get(proPurOrderItemsBacktoDO.getId().toString());//通过订单细目id能找到相对应的本次发货数
				thisSendQty=thisSendQty==null?0:thisSendQty;
				//如果本次发货数>0
				if(thisSendQty>0){
					if("".equals(warehouse)){
						warehouse=proPurOrderItemsBacktoDO.getWarehouse();
					}else if(!warehouse.equals(proPurOrderItemsBacktoDO.getWarehouse())){
						return true;
					}
				}
				
			}
		}
		return false;
	}
	/**
	 * 检查发货方式是否不一致
	 * @author luohoudong
	 * @version created at 2016-8-10 上午11:37:17
	 * @param responItemsDOs
	 * @return true:不一致   false：一致
	 */
	public boolean checkOrderSendGoodsTypeIsDiff(List<ProResponseItemsBacktoDO> responItemsDOs){
		Map<String,Integer> map=new HashMap<String, Integer>();
		String ids="";//订单细目ids
		String purchaserOrderCode="";//采购订单号
		Boolean isZG=false;//发货方式是否直供
		Boolean orderCodeFlag=false;//订单号是否不一致
		for (ProResponseItemsBacktoDO proResponseItemsBacktoDO : responItemsDOs) {
			if(proResponseItemsBacktoDO.getProPurOrderItemsId()!=null){
				ids=StringUtils.idsAddChildId(ids,proResponseItemsBacktoDO.getProPurOrderItemsId().toString(),",");
				//把页面相应订单细目id和本次发货数 放入map的k(订单细目id) v(本次发货数)里面
				map.put(proResponseItemsBacktoDO.getProPurOrderItemsId().toString(),proResponseItemsBacktoDO.getThisSendQty());
			}
		}
		if(!"".equals(ids)){
			ProPurOrderItemsBacktoCondition condition=new ProPurOrderItemsBacktoCondition();
			condition.setIds(ids);
			List<ProPurOrderItemsBacktoDO> orderItemsList=proPurOrderItemsDao.findProPurOrderItems(condition);
			for (ProPurOrderItemsBacktoDO proPurOrderItemsBacktoDO : orderItemsList) {
				Integer thisSendQty= map.get(proPurOrderItemsBacktoDO.getId().toString());//通过订单细目id能找到相对应的本次发货数
				thisSendQty=thisSendQty==null?0:thisSendQty;
				//如果本次发货数>0
				if(thisSendQty>0){
					if("2".equals(proPurOrderItemsBacktoDO.getSendGoodsType()))
						isZG=true;//如果是直供标记直供标示
					else if("".equals(proPurOrderItemsBacktoDO.getPurchaseOrderCode())){
						purchaserOrderCode=proPurOrderItemsBacktoDO.getPurchaseOrderCode();
					}else if(!purchaserOrderCode.equals(proPurOrderItemsBacktoDO.getPurchaseOrderCode())){
						orderCodeFlag=true;
					}
				}
				if(isZG && orderCodeFlag){
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	
	
	

}

