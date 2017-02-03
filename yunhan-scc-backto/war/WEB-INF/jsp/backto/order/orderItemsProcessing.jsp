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
<%@ include file="/common/publicCsJs.jsp"%>
<script type="text/javascript"
	src="<c:url value='/js/backto/order/orderItemsProcessing.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/backto/order/orderImport.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/backto/sendgoods/sendGoodsAndSend.js'/>"></script>
</head>

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
            <li class="active">待处理品种</li>
            <li>已处理品种查询</li>
        </ul>
  </div>
  <div class="contentBox">
    <div class="Tab tabOne">
        <div class="searchBox">
        	<div class="screenTable">
		        <div class="left">
		          <ul>
		            <li> <span class="name"><span class="fontred">*</span> 采购商</span>
		              <div class="com">
		                <select id="pur_all" name="pur_all" class="span10 query_data_all">
							<!-- <option value="">==请选择采购商==</option> -->
								<c:forEach items="${department }" var="list">
									<option <c:if test="${list.sapvendorId==purchaserId}">selected</c:if> value = "${list.sapvendorId }">${list.name }</option>
								</c:forEach>
						</select>
		              </div>
		            </li>
		            <li> <span class="name"><span class="fontred">*</span> 仓 位</span>
		              <div class="com">
		                <select class="span10 query_data_all" id="dc_all" name="dc">
		                </select>
		              </div>
		            </li>
		            <li> <span class="name">品种处理情况</span>
		               <div class="com">
		               		<div class="isValidOne"></div>
		               </div>
		            </li>
		            <li class="hide"> 
		              <span class="name">订单种类</span>
		              <div class="com">
			              <div class="AllSelect">
			              	
			              </div>
		               <!-- <select class="span10 query_data_all" id="orderType">
		                    <option value="">全部</option>
		                    <option value="0">零售订单</option>
		                    <option value="5">文教订单</option>
		                    <option value="10">电商订单</option>
		                    <option value="15">大中专订单</option>
		                    <option value="20">团购订单</option>
		                    <option value="25">馆配订单</option>
		                    <option value="30">活动订书</option>
		                  </select> -->
		              </div>
		            </li>
		            <li class="hide">  <span class="name">新品订单</span>
		              <div class="choice">
		                 <input type="checkbox" value="" class="all-check" id="all_1" name="isNew"  checked="checked" >
		                <label for="all_1">全部</label>
		                <input type="checkbox" value="Y" class="single-check" id="new_1" name="isNew" checked="checked" >
		                <label for="new_1">新品</label>
		                <input type="checkbox" value="N" class="single-check" id="new_2" name="isNew" checked="checked" >
		                <label for="new_2">非新品</label>
		              </div>
		            </li>
		            <li class="hide">  <span class="name">紧急程度</span>
		              <div class="choice">
		                <input type="checkbox" value="" class="all-check" id="all_2" name="urgentFlag" checked="checked">
		                <label for="all_2">全部</label>
		                <input type="checkbox" value="1" class="single-check" id="jd_1" name="urgentFlag" checked="checked">
		                <label for="jd_1">急单</label>
		                <input type="checkbox" value="2" class="single-check" id="jd_2" name="urgentFlag" checked="checked">
		                <label for="jd_2">普通单</label>
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
		                <span class="add spmc-btn"></span> <span class="del"></span> </div>
		            </li>
		            <li class="hide">  
		              <span class="name">订单号码</span>
		              <div class="com gys">
		                <input type="text" id="orderCode" value="${orderCodes }">
		                <span class="add ddh-btn"></span> <span class="del"></span> </div>
		            </li>
		            <li>  <span class="name">发送日期</span>
		              <div class="com time">
		                <input type="text" onClick="WdatePicker();" id="sendDateStart">
		                <span class="u-icon u-icon-date"></span>
		                <span>至</span>
		                <input type="text" onClick="WdatePicker();" id="sendDateEnd">
		                <span class="u-icon u-icon-date"></span>
		                <div class="time_box">
		                    <a name="send_Date" id="month_1" >1个月内</a>
		                	<a name="send_Date" id="month_1_ago" >1个月前</a><i class="badge" id="month1ago"></i>
		                </div>
		              </div>
		            </li>
		            <li>  <span class="name">下载标识</span>
		              <div class="choice">
		                  <input type="checkbox" value="" class="all-check" id="all_3" name="isExport"  checked="checked" >
		                  <label for="all_3">全部</label>
		                  <input type="checkbox" value="Y" class="single-check" id="cl_1" name="isExport" checked="checked" >
		                  <label for="cl_1">已下载</label>
		                  <input type="checkbox" value="N" class="single-check" id="cl_2" name="isExport" checked="checked" >
		                  <label for="cl_2">未下载</label>
		              </div>
		            </li>
		          </ul>
		          <div> 
		            <!--全选插件--> 
		          </div>
		        </div>
		        <div class="right" >
		            <div class="btn_back">
		                <a href="javascript:;"  class="btn btn-primary btn-small"  id="clickBtnSerach">
		                <i class="icon-white icon-search"  ></i> 搜索</a>
		                <div class="shouListBtn">
		                    <i class="unfold left-10 top-4 img"></i>
		                    <i class="fontleft left-10 text"> 展 开</i>
		                </div>
		            </div>
		        </div>
		  </div>
      </div>
        <div class="infoBox" >
        	<div class="left" style="width: 50%"> 
	        	<div class="fontred fontB">
	                <span>收货地址：<span id="paddr"></span></span>
			        <span>收货人：<span id="contact"></span></span> 
			        <span>联系电话：<span id="contactNumber"></span></span>
	             	<input type="hidden"  id="ids"> 
		        </div>
	        </div>
          <div align="right" class="right" style="width: 50%"> 
            <div align="left" class="btn-group">
              <a class="btn btn-small dropdown-toggle" data-toggle="dropdown" href="javascript:;">无订单发货 <span class="caret"></span></a>
                <ul class="dropdown-menu">
                   <li><a href="/fileupload/files/backto/template/无订单发货模板.xlsx">导 出</a></li>
                   <li><a href="javascript:;" class="blankTempImportBtn" >导 入</a></li>
                </ul>
            </div>
            <a id="importSendBtn"  class="btn  btn-small" >导入</a>
            <a  class="btn  btn-small" id="waitVarietiesExport">导出</a>
            <a  id="batchInput"  class="btn btn-small" data-toggle="modal">批量录入</a> 
            <input  type="button" id="save" class="btn btn-small" value="保 存">
            <input  type="button" id="sendGoods"  class="btn btn-small" data-toggle="modal"  value="确 认">
            <a id="closeSend" class="btn btn-small btn-primary">关闭发货</a>
            <div id="odiv" style='float: right;margin-left: 10px;text-align: left;'></div>
          </div>
        </div>
        <div class="sortBox">
          <div class="left">
              <span class="name">排序方式：</span>
              <ul class="sortBoxLi"  id="sortBox">
                  <li class="active" data-value="sendDate" name="click">发送日期↓</li>
                  <li data-value="bookTitle">商品名称↓</li>
                  <li data-value="price">定价↓</li>
                  <li data-value="discountrate">折扣↓</li>
                  <li data-value="purchaseOrderCode">订单号↓</li>
                  <li data-value="orderQty">订数↓</li>
              </ul>
          </div>
          <div class="right">
            <ul>
              <li>已选品种：<span id="totalVariety">0</span>个</li>
			  <li>本次发货数：<span id="totalSendQty">0</span></li>
			  <li>本次发货码洋：<span id="totalSendPrice">0</span></li>
			  <li>本次发货实洋：<span id="totalSendRealPrc">0</span></li>
            </ul>
          </div>
        </div>
        <div class="GdBox">
        <table id = "table" class="table-block"></table>
        <div id="page"></div>
      </div>
    </div>
    <!--  -->
    <!-- 已处理品种  查询条件开始 -->
    <div class="Tab tabTwo">
        <div class="searchBox">
        	<div class="screenTable">
	            <div class="left">
	                <ul>
	                    <li> <span class="name"><span class="fontred">*</span> 采购商</span>
	                        <div class="com">
	                              <select id="pur_allTwo" name="pur_allTwo" class="span10 query_data_all">
									<!-- <option value="">==请选择采购商==</option> -->
									<c:forEach items="${department }" var="list">
									<option <c:if test="${list.sapvendorId==purchaserId}">selected</c:if> value = "${list.sapvendorId }">${list.name }</option>
							</c:forEach>
					</select>
	                        </div>
	                    </li>
	                    <li> <span class="name"><span class="fontred">*</span> 仓 位</span>
	                        <div class="com">
	                         <select class="span10 query_data_allTwo" id="dc_allTwo" name="dcTwo">
	               			 </select>
	                        </div>
	                    </li>
	                    <li>
	                        <span class="name">品种处理情况</span>
	                        <div class="com">
	                        	<div class="isValidTwo">
	                        	
	                        	</div>
	                        </div>
	                    </li>
	                  	<li class="hide">
	                        <span class="name">订单种类</span>
	                        <div class="com">
		                        <div class="AllSelectTwo">
			              	
			              		</div>
	                           <!-- <select class="span10 query_data_allTwo" id="orderTypeTwo">
	                   				 <option value="">全部</option>
	                    			 <option value="0">零售订单</option>
	                                 <option value="5">文教订单</option>
	                                 <option value="10">电商订单</option>
	                                 <option value="15">大中专订单</option>
	                                 <option value="20">团购订单</option>
	                                 <option value="25">馆配订单</option>
	                                <option value="30">活动订书</option>
	                          </select> -->
	                        </div>
	                    </li>
	                    <li class="hide">
	                    	<span class="name">新品订单</span>
				              <div class="choice">
				                 <input type="checkbox" value="" class="all-check" id="all_5" checked="checked"  name="isNewTwo" >
				                <label for="all_5">全部</label>
				                <input type="checkbox" value="Y" class="single-check" id="newTwo_1" name="isNewTwo" checked="checked" >
				                <label for="newTwo_1">新品</label>
				                <input type="checkbox" value="N" class="single-check" id="newTwo_2" name="isNewTwo" checked="checked" >
				                <label for="newTwo_2">非新品</label>
				              </div>
	                    </li>
	                    <li class="hide"> <span class="name">紧急程度</span>
			              <div class="choice">
			                <input type="checkbox" value="" class="all-check" id="all_6" name="urgentFlagTwo" checked="checked">
			                <label for="all_6">全部</label>
			                <input type="checkbox" value="1" class="single-check" id="jdTwo_1" name="urgentFlagTwo" checked="checked">
			                <label for="jdTwo_1">急单</label>
			                <input type="checkbox" value="2" class="single-check" id="jdTwo_2" name="urgentFlagTwo" checked="checked">
			                <label for="jdTwo_2">普通单</label>
			              </div>
			            </li>
	                    <li class="hide">
	                        <span class="name">ISBN</span>
	                        <div class="com gys">
	                            <input type="text"  id="isbnTwo">
	                            <span class="add isbn-btn2"></span>
	                            <span class="del"></span>
	                        </div>
	                    </li>
	                    <li class="hide">
	                        <span class="name">商品名称</span>
	                        <div class="com gys">
	                            <input type="text" id="booktitleTwo">
	                            <span class="add spmc-btn2"></span>
	                            <span class="del"></span>
	                        </div>
	                    </li>
	                    <li class="hide">
	                        <span class="name">订单号码</span>
	                        <div class="com gys">
	                            <input type="text" id="orderCodeTwo" value="${orderCodes }">
	                            <span class="add ddh-btn2"></span>
	                            <span class="del"></span>
	                        </div>
	                    </li>
	                    <li> <span class="name">发送日期</span>
	                        <div class="com time">
	                            <input type="text" id="sendDateStartTwo" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'sendDateEndTwo\')}',dateFmt:'yyyy-MM-dd'})">
	                            <span class="u-icon u-icon-date"></span> <span>至</span>
	                            <input type="text" id="sendDateEndTwo" onclick="WdatePicker({minDate:'#F{$dp.$D(\'sendDateStartTwo\',{H:3});}',dateFmt:'yyyy-MM-dd'})">
	                            <span class="u-icon u-icon-date"></span>
	                            <!-- <div class="time_box">
	                                <a name="send_DateTwo" id="month_1_Two" >一个月内</a>
	                                <a name="send_DateTwo" id="month_1_ago_Two" >一个月前</a>
	                            </div> -->
	                        </div>
	                    </li>
	                    <li> <span class="name">下载标识</span>
	                        <div class="choice">
	                            <input type="checkbox" value="" class="all-check" id="all_4" checked="checked" name="isExportTwo" >
	                            <label for="all_4">全部</label>
	                            <input type="checkbox" value="Y" class="single-check" id="xz_1" name="isExportTwo"  checked="checked" >
	                            <label for="xz_1">已下载</label>
	                            <input type="checkbox" value="N" class="single-check" id="xz_2" name="isExportTwo" checked="checked" >
	                            <label for="xz_2">未下载</label>
	                        </div>
	                    </li>
	                </ul>
	                <div>
	                    <!--全选插件-->
	                </div>
	            </div>
	            <div class="right">
                <div class="btn_back">
                    <a href="javascript:;" id="clickBtnTwo" class="btn btn-primary btn-small">
                    <i class="icon-white icon-search"></i> 搜索</a>
                    <div class="shouListBtn">
                        <i class="unfold left-10 top-4 img"></i>
                        <i class="fontleft left-10 text"> 展 开</i>
                    </div>
                </div>
            </div>
        	</div>
        </div>
        <div class="infoBox">
            <div class="left"> 
                <div class="fontred fontB">
	                <span>收货地址：<span id="paddrTwo"></span></span>
			        <span>收货人：<span id="contactTwo"></span></span> 
			        <span>联系电话：<span id="contactNumberTwo"></span></span>
		        </div>
            </div>
            <div align="right" class="right">
                <a href="javascript:;" class="btn btn-small" id="processedVarietiesExport">导 出</a>
            	<div id="odiv2" style='float: right;margin-left: 10px;text-align: left;'></div>
            </div>
        </div>
        <div class="sortBox">
            <div class="left">
                <span class="name">排序方式：</span>
                <ul class="sortBoxLi1" id="sortBoxTwo">
                  <li class="active" data-value="sendDate" name="click">发送日期↓</li>
                  <li data-value="bookTitle">商品名称↓</li>
                  <li data-value="price">定价↓</li>
                  <li data-value="discountrate">折扣↓</li>
                  <li data-value="purchaseOrderCode">订单号↓</li>
                  <li data-value="orderQty">订数↓</li>
                </ul>
            </div>
        </div>
        <div class="GdBox">
            <table id = "table_a" class="table-block"></table>
            <div id="page_a"></div>
        </div>
    </div>
  </div>
