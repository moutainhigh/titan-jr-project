package test.fangcang.titanjr.facade;

import com.fangcang.titanjr.dto.OrganTypeEnum;
import com.fangcang.titanjr.facade.TitanFinancialOrganFacade;
import com.fangcang.titanjr.facade.TitanFinancialTradeFacade;
import com.fangcang.titanjr.request.BalanceQueryRequest;
import com.fangcang.titanjr.request.OrganInfoQueryRequest;
import com.fangcang.titanjr.request.OrganStatusRequest;
import com.fangcang.titanjr.response.BalanceQueryResponse;
import com.fangcang.titanjr.response.OrganInfoResponse;
import com.fangcang.titanjr.response.OrganStatusResponse;
import com.fangcang.titanjr.response.OrganUserInfoResponse;
import org.junit.Test;

import javax.annotation.Resource;

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

}
