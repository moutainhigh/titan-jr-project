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
<div class="header">
	<div class="w_1200">
		<div class="logo">
			<div class="l_img"><img src="<%=cssWalletPath%>/images/logo.png"></div>
			<!-- <div class="l_text">
				<i class="ico "></i>欢迎注册
			</div> -->
		</div>
		<div class="head_r">
			<ul>
				<li><a href="<%=basePath %>/main/main.shtml">首页</a></li>
				<li><a href="<%=basePath %>/main/solution.shtml" class="Aon">解决方案</a></li>
				<li class="w_240 li_1">
					<!-- 登录前 -->
					<div class="dn">
					<a href="注册.html" class="li_login">免费注册</a>
					<a class="li_btn1" href="<%=basePath %>/login.shtml">登录</a>
					</div>
					<!-- 登录后 -->
					<div class="hr_login">
						<div class="hrl_hover">
							<i class="ico"></i>
							我的账户
						</div>
						<div class="hrl_ul dn">
							<a href="资产概览.html">资产概览</a>
							<a href="泰坦钱包设置.html">泰坦钱包设置</a>
							<a href="<%=basePath %>/login.shtml">退出</a>								
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>
<!-- banner -->
<div class="h_90"></div>
<!-- 内容 -->
<div class="solve_detail w_1200">	
	<div class="sd_title" >使用泰坦云SAAS企业客户</div>
	<div class="sd_c">
		<div id="tab1" class="tab"></div>
		<div class="sd_tit">旅行企业开通在线收款服务，流程相对复杂，开通微信支付，需要旅行社申请服务号，并交纳300元每年的认证费用才能申请开通微信支付；开通企业支付宝在线收款，
需要商家提供备案的独立网站域名。申请好支付权限后，还需要经过后台配置支付参数才能开始收款。而且旅行社在线收款金额较少一般签订微信和支付宝费率都是0.6%，
相对较高。影响了旅行行业使用在线收款的积极性。</div>
		<div class="sd_bd">
			<p>泰坦钱包支付优势</p>
支持企业用户在线申请，1分钟即可完成在线收款开通全流程。开启在线收款业务。<br>
支持企业银行网关支付、个人银行网关支付、微信支付、支付宝支付，全渠道支付一次申请全部启用。<br>
泰坦钱包为旅行行业提供更低的在线收款费率：企业网银10元/笔，个人网银借记卡0.3%，个人网银贷记卡0.3%，微信PC扫码支付0.4%，支付宝PC扫码支付0.4%。<br>
2016年12月31日前面向泰坦云SAAS付费客户还有免支付手续费活动
		</div>
	</div>
	<div class="sd_title">使用泰坦云SAAS个人客户</div>
	<div class="sd_c1">
		<div id="tab2" class="tab"></div>
		<div class="sd_tit">中小微旅行社由于业务开展习惯需要，经常会用到个人账户进行收付款，且自己没有注册的独立运营网站，这使得中小微旅行社想开展在线收款业务非常困难</div>
		<div class="sd_bd">
			<p>泰坦钱包支付优势</p>
支持个人用户在线开通泰坦钱包在线收付款业务<br>
支持个人用户提现到绑定个人的银行卡上<br>
重要的一点，个人用户可享受到企业用户相同的在线收付款低费率及优惠活动
		</div>
	</div>
	<div class="sd_title">旅行社自有网站需在线收款</div>
	<div class="sd_c2">
		<div id="tab3" class="tab"></div>
		<div class="sd_tit">旅行社自建网站对接收银台，微信、支付宝、网银支付往往需要对接多个支付接口，开发及维护工作量都很大。
而且交易金额不高一般签订的支付宝、微信支付费率都是在0.6%左右</div>
		<div class="sd_bd">
			<p>泰坦钱包支付优势</p>
支持一个收银台对接，就支持企业网银、个人网银、微信、支付宝综合在线支付服务。<br>
可享受到泰坦钱包更低的支付费率：企业网银10元/笔，个人网银借记卡0.3%，个人网银贷记卡0.3%，微信PC扫码支付0.4%，支付宝PC扫码支付0.4%
		</div>
	</div>
	<div class="h_70"></div>
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