<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>注册成功-泰坦金融</title>
    <jsp:include page="/comm/tws-static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
  
 <body style="min-width: 1300px;" class="bg" >
<div class="header">
	<div class="w_1200">
		<div class="logo">
			<div class="l_img"><img src="<%=cssWalletPath %>/images/logo.png"></div>
			<div class="l_text">
				<i class="ico"></i>欢迎注册
			</div>
		</div>
		<div class="head_r">
			<ul>
				<!-- <li class="lion">首页</li>
				<li>解决方案</li> -->
				<li class="w_240 li_1">
					已注册，现在就
					<a class="li_btn" href="<%=basePath%>/ex/login.shtml">登录</a>
				</li>
			</ul>
		</div>
	</div>
</div>

<div class="register r_three">
	<div class="r_box">				
			<div class="r_t_text">
				<div class="rt_img"><i class="ico"></i></div>
				<div class="rt_c">
					<p>您的注册申请已成功提交</p>
					我们会尽快为您审核
				</div>
				<div class="rt_skip">
					3 秒后自动跳转至首页
				</div>
			</div>
			<div class="r_t_hint">				
				<a href="<%=basePath%>/main/main.shtml">立即跳转 </a>
			</div>	
	</div>
</div>
 
<jsp:include page="/comm/foot.jsp"></jsp:include>

<script type="text/javascript">
 //跳转
function timeOut(_this){
    var i=2;
    var interval=setInterval(function () {                
         if(i>0){
             _this.html( i + " 秒后自动跳转至首页"); 
             i--;
         }else{
            window.location.href="<%=basePath%>/main/main.shtml";
            clearInterval(interval);
         }; 
    }, 1000);
};
timeOut($('.rt_skip'));


</script>
</body>
</html>
