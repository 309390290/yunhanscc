/**
 * 待办任务js 
 */

In.ready('jqGrid',function() {
	//默认加载第一个仓位
//	loadUnDeal(0);
	
	/**
	 * 加载某一仓位待处理品种列表
	 */
	function loadUnDeal(index){
		var purchaserId = $("#purchaserId").val(),
		userCode = $("#userCode").val(),
		wareHouse = $("#wareHouse_"+index).val();
		var re="{'groupOp':'AND','rules':[";
		var a = "{'field':'purchaserId','op':'eq','data':'"+ purchaserId +"'},";
		re = re.concat(a);
		var a = "{'field':'userCode','op':'eq','data':'"+ userCode +"'},";
		re = re.concat(a);
		if(wareHouse!='QITA'){
			var a = "{'field':'wareHouses','op':'eq','data':'"+ wareHouse +"'},";
			re = re.concat(a);
		}
		var a = "{'field':'wareHouse','op':'eq','data':'"+ wareHouse +"'}";
		re = re.concat(a);
		var z= "]}";
    	re = re.concat(z);
    	return re;
	}
	function loadUnDealOrder(index){
		var purchaserId = $("#purchaserId").val(),
		userCode = $("#userCode").val(),
		wareHouse = $("#wareHouseOrder_"+index).val();
		var re="{'groupOp':'AND','rules':[";
		var a = "{'field':'purchaserId','op':'eq','data':'"+ purchaserId +"'},";
		re = re.concat(a);
		var a = "{'field':'userCode','op':'eq','data':'"+ userCode +"'},";
		re = re.concat(a);
		if(wareHouse!='QITA'){
			var a = "{'field':'wareHouses','op':'eq','data':'"+ wareHouse +"'},";
			re = re.concat(a);
		}
		var a = "{'field':'wareHouse','op':'eq','data':'"+ wareHouse +"'}";
		re = re.concat(a);
		var z= "]}";
    	re = re.concat(z);
    	return re;
	}
	
	//品种方式仓位切换
	var tabActive=$('#headActive li'); //tab切换
	//tab切换
	tabActive.click(function(){
	    tabActive.removeClass('active');
	    $(this).addClass('active');
		jQuery("#table").jqGrid('setGridParam',{datatype:'json',page:1,postData:{query_type:"JQGRID",reqData:loadUnDeal($(this).val())}}).trigger("reloadGrid");
	});
	
	//订单方式仓位切换
	var tabActiveb=$('#headActiveb li'); //tab切换
	//tab切换
	tabActiveb.click(function(){
	    tabActiveb.removeClass('active');
	    $(this).addClass('active');
		jQuery("#tableb").jqGrid('setGridParam',{datatype:'json',page:1,postData:{query_type:"JQGRID",reqData:loadUnDealOrder($(this).val())}}).trigger("reloadGrid");
	});
	
	
	
	/**
	 * 切换仓位
	 */
	/*$("#dc_all li").click(function(){
		//获取index
		$("#dc_all li").removeClass("active");
		$(this).addClass("active");
		var index = $(this).attr("value");
		//加载待处理数据
		loadUnDeal(index);
	});*/
	
	/**
	 * 跳转到订单品种处理页面
	 */
	 $("#goOrderItems").click(function(){
		 var purchaserId=$("#purchaserId").val();
		 var closeOrderCodes=$("#closeOrderCodes").val();
		 window.location.href=appPath+"/backto/order/page/toOrderItems?purchaserId="+purchaserId+"&isDeal=Y&isValid=N&orderCodes="+closeOrderCodes;
	 });
	 $("#goOrder").click(function(){
		 var purchaserId=$("#purchaserId").val();
		 var statisticalDimension = $("#statisticalDimension").val();
		 if(statisticalDimension=='A'){
			 window.location.href=appPath+"/backto/order/page/toOrderItems?purchaserId="+purchaserId;
		 }else{
			 window.location.href=appPath+"/backto/order/page/toOrderProcessing?purchaserId="+purchaserId+"&isDeal=N,A";
		 }
	 });
	 
	 $("#all_pending").click(function(){
		 toOrder(null,null,0);
		}
	 );
	 
	 $("#table").jqGrid({
			url: appPath+'/console/query',
			mtype:'post',
			autowidth : true, // 自适应宽度
			shrinkToFit : true, // 列自适应
			datatype : 'json',
			colNames : ["其中","未回告","预计可发","无货待加印","新书待入库"],
			colModel : [
			    {name:"orderTypeStr", index:"orderTypeStr",width : '6%',sortable: false,align : 'center'},
			    {name:"unResponselCount", index:"unResponselCount",width : '6%',sortable: false,align : 'center',
			    	formatter:function(value,options,rData){
			    		if(value>0){
			    			return "<a href='javascript:;' onclick='toOrder("+rData.orderType+",\""+rData.wareHouse+"\",1);'>"+value+"</a>";
			    		}else{
			    			return value;
			    		}
	            	}
			    },
			    {name:"expectedDeliveryCount", index:"expectedDeliveryCount",width : '6%',sortable: false,align : 'center',
			    	formatter:function(value,options,rData){
			    		if(value>0){
			    			return "<a href='javascript:;' onclick='toOrder("+rData.orderType+",\""+rData.wareHouse+"\",2);'>"+value+"</a>";
			    		}else{
			    			return value;
			    		}
	            	}
			    },
			    {name:"tempNoGoodsCount", index:"tempNoGoodsCount",width : '6%',sortable: false,align : 'center',
			    	formatter:function(value,options,rData){
			    		if(value>0){
			    			return "<a href='javascript:;' onclick='toOrder("+rData.orderType+",\""+rData.wareHouse+"\",3);'>"+value+"</a>";
			    		}else{
			    			return value;
			    		}
	            	}
			    },
			    {name:"newWaitWarehousingCount", index:"newWaitWarehousingCount",width : '6%',sortable: false,align : 'center',
			    	formatter:function(value,options,rData){
			    		if(value>0){
			    			return "<a href='javascript:;' onclick='toOrder("+rData.orderType+",\""+rData.wareHouse+"\",4);'>"+value+"</a>";
			    		}else{
			    			return value;
			    		}
	            	}
			    }
			    ],// the column discription
				postData:{query_id:"getUnDealWareHouseItemForOrderType",query_type:"JQGRID",rdParseType:"dispersed",reqData:loadUnDeal(0)} ,    //参数里面带callback
				rowNum : 100,
				rowList : [ 50, 100, 200 ],
				height : 'auto',
				jsonReader : {
					  repeatitems: false       //这里要设置为false，否则解析不了返回数据
				},
//				beforeRequest:function(){
//					$(this).setGridWidth($("#table").parents('.taskListJqGrid').eq(0).width())
//				}
				loadComplete:function(){
//					alert(1)
					$(this).setGridWidth($("#table").parents('.taskListJqGrid').eq(0).width())
				}
			});
	 
	 $("#tableb").jqGrid({
			url: appPath+'/console/query',
			mtype:'post',
			autowidth : true, // 自适应宽度
			shrinkToFit : true, // 列自适应
			datatype : 'json',
			colNames : ["其中","待处理","部分处理"],
			colModel : [
			    {name:"orderTypeStr", index:"orderTypeStr",width : '6%',align : 'center',sortable: false},
			    {name:"unDealOrderCount", index:"unDealOrderCount",width : '6%',sortable: false,align : 'center',
			    	formatter:function(value,options,rData){
			    		if(value>0){
			    			return "<a href='javascript:;' onclick='toOrderProcessing("+rData.orderType+",\""+rData.wareHouse+"\",\"N\");'>"+value+"</a>";
			    		}else{
			    			return value;
			    		}
	            	}
			    },
			    {name:"partDealOrderCount", index:"partDealOrderCount",width : '6%',sortable: false,align : 'center',
			    	formatter:function(value,options,rData){
			    		if(value>0){
			    			return "<a href='javascript:;' onclick='toOrderProcessing("+rData.orderType+",\""+rData.wareHouse+"\",\"A\");'>"+value+"</a>";
			    		}else{
			    			return value;
			    		}
	            	}
			    }
			    ],// the column discription
				postData:{query_id:"getUnDealWareHouseOrderForOrderType",query_type:"JQGRID",rdParseType:"dispersed",reqData:loadUnDealOrder(0)} ,    //参数里面带callback
				rowNum : 100,
				rowList : [ 50, 100, 200 ],
				height : 'auto',
				jsonReader : {
					  repeatitems: false       //这里要设置为false，否则解析不了返回数据
				},
				loadComplete:function(){
//					alert(2)
					$(this).setGridWidth($("#tableb").parents('.taskListJqGrid').eq(0).width())
				}
			});
	 dimensionSelect();
});



