<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/common/publicCsJsNew.jsp"%>
	<link href="<c:url value='/css/backto/modifyNew.css'/>" rel="stylesheet" >
	<script type="text/javascript" src="<c:url value='/js/backto/sendQuery/modifyNew.js'/>"></script>
  </head>
<body>
<!-- 头文件开始 --> 
<%@ include file="/common/menu.jsp" %>
<!-- 头文件结束 --> 

<!-- 中间内容结束开始 -->
<div class="headerBox">
	<div class="active_com">
		<span class="position"><b class="map-marker"></b>当前位置：</span>
		<ul class="ul">
			<li><a href="JavaScript:;"> <span>首 页</span>
			</a> <b> > </b></li>
			<li><a href="JavaScript:;"> <span>发货单维护</span>
			</a> <b> > </b></li>
			<li><a href="JavaScript:;"> <span>发货单修改</span>
			</li>
		</ul>
	</div>
</div>
<form class="form-horizontal"  id="summary" method="post">
	<div class='contBox '>
		<input type="hidden" id="sgc" value="${sendSummary.sendoutGoodsCode}">
        <input type="hidden" id="summaryId" name="id" value="${sendSummary.id}">
        <input type="hidden" class="span10" value="${sendSummary.supplierId}"  id="supplierId"><!-- 供应商ID--> 
        <input type="hidden" class="span10" value="${sendSummary.purchaserId}"  id="purchaserId"><!-- 采购商ID--> 
        <input type="hidden" class="span10" value="${constantName}"  id="constantName"><!-- constantName-->  
        <input type="hidden" id="isSupplierAddProduct" name="isSupplierAddProduct" value="${sendSummary.isSupplierAddProduct}">
			<div class='listBox '>
				<h5>发运信息</h5>
				<ul class="fyBox">
					<li>
						<span class='name'>发货单号：</span>
						<input type="text" id="sendoutGoodsCode" name="sendoutGoodsCode" value='${sendSummary.sendoutGoodsCode}' readOnly="true">
					</li>
					<li>
						<span class='name'>发货时间：</span>
						<span class='cont'> <fmt:formatDate value="${sendSummary.sendoutDate}" type="date" dateStyle="long"/> </span>
					</li>
					<li>
						<span class='name'>发货单状态：</span>
						<span class='cont'>${sendSummary.sendoutStatus==0?"未发货":sendSummary.sendoutStatus==5?"已发货（未收货）":sendSummary.sendoutStatus==10?"部分收货":"全部收货"}</span>
					</li>
					<li>
						<span class='name'>包件数：</span>
						<input type="text" id="pakagesQty" name="pakagesQty" value="${sendSummary.pakagesQty}">
					</li>
					<li>
						<span class='name'>运单号：</span>
						<input type="text" id="transportNo" name="transportNo" value="${sendSummary.transportNo}">
					</li>
					<li>
						<span class='name'>运输方式：</span>
						<input type="text" id="transportMode" name="transportMode" value="${sendSummary.transportMode}">
					</li>
					<li>
						<span class='name'>承运商：</span>
						<input type="text" id="transportCompany" name="transportCompany" value="${sendSummary.transportCompany}">
					</li>
					<li>
						<span class='name'>发到站：</span>
						<input type="text" id="destination" name="destination" value="${sendSummary.destination}">
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
		<div class='listBox listBoxbottom'>
				<h5>收货信息</h5>
				<ul>
					<li>
						<span class='name'>收货单位：</span>
						<span class='cont'>${sendSummary.receiveCompany}</span>
					</li>
					<li>
						<span class='name'>收货人：</span>
						<input type="text" id="receiveUserName" name="receiveUserName" value="${sendSummary.receiveUserName}">
					</li>
					<li>
						<span class='name'>联系电话：</span>
						<span class='cont'>${sendSummary.receivePhoneno}</span>
					</li>
				</ul>
			</div>
			
			<div class='listBox'>
				<h5>特殊说明</h5>
				<ul>
					<li class='specialLi'>
						<span class='name'>发货备注：</span>
						<input type="text" class='cont mark' id="sendoutRemark" name="sendoutRemark" value="${sendSummary.sendoutRemark}">
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
	                       		<c:if test="${sendSummary.isInitiative!='Y'}">
							      	<span id="addSendGoods" class="span add-btn" style='visibility:hidden'>发货添加商品</span> 
							    </c:if>
                        	 	<span class="span sure-btn">确认修改</span> 
							    <span class="span del-btn">整单删除</span> 
							    <span class="span back-btn">返 回</span>
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

<!-- 发货添加商品弹出开始 -->
<div id="myModal3" style="display:none">
  <div class="modal-body">
    <form class="form-horizontal">
    <div class="row-fluid top6">
      <div class="span5">
        <div class="control-group1">
          <label class="control-label3">商品名称：</label>
          <div class="controls3">
            <div class="span12">
              <input class="span12"  id="bookTitle" type="text">
            </div>
          </div>
        </div>
      </div>
      <div class="span5">
        <div class="control-group1">
          <label class="control-label3">ISBN：</label>
          <div class="controls3">
            <div class="span12">
              <input class="span12"  id="isbn" type="text">
            </div>
          </div>
        </div>
      </div>
      <div class="span2">
        <a href="#" class="btn btn-small left20"   id="search_btn" >查 询</a>
      </div>
    </div>
    <div class="row-fluid top10"><span class="fontB">供应商：<span  id="getSupplierName"></span></span></div>
    <div class="line1"></div>
    <div class="row-fluid top6">
      <div class="pull-right"> 
        <a href="javascript:" class="btn btn-small"  id="addToGoodArea">添加到商品区域</a>
      </div>
    </div>
    <div class="row-fluid top10"> 
      <table id = "tableb_1"></table>
      <div id="pageb_1"></div>
      </div>
		<div class="line1"></div>
		<div class="row-fluid top6">
		  <div class="pull-right"> 
		    <a href="javascript:" id="addrow_btn" class="btn btn-small">新增行</a> 
		    <a href="javascript:" class="btn btn-small" id="deleteData_btn">删除行</a>
		    <a href="javascript:" id="submitMasterData" class="btn btn-small  btn-primary">确认</a>
		  </div>
		</div>    
       <div class="row-fluid top10"> 
       <table id = "tableb_2">
      </table>
      <div id="pageb_2"></div>
    </div>
    </form>
  </div>
</div>
<!-- 发货添加商品弹出结束 -->  

<!-- 修改弹窗开始 -->
<div id="modModal" style="display:none">
  <div class="modal-body">
      <table id = "modtab"></table>
  </div>
</div>
<!-- 修改弹窗结束 -->

<!-- 中间内容结束 --> 

<!-- 尾文件开始 -->
<%@ include file="/common/footer.jsp" %>
<!-- 尾文件结束 --> 

</body>
</html>
