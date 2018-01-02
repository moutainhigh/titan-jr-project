<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
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
                    <input type="text" name="merchantNo" id="merchantNo" value="100020091218001" class="input_t01"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">订单号</td>
                <td><input type="text" id="orderNo" value="TJO2017112900001" name="orderNo"/>* &nbsp;&nbsp;
                    <input type="button" value="刷新" onclick="refreshOrderNo()" /></td>
            </tr>
            <tr>
                <td class="tdr">交易金额</td>
                <td><input type="text" id="orderAmount" value="100" name="orderAmount"/>*</td>
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
                    <input type="text" value="11" id="payType" name="payType"/>* 11个人银行    12企业银行    13信用卡    28微信公众号支付    30微信扫码支付Url  32支付宝扫码支付url  41快捷支付
                </td>
            </tr>
            <tr>
                <td class="tdr">所属银行信息</td>
                <td>
                    <input type="text" value="vbank" id="bankInfo" name="bankInfo"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">页面回调地址</td>
                <td>
                    <input type="text" value="http://www.fangcang.org/titanjr-pay-dev3/payment/payConfirmPage.action" id="pageUrl" name="pageUrl"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">后台通知地址</td>
                <td>
                    <input type="text" value="http://www.fangcang.org/titanjr-pay-dev3/payment/notify.action" id="notifyUrl" name="notifyUrl"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">订单创建时间</td>
                <td>
                    <input type="text" value="20171129103151" id="orderTime" name="orderTime"/>* &nbsp;&nbsp;
                    <input type="button" value="刷新" onclick="refreshOrderTime()" />
                </td>
            </tr>
            <tr>
                <td class="tdr">订单过期时间</td>
                <td>
                    <input type="text" value="201711171712" id="orderExpireTime" name="orderExpireTime"/>
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
                    <input type="text" value="1" id="charset" name="charset"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名信息</td>
                <td>
                    <input type="text" value="DF76JGHC333DAS" id="signMsg" name="signMsg"/>*
                </td>
            </tr>
            <!-- 快捷支付 -->
            <!-- <tr>
                <td class="tdr">支付账号</td>
                <td>
                    <input type="text" value="" id="payerAcount" name="payerAcount"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">银行卡类型</td>
                <td>
                    <input type="text" value="" id="payerAccountType" name="payerAccountType"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">身份证号</td>
                <td>
                    <input type="text" value="" id="idCode" name="idCode"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">信用卡有效期</td>
                <td>
                    <input type="text" value="" id="validthru" name="validthru"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">信用卡安全码</td>
                <td>
                    <input type="text" value="" id="safetyCode" name="safetyCode"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">终端IP</td>
                <td>
                    <input type="text" value="" id="terminalIp" name="terminalIp"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">终端类型</td>
                <td>
                    <input type="text" value="" id="terminalType" name="terminalType"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">终端信息</td>
                <td>
                    <input type="text" value="" id="terminalInfo" name="terminalInfo"/>*
                </td>
            </tr> -->
            
            <tr>
                <td class="tdr">操作类型</td>
                <td>
                    <input type="text" value="1001" id="signMsg" name="operateType"/>* 1001:支付请求；1002:支付查询；1003:退款请求；1004:退款查询；1005账户交易
                </td>
            </tr>
            
			<tr>
			    <td class="tdr"><button value="payTest" id="payButton" onclick="goPayPage()">去支付</button></td>
			    <td></td>
			</tr>
            </tbody>
        </table>
    </div>
</form><br/><br/>


