package com.fangcang.titanjr.pay.util;

import java.net.URLDecoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.titanjr.common.util.rsa.JsRSAUtil;

public class RSADecryptString {
	private static final Log log = LogFactory.getLog(RSADecryptString.class);

	public static String decryptString(String str, String key) 
	{
		try {
			
			
			KeyFactory keyFactory = KeyFactory.getInstance("RSA",
					new org.bouncycastle.jce.provider.BouncyCastleProvider());

			PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(
					JsRSAUtil.hexStringToBytes(key));

			PrivateKey pkey = keyFactory.generatePrivate(privateSpec);

			String[] signAr = str.split(" ");
			StringBuilder strs = new StringBuilder();
			for (int i = 0; i < signAr.length; i++) {
				
				byte[] de_result = JsRSAUtil.decrypt(pkey,JsRSAUtil.hexStringToBytes(signAr[i]));
				strs.append(new StringBuilder(new String(de_result)).reverse()
						.toString());
			}
			return URLDecoder.decode(strs.toString(),"UTF-8");
		} catch (Exception e) {
			log.error("解密失败" + e.getMessage(), e);
		}
		return null;
	}
}
