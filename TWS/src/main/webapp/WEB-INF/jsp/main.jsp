<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>金融首页</title>
	<jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	<style>html,body{ overflow: hidden;}</style>
</head>
<body>
	<div class="header_shrink "></div>
	<div class="wrap">
		<div class="header">
			<div class="h_logo fl">
				<div class="h_logo_img">
					<img src="${CURRENT_LogoUrl}" alt=""></div>
			</div>
			<ul class="h_nav fl">
			<li class="nav_FinancialService">
				<a href="<%=basePath%>/home.shtml">
					<i></i>
					<span>泰坦金融</span>
				</a>
				<div class="nav_sub">
					<a href="<%=basePath%>/home.shtml" class="s1" data-parent="false">
						<span></span>
						金融首页
					</a>
					<a href="<%=basePath%>/account/overview-main.shtml" class="s2" data-parent="false">
						<span></span>
						账户概览
					</a>
					<a href="" class="s7" data-url="<%=basePath %>/setting/slidemenu.shtml">
						<span></span>
						泰坦金融设置
					</a>
					<a href="<%=basePath%>/common/clickToPay.shtml" class="s8" data-parent="false">
						<span></span>
						付款页面
					</a>
					<a href="<%=basePath%>/help/index.action" class="s8" data-parent="false">
						<span></span>
						帮助中心
					</a>
				</div>
			</li>
		</ul>
		<div class="h_tools fr">
		</div>
		<!-- 二级导航背景 -->
		<div class="h_sub_bg"></div>
		<!-- 二级导航背景结束 -->
	</div>
	<div class="content clearfix">
		<!-- 左侧栏开始 -->
		<div class="side_area">
			<div class="menu_container"></div>
		</div>
		<!-- 左侧栏结束 -->
		<!-- 右侧主体内容开始 -->
		<div class="main_area" id="main_area"></div>
		<!-- 右侧主体内容结束 -->
	</div>
</div>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script type="text/javascript" src="<%=cssSaasPath%>/js/frame.js?v=20151010"></script>

<script>
F.UI.scan('.old_new_cut_hint');
$('.J_copy').on('click',function(){
    $.ajax({
        dataType : 'html',
        context: document.body,
        url : '版权信息.html',
        success : function(html){

            var d =  dialog({
                title: ' ',
                padding: '0 0 30px 0',
                content: html,
                skin : 'saas_pop',
               
            }).showModal()
        }
    })
});
</script>
</body>
</html>