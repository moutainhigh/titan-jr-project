<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通联网关支付</title>
</head>
<body>
	<form id="allinpayForm" action="${tlNetBankPayRequest.serverUrl }" method="post">
		<input type="hidden" name="inputCharset" value="${tlNetBankPayRequest.inputCharset }"/>
		<input type="hidden" name="pickupUrl" value="${tlNetBankPayRequest.pickupUrl }"/>
		<input type="hidden" name="receiveUrl" value="${tlNetBankPayRequest.receiveUrl }" />
		<input type="hidden" name="version" value="${tlNetBankPayRequest.version }"/>
		<input type="hidden" name="language" value="${tlNetBankPayRequest.language }" />
		<input type="hidden" name="signType" value="${tlNetBankPayRequest.signType }"/>
		<input type="hidden" name="merchantId" value="${tlNetBankPayRequest.merchantId }" />
		<input type="hidden" name="payerName" value="${tlNetBankPayRequest.payerName }"/>
		<input type="hidden" name="payerEmail" value="${tlNetBankPayRequest.payerEmail }" />
		<input type="hidden" name="payerTelephone" value="${tlNetBankPayRequest.payerTelephone }" />
		<input type="hidden" name="payerIDCard" value="${tlNetBankPayRequest.payerIDCard }" />
		<input type="hidden" name="pid" value="${tlNetBankPayRequest.pid }"/>
		<input type="hidden" name="orderNo" value="${tlNetBankPayRequest.orderNo }" />
		<input type="hidden" name="orderAmount" value="${tlNetBankPayRequest.orderAmount }"/>
		<input type="hidden" name="orderCurrency" value="${tlNetBankPayRequest.orderCurrency }" />
		<input type="hidden" name="orderDatetime" value="${tlNetBankPayRequest.orderDatetime }" />
		<input type="hidden" name="orderExpireDatetime" value="${tlNetBankPayRequest.orderExpireDatetime }"/>
		<input type="hidden" name="productName" value="${tlNetBankPayRequest.productName }" />
		<input type="hidden" name="productPrice" value="${tlNetBankPayRequest.productPrice }" />
		<input type="hidden" name="productNum" value="${tlNetBankPayRequest.productNum }"/>
		<input type="hidden" name="productId" value="${tlNetBankPayRequest.productId }" />
		<input type="hidden" name="productDesc" value="${tlNetBankPayRequest.productDesc }" />
		<input type="hidden" name="ext1" value="${tlNetBankPayRequest.ext1 }" />
		<input type="hidden" name="ext2" value="${tlNetBankPayRequest.ext2 }" />
		<input type="hidden" name="payType" value="${tlNetBankPayRequest.payType }" />
		<input type="hidden" name="issuerId" value="${tlNetBankPayRequest.issuerId }" />
		<input type="hidden" name="pan" value="${tlNetBankPayRequest.pan }" />
		<input type="hidden" name="tradeNature" value="${tlNetBankPayRequest.tradeNature }" />
		<input type="hidden" name="signMsg" value="${tlNetBankPayRequest.signMsg }" />
		
		<!-- <input type="submit" value="确认付款，到通联支付去啦"/> -->
	 </form>
</body>

<jsp:include page="/common/static-js.jsp"></jsp:include>
<script type="text/javascript">
	//alert('${tlNetBankPayRequest.orderDatetime }');
	function submitForm(){
	   $("#allinpayForm").submit();
	}
	window.onload = submitForm;
</script>
</html>