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
<link href="<c:url value='/css/backto/queryModifyNew.css'/>" rel="stylesheet" >
<script type="text/javascript" src="<c:url value='/js/backto/sendQuery/queryModifyNew.js'/>"></script>
</head>

<body>
	<!-- 头文件开始 -->
	<%@ include file="/common/menu.jsp"%>
	<!-- 头文件结束 -->
	<!-- 面包导航开始 -->
	<div class="headerBox">
		<div class="active_com">
			<span class="position"><b class="map-marker"></b>当前位置：</span>
			<ul class="ul">
				<li><a href="JavaScript:;"> <span>首 页</span>
				</a> <b> > </b></li>
				<li><a href="JavaScript:;"> <span>发货单维护</span>
				</a> <b> > </b></li>
				<li><a href="JavaScript:;"> <span>发货单查询及修改</span>
				</a></li>
			</ul>
		</div>
	</div>
	<!-- 面包导航结束 -->
	<!-- 中间内容结束开始 -->
	<div class="bodyContentBox bodyBox">
			<!-- 待发送开始 -->
			<div class="Tab" id="TabA">
				<div class="screenBox">
					<div class="screenTable">
						<div class="left">
							<ul>
								<li>
									<span class="name">发货单号</span>
									<div class="com gys">
										<input type="text" id="sendoutGoodsCode" value=""> 
										<span class="add send-btn"></span> <span class="del"></span>
									</div>
								</li>
								<li>
									<span class="name">采购商</span>
									<div class="com">
										<select id="purchaserId" class="purchaserId">
											<c:forEach items="${department }" var="list">
												<option <c:if test="${list.sapvendorId==purchaserId}">selected</c:if> value = "${list.sapvendorId }">${list.name }</option>
											</c:forEach>
										</select>
									</div>
								</li>
								<li>
									<span class="name">发货单状态</span>
									<div class="com">
										<select id="sendoutStatus">
											<option value="5">未收货</option>
											<option value="10">部分收货</option>
											<option value="15">全部收货</option>
											<option selected="selected" value="">全部</option>
										</select>
									</div>
								</li>
								<li>
									<span class="name">订单号</span>
									<div class="com gys">
										<input type="text" id="purchaseOrderCode"> 
										<span class="add order-btn"></span> <span class="del"></span>
									</div>
								</li>
								<li>
									<span class="name">制单日期</span>
									<div class="com time">
										<input type="text" onClick="WdatePicker();" id="addDateFrom">
										<b></b>
										<span></span>
										 <input type="text" onClick="WdatePicker();" id="addDateTo">
										<b></b>
									</div>
								</li>
								<li>
									<span class="name">主发单标识</span>
									<div class="com ddh">
										<input type="checkBox" id="isInitiative" value="Y">
										<label for="isInitiative">主发单</label>
									</div>
								</li>
								<li class="hide">
									<span class="name">发货日期</span>
									<div class="com time">
										<input type="text" onClick="WdatePicker();" id="sendoutDateFrom">
										<b></b>
										<span></span>
										 <input type="text" onClick="WdatePicker();" id="sendoutDateTo">
										<b></b>
										<div class="time_box">
											<div class="mes" style="height: 18px;"><i class="badge" style="display: block; "></i></div>
						                    <a name="sendDate" id="monthOne">1个月内</a>
						                    <a name="sendDate" id="monthOneAgo">1个月前</a>
						                </div>
									</div>
								</li>
								
								<li class="hide"> 
					            	<span class="name">仓 位</span>
					                <div class="com">
					              		<select id="dc_all" name="dc">
					                	</select>
					                </div>
					            </li>
							</ul>
							<div>
								<!--全选插件-->
							</div>
						</div>
						<div class="right">
		                    <div class="btn">
		                        <span class="span search-btn"><i></i>搜 索</span>
		                        <span id="open" class="span2"><i class="open"></i><b>展 开</b></span>
		                    </div>
		                </div>
					</div>
				</div>
				<div class="gridBox">
	                <div class="sortBox">
	                    <div class="orderBox">
	                        <div class="com del">
                           		<span class="span export-btn">导 出</span>
                           		<span class="span del-btn">删 除</span>
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
	<!-- 中间内容结束 -->

	<!-- 尾文件开始 -->
	<%@ include file="/common/footer.jsp"%>
	<!-- 尾文件结束 -->
	<!-- JS -->
	<form id="exportPost" method="post"></form>
</body>
</html>
