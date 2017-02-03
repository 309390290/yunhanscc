<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/common/publicCsJs.jsp"%>
<script type="text/javascript"
	src="<c:url value='/js/backto/system/toSendRuleConfig.js'/>"></script>
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
				<li><a href="JavaScript:;"> <span>发货单规则配置</span>
				</a></li>
			</ul>
		</div>
	</div>
	<!-- 面包导航结束 -->
	<!-- 中间内容开始 -->
	<div class="bodyBox">
		<div class="contentBox">
			<div class="Tab tabOne">
				<div class="searchBox">
					<div class="left">
						<ul>
							<li>
								<span class="name">采购商</span>
								<div class="com">
									<%-- <select id="search_purchaserId" class="span10 query_data_all">
										<c:if test="${purchasers.size()>1}">
											<option value="">全部</option>
										</c:if>
										<c:forEach items="${purchasers}" var="list">
											<option value="${list.sapvendorId }">${list.sapvendorId }_${list.name}</option>
										</c:forEach>
									</select> --%>
									</div>
									<div class="com gys">
										<input type="text" id="search_purchaserId" readonly="readonly"/>
										<span class="add orderCode-btn" id="slect_purchaserId"></span>
										<span class="del" id="purchaser_del"></span>
									</div>
								</li>
							<li>
								<span class="name"> 供应商 </span>
								<div class="com">
									<%-- <select id="search_supplierId" class="span10 query_data_all" >
										<c:if test="${suppliers.size()>1}">
											<option value="">全部</option>
										</c:if>
										<c:forEach items="${suppliers}" var="list">
											<option value="${list.sapvendorId }">${list.sapvendorId }_${list.name}</option>
										</c:forEach>
									</select> --%>
								</div>
								<div class="com gys">
									<input type="text" id="search_supplierId" readonly="readonly" 
										<c:if test="${isAdminRole!=true}">value="${suppliers.get(0).sapvendorId}" title="${suppliers.get(0).name}"</c:if>
									/>
									<span class="add orderCode-btn" id="slect_supplierId"></span>
									<span class="del" id="supplier_del"></span>
								</div>
							</li>
							<li>
								<span class="name"> 状态 </span>
								<div class="com">
									<select id="search_isValid"  class="span10 query_data_all">
										<option value="">全部</option>
										<option value="Y">启用</option>
										<option value="N">禁用</option>
									</select>
								</div></li>
						</ul>
						<div>
							<!--全选插件-->
						</div>
					</div>
					<div class="right" style="height:44px;">
						<div class="btn_back">
							<a href="javascript:;" id="clickBtn"class="btn btn-primary btn-small">
								<i class="icon-white icon-search"></i> 搜索
							</a>
						</div>
					</div>
				</div>
				<div class="sortBox">
					<div class="left">
						<span class="name">排序方式：</span>
						<ul id="sortBox">
							<li class="active" data-value="purchaserId" name="click">采购商↓</li>
							<li data-value="supplierId">供应商↓</li>
							<li data-value="isValid">状态↓</li>
						</ul>
					</div>
					<div class="pull-right">
						<a href="javascript:;" id="openSave" role="button"
							class="btn btn-small left10" data-toggle="modal">新增</a>
					</div>
				</div>
				<div class="GdBox">
					<table id="table" class="table-block"></table>
					<div id="page"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- 中间内容结束 -->
	<!-- 新增 发货单规则配置 模态窗口Modal -->
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header" style="height: 30px;">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h4>发货单 规则配置-新增</h4>
		</div>
		<div class="modal-body">
				<input type="hidden" id="userRoleKey" value="${isAdminRole}">
			<form action="" id="sendRuleForm" method="post" onsubmit="return validateForm()">
				<!--用于判断是doSave中调用哪个url -->
				<input type="hidden" name="urlName" id="urlName">
				<input type="hidden" id="id" name="id">
				<table class="tab100" cellSpacing=1 cellPadding=4 width="100%"
					align=center border=0>
					<tr>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="right" class="tabtd25">供应商：</td>
						<td align="left" class="tabtd75">
							 <%-- <select id="supplierId" name="supplierId" class="pageCom">
								<option value="">---请选择---</option>
								<c:forEach var="list" items="${suppliers}">
									<option value="${list.sapvendorId}" >${list.name}</option>
								</c:forEach>
							</select> --%>
							<input type="hidden" name="supplierId" id="supplierId" />
							<input type="text"  id="supplierName" class="pageCom"  style="width: 240px;"/>
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td align="right" class="tabtd25">采购商 ：</td>
						<td align="left" class="tabtd75">
							<select id="purchaserId" name="purchaserId" class="pageCom" >
								<option value="">---请选择---</option>
								<c:forEach var="list" items="${purchasers}">
									<option value="${list.sapvendorId}">${list.name}</option>
								</c:forEach>
							</select>
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td align="right" class="tabtd25">字符长度：</td>
						<td align="left" class="tabtd75">
							<div style="float: left;">
							<select id="lengthRule"  class="pageCom" style="width: 80px;">
								<option value="A">至少</option>
								<option value="B">固定</option>
							</select>
							</div>
							<div style="float: left;margin-left: 20px;">
							<select class="pageCom" id="ruleLength" style="width: 160px;">
								<c:forEach var="x" begin="1" end="20" step="1">
									<option value="${x }"
										<c:if test="${x==sendRuleConfigDo.sendoutGoodsLength && sendRuleConfigDo.lengthRule=='B'  }">selected</c:if>>
										${x}位</option>
								</c:forEach>
							</select>
							</div>
						</td>
					</tr>
					<tr>
						<td align="right" class="tabtd25">格式长度：</td>
						<td align="left" class="tabtd75">
							<div style="float: left;">
							<select id="lengthFormat" class="pageCom" style="width: 80px;">
								<option value="1">任意字符</option>
								<option value="2">选择包含字符</option>
							</select>
							</div>
							<div style="width: 160px;margin-left: 20px;float: left;">
								<select id="ryzf" disabled="disabled" class="pageCom" style="width: 160px;">
									<option>全部</option>
								</select>
				              	<div class="AllSelect" id="xzzf">
				              	</div>
				              </div>
						</td>
					</tr>
					<tr>
						<td align="right" class="tabtd25">状态：</td>
						<td align="left" class="tabtd75">
							<select id="isValid" class="pageCom">
								<option value="Y">启用</option>
								<option value="N">停用</option>
						</select></td>
					</tr>
				</table>
				<br>
			</form>
		</div>
		<div class="modal-header"
			style="height: 25px;text-align: center;background-color: #f5f5f5;">
			<a id="doSave" class="btn btn-small btn-primary">保 存</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a data-dismiss="modal" class="btn btn-small">取 消</a>
		</div>
	</div>
	<!-- end 新增  发货单规则配置  模态窗口Modal -->


	<!-- 尾文件开始 -->
	<%@ include file="/common/footer.jsp"%>
	<!-- 尾文件结束 -->
</body>
</html>
