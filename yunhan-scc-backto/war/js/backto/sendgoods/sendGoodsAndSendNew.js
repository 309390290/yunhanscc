/**
 * 订单品种发货处理--发货添加
 * wangtao
 * 2016年7月25日13:53:24
 */
/**
 * 加载添加商品列表
 */
function initMasterdataTab() {
	In.ready('jqGrid', function() {
		$("#product_list").jqGrid({
			url : appPath + '/console/query',
			// autowidth: true,
			width : 820,
			shrinkToFit : true,
			datatype : "json",
			mtype : "post",
			colNames : ["id","商品名称", "ISBN", "定 价", "折扣（%）" ],// the column header
			colModel : [ {
				name : "id",
				index : "id",
				hidden:true
			}, {
				name : "booktitle",
				index : "booktitle",
				width : '50%',
				align : 'center'
			}, {
				name : "isbn",
				index : "isbn",
				width : '30%',
				align : 'center'
			}, {
				name : "price",
				index : "price",
				width : '10%',
				align : 'center'
			}, {
				name : "discountrate",
				index : "discountrate",
				width : '10%',
				align : 'center'
			} ],// the column discription
			rowNum : 10,
			rowList : [ 5, 10, 15 ],
			multiselect : true, // 显示checkbox选择框
			rownumbers : true, // 显示左边排名列表
			viewrecords : true,
			height : '100%',
			sortable : false,
			pager : '#product_page',
			jsonReader : {
				repeatitems : false
			}, // 设置数据方式
			postData : {
				query_id : "findSupplierMasterDatasByPage",
				query_type : "JQGRID",
				rdParseType : "dispersed",
				reqData : queryMasterData()
			},
			height : '100%',
			/* 客户端排序--------------------------------------------------------开始 */
			loadBeforeSend : function() {
				$(this).jqGrid('clearGridData');
			},
			loadError : function() {
				layer.msg("系统报错！", {
					icon : 5
				});
			},
			onPaging : function(pgButton) {
				$(this).jqGrid('setGridParam', {
					datatype : 'json',
					postData : {
						reqData : queryMasterData(),
						_search1 : false
					}
				});
			},
			loadComplete : function(data) {
				
				 var _this=$(this);
				 width=_this.parents('.JGtab').eq(0).innerWidth();			
				 $(this).setGridWidth(width)
				 
				 
			}
		});
		
		$("#table_d").jqGrid({
            shrinkToFit: true, //列自适应
            datatype: "local",
            viewrecords:true, //显示总记录数
            colNames:["id","是否查询出的","是否供应商添加","商品名称","ISBN","发货价","发货折扣（%）","发货数"],// the column header names
            colModel :[
                {name:"id", index:"id",hidden:true},
                {name:"isQueryData", index:"isQueryData",hidden:true},
                {name:"isSupplierAddProduct", index:"isSupplierAddProduct",hidden:true},
                {name:"booktitle", index:"booktitle",width:'45%',align:'center',sortable:false,editable : false,editoptions : {style:"width:80%",size : 5},formatter : function(value, options, rData) {
				  var dataVal="<input style='width:80%'    value='"+value+"' id='" + rData.id + "_booktitle'/>";
				  if(rData.isQueryData) dataVal = "<span  date-type='span'  id='" + rData.id + "_booktitle'>"+value+"</span>";
				  return dataVal 
				  }
                },
                {name:"isbn", index:"isbn",width:'25%',align:'center',editable : false,sortable:false,editoptions : {style:"width:80%",size : 5},formatter : function(value, options, rData) {
				  var dataVal="<input style='width:80%'    value='"+value+"' id='" + rData.id + "_isbn'/>";
				  if(rData.isQueryData) dataVal = "<span  date-type='span'  id='" + rData.id + "_isbn'>"+value+"</span>";
				  return dataVal 
				  }
                },
                {name:"sendprice", index:"sendprice",width:'10%',align:'center',editable : false,sortable:false,editoptions : {style:"width:80%",size : 5},formatter : function(value, options, rData) {
				  var dataVal="<input style='width:80%'    value='"+value+"' id='" + rData.id + "_sendprice'/>";
				  return dataVal 
				  }
                },
                {name:"sendoutDiscountrate", index:"sendoutDiscountrate",width:'12%',align:'center',sortable:false,editable : false,editoptions : {style:"width:80%",size : 5},formatter : function(value, options, rData) {
				  var dataVal="<input style='width:80%'    value='"+value+"' id='" + rData.id + "_sendoutDiscountrate'/>";
				  return dataVal 
				  }
                },
                {name:"sendQty", index:"sendQty",width:'10%',align:'center',sortable:false,editable : false,editoptions : {style:"width:80%",size : 5},formatter : function(value, options, rData) {
				  var dataVal="<input style='width:80%'    value='"+value+"' id='" + rData.id + "_sendQty'/>";
				  return dataVal 
				  }
                }
            ],
            rowNum:9999,
            multiselect : true,  //显示checkbox选择框
           // pager: '#page_d',
            width: 820,
            height:'100%',
            loadComplete:function(){
            	var _this=$(this);
				 width=_this.parents('.JGtab').eq(0).innerWidth();			
				 $(this).setGridWidth(width)
                
            }
        });
		
	});
}