/**
 * 跳转到订单列表页面（直供）
 * 跳转到订单品种处理页面
 *  1:直供订单,
 *  2:大中专订单,
 *  3:馆配订单,
 *  4:零售\电商\团购\活动\文教
 */
function toOrder(type,index,number){
	var otherReason,controlFlag,orderType,url,wareHouse;
	if(number==1){//未回告
		controlFlag = 0
	}else if(number==2){//预计可发
		otherReason = 0;
		controlFlag = 1;
	}else if(number==3){//暂时缺货
		otherReason = 1;
		controlFlag = 1;
	}else if(number==4){//新书待入库
		otherReason = 8;
		controlFlag = 1;
	}
	//采购商
	var purchaserId=$("#purchaserId").val();
	wareHouse = index;
	/*if(index!=null){
		//仓位
		wareHouse = $("#wareHouse_"+index).val();
	}else{
		wareHouse = "";
	}*/
	//直供
	if(type!=""&&type!=null&&type==1){
		url = appPath+"/backto/order/page/toOrderProcessing?isDeal=N&purchaserId="+purchaserId+"&wareHouse="+wareHouse;
		window.location.href=url;
	}else{//非直供
		if(!type){
			orderType = "";
		}else if(type==2){
			orderType = "15";//大中专
		}else if(type == 3){
			orderType = "25";//馆配
		}else{//零售\电商\团购\活动\文教
			orderType = "0,5,10,20,30";
		}
		url = appPath+"/backto/order/page/toOrderItems?isDeal=N&sendGoodsType=1&purchaserId="+purchaserId+"&wareHouse="+wareHouse+"&orderType="+orderType;
		//是否回告
		if((controlFlag!=null && controlFlag!='') || controlFlag==0){
			url +="&controlFlag="+Number(controlFlag);
		}
		//其余满足原因
		if((otherReason!=null && otherReason!='') || otherReason==0){
			url +="&otherReason="+Number(otherReason);
		}
		window.location.href=url;
	}
}
function toOrderProcessing(type,wareHouse,isDeal){
	var orderType,url;
	//采购商
	var purchaserId=$("#purchaserId").val();
	if(!type){
		orderType = "";
	}else if(type==1){
		orderType = "";//直供展示所有的订单种类，老王说的
	}else if(type==2){
		orderType = "15";//大中专
	}else if(type == 3){
		orderType = "25";//馆配
	}else{//零售\电商\团购\活动\文教
		orderType = "0,5,10,20,30";
	}
	url = appPath+"/backto/order/page/toOrderProcessing?purchaserId="+purchaserId+"&wareHouse="+wareHouse+"&orderType="+orderType+"&isDeal="+isDeal;
	window.location.href=url;
}
function dimensionSelect(){
//	$("#bodyTabB").hide();
	var statisticalDimension = $("#statisticalDimension").val();
	var totalCount = $("#statisticalDimension").find("option:selected").attr("valueCount");
	$("#totalCount").text(totalCount);
	if(statisticalDimension=='A'){
		$("#bodyTabB").removeClass("active");
		$("#bodyTabA").addClass("active");
		jQuery("#table").jqGrid('setGridParam',{datatype:'json',page:1,postData:{query_type:"JQGRID"}}).trigger("reloadGrid");
	}else{
		$("#bodyTabA").removeClass("active");
		$("#bodyTabB").addClass("active");
		jQuery("#tableb").jqGrid('setGridParam',{datatype:'json',page:1,postData:{query_type:"JQGRID"}}).trigger("reloadGrid");
	}
	//对当前选择的维度进行数据保存
	$.ajax({
		type : "POST",
		url:appPath+'/backto/task/json/updateStatisticalDimension',
		dataType : "json",
		async:false,
		data: {"statisticalDimension" : statisticalDimension},
		success : function(json) {
			
		},error : function() {
			layer.msg("保存统计方式异常!",{icon : 0,time:2500});
		}
	});
}