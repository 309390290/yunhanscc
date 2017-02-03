In.ready('jqueryUi','jqGrid',"AllSelect","queryDataBox","autocomplete",function() {
	fleshOutQueryCondition();
	
	
	if($("#userRoleKey").val()=="true"){
		//查询条件选择供应商
		$('#slect_supplierId').queryDataBox({
			title: "选择供应商",
			url: appPath+'/backto/system/json/getSupperlierList',  //请求地址
			para:{ type:"1" },  //参数1:供应商;0:采购商
			event_type:'click',  //列表事件  默认单击click,双击为dblclick
			reload: false, //二次加载(为true时，无论query属性的值，页面在渲染和点击按钮都执行ajax)
			query: false, //输入框模糊查询开关（true时页面渲染执行ajax，false时点击按钮执行ajax），目前模糊查询只支持供应商模式
			type: null, //类型是供应商还是普通，人名(其他):'person'，供应商：缺省或null，两个类型数据结构不一样
			reqtype: 'POST', //请求方式 'GET','POST'
			selectall: false, //全选开关
//		localdata: [{"supplierId":"S0002200492","supplierName":"江西美术出版社有限责任公司","supplierShortName":"江美"},{"supplierId":"S0002200493","supplierName":"人民邮电出版社","supplierShortName":"人邮"}],  // 开启本地数据，远程请求数据自动失效
			callback: function(name,code){
				//console.log(name)
				//console.log(code);
			} //点击确认按钮后的回调函数
		});
		$("#supplier_del").click(function(){
			$("#search_supplierId").val("");
		});
	}
	//查询条件选择采购商
	$('#slect_purchaserId').queryDataBox({
		title: "选择采购商",
		url: appPath+'/backto/system/json/getSupperlierList',  //请求地址
		para:{ type:"0" },  //参数1:供应商;0:采购商
		event_type:'click',  //列表事件  默认单击click,双击为dblclick
		reload: false, //二次加载(为true时，无论query属性的值，页面在渲染和点击按钮都执行ajax)
		query: false, //输入框模糊查询开关（true时页面渲染执行ajax，false时点击按钮执行ajax），目前模糊查询只支持供应商模式
		type: null, //类型是供应商还是普通，人名(其他):'person'，供应商：缺省或null，两个类型数据结构不一样
		reqtype: 'POST', //请求方式 'GET','POST'
		selectall: false, //全选开关
//		localdata: [{"supplierId":"S0002200492","supplierName":"江西美术出版社有限责任公司","supplierShortName":"江美"},{"supplierId":"S0002200493","supplierName":"人民邮电出版社","supplierShortName":"人邮"}],  // 开启本地数据，远程请求数据自动失效
		callback: function(name,code){
			//console.log(name)
			//console.log(code);
		} //点击确认按钮后的回调函数
	});
	$("#purchaser_del").click(function(){
		$("#search_purchaserId").val("");
	});
	
	//搜索事件
	$("#clickBtn").click(function(){
 		search();
 	});
	
	$("#table").jqGrid({
		url: appPath+'/console/query',
		mtype:'post',
		autowidth : true, // 自适应宽度
		shrinkToFit : true, // 列自适应
		datatype : 'json',
		colNames : ["id","supplierId","供应商","purchaserId","采购商", "lengthRule","sendoutGoodsLength","长度", "sendFormat","格式", "isValid","状态","操作" ],
		colModel : [{name:"id", index:"id",hidden:true},
		            {name:"supplierId", index:"supplierId",hidden:true},
		    {
				name : "supplierName",
				index : "供应商",
				width : '10%',
				align : 'center',
				align:"center",
				formatter:function(value,options,rData){
					return rData.supplierId+"_"+value;
				},
				sortable:false
			}, 
			{name:"purchaserId", index:"purchaserId",hidden:true},
			{
				name : "purchaserName",
				index : "采购商",
				width : '10%',
				align : 'center',
				align:"center",
				formatter:function(value,options,rData){
					return rData.purchaserId+"_"+value;
				},
				sortable:false
			}, 
			{name:"lengthRule", index:"lengthRule",hidden:true},
			{name:"sendoutGoodsLength", index:"sendoutGoodsLength",hidden:true},
			{
				name : "sendoutGoodsLengthName",
				index : "长度",
				width : '10%',
				align : 'center',
				sortable:false,
				formatter:function(value,options,rData){
					if(rData.lengthRule=="A"){
						return "字符长度至少"+rData.sendoutGoodsLength+"位"
					}else if(rData.lengthRule=="B"){
						return "字符长度固定"+rData.sendoutGoodsLength+"位"
					}else{
						return "";
					}
  			  }
			}, 
			{name:"sendFormat", index:"sendFormat",hidden:true},
			{
				name : "sendFormatName",
				index : "格式",
				width : '6%',
				align : 'center',
				formatter:function(value,options,rData){
					if(rData.sendFormat.indexOf("A")!=-1){
						return "任意字符";
					}else{
						var data="";
						if(rData.sendFormat.indexOf("B")!=-1){
							data += "数字、";
						}
						if(rData.sendFormat.indexOf("C")!=-1){
							data += "字母、";
						}
						if(rData.sendFormat.indexOf("D")!=-1){
							data += "中文、";
						}
						if(rData.sendFormat.indexOf("E")!=-1){
							data += "特殊字符、";
						}
						if(data.length>0){
							data = data.substring(0,data.length-1);
						}
						return data;
					}
				},
				sortable:false
			}, 
			{name:"isValid", index:"isValid",hidden:true},
			{
				name : "isValidName",
				index : "状态",
				width : '6%',
				align : 'center',
				formatter:function(value,options,rData){
					if(rData.isValid=="Y"){
						return "启用";
					}
					if(rData.isValid=="N"){
						return "停用";
					}
				},
				sortable:false
			} , {
				name : "cz",
				index : "操作",
				width : '6%',
				align : 'center',
				formatter:function(value,options,rData){
					var qy ="";
					if(rData.isValid=="Y"){
						qy ="<a href='javascript:;' class='btn_white' onclick='start("+rData.id+")'>停用</a>"
					}else{
						qy ="<a href='javascript:;' class='btn_white' onclick='start("+rData.id+")'>启用</a>"
					}
					return "<a href='javascript:;' class='btn_white editRule' click-value='"+rData.id+"'>修改</a>"+qy;
				},
				sortable:false
			}],// the column discription
			postData:{query_id:"getSendRuleConfigBySupplierId",query_type:"JQGRID",rdParseType:"dispersed",reqData:queryData()} ,    //参数里面带callback
			rowNum : 100,
			rowList : [ 50, 100, 200 ],
			multiselect : false, // 显示checkbox选择框
			rownumbers : true, // 显示左边排名列表
			viewrecords:true, //显示总记录数
			pager : '#page',
			height : '100%',
			jsonReader : {
				  repeatitems: false       //这里要设置为false，否则解析不了返回数据
			}, 
			loadBeforeSend:function(){
		           $(this).jqGrid('clearGridData');
			},
			loadComplete:function(){
//	            WG.JGscrollX.Event($(this),'GdBox');
	            //修改按钮事件绑定
	            $(".editRule").click(function(){
	    			var id = $(this).attr("click-value");
	    			editRule(id);
	            });
	        },
	        onPaging : function(pgButton){
				$("#table").jqGrid('setGridParam',{
					datatype:'json',
					postData:{
						query_id:"getSendRuleConfigBySupplierId",
						query_type:"JQGRID",
						reqData:queryData()
						}
				});
			}
		});
		jQuery("#table").jqGrid('navGrid', '#page', {
			edit : false,
			add : false,
			del : false,
			search:false
		});
	//查询数据
 	function search(){
 		var re = queryData();
 		jQuery("#table").jqGrid('setGridParam',{datatype:'json',page:1,postData:{query_type:"JQGRID",reqData:re}}).trigger("reloadGrid");
 	}
 	//查询条件获取
	function queryData(){
		var re="{'groupOp':'AND','rules':[";
		var purchaserId = $("#search_purchaserId").val();
		var supplierId = $("#search_supplierId").val();
		var isValid = $("#search_isValid").val();
		//采购商
		if(null != purchaserId && '' != purchaserId){
			var ids = purchaserId.split(",");
			var purchaserIdData = '';
			for(var i=0;i<ids.length;i++){
				purchaserIdData +=ids[i]+','; 
			}
			purchaserIdData= purchaserIdData.substring(0, purchaserIdData.length-1);
    		var a = "{'field':'purchaserIds','op':'eq','data':'"+ purchaserIdData +"'},";
    		re = re.concat(a);
    	}
		//供应商
		if(null != supplierId && '' != supplierId){
			var ids = supplierId.split(",");
			var supplierIdData = "";
			for(var i=0;i<ids.length;i++){
				supplierIdData +=ids[i]+","; 
			}
			supplierIdData= supplierIdData.substring(0, supplierIdData.length-1);
    		var a = "{'field':'supplierIds','op':'eq','data':'"+ supplierIdData +"' },";
    		re = re.concat(a);
    	}
		//状态
		if(null != isValid && '' != isValid){
    		var a = "{'field':'isValid','op':'eq','data':'"+ isValid +"'},";
    		re = re.concat(a);
    	}
    	//获取排序方式
    	var sortFiled = getOderSort();
    	re = re.concat(sortFiled);
    	
    	//去掉最后一个，符号
    	if(re.length > 30){
			re = re.substr(0,re.length-1);
		}
    	var z= "]}";
    	re = re.concat(z);
    	return re;
	}
	//排序方式获取
	function getOderSort(){
 		var queryData="";
 		var $this = $("#sortBox").find(".active"),
 		orderFile = $this.attr("data-value");
 		sortRule = $this.attr("name");
 		if(sortRule!=null && sortRule!=""){
 			sortRule = "desc";
 		}else{
 			sortRule = "asc";
 		}
 		
 		//排序字段
    	var queryFiled = "{'field':'sortOrderField','op':'eq','data':'"+ orderFile +"'},";
    	queryData = queryData.concat(queryFiled);
    	//排序方式
    	var querySortRule = "{'field':'sortOrderRule','op':'eq','data':'"+ sortRule +"'},";
    	queryData = queryData.concat(querySortRule);
 		return 	queryData;
 	}
	//JQ grid 排序方式 事件绑定
    var sort= $('#sortBox li');
    sort.click(function(){
        sort.removeClass('active');
        $(this).addClass('active');
        sort.each(function(){
            $(this).text($(this).text().replace('↑','↓'));

        });
        if($(this).attr('name')=='click'){
            $(this).text($(this).text().replace('↓','↑'));
            $(this).attr('name','');
        }else {
            $(this).text($(this).text().replace('↑','↓'));
            sort.each(function(){
                $(this).attr('name','');
            });
            $(this).attr('name','click');
        }
        //查询
        search();
    });
	// 新增按钮绑定
	$("#openSave").bind("click",openSave);
	/**
	 * 打开新增发货单规则配置弹出窗口
	 */
	function openSave() {
		$("#supplierName").val("dsafsdfasd");
		$('#sendRuleForm')[0].reset();
		$("#id").val("");
		$("#supplierId").val("");
//		$("h4").html("发货单 规则配置-新增");
//		$('#myModal').modal('show');
		dialog({
	        title: '发货单 规则配置-新增',
	        fixed: true,
	        content:$("#myModal"),
	        okValue: '保存',
	        ok:function(){
	    		if(!$("#supplierId").val()){
	    			layer.alert("请选择供应商！", {icon:0});
	    			return false; 
	    		}
	    		if(!$("#purchaserId").val()){
	    			layer.alert("请选择采购商！", {icon:0});
	    			return false; 
	    		}
	    		if(!$(".AllSelect .AllSelect_title span").html()){
	    			layer.alert("至少选择一种字符！", {icon:0});
	    			return false; 
	    		}
	    		var supplierId = $("#supplierId").val();
	    		var purchaserId = $("#purchaserId").val();
	    		var sendRuleId = $("#id").val();
	    		var lengthRule = $('#lengthRule').val();//长度类型
	    		var ruleLength = $('#ruleLength').val();//长度
	    		var lengthFormat = $('#lengthFormat').val();
	    		var sendFormat="";
	    		if(lengthFormat=="1"){
	    			sendFormat = "A";
	    		}else{
	    			if($(".AllSelect .selectData").val()){
	    				var format = $(".AllSelect .selectData").val();
	    				if(format.indexOf("B")!=-1){
	    					sendFormat+="B";
	    				}
	    				if(format.indexOf("C")!=-1){
	    					sendFormat+="C";
	    				}
	    				if(format.indexOf("D")!=-1){
	    					sendFormat+="D";
	    				}
	    				if(format.indexOf("E")!=-1){
	    					sendFormat+="E";
	    				}
	    			}else{
	    				sendFormat = "BCDE"
	    			}
	    		}
	    		var isValid = $("#isValid").val();
	    		
	    		$.ajax({ 
	    			type:"post",
	    			url: appPath+'/backto/system/json/saveSendRuleConfig',
	    			data: {"lengthRule":lengthRule,"length":ruleLength,"sendFormat":sendFormat,
	    				"sendRuleId":sendRuleId,"purchaserId":purchaserId,"supplierId":supplierId,"isValid":isValid},
	    			dataType: "json",
	    			success: function(data){
	    				if(data.success){
	    					layer.msg("发货单规则保存成功！",{icon : 1});
	    					$("#table").jqGrid().trigger("reloadGrid");
//	    					$('#myModal').modal('hide');//关闭模态窗口
	    					$('#sendRuleForm')[0].reset();
	    				}else{
	    					layer.alert("发货单规则保存失败！"+data.msg, {icon : 2});
	    				}
	    			},
	    			error: function(data){
	    				layer.msg("保存发货单规则发生异常！",{icon:2});
	    			}
	    		});
	    	  
	        },
	        cancelValue: '取消',
	        cancel: function () {}
	    }).showModal();
		$("#ryzf").show();
		$("#xzzf").hide();
		$("#supplierName").removeAttr("disabled");
		$("#purchaserId").removeAttr("disabled");
		$("#purchaserId").val("");
		var dataJson = [
						{id:'all',name:"全部",check:'true'},
						{id:'B',name:"数字",check:"true"},
						{id:'C',name:"字母（不区分大小写）",check:"true"},
						{id:'D',name:"中文",check:"true"},
						{id:'E',name:"特殊字符（除数字\字母\中文外）",check:"true"}
						];
		updateAllSelect(dataJson);
		var userRoleKey = $("#userRoleKey").val();
		if(userRoleKey!="true"){
			$("#supplierId").val($("#search_supplierId").val());
			$("#supplierName").val($("#search_supplierId").attr("title"));
			$("#supplierName").attr("disabled","disabled");
		}
	}
	
	//保存发货单规则配置
	function doSave(){
		if(!$("#supplierId").val()){
			layer.alert("请选择供应商！", {icon:0});
			return; 
		}
		if(!$("#purchaserId").val()){
			layer.alert("请选择采购商！", {icon:0});
			return;
		}
		if(!$(".AllSelect .AllSelect_title span").html()){
			layer.alert("至少选择一种字符！", {icon:0});
			return;
		}
		var supplierId = $("#supplierId").val();
		var purchaserId = $("#purchaserId").val();
		var sendRuleId = $("#id").val();
		var lengthRule = $('#lengthRule').val();//长度类型
		var ruleLength = $('#ruleLength').val();//长度
		var lengthFormat = $('#lengthFormat').val();
		var sendFormat="";
		if(lengthFormat=="1"){
			sendFormat = "A";
		}else{
			if($(".AllSelect .selectData").val()){
				var format = $(".AllSelect .selectData").val();
				if(format.indexOf("B")!=-1){
					sendFormat+="B";
				}
				if(format.indexOf("C")!=-1){
					sendFormat+="C";
				}
				if(format.indexOf("D")!=-1){
					sendFormat+="D";
				}
				if(format.indexOf("E")!=-1){
					sendFormat+="E";
				}
			}else{
				sendFormat = "BCDE"
			}
		}
		var isValid = $("#isValid").val();
		
		$.ajax({ 
			type:"post",
			url: appPath+'/backto/system/json/saveSendRuleConfig',
			data: {"lengthRule":lengthRule,"length":ruleLength,"sendFormat":sendFormat,
				"sendRuleId":sendRuleId,"purchaserId":purchaserId,"supplierId":supplierId,"isValid":isValid},
			dataType: "json",
			success: function(data){
				if(data.success){
					layer.msg("发货单规则保存成功！",{icon : 1});
					$("#table").jqGrid().trigger("reloadGrid");
					$('#myModal').modal('hide');//关闭模态窗口
					$('#sendRuleForm')[0].reset();
				}else{
					layer.alert("发货单规则保存失败！"+data.msg, {icon : 2});
				}
			},
			error: function(data){
				layer.msg("保存发货单规则发生异常！",{icon:2});
			}
		});
	  }
	
	/**
	 * 更新多选框数据
	 */
	function updateAllSelect(data){
		$(".AllSelect").AllSelect({
			id:'lengthFormatId',
			zindex:"2000",
			data:data
		 });
	}
	/**
	 * 修改发货单规则配置弹出窗口
	 */
	function editRule(id){
//		$("h4").html("发货单 规则配置-修改");
//		$('#myModal').modal('show');
		dialog({
	        title: '发货单 规则配置-修改',
	        fixed: true,
	        content:$("#myModal"),
	        okValue: '保存',
	        ok:function(){

	    		if(!$("#supplierId").val()){
	    			layer.alert("请选择供应商！", {icon:0});
	    			return false; 
	    		}
	    		if(!$("#purchaserId").val()){
	    			layer.alert("请选择采购商！", {icon:0});
	    			return false; 
	    		}
	    		if(!$(".AllSelect .AllSelect_title span").html()){
	    			layer.alert("至少选择一种字符！", {icon:0});
	    			return false; 
	    		}
	    		var supplierId = $("#supplierId").val();
	    		var purchaserId = $("#purchaserId").val();
	    		var sendRuleId = $("#id").val();
	    		var lengthRule = $('#lengthRule').val();//长度类型
	    		var ruleLength = $('#ruleLength').val();//长度
	    		var lengthFormat = $('#lengthFormat').val();
	    		var sendFormat="";
	    		if(lengthFormat=="1"){
	    			sendFormat = "A";
	    		}else{
	    			if($(".AllSelect .selectData").val()){
	    				var format = $(".AllSelect .selectData").val();
	    				if(format.indexOf("B")!=-1){
	    					sendFormat+="B";
	    				}
	    				if(format.indexOf("C")!=-1){
	    					sendFormat+="C";
	    				}
	    				if(format.indexOf("D")!=-1){
	    					sendFormat+="D";
	    				}
	    				if(format.indexOf("E")!=-1){
	    					sendFormat+="E";
	    				}
	    			}else{
	    				sendFormat = "BCDE"
	    			}
	    		}
	    		var isValid = $("#isValid").val();
	    		
	    		$.ajax({ 
	    			type:"post",
	    			url: appPath+'/backto/system/json/saveSendRuleConfig',
	    			data: {"lengthRule":lengthRule,"length":ruleLength,"sendFormat":sendFormat,
	    				"sendRuleId":sendRuleId,"purchaserId":purchaserId,"supplierId":supplierId,"isValid":isValid},
	    			dataType: "json",
	    			success: function(data){
	    				if(data.success){
	    					layer.msg("发货单规则保存成功！",{icon : 1});
	    					$("#table").jqGrid().trigger("reloadGrid");
//	    					$('#myModal').modal('hide');//关闭模态窗口
	    					$('#sendRuleForm')[0].reset();
	    				}else{
	    					layer.alert("发货单规则保存失败！"+data.msg, {icon : 2});
	    				}
	    			},
	    			error: function(data){
	    				layer.msg("保存发货单规则发生异常！",{icon:2});
	    			}
	    		});
	        },
	        cancelValue: '取消',
	        cancel: function () {}
	    }).showModal();
		$("#supplierName").attr("disabled","disabled");
		$("#purchaserId").attr("disabled","disabled");
		var selected = $("#table").jqGrid("getRowData",id);
		$("#id").val(selected.id);
		$("#supplierId").val(selected.supplierId);
		$("#supplierName").val(selected.supplierName.split("_")[1]);
		if($("#userRoleKey").val()=="true"){
			getPurchaserEdit(selected.supplierId,"purchaserId",selected.purchaserId);
		}else{
			$("#purchaserId").val(selected.purchaserId);
		}
		$('#lengthRule').val(selected.lengthRule);//长度类型
		$('#ruleLength').val(selected.sendoutGoodsLength);//长度
		var dataJson = [
						{id:'all',name:"全部",check:'false'},
						{id:'B',name:"数字",check:"false"},
						{id:'C',name:"字母（不区分大小写）",check:"false"},
						{id:'D',name:"中文",check:"false"},
						{id:'E',name:"特殊字符（除数字\字母\中文外）",check:"false"}
	                ];
		if(selected.sendFormat=="A"){
			$('#lengthFormat').val(1);
			dataJson[0].check='true';
			dataJson[1].check='true';
			dataJson[2].check='true';
			dataJson[3].check='true';
			dataJson[4].check='true';
			$("#ryzf").show();
			$("#xzzf").hide();
		}else{
			$("#ryzf").hide();
			$("#xzzf").show();
			$('#lengthFormat').val(2);
			var format = selected.sendFormat;
			var sendFormat ="";
			if(format.indexOf("B")!=-1){
				sendFormat+="B,";
				dataJson[1].check='true';
			}
			if(format.indexOf("C")!=-1){
				sendFormat+="C,";
				dataJson[2].check='true';
			}
			if(format.indexOf("D")!=-1){
				sendFormat+="D,";
				dataJson[3].check='true';
			}
			if(format.indexOf("E")!=-1){
				sendFormat+="E,";
				dataJson[4].check='true';
			}
			if(sendFormat.lenght>0){
				sendFormat =sendFormat.substring(0, sendFormat.lenght-1);
			}
		}
		updateAllSelect(dataJson);
		$(".AllSelect .selectData").val(sendFormat);
		$("#isValid").val(selected.isValid);
	}
	/**
	 * 格式长度更改事件绑定
	 */
	$("#lengthFormat").change(function(){
		if($(this).val()=="1"){
			$("#ryzf").show();
			$("#xzzf").hide();
		}else{
			$("#ryzf").hide();
			$("#xzzf").show();
		}
	});
	/**
	 * 弹出框供应商更改事件绑定
	 */
	$("#supplierId").change(function(){
//		var supplierId = $(this).val();
//		if($("#userRoleKey").val()!="true"){
//			return;
//		}
//		if(supplierId){
//			getPurchaserEdit(supplierId,"purchaserId");
//		}
	});
	/**
	 * 查询条件 供应商改变事件绑定
	 */
//	$("#search_supplierId").change(function(){
//		var supplierId = $(this).val();
//		if($("#userRoleKey").val()!="true"){
//			return;
//		}
//		if(supplierId){
//			getPurchaserEdit(supplierId,"search_purchaserId");
//		}
//	});
	/**
	 * 采购商下拉框数据跟新
	 * @param supplierId 供应商ID
	 * @param bqId 更新下拉框的<select>标签id
	 * @param purchaserId 需要选择的数据
	 */
	function getPurchaserEdit(supplierId,bqId,purchaserId){
		$.ajax({ 
			type:"post",
			url: appPath+'/backto/system/json/getPurchaserList',
			data: {"supplierId":supplierId},
			dataType: "json",
			success: function(data){
				if(data.success){
					var html="<option value=''>----请选择----</option>";
					for(var i=0;i<data.obj.length;i++){
						if(data.obj[i].sapvendorId==purchaserId){
							html+="<option value='"+data.obj[i].sapvendorId+"' selected='selected'>"+data.obj[i].name+"</option>"
						}else{
							html+="<option value='"+data.obj[i].sapvendorId+"'>"+data.obj[i].name+"</option>"
						}
					}
					$("#"+bqId).html(html);
				}else{
					layer.alert(data.msg+"！", {icon : 2});
				}
			},
			error: function(data){
				layer.msg("获取采购商失败！",{icon:2});
			}
		});
	}
	
	function fleshOutQueryCondition(){
//		$("#supplierId").autocomplete({
//			autoFocus: true,
//		    source: appPath+'/backto/system/json/showSupplierIdOrName',
//		    select: function(event, ui) {
//		    	var label = ui.item.label;
//		    	var value = ui.item.value;
//		    	sapVendorId.attr("value", value);
//		    }
//		});
		
		$("#supplierName").autocomplete( appPath+'/backto/system/json/showSupplierIdOrName',{
			max: 50,    //列表里的条目数
			minChars: 0,    //自动完成激活之前填入的最小字符
			//width: $inputs.width()+12,     //提示的宽度，溢出隐藏
			scrollHeight: 300,   //提示的高度，溢出显示滚动条
			matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
			autoFill: false,    //自动填充
			formatItem: function(row, i, max) {
				return row.label;
			},
			formatMatch: function(row, i, max) {
				return row.supplierName;
			},
			formatResult: function(row) {
				return row.supplierName;      
			}
		}).result(function(event, row, formatted) {
			var supplierId = row.supplierId;
			$("#supplierId").val(supplierId);
			if($("#userRoleKey").val()!="true"){
				return;
			}
			if(supplierId){
				getPurchaserEdit(supplierId,"purchaserId");
			}
		});	
		
		
		
	}
	
});

//启用、禁用规则
function start(id){
	var selected = $("#table").jqGrid("getRowData",id);
	var isValid = "";
	if(selected.isValid=="Y"){
		isValid="N";
	}else{
		isValid="Y";
	}
	$.ajax({ 
		type:"post",
		url: appPath+'/backto/system/json/saveSendRuleConfig',
		data: {"lengthRule":selected.lengthRule,"length":selected.sendoutGoodsLength,"sendFormat":selected.sendFormat,
			"sendRuleId":selected.id,"purchaserId":selected.purchaserId,"supplierId":selected.supplierId,"isValid":isValid},
		dataType: "json",
		success: function(data){
			if(data.success){
				layer.msg("修改成功！",{icon : 1});
				$("#table").jqGrid().trigger("reloadGrid");
			}else{
				layer.alert("修改失败！"+data.msg, {icon : 2});
			}
		},
		error: function(data){
			layer.msg("保存发货单规则发生异常！",{icon:2});
		}
	});
}