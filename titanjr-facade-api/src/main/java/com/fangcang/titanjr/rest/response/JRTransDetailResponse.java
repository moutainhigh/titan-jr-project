package com.fangcang.titanjr.rest.response;

import com.fangcang.titanjr.response.BaseResponse;
import com.fangcang.titanjr.rest.dto.JRTransDetailDTO;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by zhaoshan on 2017/8/10.
 */
@ApiModel(value = "JRTransOrderResponse", description = "交易记录查询结果")
public class JRTransDetailResponse extends BaseResponse{

    @ApiModelProperty(value = "交易单详情",required = true)
    private List<JRTransDetailDTO> jrTransDetailDTOList;

    public List<JRTransDetailDTO> getJrTransDetailDTOList() {
        return jrTransDetailDTOList;
    }

    public void setJrTransDetailDTOList(List<JRTransDetailDTO> jrTransDetailDTOList) {
        this.jrTransDetailDTOList = jrTransDetailDTOList;
    }
}
