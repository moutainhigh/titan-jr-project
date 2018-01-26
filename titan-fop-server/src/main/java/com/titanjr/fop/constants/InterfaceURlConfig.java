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
        INTERFACE_URL_MAP.put(InterfaceConfigEnum.FREEZE_BALANCE, "/account/freezeAccountBalance.shtml");
        INTERFACE_URL_MAP.put(InterfaceConfigEnum.UNFREEZE_BALANCE, "/account/unFreezeAccountBalance.shtml");
        INTERFACE_URL_MAP.put(InterfaceConfigEnum.ORDER_OPERATE, "/order/orderOper.shtml");
        INTERFACE_URL_MAP.put(InterfaceConfigEnum.REFUND_ORDER_OPERATE, "/order/operateRefundOrder.shtml");
        INTERFACE_URL_MAP.put(InterfaceConfigEnum.ORDERN_QUERY, "/order/orderNQuery.shtml");
        INTERFACE_URL_MAP.put(InterfaceConfigEnum.ACCOUNT_BALANCE_TRANSFER, "/order/orderTransfer.shtml");
        

    }
    
    public static String checkstand_GateWayURL = null;

}
