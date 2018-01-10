package com.titanjr.fop.constants;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by zhaoshan on 2018/1/8.
 */
public class CommonConstants {

    @Value("${pay.appSecret}")
    public static String appSecret;

}
