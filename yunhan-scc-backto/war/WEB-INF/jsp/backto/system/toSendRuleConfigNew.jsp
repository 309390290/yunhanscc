<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/common/publicCsJsNew.jsp"%>
<link href="<c:url value='/css/backto/toSendRuleConfigNew.css'/>" rel="stylesheet" >
<script type="text/javascript" src="<c:url value='/js/backto/system/toSendRuleConfigNew.js'/>"></script>
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
	<!-- 中间内容结束开始 -->
<div class="bodyBox">
			<div class="Tab" id="TabA">
				<div class="screenBox">
					<div class="screenTable">
						<div class="left">
							<ul>
					            <li>
									<span class="name">采购商</span>
									<div class="com gys">
										<input type="text" id="search_purchaserId" readonly="readonly"/>
										<span class="add orderCode-btn" id="slect_purchaserId"></span>
										<span class="del" id="purchaser_del"></span>
									</div>
								</li>
					            <li>
									<span class="name"> 供应商 </span>
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
									</div>
								</li>
							</ul>
						</div>
						<div class="right">
		                    <div class="btn">
		                        <span class="icon-search" id="clickBtn"><i></i>搜 索</span>
		                    </div>
		                </div>
					</div>
				</div>
				<div class="gridBox">
	                <div class="sortBox">
	                	<ul id="sortBox">
						  <li class="active" data-value="purchaserId" name="click">采购商↓</li>
		                  <li data-value="supplierId">供应商↓</li>
						  <li data-value="isValid">状态↓</li>
	                    </ul>
	                    <div class="orderBox">
	                        <div class="com del">
	                        	<span class="span" id="openSave">新增</span>
                        	</div>
	                    </div>
	                </div>
	              
                <div class="jqGrid">
                	<table id="table"></table>
					<div id="page"></div>
                </div>
           	</div>
		</div>
</div>
<!-- 中间内容结束 --> 
<!-- 新增 发货单规则配置 模态窗口Modal -->
<div id="myModal" style="display:none">
	<div class="modal-body">
		<input type="hidden" id="userRoleKey" value="${isAdminRole}">
		<form action="" id="sendRuleForm" method="post" onsubmit="return validateForm()">
			<!--用于判断是doSave中调用哪个url -->
			<input type="hidden" name="urlName" id="urlName">
			<input type="hidden" id="id" name="id">
			<div class="addBox">
				<ul>
					<li>
						<span class="name">供应商：</span>
						<div class="right">
							<input type="hidden" name="supplierId" id="supplierId" />
							<input type="text"  id="supplierName"/>
							<font color="red">*</font>
						</div>
					</li>
					<li>
						<span class="name">采购商：</span>
						<div class="right">
							<select id="purchaserId" name="purchaserId" class="purchaserId" >
								<option value="">---请选择---</option>
								<c:forEach var="list" items="${purchasers}">
									<option value="${list.sapvendorId}">${list.name}</option>
								</c:forEach>
							</select>
							<font color="red">*</font>
						</div>
					</li>
					<li>
						<span class="name">字符长度：</span>
						<div class="right">
							<select id="lengthRule">
								<option value="A">至少</option>
								<option value="B">固定</option>
							</select>
							<select id="ruleLength">
								<c:forEach var="x" begin="1" end="20" step="1">
									<option value="${x }"
										<c:if test="${x==sendRuleConfigDo.sendoutGoodsLength && sendRuleConfigDo.lengthRule=='B'  }">selected</c:if>>
										${x}位</option>
								</c:forEach>
							</select>
						</div>
					</li>
					<li>
						<span class="name">格式长度：</span>
						<div class="right">
							<select id="lengthFormat">
								<option value="1">任意字符</option>
								<option value="2">选择包含字符</option>
							</select>
							<div class="choiceBox">
								<select id="ryzf" class="multTyp" disabled="disabled">
										<option>全部</option>
								</select>
				              	<div class="AllSelect multTyp" id="xzzf"></div>
							</div>
						</div>
					</li>
					<li>
						<span class="name">状 态：</span>
						<div class="right">
							<select id="isValid">
								<option value="Y">启用</option>
								<option value="N">停用</option>
							</select>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>
<!-- end 新增  发货单规则配置  模态窗口Modal -->
	<!-- 尾文件开始 -->
	<%@ include file="/common/footer.jsp"%>
	<!-- 尾文件结束 -->
</body>
</html>
