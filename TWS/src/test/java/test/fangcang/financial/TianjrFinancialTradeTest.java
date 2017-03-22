package test.fangcang.financial;

import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.enums.TradeTypeEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.bean.PayMethodConfigDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.*;
import com.fangcang.titanjr.dto.response.*;
import com.fangcang.titanjr.enums.*;
import com.fangcang.titanjr.service.*;
import com.fangcang.util.StringUtil;
import net.sf.json.JSONSerializer;
import org.junit.Before;
import org.junit.Test;
import test.fangcang.GenericTest;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class TianjrFinancialTradeTest extends GenericTest{
	
	private TitanFinancialTradeService titanFinancialTradeService= null;
	private TitanFinancialUserService titanFinancialUserService= null;
	private TitanFinancialAccountService titanFinancialAccountService = null;
	private TitanFinancialBankCardService titanFinancialBankCardService = null;
	private TitanFinancialOrganService titanFinancialOrganService=null;
	private TitanOrderService titanOrderService;
	private TitanCashierDeskService titanCashierDeskService=null;

    @Before
    public void init(){
    	titanFinancialTradeService = (TitanFinancialTradeService)cfx.getBean("titanFinancialTradeService");
    	titanFinancialUserService = (TitanFinancialUserService)cfx.getBean("titanFinancialUserService");
    	titanFinancialAccountService = (TitanFinancialAccountService)cfx.getBean("titanFinancialAccountService");
    	titanFinancialBankCardService = (TitanFinancialBankCardService)cfx.getBean("titanFinancialBankCardService");
    	titanFinancialOrganService = (TitanFinancialOrganService)cfx.getBean("titanFinancialOrganService");
    	titanOrderService = (TitanOrderService)cfx.getBean("titanOrderService");
    	titanCashierDeskService = (TitanCashierDeskService)cfx.getBean("titanCashierDeskService");
    }
    

//    @Test
    public void testPay2Merchant(){
    	try{
    		PaymentRequest paymentRequest = new PaymentRequest();
    		paymentRequest.setUserid("PM10000021");
    		paymentRequest.setBusiCode(BusiCodeEnum.MerchantOrder);
    		paymentRequest.setCharset(CharsetEnum.UTF_8);
    		paymentRequest.setMerchantcode(CommonConstant.RS_FANGCANG_CONST_ID);
    		paymentRequest.setNotifyUrl("www.fangcang.com");
    		paymentRequest.setNumber(1);
    		paymentRequest.setOrderMark(OrderMarkEnum.InsideOrder);
    		paymentRequest.setPageUrl("www.fangcang.com");
    		paymentRequest.setSignType(SignTypeEnum.MD5);
    		paymentRequest.setOperType(OperTypeEnum.Add_Order);
    		paymentRequest.setPayOrderNo("TW15121613671");
    		paymentRequest.setMerchantcode("M10000001");
    		paymentRequest.setOrdertype(OrderTypeEnum.OrderType_1);
//        	PaymentResponse paymentResponse = titanFinancialTradeService.pay2Merchant(paymentRequest);
//        	if(paymentResponse !=null){
//        		System.out.println(paymentResponse.getRechangePage());
//        	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
//    	pay2Merchant(PaymentRequest paymentRequest)
    }
    
    
    //测试免密支付设置
//    @Test
    public void testSaveAllowNoPwdPay(){
    	AllowNoPwdPayRequest allowNoPwdPayRequest = new AllowNoPwdPayRequest();
    	allowNoPwdPayRequest.setUserid("TJM10000084");
    	allowNoPwdPayRequest.setStatus("1");
    	AllowNoPwdPayResponse allowNoPwdPayResponse = titanFinancialTradeService.saveAllowNoPwdPay(allowNoPwdPayRequest);
    	System.out.println(allowNoPwdPayResponse.getReturnMessage());
    }
    
    //测试是否能免密支付
//    @Test
    public void testIsAllowNoPwdPay(){
    	JudgeAllowNoPwdPayRequest judgeAllowNoPwdPayRequest = new JudgeAllowNoPwdPayRequest();
    	judgeAllowNoPwdPayRequest.setMoney("1000");
    	judgeAllowNoPwdPayRequest.setUserid("TJM10000084");
    	AllowNoPwdPayResponse allowNoPwdPayResponse = titanFinancialTradeService.isAllowNoPwdPay(judgeAllowNoPwdPayRequest);
        if(allowNoPwdPayResponse !=null){
        	System.out.println(allowNoPwdPayResponse.isAllowNoPwdPay());
        }
    }
    
    /**
     * 获取账户的交易历史
     */
//    @Test
    public void testGetAccountHistory(){
    	AccountHistoryRequest accountHistoryRequest = new AccountHistoryRequest();
    	AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
    	accountHistoryDTO.setInaccountcode("M0000002");
    	accountHistoryDTO.setOutaccountcode("M0000001");
    	accountHistoryRequest.setAccountHistoryDTO(accountHistoryDTO);
    	AccountHistoryResponse accountHistoryResponse = titanFinancialAccountService.getAccountHistory(accountHistoryRequest);
    	if(accountHistoryResponse !=null && accountHistoryResponse.getAccountHistoryDTOList()!=null){
    		for(AccountHistoryDTO accountHistory :accountHistoryResponse.getAccountHistoryDTOList()){
    			if(accountHistory !=null){
    				System.out.println(accountHistory.getAccountcode());
    			}
    		}
    	}
    }
    
    /**
     * 测试泰坦码和泰坦账户
     */
//    @Test
    public void testCheckAccount(){
    	AccountCheckRequest accountCheckRequest = new AccountCheckRequest();
    	accountCheckRequest.setOrgName("腾讯云计算有限公司");
    	accountCheckRequest.setTitanCode("10000093");
    	AccountCheckResponse accountCheckResponse = titanFinancialAccountService.checkTitanCode(accountCheckRequest);
    	if(accountCheckResponse !=null){
    	   System.out.println(accountCheckResponse.isCheckResult());
    	}
    }
    
    /**
     * 测试账户冻结资金
     * @throws Exception
     */
//    @Test
    public void testFreezeAccountBalance() throws Exception{
    	RechargeResultConfirmRequest rechargeResultConfirmRequest = new RechargeResultConfirmRequest();
    	rechargeResultConfirmRequest.setOrderNo("2016052017121900001");
    	rechargeResultConfirmRequest.setPayAmount("30000");
    	rechargeResultConfirmRequest.setUserid("TJM10000087");
    	rechargeResultConfirmRequest.setOrderAmount("30000");
    	FreezeAccountBalanceResponse freezeAccountBalanceResponse = titanFinancialAccountService.freezeAccountBalance(rechargeResultConfirmRequest);
        if(freezeAccountBalanceResponse !=null){
        	System.out.println(freezeAccountBalanceResponse.getAuthcode());
        }
    }
    /**
     * 解冻资金
     */
//    @Test
    public void testUnFreezeAccountBalance(){
    	UnFreezeAccountBalanceRequest unFreezeAccountBalanceRequest = new UnFreezeAccountBalanceRequest();
    	unFreezeAccountBalanceRequest.setOrderNo("2016052017121900001");
    	unFreezeAccountBalanceRequest.setRequesttime(DateUtil.sdf4.format(new Date()));
    	unFreezeAccountBalanceRequest.setTfsuserid(10046);
    	unFreezeAccountBalanceRequest.setUserid("TJM10000087");
    	unFreezeAccountBalanceRequest.setRequestno(OrderGenerateService.genUnfreezeResquestNo());
    	System.out.println(OrderGenerateService.genUnfreezeResquestNo());
    	UnFreezeAccountBalanceResponse unFreezeAccountBalanceResponse = titanFinancialAccountService.unfreezeAccountBalance(unFreezeAccountBalanceRequest);
        if(unFreezeAccountBalanceResponse !=null){
        	System.out.println(unFreezeAccountBalanceResponse.isResult());
        }
    }
    
    /**
     * 测试是否有付款权限
     */
//    @Test
    public void testCheckPermission(){
    	PermissionRequest permissionRequest = new PermissionRequest();
    	permissionRequest.setFcuserid("100052");
    	//permission 1.付款权限,2.查看权限,3充值权限,4.充值和提现权限,5.理财权限,6.贷款权限,7.消息提醒权限,8.系统运营员权限
    	permissionRequest.setPermission("1");
    	CheckPermissionResponse checkPermissionResponse = titanFinancialUserService.checkUserPermission(permissionRequest);
        if(checkPermissionResponse !=null){
        	System.out.println(checkPermissionResponse.isPermission());
        }
    }
    
//    @Test
    public void testPayPassword(){
    	boolean flag = titanFinancialUserService.checkPayPassword("123456", "10046");
    	System.out.println(flag);
    }
    
    /**
     * 查询账户明细
     */
//    @Test
    public void testQueryAccountBalance(){
    	AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
    	accountBalanceRequest.setRootinstcd("M000016");
    	accountBalanceRequest.setUserid("TJM10000108");
    	AccountBalanceResponse accountBalanceResponse = titanFinancialAccountService.queryAccountBalance(accountBalanceRequest);
    	if(accountBalanceResponse !=null){
    		System.out.println(JSONSerializer.toJSON(accountBalanceResponse).toString());
    	}
    }
    
   
//    @Test
    public void testWithdraw(){
    	BalanceWithDrawRequest balanceWithDrawRequest = new BalanceWithDrawRequest();
    	balanceWithDrawRequest.setAmount("0.3");
    	balanceWithDrawRequest.setCreator("admin");
    	balanceWithDrawRequest.setOrderDate(DateUtil.sdf4.format(new Date()));
    	//balanceWithDrawRequest.setUserFee((long)0);
    	balanceWithDrawRequest.setUserId("TJM10000087");
    	balanceWithDrawRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
    	balanceWithDrawRequest.setUserorderid(OrderGenerateService.genResquestNo());
    	BalanceWithDrawResponse balaneWithDrawResponse = titanFinancialAccountService.accountBalanceWithdraw(balanceWithDrawRequest);
        if(balaneWithDrawResponse !=null){
        	System.out.println(balaneWithDrawResponse.getReturnCode());
        	System.out.println("operateStatus:"+ balaneWithDrawResponse.isOperateStatus());
        	System.out.println("resultStatus:"+ balaneWithDrawResponse.isResult());
        }
    }
    
    /**
     * 查询交易明细
     * @throws ParseException 
     */
//    @Test
    public void testQueryTradeDetail() throws ParseException{
    	TradeDetailRequest tradeDetailRequest =new TradeDetailRequest();
    	tradeDetailRequest.setUserid("TJM10000087");
    	tradeDetailRequest.setTradeTypeEnum(TradeTypeEnum.RECHARGE_RECORD);
//    	tradeDetailRequest.setStartTime(DateUtil.sdf4.parse("2016-5-25 10:11:11"));
//    	tradeDetailRequest.setBusinessordercode("H1002160506142710");
    	TradeDetailResponse tradeDetailResponse = titanFinancialTradeService.getTradeDetail(tradeDetailRequest);
    	if(tradeDetailResponse !=null){
    		 List<TransOrderDTO> transOrderDTO = tradeDetailResponse.getTransOrders().getItemList();
    		 if(transOrderDTO !=null){
    			System.out.println(JSONSerializer.toJSON(transOrderDTO));
    		 }
    	}
    	
    }
    
    /**
     * 查询交易单交易明细
     * @throws ParseException 
     */
//    @Test
    public void testQueryTradeOrderDetail() throws ParseException{
    	TradeDetailRequest tradeDetailRequest =new TradeDetailRequest();
    	tradeDetailRequest.setUserid("TJM10000087");
    	tradeDetailRequest.setTradeTypeEnum(TradeTypeEnum.TRADE_RECORD);
    	tradeDetailRequest.setStartTime(DateUtil.sdf4.parse("2016-5-25 10:11:11"));
    	tradeDetailRequest.setBusinessordercode("H1002160506142710");
    	TradeDetailResponse tradeDetailResponse = titanFinancialTradeService.getOrderTradeDetail(tradeDetailRequest);
    	if(tradeDetailResponse !=null){
    		 List<TransOrderDTO> transOrderDTO = tradeDetailResponse.getTransOrders().getItemList();
    		 if(transOrderDTO !=null){
    			System.out.println(JSONSerializer.toJSON(transOrderDTO));
    		 }
    	}
    	
    }
    
    
    //获取商家向酒店支付地址
//    @Test
    public void testGetMerchantUrl(){
    	PaymentUrlRequest paymentUrlRequest = new PaymentUrlRequest();
    	paymentUrlRequest.setMerchantcode("M10000001");
    	paymentUrlRequest.setPayOrderNo("TW15112013332");
    	paymentUrlRequest.setFcUserid("23298");
    	paymentUrlRequest.setPaySource("2");
    	paymentUrlRequest.setEscrowedDate(DateUtil.sdf.format(DateUtil.getEscrowedDate()));
    	paymentUrlRequest.setIsEscrowed("0");
//    	paymentUrlRequest.setRecieveMerchantCode("M10000002");
    	 
    }
    
    //联盟分销商向联盟供应商付款
//    @Test
    public void testGetMerchantLineUrl(){
    	PaymentUrlRequest paymentUrlRequest = new PaymentUrlRequest();
    	paymentUrlRequest.setMerchantcode("M10000001");
    	paymentUrlRequest.setPayOrderNo("TW15103012513");
    	paymentUrlRequest.setFcUserid("23415");
    	
    	paymentUrlRequest.setPaySource("2");
    	paymentUrlRequest.setEscrowedDate(DateUtil.sdf.format(DateUtil.getEscrowedDate()));
    	paymentUrlRequest.setIsEscrowed("0");//1.不冻结，0.担保支付
    	paymentUrlRequest.setRecieveMerchantCode("M10000002");
    }
    
    //获取GDP支付地址
//    @Test
    public void testGetPaymentUrl() throws UnsupportedEncodingException{
    	PaymentUrlRequest paymentUrlRequest = new PaymentUrlRequest();
    	paymentUrlRequest.setPayOrderNo("H0147160902194350");
    	paymentUrlRequest.setPaySource("1");
    	paymentUrlRequest.setEscrowedDate(DateUtil.sdf.format(DateUtil.getEscrowedDate()));
    	paymentUrlRequest.setIsEscrowed("1");
    	paymentUrlRequest.setRecieveMerchantCode("M10000002");
    	paymentUrlRequest.setNotifyUrl("http//:192.168.1.10/TFS");
    	paymentUrlRequest.setBusinessOrderCode("1234568790");
    	paymentUrlRequest.setOperater("中国人 非常好 ");
    }
    //测试绑定银行卡0
//    @Test
    public void bindBankCard(){
    	CusBankCardBindRequest bankCardBindRequest = new CusBankCardBindRequest();
//    	bankCardBindRequest.setUserid("TJM10000087");								// 用户ID  唯一标识
//    	bankCardBindRequest.setUsertype("1");								// 用户类型(1：商户，2：普通用户)
//    	bankCardBindRequest.setProductid("P000070");						// 产品号
//    	bankCardBindRequest.setAccountnumber("6222021817001210192");						// 银行卡账号
//    	bankCardBindRequest.setAccounttypeid("00");							// 账号类型 00银行卡，01存折，02信用卡。不填默认为银行卡00。
//    	bankCardBindRequest.setBankheadname("中国工商银行");							// 开户行总行名称
//    	bankCardBindRequest.setCurrency("CNY");								// 币种（CNY）
//    	bankCardBindRequest.setReq_sn("" + (new Date()).getTime());								// 交易批次号 类型：C(40) 取值：当前系统毫秒数
//    	bankCardBindRequest.setSubmit_time("20160530162356");							// 提交时间yyyyMMddHHmmss
//    	bankCardBindRequest.setAccountpurpose(BankCardEnum.BankCardPurposeEnum.OTHER_CARD.getKey());							// 账户目的(1:结算卡，2：其他卡, 3：提现卡,4:结算提现一体卡)
//    	bankCardBindRequest.setAccountproperty("2");						// 账户属性（1：对公，2：对私）
//    	bankCardBindRequest.setCertificatenumnumber("411381196802185622");					// 证件号
//    	bankCardBindRequest.setCertificatetype("0");						// 开户证件类型0：身份证,1: 户口簿，2：护照,3.军官证,4.士兵证，5.
//    	bankCardBindRequest.setAccount_name("张三");							// 账号名 银行卡或存折上的所有人姓名。
//    	bankCardBindRequest.setBank_code("105");
    	CusBankCardBindResponse cusBankCardBindResponse = titanFinancialBankCardService.bankCardBind(bankCardBindRequest);
    	if(cusBankCardBindResponse !=null){
    		System.out.println(cusBankCardBindResponse.getReturnCode());
    	}
    }
    
    //解绑银行卡
//    @Test
    public void deleteBankCard(){
    	DeleteBindBankRequest deleteBindBankRequest = new DeleteBindBankRequest();
    	deleteBindBankRequest.setAccountnumber("6222021817001210092");
    	deleteBindBankRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
    	deleteBindBankRequest.setUserid("TJM10000087");
    	deleteBindBankRequest.setUsertype("1");
    	DeleteBindBankResponse deleteBindBankResponse = titanFinancialBankCardService.deleteBindBank(deleteBindBankRequest);
    	if(deleteBindBankResponse !=null){
    		System.out.println(deleteBindBankResponse.getReturnCode());
    	}
    }
    
    //查询绑定银行卡信息
//    @Test
    public void QueryBindBankCard(){
    	BankCardBindInfoRequest bankCardBindInfoRequest = new BankCardBindInfoRequest();
    	bankCardBindInfoRequest.setObjorlist(CommonConstant.ALLCARD);
    	bankCardBindInfoRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
    	bankCardBindInfoRequest.setUsertype(CommonConstant.ENTERPRISE);
    	bankCardBindInfoRequest.setUserid("TJM10000087");
    	QueryBankCardBindInfoResponse queryBankCardBindInfoResponse = titanFinancialBankCardService.getBankCardBindInfo(bankCardBindInfoRequest);
        if(queryBankCardBindInfoResponse !=null){
    		System.out.println(JSONSerializer.toJSON(queryBankCardBindInfoResponse));
        }
    }
    
    @Test
    public void testCallBack() throws Exception{
    	TransOrderDTO transOrderDTO = new TransOrderDTO();
    	
    	
      	transOrderDTO.setPayorderno("BA092159");
    	transOrderDTO.setBusinessordercode("1000000000040871");
    	transOrderDTO.setTradeamount((long)2);
    	transOrderDTO.setMerchantcode("M10082926");
    	transOrderDTO.setCreator("测试(meimei000)");
    	transOrderDTO.setUserorderid("TJO161023004819378"); 	
    	transOrderDTO.setNotifyUrl("http://172.16.21.15:19010/GDP/fcjr_pay.shtml");
		try {
			titanFinancialTradeService.confirmFinance(transOrderDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    //@Test
    public void testConfirmData() throws Exception{
    	TransOrderDTO transOrderDTO = new TransOrderDTO();
    	transOrderDTO.setNotifyUrl("http://192.168.0.90:8088/titanjr-pay-app/quickPayment/http.action");
    	transOrderDTO.setBusinessordercode("1032353636");
    	transOrderDTO.setPayorderno("秀月");
    	transOrderDTO.setUserorderid("422423154");
    	titanFinancialTradeService.confirmFinance(transOrderDTO);
	}
    
//    @Test
    public void testGDPOrderDTO(){
    	PaymentUrlRequest paymentUrlRequest = new PaymentUrlRequest();
    	paymentUrlRequest.setPayOrderNo("H0141160727185050");
    }
    
//    @Tenst
    public void getSign(){
    	RechargeResultConfirmRequest rechargeResultConfirmRequest = new RechargeResultConfirmRequest();
    }
    
//    @Test
    public void repairTransferOrder(){
    }
    
//    @Test
    public void testNewTitanOrder(){
    	TitanOrderRequest titanOrderRequest =new TitanOrderRequest();
    	titanOrderRequest.setAmount("100");
    	titanOrderRequest.setGoodsDetail("喜马拉雅国际大酒店");
    	titanOrderRequest.setEscrowedDate("2016-08-23");
    	titanOrderRequest.setGoodsId("TW16010813732");
    	titanOrderRequest.setName("张三");
    	titanOrderRequest.setNotify("www.fangcang.com");
    	titanOrderRequest.setPayerType(PayerTypeEnum.SUPPLY_FINACIAL.key);
    	titanOrderRequest.setUserId("33535");
    	
    	titanFinancialTradeService.saveTitanTransOrder(titanOrderRequest);
    }
    
//    @Test
   public void testIsFinanceAccount(){
	   OrgBindInfo orgBindInfo = new OrgBindInfo();
	   orgBindInfo.setMerchantCode("M10021509");
	   orgBindInfo = titanFinancialOrganService.queryActiveOrgBindInfo(orgBindInfo);
	   if(StringUtil.isValidString(orgBindInfo.getMerchantCode())){
		   System.out.println(orgBindInfo.getMerchantCode());
	   }
   }
    
//    @Test
    public void testCurrentTheme(){
    	TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setUserorderid("TJO160905160115853");
		TransOrderDTO transOrderDTO = titanOrderService
				.queryTransOrderDTO(transOrderRequest);
		System.out.println(transOrderDTO.getMerchantcode());
    }
    /**
     * 测试整个支付流程  基础数据  userid :TJM10000087  orgname:腾讯云计算有限公司  泰坦码:10000093
     * 1.查询 tfsuserid： 10046  是否有付款权限  通过 pass
     * 2.传入财务订单号和商家编号进行下单操作 M10000001  TW16031615073  拿回单号：2016051620063300001 pass
     * 3. 设置支付密码   pass  
     * 4.验证支付密码  pass
     * 5.验证泰坦码和账户是否对应 pass
     * 6.获取交易历史 TW15101611231   设定TW15101611231向PM10000021付款  待验证
     * 7.充值 pass
     * 8.转账 pass
     * 9.冻结  pass
     * 
     */
    
//    @Test
    public void confirmOrderStatus(){
     titanOrderService.confirmOrderStatus("2016102716242500001");
	}
    
//    @Test
    public void initCashDesk() throws Exception{
    	CashierDeskInitRequest cashierDeskInitRequest = new CashierDeskInitRequest();
    	cashierDeskInitRequest.setConstId("M000016");
    	cashierDeskInitRequest.setUserId("TJM10000096");
    	titanCashierDeskService.initCashierDesk(cashierDeskInitRequest);
    }
    
}
