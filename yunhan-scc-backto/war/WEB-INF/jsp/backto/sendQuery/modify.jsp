<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/common/publicCsJs.jsp"%>
	<script type="text/javascript" src="<c:url value='/js/backto/sendQuery/modify.js'/>"></script>
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
			<img src="<c:url value='/img/arrow_red.gif'/>" width="7" height="5">&nbsp;&nbsp; 发货单修改 
		</div>
	</div>
<form class="form-horizontal"  id="summary" method="post"  style="padding-bottom: 40px;">
  <div class="container_center" style="margin-bottom:0">
    <div class="bs-docs-example">
      <div class="row-fluid left20">
        <div class="span3 fontB"> 发货单号：<input type="text" class="span8" id="sendoutGoodsCode"
          name="sendoutGoodsCode" value='${sendSummary.sendoutGoodsCode}' readOnly="true"> </div>
        <div class="span3 fontB"> 发货时间：<fmt:formatDate value="${sendSummary.sendoutDate}" type="date" dateStyle="long"/> </div>
        <div class="span4 fontB"> 发货单状态：
               ${sendSummary.sendoutStatus==0?"未发货":
        sendSummary.sendoutStatus==5?"已发货（未收货）":sendSummary.sendoutStatus==10?"部分收货":"全部收货"}
        </div>
        <input type="hidden" id="sgc" value="${sendSummary.sendoutGoodsCode}">
        <input type="hidden" id="summaryId" name="id" value="${sendSummary.id}">
        <input type="hidden" class="span10" value="${sendSummary.supplierId}"  id="supplierId"><!-- 供应商ID--> 
          <input type="hidden" class="span10" value="${sendSummary.purchaserId}"  id="purchaserId"><!-- 采购商ID--> 
        <input type="hidden" class="span10" value="${constantName}"  id="constantName"><!-- constantName-->  
        
        <input type="hidden" id="isSupplierAddProduct" name="isSupplierAddProduct" value="${sendSummary.isSupplierAddProduct}">
      </div>
      <div class="row-fluid fontblue fontB top6">发运信息>></div>
      <div class="row-fluid left20 top6">
        <div class="span3"> 承运商：
          <input type="text" class="span8" id="transportCompany"
          name="transportCompany" value="${sendSummary.transportCompany}">
        </div>
        <div class="span3"> 运单号：
          <input type="text" class="span8" id="transportNo" name="transportNo" value="${sendSummary.transportNo}">
        </div>
        <div class="span3"> 运输方式：
<%--                   <input type="text" class="span8" id="transportMode" name="transportMode" value="${sendSummary.transportMode eq '1' ? '汽运':'火车'}">
 --%>          <input type="text" class="span8" id="transportMode" name="transportMode" value="${sendSummary.transportMode}">
        </div>
        <div class="span3"> 包件数：
          <input type="text" class="span8" id="pakagesQty" name="pakagesQty" value="${sendSummary.pakagesQty}">
        </div>
      </div>
      <div class="row-fluid left20 top6">
        <div class="span3"> 发到站：
          <input type="text" class="span8" id="destination" name="destination" value="${sendSummary.destination}">
        </div>
        <div class="span9"> 发货备注：
          <input type="text" class="span3" id="sendoutRemark" name="sendoutRemark" value="${sendSummary.sendoutRemark}">
        </div>
      </div>
      <div class="row-fluid fontblue fontB top6">收货信息>></div>
      <div class="row-fluid left20 top6">
        <div class="span3"> 收货人：
          <input type="text" class="span8" id="receiveUserName" name="receiveUserName" value="${sendSummary.receiveUserName}">
        </div>
        <div class="span3"> 收货单位：${sendSummary.receiveCompany}</div>
        <div class="span3"> 收货联系方式：${sendSummary.receivePhoneno} </div>
      </div>
      <div class="row-fluid fontblue fontB top6">发货统计>></div>
      <div class="row-fluid left20 top6">
        <div class="span3"> 发货品种数：${sendSummary.totalVarietyQty}</div>
        <div class="span3"> 总册数：${sendSummary.totalBookQty} </div>
        <div class="span3"> 总码洋：${sendSummary.totalPrice}</div>
        <div class="span3"> 总实洋：${sendSummary.totalRealityPrice} </div>
      </div>
    </div>
    <div class="row-fluid">
      <div class="pull-right"> 
      <c:if test="${sendSummary.isInitiative!='Y'}">
      	<a id="addSendGoods" class="btn btn-small add-btn" data-toggle="modal"  style='visibility:hidden'>发货添加商品</a> 
      </c:if>
      <a href="javaScript:void(0);" class="btn btn-small left10 sure-btn">确认修改</a> 
      <a href="javaScript:void(0);" class="btn btn-small left10 del-btn">整单删除</a> 
      <a href="javaScript:void(0);" class="btn btn-small left10 back-btn">返 回</a> </div>
    </div>
    <div class="row-fluid top6"> 
      <table id = "table_list" class="table-block">
      </table>
      <div id="page"></div>
    </div>
  </div>
</form>
<!-- 发货添加商品弹出开始 -->
<div id="myModal3" class="modal hide fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4>发货添加商品</h4>
  </div>
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
<script>

 
 
        
</script>
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
  <!--   <div align="center">
      <a   id="submitMasterData" class="btn btn-default btn-primary">确认添加</a> 
      <a href="#" class="btn btn-default left20">取 消</a>
    </div> -->
    </form>
  </div>
</div>
<!-- 发货添加商品弹出结束 -->  

<!-- 修改弹窗开始 -->
<div id="modModal" class="modal-send hide fade modal in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h5>发货修改</h5>
  </div>
  <div class="modal-body">
      <table id = "modtab"></table>
  </div>
  	<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取 消</button>
        <button type="button" class="btn btn-primary" id="updateMod_but">确 认</button>
      </div>
</div>
<!-- 修改弹窗结束 -->

<!-- 中间内容结束 --> 

<!-- 尾文件开始 -->
<%@ include file="/common/footer.jsp" %>
<!-- 尾文件结束 --> 

</body>
</html>
