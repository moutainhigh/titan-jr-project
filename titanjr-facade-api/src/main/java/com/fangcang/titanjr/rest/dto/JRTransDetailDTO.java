package com.fangcang.titanjr.rest.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by zhaoshan on 2017/8/10.
 */
@ApiModel(value = "JRTransDetailDTO", description = "交易单详情")
public class JRTransDetailDTO extends JRTransOrderDTO{

    @ApiModelProperty(value = "充值单信息，可能没有")
    private JRTitanOrderPayDTO jrTitanOrderPayDTO;
    @ApiModelProperty(value = "转账单信息，可能没有")
    private JRTitanTransferDTO jrTitanTransferDTO;
    @ApiModelProperty(value = "提现单信息，可能没有")
    private JRTitanWithDrawDTO jrTitanWithDrawDTO;
    @ApiModelProperty(value = "退款单信息，可能没有")
    private JRRefundDTO jrRefundDTO;
    public JRTitanOrderPayDTO getJrTitanOrderPayDTO() {
        return jrTitanOrderPayDTO;
    }

    public void setJrTitanOrderPayDTO(JRTitanOrderPayDTO jrTitanOrderPayDTO) {
        this.jrTitanOrderPayDTO = jrTitanOrderPayDTO;
    }

    public JRTitanTransferDTO getJrTitanTransferDTO() {
        return jrTitanTransferDTO;
    }

    public void setJrTitanTransferDTO(JRTitanTransferDTO jrTitanTransferDTO) {
        this.jrTitanTransferDTO = jrTitanTransferDTO;
    }

    public JRTitanWithDrawDTO getJrTitanWithDrawDTO() {
        return jrTitanWithDrawDTO;
    }

    public void setJrTitanWithDrawDTO(JRTitanWithDrawDTO jrTitanWithDrawDTO) {
        this.jrTitanWithDrawDTO = jrTitanWithDrawDTO;
    }

    public JRRefundDTO getJrRefundDTO() {
        return jrRefundDTO;
    }

    public void setJrRefundDTO(JRRefundDTO jrRefundDTO) {
        this.jrRefundDTO = jrRefundDTO;
    }
}
