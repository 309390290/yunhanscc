/*
 * 导入中间页面
<div id="myModal5" class="modal-mini hide fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
<form id="sendOutForm" method="post">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4>解析成功</h4>
  </div>
  <div class="modal-body">
    <div>导入成功:
    	<span class="fontB" id="successData">
    	</span> 条 
    	<a  class="left40 line downLoadError">错误数据<span id="errorData"></span>条</a>
    	<div id="errorFlag" style="display: none;">
    		
     	</div>
    </div>
    <input type="hidden" id="totalSuccess" name="totalSuccess">
    <input type="hidden" id="errorFilePath">
    <input type="hidden" id="sendoutgoodscodes" name="codes">
    <input type="hidden" id="orderCode_ID" name="orderCode_ID">
    <input type="hidden" id="purId" name="purchaserId">
    <input type="hidden" id="execlurl">
    <input type="hidden" id="sendOutSummary" name="sendOutSummary">
    <input type="hidden" id="purchaserName_hiden" name="purchaserName_hiden">
    <input type="hidden" id="sapvendorID_hiden" name="sapvendorID_hiden">
    <input type="hidden" id="purchaserId_hiden" name="purchaserId_hiden">
    <input type="hidden" id="isblank" name="isblank">
    <input type="hidden" id="receiveWarehouse"  name="receiveWarehouse">
    <input type="hidden" id="receiveAddress"  name="receiveAddress">
    <input type="hidden"   name="source" value="order">
    <div align="center"> 
    <a  id="downLoadError" style="display: none;" class="btn downLoadError btn-small btn-primary" >下载</a>
    <a id="toSendGoodsFromTemp" class="btn btn-small btn-primary" >确 定</a>
    </div>
    <br>
  </div>
</form>
</div>
*/
In.ready('uploadBox',function() {
/************************ 有订单发货导入  开始 ****************************/
	 $('#importSendBtn').uploadBox({
		 beforeShow: function () {
			 $("#successData").text("");
			 $("#errorData").text("");
			 $("#errorFlag").html("");
			 $("#downLoadError").hide();
			 $("#toSendGoodsFromTemp").show();
			 //上传控件显示前的回调，如果return false将不会往下执行
		},
			//formData: {purchaserId:purchaserId},   //上传参数
		showobj:"#uploaded-box",
		maxFileNum: 20,
		type:'excel',
		//采用同样的地址？
		server:appPath + "/backto/sendGoods/import/uploadExcel",
		callback:function (data) {
			var arr = new Array();
			for(var i=0;i<data.length;i++){
				arr.push(data[i].url);
			}
			//解析excel
			$.ajax({
	 			type:"post",
	 			//async: true,
	 			//解析Execl文件地址 
	 			url: appPath+'/backto/sendGoods/resolveProResponseItemsT',
	 			traditional: true,
	 			beforeSend: function(){
	 				WG.loading.show();	
	 			},
	 			data: {files:arr},
	 			dataType: "json", 
	 			//contentType: "application/json",
	 			success: function(data){
	 				WG.loading.hide();
	 				$("#successData").text(data.obj.success);
					$("#totalSuccess").val(data.obj.success);
					$("#errorData").text(data.obj.error);
					if(Number(data.obj.error)>0){
						$("#errorFlag").show();
						var str="<span class='fontB'>其中：已处理商品"+data.obj.errorList_1+"条，不能再处理！</span>"+
					    		"<br/><span class='fontB'>其中：无法匹配商品"+data.obj.errorList_2+"条，不能处理！</span>"+
					    		"<br /><span class='fontB'>请先下载错误原因，再进行下一步处理！</span>";
						$("#downLoadError").show();
						$("#toSendGoodsFromTemp").hide();
						$("#errorFlag").html(str);
					}
					$("#sendoutgoodscodes").val((data.obj.sendOutgoodsCodes).toString());
					$("#errorFilePath").val(data.obj.errorFilePath);
					$("#sapvendorID_hiden").val(data.obj.sapvendorID_hiden);
					$("#purchaserId_hiden").val(data.obj.purchaserId_hiden);
					$("#isblank").val(data.obj.isblank);
					$("#execlurl").val("/backto/sendGoods/page/sendGoodsFromTemp");
					$("#myModal5").modal('show');
	 			},
	 			error: function(data){
	 				WG.loading.hide();
	 				layer.msg("解析excel失败！",{icon:2});
	 			}
	 		});
				
		 }
	});
	 /**
	  * 下载错误数据
	  */
	 $(".downLoadError").bind("click",function(){
		 	var form = document.getElementById("sendOutForm");
			var errorFilePath = $("#errorFilePath").val();
			var errorData = $("#errorData").html();
			if(errorData == "" || errorData == 0){
				layer.alert("没有错误数据！或者文件已经被下载，不能重复下载！", {icon:0});
				return;
			}
	 		form.action =  appPath+'/backto/sendGoods/exportErrorData?path='+encodeURI(encodeURI(errorFilePath));
	 		form.submit();
	 		$("#downLoadError").hide();
			$("#toSendGoodsFromTemp").show();
	 		$("#errorData").html(0);
	 });
	 
	 
	 function execlUrlParm(url){
			var form = document.getElementById("sendOutForm");
	 		form.action =  appPath+url;
	 		form.submit();
		}
	 
	 /**
		 * 解析Execl 后，成功数据和错误数据展示模态框 确定按钮事件处理。
		 * 我们这里重用一下，发货的框也用这个。
		 */
		$("#toSendGoodsFromTemp").click(function(){
			var url = $("#execlurl").val();
			if($("#totalSuccess").val() == 0 && $("#errorData").val() == 0){
				$("#myModal5").modal('hide');
			}else{
				execlUrlParm(url);
			}
		});
	
	/************************ 有订单发货导入  结束 ****************************/
		
	/************************ 无订单回告发货导入  开始 ****************************/
		 $('.blankTempImportBtn').uploadBox({
			 beforeShow: function () {
				 //上传控件显示前的回调，如果return false将不会往下执行
				 var purchaserId= $("select[id=pur_all]").val();
				 var warehouse= $("select[id=dc_all]").val();
				 if(purchaserId==null || purchaserId==""){
					 layer.alert("请选择采购商!",{icon:0});
					 return false;
				 }if(warehouse==null || warehouse==""){
					 layer.alert("请选择仓位!",{icon:0});
					 return false;
				 }
				 $("#successData").text("");
				 $("#errorData").text("");
			},
			formData: {purchaserId:$("select[name=pur_all]").val()},   //上传参数
			showobj:"#uploaded-box",
			maxFileNum: 20,
			type:'excel',
			//采用同样的地址？
			server:appPath + "/backto/sendGoods/import/uploadExcel",
			callback:function (data) {
				var purchaserId= $("select[id=pur_all]").val();
				var warehouse= $("select[id=dc_all]").val();
				var arr = new Array();
				for(var i=0;i<data.length;i++){
					arr.push(data[i].url);
				}
				WG.loading.show();
				//解析excel
				$.ajax({
		 			type:"post",
		 			//async: false,
		 			//解析Execl文件地址 
		 			url: appPath+'/backto/sendGoods/resolveProResponseItemsTFromBlank',
		 			traditional: true,
		 			data: {files:arr,purchaserId:purchaserId,warehouse:warehouse},
		 			dataType: "json", 
		 			//contentType: "application/json",
		 			success: function(data){
		 				WG.loading.hide();
		 				$("#successData").text(data.obj.success);
						$("#totalSuccess").val(data.obj.success);
						$("#errorData").text(data.obj.error);
						$("#sendoutgoodscodes").val((data.obj.sendOutgoodsCodes).toString());
						$("#errorFilePath").val(data.obj.errorFilePath);
						$("#sendOutSummary").val(data.obj.sendOutSummary);
						$("#purchaserName_hiden").val($("#pur_all").find("option:selected").text());
						$("#sapvendorID_hiden").val(data.obj.sapvendorID_hiden);
						$("#purchaserId_hiden").val(data.obj.purchaserId_hiden);
						$("#isblank").val(data.obj.isblank);
						$("#execlurl").val("/backto/sendGoods/page/sendGoodsFromBlankTemp");
						$("#myModal5").modal('show');
		 			},
		 			error: function(data){
		 				WG.loading.hide();
		 				layer.msg("解析excel失败！",{icon:2});
		 			}
		 		});
				
					
			 }
		});
		/************************ 无订单回告发货导入  结束 ****************************/
		
		
		
		
		
		
});	