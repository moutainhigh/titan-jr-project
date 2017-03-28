<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
    <title>登录密码修改-泰坦钱包</title>
   <jsp:include page="/comm/tws-static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
<body style="min-width: 1300px;" class="bg" >
<jsp:include page="/comm/head-title.jsp">
	<jsp:param value="修改登录密码" name="title"/>
</jsp:include>

<div class="register r_alter">
	<div class="r_box">		
		<div class="alter_password">
			<a href="<%=basePath%>/setting/login-pwd-remember.shtml">我记得原登录密码 ></a>
			<a href="<%=basePath%>/setting/login-pwd-forget.shtml">我忘记了原登录密码 ></a>
		</div>		
	</div>
</div>
<jsp:include page="/comm/foot.jsp"></jsp:include>
</body>
</html>