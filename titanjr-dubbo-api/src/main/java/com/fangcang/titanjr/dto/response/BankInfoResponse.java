package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.BankInfoDTO;

import java.util.List;

/**
 * Created by zhaoshan on 2016/6/14.
 */
public class BankInfoResponse extends BaseResponseDTO{

    private List<BankInfoDTO> bankInfoDTOList;

    public List<BankInfoDTO> getBankInfoDTOList() {
        return bankInfoDTOList;
    }

    public void setBankInfoDTOList(List<BankInfoDTO> bankInfoDTOList) {
        this.bankInfoDTOList = bankInfoDTOList;
    }
}
