<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通联网关支付</title>
</head>
<body>
	<form id="allinpayForm" action="${tlGateWayPayRequest.serverUrl }" method="post">
		<input type="hidden" name="inputCharset" value="${tlGateWayPayRequest.inputCharset }"/>
		<input type="hidden" name="pickupUrl" value="${tlGateWayPayRequest.pickupUrl }"/>
		<input type="hidden" name="receiveUrl" value="${tlGateWayPayRequest.receiveUrl }" />
		<input type="hidden" name="version" value="${tlGateWayPayRequest.version }"/>
		<input type="hidden" name="language" value="${tlGateWayPayRequest.language }" />
		<input type="hidden" name="signType" value="${tlGateWayPayRequest.signType }"/>
		<input type="hidden" name="merchantId" value="${tlGateWayPayRequest.merchantId }" />
		<input type="hidden" name="payerName" value="${tlGateWayPayRequest.payerName }"/>
		<input type="hidden" name="payerEmail" value="${tlGateWayPayRequest.payerEmail }" />
		<input type="hidden" name="payerTelephone" value="${tlGateWayPayRequest.payerTelephone }" />
		<input type="hidden" name="payerIDCard" value="${tlGateWayPayRequest.payerIDCard }" />
		<input type="hidden" name="pid" value="${tlGateWayPayRequest.pid }"/>
		<input type="hidden" name="orderNo" value="${tlGateWayPayRequest.orderNo }" />
		<input type="hidden" name="orderAmount" value="${tlGateWayPayRequest.orderAmount }"/>
		<input type="hidden" name="orderCurrency" value="${tlGateWayPayRequest.orderCurrency }" />
		<input type="hidden" name="orderDatetime" value="${tlGateWayPayRequest.orderDatetime }" />
		<input type="hidden" name="orderExpireDatetime" value="${tlGateWayPayRequest.orderExpireDatetime }"/>
		<input type="hidden" name="productName" value="${tlGateWayPayRequest.productName }" />
		<input type="hidden" name="productPrice" value="${tlGateWayPayRequest.productPrice }" />
		<input type="hidden" name="productNum" value="${tlGateWayPayRequest.productNum }"/>
		<input type="hidden" name="productId" value="${tlGateWayPayRequest.productId }" />
		<input type="hidden" name="productDesc" value="${tlGateWayPayRequest.productDesc }" />
		<input type="hidden" name="ext1" value="${tlGateWayPayRequest.ext1 }" />
		<input type="hidden" name="ext2" value="${tlGateWayPayRequest.ext2 }" />
		<input type="hidden" name="payType" value="${tlGateWayPayRequest.payType }" />
		<input type="hidden" name="issuerId" value="${tlGateWayPayRequest.issuerId }" />
		<input type="hidden" name="pan" value="${tlGateWayPayRequest.pan }" />
		<input type="hidden" name="tradeNature" value="${tlGateWayPayRequest.tradeNature }" />
		<input type="hidden" name="signMsg" value="${tlGateWayPayRequest.signMsg }" />
		
		<input type="submit" value="确认付款，到通联支付去啦"/>
	 </form>
</body>
<script type="text/javascript">
	//alert('${tlGateWayPayRequest.orderDatetime }');
	/* function submitForm(){
	   $("#allinpayForm").submit();
	}
	window.onload = submitForm; */
</script>
</html>