package test.fangcang.financial;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.fangcang.titanjr.dto.request.RechargePageRequest;
import com.fangcang.titanjr.dto.response.TransOrderCreateResponse;

import net.sf.json.JSONSerializer;

import org.junit.Before;
import org.junit.Test;

import test.fangcang.GenericTest;

import com.fangcang.titanjr.common.enums.TradeTypeEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;
import com.fangcang.titanjr.dto.bean.BusiCodeEnum;
import com.fangcang.titanjr.dto.bean.CharsetEnum;
import com.fangcang.titanjr.dto.bean.OperTypeEnum;
import com.fangcang.titanjr.dto.bean.OrderMarkEnum;
import com.fangcang.titanjr.dto.bean.OrderTypeEnum;
import com.fangcang.titanjr.dto.bean.PayMethodConfigDTO;
import com.fangcang.titanjr.dto.bean.SignTypeEnum;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.AccountCheckRequest;
import com.fangcang.titanjr.dto.request.AccountHistoryRequest;
import com.fangcang.titanjr.dto.request.AllowNoPwdPayRequest;
import com.fangcang.titanjr.dto.request.BankCardBindInfoRequest;
import com.fangcang.titanjr.dto.request.CusBankCardBindRequest;
import com.fangcang.titanjr.dto.request.DeleteBindBankRequest;
import com.fangcang.titanjr.dto.request.FinancialOrderRequest;
import com.fangcang.titanjr.dto.request.JudgeAllowNoPwdPayRequest;
import com.fangcang.titanjr.dto.request.PayMethodConfigRequest;
import com.fangcang.titanjr.dto.request.PaymentRequest;
import com.fangcang.titanjr.dto.request.PaymentUrlRequest;
import com.fangcang.titanjr.dto.request.PermissionRequest;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.TradeDetailRequest;
import com.fangcang.titanjr.dto.request.TransferRequest;
import com.fangcang.titanjr.dto.request.UnFreezeAccountBalanceRequest;
import com.fangcang.titanjr.dto.request.BalanceWithDrawRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.AccountCheckResponse;
import com.fangcang.titanjr.dto.response.AccountHistoryResponse;
import com.fangcang.titanjr.dto.response.AllowNoPwdPayResponse;
import com.fangcang.titanjr.dto.response.CheckPermissionResponse;
import com.fangcang.titanjr.dto.response.CusBankCardBindResponse;
import com.fangcang.titanjr.dto.response.DeleteBindBankResponse;
import com.fangcang.titanjr.dto.response.FinancialOrderResponse;
import com.fangcang.titanjr.dto.response.FreezeAccountBalanceResponse;
import com.fangcang.titanjr.dto.response.PaymentUrlResponse;
import com.fangcang.titanjr.dto.response.QueryBankCardBindInfoResponse;
import com.fangcang.titanjr.dto.response.RechargeResponse;
import com.fangcang.titanjr.dto.response.TradeDetailResponse;
import com.fangcang.titanjr.dto.response.TransferResponse;
import com.fangcang.titanjr.dto.response.UnFreezeAccountBalanceResponse;
import com.fangcang.titanjr.dto.response.BalanceWithDrawResponse;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialBankCardService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanFinancialUserService;

public class TianjrFinancialTradeTest extends GenericTest{
	
	private TitanFinancialTradeService titanFinancialTradeService= null;
	private TitanFinancialUserService titanFinancialUserService= null;
	private TitanFinancialAccountService titanFinancialAccountService = null;
	private TitanFinancialBankCardService titanFinancialBankCardService = null;

