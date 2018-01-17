<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
	 <meta charset="UTF-8">
    <title>融数支付</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	</head>
<body>
    <c:if test="${not empty rechargeDataDTO }">
    <!-- 支持繁体url,防止繁体插件给融数的url加big5前缀 -->
    <span style="display:none;" id="pay_form_action_gateWayUrl"><c:out value="${rechargeDataDTO.gateWayUrl}"></c:out></span>
    <span style="display:none;" id="pay_form_pageUrl"><c:out value="${rechargeDataDTO.pageUrl}"></c:out></span>
    <span style="display:none;" id="pay_form_notifyUrl"><c:out value="${rechargeDataDTO.notifyUrl}"></c:out></span>
<form action="" name="pay_form" id="pay_form" method="post" >

    <input name="merchantNo" type="hidden" value="${rechargeDataDTO.merchantNo}"/>
	<input name="orderNo" type="hidden" value="${rechargeDataDTO.orderNo}"/>
	<input name="orderAmount" type="hidden" value="${rechargeDataDTO.orderAmount}"/>
	<input name="amtType"  type="hidden" value="${rechargeDataDTO.amtType}"/>
	<input name="payType" type="hidden" value="${rechargeDataDTO.payType}"/>
	<input name="bankInfo" type="hidden" value="${rechargeDataDTO.bankInfo}"/>
    <input name="pageUrl" id="pageUrl" type="hidden" />
	<input name="notifyUrl" id="notifyUrl" type="hidden" />
	<input name="orderTime" type="hidden" value="${rechargeDataDTO.orderTime}"/>
	<input name="orderExpireTime" type="hidden" value="${rechargeDataDTO.orderExpireTime}"/>
	<input name="orderMark" type="hidden" value="${rechargeDataDTO.orderMark}"/>
	<input name="signType" type="hidden" value="${rechargeDataDTO.signType}"/>
	<input name="busiCode" type="hidden" value="${rechargeDataDTO.busiCode}"/>
	<input name="version" type="hidden" value="${rechargeDataDTO.version}"/>
	<input name="charset" type="hidden" value="${rechargeDataDTO.charset}"/>
	<input name="payerAcount" type="hidden" value="${rechargeDataDTO.payerAcount}"/>
	<input name="signMsg" type="hidden" value="${rechargeDataDTO.signMsg}"/>
</form>
</c:if>
<form action="<%=basePath%>/account/error_cashier.action" name="error_cashier"  id="error_cashier" method="post">
  <input name="msg" type="hidden" value="${msg}"/>
</form>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
</body>
<script type="text/javascript">
if('${result}'=="false"){
	   function submitErrorform(){
	   
		   $("#error_cashier").submit();
		}
	   window.onload = submitErrorform;
}else{
	   function submitform(){
	   		$("#pay_form").attr({"action":$("#pay_form_action_gateWayUrl").html()});
	   		$("#pageUrl").val($("#pay_form_pageUrl").html());
	   		$("#notifyUrl").val($("#pay_form_notifyUrl").html());
			$("#pay_form").submit();
		}
		 window.onload = submitform;
}

</script>

</html>