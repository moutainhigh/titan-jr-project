<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
	 <meta charset="UTF-8">
    <title>卡密鉴权</title>
	</head>
<body>
<c:if test="${not empty rbDataRequest }">
	<form action="${gateWayUrl}" name="cardAuth_form" id="cardAuth_form" method="post" >
	    <input name="merchant_id" type="hidden" value="${rbDataRequest.merchant_id}"/>
		<input name="data" type="hidden" value="${rbDataRequest.data}"/>
		<input name="encryptkey" type="hidden" value="${rbDataRequest.encryptkey}"/>
	</form>
</c:if>

<jsp:include page="/common/static-js.jsp"></jsp:include>
</body>
<script type="text/javascript">
function submitform(){
	   $("#cardAuth_form").submit();
	}
window.onload = submitform;

</script>

</html>