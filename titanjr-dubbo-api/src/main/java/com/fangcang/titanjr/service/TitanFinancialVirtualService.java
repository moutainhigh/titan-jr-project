package com.fangcang.titanjr.service;

import com.fangcang.exception.ServiceException;
import com.fangcang.titanjr.dto.request.BindingVirtuaOrgBankCardRequest;
import com.fangcang.titanjr.dto.request.CreateVirtualOrgRequest;
import com.fangcang.titanjr.dto.request.GetVirtuaOrgBindCarListRequest;
import com.fangcang.titanjr.dto.response.BindingVirtuaOrgBankCardResponse;
import com.fangcang.titanjr.dto.response.CreateVirtualOrgResponse;
import com.fangcang.titanjr.dto.response.GetVirtuaOrgBindCarListResponse;

/**
 * 虚拟组织操作服务接口定义
 * 
 * @ClassName: TitanFinancialVirtualService
 * @author: wengxitao
 */
public interface TitanFinancialVirtualService {

	/**
	 * 创建虚拟账户
	 * @Title: createVirtualOrg 
	 * @Description: TODO
	 * @param createVirtualOrgRequest
	 * @return
	 * @throws ServiceException
	 * @return: CreateVirtualOrgResponse
	 */
	public CreateVirtualOrgResponse createVirtualOrg(
			CreateVirtualOrgRequest createVirtualOrgRequest)
			throws ServiceException;
	/**
	 * 绑定银行卡到指定的虚拟账户
	 * @Title: bindingBankCardToVirtuaOrg 
	 * @Description: TODO
	 * @param request
	 * @return
	 * @throws ServiceException
	 * @return: BindingVirtuaOrgBankCardResponse
	 */
	public BindingVirtuaOrgBankCardResponse bindingBankCardToVirtuaOrg(
			BindingVirtuaOrgBankCardRequest request) throws ServiceException;
	/**
	 * 根据真实的机构或者对应的虚拟机构绑卡信息
	 * @Title: getVirtuaOrgBindingBankCardList 
	 * @Description: TODO
	 * @param req
	 * @return
	 * @return: GetVirtuaOrgBindCarListResponse
	 */
	public GetVirtuaOrgBindCarListResponse getVirtuaOrgBindingBankCardList(GetVirtuaOrgBindCarListRequest req);
}
