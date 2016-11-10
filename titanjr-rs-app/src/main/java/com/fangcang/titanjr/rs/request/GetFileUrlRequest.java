package com.fangcang.titanjr.rs.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/**
 * 获取文件下载路径
 * @author luoqinglong
 * @2016年11月10日
 */
public class GetFileUrlRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2488537854375135263L;
	/**
	 * 文件key
	 */
	@NotNull
	private String urlKey;
	
	public String getUrlKey() {
		return urlKey;
	}

	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}

	public void check() throws RSValidateException {
		//校验不能为空
		RequestValidationUtil.check(this);
		
	}
	
}
