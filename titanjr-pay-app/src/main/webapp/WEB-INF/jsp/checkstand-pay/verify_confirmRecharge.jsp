<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
	 <meta charset="UTF-8">
    <title>确认充值</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	</head>
<body>
    <c:if test="${not empty confirmRechargeRequest }">
<form action="http://www.fangcang.org/titanjr-pay-dev3/quickPay/confirmRecharge.action" name="verifyConfirmRecharge_form" id="verifyConfirmRecharge_form" method="post" >
    <input name="busiCode" type="hidden" value="${confirmRechargeRequest.busiCode}"/>
	<input name="signType" type="hidden" value="${confirmRechargeRequest.signType}"/>
	<input name="version" type="hidden" value="${confirmRechargeRequest.version}"/>
	<input name="merchantNo"  type="hidden" value="${confirmRechargeRequest.merchantNo}"/>
	<input name="orderNo" type="hidden" value="${confirmRechargeRequest.orderNo}"/>
	<input name="payType" type="hidden" value="${confirmRechargeRequest.payType}"/>
    <input name="checkCode" type="text" value=""/>
</form>
</c:if>
    <input type="button" value="提交" onclick="submitform()"/>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
</body>

<script type="text/javascript">
function submitform(){
	   $("#verifyConfirmRecharge_form").submit();
	}

</script>

</html>