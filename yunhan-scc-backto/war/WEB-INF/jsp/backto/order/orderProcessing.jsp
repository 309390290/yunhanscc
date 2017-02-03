<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 
 	订单处理>>>订单方式
  	add by wangtao
  	2016年7月7日13:33:32
 -->
<!DOCTYPE HTML>
<html>
  <head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<%@ include file="/common/publicCsJs.jsp"%>
	<script type="text/javascript" src="<c:url value='/js/backto/order/orderProcessing.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/backto/order/orderImport.js'/>"></script>
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
      <li> <a href="JavaScript:;"> <span>订单方式</span> </a> </li>
    </ul>
  </div>
</div>
<!-- 面包导航结束 --> 

<!-- 中间内容结束开始 -->
<div class="bodyBox">
  <div class="contentBox">
    <div class="Tab tabOne">
      <div class="searchBox">
       	<div class="screenTable">
	        <div class="left">
	        	<input type="hidden" id="sendGoodsType" value="${sendGoodsType }">
	        	<input type="hidden" id="wareHouse" value="${wareHouse}">
	          <ul>
	            <li> <span class="name"><span class="fontred">*</span> 采购商</span>
	              <div class="com">
	              	<select id="pur_all" name="pur_all" class="span10 query_data_all">
	<!-- 					<option value="">==请选择采购商==</option> -->
							<c:forEach items="${department }" var="list">
								<option value = "${list.sapvendorId }"  <c:if test="${list.sapvendorId == purchaserId }">selected="selected"</c:if>>${list.name }</option>
							</c:forEach>
					</select>
	                <!-- <select>
	                  <option>重庆文轩</option>
	                  <option>新华文轩</option>
	                  <option>深圳文轩</option>
	                </select> -->
	              </div>
	            </li>
	            <li> <span class="name"><span class="fontred">*</span> 仓 位</span>
	              <div class="com">
	              	<select class="span10 query_data_all" id="dc_all" name="dc">
	                </select>
	                <!-- <select>
	                  <option>01仓</option>
	                  <option>02仓</option>
	                  <option>其他仓</option>
	                  <option>全部</option>
	                </select> -->
	              </div>
	            </li>
	            <li> <span class="name">新品订单</span>
	              <div class="choice">
	                <input type="checkbox" value="" class="all-check" id="all_1" name="isNew" checked="checked">
	                <label for="all_1">全部</label>
	                <input type="checkbox" value="Y" class="single-check" id="new_1" name="isNew" checked="checked">
	                <label for="new_1">新品</label>
	                <input type="checkbox" value="N" class="single-check" id="new_2" name="isNew" checked="checked">
	                <label for="new_2">非新品</label>
	              </div>
	            </li>
	            <li class="block"> <span class="name">订单号码</span>
	              <div class="com gys">
	                <input type="text" id="orderCode">
	                <span class="add orderCode-btn"></span>
	                <span class="del"></span>
	              </div>
	            </li>
	            <li class="block"> <span class="name">订单种类</span>
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
	            <li class="block"> <span class="name">紧急程度</span>
	              <div class="choice">
	                <input type="checkbox" value="" class="all-check" id="all_2" name="urgentFlag" checked="checked">
	                <label for="all_2">全部</label>
	                <input type="checkbox" value="1" class="single-check" id="jd_1" name="urgentFlag" checked="checked">
	                <label for="jd_1">急单</label>
	                <input type="checkbox" value="2" class="single-check" id="jd_2" name="urgentFlag" checked="checked">
	                <label for="jd_2">普通单</label>
	              </div>
	            </li>
	            <li class="block"> <span class="name">发送日期</span>
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
	            <li class="block"> <span class="name">是否下载</span>
	              <div class="com">
	              	<select class="span10 query_data_all" id="is_export" name="is_export">
	                    <option value="">全部</option>
	                    <option value="Y">已下载</option>
	                    <option value="N">未下载</option>
	                </select>
	                </div>
	            </li>
	            <li class="block"> <span class="name">订单处理情况</span>
	              <div class="com">
	              	<div class="AllSelectDDCL">
	              	
	              	</div>
	              </div>
	              <!-- <div class="choice">
	                  <input type="checkbox" value="" class="all-check" id="all_3" name="isDeal">
	                  <label for="all_3">全部</label>
	                  <input type="checkbox" value="Y" class="single-check" id="cl_1" name="isDeal"> 
	                  <label for="cl_1">已处理</label>
	                  <input type="checkbox" value="N" class="single-check" id="cl_2" name="isDeal" checked="checked">
	                  <label for="cl_2">待处理</label>
	                  <input type="checkbox" value="A" class="single-check" id="cl_3" name="isDeal" checked="checked">
	                  <label for="cl_3">部分处理</label>
	              </div>-->
	            </li>
	          </ul>
	          <div> 
	            <!--全选插件--> 
	          </div>
	        </div>
	        <div class="right">
            <div class="btn_back">
                <a href="javascript:;" id="clickBtn" class="btn btn-primary btn-small">
                <i class="icon-white icon-search"></i> 搜索</a>
                <div class="shouListBtn">
                    <i class="fold left-10 top-4 img"></i>
                    <i class="fontleft left-10 text"> 收  起</i>
                </div>
            </div>
        </div>
      	</div>
      </div>
      <div class="infoBox">
	      <div class="left"> 
		      <div class="fontred fontB">
		      	<span>收货地址：<span id="paddr"></span></span>
		       	<span>收货人：<span id="contact"></span></span> 
		       	<span>联系电话：<span id="contactNumber"></span></span>
		       </div>
	       </div>
          <div align="right" class="right"> 
          		<div align="left" class="btn-group">
          			  <a id="importSendBtn"  class="btn  btn-small" >导入</a>
		              <a class="btn btn-small dropdown-toggle" data-toggle="dropdown" href="javascript:;">导 出<span class="caret"></span></a>
		                <ul class="dropdown-menu">
		                   <li><a href="javascript:;" id="exportOrderSingle"  title="每张订单生成一张EXCEL">逐单导出</a></li>
		                   <li><a href="javascript:;" id="exportOrderMultiple" title="订单合并生成EXCEL">合单导出</a></li> 
		                </ul>
            	</div>
          		<a href="javascript:;" id="orderItems" class="btn btn-small">多订单处理</a> 
          		<div id="odiv" style='float: right;margin-left: 10px;text-align: left;'></div>
          	</div>
        </div>
      <div class="sortBox">
          <div class="left">
              <span class="name">排序方式：</span>
              <ul id="sortBox">
                 <!--  <li class="active">综合排序</li> -->
                  <li class="active" data-value="sendDate" name="click">发送日期↓</li>
                  <li data-value="urgentFlag">紧急程度↓</li>
                  <li data-value="orderType">订单种类↓</li>
                  <li data-value="purchaseOrderCode">订单号↓</li>
              </ul>
          </div>
      </div>
      <div class="GdBox">
            <table id = "table" class="table-block"></table>
            <div id="page"></div>
        </div>
    </div>
  </div>
</div>
<!-- 中间内容结束 --> 
   
   
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
    <input type="hidden"   name="source" value="detail">
    <div align="center"> 
    <a  id="downLoadError" style="display: none;" class="btn downLoadError btn-small btn-primary" >下载</a>
    <a id="toSendGoodsFromTemp" class="btn btn-small btn-primary" >确 定</a>
    </div>
    <br>
  </div>
</form>
</div>
<!-- 解析完成modal  结束-->

 <input type="hidden" id="orderTypeRe" name="orderTypeRe" value="${orderType}">
 <input type="hidden" id="isDealRe" name="isDealRe" value="${isDeal}">
<!-- 尾文件开始 -->

<%@ include file="/common/footer.jsp" %>
<!-- 尾文件结束 --> 
   
  </body>
</html>
