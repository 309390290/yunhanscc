<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--  
	订单处理-品种方式
	wangtao
	2016年7月14日13:38:44
-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/common/publicCsJsNew.jsp"%>
<link href="<c:url value='/css/backto/orderItemsProcessingNew.css'/>" rel="stylesheet" >

<script type="text/javascript"src="<c:url value='/js/backto/order/orderItemsProcessingNew.js'/>"></script>
<script type="text/javascript"src="<c:url value='/js/backto/order/orderImportNew.js'/>"></script>
<script type="text/javascript"src="<c:url value='/js/backto/sendgoods/sendGoodsAndSendNew.js'/>"></script>

</head>
<!-- 用户声明dialog变量，以便关闭子页面IFrame的外层dialog -->
<script>
var diabox=null;
</script>
<body>
	<!-- 头文件开始 -->
	<%@ include file="/common/menu.jsp"%>
	<!-- 头文件结束 -->
	
<!-- 面包导航开始 -->
<div class="headerBox">
  <div class="active_com"> <span class="position"><b class="map-marker"></b>当前位置：</span>
    <ul class="ul">
      <li> <a href="JavaScript:;"> <span>首 页</span> </a> <b> > </b> </li>
      <li> <a href="JavaScript:;"> <span>订单处理</span> </a> <b> > </b> </li>
      <li> <a href="JavaScript:;"> <span>品种方式</span> </a> </li>
    </ul>
  </div>
</div>
<!-- 面包导航结束 --> 