<form action="<%=basePath %>/payment.shtml" name="payQuery_form" id="payQuery_form" method="post">
    <div class="payQuery">
        <table cellspacing="0" border="0">
            <tbody>
            <tr align="left">
                <td class="tdr">商户号</td>
                <td>
                    <input type="text" name="merchantNo" id="merchantNo" value="100020091218001" class="input_t01"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">订单号</td>
                <td><input type="text" id="orderNo" value="TJO2017112900001" name="orderNo"/></td>
            </tr>
            <tr>
                <td class="tdr">订单创建时间</td>
                <td>
                    <input type="text" value="20171129103151" id="orderTime" name="orderTime"/>
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
                    <input type="text" id="busiCode" name="busiCode"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">版本号</td>
                <td>
                    <input type="text" value="v1.0" id="version" name="version"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名信息</td>
                <td>
                    <input type="text" value="DF76JGHC333DAS" id="signMsg" name="signMsg"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">操作类型</td>
                <td>
                    <input type="text" value="1002" id="signMsg" name="operateType"/>
                </td>
            </tr>
            <tr>
			    <td class="tdr"><button id="payQueryButton" onclick="goPayQuery()">查询支付结果</button></td>
			    <td>
			    </td>
			</tr>
            </tbody>
        </table>
    </div>
</form><br/><br/>



<form action="<%=basePath %>/payment.shtml" name="refund_form" id="refund_form" method="post">
    <div class="refund">
        <table cellspacing="0" border="0">
            <tbody>
            <tr align="left">
                <td class="tdr">商户号</td>
                <td>
                    <input type="text" name="merchantNo"  value="100020091218001" class="input_t01"/>
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
            <tr>
                <td class="tdr">操作类型</td>
                <td>
                    <input type="text" value="1003" id="signMsg" name="operateType"/>
                </td>
            </tr>
            <tr>
			    <td class="tdr"><button value="payTest" id="refundButton" onclick="goRefundPage()">去退款</button></td>
			    <td>
			    </td>
			</tr>
            </tbody>
        </table>
    </div>
</form><br/><br/>



<form action="<%=basePath %>/payment.shtml" name="refundQuery_form" id="refundQuery_form" method="post">
    <div class="refundQuery">
        <table cellspacing="0" border="0">
            <tbody>
            <tr align="left">
                <td class="tdr">商户号</td>
                <td>
                    <input type="text" name="merchantNo" id="merchantNo" value="100020091218001" class="input_t01"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">订单号</td>
                <td><input type="text" id="orderNo" value="TJO2017112900001" name="orderNo"/></td>
            </tr>
            <tr>
                <td class="tdr">退款单号</td>
                <td><input type="text" id="refundOrderno" value="TD2017112900001" name="refundOrderno"/></td>
            </tr>
            <tr>
                <td class="tdr">退款金额</td>
                <td><input type="text" id="refundAmount" value="100" name="refundAmount"/></td>
            </tr>
            <tr>
                <td class="tdr">订单创建时间</td>
                <td>
                    <input type="text" value="20171129103151" id="orderTime" name="orderTime"/>
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
                    <input type="text" value="104" id="busiCode" name="busiCode"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">版本号</td>
                <td>
                    <input type="text" value="v1.0" id="version" name="version"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名信息</td>
                <td>
                    <input type="text" value="DF76JGHC333DAS" id="signMsg" name="signMsg"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">操作类型</td>
                <td>
                    <input type="text" value="1004" id="signMsg" name="operateType"/>
                </td>
            </tr>
            <tr>
			    <td class="tdr"><button id="payQueryButton" onclick="refundQuery()">查询退款结果</button></td>
			    <td></td>
			</tr>
            </tbody>
        </table>
    </div>
</form><br/><br/>


