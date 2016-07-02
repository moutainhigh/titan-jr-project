package com.fangcang.titanjr.dto.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * Created by zhaoshan on 2016/4/25.
 */
public class OrganBindRequest extends BaseRequestDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1499751845395422344L;
	
	@NotBlank
    private String userId;
	@NotBlank
    private String merchantCode;

    private String merchantName;
    private String userloginname;
    private String password;
    
    //1.做绑定操作，0.做解除绑定操作
    @NotNull
    private Integer operateType;

    /*** 绑定用户的信息 ****/
    private Integer tfsuserid;
    private Integer fcuserid;
    private String fcusername;
    private String fcloginname;
    
    
    public String getUserloginname() {
		return userloginname;
	}
	public void setUserloginname(String userloginname) {
		this.userloginname = userloginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
        return userId;
    }
	public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }
	public Integer getTfsuserid() {
		return tfsuserid;
	}
	public void setTfsuserid(Integer tfsuserid) {
		this.tfsuserid = tfsuserid;
	}
	public Integer getFcuserid() {
		return fcuserid;
	}
	public void setFcuserid(Integer fcuserid) {
		this.fcuserid = fcuserid;
	}
	public String getFcusername() {
		return fcusername;
	}
	public void setFcusername(String fcusername) {
		this.fcusername = fcusername;
	}
	public String getFcloginname() {
		return fcloginname;
	}
	public void setFcloginname(String fcloginname) {
		this.fcloginname = fcloginname;
	}
    
}
