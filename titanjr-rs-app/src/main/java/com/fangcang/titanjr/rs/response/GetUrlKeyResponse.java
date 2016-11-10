package com.fangcang.titanjr.rs.response;

import java.util.List;

import com.fangcang.titanjr.rs.dto.TFileUrl;
/**
 * 获取url_key
 * @author luoqinglong
 * @2016年11月10日
 */

public class GetUrlKeyResponse extends BaseResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1768478982157038511L;
	/**
	 * 文件存储信息
	 */
	List<TFileUrl> tFileUrlList;
	
	public List<TFileUrl> gettFileUrlList() {
		return tFileUrlList;
	}
	public void settFileUrlList(List<TFileUrl> tFileUrlList) {
		this.tFileUrlList = tFileUrlList;
	}
	
	
}
