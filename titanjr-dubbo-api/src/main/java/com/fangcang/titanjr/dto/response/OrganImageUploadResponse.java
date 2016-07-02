package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

/**
 * Created by zhaoshan on 2016/4/21.
 */
public class OrganImageUploadResponse extends BaseResponseDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4547841091456253050L;
	/**
	 * id 数组，格式：123,569,877
	 */
	private String imageId;
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	
	
	
}
