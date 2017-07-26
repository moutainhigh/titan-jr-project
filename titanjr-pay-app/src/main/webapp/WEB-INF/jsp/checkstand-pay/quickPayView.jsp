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
	<style>
		div{
			height: 200px;
		}
		.in{
			width: 200px;
		}
	</style>
<body>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<div>
	<table>
		<tr>
			<td>输入银行卡号</td>
			<td><input class="in" id="payerAcount" name="payerAcount" type="text"/>&nbsp;&nbsp;<input onclick="checkCardNo()" type="button" value="确认卡号"/></td>
		</tr>
		<tbody id="tbody" style="display: none;">
			<tr>
				<td>付款银行</td>
				<td><span id="bankName"></span>&nbsp;&nbsp;<span id="showPayerAccountType"></span></td>
				<input type="hidden" id="payerAccountType" />
				<input type="hidden" id="bankInfo" />
			</tr>
			<tr>
				<td>持卡人姓名</td>
				<td><input id="payerName" name="payerName" value="赵闪" type="text"/></td>
			</tr>
			<tr>
				<td>身份证号</td>
				<td><input id="idCode" name="idCode" value="411381198907135674" type="text"/></td>
			</tr>
			<tr>
				<td>银行预留手机号</td>
				<td><input id="payerPhone" name="payerPhone" value="18620352083" type="text"/></td>
			</tr>
			<tr>
				<td>信用卡安全码</td>
				<td><input id="safetyCode" name="safetyCode" value="450" type="text"/></td>
			</tr>
			<tr>
				<td>信用卡有效期至</td>
				<td><input id="validthru" name="validthru" value="0819" type="text"/></td>
			</tr>
			<tr>
				<td>手机验证码</td>
				<td><input id="checkCode" name="checkCode" type="text"/>&nbsp;&nbsp;<input id="getCheckCode" type="button" value="获取验证码"/></td>
			</tr>
			<tr>
				<td></td>
				<td><input id="recharge" name="recharge" type="button" value="确认支付"/></td>
			</tr>
		</tbody>
	</table>
</div>
</body>
<script type="text/javascript">
	function checkCardNo(inputObj){
		$.ajax({
			type : "post",
			url : "<%=basePath%>/quickPay/checkCardCanQuickPay.action",
               data: {
            	   busiCode: "106",
            	   signType: "1",
            	   version: "v1.1",
            	   merchantNo: "M000016",
            	   cardNo: $("#payerAcount").val()
               },
               dataType: "json",
               success: function (data) {
            	   if(data.success == false){
            		   alert(data.errMsg);
            		   $("#tbody").hide();
            		   return;
            	   }
            	   $("#bankName").text(data.bankName);
            	   $("#bankInfo").val(data.bankInfo);
            	   $("#payerAccountType").val(data.cardType);
            	   if(data.cardType == '10'){
            		   $("#showPayerAccountType").text("储蓄卡");
            	   }else if(data.cardType == '11'){
            		   $("#showPayerAccountType").text("信用卡");
            	   }else{
            		   $("#showPayerAccountType").text("未知类型");
            	   }
            	   
            	   $("#tbody").show();
            	   
               },complete:function(){
               	   top.F.loading.hide();
               }
         });
	}
</script>

</html>