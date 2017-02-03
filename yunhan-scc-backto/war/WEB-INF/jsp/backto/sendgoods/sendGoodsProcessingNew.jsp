<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/common/publicCsJsNew.jsp"%>
<link href="<c:url value='/css/backto/sendGoodsProcessingNew.css'/>" rel="stylesheet" >
<script type="text/javascript" src="<c:url value='/js/backto/sendgoods/sendGoodsProcessingNew.js'/>"></script>

</head>

<body style="min-width:100%">
    <div class="sendProductsBox">
      
        <div class="infoBox">
        	<input type="hidden" id="addProductPurs" value="${addProductPurs}">
        	<input type="hidden" id="reportIds" value="${reportIds}">
        	<input type="hidden" id="purchaserId" value="${sendoutSummaryDO.purchaserId}">
        	<input type="hidden" id="supplierId" value="${sendoutSummaryDO.supplierId}">
        	<input type="hidden" id="sendReg" value="${sendReg}">
        	<input type="hidden" id="receiveWarehouse" value="${sendoutSummaryDO.receiveWarehouse}">
          <ul>
            <li>
            	<span class="name">发货品种数：</span>
            	<span class="con" id="totalVarietyQty">${sendoutSummaryDO.totalVarietyQty}</span>
            </li>
            <li>
            	<span class="name">本次发货数：</span>
            	<span class="con" id="totalBookQty">${sendoutSummaryDO.totalBookQty}</span>
            </li>
            <li>
            	<span class="name">本次发货总码洋：</span>
            	<span class="con" id="totalPrice">${sendoutSummaryDO.totalPrice}</span>
         	</li>
            <li>
            	<span class="name">本次发货总实洋：</span>
            	<span class="con" id="totalRealityPrice">${sendoutSummaryDO.totalRealityPrice}</span>
            </li>
            </ul>
            <div class='connect'>
            	<div class="left">
	            	<span class="name">收货人：</span>
	            	<span class="con" id="receiveUserName">${sendoutSummaryDO.receiveUserName}</span>
            	</div>
	            <div class="right">
	            	<span class="name">联系电话：</span>
	            	<span class="con" id="receivePhoneno">${sendoutSummaryDO.receivePhoneno}</span>
	            </div>
            </div>
            <div class='add'>
            	<span class="name">收货地址：</span>
            	<span class="con" id="receiveAddress">${sendoutSummaryDO.receiveAddress}</span> 
            </div>
          </ul>
        </div>
        <div class="actiBtn">
                <a href="javascript:;" id="addProduct" style="visibility:hidden" class="btn_background addProduct">发货添加商品</a>
                <a href="javascript:;" class="btn_background sendBtn">发 送</a> 
        </div>
        
        <div class="inputBox">
          <ul>
            <li>
              <span class="name"><span class="fontred">*</span> 发货单号：</span>
              <input type="text" id="sendoutGoodsCode">
            </li>
            <li>
              <span class="name"><span class="fontred">*</span> 发货日期：</span>
              <input type="text" class="startTime timeIcon" id="sendoutDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(){if(window.parent){window.parent.window.FORM_CHANGED = true;}}})" type="text"  >
            </li>
            <li>
              <span class="name"><span class="fontred">*</span> 发到站：</span>
              <input type="text" id="sendoutStation" value="${sendoutSummaryDO.sendoutStation}">
            </li>
            <li>
              <span class="name">承运商：</span>
              <input type="text" id="transportCompany" >
            </li>
            <li>
              <span class="name">运单号：</span>
              <input type="text" id="transportNo">
            </li>
            <li>
              <span class="name">运输方式：</span>
              <select id="transportMode" style='width: 54%;'>
              	<option value="1">汽车</option>
              	<option value="2">火车</option>
              </select>
            </li>
            <li>
              <span class="name">包件数：</span>
              <input type="text" id="pakagesQty">
            </li>
            <li style='width:62%'>
              <span class="name">发货单备注：</span>
              <input type="text" id="sendoutRemark" style='width:81%'>
            </li>
            
          </ul>
        </div>
        <div class="percent">
          <div class="btn">
            <span class="fontB">发货商品明细</span>
            <span id="icon" class="unfold"></span> 
            <span id="TABopen" class="fontleft"> 展 开</span>
          </div>
          
          <div class="JGtab" style="height: 0px;overflow: hidden;">
            <table id = "sendProduct_List" class="table-block"></table>
          </div>
        </div>
     
    </div>
 <!-- Modal3弹出开始 -->
<div id="myModal3" class="myModal3" style="display:none;" >
    <div class="modal-header">
        <span class="closeAddSend">×</span>
        <h5>发货添加商品</h5>
    </div>
    <div class="box-body" >
             <ul class="inputBox">
                 <li>
                     <span class="name">商品名称：</span>
                     <input id="query_bookTitle" type="text">
                 </li>

                 <li>
                     <span class="name">ISBN：</span>
                     <input id="query_isbn" type="text">
                 </li>
                 <li style="text-align: right;">
                      <a href="javascript:;"  class="btn_background search">查 询</a>
                 </li>
             </ul>
            <div class="addBox">
                <span class="fontB">供应商：${sendoutSummaryDO.supplierName}</span>
                <a href="javascript:;" class="btn_white addProductBtn ">添加到商品区域</a>
            </div>
            <div class="JGtab">
                <table id = "product_list"></table>
                <div id="product_page"></div>
            </div>
            
            <div class="addBox">
				<a href="javascript:;" id="save" class="btn btn_background ">确 认</a>
                <a href="javascript:;" class="deleteData_btn btn_white ">删除行</a>
                <a href="javascript:;" class=" addRow btn_white">新增行</a>
            </div>
            <div class="JGtab">
                <table id = "table_d"></table>
            </div>
        
    </div>
</div>
<!-- Modal3弹出结束 -->
</body>
</html>