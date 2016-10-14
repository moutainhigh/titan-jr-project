package com.fangcang.titanjr.rs.response;

import java.util.List;

import com.Rop.api.domain.FileUrl;


public class GetUrlKeyResponse extends BaseResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1768478982157038511L;
	/**
	 * 文件存储信息
	 */
	List<FileUrl> fileUrlList;
	public List<FileUrl> getFileUrlList() {
		return fileUrlList;
	}
	public void setFileUrlList(List<FileUrl> fileUrlList) {
		this.fileUrlList = fileUrlList;
	}
	
	
}
