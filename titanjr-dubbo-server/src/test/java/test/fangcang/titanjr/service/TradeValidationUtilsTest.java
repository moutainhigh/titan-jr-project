package test.fangcang.titanjr.service;

import com.Rop.api.ApiException;
import com.Rop.api.domain.Transorderinfo;
import com.fangcang.merchant.enums.VersionEnum;
import com.fangcang.titanjr.dao.TitanRefundDao;
import com.fangcang.titanjr.dto.bean.RefundDTO;
import com.fangcang.titanjr.dto.request.NotifyRefundRequest;
import com.fangcang.titanjr.dto.response.NotifyRefundResponse;
import com.fangcang.titanjr.dto.response.RefundOrderResponse;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.enums.SignTypeEnum;
import com.fangcang.titanjr.util.TradeValidationUtils;
import com.fangcang.util.DateUtil;
import org.junit.Test;
import test.fangcang.titanjr.SpringTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoshan on 2017/11/24.
 */
public class TradeValidationUtilsTest extends SpringTest {

    @Resource
    TradeValidationUtils validationUtils;

    @Resource
    TitanRefundDao titanRefundDao;

    @Test
    public void testGetTransOrderInfo(){
        Date start = DateUtil.stringToDate("2017-11-27 00:00:00","yyyy-MM-dd HH:mm:ss");
        Date end = DateUtil.stringToDate("2017-11-29 00:00:00","yyyy-MM-dd HH:mm:ss");
//        try {
//            List<Transorderinfo> result =  validationUtils.validOrderPayRequest(start,end);
//            System.out.println(result);;
//        } catch (ApiException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testValidPayAndTransfer(){
        Date start = DateUtil.stringToDate("2016-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
        Date end = DateUtil.stringToDate("2017-12-06 23:59:59","yyyy-MM-dd HH:mm:ss");
        try {
            List<Transorderinfo> result =  validationUtils.validPayAndTransfer(start,end,"TJM10000022");
            System.out.println(result);;
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void queryNotifyRefundTest(){
        NotifyRefundRequest notifyRefundRequest = new NotifyRefundRequest();
        notifyRefundRequest.setBusiCode(BusiCodeEnum.QueryRefund.getKey());
        notifyRefundRequest.setMerchantNo("M000016");
        notifyRefundRequest.setOrderNo("2017112713031500004");
        notifyRefundRequest.setRefundAmount("101700");
        //DateUtil.dateToString(DateUtil.stringToDate("2017-04-06 15:23:48","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss")
        notifyRefundRequest.setOrderTime("20171127130315");
        notifyRefundRequest.setRefundOrderno("OD20171127130817008");//OD20170502092341001,OD20170505142021001 ，OD2017060210124100122
        notifyRefundRequest.setVersion("v1.1");
        notifyRefundRequest.setSignType(SignTypeEnum.MD5.getKey());
//        NotifyRefundResponse notifyRefundResponse = validationUtils.notifyGatewayRefund(notifyRefundRequest);
//        System.out.println(notifyRefundResponse);
    }

    @Test
    public void validRefundOrderInfoTest(){
        Date start = DateUtil.stringToDate("2017-11-14 00:00:00","yyyy-MM-dd HH:mm:ss");
        Date end = DateUtil.stringToDate("2017-11-29 00:00:00","yyyy-MM-dd HH:mm:ss");
//        try {
//            List<Transorderinfo> result =  validationUtils.validRefundOrderInfo(start,end);
//            System.out.println(result);
//        } catch (ApiException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void fixRefundFailOrderTest(){
        RefundDTO refundDTO = new RefundDTO();
        refundDTO.setRefundAmount("477100");
        refundDTO.setOrderNo("2017111016225100004");
        refundDTO.setUserOrderId("TJO171110162226260");
        refundDTO.setOrderTime("20171110162251");
        refundDTO.setVersion("v1.0");
//        RefundOrderResponse refundOrderResponse =  validationUtils.fixRefundFailOrder(refundDTO);

        NotifyRefundRequest notifyRefundRequest = new NotifyRefundRequest();
        notifyRefundRequest.setBusiCode(BusiCodeEnum.QueryRefund.getKey());
        notifyRefundRequest.setMerchantNo("M000016");
        notifyRefundRequest.setOrderNo("2017112713031500004");
        notifyRefundRequest.setRefundAmount("101700");
        //DateUtil.dateToString(DateUtil.stringToDate("2017-04-06 15:23:48","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss")
        notifyRefundRequest.setOrderTime("20171127130315");
        notifyRefundRequest.setRefundOrderno("OD20171201101923006");//OD20170502092341001,OD20170505142021001 ，OD2017060210124100122
        notifyRefundRequest.setVersion("v1.0");
        notifyRefundRequest.setSignType(SignTypeEnum.MD5.getKey());
//        NotifyRefundResponse notifyRefundResponse = validationUtils.notifyGatewayRefund(notifyRefundRequest);

    }

    @Test
    public void testQueryRefundList(){
        RefundDTO refundDTO = new RefundDTO();
        refundDTO.setOrderNo("2017112713031500004");
//        List<RefundDTO>  list = titanRefundDao.queryRefundDTO(refundDTO);
//        System.out.println(list);
    }

}
