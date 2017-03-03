package test.fangcang.titanjr.service;

import com.fangcang.merchant.api.MerchantFacade;
import com.fangcang.merchant.query.dto.MerchantDetailQueryDTO;
import com.fangcang.merchant.response.dto.MerchantResponseDTO;
import com.fangcang.order.api.HotelOrderSearchFacade;
import com.fangcang.order.dto.OrderDetailResponseDTO;
import com.fangcang.titanjr.common.factory.HessianProxyBeanFactory;
import com.fangcang.titanjr.common.factory.ProxyFactoryConstants;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.dto.request.FinancialUserBindRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.fangcang.titanjr.SpringTest;

import java.util.List;

/**
 * Created by zhaoshan on 2016/5/11.
 */
public class HessianRemoteTest extends SpringTest {

    @Autowired
    HessianProxyBeanFactory hessianProxyBeanFactory ;

//    @Test
    public void queryFinanceSerachTest(){
//        FinanceSearchRemote financeSearchRemote = hessianProxyBeanFactory.getHessianProxyBean(
//                FinanceSearchRemote.class,
//                ProxyFactoryConstants.financeSearchRemoteUrl + "financeSearchRemote");
//        FinanceOrderQuery financeOrderQuery = new FinanceOrderQuery();
//        financeOrderQuery.setMerchantCode("M10000001");//商家编号
//        financeOrderQuery.setFinanceCode("TW15122313691");//支付工单号
//        List<FinanceOrderResponse> financeOrderResponses = financeSearchRemote.searchOrderFinanceOrderList(financeOrderQuery);
//        System.out.println(financeOrderResponses);
    }
    
    
//    @Test
    public void queryFinanceOrderTest(){
    	try{
//    		FinanceOrderRemote financeOrderRemote = hessianProxyBeanFactory.getHessianProxyBean(
//        			FinanceOrderRemote.class,
//                    ProxyFactoryConstants.financeSearchRemoteUrl + "financeOrderRemote");
////    	    "http://192.168.0.49:80/hotel-finance-server/remote/financeOrderRemote 孙志平财务ip地址
//        	// 1
//    		// 100
//    		//
//        	FinanceOrderConfirmRequest financeOrderConfirmRequest = new FinanceOrderConfirmRequest();
//        	financeOrderConfirmRequest.setFinanceCode("TW14081902205");
//    		financeOrderConfirmRequest.setMerchantCode("M10000001");
//    		financeOrderConfirmRequest.setStatus(TradeStatusEnum.SUCCESS);
//    		financeOrderConfirmRequest.setSerialNumber("titanjrrequdo16060sf");
//    		financeOrderConfirmRequest.setConfirmManCode("23410");
//    		financeOrderConfirmRequest.setConfirmMan("system");
//            String msg= financeOrderRemote.confirmFinanceOrder(financeOrderConfirmRequest);
//            System.out.println(msg);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
        
    }

//    @Test
    public void validationTest(){
        FinancialUserBindRequest request = new FinancialUserBindRequest();
        request.setFcLoginUserName("");
        request.setLoginUserName("");
        request.setMerchantCode("");
        request.setUserid(2132432);
        System.out.println(GenericValidate.validate(request));
    }
    
//    @Test
    public void validateOrderService(){
    	HotelOrderSearchFacade hotelOrderSearchFacade = hessianProxyBeanFactory.getHessianProxyBean(
    			HotelOrderSearchFacade.class,
                ProxyFactoryConstants.orderServiceUrl + "hotelOrderSearchFacade");
    	
    	OrderDetailResponseDTO oDetailResponseDTO = hotelOrderSearchFacade.queryOrderByCode("H0167130617070623");
        System.out.println(oDetailResponseDTO.getHotelName());
    }
    
    @Test
    public void testCurrentTheme(){
    	MerchantDetailQueryDTO queryDTO = new MerchantDetailQueryDTO();
        queryDTO.setMerchantCode("M10029591");
        try{
    		MerchantFacade merchantFacade = hessianProxyBeanFactory
    				.getHessianProxyBean(MerchantFacade.class,
    						ProxyFactoryConstants.merchantServerUrl
    								+ "merchantFacade");

    		MerchantResponseDTO merchantResponseDTO =  merchantFacade.queryMerchantDetail(queryDTO);
        	System.out.println("1111111111111111111"+merchantResponseDTO.getTheme());
        }catch(Exception e){
        	e.printStackTrace();
        }
       
        
       
    }
    
}
