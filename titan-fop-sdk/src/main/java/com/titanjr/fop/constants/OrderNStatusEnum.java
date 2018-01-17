package com.titanjr.fop.constants;

/**
 * Created by zhaoshan on 2018/1/16.
 */
public enum OrderNStatusEnum {

    NOEFFECT_0("0", "失效"),//失效不返回
    NORMAL_1("1", "正常"),//充值和转账返回
    CHECK_SUCC_2("2", "对账成功"),//现有场景无
    CHECK_FAIL_3("3", "对账失败"),//现有场景无
    PAY_SUCC_4("4", "付款成功"),//提现返回
    PAY_FAIL_5("5", "付款失败"),//失败不返回
    CORRECT_6("6", "交易已冲正"),//提现可能出现已冲正，需上游接口返回
    SENDING_7("7", "交易发送中"),;//最常见提现发送中，需上游接口返回

    private String key;
    private String desc;

    OrderNStatusEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
