package test.fangcang.titanjr.rs.invoker.loancredit;

import com.Rop.api.DefaultRopClient;
import com.Rop.api.request.*;
import com.Rop.api.response.*;
import com.fangcang.util.DateUtil;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import test.fangcang.titanjr.rs.invoker.loancredit.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zhaoshan on 2016/10/9.
 */
public class LoanDemoTest {

    private static String ropUrl = "https://testapi.open.ruixuesoft.com:30005/ropapi";
    private static String appKey = "F1A95B5E-3485-4CEB-8036-F2B9EC53EF65";
    private static String appSecret = "8B6E8EEF-48CC-4CCF-94C6-55C4AA2FE9F2";

    static DefaultRopClient ropClient = new DefaultRopClient(ropUrl, appKey,
            appSecret, "xml");
    //文件
    //上传公钥：
    String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWYa7Oj4e0we9Tg9kJLjyNS3rLzlo/8nHp7Sb+oEfgnK/1gzH0OMlp8Hf09WyNk3T64UZtYPd5KwCVWMoZyK3jQwttHr+3cJ8335B4NzSwWjBfRJAf3bQPw0iZT+uoLF5+L7tbIK0Ql4bdDX+io1TkCagRU6XASrG7reXa3yc45wIDAQAB";
    //下载私钥：
    String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJZhrs6Ph7TB71OD2QkuPI1LesvOWj/ycentJv6gR+Ccr/WDMfQ4yWnwd/T1bI2TdPrhRm1g93krAJVYyhnIreNDC20ev7dwnzffkHg3NLBaMF9EkB/dtA/DSJlP66gsXn4vu1sgrRCXht0Nf6KjVOQJqBFTpcBKsbut5drfJzjnAgMBAAECgYBtzcOjJeBUFutWUsZt0qn6DawLFpCFctTElxpK/+Ob2S0OGo6mIbTHuwMMBQwPUCUxbr5K7WuluMWDR0LCkuqMEQHHhJNVTknfVpwYcy5kGBbCGEywhgnfLlzIEfdeAiJ0I+Kfrzxc+QwvU/ecNPGhNhagbd8cHoI61/5EEf8JEQJBAMZ0uysxlMiEnc5QCsFfNVNkwsGhzwSvT3+xoKiLhM389Bxz14TEF9OKD241Lz9xoqGP9tHpDFeX/eJm+brksD8CQQDB/GrMWnETJ/kHEv37PZsiCtE6LQluMtqNIIwzSiliaYfF6xmcSMX6gBKS7DATu9lBJipJKTYWv8tyOWx6nU1ZAkAuxdhPJ9JfKBJhS7AdPyk8TGUyacZ23jKob97jmm5kdhe6lPrYibbr3oAgg1xtYYTo+xs7AegsxN/LemWlTLzVAkEAl1bPFzjkCLbhwJQfk7ffZLKdws5KEjAYc4vV9VnBaaa9Jzqgk13vHtx2ISuk4nBmMT6ONN+y9BKTrTyBljNMmQJAHmS2M2ZfvrZRYNfKyi/de2tAk/kTNX4rB62Uqwz6Z3+VzcDNdJpey/RNwd0QvC1O4OKJY3gDGkyO/YI4HJkr+w==";

    public static void main(String[] args) {
        String session = "1460355562856409835";
        String strMsg = null;

//        文件上传API接口 "ruixue.fs.file.upload";
//        strMsg = doFsFileUpload(session);
//        已上传文件下载地址获取 "ruixue.fs.fileurl.get";
//        strMsg = doFsFileUrlGet(session);
        /*ruixue.wheatfield.oprsystem.credit.company 机构授信申请接口*/
//        strMsg = doWheatfieldOprsystemCreditCompany(session);

        /*ruixue.wheatfield.order.mixservice.creditmerchantinfoquery 机构授信信息查询*/
//        strMsg = doWheatfieldOrderMixserviceCreditmerchantinfoquery(session);
//        System.out.println(strMsg);

    }