<!-- 中间内容结束开始 -->
	<div class="bodyBox">
		<div class="bodyTab">
			<ul class="headActive" id="headActive">
				<li class="active"><a href="javascript:;">待处理品种</a></li>
				<li><a href="javascript:;">已处理品种查询</a></li>
			</ul>
			<span class="activeNow" id="activeNow"></span>
		</div>
	
	<div class="bodyContentBox">
			<!-- 待发送开始 -->
			<div class="Tab tabOne active" id="TabA">
				<div class="screenBox">
					<div class="screenTable">
					<div class="left">
						<ul>
							<li> 
								<span class="name"><span class="fontred">*</span> 采购商</span>
				              	<div class="com">
				                <select id="pur_all" name="pur_all" class="query_data_all">
										<c:forEach items="${department }" var="list">
											<option <c:if test="${list.sapvendorId==purchaserId}">selected</c:if> value = "${list.sapvendorId }">${list.name }</option>
										</c:forEach>
								</select>
				              </div>
				            </li>
							<li> 
								<span class="name"><span class="fontred">*</span> 仓 位</span>
				              	<div class="com">
				                	<select class="query_data_all" id="dc_all" name="dc">
				                </select>
				              </div>
				            </li>
							<li> 
								<span class="name">品种处理情况</span>
				                <div class="com">
				               		<div class="isValidOne"></div>
				                </div>
				            </li>
				            <li> 
				            	<span class="name">发送日期</span>
				             	<div class="com time">
					                <input type="text" onclick="WdatePicker();" id="sendDateStart">
					                <b></b>
					                <span></span>
					                <input type="text" onclick="WdatePicker();" id="sendDateEnd">
					                <b></b>
				                	<div class="time_box">
					                	<div class="mes" style="height: 18px;"><i class="badge" id="month1ago"></i></div>
					                    <a name="send_Date" id="month_1">1个月内</a>
					                    <a name="send_Date" id="month_1_ago">1个月前</a>
					                </div>
			                	</div>
				            </li>
				            <li>  
				            	<span class="name">下载标识</span>
				            	<div class="com">
					            	<select id="isExport">
					                    <option selected="" value="">全部</option>
					                    <option value="Y">已下载</option>
					                    <option value="N">未下载</option>
				                    </select>
			                    </div>
				            </li>
				            <li>  
				            	<span class="name">紧急程度</span>
				            	<div class="com">
					            	<select id="urgentFlag">
					                    <option selected="" value="">全部</option>
					                    <option value="1">急单</option>
					                    <option value="2">普通单</option>
				                    </select>
			                    </div>
				            </li>
				            <li class="hide">  
				              <span class="name">订单种类</span>
				              <div class="com">
					              <div class="AllSelect"></div>
				              </div>
				            </li>
				            <li class="hide">   
				            	<span class="name">新品订单</span>
				            	<div class="com">
					            	<select id="isNew">
					                    <option selected="" value="">全部</option>
					                    <option value="Y">新品</option>
					                    <option value="N">非新品</option>
				                    </select>
			                    </div>
				            </li>
							<li class="hide">   
				              <span class="name">订单号码</span>
				              <div class="com gys">
				                <input type="text" id="orderCode" value="${orderCodes }">
				                <span class="add ddh-btn"></span> <span class="del"></span> 
				              </div>
				            </li>
							<li class="hide">  
				              <span class="name">ISBN</span>
				              <div class="com gys">
				                <input type="text"  id="isbn">
				                <span class="add isbn-btn"></span>
				                <span class="del"></span>
				              </div>
				            </li>
							<li class="hide">  
				              <span class="name">商品名称</span>
				              <div class="com gys">
				                <input type="text"  id="booktitle">
				                <span class="add spmc-btn"></span> <span class="del"></span> 
				              </div>
				            </li>
						</ul>
					</div>
					<div class="right">
	                    <div class="btn">
	                        <span class="span1" id="clickBtnSerach"><i></i>搜 索</span>
	                        <span id="openA" class="span2"><i class="open"></i><b>展 开</b></span>
	                    </div>
               		</div>
					</div>
				</div>
				
				<div class="gridBox">
					<div class="infoBox">
						 <div class="left">
			                <span>收货地址：<i id="paddr"></i></span>
					        <span class="shMark">收货人：<i id="contact"></i></span> 
					        <span class="shMark">联系电话：<i id="contactNumber"></i></span>
			             	<input type="hidden" id="ids"> 
			             </div>
			             <div class="right">
						 	 <span>本次发货实洋：<i id="totalSendRealPrc">0</i></span>
						 	 <span>本次发货码洋：<i id="totalSendPrice">0</i></span>
						 	 <span>本次发货数：<i id="totalSendQty">0</i></span>
			              	 <span>已选品种：<i id="totalVariety">0</i>个</span>
					 	 </div>
			        </div>
	                <div class="sortBox">
	                	<ul class="sortBoxLi"  id="sortBox">
			                  <li class="active" data-value="sendDate" name="click">发送日期↓</li>
			                  <li data-value="bookTitle">商品名称↓</li>
			                  <li data-value="price">定价↓</li>
			                  <li data-value="discountrate">折扣↓</li>
			                  <li data-value="purchaseOrderCode">订单号↓</li>
			                  <li data-value="orderQty">订数↓</li>
		                </ul>
	                    <div class="orderBox">
	                        <div class="com del">
				              
							  <div class="selectDIY">
								    <span>无订单发货</span>
							    	<i></i>
								    <div class="oCon">
								        <a href="/fileupload/files/backto/template/无订单发货模板.xlsx">导 出</a>
								        <a href="javascript:;" class="blankTempImportBtn">导 入</a>
								    </div>
							  </div>
	                          <span class='span' id="importSendBtn">导 入</span>
	                          <span class='span' id="waitVarietiesExport">导 出</span>
	                          <span class='span' id="batchInput">批量录入</span>
	                          <span class='span' id="save">保 存</span>
	                          <span class='span' id="sendGoods">确 认</span>
	                          <span class='span spanBack' id="closeSend">关闭发货</span>
	                          <div id="odiv" style='float: right;margin-left: 4px;text-align: left;'></div>
	                        </div>
	                    </div>
	                </div>
	                <div class="jqGrid">
	                	<table id="table"></table>
						<div id="page"></div>
	                </div>
            	</div>
			</div>
			<!-- 待发送结束 -->
			<!-- 不发送开始 -->
			<div class="Tab tabTwo" id="TabB">
				<div class="screenBox">
					<div class="screenTable">
					<div class="left">
						<ul>
							<li> 
								<span class="name"><span class="fontred">*</span> 采购商</span>
				              	<div class="com">
				                <select id="pur_allTwo" name="pur_allTwo" class="query_data_all">
										<c:forEach items="${department }" var="list">
											<option <c:if test="${list.sapvendorId==purchaserId}">selected</c:if> value = "${list.sapvendorId }">${list.name }</option>
										</c:forEach>
								</select>
				              </div>
				            </li>
							<li> 
								<span class="name"><span class="fontred">*</span> 仓 位</span>
				              	<div class="com">
				                	<select class="query_data_allTwo" id="dc_allTwo" name="dcTwo">
				                </select>
				              </div>
				            </li>
							<li> 
								<span class="name">品种处理情况</span>
				                <div class="com">
				               		<div class="isValidTwo"></div>
				                </div>
				            </li>
				            <li> <span class="name">发送日期</span>
					              <div class="com time">
					                <input type="text" onClick="WdatePicker();" id="sendDateStartTwo">
					                <b></b>
					                <span></span>
					                <input type="text" onClick="WdatePicker();" id="sendDateEndTwo">
					                <b></b>
			                	</div>
				            </li>
				            <li>  
				            	<span class="name">下载标识</span>
				            	<div class="com">
					            	<select id="isExportTwo">
					                    <option selected="" value="">全部</option>
					                    <option value="Y">已下载</option>
					                    <option value="N">未下载</option>
				                    </select>
			                    </div>
				            </li>
				            <li>  
				            	<span class="name">紧急程度</span>
				            	<div class="com">
					            	<select id="urgentFlagTwo">
					                    <option selected="" value="">全部</option>
					                    <option value="1">急单</option>
					                    <option value="2">普通单</option>
				                    </select>
			                    </div>
				            </li>
				            <li class="hide">  
				              <span class="name">订单种类</span>
				              <div class="com">
					              <div class="AllSelectTwo"></div>
				              </div>
				            </li>
				            <li class="hide">   
				            	<span class="name">新品订单</span>
				            	<div class="com">
					            	<select id="isNewTwo">
					                    <option selected="" value="">全部</option>
					                    <option value="Y">新品</option>
					                    <option value="N">非新品</option>
				                    </select>
			                    </div>
				            </li>
							<li class="hide">  
				              <span class="name">订单号码</span>
				              <div class="com gys">
				                <input type="text" id="orderCodeTwo" value="${orderCodes }">
				                <span class="add ddh-btn"></span> <span class="del"></span> </div>
				            </li>
							<li class="hide">  
				              <span class="name">ISBN</span>
				              <div class="com gys">
				                <input type="text"  id="isbnTwo">
				                <span class="add isbn-btn"></span>
				                <span class="del"></span>
				              </div>
				            </li>
							<li class="hide">  
				              <span class="name">商品名称</span>
				              <div class="com gys">
				                <input type="text"  id="booktitleTwo">
				                <span class="add spmc-btn"></span> <span class="del"></span> 
				              </div>
				            </li>
						</ul>
						<div>
							<!--全选插件-->
						</div>
					</div>
					<div class="right">
	                    <div class="btn">
	                        <span class="span1" id="clickBtnTwo"><i></i>搜 索</span>
	                        <span id="openB" class="span2"><i class="open"></i><b>展 开</b></span>
	                    </div>
                	</div>
				</div>
				</div>
				<div class="gridBox">
					<div class="infoBox">
						 <div class="left">
			                <span>收货地址：<i id="paddrTwo"></i></span>
					        <span class="shMark">收货人：<i id="contactTwo"></i></span> 
					        <span class="shMark">联系电话：<i id="contactNumberTwo"></i></span>
			             </div>
			        </div>
	                <div class="sortBox">
		                <ul class="sortBoxLi1" id="sortBoxTwo">
		                  <li class="active" data-value="sendDate" name="click">发送日期↓</li>
		                  <li data-value="bookTitle">商品名称↓</li>
		                  <li data-value="price">定价↓</li>
		                  <li data-value="discountrate">折扣↓</li>
		                  <li data-value="purchaseOrderCode">订单号↓</li>
		                  <li data-value="orderQty">订数↓</li>
		                </ul>
	                    <div class="orderBox">
	                        <div class="com del">
	                          <span class='span' id="processedVarietiesExport">导 出</span>
            				  <div id="odiv2" style='float: right;margin-left: 4px;text-align: left;'></div>
	                        </div>
	                    </div>
	                </div>
	                <div class="jqGrid">
	                	<table id="table_a"></table>
						<div id="page_a"></div>
	                </div>
            	</div>
			</div>
			<!-- 不发送结束 -->
		</div>
	</div>


