package com.titanjr.checkstand.constants;

import com.fangcang.util.StringUtil;

/**
 * 通联网银退款状态枚举
 * @author Jerry
 * @date 2017年12月6日 下午5:39:49
 */
public enum TLNetBankRefundResultEnum {

    UN_PROCESS("TKSUCC0001","退款未受理","0"),
    IN_AUDIT("TKSUCC0002","待通联审核","0"),
    PASS_AUDIT("TKSUCC0003","通联审核通过","0"),
    IN_PROCESS("TKSUCC0005","处理中","0"),
    OFFSET("TKSUCC0004","退款冲销","4"),
    SUCCESS("TKSUCC0006","退款成功","2"),
    FAILD("TKSUCC0007","退款失败","3"),
    UN_AUDIT("TKSUCC0008","通联审核不通过","1");

    private String key; //通联退款结果key
    private String remark;
    private String status; //融数退款状态    0处理中，1审核失败，2退款成功 ,3退款失败，4，退款冲销

    private TLNetBankRefundResultEnum(String key, String remark, String status) {
		this.key = key;
		this.remark = remark;
		this.status = status;
	}

	
    public static TLNetBankRefundResultEnum getTLRefundResultEnumByKey(String key){
    	if(!StringUtil.isValidString(key)){
    		return null;
    	}
        for (TLNetBankRefundResultEnum refundResultEnum : TLNetBankRefundResultEnum.values()){
            if (key.equals(refundResultEnum.key)){
                return refundResultEnum;
            }
        }
        return null;
    }


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

}
