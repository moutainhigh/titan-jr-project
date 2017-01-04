<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/comm/path-param.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SAAS后台管理</title>
<jsp:include page="/comm/static-resource.jsp"></jsp:include>
<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
  
  <body class="bgf2f2f2">
    <div class="TFS_Audit02">
       <h3><span>包房贷款申请已提交！</span></h3>
       <p>贷款编号：${orderNo }<br/>
        申请提交时间：${ orderCreateTime}
       </p>
    </div>
    <div class="TFS_Audit04"> 
        您现在可以：<a href="<%=basePath %>/loan/credit/checkCreditStatus.shtml">返回我的贷款</a> <a href="<%=basePath %>/loan_apply/main.shtml">再贷一笔</a>
    </div>
  
  
    <jsp:include page="/comm/static-js.jsp"></jsp:include>
  </body>
</html>
