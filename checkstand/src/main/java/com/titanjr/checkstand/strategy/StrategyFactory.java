package com.titanjr.checkstand.strategy;

import com.fangcang.util.SpringContextUtil;
import com.titanjr.checkstand.constants.AgentTradeCodeEnum;
import com.titanjr.checkstand.constants.BusiCodeEnum;
import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.strategy.agent.AgentTradeStrategy;
import com.titanjr.checkstand.strategy.pay.PayRequestStrategy;
import com.titanjr.checkstand.strategy.query.QueryStrategy;
import com.titanjr.checkstand.strategy.quickPay.QuickPayStrategy;
import com.titanjr.checkstand.strategy.refund.OrderRefundStrategy;

/**
 * Created by zhaoshan on 2017/11/20.
 */
public class StrategyFactory {

	/**
	 * 支付策略
	 * @author zhaoshan
	 * @date 2017年12月1日 上午10:04:42 
	 */
    public static PayRequestStrategy getPayRequestStrategy(PayTypeEnum payTypeEnum){

        if (PayTypeEnum.PERSON_EBANK.equals(payTypeEnum) || PayTypeEnum.COMP_EBANK.equals(payTypeEnum)
                || PayTypeEnum.CREDIT_EBANK.equals(payTypeEnum)){
            return (PayRequestStrategy)SpringContextUtil.getBean("netBankPayStrategy");
        }

        if (PayTypeEnum.QR_WECHAT_URL.equals(payTypeEnum) || PayTypeEnum.QR_ALIPAY_URL.equals(payTypeEnum) ||
                PayTypeEnum.QR_ALIPAY.equals(payTypeEnum) || PayTypeEnum.QR_WECHAT.equals(payTypeEnum)
                || PayTypeEnum.WECHAT.equals(payTypeEnum)){
            return (PayRequestStrategy)SpringContextUtil.getBean("qrCodePayStrategy");
        }
        
        if (PayTypeEnum.QUICK_NEW.equals(payTypeEnum)){
            return (PayRequestStrategy)SpringContextUtil.getBean("quickPayStrategy");
        }

        return null;
    }
    
    /**
     * 订单（支付结果）查询策略
     * @author Jerry
     * @date 2017年12月1日 上午10:04:23
     */
    public static QueryStrategy getPayQueryStrategy(PayTypeEnum payTypeEnum){

        if (PayTypeEnum.PERSON_EBANK.equals(payTypeEnum) || PayTypeEnum.COMP_EBANK.equals(payTypeEnum)
                || PayTypeEnum.CREDIT_EBANK.equals(payTypeEnum)){
            return (QueryStrategy)SpringContextUtil.getBean("netBankPayQueryStrategy");
        }

        if (PayTypeEnum.QR_WECHAT_URL.equals(payTypeEnum) || PayTypeEnum.QR_ALIPAY_URL.equals(payTypeEnum) ||
                PayTypeEnum.QR_ALIPAY.equals(payTypeEnum) || PayTypeEnum.QR_WECHAT.equals(payTypeEnum) || 
                PayTypeEnum.WECHAT.equals(payTypeEnum)){
            return (QueryStrategy)SpringContextUtil.getBean("qrPayQueryStrategy");
        }
        
        if (PayTypeEnum.QUICK_NEW.equals(payTypeEnum)){
            return (QueryStrategy)SpringContextUtil.getBean("quickPayQueryStrategy");
        }

        return null;
    }
    
    /**
     * 退款策略
     * @author Jerry
     * @date 2017年12月5日 下午6:11:35
     */
    public static OrderRefundStrategy getRefundStrategy(PayTypeEnum payTypeEnum){

        if (PayTypeEnum.PERSON_EBANK.equals(payTypeEnum) || PayTypeEnum.COMP_EBANK.equals(payTypeEnum)
                || PayTypeEnum.CREDIT_EBANK.equals(payTypeEnum)){
            return (OrderRefundStrategy)SpringContextUtil.getBean("netBnakOrderRefundStrategy");
        }

        if (PayTypeEnum.QR_WECHAT_URL.equals(payTypeEnum) || PayTypeEnum.QR_ALIPAY_URL.equals(payTypeEnum) ||
                PayTypeEnum.QR_ALIPAY.equals(payTypeEnum) || PayTypeEnum.QR_WECHAT.equals(payTypeEnum) || 
                PayTypeEnum.WECHAT.equals(payTypeEnum)){
            return (OrderRefundStrategy)SpringContextUtil.getBean("qrOrderRefundStrategy");
        }
        
        if (PayTypeEnum.QUICK_NEW.equals(payTypeEnum)){
            return (OrderRefundStrategy)SpringContextUtil.getBean("quickPayRefundStrategy");
        }

        return null;
    }
    
