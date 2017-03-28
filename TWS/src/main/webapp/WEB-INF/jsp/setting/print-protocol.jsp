<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title></title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
  
  <body class="bg" >
<!-- 头部 -->
<!-- 内容 -->
<div class="w_1200" style="width:860px;">
	<div class="overview clearfix m_t20 o_set">	
		<div class="s_right clearfix" style="width:830px;border:0;">
			<h2 style="text-align:center;padding:20px 0px;font-weight:bold;font-size:20px;">泰坦云金融服务协议</h2>
			<jsp:include page="/comm/content.jsp"></jsp:include>
			<div class="sra_btn" id="p_btn">
					<div class="h_61"></div>
					<div class="sra_btn1 sra_fixed" style="width:890px;margin-left:-30px;">
						<div class="sra_xi"></div>
						<a class="btn btn_g" href="javascript:;" onclick="printPro()">打印</a>
					</div>
			    </div>
		</div>
	</div>
</div>
<div class="h_40"></div>
<!-- 版权 -->
<jsp:include page="/comm/foot.jsp"></jsp:include>
<div class="h_40"></div>
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
function printPro(){
	$("#p_btn").hide();
	window.print();
}
printPro();
</script>

</body>
</html>

