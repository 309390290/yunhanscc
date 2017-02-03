/**
 * 订单品种发货处理
 * wangtao
 * 2016年7月14日15:18:32
 */
In.ready('jqGrid','WdatePicker',function() {
	$("#sendoutDate").val(getToday());
	eval("var sendReg=/"+$("#sendReg").val()+"/");
	var  addProductPurs=$("#addProductPurs").val();
	var thisPurchaserId=$("#purchaserId").val();
	var sendGoodsMaxLength=50;
	if(addProductPurs.indexOf(thisPurchaserId)>=0){
		var a =document.getElementById("addProduct");
		a.style.visibility="visible";

	}
	var fatherBody = $(window.top.document.body);
	/*initMasterdataTab();*/
	
	$("#myModal3").mouseover(function(){
		return false;
	})
    /**
     * 打开添加发货model
     */
    $(".addProduct").bind("click",function(){
    	fatherBody.append("<div id='backdropId' class='modal-backdrop' style='z-index:9998; 'filter':'alpha(opacity=60)','opacity':0.6'></div>");
        if(fatherBody.find('#myModal3').length==0){
        	fatherBody.append($('#myModal3').show());
        }else{
        	fatherBody.find('#myModal3').show();
        }
//        setTimeout(function(){
//        },4000)
        window.parent.initMasterdataTab();
    });
    /**
     * 绑定查询商品按钮
     */
    $(".search").bind("click",function(){
    	parent.andSendSearch();
    });
    /**
     * 绑定发货单失去焦点事件
     */
    $("#sendoutGoodsCode").bind("blur",function(){
    	var sendoutGoods=$.trim($("#sendoutGoodsCode").val());
    	if($("#sendReg").val()!="none" && !sendReg.test(sendoutGoods)){
    		layer.tips("发货单号填写错误，请重新填写！",$("#sendoutGoodsCode"));
    		$("#sendoutGoodsCode").focus();
    		$("#sendoutGoodsCode").val(sendoutGoods);
    	}
    });
    
    /**
     * 绑定跳转到发货单详情事件
     */
    $(document).on('click','.goto',function(){
    	
    	var _parent = window.parent;
    	var sendoutSummaryId=$(this).attr("id");
    	var url=appPath+"/backto/sendQuery/page/detail?id="+sendoutSummaryId;
    	_parent.location.href=url;
    	
    });
    
    /**
     * 添加到商品区域按钮绑定
     */
    $(".addProductBtn").bind("click",function(){
    	parent.addProductToAddView();
    });
    /**
     * 新增行
     */
    $(".addRow").bind("click",function(){
    	parent.addRow();
    });
    
    /**
     * 删除行
     */
    $(".deleteData_btn").bind("click",function(){
    	parent.deleteRowData();
    });
    
    
    /**
     * 关闭添加发货model
     */
    $('.closeAddSend').click(function () {
    	total();
    	fatherBody.find('#myModal3').hide()
        fatherBody.find('#backdropId').remove();
    });
    
    /**
     * 绑定发货单发送按钮点击事件
     */
    $(".sendBtn").bind("click",function(){
    	var sendFlag=true;//true:可以发货  false:不能发货
    	var msgCode="";//检查是否可以发货返回编码
    	
    	
    	var reportIds=$("#reportIds").val();//回告细目ids 
    	var orderItemsIds="";//订单细目ids
    	
    	var purchaserId=$("#purchaserId").val();//采购商id
    	var supplierId=$("#supplierId").val();//供应商id
    	var receiveWarehouse=$("#receiveWarehouse").val();//收货仓位
    	
    	var datas=$("#sendProduct_List").jqGrid("getRowData");
    	var responItems=[];
    	var sendOutSummary={};
    	
    	
    	
    	/**************不可修改的发货单头信息 ***************/
    	sendOutSummary.totalVarietyQty=$("#totalVarietyQty").text();//发货品种数
    	sendOutSummary.totalBookQty=$("#totalBookQty").text();//本次发货数
    	sendOutSummary.totalPrice=$("#totalPrice").text();//本次发货总码洋
    	sendOutSummary.totalRealityPrice=$("#totalRealityPrice").text();//本次发货总实洋
    	sendOutSummary.receiveUserName=$("#receiveUserName").text();//收货人
    	sendOutSummary.receivePhoneno=$("#receivePhoneno").text();//联系电话
    	sendOutSummary.receiveAddress=$("#receiveAddress").text();//收货地址
    	
    	sendOutSummary.responseItemsIds=reportIds;//所有要回告的回告细目ids
    	sendOutSummary.purchaserId=purchaserId;//采购商id
    	sendOutSummary.supplierId=supplierId;//供应商id
    	sendOutSummary.receiveWarehouse=receiveWarehouse;//收货仓位
    	
    	
    	/**************可修改的发货单头信息 ***************/
    	var sendoutGoodsCode=$.trim($("#sendoutGoodsCode").val());//发货单号
    	var	sendoutDate=$("#sendoutDate").val();//发货日期
    	var	sendoutStation=$.trim($("#sendoutStation").val());//发到站
    	var	transportCompany=$.trim($("#transportCompany").val());//承运商
    	var	transportNo=$.trim($("#transportNo").val());//运单号
    	var	transportMode=$.trim($("select[id=transportMode]").val());//运货方式
    	var	pakagesQty=$.trim($("#pakagesQty").val());//包件数
    	var	sendoutRemark=$.trim($("#sendoutRemark").val());//发货单备注
    	
    	if($("#sendReg").val()!="none" && !sendReg.test(sendoutGoodsCode)){
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
    	
    	sendOutSummary.sendoutGoodsCode=sendoutGoodsCode;//发货单号
    	sendOutSummary.sendoutDate=sendoutDate;//发货日期
    	sendOutSummary.sendoutStation=sendoutStation;//发到站
    	sendOutSummary.transportCompany=transportCompany;//承运商
    	sendOutSummary.transportNo=transportNo;//运单号
    	sendOutSummary.transportMode=transportMode;//运货方式
    	sendOutSummary.pakagesQty=pakagesQty;//包件数
    	sendOutSummary.sendoutRemark=sendoutRemark;//发货单备注
    	/*****	发货品种信息  ****/
    	for(var i=0;i<datas.length;i++){
    		var pro={};
    		var rowData=datas[i];
    		pro.id=rowData.id;//回告细目id
    		if(orderItemsIds=="" && rowData.proPurOrderItemsId!=null && rowData.proPurOrderItemsId!=""){
    			orderItemsIds=rowData.proPurOrderItemsId;
    		}else if(orderItemsIds!="" && rowData.proPurOrderItemsId!=null && rowData.proPurOrderItemsId!=""){
    			orderItemsIds+=","+rowData.proPurOrderItemsId;
    		}
    		pro.proPurOrderItemsId=rowData.proPurOrderItemsId;//订单细目id
    		pro.purchaseOrderCode=rowData.purchaseOrderCode;//采购订单号
    		pro.supplierCommoditiesId=rowData.supplierCommoditiesId;//供应商商品id
    		pro.isSupplierAddProduct=rowData.isSupplierAddProduct;//是否供应商添加
    		pro.isbn=rowData.isbn;//isbn
    		pro.bookTitle=rowData.bookTitle;//书名
    		pro.availablePrice=rowData.availablePrice;//可供价
    		pro.availableDiscountrate=rowData.availableDiscountrate;//可供折扣
    		pro.orderQty=rowData.orderQty;//订数
    		pro.thisSendQty=rowData.thisSendQty;//本次发货数
    		pro.historyOrderQty=rowData.historyOrderQty;//已发数
    		responItems.push(pro);
    	}
    	
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
    	
    });
    
  //发货信息收起
    $('#TABopen').toggle(
        function() {
        	
        	   $(this).parent().next().height('auto');
               $(this).text(' 收 起');
               $(this).prev().removeClass('unfold');
               $(this).prev().addClass('fold')
        	
        	
        	
//        	$(this).parent().next().stop(true,true).slideUp();
//            $(this).text(' 展 开');
//            $(this).prev().removeClass('fold');
//            $(this).prev().addClass('unfold')
        },function(){
//            $(this).parent().next().stop(true,true).slideDown();
//            $(this).text(' 收 起');
//            $(this).prev().removeClass('unfold');
//            $(this).prev().addClass('fold')
        	
        	 $(this).parent().next().height(0);
          $(this).text(' 展 开');
          $(this).prev().removeClass('fold');
          $(this).prev().addClass('unfold')
        }
    );
    
    
    function queryData(){
    	var reportIds=$("#reportIds").val();
    	
    	var queryData="{'groupOp':'AND','rules':[";
    	var a = "{'field':'ids','op':'eq','data':'"+reportIds+"'},";
    	queryData = queryData.concat(a);
    	
    	
    	queryData = queryData.substring(0,queryData.length-1);
    	var z= "]}";
    	queryData = queryData.concat(z);
    	return queryData;
    }
    
    function total(){
    	var objs = $("#sendProduct_List").jqGrid("getRowData");
    	$('#totalVarietyQty').text(objs.length);
    	var totalBookQty=0;//总册数
    	var totalPrice=0;//本次发货码洋数
    	var totalRealityPrice=0;//本次发货实洋数
    	for(var i=0;i<objs.length;i++){
    		var rowData=objs[i];
    		var price=Number(rowData.availablePrice);//定价
    		var discountrate=Number(rowData.availableDiscountrate);//折扣
    		var thisSendQty=Number(rowData.thisSendQty);//本次发货数
    		totalBookQty+=thisSendQty;
    		totalPrice+=thisSendQty*price;
    		totalRealityPrice+=thisSendQty*price*discountrate/100;
    	}
    	$("#totalBookQty").text(totalBookQty);
    	$("#totalPrice").text(totalPrice.toFixed(2));
    	$("#totalRealityPrice").text(totalRealityPrice.toFixed(2));
    }
    
    /*****************加载发货商品明细列表  开始****************/
    $("#sendProduct_List").jqGrid({
  		url:appPath+'/console/query', 
		width: 820, 
 		shrinkToFit: true, 
 		datatype: "json", 
 		mtype:"post",
 		colNames:["id","订单细目id","供应商商品id","是否供应商添加","已发数","隐藏订单号","订单号","ISBN","商品名称","发货价","发货折扣（%）","订 数","本次发货数"],// the column header names
        colModel :[
            {name:"id", index:"id",hidden:true},
            {name:"proPurOrderItemsId", index:"proPurOrderItemsId",hidden:true},
            {name:"supplierCommoditiesId", index:"supplierCommoditiesId",hidden:true},
            {name:"isSupplierAddProduct", index:"isSupplierAddProduct",hidden:true},
            {name:"historyOrderQty", index:"historyOrderQty",hidden:true},
            {name:"purchaseOrderCode", index:"purchaseOrderCode",hidden:true},
            {name:"purchaseOrderCodeStr", index:"purchaseOrderCodeStr",width:'15%',sortable:false,formatter : function(value, options, rData) {
            		if(rData.isSupplierAddProduct=="Y"){
            			return "<i class=\"top_ico\">供应商发货添加</i>";
            		}else{
            			return rData.purchaseOrderCode;
            		}
			  },align:'center'
            },
            {name:"isbn", index:"isbn",width:'15%',sortable:false,align:'center'},
            {name:"bookTitle", index:"bookTitle",width:'30%',sortable:false,align:'center'},
            {name:"availablePrice", index:"availablePrice",width:'10%',sortable:false,align:'center'},
            {name:"availableDiscountrate", index:"availableDiscountrate",sortable:false,width:'12%',align:'center'},
            {name:"orderQty", index:"orderQty",width:'10%',sortable:false,align:'center'},
            {name:"thisSendQty", index:"thisSendQty",width:'10%',sortable:false,align:'center'}
        ],
 	  rowNum:9999,
 	  rowList:[20,50,100],
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
      postData: {query_id:"findProResponseItems",query_type:"JQGRID",rdParseType:"dispersed",reqData:queryData()},
      height: '100%',
      /* 客户端排序--------------------------------------------------------开始 */
      loadBeforeSend:function(){
    	  $("#sendProduct_List").jqGrid('clearGridData');
      },
      loadError:function(){
    	  layer.msg("系统报错！",{icon:5});
      },
      onPaging : function(pgButton){
    	  $("#sendProduct_List").jqGrid('setGridParam',{datatype:'json',page:1,postData:{reqData:queryData(),_search1:false}});
      }, 
      loadComplete : function(data) {	     
    	  total();
    	  WG.JGscrollX.Event($(this),'JGtab',true);
      }
      
      /* 客户端排序 --------------------------------------------------------结束*/
  	});
    
    var newrowid3=0 ;
    /*****************加载发货商品明细列表  结束****************/
    $("#save").click(function(){
    	var data=new Array();
    	newrowid3 = newrowid3+1;
        var intData=20160001;
        newrowid3=newrowid3+intData;
        newrowid3=parseInt(newrowid3)
        var datas=$("#sendProduct_List").jqGrid("getRowData");
    	var ids = fatherBody.find("#table_d").jqGrid('getGridParam','selarrrow');
        if(ids.length==0){
        	parent.alertMsg("请选择要添加商品明细!");
        	return ;
        }
    	for ( var i = 0; i < ids.length; i++) {
    		var rowData = fatherBody.find("#table_d").jqGrid('getRowData',ids[i]);
    		var isbn="";
    		var booktitle="";
    		if(rowData.isQueryData=="true"){
    			isbn= $.trim(fatherBody.find("#"+rowData.id+"_isbn").text());
    			booktitle=$.trim(fatherBody.find("#"+rowData.id+"_booktitle").text());
    		}else{
    			isbn=$.trim(fatherBody.find("#"+rowData.id+"_isbn").val());
    			booktitle=$.trim(fatherBody.find("#"+rowData.id+"_booktitle").val());
    		}
    		
    		var sendprice=$.trim(fatherBody.find("#"+rowData.id+"_sendprice").val());
    		var sendoutDiscountrate=$.trim(fatherBody.find("#"+rowData.id+"_sendoutDiscountrate").val());
    		var thisSendQty=$.trim(fatherBody.find("#"+rowData.id+"_sendQty").val());
    		dataRow={
    				id:newrowid3,
    				proPurOrderItemsId:"",
    				supplierCommoditiesId:"",
    				isSupplierAddProduct:"Y",
    				historyOrderQty:"",
    				purchaseOrderCode:"",
    				isbn:isbn,
    				bookTitle:booktitle,
    				availablePrice:sendprice,
    				availableDiscountrate:sendoutDiscountrate,
    				orderQty:"",
    				thisSendQty:thisSendQty
    		}
    		var dataFlag=parent.checkAddDatas(dataRow);
    		if(dataFlag){
    			return ;
    		}
    		var isRepeatf=parent.productIsRepeat(datas,dataRow);
    		if(isRepeatf){
    			parent.alertMsg("请勿重复添加！");
				return ;
    		}
    		//将新添加的行插入到第一列  
    	    $("#sendProduct_List").jqGrid("addRowData",newrowid3,dataRow, "first");  
    	    //设置grid单元格不可编辑  
    	    $("#sendProduct_List").setGridParam({cellEdit:false});  
		}
    	parent.alerSuccesstMsg("添加成功!");
    	total();
    	fatherBody.find('#myModal3').hide()
        fatherBody.find('#backdropId').remove();
    	
    });
    
    
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
    		data:{ "sendOutSummary":JSON.stringify(sendOutSummary),"responItems":JSON.stringify(responItems) },
    		success:function(data){
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
    	/**************** 页面发货  开始**************/
    	$.ajax({
    		url:appPath+"/backto/sendGoods/sendOutFromPage",
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
    				msg = "发送成功！<a  class='goto' id='"+data.obj+"' href='javascript:void(0);'>查看发货单号:"+sendoutGoodsCode+"</a>";
    				/*var indexI=layer.confirm(msg, {
    					icon:1,
    	     		    btn: ['确定'], //按钮
    	       		}, function(){
    	       			//location.href=appPath+"/backto/order/page/toOrderItems";
    	       			layer.close(indexI);
    	       			parent.closeSendGoods();
    		   		});*/
    				var indexI=layer.alert(msg,{icon:1,closeBtn:0},function(){
						layer.close(indexI);
    	       			parent.closeSendGoods();
	    			});
    			}else{
    				layer.alert(data.msg,{icon:2});
    			}
    			
    		},
    		error:function(data){
    			
    		}
    		
    	})
    	/**************** 页面发货  结束**************/
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


