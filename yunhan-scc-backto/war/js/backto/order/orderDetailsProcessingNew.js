/**
 * 订单处理-订单详情
 * 2016年7月14日16:49:43
 * @author wangtao
 */

//合计品种
	var totalVariety =0,
	//合计本次发货数
	totalSendQty = 0,
	//合计本次发货码洋
	totalSendPrice = 0.00,
	//合计本次发货实洋
	totalSendRealPrc = 0.00;
In.ready('multipleDataBox','autocomplete','uploadBox','bmz','jqGrid','WdatePicker','fileUtil','select','bmz','jqGrid',function() {
	//表格记录有问题的id数组
	var unSendArray = new Array();
	//是否全部处理
	 var isDealFlag = true;
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
	
	
    //批量录入日期
    $('#start,#end,.startTime').click(function(){
        WdatePicker();
    });

   //发货信息收起
    $(".more-btn").on("click",function(){
	    var $this = $(this);
	    var $mbox = $(".listHidden");
	    if($this.attr("name")=="open"){
	      $mbox.show();
	      $this.attr("name","close")
	      $(this).text("收起▲")
	    }else{
	    	$mbox.hide();
	    	$this.attr("name","open")
	      $(this).text("展开▼")
	    }
	  });
    
    $("#table").jqGrid({
    	url: appPath+'/console/query',
		mtype:'post',
        autowidth: true, //自适应宽度
        shrinkToFit: true, //列自适应
        datatype: "json",
        colNames:["细目ID","商品信息","","定 价","","折扣（%）","订 数","","本次发货数","","其余满足情况","其余预计发货日期","备 注","已发数","已收数","发货率（%）","","品种处理情况","回告历史"],// the column header names
        colModel :[
            {name:"id", index:"细目id",width:'6%',hidden:true,align:'center'},
            {name:"isbn", index:"商品信息",width:'22%',align:'center',sortable:false,
            	formatter:function(value,options,rData){
            		var html="<div class='iconBox'>";
            		//已导出
            		if(rData.isExport=='Y'){
            			html +="<div class='right'><i class='u-icon-yxz'>已下载</i></div>";
            		}
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
            			html+="<li class='fontfui'>从书名："+rData.seriesTitle+"</li>" 
            		}else{
            			html+="<li class='fontfui'>从书名：</li>" 
            		}
            		html+=	"<li >ISBN："+value+"</li></ul></div></div>";
            		html +="</div>"
            	    return html;
            	}
            },
            {name:"priceTemp", index:"临时定 价",width:'7%',align:'center',title:false,hidden:true,
            	formatter:function(value,options,rData){
            		return "<input type='hidden' value='' class='b80' id='priceTemp_"+rData.id+"'>";
            	}
            },
            {name:"price", index:"定 价",width:'8%',align:'center',title:false,sortable:false,
            	formatter:function(value,options,rData){
            		var price = Number(value).toFixed(2);
            		//已回告完成过
            		if(rData.historyAvailablePrice !=null && rData.historyAvailablePrice !=""){
            			var availablePrice = Number(rData.historyAvailablePrice).toFixed(2);
            			if(value != rData.historyAvailablePrice){
            				var div="<div title='"+availablePrice+"'>";
            				return div+"<div class='fontdel'>订单定价："+price+"</div><div id='price_"+rData.id+"' value='"+availablePrice+"'>"+availablePrice+"</div></div>";
            			}else{
            				var div="<div title='"+price+"'>";
            				return "<div id='price_"+rData.id+"' value='"+price+"'>"+price+"</div></div>";
            			}
            		}
            		//回告暂存过
            		if(rData.availablePrice!=null && rData.availablePrice!=""){
            			var availablePrice = Number(rData.availablePrice).toFixed(2);
            			var div = "<div class='JGtab'><input type='text' value='"+availablePrice+"' class='b80 inputWidth' id='price_"+rData.id+"'></div>";
            			if(value != rData.availablePrice){
            				div = "<div class='fontdel'>订单定价："+price+"</div>"+div;
            			}
            			return div;
            		}else{
            			if(rData.isDeal=='Y'){
            				return "<div id='price_"+rData.id+"' value='"+price+"'>"+price+"</div>";
            			}else{
            				return "<div class='JGtab'><input type='text' value='"+price+"' class='b80 inputWidth' id='price_"+rData.id+"'></div>";
            			}
            			
            		}
            	}
            
            },
            {name:"discountrateTemp", index:"临时折扣（%）",width:'7%',align:'center',hidden:true,
            	formatter:function(value,options,rData){
            		return "<input type='hidden' value='' class='b80' id='discountrateTemp_"+rData.id+"'>";
            	}
            	
            },
            {name:"discountrate", index:"折扣（%）",width:'8%',align:'center',sortable:false,
            	formatter:function(value,options,rData){
            		var discount = Number(value).toFixed(2);
            		//已回告完成过
            		if(rData.historyAvailableDiscountrate !=null && rData.historyAvailableDiscountrate !=""){
            			var availableDiscountrate = Number(rData.historyAvailableDiscountrate).toFixed(2);
            			if(value != rData.historyAvailableDiscountrate){
            				//存在暂存
            				if(rData.responseStatus==0){
            					availableDiscountrate = Number(rData.availableDiscountrate).toFixed(2);
            				}
            				var div = "<div class='JGtab'><input type='text' value='"+availableDiscountrate+"' class='b80 inputWidth' id='discountrate_"+rData.id+"'></div>";
            				if(rData.isDeal=='Y'){
            					div = "<div id='discountrate_"+rData.id+"' value='"+availableDiscountrate+"'>"+availableDiscountrate+"</div>"
            				}
            				if(availableDiscountrate!=discount){
            					div = "<div class='fontdel'>订单折扣："+discount+"</div>"+div;
            				}
            				return div;
            			}else{
            				if(rData.isDeal=='Y'){
            					return "<div id='discountrate_"+rData.id+"' value='"+discount+"'>"+discount+"</div>";
            				}else{Number(rData.availableDiscountrate).toFixed(2)
            					//存在暂存
                				if(rData.responseStatus==0){
                					return "<div class='JGtab'><input type='text' value='"+Number(rData.availableDiscountrate).toFixed(2)+"' class='b80 inputWidth' id='discountrate_"+rData.id+"'></div>";
                				}else{
                					return "<div class='JGtab'><input type='text' value='"+discount+"' class='b80 inputWidth' id='discountrate_"+rData.id+"'></div>";
                				}
            					
            				}
            			}
            		}
            		//回告暂存
            		if(rData.availableDiscountrate!=null && rData.availableDiscountrate!=""){
            			var availableDiscountrate = Number(rData.availableDiscountrate).toFixed(2);
            			var div = "<div class='JGtab'><input type='text' value='"+availableDiscountrate+"' class='b80 inputWidth' id='discountrate_"+rData.id+"'></div>";
            			if(value!=rData.availableDiscountrate){
            				div = "<div class='fontdel'>订单折扣："+discount+"</div>"+div;
            			}
            			return div;
            		}else{
            			if(rData.isDeal=='Y'){
            				return "<div id='discountrate_"+rData.id+"' value='"+discount+"'>"+discount+"</div>";
            			}else{
            				return "<div class='JGtab'><input type='text' value='"+discount+"' class='b80 inputWidth' id='discountrate_"+rData.id+"'></div>";
            			}
            		}
            	}
            },
            {name:"orderQty", index:"订 数",width:'4%',align:'center',sortable:false},
            {name:"sendQtyTemp", index:"发货数暂存",width:'4%',align:'center',hidden:true,
            	formatter:function(value,options,rData){
            		if(rData.isDeal=='N'){
            			var sendQtyTemp = rData.orderQty - rData.sendoutQty;
            			if(rData.responseStatus==0){
            				sendQtyTemp = rData.thisSendQty;
            			}
            			return "<input type='text' value='"+sendQtyTemp+"' class='b80 inputWidth' id='sendQtyTemp_"+rData.id+"'>";
            		}else{
            			return "";
            		}
            	}
            },
            {name:"sendQty", index:"本次发货数",width:'6%',align:'center',sortable:false,
            	formatter:function(value,options,rData){
            		if(rData.isDeal=='N'){
            			var sendQty = rData.orderQty - rData.sendoutQty;
            			if(rData.responseStatus==0){
            				sendQty = rData.thisSendQty;
            			}
            			return "<div class='JGtab'><input type='text' value='"+sendQty+"' class='b80 inputWidth' id='sendQty_"+rData.id+"'></div>";
            		}else{
            			return "";
            		}
            	}
            	
            },
            {name:"reasonValue", index:"其余满足情况",width:'8%',align:'center',hidden:true,
            	formatter:function(value,options,rData){
            		var reasonValue = rData.availableReason == null ?'':rData.availableReason,
            			responseStatus = rData.responseStatus==null?'':rData.responseStatus;
            		var html = "<input type='text' value='"+responseStatus+"' class='b80 inputWidth' id='responseStatus_"+rData.id+"'>" +
            				"<input type='text' value='"+reasonValue+"' class='b80 inputWidth' id='reasonValue_"+rData.id+"'>"
            		return html;
            	}
            },
            {name:"availableReason", index:"其余满足情况",width:'8%',align:'center',sortable:false,
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
            		
            	}
            },
            {name:"preSendDate", index:"其余预计发货日期",width:'9%',align:'center',sortable:false,
            	formatter:function(value,options,rData){
            		var html="";
            		if(rData.historyPreSendDate!=null && rData.historyPreSendDate !=''){
            			var historyPreSendDate = rData.historyPreSendDate.substr(0,10);
        				html +="<div>"+historyPreSendDate+"</div>"
            		}
            		value = value==null ? '':value.substr(0,10);
            		if(rData.isDeal=='Y'){
            			html +="<div>"+value+"</div>"
    				}else{
//    					html +="<div class='JGtab'><input type='text' value='"+value+"' class='b80' id='preSendDate_"+rData.id+"'></div>";
    					html +="<input type='text' class='SetTiemBox timeIcon' value='"+value+"' id='preSendDate_"+rData.id+"'>";
    				}
    				return html;
            	}
            },
            {name:"responseRemark", index:"备 注",width:'10%',align:'center',sortable:false,
            	formatter:function(value,options,rData){
            		var html="";
            		if(rData.historyResponseRemark!=null && rData.historyResponseRemark !=''){
        				html +="<div>"+rData.historyResponseRemark+"</div>"
            		}
            		value = value==null ? '':value;
            		if(rData.isDeal=='Y'){
    					html +="<div>"+value+"</div>"
    				}else{
    					html +="<div class='JGtab'><input type='text' value='"+value+"' class='b80 inputWidth' id='responseRemark_"+rData.id+"'></div>";
    				}
            		return html;
            	}
            },
            {name:"sendoutQty", index:"已发数",width:'4%',align:'center',sortable:false},
            {name:"receiveQty", index:"已收数",width:'4%',align:'center',sortable:false},
            {name:"fhl", index:"发货率（%）",width:'6%',align:'center',sortable:false,
            	formatter:function(value,options,rData){
            		var orderQty = parseInt(rData.orderQty);
            		var sendoutQty = parseInt(rData.sendoutQty);
            		var fhl = sendoutQty/orderQty*100;
            		return fhl.toFixed(2);
            	}
            },
            {name:"isDealStr", index:"品种处理情况",width:'8%',align:'center',hidden:true,
            	formatter:function(value,options,rData){
            		return "<input type='text' value='"+rData.isDeal+"' class='b80 inputWidth' id='isDealStr_"+rData.id+"'>";
            	}
            },
            {name:"isDeal", index:"品种处理情况",width:'8%',align:'center',sortable:false,
            	formatter:function(value,options,rData){
            		if(value=='Y'){
            			if(rData.isValid=='N'){
            				return "<div id='isDeal_"+rData.id+"'>已处理<br/>(采购关闭)<div>"
            			}else if(rData.isValid=='X'){
            				return "<div id='isDeal_"+rData.id+"'>已处理<br/>(供商关闭)<div>"
            			}
            			return "<div id='isDeal_"+rData.id+"' value='Y'>已处理<div>";
            		}else if(value=='N'){
            			return "<div id='isDeal_"+rData.id+"'>待处理<div>";
            		}else{
            			return "";
            		}
            	}
            },
            {name:"responseHistory", index:"回告历史",width:'6%',align:'center',sortable:false,
            	formatter:function(value,options,rData){
            		if(rData.sendoutQty>0
            				||(rData.historyAvailablePrice !=null && rData.historyAvailablePrice !="")){
            			return "<a hrep='javascript:;' onclick='queryResponse("+rData.id+");' class='line' data-toggle='modal'>查 看</a>";
            		}else{
            			return "";
            		}
            	}
            	
            }
        ],// the column discription
        postData:{query_id:"queryPendingOrderItemsByPage",query_type:"JQGRID",rdParseType:"dispersed",reqData:queryData()} , 
        rowNum:200,
        rowList:[100,200,300,500],
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
        onPaging : function(pgButton){
        	//重置合计
        	Initialization();
			$("#table").jqGrid('setGridParam',{
				datatype:'json',
				postData:{
					query_id:"queryPendingOrderItemsByPage",
					query_type:"JQGRID",
					reqData:queryData()
					}
			});
		},
		onSelectRow:function (id,status){
//			var rowData= $("#table").jqGrid("getRowData",id);
			//选中
			if(status){
				totalVariety = totalVariety+1;
				sendQty = $(this).find("#sendQty_"+id).val();
				if(sendQty!=""&& sendQty!=null){
					price = $(this).find("#price_"+id).attr("value");
					discountrate = $(this).find("#discountrate_"+id).attr("value");
					totalSendQty += parseInt(sendQty);
					totalSendPrice += parseInt(sendQty) * Number(price);
					totalSendRealPrc += parseInt(sendQty) * Number(price) * Number(discountrate)/100;
				}
			}else{//取消
				totalVariety = totalVariety - 1;
				if(totalVariety==0){
					Initialization();
				}
				sendQty = $(this).find("#sendQty_"+id).val();
				if(sendQty!="" && sendQty!=null && totalSendQty>0){
					price = $(this).find("#price_"+id).attr("value");
					discountrate = $(this).find("#discountrate_"+id).attr("value");
					totalSendQty -= parseInt(sendQty);
					totalSendPrice -= parseInt(sendQty) * Number(price);
					totalSendRealPrc -= parseInt(sendQty) * Number(price) * Number(discountrate)/100 ;
				}
			}
			//设置
			InitTotal();
			//判断是否可进行保存
//			setDisabled();
			
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
						price = $this.find("#price_"+n).attr("value");
						discountrate = $this.find("#discountrate_"+n).attr("value");
						totalSendQty += parseInt(sendQty);
						totalSendPrice += parseInt(sendQty) * Number(price);
						totalSendRealPrc += parseInt(sendQty) * Number(price)*Number(discountrate)/100;
					}
	    		});
	    		//设置
	    		InitTotal();
	    	}else{
	    		Initialization();
	    	}
			//判断是否可进行保存
