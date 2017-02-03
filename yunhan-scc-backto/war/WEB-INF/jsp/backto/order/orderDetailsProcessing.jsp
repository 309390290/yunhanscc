<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 
	订单处理-订单详情
	2016年7月14日16:41:04
	@author wangtao
 -->
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="UTF-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<%@ include file="/common/publicCsJs.jsp"%>
	<script type="text/javascript" src="<c:url value='/js/backto/order/orderDetailsProcessing.js'/>"></script>
	<script type="text/javascript"
	src="<c:url value='/js/backto/sendgoods/sendGoodsAndSend.js'/>"></script>
	<script type="text/javascript"
	src="<c:url value='/js/backto/order/orderImport.js'/>"></script>
	<style>
	.SetTiemBox{
		/* position: absolute;
	    top: 50%;
	    margin-left: -50px;
	    margin-top: -10px;
	    left: 50%; */
	    height: 20px;
	    width: 100px;
	    border: 1px solid #ccc;
	    display: inline-block;
	    background-color: #fff;
	}
	
	
	</style>
	
  </head>
  
  <body>
  	<!-- 头文件开始 -->
	<%@ include file="/common/menu.jsp"%>
	<!-- 头文件结束 -->
	<!-- 面包导航开始 -->
	<div class="headerBox">
		<div class="active_com">
			<span class="position"><b class="map-marker"></b>当前位置：</span>
			<ul class="ul">
				<li><a href="JavaScript:;"> <span>订单处理</span>
				</a> <b> > </b></li>
				<li><a href="JavaScript:;"> <span>订单方式</span>
				</a> <b> > </b></li>
				<li><a href="JavaScript:;"> <span>订单详情</span>
				</a></li>
			</ul>
		</div>
	</div>
	<!-- 面包导航结束 -->

	<!-- 中间内容结束开始 -->
	<div class="bodyBox">
		<div class="contentBox">
			<div class="Tab tabOne">
				<div class="infoBox">
					<div class="info_two">
						<div class="left" id="111" value="1">
							<span class="title">订单信息>></span>
						</div>
						<input type="hidden" id="purchaseOrderCode" value="${summary.purchaseOrderCode}">
						<input type="hidden" id="purchaserId" value="${summary.purchaserId}">
						<input type="hidden" id="receiveAddress" value="${summary.receiveAddress}">
						<input type="hidden" id="receiveContactUser" value="${summary.receiveContactUser}">
						<input type="hidden" id="receivePhoneno" value="${summary.receivePhoneno}">
						<input type="hidden" id="orderType" value="${summary.orderType}">
						<input type="hidden" id="requestType" value="${requestType}">
						<input type="hidden" id="isDeal" value="${isDeal}">
						<input type="hidden" id="purchaserName" value="${summary.purchaserName}">
						<input type="hidden" id="sendGoodsIds">
						<div class="right">
							<ul>
								<li>订单号码：${summary.purchaseOrderCode} </li>
								<li>制单日期：
									<c:if test="${summary.addDate!=null}">
										<fmt:formatDate value="${summary.addDate}" pattern="yyyy-MM-dd" /> 
									</c:if>
								</li>
								<li>制单人：${summary.addUser }</li>
								<li>制单人联系电话：${summary.addUserPhoneno }</li>
								<li>采购商：${summary.purchaserName}</li>
								<li>发送日期：
									<c:if test="${summary.sendDate!=null}">
										<fmt:formatDate value="${summary.sendDate}" pattern="yyyy-MM-dd" />
									</c:if>
								</li>
								<li>订单种类：${summary.orderType ==0 ? "零售订单":
			summary.orderType==5?"文教订单":summary.orderType==10?"电商订单":summary.orderType==15?"大中专订单":
			summary.orderType==20?"团购订单":summary.orderType==25?"馆配订单":"活动订书" }</li>
								<li>紧急程度：${summary.urgentFlag ==1 ? "急单":"普通单" }</li>
							</ul>
						</div>
					</div>
					<div class="info_one">
						<div class="left">
							<span class="title">收货信息>></span>
						</div>
						<div class="right">
							<ul>
								<li>收货地址：${summary.receiveAddress }</li>
								<li>收货联系人：${summary.receiveContactUser }</li>
								<li>收货联系电话：${summary.receivePhoneno }</li>
							</ul>
						</div>
					</div>
					<div class="info_one">
						<div class="left">
							<span class="title">特殊要求说明>></span>
						</div>
						<div class="right">
							<ul>
								<li>要求到货日期：
									<c:if test="${summary.reqArriveDate!=null}">
										<fmt:formatDate value="${summary.reqArriveDate}" pattern="yyyy-MM-dd" />
									</c:if>
								</li>
								<li>订单备注：${summary.orderRemark }</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="infoBox">
					<div class="left">
						<ul>
							<li>
								<div class="choice">
									<span class="title">品种处理情况：</span> 
									<input type="checkbox" value="" id="all_1" class="all-check" name="isDeal"
										<c:if test="${isDeal=='Y' }">checked="checked"</c:if>> 
										<label for="all_1">全部</label> 
									<input type="checkbox" value="Y" id="yc_1" class="single-check" name="isDeal"> 
										<label for="yc_1">已处理</label>
									<input type="checkbox" value="N" id="yc_2" class="single-check" name="isDeal" 
										<c:if test="${isDeal=='N' }">checked="checked"</c:if>>
										<label for="yc_2">待处理</label>
									<input type="checkbox" value="A" id="yc_3" class="single-check" name="isDeal" 
										<c:if test="${isDeal=='N' }">checked="checked"</c:if>>
										<label for="yc_3">部分处理</label>
								</div>
							</li>
							<li>
								<div class="choice">
									<span class="title">下载标识：</span> 
									<input type="checkbox" value="" class="all-check" id="all_2" checked="checked" name="isExport">
										<label for="all_2">全部</label> 
									<input type="checkbox" value="1" class="single-check" id="yx_1" checked="checked" name="isExport"> 
										<label for="yx_1">已下载</label>
									<input type="checkbox" value="0" class="single-check" id="yx_2" checked="checked" name="isExport">
										<label for="yx_2">未下载</label>
								</div>
							</li>
						</ul>
					</div>
					<div align="right" class="right">
						<!-- <a href="javascript:;" class="btn btn-small" id="OrderExport">导 出</a>  -->
						<input type="button" class="btn btn-small" id="OrderExport" value="导 出">
						<!--<a href="javascript:;" class="btn btn-small" id="OrderImport">导 入</a>  -->
						<input type="button" class="btn btn-small" id="importSendBtn" value="导 入">
						<!--<a href="javascript:;" id="batchInput" class="btn  btn-small" data-toggle="modal">批量录入</a> -->
						<input type="button" class="btn btn-small" data-toggle="modal" id="batchInput" value="批量录入">
						<!-- <a href="javascript:;" class="btn btn-small" id="save">保 存</a>  -->
						<input type="button" class="btn btn-small" id="save" value="保 存">
						<!-- <a href="javascript:;" id="sendGoods" class="btn btn-small" data-toggle="modal">确 认</a>  -->
						<input type="button" id="sendGoods" class="btn btn-small" data-toggle="modal" value="确 认">
						<a href="javascript:;" id="goBack" class="btn btn-small">返 回</a>
						<!-- <a href="javascript:;" class="btn btn-small btn-primary" id="closeSend">关闭发货</a> -->
						<input type="button" id="closeSend" class="btn btn-small btn-primary" value="关闭发货">
					</div>
				</div>
				<div class="sortBox">
					<div class="left"></div>
					<div class="right">
						<ul>
							<li>已筛选品种：<span id="totalVariety">0</span>个</li>
							<li>本次发货数：<span id="totalSendQty">0</span></li>
							<li>本次发货码洋：<span id="totalSendPrice">0.00</span></li>
							<li>本次发货实洋：<span id="totalSendRealPrc">0.00</span></li>
						</ul>
					</div>
				</div>
				<div class="GdBox">
					<table id="table" class="table-block"></table>
					<div id="page"></div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal弹出开始 -->
	<div id="myModal" class="modal-mini hide fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close"	aria-hidden="true" id="batchClose">×</button>
			<h5>批量录入</h5>
		</div>
		<form id="batchInputForm">
			<div class="modal-body">
				<div class="popup">
					<input class="big" type="hidden" id="batchIds">
					<div class="rowBox">
						<span class="w110">折 扣（%）：</span> <input class="big" type="text" id="batchDiscount">
					</div>
					<div class="rowBox">
						<span class="w110">本次发货数：</span> <input class="big" type="text" id="batchSendQty" maxlength="8">
					</div>
					<div class="rowBox">
						<span class="w110">其余满足情况：</span> <select class="big" id="batchAvailableReason">
							<option value=''></option>
            				<option value='0'>预计可发</option>
            				<option value='1'>无货待加印</option>
            				<option value='8'>新书待入库</option>
            				<option value='2'>已停产</option>
            				<option value='3'>改版</option>
            				<option value='4'>版权到期</option>
            				<option value='5'>商品无效</option>
            				<option value='6'>销售受限</option>
						</select>
					</div>
					<div class="rowBox">
						<span class="w110">其余预计发货日期：</span>
						<div class="time">
							<input class="date" type="text" id="start"> 
								<span class="u-icon u-icon-date"></span>
						</div>
						<!-- <span class="w14">至</span>
						<div class="time">
							<input class="date" type="text" id="end"> <span
								class="u-icon u-icon-date"></span>
						</div> -->
					</div>
					<div class="rowBox">
						<span class="w110">备 注：</span> <input class="big" type="text" id="batchRemark">
					</div>
					<div align="center" class="rowBox top10">
						<a href="javascript:;" class="btn btn-small btn-primary right-10" id="batchInputSave" >确 定</a> 
						<a href="javascript:;" class="btn btn-small " data-dismiss="modal"  id="batchCanle">取 消</a>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- Modal弹出结束 -->

	<!-- Modal1弹出开始 -->
	<div id="myModal1" class="modal-send hide fade modal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close closeSendGoods" aria-hidden="true">×</button>
			<h5>发货信息</h5>
		</div>
		<iframe id="sendGoodsViewIFrame" frameborder=0 width="100%"
			height="450" marginheight=0 marginwidth=0 scrolling=yes></iframe>
		<!-- <div align="center" class="bottom-10">
			<a href="javascript:;" id="saveSendGoods" class="btn btn-small btn-primary right-10">确 定</a> 
			<a href="javascript:;" class="btn btn-small closeSendGoods">取 消</a>
		</div> -->
	</div>
	<!-- Modal1弹出结束 -->

	<!-- Modal2弹出开始 -->
	<div id="myModal2" class="modal-send hide fade modal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h5>回告历史</h5>
		</div>
		<form id="responseHistoryForm">
			<input type="hidden" id="responseId">
			<div class="modal-body">
				<div class="popup">
					<div class="JGtab">
						<table id="table_b" class="table-block"></table>
						<div id="page_b"></div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- Modal2弹出结束 -->
	
	<!-- ModalReason弹出开始 -->
	<div id="myModalReason" class="modal-mini hide fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h5>关闭发货</h5>
		</div>
		<form id="notGoodsReasonForm">
			<input type="hidden" id="notGoodsReasonIds">
			<div class="modal-body">
				<div class="popup">
					<div class="rowBox">
						<span class="w110" style="float: none;">注意：关闭后，当前订单的品种不再发货！<br>* 请选择关闭原因：</span> 
					</div>
					<div class="rowBox">
						<select class="big" id="notGoodsReason">
            				<option value='2'>已停产</option>
            				<option value='3'>改版</option>
            				<option value='4'>版权到期</option>
            				<option value='5'>商品无效</option>
            				<option value='6'>销售受限</option>
            				<option value='7'>其他</option>
						</select>
					</div>
					<div align="center" class="rowBox top10">
						<a href="javascript:;" class="btn btn-small btn-primary right-10" id="notGoodsReasonSave" >确 定</a> 
						<a href="javascript:;" class="btn btn-small " data-dismiss="modal"  id="notGoodsReasonCanle">取 消</a>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- Modal2弹出结束 -->
	
	<!-- 解析完成modal  开始 -->
