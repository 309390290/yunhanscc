/**add by yxp 2016-02-19 发货单查询及修改js*/
var modID_Tmp = "";//用于临时保存 需要修改的ID。
var isMod = false;//用于表示表格是否可以修改，true 可以修改 false 不可以修改
In.ready('jqGrid','select',function() {
	var regzd=new RegExp("\\\\","gm");  //正则表达式 

	var supplierId = sapvendorId;
	var userCode = userName;
	var  purchaserId=$("#constantName").val();
	var newPurchaserId=$("#purchaserId").val();
	//alert(purchaserId);
	//alert(newPurchaserId);
	if(purchaserId.indexOf(newPurchaserId)>=0){
		var ui =document.getElementById("addSendGoods");
		if(ui!=null){
			ui.style.visibility="visible";

		}
	}
	var bmz_yz=[];
	//加载常量
	$.ajax({
		type:"get",
		async:false,
		url:appPath+'/backto/constant/json/getConstantByCode', 
		data: {typeCode:"BMZ"} ,
		dataType: "json", 
		success: function(data){
			bmz_yz = data.obj;
		},
		error: function(data){
		  alert("error:获取数据异常");
		}
	});
	/**
  	 * 检查是否最后一次发货 若不是则给出相应提示
  	 */
  	function inspectSend(id,proPurOrderItemsId){
  	//获取该发货单是否是最近一次回告。是最近一次回告的才可以编辑
		return $.ajax({
				url:appPath+"/backto/sendQuery/isLastResone",
				type:"POST",
				dataType:"json",
				async: false,
				data:{"id":id,//发货单ID 
						"proPurOrderItemsId":proPurOrderItemsId//订单ID
					 },
				success:function(data){
					if(data.success){
						isMod = data.obj.frist;
						if(data.obj.last == true){
						//弹出对话框
							return true;
						}else{
							layer.alert("因商品发货后，又进行回告、发货处理！不能直接修改删除记录", {icon:0});//0：弹框图片为叹号警告
							return false;
						}
					}else{
						layer.alert("因商品发货后，又进行回告、发货处理！不能直接修改删除记录", {icon:0});//0：弹框图片为叹号警告
						return false;
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					layer.alert("因商品发货后，又进行回告、发货处理！不能直接修改删除记录", {icon:0});//0：弹框图片为叹号警告
					flag=true;
					return false;
				}
		     });
  	}
	$("#table_list").jqGrid({ 
	    url:appPath+'/console/query',
		autowidth: true, //自适应宽度
 		shrinkToFit: true, //列自适应
		datatype: "json", //数据格式
		mtype:"POST",
		colNames:["ID","status","采购商id","发货单号","云汉订单号","采购订单号","主发标示","定价","订单细目id","订单号","ISBN","商品名称","发货价","发货折扣（%）","发货数","行项操作"],// the column header names  
	    colModel :[   
              {name:"id",index:"id",hidden:true}, 
              {name:"status",index:"status",hidden:true}, 
              {name:"purchaserId",index:"purchaserId",hidden:true}, 
              {name:"sendoutGoodsCode", index:"sendoutGoodsCode",hidden:true},
              {name:"yunhanOrderCode", index:"yunhanOrderCode",hidden:true},
              {name:"purchaseOrderCode", index:"purchaseOrderCode",hidden:true},
              {name:"isSupplierAddProduct", index:"isSupplierAddProduct",hidden:true},
              {name:"price", index:"price",hidden:true},
              {name:"proPurOrderItemsId", index:"proPurOrderItemsId",hidden:true},
			  {name:"purchaseOrderCodeSpan", index:"purchaseOrderCodeSpan",formatter : function(value, options, rData) {
				  //主发品种标识：Y-主发、1-订单发货、2-订单增发
				  //alert(rData.isInitiative);
				  if(rData.isSupplierAddProduct=="Y" ){
					  return "<i class=\"top_ico\">供应商发货添加</i>";
				  }else if(rData.isInitiative=="Y" ){
					  return "<i class=\"top_ico\">主发</i>";
				  }else if(rData.purchaseOrderCode==null){
//					  return "<span class='icon-info-sign icon-red'></span> <span class='fontblue'>供应商发货添加</span>";
					  return ""; //2016年4月6日17:01:39  冯劲弢确认 历史数据 和接口数据 若不是主发 订单号又为空则直接展示为空
				  }else {
					var str="";
					if(rData.urgentFlag=="1"){
					  str+="<i class='top_ico'>急单</i>"
				     }
					if(rData.orderType==0){
						  str+="<i class='top_ico'>零售订单</i>"
					 }else if(rData.orderType==5){
						  str+="<i class='top_ico'>文教订单</i>"
					 }else if(rData.orderType==10){
						  str+="<i class='top_ico'>电商订单</i>"
					 }else if(rData.orderType==15){
						  str+="<i class='top_ico'>大中专订单</i>"
					 }else if(rData.orderType==20){
						  str+="<i class='top_ico'>团购订单</i>"
					 }else if(rData.orderType==25){
						  str+="<i class='top_ico'>馆配订单</i>"
					 }else if(rData.orderType==30){
						  str+="<i class='top_ico'>活动订书</i>"
					 }
					return str+rData.purchaseOrderCode;
				  }
			  },width:'12%',align:'center'},   
			  {name:"isbn", index:"isbn",width:'12%',align:'center'}, 
			  {name:"bookTitle", index:"bookTitle",width:'30%',align:'center'}, 
			  {name:"sendoutPrice", index:"sendoutPrice",width:'12%',formatter : function(value, options, rData) {
				  if(rData.isSupplierAddProduct=="Y" ){
					  return "<input type='text' style='width:80%' id='"+rData.id+"_sp' value='"+value+"'>";
				  }else{
					  return value;
				  }
				  
			  },align:'center'}, 
			  {name:"sendoutDiscountrate", index:"sendoutDiscountrate",formatter : function(value, options, rData) {
				 
				  if(rData.isSupplierAddProduct=="Y" ){
					  return "<input type='text' class='sendoutDiscountrate' style='width:80%' id='"+rData.id+"_sd' value='"+value+"'>";
				  }else{
					  return value;
				  }
			  },width:'12%',align:'center'}, 
			  {name:"sendoutQty", index:"sendoutQty",width:'12%',formatter : function(value, options, rData) {
				  if(rData.isSupplierAddProduct=="Y" ){
					  return "<input type='text' style='width:80%' id='"+rData.id+"_sq' value='"+value+"'>";
				  }
				  else{
					  return value;
				  }
			  },align:'center'},
			  {name:"edit", index:"行项操作",formatter : function(value, options, rData) {
				  var yunhanOrderCode=rData.yunhanOrderCode==null?"":rData.yunhanOrderCode;
				  if(rData.isSupplierAddProduct=="Y" ){//供应商发货添加
					  return "<a href='javaScript:void(0);' proPurOrderItemsId='"+ rData.proPurOrderItemsId +"' orderCode=\""+yunhanOrderCode+"\" purchaserId=\""+rData.purchaserId+"\"  id=\""+rData.id+"\" data-status=\""+rData.status+"\" class='btn btn-mini del-mini-btn'>删 除</a>";
				  }else{
					  return "<a href='javaScript:void(0);' proPurOrderItemsId='"+ rData.proPurOrderItemsId +"' orderCode=\""+yunhanOrderCode+"\" purchaserId=\""+rData.purchaserId+"\"  id=\""+rData.id+"\" data-status=\""+rData.status+"\" class='btn btn-mini mod-mini-btn'>修 改</a>" + " <a href='javaScript:void(0);' proPurOrderItemsId='"+ rData.proPurOrderItemsId +"' orderCode=\""+yunhanOrderCode+"\" purchaserId=\""+rData.purchaserId+"\"  id=\""+rData.id+"\" data-status=\""+rData.status+"\" class='btn btn-mini del-mini-btn'>删 除</a>";
				  }
			  },width:'10%',align:'center'}
			],
	   	rowNum: 50,  //默认分页行数
		rowList:[50,100],  //可选分页行数
		pager: '#page',  //分页按钮展示地址
		sortname: 'id',  //默认排序字段
		viewrecords: true,   //是否显示数据总条数
		multiselect:false,  //复选框
		sortorder: "desc",  //默认排序方式
		jsonReader: {repeatitems : false}, //设置数据方式
		postData: {query_id:"queryProSendoutItems",query_type:"JQGRID",rdParseType:"dispersed", reqData:queryData()}, 
		height: '100%', 
		/* 客户端排序--------------------------------------------------------开始 */
		loadBeforeSend:function(){
             $(this).jqGrid('clearGridData');
	    },
	    loadError:function(){
	    	layer.msg("系统报错！",{icon:5});
	    },
		onPaging : function(pgButton){
			$(this).jqGrid('setGridParam',{datatype:'json',postData:{reqData:queryData(),_search1:false}});
		}, 
		loadComplete : function(data) {
			$(".del-mini-btn").click(function(){
	  			var id = $(this).attr("id");
	  			var yunhanOrderCode=$(this).attr("orderCode");
	  			var purchaserId=$(this).attr("purchaserId");
	  			var status=$(this).attr("data-status");
	  			var proPurOrderItemsId = $(this).attr("proPurOrderItemsId");
	  			if(status=="10"){
	  				layer.alert("商品已收货，不能修改、删除！", {icon:0});
	  				return;
	  			}else if(status=="15"){
	  				layer.alert("商品已收货，不能修改、删除！", {icon:0});
	  				return;
	  			}else if(inspectSend(id,proPurOrderItemsId)){
	  				
	  				var data=inspectSend(id,proPurOrderItemsId).responseText;
  					data=JSON.parse(data);
  					//console.log("data.obj.last------------->>>"+data.obj.last);
  					//console.log("data.success-------------->>>>>"+data.success);
  					if(data.obj.last&&data.success){
  						delItems(id);
  					}
		  			
	  			}
	  			
	  		});
			
			$(".mod-mini-btn").click(function(){
				var id = $(this).attr("id");
				var proPurOrderItemsId = $(this).attr("proPurOrderItemsId");
	  			var yunhanOrderCode=$(this).attr("orderCode");
	  			var purchaserId=$(this).attr("purchaserId");
	  			var status=$(this).attr("data-status");
	  			if(status=="10"){
	  				layer.alert("商品已收货，不能修改、删除！", {icon:0})
	  				return;
	  			}else if(status=="15"){
	  				layer.alert("商品已收货，不能修改、删除！", {icon:0})
	  				return;
	  			}else{
	  				modID_Tmp = id;
	  				//获取该发货单是否是最近一次回告。是最近一次回告的才可以编辑
	  				if(inspectSend(id,proPurOrderItemsId)){
	  					//console.log(inspectSend(id,proPurOrderItemsId).responseText);
	  					var data=inspectSend(id,proPurOrderItemsId).responseText;
	  					data=JSON.parse(data);
	  					//console.log(data.obj);
	  					if(data.obj.last&&data.success){
	  						$("#modModal").modal('show');
	  						
	  					}
	  					
	  				}
	  			}
			});
			$('.sendoutDiscountrate').keyup(function(){
				var sendoutDiscountrate=$(this).val();
				if(sendoutDiscountrate == '' || isNaN(sendoutDiscountrate)){
          			layer.alert("发货折扣必须为数字", {icon:2});
          			$(this).val("");
          			return;
          		}
          		if(sendoutDiscountrate <=0){
          			layer.alert("发货折扣不能小于等于0", {icon:2});
          			$(this).val("");
          			return;
          		}
          		if(sendoutDiscountrate >100){
          			layer.alert("发货折扣不能大于100", {icon:2});
          			$(this).val("");
          			return;
          		}
				
			});
			
			
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
		}
		/* 客户端排序 --------------------------------------------------------结束*/
	});
  	$("#table_list").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });  //jqgrid取消掉水平滚动条
  	
  	
  	$("#modtab").jqGrid({
			url:appPath+'/console/query',
			autowidth: true, //自适应宽度
			shrinkToFit: true, //列自适应
			datatype: "local", //数据格式
			mtype:"POST",
		    colNames:["id","回告订单ID","订单细目ID","订数","已发数","已收数","本次发货数","本次发货数(隐藏)","其余满足情况","其余预计发货日期","备注","发货价","发货折扣"],// the column header names  
		    colModel :[   
		          {name:"id", index:"id", hidden:true},
		          {name:"responseId", index:"细目ID", hidden:true,formatter : function(value, options, rData) {
		        	  return "<input value='"+value+"' id='" + rData.id + "_responseId'/>";
				  }},
				  {name:"proPurOrderItemsId", index:"细目ID", hidden:true,formatter : function(value, options, rData) {
		        	  return "<input value='"+value+"' id='" + rData.id + "_proPurOrderItemsId'/>";
				  }},
		          {name:"orderQty", index:"订数",width:'16%',align:'center',formatter : function(value, options, rData) {
					  return "<span id='" + rData.id + "_orderQty'>" + value + "</span>";
				  }},
				  {name:"historyOrderQty", index:"已发数",width:'16%',align:'center',formatter : function(value, options, rData) {
					  return "<span id='" + rData.id + "_historyOrderQty'>" + value + "</span>";
				  }}, 
				  {name:"receiveQty", index:"已收数",width:'16%',align:'center',formatter : function(value, options, rData) {
					  if(value == null){
						  value = 0;
					  }
					  return value;
				  }},
				  {name:"thisSendQty", index:"本次发货数",width:'16%',align:'center',formatter : function(value, options, rData) {
					  if(value == null){
						  value = "";
					  }
					  return "<input style='width:80%'   value='"+value+"' id='" + rData.id + "_sendoutQty'/>";
				  }}, 
				  {name:"本次发货数", index:"本次发货数",width:'16%',align:'center', hidden:true,formatter : function(value, options, rData) {
					  if(value == null){
						  value = "";
					  }
					  return "<input style='width:80%'   value='"+rData.thisSendQty+"'  id='" + rData.id + "_sendoutQtyHistory'/>";
				  }}, 
				  {name:"otherAvailableReason", index:"其余满足情况",width:'25%',align:'center',formatter : function(value, options, rData) {
					  	var zt = 1;
					  	if(value == null){
					  		value = "";
					  	}
//     				if(parseInt(rData.orderQty)==parseInt(rData.availableQty)){
//     					zt = 1;
//    				}
					  	return "<select selectVal="+value+" data-id='"+rData.id +"' id='bmz_"+rData.id+"' style='width:80%'  name='bmz'  flag ='"+zt+"' ><option value=''>====</option></select>";	     			
				  }},
				  {name:"preSendDate", index:"其余预计发货日期",width:'25%',align:'center',formatter : function(value, options, rData) {
					if(value == null){
					  value = "";
				  }else{
					  value = value==null ? '':value.substr(0,10);
				  }
					return "<input class='Wdate query_data_all' style='width:80%' onfocus='WdatePicker()' type='text' value='"+value+"' id='" + rData.id + "_preSendDate'>"
				  }},
				  {name:"remark", index:"备注",width:'25%',align:'center',formatter : function(value, options, rData) {
					if(value == null){
					  value = "";
				  }
					  return "<input style='width:80%'   value='"+value+"' id='" + rData.id + "_remark'/>";
				  }},
				  {name:"sendoutPrice", index:"发货价",width:'16%',align:'center',formatter : function(value, options, rData) {
					if(value == null){
					  value = "";
					}
					if(isMod == true){
						return "<input style='width:80%'   value='"+value+"' id='" + rData.id + "_sendoutPrice'/>";
					}else{
						return value;
					}
				  }},
				  {name:"sendoutDiscountrate", index:"发货折扣",width:'16%',align:'center',formatter : function(value, options, rData) {
					if(value == null){
					  value = "";
				  }
					  return "<input style='width:80%'   value='"+value+"' id='" + rData.id + "_sendoutDiscountrate'/>";
				  }},
				],// the column discription    isQueryData
			postData:{query_id:"findSendOrderByID",query_type:"JQGRID",rdParseType:"dispersed"} ,  
			jsonReader : {
				repeatitems: false       //这里要设置为false，否则解析不了返回数据
			},
			loadComplete:function(data){
				$(this).setGridWidth(820);
				$(this).parents('#modModal').eq(0).find('.ui-jqgrid-bdiv').width('821');
				$('#gbox_modtab').width('822');
				
				/**
				 * 满足情况
				 */
		    	 $("select[name='bmz']").each(function(){
					   var selectVal = $(this).attr("selectVal");
					  // alert(selectVal);
					   var flag = $(this).attr("flag");
					   selectVal = (selectVal == null || selectVal == '')?1:selectVal;
					  // selectVal = flag==1?"":selectVal;
					  // alert(selectVal);
					  $(this).select({
		 					/*url: appPath+'/backto/constant/json/getConstantByCode', //请求地址
		 					para: {typeCode:'BMZ'},*/
						  	localdata:bmz_yz,
		 					selected: selectVal //默认选中的值
		 				});
				   });
				
		    	 
		    	//获取ids
		            var arr=$(this).jqGrid('getDataIDs'); 
		            for(var j=0;j<arr.length;j++){
		            	//初始化状态
		            	var id= arr[j]; 
		            	//alert(id);
		            	var orderQtyInt = $("#" + id + "_orderQty").html();
		            	var amountInt=$("#" + id + "_sendoutQty").val();
		            	if(parseInt(amountInt)>=parseInt(orderQtyInt)){
							$("#modtab").find("#bmz_"+id).attr("value", '');
							$("#modtab").find("#bmz_"+id).attr("disabled", true);
						}
		            	
		            	// 订数改变事件
		            	$('#'+arr[j]+'_sendoutQty').keyup(function(){
		            		
		            		//var id = $(this).attr("id").substring($(this).attr("id").indexOf("_")+1);
		            		//var id = arr[j];
		            		//alert(id);
		            		var amount=$(this).val();
		            		var sendQtyTemp = $("#modtab").find("#"+id+"_historyOrderQty").html();
		            		var reg = /^(0|([1-9]{1}[0-9]{0,7}))$/;
		            		var reg1 = /^[0-9]*$/;
							if (!reg.test(amount) && amount!='') {
								if(reg1.test(amount)){
									layer.msg("本次发货数只能输入最多 8 位整数!",{icon : 0,time:2000});
									$(this).val(sendQtyTemp);
									return;
								}else{
									layer.msg("请正确填写本次发货数!",{icon : 0,time:2000});
									$(this).val(sendQtyTemp);
									return;
								}
							}
		            
							var _proPurOrderItemsId= $("#" + id + "_proPurOrderItemsId").val();
			            	//alert(_proPurOrderItemsId);
			            	if(_proPurOrderItemsId=="null"){
			            		return;
			            	}
		            	//判断发货数与订数-已发数关系
						var rowData = $("#modtab").jqGrid("getRowData",id);
						//订数
				  		var orderQty = $("#" + id + "_orderQty").html();
				  		var sendoutQty = $("#" + id + "_sendoutQty").val();//本次发货数
				  		//已发数
				  		var receiveQty = $("#" + id + "_sendoutQtyHistory").val();
				  		//历史发货数
						var sendoutQtyHistory=$("#" + id + "_historyOrderQty").html();
							//sendoutQty > (parseInt(orderQty * 1.3) - parseInt(receiveQty)+parseInt(sendoutQtyHistory))
						var unsendoutQty = parseInt(orderQty)- parseInt(receiveQty);
						var maxUnsendoutQty = parseInt(orderQty) * 1.3- parseInt(receiveQty);
						//console.log("sendoutQty=============>>"+sendoutQty);
						//console.log("orderQty * 1.3==================>>"+parseInt(orderQty * 1.3));
						//console.log("parseInt(receiveQty)==========>>"+parseInt(receiveQty));
						//console.log("parseInt(sendoutQtyHistory)=============>>"+parseInt(sendoutQtyHistory));
						if( sendoutQty <= 0||sendoutQty > (parseInt(orderQty * 1.3) - parseInt(receiveQty)+parseInt(sendoutQtyHistory))){
				  			layer.alert("修改范围：0<本次发货数<=订数×130%-已发数", {icon:2});
				  			$(this).val(sendQtyTemp);
				  			return;
				  		}
						/*if(sendoutQty <= 0 ){
							$(this).val(sendQtyTemp);
				  			return;
						}*/
						//本次发货数>=订数-已发数，“其余满足情况”置为空，不可编辑
/*						if(amount>maxUnsendoutQty){
//							layer.msg("本次发货数不能超过未发数的130%!",{icon : 0,time:1800});
							//setState(id,true,1);
							$("#modtab").find("#bmz_"+id).attr("value", '');
							$("#modtab").find("#bmz_"+id).attr("disabled", true);
//							$(this).val(sendQtyTemp);
							return;
						}*/
						var  qtySend=parseInt(orderQty * 1.3) - parseInt(receiveQty)+parseInt(sendoutQtyHistory);
						//console.log("本次发货数---------------->>>>"+amount);
						//console.log("parseInt(orderQty * 1.3)------------->>>"+parseInt(orderQty * 1.3));
						//console.log("parseInt(receiveQty)+)------------->>>"+parseInt(receiveQty));
						//console.log("parseInt(sendoutQtyHistory)------------->>>"+parseInt(sendoutQtyHistory));
						//console.log("订数×130%-已发数;---------------->>>>"+qtySend);
						////console.log("判断结果-------------》》》"+0<=amount<=qtySend);
						//console.log("订数-------------》》》"+orderQty);
						
						if(parseInt(amount)>=parseInt(orderQty)){
							$("#modtab").find("#bmz_"+id).attr("value", '');
							$("#modtab").find("#bmz_"+id).attr("disabled", true);
						}else if(0<=amount<=qtySend){//0<=本次发货数<订数-已发数，自动默认“其余满足情况=暂时缺货”，可修改
							$("#modtab").find("#bmz_"+id).attr("disabled", false);
							$("#modtab").find("#bmz_"+id).attr("value", 1);
							//alert(11);
						}
		            });
		            		
		            	
		            	
		            	$('#'+arr[j]+'_sendoutDiscountrate').keyup(function(){
		            		var sendoutDiscountrate = $("#" + id + "_sendoutDiscountrate").val()//发货折扣
		            		if(sendoutDiscountrate == '' || isNaN(sendoutDiscountrate)){
		              			layer.alert("发货折扣必须为数字", {icon:2});
		              			 $("#" + id + "_sendoutDiscountrate").val("");
		              			return;
		              		}
		              		if(sendoutDiscountrate <=0){
		              			layer.alert("发货折扣不能小于等于0", {icon:2});
		              			 $("#" + id + "_sendoutDiscountrate").val("");
		              			return;
		              		}
		              		if(sendoutDiscountrate >100){
		              			layer.alert("发货折扣不能大于100", {icon:2});
		              		 $("#" + id + "_sendoutDiscountrate").val("");
		              			return;
		              		}
		            		
		            	});
		            	
						//本次发货数改变关联事件
						if($("#modtab").find("#"+id).attr("aria-selected")=="true"){//勾选需要改变合计
							var totalAmount = amount-sendQtyTemp;
							price = $("#modtab").find("#price_"+id).val();
							discountrate =$("#modtab").find("#discountrate_"+id).val();
							totalSendQty += parseInt(totalAmount);
							totalSendPrice += parseInt(totalAmount) * Number(price);
							totalSendRealPrc = totalSendRealPrc*100 + parseInt(totalAmount) * Number(price) * Number(discountrate) ;
						}
						
						
					   	//其余满足情况改变事件
		            	$('#bmz_'+arr[j]+'').change(function(){
		            		var id = $(this).attr("id").substring($(this).attr("id").indexOf("_")+1);
		            		var optionVal = $(this).val();
		            		if(optionVal!=null && optionVal!=""){
		            			//去掉背景色和禁用的保存、操作按钮
		            			//setState(id,false,1);
		            			//其余满足情况=预计可发：其余预计发货日期必填，否则标红该行，不允许保存；
		            			if(parseInt(optionVal)==0){
		            				var preSendDate =$("#modtab").find("#preSendDate_"+id).val();
		            				if(preSendDate=="" || preSendDate==null){
		            					//setState(id,true,1);
		            				}
		            			}else if(parseInt(optionVal)>=1){
		            				var amount = $("#modtab").find("#sendQty_"+id).val();
		                			var rowData = $("#modtab").jqGrid("getRowData",id);
		        					var unsendoutQty = parseInt(rowData.orderQty)- parseInt(rowData.sendoutQty);
		            				if(amount>=unsendoutQty){
		            					//setState(id,true,1);
		            				}
		            			}
		            		}else{
		            			//0<=本次发货数<订数-已发数，其余满足情况不能为空
		            			var amount = $("#modtab").find("#sendQty_"+id).val();
		            			var rowData = $("#modtab").jqGrid("getRowData",id);
		    					var unsendoutQty = parseInt(rowData.orderQty)- parseInt(rowData.sendoutQty);
		    					if(amount!=''&&amount>=0&&amount<unsendoutQty){
		    						//setState(id,true,1);
		    					}else{
		                			//setState(id,false,1);
		    					}
		            		}
		            		
		            	});
		            	
		            	$('#'+arr[j]+'_preSendDate').click(function(){
		            		var id = $(this).attr("id").substring($(this).attr("id").indexOf("_")+0);
		            		var preSendDate = $(this).val(),
		            		availableReason = $("#modtab").find("#bmz_"+id).val();
		            		//日期控件添加监听事件
		            		WdatePicker(
		            				{onpicking:function(dp){//确定
		            					if(parseInt(availableReason)==0){
		            						setState(id,false,1);
		            					}
		            						
		            					},
		            				onclearing:function(){//清空
		            					if(parseInt(availableReason)==0){
		            						setState(id,true,1);
		            					}
		            						
		            					}
		            				}
		            		);
		            	});
		            }
			},
		    height:'100%',
			
		});
		jQuery("#modtab").jqGrid('navGrid','#modtab',{edit:false,add:false,del:false});
  	
  	function queryModData(modID_Tmp){
		if(modID_Tmp == ""){
			return;
		}
		var queryData="{'groupOp':'AND','rules':[";
		var a = "{'field':'id','op':'eq','data':'"+modID_Tmp+"'}";
		queryData = queryData.concat(a);
		var z= "]}";
		queryData = queryData.concat(z);
		return queryData;
	};
  	
  	$('#modModal').on('shown', function (e) {
  		$("#modtab").jqGrid('setGridParam', {
  			datatype : 'json',
  			page : 1,
  			postData : {
  				query_id : "findSendOrderByID",
  				query_type : "JQGRID",
  				reqData : queryModData(modID_Tmp)
  			}
  		}).trigger("reloadGrid");
  	});
  	
  	
  	/**
  	 * 查询参数 by zxc 
  	 */
  	function queryData(){
  		if(null==userCode || userCode == undefined ||userCode.length==0){
		    	layer.alert("获取身份失败，请刷新后重试！", {icon:0});//0：弹框图片为叹号警告
				return false;
		    }
  		var sendoutGoodsCode	= $("#sendoutGoodsCode").val().replace(regzd,'\\\\');
  		//alert(newPurchaserId);
  		var queryData="{'groupOp':'AND','rules':[";
  		var a = "{'field':'userCode','op':'eq','data':'"+ userCode +"'},";
		queryData = queryData.concat(a);
		var supplierId = "{'field':'supplierId','op':'eq','data':'"+ sapvendorId +"'},";
		queryData = queryData.concat(supplierId);
		var purchaserId= "{'field':'purchaserId','op':'eq','data':'"+ newPurchaserId +"'},";
		queryData = queryData.concat(purchaserId);
  		//发货单
  		if(null != sendoutGoodsCode && sendoutGoodsCode.length>0){
  			var sendoutGoodsCodeQC = "{'field':'sendoutGoodsCode','op':'eq','data':'"+ sendoutGoodsCode +"'},";
  			queryData = queryData.concat(sendoutGoodsCodeQC);
  		}else{
  			layer.msg("系统报错！",{icon:5});
  			return false;
  		}
  		if(queryData.length > 30){
  			queryData = queryData.substring(0,queryData.length-1);
		}
  		var z= "]}";
  		queryData = queryData.concat(z);
  		//console.log(queryData);
  		return queryData;
  	}

  	
  	/**
  	 * 查询操作 by zxc
  	 */
  	function search(){
  		var param = queryData();
  		if(param){
  			jQuery("#table_list").jqGrid('setGridParam',{datatype:'json',postData:{query_id:"queryProSendoutItems",query_type:"JQGRID",reqData:param}}).trigger("reloadGrid");
  		}
  	}
  	
  	/**
	 * 查询细目列表
	 * 
	 */
	function searchTab() {
		jQuery("#table_list").jqGrid('setGridParam', {
			datatype : 'json',
			page : 1,
			postData : {
				query_id : "queryProSendoutItems",
				query_type : "JQGRID",
				reqData : queryData()
			}
		}).trigger("reloadGrid");
		
	}
    /**
	 * 删除操作 by wangtao
	 */
    function delItems(id){
    	 layer.confirm('确认后无法复原，是否确认删除行项？', {
    		zIndex:999,
 		    btn: ['确定','取消'] //按钮
 		}, function(){
 			WG.loading.show();
 			$.ajax({
				url:appPath+"/backto/sendQuery/delSendoutItems",
				type:"POST",
				dataType:"json",
				data:{"id":id},
				success:function(data){
					WG.loading.hide();
					if(data.success){
						searchTab();
						layer.alert("删除成功！",{icon:1,closeBtn:0},function(index){
							var obj = $("#table_list").jqGrid("getRowData");
							if(obj.length==0){
								window.location.href=appPath+"/backto/sendQuery/page/qureyModify";
							}
							layer.close(index);
		    			});
					}else{
						layer.alert("删除失败！",{icon:2},function(index){
		    				layer.close(index);
		    			});
					}
				},
				error: function(data){
					WG.loading.hide();
				}
		        });
 		},function(){
 			return;
 		});
    }
    
    /**
	 * 整单删除操作 by yxp
	 */
    function delBySendoutCode(){
    	 var sendoutGoodsCode	= $("#sgc").val();
    	 var summaryId	= $("#summaryId").val();
    	 var yunhanOrderCodes="";
    	 var rowIds =  $("#table_list").jqGrid('getDataIDs');
    	 for(var k = 0;k<rowIds.length;k++){
				var rowData = $("#table_list").jqGrid("getRowData", rowIds[k]);
				if(yunhanOrderCodes==""){
					yunhanOrderCodes=rowData.yunhanOrderCode;
				}else{
					yunhanOrderCodes+=","+rowData.yunhanOrderCode;
				}
		}
    	 layer.confirm('当前发货单即将作废，确认后无法复原，是否确认删除？', {
    		zIndex:999,
 		    btn: ['确定','取消'] //按钮
 		}, function(){
				$.ajax({
					url:appPath+"/backto/sendQuery/getRigthUpdateOrDeleteProSendSendItems",
					type:"POST",
					dataType:"json",
					data:{"ids":summaryId},
					success:function(data){
						if(data.success){
							//layer.alert("删除成功！", {icon:1});//1：弹框图片为勾
							//alert(data.obj);
							//search();
							if(data.obj){
								WG.loading.show();
								$.ajax({
			    					url:appPath+"/backto/sendQuery/delSendoutSummary",
			    					type:"POST",
			    					dataType:"json",
			    					data:{
			    						"ids":summaryId,
			    						"sendoutGoodsCode":sendoutGoodsCode,
			    						"yunhanOrderCodes":yunhanOrderCodes
			    						},
			    					success:function(data){
			    						WG.loading.hide();
			    						layer.alert("删除成功！",{icon:1,closeBtn:0},function(){
			    		    				window.location.href=appPath+"/backto/sendQuery/page/qureyModify"
			    		    			});
			    						},
			        					error: function(data){
			        						WG.loading.hide();
			        					}
			    					});
			    					
							}else{
								layer.alert("不是最后一次发货，不能删除", {icon:2});//2：弹框图片为叉叉
							}
							
						}else{
							layer.alert(data.msg, {icon:2});//2：弹框图片为叉叉
						}
					}
			        });
//				layer.msg("", {icon:1})
//				setTimeout(function(){;},3000);
     },function(){
   	   return;
      });
    }
    /**
     * 确认修改按钮处理事件 yangtao 2016-8-4
     */
    function sureModify(){
    	var rows = $("#table_list").jqGrid("getRowData");
		// 如果输入框有值，设置初始值
    	var items = [];  
    	var para = $("#summary").serializeArray();
    	//console.log(para);
		$.each(rows, function(i, row) {
		    var pro = {};
			var sendoutPrice = Number($("#"+row.id+"_sp").val());
			var sendoutDiscountrate = Number($("#"+row.id+"_sd").val());
			var sendoutQty = Number($("#"+row.id+"_sq").val());
			var remark=$("#"+row.id+"_remark").val();
			var preSendDate=$("#"+row.id+"_preSendDate").val();
			if(NaN != sendoutPrice && sendoutPrice >=0
				&& NaN != sendoutDiscountrate && sendoutDiscountrate >=0
				&& NaN != sendoutQty && sendoutQty >=0){
				if(row.id.indexOf("N")==-1){
					pro.id = row.id;
				}
				pro.sendoutPrice = sendoutPrice;
				pro.isbn = row.isbn;
				pro.purchaseOrderCode = row.purchaseOrderCode;
				pro.yunhanOrderCode = row.yunhanOrderCode;
				pro.bookTitle = row.bookTitle;
				pro.sendoutDiscountrate = sendoutDiscountrate;
				pro.isSupplierAddProduct=row.isSupplierAddProduct;
				pro.sendoutQty = sendoutQty;
				pro.sendoutGoodsCode = $("#sendoutGoodsCode").val();
				pro.price = row.price;
				pro.proPurOrderItemsId = row.proPurOrderItemsId;
				pro.supplierId=supplierId;
				pro.purchaserId=newPurchaserId;
				pro.remark=remark;
				pro.preSendDate=preSendDate;
				items.push(pro);
			} 
		});
		para.push({"name":"sendOutItemsJson",value:JSON.stringify(items)});
		para.push({"name":"supplierId",value:supplierId});
		para.push({"name":"purchaserId",value:newPurchaserId});
		$.ajax({
			url:appPath + "/backto/sendQuery/saveSendSummaryItems",
			type:"post",
			dataType:"JSON",
			data : para,
//			traditional:true,
			success : function(data) {
				if (data.success) {
					searchTab();
					layer.alert(data.msg, {
						icon : 1
					});
					 
				} else {
					layer.alert(data.msg, {
						icon : 2
					});
				}
			}
		});
    }
    
    /**发货添加商品*/
  	$(".add-btn").click(function(){
  		addProducts();
  	});
  	
  	$("#addSendGoods").click(function(){
  		$("#myModal3").modal('show');
  	});
  	/*document.getElementById("addSendGoods").onclick=function(){
  		$("#myModal3").modal('show');
  	}*/
  	/**确认修改*/
  	$(".sure-btn").click(function(){
  		layer.confirm("是否确认修改！",{icon:3},sureModify);
  	});
  	/**整单删除*/
  	$(".del-btn").click(function(){
  		delBySendoutCode();
  	});
  	/**返回*/
  	$(".back-btn").click(function(){
  		window.history.go(-1);
  		//var id=$("#summaryId").val();
  		//window.location.href=appPath+"/backto/sendQuery/page/detail?id="+id;
  	});
  	/**
  	 * 修改弹窗中的确认按钮事件 yangtao 2016-8-2
  	 */
  	$("#updateMod_but").click(function(){
  		//发货单号
  		var sendoutGoodsCode	= $("#sgc").val();
  		var supplierId = $("#supplierId").val();
  		var purchaserId = $("#purchaserId").val();
  		//订单细目ID
  		var proPurOrderItemsId = $("#" + modID_Tmp +"_proPurOrderItemsId").val();
  		//采购商供应商
  		var responseId = $("#" + modID_Tmp + "_responseId").val();
  		var sendoutQty = $("#" + modID_Tmp + "_sendoutQty").val();//本次发货数
  		var otherAvailableReason =  $("#bmz_" + modID_Tmp).val();//其余满足情况
  		var preSendDate = $("#" + modID_Tmp + "_preSendDate").val()//其余预计发货日期
  		var remark =  $("#" + modID_Tmp + "_remark").val()//备注
  		var sendoutPrice = $("#" + modID_Tmp + "_sendoutPrice").val()//发货价
  		var sendoutDiscountrate = $("#" + modID_Tmp + "_sendoutDiscountrate").val()//发货折扣
  		var sendoutQtyHistory=$("#" + modID_Tmp + "_sendoutQtyHistory").val()//发货折扣
  		//对数据进行验证 有可能没有回告ID？ 暂时注释对回告ID的验证
//  		if(responseId == ''){
//  			layer.alert("获取数据出错", {icon:2});
//  			return;
//  		}
  		//alert(sendoutQty);
  		if(sendoutQty == '' || isNaN(sendoutQty)){
  			layer.alert("发货数必须为数字", {icon:2});
  			return;
  		}
  		//订数
  		var orderQty = $("#" + modID_Tmp + "_orderQty").html();
  		
  		//已发数
  		var receiveQty = $("#" + modID_Tmp + "_historyOrderQty").html();
  		//alert(parseInt(orderQty * 1.3) - parseInt(receiveQty)+parseInt(sendoutQtyHistory));
  		
  		if (!typeof(sendoutPrice) == "undefined")
  		{
  			if(sendoutPrice == '' || isNaN(sendoutPrice)){
  	  			layer.alert("发货价必须为数字", {icon:2});
  	  			return;
  	  		}
  		}
  		
  		if(sendoutDiscountrate == '' || isNaN(sendoutDiscountrate)){
  			layer.alert("发货折扣必须为数字", {icon:2});
  			return;
  		}
  		if(sendoutDiscountrate <=0){
  			layer.alert("发货折扣不能小于等于0", {icon:2});
  			return;
  		}
  		if(sendoutDiscountrate >100){
  			layer.alert("发货折扣不能大于100", {icon:2});
  			return;
  		}
  		
  		if(proPurOrderItemsId!="null"){
  			
  			if(sendoutQty <= 0 || sendoutQty > (parseInt(orderQty * 1.3) - parseInt(receiveQty)+parseInt(sendoutQtyHistory))){
  	  			layer.alert("修改范围：0<=本次发货数<=订数×130%-已发数", {icon:2});
  	  			return;
  	  		}
  			
  			if(otherAvailableReason=="0"){
  	  			if(preSendDate==''){
  	  				layer.alert("当其余满足情况为‘预计可发’时，‘其余预计发货日期’不能为空", {icon:2});
  	  	  			return;
  	  			}
  	  		}
  		}
  		$.ajax({
  	   		url:appPath+"/backto/sendQuery/updateSendoutItems",
  	   		type:"POST",
  	   		dataType:"json",
  	   		async: false,
  	   		data:{
  	   			"id":modID_Tmp,
  	   			"responseId":responseId,
  	   			"sendoutQty":sendoutQty,
   				"otherAvailableReason":otherAvailableReason,
   				"preSendDate":preSendDate,
   				"remark":remark,
   				"sendoutPrice":sendoutPrice,
   				"sendoutDiscountrate":sendoutDiscountrate,
   				"sendoutGoodsCode":sendoutGoodsCode,
  	  			"supplierId":supplierId,
  	  			"purchaserId":purchaserId,
  	  			"proPurOrderItemsId":proPurOrderItemsId
   				},
  	   	    traditional: true,
  	   		success:function(data){
  	   			if(data.success){
					searchTab();
  	   				layer.alert("保存成功", {icon:1});
  	   			$("#modModal").modal('hide');
  	   			}else{
  	   				layer.alert(data.msg, {icon:2});//2：弹框图片为叉叉
  	   			}
  	   		},
  	   		error:function(XMLHttpRequest, textStatus, errorThrown){
  	   			layer.alert("保存失败", {icon:2});
  	   		}
  	      });
  	});
  	
});



