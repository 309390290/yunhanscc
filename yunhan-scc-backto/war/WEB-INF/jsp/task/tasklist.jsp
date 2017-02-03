<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<%@ include file="/common/publicCsJs.jsp"%>
	<script type="text/javascript" src="<c:url value='/js/backto/task/tasklist.js'/>"></script>
	<style>
		.taskListHead{
	 	    margin: 20px;
	 	  
	 }
.taskListHead:after {
    content: "020";
    display: block;
    height: 0;
    clear: both;
    visibility: hidden;
}
.taskListHead .oDiv{
	float: left;
    line-height: 100%;
      border-left: 4px solid #0b56bb;
	padding-left: 4px;
	font-size: 14px;
}
.taskListHead .oDiv span{
	color:red;
}
.taskListHead .oSelect{
	float: left;
	height: 24px;
    line-height: 24px;
    margin-left: 10px;
    margin-top: -5px;
    width:130px;
}
.taskListJqGrid{
    padding: 20px;
}
.taskListJqGrid div{
    font-size:12px;
}
.taskListJqGrid td{
    font-size:12px;
}

.taskListTab{
   display: none;
}
.taskListTab.active{
   display: block;
}
	</style>
  </head>
<body>
<!-- 头文件开始 --> 
<%@ include file="/common/menu.jsp" %>
<!-- 头文件结束 --> 

<!-- 面版导航开始 -->
<div class="headerBox">
  <div class="active_com"> <span class="position"><b class="map-marker"></b>当前位置：</span>
    <ul class="ul">
      <li> <a href="JavaScript:;"> <span>首 页</span> </a> <b> > </b> </li>
      <li> <a href="JavaScript:;"> <span>个人管理</span> </a> <b> > </b> </li>
      <li> <a href="JavaScript:;"> <span>待办工作</span> </a> </li>
    </ul>
  </div>
</div>
<!-- 面版导航结束 --> 

<!-- 中间内容结束开始 -->
<div class="bodyBox">
	<div class='taskListHead'>
		<div class='oDiv' id="goOrder" style="cursor:hand">${department.name }待处理<span id="totalCount"><c:if test="${statisticalDimension=='A' }">${total}</c:if><c:if test="${statisticalDimension=='B' }">${orderTotal}</c:if></span>条</div>
		
		<select class='oSelect'  id="statisticalDimension" onchange="dimensionSelect()" >
			<option value="A"  <c:if test="${statisticalDimension=='A' }">selected="selected"</c:if> valueCount="${total}">按品种方式统计</option>
			<option value="B"  <c:if test="${statisticalDimension=='B' }">selected="selected"</c:if> valueCount="${orderTotal}">按订单方式统计</option>
		</select>
	</div>
	<input type="hidden" id="purchaserId" value="${department.sapvendorId }">
    <input type="hidden" id="userCode" value="${userCode }"> 	
	<div class='taskListTab <c:if test="${statisticalDimension=='A' }">active</c:if>'  id="bodyTabA">
		<div class="bodyTab">
	        <ul class="headActive" id="headActive">
	        	<c:forEach items="${tasks }" var="list" varStatus="status">
	           		<c:if test="${list.unDealCount>0}">
	           			<li <c:if test="${status.index==0 }">class="active"</c:if> value="${status.index}">${list.wareHouseStr }<font color="red">(${list.unDealCount})</font>个</li>
	           			<input type="hidden" id="wareHouse_${status.index }" value="${list.wareHouse }"> 	
	           		</c:if>
	           	</c:forEach>
	            <!-- <li class="active">新品报订</li>
	            <li>商品查询及报订</li>
	            <li>报订暂存</li> -->
	        </ul>
	        <span class="activeNow" id="activeNow"></span>
	    </div>
	   	<div class="taskListJqGrid">
	        <table id = "table"></table>
	        <div id="table_page"></div>
	    </div>
	</div>
    <div class='taskListTab <c:if test="${statisticalDimension=='B' }">active</c:if>'  id="bodyTabB">
	    <div class="bodyTab">
	        <ul class="headActive" id="headActiveb">
	        	<c:forEach items="${orderTasks }" var="list" varStatus="status">
	           		<c:if test="${list.unDealCount>0}">
	           			<li <c:if test="${status.index==0 }">class="active"</c:if> value="${status.index}">${list.wareHouseStr }<font color="red">(${list.unDealCount})</font>个</li>
	           			<input type="hidden" id="wareHouseOrder_${status.index }" value="${list.wareHouse }"> 	
	           		</c:if>
	           	</c:forEach>
	            <!-- <li class="active">新品报订</li>
	            <li>商品查询及报订</li>
	            <li>报订暂存</li> -->
	        </ul>
	    </div>
	  	<div class="taskListJqGrid">
		      <table id = "tableb"></table>
		      <div id="tableb_page"></div>
		 </div>
    </div>
</div>
<c:if test="${closeCount>0 }">
			<div class='taskListHead'>
				<div class='oDiv' id="goOrderItems" style="cursor:hand">采购商已关闭的订单商品<span>${closeCount }</span>个</div>
				<input type="hidden" id="closeOrderCodes" value="${closeOrderCodes }"/>
			</div>
          	 <%-- <div class="heigh30">
              <div class="left">
                  <span class="fontB">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
              </div>
              <div class="right">
                  <a class="blue" href="javascript:;" id="goOrderItems"> 采购商已关闭的订单商品（<span class="red">${closeCount }</span>）个！</a>
              </div>
          	</div> --%>
          </c:if>
<!-- 中间内容结束 --> 

<!-- 尾文件开始 -->
<%@ include file="/common/footer.jsp" %>
<!-- 尾文件结束 --> 

</body>
</html>
