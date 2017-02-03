﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/common/publicCsJsNew.jsp"%>
	<link href="<c:url value='/css/backto/detailNew.css'/>" rel="stylesheet" >
	<script type="text/javascript" src="<c:url value='/js/backto/sendQuery/detailNew.js'/>"></script>
  </head>
  
  <body>
<!-- 头文件开始 --> 
<%@ include file="/common/menu.jsp" %>
<!-- 头文件结束 --> 

<!-- 中间内容结束开始 -->
<div class="headerBox">
  <div class="active_com"> <span class="position"><b class="map-marker"></b>当前位置：</span>
    <ul class="ul">
      <li> <a href="JavaScript:;"> <span>首 页</span> </a> <b> > </b> </li>
      <li> <a href="JavaScript:;"> <span>发货单维护</span> </a> <b> > </b> </li>
      <li> <a href="JavaScript:;"> <span>发货单详情</span> </a> </li>
    </ul>
  </div>
</div>
<form class="form-horizontal">	
    <input type="hidden" id="sgc" value='${sendSummary.sendoutGoodsCode}'>
    <input type="hidden" id="summaryId" name="id" value="${sendSummary.id}">
    <input type="hidden" id="sendoutStatus" value="${sendSummary.sendoutStatus}">
    <input type="hidden" id="purchaser_id" value="${sendSummary.purchaserId}">
	<div class='contBox '>
			<div class='listBox '>
				<h5>发运信息</h5>
				<ul>
					<li>
						<span class='name'>发货单号：</span>
						<span class='cont'>${sendSummary.sendoutGoodsCode}</span>
					</li>
					<li>
						<span class='name'>发货时间：</span>
						<span class='cont'> <fmt:formatDate value="${sendSummary.sendoutDate}" type="date" dateStyle="long"/> </span>
					</li>
					<li>
						<span class='name'>包件数：</span>
						<span class='cont' id="pakagesQty">${sendSummary.pakagesQty}</span>
					</li>
					<li>
						<span class='name'>发货单状态：</span>
						<span class='cont'>${sendSummary.sendoutStatus==0?"未发货":sendSummary.sendoutStatus==5?"已发货（未收货）":sendSummary.sendoutStatus==10?"部分收货":"全部收货"}</span>
					</li>
					<li>
						<span class='name'>运单号：</span>
						<span class='cont' id="transportNo">${sendSummary.transportNo}</span>
					</li>
					<li>
						<span class='name'>运输方式：</span>
						<span class='cont' id="transportMode">${sendSummary.transportMode == "1" ? "汽运":sendSummary.transportMode == "2"?"火车":sendSummary.transportMode}</span>
					</li>
					<li>
						<span class='name'>承运商：</span>
						<span class='cont' id="transportCompany">${sendSummary.transportCompany}</span>
					</li>
					<li>
						<span class='name'>发到站：</span>
						<span class='cont' id="destination">${sendSummary.sendoutStation}</span>
					</li>
					<li>
						<span class='name'>品种数：</span>
						<span class='cont'>${sendSummary.totalVarietyQty}</span>
					</li>
					<li>
						<span class='name'>总册数：</span>
						<span class='cont'>${sendSummary.totalBookQty}</span>
					</li>
					<li>
						<span class='name'>总码洋：</span>
						<span class='cont' >${sendSummary.totalPrice}</span>
					</li>
					<li>
						<span class='name'>总实洋：</span>
						<span class='cont' >${sendSummary.totalRealityPrice}</span>
					</li>
				</ul>
			</div>
		<div class='listBoxOC'>
			<span class="more-btn" name="open">展开▼</span>
		</div>
		<div class='listBox listBoxbottom listHidden'>
				<h5>收货信息</h5>
				<ul>
					<li>
						<span class='name'>收货单位：</span>
						<span class='cont' id="receiveCompany">${sendSummary.receiveCompany}</span>
					</li>
					<li>
						<span class='name'>收货人：</span>
						<span class='cont' id="receiveUserName">${sendSummary.receiveUserName}</span>
					</li>
					<li>
						<span class='name'>联系电话：</span>
						<span class='cont' id="receivePhoneno">${sendSummary.receivePhoneno}</span>
					</li>
				</ul>
			</div>
			
			<div class='listBox listHidden'>
				<h5>特殊说明</h5>
				<ul>
					<li class='specialLi'>
						<span class='name'>发货备注：</span>
						<span class='cont specialSpan' id="sendoutRemark">${sendSummary.sendoutRemark}</span>
					</li>
				</ul>
			</div>
	
	</div>
	<div class="bodyBox">
		<div class="bodyContentBox">
			<div class="Tab tabOne active">
				<div class="gridBox">
	                <div class="sortBox">
	                    <div class="orderBox">
	                        <div class="com del">
		                        <c:if test="${sendSummary.sendoutStatus != 10 && sendSummary.sendoutStatus != 15}">
					      			<span class='span modify-btn'>修 改</span>
					      		</c:if>
	                            	<span class='span back-btn'>返  回</span>
	                        </div>
	                    </div>
	                </div>
	                <div class="jqGrid">
	                	<table id="table_list"></table>
						<div id="page"></div>
	                </div>
	            	</div>	
			</div>
		</div>
	</div>	
</form>
<!-- 中间内容结束 --> 

<!-- 尾文件开始 -->
<%@ include file="/common/footer.jsp" %>
<!-- 尾文件结束 -->  
</body>
</html>
