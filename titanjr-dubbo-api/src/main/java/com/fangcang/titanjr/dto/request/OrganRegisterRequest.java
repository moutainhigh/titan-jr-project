package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.bean.OrgImageInfo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by zhaoshan on 2016/4/21.
 */
public class OrganRegisterRequest extends BaseRequestDTO {
    //公司名称，需生成公司编码
	@NotBlank
    private String orgName;
    //注册机构时候必须字段，跟公司名可以一致
    private String userName;
    
    private String orgCode;
    //联系人
    private String connect;
    //联系电话
    private String mobileTel;
    //注册个人机构时必须，营业执照号
    private String buslince;


    //注册个人机构时必须，跟orgName可以一样
   // private String personName;
    //注册个人机构时必须，身份认证类型，默认身份证 = 0
    private String certificateType;
    //注册个人机构时必须，身份认证编码
    private String certificateNumber;


    //机构类型
    private Integer orgType;
    @NotNull
    private Integer userType;

    //注册来源，1.SaaS页面注册，2.金服官网注册，3.后台自动注册,RegisterSourceEnum
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
    private String titancode;
    
    /***************** 房仓信息 *****************/
    //房仓登录用户名
    private String fcLoginUserName;
    //商家编码或者第三方的编码
    private String  merchantCode;
    //商家名称
    private String merchantname;
    //SAAS
    private String bindapplyname;
    
    private String bindapplyphone;
    
    
    /*******    *****/
    private String checkResKey;
    
    
    public String getCheckResKey() {
		return checkResKey;
	}

	public void setCheckResKey(String checkResKey) {
		this.checkResKey = checkResKey;
	}

	public String getTitancode() {
		return titancode;
	}

	public void setTitancode(String titancode) {
		this.titancode = titancode;
	}

	public String getBindapplyname() {
		return bindapplyname;
	}

	public void setBindapplyname(String bindapplyname) {
		this.bindapplyname = bindapplyname;
	}

	public String getBindapplyphone() {
		return bindapplyphone;
	}

	public void setBindapplyphone(String bindapplyphone) {
		this.bindapplyphone = bindapplyphone;
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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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

	public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getBuslince() {
        return buslince;
    }

    public void setBuslince(String buslince) {
        this.buslince = buslince;
    }

//    public String getPersonName() {
//        return personName;
//    }
//
//    public void setPersonName(String personName) {
//        this.personName = personName;
//    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
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
    
}
