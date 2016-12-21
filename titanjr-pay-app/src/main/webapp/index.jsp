<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <script type="text/javascript" src="http://hres.fangcang.com/js/common/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="http://hres.fangcang.com/js/common/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/titanpay.js"></script>

</head>

<script type="text/javascript">
	window.onload = function() {

		var pub = "d8b6e03dd8f9bf45157f0d14aedf9a696665641da90cab5114a22b7f6c711f22429c32c99ab76e3ce74de00145bcd50b9d2e7c60cd97a4979a5d0ce4ead9ba61baca1495758d69cc1f76e69db43f1ef1f9c33cd2edb8c726ed17c297a7b9fa3f18e58aef9d3f33f8431a41cc3c0ca7bc5151d33a8691e6506e0439363aec0063";
		var expo = "10001";
// 		titanPayObj.initTitanPay();

	 titanPayObj.initTitanPay();
	 
	 /**
	titanPayObj.initTitanPay({
		/**
		titanPayObj.initTitanPay({
			address : '127.0.0.1:8084',
			empoent : expo,
			module : pub
		});
		 */
		 

		var businessInfo = {
			inAccountCode : "A000003337",
			outAccountCode : "A000018432",
			bussCode : "566322565",//业务单号
		};
		
		
		
		document.getElementById('payButton').onclick = function() {
			
			var orderInfo = {
		                name : $("#name").val(), //打开收银台人员姓名N
		                escrowedDate : $("#escrowedDate").val(),//保证期时间N
		                goodsId : $("#goodsId").val(),//商品编号,可以是对方的订单号
		                goodsDetail : $("#goodsDetail").val(),//商品描述 N
		                goodsName : $("#goodsName").val(),//商品名称N
		                userId : $("#userId").val(),//付款方身份标示
		                ruserId : $("#ruserId").val(),//收款方身份标示N
		                amount : $("#amount").val(),//订单金额
		                payerType : $("#payerType").val(),//付款人类型
		                notify : $("#notify").val()
		            };
			
			titanPayObj.titanPay(orderInfo, businessInfo);
		};
		
		 document.getElementById('refundButton').onclick = function(){
			var refundInfo ={
					orderNo:$("#reorderNo").val(),
					userId:$("#reuserId").val(),
					tfsUserid:$("#retfsUserid").val()
			};
			
			   window.location=titanPayObj.getRefund(refundInfo);
		 };

	};
</script>

<body>
	<h2>Hello World!</h2>
	
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
                <td><input type="text" id="goodsId" value="201611141238596002662" name="goodsId"/>
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
                    <input type="text" value="TJM10000098" id="ruserId" name="ruserId"/>
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
                    <input type="text" value="1" id="payerType" name="payerType"/><span>1:B2B交易平台  2:GDP 3:财务供应商  4:联盟供应商付款 1001:对外开放平台
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
                    <input type="text" value="http://localhost:8088/titanjr-pay-app/quickPayment/customerNotify.action" id="notify" name="notify"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</form>
	 <tr>
                <td class="tdr"></td>
                <td><button value="asdfadfasd" id="payButton">去支付</button></td>
            </tr>
		
	
	
	<form id="payForm">
    <div class="pay">
        <input type="hidden" id="inAccountCode" name="inAccountCode"/>
        <input type="hidden" id="outAccountCode" name="outAccountCode"/>
        <input type="hidden" id="bussCode" name="bussCode" value="skdhjskdjl"/>

        <table cellspacing="0" border="0">
            <tbody>
            <tr align="left">
                <td class="tdr">退款单号</td>
                <td>
                    <input type="text" name="reorderNo"  id="reorderNo" value="dfadfs0055" class="input_t01"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">金融账号</td>
                <td><input type="text" id="reuserId" value="TJM10000035" name="reuserId"/></td>
            </tr>
            <tr>
                <td class="tdr">操作人ID</td>
                <td><input type="text" id="retfsUserid" value="10122" name="retfsUserid"/>
                </td>
            </tr>
            
            </tbody>
        </table>
    </div>
</form>
	<tr>
                <td class="tdr"></td>
                <td><button value="asdfadfasd" id="refundButton">退款</button></td>
            </tr>
	
</body>


</html> 




<!-- 对外收银台的对接 -->
 <%--   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试收银台</title>
    <script type="text/javascript" src="http://hres.fangcang.com/js/common/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="http://hres.fangcang.com/js/common/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/pay.js"></script>
    <script>
        $(document).ready(function () {
            var configObj = {};
            configObj.module = "c7051ab21f7356dbd454aa3ea6e49e5e2f7186ea9000496d977539ba70d580ce63544ca43b0de147e9f2e4d103abe2e6caf00ed18b708dbab92581845ee22d681afd1d08947aa9e3345bb41eda1411f24ed6bdccd909b824e11f5cc30fbab764eeabd381256bae2e557ab142dea389bb69d5a33a1bb5b3f285bcff1c32aefd65";
            configObj.address = "http://www.fangcang.org";
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
                <td><input type="text" id="goodsId" value="201611141238596002662" name="goodsId"/>
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
                    <input type="text" value="TJM10000098" id="ruserId" name="ruserId"/>
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
                    <input type="text" value="http://localhost:8088/titanjr-pay-app/quickPayment/customerNotify.action" id="notify" name="notify"/>
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
 --%>