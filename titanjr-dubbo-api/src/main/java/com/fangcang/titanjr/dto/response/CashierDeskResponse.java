package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.CashierDeskDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaoshan on 2016/5/13.
 */
public class CashierDeskResponse extends BaseResponseDTO implements Serializable{

    private List<CashierDeskDTO> cashierDeskDTOList;

    public List<CashierDeskDTO> getCashierDeskDTOList() {
        return cashierDeskDTOList;
    }

    public void setCashierDeskDTOList(List<CashierDeskDTO> cashierDeskDTOList) {
        this.cashierDeskDTOList = cashierDeskDTOList;
    }
}
