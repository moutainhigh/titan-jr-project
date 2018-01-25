package com.titanjr.fop.util;

import com.fangcang.util.StringUtil;
import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.domain.RequestParametersHolder;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaoshan on 2017/12/20.
 */
public abstract class FopUtils {

    private FopUtils() {
    }

    public static String signFopRequest(RequestParametersHolder parametersHolder, String appSecret) throws IOException {
        TreeMap treeMap = new TreeMap();
        FopHashMap appParams = parametersHolder.getApplicationParams();
        if (appParams != null && appParams.size() > 0) {
            treeMap.putAll(appParams);
        }

        FopHashMap protocalParams = parametersHolder.getProtocalMustParams();
        if (protocalParams != null && protocalParams.size() > 0) {
            treeMap.putAll(protocalParams);
        }

        FopHashMap optParams = parametersHolder.getProtocalOptParams();
        if (optParams != null && optParams.size() > 0) {
            treeMap.putAll(optParams);
        }

        Set entrySet = treeMap.entrySet();
        StringBuilder stringBuilder = new StringBuilder(appSecret);
        Iterator iterator = entrySet.iterator();

        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (StringUtil.isValidString((String) entry.getKey()) && StringUtil.isValidString((String) entry.getValue())) {
                stringBuilder.append((String) entry.getKey()).append((String) entry.getValue());
            }
        }
        byte[] encryptRequest = encryptMD5(stringBuilder.toString());
        return byte2hex(encryptRequest);
    }


    public static byte[] encryptMD5(String encryptRequest) throws IOException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] messageByte = messageDigest.digest(encryptRequest.getBytes("UTF-8"));
            return messageByte;
        } catch (GeneralSecurityException sex) {
            throw new IOException(sex);
        }
    }

    public static String byte2hex(byte[] byteRequest) {
        StringBuilder response = new StringBuilder();

        for (int index = 0; index < byteRequest.length; ++index) {
            String result = Integer.toHexString(byteRequest[index] & 255);
            if (result.length() == 1) {
                response.append("0");
            }
            response.append(result.toUpperCase());
        }
        return response.toString();
    }
    private static boolean isMatch(String regex, String orginal){
        if (orginal == null || orginal.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(orginal);
        return isNum.matches();
    }

    public static boolean isPositiveInteger(String orginal) {
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
    }

}
