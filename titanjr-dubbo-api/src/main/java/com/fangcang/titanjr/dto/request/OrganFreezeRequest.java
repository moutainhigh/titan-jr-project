package com.fangcang.titanjr.dto.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * Created by zhaoshan on 2016/4/25.
 */
public class OrganFreezeRequest extends BaseRequestDTO{
	public static final int OPERATETYPE_UNFREEZE_1 = 1;//解冻,生效
	public static final int OPERATETYPE_FREEZE_2 = 2;//冻结
    //调用方传进来，写在session中
	@NotNull
    private String userid;
     
    @NotNull
    private Integer usertype;
    //TODO 多套账户体系这个位置可能不能这样处理
    private String productid;
    //操作类型 1.解冻，2.冻结
    @NotNull
    private Integer operateType;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
}
