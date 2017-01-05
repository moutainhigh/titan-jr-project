<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>金融首页</title>
	<jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
<body style="min-width: 1300px;" >
<!-- 头部 -->
<jsp:include page="header.jsp"></jsp:include>
<!-- banner -->
<div class="main_col m_t90">
<div class="swiper-container">
<div class="swiper-wrapper">
    <div class="swiper-slide"><a href="<%=basePath %>/main/main_detail_one.shtml" class="home01">&nbsp;</a></div>
    <div class="swiper-slide"><a href="<%=basePath %>/main/main_detail_two.shtml" class="home02">&nbsp;</a></div>
    <div class="swiper-slide"><a href="<%=basePath %>/main/main_detail_three.shtml" class="home03">&nbsp;</a></div>             
</div>
<div class="swiper-pagination"></div>
<div class="swiper-button-next"></div>
<div class="swiper-button-prev"></div>
</div>
</div>
<!-- 内容 -->
<div class="wallet_home w_1200">
	<div class="wh_c">
		<div class="wh_title">泰坦钱包</div>
	</div>
	<div class="wh_c1">
		<div class="wh_c1_l">
			<p>安全的账户体系</p>
			<a href="首页详细1.html">了解更多</a>
		</div>
		<div class="wh_c1_r">
			<ul>
			
				<li>充值</li>
				<li>提现</li>
				<li>账户管理</li>
			    <li>交易管理</li>
			</ul>
		</div>
	</div>
	<div class="wh_c1">
		<div class="wh_c1_l">
			<p>全渠道在线支付、一键对接</p>
			<a href="首页详细2.html">了解更多</a>
		</div>
		<div class="wh_c1_r wh1">
			<ul>			
				<li>银行网关支付</li>
				<li>微信支付</li>
				<li>支付宝支付</li>
			    <li>快捷支付</li>
			</ul>
		</div>
	</div>
	<div class="wh_c1 h_250">
		<div class="wh_c1_l">
			<p>旅行行业全支付场景覆盖，轻松开通在线收付款服务</p>
			<a href="首页详细3.html">了解更多</a>
		</div>
		<div class="wh_c1_r wh2">
			<ul>			
				<li>订单收款</li>
				<li>账单收款</li>
				<li>在线付款</li>
			    <li>自有网站收款</li>
			</ul>
		</div>
	</div>
</div>
<div class="wallet_home1">
	<div class="wh_c w_1200">
		<div class="wh_c_title">解决方案</div>
		<div class="wh_c_c">

			<a href="解决方案详细.html#tab1" class="li1">
				<div class="w_tit">企业客户</div>
				<div class="w_c">支持企业用户在线申请，1分钟完成<br>开通全流程。开启在线收款业务</div>
			</a>
			<a href="解决方案详细.html#tab2" class="li2">
				<div class="w_tit">个人客户</div>
				<div class="w_c">支持个人用户在线开通泰坦钱包<br>在线收付款业务</div>
			</a>
			<a href="解决方案详细.html#tab3" class="li3">
				<div class="w_tit">收银台</div>
				<div class="w_c">支持一个收银台对接网银、<br>微信、支付宝多种渠道在线收付款服务</div>
			</a>

		</div>
	</div>	
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