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
                <td class="tdr">版本号</td>
                <td>
                    <input type="text" value="v1.0" id="version" name="version"/>含快捷支付：v1.1
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
            <tr>
                <td class="tdr">---------------------</td>
                <td>快捷支付相关参数---------------------</td>
            </tr>
            <tr>
                <td class="tdr">银行卡号</td>
                <td>
                    <input type="text" value="6214242710498301" id="payerAccount" name="payerAccount"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">银行卡类型</td>
                <td>
                    <input type="text" value="10" id="payerAccountType" name="payerAccountType"/>* 10储蓄卡  11信用卡
                </td>
            </tr>
            <tr>
                <td class="tdr">支付人</td>
                <td>
                    <input type="text" value="韩梅梅" id="payerName" name="payerName"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">身份证号</td>
                <td>
                    <input type="text" value="210302196001012114" id="idCode" name="idCode"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">手机号</td>
                <td>
                    <input type="text" value="13220482188" id="payerPhone" name="payerPhone"/>*
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
                    <input type="text" value="192.168.0.77" id="terminalIp" name="terminalIp"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">终端类型</td>
                <td>
                    <input type="text" value="web" id="terminalType" name="terminalType"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">终端信息</td>
                <td>
                    <input type="text" value="IMEI_MAC" id="terminalInfo" name="terminalInfo"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">---------------------</td>
                <td>快捷支付相关参数---------------------</td>
            </tr>
            <tr>
                <td class="tdr">业务编码</td>
                <td>
                    <input type="text" value="101" id="busiCode" name="busiCode"/>* 101:支付请求
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
                    <input type="text" name="merchantNo" id="merchantNo" value="100020091218001" class="input_t01"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">订单号</td>
                <td><input type="text" id="orderNo" value="TJO2017112900001" name="orderNo"/></td>*
            </tr>
            <tr>
                <td class="tdr">订单创建时间</td>
                <td>
                    <input type="text" value="20171129103151" id="orderTime" name="orderTime"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">签名类型</td>
                <td>
                    <input type="text" value="1" id="signType" name="signType"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">版本号</td>
                <td>
                    <input type="text" value="v1.0" id="version" name="version"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">签名信息</td>
                <td>
                    <input type="text" value="DF76JGHC333DAS" id="signMsg" name="signMsg"/>*
                </td>
            </tr>
            <tr>
                <td class="tdr">业务编码</td>
                <td>
                    <input type="text" value="102" id="busiCode" name="busiCode"/>* 102支付查询
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
                <td class="tdr">业务编码</td>
                <td>
                    <input type="text" value="103" id="busiCode" name="busiCode"/>103:退款请求
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
                <td class="tdr">业务编码</td>
                <td>
                    <input type="text" value="104" id="busiCode" name="busiCode"/>104:退款查询
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
                <td class="tdr">商户单号（批次号）</td>
                <td><input type="text" id="agentPay_orderNo" value="" name="orderNo"/>
                    <input type="button" value="刷新" onclick="refreshAgentPayOrderNo()" /></td>
            </tr>
            <tr>
                <td class="tdr">序号</td>
                <td><input type="text" id="number" value="123456" name="number"/>融宝必填：不能大于6个字符 </td>
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
                <td><input type="text" id="tradeCode" value="100014" name="tradeCode"/>100014通联代付；300001融宝代付</td>
            </tr>
            <!-- <tr>
                <td class="tdr">提交时间</td>
                <td>
                    <input type="text" id="submitTime" name="submitTime"/>
                    <input type="button" value="刷新" onclick="refreshSubmitTime()" />
                </td>
            </tr> -->
            <tr>
                <td class="tdr">银行标示</td>
                <td>
                    <input type="text" value="icbc" id="bankInfo" name="bankInfo"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">银行名称</td>
                <td>
                    <input type="text" value="工商银行" id="bankName" name="bankName"/>
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
                    <input type="text" value="421381177110306452" id="idCode" name="idCode"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">证件类型</td>
                <td>
                    <input type="text" value="0" id="idType" name="idType"/>0：身份证
                </td>
            </tr>
            <tr>
                <td class="tdr">业务编码</td>
                <td>
                    <input type="text" value="105" id="busiCode" name="busiCode"/>* 105账户交易
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
                <td><input type="text" id="orderNo" value="TJO2017112900001" name="orderNo"/>通联：若不填时间必填；融宝必填</td>
            </tr>
            <tr>
                <td class="tdr">交易代码</td>
                <td><input type="text" id="tradeCode" value="200004" name="tradeCode"/></td>通联：200004 融宝：300002
            </tr>
            <tr>
                <td class="tdr">交易状态</td>
                <td>
                    <input type="text" value="2" id="tradeStatus" name="tradeStatus"/>通联必填：0成功，1失败，2全部，3退票，4代付失败退款，5代付退票退款，6委托扣款，7提现
                </td>
            </tr>
            <tr>
                <td class="tdr">查询类型</td>
                <td>
                    <input type="text" value="1" id="queryType" name="queryType"/>通联必填  0.按完成日期1.按提交日期
                </td>
            </tr>
            <tr>
                <td class="tdr">查询日期</td>
                <td>
                    <input type="text" value="2018-02-06" id="transDate" name="transDate"/>融宝必填  yyyy-MM-dd
                </td>
            </tr>
            <tr>
                <td class="tdr">序号</td>
                <td>
                    <input type="text" value="123456" id="number" name="number"/>融宝必填：不能大于6个字符
                </td>
            </tr>
            <tr>
                <td class="tdr">开始时间</td>
                <td>
                    <input type="text" id="startDate" name="startDate"/>通联：若不填则orderNo则必填
                </td>
            </tr>
            <tr>
                <td class="tdr">结束时间</td>
                <td>
                    <input type="text" id="endDate" name="endDate"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">业务编码</td>
                <td>
                    <input type="text" value="105" id="busiCode" name="busiCode"/>* 105账户交易
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
                <td class="tdr">流水号</td>
                <td>
                    <input type="text" name="serialNo" id="serialNo" class="input_t01"/> 
                    	<input type="button" value="刷新" onclick="refreshSerialNo()" />通联必填
                </td>
            </tr>
            <tr>
                <td class="tdr">交易代码</td>
                <td><input type="text" id="tradeCode" value="200002" name="tradeCode"/>* 通联:200002 融宝:300003</td>
            </tr>
            <tr>
                <td class="tdr">交易状态</td>
                <td>
                    <input type="text" value="2" id="tradeStatus" name="tradeStatus"/>通联必填   0成功，1失败，2全部，3退票，4代付失败退款，5代付退票退款，6委托扣款，7提现
                </td>
            </tr>
            <tr>
                <td class="tdr">查询类型</td>
                <td>
                    <input type="text" value="1" id="queryType" name="queryType"/>通联必填    0.按完成日期1.按提交日期
                </td>
            </tr>
            <tr>
                <td class="tdr">开始时间</td> 
                <td>
                    <input type="text" id="startDate" name="startDate"/>通联必填 yyyyMMddHHmmss
                </td>
            </tr>
            <tr>
                <td class="tdr">结束时间</td>
                <td>
                    <input type="text" id="endDate" name="endDate"/>通联必填 
                </td>
            </tr>
            <tr>
                <td class="tdr">交易日期</td>
                <td>
                    <input type="text" id="tradeDate" name="tradeDate"/>融宝必填  yyyyMMdd
                </td>
            </tr>
            <tr>
                <td class="tdr">业务编码</td>
                <td>
                    <input type="text" value="105" id="busiCode" name="busiCode"/>* 105账户交易
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


