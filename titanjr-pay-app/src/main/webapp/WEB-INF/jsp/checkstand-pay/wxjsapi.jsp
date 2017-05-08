<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">

		var payJson = '${payJson}';
		
		//转换成js对象
		eval("var payObj = " +payJson);
		
		function onBridgeReady() {
			WeixinJSBridge.invoke('getBrandWCPayRequest', payObj , function(res) {
				//成功
				if (res.err_msg == "get_brand_wcpay_request:ok") {
					window.location.href='${successJumpUrl}';
				}else if(res.err_msg == "get_brand_wcpay_request:cancel") 
				{
					window.location.href='${failJumpUrl}';
				}else if(res.err_msg == "get_brand_wcpay_request:fail") 
				{
					window.location.href='${failJumpUrl}';
				}
			});
		}
		if (typeof WeixinJSBridge == "undefined") {
			if (document.addEventListener) {
				document.addEventListener('WeixinJSBridgeReady', onBridgeReady,
						false);
			} else if (document.attachEvent) {
				document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
				document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
			}
		} else {
			onBridgeReady();
		}
	</script>

</body>
</html>