<!-- Modal弹出开始 -->
<div id="myModal" style="display: none;">
    <form>
        <div class="modal-body">
            <div class="popup">
                <div class="rowBox">
                    <span class="w110">折 扣：</span>
                    <input class="big" type="text"  id="batchDiscountrate">
                </div>
                <div class="rowBox">
                    <span class="w110">本次发货数：</span>
                    <input class="big" type="text"  id="batchSendQty">
                </div>
                <div class="rowBox"> <span class="w110">其余满足情况：</span>
                    <select class="big" name="batchNotEnoughReason" id="batchNotEnoughReason">
                        <option></option>
                        <option value='0'>预计可发</option>
                		<option value='1'>无货待加印</option>
                		<option value='8'>新书待入库</option>
                		<option value='2'>已停产</option>
                		<option value='3'>改版</option>
                		<option value='4'>版权到期</option>
                		<option value='5'>商品无效</option>
                		<option value='6'>销售受限</option>
                		<option value='9'>无货不发</option>
                    </select>
                </div>
                <div class="rowBox"> <span class="w110">其余预计发货日期：</span>
                    <div class="time">
                        <input class="date Wdate" type="text" onclick="WdatePicker()"  id="batchSendDate" >
                        <span class="u-icon u-icon-date"></span>
                    </div>
                </div>
                <div class="rowBox">
                    <span class="w110">备 注：</span>
                    <input class="big" type="text" id="batchRemark">
                </div>
            </div>
        </div>
    </form>
