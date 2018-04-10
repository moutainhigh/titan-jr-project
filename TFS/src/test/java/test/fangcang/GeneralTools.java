package test.fangcang;

import com.Rop.api.DefaultRopClient;
import com.Rop.api.request.WheatfieldOrderServiceReturngoodsRequest;
import com.Rop.api.response.WheatfieldOrderServiceReturngoodsResponse;
import com.fangcang.titanjr.common.bean.CallBackInfo;
import com.fangcang.titanjr.common.bean.NotifyBean;
import com.fangcang.titanjr.common.enums.*;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.RSConvertFiled2ObjectUtil;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.titanjr.common.util.httpclient.TitanjrHttpTools;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.NotifyRefundRequest;
import com.fangcang.titanjr.dto.request.RefundOrderRequest;
import com.fangcang.titanjr.dto.response.NotifyRefundResponse;
import com.fangcang.titanjr.dto.response.RefundOrderResponse;
import com.fangcang.titanjr.entity.TitanRefund;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.enums.SignTypeEnum;
//import com.fangcang.titanjr.enums.VersionEnum;
import com.fangcang.titanjr.rs.request.RSRefundRequest;
import com.fangcang.titanjr.rs.response.RsRefundResponse;
import com.fangcang.util.MyBeanUtil;
import com.fangcang.util.StringUtil;
import net.sf.json.JSONSerializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoshan on 2017/3/23.
 */
public class GeneralTools implements Serializable {

    private static final Log log = LogFactory.getLog(CommonTest.class);

    private static String ropUrl = "https://api.open.ruixuesoft.com:30005/ropapi";
    private static String appKey = "93A6626A-C082-4D25-B496-EA9CC6E90EDB";
    private static String appSecret = "DC368712-18A4-4290-9A58-FF995DC161DC";
    private static String sessionKey = "1492784442821246488";
    private static DefaultRopClient ropClient = new DefaultRopClient(ropUrl, appKey, appSecret, "xml");

    public static void main(String[] args) {

//        testQueryNotifyRefund();
//        testAddRefundOrder();
//        testRefundRequest();

//        System.out.println(System.currentTimeMillis());
//        System.out.println(DateUtil.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));

//        getBankCardInfo();


//        payCallBack();
    }

    private static NotifyRefundResponse getBankCardInfo() {

        List<NameValuePair> params = getBankQueryParam();
        System.out.println(params);
        String response ="";
        HttpPost httpPost = new HttpPost("http://checkstand.rongcapital.cn:8485/checkstand/payment");
        try {
            HttpResponse resp = HttpClient.httpRequest(params,httpPost);
            if (null != resp) {
                HttpEntity entity = resp.getEntity();
                response = EntityUtils.toString(entity);
                log.info("卡类型返回结果："+response);
                return null;
            }
        } catch (ParseException e) {
            log.error("卡类型查询异常", e);
        } catch (Exception e) {
            log.error("卡类型查询异常", e);
        }
        return null;
    }

