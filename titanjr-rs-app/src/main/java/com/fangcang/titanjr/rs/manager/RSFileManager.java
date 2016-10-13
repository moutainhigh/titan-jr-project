package com.fangcang.titanjr.rs.manager;

import com.fangcang.titanjr.rs.request.GetFileUrlRequest;
import com.fangcang.titanjr.rs.request.GetUrlKeyRequest;
import com.fangcang.titanjr.rs.response.GetFileUrlResponse;
import com.fangcang.titanjr.rs.response.GetUrlKeyResponse;

/**
 * 融数文件管理
 * @author luoqinglong
 * @2016年8月11日
 */
public interface RSFileManager {
	
	/**
	 * 获取url_key
	 * @param getUrlKeyRequest
	 * @return
	 */
	GetUrlKeyResponse getUrlKey(GetUrlKeyRequest getUrlKeyRequest);
	/***
	 * 获取文件下载路径
	 * @param getFileUrlRequest
	 * @return
	 */
	GetFileUrlResponse getFileUrl(GetFileUrlRequest getFileUrlRequest);
	
}
