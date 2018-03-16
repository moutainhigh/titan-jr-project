package com.titanjr.checkstand.util.rbUtil;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Random;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.request.RBDataRequest;

/**
 * 
 * @author Jerry
 * @date 2018年1月3日 下午5:45:06
 */
public class Decipher {
	
	private final static Logger logger = LoggerFactory.getLogger(Decipher.class);

	
	/**
	 * 解密融宝返回数据
	 * @author Jerry
	 * @date 2018年1月3日 下午5:44:41
	 */
	public static String decryptData(String responseStr, String resUrl) throws Exception {

		//将返回的json串转换为map
		TreeMap<String, String> map = JSON.parseObject(responseStr,
				new TypeReference<TreeMap<String, String>>() {
				});
		String encryptkey = map.get("encryptkey");
		String data = map.get("data");

		// 获取自己私钥解密
		PrivateKey pvkformPfx = RSA.getPvkformPfx(resUrl+SysConstant.PRIVATE_KEY_PATH,
				SysConstant.PRIVATE_KEY_PWD);
		String decryptData = RSA.decrypt(encryptkey, pvkformPfx);

		responseStr = AES.decryptFromBase64(data, decryptData);

		return responseStr;
	}

	/**
	 * 解密2
	 * 
	 * @param merchant_id
	 * @param data
	 * @param encryptkey
	 * @return
	 * @throws com.reapal.common.exception.ServiceException
	 */
	/*public static String decryptData(String encryptkey, String data)
			throws Exception {

		// 获取自己私钥解密
		PrivateKey pvkformPfx = RSA.getPvkformPfx(SysConstant.PRIVATE_KEY_PATH, SysConstant.PRIVATE_KEY_PWD);
				
		String decryptKey = RSA.decrypt(encryptkey, pvkformPfx);

		return AES.decryptFromBase64(data, decryptKey);

	}*/
	

	/**
	 * 融宝接口的请求数据加密
	 * @author Jerry
	 * @date 2018年1月3日 下午5:28:34
	 */
	public static RBDataRequest encryptData(String json, String merchantId, String resUrl) throws Exception {
		logger.info("json数据=============>" + json);
		RBDataRequest rbDataRequest = new RBDataRequest();
		rbDataRequest.setMerchant_id(merchantId);

		// 商户获取融宝公钥
		PublicKey pubKeyFromCrt = RSA.getPubKeyFromCRT(resUrl+SysConstant.PUBLIC_KEY_PATH);
		// 随机生成16数字
		String key = getRandom(16);

		// 使用RSA算法将商户自己随机生成的AESkey加密
		String encryptKey = RSA.encrypt(key, pubKeyFromCrt);
		// 使用AES算法用随机生成的AESkey，对json串进行加密
		String encryData = AES.encryptToBase64(json, key);

		logger.info("密文key============>" + encryptKey);
		logger.info("密文数据===========>" + encryData);

		rbDataRequest.setEncryptkey(encryptKey);
		rbDataRequest.setData(encryData);

		return rbDataRequest;
	}

	public static Random random = new Random();

	public static String getRandom(int length) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < length; i++) {
			boolean isChar = (random.nextInt(2) % 2 == 0);// 输出字母还是数字
			if (isChar) { // 字符串
				int choice = (random.nextInt(2) % 2 == 0) ? 65 : 97; // 取得大写字母还是小写字母
				ret.append((char) (choice + random.nextInt(26)));
			} else { // 数字
				ret.append(Integer.toString(random.nextInt(10)));
			}
		}
		return ret.toString();
	}

	
	public static void main(String[] args) {

		String data = "IaCNI1qIP6BKiITCOGghopk3hXJnpqQYNdxCHU/K+MxIhNkcha2+vu1OHMmBNhctU4hvQBMNWCF+Pq4Hk0ZjfI70qAofmvohO+N05Fl+ym7gkEJp57TqTawq7XQjN4TXaoqHfjVoZkpt7ITXjAgjx0dP6mVdCEh3yyr3Qc1Dy8LedvoEKG7uTE3Xi6DjmJN7rGR31RGwpJZ6iocaJRv+3TihcuKLYmWKDW+G1BU48fKYRZL4W5a/S1e3BJb0v6iOzUslPuUd6fShxUZADX5SVSBtfyHaOHOwzdRgbLshWDI=";
		String encryptkey = "YBZIFt09u/Y8gGMqAo43XdrNm763BIxwmgHAKg1asJ0BDrRvjfUpLrKd6Uh1dH38kE+NKV72Tr2mS3uvsgEPgbQmcVhbSWITQSecHNxSdRTyvj5Nj4fe67TD74GI2MTN0Fq2y0Sr5J9RI0fsoAzBRZZg5b3z/WJVjaq0+gS//YM500DJioa3iGjUDqgdUUwhh8cz3b5jwiFv0LvHeuUW1Qr9iSE4UW1RBRoCKFWNDNsxVoU8kZgEmSk2t439SMjY27Ixx81cxIW9q9m+MbCZwh1FnyohpREz0vTI3qfo76lsk9/2NJMHbJfF5B9skXjDBqIx3jK71auQpBhZD94CgA==";
		
		try {
			System.out.println("=========" + decryptData(encryptkey, data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
