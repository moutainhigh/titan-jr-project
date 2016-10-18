package test.fangcang.titanjr.rs.invoker;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.Rop.api.DefaultRopClient;
import com.Rop.api.request.*;
import com.Rop.api.response.*;
import com.ruixue.oss.common.utils.DateUtil;


public class DemoAPI {
	private static String ropUrl = "https://testapi.open.ruixuesoft.com:30005/ropapi";
	private static String appKey = "F1A95B5E-3485-4CEB-8036-F2B9EC53EF65";
	private static String appSecret = "8B6E8EEF-48CC-4CCF-94C6-55C4AA2FE9F2";

	static DefaultRopClient ropClient = new DefaultRopClient(ropUrl, appKey,
			appSecret, "xml");

	public static void main(String[] args) throws FileNotFoundException {
		String strMsg = null;
//		String session = DemoAPI.sessionGet();
		String session = "1460355562856409835";

		//查询账户状态
//		String requestType = "ruixue.wheatfield.account.check";
		//开户信息查询（企业）（支持批量模糊查询）141223100000056
//		String requestType = "ruixue.wheatfield.batchquery.company";
		//查询可用余额
//		String requestType = "ruixue.wheatfield.balance.getlist";
		//绑定银行卡信息查询 =============================com.Rop.api.ApiException: XML_PARSE_ERROR，账户不存在
//		String requestType = "ruixue.wheatfield.accountinfo.query";
		//查询省市信息
//		String requestType = "ruixue.wheatfield.city.query";
		//查询银行信息
//		String requestType = "ruixue.wheatfield.bankn.query";
		//查询账户流水
//		String requestType = "ruixue.wheatfield.finanace.entrylist.query";
		//订单基础操作，增删改
//		String requestType = "ruixue.wheatfield.order.oper"; //==生成订单的orderid   2016041114451400001
//		String requestType ="ruixue.wheatfield.order.service.withdrawservice";

		//账户整体冻结 =====输入参数失败。
//		String requestType = "ruixue.wheatfield.account.freeze";



		//企业账户更新 =====远程服务连接失败。
//		String requestType = "ruixue.wheatfield.enterprise.updatecompanyinfo";
		//个人账户开户
//		String requestType = "ruixue.wheatfield.person.accountopr";
		
//		String requestType = "ruixue.wheatfield.enterprise.entityaccountopt";
		//开户信息查询（个人）（支持批量模糊查询） === 无权限，需添加
//		String requestType = "ruixue.wheatfield.batchquery.person";
		//绑定银行卡信息接口：再绑就提示已绑定
//		String requestType = "ruixue.wheatfield.bankaccount.binding";
		//只可以删除提现卡和其他卡：返回账户不存在
//		String requestType = "ruixue.wheatfield.accountinfo.delete";
		//检查提现卡信息：存在返回false，不存在返回ture  modityWithDrawCard
//		String requestType = "ruixue.wheatfield.accountnum.ckeck";
		//单笔转账操作
//		String requestType = "ruixue.wheatfield.order.transfer";

		//查询信贷账户余额 TJM10000087
		String requestType = "ruixue.wheatfield.balance.getlist";

		if ("ruixue.wheatfield.enterprise.entityaccountopt".equals(requestType)) {
			//企业账户开户
			strMsg = doWheatfieldEnterpriseEntityaccountoptRequest(session);
		} else if ("ruixue.wheatfield.enterprise.updatecompanyinfo".equals(requestType)) {
			//企业账户更新
			strMsg = doWheatfieldEnterpriseUpdatecompanyinfoRequest(session);
		} else if ("ruixue.wheatfield.account.freeze".equals(requestType)) {
			//冻结账户
			strMsg = doWheatfieldAccountFreezeRequest(session);
		} else if ("ruixue.wheatfield.account.rmfreeze".equals(requestType)) {
			//解冻账户
			strMsg = doWheatfieldAccountRmfreezeRequest(session);
		} else if ("ruixue.wheatfield.order.service.authcodeservice".equals(requestType)) {
			//账户资金冻结 （授权码）
			strMsg = doWheatfieldOrderServiceAuthcodeserviceRequest(session);
		} else if ("ruixue.wheatfield.order.service.thawauthcode".equals(requestType)) {
			//账户资金解冻 （授权码）
			strMsg = doWheatfieldOrderServiceThawauthcodeRequest(session);
		} else if ("ruixue.wheatfield.account.close".equals(requestType)) {
			// 销户
			strMsg = doWheatfieldAccountCloseRequest(session);
		} else if ("ruixue.wheatfield.bankaccount.binding".equals(requestType)) {
			//绑定银行账户(对公账户或对私账户)
			strMsg = doWheatfieldBankaccountBindingRequest(session);
		} else if ("ruixue.wheatfield.accountinfo.delete".equals(requestType)) {
			// 删除账户绑定卡
			strMsg = doWheatfieldAccountinfoDeleteRequest(session);
		} else if ("ruixue.wheatfield.accountinfo.update".equals(requestType)) {
			// 修改结算（提现）卡
			strMsg = doWheatfieldAccountinfoUpdateRequest(session);
		} else if ("ruixue.wheatfield.accountnum.ckeck".equals(requestType)) {
			// 校验账号是否绑定
			strMsg = doWheatfieldAccountnumCkeckRequest(session);
		} else if ("ruixue.wheatfield.account.update".equals(requestType)) {
			// 对公校验失败卡修改
			strMsg = doWheatfieldAccountUpdate(session);
		} else if ("ruixue.wheatfield.account.check".equals(requestType)) {
			// 检查账户有效性
			strMsg = doWheatfieldAccountCheckRequest(session);
		} else if ("ruixue.wheatfield.batchquery.company".equals(requestType)) {
			//开户信息查询（企业）（支持批量模糊查询）
			strMsg = doWheatfieldBatchqueryCompanyRequest(session);
		} else if ("ruixue.wheatfield.balance.getlist".equals(requestType)) {
			//查询账户余额
			strMsg = doWheatfieldBalanceGetlistRequest(session);
		} else if ("ruixue.wheatfield.accountinfo.query".equals(requestType)) {
			// 查询账户绑定卡信息
			strMsg = doWheatfieldAccountinfoQueryRequest(session);
		} else if ("ruixue.wheatfield.city.query".equals(requestType)) {
			//查询省市
			strMsg = doWheatfieldCityQueryRequest(session);
		} else if ("ruixue.wheatfield.bankn.query".equals(requestType)) {
			//查询银行信息
			strMsg = doWheatfieldBanknQuery(session);
		} else if ("ruixue.wheatfield.finanace.entrylist.query".equals(requestType)) {
			//账户流水查询
			strMsg = doWheatfieldFinanaceEntrylistQueryRequest(session);
		} else if ("ruixue.wheatfield.order.service.withdrawservice".equals(requestType)) {
			//订单提现
			strMsg = doWheatfieldOrderServiceWithdrawservice(session);
		} else if ("ruixue.wheatfield.order.service.kzhwithhold".equals(requestType)) {
			//代付
			strMsg = doWheatfieldOrderServiceKzhwithholdRequest(session);
		} else if ("ruixue.wheatfield.order.service.kzhcollection".equals(requestType)) {
			//代扣
			strMsg = doWheatfieldOrderServiceKzhcollectionRequest(session);
		} else if ("ruixue.wheatfield.order.oper".equals(requestType)) {
			//业务落单 （充值）
			strMsg = doWheatfieldOrderOperRequest(session);
		} else if ("ruixue.wheatfield.order.service.multitransfer".equals(requestType)) {
			//多次转账
			strMsg = doWheatfieldOrderServiceMultitransferRequest(session);
		} else if ("ruixue.wheatfield.person.accountopr".equals(requestType)) {
			//个人创建账户
			strMsg = doWheatfieldPersonAccountoprRequest(session);
		} else if ("ruixue.wheatfield.batchquery.person".equals(requestType)) {
			//个人开户信息查询
			strMsg = doWheatfieldBatchqueryPerson(session);
		}else if ("ruixue.wheatfield.order.transfer".equals(requestType)) {
			//单笔转账
			strMsg = doWheatfieldOrderTransfer(session);
		}

		if (strMsg == null) {
			strMsg = "操作成功！";
		}
	}

