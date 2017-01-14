<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>个人账户资料-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
  
  <body style="min-width: 1300px;" class="bg" >
<!-- 头部 -->
<jsp:include page="./../header.jsp"></jsp:include>
<div class="h_90"></div>
<!-- 内容 -->
<div class="w_1200 ">
	<div class="overview clearfix m_t20 o_set">	
		<jsp:include page="../slidemenu/jr-setting-menu.jsp">
			<jsp:param value="info" name="menu"/>
		</jsp:include>
		<div class="s_right clearfix">
			<div class="sr_crumbs">我的账户  >  泰坦钱包设置  >  个人账户资料</div>
			<div class="sr_list">
				<div class="img ico"></div>
				<div class="tit">个人账户</div>
				<div class="srl_c clearfix"><div class="w_60 fl">用户名：</div> <div class="con">${tfsUser.userloginname }</div></div>
				<div class="srl_c clearfix"><div class="w_60 fl">姓名：</div> <div class="con">${tfsUser.username }</div></div>
			</div>
			<!-- 登录密码 -->
			<c:if test="${empty tfsUser.password }">
				<div class="sr_list">
					<div class="img img1 ico"></div>
					<div class="tit">登录密码</div>
					<div class="srl_c">尚未设置登录密码 <a href="<%=basePath %>/ex/login-pwd-set.shtml" class="blue undl">现在设置</a></div>
				</div>
			</c:if>
			
			<c:if test="${not empty tfsUser.password }">
				<div class="sr_list ">
					<div class="img  ico"></div>
					<div class="tit">登录密码</div>
					<div class="srl_c">您已设置登录密码 <a href="<%=basePath %>/setting/login-pwd.shtml" class="blue undl">修改密码</a></div>
				</div>
			</c:if>

			<!-- 付款密码 -->
			<c:if test="${ empty tfsUser.paypassword }">
				<div class="sr_list">
				<div class="img img1 ico"></div>
				<div class="tit">付款密码</div>
				<div class="srl_c">尚未设置付款密码 <a href="<%=basePath %>/setting/pay-set.shtml" class="blue undl">现在设置</a></div>
			</div>
			</c:if>
			<c:if test="${not empty tfsUser.paypassword }">
				<div class="sr_list ">
					<div class="img  ico"></div>
					<div class="tit">付款密码</div>
					<div class="srl_c">您已设置付款密码 <a href="<%=basePath %>/setting/modify-pwd.shtml" class="blue undl">修改密码</a></div>
				</div>
			</c:if>
			<div class="h_90"></div>
		</div>
	</div>
</div>
<div class="h_40"></div>
<!-- 版权 -->
<jsp:include page="/comm/foot.jsp"></jsp:include>
<script type="text/javascript">  
//内容高度
function scrollCon(){
	var winH=$(window).height();
	$('.s_right').css({'min-height':winH-235});
}
scrollCon();
$(window).on('resize.fixed',function(){
	scrollCon();
});

//我的账户
$('.hr_login').hover(function(){
	$(this).find('.hrl_ul').removeClass('dn');
	$(this).find('.hrl_hover').addClass('l_red');
},function(){
	$(this).find('.hrl_ul').addClass('dn');
	$(this).find('.hrl_hover').removeClass('l_red');
});
</script>

</body>
</html>

