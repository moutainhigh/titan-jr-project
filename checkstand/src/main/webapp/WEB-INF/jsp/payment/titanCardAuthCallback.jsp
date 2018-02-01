<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
	 <meta charset="UTF-8">
    <title>卡密鉴权</title>
	</head>
<body>
<c:if test="${not empty titanCardAuthResponse }">
	<form action="${titanCardAuthResponse.titanCardAuthCallbackPageUrl}" name="cardAuth_form" id="cardAuth_form" method="post" >
	    <input name="busiCode" type="hidden" value="${titanCardAuthResponse.busiCode}"/>
		<input name="merchantNo" type="hidden" value="${titanCardAuthResponse.merchantNo}"/>
		<input name="orderNo" type="hidden" value="${titanCardAuthResponse.orderNo}"/>
		<input name="terminalType" type="hidden" value="${titanCardAuthResponse.terminalType}"/>
		<input name="cardNo" type="hidden" value="${titanCardAuthResponse.cardNo}"/>
		<input name="bankName" type="hidden" value="${titanCardAuthResponse.bankName}"/>
		<input name="bankInfo" type="hidden" value="${titanCardAuthResponse.bankInfo}"/>
		<input name="phone" type="hidden" value="${titanCardAuthResponse.phone}"/>
		<input name="statusId" type="hidden" value="${titanCardAuthResponse.statusId}"/>
		<input name="cardPassCheckMsg" type="hidden" value="${titanCardAuthResponse.cardPassCheckMsg}"/>
		<input name="version" type="hidden" value="${titanCardAuthResponse.version}"/>
		<input name="signType" type="hidden" value="${titanCardAuthResponse.signType}"/>
		<input name="signMsg" type="hidden" value="${titanCardAuthResponse.signMsg}"/>
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