//			setDisabled();
		},
		loadComplete:function(){
			//表单修改状态
			window.FORM_CHANGED = false;//初始状态为未修改
			window.isSave = true; //初始状态为为保存
			window.onbeforeunload = function(event){ 
				//已修改并且未保存，则提示
				if(window.FORM_CHANGED){
					if(window.isSave){
						return '您有数据未保存!'; 
					}
				}
			};
			$(document).on('change','textarea,input[type="text"],select',function(){
						window.FORM_CHANGED = true;
					});
			
			Initialization();
			$("select[name='bmz']").each(function(){
				   var selectVal = $(this).attr("selectVal");
				   	   selectVal = (selectVal == null || selectVal == '')?1:selectVal;
				  $(this).select({
					  	localdata:bmz_yz,
	 					selected: selectVal //默认选中的值
	 				});
			 });
            WG.JGscrollX.Event($(this),'GdBox');
            //获取ids
            var arr=$(this).jqGrid('getDataIDs');
            //是否全 部已处理
            for(var j=0;j<arr.length;j++){
            	//初始化状态
            	var id= arr[j];
            	var isDealStr = $("#table").find("#isDealStr_"+id).val();
            	if(isDealStr=='N'){
            		isDealFlag = false;
            	}
            	//表格数据初始化相关设置
            	setData(id);
            	//勾选
            	$("#table").jqGrid('setSelection',id);
            	
            	/**
            	 * 定价改变事件
            	 */
            	$('#price_'+arr[j]+'').keyup(function(){
            		var id = $(this).attr("id").substring($(this).attr("id").indexOf("_")+1);
            		var priceTemp = $("#table").find("#priceTemp_"+id).val();
            		var reg = /^\d{0,8}\.{0,1}(\d{1,2})?$/;
            		if (!reg.test($(this).val()) && $(this).val() != '') {
            			layer.msg("请正确填写定价!",{icon : 0,time:2000});
            			$(this).val(priceTemp);
        				return;
            		}
            		if($("#table").find("#"+id).attr("aria-selected")=="true"){
            			var priceTemp = $("#table").find("#priceTemp_"+id).attr("value");
            			var amount = $("#table").find("#sendQty_"+id).val();
            			var price = $(this).val();
            			var finalPrice = Number(price)-Number(priceTemp);
						discountrate =$("#table").find("#discountrate_"+id).attr("value");
						totalSendPrice += Number(amount) * Number(finalPrice);
						totalSendRealPrc += Number(amount) * Number(finalPrice) * Number(discountrate)/100 ;
						//设置
						InitTotal();
            		}
            		$("#table").find("#priceTemp_"+id).val($(this).val());
            	});
            	
            	/**
            	 * 折扣改变事件
            	 */
            	$('#discountrate_'+arr[j]+'').keyup(function(){
            		var id = $(this).attr("id").substring($(this).attr("id").indexOf("_")+1);
            		var discountrateTemp = $("#table").find("#discountrateTemp_"+id).val();
            		var reg = /^(0|[1-9]|[1-9]\d|100)(\.\d{1,2}|\.{0})$/;
            		if (!reg.test($(this).val()) && $(this).val() != '') {
            			layer.msg("请正确填写折扣!",{icon : 0,time:2000});
            			$(this).val(discountrateTemp);
        				return;
            		}
            		if($("#table").find("#"+id).attr("aria-selected")=="true"){
            			var price = $("#table").find("#price_"+id).attr("value");
            			var amount = $("#table").find("#sendQty_"+id).val();
						discountrate =$(this).val();
						var discountrateTemp = $("#table").find("#discountrateTemp_"+id).attr("value");
            			var finalDiscountrate = Number(discountrate)-Number(discountrateTemp);
//						totalSendPrice += parseInt(amount) * Number(price);
						totalSendRealPrc += Number(amount) * Number(price) * Number(finalDiscountrate)/100 ;
						//设置
						InitTotal();
            		}
            		$("#table").find("#discountrateTemp_"+id).val($(this).val());
            	});
            	
            	/**
            	 * 订数改变事件
            	 */ 
            	$('#sendQty_'+arr[j]+'').keyup(function(){
            		var id = $(this).attr("id").substring($(this).attr("id").indexOf("_")+1);
            		var amount=$(this).val();
            		var sendQtyTemp = $("#table").find("#sendQtyTemp_"+id).val();
            		var reg = /^(0|([1-9]{1}[0-9]{0,7}))$/;
					if (!reg.test(amount) && amount != '') {
						if(amount==0){
							layer.msg("本次发货数只能输入最多首位不为 0 的 8 位整数!",{icon : 0,time:2000});
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
					
					var isdisabled = false;
					//本次发货数>=订数-已发数，“其余满足情况”置为空，不可编辑
					if(amount>maxUnsendoutQty){
//						layer.msg("本次发货数不能超过未发数的130%!",{icon : 0,time:1800});
						setState(id,true,1);
						$("#table").find("#availableReason_"+id).attr("value", '');
						$("#table").find("#availableReason_"+id).attr("disabled", true);
						$("#table").find("#preSendDate_"+id).html('');
						isdisabled = true;
					}else if(amount>=unsendoutQty){
						$("#table").find("#availableReason_"+id).attr("value", '');
						$("#table").find("#availableReason_"+id).attr("disabled", true);
						$("#table").find("#preSendDate_"+id).html('');
					}else if(0<=amount && amount<unsendoutQty){//0<=本次发货数<订数-已发数，自动默认“其余满足情况=暂时缺货”，可修改
						$("#table").find("#availableReason_"+id).attr("disabled", false);
						$("#table").find("#availableReason_"+id).attr("value", 1);
					}
					
					//本次发货数改变关联事件
					if($("#table").find("#"+id).attr("aria-selected")=="true"){//勾选需要改变合计
						var totalAmount = amount-sendQtyTemp;
						price = $("#table").find("#price_"+id).attr("value");
						discountrate =$("#table").find("#discountrate_"+id).attr("value");
						totalSendQty += parseInt(totalAmount);
						totalSendPrice += parseInt(totalAmount) * Number(price);
						totalSendRealPrc += parseInt(totalAmount) * Number(price) * Number(discountrate)/100 ;
						//设置
						InitTotal();
					}
					
					//重新设置暂存发货数
					$("#table").find("#sendQtyTemp_"+id).val(amount);
					if(!isdisabled){
						//若存在背景色，则重置并重置保存和确认按钮状态
						setState(id,false,1);
					}
					
            	});
            	
            	/**
            	 * 其余满足情况改变事件
            	 */
            	$('#availableReason_'+arr[j]+'').change(function(){
            		var id = $(this).attr("id").substring($(this).attr("id").indexOf("_")+1);
            		var optionVal = $(this).val();
            		var amount = $("#table").find("#sendQty_"+id).val();
        			var rowData = $("#table").jqGrid("getRowData",id);
					var unsendoutQty = parseInt(rowData.orderQty)- parseInt(rowData.sendoutQty);
            		if(optionVal!=null && optionVal!=""){
            			//其余满足情况=预计可发：其余预计发货日期必填，否则标红该行，不允许保存；
            			if(parseInt(optionVal)==0){
            				var preSendDate =$("#table").find("#preSendDate_"+id).html();
            				if(amount!='' && amount>=0
        							&& amount<unsendoutQty){
            					if(preSendDate=="" || preSendDate==null){
                					setState(id,true,1);
                				}else{
                					setState(id,false,1);
                				}
            				}
            				if(amount>=unsendoutQty){
            					setState(id,true,1);
            				}
            			}else{
            				setState(id,false,1);
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
            	
            	/**
            	 * 预计发货日期点击事件
            	 */
            	$('#preSendDate_'+arr[j]+'').click(function(){
            		var id = $(this).attr("id").substring($(this).attr("id").indexOf("_")+1);
            		var preSendDate = $(this).attr("text");
            		//日期控件添加监听事件
            		WdatePicker(
            				{onpicking:function(dp){//确定
            					var reson = $("#table").find("#availableReason_"+id).val();
            					if(parseInt(reson)==0){
            						setState(id,false,1);
            						$(this).blur();
            					}
            						
            					},
            				onclearing:function(){//清空
            					var reson = $("#table").find("#availableReason_"+id).val();
            					if(parseInt(reson)==0){
            						setState(id,true,1);
            					}
            					}
            				}
            		);
            		
            	});
            }
            //默认勾选全部
//            $("#table").find(".cbox").trigger("click").prop('checked', true);
            $("#cb_table").prop('checked', true);
        },
		
    });
	jQuery("#table").jqGrid('navGrid','#page',{edit:false,add:false,del:false,search:false});

	/**
	 * 判断是否可以进行保存发货确认
	 */
	function setDisabled(){
		//判断是否可进行保存
		var ids = $("#table").jqGrid("getGridParam","selarrrow");
		var isDeal = $("input[name='isDeal']:checked").val();
		if(isDeal=='Y' || ids.length==0){
			isDealFlag = true;
		}else{
			for(var i=0;i<ids.length;i++){
				var isDealStr = $("#isDealStr_"+ids[i]).val();
				if(isDealStr=='N'){
					isDealFlag = false;
					break;
				}else{
					isDealFlag = true;
				}
			}
		}
		booleanDisabled();
	}
	
	/**
	 * 加载表格时初始化设置
	 */
	function setData(id){
//		var sendQtyTemp = $("#table").find("#sendQtyTemp_"+id).val();
		var rowData = $("#table").jqGrid("getRowData",id);
		//暂存加载
		var reasonValue = $("#table").find("#reasonValue_"+id).attr("value"),
		//是否为暂存
		responseStatus = $("#table").find("#responseStatus_"+id).attr("value");
		//设置临时定价和临时折扣
		var price = $("#table").find("#price_"+id).attr("value"),
		discountrate = $("#table").find("#discountrate_"+id).attr("value");
		$("#table").find("#priceTemp_"+id).attr("value", price);
		$("#table").find("#discountrateTemp_"+id).attr("value", discountrate);
		
		//收货数大于已发数背景标蓝
        var sendoutQty = rowData.sendoutQty,
        receiveQty = rowData.receiveQty;
        if(sendoutQty!=null && sendoutQty !=""
       	 && receiveQty!=null && receiveQty!=""
       	 && sendoutQty<receiveQty){
       	 $("#table").find("#"+id+ " td").css("background-color","#d0e8fe");
        }
		if(reasonValue!=null && reasonValue!=''){
			$("#table").find("#availableReason_"+id).attr("value", reasonValue);
			$("#table").find("#availableReason_"+id).attr("disabled", false);
			//不为暂存的其余满足原因的才是回告过永久缺货
			if(responseStatus == null || responseStatus == ''){
				$("#table").find("#sendQty_"+id).val(0);
				$("#table").find("#sendQtyTemp_"+id).val(0);
				return;
			}
		}
		//判断发货数与订数-已发数关系
		var unsendoutQty = parseInt(rowData.orderQty)- parseInt(rowData.sendoutQty),
		amount=$("#table").find("#sendQty_"+id).val();
		if(amount>=unsendoutQty){
			$("#table").find("#availableReason_"+id).attr("value", '');
			$("#table").find("#preSendDate"+id).html('');
			$("#table").find("#availableReason_"+id).attr("disabled", true);
		}else if(amount>=0 && amount<unsendoutQty){//0<=本次发货数<订数-已发数，自动默认“其余满足情况=暂时缺货”，可修改
			$("#table").find("#availableReason_"+id).attr("disabled", false);
			if(reasonValue!=null && reasonValue!=''){
				$("#table").find("#availableReason_"+id).attr("value", reasonValue);
			}else{
				$("#table").find("#availableReason_"+id).attr("value", 1);
			}
		}
		
	}
  
    
    
    //总页面上的回告发货确认按钮
    $("#sendGoods").bind("click",function(){
    	var ids = new Array();
    	ids = $("#table").jqGrid("getGridParam","selarrrow");
    	if(ids.length==0){
    		layer.alert("请先勾选待处理品种，再进行【确认】操作!", {icon : 0});
    		return;
    	}else{
    		//判断是否存在错误数据
    		var isSend = booleanDisabled(ids);
    		if(!isSend){
        		return;
    		}
    		//发货控制需求检查
    		var result = checkData(ids);
    		var msg;
    		if(result==1){
    			msg = "馆配订单，有品种不能全部发货，是否继续?";
    		}else if(result==2){
    			msg = "大中专、团购、活动订书，有品种不能全部发货，是否继续?";
    		}else if(result==3){
    			layer.alert("已勾选商品未填写本次发货数!", {icon : 0});
        		return;
    		}
    		if(msg!=null && msg!=''){
    			layer.confirm(msg, {
       			 btn: ['是','否'], //按钮
   	     		 icon:3,
   	     		 shade: false //不显示遮罩
   	  			}, function(index){
   	  				layer.close(index);
   	  				sendoutGoods(ids);
   	  			});
    		}else{
    			sendoutGoods(ids);
    		}
    		
    	}
    	
//    	openSendGoodsIframe("333,3334,444");
    });
    
    /**
     * 确认保存
     */
    function sendoutGoods(ids){
    	var saveSend = [];
    	var msg="";
    	var closeIds="";
    	//处理数据
		for(var i=0; i< ids.length; i++){
			var id = ids[i];
			var isDealStr = $("#table").find("#isDealStr_"+id).val();
			//已处理的不计算
			if(isDealStr=='Y') continue;
			
			var availablePrice = $("#table").find("#price_"+id).attr("value"),
			availableDiscountrate = $("#table").find("#discountrate_"+id).val(),
			thisSendQty = $("#table").find("#sendQty_"+id).val(),
			otherAvailableReason = $("#table").find("#availableReason_"+id).val(),
			preSendDate = $("#table").find("#preSendDate_"+id).html(),
			responseRemark = $("#table").find("#responseRemark_"+id).val(),
			purchaserId = $("#purchaserId").val();
			
			if(otherAvailableReason!=null && otherAvailableReason!=''
				&& forEverStockoutReason(otherAvailableReason)){
				msg ="回告永久缺货（改版、已停产、版权到期、商品无效、销售受限、无货不发）的商品将自动关闭，除本次发货数外，其余未发数将无法再发货!仍然要继续吗?"
			}
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
		//保存开始
		if(msg!=''){
			layer.confirm(msg, {
				 btn: ['是','否'], //按钮
	     		 icon:3,
	     		 shade: false //不显示遮罩
	  		}, function(index){
	  			layer.close(index);
	  			WG.loading.show();
	  			$.ajax({ 
					type: "post",
					url: appPath+"/backto/order/json/saveResponse", 
					data: {"saveSend" : JSON.stringify(saveSend),"type":0},//type=0标识回告发货，若是回告暂存就不需要传改参数
					dataType: "json", 
					success: function(json){
						WG.loading.hide();
						if(json.success){
							//打开发货界面	
							openSendGoodsIframe(json.obj);
						}else{
							WG.loading.hide();
							layer.msg(json.msg,{icon:2},function(){
								//刷新页面
								search();
							});
						}
					},error : function() {
						WG.loading.hide();
						layer.msg("保存异常!",{icon : 0,time:2500});
					}
	  			});
	  			
	  		});
		}else{
			WG.loading.show();
  			$.ajax({ 
				type: "post",
				url: appPath+"/backto/order/json/saveResponse", 
				data: {"saveSend" : JSON.stringify(saveSend),"type":0},//type=0标识回告发货，若是回告暂存就不需要传改参数
				dataType: "json", 
				success: function(json){
					WG.loading.hide();
					if(json.success){
						//打开发货界面	
						openSendGoodsIframe(json.obj);
					}else{
						WG.loading.hide();
						layer.msg(json.msg,{icon:2},function(){
							//刷新页面
							search();
						});
					}
				},error : function() {
					WG.loading.hide();
					layer.msg("保存异常!",{icon : 0,time:2500});
				}
  			});
		}
		
    }
    
    /**
     * 发货控制检查
     * 
     */
    function checkData(ids){
    	var orderType = $("#orderType").val();
    	var falg = false,
    	result = 0;
    	/**
    	 * 勾选品种中，任意品种的本次发货数>0， 
    	*	a、存在品种所属订单的订单种类=馆配订单；
    	*	系统检查该馆配订单的所有品种（已处理品种除外）：
		*	是否“本次发货数>=订数”；如果任一品种的“本次发货数<订数”，则需做出提示：
		*	馆配订单，有品种不能全部发货，是否继续？
		*	选择是，可以继续发货；
		*	选择否，取消；
    	*/
    	if(orderType==25){
    		for(var i=0; i< ids.length; i++){
    			var thisSendQty = $("#table").find("#sendQty_"+ids[i]).val();
    			if(thisSendQty>0){
    				falg = true;
    				result = 0;
    				break;
    			}
    			
    			if(thisSendQty==null || thisSendQty == ''){
    				falg = true;
    				result = 3;
    			}
    		}
    		
    		//检查该订单的所有品种
    		if(falg){
    			var rowIds = new Array();
    			rowIds = $("#table").jqGrid('getDataIDs');
    			for(var j=0; j< ids.length; j++){
    				var sendQty = $("#table").find("#sendQty_"+ids[j]).val();
    				var isDealStr = $("#table").find("#isDealStr_"+ids[j]).val();
    				if(isDealStr=='Y') continue
    				//获取本次发货数、订数
    				var rowData = $("#table").jqGrid("getRowData",ids[j]),
    				orderQty = rowData.orderQty;
    				//不满足
    				if(sendQty!=null && sendQty!='' && Number(sendQty)<orderQty){
    					falg = false;
    					break;
    				}
    			}
    		}
    		if(!falg){
    			result = 1;//馆配订单不满足
    		}
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
    	else if(orderType==15
    			||orderType==20
    			||orderType==30){
    		for(var i=0; i< ids.length; i++){
    			var thisSendQty = $("#table").find("#sendQty_"+ids[i]).val();
    			if(thisSendQty>0){
    				falg = true;
    				result=0;
    				break;
    			}
    			if(thisSendQty==null || thisSendQty == ''){
    				falg = true;
    				result = 3;
    			}
    		}
    		//检查该订单的所有【本次发货数】>0的品种
    		if(falg){
    			var rowIds = new Array();
    			rowIds = $("#table").jqGrid('getDataIDs');
    			for(var j=0; j< ids.length; j++){
    				var isDealStr = $("#table").find("#isDealStr_"+ids[j]).val();
    				var sendQty = $("#table").find("#sendQty_"+ids[j]).val();
    				if(isDealStr=='Y' || Number(sendQty)==0) continue;
    				//获取本次发货数、订数
    				var rowData = $("#table").jqGrid("getRowData",ids[j]),
    				orderQty = rowData.orderQty;
    				if(sendQty!=null && sendQty!='' 
    					&& Number(sendQty)<orderQty){
    					falg = false;
    					break;
    				}
    			}
    		}
    		if(!falg){
    			result = 2;//大中专、团购、活动订书订单不满足
    		}
    	}else{
    		for(var i=0; i< ids.length; i++){
    			var isDealStr = $("#table").find("#isDealStr_"+ids[j]).val();
    			if(isDealStr=='Y') continue;
    			result = 3;
    			var thisSendQty = $("#table").find("#sendQty_"+ids[i]).val();
    			if(thisSendQty!=null && thisSendQty!=''
    				&& thisSendQty>=0){
    				result = 0;
    				break;
    			}
    		}
    	}
    	return result;
    }
    
    
    /**
     * 弹出发货确认详情页面
     * 填写发货详细信息
     * @param ids 回告细目id集合，多个用英文字符逗号分隔
     * 2016年7月14日16:26:22
     */
    function openSendGoodsIframe(ids){
    	if(ids==null || ids==""){
    		layer.msg("回告成功!",{icon:1},function(){
				//刷新页面
				search();
			});
    	}else{
    		var url=appPath+"/backto/sendGoods/page/sendGoodsProcessing?reportIds="+ids+"&purchaserId="+$("#purchaserId").val();
        	$("#sendGoodsViewIFrame").attr("src",url);
        	diabox=dialog({
    	        title: '发货信息',
    	        fixed: true,
    	        content:$("#myModal1"),
    	        //关闭窗口判断提示
    	        cancel: function () {
    	        	if(window.POPUP_CHANGED){
    	        		dialog({
        	    	        title: '温馨提示',
        	    	        fixed: true,
        	    	        content:"有数据未保存！确定关闭？",
        	    	        okValue:'确定',
        	    	        ok: function () {
        	    	        	diabox.close().remove();
        	    	        },
        	    	        cancelValue: '取消',
        	    	        cancel: function () {
        	    	        },
        	    	    }).showModal();
        	        	return false;
    	        	}
    	        },
    	        cancelDisplay: false
    	    }).showModal();
        	//为弹窗的表单加上事件
        	window.POPUP_CHANGED = false;
        	$("#sendGoodsViewIFrame").on("load",function(){
            	$(window.frames["sendGoodsViewIFrame"].document).off("change input").on('change input','input,select',function(){            		
        			window.POPUP_CHANGED = true;
            	})        		
        	});
    	}
    	
    }
    
    //取消发货按钮
    $(".closeSendGoods").bind("click",function(){
    	$("#sendGoodsViewIFrame").attr("src",null);
    	$("#myModal1").modal("hide");
    	if($("#myModal3")){
    		$("#myModal3").remove();
    	}
    });
    
    //下载标识和处理情况change事件
    $("input[name='isDeal'],input[name='isExport']").change(function(){
    	/**
    	 * 为解决在ie模式下checkbox的change事件执行比获取勾选框值慢,
    	 * 导致出现先获取被勾选框值再执行勾选，造成查询条件获取不正确
    	 * 所以加上一个定时器。
    	 */
    	setTimeout(function(){
    		search();
    	},500);
    });
    
    //查询
    function search(){
    	var re = queryData();
 		jQuery("#table").jqGrid('setGridParam',{datatype:'json',page:1,postData:{query_type:"JQGRID",reqData:re}}).trigger("reloadGrid");
 		Initialization();
    }
    
    /**
     * 合计
     */
    function InitTotal(){
		//设置
    	$("#totalVariety").text(totalVariety);
		$("#totalSendQty").text(totalSendQty);
		$("#totalSendPrice").text(totalSendPrice.toFixed(2));
		$("#totalSendRealPrc").text(totalSendRealPrc.toFixed(2));
    }
    
    //批量填写
    $("#batchInput").bind("click",function(){
    	var ids = $("#table").jqGrid("getGridParam","selarrrow");
    	if(ids.length==0){
    		layer.alert("请先勾选商品，再进行【批量录入】操作!", {icon : 0});
    		return;
    	}
    	//判断是否存在错误数据
		var isSend = booleanDisabled(ids);
		if(!isSend){
    		return;
		}
		//设置需要填写的id
		$("#batchInputForm").find("#batchIds").val(ids);
		dialog({
	        title: '批量录入',
	        fixed: true,
	        content:$("#myModal"),
	        okValue: '确定',
	        ok:function(){
	        	//批量填写判断开始
	        	var $this =  $("#batchInputForm");
	        	//改为使用jquery的$.trim()方法
	        	batchDiscount = $.trim($this.find("#batchDiscount").val()),
	        	batchSendQty = $.trim($this.find("#batchSendQty").val()),
	        	batchAvailableReason = $this.find("#batchAvailableReason").val(),//其余满足原因
	        	batchSendDate = $this.find("#start").val(),//预计发货日期
	        	batchRemark =$.trim($this.find("#batchRemark").val());
	        	//排空处理
	        	if((batchDiscount!=null && batchDiscount!="")
	        			|| (batchSendQty!=null && batchSendQty!="")
	        			|| (batchAvailableReason!=null && batchAvailableReason!="")
	        			|| (batchSendDate!=null && batchSendDate!="")
	        			|| (batchRemark!=null && batchRemark!="")){
	        		//检查折扣格式,需求是保留两位小数
	        		var reg = /^(0|[1-9]|[1-9]\d|100)(\.\d{1,2}|\.{0})$/;
	        		if (!reg.test(batchDiscount) && batchDiscount != '') {
	        			layer.msg("请正确填写折扣!",{icon : 0,time:2000});
	        			return false;
	        		}
	        		//检查本次发货数格式
	        		reg=/^(0|([1-9]{1}[0-9]{0,7}))$/;
	        		if (!reg.test(batchSendQty) && batchSendQty != '') {
	    				if(batchSendQty==0){
	    					layer.msg("本次发货数只能输入最多首位不为 0 的 8 位整数!",{icon : 0,time:2000});
	    					return false;
	    				}else{
	    					layer.msg("请正确填写本次发货数!",{icon : 0,time:2000});
	    					return false;
	    				}
	    			}
	        		
	        		//获取勾选
	        		var idArray = new Array();
	            	idArray = $("#table").jqGrid("getGridParam","selarrrow");
	            	
	            	//初始化合计
	            	Initialization();
	            	
	            	//设置
	            	for(var i=0; i<idArray.length; i++){
	            		var id = idArray[i];
	            		var thisSendQty = batchSendQty,
	            		thisAvailableReason = batchAvailableReason;
	            		thisPreSendDate = batchSendDate;
	            		thisDiscount = batchDiscount;
	            		//已处理的跳过
	            		var isDealStr = $("#table").find("#isDealStr_"+id).val();
	            		if(isDealStr=='Y') continue;
	            		/****数据检查部分****/
	            		//批量数据检验,不合逻辑品种进行标红
	            		var isAccesses = true;
	            		var rowData = $("#table").jqGrid("getRowData",id);
	            		var unsendoutQty = parseInt(rowData.orderQty)- parseInt(rowData.sendoutQty),
	    				maxUnsendoutQty = parseInt(rowData.orderQty) * 1.3- parseInt(rowData.sendoutQty);
	            		
	            		//数据处理，如果批量填写有数据的使用批量数据，若无则使用原有的（本次发货数、其余满足原因、预计发货日期）
	            		if(batchSendQty==null || batchSendQty==""){
	            			thisSendQty = $("#table").find("#sendQty_"+id).val();
	            		}
	            		if(batchAvailableReason==null || batchAvailableReason==""){
	            			thisAvailableReason = $("#table").find("#availableReason_"+id).val();
	            		}
	            		if(batchSendDate == null || batchSendDate == ""){
	            			thisPreSendDate = $("#table").find("#preSendDate_"+id).html();
	            		}
	            		if(batchDiscount==null || batchDiscount==''){
	            			thisDiscount = $("#table").find("#discountrate_"+id).val();
	            		}
	            		
	            		//先设置再判断
	            		/****赋值部分****/
	            		//折扣	
	            		$("#table").find("#discountrate_"+id).val(Number(thisDiscount).toFixed(2));
	            		//本次发货数
	            		$("#table").find("#sendQty_"+id).val(thisSendQty);
	            		$("#table").find("#sendQtyTemp_"+id).val(thisSendQty);
	            		//其余满足原因  update by luohoudong  放后面设置
	            		$("#table").find("#availableReason_"+id).val(thisAvailableReason); 
	            		//预计发货日期
	            		$("#table").find("#preSendDate_"+id).html(thisPreSendDate);
	            		//备注
	            		if(batchRemark!=null && batchRemark!=""){
	            			$("#table").find("#responseRemark_"+id).val(batchRemark);
	            		}
	            		
	            		/****合计部分****/
	            		//重新合计数量
	            		var price = $("#table").find("#price_"+id).attr("value");
	        			totalVariety += 1;
	        			totalSendQty += Number(thisSendQty);
	    				totalSendPrice += Number(thisSendQty) * Number(price);
	    				totalSendRealPrc +=Number(thisSendQty) * Number(price) * Number(discountrate)/100;
	    				InitTotal();
	    				//判断订数关系
	    				if(thisSendQty!=null && thisSendQty!=""){
	    					//1、本次发货数>130%订数-已发数
	    					if(thisSendQty>maxUnsendoutQty){
	    						isAccesses = false;
	    					}
	    					//2、本次发货数>=订数-已发数，但“其余满足情况”非空；
	    					if(thisSendQty>=unsendoutQty 
	    							&& thisAvailableReason != null
	    							&& thisAvailableReason != ''){
	    						thisAvailableReason="";
	    						$("#table").find("#availableReason_"+id).val(thisAvailableReason);
	    						$("#table").find("#availableReason_"+id).attr("disabled", true);
	    						//isAccesses = false;
	    					}
	    					//3、本次发货数<订数-已发数，但“其余满足情况”为空；
	    					if(thisSendQty<unsendoutQty && (thisAvailableReason == '' ||  thisAvailableReason == null)){
	    						isAccesses = false;
	    					}
	    				}
	    				//判断预计发货日期
	    				//4、其余满足情况=预计可发，但“其余预计发货日期”为空
	    				if(thisAvailableReason == "0" && (thisPreSendDate=='' || thisPreSendDate == null)){
	    					isAccesses = false;
	    				}
	    				
	    				
	    				if(thisAvailableReason!=null && thisAvailableReason!=''){
	    					$("#table").find("#availableReason_"+id).attr("disabled", false);
	    				}
	    				
	    				if(thisSendQty<unsendoutQty){
	    					$("#table").find("#availableReason_"+id).attr("disabled", false);
	    				}
	    				
	    				/****品种行项和按钮处理部分*****/
	            		//品种行项标红
	            		if(!isAccesses){
	            			setState(id,true,1);
	            		}else{
	            			setState(id,false,1);
	            		}
	            	}
	            	//弹框隐藏
	            	 batchClose();
	        	}else{
	        		layer.alert("请至少填写一项，再进行确认!", {icon : 0});
	        		return false;
	        	}
	        	//批量填写判断结束
	        },
	        cancelValue: '取消',
	        cancel: function () {}
	    }).showModal();
    });
    
    
    /**
     * 批量录入关闭/取消
     */
    $("#batchClose,#batchCanle").bind("click",function(){
    	 batchClose();
    });
    /**
     * 关闭弹框
     */
    function batchClose(){
    	//数据清空
    	var $this = $("#batchInputForm");
    	$this.find("#batchIds").val(''),//需要批量的记录id
    	$this.find("#batchDiscount").val(''),//折扣
    	$this.find("#batchSendQty").val(''),//本次发货数
    	$this.find("#batchAvailableReason").val(''),//其余满足原因
    	$this.find("#start").val(''),//预计发货日期
    	$this.find("#batchRemark").val('');//备注
    	
    	//隐藏
    	$("#myModal").modal("hide");
    }
   /**
    * 1/背景和按钮状态设置
    * 2/设置行项可编辑状态
    */
    function setState(id,flag,type){
    	if(type==1){
    		if(flag){
        		$("#table").find("#"+id+ " td").css("background-color","#ffdcdc");
        		var exist=false;//如果错误数据数组中已经存在,则不添加
        		//将错误数据加入数组中
        		for(var i=0;i<unSendArray.length;i++){
        			if(unSendArray[i]==id){
        				exist=true;
        			}
        		}
        		if(!exist){
        			unSendArray.push(id);
        		}
        	}else{
        		$("#table").find("#"+id+ " td").css("background-color","");
        		
        		for(var i=0;i<unSendArray.length;i++){
        			if(unSendArray[i]==id){
        				//将错误数据移除数组
        				unSendArray.splice(i, 1);
        				break;
        			}
        		}
        	}
    	}
//    	booleanDisabled();
    }
    
    /**
     * 判断是否有错误记录勾选
     */
    function booleanDisabled(ids){
    	var isSendGoods = false;
    	if(unSendArray.length==0){
    		isSendGoods = true;
    	}
    	//若有勾选错误数据
    	for(var i=0;i<unSendArray.length;i++){
    		if($("#table").find("#"+unSendArray[i]).attr("aria-selected")=="true"){
    			var id = unSendArray[i];
    			var thisSendQty = $("#table").find("#sendQty_"+id).val(),
    			availableReason = $("#table").find("#availableReason_"+id).val(),
    			preSendDate = $("#table").find("#preSendDate_"+id).html();
    			
    			var rowData = $("#table").jqGrid("getRowData",id);
    			var orderQty = rowData.orderQty,
    			sendQty = rowData.sendoutQty;
    			
    			var maxthisSend = orderQty*1.3-sendQty,
    			unSend = orderQty-sendQty;
    			//判断订数方面
    			if(thisSendQty!=null && thisSendQty!=''){
    				if(thisSendQty>maxthisSend){
    					layer.msg("累计发货数不允许超过订数的30%!", {icon : 0});
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
    	
    	//判断是否全部为已处理
    	if(ids!=null && ids.length>0){
    		var isDeal = true;
    		for(var j=0;j<ids.length;j++){
    			var isDealStr = $("#table").find("#isDealStr_"+ids[j]).val();
    			//已处理的不计算
    			if(isDealStr=='N') {
    				isDeal = false;
    				break;
    			}
    		}
    		
    		if(isDeal){
    			layer.msg("已处理的商品不能进行此操作!", {icon : 0});
    			isSendGoods = false;
    		}
    	}
    	return isSendGoods;
    }
    
  //返回按钮
    $("#goBack").bind("click",function(){
    	var requestType = $("#requestType").val();
    	var isDeal=$("#isDeal").val();
    	if(requestType=="summary"){
    		window.location.href=appPath+"/backto/order/page/toOrderProcessing";
    	}
    	if(requestType == "item"){
    		if(isDeal=="Y"){
        		window.location.href=appPath+"/backto/order/page/toOrderItems?isDeal=Y";
    		}else{
        		window.location.href=appPath+"/backto/order/page/toOrderItems?isDeal=N";

    		}
    	}
    });
    
  //关闭发货
    $("#closeSend").bind("click",function(){
    	//获取勾选id
    	var ids = new Array();
    	ids = $("#table").jqGrid("getGridParam","selarrrow");
    	if(ids.length==0){
    		layer.alert("请勾选商品!", {icon : 0});
    		return;
    	}else{
    		var disabled = booleanDisabled(ids);
    		if(!disabled){
    			return;
    		}
    		//排除已处理id
    		for(var i=0; i<ids.length; i++){
    			//获取当前品种处理状态
    			var isDeal = $("#table").find("#isDealStr_"+ids[i]).val();
    			if(isDeal=='Y'){
    				ids.splice(i, 1);
    				continue;
    			}
    		}
    		//设置需要填写的id
    		$("#notGoodsReasonForm").find("#notGoodsReasonIds").val(ids);
    		dialog({
		        title: '关闭发货',
		        fixed: true,
		        content:$("#myModalReason"),
		        okValue: '确定',
		        ok:function(){
		        	notGoodsReasonSave();
		        },
		        cancelValue: '取消',
		        cancel: function () {
		        	$("#notGoodsReasonForm").find("#notGoodsReasonIds").val("");
		        }
		    }).showModal();
    	}
    });
    
    /**
     * 关闭发货保存
     */
    function notGoodsReasonSave(){
    	var ids = $("#notGoodsReasonForm").find("#notGoodsReasonIds").val(),
    	notGoodsReason = $("#notGoodsReasonForm").find("#notGoodsReason").val(),
    	purchaserId = $("#purchaserId").val(),
    	purchaseOrderCode = $("#purchaseOrderCode").val();
//    	$("#myModalReason").modal("hide");
    	WG.loading.show();
    	//执行保存
    	$.ajax({
    		type : "post",
			url:appPath+'/backto/order/json/saveCloseSendOutGoods',
			data: {"ids" : ids,
				"notGoodsReason":notGoodsReason,
				"purchaserId":purchaserId,
				"purchaseOrderCode":purchaseOrderCode
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
    }
    
    /**
     * 保存
     */
    $("#save").bind("click",function(){
    	saveResponse();
    });
    /**
     * 回告保存
     */
    function saveResponse(){
    	var ids = new Array();
    	ids = $("#table").jqGrid("getGridParam","selarrrow");
    	if(ids.length==0){
    		layer.alert("请先勾选待处理商品，再进行【保存】操作!", {icon : 0});
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
    			var id = ids[i];
    			var isDealStr = $("#table").find("#isDealStr_"+id).val();
    			//已处理的不计算
    			if(isDealStr=='Y') continue;
    			
    			var availablePrice = $("#table").find("#price_"+id).attr("value"),
    			availableDiscountrate = $("#table").find("#discountrate_"+id).val(),
    			thisSendQty = $("#table").find("#sendQty_"+id).val(),
    			otherAvailableReason = $("#table").find("#availableReason_"+id).val(),
    			preSendDate = $("#table").find("#preSendDate_"+id).html(),
    			responseRemark = $("#table").find("#responseRemark_"+id).val(),
    			purchaserId = $("#purchaserId").val();
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
    		//保存开始
    		layer.confirm('是否确认保存?', {
    			 btn: ['确定','取消'], //按钮
	     		 icon:3,
	     		 shade: false //不显示遮罩
	  		}, function(index){
	  			layer.close(index);
	  			WG.loading.show();
	  			var getTimestamp=new Date().getTime();
	  			$.ajax({ 
					type: "post",
					url: appPath+"/backto/order/json/saveResponse?timestamp="+getTimestamp, 
					data: {"saveSend" : JSON.stringify(saveSend)},
					dataType: "json", 
					success: function(json){
						window.isSave = false;
						WG.loading.hide();
						if(json.success){
							layer.msg("保存成功",{icon:1},function(){
								//刷新表格
								search();
							});
							
						}else{
							WG.loading.hide();
							layer.msg(json.msg,{icon:2},function(){
								//刷新页面
								search();
							});
						}
					},error : function() {
						WG.loading.hide();
						layer.msg("保存异常!",{icon : 0,time:2500});
					}
	  			});
	  			
	  		});
    	}
    }
    
    

	
	// 修改导出状态函数(参数1：所选细目ids；参数2：刷新的方法名)
	function updateExportState(orderItemsIds,search){
		if('' != orderItemsIds){
			$.ajax({ 
				type:"post",
				url: appPath+'/backto/system/json/updateExportState',
				data: {"orderItemsIds":orderItemsIds},
				dataType: "json",
				success: function(data){
					if(data.success){
						layer.msg("更新导出状态成功！",{icon:1});
						//search();//更新成功都重新刷新列表页面
						var re = queryData();
				 		jQuery("#table").jqGrid('setGridParam',{datatype:'json',page:1,postData:{query_type:"JQGRID",reqData:re}}).trigger("reloadGrid");
				 		Initialization();
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
	function doExport(queryData,orderItemsIds,search){
		if(queryData!=""){
			$.ajax({ 
				type:"post",
				url: appPath+"/console/export/ajax/backtoOrderDetailExport/excel",
				dataType: "json",
				data:{fileName:name,pst:false,reqData:queryData},
				beforeSend : function(){
		        	WG.loading.show();
		        },
				// async: false,
				success: function(data){
					WG.loading.hide();
					if(data.success){
						layer.alert("导出文件完成！",{icon:1});
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
    
	//订单导出/下载
	$("#OrderExport").bind("click",function(){
		//$("#processedVarietiesExport").unbind("click").one("click",function(){
		var orderItemsIds = getSelectIds("table");
		if(orderItemsIds==""){
			return ;
		}
		
		//判断是否存在错误数据
		var ids = new Array;
		ids = orderItemsIds.split(",");
		var isSend = booleanDisabled(ids);
		if(!isSend){
    		return;
		}
		
		var queryData = "{'groupOp':'AND','rules':[";
		var receiveAddress = $("#receiveAddress").val();
		var purchaserId = $("#purchaserId").val();
		//采购商编码
	   	if(null != purchaserId && '' != purchaserId){
	   		var purchaserId = "{'field':'purchaserId','op':'eq','data':'"+ purchaserId +"'},";
	   		queryData = queryData.concat(purchaserId);
	   	}
		//收货地址
	   	if(null != receiveAddress && '' != receiveAddress){
	   		var receiveAddress = "{'field':'receiptAddress','op':'eq','data':'"+ receiveAddress +"'},";
	   		queryData = queryData.concat(receiveAddress);
	   	}
	   	//收货人
	   	var receiveContactUser = $("#receiveContactUser").val();
		if(null != receiveContactUser && '' != receiveContactUser){
	   		var receiveContactUser = "{'field':'consignee','op':'eq','data':'"+ receiveContactUser +"'},";
	   		queryData = queryData.concat(receiveContactUser);
	   	}
		//收货联系电话
	   	var receivePhoneno = $("#receivePhoneno").val();
		if(null != receivePhoneno && '' != receivePhoneno){
	   		var receivePhoneno = "{'field':'telephone','op':'eq','data':'"+ receivePhoneno +"'},";
	   		queryData = queryData.concat(receivePhoneno);
	   	}
		var timeDay = new Date();
	    var name = $("#purchaserName").val() + "_订单详情_"+ timeDay.getFullYear()+""+((timeDay.getMonth()+1)<10?"0"+(timeDay.getMonth()+1):(timeDay.getMonth()+1))+""+timeDay.getDate()+""+timeDay.getHours()+""+timeDay.getMinutes()+""+timeDay.getSeconds();
	    var a = "{'field':'excelName','op':'eq','data':'"+name+"'},";
		queryData = queryData.concat(a);
		//所选行ids
		var a = "{'field':'orderItemsIds','op':'eq','data':'"+orderItemsIds.toString()+"'}";
			queryData = queryData.concat(a);
		var f= "]}";
		queryData = queryData.concat(f);
		doExport(queryData,orderItemsIds,"search");//导出处理操作
	});	
});
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
//查询参数
function queryData(){
	var re="{'groupOp':'AND','rules':[";
	var purchaserId = $("#purchaserId").val();
	var purchaseOrderCode = $("#purchaseOrderCode").val();
//	var isDeal = $("input[name='isDeal']:checked").val();
	var isDeal = "";
	var  isDeal11 = $("input[name='isDeal']:checked");
	isDeal11.each(function(){
		isDeal +=$(this).val();
	});
	if(isDeal.length>=3){
		isDeal = "";
	}
	var isExport = $("input[name='isExport']:checked").val();
	if(null != purchaserId && '' != purchaserId){
		var a = "{'field':'purchaserId','op':'eq','data':'"+ purchaserId +"'},";
		re = re.concat(a);
	}
	if(null != purchaseOrderCode && '' != purchaseOrderCode){
		var a = "{'field':'purchaseOrderCode','op':'eq','data':'"+ purchaseOrderCode +"'},";
		re = re.concat(a);
	}
	if(null != isDeal && '' != isDeal){
		var a = "{'field':'isDeal','op':'eq','data':'"+ isDeal +"'},";
		re = re.concat(a);
	}
	if(null != isExport && '' != isExport){
		var a = "{'field':'isExport','op':'eq','data':'"+ isExport +"'},";
		re = re.concat(a);
	}
	//去掉最后一个，符号
	if(re.length > 30){
		re = re.substr(0,re.length-1);
	}
	var z= "]}";
	re = re.concat(z);
	return re;
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
 * 操作（回告、发货）后的刷新
 */
function searchOperation(){
	var re = queryData();
	jQuery("#table").jqGrid('setGridParam',{datatype:'json',postData:{query_type:"JQGRID",reqData:re}}).trigger("reloadGrid");
	Initialization();
}

function queryResponse(id){
	//查看历史
		$("#table_b").jqGrid({
	    	url: appPath+'/console/query',
	    	mtype:'post',
	        shrinkToFit: true, //列自适应
	        datatype: "local",
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
	        postData:{query_id:"queryResponseHistory",query_type:"JQGRID",rdParseType:"dispersed",reqData:queryHistoryData(id)} ,
	        rowNum:10,
	        rowList:[10,20,30],
	        pager: '#page_b',
	        width: 817,
	        rownumbers : true, // 显示左边排名列表
	        viewrecords:true, //显示总记录数
	        height:'100%',
	        jsonReader : {
	  		  repeatitems: false       //这里要设置为false，否则解析不了返回数据
	        }, 
	        loadBeforeSend:function(){
	            $(this).jqGrid('clearGridData');
	        },
	        loadComplete:function(){
	        }
	    });
		jQuery("#table_b").jqGrid('navGrid','#page_b',{edit:false,add:false,del:false,search:false});
 		jQuery("#table_b").jqGrid('setGridParam',{datatype:'json',page:1,postData:{query_type:"JQGRID",reqData:queryHistoryData(id)}}).trigger("reloadGrid");
 		dialog({
 	        title: '回告历史',
 	        fixed: true,
 	        content:$("#myModal2"),
 	        cancel: function () {},
 	        cancelDisplay: false
 	    }).showModal();
}
    
  
	/**
	 * 查看回告历史
	 */
	function queryHistoryData(id){
		var re="{'groupOp':'AND','rules':[";
		if(null != id && '' != id){
			var a = "{'field':'id','op':'eq','data':'"+ id +"'}";
			re = re.concat(a);
		}
		var z= "]}";
		re = re.concat(z);
		return re;
	}

	
	
	