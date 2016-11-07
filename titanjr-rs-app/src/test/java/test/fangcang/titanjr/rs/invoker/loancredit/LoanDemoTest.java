package test.fangcang.titanjr.rs.invoker.loancredit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import com.Rop.api.DefaultRopClient;
import com.Rop.api.request.FsFileUploadRequest;
import com.Rop.api.request.FsFileurlGetRequest;
import com.Rop.api.request.WheatfieldInterestRepaymentQueryborrowinfoRequest;
import com.Rop.api.request.WheatfieldInterestRepaymentQuerypartyinitiativerepaymentRequest;
import com.Rop.api.request.WheatfieldInterestRepaymentQueryuserrepaymentRequest;
import com.Rop.api.request.WheatfieldInterestRepaymentUserinitiativerepamentRequest;
import com.Rop.api.request.WheatfieldOprsystemCreditCompanyRequest;
import com.Rop.api.request.WheatfieldOrderMixserviceCreditapplicationRequest;
import com.Rop.api.request.WheatfieldOrderMixserviceCreditmerchantinfoqueryRequest;
import com.Rop.api.request.WheatfieldOrderServiceAgreementconfirmRequest;
import com.Rop.api.request.WheatfieldOrderServiceNewloanapplyRequest;
import com.Rop.api.response.FsFileUploadResponse;
import com.Rop.api.response.FsFileurlGetResponse;
import com.Rop.api.response.WheatfieldInterestRepaymentQueryborrowinfoResponse;
import com.Rop.api.response.WheatfieldInterestRepaymentQuerypartyinitiativerepaymentResponse;
import com.Rop.api.response.WheatfieldInterestRepaymentQueryuserrepaymentResponse;
import com.Rop.api.response.WheatfieldInterestRepaymentUserinitiativerepamentResponse;
import com.Rop.api.response.WheatfieldOprsystemCreditCompanyResponse;
import com.Rop.api.response.WheatfieldOrderMixserviceCreditapplicationResponse;
import com.Rop.api.response.WheatfieldOrderMixserviceCreditmerchantinfoqueryResponse;
import com.Rop.api.response.WheatfieldOrderServiceAgreementconfirmResponse;
import com.Rop.api.response.WheatfieldOrderServiceNewloanapplyResponse;
import com.fangcang.titanjr.common.util.Tools;

/**
 * Created by zhaoshan on 2016/10/9.
 */
public class LoanDemoTest {

//    private static String ropUrl = "https://testapi.open.ruixuesoft.com:30005/ropapi";
//    private static String appKey = "F1A95B5E-3485-4CEB-8036-F2B9EC53EF654";
//    private static String appSecret = "8B6E8EEF-48CC-4CCF-94C6-55C4AA2FE9F24";
//    private static String session = "1460355562856409835";
    
    
    private static String ropUrl = "https://api.open.ruixuesoft.com:30005/ropapi";
	private static String appKey = "C5CE632E-FDA6-436A-B4DF-1DE93A2C72C3";
	private static String appSecret = "D7787ED2-0465-42C7-9CF8-25D5CC6ACA34";
	private static String session = "1478056836773639888";
	

