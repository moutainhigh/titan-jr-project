<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>授信申请-泰坦金融</title>
       <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
<body class="bgf2f2f2">
	<c:if test="${empty errorMsg}">
	    <div class="TFS_Audit02">
	       <h3><span>授信申请已提交</span></h3>
	       <p> 申请提交时间：${reqTime}<br/>请耐心等待，自申请之日起，5个工作日内可获取申请结果   </p>
	    </div>
	    <div class="TFS_data_button" style="background: #f2f2f2">
	        <a class="btn btnNext" href="<%=basePath %>/loan/credit/checkCreditStatus.shtml">完成</a>
	    </div>
	    <div style="height: 100px"></div>
    </c:if>
    
    <c:if test="${not empty errorMsg}">
    	<div class="TFS_Audit01">
        <span class="TFS_Audit03">${errorMsg}</span>
  		  </div>
	    <div class="TFS_data_button" style="background: #f2f2f2">
	    	<a class="btn btnNext" href="javascript:history.go(-1)">返回</a>
	        <a class="btn btnNext" href="<%=basePath %>/loan/credit/checkCreditStatus.shtml">关闭</a>
	    </div>
	    <div style="height: 100px"></div>
    </c:if>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
</body>
</html>