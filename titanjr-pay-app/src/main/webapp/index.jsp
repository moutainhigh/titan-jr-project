<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试收银台</title>
    <script type="text/javascript" src="http://hres.fangcang.com/js/common/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="http://hres.fangcang.com/js/common/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/pay.js"></script>
    <script>
        $(document).ready(function () {
            var configObj = {};
            configObj.module = "9ae037870540fb4820c4d69c494096f09dbdd2dca06022b56a93695ae06db35d9a6923e1690825e5ba9e711cb2ee75dd39ed647cc8c7687574449c7cb95cf686c9bc4f873df31e27b2678c9ce6f6d82e19dd76ccdea7ce8fe2733330d70a63e2f11f0be7dbcd3793f22329c5445491e91beb83416ba4cded0b0f07b9d35ee88b";
            configObj.address = "http://192.168.0.90:8088";
            configObj.empoent="10001";
            var result = titanPayObj.initTitanPay(configObj);
        });

        function testOrderPay() {
            var tempwindow = window.open();
            var orderInfo;
            var businessInfo;
            orderInfo = {
                name : $("#name").val(), //打开收银台人员姓名N
                escrowedDate : $("#escrowedDate").val(),//保证期时间N
                goodsId : $("#goodsId").val(),//商品编号,可以是对方的订单号
                goodsDetail : $("#goodsDetail").val(),//商品描述 N
                goodsName : $("#goodsName").val(),//商品名称N
                userId : $("#userId").val(),//付款方身份标示
                ruserId : $("#ruserId").val(),//收款方身份标示N
                amount : $("#amount").val(),//订单金额
                payerType : $("#payerType").val(),//付款人类型
                currencyType : $("#currencyType").val(),//币种
                checkOrderUrl : $("#checkOrderUrl").val(),//可选
                notify : $("#notify").val()
            };

            businessInfo = {
                bussCode:$("#bussCode").val(),
                ruserId: $("#ruserId").val()
            }
            tempwindow.location = titanPayObj.getTitanPayUrl(orderInfo, businessInfo);
        }

    </script>
</head>
<body>
<form id="payForm">
    <div class="pay">
        <input type="hidden" id="inAccountCode" name="inAccountCode"/>
        <input type="hidden" id="outAccountCode" name="outAccountCode"/>
        <input type="hidden" id="bussCode" name="bussCode" value="skdhjskdjl"/>

        <table cellspacing="0" border="0">
            <tbody>
            <tr align="left">
                <td class="tdr">操作人</td>
                <td>
                    <input type="text" name="name"  id="name" value="zhangsan" class="input_t01"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">担保结束日期</td>
                <td><input type="text" id="escrowedDate" value="2016-12-03" name="escrowedDate"/></td>
            </tr>
            <tr>
                <td class="tdr">业务订单号</td>
                <td><input type="text" id="goodsId" value="201611141238596002655" name="goodsId"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">商品名称</td>
                <td>
                    <input type="text" id="goodsName" value="签证预定单" name="goodsName"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">商品详情</td>
                <td>
                    <input type="text" value="预定日本签证，加急，5张...." id="goodsDetail" name="goodsDetail"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">付款方机构编码</td>
                <td>
                    <input type="text" value="" id="userId" name="userId"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">收款方机构编码</td>
                <td>
                    <input type="text" value="TJM10000109" id="ruserId" name="ruserId"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">付款金额</td>
                <td>
                    <input type="text" value="0.01" id="amount" name="amount"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">收付款类型</td>
                <td>
                    <input type="text" value="1001" id="payerType" name="payerType"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">币种</td>
                <td>
                    <input type="text" value="1" id="currencyType" name="currencyType"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">订单校验接口地址</td>
                <td>
                    <input type="text" id="checkOrderUrl" name="checkOrderUrl"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">支付通知接口地址</td>
                <td>
                    <input type="text" value="http://localhost:8080/CashierDesk/payCallBack" id="notify" name="notify"/>
                    <%--<input type="text" value="http://www.fangcang.org/titanjr-pay-app/testCallBack" id="notify" name="notify"/>--%>
                </td>
            </tr>
            <tr>
                <td class="tdr"></td>
                <td><input type="button" class="input_b01" value="收银台付款" onClick="testOrderPay()"/></td>
            </tr>
            </tbody>
        </table>
    </div>
</form>

</body>
</html>
