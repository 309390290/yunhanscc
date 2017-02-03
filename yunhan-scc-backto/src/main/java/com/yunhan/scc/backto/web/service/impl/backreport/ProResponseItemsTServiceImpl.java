package com.yunhan.scc.backto.web.service.impl.backreport;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yunhan.scc.backto.web.dao.mapper.backreport.ProResponseItemsTDao;
import com.yunhan.scc.backto.web.dao.mapper.order.ProPurOrderItemsDao;
import com.yunhan.scc.backto.web.dao.mapper.system.DcLocationBacktoDao;
import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsTBacktoDO;
import com.yunhan.scc.backto.web.entities.order.ProPurOrderItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutSummaryBacktoDO;
import com.yunhan.scc.backto.web.entities.system.DcLocationBacktoDo;
import com.yunhan.scc.backto.web.model.backreport.ProResponseItemsTBacktoCondition;
import com.yunhan.scc.backto.web.service.backreport.ProResponseItemsTService;
import com.yunhan.scc.tool.stream.StreamUtils;
import com.yunhan.scc.tools.component.service.impl.commonQuery.exporter.ExportServiceImpl;
import com.yunhan.scc.tools.execl.ColORRow;
import com.yunhan.scc.tools.execl.ExeclBeanInterface;
import com.yunhan.scc.tools.execl.ExeclReadUtil;
import com.yunhan.scc.tools.properties.ParameterTool;
import com.yunhan.scc.tools.util.DateUtils;
import com.yunhan.scc.tools.util.StringUtil;
import com.yunhan.scc.trading.web.entities.system.Operator;

/**
 * 
 * 临时回告发货信息service实现
 * @author luohoudong
 * @version created at 2016-7-14 下午3:48:07
 */
@Service
public class ProResponseItemsTServiceImpl implements ProResponseItemsTService{
	
	private Log log = LogFactory.getLog(ProResponseItemsTServiceImpl.class);
	
	@Autowired
	private ProResponseItemsTDao proResponseItemsTDao;
	@Autowired
	private ExeclProResponseItemsTServiceImpl execlProResponseItemsTServiceImpl;
	@Autowired
	private ExeclProResponseItemsTFromBlankServiceImpl execlProResponseItemsTFromBlankServiceImpl;
	@Autowired
	private ExeclProSendoutSummaryBacktoServiceImpl execlProSendoutSummaryBacktoServiceImpl;
	@Autowired	
	private ExportServiceImpl exportServiceImpl;
	@Autowired	
	private ProPurOrderItemsDao proPurOrderItemsDao;
	@Autowired	
	private DcLocationBacktoDao dcLocationBacktoDao; 
	
	/**
	 * 保存临时回告发货信息
	 * @author luohoudong
	 * @version created at 2016-7-14 下午3:49:27
	 * @param responseItemsTDO
	 */
	public void save(ProResponseItemsTBacktoDO responseItemsTDO){
		proResponseItemsTDao.save(responseItemsTDO);
	}
	
