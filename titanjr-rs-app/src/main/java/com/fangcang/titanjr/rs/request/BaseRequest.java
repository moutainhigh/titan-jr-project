package com.fangcang.titanjr.rs.request;


import com.fangcang.titanjr.common.exception.RSValidateException;

import java.io.Serializable;

/**
 * Created by zhaoshan on 2016/4/8.
 */
public  abstract class BaseRequest implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4138055894487614737L;
	//==========大部分接口均用到的字段======
    // 接入机构中设置的用户ID  不能重复
    private String userid;
    //机构码
    private String constid;
    // 产品号
    private String productid;

    /**
     * 检查参数格式正确性
     * @throws com.fangcang.titanjr.common.exception.RSValidateException
     */
    public abstract void check() throws RSValidateException;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getConstid() {
        return constid;
    }

    public void setConstid(String constid) {
        this.constid = constid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }
}
