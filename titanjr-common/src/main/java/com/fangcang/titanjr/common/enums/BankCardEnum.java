package com.fangcang.titanjr.common.enums;


public class BankCardEnum {

    public enum BankCardStatusEnum {
    	INVALID("0", "失效/审核失败"),
    	NORMAL("1", "正常"), 
    	CHECKING("2", "审核中"),
    	NO_BANKCARD("20","未绑卡"),
    	BANKCARDSTATUS_EXCEPTION("99","绑卡状态不存在");
        private String key;
        private String des;

        private BankCardStatusEnum(String key, String des) {
            this.key = key;
            this.des = des;
        }

        public String getKey() {
            return key;
        }

        public String getDes() {
            return des;
        }
        
        public static BankCardStatusEnum  getEnumByKey(String key){
        	BankCardStatusEnum entity = BANKCARDSTATUS_EXCEPTION;
    		for(BankCardStatusEnum item : BankCardStatusEnum.values()) {
    			if(item.key.equals(key)) {
    				entity = item;
    				break;
    			}
    		}
    		return entity;
    	}
    }

    public enum BankCardPurposeEnum {
        DEBIT_CARD("1", "结算卡"), WITHDRAW_CARD("3", "提现卡"), DEBIT_WITHDRAW_CARD("4", "提现结算一体卡"), OTHER_CARD("2", "其他卡");
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