    static DefaultRopClient ropClient = new DefaultRopClient(ropUrl, appKey,
            appSecret, "xml");
    //文件
    //上传公钥：
    String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWYa7Oj4e0we9Tg9kJLjyNS3rLzlo/8nHp7Sb+oEfgnK/1gzH0OMlp8Hf09WyNk3T64UZtYPd5KwCVWMoZyK3jQwttHr+3cJ8335B4NzSwWjBfRJAf3bQPw0iZT+uoLF5+L7tbIK0Ql4bdDX+io1TkCagRU6XASrG7reXa3yc45wIDAQAB";
    //下载私钥：
    String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJZhrs6Ph7TB71OD2QkuPI1LesvOWj/ycentJv6gR+Ccr/WDMfQ4yWnwd/T1bI2TdPrhRm1g93krAJVYyhnIreNDC20ev7dwnzffkHg3NLBaMF9EkB/dtA/DSJlP66gsXn4vu1sgrRCXht0Nf6KjVOQJqBFTpcBKsbut5drfJzjnAgMBAAECgYBtzcOjJeBUFutWUsZt0qn6DawLFpCFctTElxpK/+Ob2S0OGo6mIbTHuwMMBQwPUCUxbr5K7WuluMWDR0LCkuqMEQHHhJNVTknfVpwYcy5kGBbCGEywhgnfLlzIEfdeAiJ0I+Kfrzxc+QwvU/ecNPGhNhagbd8cHoI61/5EEf8JEQJBAMZ0uysxlMiEnc5QCsFfNVNkwsGhzwSvT3+xoKiLhM389Bxz14TEF9OKD241Lz9xoqGP9tHpDFeX/eJm+brksD8CQQDB/GrMWnETJ/kHEv37PZsiCtE6LQluMtqNIIwzSiliaYfF6xmcSMX6gBKS7DATu9lBJipJKTYWv8tyOWx6nU1ZAkAuxdhPJ9JfKBJhS7AdPyk8TGUyacZ23jKob97jmm5kdhe6lPrYibbr3oAgg1xtYYTo+xs7AegsxN/LemWlTLzVAkEAl1bPFzjkCLbhwJQfk7ffZLKdws5KEjAYc4vV9VnBaaa9Jzqgk13vHtx2ISuk4nBmMT6ONN+y9BKTrTyBljNMmQJAHmS2M2ZfvrZRYNfKyi/de2tAk/kTNX4rB62Uqwz6Z3+VzcDNdJpey/RNwd0QvC1O4OKJY3gDGkyO/YI4HJkr+w==";

    public static void main(String[] args) {
        
        String strMsg = null;

		// 文件上传API接口 "ruixue.fs.file.upload";
		// strMsg = doFsFileUpload(session);
		// 已上传文件下载地址获取 "ruixue.fs.fileurl.get";
		// strMsg = doFsFileUrlGet(session);
		/* ruixue.wheatfield.oprsystem.credit.company 机构授信申请接口 */
		// strMsg = doWheatfieldOprsystemCreditCompany(session);//上传企业资料
        ;
		//  doWheatfieldOrderMixserviceCreditapplication(session);//授信申请
		/* 7 ruixue.wheatfield.order.service.agreementconfirm 7 协议确认 */
		// doWheatfieldOrderServiceAgreementconfirm(session);//

		/* 9 ruixue.wheatfield.order.mixservice.creditmerchantinfoquery 机构授信信息查询 */
		// strMsg = doWheatfieldOrderMixserviceCreditmerchantinfoquery(session);

		// strMsg = doWheatfieldOrderServiceNewloanapply(session);//贷款

		 doqueryborrowinfo(session);//查询待还款记录
		// System.out.println(testGetCreditJsonData());

    }

