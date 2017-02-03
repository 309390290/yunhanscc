
In.ready('multipleDataBox','autocomplete','jqGrid','WdatePicker',function() {
	eval("var sendReg=/"+$("#sendReg").val()+"/");
	var re = /^\+?[1-9][0-9]*$/ ; 
	var sendGoodsMaxLength=50;
	
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
	 * 绑定发货按钮
	 */
	$(".sendOutBtn").bind("click",function(){
		
		var responItems=[];
    	var sendOutSummary={};
    	var receiveAddress=$("#receiveAddress").val();//收货地址
    	var receivePhoneno=$("#receivePhoneno").val();//收货电话
    	var receiveUserName=$("#receiveUserName").val();//收货人
		var sendoutGoodsCode=$.trim($("#sendoutGoodsCode").val());//发货单号
		var sendoutDate=$("#sendoutDate").val();//发货日期
		var sendoutStation=$.trim($("#sendoutStation").val());//发到站
		var transportMode=$("#transportMode").find("option:selected").val();//运输方式
		var transportNo=$.trim($("#transportNo").val());//运单号
		var transportCompany=$.trim($("#transportCompany").val());//承运商
		var pakagesQty=$.trim($("#pakagesQty").val());//包件数
		var sendoutRemark=$("#sendoutRemark").val();//发货单备注
		var receiveWarehouse=$("#receiveWarehouse").val();//仓 位
		var purchaserId=$("#purchaserId").val();//采购商id
		
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
    	
    	sendOutSummary.purchaserId=purchaserId;//
    	sendOutSummary.supplierId=sapvendorId;
    	sendOutSummary.receiveWarehouse=receiveWarehouse;//收货仓位
    	sendOutSummary.sendoutGoodsCode=sendoutGoodsCode;//发货单号
    	sendOutSummary.sendoutDate=sendoutDate;//发货日期
    	sendOutSummary.sendoutStation=sendoutStation;//发到站
    	sendOutSummary.transportCompany=transportCompany;//承运商
    	sendOutSummary.transportNo=transportNo;//运单号
    	sendOutSummary.transportMode=transportMode;//运货方式
    	sendOutSummary.pakagesQty=pakagesQty;//包件数
    	sendOutSummary.sendoutRemark=sendoutRemark;//发货单备注
    	sendOutSummary.receiveAddress=receiveAddress;
    	sendOutSummary.receivePhoneno=receivePhoneno;
    	sendOutSummary.receiveUserName=receiveUserName;
    	
    	var datas=$("#blankTemp_list").jqGrid("getRowData");
    	/*****	发货品种信息  ****/
    	for(var i=0;i<datas.length;i++){
    		var pro={};
    		var rowData=datas[i];
    		var availablePrice=$("#availablePrice_"+rowData.id).val();//发货价
    		var availableDiscountrate=$("#availableDiscountrate_"+rowData.id).val();//发货折扣
    		var thisSendQty=$("#thisSendQty_"+rowData.id).val();//本次发货数
    		var responseRemark=$("#remark_"+rowData.id).val();//备注
    			
    		if(availablePrice==""){
				layer.tips('发货价不能为空!',$("#availablePrice_"+rowData.id));
				return ;
			}if(isNaN(availablePrice) || Number(availablePrice)<=0 ){
				layer.tips('发货价不合法!',$("#availablePrice_"+rowData.id));
				return ;
			}if(availableDiscountrate==""){
				layer.tips('发货折扣不能为空!',$("#availableDiscountrate_"+rowData.id));
				return ;
			}if(isNaN(availableDiscountrate) || Number(availableDiscountrate)<=0 ){
				layer.tips('折扣不合法!',$("#availableDiscountrate_"+rowData.id));
				return ;
			}if(thisSendQty=="" || isNaN(thisSendQty) || !re.test(thisSendQty)){
				layer.tips('本次发货数必须为正整数!',$("#thisSendQty_"+rowData.id));
				return;
			}
			
			
    		pro.isSupplierAddProduct="Y";//是否供应商添加
    		pro.isbn=rowData.isbn;//isbn
    		pro.bookTitle=rowData.bookTitle;//书名
    		pro.availablePrice=availablePrice;
    		pro.availableDiscountrate=availableDiscountrate;
    		pro.thisSendQty=thisSendQty;
    		pro.responseRemark=responseRemark;
    		pro.purchaserId=purchaserId;//
    		pro.supplierId=sapvendorId;
    		responItems.push(pro);
    	}
    	
    	/**************** 验证发货单是否可以追加  开始**************/
    	$.ajax({
    		url:appPath+"/backto/sendGoods/checkSendoutAdditional",
    		type:"POST",
    		dataType:"json",
    		data:{ "sendOutSummary":JSON.stringify(sendOutSummary) },
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
    					var indexI=layer.confirm("发货单号重复，是否追加？",function(){
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
    	
    	
	});
	
	
	/**
     * 回告并发货操作
     * @param sendOutSummary  发货单总目信息
     * @param responItems     发货单信息信息
     * @param sendoutGoodsCode	发货单号
     */
    function reportAndSendout(sendOutSummary,responItems,sendoutGoodsCode){
    	
    	/**************** 模板发货  开始**************/
    	$.ajax({
    		url:appPath+"/backto/sendGoods/sendOutFromBlankTemp",
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
    				layer.msg("发货成功!",{icon:1});
    				setTimeout(function(){window.location.href=appPath+"/backto/order/page/toOrderItems";},1000);
    			}else{
    				layer.alert(data.msg,{icon:2});
    			}
    			
    		},
    		error:function(data){
    			
    		}
    		
    	})
    	/**************** 模板发货  结束**************/
    }

    $("#blankTemp_list").jqGrid({
    	url:appPath+'/console/query', 
		autowidth: true, 
 		shrinkToFit: true, 
 		datatype: "json", 
 		mtype:"post",
        colNames:["id","ISBN","商品名称","发货价","发货折扣","本次发货数","备 注"],// the column header names
        colModel :[
            {name:"id", index: "id", hidden:true},
            {name:"isbn", index:"isbn",width:'15%',align:'center'},
            {name:"bookTitle", index:"bookTitle",width:'30%',align:'center'},
            {name:"availablePrice", index:"availablePrice",width:'10%',align:'center',
            	formatter:function(value, options, rData){
            		var availablePrice=rData.availablePrice==null?"":rData.availablePrice;
            		return "<div class='JGtab'><input type='text'  default='"+availablePrice+"' id='availablePrice_"+rData.id+"' value='"+availablePrice+"' class='b80 total'></div>";
            	}
            },
            {name:"availableDiscountrate", index:"availableDiscountrate",width:'10%',align:'center',
            	formatter:function(value, options, rData){
            		var availableDiscountrate=rData.availableDiscountrate==null?"":rData.availableDiscountrate;
            		return "<div class='JGtab'><input type='text'  default='"+availableDiscountrate+"'  id='availableDiscountrate_"+rData.id+"' value='"+availableDiscountrate+"' class='b80 total'></div>";
            	}
            },
            {name:"thisSendQty", index:"thisSendQty",width:'10%',align:'center',
            	formatter:function(value, options, rData){
            		var thisSendQty=rData.thisSendQty==null?"":rData.thisSendQty;
            		return "<div class='JGtab'><input type='text'  default='"+thisSendQty+"' id='thisSendQty_"+rData.id+"' value='"+thisSendQty+"' class='b80 total'></div>";
            	}
            },
            {name:"remark", index:"备 注",width:'25%',align:'center',
            	formatter:function(value, options, rData){
            		return "<div class='JGtab'><input type='text'  id='remark_"+rData.id+"'  class='b80'></div>";
            	}
            }
        ],// the column discription
        rowNum:999,
        //multiselect : true,  //显示checkbox选择框
        //rownumbers: true,    //显示左边排名列表
        pager: '#blankTemp_page',
        height:'100%',
        rownumbers: true,    //显示左边排名列表
        viewrecords: true, 
        sortable:false,
        jsonReader: { 
    	  root: "rows",
    	  page: "page",
    	  total: "total",
    	  records: "records",
    	  repeatitems : false
        }, //设置数据方式
        postData: {query_id:"findBlankTempReportItemsTs",query_type:"JQGRID",rdParseType:"dispersed",reqData:queryData()},
        height: '100%',
        loadComplete:function(){
        	WG.JGscrollX.Event($(this),'GdBox');
        	total();
        	//注册change事件
			  $(".total").on("change",function(){
 				  var _default = $(this).attr("default");
 				  var _value=$.trim($(this).val());
 				  if( _value=="" ||  isNaN(_value) || Number(_value)<=0){
 					 layer.tips('输入不合法',$(this));
 					 $(this).val(_default);
 		    		 return ;
 				  }
 				 $(this).attr("default",_value);
				  total();
			  });
        }
    });
    
    
    
    function queryData(){
    	var queryData="{'groupOp':'AND','rules':[";
    	var a = "{'field':'addUserCode','op':'eq','data':'"+userName+"'},";
    	queryData = queryData.concat(a);
    	queryData = queryData.substring(0,queryData.length-1);
    	var z= "]}";
    	queryData = queryData.concat(z);
    	return queryData;
    }
    /**
     * 统计
     */
    function total(){
    	var objs = $("#blankTemp_list").jqGrid("getRowData");
    	$("#totalVarietyQty").html(objs.length);
    	var totalBookQty=0;//总册数
    	var totalPrice=0;//本次发货码洋数
    	var totalRealityPrice=0;//本次发货实洋数
    	for(var i=0;i<objs.length;i++){
    		var rowDataId=objs[i].id;
    		var price=Number($("#availablePrice_"+rowDataId).val());//定价
    		var discountrate=Number($("#availableDiscountrate_"+rowDataId).val());//折扣
    		var thisSendQty=Number($("#thisSendQty_"+rowDataId).val());//本次发货数
    		totalBookQty+=thisSendQty;
    		totalPrice+=thisSendQty*price;
    		totalRealityPrice+=thisSendQty*price*discountrate/100;
    	}
    	$("#totalBookQty").html(totalBookQty);
    	$("#totalPrice").html(totalPrice.toFixed(2));
    	$("#totalRealityPrice").html(totalRealityPrice.toFixed(2));
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
