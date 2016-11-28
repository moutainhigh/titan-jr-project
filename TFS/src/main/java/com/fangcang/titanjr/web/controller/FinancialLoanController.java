package com.fangcang.titanjr.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.dto.response.FTPConfigResponse;
import com.fangcang.titanjr.service.TitanSysconfigService;
import com.fangcang.util.StringUtil;

/**
 * 贷款请求控制器
 * @author wengxitao
 */
@Controller
@RequestMapping("loan")
public class FinancialLoanController extends BaseController
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory
			.getLog(FinancialLoanCreditController.class);
	
	@Resource
	private TitanSysconfigService sysconfigService;
	
	
	/**
	 * 进入贷款主页
	 */
	@RequestMapping(value = "/loanMain", method = RequestMethod.GET)
	public String loanMain()
	{
		return "/loan/loan-main";
	}
	
}