    /*************************授信申请融数所用接口*******************************/
    /* ruixue.fs.file.upload 测试文件上传 （第4步） */
    private static String doFsFileUpload(String session) {
        String strError = null;
        try {
            FsFileUploadRequest req = new FsFileUploadRequest();
            req.setBatch("");
            req.setPath("d:/temp.zip");
            req.setType(73);
            req.setInvoice_date(com.fangcang.util.DateUtil.dateFormat(new Date(),"yyyy-MM-dd"));
            FsFileUploadResponse rsp = ropClient.execute(req,session);
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

    /* ruixue.fs.fileurl.get 测试文件下载地址获取  */
    private static String doFsFileUrlGet(String session) {
        String strError = null;
        try {
            FsFileurlGetRequest req = new FsFileurlGetRequest();
            req.setUrl_key("af165cd4-4434-49c8-980f-7a652471c7d7");
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
            req.setAddress("深圳市福田区创业花园");										//企业地址
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
            req.setRatetemplrate("12548");					//费率模板 为融数的费率模板id
//			req.setJsondata("{\"propertyLastYear\":\"10000\",\"debtLastYear\":\"10000\",\"averageSalary\":\"100\"}");
            req.setJsondata("{" + "'propertyLastYear':'10000', "//拯救大兵测试机构082202
                    + "'debtLastYear':'10000',"
                    + "'averageSalary':'100',"
                    + "'areaClassroom':'1000',"
                    + "'frequencyLastYear':'100000',"
                    + "'revenueLastYear':'10000',"
                    + "'foundation':'2015-08-24 12:12:12'}");
            //申请明细信息json串 如果为空 请传 {}
            req.setUrlkey("1212");										//授信申请资料上传urlkey

            //可选
            req.setUserrelateid("");					//关联用户id
            req.setRemark("");							//备注
            WheatfieldOrderMixserviceCreditapplicationResponse rsp = ropClient.execute(req,
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

    /*ruixue.wheatfield.order.service.agreementconfirm 应收账款申请协议确认（第7步）*/
    private static String doWheatfieldOrderServiceAgreementconfirm(String session) {
        String strError = null;
        try {
            WheatfieldOrderServiceAgreementconfirmRequest req = new WheatfieldOrderServiceAgreementconfirmRequest();
            req.setUrlkeya("a");					//分期付款培训协议(userflag 为1时必须)
            req.setUrlkeyb("b");					//应收账款融资申请书(userflag 为1时必须)
            req.setUrlkeyc("c");					//应收账款债权转让通知书回执(userflag 为1时必须)
            req.setUrlkeyd("d");					//应收账款债权转让通知书(userflag 为1时必须)
            req.setUserorderid("xxx");		//应收账款申请商户流水   是贷款申请的userorderid
            req.setRootinstcd("M000016");			//机构号
            req.setUserid("bbzx_xy_14");			//用户id
            req.setUserflag("1");					//用户标识 1学生 2机构
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
            req.setUserid("xxx");										//申请人id
            req.setUserorderid("xxx");							//交易流水
            req.setAmount("100");												//申请金额
            req.setProductid("P000070");										//产品id
            req.setRootinstcd("M000016");										//机构码
            req.setUserrelateid("xxx");									//申请的机构
            req.setUsername("xxx");											//申请人姓名
            req.setRequestdate("2016-07-27 12:12:12");							//申请时间
            req.setRatetemplate("xxx");							//费率模板
//			req.setJsondata("{\"premiumWay\":\"RA201607191100017\",\"loanApplySource\":\"0\",\"courseOpenTime\":\"20160823\",\"coursePeriod\":\"1500\"}");
            //申请明细信息json串
            req.setJsondata("{" + "'corporation':'拯救大兵测试机构082305', "//拯救大兵测试机构082202
                    + "'courseName':'英语',"
                    + "'coursePrice':'100',"
                    + "'loanApplySource':'0',"
                    + "'amount':'100',"
                    + "'premiumWay':'RA201404060000001',"
                    + "'corporationName':'拯救大兵测试机构082202',"
                    + "'corporationAddress':'华尔街地址：酒仙桥路',"
                    + "'corporationContact':'010-88888888',"
                    + "'primaryContactName':'第一联系人pipipapa酱',"
                    + "'primaryContactPhone':'13881061020',"
                    + "'primaryContactRelation':'夫妻',"
                    + "'secondaryContactName':'第二联系人papi酱',"
                    + "'secondaryContactPhone':'13991061020',"
                    + "'applicateName':'',"
                    + "'applicateCardId':'',"
                    + "'applyLoanSource':'3',"// 贷款申请来源  0 pc 1 ios 2 安卓 3 未知
                    + "'applyadviser':'课程顾问',"
                    + "'applyadvisertelnumber':'课程顾问电话',"
                    + "'gps':'gps经纬度',"
                    + "'validityStart':'2016-08-03',"//身份证有效期起始
                    + "'validityEnd':'2026-08-02',"//身份证有效期截止
                    + "'classInfo':' 班级信息',"
                    + "'dogId':'456京东号',"
                    + "'catId':' 123宝号',"
//					+ "'courseOpenTime':'20160823',"//开课时间
//					+ "'coursePeriod':'1500',"//开课时长
                    + "'QQ':'179854857',"
                    + "'secondaryContactRelation':'父女'}");//申请明细信息json串
            req.setUrlkey("123");												//贷款申请资料上传urlkey

            //可选
            req.setReqesttime("");												//申请期限
            req.setRemark("");													//备注
            WheatfieldOrderServiceNewloanapplyResponse rsp = ropClient.execute(req,
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

    //提供接受推送结果的接口（接受授信申请：终审通过31，开通通过41）（接受贷款申请：批贷通过31，放款通过41）

    //测试构造授信申请所需json串
    public  static void testGetCreditJsonData(){
        CreditJsonData creditJsonData = new CreditJsonData();
        BusinessPlace businessPlace = new BusinessPlace();
        businessPlace.setCoveredArea("150");
        businessPlace.setLeaseAddress("向荣大厦");
        businessPlace.setLeaseTime("2");
        businessPlace.setPayMethod(3);
        businessPlace.setPropertyType(2);
        businessPlace.setRemark("租赁");
        businessPlace.setRentAmount(BigDecimal.valueOf(15000d));
        creditJsonData.setBusinessPlace(businessPlace);

        CompanyGuarantee companyGuarantee = new CompanyGuarantee();
        companyGuarantee.setBusinessLicense("4403900847984003");
        companyGuarantee.setCompanyName("房仓测试旅行社");
        companyGuarantee.setContactName("David");
        companyGuarantee.setContactPhone("18600000000");
        companyGuarantee.setEnterpriseScale("500");
        companyGuarantee.setFoundDate(DateUtil.dateFormat(new Date(), "yyyy-MM-dd"));
        companyGuarantee.setLegalPersonCertificateNumber("450231198006185623");
        companyGuarantee.setLegalPersonCertificateType(1);
        companyGuarantee.setLegalPersonName("张三丰");
        companyGuarantee.setOfficeAddress("向荣大厦");
        companyGuarantee.setOrgCodeCertificate("OrgCode1234");
        companyGuarantee.setRegisterAccount("zhangsanfeng");
        companyGuarantee.setRegisterAddress("深圳福田");
        companyGuarantee.setRegisterDate(DateUtil.dateFormat(new Date(), "yyyy-MM-dd"));
        companyGuarantee.setTaxRegisterCode("001TaxCode002");
        creditJsonData.setCompanyGuarantee(companyGuarantee);

        PersonGuarantee personGuarantee = new PersonGuarantee();
        personGuarantee.setOfficeAddress("向荣大厦");
        personGuarantee.setCarBrand("宝马");
        personGuarantee.setCarPropertyType(1);//"有车无贷"
        personGuarantee.setCarPurchaseTime("2016-06-04");
        personGuarantee.setCurrentLiveAddress("福田");
        personGuarantee.setFirstContactName("张三1");
        personGuarantee.setFirstContactPhone("13100000000");
        personGuarantee.setGraduateSchool("深圳大学");
        personGuarantee.setHaveChildren(0);//无子女
        personGuarantee.setHighestEducation(2);//硕士
        personGuarantee.setHousePropertyType(1);//有房无贷
        personGuarantee.setIndustry("互联网");
        personGuarantee.setMarriageStatus(1);//已婚
        personGuarantee.setMobileNumber("18100000000");
        personGuarantee.setNationalIdentityNumber("62032198111062206");
        personGuarantee.setNativePlace("广西");
        personGuarantee.setOtherProperty("股票基金");
        personGuarantee.setPersonName("张三丰");
        personGuarantee.setPresentOccupation("总经理");
        personGuarantee.setPropertyRemark("所有资产如上");
        personGuarantee.setRelationToGuarantee1(1);//同事
        personGuarantee.setRelationToGuarantee2(4);//父母
        personGuarantee.setSecondContactName("张三2");
        personGuarantee.setSecondContactPhone("13200000000");
        personGuarantee.setWorkCompany("五百强平安");
        personGuarantee.setWorkTelephone("075512345678");
        personGuarantee.setYearsOfWorking("15年以上");
        creditJsonData.setPersonGuarantee(personGuarantee);


        CooperativeEnterprise cooperativeEnterprise = new CooperativeEnterprise();
        cooperativeEnterprise.setCompanyName("XY国际旅行社");
        cooperativeEnterprise.setCooperativeType(1);//f分销商
        cooperativeEnterprise.setCooperativeYears("3");
        cooperativeEnterprise.setSettlementMethod(1);//"月结"
        cooperativeEnterprise.setYearlyTradeAmount(BigDecimal.valueOf(15600));
        cooperativeEnterprise.setYearlyTradeRatio("28%");
        creditJsonData.setCooperativeEnterprises(new ArrayList<CooperativeEnterprise>());
        creditJsonData.getCooperativeEnterprises().add(cooperativeEnterprise);

        MainBusiness mainBusiness = new MainBusiness();
        mainBusiness.setBusinessName("酒店预订");
        mainBusiness.setYearlySaleAmount(BigDecimal.valueOf(56000));
        mainBusiness.setYearlySaleRatio("20%");
        creditJsonData.setMainBusinesses(new ArrayList<MainBusiness>());
        creditJsonData.getMainBusinesses().add(mainBusiness);

        StockHolder stockHolder = new StockHolder();
        stockHolder.setContributionAmount(BigDecimal.valueOf(1000000));
        stockHolder.setEquityRatio("33%");
        stockHolder.setName("武当山实业有限公司");
        creditJsonData.setStockHolders(new ArrayList<StockHolder>());
        creditJsonData.getStockHolders().add(stockHolder);

        JSON result = JSONSerializer.toJSON(creditJsonData);
        System.out.println(result.toString());
        String testJson = result.toString();
    }

    //设计收银台如何调用运营贷来付款：经历申请贷款和放款的过程

    //搞清楚运营贷所需jsondata以及放款时效

}
