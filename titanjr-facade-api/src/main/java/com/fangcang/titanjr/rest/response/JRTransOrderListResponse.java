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


    @ApiModelProperty(value = "总记录数量",required = true)
    private long totalCount;
    @ApiModelProperty(value = "总页数",required = true)
    private int totalPage;
    @ApiModelProperty(value = "当前页编号",required = true)
    private int currentPage;

    public List<JRTransOrderDTO> getJrTransOrderDTOList() {
        return jrTransOrderDTOList;
    }

    public void setJrTransOrderDTOList(List<JRTransOrderDTO> jrTransOrderDTOList) {
        this.jrTransOrderDTOList = jrTransOrderDTOList;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
