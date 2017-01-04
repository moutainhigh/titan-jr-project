package com.fangcang.titanjr.common.enums;
/**
 * 授信单审核结果
 * @author wengxitao
 */
public enum LoanCreditStatusEnum {
	//1 草稿 2 授信初审  3 授信终审 4 审核失败 5 授信成功 6 授信单过期  7 授信失效Expire
	TO_CHECK(2,"待审核"),PASS(3, "复审中"), NO_PASS(4, "审核不通过"),REVIEW_PASS(5,"融数终审通过") , EXPIRE(6 , "授信过期"),INFO_COMMIT_ING(10 , "资料提交中"),INFO_COMMIT_FAIL(11 , "资料提交失败");

	int status;
	String desc;

	LoanCreditStatusEnum(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}
	
	public static LoanCreditStatusEnum  getEnumByStatus(int status){
		LoanCreditStatusEnum entity = null;
		for(LoanCreditStatusEnum item : LoanCreditStatusEnum.values()) {
			if(item.getStatus() == status) {
				entity = item;
				break;
			}
		}
		return entity;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
