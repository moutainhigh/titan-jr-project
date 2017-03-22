package com.fangcang.titanjr.common.util.rsa;

import java.net.URLEncoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.util.StringUtil;
/***
 * 融数rsa处理类
 * @author luoqinglong
 * @date   2016年11月30日
 */

public class RSAUtil {
	private static final Log log = LogFactory.getLog(RSAUtil.class);
	
	/**
	 * 融数上传文件加密公钥,dubbo-server的RSInvokeInitManagerImpl类会重新初始化这个值
	 */
	public static String UPLOAD_FILE_RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIORKS8rUfqq+8FnJbAALZpZ45ImXhbYdDBQsaMRyqbLXNMiRR3u41Ahkt2XMKo6+JR5x8OFUks3btCLoXx05GaPTjHAcDt+4JCvhFIi296KuYRElIoPrVDvkHtIkQZ64n/LYEaVAoYGuKw5g8DclCxDQgR7VmkiUqVbRem6UMLwIDAQAB";
	
	/** 
     * 加密算法RSA 
     */ 
	public static final String KEY_ALGORITHM = "RSA";
	/** *//** 
     * 签名算法 
     */  
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	 /** *//** 
     * 获取公钥的key 
     */  
	public static final String PUBLIC_KEY = "RSAPublicKey";
	/** *//** 
     * 获取私钥的key 
     */  
	public static final String PRIVATE_KEY = "RSAPrivateKey";
	
	public static final String PUBLIC_KEY_MODULE = "module";
	
	public static final String PUBLIC_KEY_EMPOENT = "empoent";
	
	public static final String QSTRING_EQUAL = "=";
	public static final String QSTRING_SPLIT = "&";
	//SIGN
	public final static String SIGNATURE = "sign";
	public final static String RSCODE = "rsCode";
	public final static String RSMEG = "rsMsg";
	
    /** *//**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    
    /** *//**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

	
    
    /**
     * 生成字符串类型的公钥、私钥对
     * @return
     * @throws Exception
     */
    public static Map<String, String> generateKeys() throws Exception{
    	Map<String, String> keysMap = new HashMap<String, String>();
    	KeyPair kp = RsaHelper.generateRSAKeyPair();
    	PublicKey pubKey = kp.getPublic();
        PrivateKey priKey = kp.getPrivate();
        
    	String pubKeyXml = RsaHelper.encodePublicKeyToXml(pubKey);
        String priKeyXml = RsaHelper.encodePrivateKeyToXml(priKey);
//        String pubKeyXml = RsaHelper.encode64PublicKeyString(pubKey);
//        String priKeyXml = RsaHelper.encode64PrivateKeyString(priKey);
    	keysMap.put(RSAUtil.PUBLIC_KEY, pubKeyXml);
    	keysMap.put(RSAUtil.PRIVATE_KEY, priKeyXml);
    	return keysMap;
    }
    
    
    /**
     * 生成公私钥对
     * @return
     * @throws Exception
     */
    public static Map<String,String>generateStringKsys()throws Exception{
    	Map<String, String> keysMap = new HashMap<String, String>();
    	KeyPair kp = RsaHelper.generateRSAKeyPair();
    	PublicKey pubKey = kp.getPublic();
        PrivateKey priKey = kp.getPrivate();
        String privateKey = Base64Helper.encode(priKey.getEncoded());
        String publicKey = Base64Helper.encode(pubKey.getEncoded());
        RSAPublicKey rsaPukey = (RSAPublicKey)pubKey;
        
        keysMap.put(RSAUtil.PUBLIC_KEY_MODULE, rsaPukey.getModulus().toString(16));
        keysMap.put(RSAUtil.PUBLIC_KEY_EMPOENT, rsaPukey.getPublicExponent().toString(16));
    	keysMap.put(RSAUtil.PRIVATE_KEY, privateKey);
    	keysMap.put(RSAUtil.PUBLIC_KEY, publicKey);
    	return keysMap;
    }
    
    
    /** *//** 
     * <p> 
     * 公钥加密 
     * </p> 
     *  
     * @param data 源数据 
     * @param publicKey 公钥(BASE64编码) 
     * @return 
     * @throws Exception 
     */ 
    public static String encryptByPublicKey(String sourceData, String publicKey) throws Exception {
    	
    	PublicKey publicKeyObj = RsaHelper.decodePublicKeyFromXml(publicKey);
//    	PublicKey publicKeyObj = RsaHelper.decodePublicKeyFromBase64Str(publicKey);
    	byte [] sourceDataByteArray = sourceData.getBytes("utf-8");
    	byte [] encodeDataByteArray = RsaHelper.encryptData(sourceDataByteArray, publicKeyObj);
        return Base64Helper.encode(encodeDataByteArray);
    }  
    