    /**
     * 退款查询策略
     * @author Jerry
     * @date 2017年12月5日 下午6:11:54
     */
    public static QueryStrategy getRefundQueryStrategy(PayTypeEnum payTypeEnum){

        if (PayTypeEnum.PERSON_EBANK.equals(payTypeEnum) || PayTypeEnum.COMP_EBANK.equals(payTypeEnum)
                || PayTypeEnum.CREDIT_EBANK.equals(payTypeEnum)){
            return (QueryStrategy)SpringContextUtil.getBean("netBankRefundQueryStrategy");
        }

        if (PayTypeEnum.QR_WECHAT_URL.equals(payTypeEnum) || PayTypeEnum.QR_ALIPAY_URL.equals(payTypeEnum) ||
                PayTypeEnum.QR_ALIPAY.equals(payTypeEnum) || PayTypeEnum.QR_WECHAT.equals(payTypeEnum) || 
                PayTypeEnum.WECHAT.equals(payTypeEnum)){
            return (QueryStrategy)SpringContextUtil.getBean("qrOrderRefundQueryStrategy");
        }
        
        if (PayTypeEnum.QUICK_NEW.equals(payTypeEnum)){
            return (QueryStrategy)SpringContextUtil.getBean("quickPayRefundQueryStrategy");
        }

        return null;
    }
    
    /**
     * 账户交易策略
     * @author Jerry
     * @date 2018年1月3日 下午7:41:51
     */
    public static AgentTradeStrategy getAgentTradeStrategy(String tradeCode){

        if (AgentTradeCodeEnum.AGENT_PAY.getCode().equals(tradeCode)){
            return (AgentTradeStrategy)SpringContextUtil.getBean("agentPayStrategy");
        }

        if (AgentTradeCodeEnum.AGENT_QUERY.getCode().equals(tradeCode)){
            return (AgentTradeStrategy)SpringContextUtil.getBean("agentQueryStrategy");
        }
        
        if (AgentTradeCodeEnum.AGENT_DOWNLOAD.getCode().equals(tradeCode)){
        	return (AgentTradeStrategy)SpringContextUtil.getBean("agentDownloadStrategy");
        }

        return null;
    }
    
    /**
     * 快捷支付策略
     * @author Jerry
     * @date 2018年1月3日 下午8:15:43
     */
    public static QuickPayStrategy getQuickpayStrategy(BusiCodeEnum busiCodeEnum){
    	
    	if (busiCodeEnum == BusiCodeEnum.CARD_BIN_QUERY){
        	return (QuickPayStrategy)SpringContextUtil.getBean("cardBINQueryStrategy");
        }
        
        if(busiCodeEnum == BusiCodeEnum.QUICK_PAY_CONFIRM){
        	return (QuickPayStrategy)SpringContextUtil.getBean("quickPayConfirmStrategy");
        }
        
        if(busiCodeEnum == BusiCodeEnum.RE_SEND_MSG){
        	return (QuickPayStrategy)SpringContextUtil.getBean("reSendMsgStrategy");
        }
        
        if(busiCodeEnum == BusiCodeEnum.CARD_AUTH){
        	return (QuickPayStrategy)SpringContextUtil.getBean("cardAuthStrategy");
        }
        
        if(busiCodeEnum == BusiCodeEnum.CARD_BIND_QUERY){
        	return (QuickPayStrategy)SpringContextUtil.getBean("bindCardListQueryStrategy");
        }
        
        if(busiCodeEnum == BusiCodeEnum.CARD_UNBIND){
        	return (QuickPayStrategy)SpringContextUtil.getBean("unBindCardStrategy");
        }

        return null;
    }

}
