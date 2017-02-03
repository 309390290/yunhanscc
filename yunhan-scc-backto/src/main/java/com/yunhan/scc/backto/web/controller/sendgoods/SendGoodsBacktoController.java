package com.yunhan.scc.backto.web.controller.sendgoods;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.yunhan.scc.backto.web.controller.BaseController;
import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsBacktoDO;
import com.yunhan.scc.backto.web.entities.sendgoods.ProSendoutSummaryBacktoDO;
import com.yunhan.scc.backto.web.entities.system.ConstantBacktoDo;
import com.yunhan.scc.backto.web.entities.system.SendRuleConfigDo;
import com.yunhan.scc.backto.web.model.backreport.ProResponseItemsTBacktoCondition;
import com.yunhan.scc.backto.web.model.sendgoods.ProSendoutSummaryBacktoCondition;
import com.yunhan.scc.backto.web.model.system.SendRuleConfigCondition;
import com.yunhan.scc.backto.web.service.backreport.ProResponseItemsTService;
import com.yunhan.scc.backto.web.service.sendgoods.SendgoodsService;
import com.yunhan.scc.backto.web.service.system.ConstantBacktoService;
import com.yunhan.scc.backto.web.service.system.SendRuleConfigService;
import com.yunhan.scc.tools.page.Json;
import com.yunhan.scc.tools.properties.ParameterTool;
import com.yunhan.scc.tools.util.DateUtils;
import com.yunhan.scc.tools.util.FileDownload;
import com.yunhan.scc.trading.web.entities.system.Operator;
import com.yunhan.scc.trading.web.spring.SessionUser;
/**
 * 发货控制器
 * @author wangtao
 * 2016年7月14日13:57:31
 */
@Controller
@RequestMapping("/backto/sendGoods")
public class SendGoodsBacktoController  extends BaseController{
	private Log log = LogFactory.getLog(SendGoodsBacktoController.class);
	
	@Autowired
	private ProResponseItemsTService proResponseItemsTService;
	@Autowired
	private SendgoodsService sendgoodsService;
	@Autowired
	private ConstantBacktoService constantBacktoService;
	@Autowired
	private SendRuleConfigService sendRuleConfigService;
	 
	public static final long FIlESIZE = 3 * 1024 * 1024;//文件大小 3M
	public static final int FILENUMBER = 10;//文件个数 10个
	
	
	/**
	 * 跳转到发货处理页面
	 * @author luohoudong
	 * @version created at 2016-7-19 上午11:49:18
	 * @param request
	 * @return
	 */
	@RequestMapping("/page/sendGoodsProcessing")
	public String toSendGoodsProcessing(HttpServletRequest request){
		Operator operator=SessionUser.getSessionOperator();//从session中获取登录用户信息
		String reportIds=request.getParameter("reportIds");
//		String purchaserId = request.getParameter("purchaserId");
		//reportIds="3986,4815,4901,4902";
		List<ConstantBacktoDo> constants = constantBacktoService.getConstantsByType("ListAddGoodsPurchaserId");
		request.setAttribute("addProductPurs",
				constants.get(0).getValue());
		ProSendoutSummaryBacktoDO sendoutSummaryDO=sendgoodsService.getSendoutHeaders(reportIds);
		request.setAttribute("sendReg", "none");//发货单正则表达式
		try {
			SendRuleConfigCondition sendRule = new SendRuleConfigCondition();//update wuyounan 2016-10-26
			sendRule.setSupplierId(operator.getSapvendorId()); 
			sendRule.setPurchaserId(sendoutSummaryDO.getPurchaserId()); 
			sendRule.setIsValid("Y");
			 List<SendRuleConfigDo> sendRuleConfigDoList= sendRuleConfigService.getSendRuleConfigBySupplierId(sendRule);
			 if(sendRuleConfigDoList.size()>0){ 
				 request.setAttribute("sendReg",sendRuleConfigDoList.get(0).getRegularExpression());
			 }//update wuyounan 2016-10-26
		} catch (Exception e) {
			log.error("供应商:"+operator.getSapvendorId()+"获取发货单号发送规则配置出现错误!", e);
			log.info("供应商:"+operator.getSapvendorId()+"获取发货单号发送规则配置出现错误!");
		}
		request.setAttribute("sendoutSummaryDO", sendoutSummaryDO);
		request.setAttribute("reportIds", reportIds);
		return "/backto/sendgoods/sendGoodsProcessingNew";
	}
	
	
	/**
	 * 跳转到模板发货处理页面
	 * @author luohoudong
	 * @version created at 2016-7-19 上午11:49:18
	 * @param request
	 * @return
	 */
	@RequestMapping("/page/sendGoodsFromTemp")
	public String toSendGoodsFromTemp(HttpServletRequest request){
		Operator operator=SessionUser.getSessionOperator();//从session中获取登录用户信息
		String today=DateUtils.getCurrentDate("yyyy-MM-dd");
		String source =request.getParameter("source");
//		String purchaserId = request.getParameter("purchaserId_hiden");
		List<String>  sendOutCodes=proResponseItemsTService.findProResponseItemsTSendoutGoods(operator.getSoName());
		ProResponseItemsTBacktoCondition condition=new ProResponseItemsTBacktoCondition();
		condition.setAddUserCode(operator.getSoName());
		Integer onReportNum=  proResponseItemsTService.findOnlyReportItemsTs_count(condition);
		if(onReportNum.equals(0)){
			request.setAttribute("reportShow", false);
		}else{
			request.setAttribute("reportShow", true);
		}
		request.setAttribute("sendReg", "none");//发货单正则表达式
		request.setAttribute("sendOutCodes", sendOutCodes);
		request.setAttribute("source", source);
		request.setAttribute("today", today);
		return "/backto/sendgoods/sendGoodsFromTempNew";
	}
	
