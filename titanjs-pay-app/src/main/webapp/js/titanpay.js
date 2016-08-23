/*

var orderInfo = {

	merchantCode : "",
	payOrderNo : "",
	paySource : "",
	fcUserid : "",
	operater : "",
	businessOrderCode : "",
	recieveMerchantCode : "",
	isEscrowed : "",
	escrowedDate : "",
	businessInfo : {

	}
};

var businessInfo
{

}*/
var titanPayObj = {};

function initTitanPayObj() {

	var config = {
		intervalTime : 3000
	};
	// var timeIntervalObj = null;
	var titan_rsakey = null;
	
	
	/**
	 * 初始化公钥及地址
	 * 
	 * @param address
	 *            请求地址：127.0.0.1:8080
	 * @param empoent
	 *            指数
	 * @param module
	 *            模数titanJrPayRsaObj
	 */
	titanPayObj.initTitanPay = function(address, empoent, module) {
		config.address = address;
		config.empoent = empoent;
		config.module = module;

		
		this.createPayForm(address);

	}

	titanPayObj.createPayForm = function(address) {

		var elscript = document.createElement("script");
		elscript.src = "http://" + address + "/titanjs-pay-app/js/rsa/RSA.js";
		elscript.setAttribute("type", "text/javascript");
		document.body.appendChild(elscript);

		var form = document.createElement("form");
		form.action = 'http://' + address
				+ '/titanjs-pay-app/trade/titanPay.action';
		form.target = '_blank';
		form.id = 'titan_pay_form';
		form.method = 'post';

		var orderInfo = document.createElement("input");
		orderInfo.type = 'hidden';
		orderInfo.name = 'orderInfo';
		orderInfo.id = 'titan_orderInfo';

		var businessInfo = document.createElement("input");
		businessInfo.type = 'hidden';
		businessInfo.name = 'businessInfo';
		businessInfo.id = 'titan_businessInfo';

		form.appendChild(orderInfo);
		form.appendChild(businessInfo);
		document.body.appendChild(form);
	}

	/**
	 * 检查订单信息是否合法
	 * 
	 * @param orderInfo
	 */
	titanPayObj.checkOrder = function(orderInfo) {
		return true;
	}

	/**
	 * 根据订单信息生成签名
	 * 
	 * @param orderInfo
	 */
	titanPayObj.titanEncrypted = function(str) {
		

		if (titan_rsakey == null) {
			titanJrPayRsaObj.setMaxDigits(129);
			titan_rsakey = new titanJrPayRsaObj.RSAKeyPair(config.empoent, "",
					config.module);
		}
		
		return titanJrPayRsaObj.encryptedString(titan_rsakey,
				encodeURIComponent(str));
	}

	/**
	 * 请求支付
	 * 
	 * @param orderInfo
	 *            订单信息
	 * @param businessInfo
	 *            业务自定义信息
	 * @param resultCallBack
	 *            用户自定义回调函数
	 */
	titanPayObj.titanPay = function(orderInfo, businessInfo) {

		if (this.checkOrder(orderInfo)) {

			document.getElementById('titan_orderInfo').value = this
					.titanEncrypted(JSON.stringify(orderInfo));
			document.getElementById('titan_businessInfo').value = JSON
					.stringify(businessInfo);
			document.getElementById('titan_pay_form').submit();
			// $('#titan_orderInfo').val();
			// $('#titan_businessInfo').val(JSON.stringify(businessInfo));
			// $('#titan_pay_form').submit();
		}
	}
};

initTitanPayObj();