    /** 
     * 私钥解密 
     * @param encryptedData 已加密数据 
     * @param privateKey 私钥(BASE64编码) 
     * @return 
     * @throws Exception 
     */  
    public static String decryptByPrivateKey(String encryptedData, String privateKey) throws Exception {
       PrivateKey privateKeyObj = RsaHelper.decodePrivateKeyFromXml(privateKey);
//     PrivateKey privateKeyObj = RsaHelper.decodePrivateKeyFromBase64Str(privateKey);
       byte [] encryptedDataArray = Base64Helper.decode(encryptedData);
       byte [] decryptedDataByteArray = RsaHelper.decryptData(encryptedDataArray, privateKeyObj);
       if(decryptedDataByteArray == null) return null;
       String decryptedData = new String(decryptedDataByteArray, "utf-8");
       return decryptedData;
    }
	
	/**
	 * 公钥加密
	 * @param data 待加密Map
	 * @param publicKey 公钥
	 * @return String
	 * @Description:
	 * @author 刘恒   
	 * @date 2015年3月24日 下午9:44:35 
	 * @version V1.0
	 * @throws Exception 
	 */
	public static String publicRequestEncrypt(Map<String, String> data,String publicKey) throws Exception{
		String nvp = buildReq(data,false);
		log.info("【待签名字符串】"+nvp);
		return encryptByPublicKey(nvp,publicKey);
	}
	
	
	
	
    /**
	 * 验密
	 * @param paramMap
	 * @param privateKey  钱包私钥
	 * @return boolean
	 * @Description:
	 * @author 刘恒   
	 * @date 2015年3月24日 下午7:16:03 
	 * @version V1.0
	 */
	public static boolean privateDecrypt(Map<String,String> paramMap,String privateKey){
		// 获取请求参数中的签名域
		String sign = paramMap.get(SIGNATURE);
		log.info("【请求验签|请求签名域】"+sign);
		if(StringUtil.isValidString(sign)){
			return false;
		}
		// 拼接请求字符串
		String nvp = buildReq(paramMap,false);
		log.info("【请求验签|签名参数】"+nvp);
		try {
			return nvp.equalsIgnoreCase(decryptByPrivateKey(sign, privateKey));
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 组装签名参数
	 * @param req 签名参数
	 * @param type true：仅用于创建返回签名域  false：创建请求签名域和其他
	 * @return String
	 * @Description:
	 * @author 刘恒   
	 * @date 2015年3月24日 下午3:47:25 
	 * @version V1.0
	 */
	public static String buildReq(Map<String, String> req,boolean type) {
		// 除去数组中的空值和签名参数
        Map<String, String> filteredReq = paraFilter(req,type);
		String prestr = createLinkString(filteredReq);
		return prestr;
	}

	/**
	 * 拼接签名字符串
	 * @param para
	 * @return String
	 * @Description:
	 * @author 刘恒   
	 * @date 2015年3月24日 下午2:32:11 
	 * @version V1.0
	 */
	public static String createLinkString(Map<String, String> para) {

		List<String> keys = new ArrayList<String>(para.keySet());
		// 根据key值作升序排列
		Collections.sort(keys);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = para.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				sb.append(key).append(QSTRING_EQUAL).append(value);
			} else {
				sb.append(key).append(QSTRING_EQUAL).append(value).append(QSTRING_SPLIT);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 过滤空值、签名域
	 * @param para
	 * @param paramType  true：接口返回签名（使用）  false：请求签名
	 * @return Map<String,String>
	 * @Description:
	 * @author 刘恒   
	 * @date 2015年3月24日 下午2:34:19 
	 * @version V1.0
	 */
	public static Map<String, String> paraFilter(Map<String, String> para,boolean type) {

        Map<String, String> result = new HashMap<String, String>();

        if (para == null || para.size() <= 0) {
            return result;
        }

        for (String key : para.keySet()) {
            String value = para.get(key);
            if(type && !key.equalsIgnoreCase(RSCODE) && !key.equalsIgnoreCase(RSMEG)){
        		continue;
        	}
            // 排除空值、签名域
            if (value == null || value.equals("") || key.equalsIgnoreCase(SIGNATURE)) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
	
	
	 /** *//**
     * <p>
     * 公钥加密
     * </p>
     * 
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64Helper.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        byte [] encodeDataByteArray = RsaHelper.encryptData(data, publicK);
        return encodeDataByteArray;
    }
    
    /**
     * 公钥加密，并特殊处理base64中的字符.用于http get	请求传输
     * @param originData  明文
     * @param publicKey 公钥的encoded
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKeyGet(String originData, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64Helper.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        byte [] encodeDataByteArray = RsaHelper.encryptData(originData.getBytes("utf-8"), publicK);
        return replaceBase64(Base64Helper.encode(encodeDataByteArray));
    }
    /**
     * 解密特殊处理的base64
     * @param encryptedData 密文
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKeyGet(String encryptedData, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64Helper.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        byte [] decryptedDataByteArray = RsaHelper.decryptData(Base64Helper.decode(rebackBase64(encryptedData)), privateK);
        if(decryptedDataByteArray == null) return null;
        String decryptedData = new String(decryptedDataByteArray, "utf-8");
        return decryptedData;
    }
    /**
     * 替换+/为-*
     * @param base64Str  base64字符串
     * @return
     */
    public static String replaceBase64(String base64Str){
    	return base64Str.replaceAll("\\+", "\\-").replaceAll("/", "\\*");
    }
    /***
     * 还原特殊字符为base64字符串
     * @param base64ReplaceStr  特殊处理的base64字符串
     * @return
     */
    public static String rebackBase64(String base64ReplaceStr){
    	return base64ReplaceStr.replaceAll("\\-", "\\+").replaceAll("\\*", "/");
    }
    /** *//**
     * <P>
     * 私钥解密
     * </p>
     * 
     * @param encryptedData 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    
    public static String decryptRSAToString(String encryptedData, String privateKey) throws Exception{
    	byte[] encrypted = encryptedData.getBytes();
    	return decryptByPrivateKey(encrypted, privateKey);
    	
    }
    
    public static String decryptByPrivateKey(byte[] encryptedData, String privateKey)
            throws Exception {
    	
    	
        byte[] keyBytes = Base64Helper.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        byte [] decryptedDataByteArray = RsaHelper.decryptData(encryptedData, privateK);
        if(decryptedDataByteArray == null) return null;
        String decryptedData = new String(decryptedDataByteArray, "utf-8");
        return decryptedData;
    }

    /**
     * 按key的顺序生成key=value串(值不为空的key-value才会返回)
     * @param parameters 其他要加入签名的参数
     * @param key 平台md5key
     * @date 2017-02-28
     * @return
     */
    public static String generatorSignParam(Map<String, String> parameters,String key) {
        StringBuffer params = new StringBuffer();
        if(!parameters.isEmpty()) {
        	Object[] keys = parameters.keySet().toArray();
			Arrays.sort(keys);
			for (int i = 0; i < keys.length; i++) {
        		String name = keys[i].toString();
        		String value = parameters.get(name);
        		if(StringUtil.isValidString(value)){
        			params.append(name + "=");
        			params.append(value);
        			params.append("&");
        		}
               
            }
        }
        params.append("key="+key);
        return params.toString();
    }
	
	public static void main(String[] args) throws Exception{
//		String msg = "罗庆龙";
		//Map<String,String> keys = RSAUtil.generateStringKsys();
		//String publicKey = keys.get(RSAUtil.PUBLIC_KEY);
		//System.out.println(keys.toString());
//		String publicMsg = RSAUtil.encryptByPublicKey(msg, publicKey);
//		System.out.println("----------"+publicMsg);
//		
//		String privateKey = keys.get(RSAUtil.PRIVATE_KEY);
//		String endMsg = RSAUtil.decryptByPrivateKey(publicMsg, privateKey);
//		
//		System.out.println(endMsg);
		
		String url = URLEncoder.encode("http://local.luoqinglong.com:8020/TWS/account/overview-main.shtml","UTF-8");
		
		/************* 第三方注册 *****************/
		//RSA加密
		String infoMingwen = "cooporgname=TTM商家二2&cooporgcode=GL100000033&cooploginname=luoeeeeeeeeee333@qq.com&coopuserid=111000333&notifyurl=http%3A%2F%2Fbaidu.com";
		String publicKey1 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbf0mFWF2r4YfaT1catS1fXFFkFp7DSiX/IhuolNuDKnTKa2/rhKOR7ZO5/H6J1JxKVlhieYl6lEiwByeDCuZRbTMq/bhT9gjdh5BsOXvNr6I7QSYI9nSV5PZhWtu47tlR3//hc9SRccQo66tGQx8uTEjjQegFQsYOrx1NtQrPbQIDAQAB";//keys1.get(RSAUtil.PUBLIC_KEY);
		String info = RSAUtil.encryptByPublicKeyGet(infoMingwen, publicKey1); 
		System.out.println("第三方注册info密文："+info);
		
		String privateKey1 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJt/SYVYXavhh9pPVxq1LV9cUWQWnsNKJf8iG6iU24MqdMprb+uEo5Htk7n8fonUnEpWWGJ5iXqUSLAHJ4MK5lFtMyr9uFP2CN2HkGw5e82vojtBJgj2dJXk9mFa27ju2VHf/+Fz1JFxxCjrq0ZDHy5MSONB6AVCxg6vHU21Cs9tAgMBAAECgYA/fq191sSBMBmySl2OGVdKrj7J+PahVgDH+pyCrS4plEeWdGKCMGo+Z13AP1tIWtnq/PEgXPKHbE92GaXHu8ap7Dbc2132S8DMw/ENDCcoKko1bm3n749mg5xQp+pG2/VTqYN0mqab26hGAaRe8alqN4H1qDr7DiHPnMv0oak7PQJBAM9CS5oTln11T0NmoG4yxzKN3GnkIH5BhM7YYDvU+BbCH6Z4WhXTYejlTZQrycscGfXnsLD4dJmtkvzHsVnx458CQQDAEMGFf/iVQTvlADi9umGcjuf0wZpvFGgP0wWzgN4Td0q5pdo1ialFY7vmDJ6l+8qLeF182a8+Ehrhmt2cPBFzAkEAw6fmDvHm41DXHt3HHcC547OgHCbLteTMJyiE0HbgLTvc/R4ojOocR+wQLIyZ1zAUIdwzJR2nlVwSoqcxeaOsKQJATVbx9mX/nWp1SdN7BbtFjxtkgARtwYkAFk8tHokn48LHjlYgtipGxTBR+2Ldh40KlHrVem7VyDLWLD6GOmFdzQJAJUATreSlavtCZBLCVXhnO0sZBY47zhlS7s7qLjud6n3HUaSGGt5pQCZAzbWbyw+zmCSH9/RBIPn8GBNdgPlzbg==";//keys1.get(RSAUtil.PRIVATE_KEY);
		
		//md5
		try {
			String key = "eki6ixw9y3q5rqd3";
			Map<String, String> signParamter = new HashMap<String, String>();
			signParamter.put("channel", "MGKD6EP5");
			signParamter.put("encrypt_type", "RSA");
			signParamter.put("info", info);
			
			String md5 = generatorSignParam(signParamter,key);
			String signString = MD5.MD5Encode(md5).toUpperCase();
			System.out.println("第三方注册参数:"+md5);
			System.out.println("第三方注册签名sign:"+signString);
		}   catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		/****************** 管理员代理登录 ************/
		//RSA
		String infoProxyLoginMingwen = "orgcode=TJM10030177&tfsuserid=10151";
		String infoProxyLogin = RSAUtil.encryptByPublicKeyGet(infoProxyLoginMingwen, publicKey1); 
		System.out.println("管理员代理登录 密文info："+infoProxyLogin);
		
		//md5
		try {
			String key = "eki6ixw9y3q5rqd3";
			Long reqtime = new Date().getTime();
			//String md5 = "channel=MGKD6EP5&encrypt_type=RSA&info="+infoProxyLogin+"&jumpurl="+url+"&reqtime="+reqtime+"&key="+key;
			
			Map<String, String> signParamter = new HashMap<String, String>();
			signParamter.put("channel", "MGKD6EP5");
			signParamter.put("encrypt_type", "RSA");
			signParamter.put("info", infoProxyLogin);
			signParamter.put("jumpurl", url);
			signParamter.put("reqtime", reqtime.toString());
			
			String md5 = generatorSignParam(signParamter,key);
			
			String signString = MD5.MD5Encode(md5).toUpperCase();
			System.out.println("管理员代理登录 参数:"+md5);
			System.out.println("管理员代理登录 sign:"+signString);
		}   catch (Exception e) {
			e.printStackTrace();
		} 
	}
    
}