	/**
	 * 跳转到空白模板发货处理页面
	 * @author luohoudong
	 * @version created at 2016-7-19 上午11:49:18
	 * @param request
	 * @return
	 */
	@RequestMapping("/page/sendGoodsFromBlankTemp")
	public String toSendGoodsFromBlankTemp(HttpServletRequest request){
		Operator operator=SessionUser.getSessionOperator();//从session中获取登录用户信息
		String parm =request.getParameter("sendOutSummary");
		String purchaserName=request.getParameter("purchaserName_hiden");
		String purchaserId = request.getParameter("purchaserId_hiden");
		ProSendoutSummaryBacktoDO sendOutSummary=JSONObject.parseObject(parm, ProSendoutSummaryBacktoDO.class);
		//List<String>  sendOutCodes=proResponseItemsTService.findProResponseItemsTSendoutGoods(operator.getSoName());
		request.setAttribute("sendReg", "none");//发货单正则表达式
		try {
			SendRuleConfigCondition sendRule = new SendRuleConfigCondition();//update wuyounan 2016-10-26
			sendRule.setSupplierId(operator.getSapvendorId()); 
			sendRule.setPurchaserId(purchaserId); 
			sendRule.setIsValid("Y");
			List<SendRuleConfigDo> sendRuleConfigDoList= sendRuleConfigService.getSendRuleConfigBySupplierId(sendRule);
			 if(sendRuleConfigDoList.size()>0){
				 request.setAttribute("sendReg",sendRuleConfigDoList.get(0).getRegularExpression());
			 }
		} catch (Exception e) {
			log.error("供应商:"+operator.getSapvendorId()+"获取发货单号发送规则配置出现错误!", e);
			log.info("供应商:"+operator.getSapvendorId()+"获取发货单号发送规则配置出现错误!");
		}
		sendOutSummary.setPurchaserName(purchaserName);
		request.setAttribute("sendOutSummary",sendOutSummary);
		return "/backto/sendgoods/sendGoodsFromBlankTempNew";
	}
	