<p>--------------------------------快捷支付测试--------------------------------</p>
<form action="<%=basePath %>/payment.shtml" name="payConfirm_form" id="payConfirm_form" method="post">
    <div class="payConfirm">
        <table cellspacing="0" border="0">
            <tbody>
            <tr align="left">
                <td class="tdr">商户号</td>
                <td>
                    <input type="text" name="merchantNo" id="merchantNo" value="100020091218001" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">订单号</td>
                <td>
                    <input type="text" name="orderNo" id="orderNo" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">支付方式</td>
                <td>
                    <input type="text" value="41" name="payType" id="payType" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">手机验证码</td>
                <td>
                    <input type="text" name="checkCode" id="checkCode" class="input_t01"/>* 
                </td>
            </tr>
            <tr>
                <td class="tdr">版本号</td>
                <td>
                    <input type="text" value="v1.1" id="version" name="version"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名类型</td>
                <td>
                    <input type="text" value="1" id="signType" name="signType"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名信息</td>
                <td>
                    <input type="text" value="DF76JGHC333DAS" id="signMsg" name="signMsg"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">业务编码</td>
                <td>
                    <input type="text" value="109" id="busiCode" name="busiCode"/>* 109确认支付
                </td>
            </tr>
            <tr>
			    <td class="tdr"><input type="submit" value="快捷支付-确认" id="payConfirm"></button></td>
			    <td>
			    </td>
			</tr>
            </tbody>
        </table>
    </div>
</form><br/><br/>


<form action="<%=basePath %>/payment.shtml" name="cardAuth_form" id="cardAuth_form" method="post">
    <div class="cardAuth">
        <table cellspacing="0" border="0">
            <tbody>
            <tr align="left">
                <td class="tdr">商户号</td>
                <td>
                    <input type="text" name="merchantNo" id="merchantNo" value="100020091218001" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">订单号</td>
                <td>
                    <input type="text" name="orderNo" id="orderNo" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">支付方式</td>
                <td>
                    <input type="text" value="41" name="payType" id="payType" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">终端类型</td>
                <td>
                    <input type="text" value="web" name="terminalType" id="terminalType" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">银行卡号</td>
                <td>
                    <input type="text" value="6214242710498509" name="cardNo" id="cardNo" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">页面返回地址</td>
                <td>
                    <input type="text" value="http://www.fangcang.org/titanjr-pay-dev3/quickPay/cardSceurityVerifyResultPage.action" name="cardCheckPageUrl" id="cardCheckPageUrl" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">后台通知地址</td>
                <td>
                    <input type="text" value="http://www.fangcang.org/titanjr-pay-dev3/quickPay/cardSceurityVerifyResultNotice.action" name="cardChecknotifyUrl" id="cardChecknotifyUrl" class="input_t01"/>* 
                </td>
            </tr>
            <tr>
                <td class="tdr">版本号</td>
                <td>
                    <input type="text" value="v1.1" id="version" name="version"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名类型</td>
                <td>
                    <input type="text" value="1" id="signType" name="signType"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名信息</td>
                <td>
                    <input type="text" value="DF76JGHC333DAS" id="signMsg" name="signMsg"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">业务编码</td>
                <td>
                    <input type="text" value="114" id="busiCode" name="busiCode"/>* 114卡密鉴权
                </td>
            </tr>
            <tr>
			    <td class="tdr"><input type="submit" value="卡密鉴权" id="cardAuth"></button></td>
			    <td>
			    </td>
			</tr>
            </tbody>
        </table>
    </div>
