<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${qrCode.payType=='32' }">
		<h3>支付宝扫码支付</h3>
	</c:if>
	<c:if test="${qrCode.payType=='30' }">
		<h3>微信扫码支付</h3>
	</c:if>
	<div class="wx_fk fl">
			<img id="qrcode" alt="二维码支付" src="<%=basePath%>/pay/wxPicture.shtml?url=${qrCode.respJs}" class="fl">		
	</div>
</body>
</html>