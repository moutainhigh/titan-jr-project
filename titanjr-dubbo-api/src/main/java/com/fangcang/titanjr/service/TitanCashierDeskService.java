package com.fangcang.titanjr.service;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.CashierItemBankDTO;
import com.fangcang.titanjr.dto.bean.CommonPayMethodDTO;
import com.fangcang.titanjr.dto.bean.gateway.CommonPayHistoryDTO;
import com.fangcang.titanjr.dto.request.CashierDeskInitRequest;
import com.fangcang.titanjr.dto.request.CashierDeskQueryRequest;
import com.fangcang.titanjr.dto.request.CashierDeskUpdateRequest;
import com.fangcang.titanjr.dto.request.PaymentItemRequest;
import com.fangcang.titanjr.dto.response.CashierDeskInitResponse;
import com.fangcang.titanjr.dto.response.CashierDeskResponse;

/**
 * 金服平台收银台相关服务
 * 包括查询收银台获取匹配列表
 * 收银台展示逻辑处理（判定校验）
 * 费率配置等等
 * Created by zhaoshan on 2016/4/21.
 */
public interface TitanCashierDeskService {
    /**
     * 查询得到收银台
     * 包括收银台包含的子项，子项对应的银行卡，每个子项的费率配置
     * @param cashierDeskQueryRequest
     * @return
     */
    public CashierDeskResponse queryCashierDesk(CashierDeskQueryRequest cashierDeskQueryRequest);

    /**
     * 收银台初始化接口
     * @param cashierDeskInitRequest
     * @return
     */
    public CashierDeskInitResponse initCashierDesk(CashierDeskInitRequest cashierDeskInitRequest) throws Exception;


	public boolean updateCashierDesk(CashierDeskUpdateRequest deskUpdateRequest) throws Exception;

   /**
    * 根据银行标识查询银行信息
    * @param bankName
    * @return
    */
    public CashierItemBankDTO queryCashierItemBankDTOByBankName(String bankName);

    /**
     * 常用的支付方式
     * @param commonPayMethodDTO
     * @return
     */
    public boolean saveCommonPayMethod(CommonPayMethodDTO commonPayMethodDTO);
    
    /**
     * 获取常用的收银台信息
     * @param cashierDeskQueryRequest
     * @return
     */
    public List<CommonPayMethodDTO> queryCommonPayMethod(CashierDeskQueryRequest cashierDeskQueryRequest);
    
    /**
     * 保存常用支付历史记录（新版收银台）
     * @author Jerry
     * @date 2017年8月8日 上午9:50:56
     * @param commonPayHistoryDTO
     * @return
     */
    public BaseResponseDTO saveCommonPayHistory(CommonPayHistoryDTO commonPayHistoryDTO);
    
    /**
     * 查询常用支付历史列表（新版收银台）
     * @author Jerry
     * @date 2017年8月8日 下午5:22:58
     */
    public List<CommonPayHistoryDTO> queryCommonPayHistory(CommonPayHistoryDTO commonPayHistoryDTO);
    
    /**
     * 查询单个实体
     * @author Jerry
     * @date 2018年1月30日 上午11:17:26
     */
    public CommonPayHistoryDTO getCommonPayHistory(CommonPayHistoryDTO commonPayHistoryDTO);
    
    /**
     * 删除本地绑卡信息
     * @author Jerry
     * @date 2018年1月30日 上午11:39:38
     */
    public int delCommonPayHistory(CommonPayHistoryDTO commonPayHistoryDTO);
    

    public void initttMallCashDesk();
    
    /**
	 * 添加一个支付item
	 * @param request
	 */
	public <T> void addModelOfPayment(PaymentItemRequest<T> request);
	
	
	
	/**
	 * 暂时增加对贷款收银台的初始化方法
	 * @Title: executeLoanDeskInit 
	 * @Description: TODO
	 * @throws ServiceException
	 * @return: void
	 */
	public void executeLoanDeskInit() throws Exception;
	
	/**
	 * 对已经注册的机构增加微信公众号收银台
	 * @throws Exception
	 */
	//public void executeWxPublicInit() throws Exception;
	
	/**
	 * 初始化交易平台收银台
	 * @author Jerry
	 * @date 2017年10月26日 上午11:48:43
	 * @return
	 */
	public BaseResponseDTO initTradingPlatformCashierdesk();
	
}