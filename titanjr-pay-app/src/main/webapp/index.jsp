 
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script type="text/javascript" src="js/titanpay.js"></script>
</head>
<body>
	<h2>Hello World!</h2>
	<button value="asdfadfasd" id="payButton">pay</button>
</body>
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

		var orderInfo = {
			name : "zhang", //打开收银台人员姓名  N
			escrowedDate : "2016-08-31",//保证期时间  N
			goodsId : "akjsdkas31",//商品编号，可以是对方的订单号
			goodsDetail : "我是啊好人",//商品描述  N
			goodsName : "提现最帅了！",//商品名称 N
			userId : "23298",//付款方身份标示   如果是财务，则建议是FCUSERID，  如果是GDP，则是用户ID
			ruserId : "M10021069",//收款方身份标示 N  ,GDP可以指定接受方的   商家联盟可以指定其FCUSERID//M10021069
			amount : "3292368",//订单金额
			payerType : "4",//付款人类型   财务 GDP 等
			// 			currencyType : "1",//币种
			// 			checkOrderUrl : 'http://www.baidu.com',//可选
			notify : 'http://www.baidu.com'
		};

		var businessInfo = {
			inAccountCode : "A000003337",
			outAccountCode : "A000018432",
			bussCode : "566322565",//业务单号
			fcUserId:"31975"
		};

		document.getElementById('payButton').onclick = function() {
			titanPayObj.titanPay(orderInfo, businessInfo);
		}

	}
</script>
</html>
 