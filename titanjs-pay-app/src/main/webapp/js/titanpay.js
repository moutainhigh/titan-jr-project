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
 *            模数
 */
function initTitanPay(address, empoent, module) {
	config.address = address;
	config.empoent = empoent;
	config.module = module;

	setMaxDigits(129);
	titan_rsakey = new RSAKeyPair(config.empoent, "", config.module);
	createPayForm(address);

}

function createPayForm(address) {
	$('body')
			.append(
					"<form action='http://"
							+ address
							+ "/pay/trade/titanPay.action'"
							+ " method='post' id='titan_pay_form' target='_blank'> "
							+ "<input type='hidden' name='orderInfo' id='titan_orderInfo'/> <input type='hidden' name='businessInfo' id='titan_businessInfo'/> "
							+

							" </form>");

}

/**
 * 检查订单信息是否合法
 * 
 * @param orderInfo
 */
function checkOrder(orderInfo) {
	return true;
}

/**
 * 根据订单信息生成签名
 * 
 * @param orderInfo
 */
function titanEncrypted(str) {
	return encryptedString(titan_rsakey, encodeURIComponent(str));

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
function titanPay(orderInfo, businessInfo) {

	if (checkOrder(orderInfo)) {
		$('#titan_orderInfo').val(titanEncrypted(JSON.stringify(orderInfo)));
		$('#titan_businessInfo').val(titanEncrypted(JSON.stringify(businessInfo)));
		$('#titan_pay_form').submit();
	}
}