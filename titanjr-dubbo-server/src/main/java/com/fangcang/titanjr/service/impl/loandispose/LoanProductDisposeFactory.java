package com.fangcang.titanjr.service.impl.loandispose;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fangcang.titanjr.common.enums.LoanProductEnum;

/**
 * 贷款逻辑工厂类
 * 
 * @ClassName: LoanProductDisposeFactory
 * @Description: TODO
 * @author: Administrator
 * @date: 2017年3月14日 下午3:13:46
 */
@Component
public final class LoanProductDisposeFactory {

	@Resource
	private LoanRoomPackDispose loanRoomPackDispose;

	@Resource
	private LoanOperactionDispose loanOperactionDispose;

	public final LoanProductDisposeAbstrator getDisposeAbstrator(
			LoanProductEnum productEnum) {
		if (productEnum != null) {
			if (productEnum.getCode() == LoanProductEnum.ROOM_PACK.getCode()) {
				return loanRoomPackDispose;
			} else if (productEnum.getCode() == LoanProductEnum.OPERACTION
					.getCode()) {
				return loanOperactionDispose;
			}
		}
		return null;
	}
}
