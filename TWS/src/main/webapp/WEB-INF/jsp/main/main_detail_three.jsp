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
<jsp:include page="../header.jsp"></jsp:include>
<!-- banner -->
<div class="h_90"></div>
<!-- 内容 -->
<div class="h_detail h_img1">	
	<div class="hd_c w_1200 clearfix">
		<div class="con">
			<div class="con_c">
				<p>旅行行业全支付场景覆盖，轻松开通在线收付款服务</p>PC端收付款场景——泰坦云SAAS单结订单在线收款、泰坦云SAAS月结账单在线收款、泰坦云SAAS财务在线付款给供应商、旅行社自有网站在线收款<br>
移动端收付款场景——泰坦云旅信产品在线收款（建设中）旅行社自有订阅号转在线收款（建设中）移动面对面收款（建设中）
			</div>
			<div class="con_c1">
				<p>PC端收付款场景</p>
泰坦云SAAS单结订单在线收款：月结账单在线收款：财务在线付款给供应商、<br>
旅行社自有网站在线收款
			<div class="img img2"></div>
			</div>
		</div>
		<div class="con1">
			<div class="con_c">
				<p>移动端收付款场景</p>
泰坦云旅信产品在线收款（建设中）<br>
旅行社自有订阅号转在线收款（建设中）<br>
移动面对面收款（建设中）
				<div class="img img3"></div>
			</div>
		</div>
		
	</div>
	<div class="h_160"></div>
</div>

<!-- 版权 -->
<div class="footer1">
	<div class="f_bd">
		<div class="fl">
			Copyright © 2012-2016, fangcang.com. All Rights Reserved 泰坦云 版权所有 粤ICP备13046150号	
		</div>
		<div class="fl f_bd_r">
		<script charset="utf-8" type="text/javascript" src="http://szcert.ebs.org.cn/govicon.js?id=78ccac39-a97a-452c-9f81-162cd840cff6&amp;width=130&amp;height=50&amp;type=2" id="ebsgovicon"></script>
		</div>
	</div>	
</div>


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