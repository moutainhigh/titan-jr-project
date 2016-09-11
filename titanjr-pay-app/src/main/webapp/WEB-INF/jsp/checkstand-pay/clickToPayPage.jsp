<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/comm/taglib.jsp"%>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
</head>
<body>
<span href="" class="J_sell1" style="margin:50px; display: block; cursor: pointer;">点我！</span>
<span href="" class="J_sell2" style="margin:50px; display: block; cursor: pointer;">再点我！</span>

<form id="orderPayTest" name="orderPayTest" action="http://local.fangcang.com:8080/TFS/pay/toCashierDesk.shtml"
      method="post" target="_blank">
    <input type="hidden" id="commodityId" name="commodityId" value="321312312"/>
    <input type="hidden" id="cityCode" name="cityCode" value="21312312"/>
</form>

<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script src="<%=cssSaasPath%>/js/move.js"></script>

<script>
    $('.J_sell1').on('click', function () {
        window.top.createIframeDialog({
            url: 'http://local.fangcang.com:8080/TFS/trade/showCashierDesk.shtml',//参数直接跟在地址后面，post方式需要跟前端确认如何做
        });
        return false;
    });
    $('.J_sell2').on('click', function () {
        window.top.createIframeDialog({
            url: 'http://local.fangcang.com:8080/TFS/trade/showCashierDesk.shtml',
        });
        return false;
    });
</script>
</body>
</html>