<form action="<%=basePath %>/payment.shtml" name="agentPay_form" id="agentPay_form" method="post">
    <div class="agentPay">
        <table cellspacing="0" border="0">
            <tbody>
            <tr align="left">
                <td class="tdr">商户号</td>
                <td>
                    <input type="text" name="merchantNo" id="merchantNo" value="100020091218001" class="input_t01"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">商户单号</td>
                <td><input type="text" id="agentPay_orderNo" value="" name="orderNo"/>
                    <input type="button" value="刷新" onclick="refreshAgentPayOrderNo()" /></td>
            </tr>
            <tr>
                <td class="tdr">交易金额</td>
                <td><input type="text" id="tradeAmount" value="1" name="tradeAmount"/></td>
            </tr>
            <tr>
                <td class="tdr">币种</td>
                <td><input type="text" id="currency" value="CNY" name="currency"/></td>
            </tr>
            <tr>
                <td class="tdr">交易代码</td>
                <td><input type="text" id="tradeCode" value="100014" name="tradeCode"/></td>
            </tr>
            <tr>
                <td class="tdr">提交时间</td>
                <td>
                    <input type="text" id="submitTime" name="submitTime"/>
                    <input type="button" value="刷新" onclick="refreshSubmitTime()" />
                </td>
            </tr>
            <tr>
                <td class="tdr">银行标示</td>
                <td>
                    <input type="text" value="icbc" id="bankInfo" name="bankInfo"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">银行卡类型</td>
                <td>
                    <input type="text" value="00" id="accountType" name="accountType"/> 00：银行卡
                </td>
            </tr>
            <tr>
                <td class="tdr">银行卡号</td>
                <td>
                    <input type="text" value="6212264000052615040" id="accountNo" name="accountNo"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">账户名</td>
                <td>
                    <input type="text" value="奥特曼" id="accountName" name="accountName"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">帐户属性</td>
                <td>
                    <input type="text" value="0" id="accountProperty" name="accountProperty"/>0私人，1公司
                </td>
            </tr>
            <tr>
                <td class="tdr">证件号</td>
                <td>
                    <input type="text" value="421381177110306452" id="accountId" name="accountId"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">证件类型</td>
                <td>
                    <input type="text" value="0" id="idType" name="idType"/>0：身份证
                </td>
            </tr>
            <tr>
                <td class="tdr">操作类型</td>
                <td>
                    <input type="text" value="1005" id="signMsg" name="operateType"/>* 1005账户交易
                </td>
            </tr>
            <tr>
			    <td class="tdr"><button id="agentPayButton" onclick="goAgentPay()">代付</button></td>
			    <td>
			    </td>
			</tr>
            </tbody>
        </table>
    </div>
</form><br/><br/>

<form action="<%=basePath %>/payment.shtml" name="agentQuery_form" id="agentQuery_form" method="post">
    <div class="agentQuery">
        <table cellspacing="0" border="0">
            <tbody>
            <tr align="left">
                <td class="tdr">商户号</td>
                <td>
                    <input type="text" name="merchantNo" id="merchantNo" value="100020091218001" class="input_t01"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">商户单号</td>
                <td><input type="text" id="orderNo" value="TJO2017112900001" name="orderNo"/>若不填时间必填</td>
            </tr>
            <tr>
                <td class="tdr">交易代码</td>
                <td><input type="text" id="tradeCode" value="200004" name="tradeCode"/></td>
            </tr>
            <tr>
                <td class="tdr">交易状态</td>
                <td>
                    <input type="text" value="2" id="tradeStatus" name="tradeStatus"/>0成功，1失败，2全部，3退票，4代付失败退款，5代付退票退款，6委托扣款，7提现
                </td>
            </tr>
            <tr>
                <td class="tdr">查询类型</td>
                <td>
                    <input type="text" value="1" id="queryType" name="queryType"/>0.按完成日期1.按提交日期
                </td>
            </tr>
            <tr>
                <td class="tdr">开始时间</td>
                <td>
                    <input type="text" id="startDate" name="startDate"/>若不填则orderNo则必填
                </td>
            </tr>
            <tr>
                <td class="tdr">结束时间</td>
                <td>
                    <input type="text" id="endDate" name="endDate"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">操作类型</td>
                <td>
                    <input type="text" value="1005" id="signMsg" name="operateType"/>* 1005账户交易
                </td>
            </tr>
            <tr>
			    <td class="tdr"><button id="agentQueryButton" onclick="agentQuery()">代付查询</button></td>
			    <td>
			    </td>
			</tr>
            </tbody>
        </table>
    </div>
