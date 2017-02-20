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
<jsp:include page="/comm/header.jsp"><jsp:param name="menu" value="fangan"/></jsp:include>
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
<div class="wh_solve w_1200">
	<div class="wh_title">解决方案</div>
	<div class="wh_c">
		<a href="<%=basePath %>/main/solution_detail.shtml"  class="li1">
			&nbsp;
		</a>
		<a href="<%=basePath %>/main/solution_detail.shtml" class="li2">
			&nbsp;
		</a>
		<a href="<%=basePath %>/main/solution_detail.shtml" class="li3">
			&nbsp;
		</a>
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