	/**
	 * 页面方式发货
	 * @author luohoudong
	 * @version created at 2016-7-19 上午11:51:10
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/sendOutFromPage", method = RequestMethod.POST)
	public void sendOutFromPage(HttpServletRequest request, HttpServletResponse response){
		log.info("页面发货开始=========>");
		Json json = new Json();
		String sendOutSummary=request.getParameter("sendOutSummary");
		String responItems=request.getParameter("responItems");
		ProSendoutSummaryBacktoDO summaryDO=JSONObject.parseObject(sendOutSummary, ProSendoutSummaryBacktoDO.class);
		List<ProResponseItemsBacktoDO> responItemsDOs=JSONObject.parseArray(responItems, ProResponseItemsBacktoDO.class);
		try {
			String sendoutSummaryId=sendgoodsService.reportAndSendout(summaryDO, responItemsDOs, "PAGE");
			json.setSuccess(true);
			json.setObj(sendoutSummaryId);
			json.setMsg("发货成功!");
			log.info("页面发货结束=========>");
		} catch (Exception e) {
			log.error("页面方式发货异常!发货单号:"+summaryDO.getSendoutGoodsCode(), e);
			json.setSuccess(false);
			json.setMsg("页面方式发货异常!发货单号:"+summaryDO.getSendoutGoodsCode());
		}
		writeJson(json, response);
	}
	
	/**
	 * 模板方式发货
	 * @author luohoudong
	 * @version created at 2016-8-2 上午9:43:43
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/sendOutFromTemp", method = RequestMethod.POST)
	public void sendOutFromTemp(HttpServletRequest request, HttpServletResponse response){
		log.info("模板发货开始=========>");
		Json json = new Json();
		String sendOutSummary=request.getParameter("sendOutSummary");
		String responItems=request.getParameter("responItems");
		ProSendoutSummaryBacktoDO summaryDO=JSONObject.parseObject(sendOutSummary, ProSendoutSummaryBacktoDO.class);
		List<ProResponseItemsBacktoDO> responItemsDOs=JSONObject.parseArray(responItems, ProResponseItemsBacktoDO.class);
		try {
			sendgoodsService.reportAndSendout(summaryDO, responItemsDOs, "TEMP");
			json.setSuccess(true);
			json.setMsg("发货成功!");
			log.info("模板发货结束=========>");
			 
		} catch (Exception e) {
			log.error("模板方式发货异常", e);
			json.setSuccess(false);
			json.setMsg("模板方式发货异常!");
		}
		writeJson(json, response);
	}
	
	/**
	 * 空白模板发货
	 * @author luohoudong
	 * @version created at 2016-8-2 上午9:43:43
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/sendOutFromBlankTemp", method = RequestMethod.POST)
	public void sendOutFromBlankTemp(HttpServletRequest request, HttpServletResponse response){
		log.info("空白模板发货开始=========>");
		Json json = new Json();
		String sendOutSummary=request.getParameter("sendOutSummary");
		String responItems=request.getParameter("responItems");
		ProSendoutSummaryBacktoDO summaryDO=JSONObject.parseObject(sendOutSummary, ProSendoutSummaryBacktoDO.class);
		List<ProResponseItemsBacktoDO> responItemsDOs=JSONObject.parseArray(responItems, ProResponseItemsBacktoDO.class);
		try {
			sendgoodsService.reportAndSendout(summaryDO, responItemsDOs, "B_TEMP");
			json.setSuccess(true);
			json.setMsg("发货成功!");
			log.info("空白模板发货结束=========>");
		} catch (Exception e) {
			log.error("空白模板方式发货异常", e);
			json.setSuccess(false);
			json.setMsg("空白模板方式发货异常!");
		}
		writeJson(json, response);
	}
	
	/**
	 * 模板方式回告
	 * @author luohoudong
	 * @version created at 2016-8-2 上午9:43:43
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveResponseFromTemp", method = RequestMethod.POST)
	public void saveResponseFromTemp(HttpServletRequest request, HttpServletResponse response){
		log.info("模板回告开始=========>");
		Json json = new Json();
		try {
			Operator operator = SessionUser.getSessionOperator();
			if(null == operator){
				json.setSuccess(false);
				json.setMsg("用户登陆失败，请重新登陆！");
			}else{
				String saveSend = request.getParameter("saveSend");
				List<ProResponseItemsBacktoDO> SendBacktoDOs = JSONObject.parseArray(saveSend,ProResponseItemsBacktoDO.class);
				sendgoodsService.batchSaveResponseFromTemp(SendBacktoDOs,operator.getSoName());
				json.setSuccess(true);
				log.info("模板回告结束=========>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			json.setSuccess(false);
			json.setMsg("保存数据后台服务器异常");
		}
		writeJson(json, response);
	}
	
	
	
	/**
	 * 检查发货单是否可以发货
	 * A:存在品种所属订单的订单种类=馆配订单；
	 * B:存在品种所属订单的订单种类=大中专、团购、活动订书
	 * C:本次发货的品种，所属订单的仓位不一致
	 * D:勾选了直供订单品种，且还有其它订单品种
	 * E:其他采购商已经使用了此发货单号
	 * S:通过验证
	 * @author luohoudong
	 * @version created at 2016-8-9 上午10:25:37
	 * @param request
	 * @param response
	 * 
	 */
	@RequestMapping(value = "/checkSendoutIsMaySend", method = RequestMethod.POST)
	public void checkSendoutIsMaySend(HttpServletRequest request, HttpServletResponse response){
		log.info(" 检查发货单是否可以发货开始=========>");
		Json json = new Json();
		json.setSuccess(false);
		try {
			String responItems=request.getParameter("responItems");
			String sendOutSummary=request.getParameter("sendOutSummary");
			ProSendoutSummaryBacktoCondition sendoutSummaryBacktoCondition=JSONObject.parseObject(sendOutSummary, ProSendoutSummaryBacktoCondition.class);
			List<ProResponseItemsBacktoDO> responItemsDOs=JSONObject.parseArray(responItems, ProResponseItemsBacktoDO.class);
			if(sendoutSummaryBacktoCondition==null){
				json.setMsg(" 检查发货单是否可以发货====>传入参数缺失{sendOutSummary}!");
			}if(responItemsDOs==null ){
				json.setMsg(" 检查发货单是否可以发货====>传入参数缺失{responItems}!");
			}else{
				String  msgCode=sendgoodsService.checkSendoutIsMaySend(responItemsDOs,sendoutSummaryBacktoCondition);
				json.setSuccess(true);
				json.setObj(msgCode);
			}
		} catch (Exception e) {
			log.error("检查发货单是否可以发货异常", e);
			json.setSuccess(false);
			json.setMsg("检查发货单是否可以发货异常!");
		}
		writeJson(json, response);
		
	}
	
	
	
	
	
	
	/**
	 * 检查发货单是否可以追加
	 * @author luohoudong
	 * @version created at 2016-7-19 下午4:20:10
	 * @param request
	 * @param response
	 * num > 1 则追加的发货单品种已收货 ;num=1  则追加的发货单仓位不一致;num=0 则可以追加 ;num= -1 则订单号不重复
	 */
	@RequestMapping(value = "/checkSendoutAdditional", method = RequestMethod.POST)
	public void checkSendoutAdditional(HttpServletRequest request, HttpServletResponse response){
		log.info(" 检查发货单是否可以追加开始=========>");
		Json json = new Json();
		json.setSuccess(false);
		try {
			String sendOutSummary=request.getParameter("sendOutSummary");
			String responItems=request.getParameter("responItems");
			ProSendoutSummaryBacktoCondition sendoutSummaryBacktoCondition=JSONObject.parseObject(sendOutSummary, ProSendoutSummaryBacktoCondition.class);
			List<ProResponseItemsBacktoDO> responItemsDOs=JSONObject.parseArray(responItems, ProResponseItemsBacktoDO.class);
			if(sendoutSummaryBacktoCondition.getPurchaserId()==null || 
			    "".equals(sendoutSummaryBacktoCondition.getPurchaserId())){
				json.setMsg("检查发货单是否可以追加====>采购商id不能为空!");
			}else if(sendoutSummaryBacktoCondition.getSupplierId()==null || 
				    "".equals(sendoutSummaryBacktoCondition.getSupplierId())){
				json.setMsg("检查发货单是否可以追加====>供应商id不能为空!");
			}else if(sendoutSummaryBacktoCondition.getReceiveWarehouse()==null || 
				    "".equals(sendoutSummaryBacktoCondition.getReceiveWarehouse())){
				json.setMsg("检查发货单是否可以追加====>仓位不能为空!");
			}/*else if(sendoutSummaryBacktoCondition.getOrderItemsIds()==null || 
				    "".equals(sendoutSummaryBacktoCondition.getOrderItemsIds())){
				json.setMsg("检查发货单是否可以追加====>订单细目ids不能为空!");
			}*/else if(sendoutSummaryBacktoCondition.getSendoutGoodsCode()==null || 
				    "".equals(sendoutSummaryBacktoCondition.getSendoutGoodsCode())){
				json.setMsg("检查发货单是否可以追加====>发货单号不能为空!");
			}/*else if(responItemsDOs==null || responItemsDOs.size()<1){
				json.setMsg("检查发货单是否可以追加====>发货品种不能为空!");
			}*/else{
				Integer num =sendgoodsService.checkSendoutAdditional(sendoutSummaryBacktoCondition,responItemsDOs);
				json.setSuccess(true);
				json.setObj(num);
			}
		} catch (Exception e) {
			log.error("检查发货单是否可以追加异常", e);
			json.setSuccess(false);
			json.setMsg("检查发货单是否可以追加异常!");
		}
		writeJson(json, response);
		
	}
	
	
	
