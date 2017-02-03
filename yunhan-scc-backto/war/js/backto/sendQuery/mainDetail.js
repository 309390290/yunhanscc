/**add by zxc 2016-02-19 发货单详情js*/
In.ready('jqGrid',function() {
	var supplierId = sapvendorId;
	var userCode = userName;
	
	$("#table_list").jqGrid({ 
	    url:appPath+'/console/query',
		autowidth: true, //自适应宽度
 		shrinkToFit: true, //列自适应
		datatype: "json", //数据格式
		mtype:"POST",
		colNames:["ID","发货单号","订单号","ISBN","商品名称","发货价","发货折扣（%）","发货数"],// the column header names  
	    colModel :[   
              {name:"id", index:"id",hidden:true}, 
              {name:"sendoutGoodsCode", index:"sendoutGoodsCode",hidden:true},
			  {name:"purchaseOrderCode", index:"purchaseOrderCode",formatter : function(value, options, rData) {
				  if(rData.isInitiative==0 ){
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
					return str+value;
				  }
			  },width:'12%',align:'center'},   
			  {name:"isbn", index:"isbn",width:'12%',formatter : function(value, options, rData) {
				  if(rData.isInitiative==0 ){
					  return "<span class='fontblue'>"+value+"</span>"
				  }
				  return value;
			  },align:'center'}, 
			  {name:"bookTitle", index:"bookTitle",width:'30%',formatter : function(value, options, rData) {
				  if(rData.isInitiative==0 ){
					  return "<span class='fontblue'>"+value+"</span>"
				  }
				  return value;
			  },align:'center'}, 
			  {name:"sendoutPrice", index:"sendoutPrice",width:'12%',formatter : function(value, options, rData) {
				  if(rData.isInitiative==0 ){
					  return "<span class='fontblue'>"+value+"</span>"
				  }if(rData.price==null){
					  return "<span class='fontred' title='原定价:无'>"+value+"</span>"
				  }else if(value-rData.price!=0){
					 return "<span class='fontred' title='原定价"+rData.price+"'>"+value+"</span>"
				  }
				  return value;
			  },align:'center'}, 
			  {name:"sendoutDiscountrate", index:"sendoutDiscountrate",formatter : function(value, options, rData) {
				  if(rData.isInitiative==0 ){
					  return "<span class='fontblue'>"+value+"</span>"
				  }if(rData.discountrate==null){
					  return "<span class='fontred' title='原折扣:无'>"+value+"</span>"
				  }else  if(value-rData.discountrate!=0){
					  return "<span class='fontred' title='原折扣"+rData.discountrate+"%'>"+value+"</span>"
				  }
				  return value;
			  },width:'12%',align:'center'}, 
			  {name:"sendoutQty", index:"sendoutQty",width:'12%',formatter : function(value, options, rData) {
				  if(rData.isInitiative==0 ){
					  return "<span class='fontblue'>"+value+"</span>"
				  }
				  if(value>rData.orderQty){
					  var d = value-rData.orderQty;
					  return "<span class='fontred' title='超发数"+d+"'>"+value+"</span>"
				  }
				  return value;
			  },align:'center'}
			],
	   	rowNum: 50,  //默认分页行数
		rowList:[50,100],  //可选分页行数
		pager: '#page',  //分页按钮展示地址
		sortname: 'id',  //默认排序字段
		viewrecords: true,   //是否显示数据总条数
		multiselect:true,  //复选框
		sortorder: "desc",  //默认排序方式
		jsonReader: { 
			  root: "rows",
			  page: "page",
			  total: "total",
			  records: "records",
			  repeatitems : false
		}, //设置数据方式
		postData: {query_id:"querySendItemsToDetail",query_type:"JQGRID",rdParseType:"dispersed", reqData:queryData()}, 
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
	  			delItems(id);
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

  	
  	/**
  	 * 查询参数 by zxc 
  	 */
  	function queryData(){

  		var sendoutGoodsCode	= $("#sgc").val();
  		var queryData="{'groupOp':'AND','rules':[";
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
  		return queryData;
  	}

  	/**
  	 * 查询操作 by zxc
  	 *//*
  	function search(){
  		var param = queryData();
  		if(param){
  			jQuery("#table_list").jqGrid('setGridParam',{datatype:'json',postData:{query_id:"querySendItemsToDetail",query_type:"JQGRID",reqData:param}}).trigger("reloadGrid");
  		}
  	}*/
	/**
	 * 修改操作 by zxc
	 */
	function modify(){
		var id = $("#summaryId").val();
		var sendoutStatus = $("#sendoutStatus").val();
		if(sendoutStatus=="10"||sendoutStatus=="15"){
			layer.alert("已收货的发货单不能修改！", {icon:0});//0：弹框图片为叹号警告
			return false;
		}
		if(null!=id && undefined!= id && id.length>0){
			 var url = appPath+"/backto/sendQuery/page/modify?id="+id;
			 window.location.href = url;
		}else{
			layer.alert("请刷新后重试！", {icon:0});//0：弹框图片为叹号警告
		}
	}
 
  	/**确认修改*/
  	$(".modify-btn").click(function(){
  		modify();
  	});
  	/**返回*/
  	$(".back-btn").click(function(){
  		window.history.go(-1);
  	});
});