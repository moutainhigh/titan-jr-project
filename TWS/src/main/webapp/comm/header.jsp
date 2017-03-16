<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<div class="header">
	<div class="w_1200">
		<div class="logo">
			<div class="l_img"><a href="<%=basePath%>"><img src="<%=cssWalletPath%>/images/logo.png"></a></div>
		</div>
		<div class="head_r">
			<ul>
				<li><a href="<%=basePath%>" <c:if test="${param.menu=='index'}">class="Aon"</c:if>>首页</a></li>
				<li><a href="<%=basePath %>/main/solution.shtml" <c:if test="${param.menu=='fangan'}">class="Aon"</c:if>>解决方案</a></li>
				<li class="w_240 li_1">
					<!-- 登录前 -->
					<div class="" id="no_login">
					<a href="<%=basePath %>/ex/organ/showOrgUser.shtml" class="li_login">免费注册</a>
					<a class="li_btn1" href="<%=basePath %>/ex/login.shtml">登录</a>
					</div>
					<!-- 登录后 -->
					<div class="hr_login dn" id="y_login">
						<div class="hrl_hover">
							<i class="ico"></i>
							我的账户
						</div>
						<div class="hrl_ul dn">
							<a href="<%=basePath %>/account/overview-main.shtml">资产概览</a>
							<a href="<%=basePath %>/setting/user-info.shtml">泰坦钱包设置</a>
							<a href="<%=basePath %>/ex/loginout.shtml">退出</a>								
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">


$(document).ready(function(){
	$('.hr_login').hover(function(){
		$(this).find('.hrl_ul').removeClass('dn');
		$(this).find('.hrl_hover').addClass('l_red');
	},function(){
		$(this).find('.hrl_ul').addClass('dn');
		$(this).find('.hrl_hover').removeClass('l_red');
	});
	$.ajax({
		url:'<%=basePath%>/ex/loginService.shtml',
		type:'post',
		dataType:'json',
		success:function(json){
			if(json.code==1){
				if(json.data.islogin=="1"){
					$("#y_login").show();
					$("#no_login").hide();
				}else{
					$("#y_login").hide();
					$("#no_login").show();
				}
			}
		}
	});
});
</script >
