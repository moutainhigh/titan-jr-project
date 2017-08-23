package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.bean.OrgImageInfo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by zhaoshan on 2016/4/21.
 */
public class OrganRegisterRequest extends RegOrgSubRequest {
     
    //注册机构时候必须字段，跟公司名可以一致
    private String userName;

    //
    @NotNull
    private Integer registerSource;
    //合作方类型，见CoopTypeEnum
    private Integer coopType;

    //图片信息列表，不需审核时候可为空
    private List<OrgImageInfo> orgImageInfoList;
    private String email;
    @NotBlank
    private String userloginname;
    
    private String password;
    
    //可以是数组,格式：111,222,333
    private String imageid;
    
    /***************** 房仓信息 *****************/
    //房仓登录用户名
    private String fcLoginUserName;
    //合作方的用户id
    private String coopUserId;
    //商家编码或者第三方的编码
    private String  merchantCode;
    //商家名称
    private String merchantname;
     
    /*******    *****/
    private String checkResKey;
    
    
    public String getCheckResKey() {
		return checkResKey;
	}

	public void setCheckResKey(String checkResKey) {
		this.checkResKey = checkResKey;
	}

	public String getMerchantname() {
		return merchantname;
	}

	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname;
	}

	public String getFcLoginUserName() {
		return fcLoginUserName;
	}

	public void setFcLoginUserName(String fcLoginUserName) {
		this.fcLoginUserName = fcLoginUserName;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	 

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageid() {
		return imageid;
	}

	public void setImageid(String imageid) {
		this.imageid = imageid;
	}

	 

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public List<OrgImageInfo> getOrgImageInfoList() {
        return orgImageInfoList;
    }

    public void setOrgImageInfoList(List<OrgImageInfo> orgImageInfoList) {
        this.orgImageInfoList = orgImageInfoList;
    }

    public Integer getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(Integer registerSource) {
        this.registerSource = registerSource;
    }


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

	public String getCoopUserId() {
		return coopUserId;
	}

	public void setCoopUserId(String coopUserId) {
		this.coopUserId = coopUserId;
	}

	public Integer getCoopType() {
		return coopType;
	}

	public void setCoopType(Integer coopType) {
		this.coopType = coopType;
	}
    
}
