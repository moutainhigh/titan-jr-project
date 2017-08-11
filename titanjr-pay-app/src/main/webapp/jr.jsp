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
            billCode:"BS007425",
		};
		
		
		
		document.getElementById('payButton').onclick = function() {
			
			var orderInfo = {
		                name : $("#name").val(), //打开收银台人员姓名N
		                escrowedDate : $("#escrowedDate").val(),//保证期时间N
		                goodsId : $("#goodsId").val(),//商品编号,可以是对方的订单号
		                goodsDetail : $("#goodsDetail").val(),//商品描述 N
		                goodsName : $("#goodsName").val(),//商品名称N
		                userId : $("#userId").val(),//付款方用户ID
		                partnerOrgCode : $("#partnerOrgCode").val(),//付款方的机构编码
		                orgCode : $("#orgCode").val(),//付款方的金融机构编码
		                ruserId : $("#ruserId").val(),//收款方身份标示N
		                amount : $("#amount").val(),//订单金额
		                payerType : $("#payerType").val(),//付款人类型
		                notify : $("#notify").val(),
		                version : $("#version").val(),//金融版本 v1.0  v1.1
		                freezeType : $("#freezeType").val()
		            };
			
			titanPayObj.titanPay(orderInfo, businessInfo);
		};
		
		 document.getElementById('refundButton').onclick = function(){
			var refundInfo ={
					orderNo:$("#reorderNo").val(),
					userId:$("#reuserId").val(),
					tfsUserid:$("#retfsUserid").val(),
					isMerchCode:$("#isMerchCode").val(),
					notifyUrl:$("#notifyUrl").val(),
					businessInfo:"125sdlkkkl"
			};
			
			   window.open().location=titanPayObj.getRefund(refundInfo);
		 };
		 
		 
		

	};
	
	
	 
	 
</script>

<body>
	<h2>Hello World! <%=request.getLocalAddr() %></h2>
	
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
                    <input type="text" name="name"  id="name" value="德玛西亚" class="input_t01"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">担保结束日期</td>
                <td><input type="text" id="escrowedDate" value="2017-10-03" name="escrowedDate"/></td>
            </tr>
            <tr>
                <td class="tdr">业务订单号</td>
                <td><input type="text" id="goodsId" value="201708021425596001001" name="goodsId"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">商品名称</td>
                <td>
                    <input type="text" id="goodsName" value="测试预定单" name="goodsName"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">商品详情</td>
                <td>
                    <input type="text" value="预定单，加急，100张" id="goodsDetail" name="goodsDetail"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">付款方用户ID</td>
                <td>
                    <input type="text" value="" id="userId" name="userId"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">付款方机构编码</td>
                <td>
                    <input type="text" value="" id="partnerOrgCode" name="userId"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">付款方金融机构编码</td>
                <td>
                    <input type="text" value="" id="orgCode" name="userId"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">收款方机构编码</td>
                <td>
                    <input type="text" value="TJM60000001" id="ruserId" name="ruserId"/>
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
                    <input type="text" value="1" id="payerType" name="payerType"/><span>1:B2B交易平台 <span title='2:GDP-收款方SAAS商家编码'> 2:GDP</span> <span title='3:财务供应商-付款方为SAAS用户id'>3:财务供应商</span>  4:联盟供应商付款 1001:对外开放平台,1024:TTM-SAAS商家,10242：TTM-金融商家，10243:微信公众号
                </td>
            </tr>
            <tr>
                <td class="tdr">币种</td>
                <td>
                    <input type="text" value="1" id="currencyType" name="currencyType"/>
                </td>
            </tr>
            <tr>
                <td class="tdr">冻结类型</td>
                <td>
                    <input type="text" value="2" id="freezeType" name="freezeType"/><span>1-转账到收款方，不冻结；2-转账到收款方并冻结收款方资金；3-不转账，冻结付款方资金</span>
                </td>
            </tr>
            <tr>
                <td class="tdr">版本号</td>
                <td>
                    <input type="text" value="v1.0" id="version" name="version"/><span>v1.0-老版本；v1.1-新版本（新版收银台，快捷支付，账户升级）</span>
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
                    <input type="text" value="http://192.168.0.77:8084/titanjr-pay-app/payCallBack" id="notify" name="notify"/>
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
                    <input type="text" name="reorderNo"  id="reorderNo" value="dfadfs0055" class="input_t01"/>与业务订单号相同
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
             <tr>
                <td class="tdr">是否为商家编码</td>
                <td><input type="text" id="isMerchCode" value="10122" name="isMerchCode"/>1,商家编码，0金融账号
                </td>
            </tr>
             <tr>
                <td class="tdr">回调地址</td>
                <td><input type="text" id="notifyUrl" value="10122" name="notifyUrl"/>
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




	<form id="wxPayForm" action="titanPay.action" method="POST">
		<div class="pay">

			<table cellspacing="0" border="0">
				<tbody>
					<tr align="left">
						<td class="tdr">收款机构</td>
						<td><input type="text" name="payeeOrg" id="payeeOrg"
							value="TJM10000029" class="input_t01" /></td>
					</tr>
					<tr>
						<td class="tdr">收款机构名称</td>
						<td><input type="text" id="payeeOrgName" value="郑尧辉"
							name="payeeOrgName" /></td>
					</tr>
					<tr>
						<td class="tdr">付款机构</td>
						<td><input type="text" id="payerOrg" value=""
							name="payerOrg" /></td>
					</tr>
					<tr>
						<td class="tdr">付款机构名称</td>
						<td><input type="text" id="payerOrgName" value=""
							name="payerOrgName" /></td>
					</tr>
					<tr>
						<td class="tdr">商品名称或者简要</td>
						<td><input type="text" id="commodityName" value="测试微信公众号支付"
							name="commodityName" /></td>
					</tr>
					<tr>
						<td class="tdr">商品详细描述</td>
						<td><input type="text" id="commodityDesc" value="测试微信公众号支付详细描述" name="commodityDesc" /></td>
					</tr>
					
					
					<tr>
						<td class="tdr">业务单号</td>
						<td><input type="text" id="bussOrderNo" value="5445645645"
							name="bussOrderNo" /></td>
					</tr>
					<tr>
						<td class="tdr">币种 1 人民币</td>
						<td><input type="text" id="currencyType" value="1"
							name="currencyType" /></td>
					</tr>
					<tr>
						<td class="tdr">订单金额</td>
						<td><input type="text" id="amount" value="0.02"
							name="amount" /></td>
					</tr>
					
					
					<tr>
						<td class="tdr">支付通道类型  WX</td>
						<td><input type="text" id="channelType" value="10243"
							name="channelType" /></td>
					</tr>
					
					
					<tr>
						<td class="tdr">业务通知地址</td>
						<td><input type="text" id="notifyUrl" value="http://www.163.com"
							name="notifyUrl" /></td>
					</tr>
					
					
					<tr>
						<td class="tdr">业务信息</td>
						<td><input type="text" id="bussInfo" value='{"ordercontent":"taogegege"}'
							name="bussInfo" /></td>
					</tr>
					
					
					<tr>
						<td class="tdr">成功回调地址</td>
						<td><input type="text" id="successJumpUrl" value="http://www.sina.com"
							name="successJumpUrl" /></td>
					</tr>
					
					<tr>
						<td class="tdr">失败回调地址</td>
						<td><input type="text" id="failJumpUrl" value="http://www.baidu.com"
							name="failJumpUrl" /></td>
					</tr>
					
				</tbody>
			</table>
			
		</div>
		<button type="submit" >微信支付</button>
	</form>

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