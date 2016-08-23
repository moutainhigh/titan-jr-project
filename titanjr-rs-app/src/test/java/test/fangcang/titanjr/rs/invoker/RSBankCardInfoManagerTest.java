package test.fangcang.titanjr.rs.invoker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONSerializer;

import org.junit.Before;
import org.junit.Test;

import com.fangcang.titanjr.rs.manager.RSAccTradeManager;
import com.fangcang.titanjr.rs.manager.RSBankCardInfoManager;
import com.fangcang.titanjr.rs.request.AccountBalanceQueryRequest;
import com.fangcang.titanjr.rs.request.BankCardBindCheckRequest;
import com.fangcang.titanjr.rs.request.BankCardBindRequest;
import com.fangcang.titanjr.rs.request.BankCardQueryRequest;
import com.fangcang.titanjr.rs.request.DeletePersonCardRequest;
import com.fangcang.titanjr.rs.request.InvalidPubCardModifyRequest;
import com.fangcang.titanjr.rs.request.WithDrawCardModifyRequest;
import com.fangcang.titanjr.rs.response.AccountBalanceQueryResponse;
import com.fangcang.titanjr.rs.response.BankCardBindCheckResponse;
import com.fangcang.titanjr.rs.response.BankCardBindResponse;
import com.fangcang.titanjr.rs.response.BankCardQueryResponse;
import com.fangcang.titanjr.rs.response.DeletePersonCardResponse;
import com.fangcang.titanjr.rs.response.InvalidPubCardModifyResponse;
import com.fangcang.titanjr.rs.response.WithDrawCardModifyResponse;

import test.fangcang.titanjr.rs.base.GenericTest;

public class RSBankCardInfoManagerTest extends GenericTest {

	private static RSBankCardInfoManager rsBankCardInfoManager = null;

	@Before
	public void init() {
		rsBankCardInfoManager = (RSBankCardInfoManager) cfx.getBean("rsBankCardInfoManager");
	}
	
