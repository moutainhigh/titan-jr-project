package com.fangcang.titanjr.dto.request;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaoshan on 2017/8/25.
 * 主要用于修改收银台的开关情况
 */
public class CashierDeskUpdateRequest implements Serializable{

    //收银台开关
    private Integer isOpen;
    //收银台名称
    private String deskName;
    //付款类型，一般不用
    private Integer payType;

    //userId
    private String userId;

    //收银台id
    private Long deskId;

    //收银台用途列表，userFor的列表
    private List<Integer> usedList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getDeskId() {
        return deskId;
    }

    public void setDeskId(Long deskId) {
        this.deskId = deskId;
    }

    public List<Integer> getUsedList() {
        return usedList;
    }

    public void setUsedList(List<Integer> usedList) {
        this.usedList = usedList;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public String getDeskName() {
        return deskName;
    }

    public void setDeskName(String deskName) {
        this.deskName = deskName;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
