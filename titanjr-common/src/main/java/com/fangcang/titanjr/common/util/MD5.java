package com.fangcang.titanjr.common.util;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;

import com.fangcang.util.StringUtil;

/**
 * Created by zhaoshan on 2016/4/15.
 */
public class MD5 {

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }
    /***
     * 请使用 MD5Encode(string ,string)
     * @param origin
     * @return
     */
    @Deprecated 
    public static String MD5Encode(String origin){
    	return MD5Encode(origin,"");
    }
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    /**
     * md5加密
     * @param password
     * @param salt
     * @return
     */
    public static String titanEncrypt(String password,String salt){
    	return MD5.MD5Encode(password+salt, "UTF-8");
    }

    /**
     * 生成12位含特殊字符的串
     * @return
     */
    public static String getSalt(){
    	return getSalt(CommonConstant.PASSWORD_SALT_LENGTH);
    }
    
    /**
     * 生成len位含特殊字符的串
     * @return
     */
    public static String getSalt(int len){
    	return RandomStringUtils.random(len, new char[]{'!','@','#','$','%','^','&','*','_','+',':','|','<','>','?','~','B','Y','H','N','U','J','M','I','K','O','L','P','1','2','3','4','5','6','7','8','9','0','o','p','q','r','s','t','u','v','w','x','y','z'});
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
    
     
    public static void main(String[] arg){
    	//System.out.println(MD5.MD5Encode("1111"));
    	//System.out.println(MD5.MD5Encode("1111","UTF-8"));
    }
}