</div>
<!-- Modal弹出结束 --> 

<!-- Modal1弹出开始 -->
<div id="myModal1" style="display:none">
	<iframe id="sendGoodsViewIFrame" name="sendGoodsViewIFrame" frameborder=0 width="900" height="450" marginheight=0 marginwidth=0 scrolling=yes ></iframe>
</div>
<!-- Modal1弹出结束 -->

<!-- Modal2弹出开始 -->
<div id="myModal2" style="display:none ;padding-bottom:20px">
   <div class="JGtab">
       <table id = "table_b"></table>
   </div>
</div>
<!-- Modal2弹出结束 -->


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


<!-- ModalReason弹出开始 -->
	<div id="myModalReason" style="display:none">
		<form id="notGoodsReasonForm" method="post">
			<input class="big" type="hidden" id="notGoodsReasonIds">
			<div class="modal-body">
				<div class="popup">
					<div class="rowBox">
						<span class="w120">注意：关闭后，当前订单的品种不再发货！<br>* 请选择关闭原因：</span> 
					</div>
					<div class="rowBox">
						<select class="big" id="notGoodsReason">
            				<option value='2'>已停产</option>
            				<option value='3'>改版</option>
            				<option value='4'>版权到期</option>
            				<option value='5'>商品无效</option>
            				<option value='6'>销售受限</option>
            				<option value='9'>无货不发</option>
						</select>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- Modal2弹出结束 -->

<!-- 中间内容结束 --> 

 <input type="hidden" id="isDealRe" name="isDealRe" value="${isDeal}">
 <input type="hidden" id="wareHouseRe" name="wareHouseRe" value="${wareHouse}">
 <input type="hidden" id="sendGoodsTypeRe" name="sendGoodsTypeRe" value="${sendGoodsType}">
 <input type="hidden" id="isValidRe" name="isValidRe" value="${isValid}">
 <input type="hidden" id="orderTypeRe" name="orderTypeRe" value="${orderType}">
 <input type="hidden" id="otherReasonRe" name="otherReasonRe" value="${otherReason}">
 <input type="hidden" id="controlFlagRe" name="controlFlagRe" value="${controlFlag}">


	<!-- 尾文件开始 -->

	<%@ include file="/common/footer.jsp"%>
	<!-- 尾文件结束 -->
</body>
</html>
