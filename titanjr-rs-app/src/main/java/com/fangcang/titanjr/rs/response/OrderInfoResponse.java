package com.fangcang.titanjr.rs.response;

import com.fangcang.titanjr.rs.response.Retbeanlist;
import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * 封装融数返回的xml信息
 * @author Administrator
 *
 */
@XStreamAlias("wheatfield_order_oper_response")
public class OrderInfoResponse {

	private String is_success;

	private String msg;

	@XStreamAlias("retbeanlist")
	private Retbeanlist retbeanlist;

	public String getIs_success() {
		return is_success;
	}

	public void setIs_success(String is_success) {
		this.is_success = is_success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Retbeanlist getRetbeanlist() {
		return retbeanlist;
	}

	public void setRetbeanlist(Retbeanlist retbeanlist) {
		this.retbeanlist = retbeanlist;
	}

}
