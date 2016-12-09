package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

/***
 * 授信结果通知
 * @author luoqinglong
 * @date   2016年12月8日
 */
public class NotifyRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1316129673393022343L;
	private String status;
	// 通知的中文信息
	private String msg;
}
