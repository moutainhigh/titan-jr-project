package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.CheckStatus;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.bean.OrgImageInfo;

import java.util.List;

/**
 * 机构查询返回结果
 * Created by zhaoshan on 2016/3/30.
 */
public class FinancialOrganResponse extends BaseResponseDTO{

    private FinancialOrganDTO financialOrganDTO;

    public FinancialOrganDTO getFinancialOrganDTO() {
        return financialOrganDTO;
    }

    public void setFinancialOrganDTO(FinancialOrganDTO financialOrganDTO) {
        this.financialOrganDTO = financialOrganDTO;
    }
}
