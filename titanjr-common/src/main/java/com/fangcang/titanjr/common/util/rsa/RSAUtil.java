package com.fangcang.titanjr.common.util.rsa;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.Cipher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;











import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.util.StringUtil;


public class RSAUtil {
	private static final Log log = LogFactory.getLog(RSAUtil.class);
	/** *//** 
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
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
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
    	byte[] result = decryptByPrivateKey(encrypted, privateKey);
    	return new String(result);
    	
    }
    
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)
            throws Exception {
    	
    	
        byte[] keyBytes = Base64Helper.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM , "BC");
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

	
	
	public static void main(String[] args){
		try {
			
			Map<String,String> keyMap = generateStringKsys();
//			String apc = "{\"name\":\"zhangsan\",\"escrowedDate\":\"2016-12-03\",\"goodsId\":\"201611141238596002654\",\"goodsDetail\":\"预定日本签证，加急，5张....\",\"goodsName\":\"签证预定单\",\"userId\":\"\",\"ruserId\":\"TJM10000109\",\"amount\":\"889\",\"payerType\":\"1001\",\"currencyType\":\"1\",\"checkOrderUrl\":\"\",\"notify\":\"http://localhost:8080/CashierDesk/payCallBack\"}";
//			RSAPublicKey publicKey = new RSAPublicKeySpec(new BigInteger(keyMap.get(RSAUtil.PUBLIC_KEY_MODULE)),new BigInteger(keyMap.get(RSAUtil.PUBLIC_KEY_EMPOENT)));
			
//			byte[] encryptMsg =  encryptByPublicKey(apc.getBytes(), keyMap.get(RSAUtil.PUBLIC_KEY));
			
//			byte[] decryptMsg = decryptByPrivateKey(encryptMsg, keyMap.get(RSAUtil.PRIVATE_KEY));
			
//			String ss = new String(decryptMsg,"UTF-8");
			System.out.println(keyMap.get(RSAUtil.PUBLIC_KEY_MODULE));
			System.out.println(keyMap.get(RSAUtil.PUBLIC_KEY_EMPOENT));
			System.out.println(keyMap.get(RSAUtil.PRIVATE_KEY));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("11");
			e.printStackTrace();
		}
	}
    
}
