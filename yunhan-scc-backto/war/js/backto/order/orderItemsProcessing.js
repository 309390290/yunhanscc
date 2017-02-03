/**
 * 订单处理-品种方式
 * wangtao
 * 2016年7月14日13:40:46
 */

In.ready('multipleDataBox','AllSelect','autocomplete','select','bmz','uploadBox','jqGrid','WdatePicker','fileUtil','SetJqGridList',function() {
	
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
	//回告对象
	var responseOrder = {
			proPurOrderItemsId : null,
			availablePrice : 0,
			availableDiscountrate : 0,
			thisSendQty : 0,
			otherAvailableReason : null,
			preSendDate : null,
			responseRemark : null,
			purchaserId : null
	};
	//合计品种
	var totalVariety =0,
	//合计本次发货数
	totalSendQty = 0,
	//合计本次发货码洋
	totalSendPrice = 0.00,
	//合计本次发货实洋
	totalSendRealPrc = 0.00;
	
	var tbPage = 1;//页面
	
	//点击订单链接进入订单方式-订单详情，返回本页面的时候，页面输入框多了个1，所以在加载的时候清空
	$("#sendDateEnd").val("");
	$("#isbn").val("");
	$("#isbnTwo").val("");
	//$("#orderCode").val("");
	//$("#orderCodeTwo").val("");
	
	
	
	
	
	/*******订单种类开始******/
	var orderTypeRe = $("#orderTypeRe").val();
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
	var isValidRe = $("#isValidRe").val();
	
	var isValidJson = [
	                 {id:'all',name:"全部",check:'false'},
	                 {id:'N',name:"采购商关闭",check:'false'},
	                 {id:'X',name:"供应商关闭",check:'false'},
	                 {id:'Y',name:"已处理",check:'false'}
	                 ];
	
	if(isValidRe!=null && isValidRe!=''){
		for(var i=0;i<isValidJson.length;i++){
			if(isValidJson[i].id==isValidRe){
				isValidJson[i].check='true';
			}
		}
	}else{
		isValidJson[0].check = 'true';
	}
	
	
	var isValidOneJson = [
		                 {id:'all',name:"全部",check:'false'},
		                 {id:'noReport',name:"未回告",check:'false'},
		                 {id:'preSend',name:"预计可发",check:'false'},
		                 {id:'tempStockout',name:"无货待加印",check:'false'},
		                 {id:'newWaitWarehousing',name:"新书待入库",check:'false'}
		                 ];
	
	var otherReasonRe = $("#otherReasonRe").val();
	var controlFlagRe = $("#controlFlagRe").val();
	if((otherReasonRe!=null && otherReasonRe!='') || (controlFlagRe!=null && controlFlagRe!='') ){
		for(var i=0;i<isValidOneJson.length;i++){
			if(isValidOneJson[i].id=="preSend" && otherReasonRe==PREDICT_SEND){//预计可发
				isValidOneJson[i].check='true';
				otherReasonRe="preSend";
			}else if(isValidOneJson[i].id=="tempStockout" && otherReasonRe==TEMPORARILY_STOCKOUT){//暂时缺货
				isValidOneJson[i].check='true';
				otherReasonRe="tempStockout";
			}else if(isValidOneJson[i].id=="noReport" && controlFlagRe=="0"){//未回告
				isValidOneJson[i].check='true';
				otherReasonRe="noReport";
			}else if(isValidOneJson[i].id=="newWaitWarehousing" && otherReasonRe==NEW_WAITING_WAREHOUSING){//新书待入库
				isValidOneJson[i].check='true';
				otherReasonRe="newWaitWarehousing";
			}
		}
		
	}else{
		isValidOneJson[0].check = 'true';
	}
	//存在查询条件时
	if(orderTypeRe!=null && orderTypeRe!=''){
		//循环获取每一个种类数据
		for(var i=0;i<dataJson.length;i++){
			//循环判断每一个种类的id是否在查询条件中，若有则设置选中
			for( var aName in dataJson[i]){
				if(aName!='id') continue;
				var index = orderTypeRe.indexOf(dataJson[i].id);
				if(index >-1 ){	
					if(dataJson[i].id==0 || dataJson[i].id==5){
						if(orderTypeRe.substr(index-1,1)==1
								||orderTypeRe.substr(index-1,1)==2
								||orderTypeRe.substr(index-1,1)==3){
							continue;
						}
						dataJson[i].check='true';
					}else{
						dataJson[i].check='true';	
					}
				}
			} 
		}
		
	}else{//无查询条件时
		dataJson[0].check = 'true';
	}
	$(".AllSelect").AllSelect({
		data:dataJson
	 });
	$(".AllSelectTwo").AllSelect({
		data:dataJson
	 });
	$(".isValidTwo").AllSelect({
		data:isValidJson
	 });
	$(".isValidOne").AllSelect({
		data:isValidOneJson
	 });
	$(".selectData").val(orderTypeRe);
	
	$(".isValidTwo .selectData").val(isValidRe);
	
	$(".isValidOne .selectData").val(otherReasonRe);
	/*******订单种类结束******/
	
	 var tabActive=$('#headActive li'),  //tab切换
     tabCom= $('.contentBox .Tab');//tab内容

 //tab切换
 tabActive.click(function(){
     tabActive.removeClass('active');
     $(this).addClass('active');
     tabCom.hide();
     tabCom.eq($(this).index()).show();
     $("#table").setGridWidth($('.contentBox').eq(0).width())//设置表格宽度
    
 });
	
	//初始化采购商查询条件
    var datatype = 'local';
    var userNameTemp = userName+"orderTiems";
	if(sessionStorage[userNameTemp]){
		var searchData = JSON.parse(sessionStorage.getItem(userNameTemp));
		//按照循环返回值，使其选择。
		for(var name in searchData){
			
			//checked勾选框
			if(name=='isNew'||name=='controlFlag'||name=='isDeal'||name=='isExport'||name=='isExportTwo'||name=='isNewTwo'){
				if(name!='dc_all' && searchData[name].indexOf("all")>-1){
					$("#"+searchData[name]).parent().find("input").attr("checked",true);
				}else{
					$("#"+searchData[name]).parent().find("input").attr("checked",false);
					$("#"+searchData[name]).attr("checked",true);
				}
				continue;
			}
			if('orderType'==name){
				//ordertTypeSelect(searchData[name]);
			}
			$("#"+name).val(searchData[name]);
		}
		//仓位选 
		if($("#pur_all").val()){
			getDC($("#pur_all").val());
			$("#dc_all").val(searchData["dc_all"]);
			reloadDcInfo();
		}
		//仓位选 
		if($("#pur_allTwo").val()){
			getDCTwo($("#pur_allTwo").val());
			$("#dc_allTwo").val(searchData["dc_allTwo"]);
			reloadDcInfoTwo();
		}
		datatype = 'json';
		
		//清理掉暂存的查询条件
//		sessionStorage.clear();
		tbPage = 1;
		//清除数据
		$("#sendGoodsTypeRe").val('');
		$("#otherReasonRe").val('');
		search();
		var  isDeal=$("#isDealRe").val();
		if(isDeal=="Y"){
			 	tabActive.removeClass('active');
			 	tabActive.eq(1).addClass('active');
			 	  tabCom.hide();
			   tabCom.eq(1).show();
		}
	}else{
		//默认为全部
		//ordertTypeSelect();
		var purchaserId = $("#pur_all").val();
		loadData(purchaserId);
		var purchaserIdTwo = $("#pur_allTwo").val();
		loadDataTwo(purchaserIdTwo);
		
		var  isDeal=$("#isDealRe").val();
		if(isDeal=="Y"){
			 	tabActive.removeClass('active');
			 	tabActive.eq(1).addClass('active');
			 	  tabCom.hide();
			   tabCom.eq(1).show();
		}
		datatype = 'json';
		tbPage = 1;
		//清除数据
		$("#sendGoodsTypeRe").val('');
		$("#otherReasonRe").val('');
		//search();
	}
	
		
    //isbn
    function isbn(id){
        id.multipleDataBox({
            title:"ISBN"
//            test:true //测试数据
//            query:true//模糊查询开关，如果为false, url和para属性将无作用
//            url:'#url_path', //请求地址
//            para:{ id:222 } //请求参数
        });
    }
    isbn($('.isbn-btn'));
    isbn($('.isbn-btn2'));

    //订单号

    function ddh(id){
        id.multipleDataBox({
            title:"订单号码"
//            test:true, //测试数据
//            query:true, //模糊查询开关，如果为false, url和para属性将无作用
//            url:'#url_path', //请求地址
//            para:{ id:222 } //请求参数
        });
    }
    ddh($('.ddh-btn'));
    ddh($('.ddh-btn2'));

    //商品名称
    function spmc(id){
        id.multipleDataBox({
            title:"商品名称"
//            test:true, //测试数据
//            query:true, //模糊查询开关，如果为false, url和para属性将无作用
//            url:'#url_path', //请求地址
//            para:{ id:222 } //请求参数
        });
    }
    spmc($('.spmc-btn'));
    spmc($('.spmc-btn2'));


    $('.del').click(function(){
        $(this).parent().find('input').val('')
    });


    //时间 一个月前 后效果
    $('.time_box a ').click(function(){
        $(this).parent().find('a').removeClass('active');
        $(this).addClass('active');
    });


    //批量录入日期
    $('.startTime').click(function(){
        WdatePicker()
    });

    //展开收起搜索
    $('.shouListBtn').toggle(
            function() {
                $(this).parents('.searchBox').find('.left ul .hide').show();
                $(this).find('.img').removeClass('unfold');
                $(this).find('.img').addClass('fold');
                $(this).find('.text').text('收 起');
            },function(){
                $(this).parents('.searchBox').find('.left ul .hide').hide();
                $(this).find('.img').removeClass('fold');
                $(this).find('.img').addClass('unfold');
                $(this).find('.text').text('展 开');
            }
    );

    //展开收起搜索
    /*$('.shouListBtn').toggle(
        function() {
            var length=$(this).parents('.searchBox').find('.left ul li').length;
            var height=Math.ceil(length/3)*34+10;
            $(this).parents('.searchBox').find('.left ul').animate({'height':height});
            $(this).parents('.right').animate({'height':height});
            $(this).find('.text').text(' 收 起');
            $(this).find('.img').removeClass('unfold');
            $(this).find('.img').addClass('fold');
        },function(){
            $(this).parents('.searchBox').find('.left ul').animate({'height':'44'});
            $(this).parents('.right').animate({'height':'44'});
            $(this).find('.text').text(' 展 开');
            $(this).find('.img').removeClass('fold');
            $(this).find('.img').addClass('unfold');
        }
    );*/
/*
    //jgrid排序
    function sortTab(sort){
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
        })
    }
    sortTab($('.sortBoxLi li'));*/

    //JQ grid排序方式
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
    
    
    //JQ grid排序方式
    var sortTwo= $('#sortBoxTwo li');
    sortTwo.click(function(){
    	sortTwo.removeClass('active');
        $(this).addClass('active');
        sortTwo.each(function(){
            $(this).text($(this).text().replace('↑','↓'));

        });
        if($(this).attr('name')=='click'){
            $(this).text($(this).text().replace('↓','↑'));
            $(this).attr('name','');
        }else {
            $(this).text($(this).text().replace('↑','↓'));
            sortTwo.each(function(){
                $(this).attr('name','');
            });
            $(this).attr('name','click');
        }
        //查询
        searchTwo();
    });
    $('#table').jqGridScroll();
    //待处理品种
    $("#table").jqGrid({
    	url: appPath+'/console/query',
		mtype:'post',
		autowidth : true, // 自适应宽度
		shrinkToFit : true, // 列自适应
		datatype : datatype,
        colNames:["id","","","","订单折扣","发货数","发送日期","制单日期","订单号","订单备注","商品信息","定 价","折扣（%）","订 数","发货数暂存","本次发货数","","其余满足情况","其余预计发货日期","备 注","已发数","已收数"],// the column header names
        colModel :[
            {name:"id", index:"id", hidden:true},
            {name:"warehouse", index:"warehouse", hidden:true},
            {name:"sendGoodsType", index:"sendGoodsType", hidden:true},
            {name:"orderType", index:"orderType", hidden:true},
            {name:"discountrateBlank", index:"订单折扣",width:'7%',align:'center', hidden:true,
            	formatter:function(value,options,rData){
            		return rData.discountrate;
            	}},
            {name:"sendoutQty1", index:"sendoutQty1", hidden:true},
            {name:"sendDate", index:"发送日期",width:'7%',align:'center',sortable: false,
            	formatter:function(value,options,rData){
            		return value = value==null ? '':value.substr(0,10);
            	}	
            },
            {name:"addDate", index:"制单日期",width:'7%',align:'center',sortable: false,hidden:true,
            	formatter:function(value,options,rData){
            		return value = value==null ? '':value.substr(0,10);
            	}		
            },
            {name:"purchaseOrderCode", index:"订单号",width:'10%',align:'center',sortable: false,
            	formatter:function(value,options,rData){
				  //订单种类
				  var order_type = "";
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
				 // alert(order_type);
				  var htmls =  "<div class='iconBox'>";
				  //已下载
	  			 if(rData.isExport == 'Y'){
	  				 htmls += "<div class='right'><i class='u-icon-yxz'>已下载</i></div>";
	  			 }
	  			 //订单号和订单类型
	  				htmls +="<div><a class=\"orderDetail-btn\" herf='#' id=\""+rData.summaryId+"\">"+value+"</a><input type='hidden' value='"+value+"' id='purchaseOrderCode_"+rData.id+"'></div>" +
	  						"<ul class='uicon'><li><i class='u-icon-"+order_type+"'>"+order_name+"</i></li>";
				//紧急  
	  			if(rData.urgentFlag == 1){
					  htmls += "<li><i class='u-icon-j'>急</i></li>";
				  }
				  //新品
				  if(rData.isNew == "Y"){
					  htmls += "<li><i class='u-icon-x'>新</i></li>";
				  }
				  //直供
				  if(rData.sendGoodsType == 2){
					  htmls += "<li><i class='u-icon-zg'>直供</i></li>";
				  }
	        	  htmls += "</ul></div>";
				  return htmls;
			  }},
            {name:"remark", index:"订单备注",width:'10%',align:'center',hidden:true,sortable: false},
            {name:"isbn", index:"商品信息",width:'16%',align:'center',sortable: false,
            	formatter:function(value,options,rData){
            		var html="<div class='iconBox'>";
            		//重点
            		html +="<div class='left15'><ul>";
            		if(rData.isImportent=='Y'){
            			html+="<li><i class='u-icon-zd'>重点</i></li>";
            		}
            		//组套
            		if(rData.isSuitBook==2){
            			html+="<li><i class='u-icon-zt'>组套</i></li>";
            		}
            		html +="</ul></div>";
            		//书名部分
            		html+="<div class='right85'><ul class='newline'><li>"+rData.bookTitle+"</li>"
            		if(rData.seriesTitle!=null && rData.seriesTitle!=""){
            			html+="<li class='fontfui'>丛书名："+rData.seriesTitle+"</li>" 
            		}else{
            			html+="<li class='fontfui'>丛书名：</li>" 
            		}
            		html+=	"<li >ISBN："+value+"</li></ul></div></div>";
            		html +="</div>"
            	    return html;
            	}},
            {name:"price", index:"定 价",width:'5%',align:'center',sortable: false,
            		formatter:function(value,options,rData){
            			var html="";
            			var price = Number(value).toFixed(2);
            			if(rData.historyAvailablePrice!=null && rData.historyAvailablePrice!=""){//已回告
            				if(value!=rData.historyAvailablePrice){
                				html+="<div class='fontdel'>订单定价:"+price+"</div><div>"
                				html+=Number(rData.historyAvailablePrice).toFixed(2);
                				html+="<input type='hidden' id='price_"+rData.id+"' value='"+Number(rData.historyAvailablePrice).toFixed(2)+"' last-price='"+Number(rData.historyAvailablePrice).toFixed(2)+"' ></div>";
                			}else{
                				html+=price+"<input type='hidden' id='price_"+rData.id+"' value='"+Number(price).toFixed(2)+"' last-price='"+Number(price).toFixed(2)+"' >";
                			}
            			}else{
            				if(rData.availablePrice!=null && rData.availablePrice!=""){
            					html+="<div class='JGtab'><input type='text' id='price_"+rData.id+"' value='"+Number(rData.availablePrice).toFixed(2)+"' class='b80' last-price='"+Number(rData.availablePrice).toFixed(2)+"' ></div>";
            				}else{
            					html+="<div class='JGtab'><input type='text' id='price_"+rData.id+"' value='"+Number(price).toFixed(2)+"' class='b80' last-price='"+Number(price).toFixed(2)+"' ></div>";
            				}
            			}
                	    return html;
                	}},
            {name:"discountrate", index:"折扣（%）",width:'4%',align:'center',sortable: false,
                		formatter:function(value,options,rData){
                			var html="<div class='JGtab'>";
                			if(rData.historyAvailableDiscountrate!=null && rData.historyAvailableDiscountrate!=""){//已回告
                				if(value!=rData.historyAvailableDiscountrate){
                					if(rData.availableDiscountrate!=null && rData.availableDiscountrate!=""){//暂存不为空，显示暂存折扣，否则显示历史回告折扣
                						html+="<div class='fontdel'>订单折扣："+Number(value).toFixed(2)+"<div>";
                						html+="<div class='JGtab'><input type='text'    id='discountrate_"+rData.id+"'    value='"+Number(rData.availableDiscountrate).toFixed(2)+"' class='b80' last-discountrate='"+Number(rData.availableDiscountrate).toFixed(2)+"'></div>"
                						html+="</div>";
                					}else{
                						html+="<div class='fontdel'>订单折扣："+Number(value).toFixed(2)+"<div>";
                						html+="<div class='JGtab'><input type='text'    id='discountrate_"+rData.id+"'    value='"+Number(rData.historyAvailableDiscountrate).toFixed(2)+"' class='b80' last-discountrate='"+Number(rData.historyAvailableDiscountrate).toFixed(2)+"'></div>"
                						html+="</div>";
                					}
                    			}else{
                    				if(rData.availableDiscountrate!=null && rData.availableDiscountrate!=""){//暂存不为空，显示暂存折扣，否则显示订单折扣
                    					html+="<input type='text'  id='discountrate_"+rData.id+"'   value='"+Number(rData.availableDiscountrate).toFixed(2)+"' class='b80' last-discountrate='"+Number(rData.availableDiscountrate).toFixed(2)+"'></div>";
                    				}else{
                    					html+="<input type='text'  id='discountrate_"+rData.id+"'   value='"+Number(rData.historyAvailableDiscountrate).toFixed(2)+"' class='b80' last-discountrate='"+Number(rData.historyAvailableDiscountrate).toFixed(2)+"'></div>";
                    				}
                    			}
                			}else{
                				if(rData.availableDiscountrate!=null && rData.availableDiscountrate!=""){
                					html+="<input type='text'  id='discountrate_"+rData.id+"'   value='"+Number(rData.availableDiscountrate).toFixed(2)+"' class='b80' last-discountrate='"+Number(rData.availableDiscountrate).toFixed(2)+"'></div>";
                				}else{
                    				html+="<input type='text' id='discountrate_"+rData.id+"'  value='"+Number(value).toFixed(2)+"' class='b80' last-discountrate='"+Number(value).toFixed(2)+"'></div>";
                				}
                			}
                    	    return html;
                    	}},
            {name:"orderQty", index:"订 数",width:'4%',align:'center',sortable: false},
            {name:"sendQtyTemp", index:"发货数暂存",width:'6%',align:'center',hidden:true,
            	formatter:function(value,options,rData){
            		if(rData.isDeal=='N'){
            			var sendQty = rData.orderQty - rData.sendoutQty;
            			if(rData.thisSendQty!=null && rData.thisSendQty!=""){
            				sendQty = rData.thisSendQty;
            			}
            			return "<div class='JGtab'><input type='text' value='"+sendQty+"' class='b80' id='sendQtyTemp_"+rData.id+"'></div>";
            		}else{
            			return "";
            		}
            	}},
            {name:"thisSendQty", index:"本次发货数",width:'6%',align:'center',sortable: false,
            	formatter:function(value,options,rData){
            		if(rData.isDeal=='N'){
            			var sendQty = rData.orderQty - rData.sendoutQty;
            			if(rData.thisSendQty!=null && rData.thisSendQty!=""){
            				sendQty = rData.thisSendQty;
            			}else if((rData.availablePrice==null || rData.availablePrice=="")&&rData.availableReason>1){//判断是否有暂存,没有暂存，并且其余满足情况为永久缺货，本次发货数默认为0
            				sendQty = 0;
            			}
            			return "<div class='JGtab'><input type='text' value='"+sendQty+"' class='b80' id='sendQty_"+rData.id+"'></div>";
            		}else{
            			return "";
            		}
            	}},
        	 {name:"reasonValue", index:"其余满足情况",width:'8%',align:'center',hidden:true,
            	formatter:function(value,options,rData){
            		var reasonValue = rData.availableReason == null ?'':rData.availableReason;
            		var html ="<input type='text' value='"+reasonValue+"' class='b80' id='reasonValue_"+rData.id+"'>";
            		return html;
            	}
            },
            {name:"availableReason", index:"其余满足情况",width:'6%',align:'center',sortable: false,
                	formatter:function(value,options,rData){
                		var html="";
                		if(rData.isDeal=='N'){
                			if(rData.historyOtherAvailableReason != null && rData.historyOtherAvailableReason != ""){
                				html += "<div>"+getOtherAvailableReasonStr(rData.historyOtherAvailableReason)+"</div>";
                			}
                			html+="<select selectVal="+value+" data-id='"+rData.id +"' id='availableReason_"+rData.id+"' style='width:80%'  name='bmz'   ><option value=''></option></select>";
                			return html;
                		}else{
                			return "";
                		}
                		
                	}},
            {name:"preSendDate", index:"其余预计发货日期",width:'8%',align:'center',sortable: false,
                    	formatter:function(value,options,rData){
                    		var html="";
                    		if(rData.isDeal=='N'){
                    			if(rData.historyPreSendDate!=null && rData.historyPreSendDate !=''){
                    				html += "<div>"+rData.historyPreSendDate.substr(0,10)+"</div>";
                    			}
                    			value = value==null ? '':value.substr(0,10);
                    			html +="<div class='JGtab'><input type='text' value='"+value+"' class='b90' id='preSendDate_"+rData.id+"'></div>";
                    			return html;
                    		}else{
                    			return "";
                    		}
                    	}},
            {name:"responseRemark", index:"备 注",width:'10%',align:'center',sortable: false,
                        	formatter:function(value,options,rData){
                        		var html="";
                        		if(rData.isDeal=='N'){
                        			if(rData.historyResponseRemark!=null && rData.historyResponseRemark !=''){
                        				html +="<div>"+rData.historyResponseRemark+"</div>"
                            		}
                        			value = value==null ? '':value;
                        			html +="<div class='JGtab'><input type='text' value='"+value+"'  class='b80' id='responseRemark_"+rData.id+"'></div>";
                        			return html;
                        		}else{
                        			return "";
                        		}
                        	}},
            {name:"sendoutQty", index:"已发数",width:'4%',align:'center',hidden:true,sortable: false},
            {name:"receiveQty", index:"已收数",width:'4%',align:'center',hidden:true,sortable: false}
        ],// the column discription
        rowNum:100,
        rowList:[100,200,500],
        multiselect : true,  //显示checkbox选择框
        rownumbers: true,    //显示左边排名列表
        viewrecords: true, 
        pager: '#page',
        height:'100%',
        sortable:false,
        jsonReader: { 
      	  root: "rows",
      	  page: "page",
      	  total: "total",
      	  records: "records",
      	  repeatitems : false
        }, //设置数据方式
        postData: {query_id:"findProPurOrderItemsByPage",query_type:"JQGRID",rdParseType:"dispersed",reqData:queryData()},
        loadBeforeSend : function() {
        	//先初始化
			Initialization();
			
			
			$(this).jqGrid('clearGridData');
		},
        onSelectRow:function (id,status){
//			var rowData= $("#table").jqGrid("getRowData",id);
			//选中
			if(status){
				totalVariety = totalVariety+1;
				sendQty = $(this).find("#sendQty_"+id).val();
				if(sendQty!=""&& sendQty!=null){
					price = $(this).find("#price_"+id).val();
					discountrate = $(this).find("#discountrate_"+id).val();
					totalSendQty += parseInt(sendQty);
					totalSendPrice += parseInt(sendQty) * Number(price);
					totalSendRealPrc = totalSendRealPrc*100 + parseInt(sendQty) * Number(price) * Number(discountrate);
				}
			}else{//取消
				totalVariety = totalVariety - 1;
				if(totalVariety==0){
					Initialization();
				}
				sendQty = $(this).find("#sendQty_"+id).val();
				if(sendQty!="" && sendQty!=null && totalSendQty>0){
					price = $(this).find("#price_"+id).val();
					discountrate = $(this).find("#discountrate_"+id).val();
					totalSendQty -= parseInt(sendQty);
					totalSendPrice -= parseInt(sendQty) * Number(price);
					totalSendRealPrc = totalSendRealPrc*100 - parseInt(sendQty) * Number(price) * Number(discountrate) ;
				}
			}
			//设置
			InitTotal();
		},
		onSelectAll: function(ids, status) {
			var $this = $("#table");
			if (status) {
				//先初始化
				Initialization();
	    		$.each(ids, function(i, n){
//	    			var rowData= $("#table").jqGrid("getRowData",n);
	    			totalVariety = totalVariety+1;
	    			sendQty = $this.find("#sendQty_"+n).val();
					if(sendQty != "" && sendQty != null && sendQty != undefined){
						price = $this.find("#price_"+n).val();
						discountrate = $this.find("#discountrate_"+n).val();
						totalSendQty += parseInt(sendQty);
						totalSendPrice += parseInt(sendQty) * Number(price);
						totalSendRealPrc = totalSendRealPrc + parseInt(sendQty) * Number(price)*Number(discountrate);
					}
	    		});
	    		//设置
	    		InitTotal();
	    	}else{
	    		Initialization();
	    	}
		},
		loadComplete:function(){
			/**
			 * 展开列表-待处理品种
			 */
			$("#odiv").SetJqGridList({
		        jqID:'table',
		        width:'90',
		        text:'字段设置',
		        jqGridBox:'GdBox',
		        storageID:'orderItemsProcessingOne',
		        data:[
		            {
		                id:'addDate',
		                name:"制单日期"
		            },
		            {
		                id:'remark',
		                name:"订单备注",
		                show:true
		            },
		            {
		                id:'sendoutQty',
		                name:"已发数"
		            },
		            {
		                id:'receiveQty',
		                name:"已收数"
		            }
		        ]
		    });
			$("select[name='bmz']").each(function(){
				   var selectVal = $(this).attr("selectVal");
				   	   selectVal = (selectVal == null || selectVal == '')?1:selectVal;
				  $(this).select({
					  	localdata:bmz_yz,
	 					selected: selectVal //默认选中的值
	 				});
			   });
			
			$(".orderDetail-btn").click(function(){
				saveSearch();
			   var id =  $(this).attr("id");
			   window.location.href=appPath+"/backto/order/page/toOrderDetailsProcessing?id="+id+"&isDeal=N&type=item";
		    });
            WG.JGscrollX.Event($(this),'GdBox');
            //获取ids
            var arr=$(this).jqGrid('getDataIDs'); 
            for(var j=0;j<arr.length;j++){
            	//初始化状态
            	var id= arr[j];
            	setAmount(id);
            	//定价改变事件
            	$('#price_'+arr[j]+'').blur(function(){
            		var id = $(this).attr("id").substring($(this).attr("id").indexOf("_")+1);
            		var price=$(this).val();
            		var reg = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
            		if($("#table").find("#"+id).attr("aria-selected")=="true" && reg.test(price)){
	            		var id = $(this).attr("id").substring($(this).attr("id").indexOf("_")+1);
	            		var amount = $("#table").find("#sendQty_"+id).val();
	            		var totalPrice = price - $(this).attr("last-price");
	            		discountrate =$("#table").find("#discountrate_"+id).val();
						totalSendPrice += parseInt(amount) * Number(totalPrice);
						totalSendRealPrc = totalSendRealPrc*100 + parseInt(amount) * Number(totalPrice) * Number(discountrate) ;
						//设置
						InitTotal();
            		}
            		if(reg.test(price)){
            			$(this).attr("last-price",price);
            		}else{
            			$(this).val($(this).attr("last-price"));
            		}
            	});
            	$('#discountrate_'+arr[j]+'').blur(function(){
            		var id = $(this).attr("id").substring($(this).attr("id").indexOf("_")+1);
            		var discountrate=$(this).val();
            		var reg = /^(\d{1,2}(\.\d{1,3})?|100)$/;
            		if($("#table").find("#"+id).attr("aria-selected")=="true" && reg.test(discountrate)){
	            		var id = $(this).attr("id").substring($(this).attr("id").indexOf("_")+1);
	            		var amount = $("#table").find("#sendQty_"+id).val();
	            		var totaldiscountrate = discountrate - $(this).attr("last-discountrate");
	            		price =$("#table").find("#price_"+id).val();
						totalSendRealPrc = totalSendRealPrc*100 + parseInt(amount) * Number(price) * Number(totaldiscountrate) ;
						//设置
						InitTotal();
            		}
            		if(reg.test(discountrate)){
            			$(this).attr("last-discountrate",discountrate);
            		}else{
            			$(this).val($(this).attr("last-discountrate"));
            		}
            	});
            	// 订数改变事件
            	$('#sendQty_'+arr[j]+'').keyup(function(){
            		var id = $(this).attr("id").substring($(this).attr("id").indexOf("_")+1);
            		var amount=$(this).val();
            		var sendQtyTemp = $("#table").find("#sendQtyTemp_"+id).val();
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
					//判断发货数与订数-已发数关系
					var rowData = $("#table").jqGrid("getRowData",id);
					var unsendoutQty = parseInt(rowData.orderQty)- parseInt(rowData.sendoutQty);
					var maxUnsendoutQty = parseInt(rowData.orderQty) * 1.3- parseInt(rowData.sendoutQty);
					//本次发货数>=订数-已发数，“其余满足情况”置为空，不可编辑
					if(amount>maxUnsendoutQty){
//						layer.msg("本次发货数不能超过未发数的130%!",{icon : 0,time:1800});
						setState(id,true,1);
						$("#table").find("#availableReason_"+id).attr("value", '');
						$("#table").find("#availableReason_"+id).attr("disabled", true);
//						$(this).val(sendQtyTemp);
						return;
					}
					if(amount>=unsendoutQty){
						$("#table").find("#availableReason_"+id).attr("value", '');
						$("#table").find("#availableReason_"+id).attr("disabled", true);
					}else if(0<amount<unsendoutQty){//0<=本次发货数<订数-已发数，自动默认“其余满足情况=暂时缺货”，可修改
						$("#table").find("#availableReason_"+id).attr("disabled", false);
						$("#table").find("#availableReason_"+id).attr("value", 1);
					}
					
					//本次发货数改变关联事件
					if($("#table").find("#"+id).attr("aria-selected")=="true"){//勾选需要改变合计
						var totalAmount = amount-sendQtyTemp;
						price = $("#table").find("#price_"+id).val();
						discountrate =$("#table").find("#discountrate_"+id).val();
						totalSendQty += parseInt(totalAmount);
						totalSendPrice += parseInt(totalAmount) * Number(price);
						totalSendRealPrc = totalSendRealPrc*100 + parseInt(totalAmount) * Number(price) * Number(discountrate) ;
//						console.log(totalSendRealPrc);
						//设置
						InitTotal();
					}
					//重新设置暂存发货数
					$("#table").find("#sendQtyTemp_"+id).val(amount);
					//若存在背景色，则重置并重置保存和确认按钮状态
					setState(id,false,1);
					
            	});
            	
            	//其余满足情况改变事件
            	$('#availableReason_'+arr[j]+'').change(function(){
            		var id = $(this).attr("id").substring($(this).attr("id").indexOf("_")+1);
            		var optionVal = $(this).val();
            		if(optionVal!=null && optionVal!=""){
            			//去掉背景色和禁用的保存、操作按钮
            			setState(id,false,1);
            			//其余满足情况=预计可发：其余预计发货日期必填，否则标红该行，不允许保存；
            			if(parseInt(optionVal)==0){
            				var preSendDate =$("#table").find("#preSendDate_"+id).val();
            				if(preSendDate=="" || preSendDate==null){
            					setState(id,true,1);
            				}
            			}else if(parseInt(optionVal)>=1){
            				var amount = $("#table").find("#sendQty_"+id).val();
                			var rowData = $("#table").jqGrid("getRowData",id);
        					var unsendoutQty = parseInt(rowData.orderQty)- parseInt(rowData.sendoutQty);
            				if(amount>=unsendoutQty){
            					setState(id,true,1);
            				}
            			}
            		}else{
            			//0<=本次发货数<订数-已发数，其余满足情况不能为空
            			var amount = $("#table").find("#sendQty_"+id).val();
            			var rowData = $("#table").jqGrid("getRowData",id);
    					var unsendoutQty = parseInt(rowData.orderQty)- parseInt(rowData.sendoutQty);
    					if(amount!=''&&amount>=0&&amount<unsendoutQty){
    						setState(id,true,1);
    					}else{
                			setState(id,false,1);
    					}
            		}
            		
            	});
            	
            	$('#preSendDate_'+arr[j]+'').click(function(){
            		var id = $(this).attr("id").substring($(this).attr("id").indexOf("_")+1);
            		var preSendDate = $(this).val();
            		//日期控件添加监听事件
            		WdatePicker(
            				{onpicking:function(dp){//确定
                        	    var availableReason = $("#table").find("#availableReason_"+id).val();
            					if(parseInt(availableReason)==0){
            						setState(id,false,1);
            					}
            						
            					},
            				onclearing:function(){//清空
            					var availableReason = $("#table").find("#availableReason_"+id).val();
            					if(parseInt(availableReason)==0){
            						setState(id,true,1);
            					}
            						
            					}
            				}
            		);
            	});
            }
        },
    });
//    jQuery("#table").jqGrid('navGrid','#page',{edit:false,add:false,del:false});



    $('#table_a').jqGridScroll();//声明表头悬浮
    $("#table_a").jqGrid({
    	url: appPath+'/console/query',
		mtype:'post',
		autowidth : true, // 自适应宽度
		shrinkToFit : true, // 列自适应
		datatype : datatype,
        colNames:["id","发送日期","制单日期","订单号","订单备注","商品信息","订单定价","订单折扣","订 数","订单码洋","已发数","发货码洋","已收数","发货率","品种处理情况","回告历史"],// the column header names
        colModel :[
            {name:"id", index:"品种主键id",width:'6%',align:'center',hidden:true},
            {name:"sendDate", index:"发送日期",width:'6%',align:'center',sortable: false,
            	formatter:"date",
            	formatoptions:{srcformat: 'Y-m-d', newformat: 'Y-m-d'}
            },
            {name:"addDate", index:"制单日期",width:'6%',align:'center',sortable: false,hidden:true,
            	formatter:"date",
            	formatoptions:{srcformat: 'Y-m-d', newformat: 'Y-m-d'}
            },
            {name:"purchaseOrderCode", index:"订单号",width:'10%',align:'center',sortable: false,
            	formatter:function(value,options,rData){
  				  //订单种类
  				  var order_type = "";
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
  				 // alert(order_type);
  				  var htmls =  "<div class='iconBox'>";
  				  //已下载
  	  			 if(rData.isExport == 'Y'){
  	  				 htmls += "<div class='right'><i class='u-icon-yxz'>已下载</i></div>";
  	  			 }
  	  			 //订单号和订单类型
  	  				htmls +="<div><a class=\"orderDetailTwo-btn\" herf='#' id=\""+rData.summaryId+"\">"+value+"</a></div>" +
  	  						"<ul class='uicon'><li><i class='u-icon-"+order_type+"'>"+order_name+"</i></li>";
  				//紧急  
  	  			if(rData.urgentFlag == 1){
  					  htmls += "<li><i class='u-icon-j'>急</i></li>";
  				  }
  				  //新品
  				  if(rData.isNew == "Y"){
  					  htmls += "<li><i class='u-icon-x'>新</i></li>";
  				  }
  				  //直供
  				  if(rData.sendGoodsType == 2){
  					  htmls += "<li><i class='u-icon-zg'>直供</i></li>";
  				  }
  	        	  htmls += "</ul></div>";
  				  return htmls;
  			  }	
            },
            {name:"remark", index:"订单备注",width:'10%',align:'center',sortable: false,hidden:true},
            {name:"isbn", index:"商品信息",width:'18%',align:'center',sortable: false,
            	formatter:function(value,options,rData){
            		var html="<div class='iconBox'>";
            		//已导出
            		/*if(rData.isExport=='Y'){
            			html +="<div class='right'><i class='u-icon-yxz'></i></div>";
            		}*/
            		//重点
            		html +="<div class='left15'><ul>";
            		if(rData.isImportent=='Y'){
            			html+="<li><i class='u-icon-zd'>重点</i></li>";
            		}
            		//组套
            		if(rData.isSuitBook==2){
            			html+="<li><i class='u-icon-zt'>组套</i></li>";
            		}
            		html +="</ul></div>";
            		//书名部分
            		html+="<div class='right85'><ul class='newline'><li style='width:90%;'>"+rData.bookTitle+"</li>"
            		if(rData.seriesTitle!=null && rData.seriesTitle!=""){
            			html+="<li style='width:90%;' class='fontfui'>丛书名："+rData.seriesTitle+"</li>" 
            		}else{
            			html+="<li style='width:90%;' class='fontfui'>丛书名：</li>" 
            		}
            		html+=	"<li style='width:90%;' >ISBN："+value+"</li></ul></div></div>";
            		html +="</div>"
            	    return html;
            	}	
            },
            {name:"price", index:"定 价",width:'4%',align:'center',sortable: false,
            	formatter:function(value,options,rData){
        			var html="";
        				if(rData.availablePrice!=null && value!=rData.availablePrice){
            				html+="<div class='fontdel'>订单定价："+Number(value).toFixed(2)+"</div><div>";
            				html+=Number(rData.availablePrice).toFixed(2);
            				html+="</div>";
            			}else{
            				html+=Number(value).toFixed(2);
            			}
            	    return html;
            	}
            },
            {name:"discountrate", index:"订单折扣（%）",width:'6%',align:'center',sortable: false,
            	formatter:function(value,options,rData){
        			var html="";
        				if(rData.availableDiscountrate!=null && value!=rData.availableDiscountrate){
            				html+="<div class='fontdel'>订单折扣："+Number(value).toFixed(2)+"</div><div>";
            				html+=Number(rData.availableDiscountrate).toFixed(2);
            				html+="</div>";
            			}else{
            				html+=Number(value).toFixed(2);
            			}
            	    return html;
            	}
            },
            {name:"orderQty", index:"订 数",width:'4%',align:'center',sortable: false},
            {name:"name_a9", index:"订单码洋",width:'5%',align:'center',sortable: false,
            	formatter:function(value,options,rData){
        			return Number(rData.orderQty*rData.price).toFixed(2);
            	}
            },
            {name:"sendoutQty", index:"已发数",width:'4%',align:'center',sortable: false,
            	formatter:function(value,options,rData){
            		if( value ==null || value ==""){
            			return "0";
            		}else{
            			return value;
            		}
            	}
            },
            {name:"name_a11", index:"发货码洋",width:'5%',align:'center',sortable: false,
            	formatter:function(value,options,rData){
        			return Number(rData.sendoutQty*rData.availablePrice).toFixed(2);
            	}		
            },
            {name:"receiveQty", index:"已收数",width:'4%',align:'center',sortable: false,
            	formatter:function(value,options,rData){
            		if( value ==null || value ==""){
            			return "0";
            		}else{
            			return value;
            		}
            	}	
            },
            {name:"name_a13", index:"发货率",width:'4%',align:'center',sortable: false,
            	formatter:function(value,options,rData){
        			return (Number((rData.sendoutQty/rData.orderQty)*100)).toFixed(2) +"%";
            	}		
            },
            {name:"isValid", index:"品种处理情况",width:'6%',align:'center',sortable: false,hidden:true,
            	formatter:function(value,options,rData){
            		if(value =='N'){
            			return "已处理<br/>（采购关闭）";
            		}else if(value =='X'){
            			return "已处理<br/>（供商关闭）";
            		}else{
            			return "已处理";
            		}
            	}
            },
            {name:"status", index:"回告历史",width:'5%',align:'center',sortable: false,
            	formatter:function(value,options,rData){
            		if(value ==null){
            			return "";
            		}else{
            			return "<a item-id='"+rData.id+"' href='#myModal2' data-toggle='modal' class=\"showBackToHistory\">查 看</a>";
            		}
            	}
            }
        ],// the column discription
        rowNum:20,
        rowList:[20,50,100],
        multiselect : true,  //显示checkbox选择框
        rownumbers: true,    //显示左边排名列表
        viewrecords: true, 
        pager: '#page_a',
        height:'100%',
        sortable:false,
        jsonReader: { 
      	  root: "rows",
      	  page: "page",
      	  total: "total",
      	  records: "records",
      	  repeatitems : false
        }, //设置数据方式
        postData: {query_id:"findProPurOrderItemsTwoByPage",query_type:"JQGRID",rdParseType:"dispersed",reqData:queryDataTwo()},
        loadBeforeSend : function() {
			$(this).jqGrid('clearGridData');
//			WG.loading.show();
		},
        loadComplete:function(){
        	/**
        	 * 展开列表-已处理品种
        	 */
        	$("#odiv2").SetJqGridList({
                jqID:'table_a',
                width:'90',
                text:'字段设置',
                jqGridBox:'GdBox',
                storageID:'orderItemsProcessingTwo',
                data:[
                    {
                        id:'addDate',
                        name:"制单日期"
                    },
                    {
                        id:'remark',
                        name:"订单备注",
		                show:true
                    },
                    {
                        id:'isValid',
                        name:"品种处理情况"
                    }
                ]
            });
        	$(".orderDetailTwo-btn").click(function(){
        		saveSearch();
			   var id =  $(this).attr("id");
			   window.location.href=appPath+"/backto/order/page/toOrderDetailsProcessing?id="+id+"&isDeal=Y&type=item";
		    });
        	tbPage = $('#table_a').getGridParam('page');//获取当前页码
        	$(this).setGridWidth($(document).width()-1);
            setTimeout(function(){
                $('#page_a_left').width($('#page_a').width()/2.5);
            },500);
            WG.JGscrollX.Event($(this),'GdBox');
            
            $(".showBackToHistory").click(function(){
				var id=$(this).attr("item-id");
				var re=queryDataHistory(id);
				jQuery("#table_b").jqGrid('setGridParam', {
					datatype : 'json',
					page : 1,
					postData : {
						query_id : "queryResponseHistory",
						query_type : "JQGRID",
						reqData : re
					}
				}).trigger("reloadGrid");
			});
            
            //当加载完数据，更新从待办进入查看采购已关闭品种为已查看
            var isValidRe = $("#isValidRe").val();
            if(isValidRe =='N'){
            	//调用更新为已查看方法
            	var selectedIds = $(this).jqGrid("getDataIDs");
            	if(selectedIds !=null && selectedIds != ""){//有采购商关闭的品种就执行更新
            		updateOrderItemViewed();
            	}
            }
        }
    });
//    jQuery("#table_a").jqGrid('navGrid','#page_a',{edit:false,add:false,del:false});

    $("#table_b").jqGrid({
    	url: appPath+'/console/query',
		mtype:'post',
		autowidth : true, // 自适应宽度
		shrinkToFit : true, // 列自适应
		datatype : "json",
		 colNames:[/*"序 号",*/"回告日期","发货数","其余满足情况","其余预计发货日期","定 价","折扣（%）","发货码洋","发货实洋","发货单号","备 注"],// the column header names
	        colModel :[
//	            {name:"name_b1", index:"序 号",width:'6%',align:'center'},
	            {name:"responseDate", index:"回告日期",width:'10%',align:'center',
	            	formatter:"date", formatoptions: {newformat:'Y-m-d'}},
	            {name:"thisSendQty", index:"发货数",width:'6%',align:'center',
	            	formatter:function(value,options,rData){
	            		if(value==""||value==null)
	            			return 0;
	            		else
	            			return value;
	            	}
	            },
	            {name:"otherAvailableReason", index:"其余满足情况",width:'10%',align:'center',
	            	formatter:function(value,options,rData){
	            		if(value != null && value != ""){
	            			value=getOtherAvailableReasonStr(value);
         			}else{
         				value='';
         			}
         			return value;
	            	}
	            },
	            {name:"preSendDate", index:"其余预计发货日期",width:'13%',align:'center',
	            	formatter:"date", formatoptions: {newformat:'Y-m-d'}},
	            {name:"availablePrice", index:"定 价",width:'6%',align:'center',formatter:'number'},
	            {name:"availableDiscountrate", index:"折扣（%）",width:'8%',align:'center',formatter:'number'},
	            {name:"responseMY", index:"发货码洋",width:'8%',align:'center',
	            	formatter:function(value,options,rData){
	            		if(rData.thisSendQty==""||rData.thisSendQty==null)
	            			rData.thisSendQty = 0;
	            		var MY =parseInt(rData.thisSendQty) *  Number(rData.availablePrice); 
	            		MY = MY.toFixed(2);
	            		return MY;
	            	}
	            },
	            {name:"responseSY", index:"发货实洋",width:'8%',align:'center',
	            	formatter:function(value,options,rData){
	            		if(rData.thisSendQty==""||rData.thisSendQty==null)
	            			rData.thisSendQty = 0;
	            		var SY =parseInt(rData.thisSendQty) *  Number(rData.availablePrice) * Number(rData.availableDiscountrate)/100;
	            		SY = SY.toFixed(2);
	            		return SY;
	            	}
	            },
	            {name:"sendoutGoodsCode", index:"发货单号",width:'13%',align:'center'},	
	            {name:"responseRemark", index:"备 注",width:'13',align:'center'}
        ],
        postData: {query_id:"queryResponseHistory",query_type:"JQGRID",rdParseType:"dispersed",reqData:queryDataHistory()},
        rowNum:10,
        rowList:[10,20,30],
        rownumbers: true,    //显示左边排名列表
        pager: '#page_b',
        width: 817,
        height:'100%',
        jsonReader: { 
	    	  root: "rows",
	    	  page: "page",
	    	  total: "total",
	    	  records: "records",
	    	  repeatitems : false
	      },
        loadComplete:function(){
        	$(this).setGridWidth(817);
        }
    });
    jQuery("#table_b").jqGrid('navGrid','#page_b',{edit:false,add:false,del:false});
    
	
	function updateOrderItemViewed(){
//		alert(userName);
		var purchaserId = $("#pur_allTwo").val();
		$.ajax({ 
			type: "post",
			url: appPath+"/backto/task/json/updateOrderItemViewed", 
			data: {"purchaserId" : purchaserId,"userCode":userName},
			dataType: "json", 
			success: function(json){
				
			},error : function() {
				layer.msg("更新采购商关闭是否查看异常!",{icon : 0,time:2500});
			}
		});
	}
	
	function setAmount(id){
		var amount=$("#table").find("#sendQty_"+id).val(),
		sendQtyTemp = $("#table").find("#sendQtyTemp_"+id).val();
		//判断发货数与订数-已发数关系
		var rowData = $("#table").jqGrid("getRowData",id);
		var unsendoutQty = parseInt(rowData.orderQty)- parseInt(rowData.sendoutQty);
		var reasonValue = $("#table").find("#reasonValue_"+id).attr("value");
		if(reasonValue!=null && reasonValue!=''){
			$("#table").find("#availableReason_"+id).attr("value", reasonValue);
			$("#table").find("#availableReason_"+id).attr("disabled", false);
		}else if(amount>=unsendoutQty){
			$("#table").find("#availableReason_"+id).attr("value", '');
			$("#table").find("#availableReason_"+id).attr("disabled", true);
		}else if(0<amount<unsendoutQty){//0<=本次发货数<订数-已发数，自动默认“其余满足情况=暂时缺货”，可修改
			$("#table").find("#availableReason_"+id).attr("disabled", false);
			$("#table").find("#availableReason_"+id).attr("value", 1);
		}
	}
	
	   /**
	    * 1/背景和按钮状态设置
	    * 2/设置行项可编辑状态
	    */
	    function setState(id,flag,type){
	    	if(type==1){
	    		if(flag){
	        		$("#table").find("#"+id+ " td").css("background-color","#ffdcdc");
	        	}else{
	        		$("#table").find("#"+id+ " td").css("background-color","");
	        	}
	    		/*$("#sendGoods").attr("disabled",flag);
	    		$("#save").attr("disabled",flag);*/
	    	}else if(type==2){
	    		$("#table").find("#price_"+id).attr("disabled", flag);
				$("#table").find("#discountrate_"+id).attr("disabled", flag);
				$("#table").find("#sendQty_"+id).attr("disabled", flag);
				$("#table").find("#preSendDate_"+id).attr("disabled", flag);
				$("#table").find("#responseRemark_"+id).attr("disabled", flag);
	    	}
	    }
	  /**
     * 合计初始化
     */
    function Initialization(){
    	totalVariety = 0;
		totalSendQty = 0;
		totalSendPrice =0.00;
		totalSendRealPrc = 0.00;
		//设置
		$("#totalVariety").text(totalVariety);
		$("#totalSendQty").text(totalSendQty);
		$("#totalSendPrice").text(totalSendPrice);
		$("#totalSendRealPrc").text(totalSendRealPrc);
    }
    
    /**
     * 合计
     */
    function InitTotal(){
		//设置
    	$("#totalVariety").text(totalVariety);
		$("#totalSendQty").text(totalSendQty);
		$("#totalSendPrice").text(totalSendPrice.toFixed(2));
		totalSendRealPrc = totalSendRealPrc*0.01;
		$("#totalSendRealPrc").text(totalSendRealPrc.toFixed(2));
    }
	
    /**
     * 判断是否有错误记录勾选
     */
    function booleanDisabled(ids){
    	var isSendGoods = true;
    	/*if(ids.length==0){
    		isSendGoods = true;
    	}*/
    	//判断是否是同一仓位
    	var firstWareHouse = $("#table").find("#sendQty_"+ids[0]).val();
    	var isEqualWareHouse = true;
    	//若有勾选错误数据
    	for(var i=0;i<ids.length;i++){
    		var id = ids[i];
    		if(firstWareHouse!=$("#table").find("#sendQty_"+id).val()){
    			isEqualWareHouse=false;
    		}
    		if($("#table").find("#"+ids[i]).attr("aria-selected")=="true"){
    			var thisSendQty = $("#table").find("#sendQty_"+id).val(),
    			availableReason = $("#table").find("#availableReason_"+id).val(),
    			preSendDate = $("#table").find("#preSendDate_"+id).val();
    			
    			var rowData = $("#table").jqGrid("getRowData",id);
    			var orderQty = rowData.orderQty,
    			sendQty = rowData.sendoutQty;
    			
    			var maxthisSend = orderQty*1.3-sendQty,
    			unSend = orderQty-sendQty;
    			//判断订数方面
    			if(thisSendQty!=null && thisSendQty!=''){
    				if(thisSendQty>maxthisSend){
    					layer.msg("累计发货数不允许超过订数的130%!", {icon : 0});
        				$("#table").find("#sendQty_"+id).focus();
        				isSendGoods = false;
        				break;
    				}
    				
    				if(thisSendQty<unSend){
    					if(availableReason==null || availableReason==''){
    						layer.msg("满足数<订数，请填写其余满足情况!", {icon : 0});
            				$("#table").find("#availableReason_"+id).focus();
            				isSendGoods = false;
            				break;
    					}else if(availableReason!=null && availableReason!=''
    								&& availableReason == '0'){
    						if(preSendDate==null || preSendDate==''){
    							layer.msg("其余满足情况为预计可发，请填写其余预计发货日期!", {icon : 0});
                				$("#table").find("#preSendDate_"+id).focus();
                				isSendGoods = false;
                				break;
    						}
    					}
    				}
    				if(thisSendQty>=unSend && availableReason!=null && availableReason!=''){
    					layer.msg("满足数大于等于订数，其余满足情况不能填写!", {icon : 0});
        				$("#table").find("#availableReason_"+id).focus();
        				isSendGoods = false;
        				break;
    				}
    			}
    		}else{
    			isSendGoods = true;
    		}
    	}
    	return isSendGoods;
    }
    
	//已处理品种-查询条件获取
	function queryDataTwo(){
		//userName='2200059PQ';
		var re="{'groupOp':'AND','rules':[";
		var queryOrdertypeQC = "{'field':'userCode','op':'eq','data':'"+userName+"'},";
		re = re.concat(queryOrdertypeQC);
		var purchaserId = $("#pur_allTwo").val();
		var dc = $("#dc_allTwo").val();
		var isExportArr =[]; 
		$('input[name="isExportTwo"]:checked').each(function(){ 
			isExportArr.push($(this).val()); 
		}); 
		var isExport="";
		if(isExportArr.length>0){
			if(isExportArr.length=1){
				isExport=isExportArr[0];
			}else{
				isExport=null;
			}
		}
		var urgentFlag = $("input[name='urgentFlagTwo']:checked").val();
//		var orderType = $("#orderTypeTwo").val();
		var orderType = $(".AllSelectTwo .selectData").val();
		var isbn=$("#isbnTwo").val();
		var isNew = $("input[name='isNewTwo']:checked").val();
		//订单号码
		var orderCode = $("#orderCodeTwo").val();
		//书名
		var booktitle=$("#booktitleTwo").val();
		var sendDateStart	 = $("#sendDateStartTwo").val();
    	var sendDateEnd	 = $("#sendDateEndTwo").val();
    	//var isValid=$("#isValidRe").val();
    	var isValids=$(".isValidTwo .selectData").val();
		//采购商
		if(null != purchaserId && '' != purchaserId){
    		var a = "{'field':'purchaserId','op':'eq','data':'"+ purchaserId +"'},";
    		re = re.concat(a);
    		//获取一个月以前的待处理品种
    		/*autoCountMonthAgo();*/
    	}else{
	    	//layer.msg("请选择采购商",{icon:0});
	    	return;
    	}
		//仓位
    	if(null != dc && '' != dc){
    		var a = "{'field':'warehouse','op':'eq','data':'"+ dc +"'},";
    		re = re.concat(a);
    	}
    	//下载标识
    	if(null != isExport && '' != isExport){
    		var a = "{'field':'isExport','op':'eq','data':'"+ isExport +"'},";
    		re = re.concat(a);
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
    	//isbn
    	 if(null != isbn && '' != isbn){
 			if(isbn.indexOf(",")!=-1){
 				var a = "{'field':'isbns','op':'eq','data':'"+ isbn +"'},";
     			re = re.concat(a);
	    		}else{
					var a = "{'field':'isbn','op':'eq','data':'"+ isbn +"'},";
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
     	
    	//商品名称
    	if(null != booktitle && '' != booktitle){
    		if(booktitle.indexOf(",")!=-1){
				var a = "{'field':'bookTitles','op':'eq','data':'"+ booktitle +"'},";
    			re = re.concat(a);
			}else{
				var a = "{'field':'bookTitle','op':'eq','data':'"+ booktitle +"'},";
    			re = re.concat(a);
			}
    	}
    	//新品
    	if(null != isNew && '' != isNew && undefined != isNew){
    		var isNew = "{'field':'isNew','op':'eq','data':'"+ isNew +"'},";
    		re = re.concat(isNew);
    	}
    	//发送日期-开始
    	if(null != sendDateStart && '' != sendDateStart){
    		var a = "{'field':'sendDateStart','op':'eq','data':'"+ sendDateStart +"'},";
    		re = re.concat(a);
    	}
    	//发送日期-结束
    	if(null != sendDateEnd && '' != sendDateEnd){
    		var a = "{'field':'sendDateEnd','op':'eq','data':'"+ sendDateEnd +"'},";
    		re = re.concat(a);
    	}
    	//品种处理情况
    	if(null != isValids && '' != isValids && undefined != isValids){
    		var a = "{'field':'isValids','op':'eq','data':'"+ isValids +"'},";
    		re = re.concat(a);
    	}
    	//紧急程度
    	if(null != urgentFlag && '' != urgentFlag && undefined != urgentFlag){
    		var a = "{'field':'urgentFlag','op':'eq','data':'"+ urgentFlag +"'},";
    		re = re.concat(a);
    	}
    	/*if(null != isValid && '' != isValid && undefined != isValid){
    		var a = "{'field':'isValid','op':'eq','data':'"+ isValid +"'},";
    		re = re.concat(a);
    	}*/
    	//获取排序方式
    	var sortFiled = getOderSortTwo();
    	re = re.concat(sortFiled);
    	//去掉最后一个，符号
    	if(re.length > 30){
			re = re.substr(0,re.length-1);
		}
    	var z= "]}";
    	re = re.concat(z);
    	return re;
	}
    
	//回告历史查询条件获取
	function queryDataHistory(id){
		if(id ==undefined){
			id=0;
		}
		var re="{'groupOp':'AND','rules':[";
		var queryOrdertypeQC = "{'field':'id','op':'eq','data':'"+id+"'}";
		re = re.concat(queryOrdertypeQC);
    	var z= "]}";
    	re = re.concat(z);
    	return re;
	}

    //总页面上的回告发货确认按钮
    $("#sendGoods").bind("click",function(){
    	var ids = new Array();
    	ids = $("#table").jqGrid("getGridParam","selarrrow");
    	if(ids.length==0){
    		layer.alert("请先勾选待处理品种，再进行【确认】操作!", {icon : 0});
    		return;
    	}else{
    		
			//判断是否是同一仓位、判断是否同时存在直供和非直供订单
    		var firstrowData = $("#table").jqGrid("getRowData",ids[0]);
			var firstWareHouse = firstrowData.warehouse;
			firstWareHouse = firstWareHouse=='null'?"":firstWareHouse;
			var firstsendGoodsType=firstrowData.sendGoodsType;
			var firstpurchaseOrderCode="";
			for(var i=0;i<ids.length;i++){
				var id = ids[i];
				var rowData = $("#table").jqGrid("getRowData",id);
				var warehouse = rowData.warehouse;//获取此次循环的仓位
				warehouse = warehouse=='null'?"":warehouse;
				var sendGoodsType = rowData.sendGoodsType;//获取此品种是否是直供订单
				var purchaseOrderCode = $("#table").find("#purchaseOrderCode_"+id).val();//获取订单号
				if(firstsendGoodsType!=sendGoodsType){
					layer.alert("直供订单，不能与其他订单一起发货！", {icon : 0});
					return;
				}else if(sendGoodsType == 2){//等于直供订单
					if(firstpurchaseOrderCode==""){
						firstpurchaseOrderCode=purchaseOrderCode;
					}else if(firstpurchaseOrderCode!=purchaseOrderCode){
						layer.alert("直供订单，不能与其他订单一起发货！", {icon : 0});
						return;
					}
				}
				if(firstWareHouse!=warehouse){
					layer.alert("订单仓位不一致，不能一起发货！", {icon : 0});
					return;
				}
			}
			
			var resultGp = 0;
			var resultDtd = 0;
			for(var i=0;i<ids.length;i++){
				var rowData = $("#table").jqGrid("getRowData",ids[i]);
				var orderType = rowData.orderType;
				var thisSendQty = $("#table").find("#sendQty_"+ids[i]).val();
				var orderQty = rowData.orderQty;
				var sendOutQty = rowData.sendoutQty;
				if(thisSendQty==null||thisSendQty==""){
					layer.alert("发货数不能为空！", {icon : 0});
					return;
				}
				/**
		    	 * 勾选品种中，任意品种的本次发货数>0， 
		    	*	a、存在品种所属订单的订单种类=馆配订单；
		    	*	系统检查该馆配订单的所有品种（已处理品种除外）：
				*	是否“本次发货数>=订数”；如果任一品种的“本次发货数<订数”，则需做出提示：
				*	馆配订单，有品种不能全部发货，是否继续？
				*	选择是，可以继续发货；
				*	选择否，取消；
		    	*/
				if(orderType==25 && thisSendQty>0 && Number(thisSendQty)<orderQty){
					resultGp = 1;
				}
				/**
		    	 * 勾选品种中，任意品种的本次发货数>0
		    	 * 存在品种所属订单的订单种类=大中专、团购、活动订书；
		    	 * 此时系统需要去检查该订单的所有【本次发货数】>0的品种（已处理品种、“本次发货数=0”品种除外）：
				 *	是否本次发货数>=订数；如果任一品种的“本次发货数<订数”，则做出提示：
				 *	大中专、团购、活动订书，有品种不能全部发货，是否继续？
				 *	选择是，可以继续发货；
				 *	选择否，取消；
		    	 */
				if((orderType==15 ||orderType==20 ||orderType==30) && thisSendQty>0 && Number(thisSendQty)<orderQty){
					resultDtd = 2;
				}
			}
			var msg;
			var msgGp = "馆配订单，有品种不能全部发货，是否继续?";
			var msgDtd = "大中专、团购、活动订书，有品种不能全部发货，是否继续?";
    		if(resultGp==1){
    			layer.confirm(msgGp, {
          			 btn: ['是','否'], //按钮
      	     		 icon:3,
      	     		 shade: false //不显示遮罩
      	  			}, function(index){
      	  				layer.close(index);
      	  				if(resultDtd==2){
	      	  				layer.confirm(msgDtd, {
	      	        			 btn: ['是','否'], //按钮
	      	    	     		 icon:3,
	      	    	     		 shade: false //不显示遮罩
	      	    	  			}, function(index){
	      	    	  				layer.close(index);
	      	    	  				saveResponse("0");
	      	    	  			});
      	  				}else{
      	  					saveResponse("0");
      	  				}
      	  			});
    		}else if(resultDtd==2){
    			layer.confirm(msgDtd, {
          			 btn: ['是','否'], //按钮
      	     		 icon:3,
      	     		 shade: false //不显示遮罩
      	  			}, function(index){
      	  				layer.close(index);
      	  				saveResponse("0");
      	  			});
    		}else{
    			saveResponse("0");
    		}
    		
    	}
    	
    	//sureSave();
    	
    });
 // 采购商改变事件，重新加载仓位和表单
	$("#pur_all").change(function(){
 		loadData($(this).val());
 	});
	 // 采购商改变事件，重新加载仓位和表单
	$("#pur_allTwo").change(function(){
 		loadDataTwo($(this).val());
 	});
	
	// 仓位改变事件
 	$("select[name='dc']").change(function(){
 		reloadDcInfo();
 	});
 	
 	// 仓位改变事件
 	$("select[name='dcTwo']").change(function(){
 		reloadDcInfoTwo();
 	});
 	
 	//搜索事件
	$("#clickBtnSerach").click(function(){
		tbPage = 1;
		//清除数据
		$("#sendGoodsTypeRe").val('');
		$("#otherReasonRe").val('');
 		search();
 	});
 	//搜索事件
	$("#clickBtnTwo").click(function(){
		tbPage = 1;
		$("#isValidRe").val('');
 		searchTwo();
 	});
	
	
	//批量录入
	$("#batchInput").click(function(){
		showBatchInput();
 	});
	
	//批量录入
	$("#submitBatch").click(function(){
		submitBatch();
 	});
	 /**
     * 保存
     */
    $("#save").bind("click",function(){
    	saveResponse("1");
    });
    //查询
    function search(){
    	var purchaserId = $("#pur_all").val();
 		if(null == purchaserId || '' == purchaserId || undefined== purchaserId){
    		layer.msg("请选择采购商",{icon:0});
    		return;
    	}
 		//alert(purchaserId);
    	var re = queryData();
 		jQuery("#table").jqGrid('setGridParam',{datatype:'json',page:1,postData:{query_type:"JQGRID",reqData:re}}).trigger("reloadGrid");
 		Initialization();
    }
  
	//查询数据
 	function searchTwo(){
 		var purchaserId = $("#pur_allTwo").val();
 		if(null == purchaserId || '' == purchaserId || undefined== purchaserId){
    		layer.msg("请选择采购商",{icon:0});
    		return;
    	}
 		var re = queryDataTwo();
 		jQuery("#table_a").jqGrid('setGridParam',{datatype:'json',page:tbPage,postData:{query_type:"JQGRID",reqData:re}}).trigger("reloadGrid");
 		//当刷新时，当前页码不为1，且该页没有数据时，将自动跳转到上一页
		if(tbPage>1){
			setTimeout(function(){
				var reccount = jQuery("#table_a").jqGrid('getGridParam','reccount');
				if(reccount==0){
					tbPage = tbPage -1;
					searchTwo();//重新加载
				}
			},500);
		}
 		
 	}
 	//展示批量录入窗口
 	function showBatchInput(){
 		var ids = jQuery("#table").jqGrid('getGridParam','selarrrow');
 		if (ids.length == 0) {
 			layer.alert("请选择至少一条数据！");
 			return;
 		}
 		$("#myModal").modal("show");
 	}
 	//批量确定
 	function submitBatch(){
 		var batchDiscountrate=$("#batchDiscountrate").val();
 		var batchSendQty=$("#batchSendQty").val();
 		var batchNotEnoughReason=$("#batchNotEnoughReason").val();
 		var batchSendDate=$("#batchSendDate").val();
 		var batchRemark=$("#batchRemark").val();
 		//排空处理
    	if((batchDiscountrate!=null && batchDiscountrate!="")
    			|| (batchSendQty!=null && batchSendQty!="")
    			|| (batchNotEnoughReason!=null && batchNotEnoughReason!="")
    			|| (batchSendDate!=null && batchSendDate!="")
    			|| (batchRemark!=null && batchRemark!="")){
    		
    		//检查折扣格式,需求是保留两位小数
    		var reg = /^(0|[1-9]|[1-9]\d|100)(\.\d{1,2}|\.{0})$/;
    		if (!reg.test(batchDiscountrate) && batchDiscountrate != '') {
    			layer.msg("请正确填写折扣!",{icon : 0,time:2000});
				return;
    		}
    		var reg = /^(0|([1-9]{1}[0-9]{0,7}))$/;
    		var reg1 = /^[0-9]*$/;
			if (!reg.test(batchSendQty)  && batchSendQty != '') {
				if(reg1.test(batchSendQty)){
					layer.msg("本次发货数只能输入最多 8 位整数!",{icon : 0,time:2000});
					return;
				}else{
					layer.msg("请正确填写本次发货数!",{icon : 0,time:2000});
					return;
				}
			}
			if(batchRemark.length>20){
     			layer.alert("需求备注不能超过20个字符！");
     			return;
     		}
			//获取勾选
     		var moduleIds = jQuery("#table").jqGrid('getGridParam','selarrrow');
     		if (moduleIds.length == 0) {
     			layer.alert("请选择至少一条数据！");
     			return;
     		}
        	//初始化合计
        	Initialization();
        	
     		for(var j=0;j<moduleIds.length;j++){
     			 batchDiscountrate=$("#batchDiscountrate").val();
     	 		 batchSendQty=$("#batchSendQty").val();
     	 		 batchNotEnoughReason=$("#batchNotEnoughReason").val();
     	 		 batchSendDate=$("#batchSendDate").val();
     	 		 batchRemark=$("#batchRemark").val();
     			 id = moduleIds[j];
     			//批量数据检验,不合逻辑品种进行标红
        		var isAccesses = true;
    			var rowData = jQuery("#table").jqGrid('getRowData',id);
    			//默认订数和可填的最大订数
    			var unsendoutQty = parseInt(rowData.orderQty)- parseInt(rowData.sendoutQty),
				maxUnsendoutQty = parseInt(rowData.orderQty) * 1.3- parseInt(rowData.sendoutQty);
    			
    			//数据处理，如果批量填写有数据的使用批量数据，若无则使用原有的（本次发货数、其余满足原因、预计发货日期）
    			if(batchSendQty==null || batchSendQty==""){
    				batchSendQty = $("#table").find("#sendQty_"+id).val();
        		}
        		if(batchNotEnoughReason==null || batchNotEnoughReason==""){
        			batchNotEnoughReason = $("#table").find("#availableReason_"+id).val();
        		}
        		if(batchSendDate == null || batchSendDate == ""){
        			batchSendDate = $("#table").find("#preSendDate_"+id).val();
        		}
        		//1、本次发货数>130%订数-已发数
        		if(batchSendQty>maxUnsendoutQty){
					isAccesses = false;
				}
				//2、本次发货数>=订数-已发数，但“其余满足情况”非空；
				var availableReason = $("#table").find("#availableReason_"+id).val();
				if(batchSendQty>=unsendoutQty && batchNotEnoughReason != ''){
					batchNotEnoughReason="";
					$("#table").find("#availableReason_"+id).val(batchNotEnoughReason);
					$("#table").find("#availableReason_"+id).attr("disabled", true);
					//isAccesses = false;
				}
				//3、本次发货数<订数-已发数，但“其余满足情况”为空；
				if(batchSendQty!=null && batchSendQty!="" && 
						batchSendQty<unsendoutQty && batchNotEnoughReason == ''){
					$("#table").find("#availableReason_"+id).attr("disabled", false);
					isAccesses = false;
				}
				//4、其余满足情况=预计可发，但“其余预计发货日期”为空
				if(batchNotEnoughReason == "0" && (batchSendDate=='' || batchSendDate == null) ){
					$("#table").find("#availableReason_"+id).attr("disabled", false);
					isAccesses = false;
				}
				
				if(isAccesses && batchSendQty<unsendoutQty){
					$("#table").find("#availableReason_"+id).attr("disabled", false);
				}
				
				/****赋值部分****/
        		//折扣
				if(batchDiscountrate!=null && batchDiscountrate!=""){
					$("#table").find("#discountrate_"+id).val(Number(batchDiscountrate).toFixed(2));
				}
				//本次发货数
        		if(batchSendQty!=null && batchSendQty!=""){
        			$("#table").find("#sendQty_"+id).val(batchSendQty);
        			$("#table").find("#sendQtyTemp_"+id).val(batchSendQty);
        		}
        		//其余满足原因
        		$("#table").find("#availableReason_"+id).val(batchNotEnoughReason);
        		
        		//预计发货日期
        		if(batchSendDate!=null && batchSendDate!=""){
        			$("#table").find("#preSendDate_"+id).val(batchSendDate);
        		}
        		//备注
        		if(batchRemark!=null && batchRemark!=""){
        			$("#table").find("#responseRemark_"+id).val(batchRemark);
        		}
    			 
        		/****合计部分****/
        		//重新合计数量
    			totalVariety += 1;
    			var price = $("#table").find("#price_"+id).attr("value"),
    			discountrate,sendQty;
    			
    			if(batchDiscountrate!=null && batchDiscountrate!=""){
    				discountrate = batchDiscountrate;
    			}else{
    				discountrate = $("#table").find("#discountrate_"+id).val();
    			}
    			if(batchSendQty!=null && batchSendQty!=""){
    				 sendQty= batchSendQty;
    			}else{
    				 sendQty= $("#table").find("#sendQty_"+id).val();
    			}
    			totalSendQty += parseInt(sendQty);
				totalSendPrice += parseInt(sendQty) * Number(price);
				totalSendRealPrc = totalSendRealPrc*100 + parseInt(sendQty) * Number(price) * Number(discountrate);
//				totalSendRealPrc +=parseInt(sendQty) * Number(price) * Number(discountrate)/100;
				InitTotal();
				
				
				
				
				/****品种行项和按钮处理部分*****/
        		//品种行项标红
        		if(!isAccesses){
        			setState(id,true,1);
        		}else{
        			setState(id,false,1);
        		}
    			/*if(discountrate>rowData.discountrateBlank){
    				layer.alert("填写折扣不能大于订单折扣");
    				return;
    			}
    			var sendQtyThis= rowData.orderQty - rowData.sendoutQty;
    			var thisSendQty=sendQtyThis*1.3;
    			if(sendQty>thisSendQty){
    				layer.alert("发货数不能超出未发数的30%");
    			}
    			$("#discountrate_"+rowData.id).val(discountrate);
    			$("#sendQty_"+rowData.id).val(sendQty);
    			$("#preSendDate_"+rowData.id).val(sendDate);
    			$("#responseRemark_"+rowData.id).val(remark);
    			$("#availableReason_"+rowData.id).val(notEnoughReason);*/
     		}
     		$("#batchDiscountrate").val("");
     		$("#batchSendQty").val("");
     		$("#batchNotEnoughReason").val("");
     		$("#batchSendDate").val("");
     		$("#batchRemark").val("");
     		$("#myModal").modal("hide");
     		
     		
    	}else{
    		layer.alert("请至少填写一项，再进行确认!", {icon : 0});
    		return;
    	}
 		
 	}
    /**
     * 回告保存  0、确认按钮  1、保存按钮
     */
    function saveResponse(sendFlag){
    	var ids = new Array();
    	ids = $("#table").jqGrid("getGridParam","selarrrow");
    	if(ids.length==0){
    		layer.alert("请先勾选待处理品种，再进行【保存】操作!", {icon : 0});
    		return;
    	}else{
    		//判断是否存在错误数据
    		var isSend = booleanDisabled(ids);
    		if(!isSend){
        		return;
    		}
    		var saveSend = [], saveUnsend = [];
    		//处理数据
    		for(var i=0; i< ids.length; i++){
//    			rData.sendGoodsType
    			var id = ids[i];
    			var availablePrice = $("#table").find("#price_"+id).attr("value"),
    			availableDiscountrate = $("#table").find("#discountrate_"+id).val(),
    			thisSendQty = $("#table").find("#sendQty_"+id).val(),
    			otherAvailableReason = $("#table").find("#availableReason_"+id).val(),
    			preSendDate = $("#table").find("#preSendDate_"+id).val(),
    			responseRemark = $("#table").find("#responseRemark_"+id).val(),
    			//purchaserId = $("#purchaserId").val();
    			 purchaserId = $("#pur_all").val();
    			//处理数据
    			var responseOrderTemp = $.extend({}, responseOrder, {
    				proPurOrderItemsId : Number(ids[i]),
    				availablePrice : Number(availablePrice),
    				availableDiscountrate : Number(availableDiscountrate),
    				thisSendQty : thisSendQty==""?0:Number(thisSendQty),
    				otherAvailableReason : otherAvailableReason==""?null:otherAvailableReason,
    				preSendDate : preSendDate==""?null:preSendDate,
    				responseRemark : responseRemark==""?null:responseRemark,
    				purchaserId :purchaserId
				});
    			saveSend.push(responseOrderTemp);
    		}
    		//console.log(saveSend);
    		//保存开始
    		var getTimestamp=new Date().getTime();
	  			
	  			$.ajax({ 
					type: "post",
					url: appPath+"/backto/order/json/saveResponse?timestamp="+getTimestamp, 
					data: {"saveSend" : JSON.stringify(saveSend),"type":sendFlag},
					dataType: "json", 
					async: false,
					success: function(json){
						WG.loading.hide();
						if(json.success){
							$("#ids").val(json.obj);
							//alert(json.obj);
							/*layer.msg("保存成功",{icon:1},function(){
								//刷新表格
								search();
							});*/
							WG.loading.hide();
							if(json.obj){
								openSendGoodsIframe(json.obj);
								//console.log(json);
//								search();
							}else{
								search();
							}
							
						}else{
							layer.msg(json.msg,{icon:2},function(){
								//刷新页面
								search();
							});
							WG.loading.hide();
						}
					},error : function() {
						WG.loading.hide();
						layer.msg("保存异常!",{icon : 0,time:2500});
					}
	  			});
	  		
    	}
    }
    
    
    function  sureSave(){
    	var selectedIds = $("#table").jqGrid("getGridParam", "selarrrow");
	 		if(selectedIds < 1){
	 			layer.alert("请勾选订单！",{icon:0});
	 			return;
	 		}
	 		var gp = 0;   //馆配
	 		var dzz = 0;   //大中专
	 		var qt = 0;  //其他
	 		var orders = "";
	 		var ary=new Array();
			for(var i = 0;i<selectedIds.length;i++){
	 			var rowData = $("#tableb").jqGrid("getRowData", selectedIds[i]);
	 			var orderType = rowData.orderType;  //订单种类
	 			var sendGoodsType = rowData.sendGoodsType;  //发货方式(收货类型):1：普通,2：直供
	 			ary.push(rowData.warehouse);
	 			orders += rowData.yunhanOrderCode+",";
	 			//判断是否是直供订单，如果是则不允许多单回告
	 			if(2 == sendGoodsType){
	 				layer.alert("您勾选是直供门店订单，请单订单回告！",{icon:0});
	 				return false;
	 			}
	 			if(25 == orderType){
	 				gp += 1;
	 			}else if(15 == orderType){
	 				dzz += 1 ;
	 			}else {
	 				qt += 1;
	 			}
			}
			
			
			//判断订单是否属于一个仓库
		var nary=ary.sort();
			if(ary.length>1){
				var str = [];
	 			for(var i = 0,len = ary.length;i < len;i++){
	 			! RegExp(ary[i],"g").test(str.join(",")) && (str.push(ary[i]));
	 			}
				if(str.length>1){
					    layer.alert("你勾选了不同仓位的订单，不能同时操作，请调整！",{icon:0});
  			       return false;
				}
				/*for(var i=1;i<ary.length;i++){
	 			    if (nary[i]==nary[i+1]){
	 			    	// alert("数组重复内容为:"+nary[i]);
	 			    }else{
	 			    	//alert("数组重复内容为---:"+nary[i]);
	 			    	//layer.alert("您勾选了单子包含不同仓位，不能同时操作，请调整！",{icon:0});
	  			       return false;
	 			    }
	 			}*/
			}
			
			
			orders = orders.substring(0,orders.length-1);
			var flag = false;
			if(gp == selectedIds.length){
				flag = true;
			}else if(dzz == selectedIds.length){
				flag = true;
			}else if(qt == selectedIds.length){
				flag = true;
			}else{
				layer.alert("您勾选了不同订单种类的订单，不能同时操作，请调整！", {icon:0});
				flag = false;
				return;
			}
    	
			if(flag){
				var ids=$("#ids").val();
		    	//alert(ids);
		    	//openSendGoodsIframe(ids);
			}
    }
    //是否有保存，确认资格
    function valitionTable(){
      var	flag=false;
  	  var selectedIds = $("#table").jqGrid("getGridParam", "selarrrow");
		if(selectedIds < 1){
			layer.alert("请勾选订单！",{icon:0});
			return;
		}
		for(var i = 0;i<selectedIds.length;i++){
 			var rowData = $("#tableb").jqGrid("getRowData", selectedIds[i]);
 			var sendQtyThis= rowData.orderQty - rowData.sendoutQty;
			var thisSendQty=sendQtyThis*1.3;
			var sendQty=$("#sendQty_"+rowData.id).val();
			if(sendQty>thisSendQty){
				layer.alert("发货数不能超出未发数的130%");
			}
			//console.log(rowData);
			$("#tableb").jqGrid('setRowData', rowData, false, { color: '#FF0000' });
		}
		
		//$(this).jqGrid('setRowData', rowid, false, { color: '#FF0000' });
    	
    	
    }
    
    
    //关闭发货
    $("#closeSend").bind("click",function(){
    	//获取勾选id
    	var ids = new Array();
    	ids = $("#table").jqGrid("getGridParam","selarrrow");
    	if(ids.length==0){
    		layer.alert("请先勾选待处理品种，再进行【关闭发货】操作!", {icon : 0});
    		return;
    	}else{
    		//判断每个品种是否是待处理
    		for(var i=0; i<ids.length; i++){
    			//获取当前品种处理状态
    			var isDeal = $("#table").find("#isDealStr_"+ids[i]).val();
    			if(isDeal=='Y'){
    				layer.alert("您勾选的品种中有已处理品种，不能进行关闭发货!", {icon : 0});
    				return ;
    			}
    		}
    		//设置需要填写的id
    		$("#notGoodsReasonForm").find("#notGoodsReasonIds").val(ids);
    		$("#myModalReason").modal("show");
    	}
    });
    /**
     * 关闭发货保存
     */
    $("#notGoodsReasonSave").bind("click",function(){
    	var ids = $("#notGoodsReasonForm").find("#notGoodsReasonIds").val(),
    	notGoodsReason = $("#notGoodsReasonForm").find("#notGoodsReason").val();
    	$("#myModalReason").modal("hide");
    	WG.loading.show();
    	//执行保存
    	$.ajax({
    		type : "post",
			url:appPath+'/backto/order/json/saveCloseSendOutGoodPz',
			data: {"ids" : ids,
				"notGoodsReason":notGoodsReason
				},
			dataType : "json",
			success : function(json) {
				if(json.success){
					WG.loading.hide();
					layer.msg("关闭发货成功!",{icon :1,time:1500},function(){
						//刷新页面
						search();
					});
				}else{
					WG.loading.hide();
					layer.alert(json.msg,{icon : 2});
				}
			},error : function() {
				WG.loading.hide();
				layer.msg("关闭发货出错!",{icon : 0,time:2500});
			}
    	});
    }); 
    
    
 	//确认批量操作
    /**
     * 弹出发货确认详情页面
     * 填写发货详细信息
     * @param ids 回告细目id集合，多个用英文字符逗号分隔
     * 2016年7月14日16:26:22
     */
    function openSendGoodsIframe(ids){
    	var purchaserId = $("#pur_all").val();
    	var url=appPath+"/backto/sendGoods/page/sendGoodsProcessing?reportIds="+ids+"&purchaserId="+purchaserId;
    	$("#sendGoodsViewIFrame").attr("src",url);
    	$("#myModal1").modal({show: true, backdrop: 'static', keyboard: false}); 
    }
    
    //取消发货按钮
    $(".closeSendGoods").bind("click",function(){
    	$("#sendGoodsViewIFrame").attr("src",null);
    	$("#myModal1").modal("hide");
    	if($("#myModal3")){
    		$("#myModal3").remove();
    	}
    	//window.location.reload();//刷新当前页面
    });
    /**
     * 发货页面的确认发货按钮
     * 进行发货
     */
//    $("#saveSendGoods").bind("click",function(){
//    	var frames=document.getElementById("sendGoodsViewIFrame");//frameid即弹出发货框iframe的id名
//    	frames.contentWindow.saveSendGoods();
//    });
    
    
	//重新加载数据
	function loadData(purchaserId){
		if(null == purchaserId || '' == purchaserId || undefined == purchaserId){
			jQuery("#table").jqGrid('clearGridData');
			//清楚仓位下拉数据
			var obj=document.getElementById('dc_all');
			obj.options.length=0; 
			return;
		}
		//获取一个月以前的待处理品种
		autoCountMonthAgo();
		var flag = getDC(purchaserId);  //加载仓位
		if(flag){
			var re = queryData();
			jQuery("#table").jqGrid('setGridParam',{datatype:'json',page:1,postData:{query_type:"JQGRID",reqData:re}}).trigger("reloadGrid");
		}
	}
  //获取仓位
	function getDC(purchaserId){
		var flag = false;
 		if(null == purchaserId || '' == purchaserId){
 			$("#paddr").text("");
			$("#contact").text("");
			$("#contactNumber").text("");
			return;
 		}
 		var url=appPath+"/backto/system/json/getDCAndCount";//仓位
 		var wareHouseRe = $("#wareHouseRe").val();//获取从待办传来的仓位
 		$.ajax({
			type : "POST",
			url:url,
			dataType : "json",
			async:false,
			data: {"purId" : purchaserId,"userName":userName},
			success : function(data) {
				var options = "<option value='' selected='selected'>全部</option> ";
				if(data){
					for(var i=0;i<data.length;i++){
						if((wareHouseRe!=null || wareHouseRe!="")&& data[i].code == wareHouseRe){
							options += "<option value='"+data[i].code+"' selected paddr='"+data[i].paddr+"' contact='"+data[i].contact+"' contactNumber='"+data[i].contactNumber+"'>"+data[i].name+" "+data[i].dataNumbs+"</option>";
						}else{
							options += "<option value='"+data[i].code+"' paddr='"+data[i].paddr+"' contact='"+data[i].contact+"' contactNumber='"+data[i].contactNumber+"'>"+data[i].name+" "+data[i].dataNumbs+"</option>";
						}
						if(wareHouseRe == data[i].code){
							$("#paddr").text(data[i].paddr);
							$("#contact").text(data[i].contact);
							$("#contactNumber").text(data[i].contactNumber);
						}
					}
					$("#dc_all").html(options);
				}
				flag = true;
			},error : function() {
				alert("获取仓位出错");
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
		var re = queryData();
		//console.log(re);
		jQuery("#table").jqGrid('setGridParam',{datatype:'json',page:1,postData:{query_type:"JQGRID",reqData:re}}).trigger("reloadGrid");
		$("#paddr").text((paddr == null || paddr == 'null')?"":paddr);
		$("#contact").text((contact == null || contact == 'null')?"":contact);
		$("#contactNumber").text((contactNumber == null || contactNumber == 'null')?"":contactNumber);
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

	
	
	/**
	 * 1个月前/1个月内
	 */
	$("a[name='send_DateTwo']").click(function(){
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
		$("#sendDateStartTwo").val(startDt);
		$("#sendDateEndTwo").val(endDt);
	});

	
	
	
 	
 	
	
 	
	//重新加载数据
	function loadDataTwo(purchaserId){
		var flag = getDCTwo(purchaserId);  //加载仓位
		if(null == purchaserId || '' == purchaserId || undefined == purchaserId){
			jQuery("#table_a").jqGrid('clearGridData');
			//清楚仓位下拉数据
			var obj=document.getElementById('dc_allTwo');
			obj.options.length=0; 
			return;
		}
		if(flag){
			var re = queryDataTwo();
			jQuery("#table_a").jqGrid('setGridParam',{datatype:'json',page:1,postData:{query_type:"JQGRID",reqData:re}}).trigger("reloadGrid");
		}
	}
 	//获取仓位
	function getDCTwo(purchaserId){
		var flag = false;
 		if(null == purchaserId || '' == purchaserId){
 			$("#paddr").text("");
			$("#contact").text("");
			$("#contactNumber").text("");
			return;
 		}
 		var url=appPath+"/backto/system/json/getDC";//仓位
 		$.ajax({
			type : "POST",
			url:url,
			dataType : "json",
			async:false,
			data: {"purId" : purchaserId,"userName":userName},
			success : function(data) {
				var options = "<option value='' selected='selected'>全部</option> ";
				if(data){
					for(var i=0;i<data.length;i++){
						options += "<option value='"+data[i].code+"' paddr='"+data[i].paddr+"' contact='"+data[i].contact+"' contactNumber='"+data[i].contactNumber+"'>"+data[i].name+"</option>";
						/*if(i == 0){
							$("#paddrTwo").text(data[i].paddr);
							$("#contactTwo").text(data[i].contact);
							$("#contactNumberTwo").text(data[i].contactNumber);
						}*/
					}
					$("#dc_allTwo").html(options);
				}
				flag = true;
			},error : function() {
				alert("获取仓位出错");
			}
		});
 		return flag;
 	}
	//仓位重新加载
	function reloadDcInfoTwo(){
 		var selected = $("#dc_allTwo").find("option:selected");
		var paddr = selected.attr("paddr");
		var contact = selected.attr("contact");
		var contactNumber = selected.attr("contactNumber");
		var re = queryDataTwo();
		jQuery("#table_a").jqGrid('setGridParam',{datatype:'json',page:1,postData:{query_type:"JQGRID",reqData:re}}).trigger("reloadGrid");
		$("#paddrTwo").text((paddr == null || paddr == 'null')?"":paddr);
		$("#contactTwo").text((contact == null || contact == 'null')?"":contact);
		$("#contactNumberTwo").text((contactNumber == null || contactNumber == 'null')?"":contactNumber);
 	}

	/*//获取一个月以前的待处理品种
 	function autoCountMonthAgoTwo(){
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
				layer.msg("1个月前订单数量统计失败！",{icon:2});
			}
		});
 	}*/
 	
 	function getOderSortTwo(){
 		var queryData="";
 		var $this = $("#sortBoxTwo").find(".active"),
 		orderFile = $this.attr("data-value");
 		sortRule = $this.attr("name");
 		if(sortRule!=null && sortRule!=""){
 			sortRule = "desc";
 		}else{
 			sortRule = "asc";
 		}
 		
 		//设置排序
    	var queryFiled = "{'field':'sortOrderField','op':'eq','data':'"+ orderFile +"'},";
    	queryData = queryData.concat(queryFiled);
    	var querySortRule = "{'field':'sortOrderRule','op':'eq','data':'"+ sortRule +"'},";
    	queryData = queryData.concat(querySortRule);
 		return 	queryData;
 	}
 	
 // 修改导出状态函数(参数1：所选细目ids；参数2：刷新的方法名)
		function updateExportState(orderItemsIds,searchName){
			if('' != orderItemsIds){
				$.ajax({ 
					type:"post",
					url: appPath+'/backto/system/json/updateExportState',
					data: {"orderItemsIds":orderItemsIds},
					dataType: "json",
					success: function(data){
						if(data.success){
							layer.msg("更新导出状态成功！",{icon:1});
							if(searchName=="search"){
								search();//更新成功都重新刷新列表页面
							}else{
								searchTwo();//更新成功都重新刷新列表页面
							}
						}else{
							layer.alert("更新导出状态失败！",{icon:2});
						}
						setTimeout(WG.loading.hide(),5*1000);
						
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
					 orderItemsIds +=rowData.id+",";
				 }
				 if ("," == orderItemsIds.charAt(orderItemsIds.length - 1)) {
					 orderItemsIds = orderItemsIds.substring(0,orderItemsIds.length-1);
				}
				 return orderItemsIds;
			}
		}
		//导出处理函数
		function doExport(queryData,orderItemsIds,search,name){
			if(queryData!=""){
				$.ajax({ 
					type:"post",
					url: appPath+"/console/export/ajax/backtoProcessedVarietiesExport/excel",
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
 	//已处理品种导出/下载
		$("#processedVarietiesExport").bind("click",function(){
		//$("#processedVarietiesExport").unbind("click").one("click",function(){
		var orderItemsIds = getSelectIds("table_a");
		if(orderItemsIds==""){
			return ;
		}
		var queryData = "{'groupOp':'AND','rules':[";
		//add by yangtao 2016-4-28
		var purchaserId = $("#pur_allTwo").val();
		//采购商编码
	   	if(null != purchaserId && '' != purchaserId){
	   		var purchaserId = "{'field':'purchaserId','op':'eq','data':'"+ purchaserId +"'},";
	   		queryData = queryData.concat(purchaserId);
	   	}
	   	//仓位code
	   	var cwCode = $("#dc_allTwo").val();
		if(null != cwCode && '' != cwCode){
	   		var cwCode = "{'field':'cwCode','op':'eq','data':'"+ cwCode +"'},";
	   		queryData = queryData.concat(cwCode);
	   	}
		var purchaserName = $("#pur_all").find("option:selected").text();
		var timeDay = new Date();
	    var name =purchaserName + "_采购品种发货_"+timeDay.getFullYear()+""+((timeDay.getMonth()+1)<10?"0"+(timeDay.getMonth()+1):(timeDay.getMonth()+1))+""+timeDay.getDate()+""+timeDay.getHours()+""+timeDay.getMinutes()+""+timeDay.getSeconds();
	    var a = "{'field':'excelName','op':'eq','data':'"+name+"'},";
		queryData = queryData.concat(a);
		//获取排序方式
    	var sortFiled = getOderSortTwo();
    	queryData = queryData.concat(sortFiled);
		//所选行ids
		var a = "{'field':'orderItemsIds','op':'eq','data':'"+orderItemsIds.toString()+"'}";
			queryData = queryData.concat(a);
		var f= "]}";
		queryData = queryData.concat(f);
		//console.log(queryData);
		
		doExport(queryData,orderItemsIds,"searchTwo",name);//导出处理操作
		
	});
		
		//待处理品种导出/下载
		$("#waitVarietiesExport").bind("click",function(){
		//$("#processedVarietiesExport").unbind("click").one("click",function(){
		var orderItemsIds = getSelectIds("table");
		if(orderItemsIds==""){
			return ;
		}
		var queryData = "{'groupOp':'AND','rules':[";
		//add by yangtao 2016-4-28
		var purchaserId = $("#pur_all").val();
		//采购商编码
	   	if(null != purchaserId && '' != purchaserId){
	   		var purchaserId = "{'field':'purchaserId','op':'eq','data':'"+ purchaserId +"'},";
	   		queryData = queryData.concat(purchaserId);
	   	}
	   	//仓位code
	   	var cwCode = $("#dc_all").val();
		if(null != cwCode && '' != cwCode){
	   		var cwCode = "{'field':'cwCode','op':'eq','data':'"+ cwCode +"'},";
	   		queryData = queryData.concat(cwCode);
	   	}
		var purchaserName = $("#pur_all").find("option:selected").text();
		var timeDay = new Date();
	    var name = purchaserName + "_采购品种发货_"+timeDay.getFullYear()+""+((timeDay.getMonth()+1)<10?"0"+(timeDay.getMonth()+1):(timeDay.getMonth()+1))+""+timeDay.getDate()+""+timeDay.getHours()+""+timeDay.getMinutes()+""+timeDay.getSeconds();
	    var a = "{'field':'excelName','op':'eq','data':'"+name+"'},";
		queryData = queryData.concat(a);
		//获取排序方式
    	var sortFiled = getOderSort();
    	queryData = queryData.concat(sortFiled);
		//所选行ids
		var a = "{'field':'orderItemsIds','op':'eq','data':'"+orderItemsIds.toString()+"'}";
			queryData = queryData.concat(a);
		var f= "]}";
		queryData = queryData.concat(f);
		//console.log(queryData);
		
		doExport(queryData,orderItemsIds,"search",name);//导出处理操作
		
	});
		
		
		
});

//查询条件获取
function queryData(){
	//userName='2200059PQ';
	var re="{'groupOp':'AND','rules':[";
	var queryOrdertypeQC = "{'field':'userCode','op':'eq','data':'"+userName+"'},";
	re = re.concat(queryOrdertypeQC);
	var purchaserId = $("#pur_all").val();
	var dc = $("#dc_all").val();
	var isExportArr =[]; 
	$('input[name="isExport"]:checked').each(function(){ 
		//isExportArr.push($(this).val()); 
	}); 
	var  isExport11 = $("input[name='isExport']:checked");
	isExport11.each(function(){
		//alert($(this).val());
		isExportArr.push($(this).val());
	});
	var isExport="";
	if(isExportArr.length>0){
		if(isExportArr.length=1){
			isExport=isExportArr[0];
		}else{
			isExport=null;
		}
	}
	var urgentFlag = $("input[name='urgentFlag']:checked").val();
//	var orderType = $("#orderType").val();
	var orderType = $(".AllSelect .selectData").val();
	/*if(null == orderType || '' == orderType){
		orderType = $("#sendGoodsTypeRe").val();
	}*/
	var isDeal=$("#isDealRe").val();
	var isValid=$("#isValidRe").val();
	
	var isbn=$("#isbn").val();
	//查询待办里面带出来的直供标识
	var sendGoodsTypeRe = $("#sendGoodsTypeRe").val();
	//查询待办里面带出来的预计可发和暂时缺货
	//var otherReasonRe = $("#otherReasonRe").val();
	var otherReason="";
	//是否回告
	var controlFlagArr =[]; 
	$('input[name="control"]:checked').each(function(){ 
		//controlFlagArr.push($(this).val()); 
	}); 
	/*var  controlFlag11 = $("input[name='controlFlag']:checked");
	controlFlag11.each(function(){
		//alert($(this).val());
		controlFlagArr.push($(this).val());
	});
	var controlFlag="";
	if(controlFlagArr.length>0){
		if(controlFlagArr.length=1){
			controlFlag=controlFlagArr[0];
		}else{
			controlFlag=null;
		}
	}*/
	//未回告
	var noReport=false;
	//待处理 品种处理情况
	var isValidOne = $(".isValidOne .selectData").val();
	var isValidOneArr = isValidOne.split(",");
	for(var i=0;i<isValidOneArr.length;i++){
		if(isValidOneArr[i]=="noReport"){//未回告
			noReport=true;
			
		}else if(isValidOneArr[i]=="preSend"){//预计可发
			if(otherReason==""){
				otherReason+=PREDICT_SEND;
			}else{
				otherReason+=","+PREDICT_SEND;
			}
		}else if(isValidOneArr[i]=="tempStockout"){
			if(otherReason==""){
				otherReason+=TEMPORARILY_STOCKOUT;
			}else{
				otherReason+=","+TEMPORARILY_STOCKOUT;
			}
			
		}else if(isValidOneArr[i]=="newWaitWarehousing"){
			if(otherReason==""){
				otherReason+=NEW_WAITING_WAREHOUSING;
			}else{
				otherReason+=","+NEW_WAITING_WAREHOUSING;
			}
			
		}
	}
	//订单号码
	var orderCode = $("#orderCode").val();
	//书名
	var booktitle=$("#booktitle").val();
	var isNew = $("input[name='isNew']:checked").val();
	var sendDateStart	 = $("#sendDateStart").val();
	var sendDateEnd	 = $("#sendDateEnd").val();
	//采购商
	if(null != purchaserId && '' != purchaserId){
		var a = "{'field':'purchaserId','op':'eq','data':'"+ purchaserId +"'},";
		re = re.concat(a);
		//获取一个月以前的待处理品种
		/*autoCountMonthAgo();*/
	}else{
    	layer.msg("请选择采购商",{icon:0});
    	return;
	}
	//仓位
	if(null != dc && '' != dc){
		var a = "{'field':'warehouse','op':'eq','data':'"+ dc +"'},";
		re = re.concat(a);
	}
	//下载标识
	if(null != isExport && '' != isExport){
		var a = "{'field':'isExport','op':'eq','data':'"+ isExport +"'},";
		re = re.concat(a);
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
	//isbn
	 if(null != isbn && '' != isbn){
			if(isbn.indexOf(",")!=-1){
				var a = "{'field':'isbns','op':'eq','data':'"+ isbn +"'},";
 			re = re.concat(a);
    		}else{
				var a = "{'field':'isbn','op':'eq','data':'"+ isbn +"'},";
    			re = re.concat(a);
			}
		}
	//是否回告
 	/*if(null != controlFlag && '' != controlFlag){
 		var a = "{'field':'controlFlag','op':'eq','data':'"+ controlFlag +"'},";
 		re = re.concat(a);
 	}*/
	 //未回告
	 if(noReport){
		 var a = "{'field':'controlFlag','op':'eq','data':'0'},";
	 		re = re.concat(a);
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
 	
	//商品名称
	if(null != booktitle && '' != booktitle){
		if(booktitle.indexOf(",")!=-1){
			var a = "{'field':'bookTitles','op':'eq','data':'"+ booktitle +"'},";
			re = re.concat(a);
		}else{
			var a = "{'field':'bookTitle','op':'eq','data':'"+ booktitle +"'},";
			re = re.concat(a);
		}
	}
	//新品
	if(null != isNew && '' != isNew && undefined != isNew){
		var a = "{'field':'isNew','op':'eq','data':'"+ isNew +"'},";
		re = re.concat(a);
	}
	//if(null != isDeal && '' != isDeal && undefined != isDeal){
	//	var a = "{'field':'isDeal','op':'eq','data':'"+ isDeal +"'},";
	//	re = re.concat(a);
	//}
//	if(null != isValid && '' != isValid && undefined != isValid){
//		var a = "{'field':'isValid','op':'eq','data':'"+ isValid +"'},";
//		re = re.concat(a);
//	}
	//订单发送日期-开始
	//有时候页面会多出一个 1
	sendDateStart = sendDateStart.replace(/(^\s*)|(\s*$)/g,"");
	if(null != sendDateStart && '' != sendDateStart && sendDateStart != '1'){
		var a = "{'field':'sendDateStart','op':'eq','data':'"+ sendDateStart +"'},";
		re = re.concat(a);
	}
	//订单发送日期-结束
	sendDateEnd = sendDateEnd.replace(/(^\s*)|(\s*$)/g,"");
	if(null != sendDateEnd && '' != sendDateEnd && sendDateEnd != '1'){
		var a = "{'field':'sendDateEnd','op':'eq','data':'"+ sendDateEnd +"'},";
		re = re.concat(a);
	}
	//订单发货方式
	if(null != sendGoodsTypeRe && '' != sendGoodsTypeRe){
		var a = "{'field':'sendGoodsType','op':'eq','data':'"+ sendGoodsTypeRe +"'},";
		re = re.concat(a);
	}
	//其余满足情况
	if(null != otherReason && '' != otherReason){
		var a = "{'field':'otherAvailableReason','op':'eq','data':'"+ otherReason +"'},";
		re = re.concat(a);
	}
	//紧急程度
	if(null != urgentFlag && '' != urgentFlag && undefined != urgentFlag){
		var a = "{'field':'urgentFlag','op':'eq','data':'"+ urgentFlag +"'},";
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
			layer.msg("1个月前订单数量统计失败！",{icon:2});
		}
	});
	}
	
	//获取日期
 	function GetDateStr(AddDayCount){
 		var dd = new Date(); 
 		dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期 
 		var y = dd.getFullYear(); 
 		var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);//获取当前月份的日期，不足10补0
 		var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate(); //获取当前几号，不足10补0
 		return y+"-"+m+"-"+d; 
 	}
 	
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
		if(orderFile == undefined){
 			orderFile = "defaultSort";
 		}
		//订单发送日期-结束
	var queryFiled = "{'field':'sortOrderField','op':'eq','data':'"+ orderFile +"'},";
	queryData = queryData.concat(queryFiled);
	var querySortRule = "{'field':'sortOrderRule','op':'eq','data':'"+ sortRule +"'},";
	queryData = queryData.concat(querySortRule);
		return 	queryData;
	}
/**
 * 发货后 关闭发货弹窗
 */
function closeSendGoods(){
	$("#sendGoodsViewIFrame").attr("src",null);
	$("#myModal1").modal("hide");
	if($("#myModal3")){
		$("#myModal3").remove();
	}
	searchOperation();
}

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
	sendDateStart	 = $("#sendDateStart").val(),
	sendDateEnd	 = $("#sendDateEnd").val(),
	sendGoodsType = $("#sendGoodsType").val(),
	isbn = $("#isbn").val(),
	booktitle = $("#booktitle").val(),
	
	purchaserIdTwo = $("#pur_allTwo").val(),
	dc_allTwo = $("#dc_allTwo").val(),
	isbnTwo = $("#isbnTwo").val(),
	booktitleTwo = $("#booktitleTwo").val(),
	sendDateStartTwo	 = $("#sendDateStartTwo").val(),
	sendDateEndTwo	 = $("#sendDateEndTwo").val(),
	sendGoodsTypeTwo = $("#sendGoodsTypeTwo").val(),
	orderCodeTwo = $("#orderCodeTwo").val(),
	orderTypeTwo = $(".AllSelectTwo .selectData").val();
	
	var isNewId = $("input[name='isNew']:checked").attr("id"),
	isExport = $("input[name='isExport']:checked").attr("id"),
	controlFlag = $("input[name='controlFlag']:checked").attr("id"),
	
	isExportTwo = $("input[name='isExportTwo']:checked").attr("id"),
	isNewTwo = $("input[name='isNewTwo']:checked").attr("id");
	
	//isDealId = $("input[name='isDeal']:checked").attr("id");
	if(purchaserId!='' && purchaserId!=null){
		data["pur_all"] = purchaserId;
	}
	data["dc_all"] = dc;
	data["orderCode"] = orderCode;
	data["orderType"] = orderType;
	data["isNew"] = isNewId;
	data["isExport"] = isExport;
	data["controlFlag"] = controlFlag;
	//data["isDeal"] = isDealId;
	data["sendGoodsType"] = sendGoodsType;
	data["isbn"] = isbn;
	data["booktitle"] = booktitle;
	
	data["pur_allTwo"] = purchaserIdTwo;
	data["dc_allTwo"] = dc_allTwo;
	data["isExportTwo"] = isExportTwo;
	data["isNewTwo"] = isNewTwo;
	data["isbnTwo"] = isbnTwo;
	data["booktitleTwo"] = booktitleTwo;
	data["orderCodeTwo"] = orderCodeTwo;
	data["orderTypeTwo"] = orderTypeTwo;
	if(sendDateStartTwo!='' && sendDateStartTwo!=null){
		data["sendDateStartTwo"] = sendDateStartTwo;
	}
	if(sendDateEndTwo!='' && sendDateEndTwo!=null){
		data["sendDateEndTwo"] = sendDateEndTwo;
	}
	
	
	if(sendDateStart!='' && sendDateStart!=null){
		data["sendDateStart"] = sendDateStart;
	}
	if(sendDateEnd!='' && sendDateEnd!=null){
		data["sendDateEnd"] = sendDateEnd;
	}
	var str = JSON.stringify(data);//json转字符串
	sessionStorage[userName+"orderTiems"]=str; //存入localStorage
}
/**
 * 操作（回告、发货）后的刷新
 */
function searchOperation(){
	var re = queryData();
	jQuery("#table").jqGrid('setGridParam',{datatype:'json',postData:{query_type:"JQGRID",reqData:re}}).trigger("reloadGrid");
}

