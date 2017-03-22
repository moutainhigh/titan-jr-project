/**
 * 对数据进行rsa加密
 */
function rsaData(obj){
		setMaxDigits(129);
		var empoent = $("#empoent").val();
		var module = $("#module").val();
		var key = new RSAKeyPair(empoent,"",module); 
		return encryptedString(key,encodeURIComponent(obj));
		
}


