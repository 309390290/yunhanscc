﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/common/publicCsJs.jsp"%>
	<script type="text/javascript" src="<c:url value='/js/backto/sendQuery/detail.js'/>"></script>
  </head>
  
  <body>
<!-- 头文件开始 --> 
<%@ include file="/common/menu.jsp" %>
<!-- 头文件结束 --> 

<!-- 中间内容结束开始 -->
<div class="dh_back"> 
		<div class="l_r_t_b20 font12">
			<img src="<c:url value='/img/icon_navigation.png'/>" width="16" height="16">&nbsp;&nbsp;当前位置：首 页&nbsp;&nbsp; 
			<img src="<c:url value='/img/arrow_red.gif'/>" width="7" height="5">&nbsp;&nbsp; 发货单维护 &nbsp;&nbsp; 
			<img src="<c:url value='/img/arrow_red.gif'/>" width="7" height="5">&nbsp;&nbsp; 发货单详情 
		</div>
	</div>
<form class="form-horizontal"  style="padding-bottom: 40px;">
  <div class="container_center"  style="margin-bottom:0">
    <div class="bs-docs-example">
      <div class="row-fluid">
        <input type="hidden" id="sgc" value='${sendSummary.sendoutGoodsCode}'>
        <input type="hidden" id="summaryId" name="id" value="${sendSummary.id}">
        <input type="hidden" id="sendoutStatus" value="${sendSummary.sendoutStatus}">
        <input type="hidden" id="purchaser_id" value="${sendSummary.purchaserId}">
        <div class="span3 fontB"> 发货单号：${sendSummary.sendoutGoodsCode} </div>
        <div class="span3 fontB"> 发货时间：<fmt:formatDate value="${sendSummary.sendoutDate}" type="date" dateStyle="long"/> </div>
        <div class="span3 fontB"> 发货单状态：${sendSummary.sendoutStatus==0?"未发货":
        sendSummary.sendoutStatus==5?"已发货（未收货）":sendSummary.sendoutStatus==10?"部分收货":"全部收货"} </div>
      </div>
      <div class="row-fluid fontblue fontB">收货信息>></div>
      <div class="row-fluid left20 top6">
        <div class="span3"  > 收货单位：<span id="receiveCompany">${sendSummary.receiveCompany}</span></div>
        <div class="span3"  > 收货人： <span id="receiveUserName">${sendSummary.receiveUserName}</span></div>
        <div class="span3"  > 收货联系方式：<span  id="receivePhoneno">${sendSummary.receivePhoneno}</span></div>
      </div>
      <div class="row-fluid fontblue fontB">发运信息>></div>
      <div class="row-fluid left20 top6">
        <div class="span3"  > 承运商： <span id="transportCompany">${sendSummary.transportCompany}</span></div>
        <div class="span3"  > 运单号： <span id="transportNo">${sendSummary.transportNo}</span></div>
        <div class="span3"  > 运输方式： <span id="transportMode">${sendSummary.transportMode == "1" ? "汽运":sendSummary.transportMode == "2"?"火车":sendSummary.transportMode}</span></div>
        <div class="span3"  > 包件数：<span id="pakagesQty">${sendSummary.pakagesQty} </span></div>
      </div>
      <div class="row-fluid left20 top6">
        <div class="span3" > 发到站： <span id="destination">${sendSummary.sendoutStation}</span></div>
        <div class="span9" > 发货单备注： <span id="sendoutRemark">${sendSummary.sendoutRemark}</span> </div>
      </div>
      <div class="row-fluid fontblue fontB">发货统计>></div>
      <div class="row-fluid left20 top6">
        <div class="span3"> 发货品种数：${sendSummary.totalVarietyQty} </div>
        <div class="span3"> 总册数：${sendSummary.totalBookQty} </div>
        <div class="span3"> 总码洋：${sendSummary.totalPrice} </div>
        <div class="span3"> 总实洋：${sendSummary.totalRealityPrice} </div>
      </div>
    </div>
    <div class="row-fluid">
      <div class="pull-right">
      		<c:if test="${sendSummary.sendoutStatus != 10 && sendSummary.sendoutStatus != 15}">
	      		<a href="javaScript:void();" class="btn btn-small modify-btn">修 改</a> 
      		</c:if>
	      <a href="javaScript:void();" class="btn btn-small left20 back-btn">返 回</a> 
      </div>
    </div>
    <div class="row-fluid top6"> 
      <table id = "table_list" class="table-block">
      </table>
      <div id="page"></div>
    </div>
  </div>
</form>

<!-- 中间内容结束 --> 

<!-- 尾文件开始 -->
<%@ include file="/common/footer.jsp" %>
<!-- 尾文件结束 -->  
</body>
</html>
