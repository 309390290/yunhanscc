/**add by zxc 2016-02-19 发货单查询及修改js*/
In.ready('jqGrid','multipleDataBox','select','fileUtil',function() {
	//导出Excel数量 -- 常量
	var EXPORT_NUMBER = 100;
	
	var supplierId = sapvendorId; 
	var userCode = userName;
	/**
	 * 采购商
	 */
	/*$("#receiveCompany").select({
		url:appPath+'/backto/system/getPurchaserBussinessRelation'
	});*/
	
	//订单号批量查询控件
	$('.order-btn').multipleDataBox({
		test:false,
		query:false,
		title:'订单号码查询',
		url:'#url_path', //请求地址
		para:{ id:111 } //请求参数
	});
	
	//发货单号批量查询控件
	$('.send-btn').multipleDataBox({
		test:false,
		query:false,
		title:'发货单号查询',
		url:'#url_path', //请求地址
		para:{ id:111 } //请求参数
	});
	
	/**查询条件清除*/	
	$(".clear-btn").click(function(){
		$(this).parent().children("input[type='text']").val("");
	});
	
	/**
 	 * 近1周、近2周、1个月前
 	 */
 	$("a[name='sendDate']").click(function(){
 		var startDt,endDt;
 		var thisId = $(this).attr("id");
		if('weekOne' == thisId){
			startDt = GetDateStr(-7);
			endDt = GetDateStr(0);
		}else if('weekTwo' == thisId){
			startDt = GetDateStr(-14);
			endDt = GetDateStr(0);
		}else{
//			startDt = GetDateStr(-30);
//			endDt = GetDateStr(0);
			endDt =  GetDateStr(-30)
			startDt="";
		}
		$("#sendoutDateFrom").val(startDt);
		$("#sendoutDateTo").val(endDt);
 	});
	//获取日期
 	function GetDateStr(AddDayCount){
 		var dd = new Date(); 
 		dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期 
 		var y = dd.getFullYear(); 
 		var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);//获取当前月份的日期，不足10补0
 		var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate(); //获取当前几号，不足10补0
 		return y+"-"+m+"-"+d; 
 	}
	$("#table_list").jqGrid({ 
	    url:appPath+'/console/query',
		autowidth: true, //自适应宽度
 		shrinkToFit: true, //列自适应
		datatype: "json", //数据格式
		mtype:"POST", 
		colNames:["ID","供应商ID","采购商ID","主发标识","发货单号Back","发货单号","制单日期","采购商","合计发货品种","总册数","总码洋","总实洋","发货日期","发货单状态"],// the column header names  
	     colModel :[ 
	          {name:"id", index:"id", hidden:true},
	          {name:"supplierId", index:"supplierId", hidden:true},
	          {name:"purchaserId", index:"purchaserId", hidden:true},
	          {name:"isSupplierAddProduct", index:"isSupplierAddProduct", hidden:true},
	          {name:"sendoutGoodsCodeBack", index:"sendoutGoodsCodeBack",formatter : function(value, options, rData) {
	        	  return rData.sendoutGoodsCode;
	          }, hidden:true},
			  {name:"sendoutGoodsCode", index:"sendoutGoodsCode",width:'15%',
	        	  formatter : function(value, options, rData) {
	        		  if(rData.isInitiative=="Y" ){
						  return "<i class=\"top_ico\">主发</i><a class='line sgc-btn' id=\""+options.rowId+"\" href='#'>"+value+"</a>";
					  }else if(rData.isSupplierAddProduct=="Y"){
	        			  return  "<i class='top_ico'>供应商发货添加</i> <a class='line sgc-btn' id=\""+options.rowId+"\" href='#'>"+value+"</a>";
	        		  } 
					  return  "<a class='line sgc-btn' id=\""+options.rowId+"\" href='javaScript:void(0);'>"+value+"</a>";
	        	  }, align:'center'}, 
	          {name:"addTime", index:"addTime",width:'10%',align:'center', sorttype:'date', formatter:'date', formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d'}},   
			  {name:"purchaserName", index:"purchaserName",width:'20%',align:'center'}, 
			  {name:"totalVarietyQty", index:"totalVarietyQty",width:'10%',align:'center', sorttype:'number'},  
			  {name:"totalBookQty", index:"totalBookQty",width:'10%',align:'center', sorttype:'number'}, 
			  {name:"totalPrice", index:"totalPrice",width:'10%',align:'center', sorttype:'number'}, 
			  {name:"totalRealityPrice", index:"totalRealityPrice",width:'10%',align:'center', sorttype:'number'}, 
			  {name:"sendoutDate", index:"sendoutDate",width:'10%',align:'center', sorttype:'date', formatter:'date', formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d'}},   
			  {name:"sendoutStatus", index:"sendoutStatus",width:'10%',
				  formatter : function(value, options, rData) {
					  if(value==0)
						  return "未发货";
					  if(value==5)
						  return "已发货（未收货）";
					  if(value==10)
						  return "部分收货";
					  if(value==15)
						  return "全部收货";
					  return "";
				  },align:'center'}
			],
	   	rowNum: 50,  //默认分页行数
		rowList:[50,100],  //可选分页行数
		pager: '#page',  //分页按钮展示地址
		sortname: 'id',  //默认排序字段
		viewrecords: true,   //是否显示数据总条数 
		multiselect:true,  //复选框
		sortorder: "desc",  //默认排序方式,
		jsonReader: { 
			  root: "rows",
			  page: "page",
			  total: "total",
			  records: "records",
			  repeatitems : false
		}, //设置数据方式
		postData: {query_id:"queryProSendoutSummary",query_type:"JQGRID",rdParseType:"dispersed", reqData:queryData()}, 
		height: '100%', 
		/* 客户端排序--------------------------------------------------------开始 */
		loadBeforeSend:function(){
             $(this).jqGrid('clearGridData');
	    },
	    loadError:function(){
	    	layer.msg("系统报错！",{icon:5});
	    },
		onPaging : function(pgButton){
			//$("#node_table_list").jqGrid('setGridParam',{datatype:'json',postData:{reqData:queryData(),_search1:false}});
	    	$(this).jqGrid('setGridParam',{datatype:'json',postData:{reqData:queryData(),_search1:false}});

		}, 
		onSelectRow : function(rowid, status, e) {
		   if(status){
			var rowData = $("#node_table_list").jqGrid("getRowData", rowid);
			if(rowData.sendoutStatus=="已收货"){ 
				$(".modify-btn").attr("disabled",true);
			}
		   }else{
				$(".modify-btn").attr("disabled",false);
		   }
		},
		loadComplete : function(data) {
		      var $this = $(this);
		      if ($this.jqGrid('getGridParam', 'datatype') === 'json') {
		          $this.jqGrid('setGridParam', {
		              datatype: 'local',
		              data: data.rows,
		              pageServer: data.page,
		              recordsServer: data.records,
		              lastpageServer: data.total
		        });
		          this.refreshIndex();
		          if ($this.jqGrid('getGridParam', 'sortname') !== '') {
		              $this.triggerHandler('reloadGrid');
		          }
		      } else {
		          $this.jqGrid('setGridParam', {
		              page: $this.jqGrid('getGridParam', 'pageServer'),
		              records: $this.jqGrid('getGridParam', 'recordsServer'),
		              lastpage: $this.jqGrid('getGridParam', 'lastpageServer')
		          });
		          this.updatepager(false, true);
		      }

			
			
			$(".sgc-btn").click(function(){
				var rowId = $(this).attr("id");
				var rowData =  $("#table_list").jqGrid("getRowData", rowId);
				var id = rowData.id;
				if(rowData.isInitiative=="Y"){
					window.location.href=appPath+"/backto/sendQuery/page/detail?id="+id+"&isInitiative=Y";
				}else{
					window.location.href=appPath+"/backto/sendQuery/page/detail?id="+id;	
				}
			});
		}
		/* 客户端排序 --------------------------------------------------------结束*/
	});
  	$("#table_list").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });  //jqgrid取消掉水平滚动条

  	
  	/**
  	 * 查询参数 by zxc 
  	 */
  	function queryData(){

  		var sendoutGoodsCode	= $("#sendoutGoodsCode").val();
  		var sendoutStatus	    = $("#sendoutStatus").val();
  		var purchaseOrderCode	= $("#purchaseOrderCode").val();
  		var sendoutDateFrom	        = $("#sendoutDateFrom").val();
  		var sendoutDateTo	        = $("#sendoutDateTo").val();
  		var addDateFrom	        = $("#addDateFrom").val();
  		var addDateTo	        = $("#addDateTo").val();
  		
  		var isInitiative		= $("#isInitiative:checked").val();
  		var queryData="{'groupOp':'AND','rules':[";
  		var a = "{'field':'supplierId','op':'eq','data':'"+supplierId+"'},";
  		queryData = queryData.concat(a);
  		var b = "{'field':'addUserCode','op':'eq','data':'"+userCode+"'},";
  		queryData = queryData.concat(b);
  		//判断日期是否合法
  		if(null != sendoutDateFrom && null != sendoutDateTo
  				&& "" != sendoutDateFrom && "" != sendoutDateTo){
  			if(sendoutDateTo < sendoutDateFrom){
  				layer.alert("发货起始日期不能大于结束日期！", {icon:0});//0：弹框图片为叹号警告
  				return false;
  			}
  		}
  		//判断制单日期是否合法
  		if(null != addDateFrom && null != addDateTo
  				&& "" != addDateFrom && "" != addDateTo){
  			if(addDateTo < addDateFrom){
  				layer.alert("制单起始日期不能大于结束日期！", {icon:0});//0：弹框图片为叹号警告
  				return false;
  			}
  		}
  		
  		//发货单
  		if(null != sendoutGoodsCode && sendoutGoodsCode.length>0){
  			if(sendoutGoodsCode.indexOf(',')!=-1){
  	  			var sendoutGoodsCodeQC = "{'field':'sendoutGoodsCodes','op':'eq','data':'"+ sendoutGoodsCode +"'},";
  	  			queryData = queryData.concat(sendoutGoodsCodeQC);
  			}else{
  				var sendoutGoodsCodeQC = "{'field':'sendoutGoodsCode','op':'eq','data':'"+ sendoutGoodsCode +"'},";
  	  			queryData = queryData.concat(sendoutGoodsCodeQC);
  			}
  		}
  		var purchaserId		= $("#purchaserId").val();
  		//采购商id
  		if(null != purchaserId && purchaserId.length>0){
  			var purchaserIdQC = "{'field':'purchaserId','op':'eq','data':'"+ purchaserId +"'},";
  			queryData = queryData.concat(purchaserIdQC);
  		}
  		//发送状态
  		if(null != sendoutStatus && sendoutStatus.length>0 ){
  			var sendoutStatusQC = "{'field':'sendoutStatus','op':'eq','data':'"+ sendoutStatus +"'},";
  			queryData = queryData.concat(sendoutStatusQC);
  		}
  		//采购订单号
  		if(null != purchaseOrderCode && purchaseOrderCode.length>0 ){
  			if(purchaseOrderCode.indexOf(',')!=-1){
  				var purchaseOrderCodeQC = "{'field':'purchaseOrderCodes','op':'eq','data':'"+ purchaseOrderCode +"'},";
  				queryData = queryData.concat(purchaseOrderCodeQC);
  			}else{
  				var purchaseOrderCodeQC = "{'field':'purchaseOrderCode','op':'eq','data':'"+ purchaseOrderCode +"'},";
  				queryData = queryData.concat(purchaseOrderCodeQC);
  			}
  		}
  		//发送日期
  		if(null != sendoutDateFrom && sendoutDateFrom.length>0 ){
  			var sendoutDateFromQC = "{'field':'sendoutDateFrom','op':'eq','data':'"+ sendoutDateFrom +"'},";
  			queryData = queryData.concat(sendoutDateFromQC);
  		}
  	   //发送日期
  		if(null != sendoutDateTo && sendoutDateTo.length>0 ){
  			var sendoutDateToQC = "{'field':'sendoutDateTo','op':'eq','data':'"+ sendoutDateTo +"'},";
  			queryData = queryData.concat(sendoutDateToQC);
  		}
  		//制单日期from
  		if(null != addDateFrom && addDateFrom.length>0 ){
  			var addDateFromQC = "{'field':'addDateFrom','op':'eq','data':'"+ addDateFrom +"'},";
  			queryData = queryData.concat(addDateFromQC);
  		}
  	   //制单日期to
  		if(null != addDateTo && addDateTo.length>0 ){
  			var addDateToQC = "{'field':'addDateTo','op':'eq','data':'"+ addDateTo +"'},";
  			queryData = queryData.concat(addDateToQC);
  		}
  		//主发
  		if(null != isInitiative && undefined != isInitiative&&isInitiative.length>0  ){
  			var isInitiativeQC = "{'field':'isInitiative','op':'eq','data':'"+ isInitiative +"'},";
  			queryData = queryData.concat(isInitiativeQC);
  		}
  		if(queryData.length > 30){
  			queryData = queryData.substring(0,queryData.length-1);
		}
  		var z= "]}";
  		queryData = queryData.concat(z);
  		return queryData;
  	}

  	/**
  	 * 查询操作 by zxc
  	 */
  	function search(){
  		var param = queryData();
  		if(param){
  		 $("#table_list").jqGrid("setGridParam",{datatype:"json",page:1,postData:{query_id:"queryProSendoutSummary",query_type:"JQGRID",reqData:param}}).trigger("reloadGrid");
  		}
  	}
	/**
	 * 修改操作 by zxc
	 */
	function modify(){
		var selectedIds = $("#table_list").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length < 1){
			layer.alert("请先勾选需要修改的发货单！", {icon:0});//0：弹框图片为叹号警告
			return;
		}
		if(selectedIds.length > 1){
			layer.alert("请勿勾选多行数据！", {icon:0});//0：弹框图片为叹号警告
			return;
		}
		 var rowData = $("#table_list").jqGrid("getRowData", selectedIds[0]);
		//alert( rowData.sendoutStatus);
		/* if(rowData.sendoutStatus=="部分收货" || rowData.sendoutStatus=="全部收货"){
			layer.alert("已收货的发货单不允许修改！", {icon:0});//0：弹框图片为叹号警告
			return;
		 }*/
		 var id= rowData.id;
		 //后台校验是否收货，才能准确获得最新的数据。
			$.ajax({
	    		url:appPath+"/backto/sendQuery/findSendOutSummaryById",
	    		type:"POST",
	    		dataType:"json",
	    		data:{"ids":id,"sapvendorId":sapvendorId},
	    		async: false,
	    		success:function(data){
	    			if(data.success){
	    				if(data.msg==0){
	    					layer.alert("已收货的发货单不允许修改！", {icon:0});//1：弹框图片为勾
	    					return;
	    				}else{
	    					 var url = appPath+"/backto/sendQuery/page/modify?id="+id;
	    					 window.location.href = url;
	    				}
	    			}else{
	    				layer.alert(data.msg, {icon:2});//2：弹框图片为叉叉
	    			}
	    		}
	            });
		
	}
    /**
	 * 导出操作 by zxc
	 */
    function exportExcel(){
    	var ids = $("#table_list").jqGrid("getGridParam", "selarrrow");
    	if(ids.length > 0 && ids.length <= EXPORT_NUMBER){
  	      var codes = new Array();
  	      for(var i = 0;i<ids.length;i++){
  	    	  var rowData = $("#table_list").jqGrid("getRowData",ids[i]);
  	    	  codes.push(rowData.sendoutGoodsCodeBack);
  	      }
  	    }else{
  	    	layer.alert("请先勾选需要下载的发货单！", {icon:0});//0：弹框图片为叹号警告
			return;
  	    }
    	
    	var queryData = "{'groupOp':'AND','rules':[";
    	//采购商编码 add by yangtao 2016-5-6 导出增加供应商和采购商
    	var purchaserId	= $("#purchaserId").val();
    	if(null != purchaserId && '' != purchaserId){
    		var purchaserId = "{'field':'purchaserId','op':'eq','data':'"+ purchaserId +"'},";
    		queryData = queryData.concat(purchaserId);
    	}
    	// 供应商ID
    	var supplierId_tmp = "{'field':'supplierId','op':'eq','data':'"+sapvendorId+"'},";
  			queryData = queryData.concat(supplierId_tmp);
    	//end yangtao
		var a = "{'field':'sendoutGoodsCodes','op':'eq','data':'"+codes.toString()+"'}";
			queryData = queryData.concat(a);
		var f= "]}";
			queryData = queryData.concat(f);
		var timeDay = new Date();
	    var name = "发货单_"+timeDay.getYear()+""+timeDay.getMonth()+""+timeDay.getDay()+""+timeDay.getHours()+""+timeDay.getMinutes()+""+timeDay.getSeconds();
//	    alert(queryData);
	    //	    var url = appPath+"/console/export/querySendOutOrderList/excel?fileName="+encodeURI(encodeURI(name))+"&pst=false&reqData="+queryData;
//		$("#exportPost").attr("action", url);
//		$("#exportPost").submit();
	    doExport(queryData,name)
    }
    
  //导出处理函数
	function doExport(queryData,name){
		if(queryData!=""){
			$.ajax({ 
				type:"post",
				url: appPath+"/console/export/ajax/querySendOutOrderList/excel",
				dataType: "json",
				data:{fileName:name,pst:false,reqData:queryData},
				beforeSend : function(){
		        	WG.loading.show();
		        },
				// async: false,
				success: function(data){
					WG.loading.hide();
					if(data.success){
						download_file(data.obj);
//						layer.alert("导出文件完成！",{icon:1});
					}else{
						layer.alert("导出文件失败，请稍后再试！",{icon:2});
					}
				},
				error: function(data){
					WG.loading.hide();
					layer.msg("导出文件失败，请稍后再试！",{icon:2});
				}
			});
		}
	}
    
    /**
	 * 删除操作 by zxc
	 */
    function delSendoutSummary(){
    	var selectedIds = $("#table_list").jqGrid("getGridParam", "selarrrow");
    	if(selectedIds.length < 1){
    		layer.alert("请先勾选需要删除的发货单！", {icon:0});//0：弹框图片为叹号警告
    		return;
    	}
    	var ids =""
        	for(var i=0;i<selectedIds.length;i++){
    			var rowData = $("#table_list").jqGrid("getRowData", selectedIds[i]);
    			ids+= rowData.id+",";
        	}
        	ids = ids.substring(0,ids.length-1);
    /*	for(var i=0;i<selectedIds.length;i++){
			var rowData = $("#table_list").jqGrid("getRowData", selectedIds[i]);
			if(rowData.sendoutStatus=="部分收货" || rowData.sendoutStatus=="全部收货"){
				layer.alert("已收货的发货单不允许删除！", {icon:0});//0：弹框图片为叹号警告
	    		return;
			}
    	}*/
        	//后台校验是否收货，才能准确获得最新的数据。
     	$.ajax({
    		url:appPath+"/backto/sendQuery/findSendOutSummaryById",
    		type:"POST",
    		dataType:"json",
    		data:{"ids":ids},
    		async: false,
    		success:function(data){
    			if(data.success){
    				if(data.msg==0){
    					layer.alert("已收货的发货单不允许删除！", {icon:0});//1：弹框图片为勾
    					return;
    				}else{
    					layer.confirm(selectedIds.length+'条发货单删除后不可还原，是否确认删除？', {
    						zIndex:999,
    			 		    btn: ['确定','取消'] //按钮
    			 		}, function(){
    			    	
    			    	$.ajax({
    					url:appPath+"/backto/sendQuery/getRigthUpdateOrDeleteProSendSendItems",
    					type:"POST",
    					dataType:"json",
    					data:{"ids":ids},
    					success:function(obj){
    						if(obj.success){
    							//layer.alert("删除成功！", {icon:1});//1：弹框图片为勾
    							//alert(data.obj);
    							//search();
    							if(obj.obj){
    								WG.loading.show();
    								$.ajax({
    			    					url:appPath+"/backto/sendQuery/delSendoutSummary",
    			    					type:"POST",
    			    					dataType:"json",
    			    					data:{"ids":ids},
    			    					success:function(data){
    			    						WG.loading.hide();
    			    						if(data.success){
    			    							layer.alert("删除成功！", {icon:1});//1：弹框图片为勾
    			    							//$("#node_table_list").jqGrid('setGridParam',{datatype:'json',postData:{reqData:queryData(),_search1:false}});
    			    						//	$("#node_table_list").jqGrid('setGridParam',{datatype:'json',postData:{reqData:queryData(),_search1:false}});
    			    							// $("#node_table_list").jqGrid("setGridParam",{datatype:"json",page:1,postData:{query_id:"findMasterDataInfoBySupplierIdBackto",query_type:"JQGRID",reqData:param}}).trigger("reloadGrid");
    			    					  		 $("#table_list").jqGrid("setGridParam",{datatype:"json",page:1,postData:{query_id:"queryProSendoutSummary",query_type:"JQGRID",reqData:queryData()}}).trigger("reloadGrid");


    			    						}else{
    			    							layer.alert(data.msg, {icon:2});//2：弹框图片为叉叉
    			    						}
    			    					
    			    						},
    			        					error: function(data){
    			        						WG.loading.hide();
    			        					}
    			    					});
    			    					
    							}else{
    								layer.alert("不是最后一次发货，不能删除", {icon:2});//2：弹框图片为叉叉
    							}
    							
    						}else{
    							layer.alert(obj.msg, {icon:2});//2：弹框图片为叉叉
    						}
    					}
    			        });
    			     },function(){
    			   	   return;
    			      });
    				}
    			}else{
    				layer.alert(data.msg, {icon:2});//2：弹框图片为叉叉
    			}
    		}
            });
    
    }

	/**查询*/
  	$(".search-btn").click(function(){
  		search();
  	});
  	/**导出*/
  	$(".export-btn").click(function(){
  		exportExcel();
  	});
  	/**修改*/
  	$(".modify-btn").click(function(){
  		modify();
  	});
  	/**删除*/
  	$(".del-btn").click(function(){
  		delSendoutSummary();
  	});
});