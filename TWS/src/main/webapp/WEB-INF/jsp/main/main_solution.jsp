<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>解决方案-泰坦钱包</title>
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
	<div class="swiper-slide"><a href="<%=basePath %>/main/main_detail_one.shtml" target="_blank" class="home01">&nbsp;</a></div>
    <div class="swiper-slide"><a href="<%=basePath %>/main/main_detail_two.shtml" target="_blank" class="home02">&nbsp;</a></div>
    <div class="swiper-slide"><a href="<%=basePath %>/main/main_detail_three.shtml" target="_blank" class="home03">&nbsp;</a></div>             
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
		<a href="<%=basePath %>/main/solution_detail.shtml#tab1"  target="_blank" class="li1">
			&nbsp;
		</a>
		<a href="<%=basePath %>/main/solution_detail.shtml#tab2" target="_blank" class="li2">
			&nbsp;
		</a>
		<a href="<%=basePath %>/main/solution_detail.shtml#tab3" target="_blank" class="li3">
			&nbsp;
		</a>
	</div>
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