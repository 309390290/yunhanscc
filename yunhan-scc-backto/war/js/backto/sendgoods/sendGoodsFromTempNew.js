
In.ready('multipleDataBox','autocomplete','select','bmz','orderType','jqGrid','WdatePicker',function() {
	var regzd=new RegExp("\\\\","gm");  //正则表达式 待替换字符
	var tab = $("#show_list .active ").find("a").attr("name");
	var bmz_yz=[];
	var c_index=0;
	var re = /^\+?[1-9][0-9]*$/ ; 
	var report_re = /^\+?[0-9][0-9]*$/ ;
	var sendGoodsMaxLength=50;
	var maxSendNum=1000;//单发货单最大发货品种数
//	eval("var sendReg=/"+$("#sendReg_"+c_index).val()+"/");
	
	
	 /**
     * 绑定发货单失去焦点事件
     */
    $(".sendoutGoodsCode").bind("blur",function(){
    	var sendoutGoods=$.trim($(this).val());
    	eval("var sendReg=/"+$("#sendReg_"+c_index).val()+"/");
    	if($("#sendReg_"+c_index).val()!="none" && !sendReg.test(sendoutGoods)){
    		layer.tips("发货单号填写错误，请重新填写！",$(this));
    		$(this).focus();
    		$("#sendoutGoodsCode").val(sendoutGoods);
    	}
    });
    
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
	if(tab=="report"){
		initReportTab();
	}else{
		initTab(0);
	}
	$("#sendoutDate_"+c_index).val(getToday());
   //切换列表
	$('#show_list li').click(function(){
		$('#show_list li').removeClass('active');
		$(this).addClass('active');
		c_index=$(this).index();
		if($(this).find("a").attr("name")=="report"){//如果是只回告标签页
			initReportTab();
		}else{
			initTab($(this).index());
			
		}
		$('#myTabContent .list_box').removeClass('active')
		$('#myTabContent .list_box').eq($(this).index()).addClass('active');
		$('#left').css({'height':$('#myTabContent').height()});
	});
	
	
		/**
		 * 绑定取消按钮事件
		 */
		$(".cancelBtn").bind('click',function(){
			layer.confirm("确定要取消吗？<br/>本页签将自动关闭，本页商品不做任何处理", {
      			 btn: ['是','否'], //按钮
  	     		 icon:3,
  	     		 shade: false //不显示遮罩
  			}, function(indexI){
  				 layer.close(indexI);
  				 removeTable(c_index);
  			});
		});
		
		var sendFunction = function(){
			$(".sendBtn").unbind('click',sendFunction);
			var sendFlag=true;//true:可以发货  false:不能发货
	    	var msgCode="";//检查是否可以发货返回编码
	    	
			var reportIds="";//回告细目ids 
	    	var orderItemsIds="";//订单细目ids
	    	
	    	var responItems=[];
	    	var sendOutSummary={};
	    	var intemsNum=checkSendoutItemsNum();
	    	if(intemsNum>maxSendNum){
	    		layer.alert("发货品种数,最大不能超过1000条,请分批发货!");
	    		return ;
	    	}
	    	
	    	/**************不可修改的发货单头信息 ***************/
	    	var purchaserId=$("#purchaserId_"+c_index).val();//采购商id
	    	var supplierId=$("#supplierId_"+c_index).val();//供应商id
	    	
	    	sendOutSummary.totalVarietyQty=$("#totalVarietyQty_"+c_index).text();//发货品种数
	    	sendOutSummary.totalBookQty=$("#totalBookQty_"+c_index).text();//本次发货数
	    	sendOutSummary.totalPrice=$("#totalPrice_"+c_index).text();//本次发货总码洋
	    	sendOutSummary.totalRealityPrice=$("#totalRealityPrice_"+c_index).text();//本次发货总实洋
	    	sendOutSummary.receiveUserName=$("#receiveUserName_"+c_index).val();//收货人
	    	sendOutSummary.receivePhoneno=$("#receivePhoneno_"+c_index).val();//联系电话
	    	sendOutSummary.receiveAddress=$("#receiveAddress_"+c_index).val();//收货地址
	    	
	    	
	    	sendOutSummary.purchaserId=purchaserId;//采购商id
	    	sendOutSummary.supplierId=supplierId;//供应商id
	    	
	    	
	    	
	    	/**************可修改的发货单头信息 ***************/
	    	var historySendoutCode=$.trim($("#historySendoutCode_"+c_index).val());//发货单号
	    	var sendoutGoodsCode=$.trim($("#sendoutGoodsCode_"+c_index).val());//发货单号
	    	var	sendoutDate=$("#sendoutDate_"+c_index).val();//发货日期
	    	var	sendoutStation=$.trim($("#sendoutStation_"+c_index).val());//发到站
	    	var	transportCompany=$.trim($("#transportCompany_"+c_index).val());//承运商
	    	var	transportNo=$.trim($("#transportNo_"+c_index).val());//运单号
	    	var receiveWarehouse=$("#receiveWarehouse_"+c_index).val();//收货仓位
	    	var	transportMode=$.trim($("select[id=transportMode_"+c_index+"]").val());//运货方式
	    	var	pakagesQty=$.trim($("#pakagesQty_"+c_index).val());//包件数
	    	var	sendoutRemark=$.trim($("#sendoutRemark_"+c_index).val());//发货单备注
	    	
	    	eval("var sendReg=/"+$("#sendReg_"+c_index).val()+"/");
	    	if($("#sendReg_"+c_index).val()!="none" && !sendReg.test(sendoutGoodsCode)){
	    		layer.alert("发货单号填写错误，请重新填写！",{icon:0});
	    		return ;
	    	}
	    	if(sendoutGoodsCode==""){
	    		layer.alert("发货单号不能为空!",{icon:0});
	    		return ;
	    	}if(strLength(sendoutGoodsCode,4)>sendGoodsMaxLength){
	    		layer.alert("发货单号长度超长!",{icon:0});
	    		return ;
	    	}if(sendoutGoodsCode.indexOf("'")!=-1){
	    		layer.alert("发货单号不能包含特殊字符 ' ",{icon:0});
	    		return ;
	    	}if(sendoutDate==""){
	    		layer.alert("请选择发货日期!",{icon:0});
	    		return ;
	    	}if(sendoutStation==""){
	    		layer.alert("发到站不能为空!",{icon:0});
	    		return ;
	    	}if(pakagesQty!="" && isNaN(pakagesQty)){
	    		layer.alert("包件数不合法!",{icon:0});
	    		return ;
	    	}
	    	
	    	sendOutSummary.receiveWarehouse=receiveWarehouse;//收货仓位
	    	sendOutSummary.sendoutGoodsCode=sendoutGoodsCode;//发货单号
	    	sendOutSummary.sendoutDate=sendoutDate;//发货日期
	    	sendOutSummary.sendoutStation=sendoutStation;//发到站
	    	sendOutSummary.transportCompany=transportCompany;//承运商
	    	sendOutSummary.transportNo=transportNo;//运单号
	    	sendOutSummary.transportMode=transportMode;//运货方式
	    	sendOutSummary.pakagesQty=pakagesQty;//包件数
	    	sendOutSummary.sendoutRemark=sendoutRemark;//发货单备注
	    	sendOutSummary.historySendoutCode=historySendoutCode;//历史发货单号
	    	
	    	var datas=$("#table_"+c_index).jqGrid("getRowData");
	    	/*****	发货品种信息  ****/
	    	for(var i=0;i<datas.length;i++){
	    		var pro={};
	    		var rowData=datas[i];
	    		pro.id=rowData.respId;//回告细目id
	    		if(reportIds=="" && rowData.respId!=null && rowData.respId!=""){
	    			reportIds=rowData.respId;
	    		}else if(reportIds!="" && rowData.respId!=null && rowData.respId!=""){
	    			reportIds+=","+rowData.respId;
	    		}
	    		if(orderItemsIds=="" && rowData.proPurOrderItemsId!=null && rowData.proPurOrderItemsId!=""){
	    			orderItemsIds=rowData.proPurOrderItemsId;
	    		}else if(orderItemsIds!="" && rowData.proPurOrderItemsId!=null && rowData.proPurOrderItemsId!=""){
	    			orderItemsIds+=","+rowData.proPurOrderItemsId;
	    		}
	    		var price =$.trim($("#price_"+rowData.id).val());
	    		var proPurOrderItemsId=rowData.proPurOrderItemsId;//订单细目id
				var orderQty=rowData.orderQty;
				var sendoutQty=rowData.sendoutQty;
				var preSendDate=$("#preSendDate_"+rowData.id).val();
				var discountrate=$.trim($("#discountrate_"+rowData.id).val());
				var thisSendQty=$.trim($("#thisSendQty_"+rowData.id).val());
				var otherAvailableReason=$("select[id=bmz_"+rowData.id+"]").val();
				var responseRemark=$("#responseRemark_"+rowData.id).val();
				
				if(price==""){
					layer.tips('定价不能为空!',$("#price_"+rowData.id));
					return ;
				}if(isNaN(price) || Number(price)<=0 ){
					layer.tips('定价不合法!',$("#price_"+rowData.id));
					return ;
				}if(discountrate==""){
					layer.tips('折扣不能为空!',$("#discountrate_"+rowData.id));
					return ;
				}if(isNaN(discountrate) || Number(discountrate)<=0 ){
					layer.tips('折扣不合法!',$("#discountrate_"+rowData.id));
					return ;
				}if(thisSendQty=="" || isNaN(thisSendQty) || !re.test(thisSendQty)){
					layer.tips('本次发货数必须为正整数!',$("#thisSendQty_"+rowData.id));
					return;
				}
				if(proPurOrderItemsId!=null && proPurOrderItemsId!=""){
					if(Number(thisSendQty)>Number(orderQty)*1.3-Number(sendoutQty)){//如果
						layer.tips('超发数超过了定数的30%!',$("#thisSendQty_"+rowData.id));
						return;
					}if(Number(thisSendQty)<Number(orderQty)-Number(sendoutQty) && otherAvailableReason==""){
						layer.tips('请选择其余满足情况!',$("#bmz_"+rowData.id));
						return;
					}
					pro.isSupplierAddProduct="N";
				}else{
					pro.isSupplierAddProduct="Y";
				}
				if(otherAvailableReason=="0" && preSendDate=="" ){
					layer.tips('其他满足情况为[预计可发]时,请选择其余预计发货日期!',$("#preSendDate_"+rowData.id));
					return;
				}
	    		pro.proPurOrderItemsId=proPurOrderItemsId;//订单细目id
	    		pro.purchaseOrderCode=rowData.purchaseOrderCode;//采购订单号
	    		pro.supplierCommoditiesId=rowData.supplierCommoditiesId;//供应商商品id
	    		pro.isSupplierAddProduct=rowData.isSupplierAddProduct;//是否供应商添加
	    		pro.isbn=rowData.isbn;//isbn
	    		pro.bookTitle=rowData.bookTitle;//书名
	    		pro.availablePrice=price;//可供价
	    		pro.availableDiscountrate=discountrate;//可供折扣
	    		pro.orderQty=rowData.orderQty;//订数
	    		pro.thisSendQty=thisSendQty;//本次发货数
	    		pro.historyOrderQty=rowData.historyOrderQty;//已发数
	    		pro.preSendDate=preSendDate;//其余预计发货日期
	    		pro.responseRemark=responseRemark;//备注
	    		pro.otherAvailableReason=$("select[id=bmz_"+rowData.id+"]").val();
	    		responItems.push(pro);
	    	}
	    	sendOutSummary.responseItemsIds=reportIds;//所有要回告的回告细目ids
	    	sendOutSummary.orderItemsIds=orderItemsIds;//所有订单细目ids
	    	/****************检查发货单号是否可以发货  开始****************/
	    	$.ajax({
	    		url:appPath+"/backto/sendGoods/checkSendoutIsMaySend",
	    		type:"POST",
	    		dataType:"json",
	    		async: false,
	    		data:{ "sendOutSummary":JSON.stringify(sendOutSummary),"responItems":JSON.stringify(responItems)},
	    		success:function(data){
	    			if(data.success){//检查发货单号是否可以发货调用成功
	    				msgCode=data.obj;
	    				
	    			}
	    		}
	    	})
	    	/****************检查发货单号是否可以发货  结束****************/
	    	if(msgCode=='C'){
	    		layer.alert("订单仓位不一致，不能一起发货！",{icon:2});
	    		sendFlag=false;
				return ;
	    	}else if(msgCode=='D'){
	    		layer.alert("直供订单，不能与其他订单一起发货！",{icon:2});
	    		sendFlag=false;
				return ;
	    	}else if(msgCode=='E'){
	    		layer.alert("其他采购商已经使用了此发货单号，请重新输入发货单号!",{icon:2});
	    		sendFlag=false;
				return ;
	    	}else if(msgCode=='A'){
	    		layer.confirm("馆配订单，有品种不能全部发货，是否继续？", {
	     			 btn: ['是','否'], //按钮
	 	     		 icon:3,
	 	     		 shade: false //不显示遮罩
	 			}, function(indexI){
	 				layer.close(indexI);
	 				checkAndSend(sendOutSummary,responItems,sendoutGoodsCode);
	 			});
	    	}else if(msgCode=='B'){
	    		layer.confirm("大中专、团购、活动订书，有品种不能全部发货，是否继续？", {
	    			 btn: ['是','否'], //按钮
		     		 icon:3,
		     		 shade: false //不显示遮罩
				}, function(indexI){
					layer.close(indexI);
	 				checkAndSend(sendOutSummary,responItems,sendoutGoodsCode);
				});
	    	}else if(msgCode=='S'){
	    		checkAndSend(sendOutSummary,responItems,sendoutGoodsCode);
	    	}
	    	$(".sendBtn").bind('click',sendFunction);
};
    	

			
		
		/**
		 * 发货操作
		 */
		$(".sendBtn").bind('click',sendFunction);
		
		
		
		
		/**
		 * 回告操作
		 */
		$(".reportBtn").bind('click',function(){
			var objs = $("#table_report").jqGrid("getRowData");
			var responItems=[];
			for ( var i = 0; i < objs.length; i++) {
				var pro={};
				var rowData=objs[i];
				var proPurOrderItemsId=rowData.proPurOrderItemsId;//订单细目id
				var purchaseOrderCode=rowData.purchaseOrderCode;
				var price =$.trim($("#price_"+rowData.id).val());
				var orderQty=rowData.orderQty;
				var sendoutQty=rowData.sendoutQty;
				var preSendDate=$("#preSendDate_"+rowData.id).val();
				var discountrate=$.trim($("#discountrate_"+rowData.id).val());
				//var thisSendQty=$.trim($("#thisSendQty_"+rowData.id).attr("value"));
				var otherAvailableReason=$("select[id=bmz_"+rowData.id+"]").val();
				var responseRemark=$("#responseRemark_"+rowData.id).val();
				if(price==""){
					layer.tips('定价不能为空!',$("#price_"+rowData.id));
					return ;
				}if(isNaN(price) || Number(price)<=0 ){
					layer.tips('定价不合法!',$("#price_"+rowData.id));
					return ;
				}if(discountrate==""){
					layer.tips('折扣不能为空!',$("#discountrate_"+rowData.id));
					return ;
				}if(isNaN(discountrate) || Number(discountrate)<=0 ){
					layer.tips('折扣不合法!',$("#discountrate_"+rowData.id));
					return ;
				}/*if(thisSendQty=="" || isNaN(thisSendQty) || !report_re.test(thisSendQty)){
					layer.tips('本次发货数必须为正整数!',$("#thisSendQty_"+rowData.id));
					return;
				}
				if(Number(thisSendQty)>Number(orderQty)*1.3-Number(sendoutQty)){//如果
					layer.tips('超发数超过了定数的30%!',$("#thisSendQty_"+rowData.id));
					return;
				}if(Number(thisSendQty)<Number(orderQty)-Number(sendoutQty) && otherAvailableReason==""){
					layer.tips('请选择其余满足情况!',$("#bmz_"+rowData.id));
					return;
				}*/
				if( otherAvailableReason==""){
					layer.tips('请选择其余满足情况!',$("#bmz_"+rowData.id));
					return;
				}
				if(otherAvailableReason=="0" && preSendDate=="" ){
					layer.tips('其他满足情况为[预计可发]时,请选择其余预计发货日期!',$("#preSendDate_"+rowData.id));
					return;
				}
		    		pro.proPurOrderItemsId=proPurOrderItemsId;//订单细目id
		    		pro.purchaseOrderCode=rowData.purchaseOrderCode;//采购订单号
		    		pro.supplierCommoditiesId=rowData.supplierCommoditiesId;//供应商商品id
		    		pro.isbn=rowData.isbn;//isbn
		    		pro.bookTitle=rowData.bookTitle;//书名
		    		pro.availablePrice=price;//可供价
		    		pro.availableDiscountrate=discountrate;//可供折扣
		    		pro.orderQty=orderQty;//订数
		    		pro.thisSendQty=0;//本次发货数
		    		pro.historyOrderQty=sendoutQty;//已发数
		    		pro.preSendDate=preSendDate;//其余预计发货日期
		    		pro.otherAvailableReason=otherAvailableReason;
		    		pro.responseRemark=responseRemark;
		    		pro.purchaserId=rowData.purchaserId;
		    		pro.supplierId=rowData.supplierId;
		    		responItems.push(pro);
				
			}
			/**************** 模板发货  开始**************/
	    	$.ajax({
	    		url:appPath+"/backto/sendGoods/saveResponseFromTemp",
	    		type:"POST",
	    		dataType:"json",
	    		data:{
	    			"saveSend":JSON.stringify(responItems)
	    		},
	    		beforeSend : function(){
		        	WG.loading.show();
		        },
	    		success:function(data){
	    			WG.loading.hide();
	    			if(data.success){
	    				layer.msg("回告成功!",{icon:1});
	    				setTimeout(removeTable(c_index),2000); 
	    			}else{
	    				layer.alert(data.msg,{icon:2});
	    			}
	    			
	    		},
	    		error:function(data){
	    			
	    		}
	    		
	    	})
	    	/**************** 模板发货  结束**************/
			
			
			
			
			
		});
	
	
		/******************** 初始化发货列表  开始*******************/
		function initTab(index){
			 $("#table_"+index).jqGrid({
			  		url:appPath+'/console/query', 
					autowidth: true, 
			 		shrinkToFit: true, 
			 		datatype: "json", 
			 		mtype:"post",
			 		colNames: ["ID","回告id","isbn","书名","采购商名称","到站","仓位","订单细目id","采购订单号","订单号", "商品信息", "订单备注", "定 价", "折扣（%）", "订 数", 
			 		           "本次发货数", "其余满足情况", "其余预计发货日期", "备 注", "已发数", "已收数", "操 作"],// the column header names
			        colModel: [
			                {name: "id", index: "id", hidden:true},
			                {name: "respId", index: "respId", hidden:true},
			                {name: "isbn", index: "isbn", hidden:true},
			                {name: "bookTitle", index: "bookTitle", hidden:true},
			                {name: "purchaserName", index: "purchaserName", hidden:true},
			                {name: "destination", index: "destination", hidden:true},
			                {name: "warehouse", index: "warehouse", hidden:true},
			                {name: "proPurOrderItemsId", index: "proPurOrderItemsId", hidden:true},
			                {name: "purchaseOrderCode", index: "purchaseOrderCode", hidden:true},
			                {name: "purchaseOrderCodeStr", index: "订单号", width: '10%', align: 'center',
			                	formatter : function(value, options, rData) {
			                		var str="<div class='iconBox'>";
			                		if(rData.proPurOrderItemsId==null || rData.proPurOrderItemsId==""){
			                			return "<i class=\"top_ico\">供应商发货添加</i>";
			                		}
			                		/*if(rData.isExport=="Y"){//已下载
			                			str+="<div class='right'><i class='u-icon-yxz'></i></div>";
			                		}*/
			                		if(rData.purchaseOrderCode==null){
			                			str+="<div></div><ul class='uicon'>";
			                		}else{
			                			str+="<div>"+rData.purchaseOrderCode+"</div><ul class='uicon'>";
			                		}
			                		if(rData.orderType==TECHNICALSECONDARY){//大中专
			                			str+="<li><i class='u-icon-orderType'>大中专</i></li>";
			                		}if(rData.orderType==GENERALRETAIL){//零售
			                			str+="<li><i class='u-icon-orderType'>零售</i></li>";
			                		}if(rData.orderType==EDUCATION){//文教
			                			str+="<li><i class='u-icon-orderType'>文教</i></li>";
			                		}if(rData.orderType==GROUP){//团购
			                			str+="<li><i class='u-icon-orderType'>团购</i></li>";
			                		}if(rData.orderType==PLEASEBOOK){//馆配
			                			str+="<li><i class='u-icon-orderType'>馆配</i></li>";
			                		}
			                		
			                		if(rData.urgentFlag=="1"){//急单
			                			str+="<li><i class='u-icon-j'>急</i></li>";
			                		}if(rData.isNew=="Y"){
			                			str+="<li><i class='u-icon-x'>新</i></li>";
			                		}if(rData.sendGoodsType=="2"){
			                			str+="<li><i class='u-icon-zg'>直供</i></li>";
			                		}
			                		str+="</ul> </div>";
				            		return str;
							  }
			                },
			                {name: "sendoutGoodsCode", index: "商品信息", width: '16%', align: 'center',
			                	formatter:function(value, options, rData){
			                		var str="<div class='iconBox'><div class='left15'><ul>";
			                		if(rData.isImportent=="Y"){
			                			str+="<li><i class='u-icon-zd'>重点</i></li>";
			                		}if(rData.orderType==ACTIVITY){
			                			str+="<li><i class='u-icon-hd'>活动</i></li>";
			                		}if(rData.isSuitBook=="2"){//组套
			                			str+="<li><i class='u-icon-zt'>组套</i></li>";
			                		}
			                		str+="</ul> </div><div class='right85'><ul>";
			                		if(rData.bookTitle==null){
			                			str+="<li></li>";
			                		}else{
			                			str+="<li>"+rData.bookTitle+"</li><br/>";
			                		}if(rData.affiliateSeries==null){
			                			str+="<li class='fontfui'>从书名：</li>";
			                		}else{
			                			str+="<li class='fontfui'>从书名："+rData.affiliateSeries+"</li>";
			                		}if(rData.isbn==null){
			                			str+="<li >ISBN：</li>";
			                		}else{
			                			str+="<li >ISBN："+rData.isbn+"</li>";
			                		}
			                		
			                		
			                		return str;
			                	}
			                },
			                {name: "remark", index: "订单备注", width: '12%', align: 'center'},
			                {name: "price", index: "定 价", width: '10%', align: 'center',
			                	formatter:function(value, options, rData){
			                		var str=""; 
			                		var t_price=rData.availablePrice==null?"":rData.availablePrice;
			                		if(rData.responseStatus=="5" && rData.price==rData.reportPrice){
			                			str=rData.price;
			                			str+="<div><input type='hidden' id='price_"+rData.id+"' value='"+rData.price+"' /></div>";
			                		}else if(rData.responseStatus=="5" && rData.price!=rData.reportPrice){
			                			str="<div class='fontdel'>订单定价："+rData.price+"</div><div>"+rData.reportPrice+"</div>";
			                			str+="<div><input type='hidden' id='price_"+rData.id+"' value='"+rData.price+"' /></div>";
			                		}else if(rData.responseStatus=="0"){
			                			str="<div class='JGtab'><input type='text' id='price_"+rData.id+"' value='"+t_price+"' class='b80'></div>";
			                		}
			                		return str;
			                		
			                	}
			                },
			                {name: "discountrate", index: "折扣（%）", width: '10%', align: 'center',
			                	formatter:function(value, options, rData){
			                		var str="";
			                		var availableDiscountrate=rData.availableDiscountrate==null?"":rData.availableDiscountrate;
			                		if((rData.proPurOrderItemsId==null || rData.proPurOrderItemsId=="") || ( rData.discountrate!=null && 
			                				Number(rData.discountrate).toFixed(2)==rData.availableDiscountrate )){//如果订单折扣和导入进来的折扣相同
			                			str="<div class='JGtab'><input type='text' id='discountrate_"+rData.id+"' value='"+availableDiscountrate+"' class='b80 discountrate'></div>";
			                		}else{
			                			str="<div class='fontdel'>订单折扣："+Number(rData.discountrate).toFixed(2)+"</div><div class='JGtab'><input type='text' id='discountrate_"+rData.id+"' value='"+availableDiscountrate+"' class='b80 discountrate'></div>";
			                		}
			                		return str;
			                		
			                	}
			                },
			                {name: "orderQty", index: "订 数", width: '4%', align: 'center'},
			                {name: "thisSendQty", index: "本次发货数", width: '6%', align: 'center',
			                	formatter:function(value, options, rData){
			                		return "<div class='JGtab'><input type='text' default='"+rData.thisSendQty+"' data-id='"+rData.id+"'  id='thisSendQty_"+rData.id+"' value='"+rData.thisSendQty+"' class='b80 thisSendQty'></div>";
			                	}
			                },
			                {name: "notEnoughReason", index: "其余满足情况", width: '11%', align: 'center',
			                	formatter:function(value, options, rData){
			                		var html="";
			                		var zt;
				                	if(rData.reportAvailableReason != null && rData.reportAvailableReason != ""){
		                				html += "<div>"+getOtherAvailableReasonStr(value)+"</div>";
		                			}
			 	     				if(Number(rData.orderQty)-Number(rData.sendoutQty)<=Number(rData.thisSendQty)){
			 	     					zt = 1;
			 	    				}else{
			 	    					value=(rData.notEnoughReason==null || rData.notEnoughReason=="")?1:rData.notEnoughReason;
			 	    				}
			 	     				html+="<select selectVal="+value+" data-id='"+rData.id +"' id='bmz_"+rData.id+"' style='width:80%'  name='bmz'  flag ='"+zt+"' ><option value=''></option></select>";
			                		return html;
			                	}
			                },
			                {name: "preSendDate", index: "其余预计发货日期", width: '10%', align: 'center',
			                	formatter:function(value, options, rData){
			                		var preSendDate=rData.preSendDate==null?"":rData.preSendDate;
			                		//var preSendDate=rData.preSendDate==null?getToday():rData.preSendDate;
			                		return "<div class='JGtab'><input type='text'  id='preSendDate_"+rData.id+"' value='"+preSendDate+"' class='b80 startTime' onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\"></div>" +
			                				"<span class='u-icon u-icon-date'></span>";
			                	}
			                },
			                {name: "responseRemark", index: "备 注", width: '10%', align: 'center',
			                	formatter:function(value, options, rData){
			                		var html="";
			                		var remark=rData.responseRemark==null?"":rData.responseRemark;
			                		if(rData.historyRemark!=null && rData.historyRemark!=""){
			                			html += "<div>"+rData.historyRemark+"</div>";
			                		}
			                		html+="<div class='JGtab'><input type='text' id='responseRemark_"+rData.id+"' value='"+remark+"' class='b90'></div>";
			                		return html;
			                	}
			                },
			                {name: "sendoutQty", index: "已发数", width: '4%', align: 'center'},
			                {name: "receiveQty", index: "已收数", width: '4%', align: 'center'},
			                {name: "sendoutGoodsCode", index: "操 作", width: '6%', align: 'center',
			                	formatter:function(value, options, rData){
				                	if(rData.purchaserName!=null && rData.purchaserName!=""){//采购商名称
				                		$("#purchaserName_"+index).html(rData.purchaserName);
				                	}if(rData.destination!=null && rData.destination!=""){//到站
				                		$("#sendoutStation_"+index).val(rData.destination);
				                	}if(rData.warehouse!=null && rData.warehouse!=""){//仓位
				                		$("#receiveWarehouse_"+index).val(rData.warehouse);
				                	}if(rData.purchaserId!=null && rData.purchaserId!=""){//采购商id
				                		$("#purchaserId_"+index).val(rData.purchaserId);
				                	}if(rData.supplierId!=null && rData.supplierId!=""){//供应商id
				                		$("#supplierId_"+index).val(rData.supplierId);
				                	}if(rData.receiveAddress!=null && rData.receiveAddress!=""){//收货地址
				                		$("#receiveAddress_"+index).val(rData.receiveAddress);
				                	}if(rData.receivePhoneno!=null && rData.receivePhoneno!=""){//收货电话
				                		$("#receivePhoneno_"+index).val(rData.receivePhoneno);
				                	}if(rData.receiveContactUser!=null && rData.receiveContactUser!=""){//收货联系人
				                		$("#receiveUserName_"+index).val(rData.receiveContactUser);
				                	}if(rData.receiveContactUser!=null && rData.receiveContactUser!=""){//发货单规则正则表达式
				                		$("#sendReg_"+index).val(rData.regularExpression);
				                	}
				                	return "<a href='#' id='"+rData.id+"' class='btn_white deleteBtn btn-mini'>移 除</a>";
			                	}
			                }
			            ],// the column discription
			 	  rowNum:999,
			     // multiselect : true,  //显示checkbox选择框
			      rownumbers: true,    //显示左边排名列表
			      viewrecords: true, 
			      //pager: '#sendProduct_page',
			      height:'100%',
			      sortable:false,
			      jsonReader: { 
			    	  root: "rows",
			    	  page: "page",
			    	  total: "total",
			    	  records: "records",
			    	  repeatitems : false
			      }, //设置数据方式
			      postData: {query_id:"findTempSendItemsTs",query_type:"JQGRID",rdParseType:"dispersed",reqData:queryData(index)},
			      height: '100%',
			      /* 客户端排序--------------------------------------------------------开始 */
			      loadBeforeSend:function(){
			    	  $("#table_"+index).jqGrid('clearGridData');
			      },
			      loadError:function(){
			    	  layer.msg("系统报错！",{icon:5});
			      },
			      loadComplete:function(){
			    	 var rowDatas = $(this).jqGrid("getRowData");
		 			 if(rowDatas.length==0){
		 				removeTable(c_index);
		 			 }
		 			 for ( var i = 0; i < rowDatas.length; i++) {
		 				if(rowDatas[i].proPurOrderItemsId==null || rowDatas[i].proPurOrderItemsId==""){
                			$("#table_"+index).find("#"+rowDatas[i].id).css("background","#ffdcdc");
                		}
						getNotEnoughReason(index,rowDatas[i].id);
					}
		 			//设置两边高度
			    	  
			    	  $('#left').css({'height':$('#myTabContent').height()});
			    	  
			    	  //加载完成设置表格宽度
			    	  
			    	  $(this).setGridWidth($(this).parents('.GdBox').eq(0).width());
//			    	 var width=$(this).parents('.GdBox').eq(0).width();
//			    	 $(this).setGridWidth($(this).parents('.GdBox').eq(0).width());
//			    	 $(this).parents('.GdBox').eq(0).find('.ui-jqgrid-bdiv').css('width',width+1);
//			    	 $(this).parents('.GdBox').eq(0).find('.ui-jqgrid-hdiv').css('width',width+1);
			    	 
			    	 $("select[name='bmz']").each(function(){
						   var selectVal = $(this).attr("selectVal");
						   var flag = $(this).attr("flag");
						   selectVal = (selectVal == null || selectVal == '')?1:selectVal;
						   selectVal = flag==1?"":selectVal;
						  $(this).select({
							  	localdata:bmz_yz,
			 					selected: selectVal //默认选中的值
			 				});
					   });
			    	 
			    		//注册change事件
		 			  $(".discountrate").on("change",function(){
		 				  var _id = $(this).attr("data-id");
		 				  var _default = $(this).attr("default");
		 				  var rowData= $("#table_"+c_index).jqGrid("getRowData",_id);
		 				  var _value=$.trim($(this).val());
		 				  if( _value=="" ||  isNaN(_value) || Number(_value)<=0){
		 					 layer.tips('输入不合法',$(this));
		 					 $(this).val(_default);
		 		    		 return ;
		 				  }
		 				 $(this).attr("default",_value);
						  total(c_index);
		 				 
		 			  });
			    	 	//注册change事件
		 			  $(".thisSendQty").on("change",function(){
		 				  var _id = $(this).attr("data-id");
		 				  var _default = $(this).attr("default");
		 				  var rowData= $("#table_"+c_index).jqGrid("getRowData",_id);
		 				  var _thisSendQty=$.trim($(this).val());
		 				  var _sendoutQty=rowData.sendoutQty;
		 				  var _orderQty=rowData.orderQty;
		 				  if(_thisSendQty=="" && isNaN(_thisSendQty)){
		 					 layer.tips('本次发货数不合法',$(this));
		 					 $(this).val(_default);
		 		    		 return ;
		 				  }if(rowData.purchaseOrderCode!=null && rowData.purchaseOrderCode!="" 
		 					  && Number(_thisSendQty)>Number(_orderQty)*1.3-Number(_sendoutQty)){
		 					 layer.tips('超发数量不能大于定数的30%',$(this));
		 					// $(this).val(_default);
		 		    		 return ;
		 				  }
		 				  getNotEnoughReason(c_index,_id);
		 				 total(c_index);
		 				 
		 			  });
			    	 
			    	 total(c_index);
			    	 
			    	 /**
		 			  * 绑定删除订单事件
					  */
					  $(".deleteBtn").click(function(){
						  	var itemsId=$(this).attr("id");
						  	layer.confirm("确定要移除吗？<br/>本商品将移除，不做任何处理", {
				       			 btn: ['是','否'], //按钮
				   	     		 icon:3,
				   	     		 shade: false //不显示遮罩
			   	  			}, function(indexI){
			   	  				 layer.close(indexI);
								 $("#table_"+c_index).jqGrid("delRowData", itemsId); 
								 var objs = $("#table_"+c_index).jqGrid("getRowData");
								 if(objs.length==0){
									 removeTable(c_index);
								 }else{
									 var rData= objs[0]; 
									 if(rData.purchaserName!=null && rData.purchaserName!=""){//采购商名称
				                		$("#purchaserName_"+c_index).html(rData.purchaserName);
				                	 }
				                		$("#sendoutStation_"+c_index).val(rData.destination);
				                		$("#receiveWarehouse_"+c_index).val(rData.warehouse);
				                	if(rData.purchaserId!=null && rData.purchaserId!=""){//采购商id
				                		$("#purchaserId_"+c_index).val(rData.purchaserId);
				                	}if(rData.supplierId!=null && rData.supplierId!=""){//供应商id
				                		$("#supplierId_"+c_index).val(rData.supplierId);
				                	}if(rData.receiveAddress!=null && rData.receiveAddress!=""){//收货地址
				                		$("#receiveAddress_"+c_index).val(rData.receiveAddress);
				                	}if(rData.receivePhoneno!=null && rData.receivePhoneno!=""){//收货电话
				                		$("#receivePhoneno_"+c_index).val(rData.receivePhoneno);
				                	}if(rData.receiveContactUser!=null && rData.receiveContactUser!=""){//收货联系人
				                		$("#receiveUserName_"+c_index).val(rData.receiveContactUser);
				                	}
								 }
								 total(c_index);
			   	  			});
					  });
		          }
			      
			      /* 客户端排序 --------------------------------------------------------结束*/
			  	});
		}
		/******************** 初始化发货列表  结束*******************/
		/******************** 获取发货列表查询条件*******************/
		function queryData(index){
	    	var sendoutGoodsCode=$("#show_list li").eq(index).find("a").attr("name").replace(regzd,'\\\\');//发货单号
	    	
	    	var queryData="{'groupOp':'AND','rules':[";
	    	var a = "{'field':'addUserCode','op':'eq','data':'"+userName+"'},";
	    	queryData = queryData.concat(a);
	    	var b = "{'field':'sendoutGoodsCode','op':'eq','data':'"+sendoutGoodsCode+"'},";
	    	queryData = queryData.concat(b);
	    	
	    	queryData = queryData.substring(0,queryData.length-1);
	    	var z= "]}";
	    	queryData = queryData.concat(z);
	    	
	    	return queryData;
	    } 
		
		
		/******************** 初始化只回告列表  开始*******************/
		function initReportTab(){
			 $("#table_report").jqGrid({
			  		url:appPath+'/console/query', 
					autowidth: true, 
			 		shrinkToFit: true, 
			 		datatype: "json", 
			 		mtype:"post",
			 		colNames: ["ID","采购商id","供应商id","回告id","isbn","书名","采购商名称","到站","仓位","订单细目id","采购订单号","订单号", "商品信息", "订单备注", "定 价", "折扣（%）", "订 数", 
			 		           "本次发货数", "其余满足情况", "其余预计发货日期", "备 注", "已发数", "已收数", "操 作"],// the column header names
			        colModel: [
			                {name: "id", index: "id", hidden:true},
			                {name: "purchaserId", index: "purchaserId", hidden:true},
			                {name: "supplierId", index: "supplierId", hidden:true},
			                {name: "respId", index: "respId", hidden:true},
			                {name: "isbn", index: "isbn", hidden:true},
			                {name: "bookTitle", index: "bookTitle", hidden:true},
			                {name: "purchaserName", index: "purchaserName", hidden:true},
			                {name: "destination", index: "destination", hidden:true},
			                {name: "warehouse", index: "warehouse", hidden:true},
			                {name: "proPurOrderItemsId", index: "proPurOrderItemsId", hidden:true},
			                {name: "purchaseOrderCode", index: "purchaseOrderCode", hidden:true},
			                {name: "purchaseOrderCodeStr", index: "订单号", width: '10%', align: 'center',
			                	formatter : function(value, options, rData) {
			                		var str="<div class='iconBox'>";
			                		/*if(rData.isExport=="Y"){//已下载
			                			str+="<div class='right'><i class='u-icon-yxz'></i></div>";
			                		}*/
			                		if(rData.purchaseOrderCode==null){
			                			str+="<div></div><ul class='uicon'>";
			                		}else{
			                			str+="<div>"+rData.purchaseOrderCode+"</div><ul class='uicon'>";
			                		}
			                		if(rData.orderType==TECHNICALSECONDARY){//大中专
			                			str+="<li><i class='u-icon-orderType'>大中专</i></li>";
			                		}if(rData.orderType==GENERALRETAIL){//零售
			                			str+="<li><i class='u-icon-orderType'>零售</i></li>";
			                		}if(rData.orderType==EDUCATION){//文教
			                			str+="<li><i class='u-icon-orderType'>文教</i></li>";
			                		}if(rData.orderType==GROUP){//团购
			                			str+="<li><i class='u-icon-orderType'>团购</i></li>";
			                		}if(rData.orderType==PLEASEBOOK){//馆配
			                			str+="<li><i class='u-icon-orderType'>馆配</i></li>";
			                		}
			                		if(rData.urgentFlag=="1"){//急单
			                			str+="<li><i class='u-icon-j'>急</i></li>";
			                		}if(rData.isNew=="Y"){
			                			str+="<li><i class='u-icon-x'>新</i></li>";
			                		}if(rData.sendGoodsType=="2"){
			                			str+="<li><i class='u-icon-zg'>直供</i></li>";
			                		}
			                		str+="</ul> </div>";
				            		return str;
							  }
			                },
			                {name: "sendoutGoodsCode", index: "商品信息", width: '16%', align: 'center',
			                	formatter:function(value, options, rData){
			                		var str="<div class='iconBox'><div class='left15'><ul>";
			                		if(rData.isImportent=="Y"){
			                			str+="<li><i class='u-icon-zd'>重点</i></li>";
			                		}if(rData.orderType==ACTIVITY){
			                			str+="<li><i class='u-icon-hd'>活动</i></li>";
			                		}if(rData.isSuitBook=="2"){//组套
			                			str+="<li><i class='u-icon-zt'>组套</i></li>";
			                		}
			                		str+="</ul> </div><div class='right85'><ul>";
			                		if(rData.bookTitle==null){
			                			str+="<li></li>";
			                		}else{
			                			str+="<li>"+rData.bookTitle+"</li>";
			                		}if(rData.affiliateSeries==null){
			                			str+="<li class='fontfui'>从书名：</li>";
			                		}else{
			                			str+="<li class='fontfui'>从书名："+rData.affiliateSeries+"</li>";
			                		}if(rData.isbn==null){
			                			str+="<li >ISBN：</li>";
			                		}else{
			                			str+="<li >ISBN："+rData.isbn+"</li>";
			                		}
			                		
			                		
			                		return str;
			                	}
			                },
			                {name: "remark", index: "订单备注", width: '12%', align: 'center'},
			                {name: "price", index: "定 价", width: '6%', align: 'center',
			                	formatter:function(value, options, rData){
			                		var str="";
			                		var t_price=rData.availablePrice==null?"":rData.availablePrice;
			                		if(rData.responseStatus=="5" && rData.price==rData.reportPrice){
			                			str=rData.price;
			                			str+="<div><input type='hidden' id='price_"+rData.id+"' value='"+rData.price+"' /></div>";
			                		}else if(rData.responseStatus=="5" && rData.price!=rData.reportPrice){
			                			str="<div class='fontdel'>订单定价："+rData.price+"</div><div>"+rData.reportPrice+"</div>";
			                			str+="<div><input type='hidden' id='price_"+rData.id+"' value='"+rData.price+"' /></div>";
			                		}else if(rData.responseStatus=="0"){
			                			str="<div class='JGtab'><input type='text' id='price_"+rData.id+"' value='"+t_price+"' class='b80'></div>";
			                		}
			                		//str=str+"<div><input type='hidden' id='price_"+rData.id+"' value='"+price"' /></div>";
			                		
			                		return str;
			                		
			                	}
			                },
			                {name: "discountrate", index: "折扣（%）", width: '10%', align: 'center',
			                	formatter:function(value, options, rData){
			                		var str="";
			                		var t_discountrate=rData.availableDiscountrate==null?"":rData.availableDiscountrate;
			                		if(Number(rData.discountrate).toFixed(2)==rData.availableDiscountrate){//如果订单折扣和导入进来的折扣相同
			                			str="<div class='JGtab'><input type='text' id='discountrate_"+rData.id+"' value='"+rData.availableDiscountrate+"' class='b80'></div>";
			                		}else{
			                			str="<div class='fontdel'>订单折扣："+rData.discountrate+"</div><div class='JGtab'><input type='text' id='discountrate_"+rData.id+"' value='"+t_discountrate+"' class='b80'></div>";
			                		}
			                			
			                		return str;
			                		
			                	}
			                },
			                {name: "orderQty", index: "订 数", width: '4%', align: 'center'},
			                {name: "thisSendQty", index: "本次发货数", width: '6%', align: 'center',
			                	formatter:function(value, options, rData){
			                		return "<div class='JGtab'><span >0</span></div>";
			                		//return "<div class='JGtab'><input type='text' default='"+rData.thisSendQty+"' data-id='"+rData.id+"' id='thisSendQty_"+rData.id+"' value='"+rData.thisSendQty+"' class='b80 thisSendQty'></div>";
			                	}
			                },
			                {name: "notEnoughReason", index: "其余满足情况", width: '11%', align: 'center',
			                	formatter:function(value, options, rData){
			                		var html="";
			                		var zt;
				                	if(rData.reportAvailableReason != null && rData.reportAvailableReason != ""){
				                			value=getOtherAvailableReasonStr(rData.reportAvailableReason);
				                			html += "<div>"+value+"</div>";
		                			}
				                	//定数-已发数<=本次发货数
			 	     				if(Number(rData.orderQty)-Number(rData.sendoutQty)<=0){
			 	     					zt = 1;
			 	    				}else{
			 	    					value=(rData.notEnoughReason==null || rData.notEnoughReason=="")?1:rData.notEnoughReason;
			 	    				}
			 	     				html+="<select selectVal="+value+" data-id='"+rData.id +"' id='bmz_"+rData.id+"' style='width:80%'  name='bmz'  flag ='"+zt+"' ><option value=''></option></select>";
			                		return html;
			                	}
			                },
			                {name: "preSendDate", index: "其余预计发货日期", width: '10%', align: 'center',
			                	formatter:function(value, options, rData){
			                		var preSendDate=rData.preSendDate==null?"":rData.preSendDate;
			                		//var preSendDate=rData.preSendDate==null?getToday():rData.preSendDate;
			                		return "<div class='JGtab'><input type='text'  id='preSendDate_"+rData.id+"' value='"+preSendDate+"' class='b80 startTime' onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\"></div>" +
			                				"<span class='u-icon u-icon-date'></span>";
			                	}
			                },
			                {name: "responseRemark", index: "备 注", width: '10%', align: 'center',
			                	formatter:function(value, options, rData){
			                		var html="";
			                		var remark=rData.responseRemark==null?"":rData.responseRemark;
			                		if(rData.historyRemark!=null && rData.historyRemark!=""){
			                			html += "<div>"+rData.historyRemark+"</div>";
			                		}
			                		html+="<div class='JGtab'><input type='text' id='responseRemark_"+rData.id+"' value='"+remark+"' class='b90'></div>";
			                		return html;
			                	}
			                },
			                {name: "sendoutQty", index: "已发数", width: '4%', align: 'center'},
			                {name: "receiveQty", index: "已收数", width: '4%', align: 'center'},
			                {name: "sendoutGoodsCode", index: "操 作", width: '6%', align: 'center',
			                	formatter:function(value, options, rData){
				                	return "<a href='#' id='"+rData.id+"' class='btn_white deleteBtn btn-mini'>移 除</a>";
			                	}
			                }
			            ],// the column discription
			 	  rowNum:999,
			     // multiselect : true,  //显示checkbox选择框
			      rownumbers: true,    //显示左边排名列表
			      viewrecords: true, 
			      //pager: '#sendProduct_page',
			      height:'100%',
			      sortable:false,
			      jsonReader: { 
			    	  root: "rows",
			    	  page: "page",
			    	  total: "total",
			    	  records: "records",
			    	  repeatitems : false
			      }, //设置数据方式
			      postData: {query_id:"findOnlyReportItemsTs",query_type:"JQGRID",rdParseType:"dispersed",reqData:queryReportData()},
			      height: '100%',
			      /* 客户端排序--------------------------------------------------------开始 */
			      loadBeforeSend:function(){
			    	  $("#table_report").jqGrid('clearGridData');
			      },
			      loadError:function(){
			    	  layer.msg("系统报错！",{icon:5});
			      },
			      loadComplete:function(){
			    	  //设置两边高度
			    	  
			    	  $('#left').css({'height':$('#myTabContent').height()});
			    	  
			    	  //加载完成设置表格宽度
			    	  
			    	  $(this).setGridWidth($(this).parents('.GdBox').eq(0).width());
			    	
			    	  
			    	  
			    	  var rowDatas = $(this).jqGrid("getRowData");
			    	  if(rowDatas.length==0){
			 				removeTable(c_index);
			    	  }
			    	  $("#report_pzs").html(rowDatas.length);
			    	  for ( var i = 0; i < rowDatas.length; i++) {
							getNotEnoughReason("report",rowDatas[i].id);
						}
			    	
			    	 $("select[name='bmz']").each(function(){
						   var selectVal = $(this).attr("selectVal");
						   var flag = $(this).attr("flag");
						   selectVal = (selectVal == null || selectVal == '')?1:selectVal;
						   selectVal = flag==1?"":selectVal;
						  $(this).select({
			 					/*url: appPath+'/backto/constant/json/getConstantByCode', //请求地址
			 					para: {typeCode:'BMZ'},*/
							  	localdata:bmz_yz,
			 					selected: selectVal //默认选中的值
			 				});
					   });
			    	 
			    	
	 			  	/**
		 			  * 绑定删除订单事件
					  */
					  $(".deleteBtn").click(function(){
						  	var itemsId=$(this).attr("id");
						  	layer.confirm("确定要移除吗？<br/>本商品将移除，不做任何处理", {
				       			 btn: ['是','否'], //按钮
				   	     		 icon:3,
				   	     		 shade: false //不显示遮罩
			   	  			}, function(indexI){
			   	  				 layer.close(indexI);
								 $("#table_report").jqGrid("delRowData", itemsId); 
								 var objs = $("#table_report").jqGrid("getRowData");
								 if(objs.length==0){
									 removeTable(c_index);
								 }
			   	  			});
					  });
			    	 
		          }
			      
			      /* 客户端排序 --------------------------------------------------------结束*/
			  	});
		}
		/******************** 初始只回告列表  结束*******************/
		/******************** 获取只回告列表查询条件*******************/
		function queryReportData(){
	    	var queryData="{'groupOp':'AND','rules':[";
	    	var a = "{'field':'addUserCode','op':'eq','data':'"+userName+"'},";
	    	queryData = queryData.concat(a);
	    	
	    	queryData = queryData.substring(0,queryData.length-1);
	    	var z= "]}";
	    	queryData = queryData.concat(z);
	    	return queryData;
	    }   
		
		function total(index){
			var objs = $("#table_"+index).jqGrid("getRowData");
			$("#totalVarietyQty_"+index).html(objs.length);
			var totalBookQty=0;//总册数
			var totalPrice=0;//本次发货码洋数
			var totalRealityPrice=0;//本次发货实洋数
			for(var i=0;i<objs.length;i++){
				var rowDataId=objs[i].id;
				var price=Number($("#price_"+rowDataId).val());//定价
				var discountrate=Number($("#discountrate_"+rowDataId).val());//折扣
				var thisSendQty=Number($("#thisSendQty_"+rowDataId).val());//本次发货数
				totalBookQty+=thisSendQty;
				totalPrice+=thisSendQty*price;
				totalRealityPrice+=thisSendQty*price*discountrate/100;
			}
			$("#totalBookQty_"+index).html(totalBookQty);
			$("#totalPrice_"+index).html(totalPrice.toFixed(2));
			$("#totalRealityPrice_"+index).html(totalRealityPrice.toFixed(2));
		}
		
		/**
	     * 验证发货操作
	     * @param sendOutSummary  发货单总目信息
	     * @param responItems     发货单信息信息
	     * @param sendoutGoodsCode	发货单号
	     */
		function checkAndSend(sendOutSummary,responItems,sendoutGoodsCode){
			/**************** 验证发货单是否可以追加  开始**************/
	    	$.ajax({
	    		url:appPath+"/backto/sendGoods/checkSendoutAdditional",
	    		type:"POST",
	    		dataType:"json",
	    		async:false,
	    		data:{ "sendOutSummary":JSON.stringify(sendOutSummary),"responItems":JSON.stringify(responItems) },
	    		success:function(data){
	    			var sendFlag=false;
	    			if(data.success){//验证发货单是否可以追加调用成功
	    				//obj > 1 则追加的发货单品种已收货 ;obj=1  则追加的发货单仓位不一致;obj=0 则可以追加 ;obj= -1 则订单号不重复
	    				if(data.obj==1){
	    					layer.alert("追加的发货单仓位不一致，不能追加！",{icon:2});
	    					return ;
	    				}else if(data.obj>0){
	    					layer.alert("追加的发货单品种已收货，不能追加！",{icon:2});
	    					return ;
	    				}else if(data.obj==0){
	    					var indexI=layer.confirm("系发货单号重复，是否追加？",function(){
	    						layer.close(indexI);
	    						reportAndSendout(sendOutSummary,responItems,sendoutGoodsCode);
	    		            });
	    					
	    				}else if(data.obj==-1){
	    					reportAndSendout(sendOutSummary,responItems,sendoutGoodsCode);
	    				}
	    				
	    				
	    		    	
	    			}else{//验证发货单是否可以追加调用失败
	    				layer.alert(data.msg,{icon:2});
	    				return ;
	    			}
	    			
	    			
	    		},
	    		error:function(data){
	    			
	    		}
	    	})
	    	/**************** 验证发货单是否可以追加  结束**************/
		}
		
		/**
	     * 回告并发货操作
	     * @param sendOutSummary  发货单总目信息
	     * @param responItems     发货单信息信息
	     * @param sendoutGoodsCode	发货单号
	     */
	    function reportAndSendout(sendOutSummary,responItems,sendoutGoodsCode){
	    	
	    	/**************** 模板发货  开始**************/
	    	$.ajax({
	    		url:appPath+"/backto/sendGoods/sendOutFromTemp",
	    		type:"POST",
	    		dataType:"json",
	    		data:{
	    			"sendOutSummary":JSON.stringify(sendOutSummary),
	    			"responItems":JSON.stringify(responItems)
	    		},
	    		beforeSend : function(){
		        	WG.loading.show();
		        },
	    		success:function(data){
	    			WG.loading.hide();
	    			if(data.success){
	    				removeTable(c_index);
	    				layer.msg("发货成功!",{icon:1});
	    			}else{
	    				layer.alert(data.msg,{icon:2});
	    			}
	    			
	    		},
	    		error:function(data){
	    			
	    		}
	    		
	    	})
	    	/**************** 模板发货  结束**************/
	    }
	    
	    /**
	     * 获取其余满足情况
	     * @param tabId 表单id
	     * @param dataId 数据id
	     */
	    function getNotEnoughReason(tabId,dataId){
	    	var rowData=$("#table_"+tabId).jqGrid("getRowData",dataId);
	    	var orderQty=Number(rowData.orderQty);
	    	var sendoutQty=Number(rowData.sendoutQty);
	    	var thisSendQty=Number($("#thisSendQty_"+dataId).val());
			if(rowData.purchaseOrderCode==null || rowData.purchaseOrderCode=="" ||
					orderQty-sendoutQty<=thisSendQty){
				$("#bmz_"+rowData.id+" option:first").prop("selected", true);
				$("#bmz_"+rowData.id+"").prop("disabled",true);
			}else if(orderQty>thisSendQty){
				$("#bmz_"+rowData.id+" option[value='1']").prop("selected", true);
				$("#bmz_"+rowData.id+"").prop("disabled", false);
			}
	    }
	    /**
	     * 
	     * @param tabId
	     */
	    function removeTable(tabId){
	    	 var source=$("#source").val();
			 $("#"+tabId).parent().remove();
			 $("#show_list .active").hide();
			 $("#myTabContent .active").hide();
			 if($("#show_list li:visible").length==0){
				 if(source=="order"){//跳转到相应的页面
					 setTimeout(function(){window.location.href=appPath+"/backto/order/page/toOrderItems";},1000); 
				 }else if(source=="detail"){
					 setTimeout(function(){window.location.href=appPath+"/backto/order/page/toOrderProcessing ";},1000); 
				 }
			 }else{
				 $('#show_list li:visible').eq(0).click();
			 }
		 }
	    /**
	     * 获取当前日期  yyyy-mm-dd
	     */
	    function getToday(){
	    	var timeDay = new Date();
    		var year = timeDay.getFullYear();
			var month = timeDay.getMonth()+1;
			var day = timeDay.getDate();
			if(month < 10){ 
				month = "0" + month; 
			}if(day < 10){ 
				day = "0" + day; 
			} 
			var today=year+"-"+month+"-"+day;
			return today;
	    }
	    
	    /**
	     * 检查发货的品种数
	     */
	    function checkSendoutItemsNum(){
	    	var datas=$("#table_"+c_index).jqGrid("getRowData");
	    	var itemsNum=0;
	    	for(var i=0;i<datas.length;i++){
	    		var rowData=datas[i];
	    		if(rowData.proPurOrderItemsId!=null && rowData.proPurOrderItemsId!=""){
	    			itemsNum++;
	    		}
	    	}
	    	return itemsNum;
	    }
	    
	    /**
	     * 计算字符串的长度
	     * size(汉子为几个字符)
	     */
	    function strLength(str,size)
	    {
	        var realLength = 0, len = str.length, charCode = -1;
	        for (var i = 0; i < len; i++)
	        {
	            charCode = str.charCodeAt(i);
	            if (charCode >= 0 && charCode <= 128)
	            {
	                realLength += 1;
	            }
	            else
	            {
	                realLength += size;
	            }
	        }
	        return realLength;
	    }

});