</form><br/><br/>


<form action="<%=basePath %>/payment.shtml" name="cardBINQuery_form" id="cardBINQuery_form" method="post">
    <div class="cardBINQuery">
        <table cellspacing="0" border="0">
            <tbody>
            <tr align="left">
                <td class="tdr">商户号</td>
                <td>
                    <input type="text" name="merchantNo" id="merchantNo" value="100020091218001" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">银行卡号</td>
                <td>
                    <input type="text" name="cardNo" id="cardNo" class="input_t01"/>* 
                </td>
            </tr>
            <tr>
                <td class="tdr">版本号</td>
                <td>
                    <input type="text" value="v1.1" id="version" name="version"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名类型</td>
                <td>
                    <input type="text" value="1" id="signType" name="signType"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名信息</td>
                <td>
                    <input type="text" value="DF76JGHC333DAS" id="signMsg" name="signMsg"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">业务编码</td>
                <td>
                    <input type="text" value="106" id="busiCode" name="busiCode"/>* 106卡BIN查询
                </td>
            </tr>
            <tr>
			    <td class="tdr"><input type="submit" value="卡BIN查询" id="cardBINQuery"></button></td>
			    <td>
			    </td>
			</tr>
            </tbody>
        </table>
    </div>
</form><br/><br/>



<form action="<%=basePath %>/payment.shtml" name="reSendMsg_form" id="reSendMsg_form" method="post">
    <div class="reSendMsg">
        <table cellspacing="0" border="0">
            <tbody>
            <tr align="left">
                <td class="tdr">商户号</td>
                <td>
                    <input type="text" name="merchantNo" id="merchantNo" value="100020091218001" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">订单号</td>
                <td>
                    <input type="text" name="orderNo" id="orderNo" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">支付方式</td>
                <td>
                    <input type="text" value="41" name="payType" id="payType" class="input_t01"/>* 
                </td>
            </tr>
            <tr>
                <td class="tdr">版本号</td>
                <td>
                    <input type="text" value="v1.1" id="version" name="version"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名类型</td>
                <td>
                    <input type="text" value="1" id="signType" name="signType"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名信息</td>
                <td>
                    <input type="text" value="DF76JGHC333DAS" id="signMsg" name="signMsg"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">业务编码</td>
                <td>
                    <input type="text" value="110" id="busiCode" name="busiCode"/>* 110重发验证码
                </td>
            </tr>
            <tr>
			    <td class="tdr"><input type="submit" value="重发验证码" id="reSendMsg"></button></td>
			    <td>
			    </td>
			</tr>
            </tbody>
        </table>
    </div>
</form><br/><br/>


<form action="<%=basePath %>/payment.shtml" name="unBindCard_form" id="unBindCard_form" method="post">
    <div class="unBindCard">
        <table cellspacing="0" border="0">
            <tbody>
            <tr align="left">
                <td class="tdr">商户号</td>
                <td>
                    <input type="text" name="merchantNo" id="merchantNo" value="100020091218001" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">用户id</td>
                <td>
                    <input type="text" value="12345" name="userId" id="userId" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">绑卡ID</td>
                <td>
                    <input type="text" value="343545" name="bindCardId" id="bindCardId" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">身份证号</td>
                <td>
                    <input type="text" value="210302196001012114" name="idCode" id="idCode" class="input_t01"/>* 
                </td>
            </tr>
            <tr align="left">
                <td class="tdr">银行卡类型</td>
                <td>
                    <input type="text" value="10" name="cardType" id="cardType" class="input_t01"/>* 10储蓄卡  11信用卡
                </td>
            </tr>
            <tr>
                <td class="tdr">版本号</td>
                <td>
                    <input type="text" value="v1.1" id="version" name="version"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名类型</td>
                <td>
                    <input type="text" value="1" id="signType" name="signType"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">签名信息</td>
                <td>
                    <input type="text" value="DF76JGHC333DAS" id="signMsg" name="signMsg"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">业务编码</td>
                <td>
                    <input type="text" value="112" id="busiCode" name="busiCode"/>* 112解绑卡
                </td>
            </tr>
            <tr>
			    <td class="tdr"><input type="submit" value="解绑卡" id="unBindCard"></button></td>
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