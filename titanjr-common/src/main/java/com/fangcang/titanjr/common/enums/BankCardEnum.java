package com.fangcang.titanjr.common.enums;


public class BankCardEnum {

    public enum BankCardStatusEnum {
        NORMAL("1", "正常"), CHECKING("3", "审核中"), CHECK_FAIL("4", "审核失败"), SUBMIT("2", "待审核"),INVALID("0", "失效");
        private String key;
        private String des;

        BankCardStatusEnum(String key, String des) {
            this.key = key;
            this.des = des;
        }

        public String getKey() {
            return key;
        }

        public String getDes() {
            return des;
        }
    }

    public enum BankCardPurposeEnum {
        DEBIT_CARD("1", "企业用户"), WITHDRAW_CARD("3", "提现卡"), DEBIT_WITHDRAW_CARD("4", "提现结算一体卡"), OTHER_CARD("2", "其他卡");
        private String key;
        private String des;

        BankCardPurposeEnum(String key, String des) {
            this.key = key;
            this.des = des;
        }

        public String getKey() {
            return key;
        }

        public String getDes() {
            return des;
        }
    }
}
