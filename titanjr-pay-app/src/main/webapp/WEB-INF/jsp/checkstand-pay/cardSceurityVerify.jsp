<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
	 <meta charset="UTF-8">
    <title>卡密校验</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	</head>
<body>
<c:if test="${not empty cardSceurityVerifyRequest }">
	<form action="${cardSceurityVerifyRequest.gateWayURL}" name="cardSceurityVerify_form" id="cardSceurityVerify_form" method="post" >
	    <input name="busiCode" type="hidden" value="${cardSceurityVerifyRequest.busiCode}"/>
		<input name="signType" type="hidden" value="${cardSceurityVerifyRequest.signType}"/>
		<input name="version" type="hidden" value="${cardSceurityVerifyRequest.version}"/>
		<input name="merchantNo"  type="hidden" value="${cardSceurityVerifyRequest.merchantNo}"/>
		<input name="orderNo" type="hidden" value="${cardSceurityVerifyRequest.orderNo}"/>
		<input name="idCode" type="hidden" value="${cardSceurityVerifyRequest.idCode}"/>
		<input name="bindCardId" type="hidden" value="${cardSceurityVerifyRequest.bindCardId}"/>
	    <input name="terminalType" type="hidden" value="${cardSceurityVerifyRequest.terminalType}"/>
		<input name="cardCheckPageUrl" type="hidden" value="${cardSceurityVerifyRequest.cardCheckPageUrl}"/>
		<input name="cardChecknotifyUrl" type="hidden" value="${cardSceurityVerifyRequest.cardChecknotifyUrl}"/>
		<input name="signMsg" type="hidden" value="${cardSceurityVerifyRequest.signMsg}"/>
	</form>
</c:if>

<jsp:include page="/comm/static-js.jsp"></jsp:include>
</body>
<script type="text/javascript">
function submitform(){
	   $("#cardSceurityVerify_form").submit();
	}
window.onload = submitform;

</script>

</html>