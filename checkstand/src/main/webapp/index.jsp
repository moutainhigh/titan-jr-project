<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/comm/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script type="text/javascript"
            src="<%=request.getScheme()%>://hres.fangcang.com/js/common/jquery/jquery.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getScheme()%>://hres.fangcang.com/js/common/jquery/jquery.form.js"></script>
    <title>调用上游付款支付</title>
</head>
<body>

<form action="<%=basePath %>/payment.shtml" name="pay_form" id="pay_form" method="post">
    <div class="pay">
        <table cellspacing="0" border="0">
            <tbody>
            <tr align="left">
                <td class="tdr">商户号</td>
                <td>
                    <input type="text" name="merchantNo" id="merchantNo" value="M10000245" class="input_t01"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">订单号</td>
                <td><input type="text" id="orderNo" value="TO216548100003" name="orderNo"/></td>
            </tr>
            <tr>
                <td class="tdr">交易金额</td>
                <td><input type="text" id="orderAmount" value="10200" name="orderAmount"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">币种</td>
                <td>
                    <input type="text" id="amtType" value="1" name="amtType"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">支付方式</td>
                <td>
                    <input type="text" value="01" id="payType" name="payType"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">所属银行信息</td>
                <td>
                    <input type="text" value="" id="bankInfo" name="bankInfo"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">页面回调地址</td>
                <td>
                    <input type="text" value="www.fangcang.com" id="pageUrl" name="pageUrl"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">后台通知地址</td>
                <td>
                    <input type="text" value="www.fangcang.com/titanjr-pay-app" id="notifyUrl" name="notifyUrl"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">订单创建时间</td>
                <td>
                    <input type="text" value="201711171633" id="orderTime" name="orderTime"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">订单过期时间</td>
                <td>
                    <input type="text" value="201711171712" id="orderExpireTime" name="orderExpireTime"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">订单备注</td>
                <td>
                    <input type="text" value="测试单" id="orderMark" name="orderMark"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名类型</td>
                <td>
                    <input type="text" value="1" id="signType" name="signType"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">业务号</td>
                <td>
                    <input type="text" value="2" id="busiCode" name="busiCode"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">版本号</td>
                <td>
                    <input type="text" value="v1.0" id="version" name="version"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">编码</td>
                <td>
                    <input type="text" id="charset" name="charset"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">付款方账号</td>
                <td>
                    <input type="text" value="AC00001" id="payerAcount" name="payerAcount"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名信息</td>
                <td>
                    <input type="text" value="DF76JGHC333DAS" id="signMsg" name="signMsg"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</form>
<tr>
    <td class="tdr"></td>
    <td>
        <button value="payTest" id="payButton" onclick="goPayPage()">去支付</button>
    </td>
</tr>



<form action="<%=basePath %>/payment.shtml" name="refund_form" id="refund_form" method="post">
    <div class="pay">
        <table cellspacing="0" border="0">
            <tbody>
            <tr align="left">
                <td class="tdr">商户号</td>
                <td>
                    <input type="text" name="merchantNo"  value="M10000245" class="input_t01"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">业务号</td>
                <td><input type="text" value="103" name="busiCode"/></td>
            </tr>
            <tr>
                <td class="tdr">退款单号</td>
                <td><input type="text" id="refundOrderno" value="RD2017112015780001" name="refundOrderno"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">订单号</td>
                <td>
                    <input type="text"  value="20171117153862040001" name="orderNo"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">订单时间</td>
                <td>
                    <input type="text" value="201711171633"  name="orderTime"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">退款金额</td>
                <td>
                    <input type="text" value="10200" id="refundAmount" name="refundAmount"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">版本号</td>
                <td>
                    <input type="text" value="v1.0" name="version"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名类型</td>
                <td>
                    <input type="text" value="01" name="signType"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名信息</td>
                <td>
                    <input type="text" value="F3DK56JHSDF09D"  name="signMsg"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</form>
<tr>
    <td class="tdr"></td>
    <td>
        <button value="payTest" id="refundButton" onclick="goRefundPage()">去退款</button>
    </td>
</tr>
</body>
<script type="text/javascript">
    function goPayPage() {
        $("#pay_form").submit();
    }
    function goRefundPage() {
        $("#refund_form").submit();
    }
</script>

</html>