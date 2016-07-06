package test.fangcang.titanjr.rs.invoker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import com.Rop.api.request.WheatfieldAccountCheckRequest;
import com.Rop.api.response.WheatfieldAccountCheckResponse;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.RequestValidationUtil;
import com.fangcang.titanjr.rs.manager.RSAccTradeManager;
import com.fangcang.titanjr.rs.manager.RSAccountManager;
import com.fangcang.titanjr.rs.manager.RSOrganizationManager;
import com.fangcang.titanjr.rs.request.AccountBalanceQueryRequest;
import com.fangcang.titanjr.rs.request.AccountFlowQueryRequest;
import com.fangcang.titanjr.rs.request.AccountFreezeRequest;
import com.fangcang.titanjr.rs.request.AccountTransferRequest;
import com.fangcang.titanjr.rs.request.AccountUnFreezeRequest;
import com.fangcang.titanjr.rs.request.AccountWithDrawRequest;
import com.fangcang.titanjr.rs.request.BalanceFreezeRequest;
import com.fangcang.titanjr.rs.request.BalanceUnFreezeRequest;
import com.fangcang.titanjr.rs.request.CompOrgInfoQueryRequest;
import com.fangcang.titanjr.rs.request.CompanyOrgRegRequest;
import com.fangcang.titanjr.rs.request.CompanyOrgUpdateRequest;
import com.fangcang.titanjr.rs.request.DestoryAccountRequest;
import com.fangcang.titanjr.rs.request.OrderOperateRequest;
import com.fangcang.titanjr.rs.request.OrderTransferFlowRequest;
import com.fangcang.titanjr.rs.request.OrgStatusQueryRequest;
import com.fangcang.titanjr.rs.request.PersOrgInfoQueryRequest;
import com.fangcang.titanjr.rs.request.PersonOrgRegRequest;
import com.fangcang.titanjr.rs.request.PersonOrgUpdateRequest;
import com.fangcang.titanjr.rs.response.AccountBalanceQueryResponse;
import com.fangcang.titanjr.rs.response.AccountFlowQueryResponse;
import com.fangcang.titanjr.rs.response.AccountFreezeResponse;
import com.fangcang.titanjr.rs.response.AccountTransferResponse;
import com.fangcang.titanjr.rs.response.AccountUnFreezeResponse;
import com.fangcang.titanjr.rs.response.AccountWithDrawResponse;
import com.fangcang.titanjr.rs.response.BalanceFreezeResponse;
import com.fangcang.titanjr.rs.response.BalanceUnFreezeResponse;
import com.fangcang.titanjr.rs.response.CompOrgInfoQueryResponse;
import com.fangcang.titanjr.rs.response.CompanyOrgUpdateResponse;
import com.fangcang.titanjr.rs.response.DestoryAccountResponse;
import com.fangcang.titanjr.rs.response.OrderOperateInfo;
import com.fangcang.titanjr.rs.response.OrderOperateResponse;
import com.fangcang.titanjr.rs.response.OrderTransferFlowResponse;
import com.fangcang.titanjr.rs.response.OrgStatusQueryResponse;
import com.fangcang.titanjr.rs.response.PersOrgInfoQueryResponse;
import com.fangcang.titanjr.rs.response.PersonOrgRegResponse;
import com.fangcang.titanjr.rs.response.PersonOrgUpdateResponse;

import net.sf.json.JSONSerializer;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.HttpServletBean;

import test.fangcang.titanjr.rs.base.GenericTest;

/**
 * Created by zhaoshan on 2016/4/15.
 */
public class RSAccTradeManagerTest extends GenericTest {

    private static RSAccTradeManager rsAccTradeManager = null;

    @Before
    public void init(){
    	rsAccTradeManager = (RSAccTradeManager)cfx.getBean("rsAccTradeManager");
    }