	/**
	 * 把文件上传到服务器上
	 * @author luohoudong
	 * @version created at 2016-7-19 上午11:48:59
	 * @param response
	 * @param request
	 * @param project_no
	 */
	//多文件上传
    @RequestMapping(value="/import/uploadExcel",method = RequestMethod.POST)
    @ResponseBody
    public void manyFileUpload(HttpServletResponse response,MultipartHttpServletRequest request,String project_no){
    	Json json = new  Json();
        DefaultMultipartHttpServletRequest defaultRequest = (DefaultMultipartHttpServletRequest)request;  
        MultiValueMap<String, MultipartFile> fileMap =  defaultRequest.getMultiFileMap();
        List<MultipartFile> files = fileMap.get("file");//得到页面的file   
        //目标文件夹地址，用于测试。
        String targetPath =  ParameterTool.getParameter("exportMainPath");
        if (null != targetPath) {
        	targetPath = targetPath.endsWith("\\") ? targetPath : targetPath + "/";
		}
//        System.out.println(targetPath);
        try {
        	if(null == files){
//				throw new Exception("上传失败，上传文件为空！");
        		json.setStatusAndMessage(false, "上传失败，上传文件为空！");
    			writeJson(json, response);
    			return;
        	}
        	if(files.size() > FILENUMBER){
        		json.setStatusAndMessage(false, "单次最多上传" + FILENUMBER + "个文件");
    			writeJson(json, response);
    			return;
        	}
        	Map<String,String> map = new HashMap<String, String>();
        	for(MultipartFile file : files){
//        		System.out.println("---->" + file.getOriginalFilename());
        		if(file.getSize() > FIlESIZE){
//        			throw new Exception("上传失败，文件大小不能超过10M！");
        			json.setStatusAndMessage(false, "单个上传文件大小不能超过" + FIlESIZE + "MB!");
        			writeJson(json, response);
        			return;
        		}
        		
        		targetPath +=  file.getOriginalFilename();
        		
        		
        		File taregetFile = new File(targetPath);
        		
        		if(!taregetFile.getParentFile().exists()){
        			if(!taregetFile.getParentFile().mkdirs()){
        				json.setStatusAndMessage(false, "文件无写入权限");
            			writeJson(json, response);
    					return;
        			}
        		}
        		
        		log.info("绝对路径:" + taregetFile.getAbsolutePath());
        		try {
					file.transferTo(taregetFile);
				} catch (Exception e) {
//					e.printStackTrace();
					json.setStatusAndMessage(false, "由于上次处理文件发生异常,请刷新页面重试");
        			writeJson(json, response);
					return;
				}
        		
        		
        		map.put("url", taregetFile.getAbsolutePath());
        		json.setObj(map);
        		json.setStatusAndMessage(true, "上传成功！");
        	}
		} catch (Exception e) {
			json.setStatusAndMessage(false, "上传失败！");
			log.error("文件上传失败！",e);
//			writeJson(json, response);
//			e.printStackTrace();
		}
        writeJson(json, response);
    }
    
    
    
   
    /**
     *  回告发货模板解析控制器
     * @author luohoudong
     * @version created at 2016-7-15 下午2:38:02
     * @param request
     * @param response
     */
	@RequestMapping(value="/resolveProResponseItemsT",method=RequestMethod.POST)
    public void  resolveProResponseItemsT(HttpServletRequest request,HttpServletResponse response){
    	Operator operator = SessionUser.getSessionOperator();
    	Json json = new Json();
    	
    	if(operator == null){
    		json.setStatusAndMessage(false, "用户未登录，请先登录");
    		writeJson(json, response);
    		return;
    	}
    	
//    	String sapvendorID = operator.getSapvendorId();//根据当前用户获取供应商ID 
    	String[] files = request.getParameterValues("files");
    	
    	if(files == null){
    		json.setStatusAndMessage(false, "未获取到文件路径,请重新上传重试");
    		writeJson(json, response);
    		return;
    	}
    	
    	List<String> list = Arrays.asList(files);
    	String purchaserId = "";
    	try {
			Map<String,Object> resultMap = proResponseItemsTService.resolveProResponseItemsT(list,operator,purchaserId);
			json.setSuccess(true);
			json.setObj(resultMap);
			json.setMsg("解析成功");
		} catch (Exception e) {
			json.setStatusAndMessage(false, "处理模板发生异常");
			log.error("模板解析失败！",e);
			writeJson(json, response);
			e.printStackTrace();
		}
    	writeJson(json, response);
    	
    }
	
