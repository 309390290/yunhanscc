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
	<%@ include file="/common/publicCsJsNew.jsp"%>
	<link href="<c:url value='/css/backto/orderDetailsProcessingNew.css'/>" rel="stylesheet" >
	<script type="text/javascript" src="<c:url value='/js/backto/order/orderDetailsProcessingNew.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/backto/sendgoods/sendGoodsAndSendNew.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/backto/order/orderImportNew.js'/>"></script>
</head>
<!-- 用于声明dialog变量，以便关闭子页面IFrame的外层dialog -->
<script>
var diabox=null;
</script>
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
	<div class='contBox'>
			<div class='listBox listBoxbottom'>
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
				<h5>订单信息</h5>
				<ul>
					<li>
						<span class='name'>订单号码：</span>
						<span class='cont'>${summary.purchaseOrderCode}</span>
					</li>
					<li>
						<span class='name'>采购商：</span>
						<span class='cont'>${summary.purchaserName}</span>
					</li>
					<li>
						<span class='name'>发送日期：</span>
						<span class='cont'>
							<c:if test="${summary.sendDate!=null}">
								<fmt:formatDate value="${summary.sendDate}" pattern="yyyy-MM-dd" />
							</c:if>
						</span>
					</li>
					<li>
						<span class='name'>订单种类：</span>
						<span class='cont'>${summary.orderType ==0 ? "零售订单":
							summary.orderType==5?"文教订单":summary.orderType==10?"电商订单":summary.orderType==15?"大中专订单":
							summary.orderType==20?"团购订单":summary.orderType==25?"馆配订单":"活动订书" }
						</span>
					</li>
					<li>
						<span class='name'>紧急程度：</span>
						<span class='cont'>${summary.urgentFlag ==1 ? "急单":"普通单" }
						</span>
					</li>
					<li>
						<span class='name'>制单人：</span>
						<span class='cont'>${summary.addUser }</span>
					</li>
					<li>
						<span class='name'>制单日期：</span>
						<span class='cont'><c:if test="${summary.addDate!=null}">
										<fmt:formatDate value="${summary.addDate}" pattern="yyyy-MM-dd" /> 
										</c:if>  
						</span>
					</li>
					<li>
						<span class='name'>制单人电话：</span>
						<span class='cont'>${summary.addUserPhoneno }</span>
					</li>
				</ul>
			</div>
		<div class='listBoxOC'>
			<span class="more-btn" name="open">展开▼</span>
		</div>
		<div class='listBox listBoxbottom listHidden listLi'>
				<h5>收货信息</h5>
				<ul>
					<li>
						<span class='name'>收货地址：</span>
						<span class='cont address'>${summary.receiveAddress }</span>
					</li>
					<li>
						<span class='name'>收货联系人：</span>
						<span class='cont'>${summary.receiveContactUser }</span>
					</li>
					<li>
						<span class='name'>收货联系电话：</span>
						<span class='cont'>${summary.receivePhoneno }</span>
					</li>
				</ul>
			</div>
			
			<div class='listBox listHidden'>
				<h5>特殊要求说明</h5>
				<ul>
					<li>
						<span class='name'>要求交货日期：</span>
						<span class='cont'>
							<c:if test="${summary.reqArriveDate!=null}">
								<fmt:formatDate value="${summary.reqArriveDate}" pattern="yyyy-MM-dd" />
							</c:if>
						</span>
					</li>
					<li class='specialLi'>
						<span class='name'>订单备注：</span>
						<span class='cont'>${summary.orderRemark }</span>
					</li>
				</ul>
			</div>
	</div>
	<div class="gridBox">
	           <div class="sortBox">
	           		<div class="left">
							<div class="selectMore">
								<div class="choice">
									<span class="title">品种处理情况：</span> 
									<input type="checkbox" value="" id="all_1" class="all-check" name="isDeal"> 
										<label for="all_1">全部</label> 
									<input type="checkbox" value="Y" id="yc_1" class="single-check" name="isDeal"> 
										<label for="yc_1">已处理</label>
									<input type="checkbox" value="N" id="yc_2" class="single-check" name="isDeal" checked="checked">
										<label for="yc_2">待处理</label>
									<input type="checkbox" value="A" id="yc_3" class="single-check" name="isDeal" checked="checked">
										<label for="yc_3">部分处理</label>
								</div>
							</div>
							<div class="selectMore">
								<div class="choice xzMark">
									<span class="title">下载标识：</span> 
									<input type="checkbox" value="" class="all-check" id="all_2" checked="checked" name="isExport">
										<label for="all_2">全部</label> 
									<input type="checkbox" value="1" class="single-check" id="yx_1" checked="checked" name="isExport"> 
										<label for="yx_1">已下载</label>
									<input type="checkbox" value="0" class="single-check" id="yx_2" checked="checked" name="isExport">
										<label for="yx_2">未下载</label>
								</div>
							</div>
					</div>
	               <div class="orderBox">
	                   <div class="com del">
	                     <span class='span'  id="OrderExport">导 出</span>
	                     <span class='span'  id="importSendBtn">导 入</span>
	                     <span class='span'  id="batchInput">批量录入</span>
	                     <span class='span'  id="save">保 存</span>
	                     <span class='span'  id="sendGoods">确 认</span>
	                     <span class='span'  id="goBack">返 回</span>
	                     <span class='span spanBack'  id="closeSend">关闭发货</span>
	                   </div>
	               </div>
	           </div>
	           <div class="infoBox">
	           		
		           		<ul>
							<li>已筛选品种：<span id="totalVariety">0</span>个</li>
							<li>本次发货数：<span id="totalSendQty">0</span></li>
							<li>本次发货码洋：<span id="totalSendPrice">0.00</span></li>
							<li>本次发货实洋：<span id="totalSendRealPrc">0.00</span></li>
						</ul>
					
	           </div>
               <div class="jqGrid">
	               	<table id="table"></table>
					<div id="page"></div>
               </div>
      	</div>
	<!-- Modal弹出开始 -->
	<div id="myModal" style="display: none;">
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
            				<option value='9'>无货不发</option>
						</select>
					</div>
					<div class="rowBox">
						<span class="w110">其余预计发货日期：</span>
						<div class="time">
							<input class="Wdate" type="text" id="start"> 
								<span class="u-icon u-icon-date"></span>
						</div>
					</div>
					<div class="rowBox">
						<span class="w110">备 注：</span> <input class="big" type="text" id="batchRemark">
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- Modal弹出结束 -->

	<!-- Modal1弹出开始 -->
	<div id="myModal1" style="display: none;">
		<iframe id="sendGoodsViewIFrame" name="sendGoodsViewIFrame" frameborder=0 width="900" height="450" marginheight=0 marginwidth=0 scrolling=yes ></iframe>
	</div>
	<!-- Modal1弹出结束 -->

	<!-- Modal2弹出开始 -->
	<div id="myModal2" style="width:820px; display: none;padding-bottom:20px;max-height:450px">
		<form id="responseHistoryForm">
			<input type="hidden" id="responseId">
			<div class="JGtab">
				<table id="table_b"></table>
				<div id="page_b"></div>
			</div>
		</form>
	</div>
	<!-- Modal2弹出结束 -->
	
	<!-- ModalReason弹出开始 -->
	<div id="myModalReason" style="display:none">
		<form id="notGoodsReasonForm" method="post">
			<input class="big" type="hidden" id="notGoodsReasonIds">
				<div class="rowBox">
					<span class="w120">注意：关闭后，当前订单的品种不再发货！<br>* 请选择关闭原因：</span> 
				</div>
				<div class="rowBox">
					<select class="big" id="notGoodsReason">
           				<option value='2'>已停产</option>
           				<option value='3'>改版</option>
           				<option value='4'>版权到期</option>
           				<option value='5'>商品无效</option>
           				<option value='6'>销售受限</option>
           				<option value='9'>无货不发</option>
					</select>
				</div>
		</form>
	</div>
	<!-- Modal2弹出结束 -->
	
	<!-- 解析完成modal  开始 -->
<div id="myModal5" style="display:none">
<form id="sendOutForm" method="post">
  <div>
    	<p>导入成功:<span id="successData"></span>条</p>
    	<a href="javascript:;" class="line downLoadError">错误数据:<span id="errorData"></span>条</a>
    	<div id="errorFlag" style="display: none;"></div>
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
    <div class="actionType"> 
	    <a id="downLoadError" style="display: none;" class="downLoadError likeStyle">下载</a>
	    <a id="toSendGoodsFromTemp" class="likeStyle">确 定</a>
    </div>
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
