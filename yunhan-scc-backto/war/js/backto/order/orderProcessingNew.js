/**
 * 订单查询js 
 * add by wangtao
 * 2016年7月7日13:33:32
 */

In.ready('multipleDataBox','fileUtil','AllSelect','uploadBox','autocomplete','jqGrid','WdatePicker','SetJqGridList',function() {
	
	/**********展开收起搜索 start*********/
	 //展开收起搜索
   $('#open').toggle(
       function() {
    		$(this).parents('.screenBox').find('.left ul .show').hide();
            $(this).find('i').removeClass('close');
            $(this).find('i').addClass('open');
            $(this).find('b').text('展 开');
       },function(){
           $(this).parents('.screenBox').find('.left ul .show').show();
           $(this).find('i').removeClass('open');
           $(this).find('i').addClass('close');
           $(this).find('b').text('收 起');
       }
   );
   /**********展开收起搜索 end*********/
    //订单号码
    $('.orderCode-btn').multipleDataBox({
        title:"订单号码",
        test:false, //测试数据
        query:false, //模糊查询开关，如果为false, url和para属性将无作用
        url:'#url_path', //请求地址
        para:{ id:222 } //请求参数
    });
    $('.del').click(function(){
        $(this).parent().find('input').val('')
    });
    //新品订单
    $(".isNew").AllSelect({
    	id:"resetIsNew",
		data:[
              {id:'all',name:"全部",check:'true'},
              {id:'Y',name:"新品",check:"false"},
              {id:'N',name:"非新品",check:"false"}
              ]
	 });
    //紧急程度
    $(".urgentFlag").AllSelect({ 
    	id:"resetUrgentFlag",
		data:[
              {id:'all',name:"全部",check:'true'},
              {id:'1',name:"急单",check:"false"},
              {id:'2',name:"普通单",check:"false"}
              ]
	 });

    //导出按钮
	$("#downBtn").change(function(){
		if($(this).val()=='exportOrderSingle'){
			exportOrderSingleExport();
			$(this).val("");
		}else if($(this).val()=='exportOrderMultiple'){
			exportOrderMultipleExport();
			$(this).val("");
		}
	});
    
    //时间 一个月前 后效果
    $('.time_box a').click(function(){
        $('.time_box a').removeClass('active');
        $(this).addClass('active');
    });
    
    //JQ grid排序方式
    var sort= $('.sortBox li');
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
    
    //初始化采购商查询条件
    //var datatype = 'local';
    var userNameTemp = userName+"summary";
	if(sessionStorage[userNameTemp]){
		var searchData = JSON.parse(sessionStorage.getItem(userNameTemp));
		
		//按照循环返回值，使其选择。

		for(var name in searchData){
				//判断新品订单 选中
				if(name=='isNew'&& searchData[name]!=''){		
					var newData=[
					              {id:'all',name:"全部",check:'false'},
					              {id:'Y',name:"新品",check:"false"},
					              {id:'N',name:"非新品",check:"false"}
				                ];
	
					if(searchData[name]=='Y'){
						newData[1].check=true;
					}
					if(searchData[name]=='N'){
						newData[2].check=true;
					}
					$(".isNew").AllSelect({
				    	id:"resetIsNew",
						data:newData
					 });
					//判断紧急程度 选中		
				}else if(name=='urgentFlag'&& searchData[name]!=''){
					var newData=[
					             {id:'all',name:"全部",check:'false'},
					              {id:'1',name:"急单",check:"false"},
					              {id:'2',name:"普通单",check:"false"}
				                ];
	
					if(searchData[name]=='1'){
						newData[1].check=true;
					}
					if(searchData[name]=='2'){
						newData[2].check=true;
					}
					$(".urgentFlag").AllSelect({ 
				    	id:"resetUrgentFlag",
						data:newData
					 });
				}
			//是否下载
			if(name=='is_Export'&& searchData[name]!=''){	
				$("#is_export").val(searchData["is_Export"]);
			}
			if('orderType'==name){
				ordertTypeSelect(searchData[name]);
			}
			if(name=='isDeal'){
				isDealSelect(searchData[name]);
			}
			$("#"+name).val(searchData[name]);
		}
		//仓位选 
		if($("#pur_all").val()){
			getDC($("#pur_all").val());
			$("#dc_all").val(searchData["dc_all"]);
			reloadDcInfo();
		}
		//datatype = 'json';
		
		//清理掉暂存的查询条件
	sessionStorage.clear();
	}else{
		var orderTypeRe = $("#orderTypeRe").val();
		if(orderTypeRe !=null && orderTypeRe !=""){
			ordertTypeSelect(orderTypeRe);
		}else{
			//默认为全部
			ordertTypeSelect();
		}
		var isDealRe = $("#isDealRe").val();
		if(isDealRe !=null && isDealRe !=""){
			isDealSelect(isDealRe);
		}else{
			isDealSelect('N');//默认为待处理
		}
	}
	
	/**
	 * 订单总类事件
	 */
	function ordertTypeSelect(newData){
		//初始化数据
		var dataJson=[
		              {id:'all',name:"全部",check:'false'},
		              {id:0,name:"零售订单",check:"false"},
		              {id:5,name:"文教订单",check:"false"},
		              {id:10,name:"电商订单",check:"false"},
		              {id:15,name:"大中专订单",check:"false"},
		              {id:20,name:"团购订单",check:"false"},
		              {id:25,name:"馆配订单",check:"false"},
		              {id:30,name:"活动订书",check:"false"}
		              ];
		
		//存在查询条件时  返回订单种类
		if(newData!=null && newData!=''){
			//循环获取每一个种类数据
			var newDataType= newData.split(",");
			for( var i=0; i<newDataType.length;i++){
				for( var aName in dataJson){
					if(dataJson[aName].id==newDataType[i]){
						dataJson[aName].check=true;
					}
				}
			}
		}else{//无查询条件时
			dataJson[0].check = 'true';
		}
		 $(".AllSelect").AllSelect({
			data:dataJson
		 });
		 
		//$(".AllSelect .selectData").val(newData);
		
		//仓位选 
		if($("#pur_all").val()){
			getDC($("#pur_all").val());
		}
	}
	
	function isDealSelect(id){
		var dataJsonDDCL=[
	          {id:'all',name:"全部",check:'false'},
	          {id:'N',name:"待处理",check:"false"},
	          {id:'A',name:"部分处理",check:"false"},
	          {id:'Y',name:"已处理",check:"false"}
	          ];
		if(id!=null && id!=''){
			//循环获取每一个种类数据
			for(var i=0;i<dataJsonDDCL.length;i++){
				//循环判断每一个种类的id是否在查询条件中，若有则设置选中
				for( var aName in dataJsonDDCL[i]){
					if(aName!='id') continue;
					var index = id.indexOf(dataJsonDDCL[i].id);
					if(index >-1 ){	
						if(dataJsonDDCL[i].id==0 || dataJsonDDCL[i].id==5){
							if(id.substr(index-1,1)==1
									||id.substr(index-1,1)==2
									||id.substr(index-1,1)==3){
								continue;
							}
							dataJsonDDCL[i].check='true';
						}else{
							dataJsonDDCL[i].check='true';	
						}
					}
				} 
			}
		}else{//无查询条件时
			dataJsonDDCL[0].check = 'true';
		}
		$(".AllSelectDDCL").AllSelect({
			data:dataJsonDDCL
		 });
		$(".AllSelectDDCL .selectData").val(id);
	}
	
	//待办(直供)进入订单列表页面
	if($("#pur_all").val() && $("#sendGoodsType").val()!=''){
		getDC($("#pur_all").val());
		//datatype = 'json';
	}
	
	
    $("#table").jqGrid({
		url: appPath+'/console/query',
		mtype:'post',
		autowidth : true, // 自适应宽度
		shrinkToFit : true, // 列自适应
		datatype : 'json',
		colNames : ["ID","orderCode","发送日期", "制单日期", "订单号", "品种数",
					"总册数", "总码洋", "总实洋", "待处理品种数", "已处理品种",
					"订单备注", "订单状态", "订单处理情况" ],
		colModel : [{name:"id", index:"id",hidden:true},
		    {name:"orderCode", index:"orderCode",hidden:true,
				formatter:function(value,options,rData){
					return rData.purchaseOrderCode;
				}
		    },
		    {
				name : "sendDate",
				index : "发送日期",
				width : '10%',
				align : 'center',
				formatter:"date", 
				formatoptions: {newformat:'Y-m-d'}, 
				align:"center",
				sortable:false
			}, {
				name : "addDate",
				index : "制单日期",
				width : '10%',
				align : 'center',
				formatter:"date", 
				formatoptions: {newformat:'Y-m-d'}, 
				align:"center",
				hidden:true,
				sortable:false
			}, {
				name : "purchaseOrderCode",
				index : "订单号",
				width : '10%',
				align : 'center',
				sortable:false,
				formatter:function(value,options,rData){
  				  //订单种类
  				  var order_type = "",
  				  order_name="";
  				  
  				  switch(rData.orderType){
					  	  case 0:order_type = "ls";
					  	  		order_name = "零售";
					  	  break;
						  case 5:order_type = "wg";
						  		order_name = "文教";
						  break;
						  case 10:order_type = "ds";
						  		order_name = "电商";
						  break;
						  case 15:order_type = "dzz";
						  		order_name = "大中专";
						  break;
						  case 20:order_type = "tg";
						  		order_name = "团购";
						  break;
						  case 25:order_type = "gp";
						  		order_name = "馆配";
						  break;
						  case 30:order_type = "hd";
						  		order_name = "活动";
						  break;
  				  }
  				  var htmls =  "<div class='iconBox'>";
  				  //已下载
	  			 if(rData.isExport == 'Y'){
	  				 htmls += "<div class='right'><i class='u-icon-yxz'>已下载</i></div>"
	  			 }
	  			 //订单号和订单类型
	  			 var isDealP = rData.isDeal;
	  			 if(isDealP=='A'){isDealP = 'N';}
	  				htmls +="<div><a herf='#' id=\""+rData.id+"\" onclick=\"toOrderDetail('"+rData.id+"','"+isDealP+"')\">"+value+"</a></div>" +
	  						"<div class='uicon'>";
	  			 if(order_name){
	  				 htmls +="<i class='u-icon-orderType'>"+order_name+"</i>"
	  			 }
  				//紧急  
	  			if(rData.urgentFlag == 1){
  					  htmls += "<i class='u-icon-j'>急</i>";
  				  }
  				  //新品
  				  if(rData.isNew == "Y"){
  					  htmls += "<i class='u-icon-x'>新</i>";
  				  }
  				  //直供
  				  if(rData.sendGoodsType == 2){
  					  htmls += "<i class='u-icon-zg'>直供</i>";
  				  }
	        	  htmls += "</div></div>";
  				  return htmls;
  			  }
			}, {
				name : "totalVarietyQty",
				index : "品种数",
				width : '6%',
				align : 'center',
				sortable:false
			}, {
				name : "totalBookQty",
				index : "总册数",
				width : '6%',
				align : 'center',
				sortable:false
			}, {
				name : "totalPrice",
				index : "总码洋",
				width : '6%',
				align : 'center',
				sortable:false
			}, {
				name : "totalRealityPrice",
				index : "总实洋",
				width : '6%',
				align : 'center',
				sortable:false
			}, {
				name : "untreated",
				index : "待处理品种数",
				width : '8%',
				align : 'center',
				sortable:false
			}, {
				name : "alreadyProcessed",
				index : "已处理品种",
				width : '8%',
				align : 'center',
				sortable:false
			}, {
				name : "orderRemark",
				index : "订单备注",
				width : '16%',
				align : 'center',
				hidden:true,
				sortable:false
			}, {name : "orderStatus",index : "订单状态",width : '6%',align : 'center',sortable:false,hidden:false,
				formatter:function(value,options,rData){
					  switch(value){
						  case 10:return "未回告";
						  	break;
						  case 13:return "部分回告";
						  	break;
						  case 15:return "全部回告";
						  	break;
						  case 20:
						  case 25:  
							return "未收货";
						  	break;
						  case 30:return "部分收货";
						  	break;
						  case 35:return "全部收货";
						  	break;
/*						  case 40:return "已关闭";
						  break;
						  case 45:return "已结束";
						  break;
*/						  default:return "";
					  }
				  }
			}, {
				name : "isDeal",
				index : "订单处理情况",
				width : '8%',
				align : 'center',
				sortable:false,
				formatter:function(value,options,rData){
					if(value=='Y'){
						return "<a href=\'#'\ class=\"line detail-btn \" onclick=\"toOrderDetail('"+rData.id+"','"+value+"')\">已处理</a>";
					}else if(value=='N'){
						return "<a class=\"btn btn-mini\" onclick=\"toOrderDetail('"+rData.id+"','N')\">待处理</a>";
					}else if(value=='A'){
						return "<a class=\"btn btn-mini\" onclick=\"toOrderDetail('"+rData.id+"','N')\">部分处理</a>";
					}else{
						return "";
					}
						
				}
			} ],// the column discription
			postData:{query_id:"queryPendingOrderSummaryByPage",query_type:"JQGRID",rdParseType:"dispersed",reqData:queryData()} ,    //参数里面带callback
			rowNum : 100,
			rowList : [ 50, 100, 200 ],
			multiselect : true, // 显示checkbox选择框
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
				/**
				 * 展开列表
				 */
				$("#odiv").SetJqGridList({
			        jqID:'table',
			        storageID:'orderProcessingOne',
			        data:[
			            {
			                id:'addDate',
			                name:"制单日期"
			            },
			            {
			                id:'orderRemark',
			                name:"订单备注",
			                show:true
			            },
			            {
			                id:'orderStatus',
			                name:"订单状态",
			                show:true
			            }
			        ]
			    });
	            WG.JGscrollX.Event($(this),'GdBox');
	        },
	        onPaging : function(pgButton){
				$("#table").jqGrid('setGridParam',{
					datatype:'json',
					postData:{
						query_id:"queryPendingOrderSummaryByPage",
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
		
		//查询条件获取
		function queryData(){
			var re="{'groupOp':'AND','rules':[";
			var queryOrdertypeQC = "{'field':'userCode','op':'eq','data':'"+userName+"'},";
			var purchaserId = $("#pur_all").val();
			var dc = $("#dc_all").val();
			var orderCode = $("#orderCode").val();
			var orderType = $(".AllSelect .selectData").val();
			var isNew = $(".isNew .selectData").val();
			var urgentFlag = $(".urgentFlag .selectData").val();
			var sendDateStart	 = $("#sendDateStart").val();
	    	var sendDateEnd	 = $("#sendDateEnd").val();
			var isDeal = $(".AllSelectDDCL .selectData").val();
	    	var is_Export = $("#is_export").val();//modify by wld 2016-9-27 新增查询条件 是否下载
	    	var sendGoodsType = $("#sendGoodsType").val();
			//采购商
			if(null != purchaserId && '' != purchaserId){
	    		var a = "{'field':'purchaserId','op':'eq','data':'"+ purchaserId +"'},";
	    		re = re.concat(a);
	    		//获取一个月以前的待处理品种
	    		autoCountMonthAgo();
	    	}else{
		    	//layer.msg("请选择采购商",{icon:0});
		    	return;
	    	}
			//仓位
	    	if(null != dc && '' != dc){
	    		if(dc == 'QITA'){
	    			var a = "{'field':'warehouse','op':'eq','data':'"+ dc +"'},";
		    		re = re.concat(a);
	    		}else{
	    			var a = "{'field':'warehouses','op':'eq','data':'"+ dc +"'},";
		    		re = re.concat(a);
	    		}
	    	}
	    	//订单种类
	    	if(null != orderType && '' != orderType){
	    		if(orderType.indexOf(",")!=-1){
	    			var a = "{'field':'orderTypes','op':'eq','data':'"+ orderType +"'},";
	    			re = re.concat(a);
	    		}else{
	    			var a = "{'field':'orderType','op':'eq','data':'"+ orderType +"'},";
		    		re = re.concat(a);
	    		}
	    	}
	    	//订单号
	    	if(null != orderCode && '' != orderCode){
	    		if(orderCode.indexOf(",")!=-1){
    				var a = "{'field':'purchaserOrderCodes','op':'eq','data':'"+ orderCode +"'},";
	    			re = re.concat(a);
    			}else{
    				var a = "{'field':'purchaseOrderCode','op':'eq','data':'"+ orderCode +"'},";
	    			re = re.concat(a);
    			}
	    	}
	    	//新品
	    	if(null != isNew && '' != isNew && undefined != isNew){
	    		var isNew = "{'field':'isNew','op':'eq','data':'"+ isNew +"'},";
	    		re = re.concat(isNew);
	    	}
	    	//紧急程度
	    	if(null != urgentFlag && '' != urgentFlag && undefined != urgentFlag){
	    		var urgentFlag = "{'field':'urgentFlag','op':'eq','data':'"+ urgentFlag +"'},";
	    		re = re.concat(urgentFlag);
	    	}
	    	//订单发送日期-开始
	    	if(null != sendDateStart && '' != sendDateStart){
	    		var a = "{'field':'sendDateStart','op':'eq','data':'"+ sendDateStart +"'},";
	    		re = re.concat(a);
	    	}
	    	//订单发送日期-结束
	    	if(null != sendDateEnd && '' != sendDateEnd){
	    		var a = "{'field':'sendDateEnd','op':'eq','data':'"+ sendDateEnd +"'},";
	    		re = re.concat(a);
	    	}
	    	//处理情况
	    	if(null != isDeal && '' != isDeal){
	    		var a = "{'field':'isDeal','op':'eq','data':'"+ isDeal +"'},";
	    		re = re.concat(a);
	    	}
	    	//订单发货方式
	    	if(null != sendGoodsType && '' != sendGoodsType){
	    		var a = "{'field':'sendGoodsType','op':'eq','data':'"+ sendGoodsType +"'},";
	    		re = re.concat(a);
	    	}
	    	//modify by wld 2016-9-27 新增查询条件 是否下载
	    	if(null != is_Export && '' != is_Export){
	    		var a = "{'field':'isExport','op':'eq','data':'"+ is_Export +"'},";
	    		re = re.concat(a);
	    	}
	    	//获取排序方式
	    	var sortFiled = getOderSort();
	    	re = re.concat(sortFiled);
	    	
    		re = re.concat(queryOrdertypeQC);
	    	//去掉最后一个，符号
	    	if(re.length > 30){
				re = re.substr(0,re.length-1);
			}
	    	var z= "]}";
	    	re = re.concat(z);
	    	return re;
		}
		
		//搜索事件
		$("#clickBtn").click(function(){
			//清除数据
			$("#sendGoodsType").val('');
	 		search();
	 	});
		// 采购商改变事件，重新加载仓位和表单
		$("#pur_all").change(function(){
	 		loadData($(this).val());
	 	});
		
		// 仓位改变事件
	 	$("select[name='dc']").change(function(){
	 		reloadDcInfo();
	 	});
		
	 	//查询数据
	 	function search(){
	 		var purchaserId = $("#pur_all").val();
	 		if(null == purchaserId || '' == purchaserId || undefined== purchaserId){
	    		layer.msg("请选择采购商",{icon:0});
	    		return;
	    	}
	 		var re = queryData();
	 		jQuery("#table").jqGrid('setGridParam',{datatype:'json',page:1,postData:{query_type:"JQGRID",reqData:re}}).trigger("reloadGrid");
	 	}
	 	
		//重新加载数据
		function loadData(purchaserId){
			var flag = getDC(purchaserId);  //加载仓位
			if(null == purchaserId || '' == purchaserId || undefined == purchaserId){
				jQuery("#table").jqGrid('clearGridData');
				//清除仓位下拉数据
				var obj=document.getElementById('dc_all');
				obj.options.length=0; 
				//清理前一个月待处理标识
				$("#month1ago").text(0);
				$("#month1ago").hide();
				return;
			}
			if(flag){
				var re = queryData();
				jQuery("#table").jqGrid('setGridParam',{datatype:'json',page:1,postData:{query_type:"JQGRID",reqData:re}}).trigger("reloadGrid");
			}
		}
		
		//获取仓位
		function getDC(purchaserId){
			var flag = false;
			var wareHouse = $("#wareHouse").val();
	 		if(null == purchaserId || '' == purchaserId){
	 			$("#paddr").text("");
				$("#contact").text("");
				$("#contactNumber").text("");
				return;
	 		}
	 		var url=appPath+"/backto/system/json/getDCAndOrderCount";//仓位
	 		$.ajax({
				type : "POST",
				url:url,
				dataType : "json",
				async:false,
				data: {"purId" : purchaserId},
				success : function(data) {
					var options = "<option value='' selected='selected'>全部</option> ";
					var wareHouseArr = wareHouse.split(',');
					if(data){
						for(var i=0;i<data.length;i++){
							var sel = "";
							var isEqualHouse = false;
							if(wareHouse != ""){
								for(var j in wareHouseArr){
									if(data[i].code.indexOf(wareHouseArr[j])>=0){
										isEqualHouse = true;//包含
									}else{
										isEqualHouse = false;//不包含
										break;
									}
								}
							}
							if(isEqualHouse){
								sel = 'selected="selected"';
							}
							options += "<option value='"+data[i].code+"' "+sel+" paddr='"+data[i].paddr+"' contact='"+data[i].contact+"' contactNumber='"+data[i].contactNumber+"'>"+data[i].name+" "+data[i].dataNumbs+"</option>";
							if(isEqualHouse){
								$("#paddr").text((data[i].paddr == null || data[i].paddr == 'null')?"":data[i].paddr);
								$("#contact").text((data[i].contact == null || data[i].contact == 'null')?"":data[i].contact);
								$("#contactNumber").text((data[i].contactNumber == null || data[i].contactNumber == 'null')?"":data[i].contactNumber);
							}
						}
						$("#dc_all").html(options);
					}
					flag = true;
				},error : function() {
					layer.msg("获取仓位出错!",{icon : 0,time:2500});
				}
			});
	 		return flag;
	 	}
		
		//仓位重新加载
		function reloadDcInfo(){
	 		var selected = $("#dc_all").find("option:selected");
			var paddr = selected.attr("paddr");
			var contact = selected.attr("contact");
			var contactNumber = selected.attr("contactNumber");
			$("#paddr").text((paddr == null || paddr == 'null')?"":paddr);
			$("#contact").text((contact == null || contact == 'null')?"":contact);
			$("#contactNumber").text((contactNumber == null || contactNumber == 'null')?"":contactNumber);
			
			//查询
	        search();
	 	}
		
		/**
	 	 * 1个月前/1个月内
	 	 */
	 	$("a[name='send_Date']").click(function(){
	 		var startDt,endDt;
	 		var thisId = $(this).attr("id");
	 		var arr = thisId.split("_");
	 		var dt = arr[0];
	 		var num = arr[1];
	 		var type = arr[2];
 			if('' != dt && 'ago'==type){//一个月前
 				startDt = '';
 				endDt = GetDateStr(-30);
 			}else{//一个月内
 				var dd = new Date();
 				startDt = GetDateStr(-30);
 				endDt = GetDateStr(0);
 			}
 			$("#sendDateStart").val(startDt);
 			$("#sendDateEnd").val(endDt);
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
	 	
	 	//获取一个月以前的待处理品种
	 	function autoCountMonthAgo(){
	 		//获取采购商id
	 		var purchaserId = $("#pur_all").val();
	 		//获取一个月前的时间查询条件
	 		var endDt = GetDateStr(-30);
	 		$.ajax({
    			type:"post",
    			async:false,
    			url: appPath+'/backto/order/json/getUntreatedMonthAgo',
    			data: {
    				purchaserId:purchaserId,
    				sendDateEnd:endDt
    			} ,
    			dataType: "json", 
    			success: function(json){
    				if(json.success){
    					if(json.obj > 0){
    						$("#month1ago").text(json.obj);
    						$("#month1ago").show();
    					}else{
    						$("#month1ago").text(0);
    						$("#month1ago").hide();
    					}
    				}else{
    					layer.alert(json.msg, {
    						icon : 2
    					});
    				}
    			},
    			error: function(rsp){
    				layer.msg("1个月前待处理品种数量统计失败！",{icon:2});
    			}
    		});
	 	}
	 	
	 	function getOderSort(){
	 		var queryData="";
	 		var $this = $("#orderByBox").find(".active"),
	 		orderFile = $this.attr("data-value");
	 		sortRule = $this.attr("name");
	 		if(sortRule!=null && sortRule!=""){
	 			sortRule = "desc";
	 		}else{
	 			sortRule = "asc";
	 		}
	 		
	 		//订单发送日期-结束
	    	var queryFiled = "{'field':'sortOrderField','op':'eq','data':'"+ orderFile +"'},";
	    	queryData = queryData.concat(queryFiled);
	    	var querySortRule = "{'field':'sortOrderRule','op':'eq','data':'"+ sortRule +"'},";
	    	queryData = queryData.concat(querySortRule);
	 		return 	queryData;
	 	}
	 	
	 	/**
	 	 * 多订单查询
	 	 */
	 	$("#orderItems").click(function(){
	 		var ids = new Array();
	 		ids = $("#table").jqGrid("getGridParam","selarrrow");
	 		if(ids.length==0){
	    		layer.alert("请先勾选订单，再进行【多订单查询】操作!", {icon : 0});
	    		return;
	    	}else{
	    		var orderCodes="";
	    		$.each(ids, function(i, id) {
	    			var order  = $("#table").jqGrid("getRowData",id).orderCode;
	    			orderCodes +=order+",";
	    		})
	    		if ("," == orderCodes.charAt(orderCodes.length - 1)) {
	    			orderCodes = orderCodes.substr(0, orderCodes.length - 1);
				}
	    		//跳转到品种方式
	    		var purchaserId = $("#pur_all").val(),
	    		wareHouse = $("#dc_all").val();
	    		var url = "toOrderItems?purchaserId="+purchaserId+"&orderCodes="+orderCodes;
	    		if(wareHouse!=''){
	    			url +="&wareHouse="+wareHouse;
	    		}
	    		window.location.href=url;
	    	}
	 	});
	 	
	 // 修改导出状态函数(参数1：所选细目ids；参数2：刷新的方法名)
		function updateExportState(orderItemsIds,searchName){
			if('' != orderItemsIds){
				$.ajax({ 
					type:"post",
					url: appPath+'/backto/system/json/updateOrderExportState',
					data: {"orderIds":orderItemsIds},
					dataType: "json",
					success: function(data){
						if(data.success){
//							layer.msg("更新导出状态成功！",{icon:1});
							search();//更新成功都重新刷新列表页面
						}else{
							layer.alert("更新导出状态失败！",{icon:2});
						}
//						setTimeout(WG.loading.hide(),5*1000);
					},
					error: function(data){
						layer.msg("更新导出状态是发生异常！",{icon:2});
					}
				});
			}
		}
	 	
	 	//获取选中的id
		function getSelectIds(table){
			var selectedIds = $("#"+table).jqGrid("getGridParam", "selarrrow");
			 if(selectedIds.length < 1){
					layer.alert("请选择要导出的数据！", {icon:0});//0：弹框图片为叹号警告
					return "";
			}else{
				 var orderItemsIds = "";//订单细目id（多个值用逗号隔开）
				 for(var i = 0;i<selectedIds.length;i++){
					 var rowData = $("#"+table).jqGrid("getRowData", selectedIds[i]);
					 if($(rowData.isDeal).text() == "待处理" || $(rowData.isDeal).text() == "部分处理"){
						 orderItemsIds +=rowData.id+",";
					 }else{
						 continue;
					}
				 }
				 if ("," == orderItemsIds.charAt(orderItemsIds.length - 1)) {
					 orderItemsIds = orderItemsIds.substring(0,orderItemsIds.length-1);
				}
				 
				if(orderItemsIds.length == 0){
					layer.alert("已处理的数据不能导出！", {icon:0});//0：弹框图片为叹号警告
					return "";
				}
				 
				 return orderItemsIds;
			}
		}
		//导出处理函数
		function doExport(queryData,orderItemsIds,search,name,url){
			if(queryData!=""){
				$.ajax({ 
					type:"post",
					url: appPath+"/console/export/ajax/" + url,
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
							//导出后修改订单导出状态
							updateExportState(orderItemsIds.toString(),search);
						}else{
							layer.alert("导出文件失败！",{icon:2});
						}
					},
					error: function(data){
						WG.loading.hide();
						layer.msg("导出文件失败！",{icon:2});
					}
				});
			}
			
		}
	 	/**
	 	 * 导出按钮事件之---逐单导出 处理函数
	 	 * 2016-9-27 yangtao
	 	 */
	 	function exportOrderSingleExport(){
	 		var orderItemsIds = getSelectIds("table");
			if(orderItemsIds==""){
				return ;
			}
			var queryData = "{'groupOp':'AND','rules':[";
	   		
			var purchaserName = $("#pur_all").find("option:selected").text();
			
			var timeDay = new Date();
		    var name = purchaserName + "_采购订单发货_"+timeDay.getFullYear()+""+((timeDay.getMonth()+1)<10?"0"+(timeDay.getMonth()+1):(timeDay.getMonth()+1))+""+timeDay.getDate()+""+timeDay.getHours()+""+timeDay.getMinutes()+""+timeDay.getSeconds();
		    var a = "{'field':'excelName','op':'eq','data':'"+name+"'},";
			queryData = queryData.concat(a);
			//获取排序方式
	    	var sortFiled = getOderSort();
	    	queryData = queryData.concat(sortFiled);
			//所选行ids
			var a = "{'field':'itemsIds','op':'eq','data':'"+orderItemsIds.toString()+"'}";
				queryData = queryData.concat(a);
			var f= "]}";
			queryData = queryData.concat(f);
			doExport(queryData,orderItemsIds,"search",name,"exportOrderSingle/excel");//导出处理操作
	 	}
	 	/**
	 	 * 合单导出 事件处理函数 yangtao 2016-9-29
	 	 */
	 	function exportOrderMultipleExport(){
	 		var orderItemsIds = getSelectIds("table");
			if(orderItemsIds==""){
				return ;
			}
			var queryData = "{'groupOp':'AND','rules':[";
	   		
			var purchaserName = $("#pur_all").find("option:selected").text();
			
			
			var timeDay = new Date();
		    var name = purchaserName + "_采购订单发货_"+timeDay.getFullYear()+""+((timeDay.getMonth()+1)<10?"0"+(timeDay.getMonth()+1):(timeDay.getMonth()+1))+""+timeDay.getDate()+""+timeDay.getHours()+""+timeDay.getMinutes()+""+timeDay.getSeconds();
		    var a = "{'field':'excelName','op':'eq','data':'"+name+"'},";
			queryData = queryData.concat(a);
			//获取排序方式
	    	var sortFiled = getOderSort();
	    	queryData = queryData.concat(sortFiled);
			//所选行ids
			var a = "{'field':'itemsIds','op':'eq','data':'"+orderItemsIds.toString()+"'}";
				queryData = queryData.concat(a);
			var f= "]}";
			queryData = queryData.concat(f);
			doExport(queryData,orderItemsIds,"search",name,"exportOrderMultiple/excel");//导出处理操作
	 	}
});

/**
	 * 保存查询条件
	 */
	function saveSearch(){
		//查询条件保存
		var data={};
		var purchaserId = $("#pur_all").val(),
		dc = $("#dc_all").val(),
		orderCode = $("#orderCode").val(),
		orderType = $(".AllSelect .selectData").val(),
		sendDateStart = $("#sendDateStart").val(),
		sendDateEnd	 = $("#sendDateEnd").val(),
		is_Export = $("#is_export").val(),
		sendGoodsType = $("#sendGoodsType").val();
		
		var isNewId = $(".isNew .selectData").val();
		urgentFlagId = $(".urgentFlag .selectData").val();
		isDealId = $(".AllSelectDDCL .selectData").val();
		if(purchaserId!='' && purchaserId!=null){
			data["pur_all"] = purchaserId;
		}
		data["dc_all"] = dc;
		data["orderCode"] = orderCode;
		data["orderType"] = orderType;
		data["isNew"] = isNewId;
		data["urgentFlag"] = urgentFlagId;
		data["isDeal"] = isDealId;
		data["sendGoodsType"] = sendGoodsType;
		data["is_Export"] = is_Export;
		
		if(sendDateStart!='' && sendDateStart!=null){
			data["sendDateStart"] = sendDateStart;
		}
		if(sendDateEnd!='' && sendDateEnd!=null){
			data["sendDateEnd"] = sendDateEnd;
		}
		var str = JSON.stringify(data);//json转字符串
		sessionStorage[userName+"summary"]=str; //存入localStorage
	}
	
	
//跳转到订单详情
	function toOrderDetail(id,isDeal){
		saveSearch();
		var purchaserId = $("#pur_all").val();
		window.location.href=appPath + "/backto/order/page/toOrderDetailsProcessing?id="+id+"&purchaserId="+purchaserId+"&isDeal="+isDeal+"&type=summary";
	}