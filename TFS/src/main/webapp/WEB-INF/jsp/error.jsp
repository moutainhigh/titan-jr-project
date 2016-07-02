<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>错误信息-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
  
  <body>
    ${errormsg },<a href="javascript:;" onclick="history.go(-1)">返回</a>
  </body>
</html>
