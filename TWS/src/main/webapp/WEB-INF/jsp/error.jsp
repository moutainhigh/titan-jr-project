<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>泰坦钱包</title>
	<jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
<body style="min-width: 1300px;" >
<!-- 头部 -->
<jsp:include page="/comm/header.jsp"></jsp:include> 
<!-- banner -->
<div class="h_90"></div>
<!-- 内容 -->
<div class="TTM_contentMin">			
	<div class="unusual_wrap">
		<div class="unusual">
			<p>${errormsg}</p>
			<p>您可以去其他地方逛逛...</p>
		</div>
		<a href="javascript:history.back();" class="btn">返回</a><a href="<%=basePath %>" class="btn btn_g">去首页</a>
	</div>
</div>				

<!-- 版权 -->
<jsp:include page="/comm/foot-line.jsp"></jsp:include>
</body>
</html>
