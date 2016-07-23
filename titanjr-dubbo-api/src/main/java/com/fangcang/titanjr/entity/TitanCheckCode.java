package com.fangcang.titanjr.entity;

import java.util.Date;

/**
 * 验证码
 * @author luoqinglong
 * @2016年7月21日
 */
public class TitanCheckCode implements java.io.Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3244141399152439389L;

	private Integer codeId;

    private String receiveAddress;

    private String code;

    private Integer codeType;

    private Integer isactive;

    private Date createTime;

    private Date expiredTime;
    /**
     * 验证码使用时间
     */
    private Date useTime;

    public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
    
	public Integer getIsactive() {
		return isactive;
	}

	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}

	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	public Integer getCodeType() {
		return codeType;
	}

	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

}