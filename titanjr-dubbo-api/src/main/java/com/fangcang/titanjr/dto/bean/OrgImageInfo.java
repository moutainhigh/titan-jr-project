package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/**
 * Created by zhaoshan on 2016/4/25.
 */
public class OrgImageInfo implements Serializable {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = -192239597738926016L;
	private String imageName;
    private Integer imageType;
    private Integer sizeType;
    private String imageURL;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public Integer getSizeType() {
        return sizeType;
    }

    public void setSizeType(Integer sizeType) {
        this.sizeType = sizeType;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