	/* ruixue.wheatfield.account.open 开户 */
	public static String doWheatfieldAccountOpenRequest(String sessionKey) {
		String strError = null;

		try {
			WheatfieldAccountOpenRequest req = new WheatfieldAccountOpenRequest();
			// 必输项
			req.setUserid(""); 					// 用户ID
			req.setUsertype("2"); 				// 用户类型(1：商户，2：普通用户)
			req.setConstid("M000016"); 			// 机构码
			req.setProductid("P000070"); 		// 产品号
			req.setUsername("");				// 用户名称
			// 非必输项
			req.setRole("");					// 角色号

			WheatfieldAccountOpenResponse rsp = ropClient.execute(req,
					sessionKey);
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
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "error";
		} finally {

		}
		return strError;
	}
	
	/* ruixue.wheatfield.bankaccount.binding 绑定银行账户(对公账户或对私账户) */
	public static String doWheatfieldBankaccountBindingRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldBankaccountBindingRequest req = new WheatfieldBankaccountBindingRequest();
			req.setUserid("TJM10000010");								// 用户ID  唯一标识
			req.setUsertype("1");								// 用户类型(1：商户，2：普通用户)
			req.setConstid("M000016");							// 机构码
			req.setProductid("P000070");						// 产品号
			req.setAccountnumber("6222807200881006994");						// 银行卡账号
			req.setAccounttypeid("00");							// 账号类型 00银行卡，01存折，02信用卡。不填默认为银行卡00。
			req.setBankheadname("中国建设银行");							// 开户行总行名称
			req.setCurrency("CNY");								// 币种（CNY）
			
