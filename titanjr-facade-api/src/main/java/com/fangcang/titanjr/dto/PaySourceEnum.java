package com.fangcang.titanjr.dto;

/**
 * 支付来源
 * @author fangdaikang
 *
 */
public enum PaySourceEnum {
	    B2B("1","B2B"), SUPPLY("2","财务供应端"),
	    ALLIANCE("3","商家联盟"),MOBILE("4","移动端"),RECHARDE("5","充值"),
	    OPEN_ORG("6","对外开放用户"),TT_MALL("7","TTMALL收银台");

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
