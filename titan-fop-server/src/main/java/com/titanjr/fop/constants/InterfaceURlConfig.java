package com.titanjr.fop.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoshan on 2017/12/21.
 */
public class InterfaceURlConfig {

    //简单配置接口所用URL请求地址
    public static final Map<InterfaceConfigEnum, String> INTERFACE_URL_MAP = new HashMap<InterfaceConfigEnum, String>();


    static {
        INTERFACE_URL_MAP.put(InterfaceConfigEnum.SESSION_GET, "/common/getSession.shtml");
        INTERFACE_URL_MAP.put(InterfaceConfigEnum.BALANCE_GETLIST, "/account/getBalanceList.shtml");
        INTERFACE_URL_MAP.put(InterfaceConfigEnum.FREEZE_BALANCE, "/account/freezeBalance.shtml");
        INTERFACE_URL_MAP.put(InterfaceConfigEnum.COMPANY_CREATE, "/organ/createCompany.shtml");
    }

    public static void main(String[] args) {
        //1492784442821246488
        //1514279778259484020
        //1514279806079634938
        //1514279601978

    }

}