			req.setReq_sn("" + (new Date()).getTime());								// 交易批次号 类型：C(40) 取值：当前系统毫秒数
			req.setSubmit_time("20160411162356");							// 提交时间yyyyMMddHHmmss
			
			req.setAccountpurpose("4");							// 账户目的(1:结算卡，2：其他卡, 3：提现卡,4:结算提现一体卡)
			req.setAccountproperty("2");						// 账户属性（1：对公，2：对私）
			req.setCertificatenumnumber("51203019550201001X");					// 证件号
			req.setCertificatetype("0");						// 开户证件类型0：身份证,1: 户口簿，2：护照,3.军官证,4.士兵证，5.
																// 港澳居民来往内地通行证,6. 台湾同胞来往内地通行证,7.
																// 临时身份证,8. 外国人居留证,9. 警官证, X.其他证件
			req.setAccount_name("张三");							// 账号名 银行卡或存折上的所有人姓名。
			req.setBank_code("105");							// 银行代码总行  通过接口"ruixue.wheatfield.bankn.query" 查询
			req.setBank_branch("105584000503");							// 开户行支行号
			req.setBank_province("5800");						// 开户行所在省
			req.setBank_city("5840");							// 开户行所在市
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

			WheatfieldBankaccountBindingResponse rsp = ropClient.execute(req,sessionKey);
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
	
	/* ruixue.wheatfield.accountinfo.delete 删除绑卡 */
	public static String doWheatfieldAccountinfoDeleteRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldAccountinfoDeleteRequest req = new WheatfieldAccountinfoDeleteRequest();
			req.setUserid("PM10000021");								// 用户ID
			req.setUsertype("1");								// 用户类型(1：商户，2：普通用户)
			req.setConstid("M000016");							// 机构码
			req.setProductid("P000070");						// 产品号
			req.setAccountnumber("6222807200881006972");						// 银行卡号
			// 非必输参数
			req.setRole("");									// 角色号
			WheatfieldAccountinfoDeleteResponse rsp = ropClient.execute(req,
					sessionKey);
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
	
	/* ruixue.wheatfield.accountinfo.update 修改提现卡 */
	public static String doWheatfieldAccountinfoUpdateRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldAccountinfoUpdateRequest req = new WheatfieldAccountinfoUpdateRequest();
			req.setUserid("xxx");								// 用户ID
			req.setUsertype("2");								// 用户类型(1：商户，2：普通用户)
			req.setConstid("M000016");							// 机构码
			req.setProductid("P000070");						// 产品号
			req.setAccountnumber("xxx");						// 银行卡号：将要修改成提现卡的卡号
			
			//6214834116988756
			// 非必输参数
			req.setRole("");
			WheatfieldAccountinfoUpdateResponse rsp = ropClient.execute(req,
					sessionKey);
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
	
	/* ruixue.wheatfield.accountnum.ckeck 校验账号是否绑定 */
	public static String doWheatfieldAccountnumCkeckRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldAccountnumCkeckRequest req = new WheatfieldAccountnumCkeckRequest();
			// 必输项
			req.setUserid("PM10000021");								// 用户ID
			req.setUsertype("1");								// 用户类型
			req.setConstid("M000016");							// 机构码
			req.setProductid("P000070");						// 产品号
			req.setAccountnumber("6222807200881006973");						// 银行卡号
			// 非必输项
			req.setRole("");
			WheatfieldAccountnumCkeckResponse rsp = ropClient.execute(req,
					sessionKey);
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
	
