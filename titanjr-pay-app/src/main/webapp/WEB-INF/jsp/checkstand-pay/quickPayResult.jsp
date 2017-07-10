<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
	 <meta charset="UTF-8">
    <title>快捷支付</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	</head>
<body>
<jsp:include page="/comm/static-js.jsp"></jsp:include>

<form action="<%=basePath%>/quickPay/cardSceurityVerify.shtml?orderNo=${quickPaymentResponse.orderNo}" name="cardverify_form" id="cardverify_form" method="post" >
</form>
<c:if test="${quickPaymentResponse.certificate ne 1}">
	<form action="<%=basePath%>/quickPay/confirmRecharge.shtml" name="recharge_form" id="recharge_form" method="post" >
		<input name="busiCode" type="hidden" value="109"/>
		<input name="signType" type="hidden" value="1"/>
		<input name="version" type="hidden" value="v1.1"/>
		<input name="merchantNo"  type="hidden" value="M000016"/>
		<input name="orderNo" type="hidden" value="${quickPaymentResponse.orderNo}"/>
		<input name="payType" type="hidden" value="41"/>
	    <input name="checkCode" type="text" value=""/>
	    <input type="button" value="确认充值" onclick="submitform_recharge()"/>
	</form>
</c:if>
	
</body>
<script type="text/javascript">
alert("${quickPaymentResponse.certificate ne 1}");
//卡密校验
function submitform_cardverify(){
	   $("#cardverify_form").submit();
	}
//确认充值
function submitform_recharge(){
	   $("#recharge_form").submit();
	}
debugger;
if('${quickPaymentResponse.certificate }' == '1'){
	window.onload = submitform_cardverify;
}
</script>

</html>