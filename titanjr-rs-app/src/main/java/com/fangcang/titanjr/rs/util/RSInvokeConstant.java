package com.fangcang.titanjr.rs.util;

import com.Rop.api.DefaultRopClient;
import com.fangcang.titanjr.rs.entity.TitanPayMethodConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 此类需考虑安全性
 * Created by zhaoshan on 2016/5/10.
 */
public class RSInvokeConstant {

    //融数调用构造的client
    public static DefaultRopClient ropClient = null;
    //融数调用回话key
    public static String sessionKey = null;
    //支付网关地址
    public static String gateWayURL = null;
    //融数给出构造加密数据的签名
    public static String rsCheckKey = null;
    //房仓给融数构造加密数据的签名
    public static String titanjrCheckKey = null;
    /**
     * jr.fangcang.com商家的默认商家编码
     * 金服在SAAS系统中的商家编码
     */
    public static String defaultMerchant = null;
    //默认金服访问权限的角色id
    public static Long defaultRoleId = null;
    //支付方式配置表，key是userid，value主要包含支付方式回调地址
    public static Map<String, TitanPayMethodConfig> payMethodConfigMap = new HashMap<String, TitanPayMethodConfig>();
    //金服回调地址配置
    public static Map<String,String> callBackConfigMap = new HashMap<String, String>();
}
