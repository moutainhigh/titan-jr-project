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
 * 首页后台管理控制器
 * @author luoqinglong
 * @2016年11月15日
 */
@Controller
public class MIndexController extends BaseController{
	private static final Log logger = LogFactory.getLog(MIndexController.class);
	
	@Resource
	private TitanHelpService titanHelpService;
	
	/**
	 * 首台首页
	 * @return
	 */
	@RequestMapping("/admin/index")
	public String adminIndex(){
		
		return "admin-index";
	}
	
}
