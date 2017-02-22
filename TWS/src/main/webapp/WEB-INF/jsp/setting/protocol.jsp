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
<jsp:include page="/comm/header.jsp"></jsp:include>
<div class="h_90"></div>
<!-- 内容 -->
<div class="w_1200 ">
	<div class="overview clearfix m_t20 o_set">	
		<jsp:include page="../slidemenu/jr-setting-menu.jsp">
			<jsp:param value="protocol" name="menu"/>
		</jsp:include>
		<div class="s_right clearfix">
			<div class="sr_crumbs">我的账户  >  泰坦钱包设置  > 泰坦钱包协议</div>			
			<jsp:include page="/comm/content.jsp"></jsp:include>
			<div class="sra_btn">
					<div class="h_61"></div>
					<div class="sra_btn1 sra_fixed">
						<div class="sra_xi"></div>
						<a class="btn" href="javascript:;" onclick="window.open('http://static.fangcang.com/static/tfs/titan_finance_protocol.doc')">下载</a>
						<a class="btn btn_g" href="<%=basePath %>/ex/print-protocol.shtml" >打印</a>
					</div>
			    </div>
		</div>
	</div>
</div>
<div class="h_40"></div>
<!-- 版权 -->
<jsp:include page="/comm/foot-line.jsp"></jsp:include>
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

//按钮
$(window).on('scroll',function(){
	var st1=$(document).scrollTop(),		
		OrderPay=$('.sra_btn').offset().top-$(window).height()+61;
	if(st1<OrderPay){
		$('.sra_btn1').addClass('sra_fixed');		
	}else{
		$('.sra_btn1').removeClass('sra_fixed');
	}
})
</script>

</body>
</html>