    private static List<NameValuePair> getBankQueryParam(){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("busiCode", "106"));
        params.add(new BasicNameValuePair("merchantNo", "M000016"));
        params.add(new BasicNameValuePair("cardNo", "6222807200881006972"));//81018101302132031
        params.add(new BasicNameValuePair("version", "v1.0"));
        params.add(new BasicNameValuePair("signType", "1"));

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("signType=v1.0");
        stringBuffer.append("&version=1");
        stringBuffer.append("&merchantNo=M000016");
        stringBuffer.append("&busiCode=106");
        stringBuffer.append("&cardNo=6222807200881006972");
        stringBuffer.append("&key=PCDEFOI8808TFC");
        String signMsg = getMD5Sign(stringBuffer.toString() ,"UTF-8");
        params.add(new BasicNameValuePair("signMsg", signMsg));
        return params;
    }


    private static void  testRefundRequest(){
        NotifyRefundRequest notifyRefundRequest = new NotifyRefundRequest();
        notifyRefundRequest.setBusiCode(BusiCodeEnum.MerchantRefund.getKey());
        notifyRefundRequest.setMerchantNo("M000016");
        notifyRefundRequest.setOrderNo("2017122915515700004");
        notifyRefundRequest.setRefundAmount("115600");
        notifyRefundRequest.setOrderTime("20171229155158");
        notifyRefundRequest.setSignType(SignTypeEnum.MD5.getKey());
        notifyRefundRequest.setVersion(TitanjrVersionEnum.VERSION_1.getKey());
        notifyRefundRequest.setRefundOrderno("OD20180104143035001");//OD20170602101241001
        NotifyRefundResponse notifyRefundResponse = notifyGateawayRefund(notifyRefundRequest);
        System.out.println(notifyRefundResponse);
    }


    private static void testAddRefundOrder(){
        RefundOrderRequest refundOrderRequest = new RefundOrderRequest();
        refundOrderRequest.setAmount("115600");
        refundOrderRequest.setBusinessInfo("cb9325f7-472b-4799-acb8-af96ac9e9597");
        refundOrderRequest.setCreator("system");
        refundOrderRequest.setFee("0");
//        refundOrderRequest.setIsRealTime(1);
        refundOrderRequest.setNotifyUrl("http://www.titanmall.cn/TTMC3/orderCancel/returnRefund.shtml");
        refundOrderRequest.setOrderId("2017122915515700004");
//        refundOrderRequest.setOrderItemId();
        refundOrderRequest.setOrderTime("20171229155158");
        refundOrderRequest.setPayOrderNo("cb9325f7-472b-4799-acb8-af96ac9e9597");
        refundOrderRequest.setTransferAmount("115600");
        refundOrderRequest.setUserOrderId("TJO171229155154989");
        RefundOrderResponse refundOrderResponse = addRefundOrder(refundOrderRequest);
        System.out.println(refundOrderResponse.getRefundOrderNo());
    }

    private static RefundOrderResponse addRefundOrder(	RefundOrderRequest refundOrderRequest) {
        RefundOrderResponse refundOrderResponse = new RefundOrderResponse();
        RSRefundRequest refundRequest = new RSRefundRequest();
        refundRequest.setAmount(refundOrderRequest.getAmount());
        refundRequest.setOrderid(refundOrderRequest.getOrderId());
        refundRequest.setUserorderid(refundOrderRequest.getUserOrderId());
        refundRequest.setOrderitemid(refundOrderRequest.getOrderItemId());
        RsRefundResponse refundResponse = addRsRefundOrder(refundRequest);

        if(!CommonConstant.OPERATE_SUCCESS.equals(refundResponse.getOperateStatus())){
            log.error("下退款单失败:"+refundResponse.getReturnCode()+":"+refundResponse.getReturnMsg());
            refundOrderResponse.putErrorResult(TitanMsgCodeEnum.RS_ADD_REFUND_ORDER_FAIL);
            return refundOrderResponse;
        }

        TitanRefund titanRefund = new TitanRefund();
        titanRefund.setOrderid(refundOrderRequest.getOrderId());
        titanRefund.setRefundOrderno(refundResponse.getRefundOrderNo());
        titanRefund.setRefundAmount(refundRequest.getAmount());
        titanRefund.setCreateTime(new Date());
        titanRefund.setOrderTime(refundOrderRequest.getOrderTime());
        titanRefund.setCreator(refundOrderRequest.getCreator());
        titanRefund.setTransferAmount(refundOrderRequest.getTransferAmount());
        titanRefund.setFee(refundOrderRequest.getFee());
        titanRefund.setBusinessInfo(refundOrderRequest.getBusinessInfo());
        titanRefund.setStatus(RefundStatusEnum.REFUND_IN_PROCESS.status);
        titanRefund.setNotifyUrl(refundOrderRequest.getNotifyUrl());
        titanRefund.setUserorderid(refundOrderRequest.getUserOrderId());
        titanRefund.setPayOrderNo(refundOrderRequest.getPayOrderNo());

        System.out.println(JSONSerializer.toJSON(titanRefund).toString());
        refundOrderResponse.putSuccess();
        refundOrderResponse.setRefundOrderNo(refundResponse.getRefundOrderNo());
        return refundOrderResponse;
    }

    private static RsRefundResponse addRsRefundOrder(RSRefundRequest refundRequest) {
        RsRefundResponse response = new RsRefundResponse();
        try{
            WheatfieldOrderServiceReturngoodsRequest req = new WheatfieldOrderServiceReturngoodsRequest();
            MyBeanUtil.copyBeanProperties(req, refundRequest);
            WheatfieldOrderServiceReturngoodsResponse rsp = ropClient.execute(req, sessionKey);
            if (rsp != null) {
                log.info("调用addOrderRefund返回报文: \n" + rsp.getBody());
                String errorMsg;
                if (rsp.isSuccess() != true) {
                    if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                        errorMsg = rsp.getSubMsg();
                    } else {
                        errorMsg = rsp.getMsg();
                    }
                    response.setReturnCode(rsp.getErrorCode());
                    response.setReturnMsg(errorMsg);
                    log.error("调用接口addOrderRefund异常：" + errorMsg);
                } else {
                    response.setSuccess(true);
                    response.setOperateStatus(rsp.getIs_success());
                    response.setReturnMsg(rsp.getMsg());
                    response.setRefundOrderNo(rsp.getBatchno());;
                    if(rsp.getIs_success().equals(CommonConstant.OPERATE_SUCCESS)){
                        response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
                        response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
                    }
                }
            }else{
                response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
                response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
            }

        }catch(Exception e){
            response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
            response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
            log.error("下退款单失败"+e.getMessage());
        }
        return response;
    }



    private static void testQueryNotifyRefund(){
        NotifyRefundRequest notifyRefundRequest = new NotifyRefundRequest();
        notifyRefundRequest.setBusiCode(BusiCodeEnum.QueryRefund.getKey());
        notifyRefundRequest.setMerchantNo("M000016");
        notifyRefundRequest.setOrderNo("2018032323302900001");

        notifyRefundRequest.setRefundAmount("174000");
        //DateUtil.dateToString(DateUtil.stringToDate("2017-04-06 15:23:48","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss")
        notifyRefundRequest.setOrderTime("20180323233029");
        notifyRefundRequest.setRefundOrderno("OD20180324084337002");//OD20170502092341001,OD20170505142021001,OD2017060210124100122
        notifyRefundRequest.setVersion(TitanjrVersionEnum.VERSION_1.getKey());
        notifyRefundRequest.setSignType(SignTypeEnum.MD5.getKey());

        NotifyRefundResponse notifyRefundResponse = notifyGateawayRefund(notifyRefundRequest);
        System.out.println(notifyRefundResponse);
    }



    private static NotifyRefundResponse notifyGateawayRefund(
            NotifyRefundRequest notifyRefundRequest) {
        NotifyRefundResponse notifyRefundResponse = new NotifyRefundResponse();
        if(null ==notifyRefundRequest){
            notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
            return notifyRefundResponse;
        }
        List<NameValuePair> params = getGateawayParam(notifyRefundRequest);
        System.out.println(params);
        String response ="";
        HttpPost httpPost = new HttpPost("http://checkstand.rongcapital.cn:8485/checkstand/payment");
        try {
            HttpResponse resp = HttpClient.httpRequest(params,httpPost);
            if (null != resp) {
                HttpEntity entity = resp.getEntity();
                response = EntityUtils.toString(entity);
                log.info("退款返回信息："+response);
                notifyRefundResponse = RSConvertFiled2ObjectUtil.convertField2Object(NotifyRefundResponse.class, response);
                notifyRefundResponse.putSuccess();
                if(StringUtil.isValidString(notifyRefundResponse.getErrCode())
                        || StringUtil.isValidString(notifyRefundResponse.getErrMsg())
                        || !StringUtil.isValidString(notifyRefundResponse.getRefundOrderno())){//通知退款失败
                    log.error("退款通知异常:"+notifyRefundResponse.getErrMsg());
                    notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.RS_NOTIFY_REFUND_FAIL);
                }
                return notifyRefundResponse;
            }
        } catch (ParseException e) {
            notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.RS_NOTIFY_REFUND_FAIL);
            log.error("退款通知异常", e);
        } catch (Exception e) {
            notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.RS_NOTIFY_REFUND_FAIL);
            log.error("退款通知异常", e);
        }
        return notifyRefundResponse;
    }
    private static List<NameValuePair> getGateawayParam(NotifyRefundRequest notifyRefundRequest){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("merchantNo", notifyRefundRequest.getMerchantNo()));
        params.add(new BasicNameValuePair("busiCode", notifyRefundRequest.getBusiCode()));
        params.add(new BasicNameValuePair("orderNo", notifyRefundRequest.getOrderNo()));
        params.add(new BasicNameValuePair("refundAmount", notifyRefundRequest.getRefundAmount()));
        params.add(new BasicNameValuePair("orderTime", notifyRefundRequest.getOrderTime()));//
        params.add(new BasicNameValuePair("refundOrderno", notifyRefundRequest.getRefundOrderno()));
        params.add(new BasicNameValuePair("version", notifyRefundRequest.getVersion()));
        params.add(new BasicNameValuePair("signType", notifyRefundRequest.getSignType()));
        String signMsg = getMD5Sign(getStrSign(notifyRefundRequest) ,"UTF-8");
        params.add(new BasicNameValuePair("signMsg", signMsg));
        return params;
    }
    //获取前面字符串
    private static String getStrSign(NotifyRefundRequest notifyRefundRequest){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("signType="+notifyRefundRequest.getSignType());
        stringBuffer.append("&version="+notifyRefundRequest.getVersion());
        stringBuffer.append("&merchantNo="+notifyRefundRequest.getMerchantNo());
        stringBuffer.append("&refundOrderno="+notifyRefundRequest.getRefundOrderno());
        stringBuffer.append("&orderNo="+notifyRefundRequest.getOrderNo());
        stringBuffer.append("&orderTime="+notifyRefundRequest.getOrderTime());
        stringBuffer.append("&refundAmount="+notifyRefundRequest.getRefundAmount());
        stringBuffer.append("&key=PCDEFOI8808TFC");
        return stringBuffer.toString();
    }



    private static String getMD5Sign(String str,String charset){
        return MD5.MD5Encode(str, charset);
    }

    private static void refundCallback(){
        NotifyBean bean = new NotifyBean();
        bean.setPayOrderNo("85b66dc4-e8ab-4775-9e63-b56e0f9c115a");
        bean.setNotifyUrl("http://www.titanmall.cn/TTMC4/orderCancel/returnRefund.shtml");
        bean.setUserOrderId("TJO170429084618792");
        bean.setCode(RefundStatusEnum.REFUND_SUCCESS.status.toString());
        bean.setBusinessInfo("85b66dc4-e8ab-4775-9e63-b56e0f9c115a");
        System.out.println("退款单回调的参数："+ JSONSerializer.toJSON(bean));
        notifyTTMall(bean);
    }

    private static void notifyTTMall(NotifyBean bean){
        HttpPost httpPost = new HttpPost(bean.getNotifyUrl());
        String response = "";
        try{
            response = TitanjrHttpTools.confirmRefund(bean, httpPost);
            if(!StringUtil.isValidString(response)){
                System.out.println("回调退款单失败:" + bean.getUserOrderId());
                throw new Exception("回调异常");
            }
            CallBackInfo callBackInfo = TitanjrHttpTools.analyzeResponse(response);
            if (callBackInfo == null ||!"000".equals(callBackInfo.getCode())) {
                System.out.println("回调异常，TTMALL返回失败");
                throw new Exception("回调异常，TTMALL返回失败");
            }

            System.out.println("回调TTMALL成功");

        }catch(Exception e){
            System.out.println("退款回调失败");
        }
    }




    public static void payCallBack() {
        //转账成功之后回调:":0,"month":3,"timezoneOffset":-480,"year":117,"day":2,"date":25},"
        // provider":"","statusid":"0","adjustcontent":"","goodscnt":1,"goodsname":"交易平台付款",
        // "bankInfo":"","titanWithDrawDTO":null,
        // "goodsdetail":"H1462170420121458-珠海企鹅酒店-温带房-入住日期:2017-04-23-离店日期:2017-04-24",
        // "orderid":"2017042012152100001","refundDTO":null}

        TransOrderDTO transOrderDTO = new TransOrderDTO();
        transOrderDTO.setPayorderno("1000000000057998");
        transOrderDTO.setBusinessordercode("H1414180223143240");
        transOrderDTO.setMerchantcode("M10000722");
        transOrderDTO.setCreator("周国强(szzmkm)");
        transOrderDTO.setUserorderid("TJO180223143247888");
        transOrderDTO.setTradeamount((long)41000);
        transOrderDTO.setBusinessinfo("{\"bussCode\":\"H1414180223143240\"}");
        transOrderDTO.setNotifyUrl("http://192.168.140.169:29010/PUS/fcjr_pay.html");
        try {
            GeneralTools.confirmFinance(transOrderDTO);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public static void confirmFinance(TransOrderDTO transOrderDTO) throws Exception {

        if (transOrderDTO == null
                || !StringUtil.isValidString(transOrderDTO.getUserorderid())) {
            return;
        }

        String response = "";

        List<NameValuePair> params = getHttpParams(transOrderDTO);

        if (params == null) {
            return;
        }

        String url = null;

        if (StringUtil.isValidString(transOrderDTO.getNotifyUrl())) {
            url = transOrderDTO.getNotifyUrl();
        }else{
            return;
        }
        try {
            log.info("转账成功之后回调:" + JSONSerializer.toJSON(params) + "---url---"  + url);
            HttpPost httpPost = new HttpPost(url);
            HttpResponse resp = HttpClient.httpRequest(params,  httpPost);
            if (null != resp) {
                InputStream in = resp.getEntity().getContent();
                byte b[] = new byte[1024];

                int length = 0;
                if((length = in.read(b)) !=-1){
                    byte d[] = new byte[length];
                    System.arraycopy(b, 0, d, 0, length);
                    response = new String(d , "UTF-8");
                }
                httpPost.releaseConnection();
            }
        } catch (Exception e) {
            log.error("调用http请求通知支付失败", e);
        }
        log.info("调用http请求通知支付支付结果完成：" + response);
        if (StringUtil.isValidString(response)) {
            CallBackInfo callBackInfo = TitanjrHttpTools.analyzeResponse(response);
            System.out.println(callBackInfo.toString());
        } else {// 记录异常单
            log.error("回调无响应");
        }

    }

    private static List<NameValuePair> getHttpParams(TransOrderDTO transOrderDTO) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("payOrderCode", transOrderDTO
                .getPayorderno()));
        params.add(new BasicNameValuePair("businessOrderCode", transOrderDTO
                .getBusinessordercode()));

        if(transOrderDTO.getTradeamount()!=null ){
            params.add(new BasicNameValuePair("amount", transOrderDTO
                    .getTradeamount().toString()));
        }
        NameValuePair nameValuePair = new BasicNameValuePair("merchantCode", transOrderDTO
                .getMerchantcode());

        //此段处理逻辑是前一版本收银台的逻辑，新版本之后将舍去
//        PayerTypeEnum payerTypeEnum = PayerTypeEnum.getPayerTypeEnumByKey(transOrderDTO.getPayerType());
//        if(payerTypeEnum !=null && payerTypeEnum.isB2BPayment()){
//            OrgBindInfo orgBindInfo = this.getOrgBindInfo(transOrderDTO
//                    .getPayeemerchant());
//            if (null != orgBindInfo) {
//                nameValuePair = new BasicNameValuePair("merchantCode", orgBindInfo
//                        .getMerchantCode());
//            }
//        }
        params.add(nameValuePair);
        //end
        params.add(new BasicNameValuePair("operator", transOrderDTO
                .getCreator()));

        params.add(new BasicNameValuePair("titanPayOrderCode", transOrderDTO
                .getUserorderid()));
        params.add(new BasicNameValuePair("businessInfo", transOrderDTO.getBusinessinfo()));

        params.add(new BasicNameValuePair("payResult", "1"));
        params.add(new BasicNameValuePair("code", "valid"));
        return params;
    }

}
