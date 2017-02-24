package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaoshan on 2016/5/18.
 */
public class CashierDeskDTO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer deskId;
    private String constId;
    private String userId;
    private String deskName;
    private Integer usedFor;
    private Integer payType;

    private List<CashierDeskItemDTO> cashierDeskItemDTOList;


    public Integer getDeskId() {
        return deskId;
    }

    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    public String getConstId() {
        return constId;
    }

    public void setConstId(String constId) {
        this.constId = constId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeskName() {
        return deskName;
    }

    public void setDeskName(String deskName) {
        this.deskName = deskName;
    }

    public Integer getUsedFor() {
        return usedFor;
    }

    public void setUsedFor(Integer usedFor) {
        this.usedFor = usedFor;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public List<CashierDeskItemDTO> getCashierDeskItemDTOList() {
        return cashierDeskItemDTOList;
    }

    public void setCashierDeskItemDTOList(List<CashierDeskItemDTO> cashierDeskItemDTOList) {
        this.cashierDeskItemDTOList = cashierDeskItemDTOList;
    }

}
