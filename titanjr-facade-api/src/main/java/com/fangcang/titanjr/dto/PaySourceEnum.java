package com.fangcang.titanjr.dto;

/**
 * 支付来源
 * @author fangdaikang
 *
 */
public enum PaySourceEnum {
	    /*B2B("1","B2B"), SUPPLY("2","财务供应端"),
	    ALLIANCE("3","商家联盟"),MOBILE("4","移动端"),RECHARDE("5","充值"),
	    OPEN_ORG("6","对外开放用户"),TT_MALL("7","TTMALL收银台"),WX_PUBLIC_PAY("8","微信公众号");*/
		
		//优化调整（原来的3是无用的）   2017-08-18   update by jerry
		DISTRIBUTION_PC("1","分销工具PC收银台"), 
		FINANCE_SUPPLY_PC("2","财务供应PC收银台"),
	    TRADING_PLATFORM_PC("3","交易平台PC收银台"),
	    DISTRIBUTION_MOBILE("4","分销工具移动端收银台"),
	    RECHARGE("5","充值收银台"),
	    OPEN_PLATFORM_PC("6","对外开放PC端"),
	    TT_MALL_PC("7","TTM的PC端收银台"),
	    TT_MALL_MOBILE("8","TTM的移动端收银台"),
	    TRADING_PLATFORM_MOBILE("9","交易平台移动端收银台");

	    private String deskCode;
	    private String deskName;

	    public String getDeskCode() {
			return deskCode;
		}

		public void setDeskCode(String deskCode) {
			this.deskCode = deskCode;
		}

		public String getDeskName() {
			return deskName;
		}

		public void setDeskName(String deskName) {
			this.deskName = deskName;
		}

		private PaySourceEnum(String deskCode,String deskName){
	        this.deskCode = deskCode;
	        this.deskName = deskName;
	    }
}
