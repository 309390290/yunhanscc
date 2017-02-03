<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 
 	空白模板发货
  	add by luohoudong
 -->
<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/common/publicCsJsNew.jsp"%>
	<link href="<c:url value='/css/backto/sendGoodsFromBlankTempNew.css'/>" rel="stylesheet" >
	<script type="text/javascript" src="<c:url value='/js/backto/sendgoods/sendGoodsFromBlankTempNew.js'/>"></script>
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
      <li> <a href="JavaScript:;"> <span>空白模板</span> </a> </li>
    </ul>
  </div>
</div>
<!-- 面包导航结束 --> 

<!-- 中间内容结束开始 -->
<div class="bodyBox ">
	<div class="Tab" id="TabA">
		<div class="screenBox">
			<div class="screenTable">
				<div class="left">
					<ul>
						<li>
	                        <span class="name"><span class="fontred">*</span> 发货单号：</span>
	                        <div class="com">
	                            <input type="hidden" id="receiveAddress" value="${sendOutSummary.receiveAddress}">
	                        	<input type="hidden" id="receivePhoneno" value="${sendOutSummary.receivePhoneno}">
	                        	<input type="hidden" id="receiveUserName" value="${sendOutSummary.receiveUserName}">
	                        	<input type="hidden" id="sendReg" value="${sendReg}">
	                            <input type="text" id="sendoutGoodsCode"  value="${fn:replace(sendOutSummary.sendoutGoodsCode,'"','&quot;')}">
	                        </div>
                    	</li>
						<li> <span class="name"><span class="fontred">*</span> 发货日期：</span>
	                        <div class="com fhTime">
	                            <input type="text" id="sendoutDate" value="${sendOutSummary.sendoutDateStr}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
	                            <span class="u-icon-date"></span>
	                        </div>
	                    </li>
						<li>
	                        <span class="name"><span class="fontred">*</span> 发到站</span>
	                        <div class="com">
	                            <input type="text"  id="sendoutStation"  value="${sendOutSummary.sendoutStation}">
	                        </div>
	                    </li>
						<li> <span class="name">运输方式：</span>
	                        <div class="com">
	                            <select id="transportMode">
	                                <option value="1" <c:if test="${sendOutSummary.transportMode == '1' }"> selected="selected"</c:if>  >汽车</option>
	              					<option value="2" <c:if test="${sendOutSummary.transportMode == '2' }"> selected="selected"</c:if>>火车</option>
	                            </select>
	                        </div>
	                    </li>
	                    <li>
	                        <span class="name">运单号：</span>
	                        <div class="com">
	                            <input type="text" id="transportNo"  value="${sendOutSummary.transportNo}">
	                        </div>
	                    </li>
                    	<li>
	                        <span class="name">承运商：</span>
	                        <div class="com">
	                            <input type="text" id="transportCompany"  value="${sendOutSummary.transportCompany}" >
	                        </div>
	                    </li>
	                    <li>
	                        <span class="name">包件数：</span>
	                        <div class="com">
	                            <input type="text" id="pakagesQty"  value="${sendOutSummary.pakagesQty}" >
	                        </div>
	                    </li>
	                    
	                    <li>
	                        <span class="name">仓 位：</span>
	                        <div class="com">
	                            <input type="text" id="receiveWarehouse"  readonly="readonly" value="${sendOutSummary.receiveWarehouse}" >
	                        </div>
	                    </li>
	                    <li>
	                        <span class="name">采购商：</span>
	                        <div class="com">
	                            <input type="hidden" id="purchaserId" value="${sendOutSummary.purchaserId}">
                          		<input type="text" id="purchaserName" readonly="readonly"  value="${sendOutSummary.purchaserName}">
	                        </div>
	                    </li>
	                    
	                    <li>
	                        <span class="name">发货单备注：</span>
	                        <div class="com">
	                            <input type="text" id="sendoutRemark"  value="${sendOutSummary.sendoutRemark}" style="width: 968px;">
	                        </div>
	                    </li>
	                </ul>
				</div>
				<div class="right">
                    <div>
                        <span class="btn_background sendOutBtn" id="clickBtn"><i></i>发货</span>
                    </div>
                </div>
			</div>
		</div>
		<div class="sortBox">
            <div class="left"></div>
            <div class="right">
                 <span>品种数：<i class="fontred" id="totalVarietyQty"></i></span>
                 <span>总册数：<i class="fontred" id="totalBookQty"></i></span>
                 <span>本次发货码洋：<i class="fontred" id="totalPrice"></i></span>
                 <span>本次发货实洋：<i class="fontred"	id="totalRealityPrice"></i></span>
            </div>
        </div>
        <div class="jqGrid">
            <table id = "blankTemp_list"></table>
            <div id="blankTemp_page"></div>
        </div>
	</div>
</div>
<!-- 中间内容结束 --> 
   
<!-- 尾文件开始 -->

<%@ include file="/common/footer.jsp" %>
<!-- 尾文件结束 --> 
   
  </body>
</html>