<div id="myModal5" class="modal-mini hide fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
<form id="sendOutForm" method="post">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4>解析成功</h4>
  </div>
  <div class="modal-body">
    <div>导入成功:
    	<span class="fontB" id="successData">
    	</span> 条 
    	<a  class="left40 line downLoadError">错误数据<span id="errorData"></span>条</a>
    	<div id="errorFlag" style="display: none;">
    		
     	</div>
    </div>
    <input type="hidden" id="totalSuccess" name="totalSuccess">
    <input type="hidden" id="errorFilePath">
    <input type="hidden" id="sendoutgoodscodes" name="codes">
    <input type="hidden" id="orderCode_ID" name="orderCode_ID">
    <input type="hidden" id="purId" name="purchaserId">
    <input type="hidden" id="execlurl">
    <input type="hidden" id="sendOutSummary" name="sendOutSummary">
    <input type="hidden" id="purchaserName_hiden" name="purchaserName_hiden">
    <input type="hidden" id="sapvendorID_hiden" name="sapvendorID_hiden">
    <input type="hidden" id="purchaserId_hiden" name="purchaserId_hiden">
    <input type="hidden" id="isblank" name="isblank">
    <input type="hidden" id="receiveWarehouse"  name="receiveWarehouse">
    <input type="hidden" id="receiveAddress"  name="receiveAddress">
    <input type="hidden"   name="source" value="detail">
    <div align="center"> 
    	<a  id="downLoadError" style="display: none;" class="btn downLoadError btn-small btn-primary" >下载</a>
    	<a id="toSendGoodsFromTemp" class="btn btn-small btn-primary" >确 定</a>
    </div>
    <br>
  </div>
</form>
</div>
<!-- 解析完成modal  结束-->
	<!-- 中间内容结束 -->

	<!-- 尾文件开始 -->
	<%@ include file="/common/footer.jsp"%>
	<!-- 尾文件结束 -->
  </body>
</html>
