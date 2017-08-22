package com.fangcang.titanjr.rest.response;

import com.fangcang.titanjr.response.BaseResponse;
import com.fangcang.titanjr.rest.dto.AccountBalanceDTO;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by zhaoshan on 2017/8/14.
 */
@ApiModel(value = "AccountBalanceResponse", description = "账户余额查询结果")
public class AccountBalanceResponse extends BaseResponse {

    @ApiModelProperty(value = "账户列表详情")
    private List<AccountBalanceDTO> accountBalanceDTOList;

    public List<AccountBalanceDTO> getAccountBalanceDTOList() {
        return accountBalanceDTOList;
    }

    public void setAccountBalanceDTOList(List<AccountBalanceDTO> accountBalanceDTOList) {
        this.accountBalanceDTOList = accountBalanceDTOList;
    }
}
