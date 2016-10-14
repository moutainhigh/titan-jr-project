package com.fangcang.titanjr.common.enums;

/**
 * Created by zhaoshan on 2016/5/18.
 */
public enum SupportBankEnum {

    //B2B网银
    cmb("B2B","cmb","招商银行"),icbc("B2B","icbc","中国工商银行"),
    ccb("B2B","ccb","中国建设银行"),abc("B2B","abc","中国农业银行"),
    cmbc("B2B","cmbc","中国民生银行"),spdb("B2B","spdb","上海浦东发展银行"),
    ceb("B2B","ceb","光大银行"),comm("B2B","comm","交通银行"),
    boc("B2B","boc","中国银行"),cib("B2B","cib","兴业银行"),

    //B2C储蓄卡
    B2Ccmb("B2C","cmb","招商银行"),B2Cicbc("B2C","icbc","中国工商银行"),
    B2Cccb("B2C","ccb","中国建设银行"),B2Cabc("B2C","abc","中国农业银行"),
    B2Ccmbc("B2C","cmbc","中国民生银行"),B2Cspdb("B2C","spdb","上海浦东发展银行"),
    B2Cceb("B2C","ceb","光大银行"),B2Ccomm("B2C","comm","交通银行"),
    B2Cboc("B2C","boc","中国银行"),B2Ccib("B2C","cib","兴业银行"),
    B2Chxb("B2C","hxb","华夏银行"),B2Ccgb("B2C","cgb","广东发展银行"),
    B2Ccitic("B2C","citic","中信银行"),B2Cbos("B2C","bos","上海银行"),
    B2Cpingan("B2C","pingan","平安银行"),B2Cpsbc("B2C","psbc","邮政储蓄"),
    B2Cb1669("B2C","b1669","枣庄银行"),B2Cb1552("B2C","b1552","汉口银行"),
    B2Cb1608("B2C","b1608","齐商银行"),B2Cb1629("B2C","b1629","泰安银行"),

    //B2C信用卡
    cmbCredit("Credit","cmb","招商银行"),icbcCredit("Credit","icbc","中国工商银行"),
    ccbCredit("Credit","ccb","中国建设银行"),abcCredit("Credit","abc","中国农业银行"),
    cmbcCredit("Credit","cmbc","中国民生银行"),spdbCredit("Credit","spdb","上海浦东发展银行"),
    cebCredit("Credit","ceb","光大银行"),commCredit("Credit","comm","交通银行"),
    bocCredit("Credit","boc","中国银行"),cibCredit("Credit","cib","兴业银行"),
    hxbCredit("Credit","hxb","华夏银行"),citicCredit("Credit","citic","中信银行"),
    psbcCredit("Credit","psbc","邮政储蓄"),b1669Credit("Credit","b1669","枣庄银行"),
    b1552Credit("Credit","b1552","汉口银行");

    public String bankType;
    public String bankRemark;
    public String bankName;

    private SupportBankEnum(String bankType, String bankName, String bankRemark){
        this.bankType = bankType;
        this.bankName = bankName;
        this.bankRemark = bankRemark;
    }

    public static SupportBankEnum getBankDetailByName(String bankName){
        for (SupportBankEnum supportBankEnum : SupportBankEnum.values()){
            if (supportBankEnum.bankName.equals(bankName)){
                return supportBankEnum;
            }
        }
        return null;
    }
}
