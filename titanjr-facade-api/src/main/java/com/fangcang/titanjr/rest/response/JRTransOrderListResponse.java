package com.fangcang.titanjr.rest.response;

import com.fangcang.titanjr.response.BaseResponse;
import com.fangcang.titanjr.rest.dto.JRTransOrderDTO;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by zhaoshan on 2017/8/10.
 */
@ApiModel(value = "JRTransOrderListResponse", description = "交易记录详情")
public class JRTransOrderListResponse extends BaseResponse {

    @ApiModelProperty(value = "交易单列表",required = true)
    private List<JRTransOrderDTO> jrTransOrderDTOList;

    public List<JRTransOrderDTO> getJrTransOrderDTOList() {
        return jrTransOrderDTOList;
    }

    public void setJrTransOrderDTOList(List<JRTransOrderDTO> jrTransOrderDTOList) {
        this.jrTransOrderDTOList = jrTransOrderDTOList;
    }
}
