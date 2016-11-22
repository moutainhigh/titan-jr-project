<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>泰坦金融</title>
  <script type="text/javascript" src="<%=cssSaasPath%>/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>/js/jquery.qrcode.min.js"></script> 
</head>
  <body>
	
客户端支付单号:<input name="orderNo" id="orderNo" value="${payOrderCode}">
商户订单号:<input name="orderNo" id="orderNo" value="${businessOrderCode}">
    支付金额:<input name="orderNo" id="orderNo" value="${amount}" >
    操    作人:<input name="orderNo" id="orderNo" value="${operator}">
    商户编码:<input name="orderNo" id="orderNo" value="${merchantCode}" >
    金融单号:<input name="orderNo" id="orderNo" value="${titanPayOrderCode}" >
    支付结果:<input name="orderNo" id="orderNo" value="${payResult}" >
    编      码:<input name="orderNo" id="orderNo" value="${code}" >
	
	
</body>
</html>