	//绑定银行卡
//  @Test 
  public void testbindBankCard() throws ParseException{
	    BankCardBindRequest req= new BankCardBindRequest();
    	req.setUserid("TJM10000108");								// 用户ID  唯一标识
		req.setUsertype("1");								// 用户类型(1：商户，2：普通用户)
		req.setConstid("M000016");							// 机构码
		req.setProductid("P000070");						// 产品号
		req.setAccountnumber("6222021817003110652");						// 银行卡账号
		req.setAccounttypeid("00");							// 账号类型 00银行卡，01存折，02信用卡。不填默认为银行卡00。
		req.setBankheadname("中国工商银行");							// 开户行总行名称
		req.setCurrency("CNY");								// 币种（CNY）
		req.setReq_sn("" + (new Date()).getTime());								// 交易批次号 类型：C(40) 取值：当前系统毫秒数
		req.setSubmit_time("20160530162356");							// 提交时间yyyyMMddHHmmss
		req.setAccountpurpose("3");							// 账户目的(1:结算卡，2：其他卡, 3：提现卡,4:结算提现一体卡)
		req.setAccountproperty("2");						// 账户属性（1：对公，2：对私）
		req.setCertificatenumnumber("411381196802185123");					// 证件号
		req.setCertificatetype("0");						// 开户证件类型0：身份证,1: 户口簿，2：护照,3.军官证,4.士兵证，5.
		req.setAccount_name("张三");							// 账号名 银行卡或存折上的所有人姓名。
		req.setBank_code("105");							// 银行代码总行  通过接口"ruixue.wheatfield.bankn.query" 查询
//		req.setBank_branch("105584000503");							// 开户行支行号
//		req.setBank_province("5800");						// 开户行所在省
//		req.setBank_city("5840");							// 开户行所在市
		// 非必输参数
		req.setRole("");									//角色号
		req.setBankbranchname("");							//开户行支行名称
		req.setOpenaccountdate(new SimpleDateFormat("yyyyMMddHHmmss").
				parse("20150519170900"));					//开户日期（yyyy-MM-dd）
		req.setOpenaccountdescription("");					//账号用途
		req.setBindid("");									//协议ID，若商户不填写，通联自动生成
		req.setRelatid("");									//商户关联ID
		req.setRelatedcard("");								//关联卡号
		req.setTel("");										//手机号，小灵通号
		req.setMerrem("");									//商户保留信息
		req.setRemark("");									//备注
		req.setReferuserid("");								//第三方用户ID	
		BankCardBindResponse response = rsBankCardInfoManager.bindBankCard(req);
		System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("------------------"+response.getOperateStatus());
  }
  
//查询所绑定银行卡
  @Test 
  public void queryBindCard(){
	  BankCardQueryRequest req = new BankCardQueryRequest();
	  req.setUserid("TJM10000010");						// 用户ID
	  req.setUsertype("1");						// 用户类型(1：商户，2：普通用户)
	  req.setConstid("M000016");					// 机构码
	  req.setProductid("P000070");				// 产品号
	  req.setObjorlist("2");						// 查询种类（1：结算卡，2：所有绑定卡）
	  // 非必输参数
	  // req.setReferuserid("");
	  req.setRole("");							// 角色号
	  BankCardQueryResponse response = rsBankCardInfoManager.queryBindCard(req);
	  System.out.print("1------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
	  if(response.getBankCardInfoList() !=null){
		  System.out.println("1------------------"+JSONSerializer.toJSON(response).toString());//response.getBankCardInfoList().get(0).getAccount_number());
		  System.out.println("1------------------"+response.getBankCardInfoList().get(0).getAccountid());
	  }
      System.out.print("1------------------"+response.getOperateStatus());
  }
  
  //检查绑定银行卡的状态，如果该银行卡未被绑定，则可以绑卡(检查该用户是否可以绑定该张银行卡)
//  @Test
  public void testcheckBindCardBindStatus(){
	  BankCardBindCheckRequest req = new BankCardBindCheckRequest();
	  req.setUserid("PP10000031");								// 用户ID
	  req.setUsertype("2");								// 用户类型
	  req.setConstid("M000016");							// 机构码
	  req.setProductid("P000070");						// 产品号
	  req.setAccountnumber("6222807200881006910");						// 银行卡号
		// 非必输项
	  req.setRole("");
	  BankCardBindCheckResponse response = rsBankCardInfoManager.checkBindCardBindStatus(req);
	  System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
      System.out.print("------------------"+response.getOperateStatus());
  }
  
  //未成功
//  @Test
  public void testWithDrawCardModifyResponse(){
	  WithDrawCardModifyRequest req = new WithDrawCardModifyRequest();
	  req.setUserid("TJM10000108");								// 用户ID
	  req.setUsertype("1");								// 用户类型(1：商户，2：普通用户)
	  req.setConstid("M000016");							// 机构码
	  req.setProductid("P000070");						// 产品号
	  req.setAccountnumber("6222021817003110652");						// 银行卡号：将要修改成提现卡的卡号
		// 非必输参数
	  req.setRole("");
	  WithDrawCardModifyResponse response = rsBankCardInfoManager.modityWithDrawCard(req);
	  System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
      System.out.print("------------------"+response.getOperateStatus()); 
  }
  
  //有点问题
//  @Test
  public void testmodifyInvalidPublicCard(){
	  InvalidPubCardModifyRequest req = new InvalidPubCardModifyRequest();
	    req.setAccountid("4842");							//信息主键  查询绑卡信息
		req.setUserid("PM10000028");							//用户id		
		req.setUsertype("1");							//用户类型(1：商户，2：普通用户)
		req.setCertificatenumber("422801199201025889");				//证件号
		req.setCertificatetype("0");					//证件类型0：身份证,1: 户口簿，2：护照,3.军官证,4.士兵证，5.
														// 港澳居民来往内地通行证,6. 台湾同胞来往内地通行证,7.
														// 临时身份证,8. 外国人居留证,9. 警官证, X.其他证件
		req.setConstid("M000016");						//机构码
		req.setProductid("P000070");					//产品号
		req.setHankheadname("");						//开户总行名称
		req.setAccountnumber("");					//账号			
		req.setAccountrealname("");					//持卡人真实姓名
		req.setBankcity("");							//开户行所在市
		req.setBankhead("");							//开户总行
		req.setHankbranch("");						//开户支行
		req.setBankprovinec("");						//开户行所在省
		
		//非必填
		req.setRole("");						//角色			
		req.setHankbranchname("");				//开户行名称
		InvalidPubCardModifyResponse response = rsBankCardInfoManager.modifyInvalidPublicCard(req);
		System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
	    System.out.print("------------------"+response.getOperateStatus()); 
  }
  //成功
//  @Test
  public void testdeletePersonCard(){
	  DeletePersonCardRequest req = new DeletePersonCardRequest();
	  req.setUserid("TJM10000108");								// 用户ID
	  req.setUsertype("1");								// 用户类型(1：商户，2：普通用户)
	  req.setConstid("M000016");							// 机构码
	  req.setProductid("P000070");						// 产品号
	  req.setAccountnumber("6222021821102310265");						// 银行卡号
		// 非必输参数
	  req.setRole("");			
	  DeletePersonCardResponse response = rsBankCardInfoManager.deletePersonCard(req);
	  System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
	  System.out.print("------------------"+response.getOperateStatus()); 
  }
  
  
}
