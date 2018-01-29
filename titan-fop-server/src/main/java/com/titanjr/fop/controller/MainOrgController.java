package com.titanjr.fop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum.UserType;
import com.fangcang.titanjr.common.util.Tools;
import com.titanjr.fop.dto.OpenAccountPerson;
import com.titanjr.fop.dto.server.request.OptOrgRequest;
import com.titanjr.fop.dto.server.response.OptOrgResponse;
import com.titanjr.fop.dto.server.response.UpdateOrgReponse;
import com.titanjr.fop.entity.TitanMainOrg;
import com.titanjr.fop.request.WheatfieldBatchqueryCompanyRequest;
import com.titanjr.fop.request.WheatfieldBatchqueryPersonRequest;
import com.titanjr.fop.request.WheatfieldEnterpriseEntityaccountoptRequest;
import com.titanjr.fop.request.WheatfieldEnterpriseUpdatecompanyinfoRequest;
import com.titanjr.fop.request.WheatfieldPersonAccountoprRequest;
import com.titanjr.fop.response.WheatfieldBatchqueryCompanyResponse;
import com.titanjr.fop.response.WheatfieldBatchqueryPersonResponse;
import com.titanjr.fop.response.WheatfieldEnterpriseEntityaccountoptResponse;
import com.titanjr.fop.response.WheatfieldEnterpriseUpdatecompanyinfoResponse;
import com.titanjr.fop.response.WheatfieldPersonAccountoprResponse;
import com.titanjr.fop.service.MainOrgService;
import com.titanjr.fop.util.BeanUtils;
import com.titanjr.fop.util.ResponseUtils;

