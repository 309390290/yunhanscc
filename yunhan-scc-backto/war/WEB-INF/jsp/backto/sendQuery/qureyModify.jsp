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
<script type="text/javascript"
	src="<c:url value='/js/backto/sendQuery/queryModify.js'/>"></script>
</head>

<body>
	<!-- 头文件开始 -->
	<%@ include file="/common/menu.jsp"%>
	<!-- 头文件结束 -->

	<!-- 中间内容结束开始 -->
	<div class="dh_back">
		<div class="l_r_t_b20 font12">
			<img src="<c:url value='/img/icon_navigation.png'/>" width="16"
				height="16">&nbsp;&nbsp;当前位置：首 页&nbsp;&nbsp; <img
				src="<c:url value='/img/arrow_red.gif'/>" width="7" height="5">&nbsp;&nbsp;
			发货单维护&nbsp;&nbsp; <img src="<c:url value='/img/arrow_red.gif'/>"
				width="7" height="5">&nbsp;&nbsp; 发货单查询及修改
		</div>
	</div>
	<form class="form-horizontal" style="padding-bottom: 40px;">
		<div class="container_center"  style="margin-bottom:0">
			<div class="row-fluid top6">
				<div class="span4">
					<div class="control-group1">
						<label class="control-label3">发货单号：</label>
						<div class="controls3">
							<div class="span12">
								<input class="span8" type="text" id="sendoutGoodsCode">
								<a href="javascript:void(0);" role="button"
									class="btn btn-small send-btn" data-toggle="modal"><i
									class="icon-search"></i></a>
								<button class="clear-btn btn btn-small" type="button">
									<i class="icon-trash"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="span4">
					<div class="control-group1">
						<label class="control-label3">采购商：</label>
						<div class="controls3">
							<div class="span12">
								<select id="purchaserId" name="purchaserId" class="span10 query_data_all">
										<c:forEach items="${department }" var="list">
											<option <c:if test="${list.sapvendorId==purchaserId}">selected</c:if> value = "${list.sapvendorId }">${list.name }</option>
										</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="span4">
					<div class="control-group1">
						<label class="control-label1">发货单状态：</label>
						<div class="controls1">
							<div class="span12">
								<select class="span10" id="sendoutStatus">
									<option value="5">未收货</option>
									<option value="10">部分收货</option>
									<option value="15">全部收货</option>
									<option selected="selected" value="">全部</option>
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid top6">
				<div class="span4">
					<div class="control-group1">
						<label class="control-label3">订单号码：</label>
						<div class="controls3">
							<div class="span12">
								<input class="span8" type="text" id="purchaseOrderCode">
								<a href="javascript:void(0);" role="button"
									class="btn btn-small order-btn" data-toggle="modal"><i
									class="icon-search"></i></a>
								<button class="clear-btn btn btn-small" type="button">
									<i class="icon-trash"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="span4">
					<div class="control-group1">
						<label class="control-label3">制单日期：</label>
						<div class="controls3">
							<div class="span12">
								<input class="span5 Wdate" onfocus="WdatePicker()" type="text"
									id="addDateFrom"> 到 <input class="span5 Wdate"
									onfocus="WdatePicker()" type="text" id="addDateTo">
							</div>
						</div>
					</div>
				</div>
				<div class="span4">
					<div class="control-group1">
						<label class="control-label1">主发单标识：</label>
						<div class="controls1 top3">
							<div class="span12">
								<input name="" type="checkBox" value="Y" id="isInitiative">
								主发单
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid top6">
				<div class="span4">
					<div class="control-group1">
						<label class="control-label3">发货日期：</label>
						<div class="controls3">
							<div class="span12">
								<input class="span5 Wdate" onfocus="WdatePicker()" type="text"
									id="sendoutDateFrom"> 到 <input class="span5 Wdate"
									onfocus="WdatePicker()" type="text" id="sendoutDateTo">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="bs-docs-example">
				<div class="row-fluid">
					<div class="span6">
						<span class="fontB">【<a class="line" name="sendDate"
							id="weekOne">近1周</a>】
						</span> <span class="fontB left40">【<a class="line"
							name="sendDate" id="weekTwo">近2周</a>】
						</span> <span class="fontB left40">【<a class="line"
							name="sendDate" id="monthOne">1个月前</a>】
						</span>
					</div>
					<div class="span6">
						<div class="pull-right">
							<a href="javaScript:void(0);" class="btn btn-small search-btn">查
								询</a> <a href="javaScript:void(0);"
								class="btn btn-small left10 export-btn" title="导出已勾选的发货单">导
								出</a> <a href="javaScript:void(0);"
								class="btn btn-small left10 modify-btn" title="修改勾选的发货单">修 改</a>
							<a href="javaScript:void(0);"
								class="btn btn-small left10 del-btn" title="删除勾选的发货单">删 除</a>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid top6">
				<table id="table_list" class="table-block">
				</table>
				<div id="page"></div>
			</div>
		</div>
	</form>

	<!-- 中间内容结束 -->

	<!-- 尾文件开始 -->
	<%@ include file="/common/footer.jsp"%>
	<!-- 尾文件结束 -->
	<!-- JS -->
	<form id="exportPost" method="post"></form>
</body>
</html>
