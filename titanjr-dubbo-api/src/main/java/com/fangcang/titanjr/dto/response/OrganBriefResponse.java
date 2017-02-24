package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;

import java.util.List;

/**
 * Created by zhaoshan on 2016/6/17.
 */
public class OrganBriefResponse extends BaseResponseDTO {
    private List<FinancialOrganDTO> organDTOList;

    public List<FinancialOrganDTO> getOrganDTOList() {
        return organDTOList;
    }

    public void setOrganDTOList(List<FinancialOrganDTO> organDTOList) {
        this.organDTOList = organDTOList;
    }
}