    /*************************授信申请融数所用接口*******************************/
    /* ruixue.fs.file.upload 测试文件上传 （第4步） */
    private static String doFsFileUpload(String session) {
        String strError = null;
        try {
        	//rsa加密
            FsFileUploadRequest req = new FsFileUploadRequest();
            req.setBatch("2016-11-02");
            req.setPath("f:/dk-123456-encryptFile.zip");
            req.setType(73);
            req.setInvoice_date(com.fangcang.util.DateUtil.dateFormat(new Date(),"yyyy-MM-dd"));
            FsFileUploadResponse rsp = ropClient.execute(req,session);
            System.out.println("返回报文FsFileUploadResponse: \n" + Tools.gsonToString(rsp));
            if (rsp != null) {
                
                if (rsp.isSuccess() != true) {
                    if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                        strError = rsp.getSubMsg();
                    } else {
                        strError = rsp.getMsg();
                    }
                    
                }else{
                	return rsp.getUrl_key();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        } finally {
        }
        //af165cd4-4434-49c8-980f-7a652471c7d7
        //ffff,c7d7ea2a-e7a5-49ff-8621-61aceeffaf5c
        //150a15fa-b2fa-4c72-95b5-20f173c47814
        return strError;
    }

    /* ruixue.fs.fileurl.get 测试文件下载地址获取  */
    private static String doFsFileUrlGet(String session) {
        String strError = null;
        try {
            FsFileurlGetRequest req = new FsFileurlGetRequest();
            req.setUrl_key("c7d7ea2a-e7a5-49ff-8621-61aceeffaf5c");
            FsFileurlGetResponse rsp = ropClient.execute(req,session);
            if (rsp != null) {
                System.out.println("返回报文: \n" + rsp.getBody());
                if (rsp.isSuccess() != true) {
                    if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                        strError = rsp.getSubMsg();
                    } else {
                        strError = rsp.getMsg();
                       
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        } finally {
        }
        //af165cd4-4434-49c8-980f-7a652471c7d7
        return strError;
    }

    /* ruixue.wheatfield.oprsystem.credit.company 测试推送企业资料 --机构（企业）授信申请（第2步）  */
    private static String doWheatfieldOprsystemCreditCompany(String session) {
        String strError = null;
        try {
            WheatfieldOprsystemCreditCompanyRequest req = new WheatfieldOprsystemCreditCompanyRequest();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse("2016-10-09 00:00:00");
            req.setUserid("TJM10000087");								//申请人id（第三方系统）
            req.setConstid("M000016");									//机构号
            req.setCompanyname("腾讯云计算有限公司1"); 								//企业名称
            req.setBusinesslicense("企业执照号10000002225"); 					//营业执照号
            req.setCertificatestartdate(date); 							//营业照生效日期
            req.setCertificateexpiredate(date);							//营业照失效日期
            req.setCompanytype(1); 										//企业类型1.有限责任公司；2.股份有限公司；3.内资；4.国有全资；5.集体全资；
            //6.国外投资股份有限公司；99.其它；
            req.setRegistfinance("100000000");         					//注册资本
            req.setAddress("深圳市福田区创业花园");							//企业地址
            req.setCorporatename("张三丰");  								//法人姓名
            req.setCertificatetype(1);									//法人证件类型1.身份证;2.护照;8.户口本;21.军官证;22.士兵证;23.回乡证;
            //24.台湾居民往来大陆通行证;25.香港居民往来大陆通行证;99.其他;
            req.setCertificatenumber("411382198012212536");				//法人证件号码
            //可选
            req.setRegistrationorga("kxReg");								//企业登记机关号
            req.setTaxregcard("kxTax");										//税务登记证号
            req.setOrgancertificate("kxOrg");								//组织机构代码证号
            req.setAcuntopenlince("kxAcuntopen");							//开户许可证号
            WheatfieldOprsystemCreditCompanyResponse rsp = ropClient.execute(req,
                    session);
            if (rsp != null) {
                System.out.println("返回报文: \n" + Tools.gsonToString(rsp));
                if (rsp.isSuccess() != true) {
                    if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                        strError = rsp.getSubMsg();
                    } else {
                        strError = rsp.getMsg();
                    }
                }
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        } finally {
        }
        return strError;
    }

    /*ruixue.wheatfield.order.mixservice.creditapplication 授信申请接口 （第5步）*/
    private static String doWheatfieldOrderMixserviceCreditapplication(String session) {
        String strError = null;
        try {
            WheatfieldOrderMixserviceCreditapplicationRequest req = new WheatfieldOrderMixserviceCreditapplicationRequest();
            //必输
            //需要先调用 ruixue.wheatfield.oprsystem.credit.company 机构授信申请接口
            req.setUserid("TJM10000087");								//申请人id
            req.setAmount("100000000");									//申请金额 单位:分
            req.setRootinstcd("M000016");								//机构码 融数分配
            req.setProductid("P000070");								//产品id 融数分配
            req.setUserorderid("UD4865100054");						//第三方申请编号
            req.setReqesttime("999");										//申请期限 单位 月
            req.setOrderplatformname("腾讯云计算有限公司1");						//申请者单位名称
            req.setRequestdate("2016-10-09 12:12:12");					//申请时间 yyyy-MM-dd HH:mm:ss
            req.setRatetemplrate("RA201610141100001");					//费率模板 为融数的费率模板id
//			req.setJsondata("{\"propertyLastYear\":\"10000\",\"debtLastYear\":\"10000\",\"averageSalary\":\"100\"}");
            req.setJsondata(testGetCreditJsonData());
            
            req.setCreditype("2");//房仓的都是零售商
            //申请明细信息json串 如果为空 请传 {}
            req.setUrlkey("150a15fa-b2fa-4c72-95b5-20f173c47814");					//授信申请资料上传urlkey
            //可选
            req.setUserrelateid("");					//关联用户id
            req.setRemark("");							//备注
            WheatfieldOrderMixserviceCreditapplicationResponse rsp = ropClient.execute(req,
                    session);
            if (rsp != null) {
                System.out.println("返回报文: \n" + rsp.getBody());
                if (rsp.isSuccess() != true) {
                    if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                        strError = rsp.getOrderid();//Orderid:2016102815250400001,2016110215063100001
                    } else {
                        strError = rsp.getMsg();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        } finally {
        }
        return strError;
    }

    /*ruixue.wheatfield.order.service.agreementconfirm 应收账款申请协议确认（第7步）*/
    private static String doWheatfieldOrderServiceAgreementconfirm(String session) {
        String strError = null;
        try {
            WheatfieldOrderServiceAgreementconfirmRequest req = new WheatfieldOrderServiceAgreementconfirmRequest();
            req.setUrlkeya("a");					//分期付款培训协议(userflag 为1时必须)
            req.setUrlkeyb("b");					//应收账款融资申请书(userflag 为1时必须)
            req.setUrlkeyc("c");					//应收账款债权转让通知书回执(userflag 为1时必须)
            req.setUrlkeyd("d");					//应收账款债权转让通知书(userflag 为1时必须)
            req.setUserorderid("UD4865100054");		//应收账款申请商户流水   是贷款申请的userorderid
            req.setRootinstcd("M000016");			//机构号
            req.setUserid("TJM10000087");			//用户id
            req.setUserflag("2");					//用户标识 1学生 2机构
            req.setMerchanturlkey("e");				//机构保理合同(userflag 为2时必须)
            WheatfieldOrderServiceAgreementconfirmResponse rsp = ropClient.execute(req,
                    session);
            if (rsp != null) {
                System.out.println("返回报文: \n" + rsp.getBody());
                if (rsp.isSuccess() != true) {
                    if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                        strError = rsp.getSubMsg();
                    } else {
                        strError = rsp.getMsg();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        } finally {
        }
        return strError;
    }

    /*ruixue.wheatfield.order.mixservice.creditmerchantinfoquery 机构授信信息查询（第9步）*/
    private static String doWheatfieldOrderMixserviceCreditmerchantinfoquery(String session) {
        String strError = null;
        try {
            WheatfieldOrderMixserviceCreditmerchantinfoqueryRequest req = new WheatfieldOrderMixserviceCreditmerchantinfoqueryRequest();
            req.setUserid("TJM10000087");					//用户id
            req.setRootinstcd("M000016");				//机构id
            req.setProductid("P000070");				//产品id
            req.setUserorderid("UD4865100054");				//申请编号
            WheatfieldOrderMixserviceCreditmerchantinfoqueryResponse rsp = ropClient.execute(req,
                    session);
            if (rsp != null) {
                System.out.println("返回报文: \n" + rsp.getBody());
                if (rsp.isSuccess() != true) {
                    if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                        strError = rsp.getSubMsg();
                    } else {
                        strError = rsp.getMsg();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        } finally {
        }
        return strError;
    }

    /**************************贷款申请融数所用接口********************************/
    //合同上传（第2步）

    /*ruixue.wheatfield.order.service.newloanapply 个人贷款申请（第3步）*/
    private static String doWheatfieldOrderServiceNewloanapply(String session) {
        String strError = null;
        try {
            WheatfieldOrderServiceNewloanapplyRequest req = new WheatfieldOrderServiceNewloanapplyRequest();
            //必输
            req.setUserid("TJM10000087");										//申请人id
            req.setUserorderid("TJO2016102599977");							//交易流水
            req.setAmount("100");												//申请金额
            req.setProductid("P000070");										//产品id
            req.setRootinstcd("M000016");										//机构码
            req.setUserrelateid("TJM10000110");									//申请的机构
            req.setUsername("罗庆龙");											//申请人姓名
            req.setRequestdate("2016-07-27 12:12:12");							//申请时间
            req.setRatetemplate("RA201611011800001");							//费率模板
            
            req.setJsondata(getMoneyCreditJsonData());
            req.setUrlkey("150a15fa-b2fa-4c72-95b5-20f173c47814");	//贷款申请资料上传urlkey

            //可选
            req.setReqesttime("");												//申请期限
            req.setRemark("");													//备注
            WheatfieldOrderServiceNewloanapplyResponse rsp = ropClient.execute(req,
                    session);
            if (rsp != null) {
                System.out.println("返回报文: \n" + rsp.getBody());
                if (rsp.isSuccess() != true) {
                    if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                        strError = rsp.getOrderid();// orderid:2016102816291600001，2016110215302100001
                    } else {
                        strError = rsp.getMsg();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        } finally {
        }
        return strError;
    }
    /**
     * 主动还款
     * ruixue.wheatfield.interest.repayment.userinitiativerepament
     * @return
     */
    public static String douserinitiativerepament(String session){
    	String strError = null;
    	WheatfieldInterestRepaymentUserinitiativerepamentRequest request = new WheatfieldInterestRepaymentUserinitiativerepamentRequest();
    	request.setUserid("TJM10000087");	
    	
    	request.setProductid("P000070");										//产品id
    	request.setRootinstcd("M000016");										//机构码
    	request.setUserorderid("2016110215302100001");  						//用户订单id
    	try {
    		WheatfieldInterestRepaymentUserinitiativerepamentResponse rsp = ropClient.execute(request,session);
    		 System.out.println("返回报文: \n" + rsp.getBody());
             if (rsp.isSuccess() != true) {
                 if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                     strError = rsp.getIs_success();
                 } else {
                     strError = rsp.getMsg();
                 }
             }
		} catch (Exception e) {
			 System.out.println(e);
	         return "error";
		}
    	return strError;
    }
    
    /**
     *  主动还款查询
     * ruixue.wheatfield.interest.repayment.queryuserinitiativerepayment
     * @return
     */
    public static String doqueryuserinitiativerepayment(String session){
    	String strError = null;
    	WheatfieldInterestRepaymentQuerypartyinitiativerepaymentRequest request = new WheatfieldInterestRepaymentQuerypartyinitiativerepaymentRequest();
    	request.setUserid("TJM10000087");	
    	
    	request.setProductid("P000070");										//产品id
    	request.setRootinstcd("M000016");										//机构码
    	request.setUserorderid("2016110215302100001");  						//用户订单id
    	try {
    		WheatfieldInterestRepaymentQuerypartyinitiativerepaymentResponse rsp = ropClient.execute(request,session);
    		 System.out.println("返回报文: \n" + rsp.getBody());
             if (rsp.isSuccess() != true) {
                 if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                     strError = rsp.getIs_success();
                 } else {
                     strError = rsp.getMsg();
                 }
             }
		} catch (Exception e) {
			 System.out.println(e);
	         return "error";
		}
    	return strError;
    }
    
    
    /***
     * 查询应还款信息
     * ruixue.wheatfield.interest.repayment.queryborrowinfo
     */
    public static String doqueryborrowinfo(String session){
    	 String strError = null;
    	 try {
    			WheatfieldInterestRepaymentQueryborrowinfoRequest request = new WheatfieldInterestRepaymentQueryborrowinfoRequest();
    			request.setRootinstcd("M000016");
    			request.setProductid("P000070");
    			request.setUserid("TJM10000087");
    			request.setUserorderid("TJO2016102599977");
    			WheatfieldInterestRepaymentQueryborrowinfoResponse rsp = ropClient.execute(request,
    	                 session);
    	         if (rsp != null) {
    	             System.out.println("返回报文: \n" + rsp.getBody());
    	             if (rsp.isSuccess() != true) {
    	                 if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
    	                     strError = rsp.getIs_success();
    	                 } else {
    	                     strError = rsp.getMsg();
    	                 }
    	             }
    	         }
		} catch (Exception e) {
			 System.out.println(e);
	         return "error";
		}
    
    	return strError;
    } 
    
    /***
     * 查询贷款的还款状态及历史
     * ruixue.wheatfield.interest.repayment.queryuserrepayment
     * @return
     */
    public static String doqueryuserrepayment(String session){
    	String strError = null;
    	WheatfieldInterestRepaymentQueryuserrepaymentRequest  request = new WheatfieldInterestRepaymentQueryuserrepaymentRequest();
    	request.setRootinstcd("M000016");
		request.setProductid("P000070");
		request.setUserid("TJM10000087");
		request.setUserorderid("2016110215302100001");
    	try {
    		WheatfieldInterestRepaymentQueryuserrepaymentResponse rsp = ropClient.execute(request,session);
    		 System.out.println("返回报文: \n" + rsp.getBody());
             if (rsp.isSuccess() != true) {
                 if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                     strError = rsp.getSubMsg();
                 } else {
                     strError = rsp.getMsg();
                 }
             }
		} catch (Exception e) {
			 System.out.println(e);
	         return "error";
		}
    	return strError;
    } 
   
   
    //测试构造授信申请所需json串
    public  static String testGetCreditJsonData(){
        CreditJsonData creditJsonData = new CreditJsonData();
        creditJsonData.setRootInstCd("M000016");
        creditJsonData.setPassportNumber("27889998987");///*****************无法提供
        creditJsonData.setWorkPhone("0755-88884444");
        
        creditJsonData.setPlatformRegistTime("2015-01-10 00:00:00");
        creditJsonData.setPlatformOperaNo("M10000001");
        creditJsonData.setOperatNumberEmployees("50");
        creditJsonData.setLeaseTerm("2");
        creditJsonData.setLeaseAddress("向荣大厦");
        creditJsonData.setHousingArea("1000");
        creditJsonData.setRental("130000");
        creditJsonData.setPaymentMethod("网上转账");
        creditJsonData.setRemark("经营信息");
        //主营信息
        MainBusinessData mainBusinessData= new MainBusinessData();
        
        mainBusinessData.setMainProductsOrService("互联网");
        mainBusinessData.setMainAnnualSale("1200000");
        mainBusinessData.setMainSaleProportion("12%");
        
        List<MainBusinessData> mainBusinessDataList = new ArrayList<MainBusinessData>();
        creditJsonData.setMainBusinessData(mainBusinessDataList);
        
        //合作企业信息
        CooperationCompanyInfo cooperationCompanyInfo = new CooperationCompanyInfo();
        cooperationCompanyInfo.setCooperationName("深圳悦翔旅游有限公司");
        cooperationCompanyInfo.setYearAnnualSale("1300000");
        cooperationCompanyInfo.setSaleProportion("30%");
        cooperationCompanyInfo.setSettlement("现金");
        cooperationCompanyInfo.setCooperation("供应商");
        cooperationCompanyInfo.setCooperationYears("4年");
        
        List<CooperationCompanyInfo> cooperationCompanyInfoList = new ArrayList<CooperationCompanyInfo>();
        cooperationCompanyInfoList.add(cooperationCompanyInfo);
        creditJsonData.setCooperationCompanyInfo(cooperationCompanyInfoList);
        
        //股东信息 
        ControllData controllData = new ControllData();
        controllData.setShareholderName("周小夏");
        controllData.setContributionAmount("500000");
        controllData.setEquityRatio("10%");
        
        List<ControllData> controllDataList = new ArrayList<ControllData>();
        controllDataList.add(controllData);
        creditJsonData.setControllData(controllDataList);
        
        
        //法人
        creditJsonData.setCompanyName_c("深圳市鼎力集团");
        creditJsonData.setBusinessExpire_c("2020-10-10");
        creditJsonData.setCompanyType_c("上市公司");
        creditJsonData.setRegisteredCapital_c("50000000");
        creditJsonData.setEmployeesNumber_c("500");
        creditJsonData.setRegisteredAddress_c("深圳市福田区");
        creditJsonData.setOfficeAddress_c("深圳市罗湖区总部");
        creditJsonData.setSetUpDate_c("2011-10-10");
        creditJsonData.setBusinessLicenseNumber_c("59699878443305");
        creditJsonData.setTaxregCard_c("44433225666");
        creditJsonData.setOrganizaCertificateNo_c("");
        creditJsonData.setPlatformOperaNo_c("");
        creditJsonData.setPlatformRegistTime_c("");
        creditJsonData.setLegalPersonName_c("刘丽红");
        creditJsonData.setPersonCertificate_c("1");
        creditJsonData.setApplicateCardId_c("444569998625744");
        creditJsonData.setPrimaryContactName_c("刘小红");
        creditJsonData.setPrimaryContactPhoneNumber("13366669999");
        
        
        ///自然人担保
        creditJsonData.setApplicateName_p("陈晓晓");
        creditJsonData.setApplicateCardId_p("563589195610204521");
        creditJsonData.setWorkUnit_p("深圳市平安银行");
        creditJsonData.setWorkStatus_p("金融");
        creditJsonData.setOccupation_p("行长");
        creditJsonData.setCourseOpenTime_p("15年");
        creditJsonData.setAnnualIncome_p("1000000");
        creditJsonData.setPhoneNumber_p("13356987854");
        creditJsonData.setFamilyFixed_p("075588889999");
        creditJsonData.setPlaceOfOrigin_p("北京");
        creditJsonData.setEmail_p("llluo@eeee.com");
        creditJsonData.setAcademy_p("北京大学");
        creditJsonData.setEducation_p("博士");
        creditJsonData.setAddress_p("深圳市宝安区翻身路");
        creditJsonData.setHouseNature_p("买房");
        creditJsonData.setMarriageStatus_p("已婚");
        creditJsonData.setChildrenStatus_p("有子女");
        creditJsonData.setFirstContactName_p("李小琳");
        creditJsonData.setFirstContactPhone_p("13599998888");
        creditJsonData.setFirstContactRelations_p("母女");
        creditJsonData.setSecondContactName_p("李小桦");
        creditJsonData.setSecondContactPhone_p("13523458888");
        creditJsonData.setSecondContactRelations_p("母女");
        creditJsonData.setRoomSituation_p("有房");
        creditJsonData.setCarBrandModel_p("奔驰");
        creditJsonData.setCarValue_p("1000000");
        creditJsonData.setBuyCarYear_p("2016");
        creditJsonData.setOtherAssets("");
        creditJsonData.setRelatedNote("");
        


//        ControllData stockHolder = new ControllData();
//        stockHolder.setContributionAmount(BigDecimal.valueOf(1000000));
//        stockHolder.setEquityRatio("33%");
//        stockHolder.setName("武当山实业有限公司");
//        creditJsonData.setStockHolders(new ArrayList<ControllData>());
//        creditJsonData.getStockHolders().add(stockHolder);

        JSON result = JSONSerializer.toJSON(creditJsonData);
        return result.toString();
    }
    
    /**
     * 申请贷款时用到的jsondata
     * @return
     */
    public static String getMoneyCreditJsonData(){
    	
    	MoneyCreditJsonData moneyCreditJsonData = new MoneyCreditJsonData();
    	moneyCreditJsonData.setLoanApplicateName("陈晓新");
    	moneyCreditJsonData.setInParty("深圳市五星科技有限公司");
    	moneyCreditJsonData.setUserOrderId("201610261000123654");
    	moneyCreditJsonData.setOrderTime("2016-10-25 00:00:00");
    	moneyCreditJsonData.setProductName("10日长隆酒店包房贷");
    	moneyCreditJsonData.setInBankAccount("招商银行");
    	moneyCreditJsonData.setInBankAccountNo("62253395446877456666");
    	moneyCreditJsonData.setDeliveryStatus("");
    	moneyCreditJsonData.setReceivingState("");
    	moneyCreditJsonData.setReceiptAddress("深圳市宝安区");
    	moneyCreditJsonData.setCode("10000000000009");
    	moneyCreditJsonData.setUnitPrice("800");
    	moneyCreditJsonData.setNumber("10");
    	moneyCreditJsonData.setOrderAmount("8000");
    	moneyCreditJsonData.setOrderType("");
    	moneyCreditJsonData.setRootInstCd("M000016");
    	moneyCreditJsonData.setLoanTerm("60");
    	
    	
    	JSON result = JSONSerializer.toJSON(moneyCreditJsonData);
        
        return result.toString();
    }

    //设计收银台如何调用运营贷来付款：经历申请贷款和放款的过程

    //搞清楚运营贷所需jsondata以及放款时效

}