</div>

<!-- Modal弹出开始 -->
<div id="myModal" class="modal-mini hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h5>批量录入</h5>
    </div>
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
                    </select>
                </div>
                <div class="rowBox"> <span class="w110">其余预计发货日期：</span>
                    <div class="time">
                        <input class="date" type="text" onclick="WdatePicker()"  id="batchSendDate" >
                        <span class="u-icon u-icon-date"></span>
                    </div>
                </div>
                <div class="rowBox">
                    <span class="w110">备 注：</span>
                    <input class="big" type="text" id="batchRemark">
                </div>
                <div align="center" class="rowBox top10">
                    <a href="#"  id="submitBatch" class="btn btn-small btn-primary right-10">确 定</a>
                    <a href="#" class="btn btn-small" data-dismiss="modal">取 消</a>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- Modal弹出结束 --> 

<!-- Modal1弹出开始 -->
<div id="myModal1" class="modal-send hide fade modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
    	<button type="button" class="close closeSendGoods"  aria-hidden="true">×</button>
    	<h5>发货信息</h5>
	</div>
	<iframe id="sendGoodsViewIFrame" frameborder=0 width="100%" height="450" marginheight=0 marginwidth=0 scrolling=yes ></iframe>
	<!-- <div align="center" class="bottom-10">
       <a href="javascript:;" id="saveSendGoods" class="btn btn-small btn-primary right-10">确 定</a>
       <a href="javascript:;" class="btn btn-small closeSendGoods" >取 消</a>
   </div> -->
