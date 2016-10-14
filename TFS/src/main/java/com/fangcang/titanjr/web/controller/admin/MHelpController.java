package com.fangcang.titanjr.web.controller.admin;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.dto.request.HelpRequest;
import com.fangcang.titanjr.service.TitanHelpService;
import com.fangcang.titanjr.web.controller.BaseController;
import com.fangcang.titanjr.web.pojo.HelpPojo;
import com.fangcang.titanjr.web.pojo.HelpTypePojo;

/**
 * 帮助后台管理控制器
 * @author luoqinglong
 * @2016年10月13日
 */
@Controller
@RequestMapping("/admin")
public class MHelpController extends BaseController{
	private static final Log logger = LogFactory.getLog(MHelpController.class);
	
	@Resource
	private TitanHelpService titanHelpService;
	
	/**
	 * 保存问题(修改和新增)
	 * @param helpPojo
	 * @return
	 */
	@RequestMapping(value = "/save-help")
	public String saveHelp(HelpPojo helpPojo){
		
		HelpRequest helpRequest = new HelpRequest();
		
		helpRequest.setHelpTitle(helpPojo.getHelpTitle());
		helpRequest.setHelpContent(helpPojo.getHelpContent());
		
		if(helpPojo.getHelpId()!=null&&helpPojo.getHelpId()>0){
			//修改
			helpRequest.setHelpId(helpPojo.getHelpId());
			try {
				titanHelpService.updateHelp(helpRequest);
			} catch (GlobalServiceException e) {
			}
		}else{
			//新增
			try {
				titanHelpService.addHelp(helpRequest);
			} catch (GlobalServiceException e) {
				e.printStackTrace();
			}
		}
		
		
		return "";
	}
	
	/**
	 * 问题详细
	 * @return
	 */
	@RequestMapping(value = "/help-detail")
	public String helpDetail(){
		return "";
	}
	
	/***
	 * 保存问题分类(修改和新增)
	 * @param helpPojo
	 * @return
	 */
	@RequestMapping(value = "/save-help-type")
	public String saveHelpType(HelpTypePojo helpPojo){
		
		return "";
	}
	
	/**
	 * 问题分类详细
	 * @return
	 */
	@RequestMapping(value = "/help-type-detail")
	public String helpTypeDetail(){
		return "";
	}
	
}
