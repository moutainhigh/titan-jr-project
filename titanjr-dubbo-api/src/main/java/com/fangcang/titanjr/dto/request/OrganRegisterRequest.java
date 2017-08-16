package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.bean.OrgImageInfo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by zhaoshan on 2016/4/21.
 */
public class OrganRegisterRequest extends RegOrgSubRequest {
    //公司名称，需生成公司编码
	//@NotBlank
   // private String orgName;
    //注册机构时候必须字段，跟公司名可以一致
    private String userName;
    
  //  private String orgCode;
    //联系人
  //  private String connect;
    //联系电话
  //  private String mobileTel;
    //注册个人机构时必须，营业执照号
 ///   private String buslince;


    //注册个人机构时必须，跟orgName可以一样
   // private String personName;
    //注册个人机构时必须，身份认证类型，默认身份证 = 0
  //  private String certificateType;
    //注册个人机构时必须，身份认证编码
   // private String certificateNumber;


    //机构类型
  //  private Integer orgType;
    @NotNull
    private Integer userType;

    //注册来源，见CoopTypeEnum
    @NotNull
    private Integer registerSource;

    //图片信息列表，不需审核时候可为空
    private List<OrgImageInfo> orgImageInfoList;
    private String email;
    @NotBlank
    private String userloginname;
    
    private String password;
    
    //可以是数组,格式：111,222,333
    private String imageid;
    //private String titancode;
    
    /***************** 房仓信息 *****************/
    //房仓登录用户名
    private String fcLoginUserName;
    //合作方的用户id
    private String coopUserId;
    //商家编码或者第三方的编码
    private String  merchantCode;
    //商家名称
    private String merchantname;
    //是否自动审核
    private boolean isAutoCheck = false;
    //是否创建真实机构
    //private boolean isCreateOrgSub = false;
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


    public boolean getIsAutoCheck() {
		return isAutoCheck;
	}

	public void setIsAutoCheck(boolean isAutoCheck) {
		this.isAutoCheck = isAutoCheck;
	}

//	public boolean getIsCreateOrgSub() {
//		return isCreateOrgSub;
//	}
//
//	public void setIsCreateOrgSub(boolean isCreateOrgSub) {
//		this.isCreateOrgSub = isCreateOrgSub;
//	}


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

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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
    
}
