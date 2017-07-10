package test.fangcang.titanjr.facade;

import javax.annotation.Resource;

import org.junit.Test;

import com.fangcang.titanjr.dto.OrganTypeEnum;
import com.fangcang.titanjr.facade.TitanFinancialOrganFacade;
import com.fangcang.titanjr.facade.TitanFinancialTradeFacade;
import com.fangcang.titanjr.request.BalanceQueryRequest;
import com.fangcang.titanjr.request.OrganInfoQueryRequest;
import com.fangcang.titanjr.request.OrganStatusRequest;
import com.fangcang.titanjr.request.UpdateFreezeRequest;
import com.fangcang.titanjr.response.BalanceQueryResponse;
import com.fangcang.titanjr.response.BaseResponse;
import com.fangcang.titanjr.response.OrganInfoResponse;
import com.fangcang.titanjr.response.OrganStatusResponse;
import com.fangcang.titanjr.response.OrganUserInfoResponse;

/**
 * Created by zhaoshan on 2016/11/16.
 */
public class WalletInterfaceTest extends SpringTest {

    @Resource
    TitanFinancialOrganFacade titanFinancialOrganFacade;

    @Resource
    TitanFinancialTradeFacade titanFinanceTradeFacade;

    @Test
    public void testQueryOrganInfoByPartnerUser() {
        OrganInfoQueryRequest organInfoQueryRequest = new OrganInfoQueryRequest();
        organInfoQueryRequest.setOrganTypeEnum(OrganTypeEnum.SaaS);
        organInfoQueryRequest.setPartnerCode("M10000001");
        organInfoQueryRequest.setPartnerLoginName("fangcang");
        organInfoQueryRequest.setPartnerUserId("23415");
        OrganInfoResponse organInfoResponse = titanFinancialOrganFacade.queryOrganInfoByPartnerUser(organInfoQueryRequest);
        System.out.println(organInfoResponse.getTitanCode());
    }


    @Test
    public void testOrganUserByPartnerUser() {
        OrganInfoQueryRequest organInfoQueryRequest = new OrganInfoQueryRequest();
        organInfoQueryRequest.setOrganTypeEnum(OrganTypeEnum.SaaS);
        organInfoQueryRequest.setPartnerCode("M10000001");
        organInfoQueryRequest.setPartnerLoginName("fangcang");
        organInfoQueryRequest.setPartnerUserId("23415");
        OrganUserInfoResponse userInfoResponse = titanFinancialOrganFacade.queryOrganUserByPartnerUser(organInfoQueryRequest);
        System.out.println(userInfoResponse.getTitanUserId());
    }

    @Test
    public void testOrganStatus() {
        OrganStatusRequest organStatusRequest = new OrganStatusRequest();
        organStatusRequest.setOrganTypeEnum(OrganTypeEnum.SaaS);
        organStatusRequest.setPartnerCode("M10000001");
        organStatusRequest.setTitanOrgCode("TJM10000087");
        OrganStatusResponse organStatusResponse = titanFinancialOrganFacade.queryOrganStatus(organStatusRequest);
        System.out.println(organStatusResponse);
    }

    @Test
    public void testAccountBalance() {
        BalanceQueryRequest accountBalanceRequest = new BalanceQueryRequest();
        accountBalanceRequest.setTitanOrgCode("TJM10000087");
        BalanceQueryResponse organStatusResponse = titanFinanceTradeFacade.queryAccountBalance(accountBalanceRequest);
        System.out.println(organStatusResponse);
    }
    
    @Test
    public void testuNFreezeByOrderPayNo(){
    	UpdateFreezeRequest updateFreezeRequest = new UpdateFreezeRequest();
    	updateFreezeRequest.setOperationType("1");
    	updateFreezeRequest.setPayOrderNo("201711141238596003064");
    	updateFreezeRequest.setuNFreezeDate("2017-12-03");
    	BaseResponse baseResponse = titanFinanceTradeFacade.UpdateFreezeOrder(updateFreezeRequest);
    	System.out.println(baseResponse.isResult() + "-----" + baseResponse.getReturnMessage());
    }

}
