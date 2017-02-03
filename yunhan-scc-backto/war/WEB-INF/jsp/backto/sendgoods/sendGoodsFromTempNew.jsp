<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 
 	有订单模板发货
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
	<link href="<c:url value='/css/backto/sendGoodsFromTempNew.css'/>" rel="stylesheet" >
	<script type="text/javascript" src="<c:url value='/js/backto/sendgoods/sendGoodsFromTempNew.js'/>"></script>
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
      <li> <a href="JavaScript:;"> <span>品种处理</span> </a> <b> > </b> </li>
      <li> <a href="JavaScript:;"> <span>发货单</span> </a> </li>
    </ul>
  </div>
</div>
<!-- 面包导航结束 --> 

<!-- 中间内容结束开始 -->
<div class="bodyBox">
  <div class="contentBox">
    <div class="left12" id="left">
    	<input type="hidden" id="source" value="${source}">
    	<input type="hidden" id="today" value="${today}">
        <ul id="show_list">
            <c:forEach items="${sendOutCodes }" var="sendCode"  varStatus="statu">
	        	<c:choose>
	        		<c:when test="${statu.first }">
	        			<li class="active"><a title="${sendCode}" href="javascript:;"  name="${sendCode}">${fn:replace(sendCode,'<','&lt;')}</a></li>
	        			
	        		</c:when>
	        		<c:otherwise>
	        				<li id="left_${statu.index}"><a title="${sendCode}" href="javascript:;"  name="${sendCode}">${fn:replace(sendCode,'<','&lt;')}</a></li>
	        		</c:otherwise>
	        	</c:choose>
			</c:forEach>
			<c:if test="${reportShow==true}">
				<li  class="<c:if test="${sendOutCodes==null or sendOutCodes.size()==0}">active</c:if>" ><a title="只回告"  data-toggle="tab" name="report"  flag="report">只回告</a></li>
			</c:if>
        </ul>
    </div>
    <div class="right88" id="myTabContent" >
    <!--  加载每个发货单右边部分  开始       -->
    	<c:forEach items="${sendOutCodes}" var="sendCode" varStatus="statu">
	    	<c:choose>
	        		<c:when test="${statu.first }">
	      					<div class="list_box active" >
	        		</c:when>
	        		<c:otherwise>
	        				<div class="list_box"  >
	        		</c:otherwise>
	        </c:choose>
		        <div class="searchBox" id="right_${statu.index}">
		            <div class="left">
		                <ul>
		                    <li>
		                        <span class="name"><span class="fontred">*</span> 发货单号：</span>
		                        <div class="com">
		                        	<input type="hidden"  value="${sendCode}" id="historySendoutCode_${statu.index}">
		                            <input type="text"  value="${sendCode}" id="sendoutGoodsCode_${statu.index}" class="sendoutGoodsCode">
		                        </div>
		                    </li>
		                    <li> <span class="name"><span class="fontred">*</span> 发货日期：</span>
		                        <div class="com time">
		                           <input type="text" class="startTime" value="${today}" id="sendoutDate_${statu.index}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"  ><span class="u-icon u-icon-date"></span>
		                        </div>
		                    </li>
		                    <li>
		                        <span class="name"><span class="fontred">*</span> 发到站：</span>
		                        <div class="com">
		                            <input type="text"  id="sendoutStation_${statu.index}">
		                        </div>
		                    </li>
		                    <li>
		                        <span class="name">运输方式：</span>
		                        <div class="com">
		                            <select id="transportMode_${statu.index}">
		                                <option value="1">汽车</option>
		              					<option value="2">火车</option>
		                            </select>
		                        </div>
		                    </li>
		                    <li>
		                        <span class="name">运单号：</span>
		                        <div class="com">
		                            <input type="text" id="transportNo_${statu.index}">
		                        </div>
		                    </li>
		                    <li>
		                        <span class="name">承运商：</span>
		                        <div class="com">
		                            <input type="text" id="transportCompany_${statu.index}" >
		                        </div>
		                    </li>
		                    <li>
		                        <span class="name">仓 位：</span>
		                        <div class="com">
		                            <input type="text" id="receiveWarehouse_${statu.index}">
		                        </div>
		                    </li>
		                    <li>
		                        <span class="name">包件数：</span>
		                        <div class="com">
		                            <input type="text"  id="pakagesQty_${statu.index}">
		                        </div>
		                    </li>
		                    <li></li>
		                    <li>
		                        <span class="name">发货单备注：</span>
		                        <div class="com">
		                            <input type="text" id="sendoutRemark_${statu.index}" style="width: 870px;">
		                        </div>
		                    </li>
		                </ul>
		                <div>
		                    <!--全选插件-->
		                </div>
		            </div>
		            <div class="right">
							<div class="send">
			                    <a href="javascript:;" class="btn_background sendBtn">发 货</a>
							</div>
		                    <a href="javascript:;" class="cancelBtn">取 消</a>
		
		            </div>
		        </div>
		        <div class="sortBox">
		            <div class="left"></div>
		            <div class="right">
		            		<input type="hidden" id="purchaserId_${statu.index}">
		            		<input type="hidden" id="supplierId_${statu.index}">
		            		<input type="hidden" id="sendReg_${statu.index}">
		            		<input type="hidden" id="receiveAddress_${statu.index}">
		            		<input type="hidden" id="receivePhoneno_${statu.index}">
		            		<input type="hidden" id="receiveUserName_${statu.index}">
		                    <span>采购商：<b class="fontred" id="purchaserName_${statu.index}"> </b> </span>
		                    <span>品种数：<b class="fontred" id="totalVarietyQty_${statu.index}"> </b> </span>
		                    <span>总册数：<b class="fontred" id="totalBookQty_${statu.index}"> </b></span>
		                    <span>本次发货码洋：<b class="fontred" id="totalPrice_${statu.index}"> </b></span>
		                    <span>本次发货实洋：<b class="fontred" id="totalRealityPrice_${statu.index}" > </b></span>
		            </div>
		        </div>
		        <div class="GdBox">
		            <table id = "table_${statu.index}" class="table-block">
		            </table>
		            <div id="page_${statu.index}"></div>
		        </div>
        	</div>
        </c:forEach>
        <!--  加载每个发货单右边部分  结束       -->
        <c:if test="${reportShow==true}">
	    	<div class="list_box <c:if test="${sendOutCodes==null or sendOutCodes.size()==0}">active</c:if>" >
		    		<div class="infoBox">
			            
			            <div>
		                    <a href="javascript:;" class="reportBtn btn_background">回 告</a>
		                    <a href="javascript:;" class="cancelBtn btn_white">取 消</a>
			            </div>
			            <span>品种数：<i id="report_pzs"></i> </span>
		        	</div>
		        <div class="GdBox">
		            <table id = "table_report" class="table-block">
		            </table>
		            <div id="page_report"></div>
		        </div>
	    	</div>
    	</c:if>
        
    </div>
  </div>
</div>


<!-- 中间内容结束 --> 
   
<!-- 尾文件开始 -->

<%@ include file="/common/footer.jsp" %>
<!-- 尾文件结束 --> 
   
  </body>
</html>
