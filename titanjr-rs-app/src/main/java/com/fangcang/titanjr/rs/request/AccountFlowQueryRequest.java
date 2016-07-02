package com.fangcang.titanjr.rs.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/**
 * Created by zhaoshan on 2016/4/15.
 */
public class AccountFlowQueryRequest  extends BaseRequest{

	//记录创建开始时间 
	private Date createdtimefrom;
	
	//记录创建结束时间 
	private Date createdtimeto;
	
	//交易记录编码（查询类型为2时，必须入参） 
	private String requestid ;
	
	//查询类型（1:根据账户查询 2:根据交易记录查询） 
	private String querytype ;
	
	//管理机构代码（查询类型为1时，必须入参）
	private String rootinstcd;
	
	
	@Override
	public void check() throws RSValidateException {
		//校验不能为空
        RequestValidationUtil.checkNotEmpty(this.getRootinstcd(), "rootinstcd");
        RequestValidationUtil.checkNotEmpty(this.getUserid(), "userid");
        RequestValidationUtil.checkNotEmpty(this.getProductid(), "productid");
	}

	public Date getCreatedtimefrom() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createdDate = format.format(createdtimefrom);
		try {
			createdtimefrom = format.parse(createdDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createdtimefrom;
	}

	public void setCreatedtimefrom(Date createdtimefrom) {
		this.createdtimefrom = createdtimefrom;
	}

	public Date getCreatedtimeto() {
		
		return createdtimeto;
	}

	public void setCreatedtimeto(Date createdtimeto) {
		this.createdtimeto = createdtimeto;
	}

	public String getRequestid() {
		return requestid;
	}

	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}

	public String getQuerytype() {
		return querytype;
	}

	public void setQuerytype(String querytype) {
		this.querytype = querytype;
	}

	public String getRootinstcd() {
		return rootinstcd;
	}

	public void setRootinstcd(String rootinstcd) {
		this.rootinstcd = rootinstcd;
	}
	
	
	
}
