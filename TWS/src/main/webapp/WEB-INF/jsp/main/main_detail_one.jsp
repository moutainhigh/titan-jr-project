<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>金融首页</title>
	<jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
<body style="min-width: 1300px;" >
<!-- 头部 -->
<body style="min-width: 1300px;" >
<!-- 头部 -->
<jsp:include page="/comm/header.jsp">
<jsp:param name="menu" value="fangan"/>
</jsp:include>
<!-- banner -->
<div class="h_90"></div>
<!-- 内容 -->
<div class="h_detail ">	
	<div class="hd_c w_1200 clearfix">
		<div class="con">
			<div class="con_c">
				<p>安全的账户体系</p>
				泰坦金融会为每个注册成功的用户开通一个托管在通联支付的虚拟账户，此账户是通联开在人民银行的备付金账户上的，所有基于泰坦金融的收款、<br>
充值默认都是收到此账户上，任何第三方支付，包括支付宝，微信，账户余额也都是托管在人民银行的备付金账户，因此房仓账户的安全级别和这些第三方平台的安全级别是一样的。
			</div>
			<div class="con_c1">
				<p>充值</p>		
您可通过网银支付渠道充值到泰坦钱包账户中
			<div class="img"></div>
			</div>
		</div>
		<div class="con1">
			<div class="con_c">
				<p>提现</p>				
您可将泰坦钱包中的金额提现到账户绑定的银行借记卡中
				<div class="img"></div>
			</div>
		</div>
		<div class="con2">
			<div class="con_c">
			<p>账户管理</p>
会员管理包括修改登录密码、支付密码、联系信息、员工管理、权限管理等信息
			<div class="img"></div>
			</div>
		</div>
		<div class="con1">
			<div class="con_c">
			<p>交易管理</p>
			交易信息的查询、导出等
			<div class="img img1"></div>
			</div>
		</div>
	</div>
	<div class="h_160"></div>
</div>

<!-- 版权 -->
<jsp:include page="/comm/foot-line.jsp"></jsp:include>

<script type="text/javascript">
//我的账户
$('.hr_login').hover(function(){
	$(this).find('.hrl_ul').removeClass('dn');
	$(this).find('.hrl_hover').addClass('l_red');
},function(){
	$(this).find('.hrl_ul').addClass('dn');
	$(this).find('.hrl_hover').removeClass('l_red');
})
//广告 
var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        slidesPerView: 1,
        paginationClickable: true,
        spaceBetween: 0,
        loop: true,
        autoplay: 10000,
        autoplayDisableOnInteraction: false
 });



</script>
</body>
</html>