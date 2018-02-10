package com.titanjr.fop.constants;

/**
 * Created by zhaoshan on 2018/1/29.
 */
public enum BankCardMapper {
    //储蓄卡
    DEPOSIT_BOC("104", "中国银行", "boc", "10"),
    DEPOSIT_ABC("103", "农业银行", "abc", "10"),
    DEPOSIT_ICBC("102", "工商银行", "icbc", "10"),
    DEPOSIT_CCB("105", "建设银行", "ccb", "10"),
    DEPOSIT_CIB("309", "兴业银行", "cib", "10"),
    DEPOSIT_CEB("303", "光大银行", "ceb", "10"),
    DEPOSIT_CMBC("305", "民生银行", "cmbc", "10"),
    DEPOSIT_PSBC("403", "邮政储蓄银行", "psbc", "10"),
    DEPOSIT_COMM("301", "交通银行", "comm", "10"),
    DEPOSIT_CITIC("302", "中信银行", "citic", "10"),
    DEPOSIT_PINTAN("307", "平安银行", "pingan", "10"),
    DEPOSIT_HXB("304", "华夏银行", "hxb", "10"),
    DEPOSIT_SPDB("310", "浦发银行", "spdb", "10"),
    DEPOSIT_CGB("306", "广发银行", "cgb", "10"),
    DEPOSIT_CMB("308", "招商银行", "cmb", "10"),
    DEPOSIT_BOS("310", "上海银行", "bos", "10"),
    //信用卡
    CREDIT_BOC("104", "中国银行", "boc", "11"),
    CREDIT_ABC("103", "农业银行", "abc", "11"),
    CREDIT_ICBC("102", "工商银行", "icbc", "11"),
    CREDIT_CCB("105", "建设银行", "ccb", "11"),
    CREDIT_CIB("309", "兴业银行", "cib", "11"),
    CREDIT_CEB("303", "光大银行", "ceb", "11"),
    CREDIT_CMBC("305", "民生银行", "cmbc", "11"),
    CREDIT_PSBC("403", "邮政储蓄银行", "psbc", "11"),
    CREDIT_CITIC("302", "中信银行", "citic", "11"),
    CREDIT_PINTAN("307", "平安银行", "pingan", "11"),
    CREDIT_HXB("304", "华夏银行", "hxb", "11"),
    CREDIT_SPDB("310", "浦发银行", "spdb", "11"),
    CREDIT_CGB("306", "广发银行", "cgb", "11"),
    CREDIT_CMB("308", "招商银行", "cmb", "11");

    private String bankCode;//银行编码
    private String bankName;//银行名称
    private String bankInfo;//银行标示
    private String cardType;//10-储蓄卡，11-信用卡

    BankCardMapper(String bankCode, String bankName, String bankInfo, String cardType) {
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.bankInfo = bankInfo;
        this.cardType = cardType;
    }

    public static BankCardMapper getBankCardByCode(String bankCode){
        for (BankCardMapper cardMapper : BankCardMapper.values()){
            if (cardMapper.bankCode.equals(bankCode)){
                return cardMapper;
            }
        }
        return null;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public String getCardType() {
        return cardType;
    }
}
