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
<jsp:include page="../header.jsp"></jsp:include>
<!-- banner -->
<div class="h_90"></div>
<!-- 内容 -->
<div class="h_detail h_img">	
	<div class="hd_c w_1200 clearfix">
		<div class="con">
			<div class="con_c">
				<p>全渠道在线支付、一键对接</p>泰坦钱包已完成泰坦云SAAS系统的直连，泰坦云SAAS客户只需在线申请，就可完成全渠道在线支付的对接，方便快捷！<br>
非泰坦云SAAS客户使用泰坦钱包在线支付，也可轻松对接泰坦钱包收银台
			</div>
			<div class="con_c1">
				<p>银行网关支付</p>
支持10家企业网银支付、20家个人网银借记卡支付、15家个人网银贷记卡支付
			<div class="img img1"></div>
			</div>
		</div>
		<div class="con1">
			<div class="con_c">
				<p>微信支付</p>
支持PC微信扫码支付，移动端微信公众号支付（建设中）
				<div class="img img2"></div>
			</div>
		</div>
		<div class="con2">
			<div class="con_c">
			<p>支付宝支付</p>
支持PC支付宝扫码支付，移动端支付宝支付（建设中）
			<div class="img img1"></div>
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