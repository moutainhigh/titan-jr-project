package com.titanjr.checkstand.constants;

/**
 * 融数错误编码
 * @author Jerry
 * @date 2017年12月6日 下午6:27:51
 */
public enum RSErrorCodeEnum {

    VALID_FAILD("11001","验签失败"),
    FORMAT_ERROR("11002","格式错误"),
    MERCHANT_NOTFUND("11003","商户不存在"),
    ORDER_NOTFUND("11004","订单不存在"),
    ORDER_OUTTIME("11005","订单超时"),
    SYSTEM_ERROR("11006","系统错误"),
    ORDER_NOTPAY("11007","订单未完成支付"),
    REFUND_AMOUNT_ERROR("11008","退款金额不能大于可退款金额"),
    REFUND_OUTTIME("11009","退款逾期"),
    REFUND_QUERY_ERROR("11010","退款信息查询错误"),
    REQUEST_MORE("11012","请求频繁"),
    
    //自己添加部分，如果融数存在此错误返回可替换
    TEMP_ERROR("",""),
    PRAM_ERROR("90001","参数错误");
    
	private String errorCode;
    private String errorMsg;
    
    private RSErrorCodeEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorCode = errorMsg;
	}
    
    public static RSErrorCodeEnum build(String errMsg){
    	RSErrorCodeEnum.TEMP_ERROR.setErrorCode("99999");
    	RSErrorCodeEnum.TEMP_ERROR.setErrorMsg(errMsg);
    	return RSErrorCodeEnum.TEMP_ERROR;
    	
    }
    
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
    
}
