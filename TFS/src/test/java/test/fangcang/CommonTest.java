package test.fangcang;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fangcang.titanjr.common.bean.CallBackInfo;
import com.fangcang.titanjr.common.bean.NotifyBean;
import com.fangcang.titanjr.common.enums.RefundStatusEnum;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.titanjr.common.util.httpclient.TitanjrHttpTools;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.NotifyRefundRequest;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.util.StringUtil;

import net.sf.json.JSONSerializer;

/**
 * Created by zhaoshan on 2017/3/23.
 */
public class CommonTest {

    private static final Log log = LogFactory.getLog(CommonTest.class);

    public static void main(String[] args) {
    }

    
    
    private static void notifyGateawayRefund()   {

		NotifyRefundRequest notifyRefundRequest = new NotifyRefundRequest();
		notifyRefundRequest.setOrderNo("2017112713031500004");
		notifyRefundRequest.setOrderTime("20171127130315");
		notifyRefundRequest.setBusiCode(BusiCodeEnum.QueryRefund.getKey());
		notifyRefundRequest.setMerchantNo("M000016");
		notifyRefundRequest.setRefundAmount("101700");
		notifyRefundRequest.setRefundOrderno("OD20171201101923006");
		notifyRefundRequest.setSignType("1");
		notifyRefundRequest.setVersion("v1.0");
		
		
		List<NameValuePair> params = getGateawayParam(notifyRefundRequest);
		String response ="";
		BusiCodeEnum busiCodeEnum = BusiCodeEnum.getEnumByKey(notifyRefundRequest.getBusiCode());
		HttpPost httpPost = new HttpPost("http://checkstand.rongcapital.cn:8485/checkstand/payment");//生产地址
		HttpResponse resp = HttpClient.httpRequest(params,httpPost);
		
		if (null != resp) {
			HttpEntity entity = resp.getEntity();
			try {
				response = EntityUtils.toString(entity);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.info("调用融数网关gateWayURL退款或查询退款状态,操作："+busiCodeEnum.toString()+",orderId："+notifyRefundRequest.getOrderNo()+",请求参数:"+Tools.gsonToString(params)+",退款返回信息response："+response);
    
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
    private static String getMD5Sign(String str,String charset){
		return MD5.MD5Encode(str, charset);
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
  	
    private static void refundCallback(){
    	
        NotifyBean bean = new NotifyBean();
        bean.setPayOrderNo("8c56a10d-8268-4d5c-9fe4-35ebf424851e");
        bean.setNotifyUrl("http://www.titanmall.cn/TTMC3/orderCancel/returnRefund.shtml");
        bean.setUserOrderId("TJO170318115843129");
        bean.setCode(RefundStatusEnum.REFUND_SUCCESS.status.toString());
        bean.setBusinessInfo("370aaa97-6597-416e-9615-a43eb7b99080");
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
        TransOrderDTO transOrderDTO = new TransOrderDTO();
        transOrderDTO.setPayorderno("AD724968");
        transOrderDTO.setBusinessordercode("");
        transOrderDTO.setMerchantcode("");
        transOrderDTO.setCreator("东莞运通");
        transOrderDTO.setUserorderid("TJO170330140528406");
        transOrderDTO.setTradeamount((long)85000);
        transOrderDTO.setBusinessinfo("{\"extraInfo\":{\"merpriv\":\"15263\",\"orderamt\":\"850.0\"},\"ruserId\":\"TJM10000098\",\"bussCode\":\"\"}");
        transOrderDTO.setNotifyUrl("http://www.bookingclub.cn/taitanpayment/taitanpaynotify");
        try {
            CommonTest.confirmFinance(transOrderDTO);
        } catch (Exception e) {
            log.error("", e);
            e.printStackTrace();
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
