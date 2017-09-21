package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * Created by zhaoshan on 2016/4/21.
 */
public class OrganCheckRequest extends BaseRequestDTO{
    @NotNull
	private int orgId;
 
    @NotNull
	private int checkstatus;
	//参考：OrgCheckResultEnum
   // @NotNull
   // private String resultKey;
    private String resultMsg;

    //操作类型用来标示是后台金服运营员审核 = 1
    //还是初审失败重新发起申请时候的申请 = 2
    
    private Integer operateType;
    //是否需要发送机构注册信息
    private boolean isSendSms = true;
    
	public int getOrgId() {
		return orgId;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public int getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(int checkstatus) {
		this.checkstatus = checkstatus;
	}
 
    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

	public boolean getIsSendSms() {
		return isSendSms;
	}

	public void setIsSendSms(boolean isSendSms) {
		this.isSendSms = isSendSms;
	}

}
