package com.fangcang.titanjr.common.enums;


/**
 * 1.待审核，2.初审未通过，3.复审中，4.复审未通过，5.已通过
 * Created by zhaoshan on 2016/4/25.
 */
public enum OrgCheckResultEnum {

    FT(1,"FT","待审核"),FT_INVALID(2,"FT_INVALID","初审未通过"),REVIEW(3,"REVIEW","复审中"),
    REVIEW_INVALID(4,"REVIEW_INVALID","复审未通过"),PASS(5,"PASS","已通过");
    private int checkstatus;
    private String resultkey;
    private String resultmsg;

    private OrgCheckResultEnum(int checkstatus,String resultkey,String resultmsg){
    	this.checkstatus = checkstatus;
        this.resultkey = resultkey;
        this.resultmsg = resultmsg;
    }
    
    public static OrgCheckResultEnum  getEnumByCheckstatus(int checkstatus){
    	OrgCheckResultEnum statusEnum = null;
		for(OrgCheckResultEnum item : OrgCheckResultEnum.values()) {
			if(item.checkstatus == checkstatus) {
				statusEnum = item;
				break;
			}
		}
		return statusEnum;
	}
    public static OrgCheckResultEnum  getEnumByCheckResKey(String checkResKey){
    	OrgCheckResultEnum statusEnum = REVIEW;
		for(OrgCheckResultEnum item : OrgCheckResultEnum.values()) {
			if(item.resultkey == checkResKey) {
				statusEnum = item;
				break;
			}
		}
		return statusEnum;
	}
	public int getCheckstatus() {
		return checkstatus;
	}

	public String getResultkey() {
		return resultkey;
	}

	public String getResultmsg() {
		return resultmsg;
	}

	

}
