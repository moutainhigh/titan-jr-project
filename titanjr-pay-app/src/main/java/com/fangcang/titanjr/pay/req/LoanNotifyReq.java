package com.fangcang.titanjr.pay.req;

public class LoanNotifyReq {
	// 13位unix时间戳
	private String create_time;
	// Md5(4d7079570ef19ed949717aac81251eb1+ create_time)
	private String token;
	// 两位码（AB） A:2 初审 3终审 4 开通 B: 1 通过 2 续议 0 拒绝
	private String status;
	// 通知的中文信息
	private String msg;
	// Json格式的具体信息
	private String data;

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
