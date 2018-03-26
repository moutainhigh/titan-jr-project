/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AgentDownloadStrategy.java
 * @author Jerry
 * @date 2017年12月28日 下午6:52:22  
 */
package com.titanjr.checkstand.strategy.download;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.TradeCodeEnum;

/**
 * 对账文件下载
 * @author Jerry
 * @date 2018年3月15日 下午4:59:32
 */
@Service("accountDownloadStrategy")
public class AccountDownloadStrategy implements DownloadStrategy {

	@Override
	public String redirectResult(HttpServletRequest request) {
		
		String tradeCode = request.getParameter("tradeCode");
		
		if(!StringUtil.isValidString(tradeCode)){
			return null;
		}
		if (TradeCodeEnum.TL_GATEWAY_DOWNLOAD.getCode().equals(tradeCode)){//通联网关支付对账文件下载
			return "/download/tlGatewayPayDownload.shtml";
		}
		if (TradeCodeEnum.TL_QRCODE_DOWNLOAD.getCode().equals(tradeCode)){//通联扫码支付对账文件下载
			return "/download/tlQrcodeDownload.shtml";
		}
		if (TradeCodeEnum.TL_AGENT_DOWNLOAD.getCode().equals(tradeCode)){//通联账户交易对账文件下载
			return "/download/tlAgentDownload.shtml";
        }
		if (TradeCodeEnum.RB_AGENT_DOWNLOAD.getCode().equals(tradeCode)){//融宝对账文件下载
			return "/download/rbAccountDownload.shtml";
        }
		
		return null;
	}

}