	/* ruixue.wheatfield.account.update 对公校验失败卡修改 */
	private static String doWheatfieldAccountUpdate(String sessionKey) {
		String strError = null;
		try {
			WheatfieldAccountUpdateRequest req = new WheatfieldAccountUpdateRequest();
			
			req.setAccountid("");							//信息主键  查询绑卡信息
			req.setUserid("xxx");							//用户id		
			req.setUsertype("2");							//用户类型(1：商户，2：普通用户)
			req.setCertificatenumber("xxx");				//证件号
			req.setCertificatetype("0");					//证件类型0：身份证,1: 户口簿，2：护照,3.军官证,4.士兵证，5.
															// 港澳居民来往内地通行证,6. 台湾同胞来往内地通行证,7.
															// 临时身份证,8. 外国人居留证,9. 警官证, X.其他证件
			req.setConstid("M000016");						//机构码
			req.setProductid("P000070");					//产品号
			req.setHankheadname("xxx");						//开户总行名称
			req.setAccountnumber("xxx");					//账号			
			req.setAccountrealname("xxx");					//持卡人真实姓名
			req.setBankcity("xxx");							//开户行所在市
			req.setBankhead("xxx");							//开户总行
			req.setHankbranch("xxx");						//开户支行
			req.setBankprovinec("xxx");						//开户行所在省
			
			//非必填
			req.setRole("");						//角色			
			req.setHankbranchname("");				//开户行名称
			

			WheatfieldAccountUpdateResponse rsp = ropClient.execute(req,
					sessionKey);
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
	
	/* ruixue.wheatfield.account.check 查询账户状态 */
	private static String doWheatfieldAccountCheckRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldAccountCheckRequest req = new WheatfieldAccountCheckRequest();
			req.setUserid("PM10000021");						// 用户ID
			req.setUsertype("1");						// 用户类型(1：商户，2：普通用户)
			req.setConstid("M000016");					// 机构码
			req.setProductid("P000070");				// 产品号
			// 非必输参数
			req.setRole("");							// 角色号
			WheatfieldAccountCheckResponse rsp = ropClient.execute(req,
					sessionKey);
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
	
	/* ruixue.wheatfield.batchquery.company 查询账户状态 */
	private static String doWheatfieldBatchqueryCompanyRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldBatchqueryCompanyRequest req = new WheatfieldBatchqueryCompanyRequest();
			req.setConstid("M000016");						// 机构码
//			req.setUserid("PM10000021");							// 用户ID
			req.setUserid("141223100000056");
			req.setAcuntopnlince("");						// 开户许可证
			req.setBuslince("");							// 营业执照
			req.setCompanycode("");							// 企业编号
			req.setCompanyname("");							// 企业名称			
			req.setCorporateidentity("");					// 法人身份证
			req.setCorporatename("");						// 法人姓名
//			req.setCreatedendtime(new Date());				// 创建查询结束时间（格式 YYYYMMDD）
//			req.setCreatedstarttime(new Date());			// 创建查询开始时间（格式 YYYYMMDD）
			req.setOrgancertificate("");					// 组织结构代码证
			req.setShortname("");							// 企业简称
			req.setStatus("");								// 状态
			
			WheatfieldBatchqueryCompanyResponse rsp = ropClient.execute(req,
					sessionKey);
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
	
	/* ruixue.wheatfield.balance.getlist 查询账户余额 */
	private static String doWheatfieldBalanceGetlistRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldBalanceGetlistRequest req = new WheatfieldBalanceGetlistRequest();
			req.setUserid("TJM10000087");								//	用户id
//			req.setUserid("141223100000056");								//	用户id
			req.setRootinstcd("M000016");						// 机构号
			
			WheatfieldBalanceGetlistResponse rsp = ropClient.execute(req,
					sessionKey);
			rsp.getShbalanceinfos().get(0).getAmount();
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
	
	/* ruixue.wheatfield.accountinfo.query 查询绑定银行卡信息 */
	public static String doWheatfieldAccountinfoQueryRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldAccountinfoQueryRequest req = new WheatfieldAccountinfoQueryRequest();
			req.setUserid("TJM10000087");						// 用户ID
			req.setUsertype("1");						// 用户类型(1：商户，2：普通用户)
			req.setConstid("M000016");					// 机构码
//			req.setProductid("P000070");				// 产品号
			req.setProductid("P000230");				// 产品号
			req.setObjorlist("2");						// 查询种类（1：结算卡，2：所有绑定卡）
			// 非必输参数
			// req.setReferuserid("");
			req.setRole("");							// 角色号
			WheatfieldAccountinfoQueryResponse rsp = ropClient.execute(req,
					sessionKey);
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
		} finally {
		}
		return strError;
	}
	
	/* ruixue.wheatfield.person.accountopr 个人创建账户 */
	public static String doWheatfieldPersonAccountoprRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldPersonAccountoprRequest req = new WheatfieldPersonAccountoprRequest();
			// 必输项
			req.setPersonchnname("张三c1");						// 中文姓名（当操作类型是2：修改时，此项目为可选）
			req.setCertificatetype("0");						// 证件类型,0身份证;1护照;2军官证;3士兵证;4回乡证;
																// 5户口本;6外国护照;7其它（当操作类型是2：修改时，此项目为可选）
			req.setCertificatenumber("411381196802185636");						// 证件号（当操作类型是2：修改时，此项目为可选）
			req.setUserid("PP10000022");								// 接入机构中设置的用户ID
			req.setConstid("M000017");							// 机构码
			req.setProductid("P000071");						// 产品号
			req.setOpertype("1");								// 操作类型（1：新增，2：修改）
			// 非必输项
			req.setAddress("");									//地址
			req.setBirthday("");								//生日
			req.setEmail("");									//邮箱
			req.setFixtel("");									//固定电话号码
			req.setMobiletel("");								//手机号码
			req.setPersonengname("");							//英文名
			req.setPersonsex("");								//性别
			req.setPersontype("1");								//个人类别 默认 1
			req.setPost("");									//邮编
			req.setRemark("");									//备注
			req.setRole("");									//角色号
			req.setUsername("");								//用户名
			
			WheatfieldPersonAccountoprResponse rsp = ropClient.execute(req,
					sessionKey);
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
	