    //账户可用余额查询
//    @Test 
    public void testqueryAccountBalance(){
    	AccountBalanceQueryRequest req= new AccountBalanceQueryRequest();
    	req.setUserid("TJM10000087");								//	用户idPP10000031 1660   PM10000021 606000
//		req.setUserid("141223100000056");								//	用户id
		req.setRootinstcd("M000016");		
		req.setProductid("P000070");
		AccountBalanceQueryResponse response = rsAccTradeManager.queryAccountBalance(req);
		System.out.println("------------------"+JSONSerializer.toJSON(response).toString());
		System.out.println("------------------"+response.getOperateStatus());
		if(response.getBalanceInfoList()!=null && response.getBalanceInfoList().size()>0){
			System.out.println("------------------"+response.getBalanceInfoList().size()+"----------"+response.getBalanceInfoList().get(0).getAmount());
		    System.out.println(response.getBalanceInfoList().get(0).getBalancefrozon());  //冻结资金：3120
		}
		
    }
    
    //转账
    @Test 
    public void testAccountBalanceTransfer(){
    	AccountTransferRequest req = new AccountTransferRequest();
    	req.setTransfertype("3");								//1:子账户转账				
		req.setConditioncode("1");								//1:落单
		req.setMerchantcode("M000016");							//转入方机构号
		req.setProductid("P000148");							//转入方产品号
		req.setUserrelateid("TJM10000087");	                    //接收方用户Id		
		req.setRequestno("TJR160630192241810");									//业务订单号
		req.setRequesttime("2016-4-12 22:22:22");				//请求时间
		req.setAmount("57900");										//金额
		req.setUserfee("0");		
		req.setUserid("141223100000056");
		req.setIntermerchantcode("M000016");					//转出方机构号
		req.setInterproductid("P000070");						//转出方产品号

	        
		AccountTransferResponse response = rsAccTradeManager.accountBalanceTransfer(req);
		System.out.println("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
    	System.out.println("------------------"+response.getRetcode()+"---"+response.getRetmsg()+"---"+response.getOrderid());
    	
    }
    
    
    
    //落单操作
//    @Test 
    public void testOperateAddOrder() throws ParseException{
    	OrderOperateRequest req= new OrderOperateRequest();
    	req.setUserid("141223100000056");								// 接入机构中设置的用户ID
		req.setConstid("M000016");							// 机构码
		req.setOrdertypeid("B1");							// 基础业务为B，扩展业务待定 M70001棉庄订金支付
		req.setProductid("P000148");						// 产品号
		req.setOpertype("3");								// 操作类型（修改：2,新增：1,取消4,查询3）
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-04-28 13:43:55"); 
		req.setOrderdate(date);						// 订单日期
		req.setOrdertime(date);						// 订单时间
		req.setUserorderid("wefrwer");							// 用户订单编号
		req.setAmount("2200");								// 订单金额（若不存在商品概念则必填）
		req.setGoodsname("香格里拉酒店");							// 商品名称
		req.setGoodsdetail("预订3天2晚，4.11入住，4.14离店");							// 商品描述
		req.setNumber(1);									// 商品数量
		req.setUnitprice("0");								// 手续费
		req.setAdjusttype(null);							// 调整类型
		req.setAdjustcontent("");						// 调整内容
		req.setUserrelateid(null);							// 关联用户id（若有第三方则必须填写）
						
		OrderOperateResponse response = rsAccTradeManager.operateOrder(req);
		System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("------------------"+response.getOperateStatus()+"----------"+response.getOrderid());
		System.out.println(JSONSerializer.toJSON(response.getOrderOperateInfoList()));
    }
    
//    修改订单
//    @Test 2016051217575800001
    public void testOperateModifyOrder(){
    	OrderOperateRequest req= new OrderOperateRequest();
    	req.setUserid("TJM10000087");								// 接入机构中设置的用户ID
		req.setConstid("M000016");							// 机构码
		req.setOrdertypeid("B1");							// 基础业务为B，扩展业务待定 M70001棉庄订金支付
		req.setProductid("P000070");						// 产品号
		req.setOpertype("2");								// 操作类型（修改：2,新增：1,取消4,查询3）
		req.setOrderdate(new Date());						// 订单日期
		req.setOrdertime(new Date());						// 订单时间
		req.setUserorderid("sfsf13652");							// 用户订单编号
		req.setAmount("1100");								// 订单金额（若不存在商品概念则必填）
		req.setGoodsname("香格里拉酒店");							// 商品名称
		req.setGoodsdetail("预订3天2晚，4.11入住，4.14离店");							// 商品描述
		req.setNumber(1);									// 商品数量
		req.setUnitprice("1100");								// 手续费
		req.setAdjusttype(null);							// 调整类型
		req.setAdjustcontent("");						// 调整内容
		req.setUserrelateid(null);							// 关联用户id（若有第三方则必须填写）
						
		OrderOperateResponse response = rsAccTradeManager.operateOrder(req);
		System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("------------------"+response.getOperateStatus()+"----------"+response.getOrderid());
    }
    
    //查询订单
//    @Test 
    public void testOperateQueryOrder(){
    	OrderOperateRequest req= new OrderOperateRequest();
    	req.setUserid("TJM10000087");								// 接入机构中设置的用户ID
		req.setConstid("M000016");							// 机构码
		req.setOrdertypeid("B1");							// 基础业务为B，扩展业务待定 M70001棉庄订金支付
		req.setProductid("P000070");						// 产品号
		req.setOpertype("3");								// 操作类型（修改：2,新增：1,取消4,查询3）
		req.setOrderdate(new Date());						// 订单日期
		req.setOrdertime(new Date());						// 订单时间
		req.setUserorderid("titanjrorder160608101256659");							// 用户订单编号
		req.setAmount("1100");								// 订单金额（若不存在商品概念则必填）
//		req.setGoodsname("香格里拉酒店");							// 商品名称
//		req.setGoodsdetail("预订3天2晚，4.11入住，4.14离店");							// 商品描述
//		req.setNumber(1);									// 商品数量
		req.setUnitprice("0");								// 手续费
		req.setAdjusttype(null);							// 调整类型
		req.setAdjustcontent("");						// 调整内容
		req.setUserrelateid(null);							// 关联用户id（若有第三方则必须填写）
						
		OrderOperateResponse response = rsAccTradeManager.operateOrder(req);
		System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("------------------"+response.getOperateStatus()+"----------"+response.getOrderid());
		if(response.getOrderOperateInfoList() !=null && response.getOrderOperateInfoList().size()>0){
			for(int i=0;i<response.getOrderOperateInfoList().size();i++){
				OrderOperateInfo orderOperateInfo = response.getOrderOperateInfoList().get(i);
				System.out.println("===========orderId"+orderOperateInfo.getOrderId());
			}
		}
		System.out.println(response.getOrderOperateInfoList().size());
    }
    
//    取消订单
//    @Test 
    public void testOperateCancelOrder(){
    	OrderOperateRequest req= new OrderOperateRequest();
    	req.setUserid("PM10000025");								// 接入机构中设置的用户ID
		req.setConstid("M000016");							// 机构码
		req.setOrdertypeid("B1");							// 基础业务为B，扩展业务待定 M70001棉庄订金支付
		req.setProductid("P000070");						// 产品号
		req.setOpertype("4");								// 操作类型（修改：2,新增：1,取消4,查询3）
		req.setOrderdate(new Date());						// 订单日期
		req.setOrdertime(new Date());						// 订单时间
		req.setUserorderid("sfsf1235");							// 用户订单编号
		req.setAmount("1100");								// 订单金额（若不存在商品概念则必填）
		req.setGoodsname("香格里拉酒店");							// 商品名称
		req.setGoodsdetail("预订3天2晚，4.11入住，4.14离店");							// 商品描述
		req.setNumber(1);									// 商品数量
		req.setUnitprice("1100");								// 手续费
		req.setAdjusttype(null);							// 调整类型
		req.setAdjustcontent("");						// 调整内容
		req.setUserrelateid(null);							// 关联用户id（若有第三方则必须填写）
						
		OrderOperateResponse response = rsAccTradeManager.operateOrder(req);
		System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("------------------"+response.getOperateStatus()+"----------"+response.getOrderid());
    }
    
    //冻结余额
//    @Test
    public void testFreezeAccountBalance(){
    	BalanceFreezeRequest req = new BalanceFreezeRequest();
    	req.setConditioncode("2");					//订单关联类型（主业务单：1 业务子单：0 无业务单：2）
		req.setAmount((long)200);						//冻结金额
		req.setUserid("PM10000021");						//用户id
		req.setFunccode("40171");					//功能编码：冻结返回授权码 为：40171
		req.setMerchantcode("M000016");				//商户编码/机构号
		req.setOrderamount((long)200);				//订单金额
		req.setStatus(1);							//状态 1：正常
		req.setUserfee((long)0);					//用户手续费
		req.setProductid("P000070");				//产品号
		req.setUseripaddress("192.168.1.96");		//用户ip地址
		req.setRequestno("1234567e12");					//请求单号			
		req.setOrderno("201sdfkflks");						//订单号
		req.setOrdercount(1);						//订单数量
		req.setUserfee((long)0);					//用户手续费			
		
		//非必输
		req.setIntermerchantcode("");				//中间商户编码
		req.setBankcode("");						//银行联行编码
		req.setBusitypeid("");						//业务类型ID
		req.setErrorcode("");						//错误编码
		req.setErrormsg("");						//错误信息
		req.setFeeamount((long)0);					//手续费金额			
		req.setOrderdate(new Date());				//订单日期 格式 yyyy-MM-dd hh:mm:ss
		req.setRequesttime(new Date());				//交易请求时间 格式 yyyy-MM-dd hh:mm:ss
		req.setOrderpackageno("");					//订单包号
		req.setPaychannelid("");					//支付渠道ID			
		req.setProfit((long)0);						//利润					
		req.setTradeflowno("");						//统一交易流水号
		req.setRemark("");							//备注
		BalanceFreezeResponse response = rsAccTradeManager.freezeAccountBalance(req);
		System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("---------------------------"+response.getAuthcode());//
    }
    
	//解冻
//    @Test
    public void testUnFreezeAccountBalance(){
    	BalanceUnFreezeRequest req = new BalanceUnFreezeRequest();
    	req.setAmount("200");						//解冻金额
		req.setRootinstcd("M000016");			//机构号			
		req.setUserid("PM10000021");					//用户id
		req.setFrozenuserorderid("1234567e12");		//冻结时上传的requestno
		req.setProductid("P000070");			//产品号
		req.setRequestno("jhsdj92");				//请求号
		req.setRequesttime("2016-05-20 13:56:55");					//请求时间 yyyy-MM-dd HH:mm:ss（请按格式传递）
		req.setAuthcode("j9w5T9MYDe");					//授权码	账户资金冻结时返回 （授权码）		
		req.setConditioncode("2");				//订单关联类型（主业务单：1 业务子单：0 无业务单：2）
		BalanceUnFreezeResponse response = rsAccTradeManager.unFreezeAccountBalance(req);
		System.out.print("------------------"+response.getRetcode()+"----------"+response.getRetmsg());
		System.out.print("------------------"+response.getOperateStatus());
    }
    
    //提现
//    @Test
    public void testAccountBalanceWithDraw(){
    	AccountWithDrawRequest req = new AccountWithDrawRequest();
    	req.setProductid("P000070");					//产品号
		req.setAmount("480");							//金额
		req.setMerchantcode("M000016");					//机构号
		req.setOrderdate("2015-04-26 14:18:55");						//订单日期 yyyy-MM-dd hh:mm:ss
		req.setUserid("TJM10000108");							//用户号
		req.setUserorderid("kmdlks23sd");						//订单号
		req.setUserfee((long)0);						//手续费
		AccountWithDrawResponse response = rsAccTradeManager.accountBalanceWithDraw(req);
		System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("------------------"+response.getOperateStatus());
    }
    
    //转账订单查询
//    @Test
    public void testTransOrderQuery(){
    	OrderTransferFlowRequest orderTransferFlowRequest = new OrderTransferFlowRequest();
    	orderTransferFlowRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
    	orderTransferFlowRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
    	orderTransferFlowRequest.setUserid("PM10000021");
    	orderTransferFlowRequest.setRequestno("TJR160615103211355");
    	OrderTransferFlowResponse orderTransferFlowResponse = rsAccTradeManager.queryOrderTranferFlow(orderTransferFlowRequest);
    	if(orderTransferFlowResponse !=null){
    		System.out.println(orderTransferFlowResponse.getOperateStatus()+"----"+orderTransferFlowResponse.getRetmsg());
    	}
    }
    
}