</div>
<!-- Modal1弹出结束 -->

<!-- Modal2弹出开始 -->
<div id="myModal2" class="modal-send hide fade modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h5>回告历史</h5>
    </div>
    <form>
        <div class="modal-body">
            <div class="popup">
                <div class="JGtab">
                    <table id = "table_b" class="table-block"></table>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- Modal2弹出结束 -->


<!-- 解析完成modal  开始 -->
<div id="myModal5" class="modal-mini hide fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
<form id="sendOutForm" method="post">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4>解析成功</h4>
  </div>
  <div class="modal-body">
    <div>导入成功:
    	<span class="fontB" id="successData">
    	</span> 条 
    	<a  class="left40 line downLoadError">错误数据<span id="errorData"></span>条</a>
    	<div id="errorFlag" style="display: none;">
    		
     	</div>
    </div>
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
    <div align="center"> 
    <a  id="downLoadError" style="display: none;" class="btn downLoadError btn-small btn-primary" >下载</a>
    <a id="toSendGoodsFromTemp" class="btn btn-small btn-primary" >确 定</a>
    </div>
    <br>
  </div>
</form>
</div>
<!-- 解析完成modal  结束-->


<!-- ModalReason弹出开始 -->
	<div id="myModalReason" class="modal-mini hide fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h5>关闭发货</h5>
		</div>
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
            				<option value='7'>其他</option>
						</select>
					</div>
					<div align="center" class="rowBox top10">
						<a href="javascript:;" class="btn btn-small btn-primary right-10" id="notGoodsReasonSave" >确 定</a> 
						<a href="javascript:;" class="btn btn-small " data-dismiss="modal"  id="notGoodsReasonCanle">取 消</a>
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