@Controller
@RequestMapping(value = "/org")
public class MainOrgController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Resource
	private MainOrgService mainOrgService;
	
	/***
	 * 个人机构开通和修改
	 * @param request
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/personOpt", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String personOpt(HttpServletRequest request, RedirectAttributes attr) throws Exception {
		
		WheatfieldPersonAccountoprResponse personAccountoprResponse = new WheatfieldPersonAccountoprResponse();
		String validResult = ResponseUtils.validRequestSign(request, personAccountoprResponse);
        if (null != validResult) {
            return validResult;
        }
        personAccountoprResponse.setIs_success("true");
		WheatfieldPersonAccountoprRequest personAccountoprRequest = BeanUtils.switch2RequestDTO(WheatfieldPersonAccountoprRequest.class, request);
        if (null == personAccountoprRequest) {
            return ResponseUtils.getConvertErrorResp(personAccountoprResponse);
        }
        
        if("1".equals(personAccountoprRequest.getOpertype())){//注册
        	OptOrgRequest optOrgRequest = new OptOrgRequest();
        	optOrgRequest.setUserid(personAccountoprRequest.getUserid());
        	optOrgRequest.setProductid(personAccountoprRequest.getProductid());
        	optOrgRequest.setUsertype(UserType.PERSONAL.getKey());
        	optOrgRequest.setUsername(personAccountoprRequest.getUsername());
        	optOrgRequest.setCertificatetype(personAccountoprRequest.getCertificatetype());
        	optOrgRequest.setCertificatenumber(personAccountoprRequest.getCertificatenumber());
        	optOrgRequest.setRemark(personAccountoprRequest.getRemark());
        	OptOrgResponse optOrgResponse = mainOrgService.optOrg(optOrgRequest);
        	if(optOrgResponse.isResult()){
        		return toJson(personAccountoprResponse);
        	}else{//失败
        		personAccountoprResponse.setErrorCode(optOrgResponse.getReturnCode());
        		personAccountoprResponse.setMsg(optOrgResponse.getReturnMessage());
        		return toJson(personAccountoprResponse);
        	}
        }else if("2".equals(personAccountoprRequest.getOpertype())){//修改
        	OptOrgRequest optOrgRequest = new OptOrgRequest();
        	optOrgRequest.setUserid(personAccountoprRequest.getUserid());
        	optOrgRequest.setProductid(personAccountoprRequest.getProductid());
        	optOrgRequest.setUsername(personAccountoprRequest.getUsername());
        	optOrgRequest.setCertificatetype(personAccountoprRequest.getCertificatetype());
        	optOrgRequest.setCertificatenumber(personAccountoprRequest.getCertificatenumber());
        	optOrgRequest.setRemark(personAccountoprRequest.getRemark());
        	UpdateOrgReponse updateOrgReponse = mainOrgService.updateOrg(optOrgRequest);
        	if(updateOrgReponse.isResult()){
        		return toJson(personAccountoprResponse);
        	}else{//失败
        		personAccountoprResponse.setErrorCode(updateOrgReponse.getReturnCode());
        		personAccountoprResponse.setMsg(updateOrgReponse.getReturnMessage());
        		return toJson(personAccountoprResponse);
        	}
        	
        }else{//参数错误
        	logger.error("opertype类型不存在，机构参数personAccountoprRequest："+Tools.gsonToString(personAccountoprRequest));
        	personAccountoprResponse.setIs_success("false");
        	return ResponseUtils.getParamErrorResp(personAccountoprResponse, "opertype类型不存在");
        }
        
	}
	
	/***
	 * 企业机构注册
	 * @param request
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/enterpriseOpt", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String enterpriseOpt(HttpServletRequest request, RedirectAttributes attr) throws Exception {
		WheatfieldEnterpriseEntityaccountoptResponse enterpriseOptResponse = new WheatfieldEnterpriseEntityaccountoptResponse();
		String validResult = ResponseUtils.validRequestSign(request, enterpriseOptResponse);
        if (null != validResult) {
            return validResult;
        }
        enterpriseOptResponse.setIs_success("true");
        WheatfieldEnterpriseEntityaccountoptRequest enterpriseOptRequest = BeanUtils.switch2RequestDTO(WheatfieldEnterpriseEntityaccountoptRequest.class, request);
        if (null == enterpriseOptRequest) {
            return ResponseUtils.getConvertErrorResp(enterpriseOptResponse);
        }
        OptOrgRequest optOrgRequest = new OptOrgRequest();
    	optOrgRequest.setUserid(enterpriseOptRequest.getUserid());
    	optOrgRequest.setProductid(enterpriseOptRequest.getProductid());
    	optOrgRequest.setUsertype(UserType.ENTERPRISE.getKey());
    	optOrgRequest.setUsername(enterpriseOptRequest.getUsername());
    	optOrgRequest.setMobiletel(enterpriseOptRequest.getConnect());//联系电话
    	optOrgRequest.setConnect(enterpriseOptRequest.getCorporatename());//联系人
    	optOrgRequest.setRemark(enterpriseOptRequest.getRemark());
    	optOrgRequest.setBuslince(enterpriseOptRequest.getBuslince());
    	OptOrgResponse optOrgResponse = mainOrgService.optOrg(optOrgRequest);
    	if(optOrgResponse.isResult()){
    		return toJson(enterpriseOptResponse);
    	}else{//失败
    		enterpriseOptResponse.setErrorCode(optOrgResponse.getReturnCode());
    		enterpriseOptResponse.setMsg(optOrgResponse.getReturnMessage());
    		return toJson(enterpriseOptResponse);
    	}
        
	}
	
	
	/**
	 * 企业机构修改
	 * @param request
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCompanyInfo", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String updateCompanyInfo(HttpServletRequest request, RedirectAttributes attr) throws Exception {
		WheatfieldEnterpriseUpdatecompanyinfoResponse enterpriseOptResponse = new WheatfieldEnterpriseUpdatecompanyinfoResponse();
		String validResult = ResponseUtils.validRequestSign(request, enterpriseOptResponse);
        if (null != validResult) {
            return validResult;
        }
        enterpriseOptResponse.setIs_success("true");
        WheatfieldEnterpriseUpdatecompanyinfoRequest enterpriseOptRequest = BeanUtils.switch2RequestDTO(WheatfieldEnterpriseUpdatecompanyinfoRequest.class, request);
        if (null == enterpriseOptRequest) {
            return ResponseUtils.getConvertErrorResp(enterpriseOptResponse);
        }
		
        OptOrgRequest optOrgRequest = new OptOrgRequest();
    	optOrgRequest.setUserid(enterpriseOptRequest.getUserid());
    	optOrgRequest.setProductid(enterpriseOptRequest.getProductid());
    	optOrgRequest.setUsername(enterpriseOptRequest.getUsername());
    	optOrgRequest.setMobiletel(enterpriseOptRequest.getConnect());//联系电话
    	optOrgRequest.setConnect(enterpriseOptRequest.getCorporatename());//联系人
    	optOrgRequest.setRemark(enterpriseOptRequest.getRemark());
    	optOrgRequest.setBuslince(enterpriseOptRequest.getBuslince());
    	UpdateOrgReponse updateOrgReponse = mainOrgService.updateOrg(optOrgRequest);
    	if(updateOrgReponse.isResult()){
    		return toJson(enterpriseOptResponse);
    	}else{//失败
    		enterpriseOptResponse.setErrorCode(updateOrgReponse.getReturnCode());
    		enterpriseOptResponse.setMsg(updateOrgReponse.getReturnMessage());
    		return toJson(enterpriseOptResponse);
    	}
    	
	}
	
	//查询机构
	
	@RequestMapping(value = "/queryCompany", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryCompany(HttpServletRequest request, RedirectAttributes attr) throws Exception {
		WheatfieldBatchqueryCompanyResponse enterpriseOptResponse = new WheatfieldBatchqueryCompanyResponse();
		String validResult = ResponseUtils.validRequestSign(request, enterpriseOptResponse);
        if (null != validResult) {
            return validResult;
        }
        enterpriseOptResponse.setIs_success("true");
        WheatfieldBatchqueryCompanyRequest enterpriseOptRequest = BeanUtils.switch2RequestDTO(WheatfieldBatchqueryCompanyRequest.class, request);
        if (null == enterpriseOptRequest) {
            return ResponseUtils.getConvertErrorResp(enterpriseOptResponse);
        }
        OptOrgRequest queryOrgRequest = new OptOrgRequest();
        queryOrgRequest.setBuslince(enterpriseOptRequest.getBuslince());
        queryOrgRequest.setUserid(enterpriseOptRequest.getUserid());
        queryOrgRequest.setUsername(enterpriseOptRequest.getCompanyname());
        List<TitanMainOrg> mainOrgList = mainOrgService.queryOrg(queryOrgRequest);
        List<OpenAccountPerson> openaccountpersons = new ArrayList<OpenAccountPerson>();
        if(CollectionUtils.isNotEmpty(mainOrgList)){
        	for(TitanMainOrg item : mainOrgList){
        		OpenAccountPerson entity = new OpenAccountPerson();
        		entity.setUserid(item.getOrgCode());
        		entity.setPersonchnname(item.getOrgName());
        		entity.setCertificatetype(item.getCertificatetype()+"");
        		entity.setCertificatenumber(item.getCertificateNumber());
        		entity.setRemark(item.getRemark());
        		entity.setStatusid(item.getStatusId().toString());
        		openaccountpersons.add(entity);
        	}
        }
		
    	return toJson(enterpriseOptResponse);
	}
	
	@RequestMapping(value = "/queryPerson", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryPerson(HttpServletRequest request, RedirectAttributes attr) throws Exception {
		WheatfieldBatchqueryPersonResponse personOptResponse = new WheatfieldBatchqueryPersonResponse();
		String validResult = ResponseUtils.validRequestSign(request, personOptResponse);
        if (null != validResult) {
            return validResult;
        }
        personOptResponse.setIs_success("true");
        WheatfieldBatchqueryPersonRequest personOptRequest = BeanUtils.switch2RequestDTO(WheatfieldBatchqueryPersonRequest.class, request);
        if (null == personOptRequest) {
            return ResponseUtils.getConvertErrorResp(personOptResponse);
        }
        OptOrgRequest queryOrgRequest = new OptOrgRequest();
        queryOrgRequest.setCertificatetype(personOptRequest.getCertificatetype());
        queryOrgRequest.setCertificatenumber(personOptRequest.getCertificatenumber());
        queryOrgRequest.setUserid(personOptRequest.getUserid());
        queryOrgRequest.setUsername(personOptRequest.getPersonengname());
        List<TitanMainOrg> mainOrgList = mainOrgService.queryOrg(queryOrgRequest);
        List<OpenAccountPerson> openaccountpersons = new ArrayList<OpenAccountPerson>();
        if(CollectionUtils.isNotEmpty(mainOrgList)){
        	for(TitanMainOrg item : mainOrgList){
        		OpenAccountPerson entity = new OpenAccountPerson();
        		entity.setUserid(item.getOrgCode());
        		entity.setPersonchnname(item.getOrgName());
        		entity.setCertificatetype(item.getCertificatetype()+"");
        		entity.setCertificatenumber(item.getCertificateNumber());
        		entity.setRemark(item.getRemark());
        		entity.setStatusid(item.getStatusId().toString());
        		openaccountpersons.add(entity);
        	}
        }
        
        personOptResponse.setOpenaccountpersons(openaccountpersons);
		return toJson(personOptResponse);
	}
	
}