    @Before
    public void init(){
    	titanFinancialTradeService = (TitanFinancialTradeService)cfx.getBean("titanFinancialTradeService");
    	titanFinancialUserService = (TitanFinancialUserService)cfx.getBean("titanFinancialUserService");
    	titanFinancialAccountService = (TitanFinancialAccountService)cfx.getBean("titanFinancialAccountService");
    	titanFinancialBankCardService = (TitanFinancialBankCardService)cfx.getBean("titanFinancialBankCardService");
    }
    

//    @Test
    public void testgetFinanceOrderData(){
    	try{
    		FinancialOrderRequest financialOrderRequest = new FinancialOrderRequest();
        	financialOrderRequest.setMerchantcode("M10000001");
        	financialOrderRequest.setOrderNo("TW14090202365");

        	FinancialOrderResponse financialOrderResponse =titanFinancialTradeService.queryFinanceOrderDetail(financialOrderRequest);
            if(financialOrderResponse !=null){
            	System.out.println(financialOrderResponse.getFinanceCode()+"----"+financialOrderResponse.getOrderCode());
            }
    	}catch(Exception e){
    		e.printStackTrace();
    	
    	}
    	
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
    
    /**
     * 测试第一次落单，
     * 必须传入的参数
     * OrderNo
     * Merchantcode
     * Userid
     * Ordertypeid
     * OperType 
     * 
     * 不是必须
     * Number
     * CreateDate
     * Adjustcontent
     * Ordertype
     * Unitprice
     * Creators
     * 测试步骤，1.用新的财务单，判断整个下单过程，2.拿已下过单且为支付的财务单测试，
     * 3拿未支付但是已过时效的单号，4.失败的单号（可以数据库修改状态），5成功的财务单
     * @throws Exception 
     */
//    @Test
    public void testAddOperate() throws Exception{
    	PaymentRequest paymentRequest = new PaymentRequest();
    	paymentRequest.setMerchantcode("M10000001");
    	paymentRequest.setPayOrderNo("TW16050616113");
    	paymentRequest.setUserid("PM10000021"); //TJM10000087
    	//是否担保支付
    	paymentRequest.setEscrowedDate("2016-05-26");
		TransOrderCreateResponse orderResponse = titanFinancialTradeService.createTitanTransOrder(paymentRequest);
    	if(orderResponse !=null){
    		System.out.println(orderResponse.getOrderNo()+"-----------------");
    	}
    }
    
    
//    public void testOperateRSTransOrder(){
//    	orderRequest
//    	titanFinancialTradeService.operateRSTransOrder(orderRequest);
//    }
    /**
     * PageUrl
     * NotifyUrl
     * OrderTime
     * OrderMark
     * SignType
     * BusiCode
     * Version
     * Charset
     */
//    @Test 
    public void testRecharge2Account() throws Exception{
    	RechargePageRequest rechargePageRequest = new RechargePageRequest();
    	rechargePageRequest.setPageUrl("http://192.168.1.96:8088/TFS/order.jsp");
    	rechargePageRequest.setNotifyUrl("www.fangcang.com");
    	rechargePageRequest.setUserid("TJM10000087");
//    	                           2016051710502500001
    	rechargePageRequest.setOrderid("2016052515452200001");
    	rechargePageRequest.setOrderExpireTime(null);
    	rechargePageRequest.setBankInfo("icbc");
    	rechargePageRequest.setUserrelateid("PM10000021");
//    	merchantNo=M000016&orderNo=2016051617183000001&orderAmount=540&payType=null&orderTime=20160516171830&signType=1&version=v1.0&key=12356780Poi)(*
    	RechargeResponse orderResponse = titanFinancialTradeService.generateRechargePage(rechargePageRequest);
    	if(orderResponse !=null){
//    		System.out.println(orderResponse.getRechargePage());
    		
    	}
    }
    
    /**
     * PageUrl
     * NotifyUrl
     * OrderTime
     * OrderMark
     * SignType
     * BusiCode
     * Version
     * Charset
     */
//    @Test 
    public void testAddOrderNoandRechargeAccount() throws Exception{
    	RechargePageRequest rechargePageRequest = new RechargePageRequest();
    	rechargePageRequest.setPageUrl("http://192.168.1.96:8088/TFS/order.jsp");
    	rechargePageRequest.setNotifyUrl("www.fangcang.com");
    	rechargePageRequest.setUserid("TJM10000087");
    	rechargePageRequest.setRechargeAmount("10000.0");             
    	rechargePageRequest.setOrderExpireTime(null);
    	rechargePageRequest.setBankInfo("icbc");
    	rechargePageRequest.setUserorderid(OrderGenerateService.genSyncUserOrderId());
    	
//    	merchantNo=M000016&orderNo=2016051617183000001&orderAmount=540&payType=null&orderTime=20160516171830&signType=1&version=v1.0&key=12356780Poi)(*
    	RechargeResponse orderResponse = titanFinancialTradeService.generateOrderNoAndRechargePage(rechargePageRequest);
    	if(orderResponse !=null){
//    		System.out.println(orderResponse.getRechargePage());
    	}
    }
    
    /**
     * userid
     * productid
     * requestno 请求号
     * merchantcode 机构编码
     * transfertype 
     * conditioncode 业务订单落单，不落单
     * requesttime
     * amount
     * userfee 手续费
     * intermerchantcode  接收方机构号
     * interproductid     接收方产品号
     * userrelateid      接收方用户id
     * @throws Exception 
     */
//    @Test
    public void testTransfer2Account() throws Exception{
    	try{
    		TransferRequest transferRequest = new TransferRequest();
        	transferRequest.setCreator("admin");
        	transferRequest.setUserid("PM10000021");										//转出的用户
        	transferRequest.setRequestno(OrderGenerateService.genResquestNo());									//业务订单号
        	transferRequest.setRequesttime(DateUtil.sdf4.format(new Date()));				//请求时间
        	transferRequest.setAmount("300000");										//金额 必须是分
        	transferRequest.setUserfee("0");									
        	transferRequest.setUserrelateid("TJM10000087");	                    //接收方用户Id
        	transferRequest.setOrderid("2016063021292900001");
        	TransferResponse transferResponse = titanFinancialTradeService.transferAccounts(transferRequest);
        	if(transferResponse !=null){
//        		transferRequest
        		System.out.println(transferResponse.getReturnCode()+"-------"+transferResponse.getReturnMessage());
        		//添加账户历史操作
        		AccountHistoryResponse accountHistoryResponse = titanFinancialAccountService.addAccountHistory(transferRequest);
        		System.out.println(transferResponse.getReturnCode()+"-------"+transferResponse.getReturnMessage());
        		System.out.println(accountHistoryResponse.getReturnCode()+"-------"+accountHistoryResponse.getReturnMessage());
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
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
    
    //测试增加账户交易历史
   // @Test
    public void testAddAccountHistory(TransferRequest transferRequest){
    	
    	AccountHistoryResponse accountHistoryResponse = titanFinancialAccountService.addAccountHistory(transferRequest);
        if(accountHistoryResponse !=null){
        	System.out.println(accountHistoryResponse.getReturnMessage());
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
    	balanceWithDrawRequest.setUserFee((long)0);
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
    
    //获取GDP支付地址
//    @Test
    public void testGetPaymentUrl(){
    	PaymentUrlRequest paymentUrlRequest = new PaymentUrlRequest();
    	paymentUrlRequest.setPayOrderNo("H0139130605060630");
    	paymentUrlRequest.setPaySource("1");
    	paymentUrlRequest.setEscrowedDate(DateUtil.sdf.format(DateUtil.getEscrowedDate()));
    	paymentUrlRequest.setIsEscrowed("1");
    	paymentUrlRequest.setRecieveMerchantCode("M10000002");
    	PaymentUrlResponse paymentUrlResponse =titanFinancialTradeService.getPaymentUrl(paymentUrlRequest);
    	if(paymentUrlResponse !=null){
    		System.out.println("-------------"+paymentUrlResponse.getUrl());
    	}
    }
    
    
    //获取商家向酒店支付地址
//    @Test
    public void testGetMerchantUrl(){
    	PaymentUrlRequest paymentUrlRequest = new PaymentUrlRequest();
    	paymentUrlRequest.setMerchantcode("M10000001");
    	paymentUrlRequest.setPayOrderNo("TW15051306188");
    	paymentUrlRequest.setFcUserid("23298");
    	paymentUrlRequest.setPaySource("2");
    	paymentUrlRequest.setEscrowedDate(DateUtil.sdf.format(DateUtil.getEscrowedDate()));
    	paymentUrlRequest.setIsEscrowed("1");
//    	paymentUrlRequest.setRecieveMerchantCode("M10000002");
    	PaymentUrlResponse paymentUrlResponse =titanFinancialTradeService.getPaymentUrl(paymentUrlRequest);
    	if(paymentUrlResponse !=null){
    		System.out.println("-------------"+paymentUrlResponse.getUrl());
    	}
    }
    
    //联盟分销商向联盟供应商付款
    @Test
    public void testGetMerchantLineUrl(){
    	PaymentUrlRequest paymentUrlRequest = new PaymentUrlRequest();
    	paymentUrlRequest.setMerchantcode("M10000001");
    	paymentUrlRequest.setPayOrderNo("TW15073008812");
    	paymentUrlRequest.setFcUserid("23298");
    	
    	paymentUrlRequest.setPaySource("2");
    	paymentUrlRequest.setEscrowedDate(DateUtil.sdf.format(DateUtil.getEscrowedDate()));
    	paymentUrlRequest.setIsEscrowed("1");
    	paymentUrlRequest.setRecieveMerchantCode("M10000002");
    	PaymentUrlResponse paymentUrlResponse =titanFinancialTradeService.getPaymentUrl(paymentUrlRequest);
    	if(paymentUrlResponse !=null){
    		System.out.println("-------------"+paymentUrlResponse.getUrl());
    	}
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
    
//    @Test
    public void testPayMethod(){
    	PayMethodConfigRequest payMethodConfigRequest = new PayMethodConfigRequest();
		payMethodConfigRequest.setUserId("TJM10000087");
		PayMethodConfigDTO payMethodConfigDTO = titanFinancialTradeService.getPayMethodConfigDTO(payMethodConfigRequest);
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
    
    
}