/**
 * 添加商品查询条件
 */
function queryMasterData() {
	// var supplierId=$("#supplierId").val();
	var supplierId = sapvendorId;
	var bookTitle = $("#query_bookTitle").val();
	var isbn = $("#query_isbn").val();

	var queryData = "{'groupOp':'AND','rules':[";
	var a = "{'field':'supplierid','op':'eq','data':'" + supplierId + "'},";
	queryData = queryData.concat(a);

	// 书名
	if (null != bookTitle && "" != bookTitle) {
		var temp = "{'field':'booktitle','op':'eq','data':'" + bookTitle
				+ "'},";
		queryData = queryData.concat(temp);
	}// 书名
	if (null != isbn && "" != isbn) {
		var temp = "{'field':'isbn','op':'eq','data':'" + isbn + "'},";
		queryData = queryData.concat(temp);
	}

	queryData = queryData.substring(0, queryData.length - 1);
	var z = "]}";
	queryData = queryData.concat(z);
	return queryData;
}

/**
 * 添加发货的查询
 */
function andSendSearch(){
	 $("#product_list").jqGrid('setGridParam', {
		datatype : 'json',
		page : 1,
		postData : {
			query_id : "findSupplierMasterDatasByPage",
			query_type : "JQGRID",
			reqData : queryMasterData()
		}
	}).trigger("reloadGrid");
}


/**
 * 添加发货到待确认框中
 */
function addProductToAddView(){
	var selectedIds = $("#product_list").jqGrid("getGridParam", "selarrrow");
	if (selectedIds.length == 0) {
		layer.alert("请选择至少一条数据！",{icon:0});
		return;
	}
	var data=new Array();
	
	for ( var i = 0; i < selectedIds.length; i++) {
			rowData=$("#product_list").jqGrid("getRowData",selectedIds[i]);
			var id=rowData.id;
			var isbn=rowData.isbn;
			var booktitle=rowData.booktitle;
			var price=rowData.price;
			var discountrate=rowData.discountrate;
			var sendOutqty='';
			var isQueryData=true;
			var rData={id:id,isbn:isbn,booktitle:booktitle,price:price,discount:discountrate,sendOutQty:sendOutqty,isQueryData:isQueryData};
			var isRepeatf=isRepeat(rData);
			
			if(isRepeatf){
				layer.alert("请勿重复添加！",{icon:0});
				return ;
			}
			data[i] =rData;
	}
	addRowFromQuery(data);
} 

//添加空数据行
var newrowid = 0 ;  
function addRow(){  
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
        id: newrowid,	
        isbn: "",
        booktitle: "",  
        sendprice: "",  
        sendoutDiscountrate: "",  
        sendQty: "",
        isQueryData: false,
        isSupplierAddProduct:true
    };
    //将新添加的行插入到第一列  
    $("#table_d").jqGrid("addRowData", newrowid, dataRow, "first");  
    //设置grid单元格不可编辑  
    $("#table_d").setGridParam({cellEdit:false});  
    //设置grid单元格可编辑  
    $("#table_d").jqGrid('editRow', newrowid, false);
    $("#"+newrowid+"_isbn").val("");
    $("#"+newrowid+"_booktitle").val("");
    $("#"+newrowid+"_sendprice").val("");
    $("#"+newrowid+"_sendoutDiscountrate").val("");
    $("#"+newrowid+"_sendQty").val("");
}


