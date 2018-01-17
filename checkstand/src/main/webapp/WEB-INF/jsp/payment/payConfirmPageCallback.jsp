<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付结果页面</title>
</head>
<body>
	<form id="payConfrimPageForm" action="${payConfirmPageUrl }" method="post">
		<input type="hidden" name="merchantNo" value="${confirmPage.merchantNo }"/>
		<input type="hidden" name="orderNo" value="${confirmPage.orderNo }"/>
		<input type="hidden" name="orderAmount" value="${confirmPage.orderAmount }" />
		<input type="hidden" name="orderTime" value="${confirmPage.orderTime }"/>
		<input type="hidden" name="payStatus" value="${confirmPage.payStatus }" />
		<input type="hidden" name="payOrderNo" value="${confirmPage.payOrderNo }"/>
		<input type="hidden" name="payAmount" value="${confirmPage.payAmount }" />
		<input type="hidden" name="orderPayTime" value="${confirmPage.orderPayTime }"/>
		<input type="hidden" name="payMsg" value="${confirmPage.payMsg }" />
		<input type="hidden" name="version" value="${confirmPage.version }" />
		<input type="hidden" name="signType" value="${confirmPage.signType }" />
		<input type="hidden" name="signMsg" value="${confirmPage.signMsg }"/>
		<input type="hidden" name="payType" value="${confirmPage.payType }" />
		<input type="hidden" name="bankCode" value="${confirmPage.bankCode }"/>
		<input type="hidden" name="expand" value="${confirmPage.expand }" />
		<input type="hidden" name="expand2" value="${confirmPage.expand2 }" />
		<input type="hidden" name="userid" value="${confirmPage.userid }"/>
		<input type="hidden" name="freezereqId" value="${confirmPage.freezereqId }" />
	 </form>
</body>

<jsp:include page="/common/static-js.jsp"></jsp:include>
<script type="text/javascript">
	//alert('${payConfirmPageUrl }');
	function submitForm(){
	   $("#payConfrimPageForm").submit();
	}
	window.onload = submitForm;
</script>
</html>