</form><br/><br/>


<form action="<%=basePath %>/payment.shtml" name="agentDownload_form" id="agentDownload_form" method="post">
    <div class="agentDownload">
        <table cellspacing="0" border="0">
            <tbody>
            <tr align="left">
                <td class="tdr">商户号</td>
                <td>
                    <input type="text" name="merchantNo" id="merchantNo" value="100020091218001" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">流水号</td>唯一标示
                <td>
                    <input type="text" name="serialNo" id="serialNo" class="input_t01"/>* 
                    	<input type="button" value="刷新" onclick="refreshSerialNo()" />
                </td>
            </tr>
            <tr>
                <td class="tdr">交易代码</td>
                <td><input type="text" id="tradeCode" value="200002" name="tradeCode"/>* </td>
            </tr>
            <tr>
                <td class="tdr">交易状态</td>
                <td>
                    <input type="text" value="2" id="tradeStatus" name="tradeStatus"/>*  0成功，1失败，2全部，3退票，4代付失败退款，5代付退票退款，6委托扣款，7提现
                </td>
            </tr>
            <tr>
                <td class="tdr">查询类型</td>
                <td>
                    <input type="text" value="1" id="queryType" name="queryType"/>*  0.按完成日期1.按提交日期
                </td>
            </tr>
            <tr>
                <td class="tdr">开始时间</td>* 
                <td>
                    <input type="text" id="startDate" name="startDate"/>* 
                </td>
            </tr>
            <tr>
                <td class="tdr">结束时间</td>
                <td>
                    <input type="text" id="endDate" name="endDate"/>* 
                </td>
            </tr>
            <tr>
                <td class="tdr">操作类型</td>
                <td>
                    <input type="text" value="1005" id="signMsg" name="operateType"/>* 1005账户交易
                </td>
            </tr>
            <tr>
			    <td class="tdr"><button id="agentDownloadButton" onclick="agentDownload()">对账文件下载</button></td>
			    <td>
			    </td>
			</tr>
            </tbody>
        </table>
    </div>
</form><br/><br/>

</body>

<script type="text/javascript">
    function goPayPage() {
        $("#pay_form").submit();
    }
    function goPayQuery() {
        $("#payQuery_form").submit();
    }
    function goRefundPage() {
        $("#refund_form").submit();
    }
    function refundQuery() {
        $("#refundQuery_form").submit();
    }
    
    function goAgentPay(){
    	$("#agentPay_form").submit();
    }
    function agentQuery() {
        $("#agentQuery_form").submit();
    }
    function agentDownload(){
    	$("#agentDownload_form").submit();
    }

    
    function refreshOrderNo(){
    	$("#orderNo").val("TJO"+getDateTimeStr());
    }
    
    function refreshOrderTime(){
        $("#orderTime").val(getDateTimeStr());
    }
    
    function refreshAgentPayOrderNo(){
    	$("#agentPay_orderNo").val("TJO"+getDateTimeStr());
    }
    
    function refreshSubmitTime(){
    	$("#submitTime").val(getDateTimeStr());
    }
    
    function refreshSerialNo(){
    	$("#serialNo").val("100020091218001-"+getDateTimeStr());
    }
    
    function getDateTimeStr(){
    	var date = new Date();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        var hours = date.getHours();
        var minutes = date.getMinutes();
        var seconds = date.getSeconds();
        
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        if (hours >= 0 && hours <= 9) {
        	hours = "0" + hours;
        }
        if (minutes >= 0 && minutes <= 9) {
        	minutes = "0" + minutes;
        }
        if (seconds >= 0 && seconds <= 9) {
        	seconds = "0" + seconds;
        }
        return currentdate = date.getFullYear()+ "" + month + "" + strDate
                + "" + hours + "" + minutes + "" + seconds;
    }
    
</script>

</html>