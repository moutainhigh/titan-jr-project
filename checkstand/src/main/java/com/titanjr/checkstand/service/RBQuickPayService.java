package com.titanjr.checkstand.service;

import java.util.Map;

import com.titanjr.checkstand.request.RBBindCardQueryRequest;
import com.titanjr.checkstand.request.RBCardAuthRequest;
import com.titanjr.checkstand.request.RBCardBINQueryRequest;
import com.titanjr.checkstand.request.RBQuickPayConfirmRequest;
import com.titanjr.checkstand.request.RBQuickPayQueryRequest;
import com.titanjr.checkstand.request.RBQuickPayRefundQueryRequest;
import com.titanjr.checkstand.request.RBQuickPayRefundRequest;
import com.titanjr.checkstand.request.RBQuickPayRequest;
import com.titanjr.checkstand.request.RBReSendMsgRequest;
import com.titanjr.checkstand.request.RBUnBindCardRequest;
import com.titanjr.checkstand.respnse.TitanBindCardQueryResponse;
import com.titanjr.checkstand.respnse.TitanCardBINQueryResponse;
import com.titanjr.checkstand.respnse.TitanOrderRefundResponse;
import com.titanjr.checkstand.respnse.TitanPayConfirmResponse;
import com.titanjr.checkstand.respnse.TitanPayQueryResponse;
import com.titanjr.checkstand.respnse.TitanQuickPayResponse;
import com.titanjr.checkstand.respnse.TitanReSendMsgResponse;
import com.titanjr.checkstand.respnse.TitanRefundQueryResponse;
import com.titanjr.checkstand.respnse.TitanUnBindCardResponse;

/**
 * 融宝支付接口
 * @author Jerry
 * @date 2017年12月18日 下午2:54:59
 */
public interface RBQuickPayService {
	
	/**
	 * 卡BIN查询
	 * @author Jerry
	 * @date 2018年1月4日 下午5:03:04
	 */
	public TitanCardBINQueryResponse cardBINQuery(RBCardBINQueryRequest rbCardBINQueryRequest);
	
	/**
	 * 签约支付
	 * @author Jerry
	 * @date 2018年1月3日 下午2:36:37
	 */
	public TitanQuickPayResponse contractPay(RBQuickPayRequest rbQuickPayRequest);
	
	/**
	 * 确认支付
	 * @author Jerry
	 * @date 2018年1月5日 下午2:18:55
	 */
	public TitanPayConfirmResponse payConfirm(RBQuickPayConfirmRequest rbQuickPayConfirmRequest);
	
	/**
	 * 重发验证码
	 * @author Jerry
	 * @date 2018年1月5日 下午3:30:37
	 */
	public TitanReSendMsgResponse reSendMsg(RBReSendMsgRequest rbReSendMsgRequest);
	
	/**
	 * 支付查询
	 * @author Jerry
	 * @date 2018年1月5日 下午4:20:53
	 */
	public TitanPayQueryResponse payQuery(RBQuickPayQueryRequest rbQuickPayQueryRequest);
	
	/**
	 * 退款
	 * @author Jerry
	 * @date 2018年1月5日 下午6:43:12
	 */
	public TitanOrderRefundResponse refund(RBQuickPayRefundRequest rbQuickPayRefundRequest);
	
	/**
	 * 退款查询
	 * @author Jerry
	 * @date 2018年1月8日 下午2:32:44
	 */
	public TitanRefundQueryResponse refundQuery(RBQuickPayRefundQueryRequest rbQuickPayRefundQueryRequest);
	
	/**
	 * 卡密鉴权<br>
	 * gateWayUrl--融宝卡密鉴权网关地址<br>
	 * rbDataRequest--融宝卡密鉴权请求参数
	 * @author Jerry
	 * @date 2018年1月8日 下午5:06:26
	 */
	public Map<String, Object> cardAuth(RBCardAuthRequest rbCardAuthRequest);
	
	/**
	 * 绑卡列表查询
	 * @author Jerry
	 * @date 2018年1月8日 下午6:28:25
	 */
	public TitanBindCardQueryResponse bindCardList(RBBindCardQueryRequest rbBindCardQueryRequest);
	
	/**
	 * 解绑卡
	 * @author Jerry
	 * @date 2018年1月9日 上午11:58:51
	 */
	public TitanUnBindCardResponse unBindCard(RBUnBindCardRequest rbUnBindCardRequest);

}
