package com.fangcang.titanjr.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dto.bean.TitanHelpDTO;
import com.fangcang.titanjr.dto.bean.TitanHelpTypeDTO;
import com.fangcang.titanjr.dto.request.HelpRequest;
import com.fangcang.titanjr.dto.request.QueryPageHelpTypeRequest;
import com.fangcang.titanjr.dto.request.QueryPageHelpWordRequest;
import com.fangcang.titanjr.dto.response.QueryHelpDetailResponse;
import com.fangcang.titanjr.dto.response.QueryPageHelpResponse;
import com.fangcang.titanjr.dto.response.QueryPageHelpTypeResponse;
import com.fangcang.titanjr.dto.response.QueryPageHelpWordResponse;
import com.fangcang.titanjr.service.TitanHelpService;

/**
 * 帮助控制器
 * @author luoqinglong
 * @2016年9月22日
 */
@Controller
@RequestMapping("/help")
public class HelpController extends BaseController {
	private static final int HELP_PAGE_SIZE = 10;
	
	@Resource
	private TitanHelpService helpService;
	
	/**
	 * 帮助中心首页
	 * @return
	 */
	@RequestMapping("/index")
	public String search(){
		return "/help/index";
	}
	
	/**
	 * 帮助搜索
	 * @param w 关键词
	 * @param pn 页码
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/search")
	public String search(String w,Integer pn, Model model) throws UnsupportedEncodingException{
		//w = new String(w.getBytes("iso8859-1"), "utf-8");
		if(pn==null||pn<1){
			pn = 1;
		}
		QueryPageHelpWordRequest queryPageHelpWordRequest = new QueryPageHelpWordRequest();
		queryPageHelpWordRequest.setCurrentPage(pn);
		queryPageHelpWordRequest.setWord(w);
		QueryPageHelpWordResponse res = helpService.queryPageHelpWord(queryPageHelpWordRequest);
		model.addAttribute("w", w);
		model.addAttribute("helpPage", res.getPage());
		return "/help/search";
	}
	/**
	 * 分类和该类问题列表
	 * @param ht helptype
	 * @param pn 当前页码
	 * @param model
	 * @return
	 */
	@RequestMapping("/help-list")
	public String helpList(Integer ht,Integer pn, Model model){
		if(ht==null||ht<1){
			return "error";
		}
		if(pn==null||pn<1){
			pn = 1;
		}
		//分类
		QueryPageHelpTypeRequest queryPageHelpTypeRequest = new QueryPageHelpTypeRequest();
		queryPageHelpTypeRequest.setPageSize(PaginationSupport.NO_SPLIT_PAGE_SIZE);
		QueryPageHelpTypeResponse helpTypeResponse = helpService.queryPageHelpType(queryPageHelpTypeRequest);
		
		//分类下的帮助列表
		QueryPageHelpResponse helpResponse = getPageHelpResponse(pn,ht,1);
		
		model.addAttribute("helpPage", helpResponse.getPage());
		model.addAttribute("helpTypeList", helpTypeResponse.getPage().getItemList());
		model.addAttribute("selectHelpType", findTitanHelpType(helpTypeResponse.getPage().getItemList(),ht));
		
		return "/help/help-list";
	}
	
//	@Deprecated
//	@RequestMapping("/help-type-table")
//	public String helpTypeTable(Integer ht,Integer pn, Model model){
//		if(ht==null||ht<1){
//			return "error";
//		}
//		if(pn==null||pn<1){
//			pn = 1;
//		}
//		//分类下的帮助列表
//		QueryPageHelpResponse helpResponse = getPageHelpResponse(pn,ht,1);
//		model.addAttribute("helpTypePage", helpResponse.getPage());
//		return "/help/help-type-table";
//	}
	
	private QueryPageHelpResponse getPageHelpResponse(Integer pn,Integer ht,Integer isShow){
		HelpRequest helpRequest = new HelpRequest();
		helpRequest.setCurrentPage(pn);
		helpRequest.setHelpType(ht);
		helpRequest.setPageSize(HELP_PAGE_SIZE);
		helpRequest.setIsShow(isShow);
		QueryPageHelpResponse helpResponse = helpService.queryPageHelp(helpRequest);
		return helpResponse;
	}
	
	/**
	 * 从list中查找目标
	 * @param list
	 * @param destHelpType
	 * @return
	 */
	private TitanHelpTypeDTO findTitanHelpType(List<TitanHelpTypeDTO> list,int destHelpType){
		if(list==null || list.size()==0){
			return null;
		}
		for(TitanHelpTypeDTO item : list ){
			if(item.getHelpType()==destHelpType){
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 帮助内容详情
	 * @param hid helpid
	 * @param model
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(Integer hid, Model model){
		if(hid==null||hid<1){
			return "error";
		}
		HelpRequest helpRequest = new HelpRequest();
		helpRequest.setHelpId(hid);
		QueryHelpDetailResponse detailResponse = helpService.queryHelpDetail(helpRequest);
		TitanHelpDTO titanHelpDTO = detailResponse.getTitanHelpDTO();
		if(titanHelpDTO!=null){
			//分类
			QueryPageHelpTypeRequest queryPageHelpTypeRequest = new QueryPageHelpTypeRequest();
			queryPageHelpTypeRequest.setPageSize(PaginationSupport.NO_SPLIT_PAGE_SIZE);
			QueryPageHelpTypeResponse helpTypeResponse = helpService.queryPageHelpType(queryPageHelpTypeRequest);
			model.addAttribute("helpTypeList", helpTypeResponse.getPage().getItemList());
			model.addAttribute("selectHelpType", findTitanHelpType(helpTypeResponse.getPage().getItemList(),titanHelpDTO.getHelpType()));
		}
		model.addAttribute("titanHelpDTO", titanHelpDTO);
		
		return "/help/detail";
	}
	
}
