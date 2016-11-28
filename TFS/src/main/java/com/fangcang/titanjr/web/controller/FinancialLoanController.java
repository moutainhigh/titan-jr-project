package com.fangcang.titanjr.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	
	@RequestMapping(value="/applyLoanMain", method = RequestMethod.GET)
	public String applyLoanMain()
	{
		return "/loan/loan-apply/loan-apply";
	}
	
	@ResponseBody
	@RequestMapping(value="/loanApplyOrderNo")
	public Map<String,Object> getLoanApplyOrderNo(){
		return this.putSuccess("orderNo", OrderGenerateService.genLoanApplyOrderNo());
	}
	
	
	@RequestMapping(value="/upload")
	public void upload(@RequestParam(value = "compartment_contract", required = false) MultipartFile file,
			String loanApplyOrderNo,String fileName) throws IOException
	{
	
		getResponse().setCharacterEncoding("utf-8"); // 这里不设置编码会有乱码
		getResponse().setContentType("text/html;charset=utf-8");
		getResponse().setHeader("Cache-Control", "no-cache");

		PrintWriter out = getResponse().getWriter();
		
		boolean check = checkFileType(file.getOriginalFilename());
		if(!check){
			this.putSysError("文件格式不正确");
			out.print(toJson());
			out.flush();
			out.close();
			return;
		}
		

		String newName = this
				.getNewFileName(file.getOriginalFilename(), fileName);
		FtpUtil util = null;
		try {

			FTPConfigResponse configResponse = sysconfigService.getFTPConfig();

			util = new FtpUtil(configResponse.getFtpServerIp(),
					configResponse.getFtpServerPort(),
					configResponse.getFtpServerUser(),
					configResponse.getFtpServerPassword());

			util.ftpLogin();

			log.info("login ftp success fileName=" + newName);

			List<String> fileList = util
					.listFiles(FtpUtil.UPLOAD_PATH_LOAN_APPLY + "/"
							+ this.getUserId() + "/"+loanApplyOrderNo+"/");

			log.info("list ftp files fileList=" + fileList);

			// 检查文件是否已经上传过，如果上传过则需要把旧的文件先干掉，在上传新的哦 亲
			if (fileList != null) {
				for (int i = 0; i < fileList.size(); i++) {
					if (fileList.get(i).indexOf(fileName + ".") != -1) {
						util.deleteFile(FtpUtil.UPLOAD_PATH_LOAN_APPLY + "/"
								+ this.getUserId() + "/"+loanApplyOrderNo+"/" + fileList.get(i));
					}
				}
			}

			util.uploadStream(newName, file.getInputStream(),
					FtpUtil.UPLOAD_PATH_LOAN_APPLY + "/" + this.getUserId()
							+ "/"+loanApplyOrderNo+"/");

			log.info("upload to ftp success fileName=" + newName);

			util.ftpLogOut();

			this.putSuccess("fileName", newName);

		} catch (Exception e) {
			this.putSysError();
			log.error("", e);
		} finally {
			if (util != null) {
				try {
					util.ftpLogOut();
				} catch (Exception e) {
					this.putSysError();
					log.error("", e);
				}
			}
		}

		out.print(toJson());
		out.flush();
		out.close();
	}
	
	private String getNewFileName(String filename, String newName) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return newName + "." + filename.substring(dot + 1);
			}
		}
		return filename;
	}
	
	private boolean checkFileType(String filename){
		if(filename ==null || StringUtil.isValidString(filename)){
			return false;
		}
		int dot = filename.lastIndexOf('.');
		if ((dot > -1) && (dot < (filename.length() - 1))) {
			String suffix = filename.substring(dot + 1).toLowerCase();
			
			if(suffix.equals("pdf") || suffix.equals("jpg") || suffix.equals("jpeg") || suffix.equals("png") || suffix.equals("zip") ||suffix.equals("rar")){
				return true;
			}
		}
		return false;
	}
}
