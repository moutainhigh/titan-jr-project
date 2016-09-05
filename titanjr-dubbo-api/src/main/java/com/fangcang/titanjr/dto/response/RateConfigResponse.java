package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.TitanRateDto;

/**
 * 
 * @ClassName: RateConfigResponse 
 * @Description: 查询费率信息相应类
 * @author: wengxitao
 * @date: 2016年8月15日 上午10:19:11
 */
/**
 * 
 * @ClassName: RateConfigResponse
 * @Description: 查询费率信息相应类
 * @author: wengxitao
 * @date: 2016年8月15日 上午10:19:11
 */
public class RateConfigResponse extends BaseResponseDTO {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private List<TitanRateDto> rateInfoList = null;

	public List<TitanRateDto> getRateInfoList() {
		return rateInfoList;
	}

	public void setRateInfoList(List<TitanRateDto> rateInfoList) {
		this.rateInfoList = rateInfoList;
	}
}
