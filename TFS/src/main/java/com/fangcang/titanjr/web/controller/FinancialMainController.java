package com.fangcang.titanjr.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.dto.bean.CheckStatus;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.util.StringUtil;

/**
 * Created by zhaoshan on 2016/5/21.
 */
@Controller
public class FinancialMainController extends BaseController {

    private static final Log log = LogFactory.getLog(FinancialMainController.class);
    @Resource
    private TitanFinancialOrganService titanFinancialOrganService;
    @Resource
    private TitanFinancialUserService titanFinancialUserService;
    
    @RequestMapping(value = "/common/main", method = RequestMethod.GET)
    public String toIndex(HttpServletRequest request, Model model) {

        return "main";
    }
    /***
     * 主页
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpServletRequest request, Model model) throws Exception {
    	try {
    		queryOrgInfo(model);
		} catch (Exception e) {
			throw new Exception(e);
		}
    	
        return "tfs-main/home";
    }
    
    /***
     * 查询机构信息
     * @param model
     * @throws Exception
     */
    private void queryOrgInfo(Model model) throws Exception{
    	Integer isAdmin = (Integer)getSession().getAttribute(WebConstant.SESSION_KEY_LOGIN_IS_ADMIN);
    	String jrUserLoginName = (String)getSession().getAttribute(WebConstant.SESSION_KEY_JR_LOGIN_UESRNAME);
    	//暂时统一从session中取
    	String orgBindStatus = (String)getSession().getAttribute(WebConstant.SESSION_KEY_JR_BIND_STATUS);
    	
    	String orgCheckResultKey = "";
    	String orgCheckResultMsg = "";
    	try {
    		if(StringUtil.isValidString(jrUserLoginName)){//查询机构审核状态
        		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
        		userInfoQueryRequest.setUserLoginName(jrUserLoginName);
        		UserInfoResponse userInfoResponse = titanFinancialUserService.queryFinancialUser(userInfoQueryRequest);
        		UserInfoDTO userInfo =	userInfoResponse.getUserInfoDTOList().get(0);
        		FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
            	organQueryRequest.setUserId(userInfo.getUserId());
            	FinancialOrganResponse organOrganResponse = titanFinancialOrganService.queryFinancialOrgan(organQueryRequest);
				if (organOrganResponse.isResult()) {
					
					CheckStatus checkStatus = organOrganResponse.getFinancialOrganDTO().getCheckStatus();
					if (checkStatus != null) {
						orgCheckResultKey = checkStatus.getCheckResultKey();
						orgCheckResultMsg = checkStatus.getCheckResultMsg();
						model.addAttribute("userType", organOrganResponse.getFinancialOrganDTO().getUserType());
						model.addAttribute("orgId", organOrganResponse.getFinancialOrganDTO().getOrgId());
					}
				}
        	}
    		
		} catch (Exception e) {
			log.error("金融首页错误，登录用户名为:"+jrUserLoginName, e);
			throw new Exception(e);
		}
    	model.addAttribute("orgCheckResultKey", orgCheckResultKey);
    	model.addAttribute("orgCheckResultMsg", orgCheckResultMsg);
    	model.addAttribute("isAdmin", isAdmin==null?0:isAdmin);
    	model.addAttribute("orgBindStatus", orgBindStatus==null?0:orgBindStatus);
    }
    
    /**
     * 信贷
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/loan", method = RequestMethod.GET)
    public String loan(Model model) throws Exception{
    	try {
    		queryOrgInfo(model);
		} catch (Exception e) {
			throw new Exception(e);
		}
        return "tfs-main/loan";
    }
    
    /**
     * 收款介绍
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/receipt", method = RequestMethod.GET)
    public String receipt(Model model) throws Exception{
    	try {
    		queryOrgInfo(model);
		} catch (Exception e) {
			throw new Exception(e);
		}
        return "tfs-main/receipt";
    }
    
    /**
     * 付款介绍
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public String pay(Model model) throws Exception{
    	try {
    		queryOrgInfo(model);
		} catch (Exception e) {
			throw new Exception(e);
		}
        return "tfs-main/pay";
    }
    
    
    
    
    @RequestMapping(value = "/common/clickToPay", method = RequestMethod.GET)
    public String testPay(HttpServletRequest request, Model model) {
        return "checkstand-pay/clickToPayPage";
    }
    
    /**
     * 心跳
     */
    @ResponseBody
    @RequestMapping(value = "/beat")
    public String beat(){
    	return toJson(putSuccess("心跳"));
    }

//    @RequestMapping(value = "/testValExcp", method = RequestMethod.GET)
//    public String testValidateException(HttpServletRequest request, Model model) throws RSValidateException {
//        throw new RSValidateException("errorCode", "errorMsg");
//    }
//
//    @RequestMapping(value = "/testRunExcp", method = RequestMethod.GET)
//    public String testRuntimeException(HttpServletRequest request, Model model) throws RuntimeException {
//        throw new RuntimeException("errorCode", new IllegalAccessException("test...."));
//    }
//
//    @RequestMapping(value = "/testExcp", method = RequestMethod.GET)
//    public String testException(HttpServletRequest request, Model model) throws Exception {
//        throw new Exception("errorCode", new StackOverflowError("test..."));
//    }
}