	/* ruixue.wheatfield.enterprise.entityaccountopt 企业创建账户 */
	public static String doWheatfieldEnterpriseEntityaccountoptRequest(
			String sessionKey) {
		String strError = null;
		try {
			WheatfieldEnterpriseEntityaccountoptRequest req = new WheatfieldEnterpriseEntityaccountoptRequest();
			// 必输项
			req.setCompanyname("xxx");				// 企业名称
			req.setBuslince("xxx");					// 营业执照
			req.setUserid("xxx");					// 接入机构中设置的用户ID  不能重复
			req.setUsertype("1");					// 用户类型(1：商户 )
			req.setConstid("M000016");				// 机构码
			req.setProductid("P000070");			// 产品号
			req.setUsername("xxx");					// 用户名称，及接入机构的用户名
			
			// 非必输项
			req.setAcuntopnlince("xxx");			//开户许可证
			req.setAddress("xxx");					//地址
			req.setBusplacectf("xxx");				//经营场所实地认证
			req.setCompanycode("xxx");				//企业编号
			req.setConnect("xxx");					//联系方式
			req.setCorporateidentity("xxx");		//法人身份证
			req.setCorporatename("xxx");			//法人姓名
			req.setLoancard("");					//贷款卡
			req.setMcc("");							//MCC码
			req.setOrgancertificate("xxx");			//组织结构代码证
			req.setPost("xxx");						//邮编
			req.setRemark("xxx");					//备注
			req.setRole("");						//角色号
			req.setShortname("xxx");				//企业简称
			req.setTaxregcardf("");					//税务登记证1
			req.setTaxregcards("");					//税务登记证2

			WheatfieldEnterpriseEntityaccountoptResponse rsp = ropClient
					.execute(req, sessionKey);
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
	
	/* 企业账户更新  ruixue.wheatfield.enterprise.updatecompanyinfo  */
	public static String doWheatfieldEnterpriseUpdatecompanyinfoRequest(
			String sessionKey) {
		String strError = null;
		try {
			WheatfieldEnterpriseUpdatecompanyinfoRequest req = new WheatfieldEnterpriseUpdatecompanyinfoRequest();
			// 必输项
			req.setCompanyname("深圳市天下旅行有限公司");						// 企业名称
			req.setUserid("PM10000021");							// 接入机构中设置的用户ID
			req.setUsertype("1");							// 用户类型(1：商户 )
			req.setConstid("M000016");						// 机构码
			req.setProductid("P000070");					// 产品号
			req.setUsername("天下旅行");							// 用户名称，及接入机构的用户名
			req.setOpertype("1");							// 更新类型（1.更新）
			// 非必输项
			req.setAcuntopnlince("xxx");					//开户许可证
			req.setAddress("xxx");							//地址
			req.setBuslince("xxx");							//营业执照
			req.setBusplacectf("xxx");						//经营场所实地认证
			req.setCompanycode("xxx");						//企业编号
			req.setConnect("xxx");							//联系方式
			req.setCorporateidentity("xxx");				//法人身份证
			req.setCorporatename("xxx");					//法人姓名
			req.setLoancard("");							//贷款卡
			req.setMcc("");									//Mcc码
			req.setOrgancertificate("xxx");					//组织结构代码
			req.setPost("xxx");								//邮编
			req.setRemark("xxx");							//备注
			req.setRole("");								//角色号
			req.setShortname("xxx");						//企业简称
			req.setTaxregcardf("");							//税务登记证1
			req.setTaxregcards("");							//税务登记证2

			WheatfieldEnterpriseUpdatecompanyinfoResponse rsp = ropClient
					.execute(req, sessionKey);
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
	
	/* ruixue.wheatfield.account.freeze 冻结账户 */
	public static String doWheatfieldAccountFreezeRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldAccountFreezeRequest req = new WheatfieldAccountFreezeRequest();
			// 必输项
			req.setUserid("PM10000021");					// 用户ID
			req.setUsertype("1");					// 用户类型(1：商户，2：普通用户)
			req.setConstid("M000016");				// 机构码
			req.setProductid("P000070");			// 产品号
			
			// 非必输参数
			req.setRole("");						// 角色号

			WheatfieldAccountFreezeResponse rsp = ropClient.execute(req,
					sessionKey);
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
	
	/* ruixue.wheatfield.account.rmfreeze 账户解冻 */
	public static String doWheatfieldAccountRmfreezeRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldAccountRmfreezeRequest req = new WheatfieldAccountRmfreezeRequest();
			// 必输项
			req.setUserid("xxx");					// 用户ID
			req.setUsertype("2");					// 用户类型(1：商户，2：普通用户)
			req.setConstid("M000016");				// 机构码
			req.setProductid("P000070");			// 产品号
			// 非必输参数
			req.setRole("");						//角色号
			WheatfieldAccountRmfreezeResponse rsp = ropClient.execute(req,sessionKey);
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
		
	/* ruixue.wheatfield.order.service.authcodeservice 账户资金冻结（授权码） */
	public static String doWheatfieldOrderServiceAuthcodeserviceRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldOrderServiceAuthcodeserviceRequest req = new WheatfieldOrderServiceAuthcodeserviceRequest();
			// 必输项
			req.setConditioncode("2");					//订单关联类型（主业务单：1 业务子单：0 无业务单：2）
			req.setAmount((long)2);						//冻结金额
			req.setUserid("xxx");						//用户id
			req.setFunccode("40171");					//功能编码：冻结返回授权码 为：40171
			req.setMerchantcode("M000016");				//商户编码/机构号
			req.setOrderamount((long)2);				//订单金额
			req.setStatus(1);							//状态 1：正常
			req.setUserfee((long)0);					//用户手续费
			req.setProductid("P000070");				//产品号
			req.setUseripaddress("192.168.1.1");		//用户ip地址
			req.setRequestno("xxx");					//请求单号			
			req.setOrderno("xxx");						//订单号
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
			
			WheatfieldOrderServiceAuthcodeserviceResponse rsp = ropClient.execute(req,
					sessionKey);
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
	
	/* ruixue.wheatfield.order.service.thawauthcode 账户资金解冻 （授权码） */
	public static String doWheatfieldOrderServiceThawauthcodeRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldOrderServiceThawauthcodeRequest req = new WheatfieldOrderServiceThawauthcodeRequest();
			// 必输项
			req.setAmount("2");						//解冻金额
			req.setRootinstcd("M000016");			//机构号			
			req.setUserid("xxx");					//用户id
			req.setFrozenuserorderid("xxx");		//冻结时上传的requestno
			req.setProductid("P000070");			//产品号
			req.setRequestno("xxx");				//请求号
			req.setRequesttime("");					//请求时间 yyyy-MM-dd HH:mm:ss（请按格式传递）
			req.setAuthcode("xxx");					//授权码	账户资金冻结时返回 （授权码）		
			req.setConditioncode("2");				//订单关联类型（主业务单：1 业务子单：0 无业务单：2）
			
			WheatfieldOrderServiceThawauthcodeResponse rsp = ropClient.execute(req,
					sessionKey);
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
	
	/* ruixue.wheatfield.account.close 销户 */
	public static String doWheatfieldAccountCloseRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldAccountCloseRequest req = new WheatfieldAccountCloseRequest();
			req.setUserid("xxx");							// 用户ID
			req.setUsertype("xxx");							// 用户类型(1：商户，2：普通用户)
			req.setConstid("M000016");						// 机构码
			req.setProductid("P000070");					// 产品号
			// 非必输参数
			req.setRole("");								// 角色号

			WheatfieldAccountCloseResponse rsp = ropClient.execute(req,
					sessionKey);
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
	
	/* ruixue.wheatfield.order.oper 业务落单 （充值） */
	public static String doWheatfieldOrderOperRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldOrderOperRequest req = new WheatfieldOrderOperRequest();
			// 必输项
			req.setUserid("PM10000021");								// 接入机构中设置的用户ID
			req.setConstid("M000016");							// 机构码
//			req.setOrdertypeid("B1");							// 基础业务为B，扩展业务待定 M70001棉庄订金支付
			req.setProductid("P000070");						// 产品号
			req.setOpertype("3");								// 操作类型（修改：2,新增：1,取消4,查询3）
//			req.setOrderdate(new Date());						// 订单日期
//			req.setOrdertime(new Date());						// 订单时间
			req.setUserorderid("H0544878456346770328");							// 用户订单编号
//			req.setAmount("20");								// 订单金额（若不存在商品概念则必填）
//			req.setGoodsname("香格里拉酒店");							// 商品名称
//			req.setGoodsdetail("预订3天2晚，4.11入住，4.14离店");							// 商品描述
//			req.setNumber(0);									// 商品数量
//			req.setUnitprice(null);								// 单价
//			req.setAdjusttype(null);							// 调整类型
//			req.setAdjustcontent("");						// 调整内容
//			req.setUserrelateid(null);							// 关联用户id（若有第三方则必须填写）
			
			WheatfieldOrderOperResponse rsp = ropClient
					.execute(req, sessionKey);
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
		
	/* ruixue.wheatfield.order.service.multitransfer 多次转账 */
	public static String doWheatfieldOrderServiceMultitransferRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldOrderServiceMultitransferRequest req = new WheatfieldOrderServiceMultitransferRequest();
			// 必输项  多比转账时 参数以“|”分隔
			req.setAmount("xxx|xxx");										//金额（分）金额之和不大于落单金额
			req.setIntermerchantcode("M000016|M000016");					//收款方的机构码
			req.setInterproductid("P000070|P000070");						//收款方productid，若与转出方一致，仍需填写
			req.setUserrelateid("xxx|xxx");									//收款方userid		
			req.setUserid("xxx|xxx");										//转出方的userid
			req.setProductid("M000016|M000016");							//转出方的产品号
			req.setMerchantcode("P000070|P000070");							//转出方的机构码
			req.setUseripaddress("xxx|xxx");								//请求的机器ip			
			req.setRequestno("xxx");										//请求号	只写一个		
			req.setRemark("xxx|xxx");										//备注			
			
			WheatfieldOrderServiceMultitransferResponse rsp = ropClient
					.execute(req, sessionKey);
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
	
	/* ruixue.wheatfield.city.query 查询省市 */	
	public static String doWheatfieldCityQueryRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldCityQueryRequest req = new WheatfieldCityQueryRequest();			
			req.setCitycode("5800");// 非必输项 省：（空） ;市：省Code
			WheatfieldCityQueryResponse rsp = ropClient
					.execute(req, sessionKey);
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
		
	/* ruixue.wheatfield.order.service.kzhwithhold 代付 */	
	private static String doWheatfieldOrderServiceKzhwithholdRequest(
			String sessionKey) {
		String strError = null;
		try {
			WheatfieldOrderServiceKzhwithholdRequest req = new WheatfieldOrderServiceKzhwithholdRequest();
//			req.setAmount((long)20);					//入账金额
//			req.setOrderamount((long)20);				//订单金额
//			req.setOrdercount(1);						//订单数量
//			req.setFunccode("4014");					//代扣代付
//			req.setProductid("P000070");				//产品号
//			req.setUserfee((long)0);					//用户手续费
//			req.setUserid("xxx");						//用户号
//			req.setIntermerchantcode("xxx");			//中间商户编码 向那个账户发起代付
//			req.setUseripaddress("127.0.0.1");			//用户IP地址 格式 127.0.0.1
//			req.setOrderno("xxx");						//订单号（商户可不填）
//			req.setMerchantcode("M000016");				//商户编码/机构号
//			req.setRequestno("xxx");					//请求号
//			req.setRequesttime(new Date());				//请求时间
//			req.setOrderdate(new Date());				//订单日期 格式 yyyy-MM-dd hh:mm:ss		
//			req.setStatus(1);							//状态
//			
//			//非必输			
//			req.setFeeamount((long)0);					//手续费金额				
//			req.setOrderpackageno("");					//订单包号
//			req.setPaychannelid("");					//支付渠道ID			
//			req.setProfit((long)0);						//利润
//			req.setRemark("");							//备注				
//			req.setTradeflowno("");						//统一交易流水号			
//			req.setBankcode("");						//银行银联编号
//			req.setBusitypeid("");						//业务类型ID
//			req.setErrorcode("");						//错误信息码
//			req.setErrormsg("");						//错误信息
			
			WheatfieldOrderServiceKzhwithholdResponse rsp = 
					ropClient.execute(req, sessionKey);
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
	
	/* ruixue.wheatfield.order.service.kzhcollection 课栈发起代扣 */	
	private static String doWheatfieldOrderServiceKzhcollectionRequest(String sessionKey) {
		String strError = null;
		try {
			WheatfieldOrderServiceKzhcollectionRequest req = new WheatfieldOrderServiceKzhcollectionRequest();
			req.setAmount((long)20);					//入账金额
			req.setOrderamount((long)20);				//订单金额
			req.setOrdercount(1);						//订单数量
			req.setFunccode("4013");					//代收
			req.setProductid("P000070");				//产品号
			req.setUserfee((long)0);					//用户手续费
			req.setUserid("xxx");						//用户号
			req.setUseripaddress("xxx");				//用户IP地址 格式 127.0.0.1
			req.setOrderno("xxx");						//订单号（商户可不填）
			req.setMerchantcode("M000016");				//商户编码/机构号
			req.setRequestno("xxx");					//请求号
			req.setRequesttime(new Date());				//请求时间
			req.setOrderdate(new Date());				//订单日期 格式 yyyy-MM-dd hh:mm:ss		
			req.setStatus(1);							//状态
			req.setLoanorderid("xxx"); 					//贷款订单号
			
			//非必输			
			req.setFeeamount((long)0);					//手续费金额				
			req.setOrderpackageno("");					//订单包号
			req.setPaychannelid("");					//支付渠道ID			
			req.setProfit((long)0);						//利润
			req.setRemark("1");							//备注				
			req.setTradeflowno("");						//统一交易流水号			
			req.setBankcode("");						//银行银联编号
			req.setBusitypeid("");						//业务类型ID
			req.setErrorcode("");						//错误信息码
			req.setErrormsg("");						//错误信息
			
			
			WheatfieldOrderServiceKzhcollectionResponse rsp = 
					ropClient.execute(req, sessionKey);
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
	
	/* ruixue.wheatfield.order.service.withdrawservice 订单提现 */	
	private static String doWheatfieldOrderServiceWithdrawservice(
			String sessionKey) {
		String strError = null;
		try {
			WheatfieldOrderServiceWithdrawserviceRequest req = new WheatfieldOrderServiceWithdrawserviceRequest();
			req.setProductid("P000070");					//产品号
			req.setAmount("100");							//金额
			req.setMerchantcode("M000016");					//机构号
			req.setOrderdate("2016-07-21 14:16:22");						//订单日期 yyyy-MM-dd hh:mm:ss
			req.setUserid("TJM10000010");							//用户号
			req.setUserorderid("TJO160721100636233");						//订单号
			req.setUserfee((long)0);						//手续费
			WheatfieldOrderServiceWithdrawserviceResponse rsp = 
					ropClient.execute(req, sessionKey);
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
	
	/* ruixue.wheatfield.bankn.query 查询银行信息 */
	private static String doWheatfieldBanknQuery(String sessionKey) {
		String strError = null;
		try {
			WheatfieldBanknQueryRequest req = new WheatfieldBanknQueryRequest();
			// 非必输项
			req.setBankcode("105");// 银行code：
			// 如三个入参都为空，将会查询所有总行信息
			// 如该入参不为空，将会根据银行code获取所有支行信息
			// 注：(前提 该入参不为空)status填写的状态值将不会进行条件查询

			req.setCitycode("5840");
			// 根据接口"ruixue.wheatfield.city.query" 查询
			// 城市code：如入参全部为空，将会查询所有总行信息
			// 如该入参不为空并且bankcode入参不为空，将会查询该城市下的所有bankcode的支行信息
			// 如该入参不为空bankcode为空，将会获取该城市下所有支行信息
			// 注：(前提 该字段有值)status填写的状态值将不会进行条件查询

			//req.setStatus(1);// 查询状态：
			// 1为通联支持的银行
			// 0为通联暂不支持的银行
			// 如不填写为获取全部总行银行信息

			WheatfieldBanknQueryResponse rsp = ropClient.execute(req,
					sessionKey);
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
	
	/* ruixue.wheatfield.finanace.entrylist.query 账户流水查询  */
	private static String doWheatfieldFinanaceEntrylistQueryRequest(String session) {
		String strError = null;
		try {
			WheatfieldFinanaceEntrylistQueryRequest req = new WheatfieldFinanaceEntrylistQueryRequest();			
			
			req.setRootinstcd("M000016");							//机构码
			req.setUserid("PM10000021");									//用户id
//			req.setUserid("141223100000056");									//用户id
			req.setProductid("P000070");							//产品号			
//			req.setCreatedtimefrom(new Date());						//记录创建开始时间
//			req.setCreatedtimeto(new Date());						//记录创建结束时间			
			req.setQuerytype("1");									//查询类型（1:根据账户查询 2:根据交易记录查询）
//			req.setRequestid("xxx");								//交易记录编码（查询类型为2时，必须入参）
			
			WheatfieldFinanaceEntrylistQueryResponse rsp = ropClient.execute(req,session);
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
	
	/* ruixue.wheatfield.batchquery.person 查询个人开户信息  */
	private static String doWheatfieldBatchqueryPerson(String session) {
		String strError = null;
		try {
			WheatfieldBatchqueryPersonRequest req = new WheatfieldBatchqueryPersonRequest();			
			req.setConstid("M000016");			//机构号
			req.setUserid("PP10000021");				//用户id
			WheatfieldBatchqueryPersonResponse rsp = ropClient.execute(req,session);
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
	
	//转账
	private static String doWheatfieldOrderTransfer(String session) {
		String strError = null;
		try {
			WheatfieldOrderTransferRequest req = new WheatfieldOrderTransferRequest();
			req.setTransfertype("3");								//1:子账户转账				
			req.setConditioncode("1");								//1:落单
			req.setMerchantcode("M000016");							//转入方机构号
			req.setProductid("P000070");							//转入方产品号
			req.setUserid("");										//转出的用户
			req.setRequestno("");									//业务订单号
			req.setRequesttime("2016-12-12 22:22:22");				//请求时间
			req.setAmount("100");										//金额
			req.setUserfee("0");									
			req.setIntermerchantcode("M000016");					//转出方机构号
			req.setInterproductid("P000070");						//转出方产品号
			req.setUserrelateid("141223100000056");								//平台企业账户或普通用户  平台账户“141223100000056”
			WheatfieldOrderTransferResponse rsp = ropClient.execute(req,session);
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
	
	/*
	 * 转化日期格式
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/*
	 * 取Session
	 */
	public static String sessionGet() {
		String sessionKey = null;
		DefaultRopClient ropClient = new DefaultRopClient(ropUrl, appKey,
				appSecret);
		try {
			ExternalSessionGetRequest sessionGetReq = new ExternalSessionGetRequest();

			ExternalSessionGetResponse sessionGetRsp = ropClient
					.execute(sessionGetReq);
			sessionKey = sessionGetRsp.getSession();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {

		}
		return sessionKey;
	}
}