/**   ------------------------------------增加发货商品列表开始   **/
In.ready('jqGrid',function(){
	$("#getSupplierName").text($("#supplierName").val());
	$("#tableb_1").jqGrid({
	  //data:datab_1,
	  url: appPath+'/console/query',
	  mtype:'post',
	  //autowidth: true, //自适应宽度
	  shrinkToFit: true, //列自适应
	  datatype: "local",
	  //datatype: "json",
	  colNames:["id","供应商id","商品名称","ISBN","定价","折扣（%）"],// the column header names  
	  colModel :[   
		  {name:"id", index:"id", hidden:true},  
		  {name:"supplierId", index:"supplierId", hidden:true},
		  {name:"bookTitle", index:"商品名称",width:'30%',align:'center',editable : true,editoptions : {style:"width:80%",size : 5}}, 
		  {name:"isbn", index:"ISBN",width:'30%',align:'center',editable : true,editoptions : {style:"width:80%",size : 5}},   
		  {name:"price", index:"订 价",width:'13%',align:'center',editable : true,editoptions : {style:"width:80%",size : 5}}, 
		  {name:"discountrate", index:"折扣（%）",width:'13%',editable : true,editoptions : {style:"width:80%",size : 5}}
		],// the column discription   
        rowNum:5,
        rowList:[5,10,15],
        multiselect : true,  //显示checkbox选择框
        rownumbers: true,    //显示左边排名列表 
		postData:{query_id:"findMasterDataInfoBySupplierIdBackto",query_type:"JQGRID",rdParseType:"dispersed",reqData:getQueryData()} ,  
		jsonReader : {
			repeatitems: false       //这里要设置为false，否则解析不了返回数据
 		},
        pager: '#pageb_1',
        height:'140',
		width:'530'
	});
	jQuery("#tableb_1").jqGrid('navGrid','#pageb_1',{edit:false,add:false,del:false});
	$("[data-toggle='popover']").popover();
	
	/**
	 * 查询操作 by yxp
	 */
	 $("#search_btn").off("click").on("click",function(){
		var param = getQueryData();
		if(param){
		 $("#tableb_1").jqGrid("setGridParam",{datatype:"json",page:1,postData:{query_id:"findMasterDataInfoBySupplierIdBackto",query_type:"JQGRID",reqData:param}}).trigger("reloadGrid");
		}
	})
 
	
	
	$("#tableb_2").jqGrid({
	    data:[],
	    // url: appPath+'/console/query',
	    //mtype:'post',
	    //autowidth: true, //自适应宽度
	    shrinkToFit: true, //列自适应
	    datatype: "local",
	    // datatype: "json",
	    colNames:["id","供应商id","商品名称","ISBN","发货价","发货折扣（%）","发货数"],// the column header names  
	    colModel :[   
	            {name:"id", index:"id", hidden:true},
	            {name:"supplierId", index:"supplierId", hidden:true},
			  {name:"bookTitle", index:"商品名称",width:'30%',align:'center',editable : false,editoptions : {style:"width:80%",size : 5},formatter : function(value, options, rData) {
				  var dataVal="<input style='width:80%'  value='"+value+"'  id='" + rData.id + "_bookTitle'/>";
				  if(rData.isQueryData) dataVal = "<span date-type='span' id='" + rData.id + "_bookTitle'>"+value+"</span>";
				  return dataVal}}, 
			  {name:"isbn", index:"ISBN",width:'25%',align:'center',editable : false,editoptions : {style:"width:80%",size : 5},formatter : function(value, options, rData) {
				  var dataVal="<input style='width:80%'    value='"+value+"' id='" + rData.id + "_isbn'/>";
				  if(rData.isQueryData) dataVal = "<span  date-type='span'  id='" + rData.id + "_isbn'>"+value+"</span>";
				  return dataVal}},   
			  {name:"price", index:"订 价",width:'13%',align:'center',editable : false,editoptions : {style:"width:80%",size : 5},formatter : function(value, options, rData) {
				  var dataVal="<input style='width:80%'   value='"+value+"' id='" + rData.id + "_price'/>";
				  return dataVal}}, 
			  {name:"discountrate", index:"折扣(%)）",width:'18%',editable : false,editoptions : {style:"width:80%",size : 5},formatter : function(value, options, rData) {
				  var dataVal="<input style='width:80%'   value='"+value+"'  id='" + rData.id + "_discountrate'/>";
				  return dataVal}},
			  {name:"sendOutQty", index:"发货数",width:'14%',align:'center',editable : false,editoptions : {style:"width:80%",size : 5},formatter : function(value, options, rData) {
				  var dataVal="<input style='width:80%' value='"+value+"'   id='" + rData.id + "_sendOutQty'/>";
				  return dataVal}},
			],// the column discription    isQueryData
	    rowNum:10,
	    rowList:[10,20,30],
	    multiselect : true,  //显示checkbox选择框
	    rownumbers: true,    //显示左边排名列表 
		postData:{query_id:"findMasterDataInfoBySupplierIdBackto",query_type:"JQGRID",rdParseType:"dispersed",reqData:getQueryData()} ,  
		jsonReader : {
			repeatitems: false       //这里要设置为false，否则解析不了返回数据
		},
	    pager: '#pageb_2',
	    height:'100%',
		width:'530',
		editurl:appPath+'/backto/orderDelivery/addSendGoodsList', // this is dummy existing url  
	});
	jQuery("#tableb_2").jqGrid('navGrid','#pageb_2',{edit:false,add:false,del:false});
	$("#addrow_btn").off("click").on("click",function () {
	    addRow();
	});
 function getQueryData(){
	 var isbn=$("#isbn").val();
	 var bookTitle=$("#bookTitle").val();
	 var supplierId=$("#supplierId").val();;//供应商ID  
	 var purchaserId="";//采购商ID
		var queryData="{'groupOp':'AND','rules':[";
  		var a = "{'field':'supplierId','op':'eq','data':'"+supplierId+"'},";
  		queryData = queryData.concat(a);
  	   //isbn
  		if(null != isbn && isbn.length>0){
  			var sendoutGoodsCodeQC = "{'field':'isbn','op':'eq','data':'"+ isbn +"'},";
  			queryData = queryData.concat(sendoutGoodsCodeQC);
  		}
  		//书名
  		if(null != bookTitle && bookTitle.length>0){
  			var receiveCompanyQC = "{'field':'bookTitle','op':'eq','data':'"+ bookTitle +"'},";
  			queryData = queryData.concat(receiveCompanyQC);
  		}
  		//采购商ID
  		if(null != purchaserId && purchaserId.length>0 ){
  			var sendoutStatusQC = "{'field':'purchaserId','op':'eq','data':'"+ purchaserId +"'},";
  			queryData = queryData.concat(sendoutStatusQC);
  		}
  	   //供应商ID
  		/* if(null != supplierId && supplierId.length>0 ){
  			var sendoutStatusQC = "{'field':'supplierId','op':'eq','data':'"+ supplierId +"'},";
  			queryData = queryData.concat(sendoutStatusQC);
  		} */
  		if(queryData.length > 30){
  			queryData = queryData.substring(0,queryData.length-1);
		}
  		var z= "]}";
  		queryData = queryData.concat(z);
  		return queryData;
 }

 
 
 		//添加空数据行
        var newrowid = 0 ;  
        function addRow(){  
            var ids = jQuery("#tableb_2").jqGrid('getDataIDs');  
            //获得当前最大行号（数据编号）  
            //console.log("ids"+ids)
            //var rowid = Math.max.apply(Math,ids);
            //var rowid = 0;
            //console.log("rowid"+rowid)
            //if(ids.length==0)rowid=0;
            //获得新添加行的行号（数据编号）  
            newrowid = newrowid+1;
            var intData=20160001;
            newrowid=newrowid+intData;
            newrowid=parseInt(newrowid)
            var dataRow = { 
            	id:newrowid,	
                isbn: "",  
                bookTitle:"",  
                price:'',  
                discountrate:'',  
                sendOutQty:'', 
            };
            //将新添加的行插入到第一列  
            $("#tableb_2").jqGrid("addRowData", newrowid, dataRow, "first");  
            //设置grid单元格不可编辑  
            $("#tableb_2").setGridParam({cellEdit:false});  
            //设置grid单元格可编辑  
            $("#tableb_2").jqGrid('editRow', newrowid, false);
            $("#"+newrowid+"_isbn").val("");
            $("#"+newrowid+"_bookTitle").val("");
            $("#"+newrowid+"_price").val("");
            $("#"+newrowid+"_discountrate").val("");
            $("#"+newrowid+"_sendOutQty").val("");
        }  
		//添加已查询数据行
        var newrowid2 ;  
        function addRow2(data){
            //循环数据
            //console.log(data)
            for	(var p = 0; p<data.length; p++){
            	//获得新添加行的行号（数据编号）
            	//newrowid2 = rowid+1;
            	var dataRow = {
                	id: data[p].id,	
                    isbn: data[p].isbn,  
                    bookTitle: data[p].bookTitle,  
                    price: data[p].price,  
                    discountrate: data[p].discount,  
                    sendOutQty: data[p].sendOutQty,
                    isQueryData: data[p].isQueryData,
                };
            	//console.log(dataRow);
                //将新添加的行插入到第一列  
                $("#tableb_2").jqGrid("addRowData", newrowid2, dataRow, "first");  
                //设置grid单元格不可编辑  
                $("#tableb_2").setGridParam({cellEdit:false});  
                //设置grid单元格可编辑  
                $("#tableb_2").jqGrid('editRow', newrowid2, false);
            }
        } 
        
        
        
        
        
      //添加已查询数据行
        var newrowid3 ;  
        function addRow3(data){
            //循环数据
            //console.log(data)
            for	(var p = 0; p<data.length; p++){
            	//获得新添加行的行号（数据编号）
            	//newrowid2 = rowid+1;
            	//alert(data[p].sendOutQty);
            	//console.log(data[p])
            	var dataRow = {
                	id: data[p].id+"N",	
                    isbn: data[p].isbn,  
                    bookTitle: data[p].bookTitle,  
                    sendoutPrice: data[p].price,  
                    sendoutDiscountrate: data[p].discount,  
                    sendoutQty: data[p].sendOutqty,
                    isQueryData: data[p].isQueryData,
                    price:data[p].price,
                    isSupplierAddProduct:"Y",
                    orderQty:"",
                    availableQty:"",
                    closeStatus:"",
                    purchaseOrderCode:"",
                };
            	//console.log(dataRow);
                //将新添加的行插入到第一列  
                $("#table_list").jqGrid("addRowData", newrowid3, dataRow, "first");  
                //设置grid单元格不可编辑  
                $("#table_list").setGridParam({cellEdit:false});  
                //设置grid单元格可编辑  
                $("#table_list").jqGrid('editRow', newrowid3, false);
            }
        } 
        
        
	        //确认按钮
	        $("#confirm_btn").off("click").on("click",function(){
	          var $inputs = $('#tableb_1').find("input");
	          $inputs.each(function(index,ele){
	            if($(this).val()=="") {
	              alert("请将信息填写完整");
	              $(this).focus();
	              return false;
	            }
	          })
	        });
	        
	        //删除选择数据
	        $("#deleteData_btn").off("click").on("click",function() {
	        	var moduleIds = jQuery("#tableb_2").jqGrid('getGridParam', 'selarrrow');
	        	if (moduleIds.length == 0) {
	        		layer.alert("请选择至少一条数据！");
	        		return;
	        	} 
	        	var newIds = [];
	        	
				for(var o = 0; o<moduleIds.length; o++){
					var rowData = jQuery("#tableb_2").jqGrid('getRowData',moduleIds[o])
					newIds.push(rowData.id)
				}
	    	    
	        	var arr3 = new Array();
	        	for(var i=0; i < ids.length; i++){   
	                var flag = true;   
	                for(var j=0; j < newIds.length; j++){   
	                    if(ids[i] == newIds[j]) 
	                    flag = false;   
	                }   
	                if(flag)   
	                arr3.push(ids[i]);   
	            }   
	        	ids=arr3;
	        	for(var q = moduleIds.length-1; q >= 0; q--){
	        		$("#tableb_2").jqGrid("delRowData", moduleIds[q]); 
	        	}        	
	    	  });   
	        $("#submitMasterData").off("click").on("click",function(){
	     	   var moduleIds = jQuery("#tableb_2").jqGrid('getGridParam', 'selarrrow');
	     		if (moduleIds.length == 0) {
	     			layer.alert("请选择至少一条数据！");
	     			return;
	     		}
	     		for(var j=0;j<moduleIds.length;j++){
	     			var rowData = jQuery("#tableb_2").jqGrid('getRowData',moduleIds[j]);
	     			var isbn=$("#"+rowData.id+"_isbn").val();
	     			var bookTitle=$("#"+rowData.id+"_bookTitle").val();	
	     			var price=$("#"+rowData.id+"_price").val();	
	     			var discount =$("#"+rowData.id+"_discountrate").val();	
	     			var sendOutqty =$("#"+rowData.id+"_sendOutQty").val();
	     			if($("#"+rowData.id+"_isbn").attr("date-type") == "span"){
	     				isbn=$("#"+rowData.id+"_isbn").text();	
	     				bookTitle=$("#"+rowData.id+"_bookTitle").text();
	     			};
	     			if(isbn==""){
	     				layer.alert("isbn不能为空");
	     				return false;	
	     			}
	     			if(bookTitle==""){
	     				layer.alert("书名不能为空");
	     				return false;	
	     			}
	     		}
	     	var data=getMasterData();	
	         var flag=panduanTable2();
	         
	         var flagIsHere=getIsHere(data);
	         //alert(flagIsHere);
	         //如果 flagIsHere为true，即数据库已经存在需要做提示是否需要继续添加。
	         if(flagIsHere){
	        	 layer.confirm('系统中已存在相同的商品，是否需要继续添加？', {
			  		    btn: ['是','否'], //按钮
			  		    shade: false //不显示遮罩
			  		}, function(index){
			  			if(flag){
				 			layer.alert("选择的商品中出现ISBN 书名都重复的数据！");
				 			return;
				 		}else{
				 			var judeFlag=judgeTable2();
				 			if(judeFlag){
				 				layer.alert("选择的商品已经被添加，请重新选择");
				 				return;
				 			}
				 		};
				 		layer.close(index);
			  			addRow3(data);
			 			jQuery("#tableb_1").clearGridData();	
			 			jQuery("#tableb_2").clearGridData();
			 			ids=[];
			  		}, function(){
			  			return;
					});
	         }else{// 如果 flagIsHere为false，即数据库不存在。
	 		   if(flag){
	 			layer.alert("选择的商品中出现ISBN 书名都重复的数据！");
	 			return;
	 		  }else{
	 			var judeFlag=judgeTable2();
	 			if(judeFlag){
	 				layer.alert("选择的商品已经被添加，请重新选择");
	 				return;
	 			  }
	 		  }
	 			addRow3(data);
	 			jQuery("#tableb_1").clearGridData();	
	 			jQuery("#tableb_2").clearGridData();
	 			ids=[];
	        	}
	      }) ;  
	        //添加到商品区域按钮功能
	        $("#addToGoodArea").off("click").on("click",function(){
	      	  var moduleIds = jQuery("#tableb_1").jqGrid('getGridParam', 'selarrrow');
	      		if (moduleIds.length == 0) {
	      			layer.alert("请选择至少一条数据！");
	      			return;
	      		}
	      		var data=new Array();
	      		for(var j=0;j<moduleIds.length;j++){
	      			var rowData = jQuery("#tableb_1").jqGrid('getRowData',moduleIds[j]);
	      			var id=rowData.id;
	      			var isbn=rowData.isbn;
	      			var bookTitle=rowData.bookTitle;
	      			var price=rowData.price;
	      			var discountrate=rowData.discountrate;
	      			var sendOutqty='';
	      			var isQueryData=true;
	      			ids.push(id);
	      			var isRepeatf=isRepeat(ids);
	      			if(isRepeatf){
	      				layer.alert("请勿选择重！");
	      				ids.pop()
	      				return false;
	      			}else{
	      			}
	      			data[j] ={id:id,isbn:isbn,bookTitle:bookTitle,price:price,discount:discountrate,sendOutQty:sendOutqty,isQueryData:isQueryData};
	      		}
	      		addRow2(data)
	      	  
	        });     
        
    //获得表格2选择数据    
    var allRowDatas = new Array();//获得所以选择数据表格值
 function getMasterData(){
		var moduleIds = jQuery("#tableb_2").jqGrid('getGridParam', 'selarrrow');
		if (moduleIds.length == 0) {
			layer.alert("请选择至少一条数据！");
			return;
		}
		var rowDatas = new Array();
		for(var j=0;j<moduleIds.length;j++){
			var rowData = jQuery("#tableb_2").jqGrid('getRowData',moduleIds[j]);
			var isbn=$("#"+rowData.id+"_isbn").val();
			var bookTitle=$("#"+rowData.id+"_bookTitle").val();	
			var price=$("#"+rowData.id+"_price").val();	
			var discount =$("#"+rowData.id+"_discountrate").val();	
			var sendOutqty =$("#"+rowData.id+"_sendOutQty").val();
			//alert(sendOutqty);
			if($("#"+rowData.id+"_isbn").attr("date-type") == "span"){
				isbn=$("#"+rowData.id+"_isbn").text();	
				bookTitle=$("#"+rowData.id+"_bookTitle").text();
				
			};
			if(isbn==""){
				layer.alert("isbn不能为空");
				return false;	
			}
			if(bookTitle==""){
				layer.alert("书名不能为空");
				return false;	
			}
			//proPurOrderItemsId  订单细目ID
			 rowDatas[j] ={id:rowData.id,isbn:isbn,bookTitle:bookTitle,price:price,discount:discount,sendOutqty:sendOutqty};
			 allRowDatas.push({isbn:isbn,bookTitle:bookTitle,price:price,discount:discount,sendOutqty:sendOutqty});
		}
		//allRowDatas.push(rowDatas);
			return rowDatas;
}
 
 
//获取整个页面数据
function getDataTable(){
	var obj = $("#table").jqGrid("getRowData");
	for(var j=0;j<obj.length;j++){
		 allRowDatas.push({isbn:obj[j].isbnbak,bookTitle:obj[j].bookTitle});
		}
}  
   //后台数据校验
   function getIsHere(table2Data){
	  var dataString=JSON.stringify(table2Data);
   	var flag=false;
   	$.ajax({
   		url:appPath+"/backto/orderDelivery/getGoodIsInSystem",
   		type:"POST",
   		dataType:"json",
   		async: false,
   		data:{"dataItems":dataString},
   	    traditional: true,
   		success:function(data){
   			if(data.success){
   				if(data.msg=="1"){
   					flag=true;
   				}
   			}else{
   				layer.alert(data.msg, {icon:2});//2：弹框图片为叉叉
   			}
   		}
           });
   	return flag;
   }
   
 
 
 
    //判断确定时候，对页面表格的赋值重复校验判断
    function  judgeTable2(){
    	//console.log(allRowDatas);
    	var isbns=new Array(allRowDatas.length);
   		var bookTitles=new Array(allRowDatas.length);
   		for(var j=0;j<allRowDatas.length;j++){
   			isbns[j] =allRowDatas[j].isbn;
			bookTitles[j] =allRowDatas[j].bookTitle;
   		}
   		var flag=false;
   		var nary_isbn=isbns.sort();
   		var nary_bookTitle=bookTitles.sort();
   		//console.info(nary_isbn)
   		//console.info(nary_bookTitle)
   		for(var a=0;a<isbns.length;a++){
   			for(var b=0;b<isbns.length;b++){
   			    if (nary_isbn[a]==nary_isbn[a+1]){
   					if(nary_bookTitle[b]==nary_bookTitle[b+1]){
   							flag=true;								
   					 }	 
   			      }
   		  }
   		}
   			return flag;
    }
    
    
    
       //判读表格2中isbn  书名重复的数据
       function panduanTable2(){
    	   var moduleIds = jQuery("#tableb_2").jqGrid('getGridParam', 'selarrrow');
   		if (moduleIds.length == 0) {
   			layer.alert("请选择至少一条数据！");
   			return;
   		}
   		var isbns=new Array(moduleIds.length);
   		var bookTitles=new Array(moduleIds.length);
	    for(var j=0;j<moduleIds.length;j++){
			var rowData = jQuery("#tableb_2").jqGrid('getRowData',moduleIds[j]);
			var isbn=$("#"+rowData.id+"_isbn").val();
			var bookTitle=$("#"+rowData.id+"_bookTitle").val();	
			if($("#"+rowData.id+"_isbn").attr("date-type") == "span"){
				isbn=$("#"+rowData.id+"_isbn").text();	
				bookTitle=$("#"+rowData.id+"_bookTitle").text();
				
			}
			if(isbn==""){
				layer.alert("isbn不能为空");
				return false;	
			}
			if(bookTitle==""){
				layer.alert("书名不能为空");
				return false;	
			}
			isbns[j] =isbn;
			bookTitles[j] = bookTitle;
		}
	
	
	var flag=false;
	var nary_isbn=isbns.sort();
	var nary_bookTitle=bookTitles.sort();
	//console.info(nary_isbn)
	//console.info(nary_bookTitle)
	for(var a=0;a<isbns.length;a++){
		for(var b=0;b<isbns.length;b++){
		    if (nary_isbn[a]==nary_isbn[a+1]){
				if(nary_bookTitle[b]==nary_bookTitle[b+1]){
						flag=true;								
				 }	 
		      }
	  }
	}
		return flag;
       }
        
        //获得table_2的选择数据
        function getTable2Select(){
        	var moduleIds = jQuery("#tableb_2").jqGrid('getGridParam', 'selarrrow');
        	if (moduleIds.length == 0) {
        		layer.alert("请选择至少一条数据！");
        		return;
        	}
        		var rowDatas = new Array();
        		
        		for(var j=0;j<moduleIds.length;j++){
        			var rowData = jQuery("#tableb_2").jqGrid('getRowData',moduleIds[j]);
        			//console.log(rowData);
					var id=rowData.id;
					id=id.replace(/<\/.*>/g,"").replace(/<.*>/g,"");
        			 rowDatas.push(id);
        		}
        			return rowDatas;
        }    
        
        var ids=new Array();       
        //获得table_2的选择数据
        function getTable2ALL(){
        	var obj = $("#tableb_2").jqGrid("getRowData");
        		 var rowDatas = new Array();
        		for(var j=0;j<obj.length;j++){
					var id=obj[j].id;
					id=id.replace(/<\/.*>/g,"").replace(/<.*>/g,"");
        			rowDatas.push(id);
        		}
        			return rowDatas; 
        }   
        
        
      //去掉所有空格
        function removeAllSpace(str) {
          return str.replace(/\s+/g, "");
        }	       
      
  //判断数组里面是否重复      
 function isRepeat(arr){
    var hash = {};
    for(var i in arr) {
	    if(hash[arr[i]])
		    return true;
		    hash[arr[i]] = true;
	    }
	    return false;
    }   
 
 });

/****------------------------------------------------------------------- 增加商品列表结束***/