//添加已查询数据行
var newrowid2 ;
function addRowFromQuery(data){
    //循环数据
    //console.log(data)
    for	(var p = 0; p<data.length; p++){
    	//获得新添加行的行号（数据编号）
    	//newrowid2 = rowid+1;
    	var dataRow = {
        	id: data[p].id,	
            isbn: data[p].isbn,  
            booktitle: data[p].booktitle,  
            sendprice: data[p].price,  
            sendoutDiscountrate: data[p].discount,  
            sendQty: data[p].sendOutQty,
            isQueryData: data[p].isQueryData,
            isSupplierAddProduct:true
        };
    	//console.log(dataRow);
        //将新添加的行插入到第一列  
        $("#table_d").jqGrid("addRowData", newrowid2, dataRow, "first");  
        //设置grid单元格不可编辑  
        $("#table_d").setGridParam({cellEdit:false});  
        //设置grid单元格可编辑  
        $("#table_d").jqGrid('editRow', newrowid2, false);
    }
} 

//删除行数据
function deleteRowData(){
	var moduleIds = jQuery("#table_d").jqGrid('getGridParam', 'selarrrow');
	if (moduleIds.length == 0) {
		layer.alert("请选择至少一条数据！");
		return;
	} 
    
	for(var q = moduleIds.length-1; q >= 0; q--){
		$("#table_d").jqGrid("delRowData", moduleIds[q]); 
	}
}

/**
 * 弹出提示
 */
function alertMsg(msg){
	layer.alert(msg,{icon:0});
}

/**
 * 弹出成功提示
 */
function alerSuccesstMsg(msg){
	layer.msg(msg,{icon:1,time:1000});
}
//判断添加商品页面商品数据是否重复(根据isbn、书名、价格、折扣 判重)   
function isRepeat(rData){
	var objs=$("#table_d").jqGrid("getRowData"); 
	var flag=false;
	for ( var i = 0; i<objs.length; i++) {
		var isbn=$("#"+objs[i].id+"_isbn").text();
		var booktitle=$("#"+objs[i].id+"_booktitle").text();
		var sendprice=$("#"+objs[i].id+"_sendprice").val();
		var sendoutDiscountrate=$("#"+objs[i].id+"_sendoutDiscountrate").val();
		if(isbn==rData.isbn && booktitle==rData.booktitle && sendprice==rData.price && sendoutDiscountrate==rData.discount){
			flag=true;
			return flag;
		}
	}
	return flag;
} 


/**
 * 判断主页面商品数据是否重复(根据isbn、书名、价格、折扣 判重)
 * pDatas: 父页面数据(商品列表数据)
 * cDatas: 子页面数据(添加商品页面数据)
 */
function productIsRepeat(pDatas,cData){
	var flag=false;
	for ( var i = 0; i<pDatas.length; i++) {
		var isbn=pDatas[i].isbn;
		var booktitle=pDatas[i].bookTitle;
		var sendprice=pDatas[i].availablePrice;
		var sendoutDiscountrate=pDatas[i].availableDiscountrate;
		if(isbn==cData.isbn && booktitle==cData.bookTitle && sendprice==cData.availablePrice && sendoutDiscountrate==cData.availableDiscountrate){
			flag=true;
			return flag;
		}
	}
	
	return flag;
}  

/**
 * 检查提交的数据
 * @param dataRow
 * return {true:数据不通过验证  false:数据通过验证}
 */
function checkAddDatas(dataRow){
	var flag=true;
	var re = /^\+?[1-9][0-9]*$/ ; 
	if(dataRow.bookTitle==""){
		layer.alert("要添加的商品书名不能为空!",{icon:0});
	}else if(dataRow.isbn==""){
		layer.alert("要添加的商品ISBN不能为空!",{icon:0});
	}else if(dataRow.availablePrice==""){
		layer.alert("要添加的商品发货价不能为空!",{icon:0});
	}else if(dataRow.availableDiscountrate==""){
		layer.alert("要添加的商品发货折扣不能为空!",{icon:0});
	}else if(dataRow.thisSendQty==""){
		layer.alert("要添加的商品发货数不能为空!",{icon:0});
	}else if(!re.test(dataRow.thisSendQty)){
		layer.alert("要添加的商品发货数只能为正整数!",{icon:0});
	}else if(isNaN(dataRow.availablePrice)  || dataRow.availablePrice < 0){
		layer.alert("要添加的商品发货价不合法!",{icon:0});
    }else if(isNaN(dataRow.availableDiscountrate)  || dataRow.availableDiscountrate < 0){
		layer.alert("要添加的商品发货折扣不合法!",{icon:0});
    }else if(isNaN(dataRow.thisSendQty)  || dataRow.thisSendQty <= 0){
		layer.alert("要添加的商品发货数不合法!",{icon:0});
    }else{
    	flag=false;
    }
	
	return flag;
	
}


