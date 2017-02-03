<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 
 	订单处理>>>订单方式
  	add by wangtao
  	2016年7月7日13:33:32
 -->
<!DOCTYPE HTML>
<html>
  <head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<%@ include file="/common/publicCsJsNew.jsp"%>
	<link href="<c:url value='/css/backto/orderProcessingNew.css'/>" rel="stylesheet" >
	<script type="text/javascript" src="<c:url value='/js/backto/order/orderProcessingNew.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/backto/order/orderImportNew.js'/>"></script>
  </head>
  
  <body>
   <!-- 头文件开始 --> 
<%@ include file="/common/menu.jsp" %>
<!-- 头文件结束 --> 
<!-- 面包导航开始 -->
<div class="headerBox">
  <div class="active_com"> <span class="position"><b class="map-marker"></b>当前位置：</span>
    <ul class="ul">
      <li> <a href="JavaScript:;"> <span>首 页</span> </a> <b> > </b> </li>
      <li> <a href="JavaScript:;"> <span>订单处理</span> </a> <b> > </b> </li>
      <li> <a href="JavaScript:;"> <span>订单方式</span> </a> </li>
    </ul>
  </div>
</div>
<!-- 面包导航结束 --> 

<!-- 中间内容结束开始 -->
<div class="bodyBox">
			<input id="purchaserId" type="hidden" value="${sapvendorId }" />
			<!-- 待发送开始 -->
			<div class="Tab" id="TabA">
				<div class="screenBox">
					<div class="screenTable">
						<div class="left">
							<input type="hidden" id="sendGoodsType" value="${sendGoodsType }">
        					<input type="hidden" id="wareHouse" value="${wareHouse}">	
							<ul>
								<li> 
									<span class="name"><span class="fontred">*</span> 采购商</span>
					                <div class="com">
						              	<select id="pur_all" name="pur_all">
												<c:forEach items="${department }" var="list">
													<option value = "${list.sapvendorId }"  <c:if test="${list.sapvendorId == purchaserId }">selected="selected"</c:if>>${list.name }</option>
												</c:forEach>
										</select>
					                </div>
					            </li>
					            <li> 
					            	<span class="name"><span class="fontred">*</span> 仓 位</span>
					                <div class="com">
					              		<select id="dc_all" name="dc">
					                	</select>
					                </div>
					            </li>
					            <li> 
					            	<span class="name">新品订单</span>
					            		<div class="com">
							              	<div class="isNew" id="isNew"></div>
						                </div>
					            </li>
					            <li> <span class="name">订单号码</span>
					              <div class="com gys">
					                <input type="text" id="orderCode">
					                <span class="add orderCode-btn"></span>
					                <span class="del"></span>
					              </div>
					            </li>
					            <li> <span class="name">订单种类</span>
					              <div class="com">
					              	<div class="AllSelect">
					              	</div>
					              </div>
					            </li>
					            <li> <span class="name">紧急程度</span>
					            	<div class="com">
						              	<div class="urgentFlag" id="urgentFlag"></div>
					                </div>
					            </li>
					            <li class="show"> <span class="name">发送日期</span>
					              <div class="com time">
					                <input type="text" onClick="WdatePicker();" id="sendDateStart">
					                <b></b>
					                <span></span>
					                <input type="text" onClick="WdatePicker();" id="sendDateEnd">
					                <b></b>
						                <div class="time_box">
						                	<div class='mes' style="height: 18px;"><i class="badge" id="month1ago"></i></div>
						                    <a name="send_Date" id="month_1" >1个月内</a>
						                    <a name="send_Date" id="month_1_ago" >1个月前</a>
						                </div>
				                	</div>
					            </li>
					            <li class="show"> <span class="name">是否下载</span>
					              <div class="com">
					              	<select class="query_data_all" id="is_export" name="is_export">
					                    <option value="">全部</option>
					                    <option value="Y">已下载</option>
					                    <option value="N">未下载</option>
					                </select>
				                  </div>
					            </li>
					            <li class="show"> <span class="name">订单处理情况</span>
					              <div class="com">
					              	<div class="AllSelectDDCL">
					              	</div>
					              </div>
					            </li>
							</ul>
						</div>
						<div class="right">
		                    <div class="btn">
		                        <span class="icon-search" id="clickBtn"><i></i>搜 索</span>
		                        <span id="open" class="span2"><i class="close"></i><b>收 起</b></span>
		                    </div>
		                </div>
					</div>
				</div>
				<div class="infoBox screenBox">
			      	<span>收货地址：<b id="paddr"></b></span>
			       	<span>收货人：<b id="contact"></b></span> 
			       	<span>联系电话：<b id="contactNumber"></b></span>
				</div>
				<div class="gridBox">
	                <div class="sortBox">
	                	<ul id="orderByBox">
						  <li class="active" data-value="sendDate" name="click">发送日期↓</li>
		                  <li data-value="urgentFlag">紧急程度↓</li>
		                  <li data-value="orderType">订单种类↓</li>
		                  <li data-value="purchaseOrderCode">订单号↓</li>
	                    </ul>
	                    <div class="orderBox">
	                        <div class="com del">
	                        	<span class="span"  id="importSendBtn">导入</span>
								<select id="downBtn" class="dc_select">
										<option selected="" value="">导出</option>
										<option value="exportOrderSingle" id="exportOrderSingle">逐单导出</option>
										<option value="exportOrderMultiple" id="exportOrderMultiple">合单导出</option>
									</select>
				                <span class="span spanBack"  id="orderItems">多订单处理</span>
          						<div id="odiv"></div>
                        	</div>
	                    </div>
	                </div>
	              
                <div class="jqGrid">
                	<table id="table"></table>
					<div id="page"></div>
                </div>
           	</div>
		</div>
</div>
<!-- 中间内容结束 --> 
   
   
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
    <input type="hidden"   name="source" value="order">
    <div class="actionType"> 
    <a id="downLoadError" style="display: none;" class="downLoadError likeStyle">下载</a>
    <a id="toSendGoodsFromTemp" class="likeStyle">确 定</a>
    </div>
  </div>
</form>
</div>
<!-- 解析完成modal  结束-->

 <input type="hidden" id="orderTypeRe" name="orderTypeRe" value="${orderType}">
 <input type="hidden" id="isDealRe" name="isDealRe" value="${isDeal}">
<!-- 尾文件开始 -->

<%@ include file="/common/footer.jsp" %>
<!-- 尾文件结束 --> 
</body>
</html>