	/**
	 * 解析临时回告发货信息
	 * @author luohoudong
	 * @version created at 2016-7-14 下午3:55:29
	 * @param files
	 * @param operator
	 * @param purchaserId
	 * @throws Exception
	 * @return Map<String,Object>
	 */
	public Map<String,Object> resolveProResponseItemsT(List<String> files,Operator operator,final String purchaserId)throws Exception{
		final Map<String,Object> resultMap = new HashMap<String, Object>();
		final StringBuffer sendOutItems  = new StringBuffer();  //发货单号
		final List<ExeclBeanInterface> successList = new ArrayList<>();
		final List<ExeclBeanInterface> errorList = new ArrayList<ExeclBeanInterface>();
		final List<ExeclBeanInterface> errorList_1 = new ArrayList<>();//已经处理 的错误数据 数量
		final List<ExeclBeanInterface> errorList_2 = new ArrayList<>();//无法匹配的错误 数据 数量
		final CountDownLatch countDownLatch = new CountDownLatch(files.size());
		final String sapvendorID = operator.getSapvendorId();
		final String userName=operator.getSoName();
		//删除该用户历史的回告临时信息
		proResponseItemsTDao.delProResponseItemsTsByUserName(operator.getSoName(),null);
		
    	for(String tmppath : files){
    		 //System.out.println("路径:" + tmppath);
    		final File file = new File(tmppath);
    		/**
    		 * 这里由于上传的是多个文件，并且处理文件耗费时间较长。
    		 * 所以我们这里启动和文件数一样的线程来处理这些文件，以达到提高效率的作用。
    		 * 由于多个线程，需要都处理完成后，我们才能返回，所以我们采用CountDownLatch 来计数。
    		 */
    		Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					ExeclReadUtil execlReadUtil = new ExeclReadUtil();
					 InputStream is = null;
					try {
						is = execlReadUtil.readFileAsStream(file);
					} catch (IOException e1) {
						countDownLatch.countDown();
						log.info("文件路径未找到，或者读取文件时发生错误");
					}
					 if(is == null){
						 log.error("文件不可读,管理员检查文件正确性及磁盘使用情况");
					 }else{
						 try {
								//细目数据起始位置 有隐藏列读取隐藏列
								int StartDetailPos = execlReadUtil.getStartPos(is, "细目ID");
								//System.out.println("序号:" + StartDetailPos);
								is = execlReadUtil.readFileAsStream(file);//从新开始输入流
								
								/****************************细目数据开始***********************************/
								//读取细目数据
								ProResponseItemsTBacktoDO proResponseItemsTDO=new ProResponseItemsTBacktoDO();
								execlReadUtil.readDate(is, proResponseItemsTDO, execlProResponseItemsTServiceImpl, ColORRow.ROW, StartDetailPos, 0);
								List<ExeclBeanInterface> successList_Tmp = execlReadUtil.getSuccessList();
								List<ExeclBeanInterface> errorList_Tmp = execlReadUtil.getErrorList();
								errorList.addAll(errorList_Tmp);
//								System.out.println("成功数据：" + successList_Tmp.size() + ":" +"错误数据：" + errorList_Tmp.size());
								Map<String,Boolean> sendOutMap=new HashMap<String, Boolean>();
								
								if(null != successList_Tmp && successList_Tmp.size() > 0){
									for(ExeclBeanInterface bean : successList_Tmp){//把能匹配上订单的发货单放入map中
										ProResponseItemsTBacktoDO itemsTDO = (ProResponseItemsTBacktoDO) bean;
										Boolean isAdd=sendOutMap.get(itemsTDO.getSendoutGoodsCode());
										if(isAdd==null && "N".equals(itemsTDO.getIsSupplierAddProduct())){
											sendOutMap.put(itemsTDO.getSendoutGoodsCode(),true);
										}
										
									}
									for(ExeclBeanInterface bean : successList_Tmp){
										ProResponseItemsTBacktoDO itemsTDO = (ProResponseItemsTBacktoDO) bean;
										//供应商
										//itemsTDO.setSupplierId(sapvendorID);
										//采购商
										//itemsTDO.setPurchaserId(purchaserId);
										Boolean isExist=sendOutMap.get(itemsTDO.getSendoutGoodsCode());//发货单是否存在
										if(isExist!=null){
											itemsTDO.setAddUserCode(userName);
											successList.add(itemsTDO);
											//将细目信息保存到数据库 
											proResponseItemsTDao.save(itemsTDO);
										}else{
											itemsTDO.setErrorMessage("发货单号无法匹配有效订单!");
											itemsTDO.setErrorNumber(2);
											errorList_Tmp.add(itemsTDO);
											errorList.add(itemsTDO);
										}
									}
								}
								
								/**
								 * 对错误数据进行分类
								 */
								if(errorList_Tmp != null && errorList_Tmp.size() > 0){
									for(ExeclBeanInterface bean : errorList_Tmp){
										ProResponseItemsTBacktoDO itemsTDO = (ProResponseItemsTBacktoDO) bean;
										if(itemsTDO.getErrorNumber() == 1){
											errorList_1.add(itemsTDO);
										}
										if(itemsTDO.getErrorNumber() == 2){
											errorList_2.add(itemsTDO);
										}
									}
								}
								countDownLatch.countDown();
							} catch (Exception e) {
								countDownLatch.countDown();
								e.printStackTrace();
								log.info("解析数据出错!");
							}finally{
								StreamUtils.closeInStream(is);
							}
					 }
				}
			});
    		
    		t.start();//启动线程
			t.setName("线程名称:" + tmppath);
    	}
    	countDownLatch.await();
    	if(errorList.size() > 0){
			//生成错误数据文档 
			String templatePath = ParameterTool.getParameter("templatePath");
			if (null != templatePath) {
				templatePath = templatePath.endsWith("\\") ? templatePath : templatePath + "/";
			}
			//错误数据模板
			templatePath += "sendOutgoodsError.xls";
			String filePath = ParameterTool.getParameter("exportMainPath");
			if(null != filePath){
				filePath = filePath.endsWith("\\")?filePath:filePath+"/";
			}
			filePath += "errorData_"+DateUtils.getCurrentDate("yyyyMMddHHmmss")+".xls";
			Map<String, Object> beans = new HashMap<String,Object>();
			beans.put("exportMaps", errorList);
			exportServiceImpl.genWookbook(templatePath, filePath, false, null, beans, null, null, false);
			resultMap.put("errorFilePath", filePath);
		}
		resultMap.put("success", successList.size());
		resultMap.put("error", errorList.size());
		resultMap.put("errorList_1", errorList_1.size());
		resultMap.put("errorList_2", errorList_2.size());
		//是否是空白模板
		resultMap.put("isblank", "y");
		resultMap.put("sendOutgoodsCodes", sendOutItems);
		resultMap.put("sapvendorID_hiden", sapvendorID);
		resultMap.put("purchaserId_hiden", purchaserId);
		return resultMap;
	}
	
	
	/**
	 * 根据创建人编码查询出可以模板发货的发货单号 
	 * @author luohoudong
	 * @version created at 2016-7-27 下午4:37:22
	 * @param userCode
	 * @return
	 */
	public List<String> findProResponseItemsTSendoutGoods(String addUserCode){
		return proResponseItemsTDao.findProResponseItemsTSendoutGoods(addUserCode);
	}

	
	/**
	 * 空白模板解析临时回告信息
	 * @author luohoudong
	 * @version created at 2016-7-14 下午3:55:29
	 * @param files
	 * @param operator
	 * @param purchaserIds
	 * @param warehouse
	 * @throws Exception
	 * @return Map<String,Object>
	 */
	public Map<String,Object> resolveProResponseItemsTFromBlank(List<String> files,Operator operator,final String purchaserId,final String warehouse)throws Exception{
		final Map<String,Object> resultMap = new HashMap<String, Object>();
		final StringBuffer sendOutItems  = new StringBuffer();  //发货单号
		final List<ExeclBeanInterface> successList = new ArrayList<>();
		final List<ExeclBeanInterface> errorList = new ArrayList<ExeclBeanInterface>();
		final CountDownLatch countDownLatch = new CountDownLatch(files.size());
		final String sapvendorID = operator.getSapvendorId();
		final String userName=operator.getSoName();
		final ProSendoutSummaryBacktoDO sendOutSummary = new ProSendoutSummaryBacktoDO();
		log.info("warehouse:"+warehouse);
		//删除该用户历史的回告临时信息
		proResponseItemsTDao.delProResponseItemsTsByUserName(operator.getSoName(),null);
		
    	for(String tmppath : files){
    		 //System.out.println("路径:" + tmppath);
    		final File file = new File(tmppath);
    		/**
    		 * 这里由于上传的是多个文件，并且处理文件耗费时间较长。
    		 * 所以我们这里启动和文件数一样的线程来处理这些文件，以达到提高效率的作用。
    		 * 由于多个线程，需要都处理完成后，我们才能返回，所以我们采用CountDownLatch 来计数。
    		 */
    		Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					ExeclReadUtil execlReadUtil = new ExeclReadUtil();
					 InputStream is = null;
					try {
						is = execlReadUtil.readFileAsStream(file);
					} catch (IOException e1) {
						countDownLatch.countDown();
						log.info("文件路径未找到，或者读取文件时发生错误");
					}
					 if(is == null){
						 log.error("文件不可读,管理员检查文件正确性及磁盘使用情况");
					 }else{
						 try {
							 
							 
							 	//总目数据起始位置
								int startSuperOrderPos = execlReadUtil.getStartPos(is, "发货单号：");//由于该方法关闭了输入流，
								is = execlReadUtil.readFileAsStream(file);//从新开启输入流 
							 	
								//读取总目数据
								ProSendoutSummaryBacktoDO proSendoutSummaryBacktoDo = new ProSendoutSummaryBacktoDO();
								execlReadUtil.readDate(is, proSendoutSummaryBacktoDo, execlProSendoutSummaryBacktoServiceImpl, ColORRow.COLUMN, startSuperOrderPos, 0);
								
								//由于总目只是一条信息，但是这条信息不在一行，所以这里需要将获取到的List转成一个实体.
								List<ExeclBeanInterface> successList_Sum_Tmp = execlReadUtil.getSuccessList();
								log.info("发货单总目信息aa:"+JSONObject.toJSONString(successList_Sum_Tmp));
								for(ExeclBeanInterface execlBean : successList_Sum_Tmp){
									ProSendoutSummaryBacktoDO tmpSummary = (ProSendoutSummaryBacktoDO) execlBean;
									//发货单号：
									if(tmpSummary.getSendoutGoodsCode() != null){
										log.info("发货单号:"+tmpSummary.getSendoutGoodsCode());
										String tmp_SendoutGoodsCode = tmpSummary.getSendoutGoodsCode();
										sendOutSummary.setSendoutGoodsCode(StringUtil.delEndTwoZero(tmp_SendoutGoodsCode));
										//sendOutSummary.setSendoutGoodsCode(tmp_SendoutGoodsCode.endsWith("\\.00")?tmp_SendoutGoodsCode.replaceAll("\\.00", ""):tmp_SendoutGoodsCode);
									 }
									//发货日期
									if(tmpSummary.getSendoutDateStr() != null){
										sendOutSummary.setSendoutDateStr(tmpSummary.getSendoutDateStr());
									 }
									//发到站
									if(tmpSummary.getSendoutStation() != null){
										sendOutSummary.setSendoutStation(StringUtil.delEndTwoZero(tmpSummary.getSendoutStation()));
									 }
									//承运商
									if(tmpSummary.getTransportCompany() != null){
										String tmp_TransportCompany = tmpSummary.getTransportCompany();
										sendOutSummary.setTransportCompany(StringUtil.delEndTwoZero(tmp_TransportCompany));
									 }
									//运单号
									if(tmpSummary.getTransportNo() != null){
										String tmp_tn = tmpSummary.getTransportNo();
										sendOutSummary.setTransportNo(StringUtil.delEndTwoZero(tmp_tn));
										//sendOutSummary.setTransportNo(tmp_tn.endsWith("\\.00")?tmp_tn.replaceAll("\\.00", ""):tmp_tn);
									 }
									//包件数
									if(tmpSummary.getPakagesQty() != null){
										sendOutSummary.setPakagesQty(tmpSummary.getPakagesQty());
									 }
									//运输方式
									if(tmpSummary.getTransportMode() != null){
										String tm = tmpSummary.getTransportMode();
										if(tm.equals("汽运")){
											sendOutSummary.setTransportMode("1");
										}else{
											sendOutSummary.setTransportMode("2");
										}
									 }
									//发货单备注
									if(tmpSummary.getSendoutRemark() != null){
										sendOutSummary.setSendoutRemark(StringUtil.delEndTwoZero(tmpSummary.getSendoutRemark()));
									}
								}
								//log.info("订单头信息:"+JSONObject.toJSONString(sendOutSummary));
								
								sendOutSummary.setPurchaserId(purchaserId);
								sendOutSummary.setSupplierId(sapvendorID);
								sendOutSummary.setReceiveWarehouse(warehouse);
								
								//根据仓位编码查询仓位信息,把收货地址、收货电话、收货人写到发货单总目上
								DcLocationBacktoDo dc=dcLocationBacktoDao.getDCByCodeAndSapId(purchaserId, warehouse);
								if(dc!=null){
									sendOutSummary.setReceiveAddress(dc.getPaddr());//收货地址
									sendOutSummary.setReceivePhoneno(dc.getContactNumber());//收货电话
									sendOutSummary.setReceiveUserName(dc.getContact());//收货人
								}
								log.info("发货单总目信息:"+JSONObject.toJSONString(sendOutSummary));
								System.out.println("------>" + sendOutSummary.getSendoutGoodsCode());
								
								is = execlReadUtil.readFileAsStream(file);//从新开启输入流 
								//细目数据起始位置 有隐藏列读取隐藏列
								int StartDetailPos = execlReadUtil.getStartPos(is, "序号");
								//System.out.println("序号:" + StartDetailPos);
								is = execlReadUtil.readFileAsStream(file);//从新开始输入流
								
								/****************************细目数据开始***********************************/
								//读取细目数据
								ProResponseItemsTBacktoDO proResponseItemsTDO=new ProResponseItemsTBacktoDO();
								execlReadUtil.readDate(is, proResponseItemsTDO, execlProResponseItemsTFromBlankServiceImpl, ColORRow.ROW, StartDetailPos, 0);
								List<ExeclBeanInterface> successList_Tmp = execlReadUtil.getSuccessList();
								List<ExeclBeanInterface> errorList_Tmp = execlReadUtil.getErrorList();
								errorList.addAll(errorList_Tmp);
//								System.out.println("成功数据：" + successList_Tmp.size() + ":" +"错误数据：" + errorList_Tmp.size());
								
								if(null != successList_Tmp && successList_Tmp.size() > 0){
									for(ExeclBeanInterface bean : successList_Tmp){
										ProResponseItemsTBacktoDO itemsTDO = (ProResponseItemsTBacktoDO) bean;
										//供应商
										//itemsTDO.setSupplierId(sapvendorID);
										//采购商
										//itemsTDO.setPurchaserId(purchaserId);
										
										itemsTDO.setAddUserCode(userName);
										itemsTDO.setPurchaserId(purchaserId);
										itemsTDO.setSupplierId(sapvendorID);
										successList.add(itemsTDO);
										//将细目信息保存到数据库 
										proResponseItemsTDao.save(itemsTDO);
									}
								}
								
								countDownLatch.countDown();
							} catch (Exception e) {
								countDownLatch.countDown();
								e.printStackTrace();
								log.info("解析数据出错!");
							}finally{
								StreamUtils.closeInStream(is);
							}
					 }
				}
			});
    		
    		t.start();//启动线程
			t.setName("线程名称:" + tmppath);
    	}
    	countDownLatch.await();
    	if(errorList.size() > 0){
			//生成错误数据文档 
			String templatePath = ParameterTool.getParameter("templatePath");
			if (null != templatePath) {
				templatePath = templatePath.endsWith("\\") ? templatePath : templatePath + "/";
			}
			//错误数据模板
			templatePath += "sendOutgoodsError.xls";
			String filePath = ParameterTool.getParameter("exportMainPath");
			if(null != filePath){
				filePath = filePath.endsWith("\\")?filePath:filePath+"/";
			}
			filePath += "errorData_"+DateUtils.getCurrentDate("yyyyMMddHHmmss")+".xls";
			Map<String, Object> beans = new HashMap<String,Object>();
			beans.put("errorOrder", errorList);
			exportServiceImpl.genWookbook(templatePath, filePath, false, null, beans, null, null, false);
			resultMap.put("errorFilePath", filePath);
		}
		resultMap.put("success", successList.size());
		resultMap.put("error", errorList.size());
		resultMap.put("sendOutSummary", JSONObject.toJSONString(sendOutSummary));
		//是否是空白模板
		resultMap.put("isblank", "y");
		resultMap.put("sendOutgoodsCodes", sendOutItems);
		resultMap.put("sapvendorID_hiden", sapvendorID);
		resultMap.put("purchaserId_hiden", purchaserId);
		return resultMap;
	}
	
	
	/**
	 * 查看只回告数据条数
	 * @author luohoudong
	 * @version created at 2016-9-9 下午3:58:34
	 * @param condition
	 * @return
	 */
	public Integer findOnlyReportItemsTs_count(ProResponseItemsTBacktoCondition condition){
		return proResponseItemsTDao.findOnlyReportItemsTs_count(condition);
	}
}

