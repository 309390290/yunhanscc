<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/common/publicCsJs.jsp"%>
<script type="text/javascript"
	src="<c:url value='/js/backto/sendgoods/sendGoodsProcessing.js'/>"></script>

</head>

<body style='overflow-y: auto;'>
    <div class="modal-body">
      <div class="popup"> 
        <div class="infoBox">
        	<input type="hidden" id="addProductPurs" value="${addProductPurs}">
        	<input type="hidden" id="reportIds" value="${reportIds}">
        	<input type="hidden" id="purchaserId" value="${sendoutSummaryDO.purchaserId}">
        	<input type="hidden" id="supplierId" value="${sendoutSummaryDO.supplierId}">
        	<input type="hidden" id="sendReg" value="${sendReg}">
        	<input type="hidden" id="receiveWarehouse" value="${sendoutSummaryDO.receiveWarehouse}">
          <ul>
            <li>发货品种数：<span id="totalVarietyQty">${sendoutSummaryDO.totalVarietyQty}</span></li>
            <li>本次发货数：<span id="totalBookQty">${sendoutSummaryDO.totalBookQty}</span></li>
            <li>本次发货总码洋：<span id="totalPrice">${sendoutSummaryDO.totalPrice}</span></li>
            <li>本次发货总实洋：<span id="totalRealityPrice">${sendoutSummaryDO.totalRealityPrice}</span></li>
            <li>收货人：<span id="receiveUserName">${sendoutSummaryDO.receiveUserName}</span></li>
            <li>
            	<i class="name">联系电话：</i>
            	<span class="con" id="receivePhoneno">${sendoutSummaryDO.receivePhoneno}</span>
            </li>
            <li class="w50">收货地址：<lable id="receiveAddress">${sendoutSummaryDO.receiveAddress}</lable> </li>
          </ul>
        </div>
        <div style='float: left;width: 852px;text-align: right;margin-bottom: 15px;margin-top: 5px;'>
                <a href="javascript:;" id="addProduct" style="visibility:hidden" class="btn addProduct btn-small ">发货添加商品</a>
                <a href="#" class="btn  sendBtn btn-small right-10">发 送</a> 
        </div>
        
        <div class="inputBox">
          <ul>
            <li>
              <span class="name"><span class="fontred">*</span> 发货单号：</span>
              <input type="text" id="sendoutGoodsCode">
            </li>
            <li>
              <span class="name"><span class="fontred">*</span> 发货日期：</span>
              <input type="text" class="startTime" id="sendoutDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"  ><span class="u-icon u-icon-date"></span>
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
              <select id="transportMode" style='width: 150px;height: 26px;'>
              	<option value="1">汽车</option>
              	<option value="2">火车</option>
              </select>
            </li>
            <li>
              <span class="name">包件数：</span>
              <input type="text" id="pakagesQty">
            </li>
            <li style='width:64%'>
              <span class="name">发货单备注：</span>
              <input type="text" id="sendoutRemark" style='width:406px'>
            </li>
            
          </ul>
        </div>
        <div class="percent">
          <div class="percent50">
            <span class="fontB">发货商品明细</span>
          </div>
          <div class="percent50">
            <span class="unfold"></span> <span id="TABopen" class="fontleft"> 展 开</span>
          </div>
          <div class="JGtab" style="height: 0px;overflow: hidden;">
            <table id = "sendProduct_List" class="table-block"></table>
            <div id="sendProduct_page"></div>
          </div>
        </div>
      </div>
    </div>
 <!-- Modal3弹出开始 -->
<div id="myModal3" class="myModal3" style="z-index: 9999;display:none; position: fixed;top: 50%;left: 50%;background-color: #fff; margin-left:-430px; margin-top: -270px" >
    <div class="modal-header">
        <button type="button" class="close closeAddSend" data-dismiss="modal" aria-hidden="true">×</button>
        <h5>发货添加商品</h5>
    </div>
    <div class="modal-body" style="height: 500px !important;  overflow-y: auto !important; ">
        <div class="popup">
            <div class="inputBox row_1" style="overflow: hidden; float:none">
                <ul>
                    <li>
                        <span class="name">商品名称：</span>
                        <input id="query_bookTitle" type="text">
                    </li>

                    <li>
                        <span class="name">ISBN：</span>
                        <input id="query_isbn" type="text">
                    </li>
                    <li>
                        <div align="right">
                            <a href="javascript:;"  class="btn search btn-small right-10">查 询</a>
                        </div>
                    </li>
                </ul>
            </div>
            <div>
                <span class="fontB">供应商：${sendoutSummaryDO.supplierName}</span>
            </div>
            <div class="JGtab">
                <div align="right" class="right">
                    <a href="javascript:;" class="btn addProductBtn btn-small">添加到商品区域</a>
                </div>
                <table id = "product_list"></table>
                <div id="product_page"></div>
            </div>
            <div class="JGtab" style="padding-bottom: 15px;">
                <div align="right"  class="right">
                    <a class="btn addRow btn-small">新增行</a>
                    <a class="btn  deleteData_btn btn-small">删除行</a>
                    <a href="javascript:;" id="save" class="btn btn-small btn-primary">确 认</a>
                </div>
                <table id = "table_d" class="table-block" ></table>
                
            </div>
        </div>
    </div>
</div>
<!-- Modal3弹出结束 -->
</body>
</html>