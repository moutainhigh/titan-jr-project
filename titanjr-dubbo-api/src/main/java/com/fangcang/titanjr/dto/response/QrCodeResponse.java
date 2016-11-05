package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.QrCodeDTO;

/**
 * 扫码支付调用网关结果
 * @author Administrator
 *
 */
public class QrCodeResponse extends BaseResponseDTO {

	private QrCodeDTO qrCodeDTO;

	public QrCodeDTO getQrCodeDTO() {
		return qrCodeDTO;
	}

	public void setQrCodeDTO(QrCodeDTO qrCodeDTO) {
		this.qrCodeDTO = qrCodeDTO;
	}
	
}
