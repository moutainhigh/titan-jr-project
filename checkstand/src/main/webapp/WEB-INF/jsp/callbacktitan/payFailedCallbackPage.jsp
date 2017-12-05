<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="/common/static-js.jsp"></jsp:include>
<body>
<c:if test="${not empty payCallbackRequest }">
	<form action="${payCallbackRequest.titanjrGateWayUrl}" name="payConfirm_form" id="payConfirm_form" method="post" >
	    <input name="orderNo" type="hidden" value="${payCallbackRequest.orderNo}"/>
		<input name="payStatus" type="hidden" value="${payCallbackRequest.payStatus}"/>
		<input name="payAmount" type="hidden" value="${payCallbackRequest.payAmount}"/>
		<input name="orderPayTime" type="hidden" value="${payCallbackRequest.orderPayTime}"/>
		<input name="payMsg" type="hidden" value="${payCallbackRequest.payMsg}"/>
	</form>
</c:if>
</body>
<script type="text/javascript">
	function submitform(){
	   $("#payConfirm_form").submit();
	}
	window.onload = submitform;
</script>
</html>
