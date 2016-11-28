<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/comm/path-param.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>金服首页</title>
       <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
<body class="bgf2f2f2">
    <div class="TFS_Audit02">
       <h3><span>授信申请已提交</span></h3>
       <p> 申请提交时间：${reqTime}<br/>请耐心等待，自申请之日起，5个工作日内可获取申请结果   </p>
    </div>
    <div class="TFS_data_button" style="background: #f2f2f2">
        <a class="btn btnNext" href="<%=basePath %>/loan/credit/checkCreditStatus.shtml">完成</a>
    </div>
    <div style="height: 100px"></div>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
</body>
</html>