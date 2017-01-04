package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

import java.io.InputStream;

/**
 * TODO 需要细化尺寸类型 原图，60*40
 * 图片处理工具
 * 图片类型：1.营业执照图片，2.身份证件图片，3.组织机构代码证，4.税务登记证， 5.经营场所认证
 * Created by zhaoshan on 2016/4/21.
 */
public class OrganImageUploadRequest extends BaseRequestDTO{

    /**
	 * 
	 */
	private static final long serialVersionUID = 928341342062386217L;
	private String fileName;
    
    private String userId;
    private String orgCode;

    private Integer imageType;
   // private InputStream inStream;
    private byte[] fileBytes;
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

//    public InputStream getInStream() {
//        return inStream;
//    }
//
//    public void setInStream(InputStream inStream) {
//        this.inStream = inStream;
//    }

    public Integer getImageType() {
        return imageType;
    }

    public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

	public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

}
