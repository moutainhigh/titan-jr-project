package com.titanjr.fop.constants;

/**
 * Created by zhaoshan on 2018/1/16.
 */
public enum FuncCodeEnum {
    RECHARGE_4015("4015", "网关充值"),//有记录
    RECEIVE_4013("4013", "代收"),//无记录
    PAYOUT_4014("4014", "代付"),//只有提现卡认证时一条1分记录
    REVERSAL_10011("10011", "商户冲正"),//无记录
    REVERSAL_10012("10012", "清结算冲正"),//无记录
    WITHDRAW_4016("4016", "提现"),//有记录
    TRANSFER_3001("3001", "转账"),;//有记录

    private String key;
    private String desc;

    FuncCodeEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