	/**
	 * 
	 * 空白模板回告发货模板解析控制器
	 * @author luohoudong
	 * @version created at 2016-8-4 上午10:00:45
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/resolveProResponseItemsTFromBlank",method=RequestMethod.POST)
    public void  resolveProResponseItemsTFromBlank(HttpServletRequest request,HttpServletResponse response){
    	Operator operator = SessionUser.getSessionOperator();
    	Json json = new Json();
    	
    	if(operator == null){
    		json.setStatusAndMessage(false, "用户未登录，请先登录");
    		writeJson(json, response);
    		return;
    	}
    	String purchaserId=request.getParameter("purchaserId");
    	String warehouse=request.getParameter("warehouse");
//    	String sapvendorID = operator.getSapvendorId();//根据当前用户获取供应商ID 
    	String[] files = request.getParameterValues("files");
    	
    	if(files == null){ 
    		json.setStatusAndMessage(false, "未获取到文件路径,请重新上传重试");
    		writeJson(json, response);
    		return;
    	}
    	
    	List<String> list = Arrays.asList(files);
    	try {
			Map<String,Object> resultMap = proResponseItemsTService.resolveProResponseItemsTFromBlank(list,operator,purchaserId,warehouse);
			json.setSuccess(true);
			json.setObj(resultMap);
			json.setMsg("解析成功");
		} catch (Exception e) {
			json.setStatusAndMessage(false, "处理模板发生异常");
			log.error("模板解析失败！",e);
			writeJson(json, response);
			e.printStackTrace();
		}
    	writeJson(json, response);
    	
    }
	
	/**
	 * 下载错误数据
	 * @author luohoudong
	 * @version created at 2016-8-9 下午2:53:02
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportErrorData")
	public void exportErrorData(HttpServletResponse response, HttpServletRequest request) throws Exception{
		//获取文件路径
		//String path = request.getParameter("path");
		String path = new String(request.getParameter("path").getBytes("iso8859-1"), "UTF-8");
//		File file = new File(path);
	//	System.out.println("---->" + file.getAbsolutePath());
		FileDownload.downloadFile(request,response,path);
		FileDownload.deleteFile(path);
	}
	
}
