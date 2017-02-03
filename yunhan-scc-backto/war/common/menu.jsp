<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 

<jsp:include page='<%="/common/userChange.jsp"%>' />

<div class="headerBox">
    <div class="menuback_com" id='menu_list'>
       <h1 class="logo">
            <a href="javascript:;"></a>
       </h1>
        <!--菜单开始-->
		<ul id="menu_com" class="ulLeft"></ul>
		<!--菜单结束-->
        <ul class="ulLeft ulRight">
       	   <li id="help_li">
       	   </li>	
           <li>
				<a href="javascript:;" ><span><i><script>document.write(userTrueName); </script></i><i>(<script>document.write(userName); </script>)</i></span> <b class="caret"></b></a>
				<div class='List'>
					<a href="javascript:;" id="userData" dataType="userData">个人资料</a>
					<a href="javascript:;" id="modfiyPassWord" dataType="newPassword">修改密码</a>
					<a href="<c:url value='/logout'/>">退出</a>
				</div>
			</li>
        </ul>
    </div>
</div>
<script id="menu_list_tpl" type="text/template">
	<li><a id="AWXRed" href="<c:url value='/'/>"><span>首页</span></a></li>
	{@each objList as menu,index}
	<li>
		{@if menu.subNodeList.length !== 0}
		<a href="javascript:;"><span>${'${'}menu.name}</span> <b></b></a>
		<div class="List">
			{@each menu.subNodeList as submenu,index}
			<a href="${'${'}submenu.url}">${'${'}submenu.name}</a>
			{@/each}
		</div>
		{@else}
		<a href="${'${'}menu.url}">${'${'}menu.name}</a>
		{@/if}
	</li>
	{@/each}
</script>
<script>
In.ready("juicer","newPasswordData",function() {
	if(merchantType == 1){
		$("#help_li").html('<a href="http://${staticHost}/js/html/help.html" target="_blank"><span class="help">帮助中心</span></a>');
	}
	$.ajax({
		url:"<c:url value='/mymodule'/>?userName="+userName,
		type:"get",
		dataType:"json",
		success:function(data){
			if(data==null || data.lenght==0 || (data.error&&data.error!="") ){
				layer.alert("获取菜单失败，请重新登录", {icon: 0}, function(index){
					window.location.href="<c:url value='/logout'/>";
				 });
			}else{
				for ( var i = 0; i < data.objList.length; i++) {
					var aa = data.objList[i].subNodeList;
					for ( var j =  0; j < aa.length; j++) {
						//消息推送配置菜单，需要替换参数
						if(aa[j].url.indexOf("receiveUser")!=-1){
							aa[j].url = aa[j].url.replace("departmentId_param",departmentId)
							aa[j].url = aa[j].url.replace("soId_param",userId)
							aa[j].url = aa[j].url.replace("receiveUserType_param",merchantType);
						}
// 	 					aa[j].url= aa[j].url.replace("px.gd.cnpdx.cn","localhost:8080/ase");
					}
				}
				var _tpl = $("#menu_list_tpl").html();
				var html = juicer(_tpl, data);
				var cookie_domin = $.cookie("indexDomain"); 
				$("#menu_com").html(html);
				$("#AWXRed").attr("href", cookie_domin);
				WG.menuHover.init();
			}
		}
   });
$(function(){
		
		var timer=null;
		
		//导航栏 hover
		$('#menu_list').on('mouseenter','li',function(){
		   var _this=$(this);
		   clearInterval(timer);
		   $('#menu_list').find('.List').stop(true,true).hide();
		   timer=setTimeout(function(){
		       _this.find('.List').stop(true,true).slideDown();
		   },300);
		   _this.find('a').first().addClass('active');
		});
		$('#menu_list').on('mouseleave','li',function(){
		   var _this=$(this);
		   clearInterval(timer);
		   timer=setTimeout(function(){
		       _this.find('.List').stop(true,true).hide();
		   },200);
		   _this.find('a').first().removeClass('active');
		});
		// 修改密码、修改个人资料
		$("#modfiyPassWord,#userData").newPasswordData({
			    url:'http://px.gd.cnpdx.cn',//地址
			    userName:userName			//登录名
		 });
	});
});
</script>
